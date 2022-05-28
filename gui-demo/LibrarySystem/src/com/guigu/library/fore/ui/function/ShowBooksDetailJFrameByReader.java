package com.guigu.library.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.guigu.library.fore.ui.control.BooksSearchJPanel;
import com.guigu.library.model.BookClassify;
import com.guigu.library.model.Books;
import com.guigu.library.model.StorageArea;
import com.guigu.library.service.BookClassifyService;
import com.guigu.library.service.BooksService;
import com.guigu.library.service.StorageAreaService;
import com.guigu.library.service.impl.BookClassifyServiceImpl;
import com.guigu.library.service.impl.BooksServiceImpl;
import com.guigu.library.service.impl.StorageAreaServiceImpl;
import com.guigu.library.utils.Item;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;

public class ShowBooksDetailJFrameByReader extends JFrame implements MouseListener {

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

	// 得到屏幕大小
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// 定义service
	BookClassifyService bookClassifyService = new BookClassifyServiceImpl();
	StorageAreaService storageAreaService = new StorageAreaServiceImpl();
	BooksService booksService = new BooksServiceImpl();

	// 上一条、下一条、退出
	JLabel front, back, exit;

	// 定义父对象、当前登录员工、表格、选中行
	BooksSearchJPanel parentPanel;
	JTable parentTable;
	int selectedRow;
	int currentRow;

	// 通过构造方法初始化数据
	public ShowBooksDetailJFrameByReader(BooksSearchJPanel parentPanel,
			JTable parentTable, int selectedRow) {
		this.parentPanel = parentPanel;
		this.parentTable = parentTable;
		this.selectedRow = selectedRow;
		this.currentRow = selectedRow;
		// 初始化背景
		initBackgroundPanel();
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("查阅图书详情");
		this.setSize(600, 650);
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
	}

	/**
	 * 初始化标题面板
	 */
	private void initTitlePanel() {
		titlePanel = new JPanel();
		JLabel title = new JLabel("查阅图书详情");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);backgroundPanel.add(titlePanel, BorderLayout.NORTH);
		backgroundPanel.validate();
	}

	/**
	 * 初始化内容面板
	 */
	private void initContentPanel() {

		contentPanel = new JPanel();

		JPanel jp1 = new JPanel();
		label_isbn = new JLabel("ISBN    ");
		isbn = new JTextField(15);
		isbn.setFont(MyFont.JTextFieldFont);
		isbn.setForeground(MyColor.JTextFieldColor);
		isbn.setEditable(false);

		label_name = new JLabel("图书名称");
		name = new JTextField(15);
		name.setFont(MyFont.JTextFieldFont);
		name.setForeground(MyColor.JTextFieldColor);
		name.setEditable(false);
		jp1.add(label_isbn);
		jp1.add(isbn);
		jp1.add(label_name);
		jp1.add(name);

		JPanel jp2 = new JPanel();
		label_author = new JLabel("相关作者");
		author = new JTextField(15);
		author.setFont(MyFont.JTextFieldFont);
		author.setForeground(MyColor.JTextFieldColor);
		author.setEditable(false);

		label_translator = new JLabel("相关译者");
		translator = new JTextField(15);
		translator.setFont(MyFont.JTextFieldFont);
		translator.setForeground(MyColor.JTextFieldColor);
		translator.setEditable(false);
		jp2.add(label_author);
		jp2.add(author);
		jp2.add(label_translator);
		jp2.add(translator);

		JPanel jp3 = new JPanel();
		label_press = new JLabel("出版社  ");
		press = new JTextField(15);
		press.setFont(MyFont.JTextFieldFont);
		press.setForeground(MyColor.JTextFieldColor);
		press.setEditable(false);

		label_price = new JLabel("建议售价");
		price = new JTextField(15);
		price.setFont(MyFont.JTextFieldFont);
		price.setForeground(MyColor.JTextFieldColor);
		price.setEditable(false);
		jp3.add(label_press);
		jp3.add(press);
		jp3.add(label_price);
		jp3.add(price);

		JPanel jp4 = new JPanel();
		label_classify = new JLabel("所属分类");
		classify = new JComboBox();
		classify.setPreferredSize(new Dimension(175, 30));
		classify.setEnabled(false);
		label_area = new JLabel("存储区域");
		area = new JComboBox();
		area.setPreferredSize(new Dimension(175, 30));
		area.setEnabled(false);
		jp4.add(label_classify);
		jp4.add(classify);
		jp4.add(label_area);
		jp4.add(area);

		JPanel jp5 = new JPanel();
		label_publish_date = new JLabel("出版日期");
		publish_date = new JTextField(10);
		publish_date.setFont(MyFont.JTextFieldFont);
		publish_date.setForeground(MyColor.JTextFieldColor);
		publish_date.setEditable(false);

		label_entry_date = new JLabel("录入日期");
		entry_date = new JTextField(10);
		entry_date.setFont(MyFont.JTextFieldFont);
		entry_date.setForeground(MyColor.JTextFieldColor);
		entry_date.setEditable(false);

		label_put_on_date = new JLabel("上架日期");
		put_on_date = new JTextField(10);
		put_on_date.setFont(MyFont.JTextFieldFont);
		put_on_date.setForeground(MyColor.JTextFieldColor);
		put_on_date.setEditable(false);
		jp5.add(label_publish_date);
		jp5.add(publish_date);
		jp5.add(label_entry_date);
		jp5.add(entry_date);
		jp5.add(label_put_on_date);
		jp5.add(put_on_date);

		JPanel jp6 = new JPanel();
		label_format = new JLabel("图书规格");
		format = new JTextArea(2, 30);
		format.setEditable(false);
		format.setFont(MyFont.JTextFieldFont);
		format.setForeground(MyColor.JTextFieldColor);
		format.setBackground(Color.white);
		format.setLineWrap(true);
		jp6.add(label_format);
		jp6.add(format);

		JPanel jp7 = new JPanel();
		label_abstract_descr = new JLabel("摘要附注");
		abstract_descr = new JTextArea(5, 30);
		abstract_descr.setEditable(false);
		abstract_descr.setFont(MyFont.JTextFieldFont);
		abstract_descr.setForeground(MyColor.JTextFieldColor);
		abstract_descr.setBackground(Color.white);
		abstract_descr.setLineWrap(true);
		jp7.add(label_abstract_descr);
		jp7.add(abstract_descr);

		JPanel jp8 = new JPanel();
		label_proposal_reader = new JLabel("读者附注");
		proposal_reader = new JTextArea(5, 30);
		proposal_reader.setEditable(false);
		proposal_reader.setFont(MyFont.JTextFieldFont);
		proposal_reader.setForeground(MyColor.JTextFieldColor);
		proposal_reader.setBackground(Color.white);
		proposal_reader.setLineWrap(true);
		jp8.add(label_proposal_reader);
		jp8.add(proposal_reader);

		// 回显数据信息
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
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp6);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp7);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp8);
		// 将组件加载到contentPanel面板中
		contentPanel.add(ver);
		backgroundPanel.add(contentPanel, BorderLayout.CENTER);
		backgroundPanel.validate();
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
		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(back);
		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(exit);
		backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
		backgroundPanel.validate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == front) {
			if (currentRow > 0) {
				currentRow = currentRow - 1;
				// 刷新数据面板
				this.refreshContentPanel();
			} else {
				JOptionPane.showMessageDialog(null, "前面没有数据了！");
			}
		} else if (e.getSource() == back) {
			if (currentRow < parentTable.getRowCount() - 1) {
				currentRow = currentRow + 1;
				// 刷新数据面板
				this.refreshContentPanel();
			} else {
				JOptionPane.showMessageDialog(null, "后面没有数据了！");
			}
		} else if (e.getSource() == exit) {
			this.setVisible(false);
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
	 * 根据选中的数据记录进行数据回显
	 */
	public void echoData() {
		String book_id = parentTable.getValueAt(currentRow, 0).toString();
		Books book = null;
		try {
			book = booksService.getBooksById(book_id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (book != null) {
			isbn.setText(book.getIsbn());
			isbn.setToolTipText(book.getIsbn());
			name.setText(book.getBook_name());
			name.setToolTipText(book.getBook_name());
			author.setText(book.getAuthor());
			author.setToolTipText(book.getAuthor());
			translator.setText(book.getTranslator());
			translator.setToolTipText(book.getTranslator());
			press.setText(book.getPress());
			press.setToolTipText(book.getPress());
			price.setText(book.getPrice() + "");
			price.setToolTipText(book.getPrice() + "");
			publish_date.setText(book.getPublish_date().substring(0, 10));
			publish_date.setToolTipText(book.getPublish_date().substring(0, 10));
			entry_date.setText(book.getEntry_date().substring(0, 10));
			entry_date.setToolTipText(book.getEntry_date().substring(0, 10));
			put_on_date.setText(book.getPut_on_date().substring(0, 10));
			put_on_date.setToolTipText(book.getPut_on_date().substring(0, 10));
			format.setText(book.getFormat());
			format.setToolTipText(book.getFormat());
			abstract_descr.setText(book.getAbstract_descr());
			abstract_descr.setToolTipText(book.getAbstract_descr());
			proposal_reader.setText(book.getProposal_reader());
			proposal_reader.setToolTipText(book.getProposal_reader());

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
		}
	}

	/**
	 * 刷新内容面板
	 */
	public void refreshContentPanel() {
		backgroundPanel.remove(contentPanel);
		initContentPanel();
	}
}
