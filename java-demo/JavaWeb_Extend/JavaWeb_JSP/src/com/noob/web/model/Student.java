package com.noob.web.model;

import java.util.Date;

/**      
 * 定义Student领域对象
 * id、username、password、birthday 
 */
public class Student {
	private String id;
	private String username="noob";
	private String password;
	private Date birthday;
	public Student() {
		super();
	}
	public Student(String id, String username, String password, Date birthday) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.birthday = birthday;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", username=" + username + ", password=" + password + ", birthday=" + birthday
				+ "]";
	}
}


