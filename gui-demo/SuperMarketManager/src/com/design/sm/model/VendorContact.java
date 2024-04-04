package com.design.sm.model;

public class VendorContact {
	private String contact_id;
	private String contact_name;
	private String contact_phone;
	private String contact_email;
	private String vendor_id;
	private int owner_flag;
	public VendorContact() {
		super();
	}
	public VendorContact(String contact_id, String contact_name,
			String contact_phone, String contact_email, String vendor_id,
			int owner_flag) {
		super();
		this.contact_id = contact_id;
		this.contact_name = contact_name;
		this.contact_phone = contact_phone;
		this.contact_email = contact_email;
		this.vendor_id = vendor_id;
		this.owner_flag = owner_flag;
	}
	public String getContact_id() {
		return contact_id;
	}
	public void setContact_id(String contact_id) {
		this.contact_id = contact_id;
	}
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	public String getContact_phone() {
		return contact_phone;
	}
	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}
	public String getContact_email() {
		return contact_email;
	}
	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}
	public String getVendor_id() {
		return vendor_id;
	}
	public void setVendor_id(String vendor_id) {
		this.vendor_id = vendor_id;
	}
	public int getOwner_flag() {
		return owner_flag;
	}
	public void setOwner_flag(int owner_flag) {
		this.owner_flag = owner_flag;
	}
	@Override
	public String toString() {
		return "VendorContact [contact_id=" + contact_id + ", contact_name="
				+ contact_name + ", contact_phone=" + contact_phone
				+ ", contact_email=" + contact_email + ", vendor_id="
				+ vendor_id + ", owner_flag=" + owner_flag + "]";
	}
}
