package com.guigu.library.fore.ui.control;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

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
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import com.guigu.library.fore.ui.function.MyFavoriteJFrame;
import com.guigu.library.model.Books;
import com.guigu.library.model.Reader;
import com.guigu.library.model.Temp;
import com.guigu.library.model.Users;
import com.guigu.library.service.BooksService;
import com.guigu.library.service.ReaderService;
import com.guigu.library.service.TempService;
import com.guigu.library.service.impl.BooksServiceImpl;
import com.guigu.library.service.impl.ReaderServiceImpl;
import com.guigu.library.service.impl.TempServiceImpl;
import com.guigu.library.utils.BaseTableModule;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;
import com.guigu.library.utils.RandomGeneration;
import com.guigu.library.utils.Tools;
/**
 * 根据当前时间段筛选出借阅次数最多的图书
 * 与此同时，将以数据统计图的形式显示出相应的数据信息
 */
public class BooksRecommendJPanel implements MouseListener,
		ItemListener {

	// 定义全局组件
	JPanel backgroundPanel, topPanel, toolPanel, searchPanel, tablePanel,
			pagePanel;
	// 借助工具类完成表格数据的封装
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// 定义用到的标签
	JLabel tool_refresh, tool_save, tool_favorite;

	// 定义相应的service
	BooksService booksService = new BooksServiceImpl();
	TempService tempService = new TempServiceImpl();
	ReaderService readerService = new ReaderServiceImpl();

	Users user;

	/**
	 * 通过构造方法完成初始化
	 */
	public BooksRecommendJPanel(Users user) {
		backgroundPanel = new JPanel(new BorderLayout());
		this.user = user;
		// 初始化布局
		initTopPanel();// 初始化顶部菜单条
		initTablePanel();// 初始化显示的表格数据
	}

	/**
	 * 初始化顶部的菜单条
	 */
	private void initTopPanel() {
		topPanel = new JPanel(new BorderLayout());
		// 初始化工具条面板
		initToolPanel();
		// 将顶部菜单栏加载到背景面板中
		backgroundPanel.add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * 初始化工具条面板
	 */
	private void initToolPanel() {
		toolPanel = new JPanel(new BorderLayout());

		JPanel jp1 = new JPanel();
		
		Icon icon_refresh = new ImageIcon("icons/toolImage/refresh.png");
		tool_refresh = new JLabel(icon_refresh);
		tool_refresh.setToolTipText("数据刷新");// 设置鼠标移动时的显示内容
		tool_refresh.addMouseListener(this);// 添加鼠标监听
		
		Icon icon_save = new ImageIcon("icons/toolImage/save.png");
		tool_save = new JLabel(icon_save);
		tool_save.setToolTipText("添加至收藏夹");// 设置鼠标移动时的显示内容
		tool_save.addMouseListener(this);// 添加鼠标监听

		Icon icon_favorite = new ImageIcon("icons/toolImage/favorite.png");
		tool_favorite = new JLabel(icon_favorite);
		tool_favorite.setToolTipText("我的收藏");// 设置鼠标移动时的显示内容
		tool_favorite.addMouseListener(this);// 添加鼠标监听

		// 将初始化完成的工具加载到工具条面板中
		jp1.add(tool_save);
		jp1.add(tool_favorite);
		toolPanel.add(jp1, BorderLayout.NORTH);
		// 最终将工具条面板加载到顶部菜单条的最西面
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * 初始化显示的表格数据
	 */
	private void initTablePanel() {
		// 输入的数据进行查找
		String[] params = { "图书id", "条形码", "ISBN", "索书号", "书名", "分类id", "所属分类",
				"存储区域编号", " 存储区域", "作者", "译者", "出版日期", "出版社", "价格", "规格",
				"录入日期", "上架日期", "提要文摘附注", "使用对象附注", "借阅标识", "借阅状态", "上架标识",
				"上架状态", "删除标识" };
		Vector<Vector> vec = new Vector<>();
//		vec = booksService.pack(booksService.findBooksUnion(field_int,
//				match_int, keyword.getText()));
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
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tool_refresh) {
			// 如果点击了刷新选项则进行表格数据的刷新
			this.refreshTablePanel();
		} else if (e.getSource() == tool_save) {
			int row = table.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "请选择要添加至收藏夹的书籍信息！");
			} else {
				try {
					// 将指定书籍添加至我的收藏夹：书籍存在（ISBN相同则认为是同一本书）则提示存在信息，书籍信息不存在则进行添加
					String book_id = table.getValueAt(row, 0).toString();
					Books b = booksService.getBooksById(book_id);
					String reader_id = null;
					// 根据当前登录用户获取Reader信息
					Reader r = readerService.getReaderByUserId(this.user
							.getUser_id());
					if (r != null) {
						reader_id = r.getReader_id();
					}
					Temp t = tempService.getTempByISBN(b.getIsbn(), reader_id);
					if (t != null) {
						JOptionPane.showMessageDialog(null,
								"该书籍已添加至收藏夹，不能重复操作！");
					} else {
						// 临时记录id是随机生成的32位char类型的数据
						String temp_id = RandomGeneration.getRandom32charSeq();
						// 创建Temp记录进行添加
						Temp newTemp = new Temp(temp_id, book_id, reader_id);
						tempService.addTemp(newTemp);
						JOptionPane.showMessageDialog(null, "该书籍已成功添加至收藏夹！");
					}
				} catch (HeadlessException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == tool_favorite) {
			// 查看我的收藏夹
			new MyFavoriteJFrame(this.user);
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

}
