package com.codrim.util;

import java.util.HashMap;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;

import com.cloopen.rest.sdk.CCPRestSDK;

/**
 * 短信验证码发送工具，采用官方SDK
 * 官网：http://www.yuntongxun.com/
 */
public class SmsUtils {
	
	private static final Logger logger = Logger.getLogger("yj");
	
	// 沙盒环境: sandboxapp.cloopen.com
	// 生产环境: app.cloopen.com
	private static String serverUrl = "app.cloopen.com";	
	private static String port = "8883";
	
	private static String sid = "8a48b5514a61a814014a71358b5109c7";
	private static String token = "72cc0c2545bf42aa86f6832ec9fc458c";
	
	private static String appId = "aaf98f894a70a61d014a756be06e031d";
	
	// 短信模板 免费开发测试使用的模板ID为1
	private static String templateId = "12857";

	
//	public static void main(String[] args) {
//		String[] sss=new String[]{"18520555812","18520555813","13265070292","18826415944","13450420025","13450350963","15800029628"};
//		for(int i=0;i<sss.length;i++){
//			sendVerifyCodeSMS(sss[i], RandomStringUtils.randomNumeric(6), "1");
//		}
//	}
	
	/**
	 * 发送验证码短信
	 * @param phoneNumber 手机号
	 * @param code 验证码
	 * @param timeout 需要在多少分钟内输入验证码
	 */
	public static boolean sendVerifyCodeSMS(String phoneNumber, String code, String timeout) {

		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init(serverUrl, port); // 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount(sid, token);// 初始化主帐号名称和主帐号令牌
		restAPI.setAppId(appId); // 初始化应用ID
		
		HashMap<String, Object> result = restAPI.sendTemplateSMS(phoneNumber, templateId , new String[]{code, timeout});

		if ("000000".equals(result.get("statusCode"))) {
			logger.info(">>>>> 验证码短信发送成功: 手机号="+phoneNumber+", 验证码="+code);
			return true;
		} else {
			logger.error(">>>>> 验证码短信发送失败: 错误码="+result.get("statusCode")+", 错误信息="+result.get("statusMsg"));
			return false;
		}
	}

}
