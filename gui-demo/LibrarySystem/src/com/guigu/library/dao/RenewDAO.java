package com.guigu.library.dao;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.model.Renew;
import com.guigu.library.model.Returning;

public interface RenewDAO extends BaseDAO<Renew> {

	// 添加续借记录
	public void addRenew(Renew renew) throws SQLException;

	// 获取所有的续借记录
	public List<Renew> findAllRenew() throws SQLException;

	// 根据读者id获取所有的续借记录
	public List<Renew> findRenewByReaderId(String reader_id)
			throws SQLException;

	// 根据图书id获取所有的续借记录
	public List<Renew> findRenewByBookId(String book_id) throws SQLException;

	// 获取续借序号
	public Object getRenewSeq() throws SQLException;

	// 获取指定时间段的归还记录
	public List<Renew> findRenewByTime(String start_time,
			String end_time) throws SQLException;

}
