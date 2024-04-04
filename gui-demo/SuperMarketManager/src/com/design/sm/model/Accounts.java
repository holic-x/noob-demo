package com.design.sm.model;

public class Accounts {
	private String account_id;
	private String username;
	private String password;
	private int limits;
	public Accounts() {
		super();
	}
	public Accounts(String account_id, String username, String password,
			int limits) {
		super();
		this.account_id = account_id;
		this.username = username;
		this.password = password;
		this.limits = limits;
	}
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
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
	public int getLimits() {
		return limits;
	}
	public void setLimits(int limits) {
		this.limits = limits;
	}
	@Override
	public String toString() {
		return "Accounts [account_id=" + account_id + ", username=" + username
				+ ", password=" + password + ", limits=" + limits + "]";
	}
}
