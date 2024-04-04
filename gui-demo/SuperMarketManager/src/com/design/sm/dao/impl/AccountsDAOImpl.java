package com.design.sm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.dao.AccountsDAO;
import com.design.sm.model.Accounts;

public class AccountsDAOImpl extends BaseDAOImpl<Accounts> implements AccountsDAO{
	// 根据用户名、用户密码查找登录用户
	@Override
	public Accounts loginAccounts(Accounts user) throws SQLException {
		String sql = "select * from accounts where username=? and password=?";
		Object[] args = { user.getUsername(), user.getPassword() };
		Accounts loginAccounts = this.get(conn, sql, args);
		return loginAccounts;
	}

	// 添加用户信息
	@Override
	public void addAccounts(Accounts user) throws SQLException {
		// 随机生成10位字符串数据,作为用户id
		String sql = "insert into accounts values(?,?,?,?)";
		Object[] args = { user.getAccount_id(), user.getUsername(),
				user.getPassword(), user.getLimits() };
		this.update(conn, sql, args);
	}

	// 删除用户信息
	@Override
	public void deleteAccounts(String id) throws SQLException {
		String sql = "delete from accounts where account_id=?";
		this.update(conn, sql, id);
	}

	// 修改用户信息
	@Override
	public void updateAccounts(Accounts user) throws SQLException {
		String sql = "update accounts set username=?,password=?,limits=? where account_id=?";
		Object[] args = { user.getUsername(), user.getPassword(),
				user.getLimits(), user.getAccount_id() };
		this.update(conn, sql, args);
	}

	// 查找所有用户信息
	@Override
	public List<Accounts> findAllAccounts() throws SQLException {
		String sql = "select * from accounts";
		return this.getForList(conn, sql);
	}

	// 根据用户id获取用户姓名
	@Override
	public Object getAccountsName(String id) throws SQLException {
		String sql = "select username from accounts where account_id=?";
		return this.getForValue(conn, sql, id);
	}

	// 根据相应的关键字匹配用户名称满足的信息
	@Override
	public List<Accounts> getAccountsByNameKeyword(String keyword) throws SQLException {
		String sql = "select * from accounts where username like ?";
		return this.getForList(conn, sql, keyword);
	}

	// 根据用户id获取用户权限
	@Override
	public Object getAccountsLimits(String id) throws SQLException {
		String sql = "select limits from accounts where account_id=?";
		return this.getForValue(conn, sql, id);
	}

	// 根据用户姓名获取当前用户的用户具体信息
	@Override
	public Accounts getAccountsByName(String username) throws SQLException {
		String sql = "select * from accounts where username=?";
		return this.get(conn, sql, username);
	}

	// 根据当前用户id设置用户的权限
	@Override
	public void setAccountsLimits(String id, int limits) throws SQLException {
		String sql = "update accounts set limits=? where account_id=?";
		Object[] args = { limits, id };
		this.update(conn, sql, args);
	}

	@Override
	public Accounts getAccountsById(String id) throws SQLException {
		String sql = "select * from accounts where account_id=?";
		return this.get(conn, sql, id);
	}
}
