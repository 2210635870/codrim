package common.codrim.query.resultmap;

import java.io.Serializable;

import common.codrim.pojo.TbDailyReportReply;

public class DailyReportReply extends TbDailyReportReply implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private String replyTime;
	
	private boolean isMyReply;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}

	public boolean isMyReply() {
		return isMyReply;
	}

	public void setMyReply(boolean isMyReply) {
		this.isMyReply = isMyReply;
	}
	
	
}
