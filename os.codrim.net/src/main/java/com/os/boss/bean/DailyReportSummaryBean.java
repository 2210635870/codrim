package com.os.boss.bean;

import java.util.Date;


public class DailyReportSummaryBean {
	
	private String followUsername;
	
	private Date createDate;
	
	private int count01;
	
	private int count02;
	
	private int count03;
	
	private int count04;
	
	private int count05;
	
	private int count06;
	
	private int count07;
	
	private int countElse;
	
	private int countTotal;
	

	public DailyReportSummaryBean() {
		super();
	}

	public DailyReportSummaryBean(String followUsername, Date createDate) {
		super();
		this.followUsername = followUsername;
		this.createDate = createDate;
	}

	public String getFollowUsername() {
		return followUsername;
	}

	public void setFollowUsername(String followUsername) {
		this.followUsername = followUsername;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getCount01() {
		return count01;
	}

	public void setCount01(int count01) {
		this.count01 = count01;
	}

	public int getCount02() {
		return count02;
	}

	public void setCount02(int count02) {
		this.count02 = count02;
	}

	public int getCount03() {
		return count03;
	}

	public void setCount03(int count03) {
		this.count03 = count03;
	}

	public int getCount04() {
		return count04;
	}

	public void setCount04(int count04) {
		this.count04 = count04;
	}

	public int getCount05() {
		return count05;
	}

	public void setCount05(int count05) {
		this.count05 = count05;
	}
	
	public int getCount06() {
		return count06;
	}

	public void setCount06(int count06) {
		this.count06 = count06;
	}

	public int getCount07() {
		return count07;
	}

	public void setCount07(int count07) {
		this.count07 = count07;
	}

	public int getCountElse() {
		return countElse;
	}

	public void setCountElse(int countElse) {
		this.countElse = countElse;
	}

	public int getCountTotal() {
		return countTotal;
	}

	public void setCountTotal(int countTotal) {
		this.countTotal = countTotal;
	}

	

}
