package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import com.design.sm.fore.ui.control.StockManagerJPanel;
import com.design.sm.model.Accounts;
import com.design.sm.model.Product;
import com.design.sm.model.Temp;
import com.design.sm.model.Warehouse;
import com.design.sm.service.ProductService;
import com.design.sm.service.TempService;
import com.design.sm.service.WarehouseService;
import com.design.sm.service.impl.ProductServiceImpl;
import com.design.sm.service.impl.TempServiceImpl;
import com.design.sm.service.impl.WarehouseServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.DataValidation;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.RandomGeneration;
import com.design.sm.utils.Tools;

public class PurchaseListJPanel implements MouseListener {
	// 定义全局组件
	JPanel backgroundPanel, topPanel, toolPanel, tablePanel;
	// 借助工具类完成表格数据的封装
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// 定义用到的标签
	JLabel label_keyword, tool_modify, tool_delete, tool_commit;
	// 定义相应的service
	ProductService productService = new ProductServiceImpl();
	TempService tempService = new TempServiceImpl();
	Accounts loginUser;

	/**
	 * 通过构造方法完成初始化
	 */
	public PurchaseListJPanel(Accounts loginUser) {
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

		Icon icon_modify = new ImageIcon("icons/toolImage/modify.png");
		tool_modify = new JLabel(icon_modify);
		tool_modify.setToolTipText("修改记录");// 设置鼠标移动时的显示内容
		tool_modify.addMouseListener(this);// 添加鼠标监听

		Icon icon_delete = new ImageIcon("icons/toolImage/delete.png");
		tool_delete = new JLabel(icon_delete);
		tool_delete.setToolTipText("删除记录");// 设置鼠标移动时的显示内容
		tool_delete.addMouseListener(this);// 添加鼠标监听

		Icon icon_commit = new ImageIcon("icons/toolImage/commit.png");
		tool_commit = new JLabel(icon_commit);
		tool_commit.setToolTipText("提交订单");// 设置鼠标移动时的显示内容
		tool_commit.addMouseListener(this);// 添加鼠标监听

		// 将初始化完成的工具加载到工具条面板中
		toolPanel.add(tool_modify);
		toolPanel.add(tool_delete);
		toolPanel.add(tool_commit);
		// 最终将工具条面板加载到顶部菜单条的最西面
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * 初始化显示的表格数据
	 */
	private void initTablePanel() {

		// 输入的数据进行查找
		String[] params = { "产品id", "产品名称", "操作数量", "产品单价", "总额", "供应商id",
				"供应商" };
		Vector<Vector> vec = new Vector<>();
		try {
			vec = tempService.pack(tempService.findAllTempList());
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
		// 隐藏：5
		dcm.getColumn(5).setMinWidth(0);
		dcm.getColumn(5).setMaxWidth(0);

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
		if (e.getSource() == tool_modify) {
			// 获取当前选中要修改的数据
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要修改的记录信息");
			} else {
				Product prod = null;
				try {
					prod = productService.getProduct(table.getValueAt(row, 0)
							.toString());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				double unit_price = Double.valueOf(table.getValueAt(row, 3)
						.toString());
				int current_stock_int = prod.getCurrent_stock();
				int safe_stock = prod.getSafe_stock();
				String value = JOptionPane.showInputDialog(null, "请输入修改后的数目");
				// 处理空指针异常
				if (value == null) {
					value = "";
				}
				if (DataValidation.isSignlessInteger(value)) {
					int num = Integer.valueOf(value);
					if (num <= 0) {
						JOptionPane.showMessageDialog(null, "请输入正整数！");
					} else if ((current_stock_int + num) > safe_stock) {
						int choose = JOptionPane.showConfirmDialog(null,
								"当前商品加购后会超出安全库存数目，确认继续进行操作？");
						if (choose == 0) {
							// 确认继续进行操作，则修改记录信息
							int amount = ((int) (unit_price * num * 100)) / 100;
							Temp t = new Temp(prod.getProd_id(), num,
									unit_price, amount);
							try {
								tempService.update(t);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							// 刷新数据面板
							this.refreshTablePanel();
						} else {
							// 确认继续进行操作，则修改记录信息
							int amount = ((int) (unit_price * num * 100)) / 100;
							Temp t = new Temp(prod.getProd_id(), num,
									unit_price, amount);
							try {
								tempService.update(t);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							// 刷新数据面板
							this.refreshTablePanel();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "请输入正整数！");
				}
			}
		} else if (e.getSource() == tool_delete) {
			// 删除当前选中的数据
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要删除的记录信息");
			} else {
				// 获取当前选中记录的id
				String id = (String) table.getValueAt(row, 0);
				int result = JOptionPane.showConfirmDialog(null, "确认删除这条记录信息？");
				if (result == 0) {
					// 确认删除，则执行删除
					try {
						tempService.deleteTemp(id);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// 显示删除成功信息面板，并刷新数据面板
					JOptionPane.showMessageDialog(null, "记录删除成功！");
					refreshTablePanel();
				}
			}
		} else if (e.getSource() == tool_commit) {
			/**
			 * 选择相应的记录信息，此处进行限制，必须为同一个供应商提供的商品 才能作为一个有效的订单进行提交，汇总、提交，如果提交成功会移除
			 * 当前选中的记录，并根据记录生成新的订单信息 订单需要经过审核之后才能够进入到下一阶段的入库（即将商品放入库存中）
			 */
			// 获取当前用户选中的行,数组长度为0说明用户没有选中数据
			 int[] selectRow = table.getSelectedRows();
			 if(selectRow.length==0){
				 JOptionPane.showMessageDialog(null, "请选中要提交的订单信息！");
			 }else{
				 if(this.isValid()){
						new EnsurePurchaseOrderJFrame(this, this.loginUser,table,selectRow);
					}else{
						JOptionPane.showMessageDialog(null, "您的操作不合法，必须是同一个供应商！！");
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

	/**
	 * 获取当前选中的记录信息，判断是否为同一个供应商提供的商品
	 */
	public boolean isValid() {
		// 获取当前选中的数据
		String[] ids;
		ArrayList id_list = new ArrayList<>();
		for (int rowindex : table.getSelectedRows()) {
			Object obj = table.getValueAt(rowindex, 0);
			id_list.add(obj);
		}
		// 集合转数组
		ids = (String[]) id_list.toArray(new String[id_list.size()]);
		/**
		 *  每个供应商的信息必须一一相同，因此此处选择第一个商品id作为参考，如果存在一个不同则视为不合法的操作
		 */
		try {
			Product prod = productService.getProduct(ids[0]);
			String key = prod.getVendor_id();
			for(String id : ids){
				Product p =productService.getProduct(id);
				if(!p.getVendor_id().equals(key))//字符串进行比较
					return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
