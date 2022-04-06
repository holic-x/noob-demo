package com.design.sm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.dao.ConsumeClassDAO;
import com.design.sm.model.ConsumeClass;

public class ConsumeClassDAOImpl extends BaseDAOImpl<ConsumeClass> implements ConsumeClassDAO{

	@Override
	public List<ConsumeClass> findAllConsumeClass() throws SQLException {
		String sql = "select * from consume_class";
		return this.getForList(conn, sql);
	}

	@Override
	public void deleteConsumeClass(String id) throws SQLException {
		String sql = "delete from consume_class where class_id=?";
		this.update(conn, sql, id);
	}

	@Override
	public void updateConsumeClass(ConsumeClass cc) throws SQLException {
		String sql = "update consume_class set class_name=?,class_off=?,class_discount=? where class_id=?";
		Object[] args = {cc.getClass_name(),cc.getClass_off(),cc.getClass_discount(),cc.getClass_id()};
		this.update(conn, sql, args);
	}

	@Override
	public Object getConsumeClassNameById(int id) throws SQLException {
		String sql = "select class_name from consume_class where class_id=?";
		return this.getForValue(conn, sql, id);
	}

	@Override
	public ConsumeClass getConsumeClassById(int id) throws SQLException {
		String sql = "select * from consume_class where class_id=?";
		return this.get(conn, sql, id);
	}
}
