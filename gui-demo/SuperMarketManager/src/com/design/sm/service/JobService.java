package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.Job;

public interface JobService {

	// 通过职位id获取职位名称
	public Object getJobName(String id) throws SQLException;

	// 查找所有商品职位信息
	public List<Job> findAllJob() throws SQLException;

	// 新增职位信息
	public void addJob(Job j) throws SQLException;

	// 修改职位信息
	public void updateJob(Job j) throws SQLException;

	// 根据id删除职位信息
	public void deleteJob(String id) throws SQLException;

	// 根据部门查找相应的职位信息
	public List<Job> getJobByDeptmentId(String deptId) throws SQLException;
	
	// 将List<Job>封装为Vector<Vector>方法
	public Vector<Vector> pack(List<Job> list)throws SQLException;

	// 根据职位id相应地获取该职位的具体信息
	public Job getJobById(String id)throws SQLException;
}
