package com.guigu.library.dao;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.model.Books;

public interface BooksDAO extends BaseDAO<Books> {

	// 增加图书信息
	public void addBooks(Books book) throws SQLException;

	// 修改图书信息
	public void updateBooks(Books book) throws SQLException;

	// 根据图书id删除图书信息
	public void deleteBooks(String id) throws SQLException;

	// 根据图书id获取图书信息
	public Books getBooksById(String id) throws SQLException;

	// 查找所有图书记录
	public List<Books> findAllBooks() throws SQLException;

	// 根据字段关键字检索图书记录
	// 根据图书名称检索图书记录
	public List<Books> findBooksByName(String name_keyword) throws SQLException;

	// 根据图书的isbn检索图书记录
	public List<Books> findBooksByISBN(String isbn_keyword) throws SQLException;

	// 根据图书的索书号检索图书记录
	public List<Books> findBooksByCallno(String callno_keyword)
			throws SQLException;

	// 根据图书分类检索图书记录
	public List<Books> findBooksByClassify(String classify_keyword)
			throws SQLException;

	// 根据作者检索图书记录
	public List<Books> findBooksByAuthor(String author_keyword)
			throws SQLException;

	// 根据译者检索图书记录
	public List<Books> findBooksByTranslator(String translator_keyword)
			throws SQLException;

	// 根据出版社检索图书记录
	public List<Books> findBooksByPress(String press_keyword)
			throws SQLException;

	// 获取当前图书序列
	public Object getBookSeq() throws SQLException;

	// 根据书籍的的条形码获取书籍的数据信息
	public Books getBookByBarcode(String barcode) throws SQLException;

	// 按照从高到低的顺序获取某个时间段借阅次数在前10位的图书信息
	public List<Books> findTop10BooksByTime(String start, String end)
			throws SQLException;

	// 根据相应的图书id(只要图书的isbn相同则默认是同样的书)统计当前图书在某个时间段被借阅的次数
	public Object getCountByBookId(String book_id, String start, String end)
			throws SQLException;

}
