package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.fore.ui.control.ProductSaleJPanel;
import com.design.sm.model.Accounts;
import com.design.sm.model.ConsumeClass;
import com.design.sm.model.Customer;
import com.design.sm.model.Employee;
import com.design.sm.model.Product;
import com.design.sm.model.SaleTemp;
import com.design.sm.service.ConsumeClassService;
import com.design.sm.service.CustomerService;
import com.design.sm.service.EmployeeService;
import com.design.sm.service.ProductService;
import com.design.sm.service.SaleTempService;
import com.design.sm.service.impl.ConsumeClassServiceImpl;
import com.design.sm.service.impl.CustomerServiceImpl;
import com.design.sm.service.impl.EmployeeServiceImpl;
import com.design.sm.service.impl.ProductServiceImpl;
import com.design.sm.service.impl.SaleTempServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.DataValidation;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.Tools;

public class CashierJFrame extends JFrame implements MouseListener,
		DocumentListener {

	// 定义全局组件
	JPanel backgroundPanel, topPanel, toolPanel, centerPanel, tempTablePanel,
			searchPanel, downPanel, recordPanel, buttonPanel;
	// 借助工具类完成表格数据的封装
	BaseTableModule tempTableModule, searchTableModule;
	// 定义临时记录表格、商品查找表格
	JTable tempTable, searchTable;
	JScrollPane tempJScrollPane, searchJScrollPane;
	// 定义topPanel使用到的标签
	JLabel label_unit_price, label_quantity, label_subtotal, label_total,
			unit_price, quantity, subtotal, total;
	// 定义recordPanel使用到的标签
	JLabel label_time, label_cashier_num, label_cashier, label_customer_sign,
			label_customer, label_consume_class, label_discount, label_off,
			label_concession;
	// 创建工具条使用到的标签
	JLabel tool_add, tool_sub, tool_delete, tool_truncate,barcode;

	// 定义条形码文本框(可以输入条形码或者是商品名称)
	JTextField keyword;
	// 定义按钮工具
	// 备用金、会员、重打、结算
	JButton petty_cash, vip, reset, balance;
	// 定义会员
	double petty_cash_double=0.00;
	Customer customer = null ;

	// 定义相应的service
	// AccountsService accountsService = new AccountsServiceImpl();
	ProductService productService = new ProductServiceImpl();
	SaleTempService saleTempService = new SaleTempServiceImpl();
	EmployeeService employeeService = new EmployeeServiceImpl();
	CustomerService customerService = new CustomerServiceImpl();
	ConsumeClassService consumeClassService = new ConsumeClassServiceImpl();
	
	Accounts loginUser;
	ProductSaleJPanel parentPanel;

	/**
	 * 通过构造方法完成初始化
	 */
	public CashierJFrame(ProductSaleJPanel parentPanel, Accounts loginUser) {
		backgroundPanel = new JPanel(new BorderLayout());
		this.parentPanel = parentPanel;
		this.loginUser = loginUser;
		// 初始化布局
		initTopPanel();// 初始化顶部菜单条、工具条
		initCenterPanel();// 初始化中间的数据面板
		initDownPanel();// 初始化下方的记录数据面板和按钮组合
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("收银台");
		this.pack();
		this.setResizable(false);// 不可最大化
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

		label_unit_price = new JLabel("单价");
		label_unit_price.setForeground(MyColor.TipColor);
		unit_price = new JLabel("0.00");
		unit_price.setForeground(MyColor.JTextFieldColor);
		unit_price.setFont(MyFont.JTableFont);

		label_quantity = new JLabel("数量");
		label_quantity.setForeground(MyColor.TipColor);
		quantity = new JLabel("0");
		quantity.setForeground(MyColor.JTextFieldColor);
		quantity.setFont(MyFont.JTableFont);

		label_subtotal = new JLabel("小计");
		label_subtotal.setForeground(MyColor.TipColor);
		subtotal = new JLabel("0.00");
		subtotal.setForeground(MyColor.JTextFieldColor);
		subtotal.setFont(MyFont.JTableFont);

		label_total = new JLabel("总计");
		label_total.setForeground(MyColor.TipColor);
		total = new JLabel("0.00");
		total.setForeground(MyColor.JTextFieldColor);
		total.setFont(MyFont.JTableFont);

		Box hor1 = Box.createHorizontalBox();
		hor1.add(Box.createHorizontalStrut(100));
		hor1.add(label_unit_price);
		hor1.add(Box.createHorizontalStrut(100));
		hor1.add(label_quantity);
		hor1.add(Box.createHorizontalStrut(100));
		hor1.add(label_subtotal);
		hor1.add(Box.createHorizontalStrut(100));
		hor1.add(label_total);

		Box hor2 = Box.createHorizontalBox();
		hor2.add(Box.createHorizontalStrut(100));
		hor2.add(unit_price);
		hor2.add(Box.createHorizontalStrut(100));
		hor2.add(quantity);
		hor2.add(Box.createHorizontalStrut(100));
		hor2.add(subtotal);
		hor2.add(Box.createHorizontalStrut(100));
		hor2.add(total);

		toolPanel = new JPanel(new BorderLayout());
		// 添加增删改工具
		Icon icon_add = new ImageIcon("icons/toolImage/increase.png");
		tool_add = new JLabel(icon_add);
		tool_add.setToolTipText("数量+1");// 设置鼠标移动时的显示内容
		tool_add.addMouseListener(this);// 添加鼠标监听

		Icon icon_sub = new ImageIcon("icons/toolImage/sub.png");
		tool_sub = new JLabel(icon_sub);
		tool_sub.setToolTipText("数量-1");// 设置鼠标移动时的显示内容
		tool_sub.addMouseListener(this);// 添加鼠标监听

		Icon icon_slash = new ImageIcon("icons/toolImage/slash.png");
		tool_delete = new JLabel(icon_slash);
		tool_delete.setToolTipText("删除记录");// 设置鼠标移动时的显示内容
		tool_delete.addMouseListener(this);// 添加鼠标监听

		Icon icon_truncate = new ImageIcon("icons/toolImage/truncate.png");
		tool_truncate = new JLabel(icon_truncate);
		tool_truncate.setToolTipText("清空记录");// 设置鼠标移动时的显示内容
		tool_truncate.addMouseListener(this);// 添加鼠标监听

		// 将初始化完成的工具加载到工具条面板中
		JPanel west = new JPanel();
		west.add(tool_add);
		west.add(tool_sub);
		west.add(tool_delete);
		west.add(tool_truncate);
		JPanel east = new JPanel();
		barcode = new JLabel();
		Icon barcode_icon = new ImageIcon("icons/toolImage/barcode.png");
		barcode.setIcon(barcode_icon);
		keyword = new JTextField(20);
		keyword.setFont(MyFont.TipFont);
		keyword.setForeground(MyColor.TipColor);
		keyword.setText("条形码|");
		keyword.setPreferredSize(new Dimension(150,30));
		keyword.addMouseListener(this);
		// 文本框设置实时数据监控获取商品信息
		keyword.getDocument().addDocumentListener(this);
		east.add(barcode);
		east.add(keyword);

		toolPanel.add(west, BorderLayout.WEST);
		toolPanel.add(east, BorderLayout.EAST);

		topPanel.add(hor1, BorderLayout.NORTH);
		topPanel.add(hor2, BorderLayout.CENTER);
		topPanel.add(toolPanel, BorderLayout.SOUTH);

		// 将顶部面板加载到背景面板中
		backgroundPanel.add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * 初始化中间的数据面板
	 */
	private void initCenterPanel() {
		centerPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		// 初始化临时数据表（销售商品录入记录）
		initTempTablePanel();
		// 初始化商品查找表
		initSearchPanel();
		backgroundPanel.add(centerPanel, BorderLayout.CENTER);

	}

	/**
	 * 初始化下方的数据面板
	 */
	private void initDownPanel() {
		downPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		// 初始化左下方的数据显示记录表
		initRecordPanel();
		// 初始化按钮组合
		initButtonPanel();
		backgroundPanel.add(downPanel, BorderLayout.SOUTH);
	}

	/**
	 * 初始化临时商品销售记录表
	 */
	private void initTempTablePanel() {
		tempTablePanel = new JPanel();
		String[] params = { "产品id", "产品名称", "数量", "售价", "小计", "供应商id", "供应商" };
		Vector<Vector> vec = new Vector<>();
		try {
			vec = saleTempService.pack(saleTempService.findAllSaleTempList());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 将查询到的数据封装到BbaseTableModule中
		tempTableModule = new BaseTableModule(params, vec);
		tempTable = new JTable(tempTableModule);
		// 渲染第0列，将其显示为多选框进行显示
		tempTable.getColumnModel().getColumn(0)
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
		Tools.setTableStyle(tempTable);
		// 通过设置行的大小，隐藏某一列内容（只显示想要显示的数据）
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) tempTable
				.getColumnModel();
		// 隐藏：5
		dcm.getColumn(5).setMinWidth(0);
		dcm.getColumn(5).setMaxWidth(0);
		dcm.getColumn(6).setMinWidth(0);
		dcm.getColumn(6).setMaxWidth(0);
		// 设置滚动条
		tempJScrollPane = new JScrollPane(tempTable);
		Tools.setJspStyle(tempJScrollPane);

		tempTablePanel.setOpaque(false);// 设置透明度
		tempTablePanel.add(tempJScrollPane);
		centerPanel.add(tempTablePanel, BorderLayout.WEST);
		// 将组件加载到背景中
		backgroundPanel.validate();
	}

	/**
	 * 初始化查找面板 设置查找方式： 商品条形码结合商品关键字查找
	 */
	private void initSearchPanel() {
		searchPanel = new JPanel();
		// 要根据下拉框的选项进行筛选数据(要根据productService中返回的行进行设置，随后选择要隐藏的项目即可)
		String[] params = { "商品id", "条形码", "商品名称", "成本", "售价", "已上架", "仓库库存",
				"安全库存", "单位id", "单位", "产地", "生产日期", "商品描述", "折扣", "促销标识",
				"促销状态", "促销价格", "删除标识", "分类id", "分类", "供应商id", "供应商", "仓库id",
				"仓库" };
		Vector<Vector> vec = new Vector<>();
		if (keyword.getText().equals("条形码|")) {
			// 通过关键字搜索商品信息，获取满足条件的所有商品列表
			try {
				// 查找所有的商品信息
				List<Product> prod_list = productService.findAllProductList();
				// 二次筛选查找满足条件的已上架的该商品信息
				vec = productService.pack(getProductPutOn(prod_list));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else {
			String text = "%" + keyword.getText() + "%";
			// 通过关键字搜索商品信息，获取满足条件的所有商品列表
			try {
				List<Product> prod_list = productService
						.getProductByFlowIdUnionName(text);
				// 二次筛选查找满足条件的已上架的该商品信息
				vec = productService.pack(getProductPutOn(prod_list));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		// 将查询到的数据封装到BbaseTableModule中
		searchTableModule = new BaseTableModule(params, vec);
		searchTable = new JTable(searchTableModule);
		// 渲染第0列，将其显示为多选框进行显示
		searchTable.getColumnModel().getColumn(0)
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
		Tools.setTableStyle(searchTable);
		// 为表格添加鼠标监听
		searchTable.addMouseListener(this);
		// 通过设置行的大小，隐藏某一列内容（只显示想要显示的数据）
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) searchTable
				.getColumnModel();
		// 隐藏：0 1 6 7 8 12 13 14 16 17 18 19 21
		dcm.getColumn(0).setMinWidth(0);
		dcm.getColumn(0).setMaxWidth(0);
		dcm.getColumn(3).setMinWidth(0);
		dcm.getColumn(3).setMaxWidth(0);
		dcm.getColumn(5).setMinWidth(0);
		dcm.getColumn(5).setMaxWidth(0);
		dcm.getColumn(6).setMinWidth(0);
		dcm.getColumn(6).setMaxWidth(0);
		dcm.getColumn(7).setMinWidth(0);
		dcm.getColumn(7).setMaxWidth(0);
		dcm.getColumn(8).setMinWidth(0);
		dcm.getColumn(8).setMaxWidth(0);
		dcm.getColumn(9).setMinWidth(0);
		dcm.getColumn(9).setMaxWidth(0);
		dcm.getColumn(10).setMinWidth(0);
		dcm.getColumn(10).setMaxWidth(0);
		dcm.getColumn(11).setMinWidth(0);
		dcm.getColumn(11).setMaxWidth(0);
		dcm.getColumn(12).setMinWidth(0);
		dcm.getColumn(12).setMaxWidth(0);
		dcm.getColumn(13).setMinWidth(0);
		dcm.getColumn(13).setMaxWidth(0);
		dcm.getColumn(14).setMinWidth(0);
		dcm.getColumn(14).setMaxWidth(0);
		dcm.getColumn(17).setMinWidth(0);
		dcm.getColumn(17).setMaxWidth(0);
		dcm.getColumn(18).setMinWidth(0);
		dcm.getColumn(18).setMaxWidth(0);
		dcm.getColumn(19).setMinWidth(0);
		dcm.getColumn(19).setMaxWidth(0);
		dcm.getColumn(20).setMinWidth(0);
		dcm.getColumn(20).setMaxWidth(0);
		dcm.getColumn(21).setMinWidth(0);
		dcm.getColumn(21).setMaxWidth(0);
		dcm.getColumn(22).setMinWidth(0);
		dcm.getColumn(22).setMaxWidth(0);
		dcm.getColumn(23).setMinWidth(0);
		dcm.getColumn(23).setMaxWidth(0);

		// 设置滚动条
		searchJScrollPane = new JScrollPane(searchTable);
		Tools.setJspStyle(searchJScrollPane);

		searchPanel.setOpaque(false);// 设置透明度
		searchPanel.add(searchJScrollPane);
		// 在刷新数据的时候改变窗体大小，完成数据刷新
		centerPanel.add(searchPanel, BorderLayout.EAST);
		backgroundPanel.validate();

	}

	/**
	 * 初始化信息记录表
	 */
	private void initRecordPanel() {
		recordPanel = new JPanel(new GridLayout(1, 3));

		JPanel jp1 = new JPanel(new GridLayout(3, 1));
		// 获取当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		label_time = new JLabel("时间:"+df.format(new Date()));
		Employee findemp = null;
		try {
			findemp = employeeService.getEmployeeById(employeeService.getEmployeeIdByAccountId(this.loginUser.getAccount_id()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		label_cashier_num = new JLabel("工号:"+findemp.getEmployee_num());
		label_cashier = new JLabel("收银员:"+findemp.getEmployee_name());
		jp1.add(label_time);
		jp1.add(label_cashier_num);
		jp1.add(label_cashier);

		JPanel jp2 = new JPanel(new GridLayout(3, 1));
		label_customer_sign = new JLabel("会员标识:非会员");
		label_customer = new JLabel("会员名称");
		label_consume_class = new JLabel("会员等级");
		jp2.add(label_customer_sign);
		jp2.add(label_customer);
		jp2.add(label_consume_class);

		JPanel jp3 = new JPanel(new GridLayout(3, 1));
		label_discount = new JLabel("折扣");
		label_off = new JLabel("优惠");
		label_concession = new JLabel("优惠合计");
		jp3.add(label_discount);
		jp3.add(label_off);
		jp3.add(label_concession);

		recordPanel.add(jp1);
		recordPanel.add(jp2);
		recordPanel.add(jp3);

		downPanel.add(recordPanel, BorderLayout.WEST);
	}

	/**
	 * 初始化按钮面板
	 */
	private void initButtonPanel() {
		buttonPanel = new JPanel();
		// 添加按钮工具
		petty_cash = new JButton("备用金");
		petty_cash.setSize(75, 30);
		petty_cash.addMouseListener(this);// 添加鼠标监听
		petty_cash.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));

		vip = new JButton("会员");
		vip.setSize(75, 30);
		vip.addMouseListener(this);// 添加鼠标监听
		vip.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));

		reset = new JButton("重打");
		reset.setSize(75, 30);
		reset.addMouseListener(this);// 添加鼠标监听
		reset.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));

		balance = new JButton("结算");
		balance.setSize(75, 30);
		balance.addMouseListener(this);// 添加鼠标监听
		balance.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.red));

		// 将初始化完成的工具加载到工具条面板中
		buttonPanel.add(petty_cash);
		buttonPanel.add(vip);
		buttonPanel.add(reset);
		buttonPanel.add(balance);
		// 最终将工具条面板加载到顶部菜单条的最西面
		downPanel.add(buttonPanel, BorderLayout.EAST);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == searchTable) {
			try {
				// 如果双击了表格数据则将指定商品添加到临时记录表（清单）
				if (e.getClickCount() == 2) {
					// 根据选中的表格数据获取商品的信息
					int row = searchTable.getSelectedRow();
					if (row >= 0) {
						/**
						 * 需要判断当前商品是否为促销状态，或者打折状态， 正常销售按照售价*相应的折扣进行出售
						 * 如果为促销状态则按照促销的价格进行出售
						 * 在添加商品的时候需要判断当前的商品已存在在临时清单中，如果存在则只需要修改相应的
						 * 数量和总价，如果不存在则根据当前商品的出售状态添加商品信息
						 * 此外需要注意的是必须是从已上架的商品中查找商品信息 且购买的商品数目不能够超过商品的已上架库存
						 */
						String prod_id = searchTable.getValueAt(row, 0)
								.toString();
						double unit_price = Double.valueOf(searchTable
								.getValueAt(row, 4).toString());
						int putaway_stock = Integer.valueOf(searchTable
								.getValueAt(row, 5).toString());
						double prod_discount = Double.valueOf(searchTable
								.getValueAt(row, 13).toString());
						int promotion_flag = Integer.valueOf(searchTable
								.getValueAt(row, 14).toString());
						double promotion_price = Double.valueOf(searchTable
								.getValueAt(row, 16).toString());
						double sale_price = 0.00;
						if (promotion_flag == 0) {
							// 正常销售 会乘以相应的折扣作为最终的售价
							sale_price = ((int) (unit_price * prod_discount * 100)) / 100;
						} else if (promotion_flag == 1) {
							// 促销则按照促销价格进行销售
							sale_price = promotion_price;
						}
						// 判断当前临时表中是否存在该商品信息，如果存在则只是修改数量，如果不存在则添加商品并刷新面板
						SaleTemp st = saleTempService
								.getSaleTempByProductId(prod_id);
						if (st != null) {
							// 判断当前的商品数目加1之后是否超出已上架数目，如果超出则做出相应的提示
							if ((st.getQuantity() + 1) > putaway_stock) {
								JOptionPane.showMessageDialog(null,
										"当前加购数目超出商品已上架库存，错误操作！");
							} else {
								// 如果查找的商品不为空，则只需要相应的修改数目（数目加1）、总额即可
								st.setQuantity(st.getQuantity() + 1);
								double amount = ((int) (st.getQuantity()
										* sale_price * 100)) / 100;
								st.setAmount(amount);
								saleTempService.update(st);
							}
						} else {
							// 如果查找的商品为空，则添加根据当前商品的状态添加商品信息
							SaleTemp st1 = new SaleTemp(prod_id, 1, sale_price,
									sale_price);
							saleTempService.addSaleTemp(st1);
						}
						// 上方的面板数据刷新显示：单价、数量、小计、总计
						this.refreshTopPanel(sale_price);
						// 刷新数据信息
						this.refreshCenterPanel();
						this.refreshDownPanel();
					}
				}
			} catch (NumberFormatException | HeadlessException | SQLException e1) {
				e1.printStackTrace();
			}
		}else if(e.getSource()==tool_add){
			try {
				int row = tempTable.getSelectedRow();
				if(row>=0){
					SaleTemp findst = saleTempService.getSaleTempByProductId(tempTable.getValueAt(row, 0).toString());
					Product findprod = productService.getProduct(tempTable.getValueAt(row, 0).toString());
					if(findst.getQuantity()+1>findprod.getPutaway_stock()){
						// 加购数目超出当前商品上架库存，则进行出错提示
						JOptionPane.showMessageDialog(null, "当前加购数目超出商品已上架库存，错误操作！");
					}else{
						// 如果查找的商品不为空，则只需要相应的修改数目（数目加1）、总额即可
						findst.setQuantity(findst.getQuantity() + 1);
						double amount = ((int) (findst.getQuantity()
								* findst.getUnit_price() * 100)) / 100;
						findst.setAmount(amount);
						saleTempService.update(findst);
					}
					// 刷新数据信息
					this.refreshCenterPanel();
					this.refreshTopPanel(0);
				}
			} catch (HeadlessException | SQLException e1) {
				e1.printStackTrace();
			}
		}else if(e.getSource()==tool_sub){
			try {
				int row = tempTable.getSelectedRow();
				if(row>=0){
					SaleTemp findst = saleTempService.getSaleTempByProductId(tempTable.getValueAt(row, 0).toString());
					Product findprod = productService.getProduct(tempTable.getValueAt(row, 0).toString());
					if(findst.getQuantity()-1<=0){
						findst.setQuantity(1);
						double amount = ((int) (findst.getQuantity()
								* findst.getUnit_price() * 100)) / 100;
						findst.setAmount(amount);
						saleTempService.update(findst);
					}else{
						findst.setQuantity(findst.getQuantity() - 1);
						double amount = ((int) (findst.getQuantity()
								* findst.getUnit_price() * 100)) / 100;
						findst.setAmount(amount);
						saleTempService.update(findst);
					}
					// 刷新数据信息
					this.refreshCenterPanel();
					this.refreshTopPanel(0);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}else if(e.getSource()==tool_delete){
			try {
				int row = tempTable.getSelectedRow();
				if(row>=0){
					int choose = JOptionPane.showConfirmDialog(null, "确认移除该记录？");
					if(choose==0){
						saleTempService.deleteSaleTemp(tempTable.getValueAt(row, 0).toString());
						JOptionPane.showMessageDialog(null, "商品记录已移除！");
						// 刷新面板数据
						this.refreshCenterPanel();
						if(tempTable.getRowCount()==0){
							backgroundPanel.remove(topPanel);
							initTopPanel();
							backgroundPanel.validate();
						}else{
							// 避免空指针异常
							this.refreshTopPanel(0);
						}
					}
				}
			} catch (HeadlessException | SQLException e1) {
				e1.printStackTrace();
			}
		}else if(e.getSource()==tool_truncate){
			try {
				int row = tempTable.getSelectedRow();
				if(row>=0){
					int choose = JOptionPane.showConfirmDialog(null, "确认移除所有商品记录？");
					if(choose==0){
						saleTempService.truncateAllSaleTemp();
						JOptionPane.showMessageDialog(null, "所有商品记录已移除！");
						// 刷新面板数据
						this.refreshCenterPanel();
						backgroundPanel.remove(topPanel);
						initTopPanel();
						backgroundPanel.validate();
					}
				}
			} catch (HeadlessException | SQLException e1) {
				e1.printStackTrace();
			}
		}else if(e.getSource()==petty_cash){
			// 输入备用金
			String cash = JOptionPane.showInputDialog(null, "请输入备用金");
			if(cash!=null){
				if(DataValidation.isBigDecimal(cash)){
					petty_cash_double = Double.valueOf(cash);
				}else{
					JOptionPane.showMessageDialog(null, "输入格式有误，请输入正确的数字，可保留两位小数！");
				}
			}
		}else if(e.getSource()==vip){
			try {
				// 根据输入的用户信息查找相应的客户（根据客户联系方式进行查找）
				String phone = JOptionPane.showInputDialog(null, "请输入顾客手机号！");
				if(phone!=null){
					Customer findcust = customerService.getCustomerByPhone(phone);
					if(findcust==null){
						JOptionPane.showMessageDialog(null, "抱歉，没有找到相应的顾客信息，请确认后再次输入！");
					}else{
						customer = findcust;
						// 刷新顾客信息
						this.refreshDownPanel();
					}
				}
			} catch (HeadlessException | SQLException e1) {
				e1.printStackTrace();
			}
		}else if(e.getSource()==reset){
			keyword.setFont(MyFont.TipFont);
			keyword.setForeground(MyColor.TipColor);
			keyword.setText("条形码|");
		}else if(e.getSource()==balance){
			// 进入结算确认界面
			new EnsureSaleOrderJFrame(this,this.loginUser,this.customer);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("条形码|")) {
				keyword.setText("");
				keyword.setFont(MyFont.JTextFieldFont);
				keyword.setForeground(MyColor.JTextFieldColor);
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("")) {
				keyword.setText("条形码|");
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

	@Override
	public void insertUpdate(DocumentEvent e) {
		// 刷新表格数据面板
		this.refreshCenterPanel();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {

	}

	@Override
	public void changedUpdate(DocumentEvent e) {

	}

	// 刷新上方的数据面板
	public void refreshTopPanel(double sale_price) {
		try {
			unit_price.setText(sale_price + "");
			quantity.setText("1.0");
			double subamount = ((int) sale_price * 1 * 100) / 100;
			subtotal.setText(subamount + "");
			// 如果当前列表的商品信息不为空，则获取当前列表的所有商品的总额(处理空指针异常)
			List<SaleTemp> st_list = saleTempService.findAllSaleTempList();
			double allamount = 0.00;
			if(st_list!=null){
				allamount = saleTempService.getAllAmount();
			}
			total.setText(allamount+"");
			// 刷新数据面板
			backgroundPanel.validate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 定义刷新中间的表格的数据面板数据
	public void refreshCenterPanel() {
		backgroundPanel.remove(centerPanel);
		initCenterPanel();
		backgroundPanel.validate();
	}
	
	// 刷新下方的数据记录面板
	public void refreshDownPanel() {
		try {
			if(customer!=null){
				// 说明是会员采购，则根据输入的信息进行数据显示
				label_customer_sign.setText("会员标识：会员");
				label_customer.setText("会员名称："+customer.getCustomer_name());
				ConsumeClass cc = consumeClassService.getConsumeClassById(customer.getClass_id());
				label_consume_class.setText("会员等级："+cc.getClass_name());
				label_discount.setText("折扣："+cc.getClass_discount());
				label_off.setText("优惠："+cc.getClass_off());
				/**
				 * 自定义规则：
				 * 如果是会员采购则按照相应的条件进行优惠
				 * 1.每次购物能够以相应的折扣进行采购
				 * 2.每次购物消费经折后满1000以上能够享受相应的满减优惠
				 */
				double totalAmount = Double.valueOf(total.getText());
				// 获取打折优惠的价格
				double discountAmount = ((int)(totalAmount*cc.getClass_discount()*100))/100;
				double concession = discountAmount;
				if((totalAmount-discountAmount)>1000){
					concession += cc.getClass_off();
				}
				label_concession.setText("优惠合计："+concession);
			}
			backgroundPanel.validate();
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 刷新整个背景面板
	public void refreshBackgroundPanel(){
		backgroundPanel.removeAll();
		initTopPanel();
		initCenterPanel();
		initDownPanel();
		backgroundPanel.validate();
	}
}
