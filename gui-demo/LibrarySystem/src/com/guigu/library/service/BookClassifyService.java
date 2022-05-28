package com.guigu.library.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.guigu.library.model.BookClassify;

public interface BookClassifyService {

	// 增加图书分类信息
	public void addBookClassify(BookClassify bc) throws SQLException;

	// 修改图书分类信息
	public void updateBookClassify(BookClassify bc,String old_num) throws SQLException;

	// 根据类型编号删除图书分类信息
	public void deleteBookClassify(String classify_num) throws SQLException;

	// 定义综合方法：用以查找批量的数据
	public List<BookClassify> findBookClassifyUnion(String type,Object... args)throws SQLException;
	
	// 定义方法pack用以将List<BookClassify>类型封装为Vector<Vector>
	public Vector<Vector> pack(List<BookClassify> list)throws SQLException;

	// 根据指定的编号查找指定的图书分类信息
	public BookClassify getBookClassifyByNum(String classify_num)
			throws SQLException;
}
