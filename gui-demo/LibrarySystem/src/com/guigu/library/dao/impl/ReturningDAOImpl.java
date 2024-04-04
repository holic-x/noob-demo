package com.guigu.library.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.dao.ReturningDAO;
import com.guigu.library.model.Returning;

public class ReturningDAOImpl extends BaseDAOImpl<Returning> implements
		ReturningDAO {

	@Override
	public void addReturning(Returning returning) throws SQLException {
		String sql = "insert into returning values(?,?,?,?,to_date(substr(?,1,10),'yyyy-mm-dd'))";
		Object[] args = { returning.getReturning_id(),
				returning.getReturning_num(), returning.getBook_id(),
				returning.getReader_id(), returning.getReturning_date() };
		this.update(conn, sql, args);
	}

	@Override
	public List<Returning> findAllReturning() throws SQLException {
		String sql = "select * from returning";
		return this.getForList(conn, sql);
	}

	@Override
	public List<Returning> findReturningByReaderId(String reader_id)
			throws SQLException {
		String sql = "select * from returning where reader_id = ?";
		return this.getForList(conn, sql, reader_id);
	}

	@Override
	public List<Returning> findReturningByBookId(String book_id)
			throws SQLException {
		String sql = "select * from returning where book_id = ?";
		return this.getForList(conn, sql, book_id);
	}

	@Override
	public Object getReturningSeq() throws SQLException {
		String sql = "select lpad(returningseq.nextval,10,'0') from dual";
		return this.getForValue(conn, sql);
	}

	@Override
	public List<Returning> findReturningByTime(String start_time,
			String end_time) throws SQLException {
		String sql = "select * from returning where to_char(returning_date,'yyyy-mm-dd')>=? and to_char(returning_date,'yyyy-mm-dd')<=?";
		Object[] args = { start_time, end_time };
		return this.getForList(conn, sql, args);
	}

}
