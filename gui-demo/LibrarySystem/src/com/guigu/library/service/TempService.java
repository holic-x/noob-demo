package com.guigu.library.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.guigu.library.model.Temp;

public interface TempService {

	// 添加收藏记录
	public void addTemp(Temp t) throws SQLException;

	// 根据记录id删除收藏记录
	public void deleteTemp(String id) throws SQLException;

	// 根据指定的读者id查找收藏记录
	public List<Temp> findTempByReaderId(String reader_id) throws SQLException;

	// 将List<Temp>类型封装为Vector<Vector>类型
	public Vector<Vector> pack(List<Temp> list) throws SQLException;

	// 根据ISBN号查找收藏记录(避免重复添加收藏记录)
	public Temp getTempByISBN(String isbn,String reader_id) throws SQLException;

}
