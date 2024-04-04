package com.design.sm.model;

public class StockOrder {
	private String stock_master_id;
	private String product_id;
	private int quantity;
	private double unit_price;
	private double amount;
	private int state;
	public StockOrder() {
		super();
	}
	public StockOrder(String stock_master_id, String product_id, int quantity,
			double unit_price, double amount, int state) {
		super();
		this.stock_master_id = stock_master_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.unit_price = unit_price;
		this.amount = amount;
		this.state = state;
	}
	public String getStock_master_id() {
		return stock_master_id;
	}
	public void setStock_master_id(String stock_master_id) {
		this.stock_master_id = stock_master_id;
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
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "StockOrder [stock_master_id=" + stock_master_id
				+ ", product_id=" + product_id + ", quantity=" + quantity
				+ ", unit_price=" + unit_price + ", amount=" + amount
				+ ", state=" + state + "]";
	}
}
