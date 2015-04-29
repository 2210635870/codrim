package com.dandelion.bean;

import java.io.Serializable;

public class JsonUserInfo  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long userId;
	
	private long  points;

	private long todayPoints;
	private String unionid;
    private short isBindingDeviceId;
    private String difId;
    
    private long id;
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDifId() {
		return difId;
	}

	public void setDifId(String difId) {
		this.difId = difId;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public short getIsBindingDeviceId() {
		return isBindingDeviceId;
	}

	public void setIsBindingDeviceId(short isBindingDeviceId) {
		this.isBindingDeviceId = isBindingDeviceId;
	}

	public long getPoints() {
		return points;
	}

	public void setPoints(long points) {
		this.points = points;
	}

	public long getTodayPoints() {
		return todayPoints;
	}

	public void setTodayPoints(long todayPoints) {
		this.todayPoints = todayPoints;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}


	
	
	
}
