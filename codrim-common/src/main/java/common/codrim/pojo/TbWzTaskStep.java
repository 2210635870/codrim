package common.codrim.pojo;

import java.io.Serializable;

public class TbWzTaskStep implements Serializable {
    /**
     * tb_wz_task_step.step_id
     * @ibatorgenerated 2014-12-30 18:11:38
     */
    private Long stepId;

    /**
     * tb_wz_task_step.task_id
     * @ibatorgenerated 2014-12-30 18:11:38
     */
    private Long taskId;

    /**
     * tb_wz_task_step.step_type
     * @ibatorgenerated 2014-12-30 18:11:38
     */
    private Integer stepType;

    /**
     * tb_wz_task_step.step_desc
     * @ibatorgenerated 2014-12-30 18:11:38
     */
    private String stepDesc;

    /**
     * tb_wz_task_step.reward_percent
     * @ibatorgenerated 2014-12-30 18:11:38
     */
    private Integer rewardPercent;

    /**
     * tb_wz_task_step.screen_no
     * @ibatorgenerated 2014-12-30 18:11:38
     */
    private Integer screenNo;

    /**
     * tb_wz_task_step.screen_desc
     * @ibatorgenerated 2014-12-30 18:11:38
     */
    private String screenDesc;

    /**
     * tb_wz_task_step.count_duration
     * @ibatorgenerated 2014-12-30 18:11:38
     */
    private Integer countDuration;

    /**
     * tb_wz_task_step.count_interval
     * @ibatorgenerated 2014-12-30 18:11:38
     */
    private Integer countInterval;

    /**
     * tb_wz_task_step.is_final
     * @ibatorgenerated 2014-12-30 18:11:38
     */
    private Integer isFinal;
    
    private Short stepOrder;

    public Short getStepOrder() {
		return stepOrder;
	}

	public void setStepOrder(Short stepOrder) {
		this.stepOrder = stepOrder;
	}

	public Long getStepId() {
        return stepId;
    }

    public void setStepId(Long stepId) {
        this.stepId = stepId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Integer getStepType() {
        return stepType;
    }

    public void setStepType(Integer stepType) {
        this.stepType = stepType;
    }

    public String getStepDesc() {
        return stepDesc;
    }

    public void setStepDesc(String stepDesc) {
        this.stepDesc = stepDesc;
    }

    public Integer getRewardPercent() {
        return rewardPercent;
    }

    public void setRewardPercent(Integer rewardPercent) {
        this.rewardPercent = rewardPercent;
    }

    public Integer getScreenNo() {
        return screenNo;
    }

    public void setScreenNo(Integer screenNo) {
        this.screenNo = screenNo;
    }

    public String getScreenDesc() {
        return screenDesc;
    }

    public void setScreenDesc(String screenDesc) {
        this.screenDesc = screenDesc;
    }

    public Integer getCountDuration() {
        return countDuration;
    }

    public void setCountDuration(Integer countDuration) {
        this.countDuration = countDuration;
    }

    public Integer getCountInterval() {
        return countInterval;
    }

    public void setCountInterval(Integer countInterval) {
        this.countInterval = countInterval;
    }

    public Integer getIsFinal() {
        return isFinal;
    }

    public void setIsFinal(Integer isFinal) {
        this.isFinal = isFinal;
    }
}