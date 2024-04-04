package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.SaleMaster;
import com.design.sm.model.StockMaster;

public interface StockMasterService {

	// 增加出库/入库记录
	public void addStockMaster(StockMaster sm) throws SQLException;

	// 修改出库入库记录
	public void updateStockMaster(StockMaster sm) throws SQLException;

	// 根据主表id彻底删除出库入库记录
	public void deleteStockMaster(String id) throws SQLException;

	// 根据主表id将记录放入回收站（修改删除标识）
	public void joinRecycleBin(String id) throws SQLException;

	// 根据主表id将记录从回收站中撤回（修改删除标识）
	public void revokeRecycleBin(String id) throws SQLException;

	// 查询所有的入库记录列表
	public List<StockMaster> findAllStockInList() throws SQLException;

	// 查询所有的出库记录列表（商品退回给供应商的出库操作）
	public List<StockMaster> finfAllStockOutList() throws SQLException;

	// 根据主表id完成出库（退货）操作
	public void rejectProduct(String id) throws SQLException;

	// 根据主表id取消出库（退货）操作
	public void cancelRejectProduct(String id) throws SQLException;

	// 根据主表id修改订单处理状态(订单提交)
	public void commitStockMaster(String id) throws SQLException;

	// 根据主表id修改订单处理状态(订单审批不通过)
	public void cancelStockMaster(String id) throws SQLException;

	// 根据主表id修改订单处理状态(订单审批通过)
	public void passStockMaster(String id) throws SQLException;

	// 将List<StockMaster>信息封装为Vector<Vector>记录
	public Vector<Vector> pack(List<StockMaster> list) throws SQLException;

	// 获取当前订单编号的序列
	public String getStockInNextSeq() throws SQLException;

	// 根据订单id获取订单编号
	public String getSMOrderNumById(String id) throws SQLException;

	// 根据订单主表的出入库和订单处理状态查找记录
	public List<StockMaster> findAllStockListByState(int sign, int state)
			throws SQLException;

	// 根据商品的订单处理时间以及相应的订单处理状态获取订单信息
	public List<StockMaster> findAllStockListByTimeUnionState(int sign,
			String start, String end, int state) throws SQLException;

	// 获取商品入库\出库单的回收站的订单信息（删除标识为1）
	public List<StockMaster> findAllStockListRecycleBin(int sign)throws SQLException;
	
	// 根据订单编号获取订单主表信息
	public StockMaster getStockMasterByOrderNum(String orderNum)throws SQLException;
	
}
