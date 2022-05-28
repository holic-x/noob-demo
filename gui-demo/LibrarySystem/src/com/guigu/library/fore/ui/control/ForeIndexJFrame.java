package com.guigu.library.fore.ui.control;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import com.guigu.library.fore.ui.function.UpdateForeUserJFrame;
import com.guigu.library.model.Users;
import com.guigu.library.service.UsersService;
import com.guigu.library.service.impl.UsersServiceImpl;
import com.guigu.library.utils.MyFont;

public class ForeIndexJFrame extends JFrame implements MouseListener {
	/**
	 * 1.定义相关的属性
	 */
	// 定义用户对象(当前登录的用户)
	private Users user;
	// 获得屏幕的大小
	public static final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int height = Toolkit.getDefaultToolkit()
			.getScreenSize().height;
	// 定义全局组件
	JPanel backgroundPanel, topPanel, topMenu, topPrompt, centerPanel,
			subPanel, subMenu;
	// 定义JLabel标签
	JLabel home, booksLoan, infoManager, label_user, switchUsers;
	// 定义JTabbedPane
	JTabbedPane jTabbedPane;
	// 定义相应的service
	private UsersService userService = new UsersServiceImpl();

	/**
	 * 2.初始化参数并设置布局
	 */
	public ForeIndexJFrame(Users user) {
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

		this.setIconImage(logoImage);
		initBackgroundPanel();
		// 设置窗体大小
		this.setTitle("前台管理");
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
		String[] nameStrings = { "首页", "图书借还", "信息管理" };
		home = createMenuJLabel(home, nameStrings[0], "home", topMenu);
		home.setName("home");
		home.addMouseListener(this);

		booksLoan = createMenuJLabel(booksLoan, nameStrings[1], "booksLoan",
				topMenu);
		booksLoan.setName("booksLoan");
		booksLoan.addMouseListener(this);

		infoManager = createMenuJLabel(infoManager, nameStrings[2],
				"infoManager", topMenu);
		infoManager.setName("infoManager");
		infoManager.addMouseListener(this);
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
		if (!"infoManager".equals(name)) {
			who.add(line);
		}
		return jl;
	}

	// 初始化顶部的信息提示面板
	private void initTopPrompt() {
		Icon icon = new ImageIcon("icons/indexImage/backuser.png");
		label_user = new JLabel(icon);
		switchUsers = new JLabel();
		if (user != null) {
			switchUsers.setText("切换用户");
			label_user.setText("<html><font color='balck'>"
					+ "欢迎您,</font><font color='red'><b>"
					+ this.user.getUser_name() + "</b></font></html>");
		} else {
			switchUsers.setText("登录");
			label_user.setText("<html><font color='balck'>"
					+ "还没有登录？</font></html>");
		}
		// 添加监听：点击之后能够查看自己的账户信息
		label_user.addMouseListener(this);
		// 提供切换用户功能
		switchUsers.addMouseListener(this);
		// 设置标签字体
		label_user.setFont(MyFont.JLabelFont);
		topPrompt = new JPanel();
		topPrompt.setPreferredSize(new Dimension(225, 40));
		topPrompt.add(label_user);
		topPrompt.add(switchUsers);
	}

	private void initCenterPanel() {
		centerPanel = new JPanel(new BorderLayout());
		// 创建首页的面板
		createHomeTab();
		// 设置空间是否为透明的
		centerPanel.setOpaque(false);
	}

	/**
	 * 创建首页面板： 图书推荐、图书查询
	 */
	private void createHomeTab() {
		// 移除当前中间所有的组件
		centerPanel.removeAll();
		// 设置Tab标题与位置
		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// 设置布局、统一字体
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPane.setFont(MyFont.JLabelFont);
		// 添加相关的属性（图书推荐、图书查询）
		 jTabbedPane.addTab("图书推荐", new BooksRecommendJPanel(this.user).backgroundPanel);// 添加图书推荐页面
		jTabbedPane.addTab("图书查询",
				new BooksSearchJPanel(this.user).backgroundPanel);// 添加图书查询页面
		centerPanel.add(jTabbedPane, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	/**
	 * 创建图书借还面板： 图书预约、图书借阅、图书续借、图书归还
	 */
	public void createBooksLoanTab() {
		// 移除当前中间所有的组件
		centerPanel.removeAll();
		// 设置Tab标题与位置
		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// 设置布局、统一字体
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPane.setFont(MyFont.JLabelFont);
		// 添加相关的属性（图书预约、图书借阅、图书续借、图书归还）
		// jTabbedPane.addTab("图书预约",
		// new BooksOrderJPanel(this.user).backgroundPanel);// 添加图书预约页面
		jTabbedPane.addTab("图书借阅",
				new BooksBorrowJPanel(this.user).backgroundPanel);// 添加图书借阅页面
		jTabbedPane.addTab("图书续借/归还",
				new BooksRenewJPanel(this.user).backgroundPanel);// 添加图书图书续借页面
		centerPanel.add(jTabbedPane, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	/**
	 * 创建信息管理面板： 个人账号管理、借阅信息查询
	 */
	public void createInfoManagerTab() {
		// 移除当前中间所有的组件
		centerPanel.removeAll();
		// 设置Tab标题与位置
		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// 设置布局、统一字体
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPane.setFont(MyFont.JLabelFont);
		// 添加相关的属性（个人账号管理、借阅信息查询）
		jTabbedPane.addTab("个人账号",
				new PersonalAccountJPanel(user).backgroundPanel);// 添加个人账号管理页面
		jTabbedPane.addTab("借阅信息", new BRSearchJPanel(user).backgroundPanel);// 添加借阅信息查询页面
		centerPanel.add(jTabbedPane, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// 点击相应的部分要进行页面刷新
		if (e.getSource() == home) {
			createHomeTab();
			home.setText("<html><font color='#336699' style='font-weight:bold'>首页</font>&nbsp;</html>");
			booksLoan
					.setText("<html><font color='black'>图书借还</font>&nbsp;</html>");
			infoManager
					.setText("<html><font color='black'>信息管理</font>&nbsp;</html>");

		} else if (e.getSource() == booksLoan) {
			createBooksLoanTab();
			home.setText("<html><font color='black'>首页</font>&nbsp;</html>");
			booksLoan
					.setText("<html><font color='#336699' style='font-weight:bold'>图书借还</font>&nbsp;</html>");
			infoManager
					.setText("<html><font color='black'>信息管理</font>&nbsp;</html>");

		} else if (e.getSource() == infoManager) {
			createInfoManagerTab();
			home.setText("<html><font color='black''>首页</font>&nbsp;</html>");
			booksLoan
					.setText("<html><font color='black'>图书借还</font>&nbsp;</html>");
			infoManager
					.setText("<html><font color='#336699' style='font-weight:bold'>信息管理</font>&nbsp;</html>");

		} else if (e.getSource() == switchUsers) {
			// 点击切换用户，隐藏当前页面，进入登录页面
			this.setVisible(false);
			new ForeLoginJFrame();
		} else if (e.getSource() == label_user) {
			// 点击用户名，显示当前登录用户的用户名和用户当前的权限，用户可以相应的修改用户名和密码
			new UpdateForeUserJFrame(this, this.user);
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
