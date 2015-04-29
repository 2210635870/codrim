package common.codrim.wz.sql.result;

import java.io.Serializable;

import common.codrim.pojo.TbWzTaskRecord;

public class TaskReviewInfo extends TbWzTaskRecord implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private String phoneNumber;

	private String taskName;
	
	private String appIcon;
	
	private double taskOrigPrice;
	
	private int rewardPercent;
	
	private String stepDesc;
	
	private String screenDesc;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getAppIcon() {
		return appIcon;
	}

	public void setAppIcon(String appIcon) {
		this.appIcon = appIcon;
	}

	public double getTaskOrigPrice() {
		return taskOrigPrice;
	}

	public void setTaskOrigPrice(double taskOrigPrice) {
		this.taskOrigPrice = taskOrigPrice;
	}

	public String getStepDesc() {
		return stepDesc;
	}

	public void setStepDesc(String stepDesc) {
		this.stepDesc = stepDesc;
	}

	public String getScreenDesc() {
		return screenDesc;
	}

	public void setScreenDesc(String screenDesc) {
		this.screenDesc = screenDesc;
	}

	public int getRewardPercent() {
		return rewardPercent;
	}

	public void setRewardPercent(int rewardPercent) {
		this.rewardPercent = rewardPercent;
	}
	
	
}
