package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbLinkParam implements Serializable {
    /**
     * tb_link_param.id
     * @ibatorgenerated 2015-03-03 12:05:19
     */
    private Long id;

    /**
     * tb_link_param.product_id (产品id)
     * @ibatorgenerated 2015-03-03 12:05:19
     */
    private Long productId;

    /**
     * tb_link_param.advert_put_channel_id (投放渠道id)
     * @ibatorgenerated 2015-03-03 12:05:19
     */
    private Long advertPutChannelId;

    /**
     * tb_link_param.codrim_param_id (对应参数id)
     * @ibatorgenerated 2015-03-03 12:05:19
     */
    private Long codrimParamId;

    /**
     * tb_link_param.is_change_defult_value (1 true 0 false)
     * @ibatorgenerated 2015-03-03 12:05:19
     */
    private Short isChangeDefultValue;

    /**
     * tb_link_param.default_value_id (参数默认值id)
     * @ibatorgenerated 2015-03-03 12:05:19
     */
    private Long defaultValueId;

    /**
     * tb_link_param.change_value (1)
     * @ibatorgenerated 2015-03-03 12:05:19
     */
    private String changeValue;

    /**
     * tb_link_param.add_time (添加时间)
     * @ibatorgenerated 2015-03-03 12:05:19
     */
    private Date addTime;

    /**
     * tb_link_param.link_type (参数类型 1 产品 2 渠道)
     * @ibatorgenerated 2015-03-03 12:05:19
     */
    private Short linkType;

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

    public Long getAdvertPutChannelId() {
        return advertPutChannelId;
    }

    public void setAdvertPutChannelId(Long advertPutChannelId) {
        this.advertPutChannelId = advertPutChannelId;
    }

    public Long getCodrimParamId() {
        return codrimParamId;
    }

    public void setCodrimParamId(Long codrimParamId) {
        this.codrimParamId = codrimParamId;
    }

    public Short getIsChangeDefultValue() {
        return isChangeDefultValue;
    }

    public void setIsChangeDefultValue(Short isChangeDefultValue) {
        this.isChangeDefultValue = isChangeDefultValue;
    }

    public Long getDefaultValueId() {
        return defaultValueId;
    }

    public void setDefaultValueId(Long defaultValueId) {
        this.defaultValueId = defaultValueId;
    }

    public String getChangeValue() {
        return changeValue;
    }

    public void setChangeValue(String changeValue) {
        this.changeValue = changeValue;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Short getLinkType() {
        return linkType;
    }

    public void setLinkType(Short linkType) {
        this.linkType = linkType;
    }
}