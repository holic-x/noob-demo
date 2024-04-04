package com.guigu.library.model;

public class Setting {
	private String reader_id;
	private int infoSearch;
	private int booksManager;
	private int readerManager;
	private int systemSetup;
	public Setting() {
		super();
	}
	public Setting(String reader_id, int infoSearch, int booksManager,
			int readerManager, int systemSetup) {
		super();
		this.reader_id = reader_id;
		this.infoSearch = infoSearch;
		this.booksManager = booksManager;
		this.readerManager = readerManager;
		this.systemSetup = systemSetup;
	}
	public String getReader_id() {
		return reader_id;
	}
	public void setReader_id(String reader_id) {
		this.reader_id = reader_id;
	}
	public int getInfoSearch() {
		return infoSearch;
	}
	public void setInfoSearch(int infoSearch) {
		this.infoSearch = infoSearch;
	}
	public int getBooksManager() {
		return booksManager;
	}
	public void setBooksManager(int booksManager) {
		this.booksManager = booksManager;
	}
	public int getReaderManager() {
		return readerManager;
	}
	public void setReaderManager(int readerManager) {
		this.readerManager = readerManager;
	}
	public int getSystemSetup() {
		return systemSetup;
	}
	public void setSystemSetup(int systemSetup) {
		this.systemSetup = systemSetup;
	}
	@Override
	public String toString() {
		return "Setting [reader_id=" + reader_id + ", infoSearch=" + infoSearch
				+ ", booksManager=" + booksManager + ", readerManager="
				+ readerManager + ", systemSetup=" + systemSetup + "]";
	}
}
