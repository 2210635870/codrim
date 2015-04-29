package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbCustomerType implements Serializable {
    /**
     * tb_customer_type.id
     * @ibatorgenerated 2015-01-29 17:14:01
     */
    private Long id;

    /**
     * tb_customer_type.customer_type
     * @ibatorgenerated 2015-01-29 17:14:01
     */
    private String customerType;

    /**
     * tb_customer_type.remark
     * @ibatorgenerated 2015-01-29 17:14:01
     */
    private String remark;

    /**
     * tb_customer_type.create_date
     * @ibatorgenerated 2015-01-29 17:14:01
     */
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}