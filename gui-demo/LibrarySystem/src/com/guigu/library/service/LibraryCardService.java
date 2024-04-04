package com.guigu.library.service;

import java.sql.SQLException;

import com.guigu.library.model.LibraryCard;

public interface LibraryCardService {

	// 添加借阅证信息
	public void addLibraryCard(LibraryCard lc) throws SQLException;

	// 修改借阅证信息
	public void updateLibraryCard(LibraryCard lc) throws SQLException;

	// 根据借阅证id删除借阅证信息
	public void deleteLibraryCard(String card_id) throws SQLException;

	// 根据借阅证id获取借阅证信息
	public LibraryCard getLibraryCardById(String card_id) throws SQLException;

	// 根据借阅证编号获取借阅证信息
	public LibraryCard getLibraryCardByNum(String card_num) throws SQLException;

	// 根据借阅证id获取借阅证编号
	public String getLibraryCardNumById(String card_id) throws SQLException;

	// 获取当前借阅证编号序号
	public String getLibraryCardSeq() throws SQLException;

}
