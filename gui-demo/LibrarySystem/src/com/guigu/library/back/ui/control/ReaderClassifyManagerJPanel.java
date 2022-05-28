package com.guigu.library.back.ui.control;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;

import com.guigu.library.back.ui.function.UpdateReaderClassifyJFrame;
import com.guigu.library.model.Users;
import com.guigu.library.service.ReaderClassifyService;
import com.guigu.library.service.impl.ReaderClassifyServiceImpl;
import com.guigu.library.utils.BaseTableModule;
import com.guigu.library.utils.Tools;

public class ReaderClassifyManagerJPanel implements MouseListener {

	// 定义全局组件
	JPanel backgroundPanel, topPanel, toolPanel, tablePanel;
	// 借助工具类完成表格数据的封装
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// 定义用到的标签
	JLabel tool_modify;

	// 定义相应的service
	ReaderClassifyService readerClassifyService = new ReaderClassifyServiceImpl();

	Users user;

	/**
	 * 通过构造方法完成初始化
	 */
	public ReaderClassifyManagerJPanel(Users user) {
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
		// 将顶部菜单栏加载到背景面板中
		backgroundPanel.add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * 初始化工具条面板
	 */
	private void initToolPanel() {
		toolPanel = new JPanel();

		Icon icon_modify = new ImageIcon("icons/toolImage/modify.png");
		tool_modify = new JLabel(icon_modify);
		tool_modify.setToolTipText("修改读者分类");// 设置鼠标移动时的显示内容
		tool_modify.addMouseListener(this);// 添加鼠标监听

		// 将初始化完成的工具加载到工具条面板中
		toolPanel.add(tool_modify);
		// 最终将工具条面板加载到顶部菜单条的最西面
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * 初始化显示的表格数据
	 */
	private void initTablePanel() {

		// 输入的数据进行查找
		String[] params = { "分类编号", "分类名称", "最大借阅数", "借阅期限" };
		Vector<Vector> vec = new Vector<>();

		// 封装查找到的数据
		try {
			vec = readerClassifyService.pack(readerClassifyService
					.findAllReaderClassify());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 将查询到的数据封装到BbaseTableModule中
		baseTableModule = new BaseTableModule(params, vec);
		table = new JTable(baseTableModule);

		// table表格添加鼠标监听事件
		table.addMouseListener(this);

		// 利用提供的Tools类美化表格
		Tools.setTableStyle(table);

		// 通过设置行的大小，隐藏某一列内容（只显示想要显示的数据）
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) table
				.getColumnModel();
		// dcm.getColumn(2).setMinWidth(0);
		// dcm.getColumn(2).setMaxWidth(0);

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
		if (e.getSource() == tool_modify) {
			// 获取当前选中要修改的数据
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要修改的读者分类记录");
			} else {
				// 修改读者分类信息
				new UpdateReaderClassifyJFrame(this, table, row);
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