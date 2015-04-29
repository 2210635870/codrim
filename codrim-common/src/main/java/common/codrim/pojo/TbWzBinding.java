package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbWzBinding implements Serializable {
    /**
     * tb_wz_binding.binding_id
     * @ibatorgenerated 2015-01-08 13:29:38
     */
    private Long bindingId;

    /**
     * tb_wz_binding.user_id
     * @ibatorgenerated 2015-01-08 13:29:38
     */
    private Long userId;

    /**
     * tb_wz_binding.device_id
     * @ibatorgenerated 2015-01-08 13:29:38
     */
    private String deviceId;

    /**
     * tb_wz_binding.device_plat
     * @ibatorgenerated 2015-01-08 13:29:38
     */
    private Integer devicePlat;

    /**
     * tb_wz_binding.ip
     * @ibatorgenerated 2015-01-08 13:29:38
     */
    private String ip;

    /**
     * tb_wz_binding.mac
     * @ibatorgenerated 2015-01-08 13:29:38
     */
    private String mac;

    /**
     * tb_wz_binding.country
     * @ibatorgenerated 2015-01-08 13:29:38
     */
    private String country;

    /**
     * tb_wz_binding.region
     * @ibatorgenerated 2015-01-08 13:29:38
     */
    private String region;

    /**
     * tb_wz_binding.city
     * @ibatorgenerated 2015-01-08 13:29:38
     */
    private String city;

    /**
     * tb_wz_binding.isp
     * @ibatorgenerated 2015-01-08 13:29:38
     */
    private String isp;

    /**
     * tb_wz_binding.binding_date
     * @ibatorgenerated 2015-01-08 13:29:38
     */
    private Date bindingDate;

    
    
    private String channelName;
    
    public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Long getBindingId() {
        return bindingId;
    }

    public void setBindingId(Long bindingId) {
        this.bindingId = bindingId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getDevicePlat() {
        return devicePlat;
    }

    public void setDevicePlat(Integer devicePlat) {
        this.devicePlat = devicePlat;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public Date getBindingDate() {
        return bindingDate;
    }

    public void setBindingDate(Date bindingDate) {
        this.bindingDate = bindingDate;
    }
}