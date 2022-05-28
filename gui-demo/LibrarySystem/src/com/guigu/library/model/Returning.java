package com.guigu.library.model;

public class Returning {
	private String returning_id;
	private String returning_num;
	private String book_id;
	private String reader_id;
	private String returning_date;

	public Returning() {
		super();
	}

	public Returning(String returning_id, String returning_num, String book_id,
			String reader_id, String returning_date) {
		super();
		this.returning_id = returning_id;
		this.returning_num = returning_num;
		this.book_id = book_id;
		this.reader_id = reader_id;
		this.returning_date = returning_date;
	}

	public String getReturning_id() {
		return returning_id;
	}

	public void setReturning_id(String returning_id) {
		this.returning_id = returning_id;
	}

	public String getReturning_num() {
		return returning_num;
	}

	public void setReturning_num(String returning_num) {
		this.returning_num = returning_num;
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

	public String getReturning_date() {
		return returning_date;
	}

	public void setReturning_date(String returning_date) {
		this.returning_date = returning_date;
	}

	@Override
	public String toString() {
		return "Returning [returning_id=" + returning_id + ", returning_num="
				+ returning_num + ", book_id=" + book_id + ", reader_id="
				+ reader_id + ", returning_date=" + returning_date + "]";
	}

}
