package com.design.sm.model;

public class PurchaseNote {
	private String order_num;
	private double actual_amount;
	private int payment;
	
	public PurchaseNote() {
		super();
	}
	public PurchaseNote(String order_num, double actual_amount, int payment) {
		super();
		this.order_num = order_num;
		this.actual_amount = actual_amount;
		this.payment = payment;
	}
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	public double getActual_amount() {
		return actual_amount;
	}
	public void setActual_amount(double actual_amount) {
		this.actual_amount = actual_amount;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	@Override
	public String toString() {
		return "SoldNote [order_num=" + order_num + ", actual_amount="
				+ actual_amount + ", payment=" + payment + "]";
	}
}
