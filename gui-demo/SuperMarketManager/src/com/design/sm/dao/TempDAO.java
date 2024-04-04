package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.Temp;

public interface TempDAO extends BaseDAO<Temp>{
	
	// 增加临时记录
	public void addTemp(Temp t)throws SQLException;
	
	// 修改临时记录
	public void update(Temp t)throws SQLException;
	
	// 删除临时记录
	public void deleteTemp(String id)throws SQLException;
	
	// 获取所有的临时记录
	public List<Temp> findAllTempList()throws SQLException;
	
	// 通过产品id获取临时记录信息
	public Temp getTempByProductId(String id)throws SQLException;

}
