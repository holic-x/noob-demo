package com.design.sm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.dao.VendorContactDAO;
import com.design.sm.model.VendorContact;

public class VendorContactDAOImpl extends BaseDAOImpl<VendorContact> implements VendorContactDAO{

	@Override
	public Object getVendorContactName(String id) throws SQLException {
		String sql = "select contact_name from vendor_contact where contact_id=?";
		return this.getForValue(conn, sql, id);
	}

	@Override
	public List<VendorContact> findAllVendorContact() throws SQLException {
		String sql = "select * from vendor_contact";
		return this.getForList(conn, sql);
	}

	@Override
	public void addVendorContact(VendorContact vc) throws SQLException {
		String sql = "insert into vendor_contact values(?,?,?,?,?,?)";
		Object[] args = {vc.getContact_id(),vc.getContact_name(),vc.getContact_phone(),
				vc.getContact_email(),vc.getVendor_id(),vc.getOwner_flag()};
		this.update(conn, sql, args);
	}

	@Override
	public void deleteVendorContact(String id) throws SQLException {
		String sql ="delete from vendor_contact where contact_id=?";
		this.update(conn, sql, id);
		
	}

	@Override
	public void updateVendorContact(VendorContact vc) throws SQLException {
		String sql = "update vendor_contact set contact_name=?,"
				+ "contact_phone=?,contact_email=?,vendor_id=?,owner_flag=? where contact_id=?";
		Object[] args = {vc.getContact_name(),vc.getContact_phone(),vc.getContact_email(),
				vc.getVendor_id(),vc.getOwner_flag(),vc.getContact_id()};
		this.update(conn, sql, args);
	}

	@Override
	public List<VendorContact> getVendorContactByVendorId(String vendorId)
			throws SQLException {
		String sql = "select * from vendor_contact where vendor_id=?";
		return this.getForList(conn, sql, vendorId);
	}

	@Override
	public VendorContact getVendorContactByContactId(String contactId) throws SQLException {
		String sql = "select * from vendor_contact where contact_id = ?";
		return this.get(conn, sql, contactId);
	}
}
