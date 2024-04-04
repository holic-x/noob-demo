package com.design.sm.model;

public class SaleMaster {
	private String sale_master_id;
	private String order_num;
	private String handler_id;
	private String customer_id;
	private String handle_time;
	private int delete_flag;
	private int state;
	public SaleMaster() {
		super();
	}
	public SaleMaster(String sale_master_id, String order_num,
			String handler_id, String customer_id, String handle_time,
			int delete_flag, int state) {
		super();
		this.sale_master_id = sale_master_id;
		this.order_num = order_num;
		this.handler_id = handler_id;
		this.customer_id = customer_id;
		this.handle_time = handle_time;
		this.delete_flag = delete_flag;
		this.state = state;
	}
	public String getSale_master_id() {
		return sale_master_id;
	}
	public void setSale_master_id(String sale_master_id) {
		this.sale_master_id = sale_master_id;
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
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getHandle_time() {
		return handle_time;
	}
	public void setHandle_time(String handle_time) {
		this.handle_time = handle_time;
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
		return "SaleMaster [sale_master_id=" + sale_master_id + ", order_num="
				+ order_num + ", handler_id=" + handler_id + ", customer_id="
				+ customer_id + ", handle_time=" + handle_time + ", delete_flag="
				+ delete_flag + ", state=" + state + "]";
	}
}
