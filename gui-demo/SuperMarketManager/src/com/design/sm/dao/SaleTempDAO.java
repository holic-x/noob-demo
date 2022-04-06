package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.SaleTemp;

public interface SaleTempDAO extends BaseDAO<SaleTemp>{
	
	// 增加临时记录
	public void addSaleTemp(SaleTemp t)throws SQLException;
	
	// 修改临时记录
	public void update(SaleTemp t)throws SQLException;
	
	// 删除临时记录
	public void deleteSaleTemp(String id)throws SQLException;
	
	// 获取所有的临时记录
	public List<SaleTemp> findAllSaleTempList()throws SQLException;
	
	// 通过产品id获取临时记录信息
	public SaleTemp getSaleTempByProductId(String id)throws SQLException;
	
	// 统计当前列表购买的所有产品的总额
	public Object getAllAmount()throws SQLException;
	
	// 清空当前临时列表的所有记录
	public void truncateAllSaleTemp()throws SQLException;

}
