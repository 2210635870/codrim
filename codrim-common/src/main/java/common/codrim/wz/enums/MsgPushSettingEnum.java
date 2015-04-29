package common.codrim.wz.enums;

public enum MsgPushSettingEnum {

	SIGNIN(1, "签到"),
	READ_INFO(2, "阅读资讯"),
	INFO_DAILY_NUM(3, "资讯每日推送数量");
	
	private int code;
	private String name;
	
	private MsgPushSettingEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
