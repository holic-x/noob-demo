package com.guigu.library.service.impl;

import java.sql.SQLException;

import com.guigu.library.dao.SettingDAO;
import com.guigu.library.dao.impl.SettingDAOImpl;
import com.guigu.library.model.Setting;
import com.guigu.library.service.SettingService;

public class SettingServiceImpl implements SettingService{

	private SettingDAO settingDAO = new SettingDAOImpl();
	@Override
	public void addSetting(Setting s) throws SQLException {
		settingDAO.addSetting(s);
	}

	@Override
	public void updateSetting(Setting s) throws SQLException {
		settingDAO.updateSetting(s);
	}

	@Override
	public void deleteSetting(String reader_id) throws SQLException {
		settingDAO.deleteSetting(reader_id);
	}

	@Override
	public Setting getSettingByReaderId(String reader_id) throws SQLException {
		return settingDAO.getSettingByReaderId(reader_id);
	}

	@Override
	public Setting getUsersSettingById(String user_id) throws SQLException {
		return settingDAO.getUsersSettingById(user_id);
	}

}
