package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import common.codrim.util.CustomDateSerializer;

public class TbChannelType implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TbChannelType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TbChannelType(Integer id, String channelType, Date date,
			String remark) {
		super();
		this.id = id;
		this.channelType = channelType;
		this.date = date;
		this.remark = remark;
	}

	public TbChannelType(String channelType, Date date, String remark) {
		super();
		this.channelType = channelType;
		this.date = date;
		this.remark = remark;
	}

	/**
     * tb_channel_type.id
     * @ibatorgenerated 2014-12-24 12:23:47
     */
    private Integer id;

    /**
     * tb_channel_type.channel_type (渠道类型)
     * @ibatorgenerated 2014-12-24 12:23:47
     */
    private String channelType;

    /**
     * tb_channel_type.date (添加时间)
     * @ibatorgenerated 2014-12-24 12:23:47
     */
    private Date date;

    /**
     * tb_channel_type.remark (备注)
     * @ibatorgenerated 2014-12-24 12:23:47
     */
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}