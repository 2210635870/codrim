package common.codrim.wz.sql.result;

import java.io.Serializable;

import common.codrim.pojo.TbWzGroup;

public class GroupInfo extends TbWzGroup implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String leaderName;
	
	private String leaderIcon;
	
	private int groupUserNo;
	
	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public int getGroupUserNo() {
		return groupUserNo;
	}

	public void setGroupUserNo(int groupUserNo) {
		this.groupUserNo = groupUserNo;
	}

	public String getLeaderIcon() {
		return leaderIcon;
	}

	public void setLeaderIcon(String leaderIcon) {
		this.leaderIcon = leaderIcon;
	}
	
}
