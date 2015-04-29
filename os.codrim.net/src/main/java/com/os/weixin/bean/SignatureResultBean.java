package com.os.weixin.bean;

public class SignatureResultBean {
private String code;
private String signature;
private long timestamp;// 必填，生成签名的时间戳
private String nonceStr;
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getSignature() {
	return signature;
}
public void setSignature(String signature) {
	this.signature = signature;
}
public long getTimestamp() {
	return timestamp;
}
public void setTimestamp(long timestamp) {
	this.timestamp = timestamp;
}
public String getNonceStr() {
	return nonceStr;
}
public void setNonceStr(String nonceStr) {
	this.nonceStr = nonceStr;
}

}
