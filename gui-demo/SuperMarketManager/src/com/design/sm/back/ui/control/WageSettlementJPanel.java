package com.design.sm.back.ui.control;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import com.design.sm.back.ui.funciton.SalaryBalanceJFrame;
import com.design.sm.model.Accounts;
import com.design.sm.model.Department;
import com.design.sm.service.AccountsService;
import com.design.sm.service.DepartmentService;
import com.design.sm.service.EmployeeService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.DepartmentServiceImpl;
import com.design.sm.service.impl.EmployeeServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.Item;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.Tools;

public class WageSettlementJPanel implements MouseListener, ItemListener,
		FocusListener {

	// 定义全局组件
	public JPanel backgroundPanel, topPanel, toolPanel, searchPanel,
			tablePanel, pagePanel;
	// 定义下拉列表
	JComboBox select_department;
	// 借助工具类完成表格数据的封装
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// 定义用到的标签
	JLabel label_department, label_keyword,tool_balance,tool_export;
	// 定义相应的文本框、组合按钮
	JTextField keyword;
	JButton search;
	int maxPage;
	int currentPage;
	// 定义相应的分页组合标签
	JLabel label_all, label_start, label_end, label_last, label_next;
	JTextField page;
	// 定义相应的service
	AccountsService accountsService = new AccountsServiceImpl();
	EmployeeService employeeService = new EmployeeServiceImpl();
	DepartmentService departmentService = new DepartmentServiceImpl();
	Accounts loginUser;

	/**
	 * 通过构造方法完成初始化
	 */
	public WageSettlementJPanel(Accounts loginUser) {
		backgroundPanel = new JPanel(new BorderLayout());
		this.loginUser = loginUser;
		try {
			maxPage = employeeService.getEmployeeMaxPage();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 初始化布局
		initTopPanel();// 初始化顶部菜单条
		initTablePanel();// 初始化显示的表格数据
		initPagePanel();// 定义分页查找按钮组合
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

		Icon icon_balance = new ImageIcon("icons/toolImage/balance.png");
		tool_balance = new JLabel(icon_balance);
		tool_balance.setToolTipText("工资结算");// 设置鼠标移动时的显示内容
		tool_balance.addMouseListener(this);// 添加鼠标监听

		Icon icon_export = new ImageIcon("icons/toolImage/export.png");
		tool_export = new JLabel(icon_export);
		tool_export.setToolTipText("导出数据");// 设置鼠标移动时的显示内容
		tool_export.addMouseListener(this);// 添加鼠标监听

		// 将初始化完成的工具加载到工具条面板中
		toolPanel.add(tool_balance);
		toolPanel.add(tool_export);
		// 最终将工具条面板加载到顶部菜单条的最西面
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * 初始化查找面板 设置查找方式： 1.根据所属部门查找 2.员工名称关键字查找
	 */
	private void initSearchPanel() {
		searchPanel = new JPanel();
		// 设置员工分类下拉列表的所有属性
		label_department = new JLabel("所属部门");
		select_department = new JComboBox();
		select_department.addItem("所有内容");
		select_department.addItemListener(this);
		// 获取数据库中所有员工分类信息
		List<Department> list_dept = null;
		try {
			list_dept = departmentService.findAllDepartment();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 将获取的每项数据信息封装为Item对象
		for (Department d : list_dept) {
			// 封装id、name信息
			String did = d.getDepartment_id();
			String dname = d.getDepartment_name();
			Item item = new Item(did, dname);
			select_department.addItem(item);
		}

		// 设置关键字查找
		label_keyword = new JLabel("员工姓名");
		keyword = new JTextField(10);
		keyword.setText("关键字");
		keyword.addFocusListener(this);
		search = new JButton("查找");
		search.addMouseListener(this);

		// 将相关组件加载到指定的面板中
		JPanel jp1 = new JPanel();
		jp1.add(label_department);
		jp1.add(select_department);
		JPanel jp2 = new JPanel();
		jp2.add(label_keyword);
		jp2.add(keyword);
		jp2.add(search);
		searchPanel.add(jp1);
		searchPanel.add(jp2);
		// 将布局好的组件加载到菜单栏面板的最东面
		topPanel.add(searchPanel, BorderLayout.EAST);
	}

	/**
	 * 初始化显示的表格数据
	 */
	private void initTablePanel() {
		// 要根据下拉框的选项进行筛选数据(要根据employeeService中返回的行进行设置，随后选择要隐藏的项目即可)
		String[] params = { "员工id", "员工编号", "员工姓名", "身份证号", "年龄", "性别", "联系方式",
				"电子邮箱", "家庭住址", "入职日期", "账号id", "账号名称", "职位id", "在任职位", "部门id",
				"隶属部门","基本工资","提成" };
		Vector<Vector> vec = new Vector<>();
		// 获取当前下拉框的选择
		Object dItem = select_department.getSelectedItem();
		if (!dItem.equals("所有内容")) {
			try {
				Item item = (Item) dItem;
				vec = employeeService.pack(employeeService
						.getEmployeeByDeptId(item.getKey()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				vec = employeeService.pack(employeeService.findAllEmployee());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 将查询到的数据封装到BbaseTableModule中
		baseTableModule = new BaseTableModule(params, vec);
		table = new JTable(baseTableModule);
		// 渲染第0列，将其显示为多选框进行显示
		table.getColumnModel().getColumn(0)
				.setCellRenderer(new TableCellRenderer() {
					@Override
					public Component getTableCellRendererComponent(
							JTable table, Object value, boolean isSelected,
							boolean hasFocus, int row, int column) {
						JCheckBox ck = new JCheckBox();
						ck.setSelected(isSelected);
						ck.setHorizontalAlignment((int) 0.5f);
						return ck;
					}
				});
		// 利用提供的Tools类美化表格
		Tools.setTableStyle(table);

		// 通过设置行的大小，隐藏某一列内容（只显示想要显示的数据）
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) table
				.getColumnModel();
		dcm.getColumn(3).setMinWidth(0);
		dcm.getColumn(3).setMaxWidth(0);
		dcm.getColumn(4).setMinWidth(0);
		dcm.getColumn(4).setMaxWidth(0);
		dcm.getColumn(5).setMinWidth(0);
		dcm.getColumn(5).setMaxWidth(0);
		dcm.getColumn(8).setMinWidth(0);
		dcm.getColumn(8).setMaxWidth(0);
		dcm.getColumn(10).setMinWidth(0);
		dcm.getColumn(10).setMaxWidth(0);
		dcm.getColumn(11).setMinWidth(0);
		dcm.getColumn(11).setMaxWidth(0);
		dcm.getColumn(12).setMinWidth(0);
		dcm.getColumn(12).setMaxWidth(0);
		dcm.getColumn(14).setMinWidth(0);
		dcm.getColumn(14).setMaxWidth(0);
		// 设置滚动条
		jScrollPane = new JScrollPane(table);
		Tools.setJspStyle(jScrollPane);

		tablePanel = new JPanel(new BorderLayout());
		tablePanel.setOpaque(false);// 设置透明度
		tablePanel.add(jScrollPane);
		// 将组件加载到背景中
		backgroundPanel.add(tablePanel, BorderLayout.CENTER);
		// 在刷新数据的时候改变窗体大小，完成数据刷新
		backgroundPanel.validate();
	}

	/**
	 * 定义分页查找按钮组合
	 */
	private void initPagePanel() {
		pagePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		label_all = new JLabel("显示所有");
		label_all.setFont(MyFont.JLabelFont);
		label_all.setForeground(MyColor.JLabelColor);
		label_all.addMouseListener(this);

		label_start = new JLabel("首页");
		label_start.setFont(MyFont.JLabelFont);
		label_start.setForeground(MyColor.JLabelColor);
		label_start.addMouseListener(this);

		label_end = new JLabel("尾页");
		label_end.setFont(MyFont.JLabelFont);
		label_end.setForeground(MyColor.JLabelColor);
		label_end.addMouseListener(this);

		page = new JTextField(5);
		page.setFont(MyFont.JLabelFont);
		page.setForeground(MyColor.JLabelColor);
		page.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				currentPage = 1;
				refreshTablePanelByPage();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				Pattern pattern = Pattern.compile("[0-9]*");
				if (pattern.matcher(page.getText()).matches()) {
					currentPage = Integer.valueOf(page.getText());
					refreshTablePanelByPage();
				} else {
					JOptionPane.showMessageDialog(null, "请输入合法的数字！");
					currentPage = 1;
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});

		label_last = new JLabel("上一页");
		label_last.setFont(MyFont.JLabelFont);
		label_last.setForeground(MyColor.JLabelColor);
		label_last.addMouseListener(this);

		label_next = new JLabel("下一页");
		label_next.setFont(MyFont.JLabelFont);
		label_next.setForeground(MyColor.JLabelColor);
		label_next.addMouseListener(this);

		pagePanel.add(label_all);
		pagePanel.add(label_start);
		pagePanel.add(label_last);
		pagePanel.add(page);
		pagePanel.add(label_next);
		pagePanel.add(label_end);
		// 将组件加载到背景中
		backgroundPanel.add(pagePanel, BorderLayout.SOUTH);
		// 在刷新数据的时候改变窗体大小，完成数据刷新
		backgroundPanel.validate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == label_all) {
			refreshTablePanelBySearch();
		} else if (e.getSource() == label_start) {
			page.setText("1");
			refreshTablePanelByPage();
		} else if (e.getSource() == label_end) {
			page.setText(String.valueOf(maxPage));
			refreshTablePanelByPage();
		} else if (e.getSource() == label_last) {
			// 获取当前页数进行比较
			if (currentPage > 1) {
				page.setText((currentPage - 1) + "");
			} else if (currentPage == 1) {
				page.setText("1");
			}
			refreshTablePanelByPage();
		} else if (e.getSource() == label_next) {
			// 获取当前页数进行比较
			if (currentPage < maxPage) {
				page.setText((currentPage + 1) + "");
			} else if (currentPage == maxPage) {
				page.setText(maxPage + "");
			}
			refreshTablePanelByPage();
		} else if (e.getSource() == search) {
			// 先移除当前面板的所有数据
			tablePanel.removeAll();
			String[] params = { "员工id", "员工编号", "员工姓名", "身份证号", "年龄", "性别",
					"联系方式", "电子邮箱", "家庭住址", "入职日期", "账号id", "账号名称", "职位id",
					"在任职位", "部门id", "隶属部门" };
			Vector<Vector> vec = new Vector<>();
			// 获取文本框以及单选框的内容
			String keyword_string = keyword.getText();
			if (keyword_string.equals("关键字")) {
				try {
					vec = employeeService.pack(employeeService
							.findAllEmployee());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} else if (!keyword_string.equals("关键字")) {
				String text = "%" + keyword.getText() + "%";
				try {
					vec = employeeService.pack(employeeService
							.getEmployeeByNameKeyword(text));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			// 将查询到的数据封装到BbaseTableModule中
			baseTableModule = new BaseTableModule(params, vec);
			table = new JTable(baseTableModule);
			// 渲染第0列，将其显示为多选框进行显示
			table.getColumnModel().getColumn(0)
					.setCellRenderer(new TableCellRenderer() {
						@Override
						public Component getTableCellRendererComponent(
								JTable table, Object value, boolean isSelected,
								boolean hasFocus, int row, int column) {
							JCheckBox ck = new JCheckBox();
							ck.setSelected(isSelected);
							ck.setHorizontalAlignment((int) 0.5f);
							return ck;
						}
					});
			// 利用提供的Tools类美化表格
			Tools.setTableStyle(table);
			// 通过设置行的大小，隐藏某一列内容（只显示想要显示的数据）
			DefaultTableColumnModel dcm = (DefaultTableColumnModel) table
					.getColumnModel();
			dcm.getColumn(3).setMinWidth(0);
			dcm.getColumn(3).setMaxWidth(0);
			dcm.getColumn(4).setMinWidth(0);
			dcm.getColumn(4).setMaxWidth(0);
			dcm.getColumn(5).setMinWidth(0);
			dcm.getColumn(5).setMaxWidth(0);
			dcm.getColumn(8).setMinWidth(0);
			dcm.getColumn(8).setMaxWidth(0);
			dcm.getColumn(10).setMinWidth(0);
			dcm.getColumn(10).setMaxWidth(0);
			dcm.getColumn(11).setMinWidth(0);
			dcm.getColumn(11).setMaxWidth(0);
			dcm.getColumn(12).setMinWidth(0);
			dcm.getColumn(12).setMaxWidth(0);
			dcm.getColumn(14).setMinWidth(0);
			dcm.getColumn(14).setMaxWidth(0);

			// 设置滚动条
			jScrollPane = new JScrollPane(table);
			Tools.setJspStyle(jScrollPane);

			tablePanel = new JPanel(new BorderLayout());
			tablePanel.setOpaque(false);// 设置透明度
			tablePanel.add(jScrollPane);
			// 将组件加载到背景中
			backgroundPanel.add(tablePanel, BorderLayout.CENTER);
			// 在刷新数据的时候改变窗体大小，完成数据刷新
			backgroundPanel.validate();
		} else if (e.getSource() == tool_export) {
			// 获取当前选中的数据
			String[] ids;
			ArrayList id_list = new ArrayList<>();
			for (int rowindex : table.getSelectedRows()) {
				Object obj = table.getValueAt(rowindex, 0);
				id_list.add(obj);
			}
			// 集合转数组
			ids = (String[]) id_list.toArray(new String[id_list.size()]);
			int result = employeeService.exportSalaryData(ids);
			if (result == 1) {
				JOptionPane.showMessageDialog(null, "数据导出成功！");
			} else if (result == -1) {
				JOptionPane.showMessageDialog(null, "抱歉！数据导出失败，再试一遍吧！");
			} else if (result == 0) {
				JOptionPane.showMessageDialog(null, "用户取消了操作！");
			}
		}else if(e.getSource()==tool_balance){
			// 对员工进行总的工资结算
			new SalaryBalanceJFrame(this,this.loginUser);
		}
	}

	/**
	 * 刷新数据面板
	 */
	public void refreshTablePanelBySearch() {
		// 移除当前数据面板中的所有数据
		backgroundPanel.removeAll();
		initTopPanel();
		initTablePanel();
		initPagePanel();
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

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			// 如果点击了下拉框选项，仅仅刷新数据面板,要先将数据面板的内容移除之后再添加
			tablePanel.removeAll();// 移除数据面板中的所有数据
			initTablePanel();// 重新初始化面板
		}
	}

	/**
	 * 聚焦事件
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("关键字")) {
				keyword.setText("");
				keyword.setFont(MyFont.JTextFieldFont);
				keyword.setForeground(MyColor.JTextFieldColor);
			}
		}
	}

	/**
	 * 失去焦点事件
	 */
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("")) {
				keyword.setText("关键字");
				keyword.setFont(MyFont.TipFont);
				keyword.setForeground(MyColor.TipColor);
			}
		}
	}

	/**
	 * 分页显示调用方法：刷新表格数据 此处分页针对的是所有员工的分页，忽略条件组合查找的限制
	 */
	public void refreshTablePanelByPage() {
		backgroundPanel.remove(tablePanel);
		// 要根据下拉框的选项进行筛选数据(要根据employeeService中返回的行进行设置，随后选择要隐藏的项目即可)
		String[] params = { "员工id", "员工编号", "员工姓名", "身份证号", "年龄", "性别", "联系方式",
				"电子邮箱", "家庭住址", "入职日期", "账号id", "账号名称", "职位id", "在任职位", "部门id",
				"隶属部门","基本工资","提成" };
		Vector<Vector> vec = new Vector<>();
		try {
			vec = employeeService.pack(employeeService
					.getAllEmployeeByPage(currentPage));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 将查询到的数据封装到BbaseTableModule中
		baseTableModule = new BaseTableModule(params, vec);
		table = new JTable(baseTableModule);
		// 渲染第0列，将其显示为多选框进行显示
		table.getColumnModel().getColumn(0)
				.setCellRenderer(new TableCellRenderer() {
					@Override
					public Component getTableCellRendererComponent(
							JTable table, Object value, boolean isSelected,
							boolean hasFocus, int row, int column) {
						JCheckBox ck = new JCheckBox();
						ck.setSelected(isSelected);
						ck.setHorizontalAlignment((int) 0.5f);
						return ck;
					}
				});
		// 利用提供的Tools类美化表格
		Tools.setTableStyle(table);

		// 通过设置行的大小，隐藏某一列内容（只显示想要显示的数据）
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) table
				.getColumnModel();
		dcm.getColumn(3).setMinWidth(0);
		dcm.getColumn(3).setMaxWidth(0);
		dcm.getColumn(4).setMinWidth(0);
		dcm.getColumn(4).setMaxWidth(0);
		dcm.getColumn(5).setMinWidth(0);
		dcm.getColumn(5).setMaxWidth(0);
		dcm.getColumn(8).setMinWidth(0);
		dcm.getColumn(8).setMaxWidth(0);
		dcm.getColumn(10).setMinWidth(0);
		dcm.getColumn(10).setMaxWidth(0);
		dcm.getColumn(11).setMinWidth(0);
		dcm.getColumn(11).setMaxWidth(0);
		dcm.getColumn(12).setMinWidth(0);
		dcm.getColumn(12).setMaxWidth(0);
		dcm.getColumn(14).setMinWidth(0);
		dcm.getColumn(14).setMaxWidth(0);

		// 设置滚动条
		jScrollPane = new JScrollPane(table);
		Tools.setJspStyle(jScrollPane);

		tablePanel = new JPanel(new BorderLayout());
		tablePanel.setOpaque(false);// 设置透明度
		tablePanel.add(jScrollPane);
		// 将组件加载到背景中
		backgroundPanel.add(tablePanel, BorderLayout.CENTER);
		// 在刷新数据的时候改变窗体大小，完成数据刷新
		backgroundPanel.validate();
	}
}
