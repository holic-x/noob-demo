package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.Vendor;
import com.design.sm.model.VendorContact;

public interface VendorContactService {

	// 根据供应商联系人id获取供应商联系人姓名
	public Object getVendorContactName(String id) throws SQLException;

	// 获取所有的供应商联系人信息列表
	public List<VendorContact> findAllVendorContactList() throws SQLException;

	// 获取所有的供应商联系人信息记录
	public Vector<Vector> findAllVendorContactVector() throws SQLException;

	// 新增供应商联系人信息
	public void addVendorContact(VendorContact vc) throws SQLException;

	// 根据id删除供应商联系人信息
	public void deleteVendorContact(String id) throws SQLException;

	// 根据id修改供应商联系人信息
	public void updateVendorContact(VendorContact vc) throws SQLException;

	// 根据供应商id获取相应的联系人列表
	public List<VendorContact> getVendorContactByVendorId(String vendorId)
			throws SQLException;

	// 封装List<Vendor>信息为Vector<Vector>
	public Vector<Vector> pack(List<VendorContact> list) throws SQLException;

	// 根据供应商联系人id获取相应的联系人信息
	public VendorContact getVendorContactByContactId(String contactId) throws SQLException;
}
