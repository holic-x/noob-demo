package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.StockOrder;
import com.design.sm.model.StockOrder;

public interface StockOrderDAO extends BaseDAO<StockOrder> {

	// 增加订单明细记录
	public void addStockOrder(StockOrder t) throws SQLException;

//	// 修改订单明细记录
//	public void update(StockOrder t) throws SQLException;
//
//	// 删除订单明细记录
//	public void deleteStockOrder(String id) throws SQLException;
//
//	// 获取所有的订单明细记录
//	public List<StockOrder> findAllStockOrderList() throws SQLException;

	// 通过订单主表获取订单明细记录信息
	public List<StockOrder> getStockOrderBySMId(String smId) throws SQLException;

}
