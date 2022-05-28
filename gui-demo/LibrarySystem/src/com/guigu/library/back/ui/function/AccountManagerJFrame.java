package com.guigu.library.back.ui.function;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.guigu.library.back.ui.control.ReaderArchivesManagerJPanel;
import com.guigu.library.model.LibraryCard;
import com.guigu.library.model.Reader;
import com.guigu.library.model.Users;
import com.guigu.library.service.ReaderService;
import com.guigu.library.service.UsersService;
import com.guigu.library.service.impl.ReaderServiceImpl;
import com.guigu.library.service.impl.UsersServiceImpl;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;
import com.guigu.library.utils.RandomGeneration;

public class AccountManagerJFrame extends JFrame implements MouseListener {

	// 定义全局组件
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_id, label_name, label_password, label_limits;
	JTextField id, name, password, limits;

	// 定义service
	ReaderService readerService = new ReaderServiceImpl();
	UsersService usersService = new UsersServiceImpl();

	JLabel find, active;

	// 定义父对象、父表格、选中的行
	ReaderArchivesManagerJPanel parentPanel;
	JTable parentTable;
	int selectedRow;

	// 通过构造方法初始化数据
	public AccountManagerJFrame(ReaderArchivesManagerJPanel parentPanel,
			JTable parentTable, int selectedRow) {
		this.parentPanel = parentPanel;
		this.parentTable = parentTable;
		this.selectedRow = selectedRow;
		// 初始化背景
		initBackgroundPanel();
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("读者账号信息管理");
		this.setSize(600, 350);
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
		JLabel title = new JLabel("读者账号信息管理");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * 初始化内容面板
	 */
	private void initContentPanel() {

		contentPanel = new JPanel();
		JPanel jp1 = new JPanel();
		label_id = new JLabel("账号id  ");
		id = new JTextField(15);
		id.setFont(MyFont.JTextFieldFont);
		id.setForeground(MyColor.JTextFieldColor);
		id.setEditable(false);
		jp1.add(label_id);
		jp1.add(id);
		
		JPanel jp2 = new JPanel();
		label_name = new JLabel("用户名称");
		name = new JTextField(15);
		name.setFont(MyFont.JTextFieldFont);
		name.setForeground(MyColor.JTextFieldColor);
		name.setEditable(false);
		jp2.add(label_name);
		jp2.add(name);

		JPanel jp3 = new JPanel();
		label_password = new JLabel("账号密码");
		password = new JTextField(15);
		password.setFont(MyFont.JTextFieldFont);
		password.setForeground(MyColor.JTextFieldColor);
		password.setEditable(false);
		jp3.add(label_password);
		jp3.add(password);
		
		JPanel jp4 = new JPanel();
		label_limits = new JLabel("用户权限");
		limits = new JTextField(15);
		limits.setFont(MyFont.JTextFieldFont);
		limits.setForeground(MyColor.JTextFieldColor);
		limits.setEditable(false);
		jp4.add(label_limits);
		jp4.add(limits);

		// 数据回显
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
		buttonPanel = new JPanel();
		Icon find_icon = new ImageIcon("icons/toolImage/find.png");
		find = new JLabel(find_icon);
		find.setToolTipText("找回密码");
		find.addMouseListener(this);

		Icon active_icon = new ImageIcon("icons/toolImage/active.png");
		active = new JLabel(active_icon);
		active.setToolTipText("激活/禁用");
		active.addMouseListener(this);

		buttonPanel.add(find);
		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(active);
		buttonPanel.add(Box.createHorizontalStrut(10));
		backgroundPanel.validate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			// 根据父表格中当前指定的行获取读者的数据信息：
			String account_id_string = parentTable.getValueAt(selectedRow, 10).toString();
			String reader_id = parentTable.getValueAt(selectedRow, 0).toString();
			Reader r = readerService.getReaderById(reader_id);
			// 根据账号id获取详细的账号信息
			Users user = null;
				user = usersService.getUsersById(account_id_string);
			if (e.getSource() == find) {
				 // 找回密码
				int choose = JOptionPane.showConfirmDialog(null, "确认重置账号信息？");
				if(choose==0){
					String academic_num = r.getAcademic_num();
					user.setUser_name(academic_num);
					user.setUser_password("000000");
					usersService.updateUsers(user);
					JOptionPane.showMessageDialog(null, "用户账号密码已重置，用户名："+user.getUser_name()+"-密码："+user.getUser_password());	
					this.refreshContentPanel();
				}
			} else if (e.getSource() == active) {
				// 根据读者当前的身份激活账户
				int limits_int = user.getUser_limits();
				if(limits_int==0){
					int choose = JOptionPane.showConfirmDialog(null, "确认激活该账号信息？");
					if(choose==0){
						int classify = r.getClassify_num();
						int newlimit = 0;
						if(classify==0||classify==1||classify==2){
							// 表示普通读者，权限等级标为3
							newlimit=3;
						}else if(classify==3){
							// 表示图书管理员，权限等级标为2
							newlimit = 2;
						}else if(classify==4){
							// 表示超级管理员，权限等级标为1
							newlimit=1;
						}
						user.setUser_limits(newlimit);
						usersService.updateUsers(user);
						// 刷新数据面板
						JOptionPane.showMessageDialog(null, "账号信息激活成功！");
						this.refreshContentPanel();
					}
				}else if(limits_int!=0){
					// 禁用账户信息
					int choose = JOptionPane.showConfirmDialog(null, "确认禁用该账户信息？");
					if(choose==0){
						user.setUser_limits(0);
						usersService.updateUsers(user);
						// 刷新数据面板
						JOptionPane.showMessageDialog(null, "账号信息已禁用！");
						this.refreshContentPanel();
					}
				}
			}
		} catch (HeadlessException | SQLException e1) {
			e1.printStackTrace();
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
	 * 定义数据回显方法
	 */
	public void echoData() {
		// 根据父表格中当前指定的行获取读者的数据信息：
		String account_id_string = parentTable.getValueAt(selectedRow, 10)
				.toString();
		// 根据账号id获取详细的账号信息
		Users user = null;
		try {
			user = usersService.getUsersById(account_id_string);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 数据回显
		id.setText(user.getUser_id());
		id.setToolTipText(user.getUser_id());
		name.setText(user.getUser_name());
		name.setToolTipText(user.getUser_name());
		password.setText(user.getUser_password());
		password.setToolTipText(user.getUser_password());
		int limits_int = user.getUser_limits();
		String identity = null;
		if (limits_int == 1) {
			identity = "超级管理员";
		} else if (limits_int == 2) {
			identity = "图书馆管理员";
		} else if (limits_int == 3) {
			identity = "读者";
		} else if (limits_int == 0) {
			identity = "禁用账户";
		}
		limits.setText(identity);
		limits.setToolTipText(identity);
	}

	/**
	 * 刷新内容面板
	 */
	public void refreshContentPanel() {
		backgroundPanel.remove(contentPanel);
		initContentPanel();
		backgroundPanel.add(contentPanel);
		backgroundPanel.validate();
	}
}
