package com.design.sm.back.ui.funciton;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Map;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.design.sm.back.ui.control.WageSettlementJPanel;
import com.design.sm.model.Accounts;
import com.design.sm.service.DepartmentService;
import com.design.sm.service.EmployeeService;
import com.design.sm.service.impl.DepartmentServiceImpl;
import com.design.sm.service.impl.EmployeeServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.Tools;

/**
 * 进行各部门工资结算
 */
public class SalaryBalanceJFrame extends JFrame implements MouseListener {

	// 定义全局组件
	JPanel backgroundPanel, topPanel, toolPanel, tablePanel;
	// 借助工具类完成表格数据的封装
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// 定义用到的标签
	JLabel tool_export;
	// 定义相应的service
	EmployeeService employeeService = new EmployeeServiceImpl();
	DepartmentService departmentService = new DepartmentServiceImpl();

	WageSettlementJPanel parentPanel;
	Accounts loginUser;

	/**
	 * 通过构造方法完成初始化
	 */
	public SalaryBalanceJFrame(WageSettlementJPanel parentPanel,
			Accounts loginUser) {
		this.parentPanel = parentPanel;
		this.loginUser = loginUser;
		backgroundPanel = new JPanel(new BorderLayout());
		// 初始化布局
		initTopPanel();// 初始化顶部菜单条
		initTablePanel();// 初始化显示的表格数据
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("工资结算");
		this.setSize(600, 600);
		this.setVisible(true);
		this.setLocationRelativeTo(null);// 设置依赖项
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);// 设置关闭方式
		// 当前窗口隐藏，不影响后方数据的使用，而不是关闭整个窗口
	}

	/**
	 * 初始化顶部的菜单条
	 */
	private void initTopPanel() {
		topPanel = new JPanel(new BorderLayout());
		// 初始化工具条面板
		initToolPanel();
		// 将顶部菜单栏加载到背景面板中
		backgroundPanel.add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * 初始化工具条面板
	 */
	private void initToolPanel() {
		toolPanel = new JPanel();

		Icon icon_export = new ImageIcon("icons/toolImage/export.png");
		tool_export = new JLabel(icon_export);
		tool_export.setToolTipText("数据导出");// 设置鼠标移动时的显示内容
		tool_export.addMouseListener(this);// 添加鼠标监听

		// 将初始化完成的工具加载到工具条面板中
		toolPanel.add(tool_export);
		// 最终将工具条面板加载到顶部菜单条的最西面
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * 初始化显示的表格数据
	 */
	private void initTablePanel() {

		// 输入的数据进行查找
		String[] params = { "部门名称", "部门人数", "总工资", "平均工资" };
		Vector<Vector> vec = new Vector<>();
		// 根据部门id一一对应
		Map<String, Integer> emp_count = null;
		Map<String, Double> salary_count = null;
		try {
			emp_count = employeeService.getEmployeeSumByDeptId();
			salary_count = employeeService.getSalarySumByDeptId();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 封装查找到的结果
		// 获取id
		Object[] key1 = emp_count.keySet().toArray();
		Object[] key2 = salary_count.keySet().toArray();
		for (int i = 0; i < key1.length; i++) {
			for (int j = 0; j < key2.length; j++) {
				if (key1[i].equals(key2[j])) {
					Vector temp = new Vector<String>();
					for (int k = 0; k < 4; k++) {
						try {
							temp.add(departmentService
									.getDepartmentName(key1[i].toString()));
						} catch (SQLException e) {
							e.printStackTrace();
						}// 部门名称
						temp.add(emp_count.get(key1[i].toString()));// 部门总人数
						temp.add(salary_count.get(key1[i].toString()));// 总工资
						// 计算平均工资(利用BigDecimal进行大数运算)
						BigDecimal b1 = BigDecimal.valueOf(salary_count
								.get(key1[i].toString()));
						BigDecimal b2 = BigDecimal.valueOf(emp_count
								.get(key1[i].toString()));
						double avg_sal = Double.valueOf(b1.divide(b2, 2)
								.toString());
						temp.add(avg_sal);
					}
					vec.add(temp);
				}
			}
		}

		// 将查询到的数据封装到BbaseTableModule中
		baseTableModule = new BaseTableModule(params, vec);
		table = new JTable(baseTableModule);
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
		backgroundPanel.validate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tool_export) {
			// 导出统计数据
			int result = employeeService.exportBalanceData(this.table);
			if (result == 1) {
				JOptionPane.showMessageDialog(null, "数据导出成功！");
			} else if (result == -1) {
				JOptionPane.showMessageDialog(null, "抱歉！数据导出失败，再试一遍吧！");
			} else if (result == 0) {
				JOptionPane.showMessageDialog(null, "用户取消了操作！");
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

}