package com.guigu.library.model;

public class Books {
	 private String book_id ;
	 private String barcode	;
	 private String  isbn	;
	 private String  callno	;
	 private String  book_name	;
	 private String  classify_num	;
	 private int  area_num;
	 private String  author	;
	 private String  translator;
	 private String  publish_date	;
	 private String  press	;
	 private double  price	;
	 private String  format	;
	 private String  entry_date	;
	 private String  put_on_date	;
	 private String  abstract_descr	;
	 private String  proposal_reader	;
	 private int  borrow_flag;
	 private int  put_on_flag	;
	 private int   delete_flag;
	public Books() {
		super();
	}
	public Books(String book_id, String barcode, String isbn, String callno,
			String book_name, String classify_num, int area_num, String author,
			String translator, String publish_date, String press, double price,
			String format, String entry_date, String put_on_date,
			String abstract_descr, String proposal_reader, int borrow_flag,
			int put_on_flag, int delete_flag) {
		super();
		this.book_id = book_id;
		this.barcode = barcode;
		this.isbn = isbn;
		this.callno = callno;
		this.book_name = book_name;
		this.classify_num = classify_num;
		this.area_num = area_num;
		this.author = author;
		this.translator = translator;
		this.publish_date = publish_date;
		this.press = press;
		this.price = price;
		this.format = format;
		this.entry_date = entry_date;
		this.put_on_date = put_on_date;
		this.abstract_descr = abstract_descr;
		this.proposal_reader = proposal_reader;
		this.borrow_flag = borrow_flag;
		this.put_on_flag = put_on_flag;
		this.delete_flag = delete_flag;
	}
	public String getBook_id() {
		return book_id;
	}
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getCallno() {
		return callno;
	}
	public void setCallno(String callno) {
		this.callno = callno;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getClassify_num() {
		return classify_num;
	}
	public void setClassify_num(String classify_num) {
		this.classify_num = classify_num;
	}
	public int getArea_num() {
		return area_num;
	}
	public void setArea_num(int area_num) {
		this.area_num = area_num;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTranslator() {
		return translator;
	}
	public void setTranslator(String translator) {
		this.translator = translator;
	}
	public String getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getEntry_date() {
		return entry_date;
	}
	public void setEntry_date(String entry_date) {
		this.entry_date = entry_date;
	}
	public String getPut_on_date() {
		return put_on_date;
	}
	public void setPut_on_date(String put_on_date) {
		this.put_on_date = put_on_date;
	}
	public String getAbstract_descr() {
		return abstract_descr;
	}
	public void setAbstract_descr(String abstract_descr) {
		this.abstract_descr = abstract_descr;
	}
	public String getProposal_reader() {
		return proposal_reader;
	}
	public void setProposal_reader(String proposal_reader) {
		this.proposal_reader = proposal_reader;
	}
	public int getBorrow_flag() {
		return borrow_flag;
	}
	public void setBorrow_flag(int borrow_flag) {
		this.borrow_flag = borrow_flag;
	}
	public int getPut_on_flag() {
		return put_on_flag;
	}
	public void setPut_on_flag(int put_on_flag) {
		this.put_on_flag = put_on_flag;
	}
	public int getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
	}

	@Override
	public String toString() {
		return "Books [book_id=" + book_id + ", barcode=" + barcode + ", isbn="
				+ isbn + ", callno=" + callno + ", book_name=" + book_name
				+ ", classify_num=" + classify_num + ", area_num=" + area_num
				+ ", author=" + author + ", translator=" + translator
				+ ", publish_date=" + publish_date + ", press=" + press
				+ ", price=" + price + ", format=" + format + ", entry_date="
				+ entry_date + ", put_on_date=" + put_on_date
				+ ", abstract_descr=" + abstract_descr + ", proposal_reader="
				+ proposal_reader + ", borrow_flag=" + borrow_flag
				+ ", put_on_flag=" + put_on_flag + ", delete_flag="
				+ delete_flag + "]";
	}
}
