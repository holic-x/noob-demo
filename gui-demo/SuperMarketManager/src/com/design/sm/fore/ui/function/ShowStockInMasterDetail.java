package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.model.Accounts;
import com.design.sm.model.StockMaster;
import com.design.sm.model.StockOrder;
import com.design.sm.model.VendorContact;
import com.design.sm.service.AccountsService;
import com.design.sm.service.EmployeeService;
import com.design.sm.service.ProductService;
import com.design.sm.service.StockMasterService;
import com.design.sm.service.StockOrderService;
import com.design.sm.service.TempService;
import com.design.sm.service.VendorContactService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.EmployeeServiceImpl;
import com.design.sm.service.impl.ProductServiceImpl;
import com.design.sm.service.impl.StockMasterServiceImpl;
import com.design.sm.service.impl.StockOrderServiceImpl;
import com.design.sm.service.impl.TempServiceImpl;
import com.design.sm.service.impl.VendorContactServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.Item;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.RandomGeneration;
import com.design.sm.utils.Tools;
import com.eltima.components.ui.DatePicker;

public class ShowStockInMasterDetail extends JFrame {
	// 定义全局组件
	JPanel backgroundPanel, tablePanel, ensurePanel;
	// 借助工具类完成表格数据的封装
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// 定义用到的标签
	JLabel label_master_id, label_order_num, label_handle, label_vendor,
			label_contact, label_time, label_sum;
	JTextField master_id, order_num, handle, vendor, contact, time;
	// 定义相应的service
	ProductService productService = new ProductServiceImpl();
	StockMasterService stockMasterService = new StockMasterServiceImpl();
	StockOrderService stockOrderService = new StockOrderServiceImpl();
	EmployeeService employeeService = new EmployeeServiceImpl();
	AccountsService accountsService = new AccountsServiceImpl();
	VendorContactService vendorContactService = new VendorContactServiceImpl();

	Accounts loginUser;
	PurchaseRecordJPanel parentPanel;
	JTable parentTable;
	int selectedRow;

	/**
	 * 通过构造方法完成初始化
	 */
	public ShowStockInMasterDetail(PurchaseRecordJPanel parentPanel,
			Accounts loginUser, JTable parentTable, int selectedRow) {
		this.parentPanel = parentPanel;
		this.loginUser = loginUser;
		this.parentTable = parentTable;
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
		// 输入的数据进行查找
		String[] params = { "订单id", "订单编号", "产品id", "产品名称", "操作数量", "产品单价",
				"总额", "供应商id", "供应商" };
		Vector<Vector> vec = new Vector<>();
		// 获取当前订单id
		String smId = parentTable.getValueAt(selectedRow, 0).toString();
		try {
			vec = stockOrderService.pack(stockOrderService
					.getStockOrderBySMId(smId));
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

		label_vendor = new JLabel("供应商  ");
		vendor = new JTextField(28);
		vendor.setEditable(false);

		jp2.add(label_order_num);
		jp2.add(order_num);
		jp2.add(label_vendor);
		jp2.add(vendor);

		JPanel jp3 = new JPanel();
		label_time = new JLabel("处理时间");
		time = new JTextField(28);
		time.setEditable(false);

		label_contact = new JLabel("负责人  ");
		contact = new JTextField(28);
		contact.setEditable(false);

		jp3.add(label_time);
		jp3.add(time);
		jp3.add(label_contact);
		jp3.add(contact);

		Box ver = Box.createVerticalBox();
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp3);

		JPanel east = new JPanel(new GridLayout(3, 1));
		label_sum = new JLabel();

		east.add(label_sum);

		// 将统计后的信息进行回显
		this.echoData();
		// 将组件添加到背景面板中
		ensurePanel.add(ver, BorderLayout.WEST);
		ensurePanel.add(east, BorderLayout.CENTER);
		backgroundPanel.add(ensurePanel, BorderLayout.SOUTH);
		backgroundPanel.validate();
	}

	/**
	 * 统计信息（数据信息回显）
	 */
	public void echoData() {
		// 根据表格数据获取相应的信息
		String master_id_string = parentTable.getValueAt(selectedRow, 0)
				.toString();
		String order_num_string = parentTable.getValueAt(selectedRow, 1)
				.toString();
		String handle_string = parentTable.getValueAt(selectedRow, 3)
				.toString();
		String vendor_string = parentTable.getValueAt(selectedRow, 5)
				.toString();
		String contact_string = parentTable.getValueAt(selectedRow, 7)
				.toString();
		String time_string = parentTable.getValueAt(selectedRow, 8).toString();

		// 加载文本数据
		master_id.setText(master_id_string);
		master_id.setToolTipText(master_id_string);
		order_num.setText(order_num_string);
		order_num.setToolTipText(order_num_string);
		handle.setText(handle_string);
		handle.setToolTipText(handle_string);
		vendor.setText(vendor_string);
		vendor.setToolTipText(vendor_string);
		contact.setText(contact_string);
		contact.setToolTipText(contact_string);
		time.setText(time_string);
		time.setToolTipText(time_string);
		// 统计当前订单的所有商品总额
		double sum = 0;
		for (int i = 0; i < table.getRowCount(); i++) {
			sum += Double.valueOf(table.getValueAt(i, 6).toString());
		}
		label_sum.setText("总金额：" + sum);
	}
}
