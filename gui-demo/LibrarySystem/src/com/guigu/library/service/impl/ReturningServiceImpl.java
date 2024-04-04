package com.guigu.library.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.guigu.library.dao.BooksDAO;
import com.guigu.library.dao.ReaderDAO;
import com.guigu.library.dao.ReturningDAO;
import com.guigu.library.dao.impl.BooksDAOImpl;
import com.guigu.library.dao.impl.ReaderDAOImpl;
import com.guigu.library.dao.impl.ReturningDAOImpl;
import com.guigu.library.model.Books;
import com.guigu.library.model.Reader;
import com.guigu.library.model.Returning;
import com.guigu.library.model.Returning;
import com.guigu.library.service.ReturningService;

public class ReturningServiceImpl implements ReturningService{

	private ReturningDAO returningDAO = new ReturningDAOImpl();
	private BooksDAO booksDAO = new BooksDAOImpl();
	private ReaderDAO readerDAO = new ReaderDAOImpl();
	
	@Override
	public void addReturning(Returning returning) throws SQLException {
		returningDAO.addReturning(returning);
	}

	@Override
	public List<Returning> findAllReturning() throws SQLException {
		return returningDAO.findAllReturning();
	}

	@Override
	public List<Returning> findReturningByReaderId(String reader_id)
			throws SQLException {
		return returningDAO.findReturningByReaderId(reader_id);
	}

	@Override
	public List<Returning> findReturningByBookId(String book_id)
			throws SQLException {
		return returningDAO.findReturningByBookId(book_id);
	}

	@Override
	public String getReturningSeq() throws SQLException {
		return returningDAO.getReturningSeq()+"";
	}

	@Override
	public List<Returning> findReturningByTime(String start_time,
			String end_time) throws SQLException {
		return returningDAO.findReturningByTime(start_time, end_time);
	}

	@Override
	public Vector<Vector> pack(List<Returning> list) throws SQLException {
		// 手动封装查找到的数据信息
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (Returning obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 7; i++) {
					temp.add(obj.getReader_id());// 借阅id
					temp.add(obj.getReturning_num());// 借阅编号
					temp.add(obj.getBook_id());// 图书id
					Books book = booksDAO.getBooksById(obj.getBook_id());
					temp.add(book.getBook_name());// 图书名称
					temp.add(obj.getReader_id());// 读者id
					Reader r = readerDAO.getReaderById(obj.getReader_id());
					temp.add(r.getReader_name());// 读者姓名
					temp.add(obj.getReturning_date());// 续借日期
				}
				rows.add(temp);
			}
		}
		return rows;
	}

}
