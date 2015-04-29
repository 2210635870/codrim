package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbWzUser implements Serializable {
    /**
     * tb_wz_user.user_id
     * @ibatorgenerated 2015-01-13 12:51:09
     */
    private Long userId;

    /**
     * tb_wz_user.phone_number
     * @ibatorgenerated 2015-01-13 12:51:09
     */
    private String phoneNumber;

    /**
     * tb_wz_user.status
     * @ibatorgenerated 2015-01-13 12:51:09
     */
    private Integer status;

    /**
     * tb_wz_user.bind_status
     * @ibatorgenerated 2015-01-13 12:51:09
     */
    private Integer bindStatus;

    /**
     * tb_wz_user.username
     * @ibatorgenerated 2015-01-13 12:51:09
     */
    private String username;

    /**
     * tb_wz_user.email
     * @ibatorgenerated 2015-01-13 12:51:09
     */
    private String email;

    /**
     * tb_wz_user.icon
     * @ibatorgenerated 2015-01-13 12:51:09
     */
    private String icon;

    /**
     * tb_wz_user.balance
     * @ibatorgenerated 2015-01-13 12:51:09
     */
    private Long balance;

    /**
     * tb_wz_user.create_date
     * @ibatorgenerated 2015-01-13 12:51:09
     */
    private Date createDate;

    /**
     * tb_wz_user.group_id
     * @ibatorgenerated 2015-01-13 12:51:09
     */
    private Long groupId;

    /**
     * tb_wz_user.is_leader
     * @ibatorgenerated 2015-01-13 12:51:09
     */
    private Integer isLeader;

    /**
     * tb_wz_user.invite_by
     * @ibatorgenerated 2015-01-13 12:51:09
     */
    private Long inviteBy;

    /**
     * tb_wz_user.user_effect
     * @ibatorgenerated 2015-01-13 12:51:09
     */
    private Integer userEffect;

    /**
     * tb_wz_user.step_effect
     * @ibatorgenerated 2015-01-13 12:51:09
     */
    private Integer stepEffect;

    /**
     * tb_wz_user.last_login_date
     * @ibatorgenerated 2015-01-13 12:51:09
     */
    private Date lastLoginDate;

    /**
     * tb_wz_user.last_sign_date
     * @ibatorgenerated 2015-01-13 12:51:09
     */
    private Date lastSignDate;

    /**
     * tb_wz_user.last_cj_date
     * @ibatorgenerated 2015-01-13 12:51:09
     */
    private Date lastCjDate;

    /**
     * tb_wz_user.verify_code
     * @ibatorgenerated 2015-01-13 12:51:09
     */
    private String verifyCode;

    /**
     * tb_wz_user.verify_code_date
     * @ibatorgenerated 2015-01-13 12:51:09
     */
    private Date verifyCodeDate;

    /**
     * tb_wz_user.qr_code_user
     * @ibatorgenerated 2015-01-13 12:51:09
     */
    private String qrCodeUser;

    /**
     * tb_wz_user.qr_code_group
     * @ibatorgenerated 2015-01-13 12:51:09
     */
    private String qrCodeGroup;
    
    
    
    private Short isDisable; 
    
    private Short userSource;

    public Short getIsDisable() {
		return isDisable;
	}

	public void setIsDisable(Short isDisable) {
		this.isDisable = isDisable;
	}

	public Short getUserSource() {
		return userSource;
	}

	public void setUserSource(Short userSource) {
		this.userSource = userSource;
	}

	public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(Integer bindStatus) {
        this.bindStatus = bindStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(Integer isLeader) {
        this.isLeader = isLeader;
    }

    public Long getInviteBy() {
        return inviteBy;
    }

    public void setInviteBy(Long inviteBy) {
        this.inviteBy = inviteBy;
    }

    public Integer getUserEffect() {
        return userEffect;
    }

    public void setUserEffect(Integer userEffect) {
        this.userEffect = userEffect;
    }

    public Integer getStepEffect() {
        return stepEffect;
    }

    public void setStepEffect(Integer stepEffect) {
        this.stepEffect = stepEffect;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getLastSignDate() {
        return lastSignDate;
    }

    public void setLastSignDate(Date lastSignDate) {
        this.lastSignDate = lastSignDate;
    }

    public Date getLastCjDate() {
        return lastCjDate;
    }

    public void setLastCjDate(Date lastCjDate) {
        this.lastCjDate = lastCjDate;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Date getVerifyCodeDate() {
        return verifyCodeDate;
    }

    public void setVerifyCodeDate(Date verifyCodeDate) {
        this.verifyCodeDate = verifyCodeDate;
    }

    public String getQrCodeUser() {
        return qrCodeUser;
    }

    public void setQrCodeUser(String qrCodeUser) {
        this.qrCodeUser = qrCodeUser;
    }

    public String getQrCodeGroup() {
        return qrCodeGroup;
    }

    public void setQrCodeGroup(String qrCodeGroup) {
        this.qrCodeGroup = qrCodeGroup;
    }
}