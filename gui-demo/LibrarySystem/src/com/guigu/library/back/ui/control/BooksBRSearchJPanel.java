package com.guigu.library.back.ui.control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import com.guigu.library.model.Borrowing;
import com.guigu.library.model.Reader;
import com.guigu.library.model.Renew;
import com.guigu.library.model.Returning;
import com.guigu.library.model.Users;
import com.guigu.library.service.BorrowingService;
import com.guigu.library.service.ReaderService;
import com.guigu.library.service.RenewService;
import com.guigu.library.service.ReturningService;
import com.guigu.library.service.impl.BorrowingServiceImpl;
import com.guigu.library.service.impl.ReaderServiceImpl;
import com.guigu.library.service.impl.RenewServiceImpl;
import com.guigu.library.service.impl.ReturningServiceImpl;
import com.guigu.library.utils.BaseTableModule;
import com.guigu.library.utils.Chooser;
import com.guigu.library.utils.Tools;

public class BooksBRSearchJPanel implements MouseListener, DocumentListener,
		ItemListener {
	// 定义全局组件
	JPanel backgroundPanel, topPanel, toolPanel, tablePanel, searchPanel;
	// 借助工具类完成表格数据的封装
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// 定义用到的标签
	ButtonGroup record;
	JRadioButton tool_borrow, tool_renew, tool_returning, tool_violation;
	JTextField start_time, end_time;
	Chooser start = Chooser.getInstance();
	Chooser end = Chooser.getInstance();

	// 定义相应的service
	ReaderService readerService = new ReaderServiceImpl();
	BorrowingService borrowingService = new BorrowingServiceImpl();
	RenewService renewService = new RenewServiceImpl();
	ReturningService returningService = new ReturningServiceImpl();

	Users user;
	Reader reader;

	/**
	 * 通过构造方法完成初始化
	 */
	public BooksBRSearchJPanel(Users user) {
		this.user = user;
		backgroundPanel = new JPanel(new BorderLayout());
		// 通过当前登录账号获取读者信息
		try {
			reader = readerService.getReaderByUserId(this.user.getUser_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		// 初始化查找面板
		initSearchPanel();
		// 将顶部菜单栏加载到背景面板中
		backgroundPanel.add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * 初始化工具条面板
	 */
	private void initToolPanel() {
		toolPanel = new JPanel();
		Icon icon_borrow = new ImageIcon("icons/toolImage/borrow.png");
		tool_borrow = new JRadioButton(icon_borrow);
		tool_borrow.setToolTipText("借阅记录");// 设置鼠标移动时的显示内容
		tool_borrow.addMouseListener(this);// 添加鼠标监听
		tool_borrow.addItemListener(this);

		Icon icon_renew = new ImageIcon("icons/toolImage/renew.png");
		tool_renew = new JRadioButton(icon_renew);
		tool_renew.setToolTipText("续借记录");// 设置鼠标移动时的显示内容
		tool_renew.addMouseListener(this);// 添加鼠标监听
		tool_renew.addItemListener(this);

		Icon icon_returning = new ImageIcon("icons/toolImage/return.png");
		tool_returning = new JRadioButton(icon_returning);
		tool_returning.setToolTipText("归还记录");// 设置鼠标移动时的显示内容
		tool_returning.addMouseListener(this);// 添加鼠标监听
		tool_returning.addItemListener(this);

		Icon icon_violation = new ImageIcon("icons/toolImage/violation.png");
		tool_violation = new JRadioButton(icon_violation);
		tool_violation.setToolTipText("违章记录");// 设置鼠标移动时的显示内容
		tool_violation.addMouseListener(this);// 添加鼠标监听
		tool_violation.addItemListener(this);

		record = new ButtonGroup();
		record.add(tool_borrow);
		record.add(tool_renew);
		record.add(tool_returning);
		record.add(tool_violation);
		// 将初始化完成的工具加载到工具条面板中
		toolPanel.add(tool_borrow);
		toolPanel.add(Box.createHorizontalStrut(10));
		toolPanel.add(tool_renew);
		toolPanel.add(Box.createHorizontalStrut(10));
		toolPanel.add(tool_returning);
		toolPanel.add(Box.createHorizontalStrut(10));
		toolPanel.add(tool_violation);
		// 最终将工具条面板加载到顶部菜单条的最西面
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * 初始化查找面板 设置查找方式： 根据订单生成的时间范围进行查找
	 */
	private void initSearchPanel() {
		searchPanel = new JPanel();
		Icon start_icon = new ImageIcon("icons/toolImage/start_time.png");
		JLabel label_start = new JLabel(start_icon);
		start_time = new JTextField(20);
		start.register(start_time);
		start_time.getDocument().addDocumentListener(this);

		Icon end_icon = new ImageIcon("icons/toolImage/start_time.png");
		JLabel label_end = new JLabel(end_icon);
		end_time = new JTextField(20);
		end.register(end_time);
		end_time.getDocument().addDocumentListener(this);

		searchPanel.add(label_start);
		searchPanel.add(start_time);
		searchPanel.add(Box.createHorizontalStrut(30));
		searchPanel.add(label_end);
		searchPanel.add(end_time);

		// 将布局好的组件加载到菜单栏面板的最东面
		topPanel.add(searchPanel, BorderLayout.EAST);
	}

	/**
	 * 初始化显示的表格数据
	 */
	private void initTablePanel() {
		tablePanel = new JPanel(new BorderLayout());
		if (tool_borrow.isSelected() || tool_violation.isSelected()) {
			// 输入的数据进行查找
			String[] params = { "借阅id", "借阅编号", "图书id", "图书名称", "读者id", "读者姓名",
					"借阅日期", "应还日期", "借阅状态标识", "借阅状态", "违章状态标识", "违章状态" };
			Vector<Vector> vec = new Vector<>();
			// 判断数据输入的日期格式是否合法
			String start_time_string = start_time.getText();
			String end_time_string = end_time.getText();

			// 如果开始时间为空置为最小值
			if (start_time_string.equals("")) {
				start_time_string = "0000-00-00";
			}
			// 如果结束时间为空置为最大值
			if (end_time_string.equals("")) {
				end_time_string = "9999-99-99";
			}
			// 由于此处设置了最值，如果两个时间选择均为空则会相应设置最值，显示结果是所有的商品数据
			// 进行查找
			if (tool_borrow.isSelected()) {
				try {
					// 根据综合条件筛选读者信息(时间+读者id)
					List<Borrowing> time_list = borrowingService
							.findBorrowingByTime(start_time_string,
									end_time_string);
					List<Borrowing> all_list = borrowingService
							.findAllBorrowing();
					vec = borrowingService.pack(this.BorrowRecordfilter(
							time_list, all_list));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if ((tool_violation.isSelected())) {
				try {
					// 根据综合条件筛选读者信息(时间+读者id)
					List<Borrowing> time_list = borrowingService
							.findViolationBorrowingByTime(start_time_string,
									end_time_string);
					List<Borrowing> all_list = borrowingService
							.findAllBorrowing();
					vec = borrowingService.pack(this.BorrowRecordfilter(
							time_list, all_list));
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
			// 隐藏 2 4 7 9 10
			dcm.getColumn(2).setMinWidth(0);
			dcm.getColumn(2).setMaxWidth(0);
			dcm.getColumn(4).setMinWidth(0);
			dcm.getColumn(4).setMaxWidth(0);
			dcm.getColumn(7).setMinWidth(0);
			dcm.getColumn(7).setMaxWidth(0);
			dcm.getColumn(8).setMinWidth(0);
			dcm.getColumn(8).setMaxWidth(0);
			dcm.getColumn(10).setMinWidth(0);
			dcm.getColumn(10).setMaxWidth(0);
		} else if (tool_renew.isSelected()) {
			// 输入的数据进行查找
			String[] params = { "续借id", "续借编号", "图书id", "图书名称", "读者id", "读者姓名",
					"续借日期" };
			Vector<Vector> vec = new Vector<>();
			// 判断数据输入的日期格式是否合法
			String start_time_string = start_time.getText();
			String end_time_string = end_time.getText();
			if (start_time_string.equals("") && end_time_string.equals("")) {
				// 查找所有的续借记录
				try {
					vec = renewService.pack(renewService
							.findRenewByReaderId(reader.getReader_id()));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				// 如果开始时间为空置为最小值
				if (start_time_string.equals("")) {
					start_time_string = "0000-00-00";
				}
				// 如果结束时间为空置为最大值
				if (end_time_string.equals("")) {
					end_time_string = "9999-99-99";
				}
				// 由于此处设置了最值，如果两个时间选择均为空则会相应设置最值，显示结果是所有的商品数据
				// 进行查找
				try {
					// 根据综合条件筛选读者信息(时间+读者id)
					List<Renew> time_list = renewService.findRenewByTime(
							start_time_string, end_time_string);
					List<Renew> all_list = renewService.findAllRenew();
					vec = renewService.pack(this.RenewRecordfilter(time_list,
							all_list));
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
			// 隐藏 2 4 7 9 10
			dcm.getColumn(2).setMinWidth(0);
			dcm.getColumn(2).setMaxWidth(0);
			dcm.getColumn(4).setMinWidth(0);
			dcm.getColumn(4).setMaxWidth(0);
		} else if (tool_returning.isSelected()) {
			// 输入的数据进行查找
			String[] params = { "归还id", "归还编号", "图书id", "图书名称", "读者id", "读者姓名",
					"归还日期" };
			Vector<Vector> vec = new Vector<>();
			// 判断数据输入的日期格式是否合法
			String start_time_string = start_time.getText();
			String end_time_string = end_time.getText();
			if (start_time_string.equals("") && end_time_string.equals("")) {
				// 查找所有的归还记录
				try {
					vec = returningService.pack(returningService
							.findReturningByReaderId(reader.getReader_id()));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				// 如果开始时间为空置为最小值
				if (start_time_string.equals("")) {
					start_time_string = "0000-00-00";
				}
				// 如果结束时间为空置为最大值
				if (end_time_string.equals("")) {
					end_time_string = "9999-99-99";
				}
				// 由于此处设置了最值，如果两个时间选择均为空则会相应设置最值，显示结果是所有的商品数据
				// 进行查找
				if (tool_borrow.isSelected()) {
					try {
						// 根据综合条件筛选读者信息(时间+读者id)
						List<Returning> time_list = returningService
								.findReturningByTime(start_time_string,
										end_time_string);
						List<Returning> all_list = returningService
								.findAllReturning();
						vec = returningService.pack(this.ReturnRecordfilter(
								time_list, all_list));
					} catch (SQLException e) {
						e.printStackTrace();
					}
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
			// 隐藏 2 4 7 9 10
			dcm.getColumn(2).setMinWidth(0);
			dcm.getColumn(2).setMaxWidth(0);
			dcm.getColumn(4).setMinWidth(0);
			dcm.getColumn(4).setMaxWidth(0);
		}
		// 设置滚动条
		jScrollPane = new JScrollPane(table);
		Tools.setJspStyle(jScrollPane);

		tablePanel.setOpaque(false);// 设置透明度
		tablePanel.add(jScrollPane);
		// 将组件加载到背景中
		backgroundPanel.add(tablePanel, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	/**
	 * 刷新表格数据
	 */
	public void refreshTablePanel() {
		backgroundPanel.remove(tablePanel);
		initTablePanel();
		backgroundPanel.validate();
	}

	/**
	 * 刷新数据面板
	 */
	public void refreshBackgroundPanel() {
		// 移除当前数据面板中的所有数据
		backgroundPanel.removeAll();
		initTopPanel();
		initTablePanel();
		backgroundPanel.validate();// 验证
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

	@Override
	public void insertUpdate(DocumentEvent e) {
		// 文本框变化则相应刷新数据表格
		backgroundPanel.remove(tablePanel);
		initTablePanel();
		backgroundPanel.validate();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// 文本框变化则相应刷新数据表格
		backgroundPanel.remove(tablePanel);
		initTablePanel();
		backgroundPanel.validate();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == tool_borrow) {
			// 更改标签背景颜色，刷新数据面板
			tool_borrow.setBackground(Color.cyan);
			tool_renew.setBackground(Color.white);
			tool_returning.setBackground(Color.white);
			tool_violation.setBackground(Color.white);
			this.refreshTablePanel();
		} else if (e.getSource() == tool_renew) {
			// 更改标签背景颜色，刷新数据面板
			tool_borrow.setBackground(Color.white);
			tool_renew.setBackground(Color.cyan);
			tool_returning.setBackground(Color.white);
			tool_violation.setBackground(Color.white);
			this.refreshTablePanel();
		} else if (e.getSource() == tool_returning) {
			// 更改标签背景颜色，刷新数据面板
			tool_borrow.setBackground(Color.white);
			tool_renew.setBackground(Color.white);
			tool_returning.setBackground(Color.cyan);
			tool_violation.setBackground(Color.white);
			this.refreshTablePanel();
		} else if (e.getSource() == tool_violation) {
			// 更改标签背景颜色，刷新数据面板
			tool_borrow.setBackground(Color.white);
			tool_renew.setBackground(Color.white);
			tool_returning.setBackground(Color.white);
			tool_violation.setBackground(Color.cyan);
			this.refreshTablePanel();
		}
	}

	/**
	 * 根据查找到的借阅数据记录筛选出重复的组合: 时间限制+读者id
	 */
	public List<Borrowing> BorrowRecordfilter(List<Borrowing> time_list,
			List<Borrowing> all_list) {
		List<Borrowing> findlist = new ArrayList<>();
		for (int i = 0; i < time_list.size(); i++) {
			for (int j = 0; j < all_list.size(); j++) {
				if (time_list.get(i).getBorrowing_id()
						.equals(all_list.get(j).getBorrowing_id())) {
					findlist.add(time_list.get(i));
				}
			}
		}
		return findlist;
	}

	public List<Renew> RenewRecordfilter(List<Renew> time_list,
			List<Renew> all_list) {
		List<Renew> findlist = new ArrayList<>();
		for (int i = 0; i < time_list.size(); i++) {
			for (int j = 0; j < all_list.size(); j++) {
				if (time_list.get(i).getRenew_id()
						.equals(all_list.get(j).getRenew_id())) {
					findlist.add(time_list.get(i));
				}
			}
		}
		return findlist;
	}

	public List<Returning> ReturnRecordfilter(List<Returning> time_list,
			List<Returning> all_list) {
		List<Returning> findlist = new ArrayList<>();
		for (int i = 0; i < time_list.size(); i++) {
			for (int j = 0; j < all_list.size(); j++) {
				if (time_list.get(i).getReturning_id()
						.equals(all_list.get(j).getReturning_id())) {
					findlist.add(time_list.get(i));
				}
			}
		}
		return findlist;
	}
}