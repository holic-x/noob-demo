package com.guigu.library.back.ui.control;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
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

import com.guigu.library.back.ui.function.AccountManagerJFrame;
import com.guigu.library.back.ui.function.AddReaderJFrame;
import com.guigu.library.back.ui.function.LibraryCardManagerJFrame;
import com.guigu.library.back.ui.function.ShowReaderDetailJFrame;
import com.guigu.library.back.ui.function.UpdateReaderJFrame;
import com.guigu.library.export.ui.ReaderExportCheckBoxJFrame;
import com.guigu.library.model.Users;
import com.guigu.library.service.ReaderService;
import com.guigu.library.service.impl.ReaderServiceImpl;
import com.guigu.library.utils.BaseTableModule;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;
import com.guigu.library.utils.Tools;

public class ReaderArchivesManagerJPanel implements MouseListener,
		FocusListener {

	// 定义全局组件
	JPanel backgroundPanel, topPanel, toolPanel, searchPanel, tablePanel,
			pagePanel;
	// 借助工具类完成表格数据的封装
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// 定义用到的标签
	JLabel label_search, tool_add, tool_modify, tool_delete, tool_detail,
			tool_import, tool_export, tool_library_card, tool_account;
	JComboBox field;// 检索字段
	JTextField keyword;

	// 定义相应的service
	ReaderService readerService = new ReaderServiceImpl();

	Users user;

	/**
	 * 通过构造方法完成初始化
	 */
	public ReaderArchivesManagerJPanel(Users user) {
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
		toolPanel = new JPanel(new BorderLayout());

		JPanel jp1 = new JPanel();
		// 添加增删改工具
		Icon icon_add = new ImageIcon("icons/toolImage/add.png");
		tool_add = new JLabel(icon_add);
		tool_add.setToolTipText("录入读者信息");// 设置鼠标移动时的显示内容
		tool_add.addMouseListener(this);// 添加鼠标监听

		Icon icon_modify = new ImageIcon("icons/toolImage/modify.png");
		tool_modify = new JLabel(icon_modify);
		tool_modify.setToolTipText("修改读者信息");// 设置鼠标移动时的显示内容
		tool_modify.addMouseListener(this);// 添加鼠标监听

		Icon icon_delete = new ImageIcon("icons/toolImage/delete.png");
		tool_delete = new JLabel(icon_delete);
		tool_delete.setToolTipText("删除读者信息");// 设置鼠标移动时的显示内容
		tool_delete.addMouseListener(this);// 添加鼠标监听

		JPanel jp2 = new JPanel();
		Icon icon_detail = new ImageIcon("icons/toolImage/detail.png");
		tool_detail = new JLabel(icon_detail);
		tool_detail.setToolTipText("查看读者信息");// 设置鼠标移动时的显示内容
		tool_detail.addMouseListener(this);// 添加鼠标监听

		Icon icon_import = new ImageIcon("icons/toolImage/import.png");
		tool_import = new JLabel(icon_import);
		tool_import.setToolTipText("导入读者信息");// 设置鼠标移动时的显示内容
		tool_import.addMouseListener(this);// 添加鼠标监听

		Icon icon_export = new ImageIcon("icons/toolImage/export.png");
		tool_export = new JLabel(icon_export);
		tool_export.setToolTipText("导出读者信息");// 设置鼠标移动时的显示内容
		tool_export.addMouseListener(this);// 添加鼠标监听

		Icon icon_library_card = new ImageIcon(
				"icons/toolImage/library_card.png");
		tool_library_card = new JLabel(icon_library_card);
		tool_library_card.setToolTipText("借阅证信息管理");// 设置鼠标移动时的显示内容
		tool_library_card.addMouseListener(this);// 添加鼠标监听

		Icon icon_account = new ImageIcon("icons/toolImage/account.png");
		tool_account = new JLabel(icon_account);
		tool_account.setToolTipText("账号信息管理");// 设置鼠标移动时的显示内容
		tool_account.addMouseListener(this);// 添加鼠标监听

		// 将初始化完成的工具加载到工具条面板中
		jp1.add(tool_add);
		jp1.add(tool_modify);
		jp1.add(tool_delete);
		jp1.add(tool_detail);
		jp2.add(tool_import);
		jp2.add(tool_export);
		jp2.add(tool_library_card);
		jp2.add(tool_account);
		toolPanel.add(jp1, BorderLayout.NORTH);
		toolPanel.add(jp2, BorderLayout.SOUTH);
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
			vec = readerService.pack(readerService.findReaderUnion(0));
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
				vec = readerService.pack(readerService.findReaderUnion(
						field_int, keyword_string));
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
		} else if (e.getSource() == tool_add) {
			// 录入读者信息
			new AddReaderJFrame(this);
		} else if (e.getSource() == tool_modify) {
			// 获取当前选中要修改的数据
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要修改的读者信息");
			} else {
				// 修改读者信息
				new UpdateReaderJFrame(this, table, row);
			}
		} else if (e.getSource() == tool_delete) {
			// 删除当前选中的数据
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要删除的读者信息");
			} else {
				// 获取当前选中读者id
				String reader_id = (String) table.getValueAt(row, 0);
				int result = JOptionPane.showConfirmDialog(null, "确认删除这条读者信息？");
				if (result == 0) {
					// 确认删除，则执行删除
					try {
						readerService.deleteReader(reader_id);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// 显示删除成功信息面板，并刷新数据面板
					JOptionPane.showMessageDialog(null, "读者信息删除成功！");
					this.refreshTablePanel();
				}
			}
		} else if (e.getSource() == tool_detail) {
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要查阅的读者信息");
			} else {
				// 查看读者详细信息
				new ShowReaderDetailJFrame(this, table, row);
			}
		} else if (e.getSource() == tool_import) {
			// 导入读者数据
			// 待定
		} else if (e.getSource() == tool_export) {
			// 导读者数据（根据选中的行、选中的列导出数据信息）
			// 获取当前选中的数据
			int[] selectedRows = table.getSelectedRows();
			new ReaderExportCheckBoxJFrame(this, table, selectedRows);
		} else if(e.getSource()==tool_library_card){
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要进行借阅证管理的读者信息");
			} else {
				// 借阅证信息管理
				new LibraryCardManagerJFrame(this, table, row);
			}
		} else if(e.getSource()==tool_account){
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要进行账号管理的读者信息");
			} else {
				// 账号信息管理
				new AccountManagerJFrame(this, table, row);
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

}
