package common.codrim.wz.sql.result;

import java.io.Serializable;

import common.codrim.pojo.TbWzGroupApplication;

public class GroupApplicationInfo extends TbWzGroupApplication implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String applicantName;
	
	private String icon;
	
	private String phoneNumber;
	
	private long balance;
	
	private int stepEffect;
	
	private int isInvited;

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public int getStepEffect() {
		return stepEffect;
	}

	public void setStepEffect(int stepEffect) {
		this.stepEffect = stepEffect;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getIsInvited() {
		return isInvited;
	}

	public void setIsInvited(int isInvited) {
		this.isInvited = isInvited;
	}
	
	
}
