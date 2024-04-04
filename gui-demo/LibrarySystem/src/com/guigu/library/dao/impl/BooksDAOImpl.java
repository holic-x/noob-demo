package com.guigu.library.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.dao.BooksDAO;
import com.guigu.library.model.Books;

public class BooksDAOImpl extends BaseDAOImpl<Books> implements BooksDAO {

	@Override
	public void addBooks(Books book) throws SQLException {
		String sql = "insert into books values(?,?,?,?,?,?,?,?,?,to_date(substr(?,1,10),'yyyy-mm-dd'),"
				+ "?,?,?,to_date(substr(?,1,10),'yyyy-mm-dd'),to_date(substr(?,1,10),'yyyy-mm-dd'),?,?,?,?,?)";
		Object[] args = { book.getBook_id(), book.getBarcode(), book.getIsbn(),
				book.getCallno(), book.getBook_name(), book.getClassify_num(),
				book.getArea_num(), book.getAuthor(), book.getTranslator(),
				book.getPublish_date(), book.getPress(), book.getPrice(),
				book.getFormat(), book.getEntry_date(), book.getPut_on_date(),
				book.getAbstract_descr(), book.getProposal_reader(),
				book.getBorrow_flag(), book.getPut_on_flag(),
				book.getDelete_flag() };
		this.update(conn, sql, args);
	}

	@Override
	public void updateBooks(Books book) throws SQLException {
		String sql = "update books set isbn=?,book_name=?,classify_num=?,"
				+ "area_num=?,author=?,translator=?,publish_date=to_date(substr(?,1,10),'yyyy-mm-dd'),"
				+ "press=?,price=?,format=?,entry_date=to_date(substr(?,1,10),'yyyy-mm-dd'),"
				+ "put_on_date=to_date(substr(?,1,10),'yyyy-mm-dd'),abstract_descr=?,proposal_reader=?,"
				+ "borrow_flag=?,put_on_flag=?,delete_flag=? where book_id=?";
		Object[] args = { book.getIsbn(), book.getBook_name(),
				book.getClassify_num(), book.getArea_num(), book.getAuthor(),
				book.getTranslator(), book.getPublish_date(), book.getPress(),
				book.getPrice(), book.getFormat(), book.getEntry_date(),
				book.getPut_on_date(), book.getAbstract_descr(),
				book.getProposal_reader(), book.getBorrow_flag(),
				book.getPut_on_flag(), book.getDelete_flag(), book.getBook_id() };
		this.update(conn, sql, args);
	}

	@Override
	public void deleteBooks(String id) throws SQLException {
		String sql = "delete from books where book_id=?";
		this.update(conn, sql, id);
	}

	@Override
	public Books getBooksById(String id) throws SQLException {
		String sql = "select * from books where book_id=?";
		return this.get(conn, sql, id);
	}

	@Override
	public List<Books> findAllBooks() throws SQLException {
		String sql = "select * from books";
		return this.getForList(conn, sql);
	}

	@Override
	public List<Books> findBooksByName(String name_keyword) throws SQLException {
		String sql = "select * from books where book_name like ?";
		return this.getForList(conn, sql, name_keyword);
	}

	@Override
	public List<Books> findBooksByISBN(String isbn_keyword) throws SQLException {
		String sql = "select * from books where isbn like ?";
		return this.getForList(conn, sql, isbn_keyword);
	}

	@Override
	public List<Books> findBooksByCallno(String callno_keyword)
			throws SQLException {
		String sql = "select * from books where callno like ?";
		return this.getForList(conn, sql, callno_keyword);
	}

	@Override
	public List<Books> findBooksByClassify(String classify_keyword)
			throws SQLException {
		String sql = "select b.* from books b,book_classify bc "
				+ "where b.classify_num = bc.classify_num and bc.classify_name like ?";
		return this.getForList(conn, sql, classify_keyword);
	}

	@Override
	public List<Books> findBooksByAuthor(String author_keyword)
			throws SQLException {
		String sql = "select * from books where author like ?";
		return this.getForList(conn, sql, author_keyword);
	}

	@Override
	public List<Books> findBooksByTranslator(String translator_keyword)
			throws SQLException {
		String sql = "select * from books where translator like ?";
		return this.getForList(conn, sql, translator_keyword);
	}

	@Override
	public List<Books> findBooksByPress(String press_keyword)
			throws SQLException {
		String sql = "select * from books where press like ?";
		return this.getForList(conn, sql, press_keyword);
	}

	@Override
	public Object getBookSeq() throws SQLException {
		String sql = "select lpad(bookseq.nextval,3,'0') from dual";
		return this.getForValue(conn, sql);
	}

	@Override
	public Books getBookByBarcode(String barcode) throws SQLException {
		String sql = "select * from books where barcode = ?";
		return this.get(conn, sql, barcode);
	}

	@Override
	public List<Books> findTop10BooksByTime(String start, String end)
			throws SQLException {
//		String sql = ;
		Object[] args = {start,end};
//		return this.getForList(conn, sql, args);
		return null;
	}

	@Override
	public Object getCountByBookId(String book_id, String start, String end)
			throws SQLException {
		//???????s
		String sql = "select count(*) from borrowing where isbn=? and to_char(borrowing_date,'yyyy-mm-dd')<? and to_char(borrowing_date,'yyyy-mm-dd')>? ";
		Object[] args = {book_id,start,end};
		return this.getForValue(conn, sql, args);
	}

}
