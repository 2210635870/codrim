package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import common.codrim.util.CustomDateSerializer;

public class TbEffectRankings implements Serializable {
	/**
     * tb_effect_rankings.id (自增id)
     * @ibatorgenerated 2014-12-15
     */
    private Long id;
	/**
     * tb_effect_rankings.telephone (手机号)
     * @ibatorgenerated 2014-12-15
     */
    private String telephone;
    /**
     * tb_effect_rankings.product_name (产品名称)
     * @ibatorgenerated 2014-12-15
     */
    private String productName;
    /**
     * tb_effect_rankings.appid (ios标识)
     * @ibatorgenerated 2014-12-15
     */
    private String appid;
    /**
     * tb_effect_rankings.channel_name (渠道名称)
     * @ibatorgenerated 2014-12-15
     */
    private String channelName;
  
    /**
     * tb_effect_rankings.action_time (行为产生时间 时分秒)
     * @ibatorgenerated 2014-12-15
     */
    private Date actionTime;
    /**
     * tb_effect_rankings.deviceplate (设备系统 ios androi)
     * @ibatorgenerated 2014-12-15
     */
    private String deviceplate;
    /**
     * tb_effect_rankings.idfa (标识)
     * @ibatorgenerated 2014-12-15
     */
    private String idfa;
    /**
     * tb_effect_rankings.mac (标识)
     * @ibatorgenerated 2014-12-15
     */
    private String mac;
    /**
     * tb_effect_rankings.imei (标识)
     * @ibatorgenerated 2014-12-15
     */
    private String imei;
    /**
     * tb_effect_rankings.udid (标识)
     * @ibatorgenerated 2014-12-15
     */
    private String udid;
    
    private String ip;
    
    private String country;
    
    private String province;
    private String city;
    
    /**
     * tb_effect_rankings.rankings_action (是否充值,绑卡，消费，注册，激活，坏账等)
     * @ibatorgenerated 2014-12-16
     */
    private short rankingsAction;
    /**
     * tb_effect_rankings.remark (备注)
     * @ibatorgenerated 2014-12-15
     */
    private String remark;

    private Short  registerUse;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


	public Short getRegisterUse() {
		return registerUse;
	}

	public void setRegisterUse(Short registerUse) {
		this.registerUse = registerUse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

    public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

    public short getRankingsAction() {
		return rankingsAction;
	}

	public void setRankingsAction(short rankingsAction) {
		this.rankingsAction = rankingsAction;
	}

	public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}