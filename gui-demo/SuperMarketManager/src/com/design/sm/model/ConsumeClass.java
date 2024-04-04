package com.design.sm.model;

public class ConsumeClass {
	private int class_id;
	private String class_name;
	private int class_off;
	private double class_discount;
	public ConsumeClass() {
		super();
	}
	public ConsumeClass(int class_id, String class_name, int class_off,
			double class_discount) {
		super();
		this.class_id = class_id;
		this.class_name = class_name;
		this.class_off = class_off;
		this.class_discount = class_discount;
	}
	public int getClass_id() {
		return class_id;
	}
	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public int getClass_off() {
		return class_off;
	}
	public void setClass_off(int class_off) {
		this.class_off = class_off;
	}
	public double getClass_discount() {
		return class_discount;
	}
	public void setClass_discount(double class_discount) {
		this.class_discount = class_discount;
	}
	@Override
	public String toString() {
		return "ConsumeClass [class_id=" + class_id + ", class_name="
				+ class_name + ", class_off=" + class_off + ", class_discount="
				+ class_discount + "]";
	}
}
