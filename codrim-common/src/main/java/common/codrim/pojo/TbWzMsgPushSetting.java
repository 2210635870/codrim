package common.codrim.pojo;

import java.io.Serializable;

public class TbWzMsgPushSetting implements Serializable {
	private static final long serialVersionUID = -1500065220108818578L;

	private Long id;
	private String name;
	private String value;
	
	public TbWzMsgPushSetting() {}
	
	public TbWzMsgPushSetting(Long id, String name, String value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
