package com.design.sm.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.dao.CategoryDAO;
import com.design.sm.dao.impl.CategoryDAOImpl;
import com.design.sm.model.Category;
import com.design.sm.service.CategoryService;

public class CategoryServiceImpl implements CategoryService{

	private CategoryDAO dao = new CategoryDAOImpl(); 
	@Override
	public Object getCategoryName(String id) throws SQLException {
		return dao.getCategoryName(id);
	}

	@Override
	public List<Category> findAllCategoryList() throws SQLException {
		return dao.findAllCategory();
	}

	@Override
	public Vector<Vector> findAllCategoryVector() throws SQLException {
		List<Category> list = dao.findAllCategory();
		return this.pack(list);
	}

	@Override
	public void addCategory(Category c) throws SQLException {
		dao.addCategory(c);
	}

	@Override
	public void updateCategory(Category c) throws SQLException {
		dao.updateCategory(c);
	}
	

	@Override
	public void deleteCategory(String id) throws SQLException {
		dao.deleteCategory(id);
	}

	@Override
	public Vector<Vector> getCategoryByNameKeyword(String keyword)
			throws SQLException {
		List<Category> list = dao.getCategoryByNameKeyword(keyword);
		return this.pack(list);
	}
	
	//将List<Category>封装为Vector<Vector>类型
	public Vector<Vector> pack(List<Category> list){
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (Category obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 3; i++) {
					temp.add(obj.getCategory_id());//分类id
					temp.add(obj.getCategory_name());//分类名称
					temp.add(obj.getDelete_flag());//删除标识
				}
				rows.add(temp);
			}
		}
		return rows;
	}

}
