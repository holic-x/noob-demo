package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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

import com.design.sm.fore.ui.control.ConsumeClassManagerJPanel;
import com.design.sm.model.Accounts;
import com.design.sm.model.ConsumeClass;
import com.design.sm.service.AccountsService;
import com.design.sm.service.ConsumeClassService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.ConsumeClassServiceImpl;
import com.design.sm.utils.DataValidation;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;

public class UpdateConsumeClassJFrame extends JFrame implements MouseListener {

	// 定义全局组件
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_class_id, label_class_name, label_class_off,
			label_class_discount;
	JTextField class_id, class_name, class_off, class_discount;
	JButton save_button, reset_button;
	AccountsService userService = new AccountsServiceImpl();

	// 得到屏幕大小
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// 定义父对象、表格、选中行
	ConsumeClassManagerJPanel parentPanel;
	JTable table;
	int selectedRow;

	ConsumeClassService consumeClassService = new ConsumeClassServiceImpl();

	// 通过构造方法初始化数据
	public UpdateConsumeClassJFrame(ConsumeClassManagerJPanel parentPanel,
			JTable table, int selectedRow) {
		this.parentPanel = parentPanel;
		this.table = table;
		this.selectedRow = selectedRow;
		// 初始化背景
		initBackgroundPanel();
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("消费等级修改");
		this.setSize(425, 325);
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
		JLabel title = new JLabel("个人账号修改");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * 初始化内容面板
	 */
	private void initContentPanel() {
		contentPanel = new JPanel();

		JPanel jp1 = new JPanel();
		label_class_id = new JLabel("消费等级");
		class_id = new JTextField(15);
		class_id.setFont(MyFont.JTextFieldFont);
		class_id.setForeground(MyColor.JTextFieldColor);
		class_id.setEditable(false);
		jp1.add(label_class_id);
		jp1.add(class_id);

		JPanel jp2 = new JPanel();
		label_class_name = new JLabel("等级名称");
		class_name = new JTextField(15);
		class_name.setFont(MyFont.JTextFieldFont);
		class_name.setForeground(MyColor.JTextFieldColor);
		jp2.add(label_class_name);
		jp2.add(class_name);

		JPanel jp3 = new JPanel();
		label_class_off = new JLabel("等级优惠");
		class_off = new JTextField(15);
		class_off.setFont(MyFont.JTextFieldFont);
		class_off.setForeground(MyColor.JTextFieldColor);
		jp3.add(label_class_off);
		jp3.add(class_off);

		JPanel jp4 = new JPanel();
		label_class_discount = new JLabel("等级折扣");
		class_discount = new JTextField(15);
		class_discount.setFont(MyFont.JTextFieldFont);
		class_discount.setForeground(MyColor.JTextFieldColor);
		jp4.add(label_class_discount);
		jp4.add(class_discount);

		// 回显数据信息
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
			String id_string = table.getValueAt(selectedRow, 0).toString();
			String name_string = class_name.getText();
			String off_string = class_off.getText();
			String discount_string = class_discount.getText();
			if (name_string.equals("")) {
				JOptionPane.showMessageDialog(null, "消费等级名称不能为空");
			} else if (off_string.equals("")) {
				JOptionPane.showMessageDialog(null, "等级优惠不能为空，请输入正整数！");
			} else if (discount_string.equals("")) {
				JOptionPane.showMessageDialog(null, "等级折扣不能为空，可保留两位小数");
			} else {
				// 判断输入的等级优惠和等级折扣数据是否合法
				if (!DataValidation.isSignlessInteger(off_string)) {
					JOptionPane.showMessageDialog(null, "等级优惠输入格式有误！");
				} else if (!DataValidation.isSmallDecimal(discount_string)) {
					JOptionPane.showMessageDialog(null, "等级折扣输入格式有误！");
				} else {
					// 根据消费等级id修改等级信息
					int id_int = Integer.valueOf(id_string);
					int off_int = Integer.valueOf(off_string);
					double discount_double = Double.valueOf(discount_string);
					ConsumeClass cc = new ConsumeClass(id_int, name_string,
							off_int, discount_double);
					int choose = JOptionPane.showConfirmDialog(null,
							"确认修改消费等级信息？");
					if (choose == 0) {
						try {
							consumeClassService.updateConsumeClass(cc);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						// 完成数据的保存之后输出提示信息，并将子页面隐藏，刷新主面板的数据
						JOptionPane.showMessageDialog(null, "等级信息修改成功!");
						parentPanel.refreshTablePanel();
						this.setVisible(false);
					} 
				}
			}
		} else if (e.getSource() == reset_button) {
			// 清空所有的数据，回显数据信息
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
	 * 回显数据的方法
	 */
	public void echoData() {
		// 根据选中的表格数据返回相应的信息
		String class_id_string = table.getValueAt(selectedRow, 0).toString();
		String class_name_string = table.getValueAt(selectedRow, 1).toString();
		String class_off_string = table.getValueAt(selectedRow, 2).toString();
		String class_discount_string = table.getValueAt(selectedRow, 3)
				.toString();
		class_id.setText(class_id_string);
		class_id.setToolTipText(class_id_string);
		class_name.setText(class_name_string);
		class_name.setToolTipText(class_name_string);
		class_off.setText(class_off_string);
		class_off.setToolTipText(class_off_string);
		class_discount.setText(class_discount_string);
		class_discount.setToolTipText(class_discount_string);
	}

}
