package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.design.sm.fore.ui.control.ProductManagerJPanel;
import com.design.sm.model.Accounts;
import com.design.sm.model.Category;
import com.design.sm.service.AccountsService;
import com.design.sm.service.CategoryService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.CategoryServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.RandomGeneration;
import com.design.sm.utils.Tools;

public class CategoryManagerJFrame extends JFrame implements MouseListener,FocusListener {

	// 定义全局组件
	JPanel backgroundPanel, topPanel, toolPanel, searchPanel, tablePanel;
	// 借助工具类完成表格数据的封装
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// 定义用到的标签
	JLabel label_keyword, tool_add, tool_modify, tool_delete;
	JTextField keyword;
	JButton search_button;
	// 定义相应的service
	CategoryService unitsService = new CategoryServiceImpl();
	ProductManagerJPanel parentPanel;

	/**
	 * 通过构造方法完成初始化
	 */
	public CategoryManagerJFrame(ProductManagerJPanel parentPanel) {
		this.parentPanel = parentPanel;
		backgroundPanel = new JPanel(new BorderLayout());
		// 初始化布局
		initTopPanel();// 初始化顶部菜单条
		initTablePanel();// 初始化显示的表格数据
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("添加分类信息");
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
		tool_add.setToolTipText("新建分类");// 设置鼠标移动时的显示内容
		tool_add.addMouseListener(this);// 添加鼠标监听

		Icon icon_modify = new ImageIcon("icons/toolImage/modify.png");
		tool_modify = new JLabel(icon_modify);
		tool_modify.setToolTipText("修改分类");// 设置鼠标移动时的显示内容
		tool_modify.addMouseListener(this);// 添加鼠标监听

		Icon icon_delete = new ImageIcon("icons/toolImage/delete.png");
		tool_delete = new JLabel(icon_delete);
		tool_delete.setToolTipText("删除分类");// 设置鼠标移动时的显示内容
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

		label_keyword = new JLabel("商品分类");
		label_keyword.setFont(MyFont.JLabelFont);

		keyword = new JTextField(15);
		keyword.setFont(MyFont.TipFont);
		keyword.setForeground(MyColor.TipColor);
		keyword.setText("请输入分类关键字");
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
		String[] params = { "分类id", "分类名称", "分类标识" };
		Vector<Vector> vec = new Vector<>();
		if (!keyword.getText().equals("请输入分类关键字")) {
			try {
				// 拼接查找的字符串
				String text = "%" + keyword.getText() + "%";
				vec = unitsService.getCategoryByNameKeyword(text);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				vec = unitsService.findAllCategoryVector();
			} catch (SQLException e) {
				e.printStackTrace();
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
		if (e.getSource() == search_button) {
			// 如果点击了查找选项则进行筛选
			tablePanel.removeAll();// 移除数据面板中的所有数据
			initTablePanel();// 重新初始化面板
		}
		if (e.getSource() == tool_add) {
			String name = JOptionPane.showInputDialog(null, "请输入分类名称");
			// 下述语句用于处理空指针异常
			if (name == null) {
				name = "";
			}
			if (!name.equals("")) {
				int choose = JOptionPane.showConfirmDialog(null, "确定增加这条分类信息？");
				if (choose == 0) {
					// 随机生成10位的随机字符作为分类id
					String id = RandomGeneration.getRandom10charSeq();
					Category u = new Category(id, name, 0);
					try {
						unitsService.addCategory(u);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// 显示添加成功信息面板，并刷新数据面板
					JOptionPane.showMessageDialog(null, "分类信息添加成功！");
					this.refreshTablePanel();
				}
			}
		} else if (e.getSource() == tool_modify) {
			// 获取当前选中要修改的数据
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要修改的分类信息");
			} else {
				String name = (String) table.getValueAt(row, 1);
				String newName = JOptionPane.showInputDialog(null,
						"请输入修改后的分类名称", name);
				// 下述语句用于处理空指针异常
				if (newName == null) {
					newName = "";
				}
				if (!newName.equals("")) {
					int choose = JOptionPane.showConfirmDialog(null,
							"确定修改这条分类信息？");
					if (choose == 0) {
						// 从数据表中获取当前选中的分类信息的分类id
						String id = (String) table.getValueAt(row, 0);
						Category u = new Category(id, newName, 0);
						try {
							unitsService.updateCategory(u);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						// 显示修改成功信息面板，并刷新数据面板
						JOptionPane.showMessageDialog(null, "分类信息修改成功！");
						this.refreshTablePanel();
					}
				}
			}
		} else if (e.getSource() == tool_delete) {
			// 删除当前选中的数据
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要删除的分类信息");
			} else {
				// 获取当前选中分类的id
				String id = (String) table.getValueAt(row, 0);
				int result = JOptionPane.showConfirmDialog(null, "确认删除这条分类信息？");
				if (result == 0) {
					// 确认删除，则执行删除
					try {
						unitsService.deleteCategory(id);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// 显示删除成功信息面板，并刷新数据面板
					JOptionPane.showMessageDialog(null, "分类信息删除成功！");
					refreshTablePanel();
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
			if (keyword.getText().equals("请输入分类关键字")) {
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
				keyword.setText("请输入分类关键字");
			} else {
				keyword.setForeground(MyColor.JTextFieldColor);
				keyword.setFont(MyFont.JTextFieldFont);
			}
		}
	}

}
