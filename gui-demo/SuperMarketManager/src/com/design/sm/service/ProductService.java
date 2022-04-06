package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.Product;

public interface ProductService {

	// 查找所有的商品信息记录
	public Vector<Vector> findAllProductVector() throws SQLException;

	// 查找所有的商品信息列表
	public List<Product> findAllProductList() throws SQLException;

	// 根据商品所属分类id查找商品信息
	public Vector<Vector> findAllProductByCategoryId(String cid)
			throws SQLException;

	// 根据商品所属仓库id查找商品信息
	public Vector<Vector> findAllProductByWarehouseId(String wid)
			throws SQLException;

	// 根据商品所属分类id和商品所属仓库id联合查找商品信息
	public Vector<Vector> findAllProductUnion(String cid, String wid)
			throws SQLException;

	// 根据商品所属分类id查找商品信息列表
	public List<Product> findAllProductListByCategoryId(String cid)
			throws SQLException;

	// 根据商品所属仓库id查找商品信息列表
	public List<Product> findAllProductListByWarehouseId(String wid)
			throws SQLException;

	// 根据商品所属分类id和商品所属仓库id联合查找商品信息列表
	public List<Product> findAllProductListUnion(String cid, String wid)
			throws SQLException;

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

	// 根据商品id获取商品信息
	public Product getProduct(String id) throws SQLException;

	// 根据供应商id获取相应的供应商信息
	public List<Product> getProductByVendorId(String vendorId)
			throws SQLException;

	// 根据商品名称关键字查找商品信息
	public Vector<Vector> findAllProductByNameKeyword(String keyword)
			throws SQLException;

	// 根据商品促销状态查找商品信息
	public Vector<Vector> findAllProductByPromotionState(int promotion)
			throws SQLException;

	// 根据商品关键字和促销状态联合查找商品信息
	public Vector<Vector> findAllProductionUnionKP(String keyword, int promotion)
			throws SQLException;

	// 根据商品名称关键字查找商品信息
	public List<Product> findAllProductListByNameKeyword(String keyword)
			throws SQLException;

	// 根据商品促销状态查找商品信息
	public List<Product> findAllProductListByPromotionState(int promotion)
			throws SQLException;

	// 根据商品关键字和促销状态联合查找商品信息
	public List<Product> findAllProductionListUnionKP(String keyword,
			int promotion) throws SQLException;

	// 根据给定的商品id列表获取商品信息
	public List<Product> findAllProductByIds(String[] ids) throws SQLException;

	// 批量导出商品数据
	public int exportData(String[] ids);

	// 分页查找数据
	public Vector<Vector> getAllProductByPage(int page) throws SQLException;

	// 根据商品总数获取相应的最大分页数
	public int getProductMaxPage() throws SQLException;

	// 定义一个公有方法将List<Product> 类型封装为 Vendor<Vendor>类型
	public Vector<Vector> pack(List<Product> list) throws SQLException;

	// 根据商品的条形码和商品关键字共同查找商品信息
	public List<Product> getProductByFlowIdUnionName(String keyword)
			throws SQLException;
}
