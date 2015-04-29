package common.codrim.pojo;

import java.io.Serializable;

public class TbWzGroupTaskRatio implements Serializable {
    /**
     * tb_wz_group_task_ratio.ratio_id
     * @ibatorgenerated 2014-12-30 10:38:10
     */
    private Long ratioId;

    /**
     * tb_wz_group_task_ratio.task_id
     * @ibatorgenerated 2014-12-30 10:38:10
     */
    private Long taskId;

    /**
     * tb_wz_group_task_ratio.group_id
     * @ibatorgenerated 2014-12-30 10:38:10
     */
    private Long groupId;

    /**
     * tb_wz_group_task_ratio.leader_percent
     * @ibatorgenerated 2014-12-30 10:38:10
     */
    private Long leaderPercent;

    public Long getRatioId() {
        return ratioId;
    }

    public void setRatioId(Long ratioId) {
        this.ratioId = ratioId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getLeaderPercent() {
        return leaderPercent;
    }

    public void setLeaderPercent(Long leaderPercent) {
        this.leaderPercent = leaderPercent;
    }
}