package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.Warehouse;

public interface WarehouseService {

	// 通过仓库id获取仓库名称
	public Object getWarehouseName(String id) throws SQLException;

	// 获取当前数据库所有仓库信息列表
	public List<Warehouse> findAllWarehouseList() throws SQLException;

	// 获取当前数据库所有仓库信息记录
	public Vector<Vector> findAllWarehouseVector() throws SQLException;

	// 新增仓库信息
	public void addWarehouse(Warehouse w) throws SQLException;

	// 修改仓库信息
	public void updateWarehouse(Warehouse w) throws SQLException;

	// 根据删除仓库信息
	public void deleteWarehouse(String id) throws SQLException;
	
	//根据所属仓库名称关键字获取仓库信息
	public Vector<Vector> getWarehouseByNameKeyword(String keyword) throws SQLException;

}
