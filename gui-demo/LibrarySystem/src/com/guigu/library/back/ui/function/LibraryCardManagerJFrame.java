package com.guigu.library.back.ui.function;

import java.awt.BorderLayout;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.guigu.library.back.ui.control.ReaderArchivesManagerJPanel;
import com.guigu.library.model.LibraryCard;
import com.guigu.library.model.Reader;
import com.guigu.library.service.LibraryCardService;
import com.guigu.library.service.ReaderClassifyService;
import com.guigu.library.service.ReaderService;
import com.guigu.library.service.UsersService;
import com.guigu.library.service.impl.LibraryCardServiceImpl;
import com.guigu.library.service.impl.ReaderClassifyServiceImpl;
import com.guigu.library.service.impl.ReaderServiceImpl;
import com.guigu.library.service.impl.UsersServiceImpl;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;
import com.guigu.library.utils.RandomGeneration;

public class LibraryCardManagerJFrame extends JFrame implements MouseListener {

	// 定义全局组件
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_id, label_num, label_handle_date, label_valid_till,
			label_loss_state, label_disable_state;
	JTextField id, num, handle_date, valid_till, loss_state, disable_state;

	// 定义service
	ReaderService readerService = new ReaderServiceImpl();
	LibraryCardService libraryCardService = new LibraryCardServiceImpl();

	JLabel loss, active, replace;

	// 定义父对象、父表格、选中的行
	ReaderArchivesManagerJPanel parentPanel;
	JTable parentTable;
	int selectedRow;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String current_date = sdf.format(new Date());

	// 通过构造方法初始化数据
	public LibraryCardManagerJFrame(ReaderArchivesManagerJPanel parentPanel,
			JTable parentTable, int selectedRow) {
		this.parentPanel = parentPanel;
		this.parentTable = parentTable;
		this.selectedRow = selectedRow;
		// 初始化背景
		initBackgroundPanel();
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("借阅证信息管理");
		this.setSize(600, 300);
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
		JLabel title = new JLabel("借阅证信息管理");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * 初始化内容面板
	 */
	private void initContentPanel() {

		contentPanel = new JPanel();

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
		this.echoData();
		Box ver = Box.createVerticalBox();
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp3);
		// 将组件加载到contentPanel面板中
		contentPanel.add(ver);
	}

	/**
	 * 初始化按钮面板
	 */
	private void initButtonPanel() {
		buttonPanel = new JPanel();
		Icon loss_icon = new ImageIcon("icons/toolImage/loss.png");
		loss = new JLabel(loss_icon);
		loss.setToolTipText("挂失/取消挂失");
		loss.addMouseListener(this);

		Icon active_icon = new ImageIcon("icons/toolImage/active.png");
		active = new JLabel(active_icon);
		active.setToolTipText("激活/禁用");
		active.addMouseListener(this);

		Icon replace_icon = new ImageIcon("icons/toolImage/replace.png");
		replace = new JLabel(replace_icon);
		replace.setToolTipText("补办");
		replace.addMouseListener(this);

		buttonPanel.add(loss);
		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(active);
		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(replace);
		backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
		backgroundPanel.validate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			// 根据父表格中当前指定的行获取读者的数据信息：
			String libraryCard_id_string = parentTable.getValueAt(selectedRow,
					14).toString();
			// 根据借阅证id获取详细的借阅证信息
			LibraryCard lc = null;
			lc = libraryCardService.getLibraryCardById(libraryCard_id_string);
			int loss_state_int = lc.getLoss_state();
			int disable_state_int = lc.getDisable_state();
			if (e.getSource() == loss) {
				if (loss_state_int == 0) {
					int choose = JOptionPane.showConfirmDialog(null,
							"确认挂失该借阅证？");
					if (choose == 0) {
						// 挂失、并禁用,刷新数据面板
						lc.setLoss_state(1);
						lc.setDisable_state(1);
						libraryCardService.updateLibraryCard(lc);
						JOptionPane.showMessageDialog(null, "当前借阅证已挂失，将无法使用！");
						this.refreshContentPanel();
					}
				} else if (loss_state_int == 1) {
					int choose = JOptionPane.showConfirmDialog(null, "确认取消挂失？");
					if (choose == 0) {
						// 取消挂失、并激活,刷新数据面板
						lc.setLoss_state(0);
						lc.setDisable_state(0);
						libraryCardService.updateLibraryCard(lc);
						JOptionPane.showMessageDialog(null, "当前借阅证已取消挂失！");
						this.refreshContentPanel();
					}
				}
			} else if (e.getSource() == active) {
				if (disable_state_int == 0) {
					int choose = JOptionPane.showConfirmDialog(null,
							"确认禁用该借阅证？");
					if (choose == 0) {
						// 禁用,刷新数据面板
						lc.setDisable_state(1);
						libraryCardService.updateLibraryCard(lc);
						JOptionPane.showMessageDialog(null, "当前借阅证已禁用！");
						this.refreshContentPanel();
					}
				} else if (disable_state_int == 1) {
					int choose = JOptionPane.showConfirmDialog(null,
							"确认激活该借阅证？");
					if (choose == 0) {
						// 激活,刷新数据面板
						lc.setDisable_state(0);
						libraryCardService.updateLibraryCard(lc);
						JOptionPane.showMessageDialog(null, "当前借阅证已成功激活！");
						this.refreshContentPanel();
					}
				}
			} else if (e.getSource() == replace) {
				// 先判断是否为挂失状态，当为挂失状态方可进行补办，补办则删除当前借阅证信息，重新录入借阅证信息
				if (loss_state_int == 1) {
					int choose = JOptionPane.showConfirmDialog(null,
							"确认进行补办借阅证？");
					if (choose == 0) {
						// 获取当前使用该借阅证的读者id
						String reader_id = parentTable.getValueAt(selectedRow,
								0).toString();
						Reader r = readerService.getReaderById(reader_id);
						// 删除当前借阅证信息
						libraryCardService.deleteLibraryCard(lc.getCard_id());
						// 重新新增借阅证信息
						String libraryCardId = RandomGeneration
								.getRandom32charSeq();
						String libraryCardNum = RandomGeneration
								.getLibraryCardNum(r.getClassify_num());
						LibraryCard newlc = new LibraryCard();
						newlc.setCard_id(libraryCardId);
						newlc.setCard_num(libraryCardNum);
						newlc.setHandle_date(current_date);
						Calendar c = Calendar.getInstance();
						c.setTime(new Date());
						c.add(Calendar.YEAR, 1);
						String valid_till_date = sdf.format(c.getTime());
						newlc.setValid_till(valid_till_date);
						libraryCardService.addLibraryCard(newlc);
						// 重新匹配相应读者
						r.setCard_id(newlc.getCard_id());
						readerService.updateReader(r);
						// 刷新数据面板
						JOptionPane.showMessageDialog(null, "借阅证补办成功！");
						this.setVisible(false);
						parentPanel.refreshTablePanel();
					}
				} else if (loss_state_int == 0) {
					JOptionPane
							.showMessageDialog(null, "当前借阅证不为“挂失状态”，无法进行补办！");
				}
			}
		} catch (HeadlessException | SQLException e1) {
			e1.printStackTrace();
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
	 * 定义数据回显方法
	 */
	public void echoData() {
		// 根据父表格中当前指定的行获取读者的数据信息：
		String libraryCard_id_string = parentTable.getValueAt(selectedRow, 14)
				.toString();
		// 根据借阅证id获取详细的借阅证信息
		LibraryCard lc = null;
		try {
			lc = libraryCardService.getLibraryCardById(libraryCard_id_string);
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

	/**
	 * 刷新内容面板
	 */
	public void refreshContentPanel() {
		backgroundPanel.remove(contentPanel);
		initContentPanel();
		backgroundPanel.add(contentPanel);
		backgroundPanel.validate();
	}
}
