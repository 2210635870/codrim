package com.codrim.bean.exchange;

import java.io.Serializable;

public class JsonExchangeRecord implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long recordId;
	
	private String exchangeDate;
	
	private String exchangeMoney;
	
	private String exchangeGold;
	
	private String reviewStatus;
	
	private String exchangeType;

	public long getRecordId() {
		return recordId;
	}

	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}

	public String getExchangeDate() {
		return exchangeDate;
	}

	public void setExchangeDate(String exchangeDate) {
		this.exchangeDate = exchangeDate;
	}

	public String getExchangeMoney() {
		return exchangeMoney;
	}

	public void setExchangeMoney(String exchangeMoney) {
		this.exchangeMoney = exchangeMoney;
	}

	public String getExchangeGold() {
		return exchangeGold;
	}

	public void setExchangeGold(String exchangeGold) {
		this.exchangeGold = exchangeGold;
	}

	public String getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public String getExchangeType() {
		return exchangeType;
	}

	public void setExchangeType(String exchangeType) {
		this.exchangeType = exchangeType;
	}
	
}
