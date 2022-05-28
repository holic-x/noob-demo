package com.guigu.library.back.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.guigu.library.back.ui.control.ReaderClassifyManagerJPanel;
import com.guigu.library.model.ReaderClassify;
import com.guigu.library.service.ReaderClassifyService;
import com.guigu.library.service.impl.ReaderClassifyServiceImpl;
import com.guigu.library.utils.DataValidation;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;

public class UpdateReaderClassifyJFrame extends JFrame implements MouseListener {
	// 定义全局组件
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_num, label_name, label_maximum, label_time_limit;
	JTextField num, name, maximum, time_limit;
	JButton save_button, reset_button;

	ReaderClassifyService readerClassifyService = new ReaderClassifyServiceImpl();
	// 定义父对象
	ReaderClassifyManagerJPanel parentPanel;
	JTable parentTable;
	int selectedRow;

	// 通过构造方法初始化数据
	public UpdateReaderClassifyJFrame(ReaderClassifyManagerJPanel parentPanel,
			JTable parentTable, int selectedRow) {
		this.parentPanel = parentPanel;
		this.parentTable = parentTable;
		this.selectedRow = selectedRow;
		// 初始化背景
		initBackgroundPanel();
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("修改读者分类信息");
		this.setSize(420, 350);
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
		backgroundPanel.add(titlePanel, BorderLayout.NORTH);
		backgroundPanel.add(contentPanel, BorderLayout.CENTER);
		backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * 初始化标题面板
	 */
	private void initTitlePanel() {
		titlePanel = new JPanel();
		JLabel title = new JLabel("修改读者分类信息");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * 初始化内容面板
	 */
	private void initContentPanel() {
		contentPanel = new JPanel();
		JPanel jp1 = new JPanel();
		label_num = new JLabel("分类编号");
		num = new JTextField(15);
		num.setFont(MyFont.JTextFieldFont);
		num.setForeground(MyColor.JTextFieldColor);
		num.setEditable(false);
		jp1.add(label_num);
		jp1.add(num);

		JPanel jp2 = new JPanel();
		label_name = new JLabel("分类名称");
		name = new JTextField(15);
		name.setFont(MyFont.JTextFieldFont);
		name.setForeground(MyColor.JTextFieldColor);
		name.setEditable(false);
		jp2.add(label_name);
		jp2.add(name);

		JPanel jp3 = new JPanel();
		label_maximum = new JLabel("最大数量");
		maximum = new JTextField(15);
		maximum.setFont(MyFont.JTextFieldFont);
		maximum.setForeground(MyColor.JTextFieldColor);
		jp3.add(label_maximum);
		jp3.add(maximum);

		JPanel jp4 = new JPanel();
		label_time_limit = new JLabel("借阅期限");
		time_limit = new JTextField(15);
		time_limit.setFont(MyFont.JTextFieldFont);
		time_limit.setForeground(MyColor.JTextFieldColor);
		jp4.add(label_time_limit);
		jp4.add(time_limit);

		// 数据回显
		this.echoData();
		Box ver = Box.createVerticalBox();
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp3);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp4);
		// 将组件加载到contentPanel面板中
		contentPanel.add(ver);
	}

	/**
	 * 初始化按钮面板
	 */
	private void initButtonPanel() {
		Box hor = Box.createHorizontalBox();
		buttonPanel = new JPanel();
		save_button = new JButton("保存");
		save_button.setForeground(Color.white);
		save_button.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.blue));

		reset_button = new JButton("重置");
		reset_button.setForeground(Color.white);
		reset_button.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.red));

		// 添加按钮监听
		save_button.addMouseListener(this);
		reset_button.addMouseListener(this);

		// 将按钮加载到面板中
		hor.add(save_button);
		hor.add(Box.createHorizontalStrut(20));
		hor.add(reset_button);
		buttonPanel.add(hor);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == save_button) {
			// 获取当前文本框的数据信息
			String num_string = num.getText();
			String name_string = name.getText();
			String maximum_string = maximum.getText();
			String time_limit_string = time_limit.getText();
			if (num_string.equals("")) {
				JOptionPane.showMessageDialog(null, "图书分类编号不能为空");
			} else if (name_string.equals("")) {
				JOptionPane.showMessageDialog(null, "图书分类名称不能为空");
			} else if (maximum_string.equals("")) {
				JOptionPane.showMessageDialog(null, "图书最大借阅数不能为空");
			} else if (time_limit_string.equals("")) {
				JOptionPane.showMessageDialog(null, "图书借阅期限不能为空");
			} else {
				// 对数据进行验证
				if (maximum_string.length() > 3
						|| time_limit_string.length() > 3) {
					JOptionPane.showMessageDialog(null, "输入正整数长度不能超出3！");
				} else if (!DataValidation.isSignlessInteger(maximum_string)
						|| !DataValidation.isSignlessInteger(time_limit_string)) {
					JOptionPane
							.showMessageDialog(null, "输入格式错误，请输入不超过三位数的正整数！");
				} else {
					int num_int = Integer.valueOf(num_string);
					int maximum_int = Integer.valueOf(maximum_string);
					int time_limit_int = Integer.valueOf(time_limit_string);
					// 创建ReaderClassify对象，将数据保存到数据库中
					ReaderClassify rc = new ReaderClassify(num_int,
							name_string, maximum_int, time_limit_int);
					int choose = JOptionPane.showConfirmDialog(null,
							"确认修改读者分类信息？");
					if (choose == 0) {
						try {
							readerClassifyService.updateReaderClassify(rc);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						// 完成数据的保存之后输出提示信息，并将子页面隐藏，刷新主面板的数据
						JOptionPane.showMessageDialog(null, "图书分类信息保存成功");
						this.setVisible(false);
						parentPanel.refreshTablePanel();
					} else {
						this.setVisible(false);
					}
				}
			}
		} else if (e.getSource() == reset_button) {
			// 回显数据
			this.echoData();
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
	 * 定义数据回显方法echoData
	 */
	public void echoData() {
		// 根据当前选中的数据记录进行信息回显
		String num_string = parentTable.getValueAt(selectedRow, 0).toString();
		String name_string = parentTable.getValueAt(selectedRow, 1).toString();
		String maximum_string = parentTable.getValueAt(selectedRow, 2)
				.toString();
		String time_limit_string = parentTable.getValueAt(selectedRow, 3)
				.toString();
		num.setText(num_string);
		num.setToolTipText(num_string);
		name.setText(name_string);
		name.setToolTipText(name_string);
		maximum.setText(maximum_string);
		maximum.setToolTipText(maximum_string);
		time_limit.setText(time_limit_string);
		time_limit.setToolTipText(time_limit_string);
	}
}