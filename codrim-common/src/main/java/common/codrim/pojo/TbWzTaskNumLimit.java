package common.codrim.pojo;

import java.io.Serializable;

public class TbWzTaskNumLimit implements Serializable {
    private Integer id;

    private Integer limitNum;

    private Short limitType;

    private String addName;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }

    public Short getLimitType() {
        return limitType;
    }

    public void setLimitType(Short limitType) {
        this.limitType = limitType;
    }

    public String getAddName() {
        return addName;
    }

    public void setAddName(String addName) {
        this.addName = addName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}