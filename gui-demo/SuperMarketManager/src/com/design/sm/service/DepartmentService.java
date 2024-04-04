package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.Department;

public interface DepartmentService {

	// 通过部门id获取部门名称
	public Object getDepartmentName(String id) throws SQLException;

	// 根据部门名称关键字获取部门信息
	public List<Department> getDepartmentByNameKeyword(String keyword)
			throws SQLException;

	// 查找所有部门信息
	public List<Department> findAllDepartment() throws SQLException;

	// 新增部门信息
	public void addDepartment(Department d) throws SQLException;

	// 修改部门信息
	public void updateDepartment(Department d) throws SQLException;

	// 根据id删除部门信息
	public void deleteDepartment(String id) throws SQLException;
	
	// 定义pack方法将List<Department>封装为Vector<Vector>记录
	public Vector<Vector> pack(List<Department> list)throws SQLException;

}
