package com.design.sm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.dao.EmployeeDAO;
import com.design.sm.model.Employee;

public class EmployeeDAOImpl extends BaseDAOImpl<Employee> implements EmployeeDAO{

	@Override
	public Object getEmployeeName(String id) throws SQLException {
		String sql = "select employee_name from employee where employee_id=?";
		return this.getForValue(conn, sql, id);
	}

	@Override
	public void addEmployee(Employee e) throws SQLException {
		String sql = "insert into employee values(?,?,?,?,?,?,?,?,?,to_date(substr(?,1,10),'yyyy-mm-dd'),?,?,?)";
		Object[] args = {e.getEmployee_id(),e.getEmployee_num(),e.getEmployee_name(),e.getId_card(),
				e.getAge(),e.getGender(),e.getPhone(),e.getEmail(),e.getAddress(),e.getHire_date(),
				e.getAccount_id(),e.getJob_id(),e.getDepartment_id()};
		this.update(conn, sql, args);
	}

	@Override
	public void deleteEmployee(String id) throws SQLException {
		String sql = "delete from employee where employee_id = ?";
		this.update(conn, sql, id);
	}

	@Override
	public void updateEmployee(Employee e) throws SQLException {
		String sql = "update employee set employee_name=?,id_card=?,age=?,gender=?,phone=?,email=?,"
				+ "address=?,hire_date=to_date(substr(?,1,10),'yyyy-mm-dd'),account_id=?,job_id=?,"
				+ "department_id=? where employee_id=?";
		Object[] args = {e.getEmployee_name(),e.getId_card(),e.getAge(),e.getGender(),
				e.getPhone(),e.getEmail(),e.getAddress(),e.getHire_date(),
				e.getAccount_id(),e.getJob_id(),e.getDepartment_id(),e.getEmployee_id()};
		this.update(conn, sql, args);
	}

	@Override
	public List<Employee> findAllEmployee() throws SQLException {
//		String sql = "select * from employee";
		String sql = "select e.employee_id,e.employee_num,e.employee_name,e.id_card,e.age,"
				+ "e.gender,e.phone,e.email,e.address,to_char(e.hire_date,'yyyy-mm-dd') as hire_date,"
				+ "e.account_id,e.job_id,e.department_id from employee e";
		//日期格式改变应该要使得数据库中查询出来的列与model中的数据一一对应
		return this.getForList(conn, sql);
	}

	@Override
	public List<Employee> getEmployeeByNameKeyword(String keyword)
			throws SQLException {
//		String sql = "select * from employee where employee_name like ?";
		String sql = "select e.employee_id,e.employee_num,e.employee_name,e.id_card,e.age,"
				+ "e.gender,e.phone,e.email,e.address,to_char(e.hire_date,'yyyy-mm-dd') as hire_date,"
				+ "e.account_id,e.job_id,e.department_id from employee e where employee_name like ?";
		return this.getForList(conn, sql, keyword);
	}

	@Override
	public Employee getEmployeeById(String id) throws SQLException {
//		String sql = "select * from employee where employee_id = ?";
		String sql = "select e.employee_id,e.employee_num,e.employee_name,e.id_card,e.age,"
				+ "e.gender,e.phone,e.email,e.address,to_char(e.hire_date,'yyyy-mm-dd') as hire_date,"
				+ "e.account_id,e.job_id,e.department_id from employee e where employee_id = ?";
		return this.get(conn, sql, id);
	}

	@Override
	public Object getEmployeeIdByAccountId(String accountId)
			throws SQLException {
		String sql = "select employee_id from employee where account_id = ?";
		return this.getForValue(conn, sql, accountId);
	}

	@Override
	public List<Employee> getEmployeeByDeptId(String deptId)
			throws SQLException {
//		String sql = "select * from employee where department_id = ?";
		String sql = "select e.employee_id,e.employee_num,e.employee_name,e.id_card,e.age,"
				+ "e.gender,e.phone,e.email,e.address,to_char(e.hire_date,'yyyy-mm-dd') as hire_date,"
				+ "e.account_id,e.job_id,e.department_id from employee e where department_id = ?";
		return this.getForList(conn, sql, deptId);
	}

	@Override
	public List<Employee> getAllEmployeeByPage(int page) throws SQLException {
//		String sql = "select * from(select rownum as num,emp.* from employee emp) where num between ? and ?";
		String sql = "select e.num,e.employee_id,e.employee_num,e.employee_name,e.id_card,e.age,"
				+ "e.gender,e.phone,e.email,e.address,to_char(e.hire_date,'yyyy-mm-dd') as hire_date,"
				+ "e.account_id,e.job_id,e.department_id from(select rownum as num,emp.* from employee emp)e "
				+ "where num between ? and ?";
		Object[] args = {(Integer.valueOf(page) - 1) * 10 + 1, (Integer.valueOf(page) - 1) * 10 + 10};
		return this.getForList(conn, sql, args);
	}

	@Override
	public Object getEmployeeCount() throws SQLException {
		String sql = "select count(*) from employee";
		return this.getForValue(conn, sql);
	}

	@Override
	public Object getEmployeeNumNextSeq() throws SQLException {
		String sql = "select lpad(empnumseq.nextval,4,'0') from dual";// 不足位数补充0
		return this.getForValue(conn, sql);
	}

	@Override
	public Object getSalarySumByDeptId(String deptId) throws SQLException {
		String sql = "select round(sum(j.base_salary*(1+j.commission_rate)),2) "
				+ "from employee e,job j,department d "
				+ "where e.job_id = j.job_id and e.department_id = d.department_id and d.department_id = ?";
		return this.getForValue(conn, sql, deptId);
	}

	@Override
	public Object getEmployeeSumByDeptId(String deptId) throws SQLException {
		String sql = "select count(*) from employee e,department d "
				+ "where e.department_id = d.department_id and d.department_id = ?";
		return this.getForValue(conn, sql, deptId);
	}
}
