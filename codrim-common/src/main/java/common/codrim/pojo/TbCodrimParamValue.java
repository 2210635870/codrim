package common.codrim.pojo;

import java.io.Serializable;

public class TbCodrimParamValue implements Serializable {
    /**
	 * tb_codrim_param_value.id
	 * @ibatorgenerated  2015-03-03 11:54:27
	 */
	private Long id;
	/**
	 * tb_codrim_param_value.codrim_link_param_id (参数id)
	 * @ibatorgenerated  2015-03-03 11:54:27
	 */
	private Long codrimLinkParamId;
	/**
	 * tb_codrim_param_value.default_value (参数值)
	 * @ibatorgenerated  2015-03-03 11:54:27
	 */
	private String defaultValue;
	/**
	 * tb_codrim_param_value.default_name (参数值含义)
	 * @ibatorgenerated  2015-03-03 11:54:27
	 */
	private String defaultName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCodrimLinkParamId() {
		return codrimLinkParamId;
	}
	public void setCodrimLinkParamId(Long codrimLinkParamId) {
		this.codrimLinkParamId = codrimLinkParamId;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getDefaultName() {
		return defaultName;
	}
	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}

}