package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbRandomSendNum implements Serializable {
	/**
	 * tb_random_send_num.id
	 * 
	 * @ibatorgenerated 2014-12-17 11:41:19
	 */
	private Long id;

	/**
	 * tb_random_send_num.mobile_mark (手机标识)
	 * 
	 * @ibatorgenerated 2014-12-17 11:41:19
	 */
	private String mobileMark;

	/**
	 * tb_random_send_num.product_name (产品)
	 * 
	 * @ibatorgenerated 2014-12-17 11:41:19
	 */
	private String productName;

	/**
	 * tb_random_send_num.channel_name (渠道)
	 * 
	 * @ibatorgenerated 2014-12-17 11:41:19
	 */
	private String channelName;
	/**
	 * tb_random_send_num.channel_name (二级渠道)
	 * 
	 * @ibatorgenerated 2014-12-17 11:41:19
	 */
	private String channelNameTwo;
	/**
	 * tb_random_send_num.random_num (已发送次数)
	 * 
	 * @ibatorgenerated 2014-12-17 11:41:19
	 */
	private Integer sendNum;
	/**
	 * tb_random_send_num.random_num (随机发送次数)
	 * 
	 * @ibatorgenerated 2014-12-17 11:41:19
	 */
	private Integer randomNum;

	/**
	 * tb_random_send_num.time (产生时间)
	 * 
	 * @ibatorgenerated 2014-12-17 11:41:19
	 */
	private Date time;
 
	private String callBackUrl;
	
	public String getCallBackUrl() {
		return callBackUrl;
	}

	public void setCallBackUrl(String callBackUrl) {
		this.callBackUrl = callBackUrl;
	}

	private Short isToChanel;
	public String getChannelNameTwo() {
		return channelNameTwo;
	}

	public void setChannelNameTwo(String channelNameTwo) {
		this.channelNameTwo = channelNameTwo;
	}

	public Short getIsToChanel() {
		return isToChanel;
	}

	public void setIsToChanel(Short isToChanel) {
		this.isToChanel = isToChanel;
	}

	public Integer getSendNum() {
		return sendNum;
	}

	public void setSendNum(Integer sendNum) {
		this.sendNum = sendNum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMobileMark() {
		return mobileMark;
	}

	public void setMobileMark(String mobileMark) {
		this.mobileMark = mobileMark;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Integer getRandomNum() {
		return randomNum;
	}

	public void setRandomNum(Integer randomNum) {
		this.randomNum = randomNum;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}