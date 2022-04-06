package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.Product;

public interface ProductDAO extends BaseDAO<Product> {

	// 查找所有的商品信息
	public List<Product> findAllProduct() throws SQLException;

	// 根据商品所属分类id查找商品信息
	public List<Product> findAllProductByCategoryId(String cid)
			throws SQLException;

	// 根据商品所属仓库id查找商品信息
	public List<Product> findAllProductByWarehouseId(String wid)
			throws SQLException;

	// 根据商品所属分类id和商品所属仓库id联合查找商品信息
	public List<Product> findAllProductUnion(String cid, String wid)
			throws SQLException;
	
	// 根据商品名称关键字查找商品信息
	public List<Product> findAllProductByNameKeyword(String keyword)throws SQLException;
	
	// 根据商品促销状态查找商品信息
	public List<Product> findAllProductByPromotionState(int promotion)throws SQLException;
	
	// 根据商品关键字和促销状态联合查找商品信息
	public List<Product> findAllProductionUnionKP(String keyword,int promotion)throws SQLException;

	// 保存商品数据详细内容（由理货员进行维护）
	public void saveProductDetail(Product prod) throws SQLException;

	// 保存商品数据简略信息（由仓库管理员进行维护）
	public void saveProductSimple(Product prod) throws SQLException;

	// 根据商品id删除商品数据
	public void deleteProduct(String id) throws SQLException;

	// 更新商品数据
	public void updateProduct(Product prod) throws SQLException;

	// 根据商品id获取商品名称
	public Object getProductName(String id) throws SQLException;

	// 根据商品id获取当前商品对象
	public Product getProduct(String id) throws SQLException;

	// 根据供应商id获取相应的商品信息
	public List<Product> getProductByVendorId(String vendorId)
			throws SQLException;
	
	// 分页查找数据
	public List<Product> getAllProductByPage(int page)throws SQLException;
	
	// 获取商品总数从而得出相应的分页数
	public Object getProductCount()throws SQLException;
	
	// 根据商品的条形码和商品关键字共同查找商品信息
	public List<Product> getProductByFlowIdUnionName(String keyword)throws SQLException;
	
}
