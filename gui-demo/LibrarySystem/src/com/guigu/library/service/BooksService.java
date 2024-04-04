package com.guigu.library.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.guigu.library.model.Books;

public interface BooksService {

	// 添加图书信息
	public void addBooks(Books book) throws SQLException;

	// 修改图书信息
	public void updateBooks(Books book) throws SQLException;

	// 根据图书id删除图书信息
	public void deleteBooks(String id) throws SQLException;

	// 根据图书id获取对应图书信息
	public Books getBooksById(String id) throws SQLException;

	// 根据组合条件检索图书信息
	public List<Books> findBooksUnion(int field, int match, Object... args)
			throws SQLException;

	// 定义pack方法将List<Books>封装为Vector<Vector>记录
	public Vector<Vector> pack(List<Books> list) throws SQLException;

	// 获取当前图书序列
	public String getBookSeq() throws SQLException;
	
	// 根据书籍的的条形码获取书籍的数据信息
	public Books getBookByBarcode(String barcode)throws SQLException;
}
