package com.codrim.bean;

import java.io.Serializable;

import common.codrim.wz.constant.DataConstant;

public class JsonErrorResult extends JsonBasicResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String errCode;
	
	private String errMsg;
	
	public JsonErrorResult() {
		super(DataConstant.FAIL);
	}

	public JsonErrorResult(String errCode) {
		this();
		this.errCode = errCode;
	}
	
	public JsonErrorResult(String errCode, String errMsg) {
		this(errCode);
		this.errMsg = errMsg;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
