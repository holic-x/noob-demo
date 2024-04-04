package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTable;

import com.design.sm.model.Employee;

public interface EmployeeService {
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
	public String getEmployeeIdByAccountId(String accountId)
			throws SQLException;

	// 根据部门id获取相应部门的员工信息
	public List<Employee> getEmployeeByDeptId(String deptId)
			throws SQLException;

	// 将List<Employee>封装为Vector<Vector>方法
	public Vector<Vector> pack(List<Employee> list) throws SQLException;

	// 分页查找数据
	public List<Employee> getAllEmployeeByPage(int page) throws SQLException;

	// 获取商品总数从而得出相应的分页数
	public int getEmployeeMaxPage() throws SQLException;

	// 批量导出员工信息数据
	public int exportData(String[] ids);

	// 根据给定的商品id列表获取商品信息
	public List<Employee> findAllEmployeeByIds(String[] empIds)
			throws SQLException;

	// 获取当前员工编号的下一个序列号
	public String getEmployeeNumNextSeq() throws SQLException;

	// 导出员工工资信息
	public int exportSalaryData(String[] empIds);
	
	// 导出所有部门工资结算信息
	public int exportBalanceData(JTable table);

	// 根据部门id获取每个部门的应发的工资总数
	public Map<String,Double> getSalarySumByDeptId() throws SQLException;

	// 根据部门id获取每个部门的总人数
	public Map<String,Integer> getEmployeeSumByDeptId() throws SQLException;
}
