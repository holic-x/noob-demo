package com.guigu.library.back.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.guigu.library.back.ui.control.AdminSetJPanel;
import com.guigu.library.model.Setting;
import com.guigu.library.service.SettingService;
import com.guigu.library.service.impl.SettingServiceImpl;
import com.guigu.library.utils.MyFont;

public class AdminSettingJFrame extends JFrame implements MouseListener {

	// 定义全局组件
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;

	ButtonGroup select;
	JRadioButton all, cancel;
	JCheckBox infoSearch, booksManager, readerManager, systemSetup;

	JCheckBox[] checkBox = { infoSearch, booksManager, readerManager, systemSetup};

	String[] names = { "信息查询", "图书管理", "读者管理", "系统设置" };
	int[] flags = { 0, 0, 0, 0 };

	JButton ensure, reset;

	// 定义父对象、父表格、选中数据
	AdminSetJPanel parentPanel;
	JTable parentTable;
	int selectedRow;

	// 定义service
	SettingService settingService = new SettingServiceImpl();

	// 通过构造方法初始化数据
	public AdminSettingJFrame(AdminSetJPanel parentPanel, JTable parentTable,
			int selectedRow) {
		this.parentPanel = parentPanel;
		this.parentTable = parentTable;
		this.selectedRow = selectedRow;
		// 初始化背景
		initBackgroundPanel();
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("管理员权限设置");
		this.setSize(450, 300);
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
		backgroundPanel.validate();
	}

	/**
	 * 初始化标题面板
	 */
	private void initTitlePanel() {
		titlePanel = new JPanel();
		JLabel title = new JLabel("管理员权限设置");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
		backgroundPanel.add(titlePanel, BorderLayout.NORTH);
	}

	/**
	 * 初始化内容面板
	 */
	private void initContentPanel() {

		contentPanel = new JPanel();

		JPanel selectPanel = new JPanel();
		select = new ButtonGroup();
		all = new JRadioButton("全选");
		all.addMouseListener(this);
		cancel = new JRadioButton("取消");
		cancel.addMouseListener(this);
		select.add(all);
		select.add(cancel);
		selectPanel.add(all);
		selectPanel.add(cancel);

		// 初始化复选框(一一对应)
		for (int i = 0; i < checkBox.length; i++) {
			checkBox[i] = new JCheckBox(names[i]);
		}

		// 每2个放置一行
		JPanel jp1 = new JPanel();
		jp1.add(checkBox[0]);
		jp1.add(checkBox[1]);

		JPanel jp2 = new JPanel();
		jp2.add(checkBox[2]);
		jp2.add(checkBox[3]);

		// 数据回显
		this.echoData();
		Box ver = Box.createVerticalBox();
		ver.add(selectPanel);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		// 将组件加载到contentPanel面板中
		contentPanel.add(ver);
		backgroundPanel.add(contentPanel, BorderLayout.CENTER);
	}

	/**
	 * 初始化按钮面板
	 */
	private void initButtonPanel() {
		buttonPanel = new JPanel();

		ensure = new JButton("确认");
		ensure.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));
		ensure.setForeground(Color.white);
		ensure.setFont(MyFont.JButtonFont);
		ensure.addMouseListener(this);

		reset = new JButton("重置");
		reset.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));
		reset.setForeground(Color.white);
		reset.setFont(MyFont.JButtonFont);
		reset.addMouseListener(this);

		buttonPanel.add(ensure);
		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(reset);
		backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == all) {
			// 默认选择所有的内容
			for (int i = 0; i < checkBox.length; i++) {
				checkBox[i].setSelected(true);
			}
		} else if (e.getSource() == cancel) {
			// 默认取消所有选择
			for (int i = 0; i < checkBox.length; i++) {
				checkBox[i].setSelected(false);
			}
		} else if (e.getSource() == ensure) {
			// 获取用户选择，修改标识
			for (int i = 0; i < checkBox.length; i++) {
				if (checkBox[i].isSelected()) {
					// 修改标识
					flags[i] = 1;
				}
			}
			int choose = JOptionPane.showConfirmDialog(null, "确认修改管理员权限？");
			if (choose == 0) {
				try {
					// 根据指定的读者id修改管理员权限
					String reader_id = parentTable.getValueAt(selectedRow, 0)
							.toString();
					Setting s = settingService.getSettingByReaderId(reader_id);
					Setting newSetting = new Setting(reader_id, flags[0],
							flags[1], flags[2], flags[3]);
					// 判断当前是否已经存在配置信息，如果不存在则进行添加，如果存在则更新
					if (s == null) {
						// 根据当前用户选择添加配置信息
						settingService.addSetting(newSetting);
					} else if (s != null) {
						// 根据当前用户选择更新配置信息
						settingService.updateSetting(newSetting);
					}
					JOptionPane.showMessageDialog(null, "管理员权限配置完成！");
				} catch (HeadlessException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == reset) {
			// 重置数据
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
	 * 定义数据回显的方法
	 */
	public void echoData() {
		String reader_id = parentTable.getValueAt(selectedRow, 0).toString();
		Setting s = null;
		try {
			s = settingService.getSettingByReaderId(reader_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (s == null) {
			// 没有指定的配置信息，默认为全都不选
			for (int i = 0; i < checkBox.length; i++) {
				checkBox[i].setSelected(false);
			}
		} else if (s != null) {
			// 数据回显
			if (s.getInfoSearch() == 1) {
				checkBox[0].setSelected(true);
			}
			if (s.getBooksManager() == 1) {
				checkBox[1].setSelected(true);
			} 
			if (s.getReaderManager() == 1) {
				checkBox[2].setSelected(true);
			} 
			if (s.getSystemSetup() == 1) {
				checkBox[3].setSelected(true);
			}
		}
	}
}