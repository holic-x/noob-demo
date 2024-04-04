package com.design.sm.model;

public class Department {
	private String department_id;
	private String department_name;
	private String manager_id;
	private String department_descr;
	public Department() {
		super();
	}
	public Department(String department_id, String department_name,
			String manager_id, String department_descr) {
		super();
		this.department_id = department_id;
		this.department_name = department_name;
		this.manager_id = manager_id;
		this.department_descr = department_descr;
	}
	public String getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getManager_id() {
		return manager_id;
	}
	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}
	public String getDepartment_descr() {
		return department_descr;
	}
	public void setDepartment_descr(String department_descr) {
		this.department_descr = department_descr;
	}
	@Override
	public String toString() {
		return "Department [department_id=" + department_id
				+ ", department_name=" + department_name + ", manager_id="
				+ manager_id + ", department_descr=" + department_descr + "]";
	}
}
