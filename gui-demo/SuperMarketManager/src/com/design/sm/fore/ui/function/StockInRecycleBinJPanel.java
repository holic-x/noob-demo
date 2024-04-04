package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import com.design.sm.model.Accounts;
import com.design.sm.model.Product;
import com.design.sm.model.StockOrder;
import com.design.sm.service.ProductService;
import com.design.sm.service.StockMasterService;
import com.design.sm.service.StockOrderService;
import com.design.sm.service.impl.ProductServiceImpl;
import com.design.sm.service.impl.StockMasterServiceImpl;
import com.design.sm.service.impl.StockOrderServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.DataValidation;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.Tools;

public class StockInRecycleBinJPanel implements MouseListener {
	// 定义全局组件
		JPanel backgroundPanel, topPanel, toolPanel, tablePanel;
		// 借助工具类完成表格数据的封装
		BaseTableModule baseTableModule;
		JTable table;
		JScrollPane jScrollPane;
		// 定义用到的标签
		JLabel tool_clean, tool_revoke;
		// 定义相应的service
		StockMasterService stockMasterService = new StockMasterServiceImpl();
		StockOrderService stockOrderService = new StockOrderServiceImpl();
		ProductService productService = new ProductServiceImpl();
		Accounts loginUser;

		/**
		 * 通过构造方法完成初始化
		 */
		public StockInRecycleBinJPanel(Accounts loginUser) {
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
			// 将顶部菜单栏加载到背景面板中
			backgroundPanel.add(topPanel, BorderLayout.NORTH);
		}

		/**
		 * 初始化工具条面板
		 */
		private void initToolPanel() {
			toolPanel = new JPanel();

			Icon icon_clean = new ImageIcon("icons/toolImage/clean.png");
			tool_clean = new JLabel(icon_clean);
			tool_clean.setToolTipText("彻底删除");// 设置鼠标移动时的显示内容
			tool_clean.addMouseListener(this);// 添加鼠标监听

			Icon icon_revoke = new ImageIcon("icons/toolImage/revoke.png");
			tool_revoke = new JLabel(icon_revoke);
			tool_revoke.setToolTipText("撤销删除");// 设置鼠标移动时的显示内容
			tool_revoke.addMouseListener(this);// 添加鼠标监听

			// 将初始化完成的工具加载到工具条面板中
			toolPanel.add(tool_clean);
			toolPanel.add(tool_revoke);
			// 最终将工具条面板加载到顶部菜单条的最西面
			topPanel.add(toolPanel, BorderLayout.WEST);
		}

		/**
		 * 初始化显示的表格数据
		 */
		private void initTablePanel() {

			// 输入的数据进行查找
			String[] params = { "订单主表id", "订单编号", "经手人id", "经手人", "供应商id", "供应商",
					"供应商联系人id", "联系人", "处理时间", "出入库标识", "出/入库", "删除标识", "删除标识含义",
					"状态标识", "订单状态" };
			Vector<Vector> vec = new Vector<>();
					try {
						// 查找当前放置在回收站的所有入库记录
						vec = stockMasterService.pack(stockMasterService
								.findAllStockListRecycleBin(0));
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
			// 通过设置行的大小，隐藏某一列内容（只显示想要显示的数据）
			DefaultTableColumnModel dcm = (DefaultTableColumnModel) table
					.getColumnModel();
			// 隐藏 2 4 6 9 11 13
			dcm.getColumn(2).setMinWidth(0);
			dcm.getColumn(2).setMaxWidth(0);
			dcm.getColumn(4).setMinWidth(0);
			dcm.getColumn(4).setMaxWidth(0);
			dcm.getColumn(6).setMinWidth(0);
			dcm.getColumn(6).setMaxWidth(0);
			dcm.getColumn(9).setMinWidth(0);
			dcm.getColumn(9).setMaxWidth(0);
			dcm.getColumn(11).setMinWidth(0);
			dcm.getColumn(11).setMaxWidth(0);
			dcm.getColumn(12).setMinWidth(0);
			dcm.getColumn(12).setMaxWidth(0);
			dcm.getColumn(13).setMinWidth(0);
			dcm.getColumn(13).setMaxWidth(0);

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
			if (e.getSource() == tool_clean) {
				// 删除当前选中的数据
				int row = table.getSelectedRow();// 得到选中的行
				if (row < 0) {// 没有选中任何收据
					JOptionPane.showMessageDialog(null, "请选择要彻底删除的订单记录");
				} else {
					// 获取当前选中仓库的id
					String id = (String) table.getValueAt(row, 0);
					int result = JOptionPane.showConfirmDialog(null, "确认彻底删除这条记录信息？");
					if (result == 0) {
						// 确认删除，则执行删除
						try {
							stockMasterService.deleteStockMaster(id);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						// 显示删除成功信息面板，并刷新数据面板
						JOptionPane.showMessageDialog(null, "订单信息已彻底删除！");
						refreshTablePanel();
					}
				}
			} else if (e.getSource() == tool_revoke) {
				// 撤销订单删除信息
				int row = table.getSelectedRow();// 得到选中的行
				if (row < 0) {// 没有选中任何收据
					JOptionPane.showMessageDialog(null, "请选择要撤销删除的订单记录");
				} else {
					// 获取当前选中行的id
					String id = (String) table.getValueAt(row, 0);
					int result = JOptionPane.showConfirmDialog(null, "确认撤销这条删除记录？");
					if (result == 0) {
						// 确认撤销，执行撤销操作
						try {
							stockMasterService.revokeRecycleBin(id);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						// 显示删除成功信息面板，并刷新数据面板
						JOptionPane.showMessageDialog(null, "该删除记录已撤销！");
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