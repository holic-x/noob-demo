package com.design.sm.dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.design.sm.dao.ProductDAO;
import com.design.sm.model.Product;

public class ProductDAOImpl extends BaseDAOImpl<Product> implements ProductDAO{

	@Override
	public List<Product> findAllProduct() throws SQLException {
		String sql = "select * from product";
		return this.getForList(conn, sql);
	}

	@Override
	public List<Product> findAllProductByCategoryId(String cid)
			throws SQLException {
		String sql = "select * from product where category_id=?";
		return this.getForList(conn, sql, cid);
	}

	@Override
	public List<Product> findAllProductByWarehouseId(String wid)
			throws SQLException {
		String sql = "select * from product where warehouse_id=?";
		return this.getForList(conn, sql, wid);
	}

	@Override
	public List<Product> findAllProductUnion(String cid, String wid)
			throws SQLException {
		String sql = "select * from product where category_id=? and warehouse_id=?";
		Object[] args={cid,wid};
		return this.getForList(conn, sql, args);
	}

	@Override
	public void saveProductDetail(Product prod) throws SQLException {
		String sql = "insert into product values(?,?,?,?,?,?,?,?,?,?,to_date(substr(?,1,10),'yyyy-mm-dd'),?,?,?,?,?,?,?,?)";
		// to_date(?,'yyyy-mm-dd HH24:MI:SS') 忽略时分秒  to_date(?,'yyyy-mm-dd')
		Object[] args = {prod.getProd_id(),prod.getFlow_id(),prod.getProd_name(),prod.getProd_cost(),
				prod.getProd_price(),prod.getPutaway_stock(),prod.getCurrent_stock(),prod.getSafe_stock(),
				prod.getProd_unit(),prod.getProd_origin(),prod.getProd_date(),prod.getProd_descr(),
				prod.getProd_discount(),prod.getPromotion_flag(),prod.getPromotion_flag(),
				prod.getDelete_flag(),prod.getCategory_id(),prod.getVendor_id(),prod.getWarehouse_id()};
		this.update(conn, sql, args);
	}
	
	@Override
	public void saveProductSimple(Product prod) throws SQLException {
		String sql = "insert into product values(?,?,?,?,?,to_date(substr(?,1,10),'yyyy-mm-dd'),?,?,?)";
		Object[] args = {prod.getProd_id(),prod.getFlow_id(),prod.getProd_name(),prod.getProd_unit(),
				prod.getProd_origin(),prod.getProd_date(),prod.getCategory_id(),prod.getVendor_id(),
				prod.getWarehouse_id()};
		this.update(conn, sql, args);
	}

	@Override
	public void deleteProduct(String id) throws SQLException {
		String sql = "delete from product where prod_id=?";
		this.update(conn, sql, id);
	}

	@Override
	public void updateProduct(Product prod) throws SQLException {
		String sql = "update product set prod_name=?,prod_cost=?,prod_price=?,"
				+ "putaway_stock=?,current_stock=?,safe_stock=?,prod_unit=?,"
				+ "prod_origin=?,prod_date=to_date(substr(?,1,10),'yyyy-mm-dd'),prod_descr=?, prod_discount=?,"
				+ "promotion_flag=?,promotion_price=?,delete_flag=?,category_id=?,"
				+ "vendor_id=?,warehouse_id=? where prod_id=?";
		Object[] args = {prod.getProd_name(),prod.getProd_cost(),
				prod.getProd_price(),prod.getPutaway_stock(),prod.getCurrent_stock(),prod.getSafe_stock(),
				prod.getProd_unit(),prod.getProd_origin(),prod.getProd_date(),prod.getProd_descr(),
				prod.getProd_discount(),prod.getPromotion_flag(),prod.getPromotion_price(),
				prod.getDelete_flag(),prod.getCategory_id(),prod.getVendor_id(),prod.getWarehouse_id(),prod.getProd_id()};
		/**
		 *  日期处理：to_date(substr(？,1,10),'yyyy-mm-dd')  to_date(?,'yyyy-mm-dd HH24:MI:SS')
		 *  截取前面的年月日即可
		 */
		this.update(conn, sql, args);
	}

	@Override
	public Object getProductName(String id) throws SQLException {
		String sql = "select prod_name from product where prod_id=?";
		return this.getForValue(conn, sql, id);
	}

	@Override
	public Product getProduct(String id) throws SQLException {
		String sql = "select * from product where prod_id=?";
		return this.get(conn, sql, id);
	}

	@Override
	public List<Product> getProductByVendorId(String vendorId)
			throws SQLException {
		String sql = "select * from product where vendor_id=?";
		return this.getForList(conn, sql, vendorId);
	}

	@Override
	public List<Product> findAllProductByNameKeyword(String keyword)
			throws SQLException {
		String sql = "select * from product where prod_name like ?";
		return this.getForList(conn, sql, keyword);
	}

	@Override
	public List<Product> findAllProductionUnionKP(String keyword, int promotion)
			throws SQLException {
		String sql = "select * from product where prod_name like ? and promotion_flag=?";
		Object[] args = {keyword,promotion};
		return this.getForList(conn, sql, args);
	}

	@Override
	public List<Product> findAllProductByPromotionState(int promotion)
			throws SQLException {
		String sql = "select * from product where promotion_flag=?";
		return this.getForList(conn, sql, promotion);
	}

	@Override
	public List<Product> getAllProductByPage(int page) throws SQLException {
		String sql = "select * from(select rownum as num,prod.* from product prod )where num between ? and ?";
		Object[] args = {(Integer.valueOf(page) - 1) * 10 + 1, (Integer.valueOf(page) - 1) * 10 + 10};
		return this.getForList(conn, sql, args);
	}

	@Override
	public Object getProductCount() throws SQLException {
		String sql = "select count(*) from product";
		return this.getForValue(conn, sql);
	}

	@Override
	public List<Product> getProductByFlowIdUnionName(String keyword)
			throws SQLException {
		String sql = "select * from product where flow_id like ? or prod_name like ?";
		Object[] args = {keyword,keyword};
		return this.getForList(conn, sql, args);
	}
}
