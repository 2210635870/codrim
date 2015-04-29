package com.codrim.bean.user;

import java.io.Serializable;

public class JsonUserInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long userId;
	
	private String status;
	
	private String isBind;
	
	private String isFirstLogin;
	private String isPointsLimit;

	public String getIsPointsLimit() {
		return isPointsLimit;
	}

	public void setIsPointsLimit(String isPointsLimit) {
		this.isPointsLimit = isPointsLimit;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsBind() {
		return isBind;
	}

	public void setIsBind(String isBind) {
		this.isBind = isBind;
	}

	public String getIsFirstLogin() {
		return isFirstLogin;
	}

	public void setIsFirstLogin(String isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}
	
}
