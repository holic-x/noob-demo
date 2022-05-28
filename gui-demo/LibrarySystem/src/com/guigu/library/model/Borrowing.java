package com.guigu.library.model;

public class Borrowing {
	private String borrowing_id;
	private String borrowing_num;
	private String book_id;
	private String reader_id;
	private String borrowing_date;
	private String suggest_return_date;
	private int borrow_state;
	private int violation_state;

	public Borrowing() {
		super();
	}

	public Borrowing(String borrowing_id, String borrowing_num, String book_id,
			String reader_id, String borrowing_date,
			String suggest_return_date, int borrow_state, int violation_state) {
		super();
		this.borrowing_id = borrowing_id;
		this.borrowing_num = borrowing_num;
		this.book_id = book_id;
		this.reader_id = reader_id;
		this.borrowing_date = borrowing_date;
		this.suggest_return_date = suggest_return_date;
		this.borrow_state = borrow_state;
		this.violation_state = violation_state;
	}

	public String getBorrowing_id() {
		return borrowing_id;
	}

	public void setBorrowing_id(String borrowing_id) {
		this.borrowing_id = borrowing_id;
	}

	public String getBorrowing_num() {
		return borrowing_num;
	}

	public void setBorrowing_num(String borrowing_num) {
		this.borrowing_num = borrowing_num;
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

	public String getBorrowing_date() {
		return borrowing_date;
	}

	public void setBorrowing_date(String borrowing_date) {
		this.borrowing_date = borrowing_date;
	}

	public String getSuggest_return_date() {
		return suggest_return_date;
	}

	public void setSuggest_return_date(String suggest_return_date) {
		this.suggest_return_date = suggest_return_date;
	}

	public int getBorrow_state() {
		return borrow_state;
	}

	public void setBorrow_state(int borrow_state) {
		this.borrow_state = borrow_state;
	}

	public int getViolation_state() {
		return violation_state;
	}

	public void setViolation_state(int violation_state) {
		this.violation_state = violation_state;
	}

	@Override
	public String toString() {
		return "Borrowing [borrowing_id=" + borrowing_id + ", borrowing_num="
				+ borrowing_num + ", book_id=" + book_id + ", reader_id="
				+ reader_id + ", borrowing_date=" + borrowing_date
				+ ", suggest_return_date=" + suggest_return_date
				+ ", borrow_state=" + borrow_state + ", violation_state="
				+ violation_state + "]";
	}

}
