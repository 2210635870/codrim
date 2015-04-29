package com.codrim.bean.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JsonTaskDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long taskId;
	private String taskName;
	private String appURL;
	private String appIcon;
	private String appScreen1;
	private String appScreen2;
	private String appSize;
	private String appPname;
	private String taskTarget;
	private String taskDesc;
	private String taskRemark;
	private String taskPrice;
	private long currentStep;
	
	private List<JsonTaskStep> taskSteps = new ArrayList<JsonTaskStep>();
	
	public void addStep(JsonTaskStep step) {
		taskSteps.add(step);
	}

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

	public String getAppURL() {
		return appURL;
	}

	public void setAppURL(String appURL) {
		this.appURL = appURL;
	}

	public String getAppIcon() {
		return appIcon;
	}

	public void setAppIcon(String appIcon) {
		this.appIcon = appIcon;
	}

	public String getAppScreen1() {
		return appScreen1;
	}

	public void setAppScreen1(String appScreen1) {
		this.appScreen1 = appScreen1;
	}

	public String getAppScreen2() {
		return appScreen2;
	}

	public void setAppScreen2(String appScreen2) {
		this.appScreen2 = appScreen2;
	}

	public String getAppSize() {
		return appSize;
	}

	public void setAppSize(String appSize) {
		this.appSize = appSize;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
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

	public List<JsonTaskStep> getTaskSteps() {
		return taskSteps;
	}

	public void setTaskSteps(List<JsonTaskStep> taskSteps) {
		this.taskSteps = taskSteps;
	}

	public String getAppPname() {
		return appPname;
	}

	public void setAppPname(String appPname) {
		this.appPname = appPname;
	}

	public long getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(long currentStep) {
		this.currentStep = currentStep;
	}

	public String getTaskTarget() {
		return taskTarget;
	}

	public void setTaskTarget(String taskTarget) {
		this.taskTarget = taskTarget;
	}
	
	
	
}
