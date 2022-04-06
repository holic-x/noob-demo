package com.design.sm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.dao.DepartmentDAO;
import com.design.sm.model.Department;

public class DepartmentDAOImpl extends BaseDAOImpl<Department> implements DepartmentDAO{

	@Override
	public Object getDepartmentName(String id) throws SQLException {
		String sql = "select department_name from department where department_id = ?";
		return this.getForValue(conn, sql, id);
	}

	@Override
	public List<Department> getDepartmentByNameKeyword(String keyword)
			throws SQLException {
		String sql = "select * from department where department_name like ?";
		return this.getForList(conn, sql, keyword);
	}

	@Override
	public List<Department> findAllDepartment() throws SQLException {
		String sql = "select * from department";
		return this.getForList(conn, sql);
	}

	@Override
	public void addDepartment(Department d) throws SQLException {
		String sql = "insert into department values(?,?,?,?)";
		Object[] args = {d.getDepartment_id(),d.getDepartment_name(),d.getManager_id(),d.getDepartment_descr()};
		this.update(conn, sql, args);
	}

	@Override
	public void updateDepartment(Department d) throws SQLException {
		String sql = "update department set department_name=?,manager_id=?,department_descr=? where department_id=?";
		Object[] args = {d.getDepartment_name(),d.getManager_id(),d.getDepartment_descr(),d.getDepartment_id()};
		this.update(conn, sql, args);
	}

	@Override
	public void deleteDepartment(String id) throws SQLException {
		String sql = "delete from department where department_id=?";
		this.update(conn, sql, id);
	}

}
