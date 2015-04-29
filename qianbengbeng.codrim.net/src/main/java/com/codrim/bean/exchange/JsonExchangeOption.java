package com.codrim.bean.exchange;

import java.io.Serializable;

public class JsonExchangeOption implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String exchange10;
	
	private String exchange20;
	
	private String exchange50;

	public String getExchange10() {
		return exchange10;
	}

	public void setExchange10(String exchange10) {
		this.exchange10 = exchange10;
	}

	public String getExchange20() {
		return exchange20;
	}

	public void setExchange20(String exchange20) {
		this.exchange20 = exchange20;
	}

	public String getExchange50() {
		return exchange50;
	}

	public void setExchange50(String exchange50) {
		this.exchange50 = exchange50;
	}
	
}
