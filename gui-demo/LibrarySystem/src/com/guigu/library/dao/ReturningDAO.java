package com.guigu.library.dao;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.model.Returning;

public interface ReturningDAO extends BaseDAO<Returning> {

	// 添加归还记录
	public void addReturning(Returning returning) throws SQLException;

	// 获取所有的归还记录
	public List<Returning> findAllReturning() throws SQLException;

	// 根据读者id获取所有的归还记录
	public List<Returning> findReturningByReaderId(String reader_id)
			throws SQLException;

	// 根据图书id获取所有的归还记录
	public List<Returning> findReturningByBookId(String book_id)
			throws SQLException;

	// 获取归还序号
	public Object getReturningSeq() throws SQLException;

	// 获取指定时间段的归还记录
	public List<Returning> findReturningByTime(String start_time,
			String end_time) throws SQLException;
}
