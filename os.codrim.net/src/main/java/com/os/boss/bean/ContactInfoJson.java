package com.os.boss.bean;

import java.io.Serializable;

public class ContactInfoJson implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String contactName;
	
	private String contactPosition;

    private String contactPhone;

    private String contactEmail;
    
    private String contactWx;
    
    
	public ContactInfoJson() {
		super();
	}

	public ContactInfoJson(String contactName, String contactPosition,
			String contactPhone, String contactEmail, String contactWx) {
		super();
		this.contactName = contactName;
		this.contactPosition = contactPosition;
		this.contactPhone = contactPhone;
		this.contactEmail = contactEmail;
		this.contactWx = contactWx;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPosition() {
		return contactPosition;
	}

	public void setContactPosition(String contactPosition) {
		this.contactPosition = contactPosition;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactWx() {
		return contactWx;
	}

	public void setContactWx(String contactWx) {
		this.contactWx = contactWx;
	} 

}
