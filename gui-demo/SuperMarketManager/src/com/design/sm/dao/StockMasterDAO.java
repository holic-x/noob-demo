package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.SaleMaster;
import com.design.sm.model.StockMaster;

public interface StockMasterDAO extends BaseDAO<StockMaster>{
	
	// 增加出库/入库记录
	public void addStockMaster(StockMaster sm)throws SQLException;
	
	// 修改出库入库记录
	public void updateStockMaster(StockMaster sm)throws SQLException;
	
	// 根据主表id彻底删除出库入库记录
	public void deleteStockMaster(String id)throws SQLException;
	
	// 根据主表id将记录放入回收站或将记录从回收站中撤回（修改删除标识）
	public void setDeleteFlag(String id,int delete_flag)throws SQLException; 
	
	// 查询所有的出库、入库记录列表（根据sign标识进行筛选sign=0入库  sign=1出库）
	public List<StockMaster> findAllStockList(int sign)throws SQLException;
	
	// 根据主表id完成出库（退货）、取消出库（退货）操作(sign=0入库  sign=1出库)
	public void setSign(String id,int sign)throws SQLException;
	
	// 根据主表id修改订单处理状态(修改state：0表示订单已提交（未审核）  1表示订单通过审核  -1表示订单未通过审核)
	public void setState(String id,int state)throws SQLException;
	
	// 获取当前订单编号的序列
	public Object getStockInNextSeq()throws SQLException;
	
	// 根据订单id获取订单编号
	public Object getSMOrderNumById(String id)throws SQLException;
	
	// 根据订单的出入库标识和处理状态查询订单信息
	public List<StockMaster> findAllStockListByState(int sign,int state)throws SQLException;
	
	// 根据商品的订单处理时间以及相应的订单处理状态获取订单信息
	public List<StockMaster> findAllStockListByTimeUnionState(int sign,String start,String end,int state)throws SQLException;

	// 获取商品入库\出库单的回收站的订单信息（删除标识为1）
	public List<StockMaster> findAllStockListRecycleBin(int sign)throws SQLException;
	
	public StockMaster getStockMasterByOrderNum(String orderNum)
			throws SQLException;
	
}
