package com.guigu.library.back.ui.control;

import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.guigu.library.model.Users;
import com.guigu.library.service.UsersService;
import com.guigu.library.service.impl.UsersServiceImpl;
import com.guigu.library.utils.ImagePanel;
import com.guigu.library.utils.MyFont;

public class BackLoginJFrame extends JFrame implements MouseListener,
		FocusListener, KeyListener {
	// 定义全局组件
	private JPanel backgroundPanel = null;
	private JTextField username = null;
	private JPasswordField password = null;
	private JLabel login_label = null;
	private JLabel reset_label = null;
	// 定义相应的service
	private UsersService usersService = new UsersServiceImpl();

	// 在构造方法中初始化组件并设置布局
	public BackLoginJFrame() {
		// 设置窗体标题、图标
		try {
			Image logoImage = null;
			logoImage = ImageIO.read(new File("icons/loginImage/logo.png"));
			this.setIconImage(logoImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 加载背景图片
		Image backgroungImage = null;
		try {
			backgroungImage = ImageIO.read(new File(
					"icons/loginImage/loginBackgroundImage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 初始化背景面板并设置布局管理器为null
		backgroundPanel = new ImagePanel(backgroungImage);
		backgroundPanel.setLayout(null);
		// 添加用户名、密码文本框
		username = new JTextField(15);
		username.setFont(MyFont.JTableFont);
		username.setText("**");
		username.setBounds(460, 231, 245, 45);
		password = new JPasswordField(15);
		password.setFont(MyFont.JTableFont);
		password.setText("密码");
		password.setBounds(460, 300, 245, 45);

		// 登录注册按钮
		Icon login_image = new ImageIcon("icons/loginImage/loginImage.png");
		login_label = new JLabel(login_image);
		login_label.setToolTipText("登录");
		login_label.setBounds(400, 375, 64, 64);

		Icon reset_image = new ImageIcon("icons/loginImage/resetImage.png");
		reset_label = new JLabel(reset_image);
		reset_label.setToolTipText("重置");
		reset_label.setBounds(550, 375, 64, 64);

		// 为组件添加监听事件：文本框添加焦点监听事件、按钮添加鼠标点击监听事件
		username.addFocusListener(this);
		password.addFocusListener(this);
		login_label.addMouseListener(this);
		reset_label.addMouseListener(this);

		// 将所有的组件加载到背景面板
		backgroundPanel.add(username);
		backgroundPanel.add(password);
		backgroundPanel.add(login_label);
		backgroundPanel.add(reset_label);

		// 选择一个组件，添加键盘监听,如果遇到点击键盘无反应的情况，则在代码适当的地方
		// 设置焦点，添加xxx.setFocusable(true)语句即可
		this.setFocusable(true);
		this.addKeyListener(this);
		username.addKeyListener(this);
		password.addKeyListener(this);
		
		// 将背景面板装载到窗体中，并设置窗体的属性
		this.add(backgroundPanel);
		// 设置窗体的相关属性
		this.setTitle("后台管理");// 设置窗体标题
		this.setSize(1060, 600);// 设置窗体大小
		this.setVisible(true);// 设置可见性
		this.setLocationRelativeTo(null);// 设置依赖项
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置关闭方式
		this.setResizable(false);// 设置禁止最大化
		this.requestFocus();// 设置格式化窗体
	}

	/**
	 * 获取焦点事件
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == username) {
			if (username.getText().equals("**"))
				username.setText("");
		} else if (e.getSource() == password) {
			if (password.getText().equals("密码")) {
				password.setText("");
				password.setEchoChar('*');// 设置密码字符
			}
		}
	}

	/**
	 * 失去焦点事件
	 */
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == username) {
			if (username.getText().equals(""))
				username.setText("**");
		} else if (e.getSource() == password) {
			if (password.getText().equals("")) {
				password.setText("密码");
				password.setEchoChar('\0');// 设置字符集
			}
		}
	}

	/**
	 * 添加鼠标点击事件
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == login_label) {
			if (username.getText().equals("**")) {
				JOptionPane.showMessageDialog(null, "用户名/账号不能为空！");
			} else if (password.getText().equals("密码")) {
				JOptionPane.showMessageDialog(null, "密码不能为空！");
			} else {
				// 获取数据进行封装
				String username_string = username.getText();
				String password_string = password.getText();
				Users user = new Users();
				user.setUser_name(username_string);
				user.setUser_password(password_string);
				Users findUser = null;
				try {
					findUser = usersService.loginUsers(user);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				// 判断是否登录成功，进入到主页面
				if (findUser != null) {
					// 判断当前账号是否激活(0表示账号未激活不可使用)
					int limit = 0;
					try {
						limit = Integer.valueOf(String.valueOf(usersService
								.getUsersLimits(findUser.getUser_id())));
					} catch (NumberFormatException | SQLException e1) {
						e1.printStackTrace();
					}
					if (limit == 0) {
						JOptionPane.showMessageDialog(null,
								"抱歉！当前账号尚未激活，处于禁用状态！不可使用！");
					} else if (limit == 3) {
						JOptionPane.showMessageDialog(null, "抱歉！您当前没有权限访问该模块！");
					} else {
						// 隐藏当前的页面，进入到主页面
						this.setVisible(false);
						new BackIndexJFrame(findUser);
					}
				} else {
					JOptionPane.showMessageDialog(null, "用户名或密码错误！");
				}
			}
		} else if (e.getSource() == reset_label) {
			username.setText("**");
			password.setText("密码");
			password.setEchoChar('\0');
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

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			// 如果按下了enter键，登录
			if (username.getText().equals("**")) {
				JOptionPane.showMessageDialog(null, "用户名/账号不能为空！");
			} else if (password.getText().equals("密码")) {
				JOptionPane.showMessageDialog(null, "密码不能为空！");
			} else {
				// 获取数据进行封装
				String username_string = username.getText();
				String password_string = password.getText();
				Users user = new Users();
				user.setUser_name(username_string);
				user.setUser_password(password_string);
				Users findUser = null;
				try {
					findUser = usersService.loginUsers(user);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				// 判断是否登录成功，进入到主页面
				if (findUser != null) {
					// 判断当前账号是否激活(0表示账号未激活不可使用)
					int limit = 0;
					try {
						limit = Integer.valueOf(String.valueOf(usersService
								.getUsersLimits(findUser.getUser_id())));
					} catch (NumberFormatException | SQLException e1) {
						e1.printStackTrace();
					}
					if (limit == 0) {
						JOptionPane.showMessageDialog(null,
								"抱歉！当前账号尚未激活，处于禁用状态！不可使用！");
					} else if (limit == 3) {
						JOptionPane.showMessageDialog(null, "抱歉！您当前没有权限访问该模块！");
					} else {
						// 隐藏当前的页面，进入到主页面
						this.setVisible(false);
						new BackIndexJFrame(findUser);
					}
				} else {
					JOptionPane.showMessageDialog(null, "用户名或密码错误！");
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
