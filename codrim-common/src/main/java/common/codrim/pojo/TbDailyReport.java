package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbDailyReport implements Serializable {
    /**
     * tb_daily_report.id
     * @ibatorgenerated 2015-01-27 15:30:03
     */
    private Long id;

    /**
     * tb_daily_report.job_category (范畴)
     * @ibatorgenerated 2015-01-27 15:30:03
     */
    private Integer jobCategory;

    /**
     * tb_daily_report.customer_id (客户)
     * @ibatorgenerated 2015-01-27 15:30:03
     */
    private Long customerId;

    /**
     * tb_daily_report.follow_user (跟进人)
     * @ibatorgenerated 2015-01-27 15:30:03
     */
    private Long followUser;

    /**
     * tb_daily_report.follow_type (跟进性质)
     * @ibatorgenerated 2015-01-27 15:30:03
     */
    private Integer followType;

    /**
     * tb_daily_report.follow_detail (跟进情况)
     * @ibatorgenerated 2015-01-27 15:30:03
     */
    private String followDetail;

    /**
     * tb_daily_report.follow_problem (遇到问题)
     * @ibatorgenerated 2015-01-27 15:30:03
     */
    private String followProblem;

    /**
     * tb_daily_report.support (需要支持)
     * @ibatorgenerated 2015-01-27 15:30:03
     */
    private String support;

    /**
     * tb_daily_report.suggestion (建议)
     * @ibatorgenerated 2015-01-27 15:30:03
     */
    private String suggestion;

    /**
     * tb_daily_report.create_date
     * @ibatorgenerated 2015-01-27 15:30:03
     */
    private Date createDate;

    /**
     * tb_daily_report.contact_name
     * @ibatorgenerated 2015-01-27 15:30:03
     */
    private String contactName;

    /**
     * tb_daily_report.contact_position
     * @ibatorgenerated 2015-01-27 15:30:03
     */
    private String contactPosition;

    /**
     * tb_daily_report.contact_phone
     * @ibatorgenerated 2015-01-27 15:30:03
     */
    private String contactPhone;

    /**
     * tb_daily_report.contact_email
     * @ibatorgenerated 2015-01-27 15:30:03
     */
    private String contactEmail;

    /**
     * tb_daily_report.contact_wx
     * @ibatorgenerated 2015-01-27 15:30:03
     */
    private String contactWx;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(Integer jobCategory) {
        this.jobCategory = jobCategory;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getFollowUser() {
        return followUser;
    }

    public void setFollowUser(Long followUser) {
        this.followUser = followUser;
    }

    public Integer getFollowType() {
        return followType;
    }

    public void setFollowType(Integer followType) {
        this.followType = followType;
    }

    public String getFollowDetail() {
        return followDetail;
    }

    public void setFollowDetail(String followDetail) {
        this.followDetail = followDetail;
    }

    public String getFollowProblem() {
        return followProblem;
    }

    public void setFollowProblem(String followProblem) {
        this.followProblem = followProblem;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPosition() {
        return contactPosition;
    }

    public void setContactPosition(String contactPosition) {
        this.contactPosition = contactPosition;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactWx() {
        return contactWx;
    }

    public void setContactWx(String contactWx) {
        this.contactWx = contactWx;
    }
}