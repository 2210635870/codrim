/**
 * 
 */
package com.codrim.util;

import java.beans.Encoder;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.collections.functors.IfClosure;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;

import common.codrim.pojo.TbProductDetail;
import common.codrim.util.HttpUtils;
import common.codrim.util.PropertiesUtil;
import common.codrim.util.StringUtil;

/**
 * @author Administrator
 *
 */
public class CallPackHttp {
	private static ExecutorService lrService = Executors
			.newFixedThreadPool(1000);
	private static final Logger log = Logger.getLogger("API");
	private static String privateKey = PropertiesUtil.getValue("/callpack.properties", "privateKey");
	private static String toChannelUrl=PropertiesUtil.getValue("/callpack.properties", "toChannelUrl");
	private static String toProductUrl=PropertiesUtil.getValue("/callpack.properties", "toProductUrl");
	public static void sendToProduct(TbProductDetail productDetail,String idfa,String source,String ip) throws UnsupportedEncodingException{
		Map map=new HashedMap();
		 String statisUrl=PropertiesUtil.getValue("/callpack.properties", "staticUrl");
		if(productDetail.getProductName().equals("我有车")){
		map.put("channelId", 20);
		map.put("idfa", idfa);
		String sign=null;
if(StringUtil.isBlank(ip)){
	sign=Sign.generateSign("channelId="+20+"&idfa="+idfa+"&source="+source, privateKey);
	log.info("----------------------发送点击数给广告主使用sign:"+"channelId="+20+"&idfa="+idfa+"&source="+source);
}else{
	sign=Sign.generateSign("channelId="+20+"&idfa="+idfa+"&source="+source+"&ip="+ip, privateKey);
	map.put("ip", ip);
	log.info("----------------------发送点击数给广告主使用sign:"+"channelId="+20+"&idfa="+idfa+"&source="+source+"&ip="+ip);
}
		map.put("sign", sign);
		map.put("source", source);
		SendDataThread sendDataToProduct=new SendDataThread(toProductUrl, map);
		log.info("----------------------发送点击数给广告主");
		lrService.execute(sendDataToProduct);
		}else{
			String url=productDetail.getProductTrackingLink();
			url=url.replace("{appId}", productDetail.getAppid());
			url=url.replace("{idfa}", idfa);
			if(StringUtil.isBlank(ip)){
				url=url.replace("{ip}", "127.0.0.1");
			}else{
				url=url.replace("{ip}", ip);
			}
			statisUrl=statisUrl.replace("{appId}", productDetail.getAppid());
			statisUrl=statisUrl.replace("{dp}", productDetail.getDeviceplate());
			statisUrl=statisUrl.replace("{source}", source);
			statisUrl=statisUrl.replace("{idfa}", idfa);

			url=url.replace("{callback_url}", URLEncoder.encode(statisUrl, "utf-8"));
			log.info("----------------------发送点击数给广告主链接:"+url);
			SendDataThread sendDataToProduct=new SendDataThread(url, null);
			log.info("----------------------发送点击数给广告主");
			lrService.execute(sendDataToProduct);
		}
	
	}
	
	
	
	public static void sendToChannel(String appid,String idfa,String url){
				url=url.replace("{appId}", appid);
		url=url.replace("{idfa}", idfa);
		SendDataThread sendDataToProduct=new SendDataThread(url, null);
		log.info("----------------------发送效果数给渠道");
		lrService.execute(sendDataToProduct);
	}
}
