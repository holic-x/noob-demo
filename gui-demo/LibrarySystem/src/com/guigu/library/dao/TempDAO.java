package com.guigu.library.dao;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.model.Temp;

public interface TempDAO extends BaseDAO<Temp>{
	
	// 添加收藏记录
	public void addTemp(Temp t)throws SQLException;
	
	// 根据记录id删除收藏记录
	public void deleteTemp(String id)throws SQLException;
	
	// 根据指定的读者id查找收藏记录
	public List<Temp> findTempByReaderId(String reader_id)throws SQLException;
	
	// 根据ISBN号查找收藏记录(避免重复添加收藏记录)
	public Temp getTempByISBN(String isbn,String reader_id)throws SQLException;
	
}
