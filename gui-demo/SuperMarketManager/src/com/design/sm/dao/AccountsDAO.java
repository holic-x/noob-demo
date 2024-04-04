package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.Accounts;

public interface AccountsDAO extends BaseDAO<Accounts> {

	// 定义用户登录的方法
	public Accounts loginAccounts(Accounts user) throws SQLException;

	// 增加用户
	public void addAccounts(Accounts user) throws SQLException;

	// 根据用户id删除用户
	public void deleteAccounts(String id) throws SQLException;

	// 修改指定用户
	public void updateAccounts(Accounts user) throws SQLException;

	// 查找所有用户
	public List<Accounts> findAllAccounts() throws SQLException;

	// 根据用户名称关键字查找用户信息
	public List<Accounts> getAccountsByNameKeyword(String keyword)
			throws SQLException;

	// 通过用户id查找用户名称
	public Object getAccountsName(String id) throws SQLException;

	// 根据账号id获取账号权限
	public Object getAccountsLimits(String id) throws SQLException;

	// 根据账号id设置账号权限
	public void setAccountsLimits(String id, int limits) throws SQLException;

	// 根据用户名查找用户信息
	public Accounts getAccountsByName(String username) throws SQLException;
	
	// 根据账号id查找账户信息
	public Accounts getAccountsById(String id)throws SQLException;

}
