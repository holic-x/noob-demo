package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.Vendor;

public interface VendorService {
	// 根据供应商id获取供应商名称
	public Object getVendorName(String id) throws SQLException;

	// 获取所有供应商信息列表
	public List<Vendor> findAllVendorList() throws SQLException;

	// 获取所有供应商记录
	public Vector<Vector> findAllVendorVector() throws SQLException;

	// 新增供应商信息
	public void addVendor(Vendor v) throws SQLException;

	// 修改供应商信息
	public void updateVendor(Vendor v) throws SQLException;

	// 根据id删除供应商信息
	public void deleteVendor(String id) throws SQLException;

	// 根据供应商名称关键字获取供应商信息
	public Vector<Vector> getVendorByNameKeyword(String keyword)
			throws SQLException;
	
	// 根据提供的供应商id数组获取相应的供应商信息
	public List<Vendor> findAllVendorByIds(String[] vendorIds)throws SQLException;
	/**
	 * 自定义导出数据
	 */
	public int exportData(String[] vendorIds)throws SQLException;
}
