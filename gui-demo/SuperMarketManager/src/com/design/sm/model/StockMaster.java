package com.design.sm.model;

public class StockMaster {
	private String stock_master_id;
	private String order_num;
	private String handler_id;
	private String vendor_id;
	private String contact_id;
	private String handle_time;
	private int sign;
	private int delete_flag;
	private int state;
	public StockMaster() {
		super();
	}
	public StockMaster(String stock_master_id, String order_num,
			String handler_id, String vendor_id, String contact_id,
			String handle_time, int sign, int delete_flag, int state) {
		super();
		this.stock_master_id = stock_master_id;
		this.order_num = order_num;
		this.handler_id = handler_id;
		this.vendor_id = vendor_id;
		this.contact_id = contact_id;
		this.handle_time = handle_time;
		this.sign = sign;
		this.delete_flag = delete_flag;
		this.state = state;
	}
	public String getStock_master_id() {
		return stock_master_id;
	}
	public void setStock_master_id(String stock_master_id) {
		this.stock_master_id = stock_master_id;
	}
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	public String getHandler_id() {
		return handler_id;
	}
	public void setHandler_id(String handler_id) {
		this.handler_id = handler_id;
	}
	public String getVendor_id() {
		return vendor_id;
	}
	public void setVendor_id(String vendor_id) {
		this.vendor_id = vendor_id;
	}
	public String getContact_id() {
		return contact_id;
	}
	public void setContact_id(String contact_id) {
		this.contact_id = contact_id;
	}
	public String getHandle_time() {
		return handle_time;
	}
	public void setHandle_time(String handle_time) {
		this.handle_time = handle_time;
	}
	public int getSign() {
		return sign;
	}
	public void setSign(int sign) {
		this.sign = sign;
	}
	public int getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "StockMaster [stock_master_id=" + stock_master_id
				+ ", order_num=" + order_num + ", handler_id=" + handler_id
				+ ", vendor_id=" + vendor_id + ", contact_id=" + contact_id
				+ ", handle_time=" + handle_time + ", sign=" + sign
				+ ", delete_flag=" + delete_flag + ", state=" + state + "]";
	}
}
