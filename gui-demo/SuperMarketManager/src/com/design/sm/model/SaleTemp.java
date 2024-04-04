package com.design.sm.model;

public class SaleTemp {
	private String product_id;
	private int quantity;
	private double unit_price;
	private double amount;
	public SaleTemp() {
		super();
	}
	public SaleTemp(String product_id, int quantity, double unit_price,
			double amount) {
		super();
		this.product_id = product_id;
		this.quantity = quantity;
		this.unit_price = unit_price;
		this.amount = amount;
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
	@Override
	public String toString() {
		return "SaleTemp [product_id=" + product_id + ", quantity=" + quantity
				+ ", unit_price=" + unit_price + ", amount=" + amount + "]";
	}
}
