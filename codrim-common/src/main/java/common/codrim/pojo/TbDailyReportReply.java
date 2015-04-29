package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbDailyReportReply implements Serializable {
    /**
     * tb_daily_report_reply.id
     * @ibatorgenerated 2015-01-28 11:39:31
     */
    private Long id;

    /**
     * tb_daily_report_reply.report_id
     * @ibatorgenerated 2015-01-28 11:39:31
     */
    private Long reportId;

    /**
     * tb_daily_report_reply.reply_by
     * @ibatorgenerated 2015-01-28 11:39:31
     */
    private Long replyBy;

    /**
     * tb_daily_report_reply.reply_msg
     * @ibatorgenerated 2015-01-28 11:39:31
     */
    private String replyMsg;

    /**
     * tb_daily_report_reply.reply_date
     * @ibatorgenerated 2015-01-28 11:39:31
     */
    private Date replyDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public Long getReplyBy() {
        return replyBy;
    }

    public void setReplyBy(Long replyBy) {
        this.replyBy = replyBy;
    }

    public String getReplyMsg() {
        return replyMsg;
    }

    public void setReplyMsg(String replyMsg) {
        this.replyMsg = replyMsg;
    }

    public Date getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
    }
}