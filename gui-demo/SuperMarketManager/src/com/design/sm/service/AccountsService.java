package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.Accounts;

public interface AccountsService {

	// 封装用户登录方法
	public Accounts loginAccounts(Accounts user) throws SQLException;

	// 增加用户
	public void addAccounts(Accounts user) throws SQLException;

	// 根据用户id删除用户
	public void deleteAccounts(String id) throws SQLException;

	// 修改指定用户
	public void updateAccounts(Accounts user) throws SQLException;

	// 查找所有用户记录
	public Vector<Vector> findAllAccountsVector() throws SQLException;

	// 查找所有用户列表
	public List<Accounts> findAllAccountsList() throws SQLException;

	// 通过用户id查找用户名称
	public Object getAccountsName(String id) throws SQLException;

	// 根据用户名称关键字查找用户信息
	public Vector<Vector> getAccountsByNameKeyword(String keyword)
			throws SQLException;

	// 根据账号id获取账号权限
	public Object getAccountsLimits(String id) throws SQLException;

	// 根据用户名判断当前的用户姓名是否合法，如果存在视为非法返回false，不存在视为合法返回true
	public boolean isValidAccountsname(String username) throws SQLException;

	// 通过用户昵称查找用户信息
	public Accounts getAccountsByName(String username) throws SQLException;

	// 根据账号id设置账号权限
	public void setAccountsLimits(String id, int limits) throws SQLException;

	// 根据账号id查找账户信息
	public Accounts getAccountsById(String id) throws SQLException;

}
