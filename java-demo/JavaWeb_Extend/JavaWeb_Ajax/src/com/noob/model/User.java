package com.noob.model;
/**       
 * <p>project_name:JavaWeb_Ajax</p>
 * <p>package_name:com.noob.model.User</p>
 * <p>description：</p>
 * <p> date:2018年8月1日下午5:49:13 </p>
 * <p>comments：    </p>
 * <p>@version  jdk1.8</p>
 * 
 * <p>Copyright (c) 2018, 892944741@qq.com All Rights Reserved. </p>    
 */

public class User {
	private String username;
	private String password;
	public User() {
		super();
	}
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
}


