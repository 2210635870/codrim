package com.codrim.bean.task;

import java.io.Serializable;

public class JsonTaskRecord implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long recordId;
	
	private String finshDate;
	
	private String taskName;
	
	private String stepDesc;
	
	private String incomeGold;

	public long getRecordId() {
		return recordId;
	}

	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}

	public String getFinshDate() {
		return finshDate;
	}

	public void setFinshDate(String finshDate) {
		this.finshDate = finshDate;
	}

	public String getStepDesc() {
		return stepDesc;
	}

	public void setStepDesc(String stepDesc) {
		this.stepDesc = stepDesc;
	}

	public String getIncomeGold() {
		return incomeGold;
	}

	public void setIncomeGold(String incomeGold) {
		this.incomeGold = incomeGold;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
}
