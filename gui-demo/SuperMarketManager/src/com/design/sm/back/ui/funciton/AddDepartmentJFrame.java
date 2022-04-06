package com.design.sm.back.ui.funciton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.back.ui.control.DepartmentManagerJPanel;
import com.design.sm.model.Department;
import com.design.sm.service.DepartmentService;
import com.design.sm.service.impl.DepartmentServiceImpl;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.RandomGeneration;

public class AddDepartmentJFrame extends JFrame implements MouseListener,
		FocusListener {
	// 定义全局组件
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_name, label_manager, label_descr;
	JTextField name;
	JTextArea descr;
	JComboBox manager;
	JButton save_button, reset_button;

	DepartmentService departmentService = new DepartmentServiceImpl();

	// 定义父对象
	DepartmentManagerJPanel parentPanel;

	// 通过构造方法初始化数据
	public AddDepartmentJFrame(DepartmentManagerJPanel parentPanel) {
		this.parentPanel = parentPanel;
		// 初始化背景
		initBackgroundPanel();
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("添加部门信息");
		this.setSize(420, 370);
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
		JLabel title = new JLabel("添加部门信息");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * 初始化内容面板
	 */
	private void initContentPanel() {
		/**
		 * 添加部门的信息： 部门id：随机生成的10int的数据 部门名称、部门主管、部门描述 之后再存入相关的数据
		 */
		contentPanel = new JPanel();
		JPanel jp1 = new JPanel();
		label_name = new JLabel("部门名称");
		name = new JTextField(15);
		name.setFont(MyFont.TipFont);
		name.setForeground(MyColor.TipColor);
		name.setText("必填项");
		name.addFocusListener(this);
		jp1.add(label_name);
		jp1.add(name);

		JPanel jp2 = new JPanel();
		label_manager = new JLabel("部门主管");
		manager = new JComboBox();
		manager.setPreferredSize(new Dimension(175, 30));
		// 初次新建是没有员工的，因此选择为空
		manager.addItem("空");
		jp2.add(label_manager);
		jp2.add(manager);

		JPanel jp3 = new JPanel();
		label_descr = new JLabel("部门描述");
		descr = new JTextArea(6, 15);
		descr.setFont(MyFont.TipFont);
		descr.setForeground(MyColor.TipColor);
		descr.setText("这个部门很懒，什么都没有留下！");
		descr.setLineWrap(true);// 自动换行
		descr.addFocusListener(this);
		jp3.add(label_descr);
		jp3.add(descr);

		Box ver = Box.createVerticalBox();
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp3);
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
			String descr_string = descr.getText();
			if (name_string.equals("必填项")) {
				JOptionPane.showMessageDialog(null, "部门名称不能为空");
			} else {
				// 获取主管下拉框选项（默认为空，直接获取即可）
				Object manager_select = manager.getSelectedItem();
				// 创建Department对象，将数据保存到数据库中
				// 部门id是随机生成的10位int类型的字符拼接的数据
				String id = RandomGeneration.getRandom10numSeq();
				Department dept = new Department(id, name_string,
						String.valueOf(manager_select), descr_string);
				int choose = JOptionPane.showConfirmDialog(null, "确认添加部门信息？");
				if (choose == 0) {
					try {
						departmentService.addDepartment(dept);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// 完成数据的保存之后输出提示信息，并将子页面隐藏，刷新主面板的数据
					JOptionPane.showMessageDialog(null, "部门信息保存成功");
					this.setVisible(false);
					parentPanel.refreshTablePanel();
				} else {
					this.setVisible(false);
				}
			}
		} else if (e.getSource() == reset_button) {
			// 清空所有的数据
			name.setFont(MyFont.TipFont);
			name.setForeground(MyColor.TipColor);
			name.setText("必填项");
			descr.setFont(MyFont.TipFont);
			descr.setForeground(MyColor.TipColor);
			descr.setText("这个部门很懒，什么也没有留下！");

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

	// 聚焦事件
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == name) {
			if (name.getText().equals("必填项")) {
				name.setForeground(MyColor.JTextFieldColor);
				name.setFont(MyFont.JTextFieldFont);
				name.setText("");
			}
		} else if (e.getSource() == descr) {
			if (descr.getText().equals("这个部门很懒，什么都没有留下！")) {
				descr.setForeground(MyColor.JTextFieldColor);
				descr.setFont(MyFont.JTextFieldFont);
				descr.setText("");
			}
		}
	}

	// 失去焦点事件
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == name) {
			if (name.getText().equals("")) {
				name.setFont(MyFont.TipFont);
				name.setForeground(MyColor.TipColor);
				name.setText("必填项");
			}
		} else if (e.getSource() == descr) {
			if (descr.getText().equals("")) {
				descr.setFont(MyFont.TipFont);
				descr.setForeground(MyColor.TipColor);
				descr.setText("这个部门很懒，什么都没有留下！");
			}
		}
	}
}