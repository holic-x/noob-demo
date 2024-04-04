package com.guigu.library.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.guigu.library.model.Reader;
import com.guigu.library.model.Users;

public interface ReaderService {

	// 添加读者信息
	public void addReader(Reader r) throws SQLException;

	// 修改读者信息
	public void updateReader(Reader r) throws SQLException;

	// 根据id删除读者信息
	public void deleteReader(String id) throws SQLException;

	// 根据id获取单个读者信息
	public Reader getReaderById(String id) throws SQLException;

	// 根据指定的条件获取读者信息
	public List<Reader> findReaderUnion(int choose, Object... args)
			throws SQLException;

	// 将List<Reader>封装为Vector<Vector>类型
	public Vector<Vector> pack(List<Reader> list) throws SQLException;

	// 根据当前登录的账号id获取相应的读者身份信息
	public Reader getReaderByUserId(String user_id) throws SQLException;
	
	// 根据读者的条形码获取读者的身份信息
	public Reader getReaderByBarcode(String barcode)throws SQLException;

}
