package com.noob.mybatis.model;

public class OrderCustom extends Orders{
	/**
	 *  此处创建查询对象，通过继承获取某个对象的所有属性，
	 *  此外还可自定义自己的属性，并且提供相应的Getter、Setter
	 */
	private String username;
	private String sex;
	private String address;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
