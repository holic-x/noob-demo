package com.guigu.library.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import com.guigu.library.model.BookClassify;
import com.guigu.library.model.Reader;
import com.guigu.library.model.Users;
import com.guigu.library.service.ReaderService;
import com.guigu.library.service.TempService;
import com.guigu.library.service.impl.ReaderServiceImpl;
import com.guigu.library.service.impl.TempServiceImpl;
import com.guigu.library.utils.BaseTableModule;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;
import com.guigu.library.utils.Tools;

public class MyFavoriteJFrame extends JFrame implements MouseListener,
		FocusListener {

	// 定义全局组件
	JPanel backgroundPanel, topPanel, toolPanel, searchPanel, tablePanel,
			downPanel;
	// 借助工具类完成表格数据的封装
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// 定义用到的标签
	JLabel tool_cancelSave;
	JTextField keyword;
	// 定义分级查找所要用到的组件
	JLabel label_level;
	JTextField level;

	// 定义相应的service
	TempService tempService = new TempServiceImpl();
	ReaderService readerService = new ReaderServiceImpl();

	Users user;

	/**
	 * 通过构造方法完成初始化
	 */
	public MyFavoriteJFrame(Users user) {
		backgroundPanel = new JPanel(new BorderLayout());
		this.user = user;
		// 初始化布局
		initTopPanel();// 初始化顶部菜单条
		initTablePanel();// 初始化显示的表格数据

		this.add(backgroundPanel);
		this.setTitle("我的收藏夹");
		this.setSize(600, 600);
		this.setVisible(true);
		this.setLocationRelativeTo(null);// 设置依赖项
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);// 设置关闭方式
		// 当前窗口隐藏，不影响后方数据的使用，而不是关闭整个窗口
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
		toolPanel = new JPanel();

		// 添加增删改工具
		Icon icon_cancelSave = new ImageIcon("icons/toolImage/cancelSave.png");
		tool_cancelSave = new JLabel(icon_cancelSave);
		tool_cancelSave.setToolTipText("取消收藏");// 设置鼠标移动时的显示内容
		tool_cancelSave.addMouseListener(this);// 添加鼠标监听

		// 将初始化完成的工具加载到工具条面板中
		toolPanel.add(tool_cancelSave);
		// 最终将工具条面板加载到顶部菜单条的最西面
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * 初始化显示的表格数据
	 */
	private void initTablePanel() {

		// 输入的数据进行查找
		String[] params = { "记录id", "条形码", "ISBN", "索书号", "书名" };
		Vector<Vector> vec = new Vector<>();
		// 获取使用当前账号的读者信息
		Reader r = null;
		try {
			r = readerService.getReaderByUserId(this.user.getUser_id());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (r != null) {
			try {
				vec = tempService.pack(tempService.findTempByReaderId(r
						.getReader_id()));
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
		// dcm.getColumn(2).setMinWidth(0);
		// dcm.getColumn(2).setMaxWidth(0);

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
		if (e.getSource() == tool_cancelSave) {
			// 新增图书分类信息
			int row = table.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "请选择要取消收藏的选项");
			} else {
				try {
					tempService.deleteTemp(table.getValueAt(row, 0).toString());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "书籍取消收藏成功！");
				this.refreshTablePanel();
			}
		}
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
			if (keyword.getText().equals("请输入图书分类编号或名称")) {
				keyword.setFont(MyFont.JTextFieldFont);
				keyword.setForeground(MyColor.JTextFieldColor);
				keyword.setText("");
			}
		} else if (e.getSource() == level) {
			if (level.getText().equals("请输入≥0的整数")) {
				level.setFont(MyFont.JTextFieldFont);
				level.setForeground(MyColor.JTextFieldColor);
				level.setText("");
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
				keyword.setText("请输入图书分类编号或名称");
			}
		} else if (e.getSource() == level) {
			if (level.getText().equals("")) {
				level.setFont(MyFont.TipFont);
				level.setForeground(MyColor.TipColor);
				level.setText("请输入≥0的整数");
			}
		}
	}

	/**
	 * 结合level和编号、名称综合筛选分类信息
	 */
	public List<BookClassify> screenData(List<BookClassify> listByLevel,
			List<BookClassify> listByKeyWord) {
		List<BookClassify> findList = new ArrayList<BookClassify>();
		for (int i = 0; i < listByLevel.size(); i++) {
			for (int j = 0; j < listByKeyWord.size(); j++) {
				// 如果编号相同默认是同一个对象
				if (listByLevel.get(i).getClassify_num()
						.equals(listByKeyWord.get(j).getClassify_num())) {
					findList.add(listByLevel.get(i));
				}
			}
		}
		return findList;
	}
}