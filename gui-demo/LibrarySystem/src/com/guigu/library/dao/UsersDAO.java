package com.guigu.library.dao;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.model.Setting;
import com.guigu.library.model.Users;

public interface UsersDAO extends BaseDAO<Users> {

	// 定义用户登录的方法
	public Users loginUsers(Users user) throws SQLException;

	// 增加用户
	public void addUsers(Users user) throws SQLException;

	// 根据用户id删除用户
	public void deleteUsers(String id) throws SQLException;

	// 修改指定用户
	public void updateUsers(Users user) throws SQLException;

	// 查找所有用户
	public List<Users> findAllUsers() throws SQLException;

	// 根据用户名称关键字查找用户信息
	public List<Users> getUsersByNameKeyword(String keyword) throws SQLException;

	// 通过用户id查找用户名称
	public Object getUsersName(String id) throws SQLException;

	// 根据账号id获取账号权限
	public Object getUsersLimits(String id) throws SQLException;

	// 根据账号id设置账号权限
	public void setUsersLimits(String id, int limits) throws SQLException;

	// 根据用户名查找用户信息
	public Users getUsersByName(String username) throws SQLException;
	
	// 根据账号id获取用户信息
	public Users getUsersById(String id) throws SQLException;
	
}
