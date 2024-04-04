package com.guigu.library.model;

public class BookClassify {
	private String classify_num;
	private String classify_name;
	private int current_level;
	private String parent_num;

	public BookClassify() {
		super();
	}

	public BookClassify(String classify_num, String classify_name,
			int current_level, String parent_num) {
		super();
		this.classify_num = classify_num;
		this.classify_name = classify_name;
		this.current_level = current_level;
		this.parent_num = parent_num;
	}

	public String getClassify_num() {
		return classify_num;
	}

	public void setClassify_num(String classify_num) {
		this.classify_num = classify_num;
	}

	public String getClassify_name() {
		return classify_name;
	}

	public void setClassify_name(String classify_name) {
		this.classify_name = classify_name;
	}

	public int getCurrent_level() {
		return current_level;
	}

	public void setCurrent_level(int current_level) {
		this.current_level = current_level;
	}

	public String getParent_num() {
		return parent_num;
	}

	public void setParent_num(String parent_num) {
		this.parent_num = parent_num;
	}

	@Override
	public String toString() {
		return "BookClassify [classify_num=" + classify_num
				+ ", classify_name=" + classify_name + ", current_level="
				+ current_level + ", parent_num=" + parent_num + "]";
	}

}
