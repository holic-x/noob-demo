package com.design.sm.model;

public class Units {
	private String units_id;
	private String Units_name;
	private int delete_flag;
	public Units() {
		super();
	}
	public Units(String units_id, String units_name, int delete_flag) {
		super();
		this.units_id = units_id;
		Units_name = units_name;
		this.delete_flag = delete_flag;
	}
	public String getUnits_id() {
		return units_id;
	}
	public void setUnits_id(String units_id) {
		this.units_id = units_id;
	}
	public String getUnits_name() {
		return Units_name;
	}
	public void setUnits_name(String units_name) {
		Units_name = units_name;
	}
	public int getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
	}
	@Override
	public String toString() {
		return "Units [units_id=" + units_id + ", Units_name=" + Units_name
				+ ", delete_flag=" + delete_flag + "]";
	}
}
