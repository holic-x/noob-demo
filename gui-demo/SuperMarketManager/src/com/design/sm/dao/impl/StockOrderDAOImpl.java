package com.design.sm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;

import com.design.sm.dao.StockOrderDAO;
import com.design.sm.model.StockOrder;

public class StockOrderDAOImpl extends BaseDAOImpl<StockOrder> implements StockOrderDAO {

	@Override
	public void addStockOrder(StockOrder t) throws SQLException {
		String sql = "insert into stock_order values(?,?,?,?,?,?)";
		Object[] args = {t.getStock_master_id(),t.getProduct_id(),t.getQuantity(),t.getUnit_price(),t.getAmount(),t.getState()};
		this.update(conn, sql, args);
	}

	@Override
	public List<StockOrder> getStockOrderBySMId(String smId) throws SQLException {
		String sql = "select * from stock_order where stock_master_id = ?";
		return this.getForList(conn, sql,smId);
	}

}
