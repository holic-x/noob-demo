package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.VendorContact;

public interface VendorContactDAO extends BaseDAO<VendorContact>{
	
	//根据供应商联系人id获取供应商联系人姓名
	public Object getVendorContactName(String id)throws SQLException;
	
	//获取所有的供应商联系人信息
	public List<VendorContact> findAllVendorContact() throws SQLException;
	
	//新增供应商联系人信息
	public void addVendorContact(VendorContact vc)throws SQLException;
	
	//根据id删除供应商联系人信息
	public void deleteVendorContact(String id)throws SQLException;
	
	//根据id修改供应商联系人信息
	public void updateVendorContact(VendorContact vc)throws SQLException;
	
	//根据供应商id查找相应的联系人列表
	public List<VendorContact> getVendorContactByVendorId(String vendorId)
			throws SQLException;
	
	//根据供应商联系人id获取相应的联系人信息
	public VendorContact getVendorContactByContactId(String contactId)throws SQLException;
}
