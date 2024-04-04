package com.guigu.library.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.dao.RenewDAO;
import com.guigu.library.model.Renew;

public class RenewDAOImpl extends BaseDAOImpl<Renew> implements RenewDAO {

	@Override
	public void addRenew(Renew renew) throws SQLException {
		String sql = "insert into renew values(?,?,?,?,to_date(substr(?,1,10),'yyyy-mm-dd'))";
		Object[] args = { renew.getRenew_id(), renew.getRenew_num(),
				renew.getBook_id(), renew.getReader_id(), renew.getRenew_date() };
		this.update(conn, sql, args);
	}

	@Override
	public List<Renew> findAllRenew() throws SQLException {
		String sql = "select * from renew";
		return this.getForList(conn, sql);
	}

	@Override
	public List<Renew> findRenewByReaderId(String reader_id) throws SQLException {
		String sql = "select * from renew where reader_id = ?";
		return this.getForList(conn, sql, reader_id);
	}

	@Override
	public List<Renew> findRenewByBookId(String book_id) throws SQLException {
		String sql = "select * from renew where book_id = ?";
		return this.getForList(conn, sql, book_id);
	}

	@Override
	public Object getRenewSeq() throws SQLException {
		String sql = "select lpad(renewseq.nextval,10,'0') from dual";
		return this.getForValue(conn, sql);
	}

	@Override
	public List<Renew> findRenewByTime(String start_time, String end_time)
			throws SQLException {
		String sql = "select * from renew where to_char(renew_date,'yyyy-mm-dd')>=? and to_char(renew_date,'yyyy-mm-dd')<=?";
		Object[] args = { start_time, end_time };
		return this.getForList(conn, sql, args);
	}

}
