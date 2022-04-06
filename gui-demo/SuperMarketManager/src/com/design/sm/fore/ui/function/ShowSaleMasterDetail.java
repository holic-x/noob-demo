package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.dao.impl.SaleOrderServiceImpl;
import com.design.sm.fore.ui.control.SaleMasterJPanel;
import com.design.sm.model.Accounts;
import com.design.sm.model.ConsumeClass;
import com.design.sm.model.Customer;
import com.design.sm.model.SaleMaster;
import com.design.sm.model.SaleOrder;
import com.design.sm.model.SoldNote;
import com.design.sm.service.AccountsService;
import com.design.sm.service.ConsumeClassService;
import com.design.sm.service.CustomerService;
import com.design.sm.service.EmployeeService;
import com.design.sm.service.ProductService;
import com.design.sm.service.SaleMasterService;
import com.design.sm.service.SaleOrderService;
import com.design.sm.service.SaleTempService;
import com.design.sm.service.SoldNoteService;
import com.design.sm.service.StockMasterService;
import com.design.sm.service.StockOrderService;
import com.design.sm.service.VendorContactService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.ConsumeClassServiceImpl;
import com.design.sm.service.impl.CustomerServiceImpl;
import com.design.sm.service.impl.EmployeeServiceImpl;
import com.design.sm.service.impl.ProductServiceImpl;
import com.design.sm.service.impl.SaleMasterServiceImpl;
import com.design.sm.service.impl.SaleTempServiceImpl;
import com.design.sm.service.impl.SoldNoteServiceImpl;
import com.design.sm.service.impl.StockMasterServiceImpl;
import com.design.sm.service.impl.StockOrderServiceImpl;
import com.design.sm.service.impl.VendorContactServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.RandomGeneration;
import com.design.sm.utils.Tools;

public class ShowSaleMasterDetail extends JFrame{
	// 定义全局组件
	JPanel backgroundPanel, buttonPanel,tablePanel, ensurePanel;
	// 借助工具类完成表格数据的封装
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// 定义用到的标签
	JLabel label_master_id, label_order_num, label_handle, label_cust,
			label_contact, label_time, label_concession,label_actual_sum,label_sum;
	JTextField master_id, order_num, handle, cust, time, concession;
	JButton commit, cancel;
	double actual_sum;
	// 定义相应的service
	SaleMasterService saleMasterService = new SaleMasterServiceImpl();
	SaleOrderService saleOrderService = new SaleOrderServiceImpl();
	CustomerService customerService = new CustomerServiceImpl();
	ConsumeClassService consumeClassService = new ConsumeClassServiceImpl();
	SoldNoteService soldNoteService = new SoldNoteServiceImpl();
	
	Accounts loginUser;
	SaleMasterJPanel parentPanel;
	JTable parentTable;
	int selectedRow;

	// 定义全局变量
	private String master_id_string, order_num_string, handle_id_string,
			cust_id_string;

	/**
	 * 通过构造方法完成初始化
	 */
	public ShowSaleMasterDetail(SaleMasterJPanel parentPanel, Accounts loginUser,
			JTable parentTable,int selectedRow) {
		this.parentPanel = parentPanel;
		this.loginUser = loginUser;
		this.parentTable = parentTable;
		this.selectedRow = selectedRow;
		backgroundPanel = new JPanel(new BorderLayout());
		// 初始化布局
		initTablePanel();// 初始化显示的表格数据
		initEnsurePanel();// 初始化确认面板
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("订单详情");
		this.setSize(725, 500);
		this.setVisible(true);
		this.setLocationRelativeTo(null);// 设置依赖项
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);// 设置关闭方式
		// 当前窗口隐藏，不影响后方数据的使用，而不是关闭整个窗口

	}

	/**
	 * 初始化显示的表格数据
	 */
	private void initTablePanel() {
		String[] params = { "产品id", "订单编号","产品id","产品名称", "数量", "售价", "小计", "供应商id", "供应商" };
		Vector<Vector> vec = new Vector<>();
		try {
			vec = saleOrderService.pack(saleOrderService.getSaleOrderBySMId(parentTable.getValueAt(selectedRow, 0).toString()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 将查询到的数据封装到BbaseTableModule中
		baseTableModule = new BaseTableModule(params, vec);
		table = new JTable(baseTableModule);
		// 利用提供的Tools类美化表格
		Tools.setTableStyle(table);
		// 通过设置行的大小，隐藏某一列内容（只显示想要显示的数据）
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) table
				.getColumnModel();
		// 隐藏：5
		dcm.getColumn(0).setMinWidth(0);
		dcm.getColumn(0).setMaxWidth(0);
		dcm.getColumn(2).setMinWidth(0);
		dcm.getColumn(2).setMaxWidth(0);
		dcm.getColumn(7).setMinWidth(0);
		dcm.getColumn(7).setMaxWidth(0);
		dcm.getColumn(8).setMinWidth(0);
		dcm.getColumn(8).setMaxWidth(0);

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

	/**
	 * 初始化确认面板
	 */
	private void initEnsurePanel() {
		ensurePanel = new JPanel(new BorderLayout());

		JPanel jp1 = new JPanel();
		label_master_id = new JLabel("订单id  ");
		master_id = new JTextField(28);
		master_id.setEditable(false);

		label_handle = new JLabel("处理人  ");
		handle = new JTextField(28);
		handle.setEditable(false);

		jp1.add(label_master_id);
		jp1.add(master_id);
		jp1.add(label_handle);
		jp1.add(handle);

		JPanel jp2 = new JPanel();
		label_order_num = new JLabel("订单编号");
		order_num = new JTextField(28);
		order_num.setEditable(false);

		label_cust = new JLabel("顾客名称");
		cust = new JTextField(28);
		cust.setEditable(false);

		jp2.add(label_order_num);
		jp2.add(order_num);
		jp2.add(label_cust);
		jp2.add(cust);

		JPanel jp3 = new JPanel();
		label_time = new JLabel("处理时间");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		time = new JTextField(28);
		time.setText(df.format(new Date()));
		label_concession = new JLabel("优惠合计");
		concession = new JTextField(28);
		concession.setEditable(false);

		jp3.add(label_time);
		jp3.add(time);
		jp3.add(label_concession);
		jp3.add(concession);

		Box ver = Box.createVerticalBox();
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp3);

		JPanel east = new JPanel(new GridLayout(2, 1));
		label_sum = new JLabel();
		label_actual_sum = new JLabel();
		east.add(label_sum);
		east.add(label_actual_sum);
		

		// 将统计后的信息进行回显
		this.echoData();
		// 将组件添加到背景面板中
		ensurePanel.add(ver, BorderLayout.WEST);
		ensurePanel.add(east, BorderLayout.CENTER);
		backgroundPanel.add(ensurePanel, BorderLayout.SOUTH);
		backgroundPanel.validate();
	}


	/**
	 * 刷新数据面板
	 */
	public void refreshTablePanel() {
		// 移除当前数据面板中的所有数据
		backgroundPanel.removeAll();
		initTablePanel();
		initEnsurePanel();
		backgroundPanel.validate();// 验证
	}

	/**
	 * 统计信息（数据信息回显）
	 */
	public void echoData() {
		try {
			// 订单id随机生成32char的id序列、一旦确定不能够随意更改
			master_id_string = parentTable.getValueAt(selectedRow, 0).toString();
			// 订单编号根据当前序列递增(从数据库中获取)
			order_num_string = parentTable.getValueAt(selectedRow, 1).toString();
			String handle_string = parentTable.getValueAt(selectedRow, 3).toString();
			String customer_string = parentTable.getValueAt(selectedRow, 4).toString();
			// 加载文本数据
			master_id.setText(master_id_string);
			master_id.setToolTipText(master_id_string);
			order_num.setText(order_num_string);
			order_num.setToolTipText(order_num_string);
			handle.setText(handle_string);
			handle.setToolTipText(handle_string);
			Customer c = customerService.getCustomerById(parentTable.getValueAt(selectedRow, 4).toString());
			if (c != null) {
				cust.setText(c.getCustomer_name());
				cust.setToolTipText(c.getCustomer_name());
				cust_id_string = c.getCustomer_id();
				// 统计当前订单的所有商品总额
				double sum = 0;
				for (int i = 0; i < table.getRowCount(); i++) {
					sum += Double.valueOf(table.getValueAt(i, 6).toString());
				}
				/**
				 *  根据产品的订单号获取订单实际支付的金额，然后用实际总额-实际金额获取优惠价格
				 *  不用再次计算，因为如果再次通过获取数据库的信息进行计算，有可能会出现顾客账号
				 *  等级提升而享有不同的优惠条件，从而引发由数据的时差引起的错误，
				 */
				SoldNote sn = soldNoteService.getSoldNoteByNum(order_num_string);
				double actual_sum = sn.getActual_amount();
				// 最终的总金额为当前所有商品总额减去优惠合计
				double concession_dobule = sum-actual_sum;
				concession.setText(concession_dobule+"");
				label_sum.setText("实付金额：" + actual_sum);
				label_actual_sum.setText("总金额："+sum);
			} else {
				// 不是vip顾客
				cust.setText("空");
				cust.setToolTipText("空");
				cust_id_string = null;
				// 统计当前订单的所有商品总额
				double sum = 0;
				for (int i = 0; i < table.getRowCount(); i++) {
					sum += Double.valueOf(table.getValueAt(i, 4).toString());
				}
				concession.setText("0.00");
				actual_sum = sum;
				label_sum.setText("实付金额：" + actual_sum);
				label_actual_sum.setText("总金额："+sum);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}