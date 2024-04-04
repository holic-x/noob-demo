package com.guigu.library.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.guigu.library.model.Renew;
import com.guigu.library.model.Returning;

public interface RenewService {

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
	public String getRenewSeq() throws SQLException;

	// 获取指定时间段的归还记录
	public List<Renew> findRenewByTime(String start_time, String end_time)
			throws SQLException;

	// 将List<Renew>转化为相应的Vector<Vector>
	public Vector<Vector> pack(List<Renew> list) throws SQLException;
	
}
