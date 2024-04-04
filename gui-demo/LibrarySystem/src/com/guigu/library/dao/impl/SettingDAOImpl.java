package com.guigu.library.dao.impl;

import java.sql.SQLException;

import com.guigu.library.dao.SettingDAO;
import com.guigu.library.model.Setting;

public class SettingDAOImpl extends BaseDAOImpl<Setting> implements SettingDAO{

	@Override
	public void addSetting(Setting s) throws SQLException {
		String sql = "insert into setting values(?,?,?,?,?)";
		Object[] args = {s.getReader_id(),s.getInfoSearch(),s.getBooksManager(),s.getReaderManager(),s.getSystemSetup()};
		this.update(conn, sql, args);
	}

	@Override
	public void updateSetting(Setting s) throws SQLException {
		String sql = "update setting set infoSearch=?,booksManager=?,readerManager=?,systemSetup=? where reader_id=?";
		Object[] args = {s.getInfoSearch(),s.getBooksManager(),s.getReaderManager(),s.getSystemSetup(),s.getReader_id()};
		this.update(conn, sql, args);
	}

	@Override
	public void deleteSetting(String reader_id) throws SQLException {
		String sql = "delete from setting where reader_id = ?";
		this.update(conn, sql, reader_id);
	}

	@Override
	public Setting getSettingByReaderId(String reader_id) throws SQLException {
		String sql = "select * from setting where reader_id = ?";
		return this.get(conn, sql, reader_id);
	}
	
	@Override
	public Setting getUsersSettingById(String id) throws SQLException {
		String sql = "select s.* from users u,reader r,setting s where u.user_id=r.user_id "
				+ "and r.reader_id=s.reader_id and u.user_id = ?";
		return this.get(conn, sql, id);
	}
}
