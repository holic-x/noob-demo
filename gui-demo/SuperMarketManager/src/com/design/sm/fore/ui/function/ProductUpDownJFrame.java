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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import com.design.sm.model.Accounts;
import com.design.sm.model.Category;
import com.design.sm.model.Product;
import com.design.sm.model.Warehouse;
import com.design.sm.service.AccountsService;
import com.design.sm.service.CategoryService;
import com.design.sm.service.ProductService;
import com.design.sm.service.WarehouseService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.CategoryServiceImpl;
import com.design.sm.service.impl.ProductServiceImpl;
import com.design.sm.service.impl.WarehouseServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.DataValidation;
import com.design.sm.utils.Item;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.Tools;

/**
 * 商品上下架处理
 */
public class ProductUpDownJFrame extends JFrame implements MouseListener,
		ItemListener, FocusListener {

	// 定义全局组件
	JPanel backgroundPanel, topPanel, toolPanel, searchPanel, tablePanel,
			pagePanel;
	// 定义下拉列表
	JComboBox select_category, select_warehouse;
	// 借助工具类完成表格数据的封装
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// 定义用到的标签
	JLabel label_catagory, label_warehouse, label_keyword, label_state,
			tool_put_on, tool_pull_off, tool_warn, tool_export;
	// 定义相应的文本框、组合按钮
	JTextField keyword;
	ButtonGroup put_on_state;
	JRadioButton put_on, pull_off;
	// 定义相应的service
	AccountsService accountsService = new AccountsServiceImpl();
	ProductService productService = new ProductServiceImpl();
	CategoryService categoryService = new CategoryServiceImpl();
	WarehouseService warehouseService = new WarehouseServiceImpl();

	Accounts loginUser;

	/**
	 * 通过构造方法完成初始化
	 */
	public ProductUpDownJFrame(Accounts loginUser) {
		backgroundPanel = new JPanel(new BorderLayout());
		this.loginUser = loginUser;
		// 初始化布局
		initTopPanel();// 初始化顶部菜单条
		initTablePanel();// 初始化显示的表格数据
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("商品上下架");
		this.setSize(1000, 550);
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
		toolPanel = new JPanel(new BorderLayout());
		// 添加增删改工具
		Icon put_on = new ImageIcon("icons/toolImage/put_on.png");
		tool_put_on = new JLabel(put_on);
		tool_put_on.setToolTipText("商品上架");// 设置鼠标移动时的显示内容
		tool_put_on.addMouseListener(this);// 添加鼠标监听

		Icon pull_off = new ImageIcon("icons/toolImage/pull_off.png");
		tool_pull_off = new JLabel(pull_off);
		tool_pull_off.setToolTipText("商品下架");// 设置鼠标移动时的显示内容
		tool_pull_off.addMouseListener(this);// 添加鼠标监听

		Icon icon_warn = new ImageIcon("icons/toolImage/warn.png");
		tool_warn = new JLabel(icon_warn);
		tool_warn.setToolTipText("上架预警");// 设置鼠标移动时的显示内容
		tool_warn.addMouseListener(this);// 添加鼠标监听

		Icon icon_export = new ImageIcon("icons/toolImage/export.png");
		tool_export = new JLabel(icon_export);
		tool_export.setToolTipText("导出数据");// 设置鼠标移动时的显示内容
		tool_export.addMouseListener(this);// 添加鼠标监听

		// 将初始化完成的工具加载到工具条面板中
		JPanel north = new JPanel();
		north.add(tool_put_on);
		north.add(tool_pull_off);
		JPanel south = new JPanel();
		south.add(tool_warn);
		south.add(tool_export);
		toolPanel.add(north, BorderLayout.NORTH);
		toolPanel.add(south, BorderLayout.SOUTH);
		// 最终将工具条面板加载到顶部菜单条的最西面
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * 初始化查找面板 设置查找方式： 1.仓库与分类组合查找上下架商品信息 2.商品名称关键字、销售状态组合查找上下架商品信息
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

		// 设置关键字查找
		label_keyword = new JLabel("商品名称");
		keyword = new JTextField(8);
		keyword.setText("关键字");
		keyword.addFocusListener(this);
		keyword.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// 先移除当前面板的所有数据
				tablePanel.removeAll();
				String[] params = { "商品id", "条形码", "商品名称", "成本", "售价", "已上架",
						"仓库库存", "安全库存", "单位id", "单位", "产地", "生产日期", "商品描述",
						"折扣", "促销标识", "促销状态", "促销价格", "删除标识", "分类id", "分类",
						"供应商id", "供应商", "仓库id", "仓库" };
				Vector<Vector> vec = new Vector<>();
				// 获取文本框以及单选框的内容
				String keyword_string = keyword.getText();
				if (keyword_string.equals("关键字")) {
					try {
						// 获取所有商品信息
						List<Product> list = productService
								.findAllProductList();
						// 将商品信息进行二次过滤
						if (put_on.isSelected()) {
							vec = productService.pack(getProductPutOn(list));
						} else if (pull_off.isSelected()) {
							vec = productService.pack(getProductPullOff(list));
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					// 获取所有经过商品名称过滤后的商品信息
					try {
						String text = "%" + keyword.getText() + "%";
						List<Product> list = productService
								.findAllProductListByNameKeyword(text);
						// 将商品信息进行二次过滤
						if (put_on.isSelected()) {
							vec = productService.pack(getProductPutOn(list));
						} else if (pull_off.isSelected()) {
							vec = productService.pack(getProductPullOff(list));
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
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
									JTable table, Object value,
									boolean isSelected, boolean hasFocus,
									int row, int column) {
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
				dcm.getColumn(6).setMinWidth(0);
				dcm.getColumn(6).setMaxWidth(0);
				dcm.getColumn(7).setMinWidth(0);
				dcm.getColumn(7).setMaxWidth(0);
				dcm.getColumn(8).setMinWidth(0);
				dcm.getColumn(8).setMaxWidth(0);
				dcm.getColumn(12).setMinWidth(0);
				dcm.getColumn(12).setMaxWidth(0);
				dcm.getColumn(13).setMinWidth(0);
				dcm.getColumn(13).setMaxWidth(0);
				dcm.getColumn(14).setMinWidth(0);
				dcm.getColumn(14).setMaxWidth(0);
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
			public void changedUpdate(DocumentEvent e) {

			}
		});
		put_on_state = new ButtonGroup();
		put_on = new JRadioButton("上架商品");
		pull_off = new JRadioButton("下架商品");
		put_on_state.add(put_on);
		put_on_state.add(pull_off);
		put_on.addItemListener(this);
		pull_off.addItemListener(this);

		// 将相关组件加载到指定的面板中
		JPanel north = new JPanel();
		north.add(label_catagory);
		north.add(select_category);
		north.add(label_warehouse);
		north.add(select_warehouse);
		JPanel south = new JPanel();
		south.add(label_keyword);
		south.add(keyword);
		south.add(put_on);
		south.add(pull_off);
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
				// 获取经过仓库和分类信息过滤的商品信息
				List<Product> listByUnion = productService
						.findAllProductListUnion(item1.getKey(), item2.getKey());
				// 将商品信息进行二次过滤
				if (put_on.isSelected()) {
					vec = productService
							.pack(this.getProductPutOn(listByUnion));
				} else if (pull_off.isSelected()) {
					vec = productService.pack(this
							.getProductPullOff(listByUnion));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (!cItem.equals("所有内容")) {
			try {
				Item item = (Item) cItem;
				// 获取经过分类信息过滤的商品信息
				List<Product> listByCid = productService
						.findAllProductListByCategoryId(item.getKey());
				// 将商品信息进行二次过滤
				if (put_on.isSelected()) {
					vec = productService.pack(this.getProductPutOn(listByCid));
				} else if (pull_off.isSelected()) {
					vec = productService
							.pack(this.getProductPullOff(listByCid));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (!wItem.equals("所有内容")) {
			try {
				Item item = (Item) wItem;
				// 获取经过仓库信息过滤的商品信息
				List<Product> listByWid = productService
						.findAllProductListByWarehouseId(item.getKey());
				// 将商品信息进行二次过滤
				if (put_on.isSelected()) {
					vec = productService.pack(this.getProductPutOn(listByWid));
				} else if (pull_off.isSelected()) {
					vec = productService
							.pack(this.getProductPullOff(listByWid));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				// 获取所有商品信息
				List<Product> list = productService.findAllProductList();
				// 将商品信息进行二次过滤
				if (put_on.isSelected()) {
					vec = productService.pack(this.getProductPutOn(list));
				} else if (pull_off.isSelected()) {
					vec = productService.pack(this.getProductPullOff(list));
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
		dcm.getColumn(6).setMinWidth(0);
		dcm.getColumn(6).setMaxWidth(0);
		dcm.getColumn(7).setMinWidth(0);
		dcm.getColumn(7).setMaxWidth(0);
		dcm.getColumn(8).setMinWidth(0);
		dcm.getColumn(8).setMaxWidth(0);
		dcm.getColumn(12).setMinWidth(0);
		dcm.getColumn(12).setMaxWidth(0);
		dcm.getColumn(13).setMinWidth(0);
		dcm.getColumn(13).setMaxWidth(0);
		dcm.getColumn(14).setMinWidth(0);
		dcm.getColumn(14).setMaxWidth(0);
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
		if (e.getSource() == tool_put_on) {
			// 商品上架
			// 获取当前选中要操作的数据
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要上架的商品");
			} else {
				/**
				 * 根据输入的数据进行操作，上架成功后修改商品的两个参数 1.已上架库存增加：之前的上架库存数目+新增的上架数目
				 * 2.仓库剩余库存减少：之前的仓库库存数目-新增的上架数目
				 */
				// 获取选择要上架的商品的当前上架库存、仓库库存数目
				int current_put_on_stock = Integer.valueOf(table.getValueAt(
						row, 5).toString());
				int current_stock = Integer.valueOf(table.getValueAt(row, 6)
						.toString());
				String tip = "当前商品--上架库存：" + current_put_on_stock + "  仓库库存："
						+ current_stock;
				String input = JOptionPane.showInputDialog(null, tip);
				// 处理空指针异常
				if(input==null){
					input="";
				}
				if (DataValidation.isSignlessInteger(input)) {
					int num = Integer.valueOf(input);
					// 判断输入数据是否满足要求
					if(num<=current_stock&&num>0){
						int choose = JOptionPane.showConfirmDialog(null, "商品上架数目为"
								+ num + ",确认上架该商品？");
						if (choose == 0) {
							// 修改商品上架数目、库存数目
							try {
								Product p = productService.getProduct(table
										.getValueAt(row, 0).toString());
								p.setPutaway_stock(current_put_on_stock + num);
								p.setCurrent_stock(current_stock - num);
								productService.updateProduct(p);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "商品上架成功！");
						}
					}else if(num>current_stock){
						JOptionPane.showMessageDialog(null, "输入数据不能超出商品当前库存！");
					}else if(num==0){
						JOptionPane.showMessageDialog(null, "输入数据不能为0！");
					}
				}
			}
		} else if (e.getSource() == tool_pull_off) {
			// 获取当前选中要操作的数据
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要下架的商品");
			} else {
				/**
				 * 根据输入的数据进行操作，上架成功后修改商品的两个参数 1.已上架库存减少：之前的上架库存数目-下架商品数目
				 * 2.仓库剩余库存增加：之前的仓库库存数目+上架商品数目
				 */
				// 获取选择要上架的商品的当前上架库存、仓库库存数目
				int current_put_on_stock = Integer.valueOf(table.getValueAt(
						row, 5).toString());
				int current_stock = Integer.valueOf(table.getValueAt(row, 6)
						.toString());
				String tip = "当前商品--上架库存：" + current_put_on_stock + "  仓库库存："
						+ current_stock;
				String input = JOptionPane.showInputDialog(null, tip);
				// 处理空指针异常
				if(input==null){
					input="";
				}
				if (DataValidation.isSignlessInteger(input)) {
					int num = Integer.valueOf(input);
					if(num<=current_put_on_stock&&num>0){
						int choose = JOptionPane.showConfirmDialog(null, "商品下架数目为"
								+ num + ",确认下架该商品？");
						if (choose == 0) {
							// 修改商品上架数目、库存数目
							try {
								Product p = productService.getProduct(table
										.getValueAt(row, 0).toString());
								p.setPutaway_stock(current_put_on_stock - num);
								p.setCurrent_stock(current_stock + num);
								productService.updateProduct(p);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "商品下架成功！");
						}
					}else if(num>current_put_on_stock){
						JOptionPane.showMessageDialog(null, "输入数据不能超出商品当前已上架数目！");
					}else if(num==0){
						JOptionPane.showMessageDialog(null, "输入数据不能为0！");
					}
				}

			}
		} else if (e.getSource() == tool_warn) {
			// 上架预警：当已上架的商品少于仓库库存的20%则进行提示（显示该商品数据）
			// 获取所有上架的商品信息
			List<Product> list = null;
			try {
				list = productService.findAllProductList();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			// 二次筛选上架商品信息
			List<Product> put_on_list = this.getProductPutOn(list);
			// 进一步获取上架预警的商品信息
			List<Product> warn_list  = this.getProductWarn(put_on_list);
			
			// 先移除当前面板的所有数据
			tablePanel.removeAll();
			String[] params = { "商品id", "条形码", "商品名称", "成本", "售价", "已上架",
					"仓库库存", "安全库存", "单位id", "单位", "产地", "生产日期", "商品描述",
					"折扣", "促销标识", "促销状态", "促销价格", "删除标识", "分类id", "分类",
					"供应商id", "供应商", "仓库id", "仓库" };
			Vector<Vector> vec = new Vector<>();
			try {
				vec = productService.pack(warn_list);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			// 将查询到的数据封装到BbaseTableModule中
			baseTableModule = new BaseTableModule(params, vec);
			table = new JTable(baseTableModule);
			// 渲染第0列，将其显示为多选框进行显示
			table.getColumnModel().getColumn(0)
					.setCellRenderer(new TableCellRenderer() {
						@Override
						public Component getTableCellRendererComponent(
								JTable table, Object value,
								boolean isSelected, boolean hasFocus,
								int row, int column) {
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
			dcm.getColumn(6).setMinWidth(0);
			dcm.getColumn(6).setMaxWidth(0);
			dcm.getColumn(7).setMinWidth(0);
			dcm.getColumn(7).setMaxWidth(0);
			dcm.getColumn(8).setMinWidth(0);
			dcm.getColumn(8).setMaxWidth(0);
			dcm.getColumn(12).setMinWidth(0);
			dcm.getColumn(12).setMaxWidth(0);
			dcm.getColumn(13).setMinWidth(0);
			dcm.getColumn(13).setMaxWidth(0);
			dcm.getColumn(14).setMinWidth(0);
			dcm.getColumn(14).setMaxWidth(0);
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
		} else if (e.getSource() == tool_export) {
			// 获取当前选中的数据
			String[] ids;
			ArrayList id_list = new ArrayList<>();
			for (int rowindex : table.getSelectedRows()) {
				Object obj = table.getValueAt(rowindex, 0);
				id_list.add(obj);
			}
			// 集合转数组
			ids = (String[]) id_list.toArray(new String[id_list.size()]);
			int result = productService.exportData(ids);
			if (result == 1) {
				JOptionPane.showMessageDialog(null, "数据导出成功！");
			} else if (result == -1) {
				JOptionPane.showMessageDialog(null, "抱歉！数据导出失败，再试一遍吧！");
			} else if (result == 0) {
				JOptionPane.showMessageDialog(null, "用户取消了操作！");
			}
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
			if (keyword.getText().equals("关键字")) {
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
				keyword.setText("关键字");
				keyword.setFont(MyFont.TipFont);
				keyword.setForeground(MyColor.TipColor);
			}
		}
	}

	/**
	 * 通过传入经过条件筛选后的商品进行二次过滤 筛选上架商品（上架库存大于0的商品认为是上架） 商品上架是将商品从仓库中放入超市
	 */
	public List<Product> getProductPutOn(List<Product> list) {
		List<Product> put_on_list = new ArrayList<Product>();
		for (Product p : list) {
			if (p.getPutaway_stock() > 0) {
				put_on_list.add(p);
			}
		}
		return put_on_list;
	}

	/**
	 * 通过传入经过条件筛选后的商品进行二次过滤 筛选下架商品（上架库存=0的商品认为是下架） 商品下架是直接将商品部分或全部退回仓库中
	 */
	public List<Product> getProductPullOff(List<Product> list) {
		List<Product> put_off_list = new ArrayList<Product>();
		for (Product p : list) {
			if (p.getPutaway_stock() == 0) {
				put_off_list.add(p);
			}
		}
		return put_off_list;
	}
	
	/**
	 * 当上架商品少于仓库库存的20%则显示商品数据进行提醒
	 */
	public List<Product> getProductWarn(List<Product> list){
		List<Product> warn_list = new ArrayList<Product>();
		for (Product p : list) {
			if (p.getPutaway_stock()<Integer.valueOf((int) (p.getCurrent_stock()*0.2))) {
				warn_list.add(p);
			}
		}
		return warn_list;
	}

}
