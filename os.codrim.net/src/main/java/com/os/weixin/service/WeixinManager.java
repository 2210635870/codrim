package com.os.weixin.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.os.weixin.bean.SignatureResultBean;
import com.os.weixin.bean.TokenBean;
import com.os.weixin.bean.WeiXinTicketBean;
import common.codrim.common.Contans;
import common.codrim.util.HttpUtils;
import common.codrim.util.JsonUtil;
import common.codrim.util.MemcacheUtil;
import common.codrim.util.PropertiesUtil;
import common.codrim.util.StringUtil;

@Repository
public class WeixinManager {
	@Autowired
	MemcacheUtil memcacheUtil;
	@Autowired
	JsonUtil jsonUtil;
private static final String url_token=PropertiesUtil.getValue("/os.properties", "weixin_url_token");
private static final String url_ticket=PropertiesUtil.getValue("/os.properties", "weixin_url_ticket");
private static final Logger log = Logger.getLogger("BOSS");

	public TokenBean getWeixinToken(){
		String token=memcacheUtil.getCacheByKey(Contans.WEIXIN_TOKEN_KEY);
		TokenBean bean=null;
		if(!StringUtil.isBlank(token)){
			try {
				log.info("------------从缓存获取微信token"+token);
				ObjectMapper mapper=new ObjectMapper();
				bean=mapper.readValue(token, TokenBean.class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				memcacheUtil.deleteCache(Contans.WEIXIN_TOKEN_KEY);
				bean=this.httpGetToken();
			}
		}else{
			bean=this.httpGetToken();
		}
		return bean;
	}

	public TokenBean httpGetToken() {
		TokenBean bean=null;
			try {
				String json = HttpUtils.httpGet(url_token,null);
				if(!StringUtil.isEmpty(json)){
					log.info("------------访问微信API接口重新获取微信token"+json);

					ObjectMapper mapper=new ObjectMapper();
				bean=mapper.readValue(json, TokenBean.class);
				if(!StringUtil.isBlank(bean.getAccess_token())){
				memcacheUtil.addCache(Contans.WEIXIN_TOKEN_KEY, jsonUtil.convertObjectToString(bean), new Date(bean.getExpires_in()*1000L));
				}
				log.info("--------------微信token 放入缓存");
				}else{
					return bean;
					}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				return bean;
				}
			return bean;
	
	}
	public SignatureResultBean getSignature(String url,SignatureResultBean bean) {
		WeiXinTicketBean ticketBean=this.getTicket();
		if(ticketBean==null){
			bean.setCode(Contans.FALSE+"");
		}
		if(ticketBean.getErrcode()!=0){
			bean.setCode(Contans.FALSE+"");
		}
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("jsapi_ticket="+ticketBean.getTicket());
		stringBuffer.append("&noncestr="+Contans.CODRIM_SIGN);
		long time=System.currentTimeMillis();
		stringBuffer.append("&timestamp="+time);
		stringBuffer.append("&url="+url);
		String sign=StringUtil.SHA1(stringBuffer.toString());
		bean.setCode(Contans.TRUE+"");
		bean.setSignature(sign);
		bean.setNonceStr(Contans.CODRIM_SIGN);
		bean.setTimestamp(time);
		return bean;
	}
	public WeiXinTicketBean getTicket() {
		// TODO Auto-generated method stub
		String ticket=memcacheUtil.getCacheByKey(Contans.WEIXIN_TICKET_KEY);
		WeiXinTicketBean bean=null;
		if(!StringUtil.isBlank(ticket)){
			try {
				log.info("------------从缓存获取微信ticket"+ticket);
				ObjectMapper mapper=new ObjectMapper();
				bean=mapper.readValue(ticket, WeiXinTicketBean.class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				memcacheUtil.deleteCache(Contans.WEIXIN_TICKET_KEY);
				bean=this.httpGetTicket();
			}
		}else{
			bean=this.httpGetTicket();
		}
		return bean;
	}
	public WeiXinTicketBean httpGetTicket() {
		WeiXinTicketBean bean=null;
			try {
				Map<String, Object> map=new HashMap<String, Object>();
				TokenBean tokenBean=this.getWeixinToken();
				if(tokenBean==null){
					return bean ;
				}
				map.put("access_token", tokenBean.getAccess_token());
				map.put("type", "jsapi");
				String json = HttpUtils.httpGet(url_ticket,map);
				if(!StringUtil.isEmpty(json)){
					log.info("------------访问微信API接口重新获取微信ticket"+json);
					ObjectMapper mapper=new ObjectMapper();
				bean=mapper.readValue(json, WeiXinTicketBean.class);
				if(bean.getErrcode()==0){
				memcacheUtil.addCache(Contans.WEIXIN_TICKET_KEY, jsonUtil.convertObjectToString(bean), new Date(bean.getExpires_in()*1000l));
				}
				log.info("--------------微信tiket 放入缓存");
				}else{
					return bean;
					}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				return bean;
				}
			return bean;
	
	}
	
	
	
	
	
	
}
