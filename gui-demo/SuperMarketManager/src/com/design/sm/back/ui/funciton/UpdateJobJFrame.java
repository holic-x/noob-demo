package com.design.sm.back.ui.funciton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.model.Department;
import com.design.sm.model.Job;
import com.design.sm.service.DepartmentService;
import com.design.sm.service.JobService;
import com.design.sm.service.impl.DepartmentServiceImpl;
import com.design.sm.service.impl.JobServiceImpl;
import com.design.sm.utils.DataValidation;
import com.design.sm.utils.Item;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.RandomGeneration;

public class UpdateJobJFrame extends JFrame implements MouseListener {
	// 定义全局组件
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_name, label_base_sal, label_commission, label_descr,
			label_department;
	JTextField name, base_sal, commission;
	JTextArea descr;
	JComboBox department;
	JButton save_button, reset_button;

	DepartmentService departmentService = new DepartmentServiceImpl();
	JobService jobService = new JobServiceImpl();

	// 定义父对象
	JobManagerJFrame parentPanel;
	JTable table;
	int selectedRow;

	// 通过构造方法初始化数据
	public UpdateJobJFrame(JobManagerJFrame parentPanel, JTable table,
			int selectedRow) {
		this.parentPanel = parentPanel;
		this.table = table;
		this.selectedRow = selectedRow;
		// 初始化背景
		initBackgroundPanel();
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("修改职位信息");
		this.setSize(420, 480);
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
		JLabel title = new JLabel("修改职位信息");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * 初始化内容面板
	 */
	private void initContentPanel() {
		/**
		 * 职位的信息： 职位id：随机生成的10int的数据 职位名称、基本工资、提成、职位描述、对应部门id、
		 */
		contentPanel = new JPanel();
		JPanel jp1 = new JPanel();
		label_name = new JLabel("职位名称");
		name = new JTextField(15);
		name.setFont(MyFont.JTextFieldFont);
		name.setForeground(MyColor.JTextFieldColor);
		jp1.add(label_name);
		jp1.add(name);

		JPanel jp2 = new JPanel();
		label_base_sal = new JLabel("基本工资");
		base_sal = new JTextField(15);
		base_sal.setFont(MyFont.JTextFieldFont);
		base_sal.setForeground(MyColor.JTextFieldColor);
		jp2.add(label_base_sal);
		jp2.add(base_sal);

		JPanel jp3 = new JPanel();
		label_commission = new JLabel("提成    ");
		commission = new JTextField(15);
		commission.setFont(MyFont.JTextFieldFont);
		commission.setForeground(MyColor.JTextFieldColor);
		jp3.add(label_commission);
		jp3.add(commission);

		JPanel jp4 = new JPanel();
		label_descr = new JLabel("职位描述");
		descr = new JTextArea(6, 15);
		descr.setFont(MyFont.JTextFieldFont);
		descr.setForeground(MyColor.JTextFieldColor);
		descr.setLineWrap(true);// 自动换行
		jp4.add(label_descr);
		jp4.add(descr);

		JPanel jp5 = new JPanel();
		label_department = new JLabel("所属部门");
		department = new JComboBox();
		department.setPreferredSize(new Dimension(175, 30));
		jp5.add(label_department);
		jp5.add(department);

		// 回显数据
		this.echoData();
		Box ver = Box.createVerticalBox();
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp3);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp4);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp5);
		// 将组件加载到contentPanel面板中
		contentPanel.add(ver);
	}

	/**
	 * 初始化按钮面板
	 */
	private void initButtonPanel() {
		Box hor = Box.createHorizontalBox();
		buttonPanel = new JPanel();
		save_button = new JButton("保存");
		save_button.setForeground(Color.white);
		save_button.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.blue));

		reset_button = new JButton("重置");
		reset_button.setForeground(Color.white);
		reset_button.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.red));

		// 添加按钮监听
		save_button.addMouseListener(this);
		reset_button.addMouseListener(this);

		// 将按钮加载到面板中
		hor.add(save_button);
		hor.add(Box.createHorizontalStrut(20));
		hor.add(reset_button);
		buttonPanel.add(hor);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == save_button) {
			// 获取当前文本框的数据信息
			String name_string = name.getText();
			String base_sal_string = base_sal.getText();
			String commission_string = commission.getText();
			String descr_string = descr.getText();
			String department_id_string = "";
			if (name_string.equals("必填项")) {
				JOptionPane.showMessageDialog(null, "职位名称不能为空");
			} else if (base_sal_string.equals("必填项")) {
				JOptionPane.showMessageDialog(null, "基本工资不能为空");
			} else {
				// 验证数据是否合法
				if (!DataValidation.isBigDecimal(base_sal_string)) {
					JOptionPane.showMessageDialog(null, "基本工资输入格式非法！！");
				} else if (!DataValidation.isSmallDecimal(commission_string)) {
					JOptionPane.showMessageDialog(null, "提成输入格式非法！！");
				} else {
					double base_sal_double = Double.valueOf(base_sal_string);
					double commission_double = Double
							.valueOf(commission_string);
					// 获取部门下拉框选项
					Item select_dept = (Item) department.getSelectedItem();
					department_id_string = select_dept.getKey();
					// 创建Job对象，将数据保存到数据库中
					// 职位id是随机生成的10位int类型的字符拼接的数据,在修改职位信息的时候不能够修改职位信息
					String id = table.getValueAt(selectedRow, 0).toString();
					Job job = new Job(id, name_string, base_sal_double,
							commission_double, descr_string,
							department_id_string);
					int choose = JOptionPane.showConfirmDialog(null,
							"确认修改职位信息？");
					if (choose == 0) {
						try {
							jobService.updateJob(job);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						// 完成数据的保存之后输出提示信息，并将子页面隐藏，刷新主面板的数据
						JOptionPane.showMessageDialog(null, "职位信息保存成功");
						this.setVisible(false);
						parentPanel.refreshTablePanel();
					} else {
						this.setVisible(false);
					}
				}
			}
		} else if (e.getSource() == reset_button) {
			// 回显数据
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
		// 从当前选中的表格数据记录中获取相应的职位信息进行数据回显
		String name_string = table.getValueAt(selectedRow, 1).toString();
		String base_sal_string = table.getValueAt(selectedRow, 2).toString();
		String commission_string = table.getValueAt(selectedRow, 3).toString();
		String descr_string = table.getValueAt(selectedRow, 4).toString();
		String department_id_string = table.getValueAt(selectedRow, 5)
				.toString();
		// 数据回显
		name.setText(name_string);
		base_sal.setText(base_sal_string);
		commission.setText(commission_string);
		descr.setText(descr_string);

		// 重新加载下拉列表，并回显数据
		department.removeAllItems();
		List<Department> list_dept = null;
		try {
			list_dept = departmentService.findAllDepartment();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i=0;i<list_dept.size();i++) {
			int sign = 0;
			String did = list_dept.get(i).getDepartment_id();
			String dname = list_dept.get(i).getDepartment_name();
			Item item = new Item(did, dname);
			department.addItem(item);
			if(did.equals(department_id_string)){
				sign = i;
				department.setSelectedIndex(sign);
			}
		}
	}
}