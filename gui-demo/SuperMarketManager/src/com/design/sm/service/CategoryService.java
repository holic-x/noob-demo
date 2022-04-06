package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.Category;

public interface CategoryService {

	// 通过分类id获取分类名称
	public Object getCategoryName(String id) throws SQLException;

	// 获取所有商品分类信息列表
	public List<Category> findAllCategoryList() throws SQLException;

	// 获取所有商品分类信息列表
	public Vector<Vector> findAllCategoryVector() throws SQLException;

	// 新增分类信息
	public void addCategory(Category c) throws SQLException;

	// 修改分类信息
	public void updateCategory(Category c) throws SQLException;

	// 根据删除分类信息
	public void deleteCategory(String id) throws SQLException;

	//根据所属分类名称关键字获取分类信息
	public Vector<Vector> getCategoryByNameKeyword(String keyword) throws SQLException;

}
