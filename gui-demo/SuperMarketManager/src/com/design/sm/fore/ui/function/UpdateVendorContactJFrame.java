package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.model.Vendor;
import com.design.sm.model.VendorContact;
import com.design.sm.service.VendorContactService;
import com.design.sm.service.VendorService;
import com.design.sm.service.impl.VendorContactServiceImpl;
import com.design.sm.service.impl.VendorServiceImpl;
import com.design.sm.utils.Item;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.RandomGeneration;

public class UpdateVendorContactJFrame extends JFrame implements MouseListener {
	// 定义全局组件
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_name, label_phone, label_email, label_vendor, label_identity;
	JTextField name, phone, email;
	JComboBox vendor, identity;
	JButton save_button, reset_button;

	VendorService vendorService = new VendorServiceImpl();
	VendorContactService vendorContactService = new VendorContactServiceImpl();

	// 得到屏幕大小
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// 定义父对象
	VendorContactManagerJFrame parentPanel;
	JTable table;
	int selectedRow;

	// 通过构造方法初始化数据
	public UpdateVendorContactJFrame(VendorContactManagerJFrame parentPanel,JTable table,int selectedRow) {
		this.parentPanel = parentPanel;
		this.table = table;
		this.selectedRow = selectedRow;
		// 初始化背景
		initBackgroundPanel();
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("修改联络人信息");
		this.setSize(420, 370);
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
		JLabel title = new JLabel("修改联络人信息");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * 初始化内容面板
	 */
	private void initContentPanel() {
		contentPanel = new JPanel();
		JPanel jp1 = new JPanel();
		label_name = new JLabel("姓名    ");
		name = new JTextField(15);
		name.setFont(MyFont.JTextFieldFont);
		name.setForeground(MyColor.JTextFieldColor);
		jp1.add(label_name);
		jp1.add(name);

		JPanel jp2 = new JPanel();
		label_phone = new JLabel("联系方式");
		phone = new JTextField(15);
		phone.setFont(MyFont.JTextFieldFont);
		phone.setForeground(MyColor.JTextFieldColor);
		jp2.add(label_phone);
		jp2.add(phone);

		JPanel jp3 = new JPanel();
		label_email = new JLabel("电子邮箱");
		email = new JTextField(15);
		email.setFont(MyFont.JTextFieldFont);
		email.setForeground(MyColor.JTextFieldColor);
		jp3.add(label_email);
		jp3.add(email);

		JPanel jp4 = new JPanel();
		label_vendor = new JLabel("负责公司");
		vendor = new JComboBox();
		vendor.setPreferredSize(new Dimension(175, 30));
		jp4.add(label_vendor);
		jp4.add(vendor);

		JPanel jp5 = new JPanel();
		label_identity = new JLabel("身份标识");
		identity = new JComboBox();
		identity.setPreferredSize(new Dimension(175, 30));
		jp5.add(label_identity);
		jp5.add(identity);

		/**
		 * 回显数据
		 */
		this.echoData();
		
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
		// 将组件加载到contentPanel面板中
		contentPanel.add(ver);
	}

	/**
	 * 初始化按钮面板
	 */
	private void initButtonPanel() {
		Box hor = Box.createHorizontalBox();
		buttonPanel = new JPanel();
		save_button = new JButton("保存");
		save_button.setForeground(Color.white);
		save_button.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.blue));

		reset_button = new JButton("重置");
		reset_button.setForeground(Color.white);
		reset_button.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.red));

		// 添加按钮监听
		save_button.addMouseListener(this);
		reset_button.addMouseListener(this);

		// 将按钮加载到面板中
		hor.add(save_button);
		hor.add(Box.createHorizontalStrut(20));
		hor.add(reset_button);
		buttonPanel.add(hor);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == save_button) {
			// 获取当前文本框的数据信息
			String name_string = name.getText();
			String phone_string = phone.getText();
			String email_string = email.getText();
			if (name_string.equals("")) {
				JOptionPane.showMessageDialog(null, "联络人名称不能为空");
			} else if (phone_string.equals("")) {
				JOptionPane.showMessageDialog(null, "联络方式不能为空");
			} else if (email_string.equals("")) {
				JOptionPane.showMessageDialog(null, "电子邮箱不能为空");
			} else {
				// 获取下拉框选项
				Item vendor_select = (Item) vendor.getSelectedItem();
				Object identity_select = identity.getSelectedItem();
				// 创建VendorContact对象，将数据保存到数据库中
				// 联系人id是随机生成的32位字符拼接的数据,在修改的时候不能修改id
				String id = table.getValueAt(selectedRow, 0).toString();
				String vendorId = vendor_select.getKey();
				int identity_int = 0;
				if (String.valueOf(identity_select).equals("负责人")) {
					identity_int = 1;
				}
				VendorContact vc = new VendorContact(id, name_string,
						phone_string, email_string, vendorId, identity_int);
				int choose = JOptionPane.showConfirmDialog(null, "确认修改联络人信息？");
				if (choose == 0) {
					try {
						vendorContactService.updateVendorContact(vc);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// 完成数据的保存之后输出提示信息，并将子页面隐藏，刷新主面板的数据
					JOptionPane.showMessageDialog(null, "联络人信息保存成功");
					this.setVisible(false);
					parentPanel.refreshTablePanel();
				} else {
					this.setVisible(false);
				}
			}
		} else if (e.getSource() == reset_button) {
			// 数据回显
			this.echoData();
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
	 * 定义数据回显的方法
	 */
	public void echoData(){
		// 获取当前读取的选中行的表格数据
		String name_string  = table.getValueAt(selectedRow, 1).toString();
		String phone_string = table.getValueAt(selectedRow, 2).toString();
		String email_string = table.getValueAt(selectedRow, 3).toString();
		String vendorId_string = table.getValueAt(selectedRow, 4).toString();
		name.setText(name_string);
		phone.setText(phone_string);
		email.setText(email_string);
		// 初始化供应商下拉框选项
		vendor.removeAllItems();
		List<Vendor> vendor_list = null;
		try {
			vendor_list = vendorService.findAllVendorList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i=0;i<vendor_list.size();i++) {
			int sign = 0;
			String vid = vendor_list.get(i).getVendor_id();
			String vname = vendor_list.get(i).getVendor_name();
			Item item = new Item(vid, vname);
			vendor.addItem(item);
			if(vid.equals(vendorId_string)){
				sign = i;
				vendor.setSelectedIndex(sign);
			}
		}
		// 初始化身份标识下拉框选项
		identity.removeAllItems();
		identity.addItem("联络员");
		identity.addItem("负责人");
		identity.setSelectedIndex(Integer.valueOf(table.getValueAt(selectedRow, 6).toString()));
	}
}