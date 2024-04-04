package com.design.sm.back.ui.funciton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.back.ui.control.AccountsMangerJPanel;
import com.design.sm.model.Accounts;
import com.design.sm.service.AccountsService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.RandomGeneration;

public class AddAccountsJFrame extends JFrame implements MouseListener,
		FocusListener {

	// 定义全局组件
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_name, label_password, label_limits;
	JTextField name, password;
	JComboBox limits;
	JButton save_button, reset_button;

	int user_limits = 0;
	AccountsService userService = new AccountsServiceImpl();

	// 得到屏幕大小
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// 定义父对象
	AccountsMangerJPanel parentPanel;

	// 通过构造方法初始化数据
	public AddAccountsJFrame(AccountsMangerJPanel parentPanel) {
		this.parentPanel = parentPanel;
		// 初始化背景
		initBackgroundPanel();
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("添加账号信息");
		this.setSize(425, 290);
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
		JLabel title = new JLabel("添加账号信息");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * 初始化内容面板
	 */
	private void initContentPanel() {
		/**
		 * 添加账号的内容： 账号id（随机生成10位的字符数据） 用户名、用户密码、用户权限
		 */
		contentPanel = new JPanel();
		JPanel jp1 = new JPanel();
		label_name = new JLabel("用户昵称");
		name = new JTextField(15);
		name.setFont(MyFont.TipFont);
		name.setForeground(MyColor.TipColor);
		name.setText("必填项");
		name.addFocusListener(this);
		jp1.add(label_name);
		jp1.add(name);

		JPanel jp2 = new JPanel();
		label_password = new JLabel("用户密码");
		password = new JTextField(15);
		password.setFont(MyFont.TipFont);
		password.setForeground(MyColor.TipColor);
		password.setText("必填项");
		password.addFocusListener(this);
		jp2.add(label_password);
		jp2.add(password);

		JPanel jp3 = new JPanel();
		label_limits = new JLabel("用户权限");
		limits = new JComboBox();
		limits.setPreferredSize(new Dimension(175, 30));
		// 初始化下拉框
		limits.addItem("禁用");
		limits.addItem("超级管理员");
		limits.addItem("经理或主管");
		limits.addItem("普通员工");
		limits.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getSource() == limits) {
					if (limits.getSelectedIndex() == 0) {
						user_limits = 0;
					} else if (limits.getSelectedIndex() == 1) {
						user_limits = 1;
					} else if (limits.getSelectedIndex() == 2) {
						user_limits = 2;
					} else if (limits.getSelectedIndex() == 3) {
						user_limits = 3;
					}
				}
			}
		});
		jp3.add(label_limits);
		jp3.add(limits);

		Box ver = Box.createVerticalBox();
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp3);

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
			String password_string = password.getText();
			boolean isValid = false;
			try {
				//判断用户昵称是否有效
				isValid = userService.isValidAccountsname(name_string);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			if(!isValid){
				JOptionPane.showMessageDialog(null, "用户昵称已存在！");
			}else if (name_string.equals("必填项")) {
				JOptionPane.showMessageDialog(null, "用户昵称不能为空");
			} else if (password_string.equals("必填项")) {
				JOptionPane.showMessageDialog(null, "用户密码不能为空");
			} else {
				// 创建Accounts对象，将数据保存到数据库中
				// 用户id是随机生成的10位字符拼接的数据
				String id = RandomGeneration.getRandom10charSeq();
				Accounts user = new Accounts(id,name_string,password_string,user_limits);
				int choose = JOptionPane.showConfirmDialog(null, "确认添加用户信息？");
				if (choose == 0) {
					try {
						userService.addAccounts(user);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// 完成数据的保存之后输出提示信息，并将子页面隐藏，刷新主面板的数据
					JOptionPane.showMessageDialog(null, "商品信息保存成功");
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
			password.setFont(MyFont.TipFont);
			password.setForeground(MyColor.TipColor);
			password.setText("必填项");
			limits.setSelectedIndex(0);
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
				name.setText("");
			}
		} else if (e.getSource() == password) {
			if (password.getText().equals("必填项")) {
				password.setText("");
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
			} else {
				name.setForeground(MyColor.JTextFieldColor);
				name.setFont(MyFont.JTextFieldFont);
			}
		} else if (e.getSource() == password) {
			if (password.getText().equals("")) {
				password.setFont(MyFont.TipFont);
				password.setForeground(MyColor.TipColor);
				password.setText("必填项");
			} else {
				password.setForeground(MyColor.JTextFieldColor);
				password.setFont(MyFont.JTextFieldFont);
			}
		}
	}
}
