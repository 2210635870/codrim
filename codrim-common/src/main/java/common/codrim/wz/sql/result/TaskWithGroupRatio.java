package common.codrim.wz.sql.result;

import java.io.Serializable;
import java.util.List;

import common.codrim.pojo.TbWzTask;

public class TaskWithGroupRatio extends TbWzTask implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long groupId;
	
	private int leaderPercent;
	
	private int totalStepCount;
	
	private int remainStepCount;
	
	private long nextStep;
	
	private int nextStepType;
	
	private int nextStepRewardPercent;
	
	List<StepWithUserRecord> steps;
	
	public void addStep(StepWithUserRecord step) {
		steps.add(step);
	}
	
	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public int getLeaderPercent() {
		return leaderPercent;
	}

	public void setLeaderPercent(int leaderPercent) {
		this.leaderPercent = leaderPercent;
	}

	public List<StepWithUserRecord> getSteps() {
		return steps;
	}

	public void setSteps(List<StepWithUserRecord> steps) {
		this.steps = steps;
	}

	public int getTotalStepCount() {
		return totalStepCount;
	}

	public void setTotalStepCount(int totalStepCount) {
		this.totalStepCount = totalStepCount;
	}

	public int getRemainStepCount() {
		return remainStepCount;
	}

	public void setRemainStepCount(int remainStepCount) {
		this.remainStepCount = remainStepCount;
	}

	public long getNextStep() {
		return nextStep;
	}

	public void setNextStep(long nextStep) {
		this.nextStep = nextStep;
	}

	public int getNextStepType() {
		return nextStepType;
	}

	public void setNextStepType(int nextStepType) {
		this.nextStepType = nextStepType;
	}

	public int getNextStepRewardPercent() {
		return nextStepRewardPercent;
	}

	public void setNextStepRewardPercent(int nextStepRewardPercent) {
		this.nextStepRewardPercent = nextStepRewardPercent;
	}
	
}
