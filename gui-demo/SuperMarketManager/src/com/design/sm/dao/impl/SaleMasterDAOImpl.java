package com.design.sm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.dao.SaleMasterDAO;
import com.design.sm.model.SaleMaster;

public class SaleMasterDAOImpl extends BaseDAOImpl<SaleMaster> implements SaleMasterDAO{

	@Override
	public void addSaleMaster(SaleMaster sm) throws SQLException {
		String sql = "insert into sale_master values(?,?,?,?,to_date(substr(?,1,10),'yyyy-mm-dd'),?,?)";
		Object[] args = {sm.getSale_master_id(),sm.getOrder_num(),sm.getHandler_id(),
				sm.getCustomer_id(),sm.getHandle_time(),sm.getDelete_flag(),sm.getState()};
		this.update(conn, sql, args);
	}

	@Override
	public void updateSaleMaster(SaleMaster sm) throws SQLException {
		String sql = "update sale_master set handler_id=?,customer_id=?,handle_time=?,delete_flag=?,state=? where sale_master_id=?";
		Object[] args = {sm.getHandler_id(),sm.getCustomer_id(),sm.getHandle_time(),sm.getDelete_flag(),
				sm.getState(),sm.getSale_master_id()};
		this.update(conn, sql, args);
	}

	@Override
	public void deleteSaleMaster(String id) throws SQLException {
		String sql = "delete from sale_master where sale_master_id=?";
		this.update(conn, sql, id);
	}

	@Override
	public void setDeleteFlag(String id, int delete_flag) throws SQLException {
		String sql = "update sale_master set delete_flag=? where sale_master_id=?";
		Object[] args = {delete_flag,id};
		this.update(conn, sql, args);
	}

	@Override
	public List<SaleMaster> findAllSaleList() throws SQLException {
		String sql = "select * from sale_master where delete_flag = 0";
		return this.getForList(conn, sql);
	}

	@Override
	public Object getSaleNextSeq() throws SQLException {
		String sql = "select saleseq.nextval from dual";
		return this.getForValue(conn, sql);
	}

	@Override
	public Object getSOOrderNumById(String id) throws SQLException {
		String sql = "select order_num from sale_master where sale_master_id=?";
		return this.getForValue(conn, sql, id);
	}

	@Override
	public List<SaleMaster> findAllSaleListByTimeUnionState(
			String start, String end, int state) throws SQLException {
		String sql = "select * from sale_master "
				+ "where to_char(handle_time,'yyyy-mm-dd') >= ? "
				+ "and to_char(handle_time,'yyyy-mm-dd') <= ? "
				+ "and state = ? and delete_flag=0";
		Object[] args = {start,end,state};
		return this.getForList(conn, sql, args);
	}

	@Override
	public List<SaleMaster> findAllSaleListRecycleBin()
			throws SQLException {
		String sql = "select * from sale_master where delete_flag=1";
		return this.getForList(conn, sql);
	}

	@Override
	public SaleMaster getSaleMasterByOrderNum(String orderNum)
			throws SQLException {
		String sql = "select * from sale_master where order_num=?";
		return this.get(conn, sql,orderNum);
	}

}
