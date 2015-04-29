package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import common.codrim.util.CustomDateSerializer;

public class TbProductDetail implements Serializable {
	
	
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TbProductDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TbProductDetail(Long id, String productName, String productId,
			String appid, String customerName, String accessPrice,
			String putPrice, Date startTime, Date endTime,
			String productTrackingLink, String codrimTrackingLink,
			String channelContactName, String channelName,
			String channelRankingNumber, Short settlementWay, Short effectType,
			String channelPostBackLink, String codrimPostBackLink,
			Short runningStatus, String productProperty, String productType,
			String applyRequire, String personInCharge, String applyName,
			Date applyTime, String evaluateName, Short evaluateResult,
			Date evaluateTime, String deviceplate, String packetSize,
			String website, Short productIsback, Short channelIsback, Short isrankingproduct, Date addTime,String addName,
			String applyRemark, String evaluateRemark) {
		super();
		this.id = id;
		this.productName = productName;
		this.productId = productId;
		this.appid = appid;
		this.customerName = customerName;
		this.accessPrice = accessPrice;
		this.putPrice = putPrice;
		this.startTime = startTime;
		this.endTime = endTime;
		this.productTrackingLink = productTrackingLink;
		this.codrimTrackingLink = codrimTrackingLink;
		this.channelContactName = channelContactName;
		this.channelName = channelName;
		this.channelRankingNumber = channelRankingNumber;
		this.settlementWay = settlementWay;
		this.effectType = effectType;
		this.channelPostBackLink = channelPostBackLink;
		this.codrimPostBackLink = codrimPostBackLink;
		this.runningStatus = runningStatus;
		this.productProperty = productProperty;
		this.productType = productType;
		this.applyRequire = applyRequire;
		this.personInCharge = personInCharge;
		this.applyName = applyName;
		this.applyTime = applyTime;
		this.evaluateName = evaluateName;
		this.evaluateResult = evaluateResult;
		this.evaluateTime = evaluateTime;
		this.deviceplate = deviceplate;
		this.packetSize = packetSize;
		this.website = website;
		this.productIsback=productIsback;
		this.channelIsback=channelIsback;
		this.isrankingproduct = isrankingproduct;
		this.addTime = addTime;
		this.addName=addName;
		this.applyRemark = applyRemark;
		this.evaluateRemark = evaluateRemark;
	}

	public TbProductDetail(String productName, String productId, String appid,
			String customerName, String accessPrice, String putPrice,
			Date startTime, Date endTime, String productTrackingLink,
			String codrimTrackingLink, String channelContactName,
			String channelName, String channelRankingNumber,
			Short settlementWay, Short effectType, String channelPostBackLink,
			String codrimPostBackLink, Short runningStatus,
			String productProperty, String productType, String applyRequire,
			String personInCharge, String applyName, Date applyTime,
			String evaluateName, Short evaluateResult, Date evaluateTime,
			String deviceplate, String packetSize, String website,
			Short productIsback,Short channelIsback,  Short isrankingproduct, Date addTime,String addName,
			String applyRemark, String evaluateRemark) {
		super();
		this.productName = productName;
		this.productId = productId;
		this.appid = appid;
		this.customerName = customerName;
		this.accessPrice = accessPrice;
		this.putPrice = putPrice;
		this.startTime = startTime;
		this.endTime = endTime;
		this.productTrackingLink = productTrackingLink;
		this.codrimTrackingLink = codrimTrackingLink;
		this.channelContactName = channelContactName;
		this.channelName = channelName;
		this.channelRankingNumber = channelRankingNumber;
		this.settlementWay = settlementWay;
		this.effectType = effectType;
		this.channelPostBackLink = channelPostBackLink;
		this.codrimPostBackLink = codrimPostBackLink;
		this.runningStatus = runningStatus;
		this.productProperty = productProperty;
		this.productType = productType;
		this.applyRequire = applyRequire;
		this.personInCharge = personInCharge;
		this.applyName = applyName;
		this.applyTime = applyTime;
		this.evaluateName = evaluateName;
		this.evaluateResult = evaluateResult;
		this.evaluateTime = evaluateTime;
		this.deviceplate = deviceplate;
		this.packetSize = packetSize;
		this.website = website;
		this.productIsback=productIsback;
		this.channelIsback=channelIsback;
		this.isrankingproduct = isrankingproduct;
		this.addTime = addTime;
		this.addName=addName;
		this.applyRemark = applyRemark;
		this.evaluateRemark = evaluateRemark;
	}

	/**
     * tb_product_detail.id (自增id)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private Long id;
    
    
    
    private String addName;
    /**
     * tb_product_detail.product_name (产品名称)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private String productName;

    /**
     * tb_product_detail.product_id (产品id)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private String productId;

    /**
     * tb_product_detail.appId (appId)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private String appid;

    /**
     * tb_product_detail.customer_name (客户名称)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private String customerName;

    /**
     * tb_product_detail.access_price (接入单价)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private String accessPrice;

    /**
     * tb_product_detail.put_price (投放单价)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private String putPrice;

    /**
     * tb_product_detail.start_time (开始时间)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private Date startTime;

    /**
     * tb_product_detail.end_time (结束时间)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private Date endTime;

    /**
     * tb_product_detail.product_tracking_link (广告link)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private String productTrackingLink;

    /**
     * tb_product_detail.codrim_tracking_link (返回渠道link)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private String codrimTrackingLink;

    /**
     * tb_product_detail.channel_contact_name (冲榜渠道对应id)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private String channelContactName;

    /**
     * tb_product_detail.channel_name (渠道名)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private String channelName;

    /**
     * tb_product_detail.channel_ranking_number (冲榜对应渠道号)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private String channelRankingNumber;

    /**
     * tb_product_detail.settlement_way (结算方式)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private Short settlementWay;

    /**
     * tb_product_detail.effect_type (效果定义)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private Short effectType;

    /**
     * tb_product_detail.channel_post_back_link (渠道link)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private String channelPostBackLink;

    /**
     * tb_product_detail.codrim_post_back_link (返回广告link)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private String codrimPostBackLink;

    /**
     * tb_product_detail.running_status (运行状态 1：运行2：暂停3：下线)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private Short runningStatus;

    /**
     * tb_product_detail.product_property (产品性质)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private String productProperty;

    /**
     * tb_product_detail.product_type (产品类型)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private String productType;

    /**
     * tb_product_detail.apply_require (申请要求)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private String applyRequire;

    /**
     * tb_product_detail.person_in_charge (渠道负责人)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private String personInCharge;

    /**
     * tb_product_detail.apply_name (申请人)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private String applyName;

    /**
     * tb_product_detail.apply_time (申请时间)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private Date applyTime;

    /**
     * tb_product_detail.evaluate_name (评估人)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private String evaluateName;

    /**
     * tb_product_detail.evaluate_result (评估结果1 接入 2 不接入 3 待评估)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private Short evaluateResult;

    /**
     * tb_product_detail.evaluate_time (评估时间)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private Date evaluateTime;

    /**
     * tb_product_detail.deviceplate (平台类型  ios android)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private String deviceplate;

    /**
     * tb_product_detail.packet_size (包大小)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private String packetSize;

    /**
     * tb_product_detail.webSite (包地址)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private String website;

    /**
     * tb_product_detail.isBack (产品是否有后台 1 有后台 0 没有后台)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private Short productIsback;
    /**
     * tb_product_detail.isBack (渠道是否有后台 1 有后台 0 没有后台)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private Short channelIsback;
    
    /**
     * tb_product_detail.isRankingProduct (是否冲榜产品)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private Short isrankingproduct;

    /**
     * tb_product_detail.add_time (添加时间)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private Date addTime;

    /**
     * tb_product_detail.apply_remark (申请人备注)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private String applyRemark;

    /**
     * tb_product_detail.evaluate_remark (评估备注)
     * @ibatorgenerated 2015-01-08 16:40:43
     */
    private String evaluateRemark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAccessPrice() {
        return accessPrice;
    }

    public void setAccessPrice(String accessPrice) {
        this.accessPrice = accessPrice;
    }

    public String getPutPrice() {
        return putPrice;
    }

    public void setPutPrice(String putPrice) {
        this.putPrice = putPrice;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getProductTrackingLink() {
        return productTrackingLink;
    }

    public void setProductTrackingLink(String productTrackingLink) {
        this.productTrackingLink = productTrackingLink;
    }

    public String getCodrimTrackingLink() {
        return codrimTrackingLink;
    }

    public void setCodrimTrackingLink(String codrimTrackingLink) {
        this.codrimTrackingLink = codrimTrackingLink;
    }

    public String getChannelContactName() {
        return channelContactName;
    }

    public void setChannelContactName(String channelContactName) {
        this.channelContactName = channelContactName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelRankingNumber() {
        return channelRankingNumber;
    }

    public void setChannelRankingNumber(String channelRankingNumber) {
        this.channelRankingNumber = channelRankingNumber;
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

    public String getChannelPostBackLink() {
        return channelPostBackLink;
    }

    public void setChannelPostBackLink(String channelPostBackLink) {
        this.channelPostBackLink = channelPostBackLink;
    }

    public String getCodrimPostBackLink() {
        return codrimPostBackLink;
    }

    public void setCodrimPostBackLink(String codrimPostBackLink) {
        this.codrimPostBackLink = codrimPostBackLink;
    }

    public Short getRunningStatus() {
        return runningStatus;
    }

    public void setRunningStatus(Short runningStatus) {
        this.runningStatus = runningStatus;
    }

    public String getProductProperty() {
        return productProperty;
    }

    public void setProductProperty(String productProperty) {
        this.productProperty = productProperty;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getApplyRequire() {
        return applyRequire;
    }

    public void setApplyRequire(String applyRequire) {
        this.applyRequire = applyRequire;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getEvaluateName() {
        return evaluateName;
    }

    public void setEvaluateName(String evaluateName) {
        this.evaluateName = evaluateName;
    }

    public Short getEvaluateResult() {
        return evaluateResult;
    }

    public void setEvaluateResult(Short evaluateResult) {
        this.evaluateResult = evaluateResult;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(Date evaluateTime) {
        this.evaluateTime = evaluateTime;
    }

    public String getDeviceplate() {
        return deviceplate;
    }

    public void setDeviceplate(String deviceplate) {
        this.deviceplate = deviceplate;
    }

    public String getPacketSize() {
        return packetSize;
    }

    public void setPacketSize(String packetSize) {
        this.packetSize = packetSize;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }



    public Short getProductIsback() {
		return productIsback;
	}
	public void setProductIsback(Short productIsback) {
		this.productIsback = productIsback;
	}
	public Short getChannelIsback() {
		return channelIsback;
	}
	public void setChannelIsback(Short channelIsback) {
		this.channelIsback = channelIsback;
	}
	public Short getIsrankingproduct() {
        return isrankingproduct;
    }

    public void setIsrankingproduct(Short isrankingproduct) {
        this.isrankingproduct = isrankingproduct;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getApplyRemark() {
        return applyRemark;
    }

    public void setApplyRemark(String applyRemark) {
        this.applyRemark = applyRemark;
    }

    public String getEvaluateRemark() {
        return evaluateRemark;
    }

    public String getAddName() {
		return addName;
	}

	public void setAddName(String addName) {
		this.addName = addName;
	}

	public void setEvaluateRemark(String evaluateRemark) {
        this.evaluateRemark = evaluateRemark;
    }
}