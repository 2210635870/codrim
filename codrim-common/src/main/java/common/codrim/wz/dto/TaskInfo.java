package common.codrim.wz.dto;

import java.io.Serializable;

public class TaskInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String taskId;
	private String taskName;
	private String taskOrigPrice;
	private String taskTarget;
	private String taskStartDate;
	private String taskEndDate;
	private String taskEffect;
	private String taskStatus;
	private String appIcon;
	private Short taskBelong;
    private Short weight;
    private Short productWeight;
	    
	    
	public Short getTaskBelong() {
			return taskBelong;
		}
		public void setTaskBelong(Short taskBelong) {
			this.taskBelong = taskBelong;
		}
		public Short getWeight() {
			return weight;
		}
		public void setWeight(Short weight) {
			this.weight = weight;
		}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskOrigPrice() {
		return taskOrigPrice;
	}
	public void setTaskOrigPrice(String taskOrigPrice) {
		this.taskOrigPrice = taskOrigPrice;
	}
	public String getTaskTarget() {
		return taskTarget;
	}
	public void setTaskTarget(String taskTarget) {
		this.taskTarget = taskTarget;
	}
	public String getTaskStartDate() {
		return taskStartDate;
	}
	public void setTaskStartDate(String taskStartDate) {
		this.taskStartDate = taskStartDate;
	}
	public String getTaskEndDate() {
		return taskEndDate;
	}
	public void setTaskEndDate(String taskEndDate) {
		this.taskEndDate = taskEndDate;
	}
	public String getTaskEffect() {
		return taskEffect;
	}
	public void setTaskEffect(String taskEffect) {
		this.taskEffect = taskEffect;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	public String getAppIcon() {
		return appIcon;
	}
	public void setAppIcon(String appIcon) {
		this.appIcon = appIcon;
	}
	public Short getProductWeight() {
		return productWeight;
	}
	public void setProductWeight(Short productWeight) {
		this.productWeight = productWeight;
	}
}
