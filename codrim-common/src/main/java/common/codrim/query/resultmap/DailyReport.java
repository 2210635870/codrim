package common.codrim.query.resultmap;

import java.io.Serializable;
import java.util.Date;

import common.codrim.pojo.TbDailyReport;

public class DailyReport extends TbDailyReport implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String followUsername;
	
	private String customerName;
	
	private String channelName;
	
	private String createDateFrom;
	
	private String createDateTo;
	
	private int reportCount;
	
	private int reportTotal;
	
	private String latestReplyBy;
	
	private Date latestReplyDate;
	
	private String latestReplyStatus;

	public String getFollowUsername() {
		return followUsername;
	}

	public void setFollowUsername(String followUsername) {
		this.followUsername = followUsername;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCreateDateFrom() {
		return createDateFrom;
	}

	public void setCreateDateFrom(String createDateFrom) {
		this.createDateFrom = createDateFrom;
	}

	public String getCreateDateTo() {
		return createDateTo;
	}

	public void setCreateDateTo(String createDateTo) {
		this.createDateTo = createDateTo;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public int getReportCount() {
		return reportCount;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	public int getReportTotal() {
		return reportTotal;
	}

	public void setReportTotal(int reportTotal) {
		this.reportTotal = reportTotal;
	}

	public String getLatestReplyBy() {
		return latestReplyBy;
	}

	public void setLatestReplyBy(String latestReplyBy) {
		this.latestReplyBy = latestReplyBy;
	}

	public Date getLatestReplyDate() {
		return latestReplyDate;
	}

	public void setLatestReplyDate(Date latestReplyDate) {
		this.latestReplyDate = latestReplyDate;
	}

	public String getLatestReplyStatus() {
		return latestReplyStatus;
	}

	public void setLatestReplyStatus(String latestReplyStatus) {
		this.latestReplyStatus = latestReplyStatus;
	}
	
	

}
