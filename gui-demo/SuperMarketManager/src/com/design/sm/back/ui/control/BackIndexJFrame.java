package com.design.sm.back.ui.control;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import com.design.sm.back.ui.funciton.UpdateBackUserJFrame;
import com.design.sm.model.Accounts;
import com.design.sm.model.Employee;
import com.design.sm.service.AccountsService;
import com.design.sm.service.EmployeeService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.EmployeeServiceImpl;
import com.design.sm.utils.ImagePanel;
import com.design.sm.utils.MyFont;
import com.eltima.components.ui.DatePicker;

public class BackIndexJFrame extends JFrame implements MouseListener {
	/**
	 * 1.定义相关的属性
	 */
	// 定义用户对象(当前登录的用户)
	private Accounts user;
	// 获得屏幕的大小
	public static final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int height = Toolkit.getDefaultToolkit()
			.getScreenSize().height;
	// 定义全局组件
	JPanel backgroundPanel, topPanel, topMenu, topPrompt, centerPanel,
			subPanel, subMenu;
	// 定义JLabel标签
	JLabel home, personnelDept, financialDept, usermanager, label_user,
			switchAccounts;
	// 定义JTabbedPane
	JTabbedPane jTabbedPane;

	// 定义相应的service
	private AccountsService userService = new AccountsServiceImpl();
	private EmployeeService employeeService = new EmployeeServiceImpl();

	// 定义当前使用该账号的员工
	Employee emp;

	/**
	 * 2.初始化参数并设置布局
	 */
	public BackIndexJFrame(Accounts user) {
		this.user = user;
		// 设置JTabbedPane的缩进
		UIManager.put("TabbedPane.tabAreaInsets",
				new javax.swing.plaf.InsetsUIResource(3, 20, 2, 20));
		// 设置logo和背景图片
		Image logoImage = null;
		try {
			logoImage = ImageIO.read(new File("icons/loginImage/logo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		/**
		 * 先根据当前登录的账号获取使用该账号的员工信息 随后对其进行权限判断 不同职位的员工只能够进入相应部门的管理界面
		 */
		// 初始化当前使用该账号的员工信息
		emp = null;
		try {
			emp = employeeService.getEmployeeById(employeeService
					.getEmployeeIdByAccountId(this.user.getAccount_id()));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		this.setIconImage(logoImage);
		initBackgroundPanel();
		// 设置窗体大小
		this.setTitle("后台管理");
		this.setSize((int) (width * 0.9), (int) (height * 0.9));
		this.setLocationRelativeTo(null);// 设置依赖项
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * 初始化背景面板
	 */
	private void initBackgroundPanel() {
		/**
		 * 背景面板可以分为上下两部分进行操作 topPanel顶部菜单 centerPanel中间的背景图片
		 */
		backgroundPanel = new JPanel(new BorderLayout());
		initTopPanel();// 初始化上方的标签面板
		initCenterPanel();// 初始化下方的图片背景面板
		// 将初始化完成的面板加载到背景面板中
		backgroundPanel.add(topPanel, BorderLayout.NORTH);
		backgroundPanel.add(centerPanel, BorderLayout.CENTER);
		this.add(backgroundPanel);
	}

	/**
	 * 初始化顶部菜单（分为左侧菜单，右侧部分信息提示）
	 */
	private void initTopPanel() {
		initTopMenu();// 初始化左侧菜单
		initTopPrompt();// 初始化右侧的信息提示
		topPanel = new JPanel(new BorderLayout());
		topPanel.setPreferredSize(new Dimension(width, 40));
		topPanel.add(topMenu, BorderLayout.WEST);
		topPanel.add(topPrompt, BorderLayout.EAST);
	}

	/**
	 * initTopMenu
	 */
	private void initTopMenu() {
		topMenu = new JPanel();
		topMenu.setPreferredSize(new Dimension(625, 40));
		// 设置透明
		topMenu.setOpaque(false);
		// 设置标签文字、并添加鼠标监听事件
		String[] nameStrings = { "首页", "人事部", "财务部", "用户管理" };
		home = createMenuJLabel(home, nameStrings[0], "home", topMenu);
		home.setName("home");
		home.addMouseListener(this);

		personnelDept = createMenuJLabel(personnelDept, nameStrings[1],
				"personnelDept", topMenu);
		personnelDept.setName("personnelDept");
		personnelDept.addMouseListener(this);

		financialDept = createMenuJLabel(financialDept, nameStrings[2],
				"financialDept", topMenu);
		financialDept.setName("financialDept");
		financialDept.addMouseListener(this);

		usermanager = createMenuJLabel(usermanager, nameStrings[3],
				"usermanager", topMenu);
		usermanager.setName("usermanager");
		usermanager.addMouseListener(this);
	}

	// 创建顶部菜单的JLabel标签
	public JLabel createMenuJLabel(JLabel jl, String text, String name,
			JPanel who) {
		// 参数对应标签设置、标签文本、标签图标名称、要添加到哪个面板
		// 设置分割线
		JLabel line = new JLabel(
				"<html>&nbsp;<font color='#D2D2D2'></font>&nbsp;</html>");
		Icon icon = new ImageIcon("icons/indexImage/" + name + ".png");
		jl = new JLabel(icon);
		jl.setText("<html><font color='balck'>" + text + "</font></html>");
		jl.addMouseListener(this);// 添加监听事件
		jl.setFont(MyFont.JLabelFont);
		who.add(jl);
		// 如果不是标签的最后一个，则添加相应的竖线
		if (!"usermanager".equals(name)) {
			who.add(line);
		}
		return jl;
	}

	// 初始化顶部的信息提示面板
	private void initTopPrompt() {
		Icon icon = new ImageIcon("icons/indexImage/backuser.png");
		label_user = new JLabel(icon);
		switchAccounts = new JLabel();
		if (user != null) {
			switchAccounts.setText("切换用户");
			label_user.setText("<html><font color='balck'>"
					+ "欢迎您,</font><font color='red'><b>"
					+ this.user.getUsername() + "</b></font></html>");
		} else {
			switchAccounts.setText("登录");
			label_user.setText("<html><font color='balck'>"
					+ "还没有登录？</font></html>");
		}
		// 添加监听：点击之后能够查看自己的账户信息
		label_user.addMouseListener(this);
		// 提供切换用户功能
		switchAccounts.addMouseListener(this);
		// 设置标签字体
		label_user.setFont(MyFont.JLabelFont);
		topPrompt = new JPanel();
		topPrompt.setPreferredSize(new Dimension(225, 40));
		topPrompt.add(label_user);
		topPrompt.add(switchAccounts);
	}

	private void initCenterPanel() {
		centerPanel = new JPanel(new BorderLayout());
		// 创建首页的面板
		createHome();
		// 设置空间是否为透明的
		centerPanel.setOpaque(false);
	}

	/**
	 * 创建主页面板 添加考勤打卡、切换用户（后期尽量完善）
	 */
	private void createHome() {
		// 移除所有的组件，放置相应的组件
		centerPanel.removeAll();
		try {
			Image backgroundImage = ImageIO.read(new File(
					"icons/indexImage/indexbackground.png"));
			ImagePanel centerBackground = new ImagePanel(backgroundImage);
			centerPanel.add(centerBackground, BorderLayout.CENTER);
		} catch (IOException e) {
			e.printStackTrace();
		}
		backgroundPanel.validate();
	}

	/**
	 * 创建人事部面板 人事部的核心基础业务包括员工管理、部门管理（包括相应的职位管理）、绩效考核
	 */
	public void createPersonnelDeptTab() {
		// 移除当前中间所有的组件
		centerPanel.removeAll();
		// 设置Tab标题与位置
		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// 设置布局、统一字体
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPane.setFont(MyFont.JLabelFont);
		// 添加相关的属性（员工管理、部门管理、绩效考核）
		jTabbedPane.addTab("员工管理",
				new EmployeeManagerJPanel(this.user).backgroundPanel);// 添加员工管理页面
		jTabbedPane.addTab("部门管理",
				new DepartmentManagerJPanel(this.user).backgroundPanel);// 添加部门管理页面
		// jTabbedPane.addTab("绩效考核", new
		// CheckingJPanel(this.user).backgroundPanel);// 添加绩效考核页面
		centerPanel.add(jTabbedPane, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	/**
	 * 创建财务部面板 财务部的核心基础业务主要包括工资结算、收支结算、财务统计、财务查询
	 */
	public void createFinancialDeptTab() {
		// 移除当前中间所有的组件
		centerPanel.removeAll();
		// 设置Tab标题与位置
		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// 设置布局、统一字体
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPane.setFont(MyFont.JLabelFont);
		// 添加相关的属性（工资结算、收支结算、财务统计、财务查询）
		jTabbedPane.addTab("工资结算",
				new WageSettlementJPanel(user).backgroundPanel);// 添加工资结算页面
		jTabbedPane.addTab("收支结算", new BalanceJPanel(user).backgroundPanel);// 添加收支结算页面
		// jTabbedPane.addTab("财务统计", new
		// StockManagerJPanel(user).backgroundPanel);// 添加财务统计页面
		// jTabbedPane.addTab("财务查询", new
		// StockManagerJPanel(user).backgroundPanel);// 添加财务查询页面
		centerPanel.add(jTabbedPane, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	/**
	 * 创建用户管理面板 用户管理的核心业务是：账号管理 由超级管理员实现
	 */
	public void createAccountsManagerTab() {
		// 移除当前中间所有的组件
		centerPanel.removeAll();
		// 设置Tab标题与位置
		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// 设置布局、统一字体
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPane.setFont(MyFont.JLabelFont);
		// 添加相关的属性（账号管理）
		jTabbedPane.addTab("账号管理",
				new AccountsMangerJPanel(user).backgroundPanel);// 添加账号管理页面
		centerPanel.add(jTabbedPane, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	/**
	 * 在点击主页的时候进行权限判断
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// 根据当前登录的用户获取用户的身份信息，根据其当前的部门、职位给以不同的权限访问不同的模块
		// 点击相应的部分要进行页面刷新
		if (e.getSource() == home) {
			// 主页
			createHome();
			home.setText("<html><font color='#336699' style='font-weight:bold'>首页</font>&nbsp;</html>");
			personnelDept
					.setText("<html><font color='black' style='font-weight:bold'>人事部</font>&nbsp;</html>");
			financialDept
					.setText("<html><font color='black' style='font-weight:bold'>财务部</font>&nbsp;</html>");
			usermanager
					.setText("<html><font color='black' style='font-weight:bold'>用户管理</font>&nbsp;</html>");
		} else if (e.getSource() == personnelDept) {
			if (emp != null) {
				String dept_id = emp.getDepartment_id();
				if (dept_id.equals("8132456543")
						|| dept_id.equals("0653000153")) {
					// 人事部员工登录、系统维护员工登录
					createPersonnelDeptTab();
					home.setText("<html><font color='#336699' style='font-weight:bold'>首页</font>&nbsp;</html>");
					personnelDept
							.setText("<html><font color='black' style='font-weight:bold'>人事部</font>&nbsp;</html>");
					financialDept
							.setText("<html><font color='black' style='font-weight:bold'>财务部</font>&nbsp;</html>");
					usermanager
							.setText("<html><font color='black' style='font-weight:bold'>用户管理</font>&nbsp;</html>");
				} else {
					JOptionPane.showMessageDialog(null, "抱歉，您当前没有权限访问该模块！");
				}
			}
		} else if (e.getSource() == financialDept) {
			if (emp != null) {
				String dept_id = emp.getDepartment_id();
				if (dept_id.equals("7168462722")
						|| dept_id.equals("0653000153")) {
					// 财务部员工登录、系统维护员工登录
					createFinancialDeptTab();
					home.setText("<html><font color='#336699' style='font-weight:bold'>首页</font>&nbsp;</html>");
					personnelDept
							.setText("<html><font color='black' style='font-weight:bold'>人事部</font>&nbsp;</html>");
					financialDept
							.setText("<html><font color='black' style='font-weight:bold'>财务部</font>&nbsp;</html>");
					usermanager
							.setText("<html><font color='black' style='font-weight:bold'>用户管理</font>&nbsp;</html>");
				}else {
					JOptionPane.showMessageDialog(null, "抱歉，您当前没有权限访问该模块！");
				}
			} 
		} else if (e.getSource() == usermanager) {
			if (emp != null) {
				String dept_id = emp.getDepartment_id();
				if (dept_id.equals("0653000153")) {
					// 用户管理--系统维护员工登录
					createAccountsManagerTab();
					home.setText("<html><font color='#336699' style='font-weight:bold'>首页</font>&nbsp;</html>");
					personnelDept
							.setText("<html><font color='black' style='font-weight:bold'>人事部</font>&nbsp;</html>");
					financialDept
							.setText("<html><font color='black' style='font-weight:bold'>财务部</font>&nbsp;</html>");
					usermanager
							.setText("<html><font color='black' style='font-weight:bold'>用户管理</font>&nbsp;</html>");
				} else {
					JOptionPane.showMessageDialog(null, "抱歉，您当前没有权限访问该模块！");
				}
			}
		} else if (e.getSource() == switchAccounts) {
			// 点击切换用户，隐藏当前页面，进入登录页面
			this.setVisible(false);
			new BackLoginJFrame();
		} else if (e.getSource() == label_user) {
			// 点击用户名，显示当前登录用户的用户名和用户当前的权限，用户可以相应的修改用户名和密码
			new UpdateBackUserJFrame(this, this.user);
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
