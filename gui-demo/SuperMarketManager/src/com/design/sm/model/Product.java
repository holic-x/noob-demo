package com.design.sm.model;

public class Product {
	private String prod_id;
	private String flow_id;
	private String prod_name;
	private double prod_cost;
	private double prod_price;
	private int putaway_stock;
	private int current_stock;
	private int safe_stock;
	private String prod_unit;
	private String prod_origin;
	private String prod_date;
	private String prod_descr;
	private double prod_discount;
	private int promotion_flag;
	private double promotion_price;
	private int delete_flag;
	private String category_id;
	private String vendor_id;
	private String warehouse_id;
	public Product() {
		super();
	}
	public Product(String prod_id, String flow_id, String prod_name,
			double prod_cost, double prod_price, int putaway_stock,
			int current_stock, int safe_stock, String prod_unit,
			String prod_origin, String prod_date, String prod_descr,
			double prod_discount, int promotion_flag, double promotion_price,
			int delete_flag, String category_id, String vendor_id,
			String warehouse_id) {
		super();
		this.prod_id = prod_id;
		this.flow_id = flow_id;
		this.prod_name = prod_name;
		this.prod_cost = prod_cost;
		this.prod_price = prod_price;
		this.putaway_stock = putaway_stock;
		this.current_stock = current_stock;
		this.safe_stock = safe_stock;
		this.prod_unit = prod_unit;
		this.prod_origin = prod_origin;
		this.prod_date = prod_date;
		this.prod_descr = prod_descr;
		this.prod_discount = prod_discount;
		this.promotion_flag = promotion_flag;
		this.promotion_price = promotion_price;
		this.delete_flag = delete_flag;
		this.category_id = category_id;
		this.vendor_id = vendor_id;
		this.warehouse_id = warehouse_id;
	}
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	public String getFlow_id() {
		return flow_id;
	}
	public void setFlow_id(String flow_id) {
		this.flow_id = flow_id;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public double getProd_cost() {
		return prod_cost;
	}
	public void setProd_cost(double prod_cost) {
		this.prod_cost = prod_cost;
	}
	public double getProd_price() {
		return prod_price;
	}
	public void setProd_price(double prod_price) {
		this.prod_price = prod_price;
	}
	public int getPutaway_stock() {
		return putaway_stock;
	}
	public void setPutaway_stock(int putaway_stock) {
		this.putaway_stock = putaway_stock;
	}
	public int getCurrent_stock() {
		return current_stock;
	}
	public void setCurrent_stock(int current_stock) {
		this.current_stock = current_stock;
	}
	public int getSafe_stock() {
		return safe_stock;
	}
	public void setSafe_stock(int safe_stock) {
		this.safe_stock = safe_stock;
	}
	public String getProd_unit() {
		return prod_unit;
	}
	public void setProd_unit(String prod_unit) {
		this.prod_unit = prod_unit;
	}
	public String getProd_origin() {
		return prod_origin;
	}
	public void setProd_origin(String prod_origin) {
		this.prod_origin = prod_origin;
	}
	public String getProd_date() {
		return prod_date;
	}
	public void setProd_date(String prod_date) {
		this.prod_date = prod_date;
	}
	public String getProd_descr() {
		return prod_descr;
	}
	public void setProd_descr(String prod_descr) {
		this.prod_descr = prod_descr;
	}
	public double getProd_discount() {
		return prod_discount;
	}
	public void setProd_discount(double prod_discount) {
		this.prod_discount = prod_discount;
	}
	public int getPromotion_flag() {
		return promotion_flag;
	}
	public void setPromotion_flag(int promotion_flag) {
		this.promotion_flag = promotion_flag;
	}
	public double getPromotion_price() {
		return promotion_price;
	}
	public void setPromotion_price(double promotion_price) {
		this.promotion_price = promotion_price;
	}
	public int getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getVendor_id() {
		return vendor_id;
	}
	public void setVendor_id(String vendor_id) {
		this.vendor_id = vendor_id;
	}
	public String getWarehouse_id() {
		return warehouse_id;
	}
	public void setWarehouse_id(String warehouse_id) {
		this.warehouse_id = warehouse_id;
	}
	@Override
	public String toString() {
		return "Product [prod_id=" + prod_id + ", flow_id=" + flow_id
				+ ", prod_name=" + prod_name + ", prod_cost=" + prod_cost
				+ ", prod_price=" + prod_price + ", putaway_stock="
				+ putaway_stock + ", current_stock=" + current_stock
				+ ", safe_stock=" + safe_stock + ", prod_unit=" + prod_unit
				+ ", prod_origin=" + prod_origin + ", prod_date=" + prod_date
				+ ", prod_descr=" + prod_descr + ", prod_discount="
				+ prod_discount + ", promotion_flag=" + promotion_flag
				+ ", promotion_price=" + promotion_price + ", delete_flag="
				+ delete_flag + ", category_id=" + category_id + ", vendor_id="
				+ vendor_id + ", warehouse_id=" + warehouse_id + "]";
	}
}
