package com.design.sm.model;

public class Employee {
	private String employee_id;
	private String employee_name;
	private String employee_num;
	private String id_card;
	private int age;
	private String gender;
	private String phone;
	private String email;
	private String address;
	private String hire_date;
	private String account_id;
	private String job_id;
	private String department_id;
	public Employee() {
		super();
	}
	public Employee(String employee_id, String employee_name,
			String employee_num, String id_card, int age, String gender,
			String phone, String email, String address, String hire_date,
			String account_id, String job_id, String department_id) {
		super();
		this.employee_id = employee_id;
		this.employee_name = employee_name;
		this.employee_num = employee_num;
		this.id_card = id_card;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.hire_date = hire_date;
		this.account_id = account_id;
		this.job_id = job_id;
		this.department_id = department_id;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getEmployee_num() {
		return employee_num;
	}
	public void setEmployee_num(String employee_num) {
		this.employee_num = employee_num;
	}
	public String getId_card() {
		return id_card;
	}
	public void setId_card(String id_card) {
		this.id_card = id_card;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHire_date() {
		return hire_date;
	}
	public void setHire_date(String hire_date) {
		this.hire_date = hire_date;
	}
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public String getJob_id() {
		return job_id;
	}
	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}
	public String getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}
	@Override
	public String toString() {
		return "Employee [employee_id=" + employee_id + ", employee_name="
				+ employee_name + ", employee_num=" + employee_num
				+ ", id_card=" + id_card + ", age=" + age + ", gender="
				+ gender + ", phone=" + phone + ", email=" + email
				+ ", address=" + address + ", hire_date=" + hire_date
				+ ", account_id=" + account_id + ", job_id=" + job_id
				+ ", department_id=" + department_id + "]";
	}
}
