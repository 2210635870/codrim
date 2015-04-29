package com.dandelion.bean;

import java.io.Serializable;

public class TokenBean implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private String access_token;
	
private int expires_in;

private String refresh_token;
private String openid;
private String scope;
private String unionid;

private String nickname;
private Short sex;
private String province;
private String city;
private String country;
private String[] privilege;
private String headimgurl; 
  private String language;

private int errcode;
private String errmsg;

public String getRefresh_token() {
	return refresh_token;
}

public String getLanguage() {
	return language;
}

public void setLanguage(String language) {
	this.language = language;
}

public String getNickname() {
	return nickname;
}

public void setNickname(String nickname) {
	this.nickname = nickname;
}

public Short getSex() {
	return sex;
}

public void setSex(Short sex) {
	this.sex = sex;
}

public String getProvince() {
	return province;
}

public void setProvince(String province) {
	this.province = province;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getCountry() {
	return country;
}

public void setCountry(String country) {
	this.country = country;
}

public String[] getPrivilege() {
	return privilege;
}

public void setPrivilege(String[] privilege) {
	this.privilege = privilege;
}

public String getHeadimgurl() {
	return headimgurl;
}

public void setHeadimgurl(String headimgurl) {
	this.headimgurl = headimgurl;
}

public void setRefresh_token(String refresh_token) {
	this.refresh_token = refresh_token;
}

public String getOpenid() {
	return openid;
}

public void setOpenid(String openid) {
	this.openid = openid;
}

public String getScope() {
	return scope;
}

public void setScope(String scope) {
	this.scope = scope;
}

public String getUnionid() {
	return unionid;
}

public void setUnionid(String unionid) {
	this.unionid = unionid;
}

public String getAccess_token() {
	return access_token;
}

public void setAccess_token(String access_token) {
	this.access_token = access_token;
}

public int getExpires_in() {
	return expires_in;
}

public void setExpires_in(int expires_in) {
	this.expires_in = expires_in;
}

public int getErrcode() {
	return errcode;
}

public void setErrcode(int errcode) {
	this.errcode = errcode;
}

public String getErrmsg() {
	return errmsg;
}

public void setErrmsg(String errmsg) {
	this.errmsg = errmsg;
}
	
	
}
