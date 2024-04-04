package com.design.sm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.dao.SoldNoteDAO;
import com.design.sm.model.SoldNote;

public class SoldNoteDAOImpl extends BaseDAOImpl<SoldNote> implements SoldNoteDAO{

	@Override
	public void addSoldNote(SoldNote sn) throws SQLException {
		String sql = "insert into sold_note values(?,?,?)";
		Object[] args = {sn.getOrder_num(),sn.getActual_amount(),sn.getPayment()};
		this.update(conn, sql, args);
	}

	@Override
	public List<SoldNote> findAllSoldNote() throws SQLException {
		String sql = "select * from sold_note";
		return this.getForList(conn, sql);
	}

	@Override
	public SoldNote getSoldNoteByNum(String orderNum) throws SQLException {
		String sql = "select * from sold_note where order_num=?";
		return this.get(conn, sql, orderNum);
	}

}
