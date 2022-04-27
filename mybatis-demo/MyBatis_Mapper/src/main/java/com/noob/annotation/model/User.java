package com.noob.annotation.model;

import com.noob.mybatis.model.Orders;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**       
 * User实体类，其属性定义与数据库中的列名一一对应   
 */

public class User implements Serializable{
	private int id;
	private String username;
	private Date birthday;
	private String sex;
	private String address;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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


