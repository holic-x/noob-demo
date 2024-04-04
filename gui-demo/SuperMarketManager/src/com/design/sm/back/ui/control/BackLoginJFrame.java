package com.design.sm.back.ui.control;

import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.model.Accounts;
import com.design.sm.model.Employee;
import com.design.sm.service.AccountsService;
import com.design.sm.service.EmployeeService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.EmployeeServiceImpl;
import com.design.sm.utils.ImagePanel;
import com.design.sm.utils.MyFont;

public class BackLoginJFrame extends JFrame implements MouseListener,FocusListener{
	//定义全局组件
	private JPanel backgroundPanel = null;
	private JTextField username = null;
	private JPasswordField password = null;
	private JButton login_button = null;
	private JButton reset_button = null;
	//定义相应的service
	private AccountsService accountsService = new AccountsServiceImpl();
	private EmployeeService employeeService = new EmployeeServiceImpl();
	//在构造方法中初始化组件并设置布局
	public BackLoginJFrame()
	{
		//设置窗体标题、图标
		try {
			Image logoImage = null;
			logoImage = ImageIO.read(new File("icons/loginImage/logo.png"));
			this.setIconImage(logoImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//加载背景图片
		Image backgroungImage = null;
		try {
			backgroungImage = ImageIO.read(new File("icons/loginImage/loginBackgroundImage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//初始化背景面板并设置布局管理器为null
		backgroundPanel = new ImagePanel(backgroungImage);
		backgroundPanel.setLayout(null);
		//添加用户名、密码文本框
		username = new JTextField(15);
		username.setFont(MyFont.JTableFont);
		username.setText("**");
		username.setBounds(455, 240, 135, 30);
		password = new JPasswordField(15);
		password.setFont(MyFont.JTableFont);
		password.setText("密码");
		password.setBounds(455, 290, 135, 30);
		
		//登录、重置按钮设定
		login_button = new JButton();
		login_button.setText("Login");
		login_button.setFont(MyFont.JButtonFont);
		login_button.setBounds(355, 340, 90, 30);
		login_button.setFocusable(false);
		login_button.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		reset_button = new JButton();
		reset_button.setText("Reset");
		reset_button.setFont(MyFont.JButtonFont);
		reset_button.setBounds(490, 340, 90, 30);
		reset_button.setFocusable(false);
		reset_button.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
		
		//为组件添加监听事件：文本框添加焦点监听事件、按钮添加鼠标点击监听事件
		username.addFocusListener(this);
		password.addFocusListener(this);
		login_button.addMouseListener(this);
		reset_button.addMouseListener(this);
		
		//将所有的组件加载到背景面板
		backgroundPanel.add(username);
		backgroundPanel.add(password);
		backgroundPanel.add(login_button);
		backgroundPanel.add(reset_button);
		//将背景面板装载到窗体中，并设置窗体的属性
		this.add(backgroundPanel);
		//设置窗体的相关属性
		this.setTitle("后台管理");//设置窗体标题
		this.setSize(1000,550);//设置窗体大小
		this.setVisible(true);//设置可见性
		this.setLocationRelativeTo(null);//设置依赖项
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭方式
		this.setResizable(false);//设置禁止最大化
		this.requestFocus();//设置格式化窗体
	}
	/**
	 * 获取焦点事件
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource()==username)
		{
			if(username.getText().equals("**"))
				username.setText("");
		}else if(e.getSource()==password)
		{
			if(password.getText().equals("密码"))
			{
				password.setText("");
				password.setEchoChar('*');//设置密码字符
			}
		}
	}
	/**
	 * 失去焦点事件
	 */
	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource()==username)
		{
			if(username.getText().equals(""))
				username.setText("**");
		}else if(e.getSource()==password)
		{
			if(password.getText().equals(""))
			{
				password.setText("密码");
				password.setEchoChar('\0');//设置字符集
			}
		}
	}
	/**
	 * 添加鼠标点击事件
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==login_button)
		{
			if(username.getText().equals("**"))
			{
				JOptionPane.showMessageDialog(null, "用户名/账号不能为空！");
			}else if(password.getText().equals("密码")){
				JOptionPane.showMessageDialog(null, "密码不能为空！");
			}else{
				//获取数据进行封装
				String username_string = username.getText();
				String password_string = password.getText();
				Accounts user = new Accounts();
				user.setUsername(username_string);
				user.setPassword(password_string);
				Accounts findUser = null;
				try {
					findUser = accountsService.loginAccounts(user);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				//判断是否登录成功，进入到主页面
				if(findUser!=null){
					//判断当前账号是否激活(0表示账号未激活不可使用)
					int limit = 0;
					try {
						limit = Integer.valueOf(String.valueOf(accountsService.getAccountsLimits(findUser.getAccount_id())));
					} catch (NumberFormatException | SQLException e1) {
						e1.printStackTrace();
					}
					if(limit==0){
						JOptionPane.showMessageDialog(null, "抱歉！当前账号尚未激活！不可使用！");
					}else{
						/**
						 *  先根据当前登录的账号获取使用该账号的员工信息
						 *  随后对其进行权限判断
						 *  如果不是人事部、财务部、系统维护人员不能登录后台管理界面
						 */
						Employee emp = null;
						try {
							emp = employeeService.getEmployeeById(employeeService.getEmployeeIdByAccountId(findUser.getAccount_id()));
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						if(emp!=null){
							String dept_id =  emp.getDepartment_id();
							if(dept_id.equals("8132456543")||dept_id.equals("7168462722")||dept_id.equals("0653000153"))
							{
								JOptionPane.showMessageDialog(null, "用户登录成功，欢迎您，"+emp.getEmployee_name()+"!");
								//隐藏当前的页面，进入到主页面
								this.setVisible(false);
								new BackIndexJFrame(findUser);
							}else{
								// 处理还没有指定相应员工的账号是无法使用的
								JOptionPane.showMessageDialog(null, "抱歉，当前该员工账号不能操作后台管理模块！");
							}
						}else{
							// 处理还没有指定相应员工的账号是无法使用的
							JOptionPane.showMessageDialog(null, "当前该员工账号非法，不能使用！");
						}
					}
				}else{
					JOptionPane.showMessageDialog(null, "用户名或密码错误！");
				}
			}
		}else if(e.getSource()==reset_button){
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
}
