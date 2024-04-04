package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.fore.ui.control.BaseDataJPanel;
import com.design.sm.model.Accounts;
import com.design.sm.model.Category;
import com.design.sm.model.Product;
import com.design.sm.model.Units;
import com.design.sm.model.Vendor;
import com.design.sm.model.Warehouse;
import com.design.sm.service.CategoryService;
import com.design.sm.service.ProductService;
import com.design.sm.service.UnitsService;
import com.design.sm.service.VendorService;
import com.design.sm.service.WarehouseService;
import com.design.sm.service.impl.CategoryServiceImpl;
import com.design.sm.service.impl.ProductServiceImpl;
import com.design.sm.service.impl.UnitsServiceImpl;
import com.design.sm.service.impl.VendorServiceImpl;
import com.design.sm.service.impl.WarehouseServiceImpl;
import com.design.sm.utils.DataValidation;
import com.design.sm.utils.Item;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.RandomGeneration;
import com.eltima.components.ui.DatePicker;

public class AddProductJFrame extends JFrame implements FocusListener,
		MouseListener {

	// 定义全局组件
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_prod_name, label_prod_cost, label_prod_price,
			label_putaway_stock, label_current_stock, label_safe_stock,
			label_prod_unit, label_prod_origin, label_prod_date,
			label_prod_descr, label_prod_discount, label_promotion_flag,
			label_promotion_price, label_category, label_vendor,
			label_warehouse;
	JTextField prod_name, prod_cost, prod_price, putaway_stock, current_stock,
			safe_stock, prod_origin, prod_discount, promotion_price;
	JTextArea prod_descr;
	JComboBox prod_unit, promotion_flag, category, vendor, warehouse;
	DatePicker prod_date;
	JButton save, reset;
	// 得到屏幕大小
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// 定义service
	UnitsService unitsService = new UnitsServiceImpl();
	CategoryService categoryService = new CategoryServiceImpl();
	VendorService vendorService = new VendorServiceImpl();
	WarehouseService warehouseService = new WarehouseServiceImpl();
	ProductService productService = new ProductServiceImpl();

	// 定义父对象、当前登录员工、表格、选中行
	Accounts loginUser;
	BaseDataJPanel parentPanel;

	// 通过构造方法初始化数据
	public AddProductJFrame(BaseDataJPanel parentPanel, Accounts loginUser) 
	{
		this.parentPanel = parentPanel;
		this.loginUser = loginUser;
		// 初始化背景
		initBackgroundPanel();
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("添加商品信息");
		this.setSize(600, 600);
		this.setVisible(true);
		this.setLocationRelativeTo(null);// 设置依赖项
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);// 设置关闭方式
		// 当前窗口隐藏，不影响后方数据的使用，而不是关闭整个窗口
	}

	private void initBackgroundPanel() {
		backgroundPanel = new JPanel(new BorderLayout());
		initTitlePanel();
		initContentPanel();
		initButtonPanel();
		backgroundPanel.add(titlePanel, BorderLayout.NORTH);
		backgroundPanel.add(contentPanel, BorderLayout.CENTER);
		backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * 初始化标题面板
	 */
	private void initTitlePanel() {
		titlePanel = new JPanel();
		JLabel title = new JLabel("添加商品信息");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * 初始化内容面板
	 */
	private void initContentPanel() {

		contentPanel = new JPanel();

		JPanel jp1 = new JPanel();
		label_prod_name = new JLabel("商品名称");
		prod_name = new JTextField(15);
		prod_name.setFont(MyFont.TipFont);
		prod_name.setForeground(MyColor.TipColor);
		prod_name.setText("必填项");
		prod_name.addFocusListener(this);
		label_prod_cost = new JLabel("商品成本");
		prod_cost = new JTextField(15);
		prod_cost.setFont(MyFont.TipFont);
		prod_cost.setForeground(MyColor.TipColor);
		prod_cost.setText("可保留两位小数");
		prod_cost.addFocusListener(this);
		jp1.add(label_prod_name);
		jp1.add(prod_name);
		jp1.add(label_prod_cost);
		jp1.add(prod_cost);

		JPanel jp2 = new JPanel();
		label_prod_price = new JLabel("出售价格");
		prod_price = new JTextField(15);
		prod_price.setFont(MyFont.TipFont);
		prod_price.setForeground(MyColor.TipColor);
		prod_price.setText("可保留两位小数");
		prod_price.addFocusListener(this);
		label_putaway_stock = new JLabel("上架库存");
		putaway_stock = new JTextField(15);
		putaway_stock.setFont(MyFont.TipFont);
		putaway_stock.setForeground(MyColor.TipColor);
		putaway_stock.setText("请输入整数");
		putaway_stock.addFocusListener(this);
		jp2.add(label_prod_price);
		jp2.add(prod_price);
		jp2.add(label_putaway_stock);
		jp2.add(putaway_stock);

		JPanel jp3 = new JPanel();
		label_current_stock = new JLabel("仓库库存");
		current_stock = new JTextField(15);
		current_stock.setFont(MyFont.TipFont);
		current_stock.setForeground(MyColor.TipColor);
		current_stock.setText("请输入整数");
		current_stock.addFocusListener(this);
		label_safe_stock = new JLabel("安全库存");
		safe_stock = new JTextField(15);
		safe_stock.setFont(MyFont.TipFont);
		safe_stock.setForeground(MyColor.TipColor);
		safe_stock.setText("请输入整数");
		safe_stock.addFocusListener(this);
		jp3.add(label_current_stock);
		jp3.add(current_stock);
		jp3.add(label_safe_stock);
		jp3.add(safe_stock);

		JPanel jp4 = new JPanel();
		label_prod_unit = new JLabel("商品单位");
		prod_unit = new JComboBox();
		prod_unit.setPreferredSize(new Dimension(175, 30));
		// 装载单位信息
		List<Units> units_list = null;
		try {
			units_list = unitsService.findAllUnitsList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Units u : units_list) {
			String units_id = u.getUnits_id();
			String units_name = u.getUnits_name();
			Item item = new Item(units_id, units_name);
			prod_unit.addItem(item);
		}
		label_prod_origin = new JLabel("商品产地");
		prod_origin = new JTextField(15);
		prod_origin.setFont(MyFont.TipFont);
		prod_origin.setForeground(MyColor.TipColor);
		prod_origin.setText("必填项");
		prod_origin.addFocusListener(this);
		jp4.add(label_prod_unit);
		jp4.add(prod_unit);
		jp4.add(label_prod_origin);
		jp4.add(prod_origin);

		JPanel jp5 = new JPanel();
		label_prod_date = new JLabel("生产日期");
		String DefaultFormat = "yyyy-MM-dd HH:mm:ss";
		// 当前时间
		prod_date = new DatePicker(new Date(), DefaultFormat,
				MyFont.JTextFieldFont, new Dimension(177, 24));
		prod_date.setSize(new Dimension(180, 50));
		label_prod_discount = new JLabel("商品折扣");
		prod_discount = new JTextField(15);
		prod_discount.setFont(MyFont.JTextFieldFont);
		prod_discount.setForeground(MyColor.TipColor);
		prod_discount.setText("可保留两位小数");
		prod_discount.addFocusListener(this);
		jp5.add(label_prod_date);
		jp5.add(prod_date);
		jp5.add(label_prod_discount);
		jp5.add(prod_discount);

		JPanel jp6 = new JPanel();
		label_promotion_flag = new JLabel("促销状态");
		promotion_flag = new JComboBox();
		promotion_flag.setPreferredSize(new Dimension(175, 30));
		promotion_flag.addItem("标价");
		promotion_flag.addItem("促销");
		label_promotion_price = new JLabel("促销价格");
		promotion_price = new JTextField(15);
		promotion_price.setFont(MyFont.TipFont);
		promotion_price.setForeground(MyColor.TipColor);
		promotion_price.setText("可保留两位小数");
		promotion_price.addFocusListener(this);
		jp6.add(label_promotion_flag);
		jp6.add(promotion_flag);
		jp6.add(label_promotion_price);
		jp6.add(promotion_price);

		JPanel jp7 = new JPanel();
		label_category = new JLabel("所属分类");
		category = new JComboBox();
		category.setPreferredSize(new Dimension(175, 30));
		// 加载分类信息
		List<Category> category_list = null;
		try {
			category_list = categoryService.findAllCategoryList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Category c : category_list) {
			String cid = c.getCategory_id();
			String cname = c.getCategory_name();
			Item item = new Item(cid, cname);
			category.addItem(item);
		}
		label_warehouse = new JLabel("所属仓库");
		warehouse = new JComboBox();
		warehouse.setPreferredSize(new Dimension(175, 30));
		// 加载仓库信息
		List<Warehouse> warehouse_list = null;
		try {
			warehouse_list = warehouseService
					.findAllWarehouseList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Warehouse w : warehouse_list) {
			String wid = w.getWarehouse_id();
			String wname = w.getWarehouse_name();
			Item item = new Item(wid, wname);
			warehouse.addItem(item);
		}
		jp7.add(label_category);
		jp7.add(category);
		jp7.add(label_warehouse);
		jp7.add(warehouse);

		JPanel jp8 = new JPanel();
		label_vendor = new JLabel("供应商  ");
		vendor = new JComboBox();
		vendor.setPreferredSize(new Dimension(250, 30));
		// 加载供应商信息
		List<Vendor> vendor_list = null;
		try {
			vendor_list = vendorService.findAllVendorList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Vendor v : vendor_list) {
			String vid = v.getVendor_id();
			String vname = v.getVendor_name();
			Item item = new Item(vid, vname);
			vendor.addItem(item);
		}
		jp8.add(label_vendor);
		jp8.add(vendor);

		JPanel jp9 = new JPanel();
		label_prod_descr = new JLabel("商品描述");
		prod_descr = new JTextArea(5, 30);
		prod_descr.setFont(MyFont.TipFont);
		prod_descr.setForeground(MyColor.TipColor);
		prod_descr.setBackground(Color.white);
		prod_descr.setText("具体的商品描述，可选填");
		prod_descr.addFocusListener(this);
		jp9.add(label_prod_descr);
		jp9.add(prod_descr);

		Box ver = Box.createVerticalBox();
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp3);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp4);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp5);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp6);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp7);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp8);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp9);
		// 将组件加载到contentPanel面板中
		contentPanel.add(ver);
	}

	/**
	 * 初始化按钮面板
	 */
	private void initButtonPanel() {
		buttonPanel = new JPanel();
		save = new JButton("保存");
		save.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));
		save.setForeground(Color.white);
		save.setFont(MyFont.JButtonFont);
		save.addMouseListener(this);

		reset = new JButton("重置");
		reset.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));
		reset.setForeground(Color.white);
		reset.setFont(MyFont.JButtonFont);
		reset.addMouseListener(this);

		buttonPanel.add(save);
		buttonPanel.add(reset);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// 获取当前文本框的数据
		String prod_name_string = prod_name.getText();
		String prod_cost_string = prod_cost.getText();
		String prod_price_string = prod_price.getText();
		String putaway_stock_string = putaway_stock.getText();
		String current_stock_string = current_stock.getText();
		String safe_stock_string = safe_stock.getText();
		String prod_origin_string = prod_origin.getText();
		String prod_discount_string = prod_discount.getText();
		String promotion_price_string = promotion_price.getText();
		String prod_descr_string = prod_descr.getText();
		if (e.getSource() == save) {
			if(prod_name_string.equals("必填项")){
				JOptionPane.showMessageDialog(null, "商品名称不能为空！");
			}else if(prod_origin_string.equals("必填项")){
				JOptionPane.showMessageDialog(null, "商品来源不能为空！");
			}
			// 提供默认值  要注意逻辑判断
			if(prod_cost_string.equals("可保留两位小数")){
				prod_cost_string = "0.00";
			}
			if(prod_price_string.equals("可保留两位小数")){
				prod_price_string = "0.00";
			}
			if(putaway_stock_string.equals("请输入整数")){
				putaway_stock_string = "0";
			}
			if(current_stock_string.equals("请输入整数")){
				current_stock_string = "0";
			}
			if(safe_stock_string.equals("请输入整数")){
				safe_stock_string = "0";
			}
			if(prod_discount_string.equals("可保留两位小数")){
				prod_discount_string = "1.00";//商品折扣默认是1.00
			}
			if(promotion_price_string.equals("可保留两位小数")){
				promotion_price_string = "0.00";
			}
			if(prod_descr_string.equals("具体的商品描述，可选填")){
				prod_descr_string = "无具体商品描述";
			}
			double prod_cost_double,prod_price_double,prod_discount_double,promotion_price_double ;
			int putaway_stock_int,current_stock_int,safe_stock_int;
			// 对数据进行处理(验证、转化)
			if(!DataValidation.isBigDecimal(prod_cost_string)){
				JOptionPane.showMessageDialog(null, "商品成本输入数据格式有误！");
			}else if(!DataValidation.isBigDecimal(prod_price_string)){
				JOptionPane.showMessageDialog(null, "商品售价输入数据格式有误！");
			}else if(!DataValidation.isSmallDecimal(prod_discount_string)){
				JOptionPane.showMessageDialog(null, "商品折扣输入数据格式有误！");
			}else if(!DataValidation.isBigDecimal(promotion_price_string)){
				JOptionPane.showMessageDialog(null, "商品促销价格输入数据格式有误！");
			}else if(!DataValidation.isBigDecimal(putaway_stock_string)){
				JOptionPane.showMessageDialog(null, "商品上架库存输入数据格式有误！");
			}else if(!DataValidation.isBigDecimal(current_stock_string)){
				JOptionPane.showMessageDialog(null, "商品当前库存输入数据格式有误！");
			}else if(!DataValidation.isBigDecimal(safe_stock_string)){
				JOptionPane.showMessageDialog(null, "商品安全库存输入数据格式有误！");
			}else{
				prod_cost_double = Double.valueOf(prod_cost_string);
				prod_price_double = Double.valueOf(prod_price_string);
				prod_discount_double = Double.valueOf(prod_discount_string);
				promotion_price_double = Double.valueOf(promotion_price_string);
				putaway_stock_int = Integer.valueOf(putaway_stock_string);
				current_stock_int = Integer.valueOf(current_stock_string);
				safe_stock_int = Integer.valueOf(safe_stock_string);
				// 获取当前下拉框的数据信息
				Object promotion_state_item = promotion_flag.getSelectedItem();
				Item units_item = (Item)prod_unit.getSelectedItem();
				Item category_item = (Item)category.getSelectedItem();
				Item vendor_item = (Item)vendor.getSelectedItem();
				Item warehouse_item = (Item)warehouse.getSelectedItem();
				// 根据下拉框信息获取相应要封装的数据信息
				int promotion_flag_int = 0 ;
				if(String.valueOf(promotion_state_item).equals("标价")){
					promotion_flag_int=0;
				}else if(String.valueOf(promotion_state_item).equals("促销")){
					promotion_flag_int=1;
				}
				String units_id = units_item.getKey();
				String category_id = category_item.getKey();
				String vendor_id = vendor_item.getKey();
				String warehouse_id = warehouse_item.getKey();
				String prod_id = RandomGeneration.getRandom32charSeq();
				String flow_id = RandomGeneration.getRandom10numSeq();
				String prod_date_string = prod_date.getText();
				// 创建商品对象，加载数据进行保存
				Product prod = new Product(prod_id, flow_id, prod_name_string, prod_cost_double,
						prod_price_double, putaway_stock_int, current_stock_int, safe_stock_int, 
						units_id, prod_origin_string, prod_date_string, prod_descr_string, 
						prod_discount_double, promotion_flag_int, promotion_price_double, 0, 
						category_id, vendor_id, warehouse_id);
				// 调用方法保存数据
				try {
					productService.saveProductDetail(prod);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				// 输出提示信息，隐藏子页面，刷新主面板
				JOptionPane.showMessageDialog(null, "商品信息保存成功！");
				this.setVisible(false);
				parentPanel.refreshTablePanelBySearch();
			}
		}else if(e.getSource() == reset){
			prod_name.setFont(MyFont.TipFont);
			prod_name.setForeground(MyColor.TipColor);
			prod_name.setText("必填项");
			prod_cost.setFont(MyFont.TipFont);
			prod_cost.setForeground(MyColor.TipColor);
			prod_cost.setText("可保留两位小数");
			prod_price.setFont(MyFont.TipFont);
			prod_price.setForeground(MyColor.TipColor);
			prod_price.setText("可保留两位小数");
			putaway_stock.setFont(MyFont.TipFont);
			putaway_stock.setForeground(MyColor.TipColor);
			putaway_stock.setText("请输入整数");
			current_stock.setFont(MyFont.TipFont);
			current_stock.setForeground(MyColor.TipColor);
			current_stock.setText("请输入整数");
			safe_stock.setFont(MyFont.TipFont);
			safe_stock.setForeground(MyColor.TipColor);
			safe_stock.setText("请输入整数");
			prod_unit.setSelectedIndex(0);
			prod_origin.setFont(MyFont.TipFont);
			prod_origin.setForeground(MyColor.TipColor);
			prod_origin.setText("必填项");
			String DefaultFormat = "yyyy-MM-dd HH:mm:ss";
			// 当前时间
			prod_date = new DatePicker(new Date(), DefaultFormat,
					MyFont.JTextFieldFont, new Dimension(177, 24));
			prod_date.setSize(new Dimension(180, 50));
			prod_discount.setFont(MyFont.JTextFieldFont);
			prod_discount.setForeground(MyColor.TipColor);
			prod_discount.setText("可保留两位小数");
			promotion_flag.setSelectedIndex(0);
			promotion_price.setFont(MyFont.JTextFieldFont);
			promotion_price.setForeground(MyColor.TipColor);
			promotion_price.setText("可保留两位小数");
			category.setSelectedIndex(0);
			vendor.setSelectedIndex(0);
			warehouse.setSelectedIndex(0);
			prod_descr.setFont(MyFont.TipFont);
			prod_descr.setForeground(MyColor.TipColor);
			prod_descr.setText("具体的商品描述，可选填");
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
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	/**
	 * 获取焦点事件
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == prod_name) {
			if(prod_name.getText().equals("必填项")){
				prod_name.setFont(MyFont.JTextFieldFont);
				prod_name.setForeground(MyColor.JTextFieldColor);
				prod_name.setText("");
			}
		} else if (e.getSource() == prod_cost) {
			if(prod_cost.getText().equals("可保留两位小数")){
				prod_cost.setFont(MyFont.JTextFieldFont);
				prod_cost.setForeground(MyColor.JTextFieldColor);
				prod_cost.setText("");
			}
		} else if (e.getSource() == prod_price) {
			if(prod_price.getText().equals("可保留两位小数")){
				prod_price.setFont(MyFont.JTextFieldFont);
				prod_price.setForeground(MyColor.JTextFieldColor);
				prod_price.setText("");
			}
		} else if (e.getSource() == putaway_stock) {
			if(putaway_stock.getText().equals("请输入整数")){
				putaway_stock.setFont(MyFont.JTextFieldFont);
				putaway_stock.setForeground(MyColor.JTextFieldColor);
				putaway_stock.setText("");
			}
		} else if (e.getSource() == current_stock) {
			if(current_stock.getText().equals("请输入整数")){
				current_stock.setFont(MyFont.JTextFieldFont);
				current_stock.setForeground(MyColor.JTextFieldColor);
				current_stock.setText("");
			}
		} else if (e.getSource() == safe_stock) {
			if(safe_stock.getText().equals("请输入整数")){
				safe_stock.setFont(MyFont.JTextFieldFont);
				safe_stock.setForeground(MyColor.JTextFieldColor);
				safe_stock.setText("");
			}
		} else if (e.getSource() == prod_origin) {
			if(prod_origin.getText().equals("必填项")){
				prod_origin.setFont(MyFont.JTextFieldFont);
				prod_origin.setForeground(MyColor.JTextFieldColor);
				prod_origin.setText("");
			}
		} else if (e.getSource() == prod_discount) {
			if(prod_discount.getText().equals("可保留两位小数")){
				prod_discount.setFont(MyFont.JTextFieldFont);
				prod_discount.setForeground(MyColor.JTextFieldColor);
				prod_discount.setText("");
			}
		} else if (e.getSource() == promotion_price) {
			if(promotion_price.getText().equals("可保留两位小数")){
				promotion_price.setFont(MyFont.JTextFieldFont);
				promotion_price.setForeground(MyColor.JTextFieldColor);
				promotion_price.setText("");
			}
		} else if (e.getSource() == prod_descr) {
			if(prod_descr.getText().equals("具体的商品描述，可选填")){
				prod_descr.setFont(MyFont.JTextFieldFont);
				prod_descr.setForeground(MyColor.JTextFieldColor);
				prod_descr.setText("");
			}
		}
	}

	/**
	 * 失去焦点事件
	 */
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == prod_name) {
			if(prod_name.getText().equals("")){
				prod_name.setFont(MyFont.TipFont);
				prod_name.setForeground(MyColor.TipColor);
				prod_name.setText("必填项");
			}
		} else if (e.getSource() == prod_cost) {
			if(prod_cost.getText().equals("")){
				prod_cost.setFont(MyFont.TipFont);
				prod_cost.setForeground(MyColor.TipColor);
				prod_cost.setText("可保留两位小数");
			}
		} else if (e.getSource() == prod_price) {
			if(prod_price.getText().equals("")){
				prod_price.setFont(MyFont.TipFont);
				prod_price.setForeground(MyColor.TipColor);
				prod_price.setText("可保留两位小数");
			}
		} else if (e.getSource() == putaway_stock) {
			if(putaway_stock.getText().equals("")){
				putaway_stock.setFont(MyFont.TipFont);
				putaway_stock.setForeground(MyColor.TipColor);
				putaway_stock.setText("请输入整数");
			}
		} else if (e.getSource() == current_stock) {
			if(current_stock.getText().equals("")){
				current_stock.setFont(MyFont.TipFont);
				current_stock.setForeground(MyColor.TipColor);
				current_stock.setText("请输入整数");
			}
		} else if (e.getSource() == safe_stock) {
			if(safe_stock.getText().equals("")){
				safe_stock.setFont(MyFont.TipFont);
				safe_stock.setForeground(MyColor.TipColor);
				safe_stock.setText("请输入整数");
			}
		} else if (e.getSource() == prod_origin) {
			if(prod_origin.getText().equals("")){
				prod_origin.setFont(MyFont.TipFont);
				prod_origin.setForeground(MyColor.TipColor);
				prod_origin.setText("必填项");
			}
		} else if (e.getSource() == prod_discount) {
			if(prod_discount.getText().equals("")){
				prod_discount.setFont(MyFont.TipFont);
				prod_discount.setForeground(MyColor.TipColor);
				prod_discount.setText("可保留两位小数");
			}
		} else if (e.getSource() == promotion_price) {
			if(promotion_price.getText().equals("")){
				promotion_price.setFont(MyFont.TipFont);
				promotion_price.setForeground(MyColor.TipColor);
				promotion_price.setText("可保留两位小数");
			}
		} else if (e.getSource() == prod_descr) {
			if(prod_descr.getText().equals("")){
				prod_descr.setFont(MyFont.TipFont);
				prod_descr.setForeground(MyColor.TipColor);
				prod_descr.setText("具体的商品描述，可选填");
			}
		}
	}
}
