package com.design.sm.back.ui.control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.design.sm.model.Accounts;
import com.design.sm.service.PurchaseNoteService;
import com.design.sm.service.SoldNoteService;
import com.design.sm.service.impl.PurchaseNoteServiceImpl;
import com.design.sm.service.impl.SoldNoteServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.Tools;
import com.eltima.components.ui.DatePicker;

public class BalanceJPanel extends JFrame implements ItemListener,
		MouseListener {

	// 定义全局组件
	JPanel backgroundPanel, topPanel, toolPanel, searchPanel, tablePanel;
	DatePicker start_time, end_time;
	// 借助工具类完成表格数据的封装
	BaseTableModule baseTableModule;
	JLabel tool_export, tool_count;
	JTable table;
	JScrollPane jScrollPane;
	// 定义相应的文本框、组合按钮
	ButtonGroup state;
	JRadioButton income, pay;
	// 定义相应的service
	SoldNoteService soldNoteService = new SoldNoteServiceImpl();
	PurchaseNoteService purchaseNoteService = new PurchaseNoteServiceImpl();

	Accounts loginUser;

	/**
	 * 通过构造方法完成初始化
	 */
	public BalanceJPanel(Accounts loginUser) {
		backgroundPanel = new JPanel(new BorderLayout());
		this.loginUser = loginUser;
		// 初始化布局
		initTopPanel();// 初始化顶部菜单条
		initTablePanel();// 初始化显示的表格数据
	}

	/**
	 * 初始化顶部的菜单条
	 */
	private void initTopPanel() {
		topPanel = new JPanel(new BorderLayout());
		// 初始化工具条面板
		initToolPanel();
		// 初始化查找面板
		initSearchPanel();
		// 将顶部菜单栏加载到背景面板中
		backgroundPanel.add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * 初始化工具条面板
	 */
	private void initToolPanel() {
		toolPanel = new JPanel();
		Icon export_icon = new ImageIcon("icons/toolImage/export.png");
		tool_export = new JLabel(export_icon);
		tool_export.setToolTipText("数据导出");
		tool_export.addMouseListener(this);

		Icon count_icon = new ImageIcon("icons/toolImage/count.png");
		tool_count = new JLabel(count_icon);
		tool_count.setToolTipText("数据统计");
		tool_count.addMouseListener(this);

		state = new ButtonGroup();
		Icon income_icon = new ImageIcon("icons/toolImage/income.png");
		income = new JRadioButton(income_icon);
		income.setToolTipText("收入记录");
		income.addItemListener(this);

		Icon pay_icon = new ImageIcon("icons/toolImage/pay.png");
		pay = new JRadioButton(pay_icon);
		pay.setToolTipText("支出记录");
		pay.addItemListener(this);

		state.add(income);
		state.add(pay);
		toolPanel.add(tool_export);
		toolPanel.add(tool_count);
		toolPanel.add(income);
		toolPanel.add(pay);
		// 最终将工具条面板加载到顶部菜单条的最西面
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * 初始化查找面板 设置查找方式： 根据订单时间进行查找
	 */
	private void initSearchPanel() {
		searchPanel = new JPanel();

		Icon start_icon = new ImageIcon("icons/toolImage/start_time.png");
		JLabel label_start = new JLabel(start_icon);
		start_time = new DatePicker();
		Icon end_icon = new ImageIcon("icons/toolImage/start_time.png");
		JLabel label_end = new JLabel(end_icon);
		end_time = new DatePicker();

		searchPanel.add(label_start);
		searchPanel.add(start_time);
		searchPanel.add(Box.createHorizontalStrut(50));
		searchPanel.add(label_end);
		searchPanel.add(end_time);
		// 将布局好的组件加载到菜单栏面板的最东面
		topPanel.add(searchPanel, BorderLayout.EAST);
	}

	/**
	 * 初始化显示的表格数据
	 */
	private void initTablePanel() {
		// 要根据下拉框的选项进行筛选数据(要根据productService中返回的行进行设置，随后选择要隐藏的项目即可)
		String[] params = { "订单编号", "处理时间", "实际金额", "支付方式编号", "支付方式" };
		Vector<Vector> vec = new Vector<>();
		// 获取时间框的选择
		String start_time_string = start_time.getText();
		String end_time_string = end_time.getText();
		if (income.isSelected()) {
			if (start_time_string.equals("") && end_time_string.equals("")) {
				try {
					vec = soldNoteService.pack(soldNoteService
							.findAllSoldNote());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				// 根据时间筛选待处理

			}
		} else if (pay.isSelected()) {
			if (start_time_string.equals("") && end_time_string.equals("")) {
				try {
					vec = purchaseNoteService.pack(purchaseNoteService
							.findAllPurchaseNote());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				// 根据时间筛选待处理

			}
		}
		// 将查询到的数据封装到BbaseTableModule中
		baseTableModule = new BaseTableModule(params, vec);
		table = new JTable(baseTableModule);
		// 渲染第0列，将其显示为多选框进行显示
		table.getColumnModel().getColumn(0)
				.setCellRenderer(new TableCellRenderer() {
					@Override
					public Component getTableCellRendererComponent(
							JTable table, Object value, boolean isSelected,
							boolean hasFocus, int row, int column) {
						JCheckBox ck = new JCheckBox();
						ck.setSelected(isSelected);
						ck.setHorizontalAlignment((int) 0.5f);
						return ck;
					}
				});
		// 利用提供的Tools类美化表格
		Tools.setTableStyle(table);

		// 通过设置行的大小，隐藏某一列内容（只显示想要显示的数据）
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) table
				.getColumnModel();
		// 隐藏：0 1 6 7 8 12 13 14 16 17 18 19 21
		// dcm.getColumn(0).setMinWidth(0);
		// dcm.getColumn(0).setMaxWidth(0);
		dcm.getColumn(3).setMinWidth(0);
		dcm.getColumn(3).setMaxWidth(0);

		// 设置滚动条
		jScrollPane = new JScrollPane(table);
		Tools.setJspStyle(jScrollPane);

		tablePanel = new JPanel(new BorderLayout());
		tablePanel.setOpaque(false);// 设置透明度
		tablePanel.add(jScrollPane);
		// 将组件加载到背景中
		backgroundPanel.add(tablePanel, BorderLayout.CENTER);
		// 在刷新数据的时候改变窗体大小，完成数据刷新
		backgroundPanel.validate();
	}

	/**
	 * 刷新数据面板
	 */
	public void refreshTablePanelBySearch() {
		// 移除当前数据面板中的所有数据
		backgroundPanel.removeAll();
		initTopPanel();
		initTablePanel();
		backgroundPanel.validate();// 验证
	}

	// /**
	// * 根据时间选择框的内容筛选数据信息
	// */
	// public Vector<Vector> getSelectContentByTime(Vector<Vector> vec){
	// Vector<Vector> newRows = new Vector<>();
	// String start_time_string = start_time.getText();
	// String end_time_string = end_time.getText();
	// for(int i=0;i<)
	// if(start_time_string.equals("")){
	//
	// }
	// return vec;
	// }

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if (income.isSelected()) {
				income.setBackground(new Color(192, 190, 204));
				pay.setBackground(new Color(250, 250, 250));
				backgroundPanel.remove(tablePanel);
				initTablePanel();
				backgroundPanel.validate();// 验证
			} else if (pay.isSelected()) {
				pay.setBackground(new Color(192, 190, 204));
				income.setBackground(new Color(250, 250, 250));
				backgroundPanel.remove(tablePanel);
				initTablePanel();
				backgroundPanel.validate();// 验证
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tool_export) {
			int result = this.exportData();
			if (result == 0) {
				JOptionPane.showMessageDialog(null, "用户取消了操作！");
			} else if (result == 1) {
				JOptionPane.showMessageDialog(null, "数据导出成功");
			} else if (result == -1) {
				JOptionPane.showMessageDialog(null, "抱歉，数据导出失败，再试试看吧！");
			}
		} else if (e.getSource() == tool_count) {
			String str = this.getAmount();
			// 进行数据的统计
			if (income.isSelected()) {
				JOptionPane.showMessageDialog(null, "总收入："+str);
			} else if (pay.isSelected()) {
				JOptionPane.showMessageDialog(null, "总支出："+str);
			}
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
	 * 自定义导出数据
	 */
	public int exportData() {
		try {
			// 创建工作目录
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("data");
			// 创建数据行
			XSSFRow row = sheet.createRow((short) 0);
			XSSFCell cell = null;
			// 创建第一行表头数据
			cell = row.createCell((short) 0);
			cell.setCellValue("订单编号");
			cell = row.createCell((short) 1);
			cell.setCellValue("处理时间");
			cell = row.createCell((short) 2);
			cell.setCellValue("实际金额");
			cell = row.createCell((short) 3);
			cell.setCellValue("支付方式编号");
			cell = row.createCell((short) 4);
			cell.setCellValue("支付方式");
			/**
			 * 默认是将全部数据导出 可以通过用户选择相应的数据进行导出
			 */
			for (int i = 0; i < table.getRowCount(); i++) {
				row = sheet.createRow(i + 1);
				cell = row.createCell(0);
				cell.setCellValue(table.getValueAt(i, 0).toString());
				cell = row.createCell(1);
				cell.setCellValue(table.getValueAt(i, 1).toString());
				cell = row.createCell(2);
				cell.setCellValue(table.getValueAt(i, 2).toString());
				cell = row.createCell(3);
				cell.setCellValue(table.getValueAt(i, 3).toString());
				cell = row.createCell(4);
				cell.setCellValue(table.getValueAt(i, 4).toString());
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

	/**
	 * 创建数据统计方法，统计当前选中的数据的所有数据的总额， 如果没有选中的内容，则默认统计当前表格所有的内容
	 */
	public String getAmount() {
		// 获取当前选中的数据
//		String[] ids;
//		ArrayList id_list = new ArrayList<>();
//		for (int rowindex : table.getSelectedRows()) {
//			Object obj = table.getValueAt(rowindex, 0);
//			id_list.add(obj);
//		}
//		// 集合转数组
//		ids = (String[]) id_list.toArray(new String[id_list.size()]);
		String str = "";
		double amount = 0.00;
		int[] rows = table.getSelectedRows();
		if(rows.length!=0){
			//表示选中了数据
			for(int i=0;i<rows.length;i++){
				amount += Double.valueOf(table.getValueAt(rows[i], 2).toString());
			}
			str= "当前选中的记录的数目为："+rows.length+"--总额共计："+amount;
		}else{
			// 默认统计表格数据
			for(int i=0;i<table.getRowCount();i++){
				amount += Double.valueOf(table.getValueAt(i, 2).toString());
			}
			str= "当前表中所有的记录数目为："+table.getRowCount()+"--总额共计："+amount;
		}
		return str;
	}
}
