package com.guigu.library.dao;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.model.Borrowing;

public interface BorrowingDAO extends BaseDAO<Borrowing> {

	// 增加借阅信息
	public void addBorrowing(Borrowing b) throws SQLException;

	// 修改借阅信息
	public void updateBorrowing(Borrowing b) throws SQLException;

	// 查找所有的图书借阅信息
	public List<Borrowing> findAllBorrowing() throws SQLException;

	// 根据读者id查找对应读者的图书借阅信息
	public List<Borrowing> findBorrowingByReaderId(String reader_id)
			throws SQLException;

	// 统计当前处于借阅状态（尚未归还书籍）的记录条数
	public Object getBorrowingCountByState() throws SQLException;

	// 获取当前的借阅序号
	public Object getBorrowingSeq() throws SQLException;

	/**
	 * 由于可能存在多个用户或同一个用户多次借阅同一本图书，如果仅仅是根据图书id
	 * 获取相关的借阅信息反而不切实际，因此此处考虑用当前最近的借阅时间和图书id 进行查找，输入图书id便能够相应获得该图书最近的借阅信息
	 */
	public Borrowing getBorrowingByBookId(String book_id) throws SQLException;

	// 根据时间范围查找所有借阅信息
	public List<Borrowing> findBorrowingByTime(String start_time,
			String end_time) throws SQLException;

	// 根据时间范围查找违章的借阅记录
	public List<Borrowing> findViolationBorrowingByTime(String start_time,
			String end_time) throws SQLException;
}
