package com.design.sm.model;

public class Vendor {
	private String vendor_id;
	private String vendor_name;
	private String vendor_phone;
	private String vendor_email;
	private String vendor_fax;
	private String vendor_address;
	public Vendor() {
		super();
	}
	public Vendor(String vendor_id, String vendor_name, String vendor_phone,
			String vendor_email, String vendor_fax, String vendor_address) {
		super();
		this.vendor_id = vendor_id;
		this.vendor_name = vendor_name;
		this.vendor_phone = vendor_phone;
		this.vendor_email = vendor_email;
		this.vendor_fax = vendor_fax;
		this.vendor_address = vendor_address;
	}
	public String getVendor_id() {
		return vendor_id;
	}
	public void setVendor_id(String vendor_id) {
		this.vendor_id = vendor_id;
	}
	public String getVendor_name() {
		return vendor_name;
	}
	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
	}
	public String getVendor_phone() {
		return vendor_phone;
	}
	public void setVendor_phone(String vendor_phone) {
		this.vendor_phone = vendor_phone;
	}
	public String getVendor_email() {
		return vendor_email;
	}
	public void setVendor_email(String vendor_email) {
		this.vendor_email = vendor_email;
	}
	public String getVendor_fax() {
		return vendor_fax;
	}
	public void setVendor_fax(String vendor_fax) {
		this.vendor_fax = vendor_fax;
	}
	public String getVendor_address() {
		return vendor_address;
	}
	public void setVendor_address(String vendor_address) {
		this.vendor_address = vendor_address;
	}
	@Override
	public String toString() {
		return "Vendor [vendor_id=" + vendor_id + ", vendor_name="
				+ vendor_name + ", vendor_phone=" + vendor_phone
				+ ", vendor_email=" + vendor_email + ", vendor_fax="
				+ vendor_fax + ", vendor_address=" + vendor_address + "]";
	}
}
