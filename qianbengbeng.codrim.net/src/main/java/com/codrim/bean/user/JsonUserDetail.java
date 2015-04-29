package com.codrim.bean.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.codrim.bean.task.JsonTaskRecord;

public class JsonUserDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long userId;
	
	private String username;
	
	private String icon;
	
	private String balance;
	
	private String phoneNumber;
	
	private String email;
	
	private String lastLoginDate;
	
	private List<JsonTaskRecord> taskRecords = new ArrayList<JsonTaskRecord>();
	
	public void addTaskRecord(JsonTaskRecord record) {
		taskRecords.add(record);
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public List<JsonTaskRecord> getTaskRecords() {
		return taskRecords;
	}

	public void setTaskRecords(List<JsonTaskRecord> taskRecords) {
		this.taskRecords = taskRecords;
	}

}
