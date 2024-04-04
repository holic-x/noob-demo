package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.PurchaseNote;

public interface PurchaseNoteService {

	// 增加销售记录
	public void addPurchaseNote(PurchaseNote pn) throws SQLException;

	// 获取所有的销售记录
	public List<PurchaseNote> findAllPurchaseNote() throws SQLException;

	// 将销售记录进行封装
	public Vector<Vector> pack(List<PurchaseNote> list) throws SQLException;

	// 通过订单编号获取相应的销售记录
	public PurchaseNote getPurchaseNoteByNum(String orderNum) throws SQLException;

}
