package com.design.sm.back.ui.control;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;

import com.design.sm.back.ui.funciton.AddAccountsJFrame;
import com.design.sm.back.ui.funciton.UpdateAccountsJFrame;
import com.design.sm.model.Accounts;
import com.design.sm.model.Employee;
import com.design.sm.service.AccountsService;
import com.design.sm.service.EmployeeService;
import com.design.sm.service.JobService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.EmployeeServiceImpl;
import com.design.sm.service.impl.JobServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.Tools;

public class AccountsMangerJPanel implements MouseListener, FocusListener {

	// 定义全局组件
	JPanel backgroundPanel, topPanel, toolPanel, searchPanel, tablePanel;
	// 借助工具类完成表格数据的封装
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// 定义用到的标签
	JLabel label_keyword, tool_add, tool_modify, tool_delete,tool_power,tool_forbidden;
	JTextField keyword;
	JButton search_button;
	// 定义相应的service
	AccountsService userService = new AccountsServiceImpl();
	EmployeeService employeeService = new EmployeeServiceImpl();
	JobService jobService = new JobServiceImpl();
	private Accounts user;

	/**
	 * 通过构造方法完成初始化
	 */
	public AccountsMangerJPanel(Accounts user) {
		backgroundPanel = new JPanel(new BorderLayout());
		this.user = user;
		// 初始化布局
		initTopPanel();// 初始化顶部菜单条
		initTablePanel();// 初始化显示的表格数据
	}

	/**
	 * 初始化顶部的菜单条
	 */
	private void initTopPanel() {
		topPanel = new JPanel(new BorderLayout());
		// 初始化工具条面板
		initToolPanel();
		// 初始化查找面板
		initSearchPanel();
		// 将顶部菜单栏加载到背景面板中
		backgroundPanel.add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * 初始化工具条面板
	 */
	private void initToolPanel() {
		toolPanel = new JPanel();

		// 添加增删改工具
		Icon icon_add = new ImageIcon("icons/toolImage/add.png");
		tool_add = new JLabel(icon_add);
		tool_add.setToolTipText("新建账号");// 设置鼠标移动时的显示内容
		tool_add.addMouseListener(this);// 添加鼠标监听

		Icon icon_modify = new ImageIcon("icons/toolImage/modify.png");
		tool_modify = new JLabel(icon_modify);
		tool_modify.setToolTipText("修改账号");// 设置鼠标移动时的显示内容
		tool_modify.addMouseListener(this);// 添加鼠标监听

		Icon icon_delete = new ImageIcon("icons/toolImage/delete.png");
		tool_delete = new JLabel(icon_delete);
		tool_delete.setToolTipText("删除账号");// 设置鼠标移动时的显示内容
		tool_delete.addMouseListener(this);// 添加鼠标监听

		Icon icon_power = new ImageIcon("icons/toolImage/power.png");
		tool_power = new JLabel(icon_power);
		tool_power.setToolTipText("权限管理");// 设置鼠标移动时的显示内容
		tool_power.addMouseListener(this);// 添加鼠标监听

		Icon icon_forbidden = new ImageIcon("icons/toolImage/forbidden.png");
		tool_forbidden = new JLabel(icon_forbidden);
		tool_forbidden.setToolTipText("禁用账号");// 设置鼠标移动时的显示内容
		tool_forbidden.addMouseListener(this);// 添加鼠标监听
		
		// 将初始化完成的工具加载到工具条面板中
		toolPanel.add(tool_add);
		toolPanel.add(tool_modify);
		toolPanel.add(tool_delete);
		toolPanel.add(tool_power);
		toolPanel.add(tool_forbidden);
		// 最终将工具条面板加载到顶部菜单条的最西面
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * 初始化查找面板
	 */
	private void initSearchPanel() {

		searchPanel = new JPanel();

		label_keyword = new JLabel("用户名称");
		label_keyword.setFont(MyFont.JLabelFont);

		keyword = new JTextField(15);
		keyword.setFont(MyFont.TipFont);
		keyword.setForeground(MyColor.TipColor);
		keyword.setText("请输入用户名称");
		keyword.addFocusListener(this);

		search_button = new JButton("查找");
		search_button.setFocusable(false);
		search_button.addMouseListener(this);

		// 将相关组件加载到指定的面板中
		searchPanel.add(label_keyword);
		searchPanel.add(keyword);
		searchPanel.add(search_button);
		// 将布局好的组件加载到菜单栏面板的最东面
		topPanel.add(searchPanel, BorderLayout.EAST);
	}

	/**
	 * 初始化显示的表格数据
	 */
	private void initTablePanel() {

		// 输入的数据进行查找
		String[] params = { "账号id", "用户名称", "用户密码", "用户密码" ,"用户权限", "用户身份" };
		Vector<Vector> vec = new Vector<>();
		if (!keyword.getText().equals("请输入用户名称")) {
			try {
				// 拼接查找的字符串
				String text = "%" + keyword.getText() + "%";
				vec = userService.getAccountsByNameKeyword(text);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				vec = userService.findAllAccountsVector();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 将查询到的数据封装到BbaseTableModule中
		baseTableModule = new BaseTableModule(params, vec);
		table = new JTable(baseTableModule);
		// 通过设置行的大小，隐藏某一列内容（只显示想要显示的数据）
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) table
				.getColumnModel();
		dcm.getColumn(0).setMinWidth(0);// 隐藏第1列
		dcm.getColumn(0).setMaxWidth(0);// 隐藏第1列
		dcm.getColumn(2).setMinWidth(0);// 隐藏第3列
		dcm.getColumn(2).setMaxWidth(0);// 隐藏第3列

		// 利用提供的Tools类美化表格
		Tools.setTableStyle(table);
		// 设置滚动条
		jScrollPane = new JScrollPane(table);
		Tools.setJspStyle(jScrollPane);

		tablePanel = new JPanel(new BorderLayout());
		tablePanel.setOpaque(false);// 设置透明度
		tablePanel.add(jScrollPane);
		// 将组件加载到背景中
		backgroundPanel.add(tablePanel, BorderLayout.CENTER);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == search_button) {
			// 如果点击了查找选项则进行筛选
			tablePanel.removeAll();// 移除数据面板中的所有数据
			initTablePanel();// 重新初始化面板
			backgroundPanel.validate();
		}
		if (e.getSource() == tool_add) {
			// 新增用户信息
			 new AddAccountsJFrame(this);
		} else if (e.getSource() == tool_modify) {
			// 获取当前选中要修改的数据
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要修改的用户");
			} else {
				// 修改用户信息
				 new UpdateAccountsJFrame(this, table, row);
			}
		} else if (e.getSource() == tool_delete) {
			// 删除当前选中的数据
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要删除的账号信息");
			} else {
				// 获取当前选中分类的id
				String id = (String) table.getValueAt(row, 0);
				Employee emp = null;
				try {
					emp = employeeService.getEmployeeById(employeeService.getEmployeeIdByAccountId(table.getValueAt(row,0).toString()));
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				int result = -1;
				if(emp!=null){
					 result = JOptionPane.showConfirmDialog(null, "当前账号尚有员工在使用，确认删除这条账号信息？");
				}else{
					result = JOptionPane.showConfirmDialog(null, "确认删除这条账号信息？");
				}
				if (result == 0) {
					// 确认删除，则执行删除
					try {
						userService.deleteAccounts(id);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// 显示删除成功信息面板，并刷新数据面板
					JOptionPane.showMessageDialog(null, "账号信息删除成功！");
					refreshTablePanel();
				}
			}
		}else if(e.getSource()==tool_power){
			// 为了提高员工效率，可批量对员工账号进行权限管理（批量激活员工账号）
			int[] rows = table.getSelectedRows();
			if(rows.length==0){
				JOptionPane.showMessageDialog(null, "请选择要操作的员工");
			}else{
				try {
					for(int i = 0;i<rows.length;i++){
						// 用户权限管理
						/**
						 * 先获取使用该账号的员工信息，通过部门对应的职位设定相应的权限，
						 * 如果是普通部门则判断其职位是否为主管、经理从而修改权限从0->2
						 * 如果是普通部门的普通员工则修改权限从0-3
						 * 如果是系统维护的员工则直接设置为超级管理员，修改权限从0->1
						 */
						Employee emp = employeeService.getEmployeeById(employeeService.getEmployeeIdByAccountId(table.getValueAt(rows[i],0).toString()));
						if(emp!=null){
							// 先判断是否为系统维修部
							if(emp.getDepartment_id().equals("0653000153")){
								Accounts newAccount = userService.getAccountsById(table.getValueAt(rows[i], 0).toString());
								newAccount.setLimits(1);
								userService.updateAccounts(newAccount);
							}else{
								//判断是否为普通员工或者是高级员工
								// 此处通过员工职位称号进行判断，存在弊端
								// （可以考虑人工进行判断，显示当前员工的职位信息然后手动修改，但存在弊端，没办法批量一次性进行操作）
								String jobName = jobService.getJobName(emp.getJob_id())+"";
								if(jobName.contains("经理")||jobName.contains("主管")){
									Accounts newAccount = userService.getAccountsById(table.getValueAt(rows[i], 0).toString());
									newAccount.setLimits(2);
									userService.updateAccounts(newAccount);
								}else{
									// 此处默认处理高级员工之外均认为是普通员工
									// （可以设置职位标识，用以标识是属于管理层还是普通员工）
									Accounts newAccount = userService.getAccountsById(table.getValueAt(rows[i], 0).toString());
									newAccount.setLimits(3);
									userService.updateAccounts(newAccount);
								}
							}
						}else{
							JOptionPane.showMessageDialog(null, "有部分账号没有员工在使用，无法进行操作！");
						}
					}
					JOptionPane.showMessageDialog(null, "员工账号批量激活成功！");
					this.refreshTablePanel();
				} catch (HeadlessException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		}else if(e.getSource()==tool_forbidden){
			// 禁用选中的员工账号
			int row = table.getSelectedRow();
			if(row<0){
				JOptionPane.showMessageDialog(null, "请选中要禁用的账号信息");
			}else{
				try {
					int choose = JOptionPane.showConfirmDialog(null, "确认禁用该账号信息？");
					if(choose==0){
						Accounts newAccount = userService.getAccountsById(table.getValueAt(row, 0).toString());
						newAccount.setLimits(0);
						userService.updateAccounts(newAccount);
						JOptionPane.showMessageDialog(null, "该账号已禁用！");
						this.refreshTablePanel();
					}
				} catch (HeadlessException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * 刷新数据面板
	 */
	public void refreshTablePanel() {
		// 移除当前数据面板中的所有数据
		backgroundPanel.removeAll();
		initTopPanel();
		initTablePanel();
		backgroundPanel.validate();// 验证
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

	// 获取焦点事件
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("请输入用户名称")) {
				keyword.setText("");
			}
		}
	}

	// 失去焦点事件
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("")) {
				keyword.setFont(MyFont.TipFont);
				keyword.setForeground(MyColor.TipColor);
				keyword.setText("请输入用户名称");
			} else {
				keyword.setForeground(MyColor.JTextFieldColor);
				keyword.setFont(MyFont.JTextFieldFont);
			}
		}
	}
}
