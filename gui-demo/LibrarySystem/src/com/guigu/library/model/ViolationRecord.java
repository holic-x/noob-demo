package com.guigu.library.model;

public class ViolationRecord {
	private String violation_id;
	private String violation_num;
	private String book_id;
	private String reader_id;
	private int violation_type;
	private String violation_date;
	private String pay_date;
	private double pay_amount;
	private int handle_flag;

	public ViolationRecord() {
		super();
	}

	public ViolationRecord(String violation_id, String violation_num,
			String book_id, String reader_id, int violation_type,
			String violation_date, String pay_date, double pay_amount,
			int handle_flag) {
		super();
		this.violation_id = violation_id;
		this.violation_num = violation_num;
		this.book_id = book_id;
		this.reader_id = reader_id;
		this.violation_type = violation_type;
		this.violation_date = violation_date;
		this.pay_date = pay_date;
		this.pay_amount = pay_amount;
		this.handle_flag = handle_flag;
	}

	public String getViolation_id() {
		return violation_id;
	}

	public void setViolation_id(String violation_id) {
		this.violation_id = violation_id;
	}

	public String getViolation_num() {
		return violation_num;
	}

	public void setViolation_num(String violation_num) {
		this.violation_num = violation_num;
	}

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

	public String getReader_id() {
		return reader_id;
	}

	public void setReader_id(String reader_id) {
		this.reader_id = reader_id;
	}

	public int getViolation_type() {
		return violation_type;
	}

	public void setViolation_type(int violation_type) {
		this.violation_type = violation_type;
	}

	public String getViolation_date() {
		return violation_date;
	}

	public void setViolation_date(String violation_date) {
		this.violation_date = violation_date;
	}

	public String getPay_date() {
		return pay_date;
	}

	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}

	public double getPay_amount() {
		return pay_amount;
	}

	public void setPay_amount(double pay_amount) {
		this.pay_amount = pay_amount;
	}

	public int getHandle_flag() {
		return handle_flag;
	}

	public void setHandle_flag(int handle_flag) {
		this.handle_flag = handle_flag;
	}

	@Override
	public String toString() {
		return "ViolationRecord [violation_id=" + violation_id
				+ ", violation_num=" + violation_num + ", book_id=" + book_id
				+ ", reader_id=" + reader_id + ", violation_type="
				+ violation_type + ", violation_date=" + violation_date
				+ ", pay_date=" + pay_date + ", pay_amount=" + pay_amount
				+ ", handle_flag=" + handle_flag + "]";
	}

}
