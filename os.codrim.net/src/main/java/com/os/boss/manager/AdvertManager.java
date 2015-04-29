/**
 * 
 */
package com.os.boss.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.os.boss.bean.ViewAdvertsAndPutChannelNumBean;

import common.codrim.common.Contans;
import common.codrim.common.ResultJsonBean;
import common.codrim.pojo.TbAdmin;
import common.codrim.pojo.TbAdvert;
import common.codrim.pojo.TbAdvertChannelEnquiry;
import common.codrim.pojo.TbAdvertPutChannel;
import common.codrim.pojo.TbAdvertPutChannelBudget;
import common.codrim.pojo.TbLinkParam;
import common.codrim.service.AdvertChannelEnquiryService;
import common.codrim.service.AdvertPutChannelBudgetService;
import common.codrim.service.AdvertPutChannelService;
import common.codrim.service.AdvertService;
import common.codrim.service.LinkParamService;
import common.codrim.util.DateUtil;
import common.codrim.util.StringUtil;

/**
 * @author Administrator
 *
 */
@Repository
public class AdvertManager {
	@Autowired
	AdvertService advertService;
	@Autowired
	AdvertPutChannelService advertPutChannelService;
	@Autowired
	AdvertChannelEnquiryService channelEnquiryService;
	@Autowired
	AdvertPutChannelBudgetService putChannelBudgetService;
	
	@Autowired
	LinkParamService linkParamService;
	public Map getAdvertsAndPutChannelNumByProductId(HttpServletRequest request){
		Map map=new HashedMap();
		List<ViewAdvertsAndPutChannelNumBean> beans=new ArrayList<ViewAdvertsAndPutChannelNumBean>();
		long id=0;
		try {
			id=Long.parseLong(request.getParameter("id"));
			map.put("total", 0);
			map.put("rows", beans);
		} catch (Exception e) {
			// TODO: handle exception
			return map;
		}
		List<TbAdvert> list=advertService.getAdvertsByproductId(id);
		if(list==null||list.size()<=0){
			map.put("total", 0);
			map.put("rows", beans);
			return map;
		}
		for(TbAdvert advert:list){
			ViewAdvertsAndPutChannelNumBean bean=this.convertAdvertToThis(advert);
			int putChannelNum=advertPutChannelService.selectPutChannelNumByAdvertId(advert.getId());
			bean.setPutChannelNums(putChannelNum);
			beans.add(bean);
		}
		map.put("total", list.size());
		map.put("rows", beans);
		return map;
	}
	public Map getAdvertsByProductId(HttpServletRequest request){
		Map map=new HashedMap();
		List<TbAdvert> beans=new ArrayList<TbAdvert>();
		long id=0;
		try {
			id=Long.parseLong(request.getParameter("id"));
		} catch (Exception e) {
			// TODO: handle exception
			map.put("total", 0);
			map.put("rows", beans);
			return map;
		}
		beans=advertService.getAdvertsByproductId(id);
		if(beans==null||beans.size()<=0){
			map.put("total", 0);
			map.put("rows", beans);
			return map;
		}
		map.put("total", beans.size());
		map.put("rows", beans);
		return map;
	}
	public ViewAdvertsAndPutChannelNumBean convertAdvertToThis(TbAdvert advert){
		ViewAdvertsAndPutChannelNumBean bean=new ViewAdvertsAndPutChannelNumBean();
		bean.setAccessPrice(advert.getAccessPrice());
		bean.setAddTime(advert.getAddTime());
		bean.setAdvertName(advert.getAdvertName());
		bean.setCreateName(advert.getCreateName());
		bean.setDeviceplate(advert.getDeviceplate());
		bean.setEffectType(advert.getEffectType());
		bean.setEffectTypeBack(advert.getEffectTypeBack());
		bean.setId(advert.getId());
		bean.setIsBack(advert.getIsBack());
		bean.setProductId(advert.getProductId());
		bean.setRequire(advert.getRequire());
		bean.setSettlementWay(advert.getSettlementWay());
		bean.setWaysOfCooperation(advert.getWaysOfCooperation());
		bean.setRunningStatus(advert.getRunningStatus());
		bean.setEvaluateStatus(advert.getEvaluateStatus());
		return bean;
	}
	/** 
	  *
	  * @author xulin
	  * @date 2015年2月27日
	  *  @param request
	  *  @param bean
	  *  @return
	  *  ResultJsonBean
	  */ 
	public ResultJsonBean addOrEditAdvert(HttpServletRequest request,ResultJsonBean bean) {
		// TODO Auto-generated method stub
		long productId =0;
		long id=0;
		try {
			id=Long.parseLong(request.getParameter("id"));
		} catch (Exception e) {
			// TODO: handle exception
			id=0;
		}
		try {
			productId=Long.parseLong(request.getParameter("productId"));
		} catch (Exception e) {
			// TODO: handle exception
			return bean;
		}
		short effectType=Short.parseShort(request.getParameter("effectType"));
		short effectTypeBack=Short.parseShort(request.getParameter("effectTypeBack"));
		short settlementWay=Short.parseShort(request.getParameter("settlementWay"));
		short waysOfCooperation=Short.parseShort(request.getParameter("waysOfCooperation"));
        String deviceplate=request.getParameter("deviceplate");
        double accessPrice=Double.parseDouble(request.getParameter("accessPrice"));
        String require=request.getParameter("require");
       int res=0;
       if (id==0) {
    	   String createName=request.getParameter("createName");
           Date addTime=DateUtil.getNowDateToDate(System.currentTimeMillis(), "yyyy-MM-dd hh:mm:ss");
          short runningStatus=Contans.RUNNING_STATUS_NEW;
          short evaluateStatus=Contans.EVALUATE_STATUS_CHANNEL_ENQUIRY;
    	   TbAdvert advert=new TbAdvert(productId, effectType, deviceplate,
    			   settlementWay, effectType, effectTypeBack, null, accessPrice, 
    			   waysOfCooperation, require, createName, addTime, runningStatus, evaluateStatus);
    	   res=advertService.saveAdvert(advert);
	}else{
		String operateEvaluateResult=request.getParameter("operateEvaluateResult");
		TbAdvert advert;
		if(!StringUtil.isBlank(operateEvaluateResult)&&operateEvaluateResult.equals("不接入")){
			 advert=new TbAdvert(id,productId, effectType, deviceplate,
		 			   settlementWay, effectType, effectTypeBack, null, accessPrice, 
		 			   waysOfCooperation, require, null, null, null, null);
				advert.setOperateEvaluateResult(Contans.OPERATE_RESULT);
		}else{
		advert=new TbAdvert(id,productId, effectType, deviceplate,
 			   settlementWay, effectType, effectTypeBack, null, accessPrice, 
 			   waysOfCooperation, require, null, null, null, null);
		}
		res=advertService.updateAdvert(advert);
	}
       if(res>0){
    	   bean.setSuccess(true);
       }else{
    	   bean.setSuccess(false);
       }
		return bean;
	}
	/** 
	  *
	  * @author xulin
	  * @date 2015年2月27日
	  *  @param request
	  *  @param bean
	  *  @return
	  *  ResultJsonBean
	 * @throws Exception 
	  */ 
	public ResultJsonBean addChannelEnquiry(HttpServletRequest request,
			ResultJsonBean bean) {
		// TODO Auto-generated method stub
		String json=request.getParameter("json");
		ObjectMapper mapper=new ObjectMapper();
		JavaType type=getCollectionType(mapper, List.class, TbAdvertChannelEnquiry.class);
		List<TbAdvertChannelEnquiry> list=null;
		try {
			list=mapper.readValue(json, type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return bean;
		}
		TbAdmin addAdmin=(TbAdmin) request.getSession().getAttribute("user");
		for(TbAdvertChannelEnquiry channelEnquiry:list){
			if(addAdmin!=null){
			channelEnquiry.setAddName(addAdmin.getAccount());
			channelEnquiry.setAddTime(new Date());
			}else{
				return  bean;
			}
		}
		
		
		int i=channelEnquiryService.insertByBatch(list);
		if(i>0){
			TbAdvert advert=new TbAdvert();
			advert.setId(list.get(0).getAdvertid());
			//advert.setEvaluateStatus(Contans.EVALUATE_STATUS_OPERATE);
			i=advertService.updateAdvert(advert);
			if(i>0)bean.setSuccess(true);
		}
		return bean;
	}
	 public static JavaType getCollectionType(ObjectMapper mapper,Class<?> collectionClass, Class<?>... elementClasses) {   
		       return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);   
		     }
	/** 
	  *
	  * @author xulin
	  * @date 2015年3月2日
	  *  @param request
	  *  @param bean
	  *  @return
	  *  ResultJsonBean
	  */ 
	public ResultJsonBean addOperateEvaluate(HttpServletRequest request,
			ResultJsonBean bean) {
		// TODO Auto-generated method stub
		long advertId=0;
		try {
			advertId=Long.parseLong(request.getParameter("advertId"));
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		String operateEvaluateName=request.getParameter("operateEvaluateName");
		Short operateEvaluateResult=Short.parseShort(request.getParameter("operateEvaluateResult"));
		Double operateEvaluatePrice=Double.parseDouble(request.getParameter("operateEvaluatePrice"));
		String operateEvaluateRemarkChannel=request.getParameter("operateEvaluateRemarkChannel");
		String operateEvaluateRemarkBusiness=request.getParameter("operateEvaluateRemarkBusiness");
		TbAdvert advert=new TbAdvert();
		advert.setId(advertId);
		advert.setOperateEvaluateName(operateEvaluateName);
		advert.setOperateEvaluatePrice(operateEvaluatePrice);
		advert.setOperateEvaluateRemarkBusiness(operateEvaluateRemarkBusiness);
		advert.setOperateEvaluateRemarkChannel(operateEvaluateRemarkChannel);
		advert.setOperateEvaluateResult(operateEvaluateResult);
		advert.setOperateEvaluateTime(DateUtil.getNowDateToDate(System.currentTimeMillis(), "yyy-MM-dd hh:mm:ss"));
	if(operateEvaluateResult==1){
		advert.setEvaluateStatus(Contans.EVALUATE_STATUS_FINAL);
	}
		int i=advertService.updateAdvert(advert);
		if(i>0)bean.setSuccess(true);
		return bean;
	}
	/** 
	  *
	  * @author xulin
	  * @date 2015年3月2日
	  *  @param request
	  *  @param bean
	  *  @return
	  *  ResultJsonBean
	  */ 
	public ResultJsonBean getAdvertById(HttpServletRequest request,
			ResultJsonBean bean) {
		// TODO Auto-generated method stub
		long id=0;
		try {
			id=Long.parseLong(request.getParameter("id"));
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		TbAdvert advert=advertService.geTbAdvertById(id);
		if(advert!=null){
			bean.setSuccess(true);
			bean.setObject(advert);
		}
		
		return bean;
	}
	/** 
	  *
	  * @author xulin
	  * @date 2015年3月2日
	  *  @param request
	  *  @param bean
	  *  @return
	  *  ResultJsonBean
	  */ 
	public ResultJsonBean addFinalEvaluate(HttpServletRequest request,
			ResultJsonBean bean) {
		// TODO Auto-generated method stub
		long advertId=0;
		try {
			advertId=Long.parseLong(request.getParameter("advertId"));
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		String finalEvaluateName=request.getParameter("finalEvaluateName");
		Short finalEvaluateResult=Short.parseShort(request.getParameter("finalEvaluateResult"));
		String finalEvaluateRemark=request.getParameter("finalEvaluateRemark");
		TbAdvert advert=new TbAdvert();
		advert.setId(advertId);
        advert.setFinalEvaluateName(finalEvaluateName);		
        advert.setFinalEvaluateRemark(finalEvaluateRemark);
        advert.setFinalEvaluateResult(finalEvaluateResult);
            advert.setEvaluateStatus(Contans.EVALUATE_STATUS_FINAL);
    	int i=advertService.updateAdvert(advert);
		if(i>0)bean.setSuccess(true);
		return bean;
	}
	public ResultJsonBean confimChannelEnquiry(HttpServletRequest request,
			ResultJsonBean bean) {
		// TODO Auto-generated method stub
		long advertId=0;
		try {
			advertId=Long.parseLong(request.getParameter("advertId"));
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		TbAdvert advert=new TbAdvert();
		advert.setId(advertId);
		advert.setEvaluateStatus(Contans.EVALUATE_STATUS_OPERATE);
		int i=advertService.updateAdvert(advert);
		if(i>0)bean.setSuccess(true);
		return bean;
	}
	public ResultJsonBean addPutChannel(HttpServletRequest request,
			ResultJsonBean bean) {
		// TODO Auto-generated method stub
		long productId=Long.parseLong(request.getParameter("productId"));
		long advertId=Long.parseLong(request.getParameter("advertId"));
		long channelId=Long.parseLong(request.getParameter("channelId"));
		String personInCharge=request.getParameter("personInCharge");
		Date startTime=DateUtil.convertStringToDate(request.getParameter("startTime"), "yyyy-MM-dd");
		Date endTime=DateUtil.convertStringToDate(request.getParameter("endTime"), "yyyy-MM-dd");
		   Short settlementWay=Short.parseShort(request.getParameter("settlementWay"));
        Short effectType=Short.parseShort(request.getParameter("effectType"));
        
        Short effectTypeBack=Short.parseShort(request.getParameter("effectTypeBack"));
        Double putPrice=Double.parseDouble(request.getParameter("putPrice"));
        Short isBack=Short.parseShort(request.getParameter("isBack"));
        Short effectCallBackType=Short.parseShort(request.getParameter("effectCallBackType"));
        String effectCallBackValue=request.getParameter("effectCallBackValue");
        Short putChannelType=Short.parseShort(request.getParameter("putChannelType"));
       String  effectCallBackUrl=null;
       String  effectCallBackParamName=null;

       if(effectCallBackType==Contans.EFFECT_CALLBACK_TYPE_URL){
    	   effectCallBackUrl=effectCallBackValue;
       }else{
    	   effectCallBackParamName=effectCallBackValue;
       }
        TbAdvertPutChannel advertPutChannel=new  TbAdvertPutChannel( null, productId, advertId, putChannelType, channelId,
        		putPrice, startTime, endTime, settlementWay, effectType, effectTypeBack, Contans.RUNNING_STATUS_NEW, effectCallBackType, effectCallBackParamName,
        		effectCallBackUrl, null, personInCharge, isBack, new Date());
        int i=advertPutChannelService.saveAdvertPutAdvert(advertPutChannel);
        if(i>0){
        	  if(isBack==Contans.TRUE){
        	List<TbAdvertPutChannelBudget> lists=this.convertTbAdvertPutChannelBudgets(request, advertPutChannel.getId());
        	if(lists!=null&&lists.size()>0){
        		putChannelBudgetService.saveAdvertPutAdvertBudgetByBatch(lists);
        	}
        	List<TbLinkParam> linkParams=this.converTbLinkParams(advertPutChannel.getId(), request);
        	int j=linkParamService.insertByBatch(linkParams);
        	if(j>0){
        		bean.setSuccess(true);
        	}
        	  }
        	
        }
        
		return bean;
	}   
	private List<TbLinkParam> converTbLinkParams(long advertPutChannelId,HttpServletRequest request ){
		List<TbLinkParam> list=new ArrayList<TbLinkParam>();
        String paramValue=request.getParameter("paramValue");
        String[]  values=paramValue.split(",");
		for(String str:values){
			TbLinkParam linkParam=new TbLinkParam();
			linkParam.setAdvertPutChannelId(advertPutChannelId);
			linkParam.setAddTime(new Date());
			linkParam.setCodrimParamId(Long.parseLong(str));
			linkParam.setLinkType(Contans.LINK_TYPE_CHANNEL);
		//	linkParam.setDefaultValueId(defaultValueId);
			linkParam.setIsChangeDefultValue(Contans.FALSE);
			list.add(linkParam);
		}
	return 	list;
	}
	private List<TbAdvertPutChannelBudget> convertTbAdvertPutChannelBudgets(HttpServletRequest request ,long advertPutChannelId){
		   Short budgeType=Short.parseShort(request.getParameter("budgeType"));
	        String budgeNumWithAll=request.getParameter("budgeNumWithAll");
	        String budgeNumWithDay=request.getParameter("budgeNumWithDay");      
	        List<TbAdvertPutChannelBudget> list=null;
	        	if(budgeType==Contans.BUDGE_TYPE_ALL){
		        	list=new ArrayList<TbAdvertPutChannelBudget>();
	        		TbAdvertPutChannelBudget advertPutChannelBudget=new TbAdvertPutChannelBudget();
	        		advertPutChannelBudget.setAdvertPutAdvertId(advertPutChannelId);
	        		advertPutChannelBudget.setBudgetType(budgeType);
	        		advertPutChannelBudget.setBudgeNum(Long.parseLong(budgeNumWithAll));
	        		list.add(advertPutChannelBudget);
	        	}
	        	if(budgeType==Contans.BUDGE_TYPE_DAY){
		        	list=new ArrayList<TbAdvertPutChannelBudget>();
                String[] budges=budgeNumWithDay.split(",");
                for(String str:budges){
                	String[] budgeWithDay=str.split("_");
                	TbAdvertPutChannelBudget advertPutChannelBudget=new TbAdvertPutChannelBudget();
	        		advertPutChannelBudget.setAdvertPutAdvertId(advertPutChannelId);
	        		advertPutChannelBudget.setBudgetType(budgeType);
	        		advertPutChannelBudget.setBudgetTime(DateUtil.convertStringToDate(budgeWithDay[0], "yyyy-MM-dd"));
                	advertPutChannelBudget.setBudgeNum(Long.parseLong(budgeWithDay[1]));
                	list.add(advertPutChannelBudget);
                	}
                }
			return list;
	        

	}
	public String convertAdvertName(short advertName){
		String name="";
		switch (advertName) {
		case Contans.EFFECT_TYPE_REGISTER:
			name="注册";
			break;
		case Contans.EFFECT_TYPE_ACTIVATE:
			name="激活";
			break;
		case Contans.EFFECT_TYPE_CONSUME_ONE:
			name="消费一次";
			break;
		case Contans.EFFECT_TYPE_CONSUME_MORE:
			name="消费两次以上";
			break;
		case Contans.EFFECT_TYPE_CONSUME_NUMBER:
			name="消费金额";
			break;
		
		}
		return name;
		
	}
	public Map putChannelList(HttpServletRequest request) {
		// TODO Auto-generated method stub
		long advertId=	Long.parseLong(request.getParameter("advertId"));
		Map map=new HashedMap();
		List<TbAdvertPutChannel> list=new ArrayList<TbAdvertPutChannel>();
		list=advertPutChannelService.geTbAdvertPutChannelList(advertId);
		map.put("total", list.size());
		map.put("rows", list);
		return map;
	}
	
}
