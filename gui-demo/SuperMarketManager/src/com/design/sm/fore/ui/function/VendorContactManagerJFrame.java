package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import com.design.sm.model.VendorContact;
import com.design.sm.service.VendorContactService;
import com.design.sm.service.VendorService;
import com.design.sm.service.impl.VendorContactServiceImpl;
import com.design.sm.service.impl.VendorServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.Tools;

public class VendorContactManagerJFrame extends JFrame implements
		MouseListener, ItemListener {
	// 定义全局组件
	JPanel backgroundPanel, topPanel, toolPanel, tablePanel, searchPanel;
	// 借助工具类完成表格数据的封装
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// 定义用到的标签
	JLabel tool_add, tool_update, tool_delete, tool_leadChange;
	// 定义相应查找按钮（此处主要以负责人和普通联络人进行分类）
	ButtonGroup lead_identity;
	JRadioButton all, lead, normal;
	// 定义相应的service
	VendorService vendorService = new VendorServiceImpl();
	VendorContactService contactService = new VendorContactServiceImpl();
	VendorManagerJFrame parentPanel;
	JTable parentTable;
	int selectedRow;

	/**
	 * 通过构造方法完成初始化
	 */
	public VendorContactManagerJFrame(VendorManagerJFrame parentPanel,
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
		this.setTitle("通讯录管理");
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
		tool_add.setToolTipText("添加联络人信息");// 设置鼠标移动时的显示内容
		tool_add.addMouseListener(this);// 添加鼠标监听

		Icon icon_update = new ImageIcon("icons/toolImage/modify.png");
		tool_update = new JLabel(icon_update);
		tool_update.setToolTipText("修改联络人信息");// 设置鼠标移动时的显示内容
		tool_update.addMouseListener(this);// 添加鼠标监听

		Icon icon_delete = new ImageIcon("icons/toolImage/delete.png");
		tool_delete = new JLabel(icon_delete);
		tool_delete.setToolTipText("删除联络人信息");// 设置鼠标移动时的显示内容
		tool_delete.addMouseListener(this);// 添加鼠标监听

		Icon icon_lead = new ImageIcon("icons/toolImage/lead.png");
		tool_leadChange = new JLabel(icon_lead);
		tool_leadChange.setToolTipText("身份管理");// 设置鼠标移动时的显示内容
		tool_leadChange.addMouseListener(this);// 添加鼠标监听

		// 将初始化完成的工具加载到工具条面板中
		toolPanel.add(tool_add);
		toolPanel.add(tool_update);
		toolPanel.add(tool_delete);
		toolPanel.add(tool_leadChange);
		// 最终将工具条面板加载到顶部菜单条的最西面
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * 初始化查找面板 设置查找方式：根据联络人身份标识进行筛选
	 */
	private void initSearchPanel() {
		searchPanel = new JPanel();
		lead_identity = new ButtonGroup();
		all = new JRadioButton("所有");
		all.addItemListener(this);
		lead = new JRadioButton("负责人");
		lead.addItemListener(this);
		normal = new JRadioButton("普通联络员");
		normal.addItemListener(this);
		lead_identity.add(all);
		lead_identity.add(lead);
		lead_identity.add(normal);
		searchPanel.add(all);
		searchPanel.add(lead);
		searchPanel.add(normal);
		// 将布局好的组件加载到菜单栏面板的最东面
		topPanel.add(searchPanel, BorderLayout.EAST);
	}

	/**
	 * 初始化显示的表格数据
	 */
	private void initTablePanel() {
		// 要根据下拉框的选项进行筛选数据(要根据productService中返回的行进行设置，随后选择要隐藏的项目即可)
		String[] params = { "联络人id", "联络人", "联系方式", "电子邮箱", "所属供应商id", "所属供应商",
				"身份标识", "身份" };
		Vector<Vector> vec = new Vector<>();
		// 获取当前选中的供应商的供应商id
		String vendorId = parentTable.getValueAt(selectedRow, 0).toString();
		try {
			if (all.isSelected()) {
				vec = contactService.pack(this.getVendorContactByIdentity(vendorId,
						-1));
			} else if (lead.isSelected()) {
				vec = contactService.pack(this
						.getVendorContactByIdentity(vendorId, 1));
			} else if (normal.isSelected()) {
				vec = contactService.pack(this
						.getVendorContactByIdentity(vendorId, 0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
		// 隐藏：0 4 6
		// dcm.getColumn(0).setMinWidth(0);
		// dcm.getColumn(0).setMaxWidth(0);
		dcm.getColumn(4).setMinWidth(0);
		dcm.getColumn(4).setMaxWidth(0);
		dcm.getColumn(6).setMinWidth(0);
		dcm.getColumn(6).setMaxWidth(0);
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
		if (e.getSource() == tool_add) {
			// 添加联络人信息
			 new AddVendorContactJFrame(this);
		} else if (e.getSource() == tool_update) {
			// 获取当前选中要操作的数据
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要修改的联络人信息");
			} else {
				// 修改联络人信息
				 new UpdateVendorContactJFrame(this, table, row);
			}
		} else if (e.getSource() == tool_delete) {
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要删除的联络人信息");
			} else {
				// 删除联络人信息
				int choose = JOptionPane.showConfirmDialog(null, "确认删除该联络人信息？");
				if (choose == 0) {
					try {
						contactService.deleteVendorContact(table.getValueAt(
								row, 0).toString());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "联络人信息删除成功！");
					// 刷新数据面板
					this.refreshTablePanel();
				}
			}
		} else if (e.getSource() == tool_leadChange) {
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要进行操作的联系人！");
			} else {
				VendorContact vc = null;
				try {
					vc = contactService.getVendorContactByContactId(table.getValueAt(row, 0).toString());
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				if(vc.getOwner_flag()==0){
					int choose = JOptionPane.showConfirmDialog(null, "确认指定其为负责人？");
					if(choose==0){
						try {
							vc.setOwner_flag(1);
							contactService.updateVendorContact(vc);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						// 刷新数据面板信息
						parentPanel.refreshTablePanel();
					}
				}else if(vc.getOwner_flag()==1){
					int choose = JOptionPane.showConfirmDialog(null, "确认取消其负责人身份？");
					if(choose==0){
						try {
							vc.setOwner_flag(0);
							contactService.updateVendorContact(vc);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						// 刷新数据面板信息
						parentPanel.refreshTablePanel();
					}
				}
				
			}
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
	 * 刷新数据面板
	 */
	public void refreshTablePanel() {
		tablePanel.removeAll();
		initTablePanel();
	}

	// 根据单选按钮选项获取相应的联系人信息
	public List<VendorContact> getVendorContactByIdentity(String vendorId,
			int choose) {
		List<VendorContact> all_list = null;
		try {
			all_list = contactService.getVendorContactByVendorId(vendorId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<VendorContact> vc_list = new ArrayList<VendorContact>();
		if (choose == -1) {
			// 显示所有的联系人信息
			vc_list = all_list;
		} else if (choose == 0) {
			// 显示普通联络员的信息
			for (VendorContact vc : all_list) {
				if (vc.getOwner_flag() == 0) {
					vc_list.add(vc);
				}
			}
		} else if (choose == 1) {
			// 显示负责人的信息
			for (VendorContact vc : all_list) {
				if (vc.getOwner_flag() == 1) {
					vc_list.add(vc);
				}
			}
		}
		return vc_list;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			// 如果点击了下拉框选项，仅仅刷新数据面板,要先将数据面板的内容移除之后再添加
			tablePanel.removeAll();// 移除数据面板中的所有数据
			initTablePanel();// 重新初始化面板
		}
	}
}
