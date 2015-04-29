package com.codrim.bean.group;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.codrim.bean.user.JsonUserDetail;

public class JsonGroupDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long groupId;
	
	private String groupIcon;
	
	private String groupName;
	
	private String groupDesc;
	
	private String groupUserNo;
	
	private String groupIncome;
	
	private String leaderName;
	
	private String leaderIcon;
	
	private String leaderRatio;
	
	private String userRole;
	
	private String hasApplication;
	
	List<JsonUserDetail> groupUsers = new ArrayList<JsonUserDetail>();
	
	public void addGroupUser(JsonUserDetail user) {
		groupUsers.add(user);
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
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

	public String getGroupUserNo() {
		return groupUserNo;
	}

	public void setGroupUserNo(String groupUserNo) {
		this.groupUserNo = groupUserNo;
	}

	public String getGroupIncome() {
		return groupIncome;
	}

	public void setGroupIncome(String groupIncome) {
		this.groupIncome = groupIncome;
	}

	public String getLeaderRatio() {
		return leaderRatio;
	}

	public void setLeaderRatio(String leaderRatio) {
		this.leaderRatio = leaderRatio;
	}

	public List<JsonUserDetail> getGroupUsers() {
		return groupUsers;
	}

	public void setGroupUsers(List<JsonUserDetail> groupUsers) {
		this.groupUsers = groupUsers;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getLeaderIcon() {
		return leaderIcon;
	}

	public void setLeaderIcon(String leaderIcon) {
		this.leaderIcon = leaderIcon;
	}

	public String getGroupIcon() {
		return groupIcon;
	}

	public void setGroupIcon(String groupIcon) {
		this.groupIcon = groupIcon;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getHasApplication() {
		return hasApplication;
	}

	public void setHasApplication(String hasApplication) {
		this.hasApplication = hasApplication;
	}
	
}
