package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.Vendor;

public interface VendorDAO {
	//根据供应商id获取供应商名称
	public Object getVendorName(String id)throws SQLException;
	
	//获取所有供应商信息
	public List<Vendor> findAllVendor()throws SQLException;
	
	//新增供应商信息
	public void addVendor(Vendor c)throws SQLException;
	
	//修改供应商信息
	public void updateVendor(Vendor c)throws SQLException;
	
	//根据id删除供应商信息
	public void deleteVendor(String id)throws SQLException;
	
	//根据供应商名称关键字获取供应商信息
	public List<Vendor> getVendorByNameKeyword(String keyword)throws SQLException;
	
	//根据供应商id获取供应商信息
	public Vendor getVendorById(String id)throws SQLException;
	
}
