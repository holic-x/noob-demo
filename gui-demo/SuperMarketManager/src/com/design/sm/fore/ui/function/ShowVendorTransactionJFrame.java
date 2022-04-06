package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.design.sm.model.PurchaseNote;
import com.design.sm.model.StockMaster;
import com.design.sm.service.EmployeeService;
import com.design.sm.service.PurchaseNoteService;
import com.design.sm.service.StockMasterService;
import com.design.sm.service.StockOrderService;
import com.design.sm.service.VendorContactService;
import com.design.sm.service.impl.EmployeeServiceImpl;
import com.design.sm.service.impl.PurchaseNoteServiceImpl;
import com.design.sm.service.impl.StockMasterServiceImpl;
import com.design.sm.service.impl.StockOrderServiceImpl;
import com.design.sm.service.impl.VendorContactServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.Tools;

public class ShowVendorTransactionJFrame extends JFrame implements
		MouseListener {

	// 定义全局组件
	JPanel backgroundPanel, tablePanel, topPanel;
	// 借助工具类完成表格数据的封装
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	JLabel tool_export, tool_count;
	// 定义相应的service
	StockMasterService stockMasterService = new StockMasterServiceImpl();
	StockOrderService stockOrderService = new StockOrderServiceImpl();
	EmployeeService employeeService = new EmployeeServiceImpl();
	VendorContactService vendorContactService = new VendorContactServiceImpl();
	PurchaseNoteService purchaseNoteService = new PurchaseNoteServiceImpl();

	// 定义父组件
	VendorManagerJFrame parentPanel;
	JTable parentTable;
	int selectedRow;

	public ShowVendorTransactionJFrame(VendorManagerJFrame parentPanel,
			JTable parentTable, int selectedRow) {
		this.parentPanel = parentPanel;
		this.parentTable = parentTable;
		this.selectedRow = selectedRow;
		backgroundPanel = new JPanel(new BorderLayout());
		initTopPanel();
		initTablePanel();
		this.add(backgroundPanel);
		// 设置窗体大小
		this.setTitle("交易记录");
		this.setSize(1000, 550);
		this.setLocationRelativeTo(null);// 设置依赖项
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	/**
	 * 初始化工具栏
	 */
	public void initTopPanel() {
		topPanel = new JPanel();
		Icon icon_export = new ImageIcon("icons/toolImage/export.png");
		tool_export = new JLabel(icon_export);
		tool_export.setToolTipText("导出数据");// 设置鼠标移动时的显示内容
		tool_export.addMouseListener(this);// 添加鼠标监听

		Icon icon_count = new ImageIcon("icons/toolImage/count.png");
		tool_count = new JLabel(icon_count);
		tool_count.setToolTipText("信息统计");// 设置鼠标移动时的显示内容
		tool_count.addMouseListener(this);// 添加鼠标监听
		topPanel.add(tool_export);
		topPanel.add(tool_count);
		backgroundPanel.add(topPanel,BorderLayout.NORTH);
		// 在刷新数据的时候改变窗体大小，完成数据刷新
		backgroundPanel.validate();
	}

	/**
	 * 初始化显示的表格数据
	 */
	private void initTablePanel() {

		// 根据日期数据筛选指定范围内的所有订单信息,刷新数据面板
		// 输入的数据进行查找
		String[] params = { "订单编号", "经手人", "供应商", "联系人", "处理时间", "实付金额","支付方式" };
		Vector<Vector> vec = new Vector<>();
		// 此处是默认查找所有的内容
		try {
			vec = this.pack(this.getSMTransactionByVendor(stockMasterService
					.findAllStockInList()));
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

	/**
	 * 二次筛选对应供应商的订单记录 1.必须是经过审核后的订单 2.订单主表的供应商id与当前选中的供应商id相同
	 */
	public List<StockMaster> getSMTransactionByVendor(List<StockMaster> list) {
		List<StockMaster> findList = new ArrayList<StockMaster>();
		String vendorId = parentTable.getValueAt(selectedRow, 0).toString();
		for (StockMaster sm : list) {
			if (sm.getState() == 1 && sm.getVendor_id().equals(vendorId)) {
				findList.add(sm);
			}
		}
		return findList;
	}

	/**
	 * 自行手动另外封装数据
	 */
	public Vector<Vector> pack(List<StockMaster> list) throws SQLException {
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (StockMaster obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 7; i++) {
					temp.add(obj.getOrder_num());// 订单编号
					temp.add(employeeService.getEmployeeName(obj
							.getHandler_id()));// 经手人姓名
					temp.add(parentTable.getValueAt(selectedRow, 1).toString());// 供应商姓名
					temp.add(vendorContactService.getVendorContactName(obj
							.getContact_id()));// 供应商联系人姓名
					temp.add(obj.getHandle_time());// 处理时间
					PurchaseNote purchaseNote = purchaseNoteService
							.getPurchaseNoteByNum(obj.getOrder_num());
					temp.add(purchaseNote.getActual_amount());// 实付金额
					if(purchaseNote.getPayment()==1){
						temp.add("现金支付");// 支付方式
					}else if(purchaseNote.getPayment()==2){
						temp.add("转账支付");
					}
				}
				rows.add(temp);
			}
		}
		return rows;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tool_export) {
			int result = this.exportData();
			if (result == 0) {
				JOptionPane.showMessageDialog(null, "用户取消了操作！");
			} else if (result == 1) {
				JOptionPane.showMessageDialog(null, "数据导出成功");
			} else if (result == -1) {
				JOptionPane.showMessageDialog(null, "抱歉，数据导出失败，再试试看吧！");
			}
		} else if (e.getSource() == tool_count) {
			String str = this.getAmount();
				JOptionPane.showMessageDialog(null, "总收入："+str);
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
	 * 自定义导出数据
	 */
	public int exportData() {
		try {
			// 创建工作目录
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("data");
			// 创建数据行
			XSSFRow row = sheet.createRow((short) 0);
			XSSFCell cell = null;
			// 创建第一行表头数据
			cell = row.createCell((short) 0);
			cell.setCellValue("订单编号");
			cell = row.createCell((short) 1);
			cell.setCellValue("经手人");
			cell = row.createCell((short) 2);
			cell.setCellValue("供应商");
			cell = row.createCell((short) 3);
			cell.setCellValue("联系人");
			cell = row.createCell((short) 4);
			cell.setCellValue("处理时间");
			cell = row.createCell((short) 5);
			cell.setCellValue("实付金额");
			cell = row.createCell((short) 6);
			cell.setCellValue("支付方式");
			/**
			 * 默认是将全部数据导出 可以通过用户选择相应的数据进行导出
			 */ 
			for (int i = 0; i < table.getRowCount(); i++) {
				row = sheet.createRow(i + 1);
				cell = row.createCell(0);
				cell.setCellValue(table.getValueAt(i, 0).toString());
				cell = row.createCell(1);
				cell.setCellValue(table.getValueAt(i, 1).toString());
				cell = row.createCell(2);
				cell.setCellValue(table.getValueAt(i, 2).toString());
				cell = row.createCell(3);
				cell.setCellValue(table.getValueAt(i, 3).toString());
				cell = row.createCell(4);
				cell.setCellValue(table.getValueAt(i, 4).toString());
				cell = row.createCell(5);
				cell.setCellValue(table.getValueAt(i, 5).toString());
				cell = row.createCell(6);
				cell.setCellValue(table.getValueAt(i, 6).toString());
			}
			// 弹出文件选择框
			JFileChooser chooser = new JFileChooser();
			// 后缀名过滤器
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"表格文件(*.xlsx)", "xlsx");
			chooser.setFileFilter(filter);
			// 下面的方法将阻塞，直到【用户按下保存按钮且“文件名”文本框不为空】或【用户按下取消按钮】
			int option = chooser.showSaveDialog(null);
			if (option == JFileChooser.APPROVE_OPTION) { // 假如用户选择了保存
				File file = chooser.getSelectedFile();
				String fname = chooser.getName(file); // 从文件名输入框中获取文件名
				// 假如用户填写的文件名不带我们制定的后缀名，那么我们给它添上后缀
				if (fname.indexOf(".xlsx") == -1) {
					file = new File(chooser.getCurrentDirectory(), fname
							+ ".xlsx");
				}
				try {
					FileOutputStream FOut = new FileOutputStream(file);
					workbook.write(FOut);
					FOut.flush();
					FOut.close();
					workbook.close();
					return 1;
				} catch (IOException e) {
					System.err.println("IO异常");
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	/**
	 * 创建数据统计方法，统计当前选中的数据的所有数据的总额， 如果没有选中的内容，则默认统计当前表格所有的内容
	 */
	public String getAmount() {
		// 获取当前选中的数据
		String str = "";
		double amount = 0.00;
		int[] rows = table.getSelectedRows();
		if(rows.length!=0){
			//表示选中了数据
			for(int i=0;i<rows.length;i++){
				amount += Double.valueOf(table.getValueAt(rows[i],5).toString());
			}
			str= "当前选中的交易记录的数目为："+rows.length+"--总额共计："+amount;
		}else{
			// 默认统计表格数据
			for(int i=0;i<table.getRowCount();i++){
				amount += Double.valueOf(table.getValueAt(i, 5).toString());
			}
			str= "当前供应商共有"+table.getRowCount()+"单交易--总额共计："+amount;
		}
		return str;
	}
}