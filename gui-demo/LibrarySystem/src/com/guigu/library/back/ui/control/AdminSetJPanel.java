package com.guigu.library.back.ui.control;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import com.guigu.library.back.ui.function.AdminSettingJFrame;
import com.guigu.library.model.Reader;
import com.guigu.library.model.Users;
import com.guigu.library.service.ReaderService;
import com.guigu.library.service.impl.ReaderServiceImpl;
import com.guigu.library.utils.BaseTableModule;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;
import com.guigu.library.utils.Tools;

public class AdminSetJPanel implements MouseListener, FocusListener {

	// 定义全局组件
	JPanel backgroundPanel, topPanel, toolPanel, searchPanel, tablePanel;
	// 借助工具类完成表格数据的封装
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// 定义用到的标签
	JLabel label_search, tool_setting;
	JComboBox field;// 检索字段
	JTextField keyword;

	// 定义相应的service
	ReaderService readerService = new ReaderServiceImpl();

	Users user;

	/**
	 * 通过构造方法完成初始化
	 */
	public AdminSetJPanel(Users user) {
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
		backgroundPanel.validate();
	}

	/**
	 * 初始化工具条面板
	 */
	private void initToolPanel() {
		toolPanel = new JPanel();

		JPanel jp1 = new JPanel();
		Icon icon_setting = new ImageIcon("icons/toolImage/setting.png");
		tool_setting = new JLabel(icon_setting);
		tool_setting.setToolTipText("管理权限设置");// 设置鼠标移动时的显示内容
		tool_setting.addMouseListener(this);// 添加鼠标监听

		// 将初始化完成的工具加载到工具条面板中
		jp1.add(tool_setting);
		toolPanel.add(jp1);
		// 最终将工具条面板加载到顶部菜单条的最西面
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * 初始化查找面板
	 */
	private void initSearchPanel() {
		searchPanel = new JPanel();
		JPanel jp = new JPanel();
		/**
		 * 设置检索字段的下拉框属性 0.所有内容 1.条形码 2.学工编号 3.身份证号 4.读者账号 5.借阅证编号
		 */
		field = new JComboBox();
		field.addItem("所有内容");
		field.addItem("条形码");
		field.addItem("学工编号");
		field.addItem("身份证号");
		field.addItem("读者账号");
		field.addItem("借阅证编号");

		keyword = new JTextField(15);
		keyword.setFont(MyFont.TipFont);
		keyword.setForeground(MyColor.TipColor);
		keyword.setText("关键字查找");
		keyword.addFocusListener(this);

		Icon icon_search = new ImageIcon("icons/toolImage/search.png");
		label_search = new JLabel(icon_search);
		label_search.setToolTipText("查找");
		label_search.addMouseListener(this);

		// 将相关组件加载到指定的面板中
		jp.add(field);
		jp.add(keyword);
		jp.add(label_search);

		searchPanel.add(jp);

		// 将布局好的组件加载到菜单栏面板的最东面
		topPanel.add(searchPanel, BorderLayout.EAST);
	}

	/**
	 * 初始化显示的表格数据
	 */
	private void initTablePanel() {
		// 输入的数据进行查找
		String[] params = { "读者id", "条形码", "学工编号", "姓名", "性别", "出生日期", "身份证号",
				"联系方式", "电子邮箱", "备注", "读者账号id", "读者账号", "读者分类编号", "所属分类",
				"借阅证id", "借阅证" };
		Vector<Vector> vec = new Vector<>();
		// 默认显示所有的内容
		try {
			vec = readerService.pack(this.getAdminByField(readerService
					.findReaderUnion(0)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 条件检索： 0.所有内容 1.条形码 2.学工编号 3.身份证号 4.读者账号 5.借阅证编号
		String keyword_string = keyword.getText();
		if (!keyword_string.equals("关键字查找")) {
			// 根据下拉框选项以及文本框中对应的内容筛选数据信息
			int field_int = field.getSelectedIndex();
			// 根据条件筛选数据信息
			try {
				vec = readerService.pack(this.getAdminByField(readerService
						.findReaderUnion(field_int, keyword_string)));
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
		// 隐藏4 5 9 10 12 14
		dcm.getColumn(4).setMinWidth(0);
		dcm.getColumn(4).setMaxWidth(0);
		dcm.getColumn(5).setMinWidth(0);
		dcm.getColumn(5).setMaxWidth(0);
		dcm.getColumn(9).setMinWidth(0);
		dcm.getColumn(9).setMaxWidth(0);
		dcm.getColumn(10).setMinWidth(0);
		dcm.getColumn(10).setMaxWidth(0);
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
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == label_search) {
			// 如果点击了查找选项则进行筛选
			this.refreshTablePanel();
		} else if (e.getSource() == tool_setting) {
			int row = table.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "请选择要进行权限设置的管理员信息！");
			} else {
				// 进行管理员权限设置(只有超级管理员才有资格进行权限设置)
				String classify = table.getValueAt(row, 12).toString();
				if (classify.equals("4")) {
					new AdminSettingJFrame(this, table, row);
				} else {
					JOptionPane.showMessageDialog(null, "抱歉，您当前没有权限进行管理员设置！");
				}
			}
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

	// 获取焦点事件
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("关键字查找")) {
				keyword.setFont(MyFont.JTextFieldFont);
				keyword.setForeground(MyColor.JTextFieldColor);
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
				keyword.setText("关键字查找");
			}
		}
	}

	/**
	 * 刷新表格数据面板
	 */
	public void refreshTablePanel() {
		// 移除当前数据面板中的所有数据
		backgroundPanel.remove(tablePanel);
		initTablePanel();
		backgroundPanel.validate();// 验证
	}

	/**
	 * 通过当前根据条件检索出的读者信息，进行二次筛选获取身份为管理员或者超级管理员的读者信息
	 */
	public List<Reader> getAdminByField(List<Reader> readers) {
		List<Reader> r = new ArrayList<Reader>();
		for (int i = 0; i < readers.size(); i++) {
			int classify = readers.get(i).getClassify_num();
			if (classify == 3 || classify == 4) {
				// 读者分类编号为3或4表示图书管理员或者是超级管理员
				r.add(readers.get(i));
			}
		}
		return r;
	}

}
