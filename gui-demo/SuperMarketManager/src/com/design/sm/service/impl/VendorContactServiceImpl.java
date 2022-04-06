package com.design.sm.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.dao.VendorContactDAO;
import com.design.sm.dao.VendorDAO;
import com.design.sm.dao.impl.VendorContactDAOImpl;
import com.design.sm.dao.impl.VendorDAOImpl;
import com.design.sm.model.Vendor;
import com.design.sm.model.VendorContact;
import com.design.sm.service.VendorContactService;

public class VendorContactServiceImpl implements VendorContactService {

	private VendorContactDAO dao = new VendorContactDAOImpl();
	private VendorDAO vendorDao = new VendorDAOImpl();

	@Override
	public Object getVendorContactName(String id) throws SQLException {
		return dao.getVendorContactName(id);
	}

	@Override
	public List<VendorContact> findAllVendorContactList() throws SQLException {
		return dao.findAllVendorContact();
	}

	@Override
	public Vector<Vector> findAllVendorContactVector() throws SQLException {
		List<VendorContact> list = dao.findAllVendorContact();
		return this.pack(list);
	}

	@Override
	public void addVendorContact(VendorContact vc) throws SQLException {
		dao.addVendorContact(vc);
	}

	@Override
	public void deleteVendorContact(String id) throws SQLException {
		dao.deleteVendorContact(id);
	}

	@Override
	public void updateVendorContact(VendorContact vc) throws SQLException {
		dao.updateVendorContact(vc);
	}

	@Override
	public List<VendorContact> getVendorContactByVendorId(String vendorId)
			throws SQLException {
		return dao.getVendorContactByVendorId(vendorId);
	}

	/**
	 * 根据身份标识获取身份信息
	 */
	public String getContactIdentity(int owner_flag) {
		String identity = "";
		if (owner_flag == 0) {
			identity = "普通联络员";
		} else if (owner_flag == 1) {
			identity = "负责人";
		}
		return identity;
	}

	@Override
	public Vector<Vector> pack(List<VendorContact> list) throws SQLException {
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (VendorContact obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 6; i++) {
					/**
					 * 显示 contact_id、contact_name、phone、email、 vendor_id
					 */
					temp.add(obj.getContact_id());// 供应商联系人id
					temp.add(obj.getContact_name());// 供应商联系人名称
					temp.add(obj.getContact_phone());// 供应商联系人联系方式
					temp.add(obj.getContact_email());// 邮箱
					temp.add(obj.getVendor_id());// 关联的供应商id
					temp.add(vendorDao.getVendorName(obj.getVendor_id()));// 关联的供应商名称
					temp.add(obj.getOwner_flag());// 身份标识
					temp.add(this.getContactIdentity(obj.getOwner_flag()));// 身份
				}
				rows.add(temp);
			}
		}
		return rows;
	}

	@Override
	public VendorContact getVendorContactByContactId(String contactId) throws SQLException {
		return  dao.getVendorContactByContactId(contactId);
	}
}
