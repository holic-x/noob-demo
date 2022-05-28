package com.guigu.library.dao;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.model.Reader;

public interface ReaderDAO extends BaseDAO<Reader>{
	
	// 添加读者信息
	public void addReader(Reader r)throws SQLException;
	
	// 修改读者信息
	public void updateReader(Reader r)throws SQLException;
	
	// 根据id删除读者信息
	public void deleteReader(String id)throws SQLException;
	
	// 查找所有读者信息
	public List<Reader> findAllReader()throws SQLException;
	
	// 以下定义根据多种方式获取读者信息
	// 根据id获取读者信息
	public Reader getReaderById(String id)throws SQLException;
	
	// 根据条形码关键字获取读者信息
	public List<Reader> findReaderByBarcode(String kw_barcode)throws SQLException;
	
	// 根据学工编号关键字获取读者信息
	public List<Reader> findReaderByAcademicNum(String kw_academic_num)throws SQLException;
	
	// 根据身份证号关键字获取读者信息
	public List<Reader> findReaderByIdCard(String kw_idCard)throws SQLException;
	
	// 根据读者账号名称关键字获取读者信息
	public List<Reader> findReaderByAccount(String kw_user_name)throws SQLException;
	
	// 根据读者借阅证编号关键字获取读者信息
	public List<Reader> findReaderByCardId(String kw_card_num)throws SQLException;
	
	// 根据当前登录的账号id获取相应的读者身份信息
	public Reader getReaderByUserId(String user_id)throws SQLException;
	
	// 根据读者的条形码获取读者的身份信息
	public Reader getReaderByBarcode(String barcode)throws SQLException;
	
}
