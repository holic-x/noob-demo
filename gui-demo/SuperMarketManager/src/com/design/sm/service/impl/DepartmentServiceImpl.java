package com.design.sm.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.dao.DepartmentDAO;
import com.design.sm.dao.EmployeeDAO;
import com.design.sm.dao.impl.DepartmentDAOImpl;
import com.design.sm.dao.impl.EmployeeDAOImpl;
import com.design.sm.model.Department;
import com.design.sm.model.Product;
import com.design.sm.model.Temp;
import com.design.sm.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService{

	private DepartmentDAO departmentDAO = new DepartmentDAOImpl();
	private EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	@Override
	public Object getDepartmentName(String id) throws SQLException {
		return departmentDAO.getDepartmentName(id);
	}

	@Override
	public List<Department> getDepartmentByNameKeyword(String keyword)
			throws SQLException {
		return departmentDAO.getDepartmentByNameKeyword(keyword);
	}

	@Override
	public List<Department> findAllDepartment() throws SQLException {
		return departmentDAO.findAllDepartment();
	}

	@Override
	public void addDepartment(Department d) throws SQLException {
		departmentDAO.addDepartment(d);
	}

	@Override
	public void updateDepartment(Department d) throws SQLException {
		departmentDAO.updateDepartment(d);
	}

	@Override
	public void deleteDepartment(String id) throws SQLException {
		departmentDAO.deleteDepartment(id);
	}

	@Override
	public Vector<Vector> pack(List<Department> list) throws SQLException {
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (Department obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 5; i++) {
					temp.add(obj.getDepartment_id());//部门id
					temp.add(departmentDAO.getDepartmentName(obj.getDepartment_id()));//部门名称
					// 在显示的时候判断部门主管是否为空，如果为空则不需要查找部门主管的姓名直接设置为空
					temp.add(obj.getManager_id());//部门主管id
					if(obj.getManager_id().equals("空")){
						temp.add("空");//部门主管姓名为空
					}else{
						temp.add(employeeDAO.getEmployeeName(obj.getManager_id()));//部门主管姓名
					}
					temp.add(obj.getDepartment_descr());//部门描述
				}
				rows.add(temp);
			}
		}
		return rows;
	}
}
