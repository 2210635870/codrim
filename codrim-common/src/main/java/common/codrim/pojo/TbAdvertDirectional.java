package common.codrim.pojo;

import java.io.Serializable;

public class TbAdvertDirectional implements Serializable {
    /**
     * tb_advert_directional.product_series_id (产品系列id)
     * @ibatorgenerated 2015-02-09 15:10:08
     */
    private Long productSeriesId;

    /**
     * tb_advert_directional.directional_type (1 地域 2 手机品牌 3 人群关于产品类型偏好)
     * @ibatorgenerated 2015-02-09 15:10:08
     */
    private Short directionalType;

    /**
     * tb_advert_directional.directional_value (选择定向值 已，号隔开)
     * @ibatorgenerated 2015-02-09 15:10:08
     */
    private String directionalValue;

    /**
     * tb_advert_directional.id
     * @ibatorgenerated 2015-02-09 15:10:08
     */
    private byte[] id;

    public Long getProductSeriesId() {
        return productSeriesId;
    }

    public void setProductSeriesId(Long productSeriesId) {
        this.productSeriesId = productSeriesId;
    }

    public Short getDirectionalType() {
        return directionalType;
    }

    public void setDirectionalType(Short directionalType) {
        this.directionalType = directionalType;
    }

    public String getDirectionalValue() {
        return directionalValue;
    }

    public void setDirectionalValue(String directionalValue) {
        this.directionalValue = directionalValue;
    }

    public byte[] getId() {
        return id;
    }

    public void setId(byte[] id) {
        this.id = id;
    }
}