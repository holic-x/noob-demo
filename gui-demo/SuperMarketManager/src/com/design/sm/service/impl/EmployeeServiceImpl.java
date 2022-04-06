package com.design.sm.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.design.sm.dao.AccountsDAO;
import com.design.sm.dao.DepartmentDAO;
import com.design.sm.dao.EmployeeDAO;
import com.design.sm.dao.JobDAO;
import com.design.sm.dao.impl.AccountsDAOImpl;
import com.design.sm.dao.impl.DepartmentDAOImpl;
import com.design.sm.dao.impl.EmployeeDAOImpl;
import com.design.sm.dao.impl.JobDAOImpl;
import com.design.sm.model.Department;
import com.design.sm.model.Employee;
import com.design.sm.model.Job;
import com.design.sm.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	private AccountsDAO accountsDAO = new AccountsDAOImpl();
	private DepartmentDAO departmentDAO = new DepartmentDAOImpl();
	private JobDAO jobDAO = new JobDAOImpl();

	@Override
	public Object getEmployeeName(String id) throws SQLException {
		return employeeDAO.getEmployeeName(id);
	}

	@Override
	public void addEmployee(Employee e) throws SQLException {
		employeeDAO.addEmployee(e);
	}

	@Override
	public void deleteEmployee(String id) throws SQLException {
		employeeDAO.deleteEmployee(id);
	}

	@Override
	public void updateEmployee(Employee e) throws SQLException {
		employeeDAO.updateEmployee(e);
	}

	@Override
	public List<Employee> findAllEmployee() throws SQLException {
		return employeeDAO.findAllEmployee();
	}

	@Override
	public List<Employee> getEmployeeByNameKeyword(String keyword)
			throws SQLException {
		return employeeDAO.getEmployeeByNameKeyword(keyword);
	}

	@Override
	public Employee getEmployeeById(String id) throws SQLException {
		return employeeDAO.getEmployeeById(id);
	}

	@Override
	public String getEmployeeIdByAccountId(String accountId)
			throws SQLException {
		return (String) employeeDAO.getEmployeeIdByAccountId(accountId);
	}

	@Override
	public List<Employee> getEmployeeByDeptId(String deptId)
			throws SQLException {
		return employeeDAO.getEmployeeByDeptId(deptId);
	}

	@Override
	public Vector<Vector> pack(List<Employee> list) throws SQLException {
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (Employee obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 18; i++) {
					temp.add(obj.getEmployee_id());// 员工id
					temp.add(obj.getEmployee_num());// 员工编号
					temp.add(obj.getEmployee_name());// 员工姓名
					temp.add(obj.getId_card());// 身份证号
					temp.add(obj.getAge());// 年龄
					temp.add(obj.getGender());// 性别
					temp.add(obj.getPhone());// 联系方式
					temp.add(obj.getEmail());// 电子邮箱
					temp.add(obj.getAddress());// 家庭住址
					temp.add(obj.getHire_date());// 入职日期
					temp.add(obj.getAccount_id());// 账号id
					temp.add(accountsDAO.getAccountsName(obj.getAccount_id()));// 账号名称
					temp.add(obj.getJob_id());// 职位id
					temp.add(jobDAO.getJobName(obj.getJob_id()));// 职位名称
					temp.add(obj.getDepartment_id());// 部门id
					temp.add(departmentDAO.getDepartmentName(obj
							.getDepartment_id()));// 部门名称
					// 由于需要查找员工的工资信息，则在此处直接通过职位id相应的获取该职位上的员工信息，直接进行显示
					// 获取基本工资、工作提成即可
					Job job = jobDAO.getJobById(obj.getJob_id());
					temp.add(job.getBase_salary());
					temp.add(job.getCommission_rate());
					// 隐藏：3 4 5 8 10 12 14
				}
				rows.add(temp);
			}
		}
		return rows;
	}

	@Override
	public List<Employee> getAllEmployeeByPage(int page) throws SQLException {
		return employeeDAO.getAllEmployeeByPage(page);
	}

	@Override
	public int getEmployeeMaxPage() throws SQLException {
		// 作分页处理 10个数据作为1页
		BigDecimal i = BigDecimal.valueOf(Integer.valueOf(employeeDAO
				.getEmployeeCount().toString()));
		BigDecimal i2 = BigDecimal.valueOf(10);
		return i.divide(i2).intValue() + 1;
	}

	@Override
	public int exportData(String[] empIds) {
		try {
			// 创建工作目录
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("data");
			// 创建数据行
			XSSFRow row = sheet.createRow((short) 0);
			XSSFCell cell = null;
			// 创建第一行表头数据
			cell = row.createCell((short) 0);
			cell.setCellValue("员工编号");
			cell = row.createCell((short) 1);
			cell.setCellValue("姓名");
			cell = row.createCell((short) 2);
			cell.setCellValue("身份证号");
			cell = row.createCell((short) 3);
			cell.setCellValue("联系方式");
			cell = row.createCell((short) 4);
			cell.setCellValue("电子邮箱");
			cell = row.createCell((short) 5);
			cell.setCellValue("家庭住址");
			cell = row.createCell((short) 6);
			cell.setCellValue("入职日期");
			cell = row.createCell((short) 7);
			cell.setCellValue("使用账号");
			cell = row.createCell((short) 8);
			cell.setCellValue("职位");
			cell = row.createCell((short) 9);
			cell.setCellValue("部门");
			/**
			 * 默认是将全部数据导出
			 * 可以通过用户选择相应的数据进行导出
			 */
			List<Employee> list = null;
			if(empIds!=null){
				list = this.findAllEmployeeByIds(empIds);
			}else{
				list = employeeDAO.findAllEmployee();
			}
			for (int i = 0; i < list.size(); i++) {
				Employee emp = list.get(i);
				row = sheet.createRow(i + 1);
				cell = row.createCell(0);
				cell.setCellValue(emp.getEmployee_num());
				cell = row.createCell(1);
				cell.setCellValue(emp.getEmployee_name());
				cell = row.createCell(2);
				cell.setCellValue(emp.getId_card());
				cell = row.createCell(3);
				cell.setCellValue(emp.getPhone());
				cell = row.createCell(4);
				cell.setCellValue(emp.getEmail());
				cell = row.createCell(5);
				cell.setCellValue(emp.getAddress());
				cell = row.createCell(6);
				cell.setCellValue(emp.getHire_date());
				cell = row.createCell(7);
				cell.setCellValue(accountsDAO.getAccountsName(emp.getAccount_id()).toString());
				cell = row.createCell(8);
				cell.setCellValue(jobDAO.getJobName(emp.getJob_id()).toString());
				cell = row.createCell(9);
				cell.setCellValue(departmentDAO.getDepartmentName(emp.getDepartment_id()).toString());
			}
		//弹出文件选择框  
	    JFileChooser chooser = new JFileChooser();  
	    //后缀名过滤器  
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(  
	            "表格文件(*.xlsx)", "xlsx");  
	    chooser.setFileFilter(filter);  
	    //下面的方法将阻塞，直到【用户按下保存按钮且“文件名”文本框不为空】或【用户按下取消按钮】  
	    int option = chooser.showSaveDialog(null);  
	    if(option==JFileChooser.APPROVE_OPTION){    //假如用户选择了保存  
	        File file = chooser.getSelectedFile();  
	        String fname = chooser.getName(file);   //从文件名输入框中获取文件名  
	        //假如用户填写的文件名不带我们制定的后缀名，那么我们给它添上后缀  
	        if(fname.indexOf(".xlsx")==-1){  
	            file=new File(chooser.getCurrentDirectory(),fname+".xlsx");  
	        }  
	        try {  
	            FileOutputStream FOut = new FileOutputStream(file);
	            workbook.write(FOut);
	            FOut.flush();
	            FOut.close();
				workbook.close();
				return 1;
	        } catch (IOException e) {  
	            System.err.println("IO异常");  
	            e.printStackTrace();  
	        }     
	    }  
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	@Override
	public List<Employee> findAllEmployeeByIds(String[] ids)
			throws SQLException {
		// 循环遍历id信息
		List<Employee> list = new ArrayList<Employee>();
		for (String id : ids) {
			Employee emp = employeeDAO.getEmployeeById(id);
			list.add(emp);
		}
		return list;
	}

	@Override
	public String getEmployeeNumNextSeq() throws SQLException {
		return (String) employeeDAO.getEmployeeNumNextSeq();
	}

	@Override
	public int exportSalaryData(String[] empIds) {
		try {
			// 创建工作目录
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("data");
			// 创建数据行
			XSSFRow row = sheet.createRow((short) 0);
			XSSFCell cell = null;
			// 创建第一行表头数据
			cell = row.createCell((short) 0);
			cell.setCellValue("员工编号");
			cell = row.createCell((short) 1);
			cell.setCellValue("姓名");
			cell = row.createCell((short) 2);
			cell.setCellValue("身份证号");
			cell = row.createCell((short) 3);
			cell.setCellValue("联系方式");
			cell = row.createCell((short) 4);
			cell.setCellValue("在职职位");
			cell = row.createCell((short) 5);
			cell.setCellValue("隶属部门");
			cell = row.createCell((short) 6);
			cell.setCellValue("基本工资");
			cell = row.createCell((short) 7);
			cell.setCellValue("工作提成");
			cell = row.createCell((short) 8);
			cell.setCellValue("实际工资");
			/**
			 * 默认是将全部数据导出
			 * 可以通过用户选择相应的数据进行导出
			 */
			List<Employee> list = null;
			if(empIds!=null){
				list = this.findAllEmployeeByIds(empIds);
			}else{
				list = employeeDAO.findAllEmployee();
			}
			for (int i = 0; i < list.size(); i++) {
				Employee emp = list.get(i);
				row = sheet.createRow(i + 1);
				cell = row.createCell(0);
				cell.setCellValue(emp.getEmployee_num());
				cell = row.createCell(1);
				cell.setCellValue(emp.getEmployee_name());
				cell = row.createCell(2);
				cell.setCellValue(emp.getId_card());
				cell = row.createCell(3);
				cell.setCellValue(emp.getPhone());
				cell = row.createCell(4);
				cell.setCellValue(emp.getEmail());
				cell = row.createCell(5);
				cell.setCellValue(jobDAO.getJobName(emp.getJob_id()).toString());
				cell = row.createCell(6);
				cell.setCellValue(departmentDAO.getDepartmentName(emp.getDepartment_id()).toString());
				cell = row.createCell(7);
				Job job = jobDAO.getJobById(emp.getJob_id());
				cell.setCellValue(job.getBase_salary());
				cell = row.createCell(8);
				cell.setCellValue(job.getCommission_rate());
				cell = row.createCell(9);
				// 计算工资
				double count = ((int)(job.getBase_salary()*(1+job.getCommission_rate())*100))/100;
				cell.setCellValue(count);
			}
		//弹出文件选择框  
	    JFileChooser chooser = new JFileChooser();  
	    //后缀名过滤器  
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(  
	            "表格文件(*.xlsx)", "xlsx");  
	    chooser.setFileFilter(filter);  
	    //下面的方法将阻塞，直到【用户按下保存按钮且“文件名”文本框不为空】或【用户按下取消按钮】  
	    int option = chooser.showSaveDialog(null);  
	    if(option==JFileChooser.APPROVE_OPTION){    //假如用户选择了保存  
	        File file = chooser.getSelectedFile();  
	        String fname = chooser.getName(file);   //从文件名输入框中获取文件名  
	        //假如用户填写的文件名不带我们制定的后缀名，那么我们给它添上后缀  
	        if(fname.indexOf(".xlsx")==-1){  
	            file=new File(chooser.getCurrentDirectory(),fname+".xlsx");  
	        }  
	        try {  
	            FileOutputStream FOut = new FileOutputStream(file);
	            workbook.write(FOut);
	            FOut.flush();
	            FOut.close();
				workbook.close();
				return 1;
	        } catch (IOException e) {  
	            System.err.println("IO异常");  
	            e.printStackTrace();  
	        }     
	    }  
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	// 工资结算统计：获取每个部门的总人数、工资统计
	@Override
	public Map<String, Double> getSalarySumByDeptId() throws SQLException {
		Map<String, Double> map = new HashMap<String, Double>();
		List<Department> list_dept = departmentDAO.findAllDepartment();
		for(Department d : list_dept){
			String deptId = d.getDepartment_id();
			Double sum = Double.valueOf(employeeDAO.getSalarySumByDeptId(deptId).toString());
			map.put(deptId, sum);
		}
		return map;
	}

	@Override
	public Map<String, Integer> getEmployeeSumByDeptId() throws SQLException {
		Map<String, Integer> map = new HashMap<String, Integer>();
		List<Department> list_dept = departmentDAO.findAllDepartment();
		for(Department d : list_dept){
			String deptId = d.getDepartment_id();
			int sum = Integer.valueOf(employeeDAO.getEmployeeSumByDeptId(deptId).toString());
			map.put(deptId, sum);
		}
		return map;
	}

	@Override
	public int exportBalanceData(JTable table) {
		try {
			// 创建工作目录
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("data");
			// 创建数据行
			XSSFRow row = sheet.createRow((short) 0);
			XSSFCell cell = null;
			// 创建第一行表头数据
			cell = row.createCell((short) 0);
			cell.setCellValue("部门名称");
			cell = row.createCell((short) 1);
			cell.setCellValue("部门人数");
			cell = row.createCell((short) 2);
			cell.setCellValue("总工资");
			cell = row.createCell((short) 3);
			cell.setCellValue("平均工资");
			/**
			 * 默认是将全部数据导出
			 */
			for (int i = 0; i < table.getRowCount(); i++) {
				row = sheet.createRow(i + 1);
				cell = row.createCell(0);
				cell.setCellValue(table.getValueAt(i, 0).toString());
				cell = row.createCell(1);
				cell.setCellValue(table.getValueAt(i, 1).toString());
				cell = row.createCell(2);
				cell.setCellValue(table.getValueAt(i, 2).toString());
				cell = row.createCell(3);
				cell.setCellValue(table.getValueAt(i, 3).toString());
			}
		//弹出文件选择框  
	    JFileChooser chooser = new JFileChooser();  
	    //后缀名过滤器  
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(  
	            "表格文件(*.xlsx)", "xlsx");  
	    chooser.setFileFilter(filter);  
	    //下面的方法将阻塞，直到【用户按下保存按钮且“文件名”文本框不为空】或【用户按下取消按钮】  
	    int option = chooser.showSaveDialog(null);  
	    if(option==JFileChooser.APPROVE_OPTION){    //假如用户选择了保存  
	        File file = chooser.getSelectedFile();  
	        String fname = chooser.getName(file);   //从文件名输入框中获取文件名  
	        //假如用户填写的文件名不带我们制定的后缀名，那么我们给它添上后缀  
	        if(fname.indexOf(".xlsx")==-1){  
	            file=new File(chooser.getCurrentDirectory(),fname+".xlsx");  
	        }  
	        try {  
	            FileOutputStream FOut = new FileOutputStream(file);
	            workbook.write(FOut);
	            FOut.flush();
	            FOut.close();
				workbook.close();
				return 1;
	        } catch (IOException e) {  
	            System.err.println("IO异常");  
	            e.printStackTrace();  
	        }     
	    }  
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}
}
