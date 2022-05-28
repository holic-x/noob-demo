package com.guigu.library.model;

public class Reader {

	private String reader_id;
	private String barcode;
	private String academic_num;
	private String reader_name;
	private String reader_sex;
	private String reader_birthday;
	private String reader_idCard;
	private String reader_phone;
	private String reader_email;
	private String reader_descr;
	private String user_id;
	private int classify_num;
	private String card_id;

	public Reader() {
		super();
	}

	public Reader(String reader_id, String barcode, String academic_num,
			String reader_name, String reader_sex, String reader_birthday,
			String reader_idCard, String reader_phone, String reader_email,
			String reader_descr, String user_id, int classify_num,
			String card_id) {
		super();
		this.reader_id = reader_id;
		this.barcode = barcode;
		this.academic_num = academic_num;
		this.reader_name = reader_name;
		this.reader_sex = reader_sex;
		this.reader_birthday = reader_birthday;
		this.reader_idCard = reader_idCard;
		this.reader_phone = reader_phone;
		this.reader_email = reader_email;
		this.reader_descr = reader_descr;
		this.user_id = user_id;
		this.classify_num = classify_num;
		this.card_id = card_id;
	}

	public String getReader_id() {
		return reader_id;
	}

	public void setReader_id(String reader_id) {
		this.reader_id = reader_id;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getAcademic_num() {
		return academic_num;
	}

	public void setAcademic_num(String academic_num) {
		this.academic_num = academic_num;
	}

	public String getReader_name() {
		return reader_name;
	}

	public void setReader_name(String reader_name) {
		this.reader_name = reader_name;
	}

	public String getReader_sex() {
		return reader_sex;
	}

	public void setReader_sex(String reader_sex) {
		this.reader_sex = reader_sex;
	}

	public String getReader_birthday() {
		return reader_birthday;
	}

	public void setReader_birthday(String reader_birthday) {
		this.reader_birthday = reader_birthday;
	}

	public String getReader_idCard() {
		return reader_idCard;
	}

	public void setReader_idCard(String reader_idCard) {
		this.reader_idCard = reader_idCard;
	}

	public String getReader_phone() {
		return reader_phone;
	}

	public void setReader_phone(String reader_phone) {
		this.reader_phone = reader_phone;
	}

	public String getReader_email() {
		return reader_email;
	}

	public void setReader_email(String reader_email) {
		this.reader_email = reader_email;
	}

	public String getReader_descr() {
		return reader_descr;
	}

	public void setReader_descr(String reader_descr) {
		this.reader_descr = reader_descr;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getClassify_num() {
		return classify_num;
	}

	public void setClassify_num(int classify_num) {
		this.classify_num = classify_num;
	}

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	@Override
	public String toString() {
		return "Reader [reader_id=" + reader_id + ", barcode=" + barcode
				+ ", academic_num=" + academic_num + ", reader_name="
				+ reader_name + ", reader_sex=" + reader_sex
				+ ", reader_birthday=" + reader_birthday + ", reader_idCard="
				+ reader_idCard + ", reader_phone=" + reader_phone
				+ ", reader_email=" + reader_email + ", reader_descr="
				+ reader_descr + ", user_id=" + user_id + ", classify_num="
				+ classify_num + ", card_id=" + card_id + "]";
	}

}
