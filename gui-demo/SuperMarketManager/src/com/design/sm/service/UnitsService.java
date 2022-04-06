package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.Units;

public interface UnitsService {

	// 通过单位id获取单位名称
	public Object getUnitsName(String id) throws SQLException;

	// 获取所有商品单位信息列表
	public List<Units> findAllUnitsList() throws SQLException;

	// 获取所有商品单位信息列表
	public Vector<Vector> findAllUnitsVector() throws SQLException;

	// 新增单位信息
	public void addUnits(Units u) throws SQLException;

	// 修改单位信息
	public void updateUnits(Units u) throws SQLException;

	// 根据删除单位信息
	public void deleteUnits(String id) throws SQLException;

	// 根据所属单位名称关键字获取单位信息
	public Vector<Vector> getUnitsByNameKeyword(String keyword)
			throws SQLException;

}
