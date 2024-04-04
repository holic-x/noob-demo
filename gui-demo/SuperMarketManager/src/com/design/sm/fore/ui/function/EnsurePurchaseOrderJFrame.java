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
import com.design.sm.model.PurchaseNote;
import com.design.sm.model.StockMaster;
import com.design.sm.model.StockOrder;
import com.design.sm.model.VendorContact;
import com.design.sm.service.AccountsService;
import com.design.sm.service.EmployeeService;
import com.design.sm.service.ProductService;
import com.design.sm.service.PurchaseNoteService;
import com.design.sm.service.StockMasterService;
import com.design.sm.service.StockOrderService;
import com.design.sm.service.TempService;
import com.design.sm.service.VendorContactService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.EmployeeServiceImpl;
import com.design.sm.service.impl.ProductServiceImpl;
import com.design.sm.service.impl.PurchaseNoteServiceImpl;
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

public class EnsurePurchaseOrderJFrame extends JFrame implements MouseListener {
	// 定义全局组件
	JPanel backgroundPanel, tablePanel, ensurePanel;
	// 借助工具类完成表格数据的封装
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// 定义用到的标签
	JLabel label_master_id, label_order_num, label_handle, label_vendor,
			label_contact, label_time,label_sum;
	JTextField master_id, order_num, handle, vendor;
	JComboBox contact;
	DatePicker time;
	JButton commit, cancel;
	// 定义相应的service
	ProductService productService = new ProductServiceImpl();
	TempService tempService = new TempServiceImpl();
	StockMasterService stockMasterService = new StockMasterServiceImpl();
	StockOrderService stockOrderService = new StockOrderServiceImpl();
	EmployeeService employeeService = new EmployeeServiceImpl();
	AccountsService accountsService = new AccountsServiceImpl();
	VendorContactService vendorContactService = new VendorContactServiceImpl();
	
	Accounts loginUser;
	PurchaseListJPanel parentPanel;
	JTable parentTable;
	int[] selectedRow;
	// 定义全局变量
	private String master_id_string, order_num_string, handle_id_string,
			vendor_id_string;

	/**
	 * 通过构造方法完成初始化
	 */
	public EnsurePurchaseOrderJFrame(PurchaseListJPanel parentPanel,
			Accounts loginUser, JTable parentTable, int[] selectedRow) {
		this.parentPanel = parentPanel;
		this.loginUser = loginUser;
		this.parentTable = parentTable;
		backgroundPanel = new JPanel(new BorderLayout());
		// 初始化布局
		initTablePanel();// 初始化显示的表格数据
		initEnsurePanel();// 初始化确认面板
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("订单确认");
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
		String[] params = { "产品id", "产品名称", "操作数量", "产品单价", "总额", "供应商id",
				"供应商" };
		Vector<Vector> vec = new Vector<>();
		// 获取当前选中的数据
		String[] ids;
		ArrayList id_list = new ArrayList<>();
		for (int rowindex : parentTable.getSelectedRows()) {
			Object obj = parentTable.getValueAt(rowindex, 0);
			id_list.add(obj);
		}
		// 集合转数组
		ids = (String[]) id_list.toArray(new String[id_list.size()]);
		try {
			vec = tempService.pack(tempService.getTempListByProductId(ids));
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
		String DefaultFormat = "yyyy-MM-dd HH:mm:ss";
		// 当前时间
		time = new DatePicker(new Date(), DefaultFormat,
				MyFont.JTextFieldFont, new Dimension(177, 24));
//		time.setSize(new Dimension(150, 50));
		
		label_contact = new JLabel("负责人  ");
		contact = new JComboBox();
		contact.setPreferredSize(new Dimension(175, 30));
		
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
		
		commit = new JButton("确认");
		commit.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
		commit.setSize(50, 30);
		commit.addMouseListener(this);

		cancel = new JButton("取消");
		cancel.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
		cancel.setSize(50, 30);
		cancel.addMouseListener(this);
		east.add(label_sum);
		east.add(commit);
		east.add(cancel);
		
		// 将统计后的信息进行回显
		this.echoData();
		// 将组件添加到背景面板中
		ensurePanel.add(ver, BorderLayout.WEST);
		ensurePanel.add(east, BorderLayout.CENTER);
		backgroundPanel.add(ensurePanel, BorderLayout.SOUTH);
		backgroundPanel.validate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == commit) {
			try {
				/**
				 * 必须保证是对应账号的相应员工登录进行操作，否则如果相应账号的员工信息不存在就会相应的
				 * 报异常，主要还是数据库插入数据测试的时候没有考虑全面
				 */
				/**
				 * 订单提交：
				 * 1.创建订单主表
				 * 2.插入订单明细
				 * 3.移除临时订单的数据
				 * 4.刷新面板数据
				 */
				// 获取相应的信息
				String contact_id =( (Item)contact.getSelectedItem()).getKey();
				String handle_time_string = time.getText();
				// 提交、生成订单、根据临时表中的数据生成相应的订单，并清除临时表中的已提交的订单
				// 先生成一个订单主表(0入库、删除标识、订单出来状态)
				StockMaster sm = new StockMaster(master_id_string, order_num_string, 
						handle_id_string, vendor_id_string, contact_id, handle_time_string, 0, 0, 0);
					stockMasterService.addStockMaster(sm);
				// 根据生成的订单插入订单明细(获取表格数据)
				for(int i=0;i<table.getRowCount();i++){
					String prod_id_string = table.getValueAt(i, 0).toString();
					int quantity_int = Integer.valueOf(table.getValueAt(i, 2).toString());
					double unit_price_double = Double.valueOf(table.getValueAt(i, 3).toString());
					double amount_double = Double.valueOf(table.getValueAt(i, 4).toString());
					StockOrder so = new StockOrder(master_id_string, prod_id_string, quantity_int, unit_price_double, amount_double, 0);
					stockOrderService.addStockOrder(so);
				}
				// 依次移除临时采购订单的订单内容
				for(int i=0;i<table.getRowCount();i++){
					String prod_id_string = table.getValueAt(i, 0).toString();
					tempService.deleteTemp(prod_id_string);
				}
				// 隐藏页面，输出提示
				this.setVisible(false);
				JOptionPane.showMessageDialog(null, "订单信息提交成功！");
				parentPanel.refreshTablePanel();
			} catch (NumberFormatException | SQLException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == cancel) {
			// 取消操作、隐藏当前面板
			this.setVisible(false);
		}
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
	 * 统计信息（数据信息回显）
	 */
	public void echoData() {
		try {
			// 订单id随机生成32char的id序列、一旦确定不能够随意更改
			master_id_string = RandomGeneration.getRandom32charSeq();
			// 订单编号根据当前序列递增(从数据库中获取)
			order_num_string = stockMasterService.getStockInNextSeq();
			// 经手id为当前登录该员工账号的员工id(通过当前员工账号获取)
			String accountId = this.loginUser.getAccount_id();
			handle_id_string = employeeService
					.getEmployeeIdByAccountId(accountId);
			// 供应商id相应的生成订单的供应商id
			vendor_id_string = table.getValueAt(0, 5).toString();// 每一行数据都限定同一个供应商才能生成订单

			// 加载文本数据
			master_id.setText(master_id_string);
			master_id.setToolTipText(master_id_string);
			order_num.setText(order_num_string);
			order_num.setToolTipText(order_num_string);
			handle.setText(String.valueOf(employeeService
					.getEmployeeName(handle_id_string)));
			handle.setToolTipText(String.valueOf(employeeService
					.getEmployeeName(handle_id_string)));
			vendor.setText(table.getValueAt(0, 6).toString());
			vendor.setToolTipText(table.getValueAt(0, 6).toString());
			// 根据当前的供应商加载相应的联系人信息
			List<VendorContact> list_contact = vendorContactService
					.getVendorContactByVendorId(vendor_id_string);
			for (VendorContact vc : list_contact) {
				String vcId = vc.getContact_id();
				String vcName = vc.getContact_name();
				Item item = new Item(vcId, vcName);
				contact.addItem(item);
			}
			
			// 统计当前订单的所有商品总额
			double sum = 0;
			for(int i=0;i<table.getRowCount();i++){
				sum += Double.valueOf(table.getValueAt(i, 4).toString());
			}
			label_sum.setText("总金额："+sum);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
