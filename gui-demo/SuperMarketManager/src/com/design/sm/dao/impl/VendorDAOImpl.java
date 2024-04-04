package com.design.sm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.dao.VendorDAO;
import com.design.sm.model.Vendor;

public class VendorDAOImpl extends BaseDAOImpl<Vendor> implements VendorDAO{

	@Override
	public Object getVendorName(String id) throws SQLException {
		String sql = "select vendor_name from vendor where vendor_id=?";
		return this.getForValue(conn, sql, id);
	}

	@Override
	public List<Vendor> findAllVendor() throws SQLException {
		String sql = "select * from vendor";
		return this.getForList(conn, sql);
	}

	@Override
	public void addVendor(Vendor v) throws SQLException {
		String sql = "insert into vendor values(?,?,?,?,?,?)";
		Object[] args = {v.getVendor_id(),v.getVendor_name(),v.getVendor_phone(),
				v.getVendor_email(),v.getVendor_fax(),v.getVendor_address()};
		this.update(conn, sql, args);
	}

	@Override
	public void updateVendor(Vendor v) throws SQLException {
		String sql = "update vendor set vendor_name=?,vendor_phone=?,"
				+ "vendor_email=?,vendor_fax=?,vendor_address=? where vendor_id=?";
		Object[] args = {v.getVendor_name(),v.getVendor_phone(),v.getVendor_email(),
				v.getVendor_fax(),v.getVendor_address(),v.getVendor_id()};
		this.update(conn, sql, args);
	}

	@Override
	public void deleteVendor(String id) throws SQLException {
		String sql = "delete from vendor where vendor_id=?";
		this.update(conn, sql, id);
	}

	@Override
	public List<Vendor> getVendorByNameKeyword(String keyword)
			throws SQLException {
		String sql = "select * from vendor where vendor_name like ?";
		return this.getForList(conn, sql, keyword);
	}

	@Override
	public Vendor getVendorById(String id) throws SQLException {
		String sql = "select * from vendor where vendor_id=?";
		return this.get(conn, sql, id);
	}
}
