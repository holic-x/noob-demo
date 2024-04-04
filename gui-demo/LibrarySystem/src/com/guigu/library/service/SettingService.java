package com.guigu.library.service;

import java.sql.SQLException;

import com.guigu.library.model.Setting;

public interface SettingService {

	// 添加设置信息
	public void addSetting(Setting s) throws SQLException;

	// 修改设置信息
	public void updateSetting(Setting s) throws SQLException;

	// 根据reader_id删除设置信息
	public void deleteSetting(String reader_id) throws SQLException;

	// 根据reader_id获取设置信息
	public Setting getSettingByReaderId(String reader_id) throws SQLException;

	// 根据当前用户的账号id获取当前用户的配置权限
	public Setting getUsersSettingById(String user_id) throws SQLException;
}
