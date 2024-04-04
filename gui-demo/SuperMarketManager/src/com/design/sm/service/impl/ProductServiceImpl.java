package com.design.sm.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
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

import com.design.sm.dao.CategoryDAO;
import com.design.sm.dao.ProductDAO;
import com.design.sm.dao.UnitsDAO;
import com.design.sm.dao.VendorDAO;
import com.design.sm.dao.WarehouseDAO;
import com.design.sm.dao.impl.CategoryDAOImpl;
import com.design.sm.dao.impl.ProductDAOImpl;
import com.design.sm.dao.impl.UnitsDAOImpl;
import com.design.sm.dao.impl.VendorDAOImpl;
import com.design.sm.dao.impl.WarehouseDAOImpl;
import com.design.sm.model.Product;
import com.design.sm.service.ProductService;

public class ProductServiceImpl implements ProductService {

	private UnitsDAO unitsDAO = new UnitsDAOImpl();
	private CategoryDAO categoryDAO = new CategoryDAOImpl();
	private WarehouseDAO warehouseDAO = new WarehouseDAOImpl();
	private VendorDAO vendorDAO = new VendorDAOImpl();
	private ProductDAO dao = new ProductDAOImpl();

	@Override
	public Vector<Vector> findAllProductVector() throws SQLException {
		return this.pack(dao.findAllProduct());
	}

	@Override
	public List<Product> findAllProductList() throws SQLException {
		return dao.findAllProduct();
	}
	
	@Override
	public Vector<Vector> findAllProductByCategoryId(String cid)
			throws SQLException {
		return this.pack(dao.findAllProductByCategoryId(cid));
	}

	@Override
	public Vector<Vector> findAllProductByWarehouseId(String wid)
			throws SQLException {
		return this.pack(dao.findAllProductByWarehouseId(wid));
	}

	@Override
	public Vector<Vector> findAllProductUnion(String cid, String wid)
			throws SQLException {
		return this.pack(dao.findAllProductUnion(cid, wid));
	}

	@Override
	public void saveProductDetail(Product prod) throws SQLException {
		dao.saveProductDetail(prod);
	}

	@Override
	public void saveProductSimple(Product prod) throws SQLException {
		dao.saveProductSimple(prod);
	}

	@Override
	public void deleteProduct(String id) throws SQLException {
		dao.deleteProduct(id);
	}

	@Override
	public void updateProduct(Product prod) throws SQLException {
		dao.updateProduct(prod);
	}

	@Override
	public Object getProductName(String id) throws SQLException {
		return dao.getProductName(id);
	}

	// 定义一个公有方法将List<Product> 类型封装为 Vendor<Vendor>类型
	public Vector<Vector> pack(List<Product> list) throws SQLException {
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (Product obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 23; i++) {
					temp.add(obj.getProd_id());//商品id
					temp.add(obj.getFlow_id());//条形码
					temp.add(obj.getProd_name());//商品名称
					temp.add(obj.getProd_cost());//商品成本
					temp.add(obj.getProd_price());//商品售价
					temp.add(obj.getPutaway_stock());//已上架库存
					temp.add(obj.getCurrent_stock());//当前仓库库存
					temp.add(obj.getSafe_stock());//安全库存
					temp.add(obj.getProd_unit());//单位id
					temp.add(unitsDAO.getUnitsName(obj.getProd_unit()));//单位名称
					temp.add(obj.getProd_origin());//产地
					temp.add(obj.getProd_date());//生产日期
					temp.add(obj.getProd_descr());//商品描述
					temp.add(obj.getProd_discount());//商品折扣
					temp.add(obj.getPromotion_flag());//促销标识
					temp.add(this.getPromotionState(obj.getPromotion_flag()));//促销状态
					temp.add(obj.getPromotion_price());//促销价格
					temp.add(obj.getDelete_flag());//删除标识	
					temp.add(obj.getCategory_id());//商品分类id
					temp.add(categoryDAO.getCategoryName(obj.getCategory_id()));//分类名称
					temp.add(obj.getVendor_id());//供应商id
					temp.add(vendorDAO.getVendorName(obj.getVendor_id()));//供应商名称
					temp.add(obj.getWarehouse_id());//仓库id
					temp.add(warehouseDAO.getWarehouseName(obj.getWarehouse_id()));//仓库名称
					/**
					 * 理货员查看信息
					 * 显示信息：名称、成本、售价、已上架库存、促销状态、单位名称、产地、生产日期、分类名称、供应商名称、仓库名称
					 * 即隐藏：0 1 6 7 8 12 13 14 16 17 18 20 22
					 * 查找：根据商品名称关键字、分类、仓库、促销状态组合查找
					 */
				}
				rows.add(temp);
			}
		}
		return rows;
	}

	@Override
	public Product getProduct(String id) throws SQLException {
		return dao.getProduct(id);
	}

	@Override
	public List<Product> getProductByVendorId(String vendorId)
			throws SQLException {
		return dao.getProductByVendorId(vendorId);
	}
	
	public String getPromotionState(int PromotionFlag){
		String state = "";
		if(PromotionFlag==0){
			state = "标价";
		}else if(PromotionFlag==1){
			state = "促销";
		}
		return state;
	}

	@Override
	public Vector<Vector> findAllProductByNameKeyword(String keyword)
			throws SQLException {
		return this.pack(dao.findAllProductByNameKeyword(keyword));
	}

	@Override
	public Vector<Vector> findAllProductionUnionKP(String keyword, int promotion)
			throws SQLException {
		return this.pack(dao.findAllProductionUnionKP(keyword, promotion));
	}

	@Override
	public Vector<Vector> findAllProductByPromotionState(int promotion)
			throws SQLException {
		return this.pack(dao.findAllProductByPromotionState(promotion));
	}
	
	/**
	 * 自定义导出数据
	 */
	public int exportData(String[] prodIds){
		try {
			// 创建工作目录
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("data");
			// 创建数据行
			XSSFRow row = sheet.createRow((short) 0);
			XSSFCell cell = null;
			// 创建第一行表头数据
			cell = row.createCell((short) 0);
			cell.setCellValue("商品名称");
			cell = row.createCell((short) 1);
			cell.setCellValue("商品成本");
			cell = row.createCell((short) 2);
			cell.setCellValue("商品售价");
			cell = row.createCell((short) 3);
			cell.setCellValue("已上架库存");
			cell = row.createCell((short) 4);
			cell.setCellValue("当前仓库库存");
			cell = row.createCell((short) 5);
			cell.setCellValue("商品产地");
			cell = row.createCell((short) 6);
			cell.setCellValue("生产日期");
			cell = row.createCell((short) 7);
			cell.setCellValue("促销状态");
			cell = row.createCell((short) 8);
			cell.setCellValue("促销价格");
			cell = row.createCell((short) 9);
			cell.setCellValue("单位");
			cell = row.createCell((short) 10);
			cell.setCellValue("所属分类");
			cell = row.createCell((short) 11);
			cell.setCellValue("所属仓库");
			cell = row.createCell((short) 12);
			cell.setCellValue("供应商");
			/**
			 * 默认是将全部数据导出
			 * 可以通过用户选择相应的数据进行导出
			 */
			List<Product> list = null;
			if(prodIds!=null){
				list = this.findAllProductByIds(prodIds);
			}else{
				list = dao.findAllProduct();
			}
			for (int i = 0; i < list.size(); i++) {
				Product prod = list.get(i);
				row = sheet.createRow(i + 1);
				cell = row.createCell(0);
				cell.setCellValue(prod.getProd_name());
				cell = row.createCell(1);
				cell.setCellValue(prod.getProd_cost());
				cell = row.createCell(2);
				cell.setCellValue(prod.getProd_price());
				cell = row.createCell(3);
				cell.setCellValue(prod.getPutaway_stock());
				cell = row.createCell(4);
				cell.setCellValue(prod.getCurrent_stock());
				cell = row.createCell(5);
				cell.setCellValue(prod.getProd_origin());
				cell = row.createCell(6);
				cell.setCellValue(prod.getProd_date());
				cell = row.createCell(7);
				cell.setCellValue(this.getPromotionState(prod.getPromotion_flag()));
				cell = row.createCell(8);
				cell.setCellValue(prod.getPromotion_price());
				cell = row.createCell(9);
				cell.setCellValue(String.valueOf(unitsDAO.getUnitsName(prod.getProd_unit())));
				cell = row.createCell(10);
				cell.setCellValue(String.valueOf(categoryDAO.getCategoryName(prod.getCategory_id())));
				cell = row.createCell(11);
				cell.setCellValue(String.valueOf(warehouseDAO.getWarehouseName(prod.getWarehouse_id())));
				cell = row.createCell(12);
				cell.setCellValue(String.valueOf(vendorDAO.getVendorName(prod.getVendor_id())));
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
	public List<Product> findAllProductByIds(String[] ids) throws SQLException {
		// 循环遍历id信息
		List<Product> list = new ArrayList<Product>();
		for(String id : ids){
			Product prod = dao.getProduct(id);
			list.add(prod);
		}
		return list;
	}

	@Override
	public Vector<Vector> getAllProductByPage(int page) throws SQLException {
		return this.pack(dao.getAllProductByPage(page));
	}

	@Override
	public int getProductMaxPage() throws SQLException {
		// 作分页处理
		BigDecimal i = BigDecimal.valueOf(Integer.valueOf(dao.getProductCount().toString()));
		BigDecimal i2 = BigDecimal.valueOf(10);
		return i.divide(i2).intValue() + 1;
	}

	@Override
	public List<Product> findAllProductListByCategoryId(String cid)
			throws SQLException {
		return dao.findAllProductByCategoryId(cid);
	}

	@Override
	public List<Product> findAllProductListByWarehouseId(String wid)
			throws SQLException {
		return dao.findAllProductByWarehouseId(wid);
	}

	@Override
	public List<Product> findAllProductListUnion(String cid, String wid)
			throws SQLException {
		return dao.findAllProductUnion(cid, wid);
	}

	@Override
	public List<Product> findAllProductListByNameKeyword(String keyword)
			throws SQLException {
		return dao.findAllProductByNameKeyword(keyword);
	}

	@Override
	public List<Product> findAllProductListByPromotionState(int promotion)
			throws SQLException {
		return dao.findAllProductByPromotionState(promotion);
	}

	@Override
	public List<Product> findAllProductionListUnionKP(String keyword,
			int promotion) throws SQLException {
		return dao.findAllProductionUnionKP(keyword, promotion);
	}

	@Override
	public List<Product> getProductByFlowIdUnionName(String keyword)
			throws SQLException {
		return dao.getProductByFlowIdUnionName(keyword);
	}
}
