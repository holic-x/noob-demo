package com.design.sm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.dao.JobDAO;
import com.design.sm.model.Job;

public class JobDAOImpl extends BaseDAOImpl<Job> implements JobDAO{

	@Override
	public Object getJobName(String id) throws SQLException {
		String sql = "select job_name from job where job_id = ?";
		return this.getForValue(conn, sql, id);
	}

	@Override
	public List<Job> findAllJob() throws SQLException {
		String sql = "select * from job";
		return this.getForList(conn, sql);
	}

	@Override
	public void addJob(Job j) throws SQLException {
		String sql = "insert into job values(?,?,?,?,?,?)";
		Object[] args = {j.getJob_id(),j.getJob_name(),j.getBase_salary(),j.getCommission_rate(),j.getJob_descr(),j.getDepartment_id()};
		this.update(conn, sql, args);
	}

	@Override
	public void updateJob(Job j) throws SQLException {
		String sql = "update job set job_name=?,base_salary=?,commission_rate=?,job_descr=?,department_id=? where job_id=?";
		Object[] args = {j.getJob_name(),j.getBase_salary(),j.getCommission_rate(),j.getJob_descr(),j.getDepartment_id(),j.getJob_id()};
		this.update(conn, sql, args);
	}

	@Override
	public void deleteJob(String id) throws SQLException {
		String sql = "delete from job where job_id = ?";
		this.update(conn, sql, id);
	}

	@Override
	public List<Job> getJobByDeptmentId(String deptId) throws SQLException {
		String sql = "select * from job where department_id = ?";
		return this.getForList(conn, sql, deptId);
	}

	@Override
	public Job getJobById(String id) throws SQLException {
		String sql = "select * from job where job_id=?";
		return this.get(conn, sql, id);
	}
}
