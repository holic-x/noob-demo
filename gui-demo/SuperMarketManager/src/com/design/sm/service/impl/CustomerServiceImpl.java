package com.design.sm.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.dao.ConsumeClassDAO;
import com.design.sm.dao.CustomerDAO;
import com.design.sm.dao.impl.ConsumeClassDAOImpl;
import com.design.sm.dao.impl.CustomerDAOImpl;
import com.design.sm.model.Category;
import com.design.sm.model.Customer;
import com.design.sm.service.CustomerService;

public class CustomerServiceImpl implements CustomerService{

	private CustomerDAO customerDAO = new CustomerDAOImpl();
	private ConsumeClassDAO consumeClassDAO = new ConsumeClassDAOImpl();
	
	@Override
	public Object getCustomerName(String id) throws SQLException {
		return customerDAO.getCustomerName(id);
	}

	@Override
	public List<Customer> findAllCustomer() throws SQLException {
		return customerDAO.findAllCustomer();
	}

	@Override
	public void addCustomer(Customer c) throws SQLException {
		customerDAO.addCustomer(c);
	}

	@Override
	public void updateCustomer(Customer c) throws SQLException {
		customerDAO.updateCustomer(c);
	}

	@Override
	public void deleteCustomer(String id) throws SQLException {
		customerDAO.deleteCustomer(id);
	}

	@Override
	public List<Customer> getCustomerByKeyword(String keyword)
			throws SQLException {
		return customerDAO.getCustomerByKeyword(keyword);
	}

	@Override
	public Customer getCustomerById(String id) throws SQLException {
		return customerDAO.getCustomerById(id);
	}

	@Override
	public Vector<Vector> pack(List<Customer> list) throws SQLException {
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (Customer obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 9; i++) {
					temp.add(obj.getCustomer_id());//顾客id
					temp.add(obj.getCustomer_name());//顾客名称
					temp.add(obj.getCustomer_address());//顾客地址
					temp.add(obj.getCustomer_phone());//联系方式
					temp.add(obj.getCustomer_email());//电子邮箱
					temp.add(obj.getIntegrate());//积分
					temp.add(obj.getBalance());//余额
					temp.add(obj.getClass_id());//消费等级
					temp.add(consumeClassDAO.getConsumeClassNameById(obj.getClass_id()));//等级名称
				}
				rows.add(temp);
			}
		}
		return rows;
	}

	@Override
	public Customer getCustomerByPhone(String phone) throws SQLException {
		return customerDAO.getCustomerByPhone(phone);
	}
}
