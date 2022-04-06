package com.design.sm.model;

public class Category {
	private String category_id;
	private String category_name;
	private int delete_flag;
	public Category() {
		super();
	}
	public Category(String category_id, String category_name, int delete_flag) {
		super();
		this.category_id = category_id;
		this.category_name = category_name;
		this.delete_flag = delete_flag;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public int getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
	}
	@Override
	public String toString() {
		return "Category [category_id=" + category_id + ", category_name="
				+ category_name + ", delete_flag=" + delete_flag + "]";
	}
}
