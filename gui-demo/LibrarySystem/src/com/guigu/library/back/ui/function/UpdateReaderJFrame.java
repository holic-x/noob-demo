package com.guigu.library.back.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.guigu.library.back.ui.control.ReaderArchivesManagerJPanel;
import com.guigu.library.model.Reader;
import com.guigu.library.model.ReaderClassify;
import com.guigu.library.model.Users;
import com.guigu.library.service.LibraryCardService;
import com.guigu.library.service.ReaderClassifyService;
import com.guigu.library.service.ReaderService;
import com.guigu.library.service.UsersService;
import com.guigu.library.service.impl.LibraryCardServiceImpl;
import com.guigu.library.service.impl.ReaderClassifyServiceImpl;
import com.guigu.library.service.impl.ReaderServiceImpl;
import com.guigu.library.service.impl.UsersServiceImpl;
import com.guigu.library.utils.Chooser;
import com.guigu.library.utils.DataValidation;
import com.guigu.library.utils.Item;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;
import com.guigu.library.utils.RandomGeneration;

public class UpdateReaderJFrame extends JFrame implements MouseListener {

	// 定义全局组件
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_academic_num, label_name, label_sex, label_birthday,
			label_idCard, label_phone, label_email, label_descr,
			label_classify;
	JTextField academic_num, name, birthday, idCard, phone, email;
	ButtonGroup sex;
	JRadioButton nan, nv;
	JTextArea descr;
	JComboBox classify;
	Chooser birthday_chooser;

	JButton save, reset;
	// 得到屏幕大小
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// 定义service
	ReaderClassifyService readerClassifyService = new ReaderClassifyServiceImpl();
	ReaderService readerService = new ReaderServiceImpl();
	UsersService usersService = new UsersServiceImpl();
	LibraryCardService libraryCardService = new LibraryCardServiceImpl();

	// 定义父对象、父表格、选中的行
	ReaderArchivesManagerJPanel parentPanel;
	JTable parentTable;
	int selectedRow;

	// 通过构造方法初始化数据
	public UpdateReaderJFrame(ReaderArchivesManagerJPanel parentPanel,
			JTable parentTable, int selectedRow) {
		this.parentPanel = parentPanel;
		this.parentTable = parentTable;
		this.selectedRow = selectedRow;
		// 初始化背景
		initBackgroundPanel();
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("修改读者信息");
		this.setSize(600, 450);
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
		JLabel title = new JLabel("修改读者信息");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * 初始化内容面板
	 */
	private void initContentPanel() {

		contentPanel = new JPanel();

		JPanel jp1 = new JPanel();
		label_academic_num = new JLabel("学工编号");
		academic_num = new JTextField(15);
		academic_num.setFont(MyFont.JTextFieldFont);
		academic_num.setForeground(MyColor.JTextFieldColor);

		label_name = new JLabel("读者姓名");
		name = new JTextField(15);
		name.setFont(MyFont.JTextFieldFont);
		name.setForeground(MyColor.JTextFieldColor);
		jp1.add(label_academic_num);
		jp1.add(academic_num);
		jp1.add(label_name);
		jp1.add(name);

		JPanel jp2 = new JPanel();
		label_sex = new JLabel("读者性别");
		sex = new ButtonGroup();
		nan = new JRadioButton("男");
		nv = new JRadioButton("女");
		sex.add(nan);
		sex.add(nv);

		label_birthday = new JLabel("出生日期");
		birthday = new JTextField(15);
		birthday_chooser = Chooser.getInstance();
		birthday_chooser.register(birthday);
		birthday.setFont(MyFont.JTextFieldFont);
		birthday.setForeground(MyColor.JTextFieldColor);

		jp2.add(label_sex);
		jp2.add(nan);
		jp2.add(nv);
		jp2.add(label_birthday);
		jp2.add(birthday);

		JPanel jp3 = new JPanel();
		label_idCard = new JLabel("身份证号");
		idCard = new JTextField(15);
		idCard.setFont(MyFont.JTextFieldFont);
		idCard.setForeground(MyColor.JTextFieldColor);

		label_phone = new JLabel("联系方式");
		phone = new JTextField(15);
		phone.setFont(MyFont.JTextFieldFont);
		phone.setForeground(MyColor.JTextFieldColor);
		jp3.add(label_idCard);
		jp3.add(idCard);
		jp3.add(label_phone);
		jp3.add(phone);

		JPanel jp4 = new JPanel();
		label_email = new JLabel("电子邮箱");
		email = new JTextField(15);
		email.setFont(MyFont.JTextFieldFont);
		email.setForeground(MyColor.JTextFieldColor);

		label_classify = new JLabel("所属分类");
		classify = new JComboBox();
		classify.setPreferredSize(new Dimension(175, 30));
		classify.setFont(MyFont.JTextFieldFont);
		classify.setForeground(MyColor.JTextFieldColor);
		jp4.add(label_email);
		jp4.add(email);
		jp4.add(label_classify);
		jp4.add(classify);

		JPanel jp5 = new JPanel();
		label_descr = new JLabel("读者简述");
		descr = new JTextArea(5, 30);
		descr.setFont(MyFont.JTextFieldFont);
		descr.setForeground(MyColor.JTextFieldColor);
		descr.setLineWrap(true);
		jp5.add(label_descr);
		jp5.add(descr);

		// 数据回显
		this.echoData();
		Box ver = Box.createVerticalBox();
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp3);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp4);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp5);
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
		// 获取下拉框和单选框选项
		String sex_string = "";
		if (nan.isSelected()) {
			sex_string = "男";
		} else if (nv.isSelected()) {
			sex_string = "女";
		}
		int classify_num = 0;
		Item classify_item = (Item) classify.getSelectedItem();
		classify_num = Integer.valueOf(classify_item.getKey());

		// 获取当前文本框的数据
		String academic_num_string = academic_num.getText();
		String name_string = name.getText();
		String birthday_string = birthday.getText();
		String idCard_string = idCard.getText();
		String phone_string = phone.getText();
		String email_string = email.getText();
		String descr_string = descr.getText();

		if (e.getSource() == save) {
			if (academic_num_string.equals("")) {
				if (classify_num == 0) {
					// 如果是普通游客，则自动随机生成8位int类型的序列，否则提示输入信息
					academic_num_string = RandomGeneration.getRandom8numSeq();
				} else {
					JOptionPane.showMessageDialog(null, "除普通游客身份之外的读者学工编号不能为空");
				}
			}
			if (name_string.equals("")) {
				JOptionPane.showMessageDialog(null, "读者姓名不能为空！");
			} else if (birthday_string.equals("")) {
				JOptionPane.showMessageDialog(null, "读者生日不能为空！");
			} else if (idCard_string.equals("")) {
				JOptionPane.showMessageDialog(null, "身份证号不能为空！");
			} else if (phone_string.equals("")) {
				JOptionPane.showMessageDialog(null, "联系方式不能为空！");
			} else if (email_string.equals("")) {
				JOptionPane.showMessageDialog(null, "电子邮箱不能为空！");
			} else {
				// 提供默认值 要注意逻辑判断
				if (descr_string.equals("")) {
					// 指定相应的信息
					descr_string = "这个读者很懒，什么都没有留下";
				}
				// 对数据进行验证
				if (!DataValidation.isBigDecimal(academic_num_string)) {
					JOptionPane.showMessageDialog(null, "学工编号输入数据格式有误！");
				} else if (!DataValidation.isValidIdCard(idCard_string)) {
					JOptionPane.showMessageDialog(null, "身份证号输入数据格式有误！");
				} else if (!DataValidation.isValidPhone(phone_string)) {
					JOptionPane.showMessageDialog(null, "联系方式输入数据格式有误！");
				} else if (!DataValidation.isValidEmail(email_string)) {
					JOptionPane.showMessageDialog(null, "电子邮箱输入数据格式有误！");
				} else {
					int choose = JOptionPane.showConfirmDialog(null,
							"确认修改读者信息？");
					if (choose == 0) {
						// 只更新读者的基本数据信息，借阅证信息、读者账号信息基本不变
						// 但由于账号一开始的用户名是与学工编号相关联，因此此处需考虑学工编号发生改变后亦重置用户登录账号信息
						String academic_num_string_old = parentTable.getValueAt(selectedRow, 2).toString();
						String user_id = parentTable.getValueAt(selectedRow, 10).toString();
						// 根据账号id获取账号信息
						Users user = null;
						try {
							user = usersService.getUsersById(user_id);
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
						
						String user_name = user.getUser_name();
						String user_password = user.getUser_password();
						if(!academic_num_string_old.equals(academic_num_string)){
							// 更新账号用户信息（初始化为用户名为学工编号，密码为000000）
							// 根据学工编号作为对应账户的用户名，初始密码默认为000000,初始默认为禁用状态
							user_name = academic_num_string;
							user_password = "000000";
						}
						// 读者id、条形码、借阅证信息不变
						String reader_id = parentTable.getValueAt(selectedRow, 0).toString();
						String barcode = parentTable.getValueAt(selectedRow, 1).toString();
						String libraryCardId = parentTable.getValueAt(selectedRow, 14).toString();
						user.setUser_name(user_name);
						user.setUser_password(user_password);
						user.setUser_limits(0);
						// 创建读者对象，加载数据进行保存
						Reader r = new Reader(reader_id, barcode,
								academic_num_string, name_string, sex_string,
								birthday_string, idCard_string, phone_string,
								email_string, descr_string, user_id,
								classify_num, libraryCardId);

						// 调用方法保存数据
						try {
							usersService.updateUsers(user);
							readerService.updateReader(r);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						// 输出提示信息，隐藏子页面，刷新.主面板
						JOptionPane.showMessageDialog(null, "图书信息保存成功！登录账号重置为"+"账号："+user_name+"密码:"+user_password);
						this.setVisible(false);
						parentPanel.refreshTablePanel();
					} else {
						// 回退，事务回滚，以保证序列正常执行，此处还没想好如何更好地实现
					}
				}
			}
		} else if (e.getSource() == reset) {
			// 重置数据信息
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
	 * 定义数据回显方法
	 */
	public void echoData() {
		// 根据父表格中选中的行获取读者的数据信息：
		String read_id = parentTable.getValueAt(selectedRow, 0).toString();
		// 根据读者id获取读者的数据信息
		Reader r = null;
		try {
			r = readerService.getReaderById(read_id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (r != null) {
			String academic_num_string = r.getAcademic_num();
			String name_string = r.getReader_name();
			String birthday_string = r.getReader_birthday().substring(0, 10);
			String idCard_string = r.getReader_idCard();
			String phone_string = r.getReader_phone();
			String email_string = r.getReader_email();
			String sex_string = r.getReader_sex();
			String descr_string = r.getReader_descr();
			int classify_num_int = r.getClassify_num();
			// 数据回显
			academic_num.setText(academic_num_string);
			name.setText(name_string);
			birthday.setText(birthday_string);
			idCard.setText(idCard_string);
			phone.setText(phone_string);
			email.setText(email_string);
			descr.setText(descr_string);
			if (sex_string.equals("男")) {
				nan.setSelected(true);
			} else if (sex_string.equals("女")) {
				nv.setSelected(true);
			}

			// 重新装载分类信息
			classify.removeAllItems();
			List<ReaderClassify> rc_list = null;
			try {
				rc_list = readerClassifyService.findAllReaderClassify();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < rc_list.size(); i++) {
				int sign = 0;
				int rc_num = rc_list.get(i).getClassify_num();
				String rc_name = rc_list.get(i).getClassify_name();
				Item item = new Item(rc_num + "", rc_name);
				classify.addItem(item);
				if (classify_num_int == rc_num) {
					sign = i;
					classify.setSelectedIndex(sign);
				}
			}
		}
	}
}