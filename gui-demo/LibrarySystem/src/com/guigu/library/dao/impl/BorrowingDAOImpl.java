package com.guigu.library.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.dao.BorrowingDAO;
import com.guigu.library.model.Borrowing;

public class BorrowingDAOImpl extends BaseDAOImpl<Borrowing> implements
		BorrowingDAO {

	@Override
	public void addBorrowing(Borrowing b) throws SQLException {
		String sql = "insert into borrowing values(?,?,?,?,to_date(substr(?,1,10),'yyyy-mm-dd'),to_date(substr(?,1,10),'yyyy-mm-dd'),?,?)";
		Object[] args = { b.getBorrowing_id(), b.getBorrowing_num(),
				b.getBook_id(), b.getReader_id(), b.getBorrowing_date(),
				b.getSuggest_return_date(), b.getBorrow_state(),
				b.getViolation_state() };
		this.update(conn, sql, args);
	}

	@Override
	public void updateBorrowing(Borrowing b) throws SQLException {
		// 不考虑其他基本信息的修改，只考虑书籍的应还日期、借阅状态、违章状态的修改
		String sql = "update borrowing set suggest_return_date = to_date(substr(?,1,10),'yyyy-mm-dd'),borrow_state=?,violation_state=? where borrowing_id=?";
		Object[] args = { b.getSuggest_return_date(), b.getBorrow_state(),
				b.getViolation_state(), b.getBorrowing_id() };
		this.update(conn, sql, args);
	}

	@Override
	public List<Borrowing> findAllBorrowing() throws SQLException {
		String sql = "select * from borrowing";
		return this.getForList(conn, sql);
	}

	@Override
	public List<Borrowing> findBorrowingByReaderId(String reader_id)
			throws SQLException {
		String sql = "select * from borrowing where reader_id=?";
		return this.getForList(conn, sql, reader_id);
	}

	@Override
	public Object getBorrowingCountByState() throws SQLException {
		String sql = "select count(*) from borrowing where borrow_state = 1";
		return this.getForValue(conn, sql);
	}

	@Override
	public Object getBorrowingSeq() throws SQLException {
		String sql = "select lpad(borrowingseq.nextval,10,'0') from dual";
		return this.getForValue(conn, sql);
	}

	@Override
	public Borrowing getBorrowingByBookId(String book_id) throws SQLException {
		String sql = "select * from borrowing where borrowing_date = (select max(borrowing_date) from borrowing where book_id = ?)";
		return this.get(conn, sql, book_id);
	}

	@Override
	public List<Borrowing> findBorrowingByTime(String start_time,
			String end_time) throws SQLException {
		String sql = "select * from borrowing where to_char(borrowing_date,'yyyy-mm-dd')>=? and to_char(borrowing_date,'yyyy-mm-dd')<=?";
		Object[] args = { start_time, end_time };
		return this.getForList(conn, sql, args);
	}

	@Override
	public List<Borrowing> findViolationBorrowingByTime(String start_time,
			String end_time) throws SQLException {
		String sql = "select * from borrowing where to_char(borrowing_date,'yyyy-mm-dd')>=? and to_char(borrowing_date,'yyyy-mm-dd')<=? and violation_state=1";
		Object[] args = { start_time, end_time };
		return this.getForList(conn, sql, args);
	}
}
