package com.codrim.bean.task;

import java.io.Serializable;

public class JsonTaskStep implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long stepId;
	private String stepType;
	private String stepDesc;
	private String reward;
	private String finishDate;
	private String reviewStatus;
	private String screenNo;
	private String countDuration;
	private String isFinal;
	private String isConfirmed;
    private String stepOrder;

	
	public String getStepOrder() {
		return stepOrder;
	}
	public void setStepOrder(String stepOrder) {
		this.stepOrder = stepOrder;
	}
	public long getStepId() {
		return stepId;
	}
	public void setStepId(long stepId) {
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
	public String getReward() {
		return reward;
	}
	public void setReward(String reward) {
		this.reward = reward;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	public String getReviewStatus() {
		return reviewStatus;
	}
	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
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
	public String getIsConfirmed() {
		return isConfirmed;
	}
	public void setIsConfirmed(String isConfirmed) {
		this.isConfirmed = isConfirmed;
	}
	public String getIsFinal() {
		return isFinal;
	}
	public void setIsFinal(String isFinal) {
		this.isFinal = isFinal;
	}
	
}
