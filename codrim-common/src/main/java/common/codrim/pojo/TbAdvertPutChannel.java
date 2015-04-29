package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import common.codrim.util.CustomDateSerializer;

public class TbAdvertPutChannel implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	
	public TbAdvertPutChannel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TbAdvertPutChannel(Long id, Long productId, Long advertId,
			Short putChannelType, Long putChannelId, Double putPrice,
			Date startTime, Date endTime, Short settlementWay,
			Short effectType, Short effectTypeBack, Short runningStatus,
			Short effectCallBackType, String effectCallBackParamName,
			String effectCallBackUrl, String remark, String personInCharge,
			Short isback, Date addTime) {
		super();
		this.id = id;
		this.productId = productId;
		this.advertId = advertId;
		this.putChannelType = putChannelType;
		this.putChannelId = putChannelId;
		this.putPrice = putPrice;
		this.startTime = startTime;
		this.endTime = endTime;
		this.settlementWay = settlementWay;
		this.effectType = effectType;
		this.effectTypeBack = effectTypeBack;
		this.runningStatus = runningStatus;
		this.effectCallBackType = effectCallBackType;
		this.effectCallBackParamName = effectCallBackParamName;
		this.effectCallBackUrl = effectCallBackUrl;
		this.remark = remark;
		this.personInCharge = personInCharge;
		this.isback = isback;
		this.addTime = addTime;
	}

	private Long id;

    private Long productId;

    private Long advertId;

    private Short putChannelType;

    private Long putChannelId;
private String channelName;
    private Double putPrice;

    private Date startTime;

    private Date endTime;

    private Short settlementWay;

    private Short effectType;

    private Short effectTypeBack;

    private Short runningStatus;

    private Short effectCallBackType;

    private String effectCallBackParamName;

    private String effectCallBackUrl;

    private String remark;

    private String personInCharge;

    private Short isback;

    private Date addTime;

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

    public Long getAdvertId() {
        return advertId;
    }

    public void setAdvertId(Long advertId) {
        this.advertId = advertId;
    }

    public Short getPutChannelType() {
        return putChannelType;
    }

    public void setPutChannelType(Short putChannelType) {
        this.putChannelType = putChannelType;
    }

    public Long getPutChannelId() {
        return putChannelId;
    }

    public void setPutChannelId(Long putChannelId) {
        this.putChannelId = putChannelId;
    }

    public Double getPutPrice() {
        return putPrice;
    }

    public void setPutPrice(Double putPrice) {
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

    public Short getSettlementWay() {
        return settlementWay;
    }

    public void setSettlementWay(Short settlementWay) {
        this.settlementWay = settlementWay;
    }

    public Short getEffectType() {
        return effectType;
    }

    public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
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

    public Short getRunningStatus() {
        return runningStatus;
    }

    public void setRunningStatus(Short runningStatus) {
        this.runningStatus = runningStatus;
    }

    public Short getEffectCallBackType() {
        return effectCallBackType;
    }

    public void setEffectCallBackType(Short effectCallBackType) {
        this.effectCallBackType = effectCallBackType;
    }

    public String getEffectCallBackParamName() {
        return effectCallBackParamName;
    }

    public void setEffectCallBackParamName(String effectCallBackParamName) {
        this.effectCallBackParamName = effectCallBackParamName;
    }

    public String getEffectCallBackUrl() {
        return effectCallBackUrl;
    }

    public void setEffectCallBackUrl(String effectCallBackUrl) {
        this.effectCallBackUrl = effectCallBackUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    public Short getIsback() {
        return isback;
    }

    public void setIsback(Short isback) {
        this.isback = isback;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}