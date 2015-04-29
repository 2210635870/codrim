package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

import org.apache.activemq.filter.function.inListFunction;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import common.codrim.util.CustomDateSerializer;

public class TbRankingsReport implements Serializable {
	/**
     * tb__rankings_report.id (自增id)
     * @ibatorgenerated 2014-12-15
     */
    private Long id;
    /**
     * tb__rankings_report.product_name (产品名称)
     * @ibatorgenerated 2014-12-15
     */
    private String productName;
    /**
     * tb__rankings_report.customer_name (客户名称)
     * @ibatorgenerated 2014-12-15
     */
    private String customerName;
    /**
     * tb__rankings_report.channel_name (渠道名称)
     * @ibatorgenerated 2014-12-15
     */
    private String channelName;
    /**
     * tb__rankings_report.deviceplate (ios android设备系统)
     * @ibatorgenerated 2014-12-15
     */
    private String deviceplate;
    /**
     * tb__rankings_report.access_price (接入单价)
     * @ibatorgenerated 2014-12-15
     */
    private String accessPrice;
    /**
     * tb__rankings_report.put_price (投放单价)
     * @ibatorgenerated 2014-12-15
     */
    private String putPrice;
    /**
     * tb__rankings_report.click_num (点击数)
     * @ibatorgenerated 2014-12-15
     */
    private Long clickNum;
    /**
     * tb__rankings_report.effect_num (效果数)
     * @ibatorgenerated 2014-12-15
     */
    private Long effectNum;
    /**
     * tb__rankings_report.income (收入)
     * @ibatorgenerated 2014-12-15
     */
    private Double income;
    /**
     * tb__rankings_report.conversin_rate (转化率)
     * @ibatorgenerated 2014-12-15
     */
    private String conversinRate;
    /**
     * tb__rankings_report.costs (成本)
     * @ibatorgenerated 2014-12-15
     */
    private Double costs;
    /**
     * tb__rankings_report.gross_profit (毛利)
     * @ibatorgenerated 2014-12-15
     */
    private Double grossProfit;
    /**
     * tb__rankings_report.gross_margin (毛利率)
     * @ibatorgenerated 2014-12-15
     */
    private String grossMargin;
    /**
     * tb__rankings_report.active (激活数)
     * @ibatorgenerated 2014-12-15
     */
    private Integer activate;
    /**
     * tb__rankings_report.register (注册数)
     * @ibatorgenerated 2014-12-15
     */
    private Integer register;
    /**
     * tb__rankings_report.consume_one (消费一次)
     * @ibatorgenerated 2014-12-15
     */
    private Integer consumeOne;
    /**
     * tb__rankings_report.consume_more (消费两次及以上)
     * @ibatorgenerated 2014-12-15
     */
    private Integer consumeMore;
    /**
     * tb__rankings_report.recharge (充值)
     * @ibatorgenerated 2014-12-15
     */
    private Integer recharge;
    /**
     * tb__rankings_report.credit (绑卡)
     * @ibatorgenerated 2014-12-15
     */
    private Integer credit;
    /**
     * tb__rankings_report.baddept (坏账)
     * @ibatorgenerated 2014-12-15
     */
    private Integer baddept;
    /**
     * tb__rankings_report.rankings_time (日期)
     * @ibatorgenerated 2014-12-15
     */
    private Date rankingsTime;
    /**
     * tb__rankings_report.rankings_hour (小时)
     * @ibatorgenerated 2014-12-15
     */
    private short rankingsHour;
    
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getDeviceplate() {
        return deviceplate;
    }

    public void setDeviceplate(String deviceplate) {
        this.deviceplate = deviceplate;
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

    public Long getClickNum() {
        return clickNum;
    }

    public void setClickNum(Long clickNum) {
        this.clickNum = clickNum;
    }

    public Long getEffectNum() {
        return effectNum;
    }

    public void setEffectNum(Long effectNum) {
        this.effectNum = effectNum;
    }

 
    public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public String getConversinRate() {
        return conversinRate;
    }

    public void setConversinRate(String conversinRate) {
        this.conversinRate = conversinRate;
    }

 
    public Double getCosts() {
		return costs;
	}

	public void setCosts(Double costs) {
		this.costs = costs;
	}


    public Double getGrossProfit() {
		return grossProfit;
	}

	public void setGrossProfit(Double grossProfit) {
		this.grossProfit = grossProfit;
	}

	public String getGrossMargin() {
        return grossMargin;
    }

    public void setGrossMargin(String grossMargin) {
        this.grossMargin = grossMargin;
    }

    public Integer getActivate() {
        return activate;
    }

    public void setActivate(Integer activate) {
        this.activate = activate;
    }

    public Integer getRegister() {
        return register;
    }

    public void setRegister(Integer register) {
        this.register = register;
    }

    public Integer getConsumeOne() {
        return consumeOne;
    }

    public void setConsumeOne(Integer consumeOne) {
        this.consumeOne = consumeOne;
    }

    public Integer getConsumeMore() {
        return consumeMore;
    }

    public void setConsumeMore(Integer consumeMore) {
        this.consumeMore = consumeMore;
    }

    public Integer getRecharge() {
        return recharge;
    }

    public void setRecharge(Integer recharge) {
        this.recharge = recharge;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getBaddept() {
        return baddept;
    }

    public void setBaddept(Integer baddept) {
        this.baddept = baddept;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getRankingsTime() {
        return rankingsTime;
    }

    public void setRankingsTime(Date rankingsTime) {
        this.rankingsTime = rankingsTime;
    }

	public short getRankingsHour() {
		return rankingsHour;
	}

	public void setRankingsHour(short rankingsHour) {
		this.rankingsHour = rankingsHour;
	}
    
    
}