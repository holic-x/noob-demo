package com.guigu.library.model;

/**
 * ∂¡’ﬂ∑÷¿‡
 */
public class ReaderClassify {
	private int classify_num;
	private String classify_name;
	private int maximum;
	private int time_limit;

	public ReaderClassify() {
		super();
	}

	public ReaderClassify(int classify_num, String classify_name,
			int maximum, int time_limit) {
		super();
		this.classify_num = classify_num;
		this.classify_name = classify_name;
		this.maximum = maximum;
		this.time_limit = time_limit;
	}

	public int getClassify_num() {
		return classify_num;
	}

	public void setClassify_num(int classify_num) {
		this.classify_num = classify_num;
	}

	public String getClassify_name() {
		return classify_name;
	}

	public void setClassify_name(String classify_name) {
		this.classify_name = classify_name;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	public int getTime_limit() {
		return time_limit;
	}

	public void setTime_limit(int time_limit) {
		this.time_limit = time_limit;
	}

	@Override
	public String toString() {
		return "ReaderClassify [classify_num=" + classify_num
				+ ", classify_name=" + classify_name + ", maximum=" + maximum
				+ ", time_limit=" + time_limit + "]";
	}

}
