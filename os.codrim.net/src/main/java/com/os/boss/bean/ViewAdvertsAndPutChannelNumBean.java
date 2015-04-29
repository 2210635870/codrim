/**
 * 
 */
package com.os.boss.bean;

import java.util.Date;

/**
 * @author Administrator
 *
 */
public class ViewAdvertsAndPutChannelNumBean {
    /**
     * tb_product_series.id
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private Long id;

    /**
     * tb_product_series.product_id (产品id)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private Long productId;

    /**
     * tb_product_series.series_id (名id)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private Short advertName;

    /**
     * tb_product_series.deviceplate (平台)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private String deviceplate;
   
    
    private Short evaluateStatus;
    
    private Short runningStatus;
    
    public Short getEvaluateStatus() {
		return evaluateStatus;
	}

	public void setEvaluateStatus(Short evaluateStatus) {
		this.evaluateStatus = evaluateStatus;
	}

	public Short getRunningStatus() {
		return runningStatus;
	}

	public void setRunningStatus(Short runningStatus) {
		this.runningStatus = runningStatus;
	}

	/**
     * tb_product_series.settlement_way (结算方式)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private Short settlementWay;

    /**
     * tb_product_series.effect_type (效果定义)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private Short effectType;

    /**
     * tb_product_series.effect_type_back (效果定义2)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private Short effectTypeBack;

    /**
     * tb_product_series.is_back (是否有后台)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private Short isBack;

    /**
     * tb_product_series.access_price (接入单价)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private Double accessPrice;

    /**
     * tb_product_series.ways_of_cooperation (合作方式)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private Short waysOfCooperation;

    /**
     * tb_product_series.require (要求)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private String require;

    /**
     * tb_product_series.create_name (添加人)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private String createName;
    /**
     * tb_product_series.add_time (添加时间)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private Date addTime;
    
    private int putChannelNums;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Short getAdvertName() {
		return advertName;
	}

	public void setAdvertName(Short advertName) {
		this.advertName = advertName;
	}

	public String getDeviceplate() {
		return deviceplate;
	}

	public void setDeviceplate(String deviceplate) {
		this.deviceplate = deviceplate;
	}

	public Short getSettlementWay() {
		return settlementWay;
	}

	public void setSettlementWay(Short settlementWay) {
		this.settlementWay = settlementWay;
	}

	public Short getEffectType() {
		return effectType;
	}

	public void setEffectType(Short effectType) {
		this.effectType = effectType;
	}

	public Short getEffectTypeBack() {
		return effectTypeBack;
	}

	public void setEffectTypeBack(Short effectTypeBack) {
		this.effectTypeBack = effectTypeBack;
	}

	public Short getIsBack() {
		return isBack;
	}

	public void setIsBack(Short isBack) {
		this.isBack = isBack;
	}

	public Double getAccessPrice() {
		return accessPrice;
	}

	public void setAccessPrice(Double accessPrice) {
		this.accessPrice = accessPrice;
	}

	public Short getWaysOfCooperation() {
		return waysOfCooperation;
	}

	public void setWaysOfCooperation(Short waysOfCooperation) {
		this.waysOfCooperation = waysOfCooperation;
	}

	public String getRequire() {
		return require;
	}

	public void setRequire(String require) {
		this.require = require;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public int getPutChannelNums() {
		return putChannelNums;
	}

	public void setPutChannelNums(int putChannelNums) {
		this.putChannelNums = putChannelNums;
	}

}
