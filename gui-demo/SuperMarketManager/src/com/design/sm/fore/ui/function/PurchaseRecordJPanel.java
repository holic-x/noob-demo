package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
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
import com.design.sm.model.PurchaseNote;
import com.design.sm.model.StockOrder;
import com.design.sm.service.AccountsService;
import com.design.sm.service.ProductService;
import com.design.sm.service.PurchaseNoteService;
import com.design.sm.service.StockMasterService;
import com.design.sm.service.StockOrderService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.ProductServiceImpl;
import com.design.sm.service.impl.PurchaseNoteServiceImpl;
import com.design.sm.service.impl.StockMasterServiceImpl;
import com.design.sm.service.impl.StockOrderServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.DataValidation;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.Tools;
import com.eltima.components.ui.DatePicker;

public class PurchaseRecordJPanel implements MouseListener, ItemListener {
	// 定义全局组件
	JPanel backgroundPanel, topPanel, toolPanel, tablePanel, searchPanel;
	// 借助工具类完成表格数据的封装
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// 定义用到的标签
	JLabel tool_delete, tool_verify, tool_detail;
	ButtonGroup sm_state;
	JRadioButton committed, pass, cancel;
	DatePicker start_time, end_time;
	JButton search;
	// 定义相应的service
	StockMasterService stockMasterService = new StockMasterServiceImpl();
	StockOrderService stockOrderService = new StockOrderServiceImpl();
	ProductService productService = new ProductServiceImpl();
	PurchaseNoteService purchaseNoteService = new PurchaseNoteServiceImpl();
	AccountsService accountsService = new AccountsServiceImpl();
	Accounts loginUser;

	/**
	 * 通过构造方法完成初始化
	 */
	public PurchaseRecordJPanel(Accounts loginUser) {
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

		Icon icon_verify = new ImageIcon("icons/toolImage/verify.png");
		tool_verify = new JLabel(icon_verify);
		tool_verify.setToolTipText("状态修改");// 设置鼠标移动时的显示内容
		tool_verify.addMouseListener(this);// 添加鼠标监听

		Icon icon_detail = new ImageIcon("icons/toolImage/detail.png");
		tool_detail = new JLabel(icon_detail);
		tool_detail.setToolTipText("订单明细");// 设置鼠标移动时的显示内容
		tool_detail.addMouseListener(this);// 添加鼠标监听

		sm_state = new ButtonGroup();
		committed = new JRadioButton("已提交");
		committed.addItemListener(this);
		pass = new JRadioButton("通过审核");
		pass.addItemListener(this);
		cancel = new JRadioButton("审核失败");
		cancel.addItemListener(this);
		sm_state.add(committed);
		sm_state.add(pass);
		sm_state.add(cancel);

		// 将初始化完成的工具加载到工具条面板中
		toolPanel.add(tool_delete);
		toolPanel.add(tool_verify);
		toolPanel.add(tool_detail);
		toolPanel.add(committed);
		toolPanel.add(pass);
		toolPanel.add(cancel);
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

		// 根据日期数据筛选指定范围内的所有订单信息,刷新数据面板
		// 输入的数据进行查找
		String[] params = { "订单主表id", "订单编号", "经手人id", "经手人", "供应商id", "供应商",
				"供应商联系人id", "联系人", "处理时间", "出入库标识", "出/入库", "删除标识", "删除标识含义",
				"状态标识", "订单状态" };
		Vector<Vector> vec = new Vector<>();
		// 判断数据输入的日期格式是否合法
		String start_time_string = start_time.getText();
		String end_time_string = end_time.getText();
		if (!(start_time_string.equals("") && end_time_string.equals(""))) {
			try {
				if (committed.isSelected()) {
					vec = stockMasterService.pack(stockMasterService
							.findAllStockListByTimeUnionState(0,
									start_time_string, end_time_string, 0));// 查找当前所有已提交但未处理的记录
				} else if (pass.isSelected()) {
					vec = stockMasterService.pack(stockMasterService
							.findAllStockListByTimeUnionState(0,
									start_time_string, end_time_string, 1));// 查找当前所有通过审核的记录
				} else if (cancel.isSelected()) {
					vec = stockMasterService.pack(stockMasterService
							.findAllStockListByTimeUnionState(0,
									start_time_string, end_time_string, -1));// 查找当前所有未通过审核的记录
				} else {
					vec = stockMasterService.pack(stockMasterService
							.findAllStockInList());// 查找当前所有入库记录
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			// 如果开始时间为空置为最小值
			if (start_time_string.equals("")) {
				start_time_string = "0000-00-00";
			}
			// 如果结束时间为空置为最大值
			if (end_time_string.equals("")) {
				end_time_string = "9999-99-99";
			}
			// 进行查找
			try {
				if (committed.isSelected()) {
					vec = stockMasterService.pack(stockMasterService
							.findAllStockListByTimeUnionState(0,
									start_time_string, end_time_string, 0));// 查找当前所有已提交但未处理的记录
				} else if (pass.isSelected()) {
					vec = stockMasterService.pack(stockMasterService
							.findAllStockListByTimeUnionState(0,
									start_time_string, end_time_string, 1));// 查找当前所有通过审核的记录
				} else if (cancel.isSelected()) {
					vec = stockMasterService.pack(stockMasterService
							.findAllStockListByTimeUnionState(0,
									start_time_string, end_time_string, -1));// 查找当前所有未通过审核的记录
				} else {
					// 此处是默认查找所有的内容
					vec = stockMasterService.pack(stockMasterService
							.findAllStockInList());// 查找当前所有入库记录
				}
			} catch (SQLException ee) {
				ee.printStackTrace();
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
		// 在刷新数据的时候改变窗体大小，完成数据刷新
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
				// 获取当前选中仓库的id
				String id = (String) table.getValueAt(row, 0);
				int result = JOptionPane.showConfirmDialog(null, "确认删除这条记录信息？");
				if (result == 0) {
					// 确认删除，则执行删除
					try {
						stockMasterService.joinRecycleBin(id);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// 显示删除成功信息面板，并刷新数据面板
					JOptionPane.showMessageDialog(null, "订单记录已放置回收站！");
					this.refreshTablePanel();
				}
			}
		} else if (e.getSource() == tool_verify) {
			// 修改当前选中查看的订单状态，根据不同的页面输出不同的信息提示
			if (committed.isSelected()) {
				// 通过修改已提交的订单状态，从而执行相应的入库操作:可有两种修改状态：通过、不通过
				int row = table.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "请选择要执行该操作的订单记录！");
				} else {
					/**
					 *  只有仓库管理高层人员或者是测试人员才有权利判断是否通过订单审核，
					 *  由于在登录处就已经限制了进入仓库管理的员工只能是物流部的员工或者是
					 *  系统维护的员工，因此此处只需要通过判断当前账号的权限是否为“超级管理员--1”
					 *  或“经理或主管--2”，才能够审核订单，否则提示没有权限！
					 */
					// 获取账号权限
					int limits = 0;
					try {
						limits = Integer.valueOf(String.valueOf(accountsService.getAccountsLimits(this.loginUser.getAccount_id())));
					} catch (NumberFormatException e2) {
						e2.printStackTrace();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					if(limits==1||limits==2){
						try {
							Object[] options = { "通过审核", "审核不通过", "取消" };
							int choose = JOptionPane.showOptionDialog(null,
									"请选择相应的操作", "订单状态修改",
									JOptionPane.DEFAULT_OPTION,
									JOptionPane.INFORMATION_MESSAGE, null, options,
									options[1]);
							if (choose == 0) {
								// 根据订单主表中的订单信息，修改库存信息，表示商品正常入库
								String smId = table.getValueAt(row, 0).toString();
								List<StockOrder> list_order = stockOrderService
										.getStockOrderBySMId(smId);
								for (int i = 0; i < list_order.size(); i++) {
									// 根据商品id修改商品的库存数目
									String prodId = list_order.get(i)
											.getProduct_id();
									int num = list_order.get(i).getQuantity();
									Product p = productService.getProduct(prodId);
									p.setCurrent_stock(p.getCurrent_stock() + num);// 修改当前商品库存
									productService.updateProduct(p);// 执行修改操作
								}
								// 修改订单状态
								stockMasterService.passStockMaster(smId);
								JOptionPane.showMessageDialog(null,
										"订单已通过审核，商品允许加入仓库");

								// 订单通过审核则添加进购记录
								Object[] option = { "现金支付", "转账支付" };
								int i = JOptionPane.showOptionDialog(null,
										"请选择交易方式！", "交易执行",
										JOptionPane.DEFAULT_OPTION,
										JOptionPane.INFORMATION_MESSAGE, null,
										option, option[1]);
								String order_num = stockMasterService
										.getSMOrderNumById(table.getValueAt(row, 0)
												.toString());
								// 获取订单信息，进行数据统计
								double actual_amount = this.getAmount(row);
								PurchaseNote pn = new PurchaseNote(order_num,
										actual_amount, (i + 1));
								purchaseNoteService.addPurchaseNote(pn);
								refreshTablePanel();
							} else if (choose == 1) {
								// 根据订单主表中的订单信息
								String smId = table.getValueAt(row, 0).toString();
								// 修改订单状态:审批不通过
								stockMasterService.cancelStockMaster(smId);
								JOptionPane.showMessageDialog(null, "订单未通过审核！");
								this.refreshTablePanel();
							}
						} catch (HeadlessException | SQLException e1) {
							e1.printStackTrace();
						}
					}else{
						JOptionPane.showMessageDialog(null, "抱歉，只有经理级以上员工才能执行该操作！");
					}
				}
			} else if (pass.isSelected()) {
				// 可以查阅审核通过的订单，但不执行任何操作
				JOptionPane.showMessageDialog(null, "无法执行该操作！");
			} else if (cancel.isSelected()) {
				// 处于审核失败的订单可以重新再次提交
				int row = table.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "请选择要执行该操作的订单记录！");
				} else {
					int choose = JOptionPane.showConfirmDialog(null,
							"确认再次提交该订单？");
					if (choose == 0) {
						// 重新修改订单状态，再次提交订单
						String id = table.getValueAt(row, 0).toString();
						try {
							stockMasterService.commitStockMaster(id);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "订单提交成功！");
						this.refreshTablePanel();
					}
				}
			}
		} else if (e.getSource() == tool_detail) {
			// 显示订单的详细信息
			int row = table.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "请选择要查看的订单信息！");
			} else {
				new ShowStockInMasterDetail(this, this.loginUser, this.table,
						row);
			}
		} else if (e.getSource() == search) {
			// 先移除数据，随后进行刷新
			backgroundPanel.remove(tablePanel);
			initTablePanel();
			backgroundPanel.validate();
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

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			// 刷新数据面板
			tablePanel.removeAll();
			initTablePanel();
			backgroundPanel.validate();
		}
	}

	/**
	 * 定义方法获取订单主表的总额
	 */
	public double getAmount(int row) {
		double amount = 0.00;
		List<StockOrder> list = null;
		try {
			list = stockOrderService.getStockOrderBySMId(table.getValueAt(row,
					0).toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < list.size(); i++) {
			StockOrder so = list.get(i);
			amount = so.getAmount();
		}
		return amount;
	}
}