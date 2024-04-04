package com.guigu.library.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.guigu.library.dao.BooksDAO;
import com.guigu.library.dao.ReaderDAO;
import com.guigu.library.dao.RenewDAO;
import com.guigu.library.dao.impl.BooksDAOImpl;
import com.guigu.library.dao.impl.ReaderDAOImpl;
import com.guigu.library.dao.impl.RenewDAOImpl;
import com.guigu.library.model.Books;
import com.guigu.library.model.Reader;
import com.guigu.library.model.Renew;
import com.guigu.library.service.RenewService;

public class RenewServiceImpl implements RenewService{

	private RenewDAO renewDAO = new RenewDAOImpl();
	private BooksDAO booksDAO = new BooksDAOImpl();
	private ReaderDAO readerDAO = new ReaderDAOImpl();
	
	@Override
	public void addRenew(Renew renew) throws SQLException {
		renewDAO.addRenew(renew);
	}

	@Override
	public List<Renew> findAllRenew() throws SQLException {
		return renewDAO.findAllRenew();
	}

	@Override
	public List<Renew> findRenewByReaderId(String reader_id) throws SQLException {
		return renewDAO.findRenewByReaderId(reader_id);
	}

	@Override
	public List<Renew> findRenewByBookId(String book_id) throws SQLException {
		return renewDAO.findRenewByBookId(book_id);
	}

	@Override
	public String getRenewSeq() throws SQLException {
		return renewDAO.getRenewSeq() + "";
	}

	@Override
	public List<Renew> findRenewByTime(String start_time, String end_time)
			throws SQLException {
		return renewDAO.findRenewByTime(start_time, end_time);
	}

	@Override
	public Vector<Vector> pack(List<Renew> list) throws SQLException {
		// 手动封装查找到的数据信息
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (Renew obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 7; i++) {
					temp.add(obj.getReader_id());// 借阅id
					temp.add(obj.getRenew_num());// 借阅编号
					temp.add(obj.getBook_id());// 图书id
					Books book = booksDAO.getBooksById(obj.getBook_id());
					temp.add(book.getBook_name());// 图书名称
					temp.add(obj.getReader_id());// 读者id
					Reader r = readerDAO.getReaderById(obj.getReader_id());
					temp.add(r.getReader_name());// 读者姓名
					temp.add(obj.getRenew_date());// 续借日期
				}
				rows.add(temp);
			}
		}
		return rows;
	}
}
