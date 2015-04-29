package com.codrim.bean;

import java.io.Serializable;

public class AddressInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String country; // 国家
	private String region; // 省份
	private String city; // 城市
	private String isp; // 服务提供商
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getIsp() {
		return isp;
	}
	public void setIsp(String isp) {
		this.isp = isp;
	}
	
	@Override
	public String toString() {
		return "AddressInfo [country=" + country + ", region=" + region
				+ ", city=" + city + ", isp=" + isp + "]";
	}
	
}
