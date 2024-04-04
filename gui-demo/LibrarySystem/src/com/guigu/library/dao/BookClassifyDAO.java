package com.guigu.library.dao;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.model.BookClassify;

public interface BookClassifyDAO extends BaseDAO<BookClassify>{
	// 增加图书分类信息
	public void addBookClassify(BookClassify bc)throws SQLException;
	
	// 修改图书分类信息
	public void updateBookClassify(BookClassify bc,String old_num)throws SQLException;
	
	// 根据类型编号删除图书分类信息
	public void deleteBookClassify(String classify_num)throws SQLException;
	
	// 根据查找所有图书分类信息
	public List<BookClassify> findAllBookClassify()throws SQLException;

	// 根据图书分类等级查找当前分类等级的所有图书分类信息
	public List<BookClassify> findBookClassifyByLevel(int level)throws SQLException;
	
	// 根据父级图书类型编号：查找下一等级的图书分类信息
	public List<BookClassify> findChildBookClassifyByParent(String parent_classify_num)throws SQLException;
	
	// 根据图书类型编号或者是图书类型名称关键字查找图书类型信息
	public List<BookClassify> findBookClassifyByKeyWord(String keyword)throws SQLException;
	
	// 根据指定的编号查找指定的图书分类信息
	public BookClassify getBookClassifyByNum(String classify_num)throws SQLException;
	
}
