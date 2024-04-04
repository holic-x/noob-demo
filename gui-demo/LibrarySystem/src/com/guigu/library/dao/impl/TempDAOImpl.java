package com.guigu.library.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.dao.TempDAO;
import com.guigu.library.model.Temp;

public class TempDAOImpl extends BaseDAOImpl<Temp> implements TempDAO {

	@Override
	public void addTemp(Temp t) throws SQLException {
		String sql = "insert into temp values(?,?,?)";
		Object[] args = { t.getTemp_id(), t.getBook_id(), t.getReader_id() };
		this.update(conn, sql, args);
	}

	@Override
	public void deleteTemp(String id) throws SQLException {
		String sql = "delete from temp where temp_id = ?";
		this.update(conn, sql, id);
	}

	@Override
	public List<Temp> findTempByReaderId(String reader_id) throws SQLException {
		String sql = "select * from temp where reader_id=?";
		return this.getForList(conn, sql, reader_id);
	}

	@Override
	public Temp getTempByISBN(String isbn, String reader_id)
			throws SQLException {
		String sql = "select t.* from temp t where t.book_id in (select b.book_id from books b where b.isbn=?) and t.reader_id=?";
		Object[] args = { isbn, reader_id };
		return this.get(conn, sql, args);
	}

}
