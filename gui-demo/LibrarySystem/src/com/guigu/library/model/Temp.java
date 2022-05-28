package com.guigu.library.model;

public class Temp {

	private String temp_id;
	private String book_id;
	private String reader_id;

	public Temp() {
		super();
	}

	public Temp(String temp_id, String book_id, String reader_id) {
		super();
		this.temp_id = temp_id;
		this.book_id = book_id;
		this.reader_id = reader_id;
	}

	public String getTemp_id() {
		return temp_id;
	}

	public void setTemp_id(String temp_id) {
		this.temp_id = temp_id;
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

	@Override
	public String toString() {
		return "Temp [temp_id=" + temp_id + ", book_id=" + book_id
				+ ", reader_id=" + reader_id + "]";
	}

}
