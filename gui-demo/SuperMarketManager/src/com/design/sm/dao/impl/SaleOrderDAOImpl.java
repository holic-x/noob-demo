package com.design.sm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.dao.SaleOrderDAO;
import com.design.sm.model.SaleOrder;

public class SaleOrderDAOImpl extends BaseDAOImpl<SaleOrder> implements SaleOrderDAO{

	@Override
	public void addSaleOrder(SaleOrder t) throws SQLException {
		String sql = "insert into sale_order values(?,?,?,?,?,?)";
		Object[] args = {t.getSale_master_id(),t.getProduct_id(),t.getQuantity(),t.getUnit_price(),t.getAmount(),t.getState()};
		this.update(conn, sql, args);
	}

	@Override
	public List<SaleOrder> getSaleOrderBySMId(String smId)
			throws SQLException {
		String sql = "select * from sale_order where sale_master_id = ?";
		return this.getForList(conn, sql,smId);
	}

}
