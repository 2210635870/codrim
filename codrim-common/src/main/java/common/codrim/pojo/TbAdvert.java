package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import common.codrim.util.CustomDateSerializer;

public class TbAdvert implements Serializable {
	
	
	
	
	
	
	
    public TbAdvert() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TbAdvert(Long productId, Short advertName,
			String deviceplate, Short settlementWay, Short effectType,
			Short effectTypeBack, Short isBack, Double accessPrice,
			Short waysOfCooperation, String require, String createName,
			Date addTime, Short runningStatus, Short evaluateStatus) {
		super();
		this.productId = productId;
		this.advertName = advertName;
		this.deviceplate = deviceplate;
		this.settlementWay = settlementWay;
		this.effectType = effectType;
		this.effectTypeBack = effectTypeBack;
		this.isBack = isBack;
		this.accessPrice = accessPrice;
		this.waysOfCooperation = waysOfCooperation;
		this.require = require;
		this.createName = createName;
		this.addTime = addTime;
		this.runningStatus = runningStatus;
		this.evaluateStatus = evaluateStatus;
	}
	public TbAdvert(Long id, Long productId, Short advertName,
			String deviceplate, Short settlementWay, Short effectType,
			Short effectTypeBack, Short isBack, Double accessPrice,
			Short waysOfCooperation, String require, String createName,
			Date addTime, Short runningStatus, Short evaluateStatus) {
		super();
		this.id = id;
		this.productId = productId;
		this.advertName = advertName;
		this.deviceplate = deviceplate;
		this.settlementWay = settlementWay;
		this.effectType = effectType;
		this.effectTypeBack = effectTypeBack;
		this.isBack = isBack;
		this.accessPrice = accessPrice;
		this.waysOfCooperation = waysOfCooperation;
		this.require = require;
		this.createName = createName;
		this.addTime = addTime;
		this.runningStatus = runningStatus;
		this.evaluateStatus = evaluateStatus;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
     *  (产品名id)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private Short advertName;

    /**
     * tb_product_series.deviceplate (平台)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private String deviceplate;

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
     * tb_product_series.is_back (是否有后台) //备用
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
     * tb_product_series.operate_evaluate_name (运营评估人)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private String operateEvaluateName;

    /**
     * tb_product_series.operate_evaluate_time (运营评估时间)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private Date operateEvaluateTime;

    /**
     * tb_product_series.operate_evaluate_result (运营评估结果)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private Short operateEvaluateResult;

    /**
     * tb_product_series.operate_evaluate_price (运营评估单价)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private Double operateEvaluatePrice;

    /**
     * tb_product_series.operate_evaluate_remark_channel (运营评估备注-渠道)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private String operateEvaluateRemarkChannel;

    /**
     * tb_product_series.operate_evaluate_remark_business (运营评估备注-商务)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private String operateEvaluateRemarkBusiness;

    /**
     * tb_product_series.final_evaluate_name (最终评估)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private String finalEvaluateName;

    /**
     * tb_product_series.final_evaluate_result (最终评估结果)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private Short finalEvaluateResult;

    /**
     * tb_product_series.final_evaluate_remark (最终评估备注)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private String finalEvaluateRemark;

    /**
     * tb_product_series.add_time (添加时间)
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    private Date addTime;

    private Short runningStatus;
    private Short evaluateStatus;
    
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
	public Short getRunningStatus() {
		return runningStatus;
	}
    public Short getEvaluateStatus() {
		return evaluateStatus;
	}

	public void setEvaluateStatus(Short evaluateStatus) {
		this.evaluateStatus = evaluateStatus;
	}
	public void setRunningStatus(Short runningStatus) {
		this.runningStatus = runningStatus;
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

    public String getOperateEvaluateName() {
        return operateEvaluateName;
    }

    public void setOperateEvaluateName(String operateEvaluateName) {
        this.operateEvaluateName = operateEvaluateName;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getOperateEvaluateTime() {
        return operateEvaluateTime;
    }

    public void setOperateEvaluateTime(Date operateEvaluateTime) {
        this.operateEvaluateTime = operateEvaluateTime;
    }

    public Short getOperateEvaluateResult() {
        return operateEvaluateResult;
    }

    public void setOperateEvaluateResult(Short operateEvaluateResult) {
        this.operateEvaluateResult = operateEvaluateResult;
    }

    public Double getOperateEvaluatePrice() {
        return operateEvaluatePrice;
    }

    public void setOperateEvaluatePrice(Double operateEvaluatePrice) {
        this.operateEvaluatePrice = operateEvaluatePrice;
    }

    public String getOperateEvaluateRemarkChannel() {
        return operateEvaluateRemarkChannel;
    }

    public void setOperateEvaluateRemarkChannel(String operateEvaluateRemarkChannel) {
        this.operateEvaluateRemarkChannel = operateEvaluateRemarkChannel;
    }

    public String getOperateEvaluateRemarkBusiness() {
        return operateEvaluateRemarkBusiness;
    }

    public void setOperateEvaluateRemarkBusiness(String operateEvaluateRemarkBusiness) {
        this.operateEvaluateRemarkBusiness = operateEvaluateRemarkBusiness;
    }

    public String getFinalEvaluateName() {
        return finalEvaluateName;
    }

    public void setFinalEvaluateName(String finalEvaluateName) {
        this.finalEvaluateName = finalEvaluateName;
    }

    public Short getFinalEvaluateResult() {
        return finalEvaluateResult;
    }

    public void setFinalEvaluateResult(Short finalEvaluateResult) {
        this.finalEvaluateResult = finalEvaluateResult;
    }

    public String getFinalEvaluateRemark() {
        return finalEvaluateRemark;
    }

    public void setFinalEvaluateRemark(String finalEvaluateRemark) {
        this.finalEvaluateRemark = finalEvaluateRemark;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}