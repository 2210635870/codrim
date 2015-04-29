package common.codrim.wz.dto;

import java.io.Serializable;

public class TaskStep implements Serializable{

	private static final long serialVersionUID = 1L;

	private String stepId;
	private String stepType;
	private String stepDesc;
	private String rewardPercent;
	private String screenNo;
	private String screenDesc;
	private String countDuration;
	private String countInterval;
	private String stepOrder;
	
	
	public String getStepOrder() {
		return stepOrder;
	}
	public void setStepOrder(String stepOrder) {
		this.stepOrder = stepOrder;
	}
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	public String getStepType() {
		return stepType;
	}
	public void setStepType(String stepType) {
		this.stepType = stepType;
	}
	public String getStepDesc() {
		return stepDesc;
	}
	public void setStepDesc(String stepDesc) {
		this.stepDesc = stepDesc;
	}
	public String getRewardPercent() {
		return rewardPercent;
	}
	public void setRewardPercent(String rewardPercent) {
		this.rewardPercent = rewardPercent;
	}
	public String getScreenNo() {
		return screenNo;
	}
	public void setScreenNo(String screenNo) {
		this.screenNo = screenNo;
	}
	public String getCountDuration() {
		return countDuration;
	}
	public void setCountDuration(String countDuration) {
		this.countDuration = countDuration;
	}
	public String getCountInterval() {
		return countInterval;
	}
	public void setCountInterval(String countInterval) {
		this.countInterval = countInterval;
	}
	public String getScreenDesc() {
		return screenDesc;
	}
	public void setScreenDesc(String screenDesc) {
		this.screenDesc = screenDesc;
	}

	
	
}
