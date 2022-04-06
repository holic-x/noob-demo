package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import com.design.sm.fore.ui.control.StockManagerJPanel;
import com.design.sm.service.VendorService;
import com.design.sm.service.impl.VendorServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.Tools;

public class VendorManagerJFrame extends JFrame implements MouseListener,FocusListener {

	// 定义全局组件
	JPanel backgroundPanel, topPanel, toolPanel, searchPanel, tablePanel,
			pagePanel;
	// 借助工具类完成表格数据的封装
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// 定义用到的标签
	JLabel  label_keyword,tool_add, tool_update, tool_delete,
	tool_provide,tool_contact,tool_export,tool_transaction;
	// 定义相应的文本框、组合按钮
	JTextField keyword;
	JButton search;
	// 定义相应的service
	VendorService vendorService = new VendorServiceImpl();
	
	StockManagerJPanel parentPanel;

	/**
	 * 通过构造方法完成初始化
	 */
	public VendorManagerJFrame(StockManagerJPanel parentPanel) {
		backgroundPanel = new JPanel(new BorderLayout());
		this.parentPanel = parentPanel;
		// 初始化布局
		initTopPanel();// 初始化顶部菜单条
		initTablePanel();// 初始化显示的表格数据
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("供应商管理");
		this.setSize(1000, 550);
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
		// 添加增删改工具
		Icon icon_add = new ImageIcon("icons/toolImage/add.png");
		tool_add = new JLabel(icon_add);
		tool_add.setToolTipText("添加供应商信息");// 设置鼠标移动时的显示内容
		tool_add.addMouseListener(this);// 添加鼠标监听

		Icon icon_update = new ImageIcon("icons/toolImage/modify.png");
		tool_update = new JLabel(icon_update);
		tool_update.setToolTipText("修改供应商信息");// 设置鼠标移动时的显示内容
		tool_update.addMouseListener(this);// 添加鼠标监听

		Icon icon_delete = new ImageIcon("icons/toolImage/delete.png");
		tool_delete = new JLabel(icon_delete);
		tool_delete.setToolTipText("删除供应商信息");// 设置鼠标移动时的显示内容
		tool_delete.addMouseListener(this);// 添加鼠标监听

		
		Icon icon_provide = new ImageIcon("icons/toolImage/provide.png");
		tool_provide = new JLabel(icon_provide);
		tool_provide.setToolTipText("供应商品");// 设置鼠标移动时的显示内容
		tool_provide.addMouseListener(this);// 添加鼠标监听
		
		Icon icon_contact = new ImageIcon("icons/toolImage/contact.png");
		tool_contact = new JLabel(icon_contact);
		tool_contact.setToolTipText("联系人");// 设置鼠标移动时的显示内容
		tool_contact.addMouseListener(this);// 添加鼠标监听
		
		Icon icon_export = new ImageIcon("icons/toolImage/export.png");
		tool_export = new JLabel(icon_export);
		tool_export.setToolTipText("导出数据");// 设置鼠标移动时的显示内容
		tool_export.addMouseListener(this);// 添加鼠标监听
		
		Icon icon_transaction = new ImageIcon("icons/toolImage/transaction.png");
		tool_transaction = new JLabel(icon_transaction);
		tool_transaction.setToolTipText("交易记录");// 设置鼠标移动时的显示内容
		tool_transaction.addMouseListener(this);// 添加鼠标监听
		
		// 将初始化完成的工具加载到工具条面板中
		toolPanel.add(tool_add);
		toolPanel.add(tool_update);
		toolPanel.add(tool_delete);
		toolPanel.add(tool_provide);
		toolPanel.add(tool_contact);
		toolPanel.add(tool_export);
		toolPanel.add(tool_transaction);
		// 最终将工具条面板加载到顶部菜单条的最西面
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * 初始化查找面板 设置查找方式：根据供应商名称关键字查找
	 */
	private void initSearchPanel() {
		searchPanel = new JPanel();
		// 设置关键字查找
		label_keyword = new JLabel("供应商查找");
		keyword = new JTextField(20);
		keyword.setText("请输入供应商名称");
		keyword.addFocusListener(this);
		search = new JButton("查找");
		search.addMouseListener(this);
		searchPanel.add(label_keyword);
		searchPanel.add(keyword);
		searchPanel.add(search);
		// 将布局好的组件加载到菜单栏面板的最东面
		topPanel.add(searchPanel, BorderLayout.EAST);
	}

	/**
	 * 初始化显示的表格数据
	 */
	private void initTablePanel() {
		// 要根据下拉框的选项进行筛选数据(要根据productService中返回的行进行设置，随后选择要隐藏的项目即可)
		String[] params = { "供应商id", "供应商", "联系方式", "电子邮箱", "传真号", "公司地址"};
		Vector<Vector> vec = new Vector<>();
		// 获取当前文本框的输入信息，根据文本框的输入查找
		String keyword_string = keyword.getText();
		if(!keyword_string.equals("请输入供应商名称")){
			String text = "%"+keyword_string+"%";
			try {
				vec = vendorService.getVendorByNameKeyword(text);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(keyword_string.equals("请输入供应商名称")){
			try {
				vec = vendorService.findAllVendorVector();
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
		// 隐藏：0 
		// dcm.getColumn(0).setMinWidth(0);
		// dcm.getColumn(0).setMaxWidth(0);

		// 设置滚动条
		jScrollPane = new JScrollPane(table);
		Tools.setJspStyle(jScrollPane);

		tablePanel = new JPanel(new BorderLayout());
		tablePanel.setOpaque(false);// 设置透明度
		tablePanel.add(jScrollPane);
		// 将组件加载到背景中
		backgroundPanel.add(tablePanel, BorderLayout.CENTER);
		// 在刷新数据的时候改变窗体大小，完成数据刷新
		backgroundPanel.validate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==search){
			// 刷新表格数据面板
			tablePanel.removeAll();
			initTablePanel();
			backgroundPanel.validate();
		}else if (e.getSource() == tool_add) {
			// 添加供应商信息
			new AddVendorJFrame(this);
		} else if (e.getSource() == tool_update) {
			// 获取当前选中要操作的数据
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要修改的供应商信息");
			} else {
				// 修改供应商信息
				new UpdateVendorJFrame(this,table,row);
			}
		} else if (e.getSource() == tool_delete) {
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要删除的供应商信息");
			} else {
				// 删除供应商信息
				int choose = JOptionPane.showConfirmDialog(null, "确认删除该供应商信息？");
				if(choose==0){
					try {
						vendorService.deleteVendor(table.getValueAt(row, 0).toString());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "供应商信息删除成功！");
					//刷新数据面板
					this.refreshTablePanel();
				}
			}
		} else if(e.getSource()==tool_provide){
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要查看的供应商");
			} else {
				new ShowVendorProvideProductJFrame(this,table,row);
			}
		}else if(e.getSource()==tool_contact){
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要查看的供应商");
			} else {
				// 联络人资料管理
				new VendorContactManagerJFrame(this,table,row);
			}
		}else if (e.getSource() == tool_export) {
			// 获取当前选中的数据
			String[] ids;
			ArrayList id_list = new ArrayList<>();
			for (int rowindex : table.getSelectedRows()) {
				Object obj = table.getValueAt(rowindex, 0);
				id_list.add(obj);
			}
			// 集合转数组
			ids = (String[]) id_list.toArray(new String[id_list.size()]);
			int result=0;
			try {
				result = vendorService.exportData(ids);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (result == 1) {
				JOptionPane.showMessageDialog(null, "数据导出成功！");
			} else if (result == -1) {
				JOptionPane.showMessageDialog(null, "抱歉！数据导出失败，再试一遍吧！");
			} else if (result == 0) {
				JOptionPane.showMessageDialog(null, "用户取消了操作！");
			}
		}else if(e.getSource()==tool_transaction){
			// 查看供应商交易记录
			int row = table.getSelectedRow();
			if(row<0){
				JOptionPane.showMessageDialog(null, "请选择要查看的供应商信息！");
			}else{
				new ShowVendorTransactionJFrame(this,this.table,row);
			}
			
		}
	}

	/**
	 * 刷新数据面板
	 */
	public void refreshTablePanelBySearch() {
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

	/**
	 * 聚焦事件
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("请输入供应商名称")) {
				keyword.setText("");
				keyword.setFont(MyFont.JTextFieldFont);
				keyword.setForeground(MyColor.JTextFieldColor);
			}
		}
	}

	/**
	 * 失去焦点事件
	 */
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("")) {
				keyword.setText("请输入供应商名称");
				keyword.setFont(MyFont.TipFont);
				keyword.setForeground(MyColor.TipColor);
			}
		}
	}
	
	/**
	 * 刷新数据面板
	 */
	public void refreshTablePanel(){
		tablePanel.removeAll();
		initTablePanel();
	}

}
