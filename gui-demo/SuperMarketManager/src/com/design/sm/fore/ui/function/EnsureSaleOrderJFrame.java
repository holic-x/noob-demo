package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
import com.design.sm.model.Accounts;
import com.design.sm.model.ConsumeClass;
import com.design.sm.model.Customer;
import com.design.sm.model.Product;
import com.design.sm.model.SaleMaster;
import com.design.sm.model.SaleOrder;
import com.design.sm.model.SaleTemp;
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
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.ConsumeClassServiceImpl;
import com.design.sm.service.impl.CustomerServiceImpl;
import com.design.sm.service.impl.EmployeeServiceImpl;
import com.design.sm.service.impl.ProductServiceImpl;
import com.design.sm.service.impl.SaleMasterServiceImpl;
import com.design.sm.service.impl.SaleTempServiceImpl;
import com.design.sm.service.impl.SoldNoteServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.RandomGeneration;
import com.design.sm.utils.Tools;

public class EnsureSaleOrderJFrame extends JFrame implements ItemListener,
		MouseListener {
	// 定义全局组件
	JPanel backgroundPanel, buttonPanel, tablePanel, ensurePanel;
	// 借助工具类完成表格数据的封装
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// 定义用到的标签
	JLabel label_master_id, label_order_num, label_handle, label_cust,
			label_contact, label_time, label_concession, label_sum;
	JTextField master_id, order_num, handle, cust, time, concession;
	JButton commit, cancel;
	double actual_sum;
	// 定义一组单选按钮用以存放付款方式
	ButtonGroup payment;
	JRadioButton cash, vipCard, thirdPart, creditCard;
	// 定义相应的service
	ProductService productService = new ProductServiceImpl();
	SaleTempService saleTempService = new SaleTempServiceImpl();
	SaleMasterService saleMasterService = new SaleMasterServiceImpl();
	SaleOrderService saleOrderService = new SaleOrderServiceImpl();
	EmployeeService employeeService = new EmployeeServiceImpl();
	AccountsService accountsService = new AccountsServiceImpl();
	ConsumeClassService consumeClassService = new ConsumeClassServiceImpl();
	CustomerService customerService = new CustomerServiceImpl();
	SoldNoteService soldNoteService = new SoldNoteServiceImpl();

	Accounts loginUser;
	CashierJFrame parentPanel;
	Customer customer;

	// 定义全局变量
	private String master_id_string, order_num_string, handle_id_string,
			cust_id_string;

	/**
	 * 通过构造方法完成初始化
	 */
	public EnsureSaleOrderJFrame(CashierJFrame parentPanel, Accounts loginUser,
			Customer customer) {
		this.parentPanel = parentPanel;
		this.loginUser = loginUser;
		this.customer = customer;
		backgroundPanel = new JPanel(new BorderLayout());
		// 初始化布局
		initButtonPanel();// 初始化支付选择按钮组
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
	 * 初始化支付选择按钮组
	 */
	public void initButtonPanel() {
		buttonPanel = new JPanel();
		payment = new ButtonGroup();
		Icon cash_icon = new ImageIcon("icons/toolImage/cash.png");
		cash = new JRadioButton(cash_icon);
		cash.setToolTipText("现金支付");
		cash.addMouseListener(this);
		cash.addItemListener(this);

		Icon vipCard_icon = new ImageIcon("icons/toolImage/vipCard.png");
		vipCard = new JRadioButton(vipCard_icon);
		vipCard.setToolTipText("vip余额支付");
		vipCard.addMouseListener(this);
		vipCard.addItemListener(this);

		Icon thirdPart_icon = new ImageIcon("icons/toolImage/thirdPart.png");
		thirdPart = new JRadioButton(thirdPart_icon);
		thirdPart.setToolTipText("第三方支付");
		thirdPart.addMouseListener(this);
		thirdPart.addItemListener(this);

		Icon creditCard_icon = new ImageIcon("icons/toolImage/creditCard.png");
		creditCard = new JRadioButton(creditCard_icon);
		creditCard.setToolTipText("信用卡支付");
		creditCard.addMouseListener(this);
		creditCard.addItemListener(this);

		payment.add(cash);
		payment.add(vipCard);
		payment.add(thirdPart);
		payment.add(creditCard);

		buttonPanel.add(cash);
		buttonPanel.add(vipCard);
		buttonPanel.add(thirdPart);
		buttonPanel.add(creditCard);
		backgroundPanel.add(buttonPanel, BorderLayout.NORTH);
	}

	/**
	 * 初始化显示的表格数据
	 */
	private void initTablePanel() {
		String[] params = { "产品id", "产品名称", "数量", "售价", "小计", "供应商id", "供应商" };
		Vector<Vector> vec = new Vector<>();
		try {
			vec = saleTempService.pack(saleTempService.findAllSaleTempList());
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
		dcm.getColumn(6).setMinWidth(0);
		dcm.getColumn(6).setMaxWidth(0);

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
		master_id = new JTextField(25);
		master_id.setEditable(false);

		label_handle = new JLabel("处理人  ");
		handle = new JTextField(25);
		handle.setEditable(false);

		jp1.add(label_master_id);
		jp1.add(Box.createHorizontalStrut(10));
		jp1.add(master_id);
		jp1.add(Box.createHorizontalStrut(10));
		jp1.add(label_handle);
		jp1.add(Box.createHorizontalStrut(10));
		jp1.add(handle);

		JPanel jp2 = new JPanel();
		label_order_num = new JLabel("订单编号");
		order_num = new JTextField(25);
		order_num.setEditable(false);

		label_cust = new JLabel("顾客名称");
		cust = new JTextField(25);
		cust.setEditable(false);

		jp2.add(label_order_num);
		jp2.add(Box.createHorizontalStrut(10));
		jp2.add(order_num);
		jp2.add(Box.createHorizontalStrut(10));
		jp2.add(label_cust);
		jp2.add(Box.createHorizontalStrut(10));
		jp2.add(cust);

		JPanel jp3 = new JPanel();
		label_time = new JLabel("处理时间");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		time = new JTextField(25);
		time.setText(df.format(new Date()));
		label_concession = new JLabel("优惠合计");
		concession = new JTextField(25);
		concession.setEditable(false);

		jp3.add(label_time);
		jp3.add(Box.createHorizontalStrut(10));
		jp3.add(time);
		jp3.add(Box.createHorizontalStrut(10));
		jp3.add(label_concession);
		jp3.add(Box.createHorizontalStrut(10));
		jp3.add(concession);

		Box ver = Box.createVerticalBox();
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp3);

		JPanel east = new JPanel(new GridLayout(3, 1));
		label_sum = new JLabel();

		commit = new JButton("确认");
		commit.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));
		commit.setSize(50, 30);
		commit.addMouseListener(this);

		cancel = new JButton("取消");
		cancel.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.red));
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
			// 判断当前订单总额是否达到1000，随后判断顾客是否为已开卡客户，提醒顾客进行开卡
			double amount = this.actual_sum;
			if (this.customer == null && amount >= 1000) {
				JOptionPane.showMessageDialog(null,
						"当前消费已满1000，可凭小票到客服中心激活积分卡哦！");
			}
			try {
				/**
				 * 必须保证是对应账号的相应员工登录进行操作，否则如果相应账号的员工信息不存在就会相应的
				 * 报异常，主要还是数据库插入数据测试的时候没有考虑全面
				 */
				/**
				 * 订单提交： 1.创建销售订单主表 2.插入销售订单明细 3.修改商品已上架数目 4.移除临时订单的数据 5.添加销售记录
				 * 6.刷新父面板数据 订单生成：判断用户是以何种方式进行支付的，支付成功则插入相应的收支信息
				 */
				int flag = 0;// 定义操作标识
				if (vipCard.isSelected()) {
					// 先判断是否为vip用户
					if (this.customer == null) {
						JOptionPane.showMessageDialog(null, "当前顾客不支持该种支付方式！");
					} else {
						// 通过获取顾客当前卡内余额、判断是否可以进行支付（由于是模拟，其他支付方式都默认直接可以进行，只是记录数据）
						double balance_double = this.customer.getBalance();
						if (actual_sum > balance_double) {
							JOptionPane.showMessageDialog(null,
									"当前卡内余额不足，请换种方式进行操作吧！");
						} else {
							// 改变余额，累计积分
							this.customer.setBalance(balance_double
									- actual_sum);
							this.customer.setIntegrate(this.customer
									.getIntegrate() + (int) actual_sum);
							customerService.updateCustomer(this.customer);
							flag = 1;
						}
					}
				} else {
					if (this.customer != null) {
						this.customer.setIntegrate(this.customer.getIntegrate()
								+ (int) actual_sum);
						customerService.updateCustomer(this.customer);
					}
					flag = 1;
				}
				if (flag == 1) {
					String handle_time_string = time.getText();
					// 提交、生成订单、根据临时表中的数据生成相应的订单，并清除临时表中的已提交的订单
					// 1.先生成一个订单主表(0入库、删除标识、订单出来状态)
					SaleMaster sm = new SaleMaster(master_id_string,
							order_num_string, handle_id_string, cust_id_string,
							handle_time_string, 0, 0);
					saleMasterService.addSaleMaster(sm);
					// 2.根据生成的订单插入订单明细(获取表格数据)
					for (int i = 0; i < table.getRowCount(); i++) {
						String prod_id_string = table.getValueAt(i, 0)
								.toString();
						int quantity_int = Integer.valueOf(table.getValueAt(i,
								2).toString());
						double unit_price_double = Double.valueOf(table
								.getValueAt(i, 3).toString());
						double amount_double = Double.valueOf(table.getValueAt(
								i, 4).toString());
						SaleOrder so = new SaleOrder(master_id_string,
								prod_id_string, quantity_int,
								unit_price_double, amount_double, 0, 0);
						saleOrderService.addSaleOrder(so);
					}
					// 3.依次修改商品对应的已上架数目
					List<SaleTemp> list_temp = saleTempService
							.findAllSaleTempList();
					for (SaleTemp st : list_temp) {
						// 获取商品id、处理数量
						String prod_id = st.getProduct_id();
						int quantity = st.getQuantity();
						Product p = productService.getProduct(prod_id);
						// 当前上架数目减去处理数量
						p.setPutaway_stock(p.getPutaway_stock() - quantity);
						productService.updateProduct(p);
					}
					// 4.依次移除临时销售订单的订单内容
					saleTempService.truncateAllSaleTemp();
					// 5.添加销售记录
					SoldNote sn = new SoldNote(order_num_string, actual_sum,
							this.getPaymentInt());
					soldNoteService.addSoldNote(sn);
					// 6.隐藏页面，输出提示
					this.setVisible(false);
					JOptionPane.showMessageDialog(null, "交易成功！");
					parentPanel.refreshBackgroundPanel();
				}
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
			order_num_string = saleMasterService.getSaleNextSeq().toString();
			// 经手id为当前登录该员工账号的员工id(通过当前员工账号获取)
			String accountId = this.loginUser.getAccount_id();
			handle_id_string = employeeService
					.getEmployeeIdByAccountId(accountId);

			// 加载文本数据
			master_id.setText(master_id_string);
			master_id.setToolTipText(master_id_string);
			order_num.setText(order_num_string);
			order_num.setToolTipText(order_num_string);
			handle.setText(String.valueOf(employeeService
					.getEmployeeName(handle_id_string)));
			handle.setToolTipText(String.valueOf(employeeService
					.getEmployeeName(handle_id_string)));
			// 顾客id由当前传入的顾客进行操作
			if (this.customer != null) {
				cust.setText(customer.getCustomer_name());
				cust.setToolTipText(customer.getCustomer_name());
				cust_id_string = customer.getCustomer_id();
				// 统计当前订单的所有商品总额
				double sum = 0;
				for (int i = 0; i < table.getRowCount(); i++) {
					sum += Double.valueOf(table.getValueAt(i, 4).toString());
				}
				// 获取打折优惠的价格
				/**
				 * 自定义规则： 如果是会员采购则按照相应的条件进行优惠 1.每次购物能够以相应的折扣进行采购
				 * 2.每次购物消费经折后满1000以上能够享受相应的满减优惠
				 */
				ConsumeClass cc = consumeClassService
						.getConsumeClassById(customer.getClass_id());
				double discountAmount = ((int) (sum * cc.getClass_discount() * 100)) / 100;
				double concession_dobule = discountAmount;
				if ((sum - discountAmount) > 1000) {
					concession_dobule += cc.getClass_off();
				}
				concession.setText(concession_dobule + "");
				// 最终的总金额为当前所有商品总额减去优惠合计
				actual_sum = sum - concession_dobule;
				label_sum.setText("总金额：" + (sum - concession_dobule));
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
				label_sum.setText("总金额：" + (sum));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据单选框选项获取用户选择的支付方式
	 */
	public int getPaymentInt() {
		int i = 0;
		if (cash.isSelected()) {
			i = 1;
		} else if (vipCard.isSelected()) {
			i = 2;
		} else if (thirdPart.isSelected()) {
			i = 3;
		} else if (creditCard.isSelected()) {
			i = 4;
		}
		return i;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if (cash.isSelected()) {
				cash.setBackground(new Color(192, 190, 204));
				vipCard.setBackground(new Color(250, 250, 250));
				thirdPart.setBackground(new Color(250, 250, 250));
				creditCard.setBackground(new Color(250, 250, 250));
			} else if (vipCard.isSelected()) {
				vipCard.setBackground(new Color(192, 190, 204));
				cash.setBackground(new Color(250, 250, 250));
				thirdPart.setBackground(new Color(250, 250, 250));
				creditCard.setBackground(new Color(250, 250, 250));
			} else if (thirdPart.isSelected()) {
				thirdPart.setBackground(new Color(192, 190, 204));
				cash.setBackground(new Color(250, 250, 250));
				vipCard.setBackground(new Color(250, 250, 250));
				creditCard.setBackground(new Color(250, 250, 250));
			} else if (creditCard.isSelected()) {
				creditCard.setBackground(new Color(192, 190, 204));
				cash.setBackground(new Color(250, 250, 250));
				vipCard.setBackground(new Color(250, 250, 250));
				thirdPart.setBackground(new Color(250, 250, 250));
			}
		}
	}
}
