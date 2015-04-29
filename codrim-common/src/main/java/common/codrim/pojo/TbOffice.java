package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import common.codrim.util.CustomDateSerializer;

public class TbOffice implements Serializable {
    /**
     * tb_office.id
     * @ibatorgenerated 2014-12-24 12:23:47
     */
    private Integer id;

    /**
     * tb_office.office_name (职位名称)
     * @ibatorgenerated 2014-12-24 12:23:47
     */
    private String officeName;

    /**
     * tb_office.date (添加时间)
     * @ibatorgenerated 2014-12-24 12:23:47
     */
    private Date date;

    /**
     * tb_office.remark (备注)
     * @ibatorgenerated 2014-12-24 12:23:47
     */
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
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