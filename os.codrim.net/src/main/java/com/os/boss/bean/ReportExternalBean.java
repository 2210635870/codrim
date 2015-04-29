/**
 * 
 */
package com.os.boss.bean;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import common.codrim.util.CustomDateSerializer;

/**
 * @author Administrator
 *
 */
public class ReportExternalBean {
private Date  date;
private String productName;
private String channelName;
private String putPrice;
private String effectNum;
private String income;

@JsonSerialize(using = CustomDateSerializer.class)
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}

public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public String getChannelName() {
	return channelName;
}
public void setChannelName(String channelName) {
	this.channelName = channelName;
}
public String getPutPrice() {
	return putPrice;
}
public void setPutPrice(String putPrice) {
	this.putPrice = putPrice;
}
public String getEffectNum() {
	return effectNum;
}
public void setEffectNum(String effectNum) {
	this.effectNum = effectNum;
}
public String getIncome() {
	return income;
}
public void setIncome(String income) {
	this.income = income;
}

	
	
	
	
}
