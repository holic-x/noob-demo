package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.SaleOrder;

public interface SaleOrderService {
	// 增加订单明细记录
	public void addSaleOrder(SaleOrder t) throws SQLException;

	// 通过订单主表获取订单明细记录信息
	public List<SaleOrder> getSaleOrderBySMId(String smId) throws SQLException;

	// 封装List<StockOrder>信息
	public Vector<Vector> pack(List<SaleOrder> list) throws SQLException;
}
