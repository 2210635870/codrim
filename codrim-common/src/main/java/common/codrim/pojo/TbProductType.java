package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import common.codrim.util.CustomDateSerializer;

public class TbProductType implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * tb_product_type.id
     * @ibatorgenerated 2014-12-24 13:32:16
     */
    private Integer id;

    /**
     * tb_product_type.product_type (产品类型)
     * @ibatorgenerated 2014-12-24 13:32:16
     */
    private String productType;

    /**
     * tb_product_type.date (添加时间)
     * @ibatorgenerated 2014-12-24 13:32:16
     */
    private Date date;

    /**
     * tb_product_type.remark (备注)
     * @ibatorgenerated 2014-12-24 13:32:16
     */
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
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