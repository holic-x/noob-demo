package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.SaleTemp;

public interface SaleTempService {
	// 增加临时记录
	public void addSaleTemp(SaleTemp t) throws SQLException;

	// 修改临时记录
	public void update(SaleTemp t) throws SQLException;

	// 删除临时记录
	public void deleteSaleTemp(String id) throws SQLException;

	// 获取所有的临时记录
	public List<SaleTemp> findAllSaleTempList() throws SQLException;

	// 将查找到的记录封装为Vector<Vector>类型
	public Vector<Vector> pack(List<SaleTemp> list) throws SQLException;

	// 通过提供的产品id数组获取临时相应的记录信息
	public List<SaleTemp> getSaleTempListByProductId(String[] ids)
			throws SQLException;

	// 根据商品id获取临时的记录信息（单个）
	public SaleTemp getSaleTempByProductId(String id) throws SQLException;

	// 统计当前列表购买的所有产品的总额
	public double getAllAmount() throws SQLException;

	// 清空当前临时列表的所有记录
	public void truncateAllSaleTemp() throws SQLException;

}
