package common.codrim.wz.sql.result;

import java.io.Serializable;

import common.codrim.pojo.TbWzTaskRecord;

public class TaskRecordInfo extends TbWzTaskRecord implements Serializable{

	private static final long serialVersionUID = 1L;

	private String taskName;
	
	private String stepDesc;
	
	private boolean realFinished;

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getStepDesc() {
		return stepDesc;
	}

	public void setStepDesc(String stepDesc) {
		this.stepDesc = stepDesc;
	}

	public boolean isRealFinished() {
		return realFinished;
	}

	public void setRealFinished(boolean realFinished) {
		this.realFinished = realFinished;
	}

	

}
