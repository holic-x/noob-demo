package com.guigu.library.back.ui.control;

import java.awt.BorderLayout;
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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableColumnModel;

import com.guigu.library.back.ui.function.AddBookClassifyJFrame;
import com.guigu.library.back.ui.function.UpdateBookClassifyJFrame;
import com.guigu.library.model.BookClassify;
import com.guigu.library.model.Users;
import com.guigu.library.service.BookClassifyService;
import com.guigu.library.service.impl.BookClassifyServiceImpl;
import com.guigu.library.utils.BaseTableModule;
import com.guigu.library.utils.DataValidation;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;
import com.guigu.library.utils.Tools;

public class BooksClassifyManagerJPanel implements MouseListener, FocusListener {

	// 定义全局组件
	JPanel backgroundPanel, topPanel, toolPanel, searchPanel, tablePanel,
			downPanel;
	// 借助工具类完成表格数据的封装
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// 定义用到的标签
	JLabel label_search,label_keyword, tool_add, tool_modify, tool_delete, tool_lastLevel,
			tool_nextLevel;
	JTextField keyword;
	// 定义分级查找所要用到的组件
	JLabel label_level;
	JTextField level;

	// 定义相应的service
	BookClassifyService bookClassifyService = new BookClassifyServiceImpl();

	Users user;

	/**
	 * 通过构造方法完成初始化
	 */
	public BooksClassifyManagerJPanel(Users user) {
		backgroundPanel = new JPanel(new BorderLayout());
		this.user = user;
		// 初始化布局
		initTopPanel();// 初始化顶部菜单条
		initDownPanel();// 初始化分级查找的工具条
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
		tool_add.setToolTipText("新建图书分类");// 设置鼠标移动时的显示内容
		tool_add.addMouseListener(this);// 添加鼠标监听

		Icon icon_modify = new ImageIcon("icons/toolImage/modify.png");
		tool_modify = new JLabel(icon_modify);
		tool_modify.setToolTipText("修改图书分类");// 设置鼠标移动时的显示内容
		tool_modify.addMouseListener(this);// 添加鼠标监听

		Icon icon_delete = new ImageIcon("icons/toolImage/delete.png");
		tool_delete = new JLabel(icon_delete);
		tool_delete.setToolTipText("删除图书分类");// 设置鼠标移动时的显示内容
		tool_delete.addMouseListener(this);// 添加鼠标监听

		// 将初始化完成的工具加载到工具条面板中
		toolPanel.add(tool_add);
		toolPanel.add(tool_modify);
		toolPanel.add(tool_delete);
		// 最终将工具条面板加载到顶部菜单条的最西面
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * 初始化查找面板
	 */
	private void initSearchPanel() {

		searchPanel = new JPanel();

		label_keyword = new JLabel("关键字");
		label_keyword.setFont(MyFont.JLabelFont);

		keyword = new JTextField(15);
		keyword.setFont(MyFont.TipFont);
		keyword.setForeground(MyColor.TipColor);
		keyword.setText("请输入图书分类编号或名称");
		keyword.addFocusListener(this);

		Icon icon_search = new ImageIcon("icons/toolImage/search.png");
		label_search = new JLabel(icon_search);
		label_search.setToolTipText("查找");
		label_search.addMouseListener(this);

		// 将相关组件加载到指定的面板中
		searchPanel.add(label_keyword);
		searchPanel.add(keyword);
		searchPanel.add(label_search);
		// 将布局好的组件加载到菜单栏面板的最东面
		topPanel.add(searchPanel, BorderLayout.EAST);
	}

	/**
	 * 初始化显示的表格数据
	 */
	private void initTablePanel() {

		// 输入的数据进行查找
		String[] params = { "分类编号", "分类名称", "当前等级", "父级编号" };
		Vector<Vector> vec = new Vector<>();

		// 组合条件查找
		if (!level.getText().equals("请输入≥0的整数") && !level.getText().equals("")
				&& !keyword.getText().equals("请输入图书分类编号或名称")) {
			try {
				List<BookClassify> listByLevel = null;
				List<BookClassify> listByKeyWord = null;
				listByLevel = bookClassifyService.findBookClassifyUnion(
						"level", level.getText());
				// 拼接查找的字符串
				String text = "%" + keyword.getText() + "%";
				listByKeyWord = bookClassifyService.findBookClassifyUnion(
						"keyword", text);
				List<BookClassify> findList = this.screenData(listByLevel,
						listByKeyWord);
				vec = bookClassifyService.pack(findList);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (!keyword.getText().equals("请输入图书分类编号或名称")) {
			try {
				List<BookClassify> listByKeyWord = null;
				// 拼接查找的字符串
				String text = "%" + keyword.getText() + "%";
				listByKeyWord = bookClassifyService.findBookClassifyUnion(
						"keyword", text);
				vec = bookClassifyService.pack(listByKeyWord);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (!level.getText().equals("请输入≥0的整数")
				&& !level.getText().equals("")) {
			try {
				List<BookClassify> listByLevel = null;
				listByLevel = bookClassifyService.findBookClassifyUnion(
						"level", level.getText());
				vec = bookClassifyService.pack(listByLevel);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				vec = bookClassifyService.pack(bookClassifyService
						.findBookClassifyUnion("all"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
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

	/**
	 * 定义分级查找的面板
	 */
	private void initDownPanel() {
		downPanel = new JPanel();
		Icon icon_lastLevel = new ImageIcon("icons/toolImage/lastLevel.png");
		tool_lastLevel = new JLabel(icon_lastLevel);
		tool_lastLevel.setToolTipText("返回上一级");// 设置鼠标移动时的显示内容
		tool_lastLevel.addMouseListener(this);// 添加鼠标监听

		label_level = new JLabel("分级查找");
		label_level.setFont(MyFont.JLabelFont);

		level = new JTextField(10);
		level.setFont(MyFont.TipFont);
		level.setForeground(MyColor.TipColor);
		level.setText("请输入≥0的整数");
		level.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				backgroundPanel.remove(tablePanel);
				initTablePanel();
				backgroundPanel.validate();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// 刷新面板数据
				String level_string = level.getText();
				// 判断输入是否为纯数字
				if (DataValidation.isSignlessInteger(level_string)) {
					int level_int = Integer.valueOf(level_string);
					if (level_int > 10) {
						JOptionPane.showMessageDialog(null, "输入数值不能超过10！");
					} else {
						backgroundPanel.remove(tablePanel);
						initTablePanel();
						backgroundPanel.validate();
					}
				} else if (!level.getText().equals("请输入≥0的整数")) {
					JOptionPane.showMessageDialog(null, "请输入大于等于0的整数！");
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});
		level.addFocusListener(this);

		Icon icon_nextLevel = new ImageIcon("icons/toolImage/nextLevel.png");
		tool_nextLevel = new JLabel(icon_nextLevel);
		tool_nextLevel.setToolTipText("下一级");// 设置鼠标移动时的显示内容
		tool_nextLevel.addMouseListener(this);// 添加鼠标监听

		downPanel.add(tool_lastLevel);
		downPanel.add(label_level);
		downPanel.add(level);
		downPanel.add(tool_nextLevel);
		backgroundPanel.add(downPanel, BorderLayout.SOUTH);
		backgroundPanel.validate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == label_search) {
			// 如果点击了查找选项则进行筛选
			tablePanel.removeAll();// 移除数据面板中的所有数据
			initTablePanel();// 重新初始化面板
			backgroundPanel.validate();
		}
		if (e.getSource() == tool_add) {
			// 新增图书分类信息
			int row = table.getSelectedRow();
			if(row<0){
				JOptionPane.showMessageDialog(null, "请选择相应的父级目录");
			}else{
				new AddBookClassifyJFrame(this,table,row);
			}
		} else if (e.getSource() == tool_modify) {
			// 获取当前选中要修改的数据
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要修改的图书分类记录");
			} else {
				// 修改图书分类信息
				 new UpdateBookClassifyJFrame(this, table, row);
			}
		} else if (e.getSource() == tool_delete) {
			// 删除当前选中的数据
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要删除的图书分类信息");
			} else {
				// 获取当前选中分类编号
				String classify_num = (String) table.getValueAt(row, 0);
				int result = JOptionPane.showConfirmDialog(null,
						"确认删除这条图书分类信息？");
				if (result == 0) {
					// 确认删除，则执行删除
					try {
						bookClassifyService.deleteBookClassify(classify_num);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// 显示删除成功信息面板，并刷新数据面板
					JOptionPane.showMessageDialog(null, "图书分类信息删除成功！");
					refreshTablePanel();
				}
			}
		} else if (e.getSource() == tool_lastLevel) {
			String level_string = level.getText();
			if (!level_string.equals("") && !level_string.equals("请输入≥0的整数")) {
				int level_int = Integer.valueOf(level_string);
				if (level_int > 0) {
					level_int -= 1;
					level.setText(level_int + "");
				}
				backgroundPanel.remove(tablePanel);
				initTablePanel();
				backgroundPanel.validate();
			} else {
				level.setText("0");
				backgroundPanel.remove(tablePanel);
				initTablePanel();
				backgroundPanel.validate();
			}
		} else if (e.getSource() == tool_nextLevel) {
			// 判断当前是否有选中数据，如果选中则显示当前选中数据的下一级目录，如果没有选中数据则默认是下一级的所有内容
			// 考虑文本框监控问题，数据回退还有点小毛病
			int row = table.getSelectedRow();
			if (row < 0) {
				String level_string = level.getText();
				if (!level_string.equals("")
						&& !level_string.equals("请输入≥0的整数")) {
					int level_int = Integer.valueOf(level_string);
					if (level_int < 10) {
						level_int += 1;
						level.setText(level_int + "");
					}
					backgroundPanel.remove(tablePanel);
					initTablePanel();
					backgroundPanel.validate();
				} else {
					level.setText("1");
					backgroundPanel.remove(tablePanel);
					initTablePanel();
					backgroundPanel.validate();
				}
			} else {
				// 显示下一级目录
				backgroundPanel.remove(tablePanel);
				// 输入的数据进行查找
				String[] params = { "分类编号", "分类名称", "当前等级", "父级编号" };
				Vector<Vector> vec = new Vector<>();
				try {
					String parent_num = table.getValueAt(row, 0).toString();
					List<BookClassify> list = bookClassifyService
							.findBookClassifyUnion("parent_classify_num",
									parent_num);
					vec = bookClassifyService.pack(list);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				// 将查询到的数据封装到BbaseTableModule中
				baseTableModule = new BaseTableModule(params, vec);
				table = new JTable(baseTableModule);

				// table表格添加鼠标监听事件
				table.addMouseListener(this);

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

	// 获取焦点事件
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("请输入图书分类编号或名称")) {
				keyword.setFont(MyFont.JTextFieldFont);
				keyword.setForeground(MyColor.JTextFieldColor);
				keyword.setText("");
			}
		} else if (e.getSource() == level) {
			if (level.getText().equals("请输入≥0的整数")) {
				level.setFont(MyFont.JTextFieldFont);
				level.setForeground(MyColor.JTextFieldColor);
				level.setText("");
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
				keyword.setText("请输入图书分类编号或名称");
			}
		} else if (e.getSource() == level) {
			if (level.getText().equals("")) {
				level.setFont(MyFont.TipFont);
				level.setForeground(MyColor.TipColor);
				level.setText("请输入≥0的整数");
			}
		}
	}

	/**
	 * 结合level和编号、名称综合筛选分类信息
	 */
	public List<BookClassify> screenData(List<BookClassify> listByLevel,
			List<BookClassify> listByKeyWord) {
		List<BookClassify> findList = new ArrayList<BookClassify>();
		for (int i = 0; i < listByLevel.size(); i++) {
			for (int j = 0; j < listByKeyWord.size(); j++) {
				// 如果编号相同默认是同一个对象
				if (listByLevel.get(i).getClassify_num()
						.equals(listByKeyWord.get(j).getClassify_num())) {
					findList.add(listByLevel.get(i));
				}
			}
		}
		return findList;
	}
}