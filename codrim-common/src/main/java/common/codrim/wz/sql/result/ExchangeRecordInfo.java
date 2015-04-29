package common.codrim.wz.sql.result;

import java.io.Serializable;
import java.util.Date;

import common.codrim.pojo.TbWzExchangeRecord;
import common.codrim.util.DateUtil;

public class ExchangeRecordInfo extends TbWzExchangeRecord implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private String userPhone;
	
	private String userEmail;

	private Date exchangeStartDate;
	private Date exchangeEndDate;
	private Date reviewStartDate;
	private Date reviewEndDate;
	 
	public Date getExchangeStartDate() {
		return exchangeStartDate;
	}

	public void setExchangeStartDate(Date exchangeStartDate) {
		this.exchangeStartDate = exchangeStartDate;
	}

	public Date getExchangeEndDate() {
		return exchangeEndDate;
	}

	public void setExchangeEndDate(Date exchangeEndDate) {
		this.exchangeEndDate = exchangeEndDate;
	}

	public Date getReviewStartDate() {
		return reviewStartDate;
	}

	public void setReviewStartDate(Date reviewStartDate) {
		this.reviewStartDate = reviewStartDate;
	}

	public Date getReviewEndDate() {
		return reviewEndDate;
	}

	public void setReviewEndDate(Date reviewEndDate) {
		this.reviewEndDate = reviewEndDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	
}
