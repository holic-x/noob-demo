package com.guigu.library.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.guigu.library.model.StorageArea;

public interface StorageAreaService {

	// 查找所有的存储区域
	public List<StorageArea> findAllStorageArea() throws SQLException;

	// 定义pack方法将List<StorageArea>封装为Vector<Vector>记录
	public Vector<Vector> pack(List<StorageArea> list) throws SQLException;

	// 根据区域编号获取区域信息
	public StorageArea getStorageAreaByNum(int num) throws SQLException;

}
