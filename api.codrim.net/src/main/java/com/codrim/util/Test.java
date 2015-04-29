/**
 * 
 */
package com.codrim.util;

import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.map.HashedMap;

import common.codrim.util.HttpUtils;
import common.codrim.util.PropertiesUtil;

/**
 * @author Administrator
 *
 */
public class Test {

	public static void main(String[] args) {
//		Map map=new HashedMap();
//		map.put("channelId", 20);
//		map.put("idfa", "sdfgasdgdsfasfas");
//		String sign=Sign.generateSign("channelId=20&idfa=sdfgasdgdsfasfas", privateKey);
//		System.out.println(sign);
//		map.put("sign", sign);
//		String json=HttpUtils.httpGet("Http://www.ihavecar.com/app/pub/codrim!notifyIOSAD.do", map);
//		System.out.println("--------------------------------"+json);
//	Map map=new HashedMap();
//	String url="http://api.adwalker.cn/AdAPI/common/ainfo.do";
//		map.put("appid", "sdkjfhasdfhas");
//	map.put("IDFA", "asdfasdfasdfasd");
//	String json=HttpUtils.httpGet(url, map);
//	System.out.println("--------------------------------"+json);
		
		String json=HttpUtils.httpGet("http://api.9080app.com/Return.ashx?adid=2260&appid=3002&mac=02:00:00:00:00:00&idfa=D723BD21-203E-4D77-AF05-ACBABDD407F0", null);
		System.out.println("--------------------------------"+json);	
	}
	
	
	
}
