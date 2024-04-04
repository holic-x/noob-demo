package com.design.sm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.dao.StockMasterDAO;
import com.design.sm.model.StockMaster;

public class StockMasterDAOImpl extends BaseDAOImpl<StockMaster> implements StockMasterDAO{

	@Override
	public void addStockMaster(StockMaster sm) throws SQLException {
		String sql = "insert into stock_master values(?,?,?,?,?,to_date(substr(?,1,10),'yyyy-mm-dd'),?,?,?)";
		Object[] args = {sm.getStock_master_id(),sm.getOrder_num(),sm.getHandler_id(),
				sm.getVendor_id(),sm.getContact_id(),sm.getHandle_time(),
				sm.getSign(),sm.getDelete_flag(),sm.getState()};
		this.update(conn, sql, args);
	}

	@Override
	public void updateStockMaster(StockMaster sm) throws SQLException {
		String sql = "update stock_master set handler_id=?,set vendor_id=?,contact_id=?,handle_time=to_date(substr(?,1,10),'yyyy-mm-dd'),"
				+ "sign=?,delete_flag=?,state=? where stock_master_id=?";
		Object[] args = {sm.getHandler_id(),sm.getVendor_id(),sm.getContact_id(),sm.getHandle_time(),
				sm.getSign(),sm.getDelete_flag(),sm.getState(),sm.getStock_master_id()};
		this.update(conn, sql, args);
	}

	@Override
	public void deleteStockMaster(String id) throws SQLException {
		String sql = "delete from stock_master where stock_master_id=?";
		this.update(conn, sql, id);
	}

	@Override
	public void setDeleteFlag(String id, int delete_flag) throws SQLException {
		String sql = "update stock_master set delete_flag=? where stock_master_id=?";
		Object[] args = {delete_flag,id};
		this.update(conn, sql, args);
	}

	@Override
	public List<StockMaster> findAllStockList(int sign) throws SQLException {
		String sql = "select * from stock_master where sign=? and delete_flag = 0";
		return this.getForList(conn, sql, sign);
	}

	@Override
	public void setSign(String id, int sign) throws SQLException {
		String sql = "update stock_master set sign=? where stock_master_id=?";
		Object[] args = {sign,id};
		this.update(conn, sql, args);
	}

	@Override
	public void setState(String id, int state) throws SQLException {
		String sql = "update stock_master set state=? where stock_master_id=?";
		Object[] args = {state,id};
		this.update(conn, sql, args);
	}

	@Override
	public Object getStockInNextSeq() throws SQLException {
		String sql = "select stockInseq.nextval from dual";
		return this.getForValue(conn, sql);
	}

	@Override
	public Object getSMOrderNumById(String id) throws SQLException {
		String sql = "select order_num from stock_master where stock_master_id=?";
		return this.getForValue(conn, sql, id);
	}

	@Override
	public List<StockMaster> findAllStockListByState(int sign,int state)
			throws SQLException {
		String sql = "select * from stock_master where sign=? and state=? and delete_flag=0";
		Object[] args = {sign,state};
		return this.getForList(conn, sql, args);
	}

	@Override
	public List<StockMaster> findAllStockListByTimeUnionState(int sign,String start,String end,int state) 
			throws SQLException {
		String sql = "select * from stock_master "
				+ "where to_char(handle_time,'yyyy-mm-dd') >= ? "
				+ "and to_char(handle_time,'yyyy-mm-dd') <= ? "
				+ "and state = ? and sign=? and delete_flag=0";
		Object[] args = {start,end,state,sign};
		return this.getForList(conn, sql, args);
	}

	@Override
	public List<StockMaster> findAllStockListRecycleBin(int sign)
			throws SQLException {
		String sql = "select * from stock_master where delete_flag=1 and sign=?";
		return this.getForList(conn, sql, sign);
	}

	@Override
	public StockMaster getStockMasterByOrderNum(String orderNum)
			throws SQLException {
		String sql = "select * from stock_master where order_num=? and sign=0 and delete_flag=0";
		return this.get(conn, sql, orderNum);
	}
}
