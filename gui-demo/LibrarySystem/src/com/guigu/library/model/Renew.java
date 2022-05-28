package com.guigu.library.model;

public class Renew {
	private String renew_id;
	private String renew_num;
	private String book_id;
	private String reader_id;
	private String renew_date;

	public Renew() {
		super();
	}

	public Renew(String renew_id, String renew_num, String book_id,
			String reader_id, String renew_date) {
		super();
		this.renew_id = renew_id;
		this.renew_num = renew_num;
		this.book_id = book_id;
		this.reader_id = reader_id;
		this.renew_date = renew_date;
	}

	public String getRenew_id() {
		return renew_id;
	}

	public void setRenew_id(String renew_id) {
		this.renew_id = renew_id;
	}

	public String getRenew_num() {
		return renew_num;
	}

	public void setRenew_num(String renew_num) {
		this.renew_num = renew_num;
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

	public String getRenew_date() {
		return renew_date;
	}

	public void setRenew_date(String renew_date) {
		this.renew_date = renew_date;
	}

	@Override
	public String toString() {
		return "Renew [renew_id=" + renew_id + ", renew_num=" + renew_num
				+ ", book_id=" + book_id + ", reader_id=" + reader_id
				+ ", renew_date=" + renew_date + "]";
	}

}
