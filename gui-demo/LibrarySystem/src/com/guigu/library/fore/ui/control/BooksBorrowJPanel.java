package com.guigu.library.fore.ui.control;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.guigu.library.model.BookClassify;
import com.guigu.library.model.Books;
import com.guigu.library.model.Borrowing;
import com.guigu.library.model.LibraryCard;
import com.guigu.library.model.Reader;
import com.guigu.library.model.ReaderClassify;
import com.guigu.library.model.StorageArea;
import com.guigu.library.model.Users;
import com.guigu.library.service.BookClassifyService;
import com.guigu.library.service.BooksService;
import com.guigu.library.service.BorrowingService;
import com.guigu.library.service.LibraryCardService;
import com.guigu.library.service.ReaderClassifyService;
import com.guigu.library.service.ReaderService;
import com.guigu.library.service.StorageAreaService;
import com.guigu.library.service.UsersService;
import com.guigu.library.service.impl.BookClassifyServiceImpl;
import com.guigu.library.service.impl.BooksServiceImpl;
import com.guigu.library.service.impl.BorrowingServiceImpl;
import com.guigu.library.service.impl.LibraryCardServiceImpl;
import com.guigu.library.service.impl.ReaderClassifyServiceImpl;
import com.guigu.library.service.impl.ReaderServiceImpl;
import com.guigu.library.service.impl.StorageAreaServiceImpl;
import com.guigu.library.service.impl.UsersServiceImpl;
import com.guigu.library.utils.DataValidation;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;
import com.guigu.library.utils.RandomGeneration;

public class BooksBorrowJPanel implements MouseListener {

	// 定义全局组件
	JPanel backgroundPanel, topPanel, toolPanel, centerPanel, readerInfoPanel,
			bookInfoPanel, downPanel, buttonPanel;

	// 定义ReaderInfoPanel（读者信息）使用到的标签和文本框
	JLabel label_reader_barcode, label_academic_num, label_reader_name,
			label_account, label_reader_classify, label_card_num,
			label_available_num, label_maximum, label_time_limit;
	JTextField reader_barcode, academic_num, reader_name, account,
			reader_classify, card_num, available_num, maximum, time_limit;

	// 定义BookInfoPanel（书籍信息）使用到的标签和文本框
	JLabel label_isbn, label_book_name, label_book_classify, label_area,
			label_author, label_translator, label_publish_date, label_press,
			label_price;
	JTextField isbn, book_name, book_classify, area, author, translator,
			publish_date, press, price;

	// 创建工具条使用到的标签
	JLabel tool_borrow, search_barcode;

	// 定义条形码文本框(通过输入书本条形码显示相应的书本信息)
	JTextField keyword;

	// 定义按钮工具
	JButton reset;

	// 定义读者、借阅书籍
	Reader r = null;
	Books book = null;

	// 定义相应的service
	ReaderService readerService = new ReaderServiceImpl();
	BooksService booksService = new BooksServiceImpl();
	UsersService usersService = new UsersServiceImpl();
	ReaderClassifyService readerClassifyService = new ReaderClassifyServiceImpl();
	LibraryCardService libraryCardService = new LibraryCardServiceImpl();
	BorrowingService borrowingService = new BorrowingServiceImpl();
	BookClassifyService bookClassifyService = new BookClassifyServiceImpl();
	StorageAreaService storageAreaService = new StorageAreaServiceImpl();

	Users user;

	/**
	 * 通过构造方法完成初始化
	 */
	public BooksBorrowJPanel(Users user) {
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
		Icon icon_borrow = new ImageIcon("icons/toolImage/borrow.png");
		tool_borrow = new JLabel(icon_borrow);
		tool_borrow.setToolTipText("图书借阅");// 设置鼠标移动时的显示内容
		tool_borrow.addMouseListener(this);// 添加鼠标监听

		// 将初始化完成的工具加载到工具条面板中
		JPanel west = new JPanel();
		west.add(tool_borrow);

		JPanel east = new JPanel();
		search_barcode = new JLabel();
		Icon barcode_icon = new ImageIcon("icons/toolImage/barcode.png");
		search_barcode.setIcon(barcode_icon);
		keyword = new JTextField(20);
		keyword.setFont(MyFont.TipFont);
		keyword.setForeground(MyColor.TipColor);
		keyword.setText("条形码|");
		keyword.setPreferredSize(new Dimension(150, 30));
		keyword.addMouseListener(this);
		// 文本框设置实时数据监控获取商品信息
		keyword.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				echoBookData();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				echoBookData();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				echoBookData();
			}
		});
		east.add(search_barcode);
		east.add(keyword);

		toolPanel.add(west, BorderLayout.WEST);
		toolPanel.add(east, BorderLayout.EAST);

		topPanel.add(toolPanel, BorderLayout.SOUTH);

		// 将顶部面板加载到背景面板中
		backgroundPanel.add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * 初始化中间的数据显示面板
	 */
	private void initCenterPanel() {
		centerPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		// 初始化读者信息显示面板
		initReaderInfoPanel();
		// 初始化书籍信息显示面板
		initBookInfoPanel();
		backgroundPanel.add(centerPanel, BorderLayout.CENTER);
	}

	/**
	 * 初始化读者信息显示面板
	 */
	private void initReaderInfoPanel() {
		readerInfoPanel = new JPanel();

		JPanel jp1 = new JPanel();
		label_reader_barcode = new JLabel("条形码  ");
		reader_barcode = new JTextField(25);
		reader_barcode.setText("请输入读者条形码");
		reader_barcode.addMouseListener(this);
		reader_barcode.setFont(MyFont.TipFont);
		reader_barcode.setForeground(MyColor.TipColor);
		reader_barcode.getDocument().addDocumentListener(
				new DocumentListener() {
					@Override
					public void removeUpdate(DocumentEvent e) {
						echoReaderData();
					}

					@Override
					public void insertUpdate(DocumentEvent e) {
						echoReaderData();
					}

					@Override
					public void changedUpdate(DocumentEvent e) {

					}
				});
		jp1.add(label_reader_barcode);
		jp1.add(reader_barcode);

		JPanel jp2 = new JPanel();
		label_academic_num = new JLabel("学工编号");
		academic_num = new JTextField(25);
		academic_num.setFont(MyFont.JTextFieldFont);
		academic_num.setForeground(MyColor.JTextFieldColor);
		academic_num.setEditable(false);
		jp2.add(label_academic_num);
		jp2.add(academic_num);

		JPanel jp3 = new JPanel();
		label_reader_name = new JLabel("读者姓名");
		reader_name = new JTextField(25);
		reader_name.setFont(MyFont.JTextFieldFont);
		reader_name.setForeground(MyColor.JTextFieldColor);
		reader_name.setEditable(false);
		jp3.add(label_reader_name);
		jp3.add(reader_name);

		JPanel jp4 = new JPanel();
		label_account = new JLabel("读者账号");
		account = new JTextField(25);
		account.setFont(MyFont.JTextFieldFont);
		account.setForeground(MyColor.JTextFieldColor);
		account.setEditable(false);
		jp4.add(label_account);
		jp4.add(account);

		JPanel jp5 = new JPanel();
		label_reader_classify = new JLabel("读者分类");
		reader_classify = new JTextField(25);
		reader_classify.setFont(MyFont.JTextFieldFont);
		reader_classify.setForeground(MyColor.JTextFieldColor);
		reader_classify.setEditable(false);
		jp5.add(label_reader_classify);
		jp5.add(reader_classify);

		JPanel jp6 = new JPanel();
		label_card_num = new JLabel("借阅证号");
		card_num = new JTextField(25);
		card_num.setFont(MyFont.JTextFieldFont);
		card_num.setForeground(MyColor.JTextFieldColor);
		card_num.setEditable(false);
		jp6.add(label_card_num);
		jp6.add(card_num);

		JPanel jp7 = new JPanel();
		label_available_num = new JLabel("可借阅数");
		available_num = new JTextField(25);
		available_num.setFont(MyFont.JTextFieldFont);
		available_num.setForeground(MyColor.JTextFieldColor);
		available_num.setEditable(false);
		jp7.add(label_available_num);
		jp7.add(available_num);

		JPanel jp8 = new JPanel();
		label_maximum = new JLabel("最大借阅");
		maximum = new JTextField(25);
		maximum.setFont(MyFont.JTextFieldFont);
		maximum.setForeground(MyColor.JTextFieldColor);
		maximum.setEditable(false);
		jp8.add(label_maximum);
		jp8.add(maximum);

		JPanel jp9 = new JPanel();
		label_time_limit = new JLabel("借阅期限");
		time_limit = new JTextField(25);
		time_limit.setFont(MyFont.JTextFieldFont);
		time_limit.setForeground(MyColor.JTextFieldColor);
		time_limit.setEditable(false);
		jp9.add(label_time_limit);
		jp9.add(time_limit);

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

		readerInfoPanel.add(ver);
		centerPanel.add(readerInfoPanel, BorderLayout.WEST);
		// 将组件加载到背景中
		backgroundPanel.validate();
	}

	/**
	 * 初始化书籍信息显示面板
	 */
	private void initBookInfoPanel() {
		bookInfoPanel = new JPanel();

		JPanel jp1 = new JPanel();
		label_isbn = new JLabel("ISBN    ");
		isbn = new JTextField(25);
		isbn.setFont(MyFont.JTextFieldFont);
		isbn.setForeground(MyColor.JTextFieldColor);
		isbn.setEditable(false);
		jp1.add(label_isbn);
		jp1.add(isbn);

		JPanel jp2 = new JPanel();
		label_book_name = new JLabel("图书名称");
		book_name = new JTextField(25);
		book_name.setFont(MyFont.JTextFieldFont);
		book_name.setForeground(MyColor.JTextFieldColor);
		book_name.setEditable(false);
		jp2.add(label_book_name);
		jp2.add(book_name);

		JPanel jp3 = new JPanel();
		label_book_classify = new JLabel("所属分类");
		book_classify = new JTextField(25);
		book_classify.setFont(MyFont.JTextFieldFont);
		book_classify.setForeground(MyColor.JTextFieldColor);
		book_classify.setEditable(false);
		jp3.add(label_book_classify);
		jp3.add(book_classify);

		JPanel jp4 = new JPanel();
		label_area = new JLabel("存储区域");
		area = new JTextField(25);
		area.setFont(MyFont.JTextFieldFont);
		area.setForeground(MyColor.JTextFieldColor);
		area.setEditable(false);
		jp4.add(label_area);
		jp4.add(area);

		JPanel jp5 = new JPanel();
		label_author = new JLabel("图书作者");
		author = new JTextField(25);
		author.setFont(MyFont.JTextFieldFont);
		author.setForeground(MyColor.JTextFieldColor);
		author.setEditable(false);
		jp5.add(label_author);
		jp5.add(author);

		JPanel jp6 = new JPanel();
		label_translator = new JLabel("图书译者");
		translator = new JTextField(25);
		translator.setFont(MyFont.JTextFieldFont);
		translator.setForeground(MyColor.JTextFieldColor);
		translator.setEditable(false);
		jp6.add(label_translator);
		jp6.add(translator);

		JPanel jp7 = new JPanel();
		label_publish_date = new JLabel("出版日期");
		publish_date = new JTextField(25);
		publish_date.setFont(MyFont.JTextFieldFont);
		publish_date.setForeground(MyColor.JTextFieldColor);
		publish_date.setEditable(false);
		jp7.add(label_publish_date);
		jp7.add(publish_date);

		JPanel jp8 = new JPanel();
		label_press = new JLabel("出版社  ");
		press = new JTextField(25);
		press.setFont(MyFont.JTextFieldFont);
		press.setForeground(MyColor.JTextFieldColor);
		press.setEditable(false);
		jp8.add(label_press);
		jp8.add(press);

		JPanel jp9 = new JPanel();
		label_price = new JLabel("建议售价");
		price = new JTextField(25);
		price.setFont(MyFont.JTextFieldFont);
		price.setForeground(MyColor.JTextFieldColor);
		price.setEditable(false);
		jp9.add(label_price);
		jp9.add(price);

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

		bookInfoPanel.add(ver);
		centerPanel.add(bookInfoPanel, BorderLayout.WEST);
		// 将组件加载到背景中
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

		reset = new JButton("重置");
		reset.setSize(75, 30);
		reset.addMouseListener(this);// 添加鼠标监听
		reset.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));
		// 将初始化完成的工具加载到工具条面板中
		buttonPanel.add(reset);
		// 最终将工具条面板加载到顶部菜单条的最西面
		downPanel.add(buttonPanel, BorderLayout.EAST);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tool_borrow) {
			// 根据当前读者数据进行图书借阅操作
			if (r == null) {
				JOptionPane.showMessageDialog(null, "请输入相应的读者条形码...");
			} else if (book == null) {
				JOptionPane.showMessageDialog(null, "当前没有要借阅的书籍哦，请确认后重新尝试！");
			} else {
				// 判断当前读者的借阅证是否处于禁用状态
				LibraryCard lc = null;
				try {
					lc = libraryCardService.getLibraryCardById(r.getCard_id());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if (lc.getDisable_state() == 1) {
					JOptionPane.showMessageDialog(null,
							"抱歉，当前借阅证已被禁用(处于异常状态，无法进行书籍借阅操作)，请到管理员处进行处理！");
				} else {
					// 判断当前可借阅数是否大于0（直接从文本框中获取相应数据）
					String availableNum_string = available_num.getText();
					int availableNum_int = 0;
					if (!availableNum_string.equals("")) {
						availableNum_int = Integer.valueOf(availableNum_string);
					}
					if (availableNum_int > 0) {
						// 执行借阅操作
						/**
						 * 借阅操作： 判断当前图书是否已上架、或者是已被借阅
						 * （因为如果是在实际的情况下，尚未上架的书籍或者是已被借阅的书籍一般是
						 * 其他无关的读者是无法进行相关操作的，但为了保证图书信息的正确性，在不同的
						 * 读者操作的时候还是要对图书的异常情况进行检验，避免错误或者是重复操作）
						 */
						// 根据当前要借阅的图书信息进行判断
						int borrow_flag_int = book.getBorrow_flag();
						int put_on_flag_int = book.getPut_on_flag();
						if (borrow_flag_int == 1) {
							// 判断当前的图书信息是否被借阅
							JOptionPane.showMessageDialog(null,
									"异常提示：抱歉，当前图书已被借阅，不能重复操作！");
						} else if (put_on_flag_int == 0) {
							JOptionPane.showMessageDialog(null,
									"异常提示：抱歉，您所查找的图书尚未上架，请确认后进行操作！");
						} else {
							// 读者信息、图书信息均处于正常状态，正常进行书籍借阅操作
							int choose = JOptionPane.showConfirmDialog(null,
									"确定借阅该书籍？");
							if (choose == 0) {
								// 执行书籍借阅操作
								// 借阅id是随机生成的32char类型的数据、借阅编号是对应的序列
								String borrowing_id = RandomGeneration
										.getRandom32charSeq();
								String borrowing_num = "0000000000";
								try {
									borrowing_num = borrowingService
											.getBorrowingSeq();
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								String book_id = book.getBook_id();
								String reader_id = r.getReader_id();
								// 获取当前系统时间
								SimpleDateFormat sdf = new SimpleDateFormat(
										"yyyy-MM-dd");
								String borrowing_date = sdf.format(new Date());
								// 根据当前读者的借阅等级获取建议还书截止时间
								int time_limit_int = Integer.valueOf(time_limit
										.getText());
								Calendar c = Calendar.getInstance();
								c.setTime(new Date());
								c.add(Calendar.DATE, time_limit_int);
								String suggest_return_date = sdf.format(c
										.getTime());
								Borrowing b = new Borrowing(borrowing_id,
										borrowing_num, book_id, reader_id,
										borrowing_date, suggest_return_date, 1,
										0);
								try {
									borrowingService.addBorrowing(b);
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								// 修改书籍借阅状态
								book.setBorrow_flag(1);
								try {
									booksService.updateBooks(book);
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								JOptionPane
										.showMessageDialog(
												null,
												"图书借阅成功，当前图书归还截止日期为"
														+ suggest_return_date
														+ ",请及时做好归还操作,如到期不能及时归还会相应降低信用度");
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "抱歉，您当前最大借阅数为:"
								+ maximum.getText() + "-当前可借阅数为:"
								+ availableNum_string + "请将相关书籍归还后再进行操作吧！");
					}
				}
			}

		} else if (e.getSource() == reset) {
			// 对应文本框置空
			reader_barcode.setText("请输入读者条形码");
			reader_barcode.setForeground(MyColor.TipColor);
			reader_barcode.setFont(MyFont.TipFont);
			academic_num.setText("");
			reader_name.setText("");
			account.setText("");
			reader_classify.setText("");
			card_num.setText("");
			available_num.setText("");
			maximum.setText("");
			time_limit.setText("");
			// 对应文本框置空
			keyword.setText("条形码|");
			keyword.setForeground(MyColor.TipColor);
			keyword.setFont(MyFont.TipFont);
			isbn.setText("");
			book_name.setText("");
			book_classify.setText("");
			area.setText("");
			author.setText("");
			translator.setText("");
			publish_date.setText("");
			press.setText("");
			price.setText("");
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
		} else if (e.getSource() == reader_barcode) {
			if (reader_barcode.getText().equals("请输入读者条形码")) {
				reader_barcode.setText("");
				reader_barcode.setFont(MyFont.JTextFieldFont);
				reader_barcode.setForeground(MyColor.JTextFieldColor);
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
		} else if (e.getSource() == reader_barcode) {
			if (reader_barcode.getText().equals("")) {
				reader_barcode.setText("请输入读者条形码");
				reader_barcode.setFont(MyFont.TipFont);
				reader_barcode.setForeground(MyColor.TipColor);
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

	// 根据当前输入的读者条形码信息回显读者数据信息
	public void echoReaderData() {
		try {
			String reader_barcode_string = reader_barcode.getText();
			if (!reader_barcode_string.equals("请输入读者条形码")
					&& DataValidation.isSignlessInteger(reader_barcode_string)) {
				r = readerService.getReaderByBarcode(reader_barcode_string);
				if (r != null) {
					// 回显数据信息
					academic_num.setText(r.getAcademic_num());

					reader_name.setText(r.getReader_name());

					Users findUser = usersService.getUsersById(r.getUser_id());
					account.setText(findUser.getUser_name());

					ReaderClassify rc = readerClassifyService
							.getReaderClassifyBynum(r.getClassify_num());
					reader_classify.setText(rc.getClassify_name());

					LibraryCard lc = libraryCardService.getLibraryCardById(r
							.getCard_id());
					card_num.setText(lc.getCard_num());

					// 根据当前借阅信息统计当前借阅的数量
					int borrow_num = borrowingService
							.getBorrowingCountByState();
					int available_num_int = rc.getMaximum() - borrow_num;
					available_num.setText(available_num_int + "");

					maximum.setText(rc.getMaximum() + "");

					time_limit.setText(rc.getTime_limit() + "");
				} else {
					// 对应文本框置空
					reader_barcode.setText("请输入读者条形码");
					reader_barcode.setForeground(MyColor.TipColor);
					reader_barcode.setFont(MyFont.TipFont);
					academic_num.setText("");
					reader_name.setText("");
					account.setText("");
					reader_classify.setText("");
					card_num.setText("");
					available_num.setText("");
					maximum.setText("");
					time_limit.setText("");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 根据当前输入的书籍条形码信息回显书籍数据信息
	public void echoBookData() {
		try {
			String book_barcode_string = keyword.getText();
			if (!book_barcode_string.equals("条形码|")
					&& DataValidation.isSignlessInteger(book_barcode_string)) {
				book = booksService.getBookByBarcode(book_barcode_string);
				if (book != null) {
					// 回显数据信息
					isbn.setText(book.getIsbn());

					book_name.setText(book.getBook_name());

					BookClassify bc = bookClassifyService
							.getBookClassifyByNum(book.getClassify_num());
					book_classify.setText(bc.getClassify_num() + "-"
							+ bc.getClassify_name());

					StorageArea sa = storageAreaService
							.getStorageAreaByNum(book.getArea_num());
					area.setText(sa.getArea_name());

					author.setText(book.getAuthor());

					translator.setText(book.getTranslator());

					publish_date.setText(book.getPublish_date()
							.substring(0, 10));

					press.setText(book.getPress());

					price.setText(book.getPrice() + "元");

				} else {
					// 对应文本框置空
					isbn.setText("");
					book_name.setText("");
					book_classify.setText("");
					area.setText("");
					author.setText("");
					translator.setText("");
					publish_date.setText("");
					press.setText("");
					price.setText("");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
