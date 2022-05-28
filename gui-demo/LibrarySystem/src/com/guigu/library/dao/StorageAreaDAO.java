package com.guigu.library.dao;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.model.StorageArea;

public interface StorageAreaDAO extends BaseDAO<StorageArea>{
	
	// 查找所有的存储区域
	public List<StorageArea> findAllStorageArea()throws SQLException;

	// 根据区域编号获取区域信息
	public StorageArea getStorageAreaByNum(int num)throws SQLException;
}
