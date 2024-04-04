package com.noob.web.cookies;
/**
 * 创建model书籍类：
 * id、名称、作者、详细描述
 */
public class Book {
	private String id;
	private String name;
	private String author;
	private String descr;
	public Book() {
		super();
	}
	public Book(String id, String name, String author, String descr) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.descr = descr;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", descr=" + descr + "]";
	}
}


