package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.PurchaseNote;

public interface PurchaseNoteDAO extends BaseDAO<PurchaseNote>{
	
	// 增加进购记录
	public void addPurchaseNote(PurchaseNote pn)throws SQLException;
	
	// 获取所有的进购记录
	public List<PurchaseNote> findAllPurchaseNote()throws SQLException;
	
	// 通过订单编号获取相应的进购记录
	public PurchaseNote getPurchaseNoteByNum(String orderNum)throws SQLException;

}
