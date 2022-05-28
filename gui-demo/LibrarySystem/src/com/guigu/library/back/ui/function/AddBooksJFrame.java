package com.guigu.library.back.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.guigu.library.back.ui.control.BooksArchivesManagerJPanel;
import com.guigu.library.model.BookClassify;
import com.guigu.library.model.Books;
import com.guigu.library.model.StorageArea;
import com.guigu.library.service.BookClassifyService;
import com.guigu.library.service.BooksService;
import com.guigu.library.service.StorageAreaService;
import com.guigu.library.service.impl.BookClassifyServiceImpl;
import com.guigu.library.service.impl.BooksServiceImpl;
import com.guigu.library.service.impl.StorageAreaServiceImpl;
import com.guigu.library.utils.Chooser;
import com.guigu.library.utils.DataValidation;
import com.guigu.library.utils.Item;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;
import com.guigu.library.utils.RandomGeneration;

/**
 * 图书id为自动生成的32char类型的序列 图书条形码是自动生成的10位int类型的编号 图书的索书号则由当前选中的图书分类编号结合序列进行拼接：自动生成
 * 其余信息则需要手动进行填充
 */
public class AddBooksJFrame extends JFrame implements FocusListener,
		MouseListener {

	// 定义全局组件
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_isbn, label_name, label_classify, label_area, label_author,
			label_translator, label_publish_date, label_press, label_price,
			label_format, label_entry_date, label_put_on_date,
			label_abstract_descr, label_proposal_reader;
	JTextField isbn, name, author, translator, press, price, publish_date,
			entry_date, put_on_date;
	JTextArea format, abstract_descr, proposal_reader;
	JComboBox classify, area;
	Chooser publish_date_chooser, entry_date_chooser, put_on_date_chooser;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String current_date = sdf.format(new Date());

	JButton save, reset;
	// 得到屏幕大小
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// 定义一个标志flag用以表示当前的根据图书isbn查找的图书信息是否存在，如果存在则无需额外生成索书号
	int flag = 0;
	String callno = "";

	// 定义service
	BookClassifyService bookClassifyService = new BookClassifyServiceImpl();
	StorageAreaService storageAreaService = new StorageAreaServiceImpl();
	BooksService booksService = new BooksServiceImpl();

	// 定义父对象、当前登录员工、表格、选中行
	BooksArchivesManagerJPanel parentPanel;

	// 通过构造方法初始化数据
	public AddBooksJFrame(BooksArchivesManagerJPanel parentPanel) {
		this.parentPanel = parentPanel;
		// 初始化背景
		initBackgroundPanel();
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("添加图书信息");
		this.setSize(600, 600);
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
		JLabel title = new JLabel("添加图书信息");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * 初始化内容面板
	 */
	private void initContentPanel() {

		contentPanel = new JPanel();

		JPanel jp1 = new JPanel();
		label_isbn = new JLabel("ISBN    ");
		isbn = new JTextField(15);
		isbn.setFont(MyFont.TipFont);
		isbn.setForeground(MyColor.TipColor);
		isbn.setText("必填项");
		isbn.addFocusListener(this);
		isbn.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// 监听文本框的变化
				echoDataByISBN();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// 监听文本框的变化
				echoDataByISBN();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});

		label_name = new JLabel("图书名称");
		name = new JTextField(15);
		name.setFont(MyFont.TipFont);
		name.setForeground(MyColor.TipColor);
		name.setText("必填项");
		name.addFocusListener(this);
		jp1.add(label_isbn);
		jp1.add(isbn);
		jp1.add(label_name);
		jp1.add(name);

		JPanel jp2 = new JPanel();
		label_author = new JLabel("相关作者");
		author = new JTextField(15);
		author.setFont(MyFont.TipFont);
		author.setForeground(MyColor.TipColor);
		author.setText("必填项");
		author.addFocusListener(this);

		label_translator = new JLabel("相关译者");
		translator = new JTextField(15);
		translator.setFont(MyFont.TipFont);
		translator.setForeground(MyColor.TipColor);
		translator.setText("必填项");
		translator.addFocusListener(this);
		jp2.add(label_author);
		jp2.add(author);
		jp2.add(label_translator);
		jp2.add(translator);

		JPanel jp3 = new JPanel();
		label_press = new JLabel("出版社  ");
		press = new JTextField(15);
		press.setFont(MyFont.TipFont);
		press.setForeground(MyColor.TipColor);
		press.setText("必填项");
		press.addFocusListener(this);

		label_price = new JLabel("建议售价");
		price = new JTextField(15);
		price.setFont(MyFont.TipFont);
		price.setForeground(MyColor.TipColor);
		price.setText("可保留两位小数");
		price.addFocusListener(this);
		jp3.add(label_press);
		jp3.add(press);
		jp3.add(label_price);
		jp3.add(price);

		JPanel jp4 = new JPanel();
		label_classify = new JLabel("所属分类");
		classify = new JComboBox();
		classify.setPreferredSize(new Dimension(175, 30));
		// 装载分类信息
		List<BookClassify> bc_list = null;
		try {
			bc_list = bookClassifyService.findBookClassifyUnion("all");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (BookClassify bc : bc_list) {
			String bc_num = bc.getClassify_num();
			String bc_name = bc.getClassify_name();
			// 为了更好地体现分类信息，将分类编号与分类名称进行字符串拼接生成新的信息
			String value = bc_num + ":" + bc_name;
			Item item = new Item(bc_num, value);
			classify.addItem(item);
		}
		label_area = new JLabel("存储区域");
		area = new JComboBox();
		area.setPreferredSize(new Dimension(175, 30));
		// 装载存储区域信息
		List<StorageArea> sa_list = null;
		try {
			sa_list = storageAreaService.findAllStorageArea();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (StorageArea sa : sa_list) {
			int sa_num = sa.getArea_num();
			String sa_name = sa.getArea_name();
			Item item = new Item(sa_num + "", sa_name);
			area.addItem(item);
		}
		jp4.add(label_classify);
		jp4.add(classify);
		jp4.add(label_area);
		jp4.add(area);

		JPanel jp5 = new JPanel();
		label_publish_date = new JLabel("出版日期");
		publish_date = new JTextField(15);
		publish_date.setText(current_date);
		publish_date_chooser = Chooser.getInstance();
		publish_date_chooser.register(publish_date);

		label_entry_date = new JLabel("录入日期");
		entry_date = new JTextField(15);
		entry_date.setText(current_date);
		entry_date_chooser = Chooser.getInstance();
		entry_date_chooser.register(entry_date);

		label_put_on_date = new JLabel("上架日期");
		put_on_date = new JTextField(15);
		put_on_date.setText(current_date);
		put_on_date_chooser = Chooser.getInstance();
		put_on_date_chooser.register(put_on_date);
		jp5.add(label_publish_date);
		jp5.add(publish_date);
		jp5.add(label_entry_date);
		jp5.add(entry_date);
		jp5.add(label_put_on_date);
		jp5.add(put_on_date);

		JPanel jp6 = new JPanel();
		label_format = new JLabel("图书规格");
		format = new JTextArea(2, 30);
		format.setFont(MyFont.TipFont);
		format.setForeground(MyColor.TipColor);
		format.setBackground(Color.white);
		format.setText("图书规格描述，必填项");
		format.setLineWrap(true);
		format.addFocusListener(this);
		jp6.add(label_format);
		jp6.add(format);

		JPanel jp7 = new JPanel();
		label_abstract_descr = new JLabel("摘要附注");
		abstract_descr = new JTextArea(5, 30);
		abstract_descr.setFont(MyFont.TipFont);
		abstract_descr.setForeground(MyColor.TipColor);
		abstract_descr.setBackground(Color.white);
		abstract_descr.setText("图书提要文摘附注，可选填");
		abstract_descr.setLineWrap(true);
		abstract_descr.addFocusListener(this);
		jp7.add(label_abstract_descr);
		jp7.add(abstract_descr);

		JPanel jp8 = new JPanel();
		label_proposal_reader = new JLabel("读者附注");
		proposal_reader = new JTextArea(5, 30);
		proposal_reader.setFont(MyFont.TipFont);
		proposal_reader.setForeground(MyColor.TipColor);
		proposal_reader.setBackground(Color.white);
		proposal_reader.setText("图书使用对象附注，可选填");
		proposal_reader.setLineWrap(true);
		proposal_reader.addFocusListener(this);
		jp8.add(label_proposal_reader);
		jp8.add(proposal_reader);

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
		// 获取当前文本框的数据
		String isbn_string = isbn.getText();
		String name_string = name.getText();
		String author_string = author.getText();
		String translator_string = translator.getText();
		String press_string = press.getText();
		String price_string = price.getText();
		String publish_date_string = publish_date.getText();
		String entry_date_string = entry_date.getText();
		String put_on_date_string = put_on_date.getText();
		String format_string = format.getText();
		String abstract_descr_string = abstract_descr.getText();
		String proposal_reader_string = proposal_reader.getText();
		if (e.getSource() == save) {
			if (isbn_string.equals("必填项")) {
				JOptionPane.showMessageDialog(null, "国际统一标准书号ISBN不能为空！");
			} else if (name_string.equals("必填项")) {
				JOptionPane.showMessageDialog(null, "图书名称不能为空！");
			} else if (author_string.equals("必填项")) {
				JOptionPane.showMessageDialog(null, "图书相关作者不能为空！");
			} else if (translator_string.equals("必填项")) {
				JOptionPane.showMessageDialog(null, "图书相关译者不能为空！");
			} else if (press_string.equals("必填项")) {
				JOptionPane.showMessageDialog(null, "图书所在出版社不能为空！");
			} else if (price_string.equals("可保留两位小数")) {
				JOptionPane.showMessageDialog(null, "图书建议售价不能为空！");
			} else if (format_string.equals("图书规格描述，必填项")) {
				JOptionPane.showMessageDialog(null, "图书规格描述不能为空！");
			} else {
				// 提供默认值 要注意逻辑判断
				if (abstract_descr_string.equals("图书提要文摘附注，可选填")) {
					abstract_descr_string = "无具体描述";
				}
				if (proposal_reader_string.equals("图书使用对象附注，可选填")) {
					proposal_reader_string = "无具体描述";
				}
				double price_double;
				// 对数据进行处理(验证、转化)
				if (!DataValidation.isBigDecimal(price_string)) {
					JOptionPane.showMessageDialog(null, "图书售价输入数据格式有误！");
				} else {
					price_double = Double.valueOf(price_string);
					// 获取当前下拉框选中的数据信息
					Item classify_item = (Item) classify.getSelectedItem();
					Item area_item = (Item) area.getSelectedItem();
					String classify_num = classify_item.getKey();
					int area_num = Integer.valueOf(area_item.getKey());

					// 生成图书id、条形码、索引号(分类编号拼接序列生成)
					String book_id = RandomGeneration.getRandom32charSeq();
					String barcode = RandomGeneration.getRandom10numSeq();
					if (flag == 0) {
						// 如果flag为0，说明当前的版本图书信息不存在，则自动根据相应编号生成信息
						callno = RandomGeneration.getBooksCallno(classify_num);
					}
					// 创建图书对象，加载数据进行保存
					Books book = new Books(book_id, barcode, isbn_string,
							callno, name_string, classify_num, area_num,
							author_string, translator_string,
							publish_date_string, press_string, price_double,
							format_string, entry_date_string,
							put_on_date_string, abstract_descr_string,
							proposal_reader_string, 0, 0, 0);
					// 调用方法保存数据
					try {
						booksService.addBooks(book);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// 输出提示信息，隐藏子页面，刷新.主面板
					JOptionPane.showMessageDialog(null, "图书信息保存成功！");
					this.setVisible(false);
					parentPanel.refreshTablePanel();
				}
			}
		} else if (e.getSource() == reset) {
			// 重置数据信息
			isbn.setFont(MyFont.TipFont);
			isbn.setForeground(MyColor.TipColor);
			isbn.setText("必填项");
			name.setFont(MyFont.TipFont);
			name.setForeground(MyColor.TipColor);
			name.setText("必填项");
			author.setFont(MyFont.TipFont);
			author.setForeground(MyColor.TipColor);
			author.setText("必填项");
			translator.setFont(MyFont.TipFont);
			translator.setForeground(MyColor.TipColor);
			translator.setText("必填项");
			press.setFont(MyFont.TipFont);
			press.setForeground(MyColor.TipColor);
			press.setText("必填项");
			price.setFont(MyFont.TipFont);
			price.setForeground(MyColor.TipColor);
			price.setText("可保留两位小数");
			format.setFont(MyFont.TipFont);
			format.setForeground(MyColor.TipColor);
			format.setText("图书规格描述，必填项");
			abstract_descr.setFont(MyFont.TipFont);
			abstract_descr.setForeground(MyColor.TipColor);
			abstract_descr.setText("图书提要文摘附注，可选填");
			proposal_reader.setFont(MyFont.TipFont);
			proposal_reader.setForeground(MyColor.TipColor);
			proposal_reader.setText("图书使用对象附注，可选填");

			// 时间默认为当前系统日期
			publish_date.setText(current_date);
			entry_date.setText(current_date);
			put_on_date.setText(current_date);

			// 下拉框选项默认为第一个
			classify.setSelectedIndex(0);
			area.setSelectedIndex(0);
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
	 * 获取焦点事件
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == isbn) {
			if (isbn.getText().equals("必填项")) {
				isbn.setFont(MyFont.JTextFieldFont);
				isbn.setForeground(MyColor.JTextFieldColor);
				isbn.setText("");
			}
		} else if (e.getSource() == name) {
			if (name.getText().equals("必填项")) {
				name.setFont(MyFont.JTextFieldFont);
				name.setForeground(MyColor.JTextFieldColor);
				name.setText("");
			}
		} else if (e.getSource() == author) {
			if (author.getText().equals("必填项")) {
				author.setFont(MyFont.JTextFieldFont);
				author.setForeground(MyColor.JTextFieldColor);
				author.setText("");
			}
		} else if (e.getSource() == translator) {
			if (translator.getText().equals("必填项")) {
				translator.setFont(MyFont.JTextFieldFont);
				translator.setForeground(MyColor.JTextFieldColor);
				translator.setText("");
			}
		} else if (e.getSource() == press) {
			if (press.getText().equals("必填项")) {
				press.setFont(MyFont.JTextFieldFont);
				press.setForeground(MyColor.JTextFieldColor);
				press.setText("");
			}
		} else if (e.getSource() == price) {
			if (price.getText().equals("可保留两位小数")) {
				price.setFont(MyFont.JTextFieldFont);
				price.setForeground(MyColor.JTextFieldColor);
				price.setText("");
			}
		} else if (e.getSource() == format) {
			if (format.getText().equals("图书规格描述，必填项")) {
				format.setFont(MyFont.JTextFieldFont);
				format.setForeground(MyColor.JTextFieldColor);
				format.setText("");
			}
		} else if (e.getSource() == abstract_descr) {
			if (abstract_descr.getText().equals("图书提要文摘附注，可选填")) {
				abstract_descr.setFont(MyFont.JTextFieldFont);
				abstract_descr.setForeground(MyColor.JTextFieldColor);
				abstract_descr.setText("");
			}
		} else if (e.getSource() == proposal_reader) {
			if (proposal_reader.getText().equals("图书使用对象附注，可选填")) {
				proposal_reader.setFont(MyFont.JTextFieldFont);
				proposal_reader.setForeground(MyColor.JTextFieldColor);
				proposal_reader.setText("");
			}
		}
	}

	/**
	 * 失去焦点事件
	 */
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == isbn) {
			if (isbn.getText().equals("")) {
				isbn.setFont(MyFont.TipFont);
				isbn.setForeground(MyColor.TipColor);
				isbn.setText("必填项");
			}
		} else if (e.getSource() == name) {
			if (name.getText().equals("")) {
				name.setFont(MyFont.TipFont);
				name.setForeground(MyColor.TipColor);
				name.setText("必填项");
			}
		} else if (e.getSource() == author) {
			if (author.getText().equals("")) {
				author.setFont(MyFont.TipFont);
				author.setForeground(MyColor.TipColor);
				author.setText("必填项");
			}
		} else if (e.getSource() == translator) {
			if (translator.getText().equals("")) {
				translator.setFont(MyFont.TipFont);
				translator.setForeground(MyColor.TipColor);
				translator.setText("必填项");
			}
		} else if (e.getSource() == press) {
			if (press.getText().equals("")) {
				press.setFont(MyFont.TipFont);
				press.setForeground(MyColor.TipColor);
				press.setText("必填项");
			}
		} else if (e.getSource() == price) {
			if (price.getText().equals("")) {
				price.setFont(MyFont.TipFont);
				price.setForeground(MyColor.TipColor);
				price.setText("可保留两位小数");
			}
		} else if (e.getSource() == format) {
			if (format.getText().equals("")) {
				format.setFont(MyFont.TipFont);
				format.setForeground(MyColor.TipColor);
				format.setText("图书规格描述，必填项");
			}
		} else if (e.getSource() == abstract_descr) {
			if (abstract_descr.getText().equals("")) {
				abstract_descr.setFont(MyFont.TipFont);
				abstract_descr.setForeground(MyColor.TipColor);
				abstract_descr.setText("图书提要文摘附注，可选填");
			}
		} else if (e.getSource() == proposal_reader) {
			if (proposal_reader.getText().equals("")) {
				proposal_reader.setFont(MyFont.TipFont);
				proposal_reader.setForeground(MyColor.TipColor);
				proposal_reader.setText("图书使用对象附注，可选填");
			}
		}
	}

	/**
	 * 根据输入的ISBN自动回显数据，如果是同一个版本的书则自动显示数据
	 */
	public void echoDataByISBN() {
		String isbn_string = isbn.getText();
		if (isbn_string != "必填项") {
			// 根据isbn查找图书信息
			List<Books> books = null;
			try {
				books = booksService.findBooksUnion(2, 1, isbn_string);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (books.size() != 0) {
				// 修改标识
				flag = 1;
				// 如果当前输入的isbn存在相应书籍则选择其中任意一本的部分数据进行数据回显
				// （图书版本相同则对应的基本信息必然一致，区别在于条形码自动生成，亦可进行相应的修改）
				Books book = books.get(0);
				name.setText(book.getBook_name());
				author.setText(book.getAuthor());
				translator.setText(book.getTranslator());
				press.setText(book.getPress());
				price.setText(book.getPrice() + "");
				publish_date.setText(book.getPublish_date().substring(0, 10));
				entry_date.setText(book.getEntry_date().substring(0, 10));
				put_on_date.setText(book.getPut_on_date().substring(0, 10));
				format.setText(book.getFormat());
				abstract_descr.setText(book.getAbstract_descr());
				proposal_reader.setText(book.getProposal_reader());

				// 如果是已存在的图书版本则其索引号为当前版本对应的内容
				callno = book.getCallno();

				String classify_num_string = book.getClassify_num();
				int area_num_int = book.getArea_num();
				// 重新装载分类数据
				classify.removeAllItems();
				List<BookClassify> bc_list = null;
				try {
					bc_list = bookClassifyService.findBookClassifyUnion("all");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				for (int i = 0; i < bc_list.size(); i++) {
					int sign = 0;
					String bc_num = bc_list.get(i).getClassify_num();
					String bc_name = bc_list.get(i).getClassify_name();
					// 为了更好地体现分类信息，将分类编号与分类名称进行字符串拼接生成新的信息
					String value = bc_num + ":" + bc_name;
					Item item = new Item(bc_num, value);
					classify.addItem(item);
					if (bc_num.equals(classify_num_string)) {
						sign = i;
						classify.setSelectedIndex(sign);
					}
				}
				// 重新装载存储区域信息
				area.removeAllItems();
				List<StorageArea> sa_list = null;
				try {
					sa_list = storageAreaService.findAllStorageArea();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				for (int i = 0; i < sa_list.size(); i++) {
					int sign = 0;
					int sa_num = sa_list.get(i).getArea_num();
					String sa_name = sa_list.get(i).getArea_name();
					Item item = new Item(sa_num + "", sa_name);
					area.addItem(item);
					if (sa_num == area_num_int) {
						sign = i;
						area.setSelectedIndex(sign);
					}
				}
			} else {
				// 清空数据
				if (!isbn.getText().equals("")) {
					// 重置数据信息
					name.setFont(MyFont.TipFont);
					name.setForeground(MyColor.TipColor);
					name.setText("必填项");
					author.setFont(MyFont.TipFont);
					author.setForeground(MyColor.TipColor);
					author.setText("必填项");
					translator.setFont(MyFont.TipFont);
					translator.setForeground(MyColor.TipColor);
					translator.setText("必填项");
					press.setFont(MyFont.TipFont);
					press.setForeground(MyColor.TipColor);
					press.setText("必填项");
					price.setFont(MyFont.TipFont);
					price.setForeground(MyColor.TipColor);
					price.setText("可保留两位小数");
					format.setFont(MyFont.TipFont);
					format.setForeground(MyColor.TipColor);
					format.setText("图书规格描述，必填项");
					abstract_descr.setFont(MyFont.TipFont);
					abstract_descr.setForeground(MyColor.TipColor);
					abstract_descr.setText("图书提要文摘附注，可选填");
					proposal_reader.setFont(MyFont.TipFont);
					proposal_reader.setForeground(MyColor.TipColor);
					proposal_reader.setText("图书使用对象附注，可选填");

					// 时间默认为当前系统日期
					publish_date.setText(current_date);
					entry_date.setText(current_date);
					put_on_date.setText(current_date);

					// 下拉框选项默认为第一个
					classify.setSelectedIndex(0);
					area.setSelectedIndex(0);
				}
			}
		}
	}
}
