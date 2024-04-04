package com.guigu.library.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.dao.ReaderDAO;
import com.guigu.library.model.Reader;

public class ReaderDAOImpl extends BaseDAOImpl<Reader> implements ReaderDAO{

	@Override
	public void addReader(Reader r) throws SQLException {
		String sql = "insert into reader values(?,?,?,?,?,to_date(substr(?,1,10),'yyyy-mm-dd'),?,?,?,?,?,?,?)";
		Object[] args= {r.getReader_id(),r.getBarcode(),r.getAcademic_num(),r.getReader_name(),r.getReader_sex(),r.getReader_birthday(),
				r.getReader_idCard(),r.getReader_phone(),r.getReader_email(),r.getReader_descr(),r.getUser_id(),r.getClassify_num(),r.getCard_id()};
		this.update(conn, sql, args);
	}

	@Override
	public void updateReader(Reader r) throws SQLException {
		// 读者id与相应生成的条形码不变
		String sql = "update reader set academic_num=?,reader_name=?,reader_sex=?,reader_birthday=to_date(substr(?,1,10),'yyyy-mm-dd'),reader_idCard=?,"
				+ "reader_phone=?,reader_email=?,reader_descr=?,user_id=?,classify_num=?,card_id=? where reader_id=?";
		Object[] args= {r.getAcademic_num(),r.getReader_name(),r.getReader_sex(),r.getReader_birthday(),r.getReader_idCard(),
				r.getReader_phone(),r.getReader_email(),r.getReader_descr(),r.getUser_id(),r.getClassify_num(),r.getCard_id(),r.getReader_id()};
		this.update(conn, sql, args);
	}

	@Override
	public void deleteReader(String id) throws SQLException {
		String sql = "delete from reader where reader_id=?";
		this.update(conn, sql, id);
	}

	@Override
	public List<Reader> findAllReader() throws SQLException {
		String sql = "select * from reader";
		return this.getForList(conn, sql);
	}

	@Override
	public Reader getReaderById(String id) throws SQLException {
		String sql = "select * from reader where reader_id=?";
		return this.get(conn, sql, id);
	}

	@Override
	public List<Reader> findReaderByBarcode(String kw_barcode)
			throws SQLException {
		String sql = "select * from reader where barcode like ?";
		return this.getForList(conn, sql, kw_barcode);
	}

	@Override
	public List<Reader> findReaderByAcademicNum(String kw_academic_num)
			throws SQLException {
		String sql = "select * from reader where academic_num like ?";
		return this.getForList(conn, sql, kw_academic_num);
	}

	@Override
	public List<Reader> findReaderByIdCard(String kw_idCard)
			throws SQLException {
		String sql = "select * from reader where reader_idCard like ?";
		return this.getForList(conn, sql, kw_idCard);
	}

	@Override
	public List<Reader> findReaderByAccount(String kw_user_name)
			throws SQLException {
		String sql = "select * from reader r,users u where r.user_id = u.user_id and u.user_name like ?";
		return this.getForList(conn, sql, kw_user_name);
	}

	@Override
	public List<Reader> findReaderByCardId(String kw_card_num)
			throws SQLException {
		String sql = "select * from reader r,library_card lc where r.card_id = lc.card_id and lc.card_num like ?";
		return this.getForList(conn, sql, kw_card_num);
	}

	@Override
	public Reader getReaderByUserId(String user_id) throws SQLException {
		String sql = "select r.* from reader r,users u where r.user_id=u.user_id and u.user_id=?";
		return this.get(conn, sql, user_id);
	}

	@Override
	public Reader getReaderByBarcode(String barcode) throws SQLException {
		String sql = "select * from reader where barcode = ?";
		return this.get(conn, sql, barcode);
	}

}
