package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbWzReview implements Serializable {
    /**
     * tb_wz_review.review_id
     * @ibatorgenerated 2014-12-25 20:21:07
     */
    private Long reviewId;

    /**
     * tb_wz_review.review_type
     * @ibatorgenerated 2014-12-25 20:21:07
     */
    private Integer reviewType;

    /**
     * tb_wz_review.review_target
     * @ibatorgenerated 2014-12-25 20:21:07
     */
    private Long reviewTarget;

    /**
     * tb_wz_review.review_status
     * @ibatorgenerated 2014-12-25 20:21:07
     */
    private Integer reviewStatus;

    /**
     * tb_wz_review.review_remark
     * @ibatorgenerated 2014-12-25 20:21:07
     */
    private String reviewRemark;

    /**
     * tb_wz_review.review_by
     * @ibatorgenerated 2014-12-25 20:21:07
     */
    private Long reviewBy;

    /**
     * tb_wz_review.review_date
     * @ibatorgenerated 2014-12-25 20:21:07
     */
    private Date reviewDate;

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getReviewType() {
        return reviewType;
    }

    public void setReviewType(Integer reviewType) {
        this.reviewType = reviewType;
    }

    public Long getReviewTarget() {
        return reviewTarget;
    }

    public void setReviewTarget(Long reviewTarget) {
        this.reviewTarget = reviewTarget;
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

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
}