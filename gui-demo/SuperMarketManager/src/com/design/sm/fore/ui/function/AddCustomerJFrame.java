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

public class AddCustomerJFrame extends JFrame implements MouseListener,FocusListener{
	// 定义全局组件
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_name, label_address, label_phone, label_email, label_integrate,
			label_balance,label_consume_class;
	JTextField name, address, phone, email, integrate, balance;
	JComboBox consume_class;
	
	JButton save_button, reset_button;

	CustomerService customerService = new CustomerServiceImpl();
	ConsumeClassService consumeClassService = new ConsumeClassServiceImpl();
	
	// 定义父对象
	CustomerManagerJPanel parentPanel;

	// 通过构造方法初始化数据
	public AddCustomerJFrame(CustomerManagerJPanel parentPanel) {
		this.parentPanel = parentPanel;
		// 初始化背景
		initBackgroundPanel();
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("添加顾客信息");
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
		JLabel title = new JLabel("添加顾客信息");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * 初始化内容面板
	 */
	private void initContentPanel() {
		/**
		 * 添加顾客的信息：
		 * 顾客id：随机32char
		 * 顾客地址、联系方式、邮箱、当前积分、当前余额、消费等级
		 */
		contentPanel = new JPanel();
		JPanel jp1 = new JPanel();
		label_name = new JLabel("顾客名称");
		name = new JTextField(15);
		name.setFont(MyFont.TipFont);
		name.setForeground(MyColor.TipColor);
		name.setText("必填项");
		name.addFocusListener(this);
		jp1.add(label_name);
		jp1.add(name);

		JPanel jp2 = new JPanel();
		label_address = new JLabel("顾客地址");
		address = new JTextField(15);
		address.setFont(MyFont.TipFont);
		address.setForeground(MyColor.TipColor);
		address.setText("可选填");
		address.addFocusListener(this);
		jp2.add(label_address);
		jp2.add(address);

		JPanel jp3 = new JPanel();
		label_phone = new JLabel("联系方式");
		phone = new JTextField(15);
		phone.setFont(MyFont.TipFont);
		phone.setForeground(MyColor.TipColor);
		phone.setText("必填项");
		phone.addFocusListener(this);
		jp3.add(label_phone);
		jp3.add(phone);

		JPanel jp4 = new JPanel();
		label_email = new JLabel("电子邮箱");
		email = new JTextField(15);
		email.setFont(MyFont.TipFont);
		email.setForeground(MyColor.TipColor);
		email.setText("可选填");
		email.addFocusListener(this);
		jp4.add(label_email);
		jp4.add(email);

		JPanel jp5 = new JPanel();
		label_integrate = new JLabel("消费积分");
		integrate = new JTextField(15);
		integrate.setFont(MyFont.TipFont);
		integrate.setForeground(MyColor.TipColor);
		integrate.setText("可选填");
		integrate.addFocusListener(this);
		jp5.add(label_integrate);
		jp5.add(integrate);

		JPanel jp6 = new JPanel();
		label_balance = new JLabel("消费余额");
		balance = new JTextField(15);
		balance.setFont(MyFont.TipFont);
		balance.setForeground(MyColor.TipColor);
		balance.setText("可选填");
		balance.addFocusListener(this);
		jp6.add(label_balance);
		jp6.add(balance);
		
		JPanel jp7 = new JPanel();
		label_consume_class = new JLabel("顾客等级");
		consume_class = new JComboBox();
		consume_class.setPreferredSize(new Dimension(175, 30));
		// 加载消费等级
		List<ConsumeClass> list_class = null;
		try {
			list_class = consumeClassService.findAllConsumeClassList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(ConsumeClass cc : list_class){
			int cid = cc.getClass_id();
			String cname = cc.getClass_name();
			Item item = new Item(cid+"",cname);
			consume_class.addItem(item);
		}
		jp7.add(label_consume_class);
		jp7.add(consume_class);

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
			if (address_string.equals("可选填")) {
				address_string = "空";
			}
			if (email_string.equals("可选填")) {
				email_string = "空";
			}
			if (integrate_string.equals("可选填")) {
				integrate_string = "0";
			}
			if (balance_string.equals("可选填")) {
				balance_string = "0.00";
			}
			if (name_string.equals("必填项")) {
				JOptionPane.showMessageDialog(null, "顾客名称不能为空");
			} else if(phone_string.equals("必填项")){
				JOptionPane.showMessageDialog(null, "顾客联系方式不能为空");
			}else {
				// 验证数据的合法性
				if(!DataValidation.isSignlessInteger(integrate_string)){
					JOptionPane.showMessageDialog(null, "消费积分输入格式有误，请输入正整数！");
				}else if(!DataValidation.isBigDecimal(balance_string)){
					JOptionPane.showMessageDialog(null, "消费余额输入格式有误，请输入整数，可保留两位小数！");
				}else{
					int integrate_int = Integer.valueOf(integrate_string);
					double balance_double = Double.valueOf(balance_string);
					// 获取消费等级的下拉框选项
					Item item = (Item)consume_class.getSelectedItem();
					int ccId = Integer.valueOf(item.getKey());
					// 创建Customer对象，将数据保存到数据库中
					// 用户id是随机生成的32位字符拼接的数据
					String id = RandomGeneration.getRandom32charSeq();
					Customer customer = new Customer(id,name_string,address_string,phone_string,
							email_string,integrate_int,balance_double,ccId);
					int choose = JOptionPane.showConfirmDialog(null, "确认添加顾客信息？");
					if (choose == 0) {
						try {
							customerService.addCustomer(customer);
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
			// 清空所有的数据
			name.setFont(MyFont.TipFont);
			name.setForeground(MyColor.TipColor);
			name.setText("必填项");
			address.setFont(MyFont.TipFont);
			address.setForeground(MyColor.TipColor);
			address.setText("可选填");
			phone.setFont(MyFont.TipFont);
			phone.setForeground(MyColor.TipColor);
			phone.setText("必填项");
			email.setFont(MyFont.TipFont);
			email.setForeground(MyColor.TipColor);
			email.setText("可选填");
			integrate.setFont(MyFont.TipFont);
			integrate.setForeground(MyColor.TipColor);
			integrate.setText("可选填");
			balance.setFont(MyFont.TipFont);
			balance.setForeground(MyColor.TipColor);
			balance.setText("可选填");
			consume_class.setSelectedIndex(0);
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

	// 聚焦事件
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == name) {
			if (name.getText().equals("必填项")) {
				name.setFont(MyFont.JTextFieldFont);
				name.setForeground(MyColor.JTextFieldColor);
				name.setText("");
			}
		} else if (e.getSource() == address) {
			if (address.getText().equals("可选填")) {
				address.setFont(MyFont.JTextFieldFont);
				address.setForeground(MyColor.JTextFieldColor);
				address.setText("");
			}
		} else if (e.getSource() == phone) {
			if (phone.getText().equals("必填项")) {
				phone.setFont(MyFont.JTextFieldFont);
				phone.setForeground(MyColor.JTextFieldColor);
				phone.setText("");
			}
		} else if (e.getSource() == email) {
			if (email.getText().equals("可选填")) {
				email.setFont(MyFont.JTextFieldFont);
				email.setForeground(MyColor.JTextFieldColor);
				email.setText("");
			}
		} else if (e.getSource() == integrate) {
			if (integrate.getText().equals("可选填")) {
				integrate.setFont(MyFont.JTextFieldFont);
				integrate.setForeground(MyColor.JTextFieldColor);
				integrate.setText("");
			}
		} else if (e.getSource() == balance) {
			if (balance.getText().equals("可选填")) {
				balance.setFont(MyFont.JTextFieldFont);
				balance.setForeground(MyColor.JTextFieldColor);
				balance.setText("");
			}
		}
	}

	// 失去焦点事件
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == name) {
			if (name.getText().equals("")) {
				name.setFont(MyFont.TipFont);
				name.setForeground(MyColor.TipColor);
				name.setText("必填项");
			}
		} else if (e.getSource() == address) {
			if (address.getText().equals("")) {
				address.setFont(MyFont.TipFont);
				address.setForeground(MyColor.TipColor);
				address.setText("可选填");
			}
		} else if (e.getSource() == phone) {
			if (phone.getText().equals("")) {
				phone.setFont(MyFont.TipFont);
				phone.setForeground(MyColor.TipColor);
				phone.setText("必填项");
			}
		} else if (e.getSource() == email) {
			if (email.getText().equals("")) {
				email.setFont(MyFont.TipFont);
				email.setForeground(MyColor.TipColor);
				email.setText("可选填");
			}
		} else if (e.getSource() == integrate) {
			if (integrate.getText().equals("")) {
				integrate.setFont(MyFont.TipFont);
				integrate.setForeground(MyColor.TipColor);
				integrate.setText("可选填");
			}
		} else if (e.getSource() == balance) {
			if (balance.getText().equals("")) {
				balance.setFont(MyFont.TipFont);
				balance.setForeground(MyColor.TipColor);
				balance.setText("可选填");
			}
		}
	}
}