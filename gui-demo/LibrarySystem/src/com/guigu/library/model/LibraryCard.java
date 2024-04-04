package com.guigu.library.model;

/**
 * ½èÔÄÖ¤
 */
public class LibraryCard {
	private String card_id;
	private String card_num;
	private String handle_date;
	private String valid_till;
	private int loss_state;
	private int disable_state;

	public LibraryCard() {
		super();
	}

	public LibraryCard(String card_id, String card_num, String handle_date,
			String valid_till, int loss_state, int disable_state) {
		super();
		this.card_id = card_id;
		this.card_num = card_num;
		this.handle_date = handle_date;
		this.valid_till = valid_till;
		this.loss_state = loss_state;
		this.disable_state = disable_state;
	}

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	public String getCard_num() {
		return card_num;
	}

	public void setCard_num(String card_num) {
		this.card_num = card_num;
	}

	public String getHandle_date() {
		return handle_date;
	}

	public void setHandle_date(String handle_date) {
		this.handle_date = handle_date;
	}

	public String getValid_till() {
		return valid_till;
	}

	public void setValid_till(String valid_till) {
		this.valid_till = valid_till;
	}

	public int getLoss_state() {
		return loss_state;
	}

	public void setLoss_state(int loss_state) {
		this.loss_state = loss_state;
	}

	public int getDisable_state() {
		return disable_state;
	}

	public void setDisable_state(int disable_state) {
		this.disable_state = disable_state;
	}

	@Override
	public String toString() {
		return "LibraryCard [card_id=" + card_id + ", card_num=" + card_num
				+ ", handle_date=" + handle_date + ", valid_till=" + valid_till
				+ ", loss_state=" + loss_state + ", disable_state="
				+ disable_state + "]";
	}

}
