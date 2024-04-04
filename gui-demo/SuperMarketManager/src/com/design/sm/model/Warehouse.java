package com.design.sm.model;

public class Warehouse {
	private String warehouse_id;
	private String warehouse_name;
	private int delete_flag;
	public Warehouse() {
		super();
	}
	public Warehouse(String warehouse_id, String warehouse_name, int delete_flag) {
		super();
		this.warehouse_id = warehouse_id;
		this.warehouse_name = warehouse_name;
		this.delete_flag = delete_flag;
	}
	public String getWarehouse_id() {
		return warehouse_id;
	}
	public void setWarehouse_id(String warehouse_id) {
		this.warehouse_id = warehouse_id;
	}
	public String getWarehouse_name() {
		return warehouse_name;
	}
	public void setWarehouse_name(String warehouse_name) {
		this.warehouse_name = warehouse_name;
	}
	public int getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
	}
	@Override
	public String toString() {
		return "Warehouse [warehouse_id=" + warehouse_id + ", warehouse_name="
				+ warehouse_name + ", delete_flag=" + delete_flag + "]";
	}
}
