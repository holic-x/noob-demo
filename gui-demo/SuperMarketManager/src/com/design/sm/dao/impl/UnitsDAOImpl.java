package com.design.sm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.dao.UnitsDAO;
import com.design.sm.model.Units;

public class UnitsDAOImpl extends BaseDAOImpl<Units> implements UnitsDAO{

	//通过单位id获取单位名称
	@Override
	public Object getUnitsName(String id) throws SQLException {
		String sql = "select units_name from units where units_id =?";
		return this.getForValue(conn, sql, id);
	}

	//通过单位名称关键字搜索单位信息
	@Override
	public List<Units> getUnitsByNameKeyword(String keyword)
			throws SQLException {
		String sql = "select * from units where units_name like ?";
		return this.getForList(conn, sql,keyword);
	}

	//查找所有的单位信息
	@Override
	public List<Units> findAllUnits() throws SQLException {
		String sql = "select * from units";
		return this.getForList(conn, sql);
	}

	//添加单位信息
	@Override
	public void addUnits(Units u) throws SQLException {
		String sql = "insert into units values(?,?,?)";
		Object[] args = {u.getUnits_id(),u.getUnits_name(),u.getDelete_flag()};
		this.update(conn, sql, args);
	}

	//修改单位信息
	@Override
	public void updateUnits(Units u) throws SQLException {
		String sql = "update units set units_name=?,delete_flag=? where units_id=?";
		Object[] args = {u.getUnits_name(),u.getDelete_flag(),u.getUnits_id()};
		this.update(conn, sql, args);
	}

	//删除单位信息
	@Override
	public void deleteUnits(String id) throws SQLException {
		String sql = "delete from units where units_id=?";
		this.update(conn, sql, id);
	}

}
