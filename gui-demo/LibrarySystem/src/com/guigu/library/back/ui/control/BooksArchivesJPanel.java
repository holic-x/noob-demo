package com.guigu.library.back.ui.control;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import com.guigu.library.model.Books;
import com.guigu.library.model.Users;
import com.guigu.library.service.BookClassifyService;
import com.guigu.library.service.BooksService;
import com.guigu.library.service.StorageAreaService;
import com.guigu.library.service.impl.BookClassifyServiceImpl;
import com.guigu.library.service.impl.BooksServiceImpl;
import com.guigu.library.service.impl.StorageAreaServiceImpl;
import com.guigu.library.utils.BaseTableModule;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;
import com.guigu.library.utils.Tools;

public class BooksArchivesJPanel implements MouseListener, FocusListener,
		ItemListener {

	// 定义全局组件
	JPanel backgroundPanel, topPanel, toolPanel, searchPanel, tablePanel,
			pagePanel;
	// 借助工具类完成表格数据的封装
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// 定义用到的标签
	JLabel label_search;
	JComboBox field, match;// 检索字段、匹配方式
	JTextField keyword;

	// 单选框按钮
	ButtonGroup searchWay;
	JRadioButton all;
	JRadioButton section;

	// 定义相应的service
	BookClassifyService bookClassifyService = new BookClassifyServiceImpl();
	StorageAreaService storageAreaService = new StorageAreaServiceImpl();
	BooksService booksService = new BooksServiceImpl();

	int maxPage = 0;
	int currentPage;
	// 定义相应的分页组合标签
	JLabel label_all, label_start, label_end, label_last, label_next;
	JTextField page;

	// 定义分页标识
	int flag = 0;
	JTable temp_table;

	Users user;

	/**
	 * 通过构造方法完成初始化
	 */
	public BooksArchivesJPanel(Users user) {
		backgroundPanel = new JPanel(new BorderLayout());
		this.user = user;
		// 初始化布局
		initTopPanel();// 初始化顶部菜单条
		initPagePanel();// 定义分页查找按钮组合
		initTablePanel();// 初始化显示的表格数据
	}

	/**
	 * 初始化顶部的菜单条
	 */
	private void initTopPanel() {
		topPanel = new JPanel(new BorderLayout());
		// 初始化查找面板
		initSearchPanel();
		// 将顶部菜单栏加载到背景面板中
		backgroundPanel.add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * 初始化查找面板
	 */
	private void initSearchPanel() {
		searchPanel = new JPanel(new BorderLayout());
		JPanel jp1 = new JPanel();
		searchWay = new ButtonGroup();
		all = new JRadioButton("所有图书");
		all.addItemListener(this);
		section = new JRadioButton("条件检索");
		section.addItemListener(this);
		searchWay.add(all);
		searchWay.add(section);
		jp1.add(all);
		jp1.add(Box.createVerticalStrut(10));
		jp1.add(section);

		JPanel jp2 = new JPanel();
		/**
		 * 设置检索字段的下拉框属性 0.所有内容 1.书名 2.isbn 3.索书号 4.图书分类 5.作者 6.译者 7.出版社
		 */
		field = new JComboBox();
		field.addItem("所有内容");
		field.addItem("书名");
		field.addItem("isbn");
		field.addItem("索书号");
		field.addItem("图书分类");
		field.addItem("作者");
		field.addItem("译者");
		field.addItem("出版社");

		// 设置匹配方式的下拉框属性： 0.前方一致 1.完全匹配 2.任意匹配
		match = new JComboBox();
		match.addItem("前方一致");
		match.addItem("完全匹配");
		match.addItem("任意匹配");

		keyword = new JTextField(15);
		keyword.setFont(MyFont.TipFont);
		keyword.setForeground(MyColor.TipColor);
		keyword.setText("关键字查找");
		keyword.addFocusListener(this);

		Icon icon_search = new ImageIcon("icons/toolImage/search.png");
		label_search = new JLabel(icon_search);
		label_search.setToolTipText("查找");
		label_search.addMouseListener(this);

		// 将相关组件加载到指定的面板中
		jp2.add(field);
		jp2.add(match);
		jp2.add(keyword);
		jp2.add(label_search);

		searchPanel.add(jp1, BorderLayout.SOUTH);
		searchPanel.add(jp2, BorderLayout.NORTH);

		// 将布局好的组件加载到菜单栏面板的最东面
		topPanel.add(searchPanel, BorderLayout.EAST);
	}

	/**
	 * 初始化显示的表格数据
	 */
	private void initTablePanel() {
		// 修改标识
		flag = 0;
		// 输入的数据进行查找
		String[] params = { "图书id", "条形码", "ISBN", "索书号", "书名", "分类id", "所属分类",
				"存储区域编号", " 存储区域", "作者", "译者", "出版日期", "出版社", "价格", "规格",
				"录入日期", "上架日期", "提要文摘附注", "使用对象附注", "借阅标识", "借阅状态", "上架标识",
				"上架状态", "删除标识" };
		Vector<Vector> vec = new Vector<>();
		if (all.isSelected()) {
			// 显示所有的内容
			try {
				vec = booksService.pack(booksService.findBooksUnion(0, 0));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (section.isSelected()) {
			// 根据条件筛选内容
			// 根据综合条件进行数据筛选:分别获取下拉框和文本框的选项内容
			int field_int = field.getSelectedIndex();
			int match_int = match.getSelectedIndex();
			try {
				if (field_int == 0) {
					// 默认查找所有的内容，忽略后方的查找条件
					vec = booksService.pack(booksService.findBooksUnion(
							field_int, match_int));
				} else {
					// 结合检索字段和匹配方式进行检索
					if (!keyword.getText().equals("关键字查找")) {
						vec = booksService.pack(booksService.findBooksUnion(
								field_int, match_int, keyword.getText()));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 将查询到的数据封装到BbaseTableModule中
		baseTableModule = new BaseTableModule(params, vec);
		table = new JTable(baseTableModule);

		// 渲染第0列，将其显示为多选框进行显示
		table.getColumnModel().getColumn(0)
				.setCellRenderer(new TableCellRenderer() {
					@Override
					public Component getTableCellRendererComponent(
							JTable table, Object value, boolean isSelected,
							boolean hasFocus, int row, int column) {
						JCheckBox ck = new JCheckBox();
						ck.setSelected(isSelected);
						ck.setHorizontalAlignment((int) 0.5f);
						return ck;
					}
				});
		// 利用提供的Tools类美化表格
		Tools.setTableStyle(table);

		// 通过设置行的大小，隐藏某一列内容（只显示想要显示的数据）
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) table
				.getColumnModel();
		// 隐藏5 7 11 13 14 15 16 17 18 19 21 23
		dcm.getColumn(5).setMinWidth(0);
		dcm.getColumn(5).setMaxWidth(0);
		dcm.getColumn(7).setMinWidth(0);
		dcm.getColumn(7).setMaxWidth(0);
		dcm.getColumn(8).setMinWidth(0);
		dcm.getColumn(8).setMaxWidth(0);
		dcm.getColumn(11).setMinWidth(0);
		dcm.getColumn(11).setMaxWidth(0);
		dcm.getColumn(13).setMinWidth(0);
		dcm.getColumn(13).setMaxWidth(0);
		dcm.getColumn(14).setMinWidth(0);
		dcm.getColumn(14).setMaxWidth(0);
		dcm.getColumn(15).setMinWidth(0);
		dcm.getColumn(15).setMaxWidth(0);
		dcm.getColumn(16).setMinWidth(0);
		dcm.getColumn(16).setMaxWidth(0);
		dcm.getColumn(17).setMinWidth(0);
		dcm.getColumn(17).setMaxWidth(0);
		dcm.getColumn(18).setMinWidth(0);
		dcm.getColumn(18).setMaxWidth(0);
		dcm.getColumn(19).setMinWidth(0);
		dcm.getColumn(19).setMaxWidth(0);
		dcm.getColumn(21).setMinWidth(0);
		dcm.getColumn(21).setMaxWidth(0);
		dcm.getColumn(23).setMinWidth(0);
		dcm.getColumn(23).setMaxWidth(0);

		// 设置滚动条
		jScrollPane = new JScrollPane(table);
		Tools.setJspStyle(jScrollPane);

		tablePanel = new JPanel(new BorderLayout());
		tablePanel.setOpaque(false);// 设置透明度
		tablePanel.add(jScrollPane);
		// 将组件加载到背景中
		backgroundPanel.add(tablePanel, BorderLayout.CENTER);
	}

	/**
	 * 分页查找工具面板
	 */
	public void initPagePanel() {
		pagePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		Icon icon_all = new ImageIcon("icons/pageImage/all.png");
		label_all = new JLabel(icon_all);
		label_all.setToolTipText("显示所有");
		label_all.addMouseListener(this);

		Icon icon_start = new ImageIcon("icons/pageImage/start.png");
		label_start = new JLabel(icon_start);
		label_start.setToolTipText("首页");
		label_start.addMouseListener(this);

		Icon icon_end = new ImageIcon("icons/pageImage/end.png");
		label_end = new JLabel(icon_end);
		label_end.setToolTipText("尾页");
		label_end.addMouseListener(this);

		page = new JTextField(5);
		page.setFont(MyFont.JLabelFont);
		page.setForeground(MyColor.JLabelColor);
		page.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				if (page.getText().equals("")) {
					refreshTablePanel();
				} else {
					currentPage = 1;
					refreshTablePanelByPage();
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				Pattern pattern = Pattern.compile("[0-9]*");
				if (pattern.matcher(page.getText()).matches()) {
					currentPage = Integer.valueOf(page.getText());
					refreshTablePanelByPage();
				} else {
					JOptionPane.showMessageDialog(null, "请输入合法的数字！");
					currentPage = 1;
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});

		Icon icon_last = new ImageIcon("icons/pageImage/last.png");
		label_last = new JLabel(icon_last);
		label_last.setToolTipText("上一页");
		label_last.addMouseListener(this);

		Icon icon_next = new ImageIcon("icons/pageImage/next.png");
		label_next = new JLabel(icon_next);
		label_next.setToolTipText("下一页");
		label_next.addMouseListener(this);

		pagePanel.add(label_all);
		pagePanel.add(label_start);
		pagePanel.add(label_last);
		pagePanel.add(page);
		pagePanel.add(label_next);
		pagePanel.add(label_end);
		// 将组件加载到背景中
		backgroundPanel.add(pagePanel, BorderLayout.SOUTH);
		// 在刷新数据的时候改变窗体大小，完成数据刷新
		backgroundPanel.validate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// 判断标识：如果flag为0说明是开始执行分页操作进行赋值
		if (flag == 0) {
			// 初始化最大页数
			maxPage = this.getMaxPage(table.getRowCount());
			// 记录临时表格数据
			temp_table = table;
		}
		if (e.getSource() == label_all) {
			page.setText("");
			this.refreshTablePanel();
		} else if (e.getSource() == label_start) {
			page.setText("1");
			this.refreshTablePanelByPage();
		} else if (e.getSource() == label_end) {
			page.setText(String.valueOf(maxPage));
			this.refreshTablePanelByPage();
		} else if (e.getSource() == label_last) {
			// 获取当前页数进行比较
			if (currentPage > 1) {
				page.setText((currentPage - 1) + "");
			} else if (currentPage == 1) {
				page.setText("1");
			}
			this.refreshTablePanelByPage();
		} else if (e.getSource() == label_next) {
			// 获取当前页数进行比较
			if (maxPage != 0) {
				if (currentPage < maxPage) {
					page.setText((currentPage + 1) + "");
				} else if (currentPage == maxPage) {
					page.setText(maxPage + "");
				}
			}
			this.refreshTablePanelByPage();
		} else if (e.getSource() == label_search) {
			// 如果点击了查找选项则进行筛选
			this.refreshTablePanel();
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

	// 获取焦点事件
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("关键字查找")) {
				keyword.setFont(MyFont.JTextFieldFont);
				keyword.setForeground(MyColor.JTextFieldColor);
				keyword.setText("");
			}
		}
	}

	// 失去焦点事件
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("")) {
				keyword.setFont(MyFont.TipFont);
				keyword.setForeground(MyColor.TipColor);
				keyword.setText("关键字查找");
			}
		}
	}

	/**
	 * 获取当前表格数据的最大分页数，默认是每10条数据进行分页
	 */
	public int getMaxPage(int rows) {
		// 作分页处理
		BigDecimal i = BigDecimal.valueOf(rows);
		BigDecimal i2 = BigDecimal.valueOf(10);
		return i.divide(i2).intValue() + 1;
	}

	/**
	 * 刷新表格数据面板
	 */
	public void refreshTablePanel() {
		// 移除当前数据面板中的所有数据
		backgroundPanel.remove(tablePanel);
		initTablePanel();
		backgroundPanel.validate();// 验证
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			// 如果单选按钮状态发生变化则刷新数据表格信息
			this.refreshTablePanel();
		}
	}

	/**
	 * 分页查找
	 */
	public void refreshTablePanelByPage() {
		// 修改标识
		flag = 1;
		// 移除背景面板的表格数据
		backgroundPanel.remove(tablePanel);
		// 输入的数据进行查找
		String[] params = { "图书id", "条形码", "ISBN", "索书号", "书名", "分类id", "所属分类",
				"存储区域编号", " 存储区域", "作者", "译者", "出版日期", "出版社", "价格", "规格",
				"录入日期", "上架日期", "提要文摘附注", "使用对象附注", "借阅标识", "借阅状态", "上架标识",
				"上架状态", "删除标识" };
		Vector<Vector> vec = new Vector<>();
		if (all.isSelected()) {
			// 显示所有的内容
			try {
				vec = booksService.pack(booksService.findBooksUnion(0, 0));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (section.isSelected()) {
			// 根据条件筛选内容
			// 根据综合条件进行数据筛选:分别获取下拉框和文本框的选项内容
			int field_int = field.getSelectedIndex();
			int match_int = match.getSelectedIndex();
			try {
				if (field_int == 0) {
					// 默认查找所有的内容，忽略后方的查找条件
					vec = booksService.pack(booksService.findBooksUnion(
							field_int, match_int));
				} else {
					// 结合检索字段和匹配方式进行检索
					if (!keyword.getText().equals("关键字查找")) {
						vec = booksService.pack(booksService.findBooksUnion(
								field_int, match_int, keyword.getText()));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (!page.getText().equals("")) {
			// 如果分页查找选择框不为空则认为是在当前查找的数据的基础上进行分页查找
			// 传入vec或者是temp_table数据进行分页查找
			vec = this.findBookByPageTable(temp_table);
			// vec = this.findBookByPageVec(vec);

			baseTableModule = new BaseTableModule(params, vec);
			table = new JTable(baseTableModule);

			// 渲染第0列，将其显示为多选框进行显示
			table.getColumnModel().getColumn(0)
					.setCellRenderer(new TableCellRenderer() {
						@Override
						public Component getTableCellRendererComponent(
								JTable table, Object value, boolean isSelected,
								boolean hasFocus, int row, int column) {
							JCheckBox ck = new JCheckBox();
							ck.setSelected(isSelected);
							ck.setHorizontalAlignment((int) 0.5f);
							return ck;
						}
					});
			// 利用提供的Tools类美化表格
			Tools.setTableStyle(table);

			// 通过设置行的大小，隐藏某一列内容（只显示想要显示的数据）
			DefaultTableColumnModel dcm = (DefaultTableColumnModel) table
					.getColumnModel();
			// 隐藏5 7 11 13 14 15 16 17 18 19 21 23
			dcm.getColumn(5).setMinWidth(0);
			dcm.getColumn(5).setMaxWidth(0);
			dcm.getColumn(7).setMinWidth(0);
			dcm.getColumn(7).setMaxWidth(0);
			dcm.getColumn(11).setMinWidth(0);
			dcm.getColumn(11).setMaxWidth(0);
			dcm.getColumn(13).setMinWidth(0);
			dcm.getColumn(13).setMaxWidth(0);
			dcm.getColumn(14).setMinWidth(0);
			dcm.getColumn(14).setMaxWidth(0);
			dcm.getColumn(15).setMinWidth(0);
			dcm.getColumn(15).setMaxWidth(0);
			dcm.getColumn(16).setMinWidth(0);
			dcm.getColumn(16).setMaxWidth(0);
			dcm.getColumn(17).setMinWidth(0);
			dcm.getColumn(17).setMaxWidth(0);
			dcm.getColumn(18).setMinWidth(0);
			dcm.getColumn(18).setMaxWidth(0);
			dcm.getColumn(19).setMinWidth(0);
			dcm.getColumn(19).setMaxWidth(0);
			dcm.getColumn(20).setMinWidth(0);
			dcm.getColumn(20).setMaxWidth(0);
			dcm.getColumn(21).setMinWidth(0);
			dcm.getColumn(21).setMaxWidth(0);
			dcm.getColumn(22).setMinWidth(0);
			dcm.getColumn(22).setMaxWidth(0);
			dcm.getColumn(23).setMinWidth(0);
			dcm.getColumn(23).setMaxWidth(0);

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
	}

	public Vector<Vector> findBookByPageVec(Vector<Vector> vec) {
		Vector<Vector> vecByPage = new Vector<Vector>();
		if (vec.size() != 0) {
			// 获取当前指定的页数
			currentPage = Integer.valueOf(page.getText());
			if (currentPage != 0 && maxPage != 0) {
				// 每10条数据记录进行分页,作为新的数据记录返回
				int start = (Integer.valueOf(currentPage) - 1) * 10 + 1;
				int end = (Integer.valueOf(currentPage) - 1) * 10 + 10;
				// 数组越界，如果不足的记录数则需要进行处理
				// 此处取余应该是当前根据条件查找到的记录数对最大记录数取余
				int last_page_count = vec.size() % 10;
				// 判断当前页数是否为最后一页,如果为最后一页则设置end界限为对应的last_page_count
				if (currentPage == maxPage) {
					end = (Integer.valueOf(currentPage) - 1) * 10
							+ last_page_count;
				}
				for (int i = start; i <= end; i++) {
					// 计数是从0开始
					vecByPage.addElement(vec.get(i - 1));// 添加记录
				}
			}
		}
		// 返回新的数据记录
		return vecByPage;
	}

	/**
	 * 对当前的数据记录行进行分页查找 初步考虑通过Vector<Vector> vec进行查找，后面发现在添加的过程中存在问题
	 * 因此此处改用table表格数据传入
	 */
	public Vector<Vector> findBookByPageTable(JTable page_table) {
		Vector<Vector> vecByPage = new Vector<Vector>();
		if (page_table != null) {
			if (page_table.getRowCount() != 0) {
				// 获取当前指定的页数
				currentPage = Integer.valueOf(page.getText());
				if (currentPage != 0) {
					// 每10条数据记录进行分页,作为新的数据记录返回
					int start = (Integer.valueOf(currentPage) - 1) * 10 + 1;
					int end = (Integer.valueOf(currentPage) - 1) * 10 + 10;
					// 数组越界，如果不足的记录数则需要进行处理
					// 此处取余应该是当前根据条件查找到的记录数对最大记录数取余
					int last_page_count = (page_table.getRowCount()) % 10;
					// 判断当前页数是否为最后一页,如果为最后一页则设置end界限为对应的last_page_count
					if (currentPage == maxPage) {
						end = (Integer.valueOf(currentPage) - 1) * 10
								+ last_page_count;
					}
					List<Books> findBooks = new ArrayList<Books>();
					for (int i = start; i <= end; i++) {
						// 添加记录
						Books find_book = null;
						try {
							find_book = booksService.getBooksById(page_table
									.getValueAt(i - 1, 0).toString());
						} catch (SQLException e) {
							e.printStackTrace();
						}
						findBooks.add(find_book);
					}
					// 封装新的vec信息
					try {
						vecByPage = booksService.pack(findBooks);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		// 返回新的数据记录
		return vecByPage;
	}
}