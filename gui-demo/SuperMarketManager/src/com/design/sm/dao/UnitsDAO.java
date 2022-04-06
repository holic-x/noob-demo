package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;
import com.design.sm.model.Units;

public interface UnitsDAO extends BaseDAO<Units> {
	// 通过所属单位id获取单位名称
	public Object getUnitsName(String id) throws SQLException;

	// 根据所属单位名称关键字获取单位信息
	public List<Units> getUnitsByNameKeyword(String keyword)
			throws SQLException;

	// 查找所有商品单位信息
	public List<Units> findAllUnits() throws SQLException;

	// 新增单位信息
	public void addUnits(Units u) throws SQLException;

	// 修改单位信息
	public void updateUnits(Units u) throws SQLException;

	// 根据删除单位信息
	public void deleteUnits(String id) throws SQLException;

}
