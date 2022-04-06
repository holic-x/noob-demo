package com.design.sm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.dao.CustomerDAO;
import com.design.sm.model.Customer;

public class CustomerDAOImpl extends BaseDAOImpl<Customer> implements CustomerDAO{

	@Override
	public Object getCustomerName(String id) throws SQLException {
		String sql = "select customer_name from customer where customer_id=?";
		return this.getForValue(conn, sql, id);
	}

	@Override
	public List<Customer> findAllCustomer() throws SQLException {
		String sql = "select * from customer";
		return this.getForList(conn, sql);
	}

	@Override
	public void addCustomer(Customer c) throws SQLException {
		String sql = "insert into customer values(?,?,?,?,?,?,?,?)";
		Object[] args = {c.getCustomer_id(),c.getCustomer_name(),c.getCustomer_address(),
				c.getCustomer_phone(),c.getCustomer_email(),c.getIntegrate(),c.getBalance(),c.getClass_id()};
		this.update(conn, sql, args);
	}

	@Override
	public void updateCustomer(Customer c) throws SQLException {
		String sql = "update customer set customer_name=?,customer_address=?,customer_phone=?,customer_email=?,"
				+ "integrate=?,balance=?,class_id=? where customer_id=?";
		Object[] args = {c.getCustomer_name(),c.getCustomer_address(),c.getCustomer_phone(),
				c.getCustomer_email(),c.getIntegrate(),c.getBalance(),c.getClass_id(),c.getCustomer_id()};
		this.update(conn, sql, args);
	}

	@Override
	public void deleteCustomer(String id) throws SQLException {
		String sql = "delete from customer where customer_id=?";
		this.update(conn, sql, id);
	}

	@Override
	public List<Customer> getCustomerByKeyword(String keyword)
			throws SQLException {
		String sql = "select * from customer where customer_name like ? or customer_phone like ?";
		Object[] args = {keyword,keyword};
		return this.getForList(conn, sql, args);
	}

	@Override
	public Customer getCustomerById(String id) throws SQLException {
		String sql = "select * from customer where customer_id=?";
		return this.get(conn, sql, id);
	}

	@Override
	public Customer getCustomerByPhone(String phone) throws SQLException {
		String sql = "select * from customer where customer_phone = ?";
		return this.get(conn, sql, phone);
	}
}
