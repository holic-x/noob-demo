package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.Employee;
import com.design.sm.model.Product;

public interface EmployeeDAO extends BaseDAO<Employee> {
	// 根据员工id获取员工姓名
	public Object getEmployeeName(String id) throws SQLException;

	// 增加员工信息
	public void addEmployee(Employee e) throws SQLException;

	// 根据id删除员工信息
	public void deleteEmployee(String id) throws SQLException;

	// 修改员工信息
	public void updateEmployee(Employee e) throws SQLException;

	// 查询所有员工信息
	public List<Employee> findAllEmployee() throws SQLException;

	// 根据员工姓名进行关键字查找员工信息
	public List<Employee> getEmployeeByNameKeyword(String keyword)
			throws SQLException;

	// 根据员工id获取单个员工信息
	public Employee getEmployeeById(String id) throws SQLException;

	// //根据员工id分配账号信息
	// public void setEmployeeAccount(String eid,String accountId)throws
	// SQLException;

	// 查询当前使用某个账号的员工id
	public Object getEmployeeIdByAccountId(String accountId)
			throws SQLException;

	// 根据部门id获取相应部门的员工信息
	public List<Employee> getEmployeeByDeptId(String deptId)
			throws SQLException;

	// 分页查找数据
	public List<Employee> getAllEmployeeByPage(int page) throws SQLException;

	// 获取商品总数从而得出相应的分页数
	public Object getEmployeeCount() throws SQLException;
	
	// 获取当前员工编号的下一个序列号
	public Object getEmployeeNumNextSeq()throws SQLException;
	
	// 根据部门id获取每个部门的应发的工资总数
	public Object getSalarySumByDeptId(String deptId)throws SQLException;
	
	// 根据部门id获取每个部门的总人数
	public Object getEmployeeSumByDeptId(String deptId)throws SQLException;
	
}
