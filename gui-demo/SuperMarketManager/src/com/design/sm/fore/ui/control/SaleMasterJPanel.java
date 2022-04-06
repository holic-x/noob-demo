package com.design.sm.fore.ui.control;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import com.design.sm.dao.impl.SaleOrderServiceImpl;
import com.design.sm.fore.ui.function.ShowSaleMasterDetail;
import com.design.sm.model.Accounts;
import com.design.sm.service.ProductService;
import com.design.sm.service.SaleMasterService;
import com.design.sm.service.SaleOrderService;
import com.design.sm.service.impl.ProductServiceImpl;
import com.design.sm.service.impl.SaleMasterServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.DataValidation;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.Tools;
import com.eltima.components.ui.DatePicker;

public class SaleMasterJPanel implements MouseListener {
	// 定义全局组件
	JPanel backgroundPanel, topPanel, toolPanel, tablePanel, searchPanel;
	// 借助工具类完成表格数据的封装
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// 定义用到的标签
	JLabel tool_delete, tool_detail;
	DatePicker start_time, end_time;
	JButton search;
	// 定义相应的service
	SaleMasterService saleMasterService = new SaleMasterServiceImpl();
	SaleOrderService saleOrderService = new SaleOrderServiceImpl();
	ProductService productService = new ProductServiceImpl();

	Accounts loginUser;

	/**
	 * 通过构造方法完成初始化
	 */
	public SaleMasterJPanel(Accounts loginUser) {
		this.loginUser = loginUser;
		backgroundPanel = new JPanel(new BorderLayout());
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
	}

	/**
	 * 初始化工具条面板
	 */
	private void initToolPanel() {
		toolPanel = new JPanel();

		Icon icon_delete = new ImageIcon("icons/toolImage/delete.png");
		tool_delete = new JLabel(icon_delete);
		tool_delete.setToolTipText("删除记录");// 设置鼠标移动时的显示内容
		tool_delete.addMouseListener(this);// 添加鼠标监听

		Icon icon_detail = new ImageIcon("icons/toolImage/detail.png");
		tool_detail = new JLabel(icon_detail);
		tool_detail.setToolTipText("订单明细");// 设置鼠标移动时的显示内容
		tool_detail.addMouseListener(this);// 添加鼠标监听

		// 将初始化完成的工具加载到工具条面板中
		toolPanel.add(tool_delete);
		toolPanel.add(tool_detail);
		// 最终将工具条面板加载到顶部菜单条的最西面
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * 初始化查找面板 设置查找方式： 根据订单生成的时间范围进行查找
	 */
	private void initSearchPanel() {
		searchPanel = new JPanel();
		Icon start_icon = new ImageIcon("icons/toolImage/start_time.png");
		JLabel label_start = new JLabel(start_icon);
		start_time = new DatePicker();
		start_time.setPreferredSize(new Dimension(150, 30));
		start_time.addMouseListener(this);
		Icon end_icon = new ImageIcon("icons/toolImage/start_time.png");
		JLabel label_end = new JLabel(end_icon);
		end_time = new DatePicker();
		end_time.setPreferredSize(new Dimension(150, 30));
		end_time.addMouseListener(this);

		search = new JButton("查找");
		search.addMouseListener(this);

		searchPanel.add(label_start);
		searchPanel.add(start_time);
		searchPanel.add(label_end);
		searchPanel.add(end_time);
		searchPanel.add(search);

		// 将布局好的组件加载到菜单栏面板的最东面
		topPanel.add(searchPanel, BorderLayout.EAST);
	}

	/**
	 * 初始化显示的表格数据
	 */
	private void initTablePanel() {

		// 输入的数据进行查找
		String[] params = { "订单主表id", "订单编号", "经手人id", "经手人", "顾客id", "顾客",
				"处理时间", "删除标识", "删除标识含义","状态标识", "订单状态" };
		Vector<Vector> vec = new Vector<>();
		// 判断数据输入的日期格式是否合法
		String start_time_string = start_time.getText();
		String end_time_string = end_time.getText();
		if(!(start_time_string.equals("")&&end_time_string.equals("")))
		try {
			vec = saleMasterService.pack(saleMasterService
					.findAllStockListByTimeUnionState(
							start_time_string, end_time_string, 0));
		} catch (SQLException e) {
			e.printStackTrace();
		}else{
			// 如果开始时间为空置为最小值
			if (start_time_string.equals("")) {
				start_time_string = "0000-00-00";
			}
			// 如果结束时间为空置为最大值
			if (end_time_string.equals("")) {
				end_time_string = "9999-99-99";
			}
			// 由于此处设置了最值，如果两个时间选择均为空则会相应设置最值，显示结果是所有的商品数据
			//进行查找
			try {
				vec = saleMasterService.pack(saleMasterService
						.findAllStockListByTimeUnionState(
								start_time_string, end_time_string, 0));
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
		// 隐藏 2 4 7 9 10
		dcm.getColumn(2).setMinWidth(0);
		dcm.getColumn(2).setMaxWidth(0);
		dcm.getColumn(4).setMinWidth(0);
		dcm.getColumn(4).setMaxWidth(0);
		dcm.getColumn(7).setMinWidth(0);
		dcm.getColumn(7).setMaxWidth(0);
		dcm.getColumn(9).setMinWidth(0);
		dcm.getColumn(9).setMaxWidth(0);
		dcm.getColumn(10).setMinWidth(0);
		dcm.getColumn(10).setMaxWidth(0);

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
		if (e.getSource() == tool_delete) {
			// 删除当前选中的数据
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要删除的订单记录");
			} else {
				// 获取当前选中数据记录的id
				String id = (String) table.getValueAt(row, 0);
				int result = JOptionPane.showConfirmDialog(null, "确认删除这条记录信息？");
				if (result == 0) {
					// 确认删除，则执行删除
					try {
						saleMasterService.joinRecycleBin(id);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// 显示删除成功信息面板，并刷新数据面板
					JOptionPane.showMessageDialog(null, "订单记录已放置回收站！");
					this.refreshTablePanel();
				}
			}
		} else if (e.getSource() == tool_detail) {
			// 显示订单的详细信息
			int row = table.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "请选择要查看的订单信息！");
			} else {
				new ShowSaleMasterDetail(this, this.loginUser, this.table, row);
			}
		} else if (e.getSource() == search) {
			this.refreshTablePanel();
		}else if(e.getSource()==start_time){
			backgroundPanel.remove(tablePanel);
			initTablePanel();
			backgroundPanel.validate();
		}else if(e.getSource()==end_time){
			backgroundPanel.remove(tablePanel);
			initTablePanel();
			backgroundPanel.validate();
		}
	}

	/**
	 * 刷新表格数据
	 */
	public void refreshTablePanel() {
		backgroundPanel.remove(tablePanel);
		initTablePanel();
		backgroundPanel.validate();
	}

	/**
	 * 刷新数据面板
	 */
	public void refreshBackgroundPanel() {
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