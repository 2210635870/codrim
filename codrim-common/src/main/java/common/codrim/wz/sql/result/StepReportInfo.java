package common.codrim.wz.sql.result;

import java.io.Serializable;

public class StepReportInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long stepId;
	
	private Integer stepEffect; //任务步骤完成人数
	
	private Integer rewardPercent; //任务步骤奖励比例
	
	private String stepDesc; //任务步骤名字
	
	private Double stepPrice; //任务步骤奖励 = 奖励比例 x 接入单价
	
	private Double stepCost; //任务步骤成本
	
	private Double taskOrigPrice;

	public Long getStepId() {
		return stepId;
	}

	public void setStepId(Long stepId) {
		this.stepId = stepId;
	}

	public Integer getStepEffect() {
		return stepEffect;
	}

	public void setStepEffect(Integer stepEffect) {
		this.stepEffect = stepEffect;
	}

	public Integer getRewardPercent() {
		return rewardPercent;
	}

	public void setRewardPercent(Integer rewardPercent) {
		this.rewardPercent = rewardPercent;
	}

	public String getStepDesc() {
		return stepDesc;
	}

	public void setStepDesc(String stepDesc) {
		this.stepDesc = stepDesc;
	}

	public Double getStepPrice() {
		return stepPrice;
	}

	public void setStepPrice(Double stepPrice) {
		this.stepPrice = stepPrice;
	}

	public Double getStepCost() {
		return stepCost;
	}

	public void setStepCost(Double stepCost) {
		this.stepCost = stepCost;
	}

	public Double getTaskOrigPrice() {
		return taskOrigPrice;
	}

	public void setTaskOrigPrice(Double taskOrigPrice) {
		this.taskOrigPrice = taskOrigPrice;
	}

	
}
