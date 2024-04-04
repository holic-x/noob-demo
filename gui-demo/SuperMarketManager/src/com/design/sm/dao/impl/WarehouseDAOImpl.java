package com.design.sm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.dao.WarehouseDAO;
import com.design.sm.model.Warehouse;

public class WarehouseDAOImpl extends BaseDAOImpl<Warehouse> implements WarehouseDAO {

	@Override
	public Object getWarehouseName(String id) throws SQLException {
		String sql = "select warehouse_name from warehouse where warehouse_id=?";
		return this.getForValue(conn, sql, id);
	}

	@Override
	public List<Warehouse> findAllWarehouse() throws SQLException {
		String sql = "select * from warehouse";
		return this.getForList(conn, sql);
	}

	@Override
	public void addWarehouse(Warehouse w) throws SQLException {
		String sql = "insert into warehouse values(?,?,?)";
		Object[] args = {w.getWarehouse_id(),w.getWarehouse_name(),w.getDelete_flag()};
		this.update(conn, sql, args);
	}

	@Override
	public void updateWarehouse(Warehouse w) throws SQLException {
		String sql = "update warehouse set warehouse_name=? where warehouse_id=?";
		Object[] args = {w.getWarehouse_name(),w.getWarehouse_id()};
		this.update(conn, sql, args);
	}

	@Override
	public void deleteWarehouse(String id) throws SQLException {
		String sql = "delete from warehouse where warehouse_id=?";
		this.update(conn, sql, id);
	}

	@Override
	public List<Warehouse> getWarehouseByNameKeyword(String keyword)
			throws SQLException {
		String sql = "select * from warehouse where warehouse_name like ?";
		return this.getForList(conn, sql, keyword);
	}

}
