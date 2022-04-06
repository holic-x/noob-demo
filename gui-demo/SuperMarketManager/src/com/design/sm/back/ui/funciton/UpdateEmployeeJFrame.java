package com.design.sm.back.ui.funciton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.back.ui.control.EmployeeManagerJPanel;
import com.design.sm.model.Accounts;
import com.design.sm.model.Department;
import com.design.sm.model.Employee;
import com.design.sm.model.Job;
import com.design.sm.service.AccountsService;
import com.design.sm.service.DepartmentService;
import com.design.sm.service.EmployeeService;
import com.design.sm.service.JobService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.DepartmentServiceImpl;
import com.design.sm.service.impl.EmployeeServiceImpl;
import com.design.sm.service.impl.JobServiceImpl;
import com.design.sm.utils.DataValidation;
import com.design.sm.utils.Item;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.eltima.components.ui.DatePicker;

public class UpdateEmployeeJFrame extends JFrame implements MouseListener,
		ItemListener {

	// 定义全局组件
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_employee_name, label_id_card, label_age, label_gender,
			label_phone, label_email, label_address, label_hire_date,
			label_job, label_department;
	JTextField employee_name, id_card, age, phone, email, address;
	ButtonGroup gender;
	JRadioButton nan, nv;
	DatePicker hire_date;
	JComboBox department, job;
	JButton save, reset;
	// 得到屏幕大小
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// 定义service
	EmployeeService employeeService = new EmployeeServiceImpl();
	DepartmentService departmentService = new DepartmentServiceImpl();
	JobService jobService = new JobServiceImpl();
	AccountsService accountsService = new AccountsServiceImpl();

	// 定义父对象、当前登录员工、表格、选中行
	Accounts loginUser;
	EmployeeManagerJPanel parentPanel;
	JTable table;
	int selectedRow;

	// 通过构造方法初始化数据
	public UpdateEmployeeJFrame(EmployeeManagerJPanel parentPanel,
			Accounts loginUser, JTable table, int selectedRow) {
		this.parentPanel = parentPanel;
		this.loginUser = loginUser;
		this.table = table;
		this.selectedRow = selectedRow;
		// 初始化背景
		initBackgroundPanel();
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("修改员工信息");
		this.setSize(600, 580);
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
		JLabel title = new JLabel("修改员工信息");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * 初始化内容面板
	 */
	private void initContentPanel() {
		contentPanel = new JPanel();
		JPanel jp1 = new JPanel();
		label_employee_name = new JLabel("员工名称");
		employee_name = new JTextField(15);
		employee_name.setFont(MyFont.JTextFieldFont);
		employee_name.setForeground(MyColor.JTextFieldColor);
		jp1.add(label_employee_name);
		jp1.add(employee_name);

		JPanel jp2 = new JPanel();
		label_id_card = new JLabel("身份证号");
		id_card = new JTextField(15);
		id_card.setFont(MyFont.JTextFieldFont);
		id_card.setForeground(MyColor.JTextFieldColor);
		jp2.add(label_id_card);
		jp2.add(id_card);

		JPanel jp3 = new JPanel();
		label_age = new JLabel("员工年龄");
		age = new JTextField(15);
		age.setFont(MyFont.JTextFieldFont);
		age.setForeground(MyColor.JTextFieldColor);
		jp3.add(label_age);
		jp3.add(age);

		JPanel jp4 = new JPanel();
		label_gender = new JLabel("员工性别");
		gender = new ButtonGroup();
		nan = new JRadioButton("男");
		nv = new JRadioButton("女");
		gender.add(nan);
		gender.add(nv);
		jp4.add(label_gender);
		jp4.add(nan);
		jp4.add(nv);

		JPanel jp5 = new JPanel();
		label_phone = new JLabel("联系方式");
		phone = new JTextField(15);
		phone.setFont(MyFont.JTextFieldFont);
		phone.setForeground(MyColor.JTextFieldColor);
		jp5.add(label_phone);
		jp5.add(phone);

		JPanel jp6 = new JPanel();
		label_email = new JLabel("电子邮箱");
		email = new JTextField(15);
		email.setFont(MyFont.JTextFieldFont);
		email.setForeground(MyColor.JTextFieldColor);
		jp6.add(label_email);
		jp6.add(email);

		JPanel jp7 = new JPanel();
		label_address = new JLabel("家庭住址");
		address = new JTextField(15);
		address.setFont(MyFont.JTextFieldFont);
		address.setForeground(MyColor.JTextFieldColor);
		jp7.add(label_address);
		jp7.add(address);

		JPanel jp8 = new JPanel();
		label_hire_date = new JLabel("入职日期");
		String DefaultFormat = "yyyy-MM-dd HH:mm:ss";
		// 当前时间
		String hire_date_string = table.getValueAt(selectedRow, 9).toString();
		// 回显日期信息
		Date date = new Date();
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(hire_date_string);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		hire_date = new DatePicker(date, DefaultFormat, MyFont.JTextFieldFont,
				new Dimension(177, 24));
		hire_date.setSize(new Dimension(180, 50));
		jp8.add(label_hire_date);
		jp8.add(hire_date);

		JPanel jp9 = new JPanel();
		label_department = new JLabel("所属部门");
		department = new JComboBox();
		department.setPreferredSize(new Dimension(175, 30));
		department.addItemListener(this);
		jp9.add(label_department);
		jp9.add(department);

		JPanel jp10 = new JPanel();
		label_job = new JLabel("任职岗位");
		job = new JComboBox();
		job.setPreferredSize(new Dimension(175, 30));
		// 根据用户选择的部门的变化，联动显示相应的职位信息
		jp10.add(label_job);
		jp10.add(job);

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
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp6);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp7);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp8);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp9);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp10);
		// 将组件加载到contentPanel面板中
		contentPanel.add(ver);
	}

	/**
	 * 初始化按钮面板
	 */
	private void initButtonPanel() {
		buttonPanel = new JPanel();
		save = new JButton("保存");
		save.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));
		save.setForeground(Color.white);
		save.setFont(MyFont.JButtonFont);
		save.addMouseListener(this);

		reset = new JButton("重置");
		reset.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));
		reset.setForeground(Color.white);
		reset.setFont(MyFont.JButtonFont);
		reset.addMouseListener(this);

		buttonPanel.add(save);
		buttonPanel.add(reset);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// 获取当前文本框的数据、根据用户的输入情况对数据进行处理
		String employee_name_string = employee_name.getText();
		String id_card_string = id_card.getText();
		String age_string = age.getText();
		String phone_string = phone.getText();
		String email_string = email.getText();
		String address_string = address.getText();
		if (e.getSource() == save) {
			if (employee_name_string.equals("必填项")) {
				JOptionPane.showMessageDialog(null, "员工名称不能为空！");
			} else if (id_card_string.equals("必填项")) {
				// 提供默认值
				JOptionPane.showMessageDialog(null, "身份证号不能为空！");
			}
			// 要注意逻辑判断
			if (age_string.equals("")) {
				age_string = "0";
			}
			if (phone_string.equals("")) {
				phone_string = "空";
			}
			if (email_string.equals("")) {
				email_string = "空";
			}
			if (address_string.equals("")) {
				address_string = "空";
			}
			int age_int;
			// 对数据进行处理(验证、转化)
			if (!DataValidation.isValidIdCard(id_card_string)) {
				JOptionPane.showMessageDialog(null, "请输入正确的身份证信息！");
			} else if ((!DataValidation.isValidAge(age_string))
					&& (!age_string.equals("0"))) {
				JOptionPane.showMessageDialog(null, "年龄输入范围为1-120之间！");
			} else if ((!DataValidation.isValidPhone(phone_string))
					&& (!phone_string.equals("空"))) {
				JOptionPane.showMessageDialog(null, "请输入正确的手机号！");
			} else if ((!DataValidation.isValidEmail(email_string))
					&& (!email_string.equals("空"))) {
				JOptionPane.showMessageDialog(null, "请输入正确的电子邮箱信息！");
			} else {
				int choose = JOptionPane.showConfirmDialog(null, "确认修改员工信息?");
				if(choose==0){
					age_int = Integer.valueOf(age_string);
					// 获取单选框选项
					String gender_string = "";
					if (nan.isSelected()) {
						gender_string = "男";
					} else if (nv.isSelected()) {
						gender_string = "女";
					}
					// 获取当前下拉框的数据信息(所属部门、任职岗位)
					Item dept_item = (Item) department.getSelectedItem();
					Item job_item = (Item) job.getSelectedItem();
					// 根据下拉框信息获取相应要封装的数据信息
					String dept_id = dept_item.getKey();
					String job_id = job_item.getKey();
					/**
					 *  随机生成32char员工id、并按照一定的规则生成员工编号,
					 *  在修改员工信息的时候id与员工编号均不能改变
					 *  且只是修改员工的基本信息，并不涉及账号的修改
					 *  账号的修改由应当由员工自行设置
					 */
					String emp_id_string = table.getValueAt(selectedRow, 0).toString();
					String emp_num_string = table.getValueAt(selectedRow, 0).toString();
					String account_id_string = table.getValueAt(selectedRow, 10).toString();
					String hire_date_string = hire_date.getText();
					// 创建员工对象，加载数据进行保存
					Employee emp = new Employee(emp_id_string, employee_name_string,
							emp_num_string, id_card_string, age_int, gender_string,
							phone_string, email_string, address_string,
							hire_date_string, account_id_string, job_id, dept_id);
					// 调用方法保存数据
					try {
						employeeService.updateEmployee(emp);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// 输出提示信息，隐藏子页面，刷新主面板
					JOptionPane.showMessageDialog(null, "员工信息保存成功！");
					this.setVisible(false);
					parentPanel.refreshTablePanelBySearch();
				}
			}
		} else if (e.getSource() == reset) {
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

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			// 如果用户选择了相应的部门，则加载相应的职位下拉框选项
			// 获取当前用户的选择,移除job下拉框的所有内容，重新加载数据
			job.removeAllItems();
			Item item_select = (Item) department.getSelectedItem();
			String deptId = item_select.getKey();
			List<Job> list_job = null;
			try {
				list_job = jobService.getJobByDeptmentId(deptId);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			for (Job j : list_job) {
				String jid = j.getJob_id();
				String jname = j.getJob_name();
				Item item = new Item(jid, jname);
				job.addItem(item);
			}
		}
	}

	/**
	 * 定义数据回显方法
	 */
	public void echoData() {
		// 从当前选中的表格中获取员工信息
		// 可以依次通过表格直接获取数据，亦可通过员工id查找详细的员工信息
		Employee emp = null;
		try {
			emp = employeeService.getEmployeeById(table.getValueAt(selectedRow,
					0).toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String name_string = emp.getEmployee_name();
		String id_card_string = emp.getId_card();
		int age_int = emp.getAge();
		String gender_string = emp.getGender();
		String phone_string = emp.getPhone();
		String email_string = emp.getEmail();
		String address_string = emp.getAddress();
		String hire_date_string = emp.getHire_date();
		String dept_id_string = emp.getDepartment_id();
		String job_id_string = emp.getJob_id();

		employee_name.setText(name_string);
		id_card.setText(id_card_string);
		age.setText(age_int + "");
		if (gender_string.equals("男")) {
			nan.setSelected(true);
			nv.setSelected(false);
		} else if (gender_string.equals("女")) {
			nv.setSelected(true);
			nan.setSelected(false);
		}
		phone.setText(phone_string);
		email.setText(email_string);
		address.setText(address_string);
	
		//由于datePicker插件不能直接设定日期，因此对于日期的数据回显还是有点小问题
		
		// 加载部门信息
		department.removeAllItems();
		List<Department> dept_list = null;
		try {
			dept_list = departmentService.findAllDepartment();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i=0;i<dept_list.size();i++) {
			int sign = 0;
			String did = dept_list.get(i).getDepartment_id();
			String dname = dept_list.get(i).getDepartment_name();
			Item item = new Item(did, dname);
			department.addItem(item);
			if(did.equals(dept_id_string)){
				sign = i;
				department.setSelectedIndex(sign);
			}
		}
		
		// 加载职位信息
		job.removeAllItems();
		List<Job> list_job = null;
		try {
			list_job = jobService.getJobByDeptmentId(dept_id_string);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		for (int i=0;i<list_job.size();i++) {
			int sign = 0;
			String jid = list_job.get(i).getJob_id();
			String jname = list_job.get(i).getJob_name();
			Item item = new Item(jid, jname);
			job.addItem(item);
			if(jid.equals(job_id_string)){
				sign = i;
				job.setSelectedIndex(sign);
			}
		}

	}
}