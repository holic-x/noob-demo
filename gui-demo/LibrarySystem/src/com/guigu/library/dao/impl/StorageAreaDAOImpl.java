package com.guigu.library.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.dao.StorageAreaDAO;
import com.guigu.library.model.StorageArea;

public class StorageAreaDAOImpl extends BaseDAOImpl<StorageArea> implements StorageAreaDAO{

	@Override
	public List<StorageArea> findAllStorageArea() throws SQLException {
		String sql = "select * from storage_area";
		return this.getForList(conn, sql);
	}

	@Override
	public StorageArea getStorageAreaByNum(int num) throws SQLException {
		String sql = "select * from storage_area where area_num=?";
		return this.get(conn, sql,num);
	}

}
