package com.guigu.library.export.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.guigu.library.back.ui.control.ReaderArchivesManagerJPanel;
import com.guigu.library.service.BooksService;
import com.guigu.library.service.impl.BooksServiceImpl;
import com.guigu.library.utils.MyFont;

public class ReaderExportCheckBoxJFrame extends JFrame implements MouseListener {

	// 定义全局组件
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;

	ButtonGroup select;
	JRadioButton all, cancel;
	JCheckBox barcode, academic_num, name, sex, birthday, idCard, phone, email,
			account, classify, libraryCard, descr;

	JCheckBox[] checkBox = { barcode, academic_num, name, sex, birthday,
			idCard, phone, email, account, classify, libraryCard, descr };

	String[] names = { "条形码  ", "学工编号", "读者姓名", "读者性别", "出生日期", "身份证号", "联系方式",
			"电子邮箱", "所属分类", "使用账号", "借阅证号", "读者简述" };
	int[] flags = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	int[] column = { 1, 2, 3, 4, 5, 6, 7, 8, 13, 11, 15, 9 };// 对应表格中的列
	JButton ensure, exit;

	// 定义父对象、父表格、选中数据
	ReaderArchivesManagerJPanel parentPanel;
	JTable parentTable;
	int[] selectedRows;

	// 定义service
	BooksService booksService = new BooksServiceImpl();

	// 通过构造方法初始化数据
	public ReaderExportCheckBoxJFrame(ReaderArchivesManagerJPanel parentPanel,
			JTable parentTable, int[] selectedRows) {
		this.parentPanel = parentPanel;
		this.parentTable = parentTable;
		this.selectedRows = selectedRows;
		// 初始化背景
		initBackgroundPanel();
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("数据导出选择");
		this.setSize(450, 500);
		this.setVisible(true);
		this.setLocationRelativeTo(null);// 设置依赖项
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);// 设置关闭方式
		// 当前窗口隐藏，不影响后方数据的使用，而不是关闭整个窗口
	}

	private void initBackgroundPanel() {
		backgroundPanel = new JPanel(new BorderLayout());
		initTitlePanel();
		initContentPanel();
		initButtonPanel();
		backgroundPanel.validate();
	}

	/**
	 * 初始化标题面板
	 */
	private void initTitlePanel() {
		titlePanel = new JPanel();
		JLabel title = new JLabel("数据导出选择");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
		backgroundPanel.add(titlePanel, BorderLayout.NORTH);
	}

	/**
	 * 初始化内容面板
	 */
	private void initContentPanel() {

		contentPanel = new JPanel();

		JPanel selectPanel = new JPanel();
		select = new ButtonGroup();
		all = new JRadioButton("全选");
		all.addMouseListener(this);
		cancel = new JRadioButton("取消");
		cancel.addMouseListener(this);
		select.add(all);
		select.add(cancel);
		selectPanel.add(all);
		selectPanel.add(cancel);

		// 初始化复选框(一一对应)
		for (int i = 0; i < checkBox.length; i++) {
			checkBox[i] = new JCheckBox(names[i]);
		}

		// 每3个放置一行
		JPanel jp1 = new JPanel();
		jp1.add(checkBox[0]);
		jp1.add(checkBox[1]);
		jp1.add(checkBox[2]);

		JPanel jp2 = new JPanel();
		jp2.add(checkBox[3]);
		jp2.add(checkBox[4]);
		jp2.add(checkBox[5]);

		JPanel jp3 = new JPanel();
		jp3.add(checkBox[6]);
		jp3.add(checkBox[7]);
		jp3.add(checkBox[8]);

		JPanel jp4 = new JPanel();
		jp4.add(checkBox[9]);
		jp4.add(checkBox[10]);
		jp4.add(checkBox[11]);

		Box ver = Box.createVerticalBox();
		ver.add(selectPanel);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp3);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp4);
		// 将组件加载到contentPanel面板中
		contentPanel.add(ver);
		backgroundPanel.add(contentPanel, BorderLayout.CENTER);
	}

	/**
	 * 初始化按钮面板
	 */
	private void initButtonPanel() {
		buttonPanel = new JPanel();

		ensure = new JButton("确认");
		ensure.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));
		ensure.setForeground(Color.white);
		ensure.setFont(MyFont.JButtonFont);
		ensure.addMouseListener(this);

		exit = new JButton("取消");
		exit.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));
		exit.setForeground(Color.white);
		exit.setFont(MyFont.JButtonFont);
		exit.addMouseListener(this);

		buttonPanel.add(ensure);
		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(exit);
		backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == all) {
			// 默认选择所有的内容
			for (int i = 0; i < checkBox.length; i++) {
				checkBox[i].setSelected(true);
			}
		} else if (e.getSource() == cancel) {
			// 默认取消所有选择
			for (int i = 0; i < checkBox.length; i++) {
				checkBox[i].setSelected(false);
			}
		} else if (e.getSource() == ensure) {
			// 获取用户选择，修改标识
			for (int i = 0; i < checkBox.length; i++) {
				if (checkBox[i].isSelected()) {
					// 修改标识
					flags[i] = 1;
				}
			}
			int choose = JOptionPane.showConfirmDialog(null, "确认导出选中数据？");
			if (choose == 0) {
				// 集合转数组
				String[] ids;
				ArrayList id_list = new ArrayList<>();
				for (int rowindex : parentTable.getSelectedRows()) {
					Object obj = parentTable.getValueAt(rowindex, 0);
					id_list.add(obj);
				}
				// 集合转数组
				ids = (String[]) id_list.toArray(new String[id_list.size()]);
				int result = this.exportData(ids);
				if (result == 1) {
					JOptionPane.showMessageDialog(null, "数据导出成功！");
					this.setVisible(false);
				} else if (result == -1) {
					JOptionPane.showMessageDialog(null, "抱歉！数据导出失败，再试一遍吧！");
				} else if (result == 0) {
					JOptionPane.showMessageDialog(null, "用户取消了操作！");
				}
			}
		} else if (e.getSource() == exit) {
			this.setVisible(false);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	/**
	 * 定义导出数据的方法
	 */
	public int exportData(String[] ids) {
		try {
			// 创建工作目录
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("data");
			// 创建数据行
			XSSFRow row = sheet.createRow((short) 0);
			XSSFCell cell = null;

			// 封装选中的数据信息(一一对应)
			List<String> name_string = new ArrayList<String>();
			List<Integer> column_int = new ArrayList<Integer>();
			for (int i = 0; i < flags.length; i++) {
				if (flags[i] == 1) {
					name_string.add(names[i]);
					column_int.add(column[i]);
				}
			}

			// 获取选中的列，通过选中的列执行数据导出
			for (int i = 0; i < name_string.size(); i++) {
				// 创建第一行表头数据
				cell = row.createCell((short) i);
				cell.setCellValue(name_string.get(i));
			}

			// 根据选中的行、列导出数据，如果没有选中行则默认是导出当前表格的所有数据的内容
			if (selectedRows.length != 0) {
				// 导出选中的数据
				for (int i = 0; i < selectedRows.length; i++) {
					row = sheet.createRow(i + 1);
					for (int j = 0; j < name_string.size(); j++) {
						// 创建每一行数据
						cell = row.createCell((short) j);
						cell.setCellValue(parentTable.getValueAt(
								selectedRows[i], column_int.get(j)).toString());
					}
				}
			} else {
				// 导出当前表格的所有数据
				for (int i = 0; i < parentTable.getRowCount(); i++) {
					row = sheet.createRow(i + 1);
					for (int j = 0; j < name_string.size(); j++) {
						// 创建每一行数据
						cell = row.createCell((short) j);
						cell.setCellValue(parentTable.getValueAt(i,
								column_int.get(j)).toString());
					}
				}
			}
			// 弹出文件选择框
			JFileChooser chooser = new JFileChooser();
			// 后缀名过滤器
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"表格文件(*.xlsx)", "xlsx");
			chooser.setFileFilter(filter);
			// 下面的方法将阻塞，直到【用户按下保存按钮且“文件名”文本框不为空】或【用户按下取消按钮】
			int option = chooser.showSaveDialog(null);
			if (option == JFileChooser.APPROVE_OPTION) { // 假如用户选择了保存
				File file = chooser.getSelectedFile();
				String fname = chooser.getName(file); // 从文件名输入框中获取文件名
				// 假如用户填写的文件名不带我们制定的后缀名，那么我们给它添上后缀
				if (fname.indexOf(".xlsx") == -1) {
					file = new File(chooser.getCurrentDirectory(), fname
							+ ".xlsx");
				}
				try {
					FileOutputStream FOut = new FileOutputStream(file);
					workbook.write(FOut);
					FOut.flush();
					FOut.close();
					workbook.close();
					return 1;
				} catch (IOException e) {
					System.err.println("IO异常");
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}
}