package com.guigu.library.model;

public class Orders {

	private String order_id;
	private String order_num;
	private String book_id;
	private String reader_id;
	private String order_date;

	public Orders() {
		super();
	}

	public Orders(String order_id, String order_num, String book_id,
			String reader_id, String order_date) {
		super();
		this.order_id = order_id;
		this.order_num = order_num;
		this.book_id = book_id;
		this.reader_id = reader_id;
		this.order_date = order_date;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getOrder_num() {
		return order_num;
	}

	public void setOrder_num(String order_num) {
		this.order_num = order_num;
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

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	@Override
	public String toString() {
		return "Orders [order_id=" + order_id + ", order_num=" + order_num
				+ ", book_id=" + book_id + ", reader_id=" + reader_id
				+ ", order_date=" + order_date + "]";
	}

}
