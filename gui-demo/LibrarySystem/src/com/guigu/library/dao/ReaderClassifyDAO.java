package com.guigu.library.dao;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.model.ReaderClassify;

public interface ReaderClassifyDAO extends BaseDAO<ReaderClassify>{
	// 修改读者分类信息
	public void updateReaderClassify(ReaderClassify rc)throws SQLException;
	// 获取所有读者分类信息
	public List<ReaderClassify> findAllReaderClassify()throws SQLException;
	// 根据分类编号获取分类名称
	public Object getClassifyNameByum(int num)throws SQLException;
	// 根据读者分类编号获取当前的读者分类信息
	public ReaderClassify getReaderClassifyBynum(int num)throws SQLException;
}
