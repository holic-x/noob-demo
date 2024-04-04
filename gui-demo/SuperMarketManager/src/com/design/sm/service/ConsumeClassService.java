package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.ConsumeClass;

public interface ConsumeClassService {

	// 获取当前定义的所有消费等级
	public List<ConsumeClass> findAllConsumeClassList() throws SQLException;

	// 将List<ConsumeClass>数据封装为Vector<Vector>类型
	public Vector<Vector> pack(List<ConsumeClass> list) throws SQLException;

	// 根据id删除消费等级信息
	public void deleteConsumeClass(String id) throws SQLException;

	// 根据id修改消费等级信息
	public void updateConsumeClass(ConsumeClass cc) throws SQLException;

	// 根据id获取消费等级名称
	public Object getConsumeClassNameById(int id) throws SQLException;

	// 根据id获取消费等级信息
	public ConsumeClass getConsumeClassById(int id) throws SQLException;

}
