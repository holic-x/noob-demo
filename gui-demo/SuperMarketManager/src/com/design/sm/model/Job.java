package com.design.sm.model;

public class Job {
	private String job_id;
	private String job_name;
	private double base_salary;
	private double commission_rate;
	private String job_descr;
	private String department_id;
	public Job() {
		super();
	}
	public Job(String job_id, String job_name, double base_salary,
			double commission_rate, String job_descr, String department_id) {
		super();
		this.job_id = job_id;
		this.job_name = job_name;
		this.base_salary = base_salary;
		this.commission_rate = commission_rate;
		this.job_descr = job_descr;
		this.department_id = department_id;
	}
	public String getJob_id() {
		return job_id;
	}
	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}
	public String getJob_name() {
		return job_name;
	}
	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}
	public double getBase_salary() {
		return base_salary;
	}
	public void setBase_salary(double base_salary) {
		this.base_salary = base_salary;
	}
	public double getCommission_rate() {
		return commission_rate;
	}
	public void setCommission_rate(double commission_rate) {
		this.commission_rate = commission_rate;
	}
	public String getJob_descr() {
		return job_descr;
	}
	public void setJob_descr(String job_descr) {
		this.job_descr = job_descr;
	}
	public String getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}
	@Override
	public String toString() {
		return "Job [job_id=" + job_id + ", job_name=" + job_name
				+ ", base_salary=" + base_salary + ", commission_rate="
				+ commission_rate + ", job_descr=" + job_descr
				+ ", department_id=" + department_id + "]";
	}
}
