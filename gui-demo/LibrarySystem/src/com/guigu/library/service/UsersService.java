package com.guigu.library.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.guigu.library.model.Setting;
import com.guigu.library.model.Users;

public interface UsersService {
	// 封装用户登录方法
	public Users loginUsers(Users user) throws SQLException;

	// 增加用户
	public void addUsers(Users user) throws SQLException;

	// 根据用户id删除用户
	public void deleteUsers(String id) throws SQLException;

	// 修改指定用户
	public void updateUsers(Users user) throws SQLException;

	// 查找所有用户记录
	public Vector<Vector> findAllUsersVector() throws SQLException;

	// 查找所有用户列表
	public List<Users> findAllUsersList() throws SQLException;

	// 通过用户id查找用户名称
	public String getUsersName(String id) throws SQLException;

	// 根据用户名称关键字查找用户信息
	public Vector<Vector> getUsersByNameKeyword(String keyword)
			throws SQLException;

	// 根据账号id获取账号权限
	public Object getUsersLimits(String id) throws SQLException;

	// 根据用户名判断当前的用户姓名是否存在，如果存在返回true，不存在返回false
	public boolean isValidUsersname(String username) throws SQLException;

	// 通过用户昵称查找用户信息
	public Users getUsersByName(String username) throws SQLException;

	// 根据账号id设置账号权限
	public void setUsersLimits(String id, int limits) throws SQLException;

	// 根据账号id获取用户信息
	public Users getUsersById(String id) throws SQLException;

}
