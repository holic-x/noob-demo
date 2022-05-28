package com.guigu.library.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.dao.BookClassifyDAO;
import com.guigu.library.model.BookClassify;

public class BookClassifyDAOImpl extends BaseDAOImpl<BookClassify> implements BookClassifyDAO{

	@Override
	public void addBookClassify(BookClassify bc) throws SQLException {
		String sql = "insert into book_classify values(?,?,?,?)";
		Object[] args = {bc.getClassify_num(),bc.getClassify_name(),bc.getCurrent_level(),bc.getParent_num()};
		this.update(conn, sql, args);
	}

	@Override
	public void updateBookClassify(BookClassify bc,String old_num) throws SQLException {
		String sql = "update book_classify set classify_num=?,classify_name=?,current_level=?,parent_num=? where classify_num=?";
		Object[] args = {bc.getClassify_num(),bc.getClassify_name(),bc.getCurrent_level(),bc.getParent_num(),old_num};
		this.update(conn, sql, args);
	}

	@Override
	public void deleteBookClassify(String classify_num) throws SQLException {
		String sql = "delete from book_classify where classify_num=?";
		this.update(conn, sql, classify_num);
	}

	@Override
	public List<BookClassify> findAllBookClassify() throws SQLException {
		String sql = "select * from book_classify";
		return this.getForList(conn, sql);
	}

	@Override
	public List<BookClassify> findBookClassifyByLevel(int level)
			throws SQLException {
		String sql = "select * from book_classify where current_level=?";
		return this.getForList(conn, sql, level);
	}

	@Override
	public List<BookClassify> findChildBookClassifyByParent(
			String parent_classify_num) throws SQLException {
		String sql = "select * from book_classify where parent_num=?";
		return this.getForList(conn, sql, parent_classify_num);
	}

	@Override
	public List<BookClassify> findBookClassifyByKeyWord(String keyword)
			throws SQLException {
		String sql = "select * from book_classify where classify_num like ? or classify_name like ?";
		Object[] args = {keyword,keyword};
		return this.getForList(conn, sql, args);
	}

	@Override
	public BookClassify getBookClassifyByNum(String classify_num)
			throws SQLException {
		String sql = "select * from book_classify where classify_num=?";
		return this.get(conn, sql, classify_num);
	}
}
