package com.noob.web.request;

import java.util.Arrays;

/**      
 * User类定义
 */

public class User {
	private String username ;
	private String password ;
	private String sex ;
	private String birthday ;
	private String[] likes ;
	private String eduction ;
	public User() {
		super();
	}
	public User(String username, String password, String sex, String birthday, String[] likes, String eduction) {
		super();
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.birthday = birthday;
		this.likes = likes;
		this.eduction = eduction;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String[] getLikes() {
		return likes;
	}
	public void setLikes(String[] likes) {
		this.likes = likes;
	}
	public String getEduction() {
		return eduction;
	}
	public void setEduction(String eduction) {
		this.eduction = eduction;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", sex=" + sex + ", birthday=" + birthday
				+ ", likes=" + Arrays.toString(likes) + ", eduction=" + eduction + "]";
	}
}


