package com.design.sm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.dao.PurchaseNoteDAO;
import com.design.sm.model.PurchaseNote;

public class PurchaseNoteDAOImpl extends BaseDAOImpl<PurchaseNote> implements PurchaseNoteDAO{

	@Override
	public void addPurchaseNote(PurchaseNote pn) throws SQLException {
		String sql = "insert into puchase_note values(?,?,?)";
		Object[] args = {pn.getOrder_num(),pn.getActual_amount(),pn.getPayment()};
		this.update(conn, sql, args);
	}

	@Override
	public List<PurchaseNote> findAllPurchaseNote() throws SQLException {
		String sql = "select * from puchase_note";
		return this.getForList(conn, sql);
	}

	@Override
	public PurchaseNote getPurchaseNoteByNum(String orderNum) throws SQLException {
		String sql = "select * from puchase_note where order_num=?";
		return this.get(conn, sql, orderNum);
	}

}
