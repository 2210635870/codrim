package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbAdvertChannelEnquiry implements Serializable {
    /**
     * tb_advert_channel_enquiry.id
     * @ibatorgenerated 2015-02-27 13:59:46
     */
    private Long id;

    /**
     * tb_advert_channel_enquiry.advertId (广告id)
     * @ibatorgenerated 2015-02-27 13:59:46
     */
    private Long advertid;

    /**
     * tb_advert_channel_enquiry.channelId (渠道id)
     * @ibatorgenerated 2015-02-27 13:59:46
     */
    private Long channelid;

    /**
     * tb_advert_channel_enquiry.person_in_charge (负责人)
     * @ibatorgenerated 2015-02-27 13:59:46
     */
    private String personInCharge;

    /**
     * tb_advert_channel_enquiry.isAccept (是否接入)
     * @ibatorgenerated 2015-02-27 13:59:46
     */
    private Short isaccept;

    /**
     * tb_advert_channel_enquiry.price (反馈单价)
     * @ibatorgenerated 2015-02-27 13:59:46
     */
    private Double price;

    /**
     * tb_advert_channel_enquiry.remark (备注)
     * @ibatorgenerated 2015-02-27 13:59:46
     */
    private String remark;

    
    private String addName;
    private Date addTime;
    
    public String getAddName() {
		return addName;
	}

	public void setAddName(String addName) {
		this.addName = addName;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdvertid() {
        return advertid;
    }

    public void setAdvertid(Long advertid) {
        this.advertid = advertid;
    }

    public Long getChannelid() {
        return channelid;
    }

    public void setChannelid(Long channelid) {
        this.channelid = channelid;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    public Short getIsaccept() {
        return isaccept;
    }

    public void setIsaccept(Short isaccept) {
        this.isaccept = isaccept;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}