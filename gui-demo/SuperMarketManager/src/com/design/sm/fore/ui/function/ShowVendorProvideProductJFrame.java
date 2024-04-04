package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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

import com.design.sm.model.Accounts;
import com.design.sm.model.Category;
import com.design.sm.model.Product;
import com.design.sm.model.Warehouse;
import com.design.sm.service.AccountsService;
import com.design.sm.service.CategoryService;
import com.design.sm.service.ProductService;
import com.design.sm.service.WarehouseService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.CategoryServiceImpl;
import com.design.sm.service.impl.ProductServiceImpl;
import com.design.sm.service.impl.WarehouseServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.Item;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.Tools;

public class ShowVendorProvideProductJFrame extends JFrame implements
		MouseListener, ItemListener, FocusListener {
	// 定义全局组件
	JPanel backgroundPanel, topPanel, toolPanel, searchPanel, tablePanel;
	// 借助工具类完成表格数据的封装
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// 定义用到的标签
	JLabel label_keyword, tool_detail, tool_export;
	// 定义相应的文本框、组合按钮
	JTextField keyword;
	JButton search;
	// 定义相应的service
	AccountsService accountsService = new AccountsServiceImpl();
	ProductService productService = new ProductServiceImpl();

	VendorManagerJFrame parentPanel;
	JTable parentTable;
	int selectedRow;

	/**
	 * 通过构造方法完成初始化
	 */
	public ShowVendorProvideProductJFrame(VendorManagerJFrame parentPanel,
			JTable parentTable, int selectedRow) {
		backgroundPanel = new JPanel(new BorderLayout());
		this.parentPanel = parentPanel;
		this.parentTable = parentTable;
		this.selectedRow = selectedRow;
		// 初始化布局
		initTopPanel();// 初始化顶部菜单条
		initTablePanel();// 初始化显示的表格数据
		// 将背景面板添加到窗体中
		this.add(backgroundPanel);
		this.setTitle("查看供应商提供商品");
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
		// 添加辅助工具
		Icon icon_detail = new ImageIcon("icons/toolImage/detail.png");
		tool_detail = new JLabel(icon_detail);
		tool_detail.setToolTipText("商品详情");// 设置鼠标移动时的显示内容
		tool_detail.addMouseListener(this);// 添加鼠标监听

		Icon icon_export = new ImageIcon("icons/toolImage/export.png");
		tool_export = new JLabel(icon_export);
		tool_export.setToolTipText("导出数据");// 设置鼠标移动时的显示内容
		tool_export.addMouseListener(this);// 添加鼠标监听

		toolPanel.add(tool_detail);
		toolPanel.add(tool_export);
		// 最终将工具条面板加载到顶部菜单条的最西面
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * 初始化查找面板 设置查找方式： 通过商品关键字查找
	 */
	private void initSearchPanel() {
		searchPanel = new JPanel();
		// 设置关键字查找
		label_keyword = new JLabel("商品名称");
		keyword = new JTextField(20);
		keyword.setText("关键字");
		keyword.addFocusListener(this);
		search = new JButton("查找");
		search.addMouseListener(this);

		// 将相关组件加载到指定的面板中
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
		String[] params = { "商品id", "条形码", "商品名称", "成本", "售价", "已上架", "仓库库存",
				"安全库存", "单位id", "单位", "产地", "生产日期", "商品描述", "折扣", "促销标识",
				"促销状态", "促销价格", "删除标识", "分类id", "分类", "供应商id", "供应商", "仓库id",
				"仓库" };
		Vector<Vector> vec = new Vector<>();
		// 获取文本框以及单选框的内容
		String keyword_string = keyword.getText();
		// 获取当前供应商id
		String vendorId = parentTable.getValueAt(selectedRow,0).toString();
		if (keyword_string.equals("关键字")) {
			try {
				vec = productService.pack(productService
						.getProductByVendorId(vendorId));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else {
			String text = "%" + keyword.getText() + "%";
			try {
				vec = productService.pack(this.getProductByNameUnionVendorId(text, vendorId));
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
		// 隐藏：0 1 6 7 8 12 13 14 16 17 18 19 21
		// dcm.getColumn(0).setMinWidth(0);
		// dcm.getColumn(0).setMaxWidth(0);
		dcm.getColumn(5).setMinWidth(0);
		dcm.getColumn(5).setMaxWidth(0);
		dcm.getColumn(6).setMinWidth(0);
		dcm.getColumn(6).setMaxWidth(0);
		dcm.getColumn(7).setMinWidth(0);
		dcm.getColumn(7).setMaxWidth(0);
		dcm.getColumn(8).setMinWidth(0);
		dcm.getColumn(8).setMaxWidth(0);
		dcm.getColumn(12).setMinWidth(0);
		dcm.getColumn(12).setMaxWidth(0);
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
		dcm.getColumn(20).setMinWidth(0);
		dcm.getColumn(20).setMaxWidth(0);
		dcm.getColumn(21).setMinWidth(0);
		dcm.getColumn(21).setMaxWidth(0);
		dcm.getColumn(22).setMinWidth(0);
		dcm.getColumn(22).setMaxWidth(0);

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
		if (e.getSource() == search) {
			// 刷新数据面板
			tablePanel.removeAll();
			initTablePanel();
			backgroundPanel.validate();
		} else if (e.getSource() == tool_detail) {
			// 查看商品详细信息
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要查看的商品");
			} else {
				 new ShowVendorProvideProductDetailJFrame(this, table, row);
			}
		} else if (e.getSource() == tool_export) {
			// 获取当前选中的数据
			String[] ids;
			ArrayList id_list = new ArrayList<>();
			for (int rowindex : table.getSelectedRows()) {
				Object obj = table.getValueAt(rowindex, 0);
				id_list.add(obj);
			}
			// 集合转数组
			ids = (String[]) id_list.toArray(new String[id_list.size()]);
			int result = productService.exportData(ids);
			if (result == 1) {
				JOptionPane.showMessageDialog(null, "数据导出成功！");
			} else if (result == -1) {
				JOptionPane.showMessageDialog(null, "抱歉！数据导出失败，再试一遍吧！");
			} else if (result == 0) {
				JOptionPane.showMessageDialog(null, "用户取消了操作！");
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

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			// 如果点击了下拉框选项，仅仅刷新数据面板,要先将数据面板的内容移除之后再添加
			tablePanel.removeAll();// 移除数据面板中的所有数据
			initTablePanel();// 重新初始化面板
		}
	}

	/**
	 * 聚焦事件
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("关键字")) {
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
				keyword.setText("关键字");
				keyword.setFont(MyFont.TipFont);
				keyword.setForeground(MyColor.TipColor);
			}
		}
	}

	/**
	 * 通过商品名称关键字查找商品信息，再由商品供应商信息进行二次筛选
	 */
	public List<Product> getProductByNameUnionVendorId(String text,
			String vendorId) {
		List<Product> list = new ArrayList<Product>();
		// 获取通过名称关键字筛选的产品信息
		List<Product> list_keyword = null;
		try {
			list_keyword = productService.findAllProductListByNameKeyword(text);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 通过获取当前选中的供应商的详细信息（供应商id）进行二次筛选
		for (Product p : list_keyword) {
			if (p.getVendor_id().equals(vendorId)) {
				list.add(p);
			}
		}
		return list;
	}
}
