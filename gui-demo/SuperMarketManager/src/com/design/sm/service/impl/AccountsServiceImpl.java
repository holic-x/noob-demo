package com.design.sm.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.dao.AccountsDAO;
import com.design.sm.dao.impl.AccountsDAOImpl;
import com.design.sm.model.Accounts;
import com.design.sm.service.AccountsService;
import com.design.sm.utils.SecretKey;

public class AccountsServiceImpl implements AccountsService {

	private AccountsDAO accountsDAO = new AccountsDAOImpl();
	// 定义用户登录的方法
	@Override
	public Accounts loginAccounts(Accounts user) throws SQLException {
		return accountsDAO.loginAccounts(user);
	}

	// 添加用户
	@Override
	public void addAccounts(Accounts user) throws SQLException {
		accountsDAO.addAccounts(user);
	}

	// 删除用户
	@Override
	public void deleteAccounts(String id) throws SQLException {
		accountsDAO.deleteAccounts(id);
	}

	// 修改用户
	@Override
	public void updateAccounts(Accounts user) throws SQLException {
		accountsDAO.updateAccounts(user);
	}

	// 获取所有的用户记录
	@Override
	public Vector<Vector> findAllAccountsVector() throws SQLException {
		return this.pack(accountsDAO.findAllAccounts());
	}

	// 获取所有的用户列表
	@Override
	public List<Accounts> findAllAccountsList() throws SQLException {
		return accountsDAO.findAllAccounts();
	}

	// 根据用户id获取用户账号名称
	@Override
	public Object getAccountsName(String id) throws SQLException {
		return accountsDAO.getAccountsName(id);
	}

	// 根据用户名称关键字查找用户信息
	@Override
	public Vector<Vector> getAccountsByNameKeyword(String keyword)
			throws SQLException {
		return this.pack(accountsDAO.getAccountsByNameKeyword(keyword));
	}

	// 根据用户id获取指定的用户权限
	@Override
	public Object getAccountsLimits(String id) throws SQLException {
		return accountsDAO.getAccountsLimits(id);
	}

	// 根据用户名进行判断，判断当前用户是否已存在，如果存在则视为不合法返回false，如果不存在则视为合法返回true
	@Override
	public boolean isValidAccountsname(String username) throws SQLException {
		Accounts findAccount = accountsDAO.getAccountsByName(username);
		if(findAccount==null)
			return true;
		return false;
	}

	// 根据用户名获取当前对应用户的信息
	@Override
	public Accounts getAccountsByName(String username) throws SQLException {
		return accountsDAO.getAccountsByName(username);
	}

	// 根据用户id设置用户权限
	@Override
	public void setAccountsLimits(String id, int limits) throws SQLException {
		accountsDAO.setAccountsLimits(id, limits);
	}

	// 将List<User>封装为Vector<Vector>类型
	public Vector<Vector> pack(List<Accounts> list) {
		// 手动封装查找到的数据信息
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (Accounts obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 5; i++) {
					temp.add(obj.getAccount_id());// 用户id
					temp.add(obj.getUsername());// 用户名称
					temp.add(obj.getPassword());// 用户密码
					temp.add(SecretKey.encrypt(obj.getPassword()));//用户密码加密
					temp.add(obj.getLimits());// 用户权限
					temp.add(this.getUserIdentity(obj.getLimits()));//用户身份
				}
				rows.add(temp);
			}
		}
		return rows;
	}

	/**
	 * 通过用户权限号获取用户的身份 
	 * 0.账号禁用
	 * 1.超级管理员：能够管理各个功能模块 （前台后台）
	 * 2.经理、主管员：能够维护、管理各个部门的工作流程
	 * 3.基层员工：只能够查看操作基本商品信息 、完成对应职位上的工作
	 * 权限控制：各个部门之间不能跨越，部门内部设置相应的监管机制
	 */
	public String getUserIdentity(int limits) {
		String identity = null;
		if (limits == 1) {
			identity = "超级管理员";
		} else if (limits == 2) {
			identity = "经理/主管";
		} else if (limits == 3) {
			identity = "普通员工";
		}else if (limits == 0) {
			identity = "禁用";
		}
		return identity;
	}

	@Override
	public Accounts getAccountsById(String id) throws SQLException {
		return accountsDAO.getAccountsById(id);
	}
	
}
