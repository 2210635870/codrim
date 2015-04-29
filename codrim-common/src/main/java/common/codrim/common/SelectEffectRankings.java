package common.codrim.common;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import common.codrim.util.CustomDateSerializer;

public class SelectEffectRankings implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;//id
  
    private String productName;//产品名称
   
    private String appid;//ios标识
  
    private String channelName;//渠道名称
  
    private Date actionTime;//行为产生时间 时分秒
 
    private String deviceplate;//设备系统 ios android
   
    private String telephone;//手机号    新加  
    
    private String idfa;//标识
  
    private String mac;//标识
  
    private String imei;//标识
  
    private String udid;//标识
    
    private String clickNum;//点击数
    
    private String effect;//效果      android设备系统中没有此字段
    
    private String activate;//是否激活
    
    private String register;//是否注册
    
    private String consumeOne;//首次消费  
  
    private String consumeMore;//  消费两次及以上
    
    private String recharge;//充值
    
    private String credit;//绑卡
    
    private String baddept;//坏账
    
    private String remark;//备注
    
    private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

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

	public String getAppid() {
		return appid;
	}
	


	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getActionTime() {
		return actionTime;
	}

	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}

	public String getDeviceplate() {
		return deviceplate;
	}

	public void setDeviceplate(String deviceplate) {
		this.deviceplate = deviceplate;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getIdfa() {
		return idfa;
	}

	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getUdid() {
		return udid;
	}

	public void setUdid(String udid) {
		this.udid = udid;
	}

	public String getClickNum() {
		return clickNum;
	}

	public void setClickNum(String clickNum) {
		this.clickNum = clickNum;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public String getActivate() {
		return activate;
	}

	public void setActivate(String activate) {
		this.activate = activate;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getConsumeOne() {
		return consumeOne;
	}

	public void setConsumeOne(String consumeOne) {
		this.consumeOne = consumeOne;
	}

	public String getConsumeMore() {
		return consumeMore;
	}

	public void setConsumeMore(String consumeMore) {
		this.consumeMore = consumeMore;
	}

	public String getRecharge() {
		return recharge;
	}

	public void setRecharge(String recharge) {
		this.recharge = recharge;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getBaddept() {
		return baddept;
	}

	public void setBaddept(String baddept) {
		this.baddept = baddept;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
