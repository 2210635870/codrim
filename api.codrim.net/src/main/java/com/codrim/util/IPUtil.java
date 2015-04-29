package com.codrim.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import common.codrim.util.HttpUtils;
import common.codrim.util.StringUtil;

public class IPUtil {
		private static String url="http://ip.taobao.com/service/getIpInfo.php";
		private static Logger log = Logger.getLogger(IPUtil.class);
	public static void main(String[] args) throws JsonProcessingException, IOException {
		System.out.println(getAddress("223.223.196.2"));
	}
		
		public static String getIp(HttpServletRequest request) {       
			String ip = request.getHeader("x-forwarded-for");
			if (StringUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (StringUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (StringUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
				if (ip.equals("127.0.0.1")) {
					InetAddress inet = null;
					try {
						inet = InetAddress.getLocalHost();
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
					ip = inet.getHostAddress();
				}
			}
			return ip.indexOf(",") > -1 ? ip.substring(0, ip.indexOf(",")) : ip;  
		}    
		
		
		
		public static Map<String,String>  getAddress(String ip) {
	         Map map=new HashMap();
	         map.put("ip", ip);
	        String json= HttpUtils.httpGet(url, map);
	        return  convertJson(json,ip);
}
		@SuppressWarnings("finally")
		public static Map<String,String>  convertJson(String json,String ip) {
			ObjectMapper mapper=new ObjectMapper();
			JsonNode node;
			String str="未分配或者内网IP";
			Map<String,String> map=new HashMap<String, String>();
			try {
				node = mapper.readTree(json);
				int code=node.getIntValue();
				if(code==0){
					JsonNode data=node.get("data");
					map.put("country", data.get("country").getTextValue());
					map.put("province", data.get("region").getTextValue());
					map.put("city", data.get("city").getTextValue());
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				map.put("country", str);
				map.put("province", str);
				map.put("city", str);
			} finally{
				log.info("--------------获取IP地址：-"+ip+"---"+map.get("country")+map.get("province")+map.get("city"));
				return map;
			}
			
		
		
		}
		
}
