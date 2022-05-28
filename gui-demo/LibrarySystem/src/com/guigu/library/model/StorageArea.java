package com.guigu.library.model;

public class StorageArea {
	private int area_num;
	private String area_name;
	private String area_descr;

	public StorageArea() {
		super();
	}

	public StorageArea(int area_num, String area_name, String area_descr) {
		super();
		this.area_num = area_num;
		this.area_name = area_name;
		this.area_descr = area_descr;
	}

	public int getArea_num() {
		return area_num;
	}

	public void setArea_num(int area_num) {
		this.area_num = area_num;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getArea_descr() {
		return area_descr;
	}

	public void setArea_descr(String area_descr) {
		this.area_descr = area_descr;
	}

	@Override
	public String toString() {
		return "Storage_area [area_num=" + area_num + ", area_name="
				+ area_name + ", area_descr=" + area_descr + "]";
	}

}
