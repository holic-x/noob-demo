package com.guigu.library.fore.ui.control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.guigu.library.model.LibraryCard;
import com.guigu.library.model.Reader;
import com.guigu.library.model.ReaderClassify;
import com.guigu.library.model.Users;
import com.guigu.library.service.BookClassifyService;
import com.guigu.library.service.BooksService;
import com.guigu.library.service.LibraryCardService;
import com.guigu.library.service.ReaderClassifyService;
import com.guigu.library.service.ReaderService;
import com.guigu.library.service.StorageAreaService;
import com.guigu.library.service.UsersService;
import com.guigu.library.service.impl.BookClassifyServiceImpl;
import com.guigu.library.service.impl.BooksServiceImpl;
import com.guigu.library.service.impl.LibraryCardServiceImpl;
import com.guigu.library.service.impl.ReaderClassifyServiceImpl;
import com.guigu.library.service.impl.ReaderServiceImpl;
import com.guigu.library.service.impl.StorageAreaServiceImpl;
import com.guigu.library.service.impl.UsersServiceImpl;
import com.guigu.library.utils.Chooser;
import com.guigu.library.utils.DataValidation;
import com.guigu.library.utils.ImagePanel;
import com.guigu.library.utils.Item;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;
import com.guigu.library.utils.RandomGeneration;

public class PersonalAccountJPanel implements MouseListener {

	// 定义全局组件
	JPanel backgroundPanel, topPanel, toolPanel, centerPanel, showPanel,
			imagePanel, cardPanel, infoPanel, downPanel, buttonPanel;

	// 定义imagePanel面板使用到的标签、按钮
	JLabel label_image, label_change;
	ImageIcon icon_image;
	JTextField image_field;

	// 定义读者信息面板使用到的标签
	JLabel label_academic_num, label_name, label_sex, label_birthday,
			label_idCard, label_phone, label_email, label_descr,
			label_classify;
	JTextField academic_num, name, birthday, idCard, phone, email;
	ButtonGroup sex;
	JRadioButton nan, nv;
	JTextArea descr;
	JComboBox classify;
	Chooser birthday_chooser;

	// 定义读者借阅证面板信息面板使用到的标签
	JLabel label_id, label_num, label_handle_date, label_valid_till,
			label_loss_state, label_disable_state;
	JTextField id, num, handle_date, valid_till, loss_state, disable_state;

	JButton save, reset;

	JLabel tool_loss;

	// 定义条形码文本框(通过输入书本条形码显示相应的书本信息)
	JTextField keyword;

	// 定义相应的service
	ReaderService readerService = new ReaderServiceImpl();
	BooksService booksService = new BooksServiceImpl();
	UsersService usersService = new UsersServiceImpl();
	ReaderClassifyService readerClassifyService = new ReaderClassifyServiceImpl();
	LibraryCardService libraryCardService = new LibraryCardServiceImpl();

	BookClassifyService bookClassifyService = new BookClassifyServiceImpl();
	StorageAreaService storageAreaService = new StorageAreaServiceImpl();

	Users user;
	// 定义当前使用该账号的读者
	Reader r;

	/**
	 * 通过构造方法完成初始化
	 */
	public PersonalAccountJPanel(Users user) {
		this.user = user;
		initBackgroundPanel();
	}

	/**
	 * 初始化背景面板
	 */
	public void initBackgroundPanel() {
		backgroundPanel = new JPanel(new BorderLayout());
		// 初始化布局
		initTopPanel();// 初始化顶部菜单条、工具条
		initCenterPanel();// 初始化中间的数据显示面板
		initDownPanel();// 初始化下方的按钮组合
	}

	/**
	 * 初始化顶部的菜单条
	 */
	private void initTopPanel() {
		topPanel = new JPanel(new BorderLayout());
		initToolPanel();
	}

	/**
	 * 初始化工具条面板
	 */
	public void initToolPanel() {

		toolPanel = new JPanel(new BorderLayout());
		// 添加增删改工具
		Icon loss_icon = new ImageIcon("icons/toolImage/loss.png");
		tool_loss = new JLabel(loss_icon);
		tool_loss.setToolTipText("借阅证挂失");
		tool_loss.addMouseListener(this);

		// 将初始化完成的工具加载到工具条面板中
		JPanel west = new JPanel();
		west.add(tool_loss);

		toolPanel.add(west, BorderLayout.WEST);

		topPanel.add(toolPanel, BorderLayout.SOUTH);

		// 将顶部面板加载到背景面板中
		backgroundPanel.add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * 初始化中间的数据显示面板
	 */
	private void initCenterPanel() {
		centerPanel = new JPanel(new BorderLayout());

		initShowPanel();
		initInfoPanel();

		backgroundPanel.add(centerPanel, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	private void initShowPanel() {
		showPanel = new JPanel(new BorderLayout());
		initImagePanel();
		initCardPanel();
		centerPanel.add(showPanel, BorderLayout.WEST);
		backgroundPanel.validate();
	}

	/**
	 * 初始化图像面板
	 */
	private void initImagePanel() {
		imagePanel = new JPanel(new BorderLayout());

		JPanel jp = new JPanel();
		image_field = new JTextField(25);
		image_field.setForeground(MyColor.TipColor);
		image_field.setFont(MyFont.TipFont);
		image_field.setEditable(false);
		label_change = new JLabel("更换图片");
		label_change.addMouseListener(this);

		// 加载头像信息，此处是作为静态图片，还需更改
		icon_image = new ImageIcon("icons/toolImage/static_icon.png");
		label_image = new JLabel(icon_image);

		jp.add(image_field);
		jp.add(label_change);

		imagePanel.add(label_image, BorderLayout.CENTER);
		imagePanel.add(jp, BorderLayout.SOUTH);
		showPanel.add(imagePanel, BorderLayout.CENTER);
	}

	/**
	 * 初始化借阅证信息面板
	 */
	private void initCardPanel() {
		cardPanel = new JPanel();
		// 加载借阅证信息
		JPanel jp1 = new JPanel();
		label_id = new JLabel("借阅证id");
		id = new JTextField(15);
		id.setFont(MyFont.JTextFieldFont);
		id.setForeground(MyColor.JTextFieldColor);
		id.setEditable(false);
		label_num = new JLabel("借阅编号");
		num = new JTextField(15);
		num.setFont(MyFont.JTextFieldFont);
		num.setForeground(MyColor.JTextFieldColor);
		num.setEditable(false);
		jp1.add(label_id);
		jp1.add(id);
		jp1.add(label_num);
		jp1.add(num);

		JPanel jp2 = new JPanel();
		label_handle_date = new JLabel("办理日期");
		handle_date = new JTextField(15);
		handle_date.setFont(MyFont.JTextFieldFont);
		handle_date.setForeground(MyColor.JTextFieldColor);
		handle_date.setEditable(false);

		label_valid_till = new JLabel("有效期至");
		valid_till = new JTextField(15);
		valid_till.setFont(MyFont.JTextFieldFont);
		valid_till.setForeground(MyColor.JTextFieldColor);
		valid_till.setEditable(false);
		jp2.add(label_handle_date);
		jp2.add(handle_date);
		jp2.add(label_valid_till);
		jp2.add(valid_till);

		JPanel jp3 = new JPanel();
		label_loss_state = new JLabel("挂失状态");
		loss_state = new JTextField(15);
		loss_state.setFont(MyFont.JTextFieldFont);
		loss_state.setForeground(MyColor.JTextFieldColor);
		loss_state.setEditable(false);

		label_disable_state = new JLabel("禁用状态");
		disable_state = new JTextField(15);
		disable_state.setFont(MyFont.JTextFieldFont);
		disable_state.setForeground(MyColor.JTextFieldColor);
		disable_state.setEditable(false);
		jp3.add(label_loss_state);
		jp3.add(loss_state);
		jp3.add(label_disable_state);
		jp3.add(disable_state);

		// 数据回显
		this.echoCardData();
		Box ver = Box.createVerticalBox();
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp3);

		cardPanel.add(ver);
		showPanel.add(cardPanel, BorderLayout.SOUTH);
	}

	/**
	 * 初始化信息面板
	 */
	private void initInfoPanel() {
		infoPanel = new JPanel();

		JPanel jp1 = new JPanel();
		label_academic_num = new JLabel("学工编号");
		academic_num = new JTextField(25);
		academic_num.setEditable(false);
		academic_num.setFont(MyFont.JTextFieldFont);
		academic_num.setForeground(MyColor.JTextFieldColor);
		jp1.add(label_academic_num);
		jp1.add(academic_num);

		JPanel jp2 = new JPanel();
		label_name = new JLabel("读者姓名");
		name = new JTextField(25);
		name.setFont(MyFont.JTextFieldFont);
		name.setForeground(MyColor.JTextFieldColor);
		jp2.add(label_name);
		jp2.add(name);

		JPanel jp3 = new JPanel();
		label_sex = new JLabel("读者性别");
		sex = new ButtonGroup();
		nan = new JRadioButton("男");
		nv = new JRadioButton("女");
		sex.add(nan);
		sex.add(nv);
		jp3.add(label_sex);
		jp3.add(Box.createHorizontalStrut(50));
		jp3.add(nan);
		jp3.add(Box.createHorizontalStrut(50));
		jp3.add(nv);

		JPanel jp4 = new JPanel();
		label_birthday = new JLabel("出生日期");
		birthday = new JTextField(25);
		birthday_chooser = Chooser.getInstance();
		birthday_chooser.register(birthday);
		birthday.setFont(MyFont.JTextFieldFont);
		birthday.setForeground(MyColor.JTextFieldColor);
		jp4.add(label_birthday);
		jp4.add(birthday);

		JPanel jp5 = new JPanel();
		label_idCard = new JLabel("身份证号");
		idCard = new JTextField(25);
		idCard.setFont(MyFont.JTextFieldFont);
		idCard.setForeground(MyColor.JTextFieldColor);
		jp5.add(label_idCard);
		jp5.add(idCard);

		JPanel jp6 = new JPanel();
		label_phone = new JLabel("联系方式");
		phone = new JTextField(25);
		phone.setFont(MyFont.JTextFieldFont);
		phone.setForeground(MyColor.JTextFieldColor);
		jp6.add(label_phone);
		jp6.add(phone);

		JPanel jp7 = new JPanel();
		label_email = new JLabel("电子邮箱");
		email = new JTextField(25);
		email.setFont(MyFont.JTextFieldFont);
		email.setForeground(MyColor.JTextFieldColor);
		jp7.add(label_email);
		jp7.add(email);

		JPanel jp8 = new JPanel();
		label_classify = new JLabel("所属分类");
		classify = new JComboBox();
		classify.setPreferredSize(new Dimension(280, 30));
		classify.setFont(MyFont.JTextFieldFont);
		classify.setForeground(MyColor.JTextFieldColor);
		classify.setEnabled(false);
		jp8.add(label_classify);
		jp8.add(classify);

		JPanel jp9 = new JPanel();
		label_descr = new JLabel("读者简述");
		descr = new JTextArea(5, 25);
		descr.setFont(MyFont.JTextFieldFont);
		descr.setForeground(MyColor.JTextFieldColor);
		descr.setLineWrap(true);
		jp9.add(label_descr);
		jp9.add(descr);

		// 数据回显
		this.echoInfoData();
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
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp6);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp7);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp8);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp9);

		infoPanel.add(ver);
		centerPanel.add(infoPanel, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	/**
	 * 初始化下方的数据面板
	 */
	private void initDownPanel() {
		downPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		// 初始化按钮组合
		initButtonPanel();
		backgroundPanel.add(downPanel, BorderLayout.SOUTH);
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
		buttonPanel.add(Box.createHorizontalStrut(30));
		buttonPanel.add(reset);
		downPanel.add(buttonPanel);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == label_change) {
			// 更换图片路径
			// 弹出文件选择框
			JFileChooser chooser = new JFileChooser();
			// 后缀名过滤器
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"表格文件(*.png)", "png");
			chooser.setFileFilter(filter);
			// 下面的方法将阻塞，直到【用户按下保存按钮且“文件名”文本框不为空】或【用户按下取消按钮】
			int option = chooser.showSaveDialog(null);
			if (option == JFileChooser.APPROVE_OPTION) { // 假如用户选择了保存
				File file = chooser.getSelectedFile();
				String fname = chooser.getName(file); // 从文件名输入框中获取文件名
				// 假如用户填写的文件名不带我们制定的后缀名，那么我们给它添上后缀
				if (fname.indexOf(".png") == -1) {
					file = new File(chooser.getCurrentDirectory(), fname
							+ ".png");
				}
				icon_image = new ImageIcon(file.getAbsolutePath());
				image_field.setText(file.getAbsolutePath());
				// 设置图片大小与标签大小相适应
				int width = 200,height = 200;	//设置图片和JLable的宽度和高度
				/**
				 * 设置图片大小与标签设置的大小相适应
				 * 得到此图标的 Image（icon_image.getImage()）；
				 * 在此基础上创建它的缩放版本，缩放版本的宽度，高度与JLble一致
				 * （getScaledInstance(width, height,Image.SCALE_DEFAULT )）
				 * 最后该图像就设置为得到的缩放版本（image.setImage）
				*/
				icon_image.setImage(icon_image.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT ));
				//可以用下面的代码来代替
				// ImageIcon image = new ImageIcon(String filename);
				// Image img = image.getImage();
				// img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
				// image.setImage(img);
				label_image.setIcon(icon_image);
				label_image.setSize(width, height);
				backgroundPanel.validate();
			}

		} else if (e.getSource() == tool_loss) {
			try {
				if (r != null) {
					// 借阅证挂失
					// 根据借阅证id获取详细的借阅证信息
					LibraryCard lc = null;
					lc = libraryCardService.getLibraryCardById(r.getCard_id());
					int loss_state_int = lc.getLoss_state();
					int disable_state_int = lc.getDisable_state();
					if (loss_state_int == 0) {
						int choose = JOptionPane.showConfirmDialog(null,
								"确认挂失该借阅证？");
						if (choose == 0) {
							// 挂失、并禁用,刷新数据面板
							lc.setLoss_state(1);
							lc.setDisable_state(1);
							libraryCardService.updateLibraryCard(lc);
							JOptionPane.showMessageDialog(null,
									"当前借阅证已挂失，将无法使用！");
						}
					} else if (loss_state_int == 1) {
						JOptionPane.showMessageDialog(null,
								"当前借阅证已挂失，不能重复操作，如需取消请到管理员处进行相关操作！");
					}
				}
			} catch (HeadlessException | SQLException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == save) {
			// 数据保存
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
						academic_num_string = RandomGeneration
								.getRandom8numSeq();
					} else {
						JOptionPane.showMessageDialog(null,
								"除普通游客身份之外的读者学工编号不能为空");
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
							String academic_num_string_old = r
									.getAcademic_num();
							String user_id = r.getUser_id();
							// 根据账号id获取账号信息
							Users user = null;
							try {
								user = usersService.getUsersById(user_id);
							} catch (SQLException e2) {
								e2.printStackTrace();
							}

							String user_name = user.getUser_name();
							String user_password = user.getUser_password();
							if (!academic_num_string_old
									.equals(academic_num_string)) {
								// 更新账号用户信息（初始化为用户名为学工编号，密码为000000）
								// 根据学工编号作为对应账户的用户名，初始密码默认为000000,初始默认为禁用状态
								user_name = academic_num_string;
								user_password = "000000";
							}
							// 读者id、条形码、借阅证信息不变
							String reader_id = r.getReader_id();
							String barcode = r.getBarcode();
							String libraryCardId = r.getCard_id();
							user.setUser_name(user_name);
							user.setUser_password(user_password);
							user.setUser_limits(0);
							// 创建读者对象，加载数据进行保存
							Reader r = new Reader(reader_id, barcode,
									academic_num_string, name_string,
									sex_string, birthday_string, idCard_string,
									phone_string, email_string, descr_string,
									user_id, classify_num, libraryCardId);

							// 调用方法保存数据
							try {
								usersService.updateUsers(user);
								readerService.updateReader(r);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							// 输出提示信息，隐藏子页面，刷新.主面板
							JOptionPane.showMessageDialog(null,
									"图书信息保存成功！登录账号重置为" + "账号：" + user_name
											+ "密码:" + user_password);
							// 刷新当前数据面板
							this.refreshBackgroundPanel();
						} else {
							// 回退，事务回滚，以保证序列正常执行，此处还没想好如何更好地实现
						}
					}
				}
			}
		} else if (e.getSource() == reset) {
			// 数据回显
			this.echoInfoData();
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

	// 刷新整个背景面板
	public void refreshBackgroundPanel() {
		backgroundPanel.removeAll();
		initTopPanel();
		initCenterPanel();
		initDownPanel();
		backgroundPanel.validate();
	}

	// 回显借阅证信息
	public void echoCardData() {
		// 根据当前的登录的账号id获取读者身份信息
		try {
			r = readerService.getReaderByUserId(this.user.getUser_id());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (r != null) {
			// 根据父表格中当前指定的行获取读者的数据信息：
			String libraryCard_id_string = r.getCard_id();
			// 根据借阅证id获取详细的借阅证信息
			LibraryCard lc = null;
			try {
				lc = libraryCardService
						.getLibraryCardById(libraryCard_id_string);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// 数据回显
			id.setText(lc.getCard_id());
			id.setToolTipText(lc.getCard_id());
			num.setText(lc.getCard_num());
			num.setToolTipText(lc.getCard_num());
			handle_date.setText(lc.getHandle_date().substring(0, 10));
			handle_date.setToolTipText(lc.getHandle_date().substring(0, 10));
			valid_till.setText(lc.getValid_till().substring(0, 10));
			valid_till.setToolTipText(lc.getValid_till().substring(0, 10));
			int loss_state_int = lc.getLoss_state();
			if (loss_state_int == 0) {
				loss_state.setText("正常使用");
				loss_state.setToolTipText("正常使用");
			} else if (loss_state_int == 1) {
				loss_state.setText("挂失状态");
				loss_state.setToolTipText("挂失状态");
			}
			int disable_state_int = lc.getDisable_state();
			if (disable_state_int == 0) {
				disable_state.setText("正常使用");
				disable_state.setToolTipText("正常使用");
			} else if (disable_state_int == 1) {
				disable_state.setText("禁用状态");
				disable_state.setToolTipText("禁用状态");
			}
		}
	}

	// 根据当前输入的读者条形码信息回显读者数据信息
	public void echoInfoData() {
		// 根据当前的登录的账号id获取读者身份信息
		try {
			r = readerService.getReaderByUserId(this.user.getUser_id());
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
