package com.design.sm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.dao.SaleTempDAO;
import com.design.sm.model.SaleTemp;

public class SaleTempDAOImpl extends BaseDAOImpl<SaleTemp> implements SaleTempDAO{

	@Override
	public void addSaleTemp(SaleTemp t) throws SQLException {
		String sql = "insert into sale_temp values(?,?,?,?)";
		Object[] args = {t.getProduct_id(),t.getQuantity(),t.getUnit_price(),t.getAmount()};
		this.update(conn, sql, args);
	}

	@Override
	public void update(SaleTemp t) throws SQLException {
		String sql = "update sale_temp set quantity=?,unit_price=?,amount=? where product_id=?";
		Object[] args = {t.getQuantity(),t.getUnit_price(),t.getAmount(),t.getProduct_id()};
		this.update(conn, sql, args);
	}

	@Override
	public void deleteSaleTemp(String id) throws SQLException {
		String sql = "delete from sale_temp where product_id=?";
		this.update(conn, sql, id);
	}

	@Override
	public List<SaleTemp> findAllSaleTempList() throws SQLException {
		String sql = "select * from sale_temp";
		return this.getForList(conn, sql);
	}

	@Override
	public SaleTemp getSaleTempByProductId(String id) throws SQLException {
		String sql = "select * from sale_temp where product_id=?";
		return this.get(conn, sql, id);
	}

	@Override
	public Object getAllAmount() throws SQLException {
		String sql = "select sum(amount) from sale_temp";
		return this.getForValue(conn, sql);
	}

	@Override
	public void truncateAllSaleTemp() throws SQLException {
		String sql = "delete from sale_temp";
		this.update(conn, sql);
	}

}
