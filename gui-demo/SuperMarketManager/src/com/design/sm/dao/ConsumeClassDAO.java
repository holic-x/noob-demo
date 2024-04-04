package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.ConsumeClass;

public interface ConsumeClassDAO extends BaseDAO<ConsumeClass>{
	
	// 获取当前定义的所有消费等级
	public List<ConsumeClass> findAllConsumeClass()throws SQLException;
	
	// 根据id删除消费等级信息
	public void deleteConsumeClass(String id)throws SQLException;
	
	// 根据id修改消费等级信息
	public void updateConsumeClass(ConsumeClass cc)throws SQLException;
	
	// 根据id获取消费等级名称
	public Object getConsumeClassNameById(int id)throws SQLException;
	
	// 根据id获取消费等级信息
	public ConsumeClass getConsumeClassById(int id)throws SQLException;

}
