package com.codrim.bean.user;

import java.io.Serializable;

public class JsonSignInInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String isSigned;
	
	private String reward;

	public String getIsSigned() {
		return isSigned;
	}

	public void setIsSigned(String isSigned) {
		this.isSigned = isSigned;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

}
