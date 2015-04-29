/**
 * 
 */
package com.codrim.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.activemq.filter.function.inListFunction;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codrim.util.CallPackHttp;
import com.codrim.util.IPUtil;

import common.codrim.common.Contans;
import common.codrim.common.SelectResultByCodition;
import common.codrim.pojo.TbChannel;
import common.codrim.pojo.TbEffectRankings;
import common.codrim.pojo.TbProductDetail;
import common.codrim.pojo.TbRandomSendNum;
import common.codrim.service.ChannelService;
import common.codrim.service.EffectRankingsService;
import common.codrim.service.ProductDetailService;
import common.codrim.service.RandomSendNumService;
import common.codrim.util.DateUtil;
import common.codrim.util.JsonUtil;
import common.codrim.util.MemcacheUtil;
import common.codrim.util.StringUtil;

/**
 * @author Administrator
 *
 */
@Service
public class ApiService {
	@Autowired
	JsonUtil jsonUtil;
	@Autowired
	ProductDetailService productDetailService;
	@Autowired
	EffectRankingsService effectRankingsService;
	@Autowired
	MemcacheUtil memcacheUtil;
	@Autowired
	ChannelService channelService;
	@Autowired
	RandomSendNumService randomSendNumService;
	private static final Logger log = Logger.getLogger("API");
	
	
public TbChannel getTbChannel(String channelNum){
	TbChannel channel=channelService.getChannelByChannelNumber(channelNum);
	return channel;
}
	/** 
	  *转换为TbRankingsProduct
	  * @author xulin
	  * @date 2014年12月17日
	  *  @param name
	  *  @param source
	  *  @param devicePlate
	  *  @return
	  *  TbRankingsProduct
	  */ 
	public TbProductDetail  getProductDetail(String productId, String source, String devicePlate,short runningStatus,String type) {
		
		String key=Contans.CACHE_PRODUCT + productId+source+devicePlate+runningStatus;
		String json=memcacheUtil.getCacheByKey(key);
		log.info("----------------------获取缓存json TbProduct："+json);
		TbProductDetail product=null;
		if(StringUtil.isBlank(json)){
			SelectResultByCodition codition=new SelectResultByCodition();
			codition.setProductId(productId);
			if(type.equals("channelName")){
				TbChannel channel=this.getTbChannel(source);
				log.info("----------------------获取缓存 TBChannel："+source+(channel==null));
				if(channel==null)return null;
				codition.setChannelName(channel.getChannelName());
			}
			if(type.equals("channelNumber")){
				codition.setChannelNumber(source);
			}
			codition.setDeviceplate(devicePlate);
			codition.setType(runningStatus);
			product=productDetailService.getProductDetailByCodition(codition);
		}else{
			product=jsonUtil.convertStringToObject(json,
					TbProductDetail.class);
		}
	if(product==null){
		return null;
	}else{
		memcacheUtil.addCache(key, jsonUtil.convertObjectToString(product), new Date(Contans.CACHE_SEVEN_DAY));
	}
	return product;
	}
	/** 
	  *转换为TbRankingsProduct
	  * @author xulin
	  * @date 2014年12月17日
	  *  @param name
	  *  @param source
	  *  @param devicePlate
	  *  @return
	  *  TbRankingsProduct
	  */ 
	public TbProductDetail getProductWithAppId( String source, String devicePlate,short runningStatus,String appId,String type) {
		String key=Contans.CACHE_PRODUCT +source+devicePlate+appId+runningStatus;
		String json=memcacheUtil.getCacheByKey(key);
		log.info("----------------------获取缓存json TbProduct："+json);
		TbProductDetail product=null;
		if(StringUtil.isBlank(json)){
			SelectResultByCodition codition=new SelectResultByCodition();
			if(type.equals("channelName")){
				TbChannel channel=this.getTbChannel(source);
				log.info("----------------------获取缓存 TBChannel："+source+(channel==null));
				if(channel==null)return null;
				codition.setChannelName(channel.getChannelName());
			}
			if(type.equals("channelNumber")){
				codition.setChannelNumber(source);
			}
			codition.setDeviceplate(devicePlate);
			codition.setAppId(appId);
			codition.setType(runningStatus);
			product=productDetailService.getProductDetailByCodition(codition);
		}else{
			product=jsonUtil.convertStringToObject(json,
					TbProductDetail.class);
		}
		
	if(product==null){
		return null;
	}else{
		memcacheUtil.addCache(key, jsonUtil.convertObjectToString(product), new Date(Contans.CACHE_SEVEN_DAY));
	}
	return product;
	}
	
	public String getCallpackUrl(TbProductDetail product,HttpServletRequest request){
		  String url=null;
		  String callBackUrl=request.getParameter("callback_url");
		  if(StringUtil.isBlank(callBackUrl)){
			  callBackUrl=request.getParameter("callback");
		  }
		  if(StringUtil.isBlank(product.getChannelPostBackLink())&&!StringUtil.isBlank(callBackUrl)){
			try {
				url = URLDecoder.decode(callBackUrl, "utf-8");
				log.info("------------------------ 获取效果回传地址使用callbackUrl:"+url);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				log.info("------------------------ 获取效果回传地址使用callpackUrl出错"+e);
				url=null;
			}
		   }
		  return url;
	}
	/** 
	  *接收渠道点击信息发送给广告主
	  * @author xulin
	  * @date 2014年12月30日
	  *  @param rankingsProduct
	  *  @param request
	  *  @return
	  *  boolean
	 * @throws UnsupportedEncodingException 
	  */ 
	public boolean  rankingClick(TbProductDetail product,HttpServletRequest request) throws UnsupportedEncodingException{
		boolean flag=false;
		flag=this.addEffectRanking(product,request,"click");
		if(!flag){
			return flag;
		}
		TbRandomSendNum randomSendNum=new TbRandomSendNum();
		randomSendNum.setChannelName(product.getChannelName());
	   randomSendNum.setChannelNameTwo(request.getParameter("source_t"));
	   randomSendNum.setProductName(product.getProductName());
	   randomSendNum.setMobileMark(request.getParameter("idfa"));
	 randomSendNum.setCallBackUrl(getCallpackUrl(product, request));
	 
	  
	   TbRandomSendNum randomSendNum_new=randomSendNumService.selectRandomSendNumByPCM(randomSendNum);
	   int i=0;
	   int sendNum;
	   int randomNum=getRandomNum();
	   if(randomSendNum_new==null){
		   randomSendNum.setRandomNum(randomNum);
		   randomSendNum.setSendNum(0);
		   randomSendNum.setIsToChanel(Contans.FALSE);
		i= randomSendNumService.addRandomSendNum(randomSendNum);
		   if(i<=0) return flag;
		   sendNum=0;
		   randomSendNum_new=randomSendNum;
	   }else{
		   sendNum=randomSendNum_new.getSendNum();
		   randomNum=randomSendNum_new.getRandomNum();
	   }
		log.info("发送点击数据到广告主随即数值为："+randomNum);
if(sendNum<randomNum){//可以给广告主发送
	                         //广告主返回的第一条成功效果给渠道回传，以后的数据回传的时候全都是失败
	//广告主发送数据
	log.info("----------------------发送点击数据到广告主");
	String ip=request.getParameter("ip");
	http://api.adwalker.cn/AdAPI/common/cinfo.do?appid={appId}&source={source} &IDFA={idfa}&client_ip={ip}&callback={callback_url}
		CallPackHttp.sendToProduct(product, request.getParameter("idfa"),request.getParameter("source"),ip);	
	randomSendNum_new.setSendNum(sendNum+1);
	randomSendNumService.updateRandomSendNum(randomSendNum_new);
}else{//不再回传，给渠道回传的时候全都是失败
	log.info("发送点击数据到广告主超过随即数值，不再发送，随即数值："+randomNum);
}
		return flag;
	}
	public boolean effect(TbProductDetail product,HttpServletRequest request){
		boolean flag=this.addEffectRanking(product, request, "");
		log.info("发送效果到渠道 ,添加效果信息："+flag);
		this.sendDataToChannel(product, request);
		return flag;
	}
	/** 
	  *发送效果回传到渠道
	  * @author xulin
	  * @date 2014年12月30日
	  *  @param product
	  *  @param request
	  *  void
	  */ 
	public void sendDataToChannel(TbProductDetail product,HttpServletRequest request){
		String reString=request.getParameter("isRes");
		if(StringUtil.isBlank(reString)){
			return ;
		}else{
			if("0".equals(reString))return ;
		}
		TbRandomSendNum randomSendNum=new TbRandomSendNum();
		randomSendNum.setChannelName(product.getChannelName());
	   randomSendNum.setProductName(product.getProductName());
	   randomSendNum.setMobileMark(request.getParameter("idfa"));
	   TbRandomSendNum randomSendNum_new=randomSendNumService.selectRandomSendNumByPCM(randomSendNum);
	   short isToChannel;
	   if(randomSendNum_new==null){
		  return ;
	   }else{
		   isToChannel=randomSendNum_new.getIsToChanel();
	   }
if(isToChannel!=1){//多线程发送给渠道效果信息
	log.info("----------------------第一次发送效果信息到渠道");
	String callBackUrl=randomSendNum_new.getCallBackUrl();
	  if(StringUtil.isBlank(product.getChannelPostBackLink())){
			log.info("------------------------ 效果回传地址使用callBackUrl:"+callBackUrl);
			CallPackHttp.sendToChannel(product.getAppid(), request.getParameter("idfa"),callBackUrl);
		   }else{
				log.info("------------------------ 效果回传地址使用ChannelPostBackLink():"+product.getChannelPostBackLink());
				CallPackHttp.sendToChannel(product.getAppid(), request.getParameter("idfa"),product.getChannelPostBackLink());
		   }
}
          randomSendNum_new.setIsToChanel((short)1);
          randomSendNumService.updateRandomSendNum(randomSendNum_new);
	}
	
	
	
	public int getRandomNum(){
		Random random=new Random();
		int i= random.nextInt(3);
		int[] nums=new int[]{3,4,5};
		return nums[i];
	}


	
/** 
  *工具方法   返回时间类型
  * @author xulin
  * @date 2014年12月17日
  *  @param request
  *  @return
  *  Date
  */ 
public Date getDateByRequest(HttpServletRequest request){
	long date;
	if(StringUtil.isBlank(request.getParameter("timeStamp"))){
		date=System.currentTimeMillis();
	}else{
		String timeStamp=request.getParameter("timeStamp");
		if(timeStamp.length()==10){
			timeStamp=timeStamp+"000";
		}
		date=Long.parseLong(timeStamp);
	}
	return DateUtil.getNowDateToDate(date, "yyyy-MM-dd HH:mm:ss");
}

	/** 
	  *添加冲榜用户行为信息
	  * @author xulin
	  * @date 2014年12月17日
	  *  @param product
	  *  @param request
	  *  @return
	  *  boolean
	  */ 
	public boolean  addEffectRanking(TbProductDetail product,HttpServletRequest request,String type) {
		boolean flag;
		log.info("----------------------添加用户行为 ：");
		TbEffectRankings effectRankings=this.converTbEffectRanking(product, request);
		if(type.equals("click")){
			effectRankings.setRankingsAction(Contans.RANKINGS_ACTION_CLICK);
		}
		if(!StringUtil.isBlank(request.getParameter("isRes"))){
			TbEffectRankings	effectRankings_new=new TbEffectRankings();
			effectRankings_new.setProductName(effectRankings.getProductName());
			effectRankings_new.setChannelName(effectRankings.getChannelName());
			effectRankings_new.setRankingsAction(Contans.RANKINGS_ACTION_CLICK);
			effectRankings_new.setIdfa(effectRankings.getIdfa());
			TbEffectRankings effectRankings_click_old=effectRankingsService.getEffectRankings(effectRankings);
			log.info("----------------------获取点击行为产生时间 ："+(effectRankings_click_old==null?"没有点击行为":"有点击行为"));
			if(effectRankings_click_old!=null){
				effectRankings.setActionTime(effectRankings_click_old.getActionTime());
				log.info("----------------------给效果行为赋值为点击时间 ：");
				int i=effectRankingsService.updateEffectByIdfa(effectRankings_new);
				log.info("----------------------更新点击数据匹配手机号 ："+(i>0?"成功":"失败"));
			}
		}
		TbEffectRankings effectRankings_register_old=effectRankingsService.getEffectRankings(effectRankings);
		log.info("----------------------检查是否已添加该用户行为 ："+(effectRankings_register_old==null?"没有添加":"已经添加"));
		if(effectRankings_register_old!=null){
			return true;
		}
		int res=effectRankingsService.addEffectRankings(effectRankings);
		log.info("----------------------添加该用户行为 ："+(res>0?"成功":"失败"));
		if(res>0){
			flag=true;
		}else{
			flag=false;
			}
		return flag;
	}


	/** 
	  *转换为tbeffectRanking
	  * @author xulin
	  * @date 2014年12月17日
	  *  @param product
	  *  @param request
	  *  @return
	  *  TbEffectRankings
	  */ 
	public  TbEffectRankings converTbEffectRanking(TbProductDetail product,HttpServletRequest request){
		final TbEffectRankings effect = new TbEffectRankings();
		effect.setChannelName(product.getChannelName());
		effect.setProductName(product.getProductName());
		effect.setActionTime(getDateByRequest(request));
		if(product.getDeviceplate().equals("ios")){
			effect.setIdfa(request.getParameter("idfa"));
		}
		if(product.getDeviceplate().equals("android")){
			effect.setImei(request.getParameter("imei"));
		}
		effect.setUdid(request.getParameter("udid"));
		effect.setMac(request.getParameter("mac"));
		effect.setDeviceplate(request.getParameter("dp"));
		effect.setAppid(product.getAppid());
		effect.setTelephone(request.getParameter("tel"));
		effect.setRegisterUse((short)0);
		String ip=request.getParameter("ip");
		if(!StringUtil.isBlank(ip)){
			effect.setIp(ip);
			Map<String, String> map=IPUtil.getAddress(ip);
			effect.setCountry(map.get("country"));
			effect.setProvince(map.get("province"));
			effect.setCity(map.get("city"));
		}
		if(!StringUtil.isBlank(request.getParameter("isRes"))){
			if(request.getParameter("isRes").equals("1")){
				effect.setRankingsAction(Contans.RANKINGS_ACTION_EFFECT_SUCCESS);
			}else{
				effect.setRankingsAction(Contans.RANKINGS_ACTION_EFFECT_ERROR);
			}
			return effect;
		}

		if(!StringUtil.isBlank(request.getParameter("click"))){
			effect.setRankingsAction(Contans.RANKINGS_ACTION_CLICK);
		}
		if(!StringUtil.isBlank(request.getParameter("consume"))){
			effect.setRankingsAction(Contans.RANKINGS_ACTION_CONSUME);
		}
		if(!StringUtil.isBlank(request.getParameter("isActivate"))){
			effect.setRankingsAction(Contans.RANKINGS_ACTION_ACTIVATE);
		}
		if(!StringUtil.isBlank(request.getParameter("isRegister"))){
			effect.setRankingsAction(Contans.RANKINGS_ACTION_REGISTER);
			effect.setRegisterUse((short)1);
		}
		if(!StringUtil.isBlank(request.getParameter("isRecharge"))){
			effect.setRankingsAction(Contans.RANKINGS_ACTION_RECHARGE);
		}
		if(!StringUtil.isBlank(request.getParameter("isCredit"))){
			effect.setRankingsAction(Contans.RANKINGS_ACTION_CREDIT);
		}
		if(!StringUtil.isBlank(request.getParameter("isBaddept"))){
			effect.setRankingsAction(Contans.RANKINGS_ACTION_BADDEPT);
		}
		
		return effect;
	}
}
