package com.design.sm.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.dao.DepartmentDAO;
import com.design.sm.dao.JobDAO;
import com.design.sm.dao.impl.DepartmentDAOImpl;
import com.design.sm.dao.impl.JobDAOImpl;
import com.design.sm.model.Employee;
import com.design.sm.model.Job;
import com.design.sm.service.JobService;

public class JobServiceImpl implements JobService{

	private JobDAO jobDAO = new JobDAOImpl();
	private DepartmentDAO departmentDAO = new DepartmentDAOImpl();
	
	@Override
	public Object getJobName(String id) throws SQLException {
		return jobDAO.getJobName(id);
	}

	@Override
	public List<Job> findAllJob() throws SQLException {
		return jobDAO.findAllJob();
	}

	@Override
	public void addJob(Job j) throws SQLException {
		jobDAO.addJob(j);
	}

	@Override
	public void updateJob(Job j) throws SQLException {
		jobDAO.updateJob(j);
	}

	@Override
	public void deleteJob(String id) throws SQLException {
		jobDAO.deleteJob(id);
	}

	@Override
	public List<Job> getJobByDeptmentId(String deptId) throws SQLException {
		return jobDAO.getJobByDeptmentId(deptId);
	}

	@Override
	public Vector<Vector> pack(List<Job> list) throws SQLException {
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (Job obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 16; i++) {
					temp.add(obj.getJob_id());//职位id
					temp.add(obj.getJob_name());//职位名称
					temp.add(obj.getBase_salary());//基本工资
					temp.add(obj.getCommission_rate());//提成
					temp.add(obj.getJob_descr());//职位描述
					temp.add(obj.getDepartment_id());//职位所属部门id
					temp.add(departmentDAO.getDepartmentName(obj.getDepartment_id()));//职位所属部门名称
				}
				rows.add(temp);
			}
		}
		return rows;
	}

	@Override
	public Job getJobById(String id) throws SQLException {
		return jobDAO.getJobById(id);
	}

}
