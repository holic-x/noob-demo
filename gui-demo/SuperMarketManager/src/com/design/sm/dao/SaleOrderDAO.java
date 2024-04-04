package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.SaleOrder;

public interface SaleOrderDAO {
	// 增加订单明细记录
	public void addSaleOrder(SaleOrder t) throws SQLException;

	// 通过订单主表获取订单明细记录信息
	public List<SaleOrder> getSaleOrderBySMId(String smId)
			throws SQLException;
}
