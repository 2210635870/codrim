package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbWzThirdDeviceUid implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * tb_wz_third_device_uid.id
     * @ibatorgenerated 2015-04-16 10:58:56
     */
    private Long id;

    /**
     * tb_wz_third_device_uid.user_id (用户id)
     * @ibatorgenerated 2015-04-16 10:58:56
     */
    private Long userId;

    /**
     * tb_wz_third_device_uid.device_id (设备id)
     * @ibatorgenerated 2015-04-16 10:58:56
     */
    private String deviceId;

    /**
     * tb_wz_third_device_uid.unionid (社交墙第三方用户id)
     * @ibatorgenerated 2015-04-16 10:58:56
     */
    private String unionid;

    /**
     * tb_wz_third_device_uid.add_date (绑定时间)
     * @ibatorgenerated 2015-04-16 10:58:56
     */
    private Date addDate;

    /**
     * tb_wz_third_device_uid.nickname (第三方昵称)
     * @ibatorgenerated 2015-04-16 10:58:56
     */
    private String nickname;

    /**
     * tb_wz_third_device_uid.sex (用户的性别，值为1时是男性，值为2时是女性，值为0时是未知)
     * @ibatorgenerated 2015-04-16 10:58:56
     */
    private Short sex;

    /**
     * tb_wz_third_device_uid.country (国家，如中国为CN)
     * @ibatorgenerated 2015-04-16 10:58:56
     */
    private String country;
    
    
  
    /**
     * tb_wz_third_device_uid.province (省份)
     * @ibatorgenerated 2015-04-16 10:58:56
     */
    private String province;

    /**
     * tb_wz_third_device_uid.city (城市)
     * @ibatorgenerated 2015-04-16 10:58:56
     */
    private String city;

    /**
     * tb_wz_third_device_uid.privilege (用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）)
     * @ibatorgenerated 2015-04-16 10:58:56
     */
    private String privilege;

    /**
     * tb_wz_third_device_uid.headimgurl (用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。)
     * @ibatorgenerated 2015-04-16 10:58:56
     */
    private String headimgurl;

    
    private String difId;
    


	public String getDifId() {
		return difId;
	}

	public void setDifId(String difId) {
		this.difId = difId;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
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

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }
}