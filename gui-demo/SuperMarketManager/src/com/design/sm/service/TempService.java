package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.Temp;

public interface TempService {
	// 增加临时记录
	public void addTemp(Temp t) throws SQLException;

	// 修改临时记录
	public void update(Temp t) throws SQLException;

	// 删除临时记录
	public void deleteTemp(String id) throws SQLException;
	
	// 获取所有的临时记录
	public List<Temp> findAllTempList()throws SQLException;
	
	// 将查找到的记录封装为Vector<Vector>类型
	public Vector<Vector> pack(List<Temp> list)throws SQLException;
	
	// 通过提供的产品id数组获取临时相应的记录信息
	public List<Temp> getTempListByProductId(String[] ids)throws SQLException;
}
