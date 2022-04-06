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

public class AddVendorContactJFrame extends JFrame implements MouseListener,
		FocusListener {
	// 定义全局组件
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_name, label_phone, label_email, label_vendor, label_identity;
	JTextField name, phone, email ;
	JComboBox vendor,identity;
	JButton save_button, reset_button;

	VendorService vendorService = new VendorServiceImpl();
	VendorContactService vendorContactService = new VendorContactServiceImpl();

	// 得到屏幕大小
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// 定义父对象
	VendorContactManagerJFrame parentPanel;

	// 通过构造方法初始化数据
	public AddVendorContactJFrame(VendorContactManagerJFrame parentPanel) {
		this.parentPanel = parentPanel;
		// 初始化背景
		initBackgroundPanel();
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("添加联络人信息");
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
		JLabel title = new JLabel("添加联络人信息");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * 初始化内容面板
	 */
	private void initContentPanel() {
		/**
		 * 添加联络人的信息： 
		 * 联络人id：随机生成的32位的字符数据 
		 * 联络人名称、联系方式、邮箱、所属供应商、身份标识
		 * 之后再存入相关的数据
		 */
		contentPanel = new JPanel();
		JPanel jp1 = new JPanel();
		label_name = new JLabel("姓名    ");
		name = new JTextField(15);
		name.setFont(MyFont.TipFont);
		name.setForeground(MyColor.TipColor);
		name.setText("必填项");
		name.addFocusListener(this);
		jp1.add(label_name);
		jp1.add(name);

		JPanel jp2 = new JPanel();
		label_phone = new JLabel("联系方式");
		phone = new JTextField(15);
		phone.setFont(MyFont.TipFont);
		phone.setForeground(MyColor.TipColor);
		phone.setText("必填项");
		phone.addFocusListener(this);
		jp2.add(label_phone);
		jp2.add(phone);

		JPanel jp3 = new JPanel();
		label_email = new JLabel("电子邮箱");
		email = new JTextField(15);
		email.setFont(MyFont.TipFont);
		email.setForeground(MyColor.TipColor);
		email.setText("必填项");
		email.addFocusListener(this);
		jp3.add(label_email);
		jp3.add(email);

		JPanel jp4 = new JPanel();
		label_vendor = new JLabel("负责公司");
		vendor = new JComboBox();
		vendor.setPreferredSize(new Dimension(175, 30));
		// 初始化供应商下拉框选项
		List<Vendor> vendor_list = null;
		try {
			vendor_list = vendorService.findAllVendorList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(Vendor v : vendor_list){
			String vid = v.getVendor_id();
			String vname = v.getVendor_name();
			Item item = new Item(vid,vname);
			vendor.addItem(item);
		}
		jp4.add(label_vendor);
		jp4.add(vendor);

		JPanel jp5 = new JPanel();
		label_identity = new JLabel("身份标识");
		identity = new JComboBox();
		identity.setPreferredSize(new Dimension(175, 30));
		identity.addItem("联络员");
		identity.addItem("负责人");
		jp5.add(label_identity);
		jp5.add(identity);

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
			if (name_string.equals("必填项")) {
				JOptionPane.showMessageDialog(null, "联络人名称不能为空");
			} else if (phone_string.equals("必填项")) {
				JOptionPane.showMessageDialog(null, "联络方式不能为空");
			}else if (email_string.equals("必填项")) {
				JOptionPane.showMessageDialog(null, "电子邮箱不能为空");
			}
			else {
				// 获取下拉框选项
				Item vendor_select = (Item)vendor.getSelectedItem();
				Object identity_select = identity.getSelectedItem();
				// 创建VendorContact对象，将数据保存到数据库中
				// 联系人id是随机生成的32位字符拼接的数据
				String id = RandomGeneration.getRandom32charSeq();
				String vendorId = vendor_select.getKey();
				int identity_int = 0;
				if(String.valueOf(identity_select).equals("负责人")){
					identity_int = 1;
				}
				VendorContact vc = new VendorContact(id, name_string, phone_string,
						email_string, vendorId, identity_int);
				int choose = JOptionPane.showConfirmDialog(null, "确认添加联络人信息？");
				if (choose == 0) {
					try {
						vendorContactService.addVendorContact(vc);
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
			// 清空所有的数据
			name.setFont(MyFont.TipFont);
			name.setForeground(MyColor.TipColor);
			name.setText("必填项");
			phone.setFont(MyFont.TipFont);
			phone.setForeground(MyColor.TipColor);
			phone.setText("必填项");
			email.setFont(MyFont.TipFont);
			email.setForeground(MyColor.TipColor);
			email.setText("必填项");
			vendor.setSelectedIndex(0);
			identity.setSelectedIndex(0);
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

	// 聚焦事件
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == name) {
			if (name.getText().equals("必填项")) {
				name.setForeground(MyColor.JTextFieldColor);
				name.setFont(MyFont.JTextFieldFont);
				name.setText("");
			}
		} else if (e.getSource() == phone) {
			if (phone.getText().equals("必填项")) {
				phone.setForeground(MyColor.JTextFieldColor);
				phone.setFont(MyFont.JTextFieldFont);
				phone.setText("");
			}
		} else if (e.getSource() == email) {
			if (email.getText().equals("必填项")) {
				email.setForeground(MyColor.JTextFieldColor);
				email.setFont(MyFont.JTextFieldFont);
				email.setText("");
			}
		}
	}

	// 失去焦点事件
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == name) {
			if (name.getText().equals("")) {
				name.setFont(MyFont.TipFont);
				name.setForeground(MyColor.TipColor);
				name.setText("必填项");
			}
		} else if (e.getSource() == phone) {
			if (phone.getText().equals("")) {
				phone.setFont(MyFont.TipFont);
				phone.setForeground(MyColor.TipColor);
				phone.setText("必填项");
			}
		} else if (e.getSource() == email) {
			if (email.getText().equals("")) {
				email.setFont(MyFont.TipFont);
				email.setForeground(MyColor.TipColor);
				email.setText("必填项");
			}
		}
	}
}
