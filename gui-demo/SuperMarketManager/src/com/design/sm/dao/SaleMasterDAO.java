package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.SaleMaster;
import com.design.sm.model.SaleMaster;

public interface SaleMasterDAO extends BaseDAO<SaleMaster> {

	// 增加销售记录
	public void addSaleMaster(SaleMaster sm) throws SQLException;

	// 修改销售记录
	public void updateSaleMaster(SaleMaster sm) throws SQLException;

	// 根据主表id彻底删除销售记录
	public void deleteSaleMaster(String id) throws SQLException;

	// 根据主表id将记录放入回收站或将记录从回收站中撤回（修改删除标识）
	public void setDeleteFlag(String id, int delete_flag) throws SQLException;

	// 查询所有的销售记录
	public List<SaleMaster> findAllSaleList() throws SQLException;

	// 获取当前订单编号的序列
	public Object getSaleNextSeq() throws SQLException;

	// 根据订单id获取订单编号
	public Object getSOOrderNumById(String id) throws SQLException;

	// 根据商品的订单处理时间以及相应的订单处理状态获取订单信息
	public List<SaleMaster> findAllSaleListByTimeUnionState(String start,
			String end, int state) throws SQLException;

	// 获取商品销售单的回收站的订单信息（删除标识为1）
	public List<SaleMaster> findAllSaleListRecycleBin()
			throws SQLException;
	
	// 根据订单编号获取订单主表信息
	public SaleMaster getSaleMasterByOrderNum(String orderNum)throws SQLException;

}
