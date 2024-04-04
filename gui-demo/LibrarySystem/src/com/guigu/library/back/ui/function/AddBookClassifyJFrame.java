package com.guigu.library.back.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.guigu.library.back.ui.control.BooksClassifyManagerJPanel;
import com.guigu.library.model.BookClassify;
import com.guigu.library.service.BookClassifyService;
import com.guigu.library.service.impl.BookClassifyServiceImpl;
import com.guigu.library.utils.ImagePanel;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;

public class AddBookClassifyJFrame extends JFrame implements MouseListener,
		FocusListener {
	// 定义全局组件
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_num, label_name, label_level, label_parent_num;
	JTextField num, name, level, parent_num;
	JButton save_button, reset_button;

	BookClassifyService bookClassifyService = new BookClassifyServiceImpl();

	// 定义父对象
	BooksClassifyManagerJPanel parentPanel;
	JTable parentTable;
	int selectedRow;

	// 通过构造方法初始化数据
	public AddBookClassifyJFrame(BooksClassifyManagerJPanel parentPanel,
			JTable parentTable, int selectedRow) {
		this.parentPanel = parentPanel;
		this.parentTable = parentTable;
		this.selectedRow = selectedRow;
		// 初始化背景
		initBackgroundPanel();
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("添加图书分类信息");
		this.setSize(420, 350);
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
		JLabel title = new JLabel("添加图书分类信息");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * 初始化内容面板
	 */
	private void initContentPanel() {
		/**
		 * 添加图书分类的信息： 图书分类编号根据父编号自定义生成 图书分类名称、分类等级、父级分类均是由当前的选中的数据默认创建下一级分类
		 */
		contentPanel = new JPanel();
		JPanel jp1 = new JPanel();
		label_num = new JLabel("分类编号");
		num = new JTextField(15);
		num.setFont(MyFont.TipFont);
		num.setForeground(MyColor.TipColor);
		num.setText("必填项");
		num.addFocusListener(this);
		jp1.add(label_num);
		jp1.add(num);

		JPanel jp2 = new JPanel();
		label_name = new JLabel("分类名称");
		name = new JTextField(15);
		name.setFont(MyFont.TipFont);
		name.setForeground(MyColor.TipColor);
		name.setText("必填项");
		name.addFocusListener(this);
		jp2.add(label_name);
		jp2.add(name);

		JPanel jp3 = new JPanel();
		label_level = new JLabel("分类等级");
		level = new JTextField(15);
		level.setFont(MyFont.JTextFieldFont);
		level.setForeground(MyColor.JTextFieldColor);
		level.addFocusListener(this);
		level.setEditable(false);
		jp3.add(label_level);
		jp3.add(level);

		JPanel jp4 = new JPanel();
		label_parent_num = new JLabel("父级编号");
		parent_num = new JTextField(15);
		parent_num.setFont(MyFont.JTextFieldFont);
		parent_num.setForeground(MyColor.JTextFieldColor);
		parent_num.addFocusListener(this);
		parent_num.setEditable(false);
		jp4.add(label_parent_num);
		jp4.add(parent_num);

		// 数据回显
		this.echoData();
		Box ver = Box.createVerticalBox();
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp3);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp4);
		// 将组件加载到contentPanel面板中
		contentPanel.add(ver);
	}

	/**
	 * 初始化按钮面板
	 */
	private void initButtonPanel() {
		Box hor = Box.createHorizontalBox();
		buttonPanel = new JPanel();
		save_button = new JButton("保存");
		save_button.setForeground(Color.white);
		save_button.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.blue));

		reset_button = new JButton("重置");
		reset_button.setForeground(Color.white);
		reset_button.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.red));

		// 添加按钮监听
		save_button.addMouseListener(this);
		reset_button.addMouseListener(this);

		// 将按钮加载到面板中
		hor.add(save_button);
		hor.add(Box.createHorizontalStrut(20));
		hor.add(reset_button);
		buttonPanel.add(hor);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == save_button) {
			// 获取当前文本框的数据信息
			String num_string = num.getText();
			String name_string = name.getText();
			int level_int = Integer.valueOf(level.getText());
			String parent_num_string = parent_num.getText();
			if (num_string.equals("必填项")) {
				JOptionPane.showMessageDialog(null, "图书分类编号不能为空");
			} else if (name_string.equals("必填项")) {
				JOptionPane.showMessageDialog(null, "图书分类名称不能为空");
			} else {
				try {
					BookClassify findBC = bookClassifyService
							.getBookClassifyByNum(num_string);
					if (findBC != null) {
						JOptionPane.showMessageDialog(null, "当前编号已存在，换一个试试吧！");
					} else {
						// 创建BookClassify对象，将数据保存到数据库中
						BookClassify bc = new BookClassify(num_string,
								name_string, level_int, parent_num_string);
						int choose = JOptionPane.showConfirmDialog(null,
								"确认添加图书分类信息？");
						if (choose == 0) {
							bookClassifyService.addBookClassify(bc);
							// 完成数据的保存之后输出提示信息，并将子页面隐藏，刷新主面板的数据
							JOptionPane.showMessageDialog(null, "图书分类信息保存成功");
							this.setVisible(false);
							parentPanel.refreshTablePanel();
						} else {
							this.setVisible(false);
						}
					}
				} catch (HeadlessException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == reset_button) {
			// 清空所有的数据
			num.setFont(MyFont.TipFont);
			num.setForeground(MyColor.TipColor);
			num.setText("必填项");
			name.setFont(MyFont.TipFont);
			name.setForeground(MyColor.TipColor);
			name.setText("必填项");
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

	// 聚焦事件
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == num) {
			if (num.getText().equals("必填项")) {
				num.setForeground(MyColor.JTextFieldColor);
				num.setFont(MyFont.JTextFieldFont);
				num.setText("");
			}
		} else if (e.getSource() == name) {
			if (name.getText().equals("必填项")) {
				name.setForeground(MyColor.JTextFieldColor);
				name.setFont(MyFont.JTextFieldFont);
				name.setText("");
			}
		}
	}

	// 失去焦点事件
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == num) {
			if (num.getText().equals("")) {
				num.setFont(MyFont.TipFont);
				num.setForeground(MyColor.TipColor);
				num.setText("必填项");
			}
		} else if (e.getSource() == name) {
			if (name.getText().equals("")) {
				name.setFont(MyFont.TipFont);
				name.setForeground(MyColor.TipColor);
				name.setText("必填项");
			}
		}
	}

	/**
	 * 定义数据回显方法echoData
	 */
	public void echoData() {
		// 根据当前的父级目录创建下一级目录信息
		// 当前选中的记录的分类编号是要创建的新的分类的父级编号
		String parent_num_string = parentTable.getValueAt(selectedRow, 0)
				.toString();
		parent_num.setText(parent_num_string);
		parent_num.setToolTipText(parent_num_string);
		int parent_level = Integer.valueOf(parentTable.getValueAt(selectedRow, 2).toString());
		level.setText((parent_level + 1) + "");
		level.setToolTipText((parent_level + 1) + "");
	}
}