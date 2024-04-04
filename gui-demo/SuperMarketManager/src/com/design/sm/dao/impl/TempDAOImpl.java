package com.design.sm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.dao.TempDAO;
import com.design.sm.model.Temp;

public class TempDAOImpl extends BaseDAOImpl<Temp> implements TempDAO{

	@Override
	public void addTemp(Temp t) throws SQLException {
		String sql = "insert into temp values(?,?,?,?)";
		Object[] args = {t.getProduct_id(),t.getQuantity(),t.getUnit_price(),t.getAmount()};
		this.update(conn, sql, args);
	}

	@Override
	public void update(Temp t) throws SQLException {
		String sql = "update temp set quantity=?,unit_price=?,amount=? where product_id=?";
		Object[] args = {t.getQuantity(),t.getUnit_price(),t.getAmount(),t.getProduct_id()};
		this.update(conn, sql, args);
	}

	@Override
	public void deleteTemp(String id) throws SQLException {
		String sql = "delete from temp where product_id=?";
		this.update(conn, sql, id);
	}

	@Override
	public List<Temp> findAllTempList() throws SQLException {
		String sql = "select * from temp";
		return this.getForList(conn, sql);
	}

	@Override
	public Temp getTempByProductId(String id) throws SQLException {
		String sql = "select * from temp where product_id=?";
		return this.get(conn, sql, id);
	}

}
