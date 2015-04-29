/**
 * 
 */
package com.os.boss.bean;


/**
 * @author Administrator
 *
 */
public class RecordDataBean {
	private long  id;
	private String accessPrice;
	private String putPrice;
	private short productIsBack;
	private Short channelIsBack;
	private long effectNum;
	private long clickNum;
    /**
     * tb_rankings_report.effect_num_with_deduplication (去重之后数据)
     * @ibatorgenerated 2015-01-14 10:36:48
     */
    private Long effectNumWithDeduplication;

    /**
     * tb_rankings_report.deduplication_num (重复数)
     * @ibatorgenerated 2015-01-14 10:36:48
     */
    private Long deduplicationNum;

    /**
     * tb_rankings_report.deduplication_rate (重复率)
     * @ibatorgenerated 2015-01-14 10:36:48
     */
    private String deduplicationRate;

    /**
     * tb_rankings_report.subtract_num (核减数)
     * @ibatorgenerated 2015-01-14 10:36:48
     */
    private Integer subtractNum;

    /**
     * tb_rankings_report.channel_external_num (渠道开放显示数据)
     * @ibatorgenerated 2015-01-14 10:36:48
     */
    private Long channelExternalNum;

    /**
     * tb_rankings_report.isSubtract (是否已核减)
     * @ibatorgenerated 2015-01-14 10:36:48
     */
    private Short issubtract;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public short getProductIsBack() {
		return productIsBack;
	}

	public void setProductIsBack(short productIsBack) {
		this.productIsBack = productIsBack;
	}

	public Short getChannelIsBack() {
		return channelIsBack;
	}

	public void setChannelIsBack(Short channelIsBack) {
		this.channelIsBack = channelIsBack;
	}

	public long getEffectNum() {
		return effectNum;
	}

	public void setEffectNum(long effectNum) {
		this.effectNum = effectNum;
	}

	public long getClickNum() {
		return clickNum;
	}

	public void setClickNum(long clickNum) {
		this.clickNum = clickNum;
	}

	public Long getEffectNumWithDeduplication() {
		return effectNumWithDeduplication;
	}

	public void setEffectNumWithDeduplication(Long effectNumWithDeduplication) {
		this.effectNumWithDeduplication = effectNumWithDeduplication;
	}

	public Long getDeduplicationNum() {
		return deduplicationNum;
	}

	public void setDeduplicationNum(Long deduplicationNum) {
		this.deduplicationNum = deduplicationNum;
	}

	public String getDeduplicationRate() {
		return deduplicationRate;
	}

	public void setDeduplicationRate(String deduplicationRate) {
		this.deduplicationRate = deduplicationRate;
	}

	public Integer getSubtractNum() {
		return subtractNum;
	}

	public void setSubtractNum(Integer subtractNum) {
		this.subtractNum = subtractNum;
	}

	public Long getChannelExternalNum() {
		return channelExternalNum;
	}

	public void setChannelExternalNum(Long channelExternalNum) {
		this.channelExternalNum = channelExternalNum;
	}

	public Short getIssubtract() {
		return issubtract;
	}

	public void setIssubtract(Short issubtract) {
		this.issubtract = issubtract;
	}
	
	

}
