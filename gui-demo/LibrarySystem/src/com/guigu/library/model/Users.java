package com.guigu.library.model;
/**
 *	ÓÃ»§ÕËºÅ
 */
public class Users {
	private String user_id;
	private String user_name;
	private String user_password;
	private int user_limits;

	public Users() {
		super();
	}

	public Users(String user_id, String user_name, String user_password,
			int user_limits) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_limits = user_limits;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public int getUser_limits() {
		return user_limits;
	}

	public void setUser_limits(int user_limits) {
		this.user_limits = user_limits;
	}

	@Override
	public String toString() {
		return "Users [user_id=" + user_id + ", user_name=" + user_name
				+ ", user_password=" + user_password + ", user_limits="
				+ user_limits + "]";
	}

}
