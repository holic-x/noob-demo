package com.guigu.library.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.dao.ReaderClassifyDAO;
import com.guigu.library.model.ReaderClassify;

public class ReaderClassifyDAOImpl extends BaseDAOImpl<ReaderClassify> implements ReaderClassifyDAO{

	@Override
	public void updateReaderClassify(ReaderClassify rc) throws SQLException {
		String sql = "update reader_classify set classify_name=?,maximum=?,time_limit=? where classify_num=?";
		Object[] args = {rc.getClassify_name(),rc.getMaximum(),rc.getTime_limit(),rc.getClassify_num()};
		this.update(conn, sql, args);
	}

	@Override
	public List<ReaderClassify> findAllReaderClassify() throws SQLException {
		String sql = "select * from reader_classify";
		return this.getForList(conn, sql);
	}

	@Override
	public Object getClassifyNameByum(int num) throws SQLException {
		String sql = "select classify_name from reader_classify where classify_num = ?";
		return this.getForValue(conn, sql, num);
	}

	@Override
	public ReaderClassify getReaderClassifyBynum(int num) throws SQLException {
		String sql = "select * from reader_classify where classify_num = ?";
		return this.get(conn, sql, num);
	}

}
