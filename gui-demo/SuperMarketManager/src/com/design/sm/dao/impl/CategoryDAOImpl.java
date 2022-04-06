package com.design.sm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.dao.CategoryDAO;
import com.design.sm.model.Category;

public class CategoryDAOImpl extends BaseDAOImpl<Category> implements
		CategoryDAO {

	@Override
	public Object getCategoryName(String id) throws SQLException {
		String sql = "select category_name from category where category_id=?";
		return this.getForValue(conn, sql, id);
	}

	@Override
	public List<Category> findAllCategory() throws SQLException {
		String sql = "select * from category";
		return this.getForList(conn, sql);
	}

	@Override
	public void addCategory(Category c) throws SQLException {
		String sql = "insert into category values(?,?,?)";
		Object[] args = { c.getCategory_id(), c.getCategory_name(),
				c.getDelete_flag() };
		this.update(conn, sql, args);
	}

	@Override
	public void updateCategory(Category c) throws SQLException {
		String sql = "update category set category_name=? where category_id=?";
		Object[] args = { c.getCategory_name(), c.getCategory_id() };
		this.update(conn, sql, args);
	}

	@Override
	public void deleteCategory(String id) throws SQLException {
		String sql = "delete from category where category_id=?";
		this.update(conn, sql, id);
	}

	@Override
	public List<Category> getCategoryByNameKeyword(String keyword)
			throws SQLException {
		String sql = "select * from category where category_name like ?";
		return this.getForList(conn, sql, keyword);
	}
}
