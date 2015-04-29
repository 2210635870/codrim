/**
 * 
 */
package com.codrim.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codrim.common.ResultJson;
import com.codrim.service.ApiService;
import com.codrim.util.GetParametersUtil;
import common.codrim.common.Contans;
import common.codrim.pojo.TbProductDetail;
import common.codrim.util.StringUtil;

/**
 * @author Administrator
 *
 */
@Controller
public class EffectController extends CommonController{
	private static final Logger log = Logger.getLogger("API");
	@Autowired
	ApiService apiService;

	/** 
	  *获取效果
	  * @author xulin
	  * @date 2014年12月10日
	  *  @param request
	  *  @return
	  *  ResultJson
	  */ 
	@RequestMapping("/statis")
	@ResponseBody
	public ResultJson addEffectRanking(HttpServletRequest request) {
		String appId = request.getParameter("appId");
		String source=request.getParameter("source");
		String devicePlate=request.getParameter("dp");
		GetParametersUtil.getParameters(request, log);
		boolean flag = false;
		if(StringUtil.isBlank(source)||StringUtil.isBlank(appId)){
			return getResultJson(flag);
		}
			TbProductDetail product =apiService.getProductWithAppId(source, devicePlate, Contans.RUNNING_STATUS_RUNNING,appId,"channelName");
			if (product != null) {
				flag=apiService.effect(product, request);
			}
		return getResultJson(flag);
	}


//	/** 
//	  *获取用户效果所有行为
//	  * @author xulin
//	  * @date 2014年12月10日
//	  *  @param request
//	  *  @return
//	  *  ResultJson
//	  */ 
//	@RequestMapping("/statis")
//	@ResponseBody
//	public ResultJson statisRanking(HttpServletRequest request) {
//		boolean flag = false;
//		String appId = request.getParameter("appId");
//		String source=request.getParameter("source");
//		String devicePlate=request.getParameter("dp");
//		GetParametersUtil.getParameters(request, log);
//		log.info("-------------------获取冲榜用户行为---"+appId+source);
//		if(StringUtil.isBlank(source)||StringUtil.isBlank(appId)){
//			return getResultJson(flag);
//		}
//		TbProductDetail product=null;
//		if("android".equals(devicePlate)){
//			product	= apiService.getProductDetail(appId,source,devicePlate,Contans.RUNNING_STATUS_RUNNING,"channelNumber");
//		}
//		if("ios".equals(devicePlate)){
//			product=apiService.getProductWithAppId(source, devicePlate, Contans.RUNNING_STATUS_RUNNING,appId,"channelName");
//		}
//		if (product != null) {
//			flag=apiService.addEffectRanking(product, request,"");
//		}
//		return getResultJson(flag);
//	}
}
