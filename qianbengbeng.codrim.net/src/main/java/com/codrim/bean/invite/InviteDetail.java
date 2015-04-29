package com.codrim.bean.invite;

import java.io.Serializable;

public class InviteDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String code;
	
	private String inviteURL;
	
	private String qrCode;
	
	private String inviterReward;
	
	private String inviteeReward;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getInviteURL() {
		return inviteURL;
	}

	public void setInviteURL(String inviteURL) {
		this.inviteURL = inviteURL;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getInviterReward() {
		return inviterReward;
	}

	public void setInviterReward(String inviterReward) {
		this.inviterReward = inviterReward;
	}

	public String getInviteeReward() {
		return inviteeReward;
	}

	public void setInviteeReward(String inviteeReward) {
		this.inviteeReward = inviteeReward;
	}
	
}
