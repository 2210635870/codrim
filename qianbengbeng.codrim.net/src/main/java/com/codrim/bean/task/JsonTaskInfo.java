package com.codrim.bean.task;

import java.io.Serializable;

public class JsonTaskInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long taskId;
	private String taskName;
	private String appIcon;
	private String taskRemark;
	private String taskPrice;
	private String taskTarget;
	private String nextStepType;
	private String finishStatus;
	
	public long getTaskId() {
		return taskId;
	}
	public void setTaskId(long taskId) {
		this.taskId = taskId;
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
	public String getTaskRemark() {
		return taskRemark;
	}
	public void setTaskRemark(String taskRemark) {
		this.taskRemark = taskRemark;
	}
	public String getTaskPrice() {
		return taskPrice;
	}
	public void setTaskPrice(String taskPrice) {
		this.taskPrice = taskPrice;
	}
	public String getTaskTarget() {
		return taskTarget;
	}
	public void setTaskTarget(String taskTarget) {
		this.taskTarget = taskTarget;
	}
	public String getNextStepType() {
		return nextStepType;
	}
	public void setNextStepType(String nextStepType) {
		this.nextStepType = nextStepType;
	}
	public String getFinishStatus() {
		return finishStatus;
	}
	public void setFinishStatus(String finishStatus) {
		this.finishStatus = finishStatus;
	}
	
	
}
