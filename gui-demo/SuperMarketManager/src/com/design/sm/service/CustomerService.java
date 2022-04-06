package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.Customer;

public interface CustomerService {

	// 根据顾客id获取顾客名称
	public Object getCustomerName(String id) throws SQLException;

	// 获取所有顾客信息
	public List<Customer> findAllCustomer() throws SQLException;

	// 新增顾客信息
	public void addCustomer(Customer c) throws SQLException;

	// 修改顾客信息
	public void updateCustomer(Customer c) throws SQLException;

	// 根据id删除顾客信息
	public void deleteCustomer(String id) throws SQLException;

	// 根据顾客名称或联系方式关键字获取顾客信息
	public List<Customer> getCustomerByKeyword(String keyword)
			throws SQLException;

	// 根据顾客id获取顾客信息
	public Customer getCustomerById(String id) throws SQLException;

	// 将List<Customer>数据封装为Vector<Vector>
	public Vector<Vector> pack(List<Customer> list) throws SQLException;
	
	// 根据顾客联系方式获取顾客信息
	public Customer getCustomerByPhone(String phone)throws SQLException;
}
