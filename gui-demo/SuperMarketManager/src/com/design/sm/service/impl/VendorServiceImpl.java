package com.design.sm.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.design.sm.dao.VendorDAO;
import com.design.sm.dao.impl.VendorDAOImpl;
import com.design.sm.model.Product;
import com.design.sm.model.Vendor;
import com.design.sm.service.VendorService;


public class VendorServiceImpl implements VendorService{

	private VendorDAO dao = new VendorDAOImpl();
	@Override
	public Object getVendorName(String id) throws SQLException {
		return dao.getVendorName(id);
	}

	@Override
	public Vector<Vector> findAllVendorVector() throws SQLException {
		List<Vendor> list = dao.findAllVendor();
		return this.pack(list);
	}

	@Override
	public List<Vendor> findAllVendorList() throws SQLException {
		return dao.findAllVendor();
	}
	
	public Vector<Vector> pack(List<Vendor> list) throws SQLException{
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (Vendor obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 7; i++) {
					/**
					 * 显示 vendor_id、vendor_name、vendor_phone、
					 * vendor_email、vendor_fax、vendor_address	
					 */
					temp.add(obj.getVendor_id());//供应商id
					temp.add(obj.getVendor_name());//供应商名称
					temp.add(obj.getVendor_phone());//供应商联系方式
					temp.add(obj.getVendor_email());//邮箱
					temp.add(obj.getVendor_fax());//传真号
					temp.add(obj.getVendor_address());//地址
				}
				rows.add(temp);
			}
		}
		return rows;
	}

	@Override
	public void addVendor(Vendor v) throws SQLException {
		dao.addVendor(v);
	}

	@Override
	public void updateVendor(Vendor v) throws SQLException {
		dao.updateVendor(v);
	}

	@Override
	public void deleteVendor(String id) throws SQLException {
		dao.deleteVendor(id);
	}

	@Override
	public Vector<Vector> getVendorByNameKeyword(String keyword)
			throws SQLException {
		List<Vendor> list = dao.getVendorByNameKeyword(keyword);
		return this.pack(list);
	}

	@Override
	public int exportData(String[] vendorIds) throws SQLException {
		try {
			// 创建工作目录
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("data");
			// 创建数据行
			XSSFRow row = sheet.createRow((short) 0);
			XSSFCell cell = null;
			// 创建第一行表头数据
			cell = row.createCell((short) 0);
			cell.setCellValue("供应商");
			cell = row.createCell((short) 1);
			cell.setCellValue("联系方式");
			cell = row.createCell((short) 2);
			cell.setCellValue("电子邮箱");
			cell = row.createCell((short) 3);
			cell.setCellValue("传真");
			cell = row.createCell((short) 4);
			cell.setCellValue("公司地址");
			cell = row.createCell((short) 5);
			/**
			 * 默认是将全部数据导出
			 * 可以通过用户选择相应的数据进行导出
			 */
			List<Vendor> list = null;
			if(vendorIds!=null){
				list = this.findAllVendorByIds(vendorIds);
			}else{
				list = dao.findAllVendor();
			}
			for (int i = 0; i < list.size(); i++) {
				Vendor v = list.get(i);
				row = sheet.createRow(i + 1);
				cell = row.createCell(0);
				cell.setCellValue(v.getVendor_name());
				cell = row.createCell(1);
				cell.setCellValue(v.getVendor_phone());
				cell = row.createCell(2);
				cell.setCellValue(v.getVendor_email());
				cell = row.createCell(3);
				cell.setCellValue(v.getVendor_fax());
				cell = row.createCell(4);
				cell.setCellValue(v.getVendor_address());
			}
		//弹出文件选择框  
	    JFileChooser chooser = new JFileChooser();  
	    //后缀名过滤器  
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(  
	            "表格文件(*.xlsx)", "xlsx");  
	    chooser.setFileFilter(filter);  
	    //下面的方法将阻塞，直到【用户按下保存按钮且“文件名”文本框不为空】或【用户按下取消按钮】  
	    int option = chooser.showSaveDialog(null);  
	    if(option==JFileChooser.APPROVE_OPTION){    //假如用户选择了保存  
	        File file = chooser.getSelectedFile();  
	        String fname = chooser.getName(file);   //从文件名输入框中获取文件名  
	        //假如用户填写的文件名不带我们制定的后缀名，那么我们给它添上后缀  
	        if(fname.indexOf(".xlsx")==-1){  
	            file=new File(chooser.getCurrentDirectory(),fname+".xlsx");  
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

	@Override
	public List<Vendor> findAllVendorByIds(String[] vendorIds)
			throws SQLException {
		List<Vendor> list = new ArrayList<Vendor>();
		for(String id : vendorIds){
			Vendor v = dao.getVendorById(id);
			if(v!=null){
				list.add(v);
			}
		}
		return list;
	}
}
