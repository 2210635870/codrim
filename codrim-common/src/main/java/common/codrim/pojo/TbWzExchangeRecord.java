package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import common.codrim.util.CustomDateSerializer;

public class TbWzExchangeRecord implements Serializable {
    /**
     * tb_wz_exchange_record.record_id
     * @ibatorgenerated 2015-01-06 21:18:49
     */
    private Long recordId;

    /**
     * tb_wz_exchange_record.user_id
     * @ibatorgenerated 2015-01-06 21:18:49
     */
    private Long userId;

    /**
     * tb_wz_exchange_record.exchange_type
     * @ibatorgenerated 2015-01-06 21:18:49
     */
    private Integer exchangeType;

    /**
     * tb_wz_exchange_record.exchange_gold
     * @ibatorgenerated 2015-01-06 21:18:49
     */
    private Long exchangeGold;

    /**
     * tb_wz_exchange_record.exchange_money
     * @ibatorgenerated 2015-01-06 21:18:49
     */
    private Double exchangeMoney;

    /**
     * tb_wz_exchange_record.exchange_date
     * @ibatorgenerated 2015-01-06 21:18:49
     */
    private Date exchangeDate;

    /**
     * tb_wz_exchange_record.zfb_account
     * @ibatorgenerated 2015-01-06 21:18:49
     */
    private String zfbAccount;

    /**
     * tb_wz_exchange_record.phone_number
     * @ibatorgenerated 2015-01-06 21:18:49
     */
    private String phoneNumber;

    /**
     * tb_wz_exchange_record.review_status
     * @ibatorgenerated 2015-01-06 21:18:49
     */
    private Integer reviewStatus;

    /**
     * tb_wz_exchange_record.review_remark
     * @ibatorgenerated 2015-01-06 21:18:49
     */
    private String reviewRemark;

    /**
     * tb_wz_exchange_record.review_by
     * @ibatorgenerated 2015-01-06 21:18:49
     */
    private Long reviewBy;

    /**
     * tb_wz_exchange_record.review_date
     * @ibatorgenerated 2015-01-06 21:18:49
     */
    private Date reviewDate;

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(Integer exchangeType) {
        this.exchangeType = exchangeType;
    }

    public Long getExchangeGold() {
        return exchangeGold;
    }

    public void setExchangeGold(Long exchangeGold) {
        this.exchangeGold = exchangeGold;
    }

    public Double getExchangeMoney() {
        return exchangeMoney;
    }

    public void setExchangeMoney(Double exchangeMoney) {
        this.exchangeMoney = exchangeMoney;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(Date exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public String getZfbAccount() {
        return zfbAccount;
    }

    public void setZfbAccount(String zfbAccount) {
        this.zfbAccount = zfbAccount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Integer reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getReviewRemark() {
        return reviewRemark;
    }

    public void setReviewRemark(String reviewRemark) {
        this.reviewRemark = reviewRemark;
    }

    public Long getReviewBy() {
        return reviewBy;
    }

    public void setReviewBy(Long reviewBy) {
        this.reviewBy = reviewBy;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
}