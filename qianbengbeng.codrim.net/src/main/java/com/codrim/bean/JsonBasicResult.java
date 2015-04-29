package com.codrim.bean;

import java.io.Serializable;

public class JsonBasicResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * result code, "success" or "fail"
	 */
	private String rtCode;
	
	private String isLimit;
	/**
	 * data
	 */
	private T result;
	
	
	public String getIsLimit() {
		return isLimit;
	}

	public void setIsLimit(String isLimit) {
		this.isLimit = isLimit;
	}

	public JsonBasicResult() {
		super();
	}

	public JsonBasicResult(String rtCode) {
		super();
		this.rtCode = rtCode;
	}

	public String getRtCode() {
		return rtCode;
	}

	public void setRtCode(String rtCode) {
		this.rtCode = rtCode;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
	
}
