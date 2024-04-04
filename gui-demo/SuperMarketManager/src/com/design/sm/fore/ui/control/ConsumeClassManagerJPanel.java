package com.design.sm.fore.ui.control;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.design.sm.fore.ui.function.UpdateConsumeClassJFrame;
import com.design.sm.model.Accounts;
import com.design.sm.service.ConsumeClassService;
import com.design.sm.service.impl.ConsumeClassServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.Tools;

public class ConsumeClassManagerJPanel implements MouseListener {

	// 定义全局组件
	JPanel backgroundPanel, topPanel, toolPanel, tablePanel;
	// 借助工具类完成表格数据的封装
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// 定义用到的标签
	JLabel tool_add, tool_modify, tool_delete;
	// 定义相应的service
	private ConsumeClassService consumeClassService = new ConsumeClassServiceImpl();

	private Accounts user;

	/**
	 * 通过构造方法完成初始化
	 */
	public ConsumeClassManagerJPanel(Accounts user) {
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

		// 添加增删改工具
		Icon icon_add = new ImageIcon("icons/toolImage/add.png");
		tool_add = new JLabel(icon_add);
		tool_add.setToolTipText("新增消费等级");// 设置鼠标移动时的显示内容
		tool_add.addMouseListener(this);// 添加鼠标监听

		Icon icon_modify = new ImageIcon("icons/toolImage/modify.png");
		tool_modify = new JLabel(icon_modify);
		tool_modify.setToolTipText("修改消费等级");// 设置鼠标移动时的显示内容
		tool_modify.addMouseListener(this);// 添加鼠标监听

		Icon icon_delete = new ImageIcon("icons/toolImage/delete.png");
		tool_delete = new JLabel(icon_delete);
		tool_delete.setToolTipText("删除消费等级");// 设置鼠标移动时的显示内容
		tool_delete.addMouseListener(this);// 添加鼠标监听

		// 将初始化完成的工具加载到工具条面板中
		toolPanel.add(tool_add);
		toolPanel.add(tool_modify);
		toolPanel.add(tool_delete);
		// 最终将工具条面板加载到顶部菜单条的最西面
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * 初始化显示的表格数据
	 */
	private void initTablePanel() {
		// 输入的数据进行查找
		String[] params = { "等级id", "等级名称", "等级优惠", "等级折扣" };
		Vector<Vector> vec = new Vector<>();
		try {
			vec = consumeClassService.pack(consumeClassService
					.findAllConsumeClassList());
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
		if (e.getSource() == tool_add) {
			// 新增消费等级信息
			/**
			 * 由于此处设计了消费等级的主键id类型为number(1),因此在设计的时候
			 * 便不测试添加方法，默认为是五个消费等级，可通过相应的情景进行修改操作
			 */
		} else if (e.getSource() == tool_modify) {
			// 获取当前选中要修改的数据
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要修改的消费等级记录");
			} else {
				// 修改消费等级信息
				 new UpdateConsumeClassJFrame(this, table, row);
			}
		} else if (e.getSource() == tool_delete) {
			// 删除当前选中的数据
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要删除的消费等级信息");
			} else {
				// 获取当前选中分类的id
				String id = table.getValueAt(row, 0).toString();
				int result = JOptionPane.showConfirmDialog(null,
						"确认删除这条消费等级信息？");
				if (result == 0) {
					// 确认删除，则执行删除
					try {
						consumeClassService.deleteConsumeClass(id);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// 显示删除成功信息面板，并刷新数据面板
					JOptionPane.showMessageDialog(null, "消费等级信息删除修改成功！");
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

}
