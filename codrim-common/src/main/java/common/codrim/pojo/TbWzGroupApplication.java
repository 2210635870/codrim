package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbWzGroupApplication implements Serializable {
    /**
     * tb_wz_group_application.id
     * @ibatorgenerated 2015-01-09 12:03:39
     */
    private Long id;

    /**
     * tb_wz_group_application.user_id
     * @ibatorgenerated 2015-01-09 12:03:39
     */
    private Long userId;

    /**
     * tb_wz_group_application.group_id
     * @ibatorgenerated 2015-01-09 12:03:39
     */
    private Long groupId;

    /**
     * tb_wz_group_application.join_reason
     * @ibatorgenerated 2015-01-09 12:03:39
     */
    private String joinReason;

    /**
     * tb_wz_group_application.status
     * @ibatorgenerated 2015-01-09 12:03:39
     */
    private Integer status;

    /**
     * tb_wz_group_application.invite_by
     * @ibatorgenerated 2015-01-09 12:03:39
     */
    private Long inviteBy;

    /**
     * tb_wz_group_application.create_date
     * @ibatorgenerated 2015-01-09 12:03:39
     */
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getJoinReason() {
        return joinReason;
    }

    public void setJoinReason(String joinReason) {
        this.joinReason = joinReason;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getInviteBy() {
        return inviteBy;
    }

    public void setInviteBy(Long inviteBy) {
        this.inviteBy = inviteBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}