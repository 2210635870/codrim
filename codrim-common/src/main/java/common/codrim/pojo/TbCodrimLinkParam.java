package common.codrim.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbCodrimLinkParam implements Serializable {
    /**
	 * tb_codrim_link_param.id
	 * @ibatorgenerated  2015-03-03 11:54:27
	 */
	private Long id;
	/**
	 * tb_codrim_link_param.param_zh_name (参数中文名)
	 * @ibatorgenerated  2015-03-03 11:54:27
	 */
	private String paramZhName;
	/**
	 * tb_codrim_link_param.param_en_name (参数英文名)
	 * @ibatorgenerated  2015-03-03 11:54:27
	 */
	private String paramEnName;
	/**
	 * tb_codrim_link_param.param_type (参数值类型 1 默认 2 ip地址解析使用)
	 * @ibatorgenerated  2015-03-03 11:54:27
	 */
	private Short paramType;
	/**
	 * tb_codrim_link_param.add_time (添加时间)
	 * @ibatorgenerated  2015-03-03 11:54:27
	 */
	private Date addTime;


	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParamZhName() {
        return paramZhName;
    }

    public void setParamZhName(String paramZhName) {
        this.paramZhName = paramZhName;
    }

    public String getParamEnName() {
        return paramEnName;
    }

    public void setParamEnName(String paramEnName) {
        this.paramEnName = paramEnName;
    }

    public Short getParamType() {
        return paramType;
    }

    public void setParamType(Short paramType) {
        this.paramType = paramType;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}