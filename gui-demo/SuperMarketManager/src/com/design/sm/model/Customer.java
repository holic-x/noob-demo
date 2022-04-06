package com.design.sm.model;

public class Customer {
	private String customer_id;
	private String customer_name;
	private String customer_address;
	private String customer_phone;
	private String customer_email;
	private int integrate;
	private double balance;
	private int class_id;
	public Customer() {
		super();
	}
	public Customer(String customer_id, String customer_name,
			String customer_address, String customer_phone,
			String customer_email, int integrate, double balance, int class_id) {
		super();
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.customer_address = customer_address;
		this.customer_phone = customer_phone;
		this.customer_email = customer_email;
		this.integrate = integrate;
		this.balance = balance;
		this.class_id = class_id;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_address() {
		return customer_address;
	}
	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}
	public String getCustomer_phone() {
		return customer_phone;
	}
	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}
	public String getCustomer_email() {
		return customer_email;
	}
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	public int getIntegrate() {
		return integrate;
	}
	public void setIntegrate(int integrate) {
		this.integrate = integrate;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getClass_id() {
		return class_id;
	}
	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}
	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", customer_name="
				+ customer_name + ", customer_address=" + customer_address
				+ ", customer_phone=" + customer_phone + ", customer_email="
				+ customer_email + ", integrate=" + integrate + ", balance="
				+ balance + ", class_id=" + class_id + "]";
	}
}
