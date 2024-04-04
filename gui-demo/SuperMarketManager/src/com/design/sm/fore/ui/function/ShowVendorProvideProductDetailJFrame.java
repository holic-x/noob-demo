package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.fore.ui.control.BaseDataJPanel;
import com.design.sm.model.Accounts;
import com.design.sm.utils.MyFont;

public class ShowVendorProvideProductDetailJFrame extends JFrame implements
		MouseListener {
	// 定义全局组件
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_prod_id, label_flow_id, label_prod_name, label_prod_cost,
			label_prod_price, label_putaway_stock, label_current_stock,
			label_safe_stock, label_prod_unit, label_prod_origin,
			label_prod_date, label_prod_descr, label_prod_discount,
			label_promotion_flag, label_promotion_price, label_category,
			label_vendor, label_warehouse;
	JTextField prod_id, flow_id, prod_name, prod_cost, prod_price,
			putaway_stock, current_stock, safe_stock, prod_unit, prod_origin,
			prod_date, prod_descr, prod_discount, promotion_flag,
			promotion_price, category, vendor, warehouse;
	JLabel front, back, exit;
	// 得到屏幕大小
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// 定义父对象、当前登录员工\表格、选中行
	ShowVendorProvideProductJFrame parentPanel;
	JTable table;
	int selectRow;
	int currentRow;

	// 通过构造方法初始化数据
	public ShowVendorProvideProductDetailJFrame(
			ShowVendorProvideProductJFrame parentPanel, JTable table,
			int selectRow) {
		this.parentPanel = parentPanel;
		this.table = table;
		this.selectRow = selectRow;
		this.currentRow = selectRow;
		// 初始化背景
		initBackgroundPanel();
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("查看商品详细信息");
		this.setSize(600, 540);
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
		JLabel title = new JLabel("商品详情");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * 初始化内容面板
	 */
	private void initContentPanel() {

		/**
		 * 从表格中获取当前选中商品的所有信息
		 */
		String prod_id_string = table.getValueAt(currentRow, 0).toString();
		String flow_id_string = table.getValueAt(currentRow, 1).toString();
		String prod_name_string = table.getValueAt(currentRow, 2).toString();
		String prod_cost_string = table.getValueAt(currentRow, 3).toString();
		String prod_price_string = table.getValueAt(currentRow, 4).toString();
		String putaway_stock_string = table.getValueAt(currentRow, 5)
				.toString();
		String current_stock_string = table.getValueAt(currentRow, 6)
				.toString();
		String safe_stock_string = table.getValueAt(currentRow, 7).toString();
		String prod_unit_string = table.getValueAt(currentRow, 9).toString();
		String prod_origin_string = table.getValueAt(currentRow, 10).toString();
		String prod_date_string = table.getValueAt(currentRow, 11).toString();
		String prod_descr_string = table.getValueAt(currentRow, 12).toString();
		String prod_discount_string = table.getValueAt(currentRow, 13)
				.toString();
		String promotion_flag_string = table.getValueAt(currentRow, 15)
				.toString();
		String promotion_price_string = table.getValueAt(currentRow, 16)
				.toString();
		String category_string = table.getValueAt(currentRow, 19).toString();
		String vendor_string = table.getValueAt(currentRow, 21).toString();
		String warehouse_string = table.getValueAt(currentRow, 23).toString();

		contentPanel = new JPanel();

		JPanel jp1 = new JPanel();
		label_prod_id = new JLabel("商品id  ");
		prod_id = new JTextField(15);
		prod_id.setFont(MyFont.JTextFieldFont);
		prod_id.setText(prod_id_string);
		prod_id.setToolTipText(prod_id_string);
		prod_id.setEditable(false);
		label_flow_id = new JLabel("条形码  ");
		flow_id = new JTextField(15);
		flow_id.setFont(MyFont.JTextFieldFont);
		flow_id.setText(flow_id_string);
		flow_id.setToolTipText(flow_id_string);
		flow_id.setEditable(false);
		jp1.add(label_prod_id);
		jp1.add(prod_id);
		jp1.add(label_flow_id);
		jp1.add(flow_id);

		JPanel jp2 = new JPanel();
		label_prod_name = new JLabel("商品名称");
		prod_name = new JTextField(15);
		prod_name.setFont(MyFont.JTextFieldFont);
		prod_name.setText(prod_name_string);
		prod_name.setToolTipText(prod_name_string);
		prod_name.setEditable(false);
		label_prod_cost = new JLabel("商品成本");
		prod_cost = new JTextField(15);
		prod_cost.setFont(MyFont.JTextFieldFont);
		prod_cost.setText(prod_cost_string);
		prod_cost.setToolTipText(prod_cost_string);
		prod_cost.setEditable(false);
		jp2.add(label_prod_name);
		jp2.add(prod_name);
		jp2.add(label_prod_cost);
		jp2.add(prod_cost);

		JPanel jp3 = new JPanel();
		label_prod_price = new JLabel("出售价格");
		prod_price = new JTextField(15);
		prod_price.setFont(MyFont.JTextFieldFont);
		prod_price.setText(prod_price_string);
		prod_price.setToolTipText(prod_price_string);
		prod_price.setEditable(false);
		label_putaway_stock = new JLabel("上架库存");
		putaway_stock = new JTextField(15);
		putaway_stock.setFont(MyFont.JTextFieldFont);
		putaway_stock.setText(putaway_stock_string);
		putaway_stock.setToolTipText(putaway_stock_string);
		putaway_stock.setEditable(false);
		jp3.add(label_prod_price);
		jp3.add(prod_price);
		jp3.add(label_putaway_stock);
		jp3.add(putaway_stock);

		JPanel jp4 = new JPanel();
		label_current_stock = new JLabel("仓库库存");
		current_stock = new JTextField(15);
		current_stock.setFont(MyFont.JTextFieldFont);
		current_stock.setText(current_stock_string);
		current_stock.setToolTipText(current_stock_string);
		current_stock.setEditable(false);
		label_safe_stock = new JLabel("安全库存");
		safe_stock = new JTextField(15);
		safe_stock.setFont(MyFont.JTextFieldFont);
		safe_stock.setText(safe_stock_string);
		safe_stock.setToolTipText(safe_stock_string);
		safe_stock.setEditable(false);
		jp4.add(label_current_stock);
		jp4.add(current_stock);
		jp4.add(label_safe_stock);
		jp4.add(safe_stock);

		JPanel jp5 = new JPanel();
		label_prod_unit = new JLabel("商品单位");
		prod_unit = new JTextField(15);
		prod_unit.setFont(MyFont.JTextFieldFont);
		prod_unit.setText(prod_unit_string);
		prod_unit.setToolTipText(prod_unit_string);
		prod_unit.setEditable(false);
		label_prod_origin = new JLabel("商品产地");
		prod_origin = new JTextField(15);
		prod_origin.setFont(MyFont.JTextFieldFont);
		prod_origin.setText(prod_origin_string);
		prod_origin.setToolTipText(prod_origin_string);
		prod_origin.setEditable(false);
		jp5.add(label_prod_unit);
		jp5.add(prod_unit);
		jp5.add(label_prod_origin);
		jp5.add(prod_origin);

		JPanel jp6 = new JPanel();
		label_prod_date = new JLabel("生产日期");
		prod_date = new JTextField(15);
		prod_date.setFont(MyFont.JTextFieldFont);
		prod_date.setText(prod_date_string);
		prod_date.setToolTipText(prod_date_string);
		prod_date.setEditable(false);
		label_prod_descr = new JLabel("商品描述");
		prod_descr = new JTextField(15);
		prod_descr.setFont(MyFont.JTextFieldFont);
		prod_descr.setText(prod_descr_string);
		prod_descr.setToolTipText(prod_descr_string);
		prod_descr.setEditable(false);
		jp6.add(label_prod_date);
		jp6.add(prod_date);
		jp6.add(label_prod_descr);
		jp6.add(prod_descr);

		JPanel jp7 = new JPanel();
		label_prod_discount = new JLabel("商品折扣");
		prod_discount = new JTextField(15);
		prod_discount.setFont(MyFont.JTextFieldFont);
		prod_discount.setText(prod_discount_string);
		prod_discount.setToolTipText(prod_discount_string);
		prod_discount.setEditable(false);
		label_promotion_flag = new JLabel("促销状态");
		promotion_flag = new JTextField(15);
		promotion_flag.setFont(MyFont.JTextFieldFont);
		promotion_flag.setText(promotion_flag_string);
		promotion_flag.setToolTipText(promotion_flag_string);
		promotion_flag.setEditable(false);
		jp7.add(label_prod_discount);
		jp7.add(prod_discount);
		jp7.add(label_promotion_flag);
		jp7.add(promotion_flag);

		JPanel jp8 = new JPanel();
		label_promotion_price = new JLabel("促销价格");
		promotion_price = new JTextField(15);
		promotion_price.setFont(MyFont.JTextFieldFont);
		promotion_price.setText(promotion_price_string);
		promotion_price.setToolTipText(promotion_price_string);
		promotion_price.setEditable(false);
		label_category = new JLabel("所属分类");
		category = new JTextField(15);
		category.setFont(MyFont.JTextFieldFont);
		category.setText(category_string);
		category.setToolTipText(category_string);
		category.setEditable(false);
		jp8.add(label_promotion_price);
		jp8.add(promotion_price);
		jp8.add(label_category);
		jp8.add(category);

		JPanel jp9 = new JPanel();
		label_vendor = new JLabel("供应商  ");
		vendor = new JTextField(15);
		vendor.setFont(MyFont.JTextFieldFont);
		vendor.setText(vendor_string);
		vendor.setToolTipText(vendor_string);
		vendor.setEditable(false);
		label_warehouse = new JLabel("所属仓库");
		warehouse = new JTextField(15);
		warehouse.setFont(MyFont.JTextFieldFont);
		warehouse.setText(warehouse_string);
		warehouse.setToolTipText(warehouse_string);
		warehouse.setEditable(false);
		jp9.add(label_vendor);
		jp9.add(vendor);
		jp9.add(label_warehouse);
		jp9.add(warehouse);

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
		Icon front_icon = new ImageIcon("icons/toolImage/front.png");
		front = new JLabel(front_icon);
		front.setToolTipText("上一条");
		front.addMouseListener(this);

		Icon back_icon = new ImageIcon("icons/toolImage/back.png");
		back = new JLabel(back_icon);
		back.setToolTipText("下一条");
		back.addMouseListener(this);

		Icon exit_icon = new ImageIcon("icons/toolImage/exit.png");
		exit = new JLabel(exit_icon);
		exit.setToolTipText("退出查看");
		exit.addMouseListener(this);
		
		buttonPanel.add(front);
		buttonPanel.add(back);
		buttonPanel.add(exit);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == front) {
			if (currentRow > 0) {
				currentRow = currentRow - 1;
				// 刷新数据面板
				refreshPanel();
			} else {
				JOptionPane.showMessageDialog(null, "前面没有数据了！");
			}
		} else if (e.getSource() == back) {
			if (currentRow < table.getRowCount() - 1) {
				currentRow = currentRow + 1;
				// 刷新数据面板
				refreshPanel();
			} else {
				JOptionPane.showMessageDialog(null, "后面没有数据了！");
			}
		} else if (e.getSource() == exit) {
			this.setVisible(false);
			;
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

	private void refreshPanel() {
		// 刷新显示面板内容
		backgroundPanel.remove(contentPanel);
		initContentPanel();
		backgroundPanel.add(contentPanel, BorderLayout.CENTER);
		backgroundPanel.validate();
	}
}
