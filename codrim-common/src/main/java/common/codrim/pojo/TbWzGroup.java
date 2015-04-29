package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbWzGroup implements Serializable {
    /**
     * tb_wz_group.group_id
     * @ibatorgenerated 2015-01-03 20:59:55
     */
    private Long groupId;

    /**
     * tb_wz_group.group_name
     * @ibatorgenerated 2015-01-03 20:59:55
     */
    private String groupName;

    /**
     * tb_wz_group.group_desc
     * @ibatorgenerated 2015-01-03 20:59:55
     */
    private String groupDesc;

    /**
     * tb_wz_group.group_icon
     * @ibatorgenerated 2015-01-03 20:59:55
     */
    private String groupIcon;

    /**
     * tb_wz_group.group_leader
     * @ibatorgenerated 2015-01-03 20:59:55
     */
    private Long groupLeader;

    /**
     * tb_wz_group.create_date
     * @ibatorgenerated 2015-01-03 20:59:55
     */
    private Date createDate;

    /**
     * tb_wz_group.leader_percent
     * @ibatorgenerated 2015-01-03 20:59:55
     */
    private Integer leaderPercent;

    /**
     * tb_wz_group.group_income
     * @ibatorgenerated 2015-01-03 20:59:55
     */
    private Long groupIncome;

    /**
     * tb_wz_group.group_effect
     * @ibatorgenerated 2015-01-03 20:59:55
     */
    private Integer groupEffect;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public String getGroupIcon() {
        return groupIcon;
    }

    public void setGroupIcon(String groupIcon) {
        this.groupIcon = groupIcon;
    }

    public Long getGroupLeader() {
        return groupLeader;
    }

    public void setGroupLeader(Long groupLeader) {
        this.groupLeader = groupLeader;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getLeaderPercent() {
        return leaderPercent;
    }

    public void setLeaderPercent(Integer leaderPercent) {
        this.leaderPercent = leaderPercent;
    }

    public Long getGroupIncome() {
        return groupIncome;
    }

    public void setGroupIncome(Long groupIncome) {
        this.groupIncome = groupIncome;
    }

    public Integer getGroupEffect() {
        return groupEffect;
    }

    public void setGroupEffect(Integer groupEffect) {
        this.groupEffect = groupEffect;
    }
}