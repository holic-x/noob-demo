package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.fore.ui.control.CustomerManagerJPanel;
import com.design.sm.model.ConsumeClass;
import com.design.sm.model.Customer;
import com.design.sm.service.ConsumeClassService;
import com.design.sm.service.CustomerService;
import com.design.sm.service.impl.ConsumeClassServiceImpl;
import com.design.sm.service.impl.CustomerServiceImpl;
import com.design.sm.utils.DataValidation;
import com.design.sm.utils.Item;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.RandomGeneration;

public class UpdateCustomerJFrame extends JFrame implements MouseListener {
	// 定义全局组件
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_name, label_address, label_phone, label_email,
			label_integrate, label_balance, label_consume_class;
	JTextField name, address, phone, email, integrate, balance;
	JComboBox consume_class;

	JButton save_button, reset_button;

	CustomerService customerService = new CustomerServiceImpl();
	ConsumeClassService consumeClassService = new ConsumeClassServiceImpl();

	// 定义父对象
	CustomerManagerJPanel parentPanel;
	JTable table;
	int selectedRow;

	// 通过构造方法初始化数据
	public UpdateCustomerJFrame(CustomerManagerJPanel parentPanel,
			JTable table, int selectedRow) {
		this.parentPanel = parentPanel;
		this.table = table;
		this.selectedRow = selectedRow;
		// 初始化背景
		initBackgroundPanel();
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("修改顾客信息");
		this.setSize(420, 450);
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
		JLabel title = new JLabel("修改顾客信息");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * 初始化内容面板
	 */
	private void initContentPanel() {
		/**
		 * 修改顾客的信息： 顾客id：随机32char（顾客的id不能够随意更改） 顾客地址、联系方式、邮箱、当前积分、当前余额、消费等级
		 */
		contentPanel = new JPanel();
		JPanel jp1 = new JPanel();
		label_name = new JLabel("顾客名称");
		name = new JTextField(15);
		name.setFont(MyFont.JTextFieldFont);
		name.setForeground(MyColor.JTextFieldColor);
		jp1.add(label_name);
		jp1.add(name);

		JPanel jp2 = new JPanel();
		label_address = new JLabel("顾客地址");
		address = new JTextField(15);
		address.setFont(MyFont.JTextFieldFont);
		address.setForeground(MyColor.JTextFieldColor);
		jp2.add(label_address);
		jp2.add(address);

		JPanel jp3 = new JPanel();
		label_phone = new JLabel("联系方式");
		phone = new JTextField(15);
		phone.setFont(MyFont.JTextFieldFont);
		phone.setForeground(MyColor.JTextFieldColor);
		jp3.add(label_phone);
		jp3.add(phone);

		JPanel jp4 = new JPanel();
		label_email = new JLabel("电子邮箱");
		email = new JTextField(15);
		email.setFont(MyFont.JTextFieldFont);
		email.setForeground(MyColor.JTextFieldColor);
		jp4.add(label_email);
		jp4.add(email);

		JPanel jp5 = new JPanel();
		label_integrate = new JLabel("消费积分");
		integrate = new JTextField(15);
		integrate.setFont(MyFont.JTextFieldFont);
		integrate.setForeground(MyColor.JTextFieldColor);
		jp5.add(label_integrate);
		jp5.add(integrate);

		JPanel jp6 = new JPanel();
		label_balance = new JLabel("消费余额");
		balance = new JTextField(15);
		balance.setFont(MyFont.JTextFieldFont);
		balance.setForeground(MyColor.JTextFieldColor);
		jp6.add(label_balance);
		jp6.add(balance);

		JPanel jp7 = new JPanel();
		label_consume_class = new JLabel("顾客等级");
		consume_class = new JComboBox();
		consume_class.setPreferredSize(new Dimension(175, 30));
		jp7.add(label_consume_class);
		jp7.add(consume_class);

		// 回显数据
		this.echoData();
		
		Box ver = Box.createVerticalBox();
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp3);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp4);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp5);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp6);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp7);
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
			String name_string = name.getText();
			String address_string = address.getText();
			String phone_string = phone.getText();
			String email_string = email.getText();
			String integrate_string = integrate.getText();
			String balance_string = balance.getText();

			// 如果选填部分的内容均为空，相应地设置为空字符串，避免在插入数据到数据库的时候出现差错
			if (address_string.equals("")) {
				address_string = "空";
			}
			if (email_string.equals("")) {
				email_string = "空";
			}
			if (integrate_string.equals("")) {
				integrate_string = "0";
			}
			if (balance_string.equals("")) {
				balance_string = "0.00";
			}
			if (name_string.equals("")) {
				JOptionPane.showMessageDialog(null, "顾客名称不能为空");
			} else if (phone_string.equals("")) {
				JOptionPane.showMessageDialog(null, "顾客联系方式不能为空");
			} else {
				// 验证数据的合法性
				if (!DataValidation.isSignlessInteger(integrate_string)) {
					JOptionPane.showMessageDialog(null, "消费积分输入格式有误，请输入正整数！");
				} else if (!DataValidation.isBigDecimal(balance_string)) {
					JOptionPane.showMessageDialog(null,
							"消费余额输入格式有误，请输入正数，可保留两位小数！");
				} else {
					int integrate_int = Integer.valueOf(integrate_string);
					double balance_double = Double.valueOf(balance_string);
					// 获取消费等级的下拉框选项
					Item item = (Item) consume_class.getSelectedItem();
					int ccId = Integer.valueOf(item.getKey());
					// 创建Customer对象，将数据保存到数据库中
					// 用户id是随机生成的32位字符拼接的数据,在修改的时候不能够随意更改
					String id = table.getValueAt(selectedRow,0).toString();
					Customer customer = new Customer(id, name_string,
							address_string, phone_string, email_string,
							integrate_int, balance_double, ccId);
					int choose = JOptionPane.showConfirmDialog(null,
							"确认修改顾客信息？");
					if (choose == 0) {
						try {
							customerService.updateCustomer(customer);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						// 完成数据的保存之后输出提示信息，并将子页面隐藏，刷新主面板的数据
						JOptionPane.showMessageDialog(null, "顾客信息保存成功");
						this.setVisible(false);
						parentPanel.refreshTablePanel();
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
	 * 定义数据回显方法
	 */
	public void echoData() {
		// 从选中的表格数据中获取相应的顾客信息
		String name_string = table.getValueAt(selectedRow, 1).toString();
		String address_string = table.getValueAt(selectedRow, 2).toString();
		String phone_string = table.getValueAt(selectedRow, 3).toString();
		String email_string = table.getValueAt(selectedRow, 4).toString();
		String integrate_string = table.getValueAt(selectedRow, 5).toString();
		String balance_string = table.getValueAt(selectedRow, 6).toString();
		String class_id_string = table.getValueAt(selectedRow, 7).toString();

		name.setText(name_string);
		address.setText(address_string);
		phone.setText(phone_string);
		email.setText(email_string);
		integrate.setText(integrate_string);
		balance.setText(balance_string);
		// 重新加载消费等级
		consume_class.removeAllItems();
		List<ConsumeClass> list_class = null;
		try {
			list_class = consumeClassService.findAllConsumeClassList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < list_class.size(); i++) {
			int sign = 0;
			int cid = list_class.get(i).getClass_id();
			String cname = list_class.get(i).getClass_name();
			Item item = new Item(cid + "", cname);
			consume_class.addItem(item);
			if (class_id_string.equals(cid + "")) {
				sign = i;
				consume_class.setSelectedIndex(sign);
			}
		}
	}
}