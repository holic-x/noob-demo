package com.design.sm.model;

public class SaleOrder {
	private String sale_master_id;
	private String product_id;
	private int quantity;
	private double unit_price;
	private double amount;
	private int delte_flag;
	private int state;
	public SaleOrder() {
		super();
	}
	public SaleOrder(String sale_master_id, String product_id, int quantity,
			double unit_price, double amount, int delte_flag, int state) {
		super();
		this.sale_master_id = sale_master_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.unit_price = unit_price;
		this.amount = amount;
		this.delte_flag = delte_flag;
		this.state = state;
	}
	public String getSale_master_id() {
		return sale_master_id;
	}
	public void setSale_master_id(String sale_master_id) {
		this.sale_master_id = sale_master_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getDelte_flag() {
		return delte_flag;
	}
	public void setDelte_flag(int delte_flag) {
		this.delte_flag = delte_flag;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "SaleOrder [sale_master_id=" + sale_master_id + ", product_id="
				+ product_id + ", quantity=" + quantity + ", unit_price="
				+ unit_price + ", amount=" + amount + ", delte_flag="
				+ delte_flag + ", state=" + state + "]";
	}
}
