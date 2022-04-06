package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.SoldNote;

public interface SoldNoteDAO extends BaseDAO<SoldNote>{
	
	// 增加销售记录
	public void addSoldNote(SoldNote sn)throws SQLException;
	
	// 获取所有的销售记录
	public List<SoldNote> findAllSoldNote()throws SQLException;
	
	// 通过订单编号获取相应的销售记录
	public SoldNote getSoldNoteByNum(String orderNum)throws SQLException;

}
