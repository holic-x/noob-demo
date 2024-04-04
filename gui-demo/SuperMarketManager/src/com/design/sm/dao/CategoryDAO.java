package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.Category;

public interface CategoryDAO extends BaseDAO<Category>{
	
	//通过所属分类id获取分类名称
	public Object getCategoryName(String id) throws SQLException;
	
	//根据所属分类名称关键字获取分类信息
	public List<Category> getCategoryByNameKeyword(String keyword) throws SQLException;

	//查找所有商品分类信息
	public List<Category> findAllCategory() throws SQLException;
	
	//新增分类信息
	public void addCategory(Category c)throws SQLException;
	
	//修改分类信息
	public void updateCategory(Category c)throws SQLException;
	
	//根据删除分类信息
	public void deleteCategory(String id)throws SQLException;
}
