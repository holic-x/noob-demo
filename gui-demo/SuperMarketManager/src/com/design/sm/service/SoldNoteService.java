package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.SoldNote;

public interface SoldNoteService {

	// 增加销售记录
	public void addSoldNote(SoldNote sn) throws SQLException;

	// 获取所有的销售记录
	public List<SoldNote> findAllSoldNote() throws SQLException;

	// 将销售记录进行封装
	public Vector<Vector> pack(List<SoldNote> list) throws SQLException;

	// 通过订单编号获取相应的销售记录
	public SoldNote getSoldNoteByNum(String orderNum) throws SQLException;

}
