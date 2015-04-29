package com.dandelion.util;

import java.io.IOException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Repository;

import com.dandelion.bean.JsonUserInfo;
import com.dandelion.bean.TokenBean;
import common.codrim.pojo.TbWzThirdDeviceUid;
import common.codrim.util.HttpUtils;
import common.codrim.util.StringUtil;
@Repository
public class WeixinUtil {
	private static final Logger log = Logger.getLogger("kroffer");


	public TbWzThirdDeviceUid  getWeixinOpenUId(String code,String difId){
	 String  WEIXIN_OPENID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx3cc220d7b8b62251&secret=40960bab186698945f7f6a067a580ffb&code={code}&grant_type=authorization_code";
		 String  WEIXIN_TOKEN_URL = "https://api.weixin.qq.com/sns/userinfo?access_token={access_token}&openid={openid}&lang=zh_CN";
			try {
				TokenBean bean=null;
				log.info("------------开始获取 openId  with code:"+code);
				String json = HttpUtils.httpGet(WEIXIN_OPENID_URL.replace("{code}", code),null);
				ObjectMapper mapper=new ObjectMapper();
				bean=mapper.readValue(json, TokenBean.class);
				if(bean!=null&&!StringUtil.isBlank(bean.getOpenid())&&!StringUtil.isBlank(bean.getAccess_token())){
					log.info("------------开始获取 token  with openId:"+bean.getOpenid());
					WEIXIN_TOKEN_URL=WEIXIN_TOKEN_URL.replace("{access_token}", bean.getAccess_token());
					WEIXIN_TOKEN_URL=WEIXIN_TOKEN_URL.replace("{openid}", bean.getOpenid());
					String jsonToken = HttpUtils.httpGet(WEIXIN_TOKEN_URL,null);
					bean=mapper.readValue(jsonToken, TokenBean.class);
					return this.convertTokenBeanToDeviceUid(bean,difId);
				}else{
					log.info("------------  获取 openId  with code :"+code +" error"+bean.getErrmsg());
					return null;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.info("------------get openId with code:"+code +" error"+e);
              return null;
			}
	}
	public TbWzThirdDeviceUid convertTokenBeanToDeviceUid(TokenBean bean,String  difId){
		TbWzThirdDeviceUid thirdDeviceUid=new TbWzThirdDeviceUid();
		if(bean==null){
			return null;
		}
		thirdDeviceUid.setAddDate(new Date());
		thirdDeviceUid.setCity(bean.getCity());
		thirdDeviceUid.setCountry(bean.getCountry());
		thirdDeviceUid.setHeadimgurl(bean.getHeadimgurl());
		thirdDeviceUid.setNickname(bean.getNickname());
		String[] pris=bean.getPrivilege();
		if(pris.length>0){
			StringBuffer buffer=new StringBuffer();
			for(String str:pris){
				buffer.append(str+",");
			}
			thirdDeviceUid.setPrivilege(buffer.toString());
		}
	
		thirdDeviceUid.setProvince(bean.getProvince());
		thirdDeviceUid.setSex(bean.getSex());
		thirdDeviceUid.setUnionid(bean.getUnionid());
		thirdDeviceUid.setDifId(difId);
		return thirdDeviceUid;
	}
	
}
