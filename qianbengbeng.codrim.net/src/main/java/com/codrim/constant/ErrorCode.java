package com.codrim.constant;

public class ErrorCode {
	
	/** 未知错误 **/
	public static final String UN000 = "000";
	
	
	
	/** 参数为空 **/
	public static final String UN100 = "100";
	/** sign校验失败 **/
	public static final String UN400 = "400";
	
	/** 用户id不存在 **/
	public static final String UN401 = "401";
	
	/** 用户被禁 **/
	public static final String UN402 = "402";
	

	//-------------------------- 注册、登录模块 	
	/** 手机号已被注册过 **/
	public static final String RL101 = "101";
	
	/** 该设备号已绑定过 **/
	public static final String RL102 = "102";
	
	/** 该手机号的绑定设备号已达上限（2个） **/
	public static final String RL103 = "103";
	
	/** 该手机号不存在（登录） **/
	public static final String RL104 = "104";
	
	/** 密码验证失败 **/
	public static final String RL105 = "105";
	
	/** 验证码超时 **/
	public static final String RL106 = "106";
	
	/** 验证码不匹配 **/
	public static final String RL107 = "107";
	
	/** 邀请码不正确 **/
	public static final String RL108 = "108";
	
	/** 验证码发送失败 **/
	public static final String RL109 = "109";
	
	//-------------------------- 团队模块
	/** 团队邀请码无效 **/
	public static final String GP201 = "201";
	
	/** 团队ID无效 **/
	public static final String GP202 = "202";
	
	/** 团队名无效 **/
	public static final String GP203 = "203";
	
	/** 加入团队申请已取消 **/
	public static final String GP204 = "204";

	//-------------------------- 兑换模块
	/** 余额不足 **/
	public static final String EX301 = "301";
}
