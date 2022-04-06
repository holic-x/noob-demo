package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.SaleMaster;
import com.design.sm.model.StockMaster;

public interface SaleMasterService {

	// 增加销售记录
	public void addSaleMaster(SaleMaster sm) throws SQLException;

	// 修改销售记录
	public void updateSaleMaster(SaleMaster sm) throws SQLException;

	// 根据主表id彻底删除销售记录
	public void deleteSaleMaster(String id) throws SQLException;

	// 根据主表id将记录放入回收站（修改删除标识）
	public void joinRecycleBin(String id) throws SQLException;

	// 根据主表id将记录从回收站中撤回（修改删除标识）
	public void revokeRecycleBin(String id) throws SQLException;

	// 查询所有的销售记录列表
	public List<SaleMaster> findAllSaleList() throws SQLException;

	// 获取当前订单编号的序列
	public Object getSaleNextSeq() throws SQLException;

	// 根据订单id获取订单编号
	public Object getSOOrderNumById(String id) throws SQLException;

	// 根据商品的订单处理时间以及相应的订单处理状态获取订单信息
	public List<SaleMaster> findAllStockListByTimeUnionState(String start,
			String end, int state) throws SQLException;

	// 获取商品销售单、退货单的回收站的订单信息（删除标识为1）
	public List<SaleMaster> findAllSaleListRecycleBin() throws SQLException;

	// 将List<SaleMaster>信息封装为Vector<Vector>记录
	public Vector<Vector> pack(List<SaleMaster> list) throws SQLException;

}
