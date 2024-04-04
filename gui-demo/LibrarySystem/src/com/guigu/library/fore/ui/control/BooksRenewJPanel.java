package com.guigu.library.fore.ui.control;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
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
import com.guigu.library.model.Renew;
import com.guigu.library.model.Returning;
import com.guigu.library.model.StorageArea;
import com.guigu.library.model.Users;
import com.guigu.library.service.BookClassifyService;
import com.guigu.library.service.BooksService;
import com.guigu.library.service.BorrowingService;
import com.guigu.library.service.LibraryCardService;
import com.guigu.library.service.ReaderClassifyService;
import com.guigu.library.service.ReaderService;
import com.guigu.library.service.RenewService;
import com.guigu.library.service.ReturningService;
import com.guigu.library.service.StorageAreaService;
import com.guigu.library.service.UsersService;
import com.guigu.library.service.impl.BookClassifyServiceImpl;
import com.guigu.library.service.impl.BooksServiceImpl;
import com.guigu.library.service.impl.BorrowingServiceImpl;
import com.guigu.library.service.impl.LibraryCardServiceImpl;
import com.guigu.library.service.impl.ReaderClassifyServiceImpl;
import com.guigu.library.service.impl.ReaderServiceImpl;
import com.guigu.library.service.impl.RenewServiceImpl;
import com.guigu.library.service.impl.ReturningServiceImpl;
import com.guigu.library.service.impl.StorageAreaServiceImpl;
import com.guigu.library.service.impl.UsersServiceImpl;
import com.guigu.library.utils.DataValidation;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;
import com.guigu.library.utils.RandomGeneration;

public class BooksRenewJPanel implements MouseListener {

	// 定义全局组件
	JPanel backgroundPanel, topPanel, toolPanel, centerPanel, bookInfoPanel,
			downPanel, buttonPanel;

	// 定义BookInfoPanel（书籍信息）使用到的标签和文本框
	JLabel label_isbn, label_book_name, label_book_classify, label_area,
			label_author, label_translator, label_publish_date, label_press,
			label_price;
	JTextField isbn, book_name, book_classify, area, author, translator,
			publish_date, press, price;

	// 创建工具条使用到的标签
	JLabel tool_renew, tool_return, search_barcode;

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
	RenewService renewService = new RenewServiceImpl();
	ReturningService returningService = new ReturningServiceImpl();

	Users user;

	/**
	 * 通过构造方法完成初始化
	 */
	public BooksRenewJPanel(Users user) {
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

		Icon icon_renew = new ImageIcon("icons/toolImage/renew.png");
		tool_renew = new JLabel(icon_renew);
		tool_renew.setToolTipText("图书续借");// 设置鼠标移动时的显示内容
		tool_renew.addMouseListener(this);// 添加鼠标监听

		Icon icon_return = new ImageIcon("icons/toolImage/return.png");
		tool_return = new JLabel(icon_return);
		tool_return.setToolTipText("图书归还");// 设置鼠标移动时的显示内容
		tool_return.addMouseListener(this);// 添加鼠标监听

		// 将初始化完成的工具加载到工具条面板中
		JPanel west = new JPanel();
		west.add(tool_renew);
		west.add(Box.createHorizontalStrut(20));
		west.add(tool_return);

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
		// 初始化书籍信息显示面板
		initBookInfoPanel();
		backgroundPanel.add(centerPanel, BorderLayout.CENTER);
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
		centerPanel.add(bookInfoPanel, BorderLayout.CENTER);
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
		// 添加按钮工具
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
		if (e.getSource() == tool_renew) {
			try {
				if (keyword.getText().equals("条形码|")) {
					JOptionPane.showMessageDialog(null,
							"抱歉，当前没有检测到要操作的书籍，请重新尝试！");
				} else {
					// 图书续借：判断当前用户借阅证是否正常使用，是否存在违纪记录
					if (book == null) {
						JOptionPane.showMessageDialog(null,
								"当前没有要借阅的书籍哦，请确认后重新尝试！");
					} else {
						// 根据当前的图书信息获取相应的借阅信息
						Borrowing b = borrowingService
								.getBorrowingByBookId(book.getBook_id());
						// 判断当前是否存在相应的记录
						if (b == null) {
							JOptionPane.showMessageDialog(null,
									"当前图书信息无借阅记录，请确认后重试！");
						} else {
							// 判断当前图书是否已归还，如果归还则不能续借
							if (b.getBorrow_state() == 0) {
								JOptionPane.showMessageDialog(null,
										"当前图书已归还，无法执行续借操作！");
							} else {
								// 判断当前读者的借阅证是否处于禁用状态
								r = readerService.getReaderById(b
										.getReader_id());
								LibraryCard lc = libraryCardService
										.getLibraryCardById(r.getCard_id());
								if (lc.getDisable_state() == 1) {
									JOptionPane
											.showMessageDialog(null,
													"抱歉，当前借阅证已被禁用(处于异常状态，无法进行书籍续借操作)，请将相关书籍及时归还并到管理员处进行处理！");
								} else {
									// 判断当前时间是否超出建议归还日期，如果超出则认为是属于违纪状态，不能够续借或者是借阅图书
									SimpleDateFormat sdf = new SimpleDateFormat(
											"yyyy-MM-dd");
									String current_date = sdf
											.format(new Date());
									if (current_date.compareTo(b
											.getSuggest_return_date()) > 0
											|| b.getViolation_state() == 1) {
										JOptionPane.showMessageDialog(null,
												"当前存在违纪现象，请将相关书籍归还后到管理员处进行处理！");
									} else {
										// 可以正常执行续借操作（续借操作默认是在当前时间的基础上根据用户等级添加指定的限制天数）
										ReaderClassify rc = readerClassifyService
												.getReaderClassifyBynum(r
														.getClassify_num());
										if (rc != null) {
											int choose = JOptionPane
													.showConfirmDialog(null,
															"确认续借该书籍？");
											if (choose == 0) {
												// 图书续借：涉及续借表、借阅表、图书表
												int time_limit_int = rc
														.getTime_limit();
												// 添加续借记录
												// 续借id是随机生成的32char序列，续借编号是自定义序列
												String renew_id = RandomGeneration
														.getRandom32charSeq();
												String renew_num = renewService
														.getRenewSeq();
												String book_id = book
														.getBook_id();
												String reader_id = r
														.getReader_id();
												Renew renew = new Renew(
														renew_id, renew_num,
														book_id, reader_id,
														current_date);
												// 修改相应的借阅建议归还日期
												Calendar c = Calendar
														.getInstance();
												c.setTime(new Date());
												c.add(Calendar.DATE,
														time_limit_int);
												String suggest_return_date = sdf
														.format(c.getTime());
												b.setSuggest_return_date(suggest_return_date);
												renewService.addRenew(renew);
												borrowingService
														.updateBorrowing(b);
												JOptionPane
														.showMessageDialog(
																null,
																"当前图书续借成功，归还日期截止到"
																		+ suggest_return_date);
											}
										}
									}
								}
							}
						}
					}
				}

			} catch (HeadlessException | SQLException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == tool_return) {
			try {
				if (keyword.getText().equals("条形码|")) {
					JOptionPane.showMessageDialog(null,
							"抱歉，当前没有检测到要操作的书籍，请重新尝试！");
				} else {
					if (book == null) {
						JOptionPane.showMessageDialog(null,
								"当前没有要借阅的书籍哦，请确认后重新尝试！");
					} else {
						// 根据当前的图书信息获取相应的借阅信息
						Borrowing b = borrowingService
								.getBorrowingByBookId(book.getBook_id());
						// 判断当前图书是否已归还，如果归还则不能续借
						if (b.getBorrow_state() == 0) {
							JOptionPane.showMessageDialog(null,
									"当前图书已归还，不能重复进行操作！");
						} else {
							int choose = JOptionPane.showConfirmDialog(null,
									"确认归还该图书？");
							if (choose == 0) {
								// 归还图书
								SimpleDateFormat sdf = new SimpleDateFormat(
										"yyyy-MM-dd");
								String current_date = sdf.format(new Date());
								if (current_date.compareTo(b
										.getSuggest_return_date()) > 0) {
									b.setViolation_state(1);
								}
								// 归还图书：添加图书归还记录、更新图书借阅信息（涉及归还表、借阅表、图书表）
								String returning_id = RandomGeneration
										.getRandom32charSeq();
								String returning_num = returningService
										.getReturningSeq();
								String book_id = book.getBook_id();
								String reader_id = r.getReader_id();
								Returning returning = new Returning(
										returning_id, returning_num, book_id,
										reader_id, current_date);
								// 修改借阅表中的借阅标识
								b.setBorrow_state(0);
								// 修改对应图书的借阅标识
								book.setBorrow_flag(0);
								// 执行操作
								returningService.addReturning(returning);
								borrowingService.updateBorrowing(b);
								booksService.updateBooks(book);
								if (b.getViolation_state() == 0) {
									JOptionPane.showMessageDialog(null,
											"当前图书归还成功");
								} else if (b.getViolation_state() == 1) {
									JOptionPane.showMessageDialog(null,
											"当前图书归还成功，属于超期归还！！");
								}
							}
						}
					}
				}
			} catch (HeadlessException | SQLException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == reset) {
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
