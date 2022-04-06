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
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.model.Vendor;
import com.design.sm.service.VendorService;
import com.design.sm.service.impl.VendorServiceImpl;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.RandomGeneration;

public class AddVendorJFrame extends JFrame implements MouseListener,
		FocusListener {
	// 定义全局组件
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_name, label_phone, label_email, label_fax, label_address;
	JTextField name, phone, email, fax, address;
	JButton save_button, reset_button;

	VendorService vendorService = new VendorServiceImpl();

	// 得到屏幕大小
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// 定义父对象
	VendorManagerJFrame parentPanel;

	// 通过构造方法初始化数据
	public AddVendorJFrame(VendorManagerJFrame parentPanel) {
		this.parentPanel = parentPanel;
		// 初始化背景
		initBackgroundPanel();
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("添加供应商信息");
		this.setSize(420, 370);
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
		JLabel title = new JLabel("添加供应商信息");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * 初始化内容面板
	 */
	private void initContentPanel() {
		/**
		 * 添加供应商的信息： 供应商id：随机生成的10位的字符数据 供应商名称：不为空 负责人、联系方式、邮箱、传真、地址可选填
		 * 之后再存入相关的数据
		 */
		contentPanel = new JPanel();
		JPanel jp1 = new JPanel();
		label_name = new JLabel("公司名称");
		name = new JTextField(15);
		name.setFont(MyFont.TipFont);
		name.setForeground(MyColor.TipColor);
		name.setText("必填项");
		name.addFocusListener(this);
		jp1.add(label_name);
		jp1.add(name);

		JPanel jp2 = new JPanel();
		label_phone = new JLabel("联系方式");
		phone = new JTextField(15);
		phone.setFont(MyFont.TipFont);
		phone.setForeground(MyColor.TipColor);
		phone.setText("可选填");
		phone.addFocusListener(this);
		jp2.add(label_phone);
		jp2.add(phone);

		JPanel jp3 = new JPanel();
		label_email = new JLabel("电子邮箱");
		email = new JTextField(15);
		email.setFont(MyFont.TipFont);
		email.setForeground(MyColor.TipColor);
		email.setText("可选填");
		email.addFocusListener(this);
		jp3.add(label_email);
		jp3.add(email);

		JPanel jp4 = new JPanel();
		label_fax = new JLabel("传真号码");
		fax = new JTextField(15);
		fax.setFont(MyFont.TipFont);
		fax.setForeground(MyColor.TipColor);
		fax.setText("可选填");
		fax.addFocusListener(this);
		jp4.add(label_fax);
		jp4.add(fax);

		JPanel jp5 = new JPanel();
		label_address = new JLabel("公司地址");
		address = new JTextField(15);
		address.setFont(MyFont.TipFont);
		address.setForeground(MyColor.TipColor);
		address.setText("可选填");
		address.addFocusListener(this);
		jp5.add(label_address);
		jp5.add(address);

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
			String phone_string = phone.getText();
			String email_string = email.getText();
			String fax_string = fax.getText();
			String address_string = address.getText();

			// 如果选填部分的内容均为空，相应地设置为空字符串，避免在插入数据到数据库的时候出现差错
			if (phone_string.equals("可选填")) {
				phone_string = "空";
			}
			if (email_string.equals("可选填")) {
				email_string = "空";
			}
			if (fax_string.equals("可选填")) {
				fax_string = "空";
			}
			if (address_string.equals("可选填")) {
				address_string = "空";
			}
			if (name_string.equals("必填项")) {
				JOptionPane.showMessageDialog(null, "供应商名称不能为空");
			} else {
				// 创建Vendor对象，将数据保存到数据库中
				// 用户id是随机生成的32位字符拼接的数据
				String id = RandomGeneration.getRandom32charSeq();
				Vendor vendor = new Vendor(id, name_string, phone_string,
						email_string, fax_string, address_string);
				int choose = JOptionPane.showConfirmDialog(null, "确认添加供应商信息？");
				if (choose == 0) {
					try {
						vendorService.addVendor(vendor);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// 完成数据的保存之后输出提示信息，并将子页面隐藏，刷新主面板的数据
					JOptionPane.showMessageDialog(null, "供应商信息保存成功");
					this.setVisible(false);
					parentPanel.refreshTablePanel();
				} else {
					this.setVisible(false);
				}
			}
		} else if (e.getSource() == reset_button) {
			// 清空所有的数据
			name.setFont(MyFont.TipFont);
			name.setForeground(MyColor.TipColor);
			name.setText("必填项");
			phone.setFont(MyFont.TipFont);
			phone.setForeground(MyColor.TipColor);
			phone.setText("可选填");
			email.setFont(MyFont.TipFont);
			email.setForeground(MyColor.TipColor);
			email.setText("可选填");
			fax.setFont(MyFont.TipFont);
			fax.setForeground(MyColor.TipColor);
			fax.setText("可选填");
			address.setFont(MyFont.TipFont);
			address.setForeground(MyColor.TipColor);
			address.setText("可选填");
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
				name.setForeground(MyColor.JTextFieldColor);
				name.setFont(MyFont.JTextFieldFont);
				name.setText("");
			}
		} else if (e.getSource() == phone) {
			if (phone.getText().equals("可选填")) {
				phone.setForeground(MyColor.JTextFieldColor);
				phone.setFont(MyFont.JTextFieldFont);
				phone.setText("");
			}
		} else if (e.getSource() == email) {
			if (email.getText().equals("可选填")) {
				email.setForeground(MyColor.JTextFieldColor);
				email.setFont(MyFont.JTextFieldFont);
				email.setText("");
			}
		} else if (e.getSource() == fax) {
			if (fax.getText().equals("可选填")) {
				fax.setForeground(MyColor.JTextFieldColor);
				fax.setFont(MyFont.JTextFieldFont);
				fax.setText("");
			}
		} else if (e.getSource() == address) {
			if (address.getText().equals("可选填")) {
				address.setForeground(MyColor.JTextFieldColor);
				address.setFont(MyFont.JTextFieldFont);
				address.setText("");
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
		} else if (e.getSource() == phone) {
			if (phone.getText().equals("")) {
				phone.setFont(MyFont.TipFont);
				phone.setForeground(MyColor.TipColor);
				phone.setText("可选填");
			}
		} else if (e.getSource() == email) {
			if (email.getText().equals("")) {
				email.setFont(MyFont.TipFont);
				email.setForeground(MyColor.TipColor);
				email.setText("可选填");
			}
		} else if (e.getSource() == fax) {
			if (fax.getText().equals("")) {
				fax.setFont(MyFont.TipFont);
				fax.setForeground(MyColor.TipColor);
				fax.setText("可选填");
			}
		} else if (e.getSource() == address) {
			if (address.getText().equals("")) {
				address.setFont(MyFont.TipFont);
				address.setForeground(MyColor.TipColor);
				address.setText("可选填");
			}
		}
	}
}
