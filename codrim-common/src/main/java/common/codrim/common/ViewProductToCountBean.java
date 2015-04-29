/**
 * 
 */
package common.codrim.common;

/**
 * @author Administrator
 *
 */
public class ViewProductToCountBean {
private long id;
private String productName;
private String customerName;
private String productIden;
private String  productType;
private String createName;
private String advertNum;
private String waitEvaluateNum;
private String countPutChannels;
private String countRunningPutChannels;
public long getId() {
	return id;
}

public String getCreateName() {
	return createName;
}

public void setCreateName(String createName) {
	this.createName = createName;
}

public void setId(long id) {
	this.id = id;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public String getCustomerName() {
	return customerName;
}
public void setCustomerName(String customerName) {
	this.customerName = customerName;
}
public String getProductIden() {
	return productIden;
}
public void setProductIden(String productIden) {
	this.productIden = productIden;
}
public String getProductType() {
	return productType;
}
public void setProductType(String productType) {
	this.productType = productType;
}

public String getWaitEvaluateNum() {
	return waitEvaluateNum;
}
public void setWaitEvaluateNum(String waitEvaluateNum) {
	this.waitEvaluateNum = waitEvaluateNum;
}

public String getAdvertNum() {
	return advertNum;
}

public void setAdvertNum(String advertNum) {
	this.advertNum = advertNum;
}

public String getCountPutChannels() {
	return countPutChannels;
}

public void setCountPutChannels(String countPutChannels) {
	this.countPutChannels = countPutChannels;
}

public String getCountRunningPutChannels() {
	return countRunningPutChannels;
}

public void setCountRunningPutChannels(String countRunningPutChannels) {
	this.countRunningPutChannels = countRunningPutChannels;
}


}
