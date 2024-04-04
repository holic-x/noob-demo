package com.guigu.library.dao.impl;

import java.sql.SQLException;

import com.guigu.library.dao.LibraryCardDAO;
import com.guigu.library.model.LibraryCard;

public class LibraryCardDAOImpl extends BaseDAOImpl<LibraryCard> implements LibraryCardDAO{

	@Override
	public void addLibraryCard(LibraryCard lc) throws SQLException {
		String sql = "insert into library_card values(?,?,to_date(substr(?,1,10),'yyyy-mm-dd'),"
				+ "to_date(substr(?,1,10),'yyyy-mm-dd'),?,?)";
		Object[] args = {lc.getCard_id(),lc.getCard_num(),lc.getHandle_date(),lc.getValid_till(),lc.getLoss_state(),
				lc.getDisable_state()};
		this.update(conn, sql, args);
	}

	@Override
	public void updateLibraryCard(LibraryCard lc) throws SQLException {
		String sql = "update library_card set handle_date=to_date(substr(?,1,10),'yyyy-mm-dd'),"
				+ "valid_till=to_date(substr(?,1,10),'yyyy-mm-dd'),loss_state=?,disable_state=? "
				+ "where card_id=?";
		Object[] args = {lc.getHandle_date(),lc.getValid_till(),lc.getLoss_state(),lc.getDisable_state(),lc.getCard_id()};
		this.update(conn, sql, args);
	}

	@Override
	public void deleteLibraryCard(String card_id) throws SQLException {
		String sql = "delete from library_card where card_id=?";
		this.update(conn, sql, card_id);
	}

	@Override
	public LibraryCard getLibraryCardById(String card_id) throws SQLException {
		String sql = "select * from library_card where card_id=?";
		return this.get(conn, sql, card_id);
	}

	@Override
	public LibraryCard getLibraryCardByNum(String card_num) throws SQLException {
		String sql = "select * from library_card where card_num=?";
		return this.get(conn, sql, card_num);
	}

	@Override
	public Object getLibraryCardNumById(String card_id) throws SQLException {
		String sql = "select card_num from library_card where card_id = ?";
		return this.getForValue(conn, sql, card_id);
	}

	@Override
	public Object getLibraryCardSeq() throws SQLException {
		String sql = "select lpad(libraryCardseq.nextval,4,'0') from dual";
		return this.getForValue(conn, sql);
	}
}
