package com.guigu.library.back.ui.control;

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

import com.guigu.library.back.ui.function.UpdateBackUserJFrame;
import com.guigu.library.model.Setting;
import com.guigu.library.model.Users;
import com.guigu.library.service.SettingService;
import com.guigu.library.service.UsersService;
import com.guigu.library.service.impl.SettingServiceImpl;
import com.guigu.library.service.impl.UsersServiceImpl;
import com.guigu.library.utils.MyFont;

public class BackIndexJFrame extends JFrame implements MouseListener {
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
	JLabel infoSearch, booksManager, readerManager, systemSetup, label_user,
			switchUsers;
	// 定义JTabbedPane
	JTabbedPane jTabbedPane;
	// 定义相应的service
	private UsersService userService = new UsersServiceImpl();
	private SettingService settingService = new SettingServiceImpl();

	/**
	 * 2.初始化参数并设置布局
	 */
	public BackIndexJFrame(Users user) {
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
		topMenu.setPreferredSize(new Dimension(800, 40));
		// 设置透明
		topMenu.setOpaque(false);
		// 设置标签文字、并添加鼠标监听事件
		String[] nameStrings = { "信息查询", "图书管理", "读者管理", "系统设置" };
		infoSearch = createMenuJLabel(infoSearch, nameStrings[0], "infoSearch",
				topMenu);
		infoSearch.setName("infoSearch");
		infoSearch.addMouseListener(this);

		booksManager = createMenuJLabel(booksManager, nameStrings[1],
				"booksManager", topMenu);
		booksManager.setName("booksManager");
		booksManager.addMouseListener(this);

		readerManager = createMenuJLabel(readerManager, nameStrings[2],
				"readerManager", topMenu);
		readerManager.setName("readerManager");
		readerManager.addMouseListener(this);

		systemSetup = createMenuJLabel(systemSetup, nameStrings[3],
				"systemSetup", topMenu);
		systemSetup.setName("systemSetup");
		systemSetup.addMouseListener(this);
	}

	// 创建顶部菜单的JLabel标签
	public JLabel createMenuJLabel(JLabel jl, String text, String name,
			JPanel who) {
		// 参数对应标签设置、标签文本、标签图标名称、要添加到哪个面板
		// 设置分割线
		JLabel line = new JLabel(
				"<html>&nbsp;<font color='#D2D2D2' ></font>&nbsp;</html>");
		Icon icon = new ImageIcon("icons/indexImage/" + name + ".png");
		jl = new JLabel(icon);
		jl.setText("<html><font color='balck'>" + text + "</font></html>");
		jl.addMouseListener(this);// 添加监听事件
		jl.setFont(MyFont.JLabelFont);
		who.add(jl);
		// 如果不是标签的最后一个，则添加相应的竖线
		if (!"systemSetup".equals(name)) {
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
		// 创建信息查询的面板
		createInfoSearchTab();
		// 设置空间是否为透明的
		centerPanel.setOpaque(false);
	}

	/**
	 * 创建信息查询面板： 公告栏、图书借阅查询、借阅信息统计、图书馆档案查询
	 */
	private void createInfoSearchTab() {
		// 移除当前中间所有的组件
		centerPanel.removeAll();
		// 设置Tab标题与位置
		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// 设置布局、统一字体
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPane.setFont(MyFont.JLabelFont);
		// 添加相关的属性（公告栏、图书借阅查询、借阅信息统计、图书档案查询）
		// jTabbedPane.addTab("公告栏",
		// new NoticeBoardJPanel(this.user).backgroundPanel);// 添加公告栏页面
		 jTabbedPane.addTab("图书借阅",new BooksBRSearchJPanel(this.user).backgroundPanel);// 添加图书借阅查询页面
		 jTabbedPane.addTab("信息统计", new InfoStatisticsJPanel(this.user).backgroundPanel);// 添加图书借阅信息统计页面
		 jTabbedPane.addTab("图书档案",new BooksArchivesJPanel(this.user).backgroundPanel);// 添加图书档案页面
		centerPanel.add(jTabbedPane, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	/**
	 * 创建图书管理面板：图书类型管理、图书档案管理、图书借还管理、
	 */
	public void createBooksManagerTab() {
		// 移除当前中间所有的组件
		centerPanel.removeAll();
		// 设置Tab标题与位置
		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// 设置布局、统一字体
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPane.setFont(MyFont.JLabelFont);
		// 添加相关的属性（图书类型管理、图书档案管理、图书借还管理、）
		jTabbedPane.addTab("图书类型管理",
				new BooksClassifyManagerJPanel(this.user).backgroundPanel);
		// 添加图书档案管理页面
		jTabbedPane.addTab("图书档案管理",
				new BooksArchivesManagerJPanel(this.user).backgroundPanel);
		// 添加图书借还管理页面
		// jTabbedPane.addTab("图书借还管理",
		// new BooksLoanJPanel(this.user).backgroundPanel);// 添加图书借还管理页面
		centerPanel.add(jTabbedPane, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	/**
	 * 创建读者管理面板：读者类型管理、读者档案管理
	 */
	public void createReaderManagerTab() {
		// 移除当前中间所有的组件
		centerPanel.removeAll();
		// 设置Tab标题与位置
		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// 设置布局、统一字体
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPane.setFont(MyFont.JLabelFont);
		// 添加相关的属性（读者类型管理、读者档案管理）
		jTabbedPane.addTab("读者类型管理",
				new ReaderClassifyManagerJPanel(user).backgroundPanel);// 添加读者类型管理页面
		jTabbedPane.addTab("读者档案管理",
				new ReaderArchivesManagerJPanel(user).backgroundPanel);// 添加读者档案管理页面
		centerPanel.add(jTabbedPane, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	/**
	 * 创建系统设置面板：图书馆信息、管理员设置
	 */
	public void createSystemSetupTab() {
		// 移除当前中间所有的组件
		centerPanel.removeAll();
		// 设置Tab标题与位置
		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// 设置布局、统一字体
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPane.setFont(MyFont.JLabelFont);
		// 添加相关的属性（图书馆信息、管理员设置）
		jTabbedPane
				.addTab("图书馆信息", new LibraryInfoJPanel(user).backgroundPanel);
		jTabbedPane.addTab("管理员设置", new AdminSetJPanel(user).backgroundPanel);// 添加管理员设置页面
		centerPanel.add(jTabbedPane, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	/**
	 * 在点击主页的时候进行权限判断
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// 获取相关的配置信息，判断当前登录的用户是否有相应的权限
		Setting s = null;
		try {
			s = settingService.getUsersSettingById(this.user.getUser_id());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (e.getSource() == switchUsers) {
			// 点击切换用户，隐藏当前页面，进入登录页面
			this.setVisible(false);
			new BackLoginJFrame();
		} else if (e.getSource() == label_user) {
			// 点击用户名，显示当前登录用户的用户名和用户当前的权限，用户可以相应的修改用户名和密码
			new UpdateBackUserJFrame(this, this.user);
		} else if (s != null) {
			// 点击相应的部分要进行页面刷新
			if (e.getSource() == infoSearch) {
				if (s.getInfoSearch() == 1) {
					createInfoSearchTab();
					infoSearch
							.setText("<html><font color='#336699' style='font-weight:bold'>信息查询</font>&nbsp;</html>");
					booksManager
							.setText("<html><font color='black'>图书管理</font>&nbsp;</html>");
					readerManager
							.setText("<html><font color='black'>读者管理</font>&nbsp;</html>");
					systemSetup
							.setText("<html><font color='black'>系统设置</font>&nbsp;</html>");
				} else {
					JOptionPane.showMessageDialog(null, "抱歉，您当前没有权限管理该模块！");
				}
			} else if (e.getSource() == booksManager) {
				if (s.getBooksManager() == 1) {
					createBooksManagerTab();
					infoSearch
							.setText("<html><font color='black'>信息查询</font>&nbsp;</html>");
					booksManager
							.setText("<html><font color='#336699' style='font-weight:bold'>图书管理</font>&nbsp;</html>");
					readerManager
							.setText("<html><font color='black'>读者管理</font>&nbsp;</html>");
					systemSetup
							.setText("<html><font color='black'>系统设置</font>&nbsp;</html>");
				} else {
					JOptionPane.showMessageDialog(null, "抱歉，您当前没有权限管理该模块！");
				}
			} else if (e.getSource() == readerManager) {
				if (s.getReaderManager() == 1) {
					createReaderManagerTab();
					infoSearch
							.setText("<html><font color='black'>信息查询</font>&nbsp;</html>");
					booksManager
							.setText("<html><font color='black'>图书管理</font>&nbsp;</html>");
					readerManager
							.setText("<html><font color='#336699' style='font-weight:bold'>读者管理</font>&nbsp;</html>");
					systemSetup
							.setText("<html><font color='black'>系统设置</font>&nbsp;</html>");
				} else {
					JOptionPane.showMessageDialog(null, "抱歉，您当前没有权限管理该模块！");
				}
			} else if (e.getSource() == systemSetup) {
				if (s.getSystemSetup() == 1) {
					createSystemSetupTab();
					infoSearch
							.setText("<html><font color='black'>信息查询</font>&nbsp;</html>");
					booksManager
							.setText("<html><font color='black'>图书管理</font>&nbsp;</html>");
					readerManager
							.setText("<html><font color='black'>读者管理</font>&nbsp;</html>");
					systemSetup
							.setText("<html><font color='#336699' style='font-weight:bold'>系统设置</font>&nbsp;</html>");
				} else {
					JOptionPane.showMessageDialog(null, "抱歉，您当前没有权限管理该模块！");
				}
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"抱歉，您当前没有任何权限进行模块操作，请联系超级管理员进行权限配置！");
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
