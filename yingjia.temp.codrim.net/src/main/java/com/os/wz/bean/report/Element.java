package com.os.wz.bean.report;

public class Element {

	private String year;
	
	private String month;
	
	private String day;
	
	private String value;
	
	public Element() {
		super();
	}

	public Element(String year, String month, String day, String value) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.value = value;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
