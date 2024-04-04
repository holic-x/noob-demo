package com.guigu.library.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
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

import com.guigu.library.fore.ui.control.ForeIndexJFrame;
import com.guigu.library.fore.ui.control.ForeLoginJFrame;
import com.guigu.library.model.Users;
import com.guigu.library.service.UsersService;
import com.guigu.library.service.impl.UsersServiceImpl;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;

public class UpdateForeUserJFrame extends JFrame implements FocusListener,
		MouseListener {

	// 定义全局组件
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_name, label_pwd_old, label_pwd_new, label_limits;
	JTextField name, pwd_old, pwd_new, limits;
	JButton save_button, reset_button;
	UsersService userService = new UsersServiceImpl();

	// 得到屏幕大小
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// 定义父对象、当前登录用户
	ForeIndexJFrame parentPanel;
	Users loginUser;

	// 通过构造方法初始化数据
	public UpdateForeUserJFrame(ForeIndexJFrame parentPanel, Users loginUser) {
		this.parentPanel = parentPanel;
		this.loginUser = loginUser;
		// 初始化背景
		initBackgroundPanel();
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("个人账号修改");
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
		/**
		 * 修改账号的内容： 账号id（随机生成10位的字符数据） 不可改动 用户名、用户密码、用户权限
		 */
		contentPanel = new JPanel();

		JPanel jp1 = new JPanel();
		label_name = new JLabel("账号名称");
		name = new JTextField(15);
		name.setFont(MyFont.JTextFieldFont);
		name.setForeground(MyColor.JTextFieldColor);
		jp1.add(label_name);
		jp1.add(name);

		JPanel jp2 = new JPanel();
		label_pwd_old = new JLabel("原始密码");
		pwd_old = new JTextField(15);
		pwd_old.setFont(MyFont.TipFont);
		pwd_old.setForeground(MyColor.TipColor);
		pwd_old.setText("请输入原始密码进行确认");
		pwd_old.addFocusListener(this);
		jp2.add(label_pwd_old);
		jp2.add(pwd_old);

		JPanel jp3 = new JPanel();
		label_pwd_new = new JLabel("新的密码");
		pwd_new = new JTextField(15);
		pwd_new.setFont(MyFont.TipFont);
		pwd_new.setForeground(MyColor.TipColor);
		pwd_new.setText("请输入新的密码");
		pwd_new.addFocusListener(this);
		jp3.add(label_pwd_new);
		jp3.add(pwd_new);

		JPanel jp4 = new JPanel();
		label_limits = new JLabel("用户权限");
		limits = new JTextField(15);
		limits.setFont(MyFont.JTextFieldFont);
		limits.setForeground(MyColor.JTextFieldColor);
		limits.setEditable(false);
		jp4.add(label_limits);
		jp4.add(limits);

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
			String name_string = name.getText();
			String pwd_old_string = pwd_old.getText();
			String pwd_new_string = pwd_new.getText();
			if (name_string.equals("")) {
				JOptionPane.showMessageDialog(null, "用户昵称不能为空");
			} else if (pwd_old_string.equals("请输入原始密码进行确认")) {
				JOptionPane.showMessageDialog(null, "原始密码不能为空");
			} else if (pwd_new_string.equals("请输入新的密码")) {
				JOptionPane.showMessageDialog(null, "新的密码不能为空");
			} else {
				// 判断用户输入的密码是否与原始密码相同，如果不相同则做出操作失败提示
				String confirm_pwd = this.loginUser.getUser_password();
				if (!pwd_old_string.equals(confirm_pwd)) {
					JOptionPane.showMessageDialog(null, "原始密码输入有误，请确认后重新操作！");
				} else {
					try {
						if (userService.isValidUsersname(name_string)
								|| name_string.equals(this.loginUser
										.getUser_name())) {
							// 创建Users对象，将数据保存到数据库中
							// 用户id、用户权限不可轻易改变
							String id = this.loginUser.getUser_id();
							int limit = this.loginUser.getUser_limits();
							Users user = new Users(id, name_string,
									pwd_new_string, limit);
							int choose = JOptionPane.showConfirmDialog(null,
									"确认修改账号信息？");
							if (choose == 0) {
								try {
									userService.updateUsers(user);
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								// 完成数据的保存之后输出提示信息，并将子页面隐藏，刷新主面板的数据
								JOptionPane
										.showMessageDialog(null, "账号信息修改成功!");
								this.setVisible(false);
							} else {
								this.setVisible(false);
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"当前用户昵称已存在，换一个试试吧！");
						}
					} catch (HeadlessException | SQLException e1) {
						e1.printStackTrace();
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
		// 根据用户的账号id获取当前用户的账号信息
		String name_string = this.loginUser.getUser_name();
		int limit = this.loginUser.getUser_limits();
		name.setText(name_string);
		if (limit == 1) {
			limits.setText("超级管理员");
		} else if (limit == 2) {
			limits.setText("图书馆管理员");
		} else if (limit == 3) {
			limits.setText("读者");
		} else if (limit == 0) {
			limits.setText("禁用账户");
		}
		pwd_old.setText("请输入原始密码进行确认");
		pwd_new.setText("请输入新的密码");
	}

	/**
	 * 聚焦事件
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == pwd_old) {
			if (pwd_old.getText().equals("请输入原始密码进行确认")) {
				pwd_old.setFont(MyFont.JTextFieldFont);
				pwd_old.setForeground(MyColor.JTextFieldColor);
				pwd_old.setText("");
			}
		} else if (e.getSource() == pwd_new) {
			if (pwd_new.getText().equals("请输入新的密码")) {
				pwd_new.setFont(MyFont.JTextFieldFont);
				pwd_new.setForeground(MyColor.JTextFieldColor);
				pwd_new.setText("");
			}
		}
	}

	/**
	 * 失去焦点事件
	 */
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == pwd_old) {
			if (pwd_old.getText().equals("")) {
				pwd_old.setFont(MyFont.TipFont);
				pwd_old.setForeground(MyColor.TipColor);
				pwd_old.setText("请输入原始密码进行确认");
			}
		} else if (e.getSource() == pwd_new) {
			if (pwd_new.getText().equals("")) {
				pwd_new.setFont(MyFont.TipFont);
				pwd_new.setForeground(MyColor.TipColor);
				pwd_new.setText("请输入新的密码");
			}
		}
	}
}
