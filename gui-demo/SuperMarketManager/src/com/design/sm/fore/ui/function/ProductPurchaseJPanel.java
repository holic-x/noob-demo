package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.xml.crypto.KeySelector.Purpose;

import com.design.sm.model.Accounts;
import com.design.sm.model.Category;
import com.design.sm.model.Product;
import com.design.sm.model.StockMaster;
import com.design.sm.model.StockOrder;
import com.design.sm.model.Temp;
import com.design.sm.model.Warehouse;
import com.design.sm.service.AccountsService;
import com.design.sm.service.CategoryService;
import com.design.sm.service.ProductService;
import com.design.sm.service.TempService;
import com.design.sm.service.WarehouseService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.CategoryServiceImpl;
import com.design.sm.service.impl.ProductServiceImpl;
import com.design.sm.service.impl.TempServiceImpl;
import com.design.sm.service.impl.WarehouseServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.DataValidation;
import com.design.sm.utils.Item;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.Tools;

public class ProductPurchaseJPanel implements MouseListener, ItemListener,
		FocusListener {

	// 定义全局组件
	public JPanel backgroundPanel, topPanel, toolPanel, searchPanel,
			tablePanel, pagePanel;
	// 定义下拉列表
	JComboBox select_category, select_warehouse;
	// 借助工具类完成表格数据的封装
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// 定义用到的标签
	JLabel label_catagory, label_warehouse, label_keyword, tool_add;

	// 定义相应的文本框、组合按钮
	JTextField keyword;
	JButton search;
	ButtonGroup warn_state;
	JRadioButton all, warn;
	// 定义相应的service
	AccountsService accountsService = new AccountsServiceImpl();
	ProductService productService = new ProductServiceImpl();
	CategoryService categoryService = new CategoryServiceImpl();
	WarehouseService warehouseService = new WarehouseServiceImpl();
	TempService tempService = new TempServiceImpl();

	Accounts loginUser;
	/**
	 * 通过构造方法完成初始化
	 */
	public ProductPurchaseJPanel(Accounts loginUser) {
		backgroundPanel = new JPanel(new BorderLayout());
		this.loginUser = loginUser;
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
		toolPanel = new JPanel(new BorderLayout());
		// 添加增删改工具
		Icon icon_add = new ImageIcon("icons/toolImage/add.png");
		tool_add = new JLabel(icon_add);
		tool_add.setToolTipText("采购商品");// 设置鼠标移动时的显示内容
		tool_add.addMouseListener(this);// 添加鼠标监听

		warn_state = new ButtonGroup();
		all = new JRadioButton("所有商品");
		all.addItemListener(this);
		warn = new JRadioButton("库存预警");
		warn.addItemListener(this);
		warn_state.add(all);
		warn_state.add(warn);
		// 将初始化完成的工具加载到工具条面板中
		JPanel north = new JPanel();
		north.add(tool_add);
		JPanel south = new JPanel();
		south.add(all);
		south.add(warn);
		toolPanel.add(north, BorderLayout.NORTH);
		toolPanel.add(south, BorderLayout.SOUTH);
		// 最终将工具条面板加载到顶部菜单条的最西面
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * 初始化查找面板 设置查找方式： 1.仓库与分类组合查找 2.商品名称请输入商品名称关键字查找
	 */
	private void initSearchPanel() {
		searchPanel = new JPanel(new BorderLayout());
		// 设置商品分类下拉列表的所有属性
		label_catagory = new JLabel("所属分类");
		select_category = new JComboBox();
		select_category.addItem("所有内容");
		select_category.addItemListener(this);
		// 获取数据库中所有商品分类信息
		List<Category> list_category = null;
		try {
			list_category = categoryService.findAllCategoryList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 将获取的每项数据信息封装为Item对象
		for (Category c : list_category) {
			// 封装id、name信息
			String id = c.getCategory_id();
			String name = c.getCategory_name();
			Item item = new Item(id, name);
			select_category.addItem(item);
		}

		// 设置所属仓库的下拉列表的所有属性
		label_warehouse = new JLabel("所属仓库");
		select_warehouse = new JComboBox();
		select_warehouse.addItem("所有内容");
		select_warehouse.addItemListener(this);
		List<Warehouse> list_warehouse = null;
		try {
			list_warehouse = warehouseService.findAllWarehouseList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 将数据封装为Item对象，并加载到下拉列表中
		for (Warehouse w : list_warehouse) {
			// 封装id和name
			String id = w.getWarehouse_id();
			String name = w.getWarehouse_name();
			Item item = new Item(id, name);
			select_warehouse.addItem(item);
		}

		// 设置请输入商品名称关键字查找
		label_keyword = new JLabel("商品名称");
		keyword = new JTextField(18);
		keyword.setText("请输入商品名称关键字");
		keyword.addFocusListener(this);
		search = new JButton("查找");
		search.addMouseListener(this);

		// 将相关组件加载到指定的面板中
		JPanel north = new JPanel();
		north.add(label_catagory);
		north.add(select_category);
		north.add(label_warehouse);
		north.add(select_warehouse);
		JPanel south = new JPanel();
		south.add(label_keyword);
		south.add(keyword);
		south.add(search);
		searchPanel.add(north, BorderLayout.NORTH);
		searchPanel.add(south, BorderLayout.SOUTH);
		// 将布局好的组件加载到菜单栏面板的最东面
		topPanel.add(searchPanel, BorderLayout.EAST);
	}

	/**
	 * 初始化显示的表格数据
	 */
	private void initTablePanel() {
		// 要根据下拉框的选项进行筛选数据(要根据productService中返回的行进行设置，随后选择要隐藏的项目即可)
		String[] params = { "商品id", "条形码", "商品名称", "成本", "售价", "已上架", "仓库库存",
				"安全库存", "单位id", "单位", "产地", "生产日期", "商品描述", "折扣", "促销标识",
				"促销状态", "促销价格", "删除标识", "分类id", "分类", "供应商id", "供应商", "仓库id",
				"仓库" };
		Vector<Vector> vec = new Vector<>();
		// 获取当前下拉框的选择
		Object cItem = select_category.getSelectedItem();
		Object wItem = select_warehouse.getSelectedItem();
		if (!cItem.equals("所有内容") && !wItem.equals("所有内容")) {
			try {
				Item item1 = (Item) cItem;
				Item item2 = (Item) wItem;
				if(all.isSelected()){
					vec = productService.findAllProductUnion(item1.getKey(), item2.getKey());
				}else if(warn.isSelected()){
					List<Product> list = productService.findAllProductListUnion(item1.getKey(),
							item2.getKey());
					vec = productService.pack(this.getStockWarnProudctByUnion(list));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (!cItem.equals("所有内容")) {
			try {
				Item item = (Item) cItem;
				if(all.isSelected()){
					vec = productService.findAllProductByCategoryId(item.getKey());
				}else if(warn.isSelected()){
					List<Product> list = productService.findAllProductListByCategoryId(item.getKey());
					vec = productService.pack(this.getStockWarnProudctByUnion(list));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (!wItem.equals("所有内容")) {
			try {
				Item item = (Item) wItem;
				if(all.isSelected()){
					vec = productService.findAllProductByWarehouseId(item.getKey());
				}else if(warn.isSelected()){
					List<Product> list = productService.findAllProductListByWarehouseId(item.getKey());
					vec = productService.pack(this.getStockWarnProudctByUnion(list));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				if(all.isSelected()){
					vec = productService.findAllProductVector();
				}else if(warn.isSelected()){
					List<Product> list = productService.findAllProductList();
					vec = productService.pack(this.getStockWarnProudctByUnion(list));
				}
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
		// 隐藏：0 1 6 7 8 12 13 14 16 17 18 19 21
		// dcm.getColumn(0).setMinWidth(0);
		// dcm.getColumn(0).setMaxWidth(0);
		dcm.getColumn(1).setMinWidth(0);
		dcm.getColumn(1).setMaxWidth(0);
		dcm.getColumn(4).setMinWidth(0);
		dcm.getColumn(4).setMaxWidth(0);
		dcm.getColumn(8).setMinWidth(0);
		dcm.getColumn(8).setMaxWidth(0);
		dcm.getColumn(12).setMinWidth(0);
		dcm.getColumn(12).setMaxWidth(0);
		dcm.getColumn(13).setMinWidth(0);
		dcm.getColumn(13).setMaxWidth(0);
		dcm.getColumn(14).setMinWidth(0);
		dcm.getColumn(14).setMaxWidth(0);
		dcm.getColumn(15).setMinWidth(0);
		dcm.getColumn(15).setMaxWidth(0);
		dcm.getColumn(16).setMinWidth(0);
		dcm.getColumn(16).setMaxWidth(0);
		dcm.getColumn(17).setMinWidth(0);
		dcm.getColumn(17).setMaxWidth(0);
		dcm.getColumn(18).setMinWidth(0);
		dcm.getColumn(18).setMaxWidth(0);
		dcm.getColumn(20).setMinWidth(0);
		dcm.getColumn(20).setMaxWidth(0);
		dcm.getColumn(22).setMinWidth(0);
		dcm.getColumn(22).setMaxWidth(0);

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
		if (e.getSource() == tool_add) {
			// 加购商品
			int row = table.getSelectedRow();
			if(row<0){
				JOptionPane.showMessageDialog(null, "请选择要加购的商品");
			}else{
				// 获取当前选中行的商品信息
				int current_stock_int = Integer.valueOf(table.getValueAt(row, 6).toString());
				int safe_stock_int = Integer.valueOf(table.getValueAt(row, 7).toString());
				String tip = "当前商品--库存数目："+current_stock_int+" 安全库存数目:"+safe_stock_int;
				String value = JOptionPane.showInputDialog(null,tip);
				// 处理空指针异常
				if(value==null){
					value="";
				}
				Product prod = null;
				try {
					prod = productService.getProduct(table.getValueAt(row, 0).toString());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}if(value.equals("0")){
					JOptionPane.showMessageDialog(null, "操作数目不能为0！！");
				}else if(!DataValidation.isSignlessInteger(value)){
					JOptionPane.showMessageDialog(null, "输入格式有误，请输入正整数！");
				}else{
					int num = Integer.valueOf(value);
					int count = current_stock_int + num;
					String product_id = table.getValueAt(row, 0).toString();
					double unit_price = Double.valueOf(table.getValueAt(row, 3).toString());
					double amount = ((int)(unit_price*num*100))/100;// 保留两位小数
					if(count>safe_stock_int){
						int choose = JOptionPane.showConfirmDialog(null, "当前商品加购后会超出安全库存数目，确认继续进行操作？");
						if(choose==0){
							// 确认继续进行操作，则添加订单明细到临时的购物清单
							Temp t = new Temp(product_id,num,unit_price,amount);
							try {
								tempService.addTemp(t);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "商品已添加到加购清单！");
						}
					}else{
						Temp t = new Temp(product_id,num,unit_price,amount);
						try {
							tempService.addTemp(t);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "商品已添加到加购清单！");
					}
				}
			}
		} else if (e.getSource() == search) {
			// 先移除当前面板的所有数据
			tablePanel.removeAll();
			String[] params = { "商品id", "条形码", "商品名称", "成本", "售价", "已上架",
					"仓库库存", "安全库存", "单位id", "单位", "产地", "生产日期", "商品描述", "折扣",
					"促销标识", "促销状态", "促销价格", "删除标识", "分类id", "分类", "供应商id",
					"供应商", "仓库id", "仓库" };
			Vector<Vector> vec = new Vector<>();
			// 获取文本框以及单选框的内容
			String keyword_string = keyword.getText();
			if (!keyword_string.equals("请输入商品名称关键字")) {
				String text = "%" + keyword.getText() + "%";
				if(all.isSelected()){
					try {
						vec = productService.findAllProductByNameKeyword(text);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else if(warn.isSelected()){
					try {
						// 获取由商品名称请输入商品名称关键字过滤后的所有商品信息
						List list = productService.findAllProductListByNameKeyword(text);
						// 二次过滤符合库存预警条件的商品信息
						vec = productService.pack(this.getStockWarnProudctByUnion(list));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}else if(keyword_string.equals("请输入商品名称关键字")) {
				if(all.isSelected()){
					try {
						vec = productService.findAllProductVector();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else if(warn.isSelected()){
					try {
						// 获取由商品名称请输入商品名称关键字过滤后的所有商品信息
						List list = productService.findAllProductList();
						// 二次过滤符合库存预警条件的商品信息
						vec = productService.pack(this.getStockWarnProudctByUnion(list));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
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
			// 隐藏：0 1 6 7 8 12 13 14 16 17 18 19 21
			// dcm.getColumn(0).setMinWidth(0);
			// dcm.getColumn(0).setMaxWidth(0);
			dcm.getColumn(1).setMinWidth(0);
			dcm.getColumn(1).setMaxWidth(0);
			dcm.getColumn(4).setMinWidth(0);
			dcm.getColumn(4).setMaxWidth(0);
			dcm.getColumn(8).setMinWidth(0);
			dcm.getColumn(8).setMaxWidth(0);
			dcm.getColumn(12).setMinWidth(0);
			dcm.getColumn(12).setMaxWidth(0);
			dcm.getColumn(13).setMinWidth(0);
			dcm.getColumn(13).setMaxWidth(0);
			dcm.getColumn(14).setMinWidth(0);
			dcm.getColumn(14).setMaxWidth(0);
			dcm.getColumn(15).setMinWidth(0);
			dcm.getColumn(15).setMaxWidth(0);
			dcm.getColumn(16).setMinWidth(0);
			dcm.getColumn(16).setMaxWidth(0);
			dcm.getColumn(17).setMinWidth(0);
			dcm.getColumn(17).setMaxWidth(0);
			dcm.getColumn(18).setMinWidth(0);
			dcm.getColumn(18).setMaxWidth(0);
			dcm.getColumn(20).setMinWidth(0);
			dcm.getColumn(20).setMaxWidth(0);
			dcm.getColumn(22).setMinWidth(0);
			dcm.getColumn(22).setMaxWidth(0);

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
	}

	/**
	 * 刷新数据面板
	 */
	public void refreshTablePanelBySearch() {
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
			// 如果点击了下拉框选项，仅仅刷新数据面板,要先将数据面板的内容移除之后再添加
			tablePanel.removeAll();// 移除数据面板中的所有数据
			initTablePanel();// 重新初始化面板
		}
	}

	/**
	 * 聚焦事件
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("请输入商品名称关键字")) {
				keyword.setText("");
				keyword.setFont(MyFont.JTextFieldFont);
				keyword.setForeground(MyColor.JTextFieldColor);
			}
		}
	}

	/**
	 * 失去焦点事件
	 */
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("")) {
				keyword.setText("请输入商品名称关键字");
				keyword.setFont(MyFont.TipFont);
				keyword.setForeground(MyColor.TipColor);
			}
		}
	}
	
	/**
	 * 根据查找的列表二次筛选库存预警的商品（当前库存数目少于仓库安全库存数目的20%的商品信息）
	 */
	public List<Product> getStockWarnProudctByUnion(List<Product> list){
		List<Product> find_list = new ArrayList<Product>();
		for(Product p : list){
			if(p.getCurrent_stock()<(int) (p.getSafe_stock()*0.20)){
				find_list.add(p);
			}
		}
		return find_list;
	}
}
