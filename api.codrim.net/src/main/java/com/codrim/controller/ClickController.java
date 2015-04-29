/**
 * 
 */
package com.codrim.controller;

import java.io.UnsupportedEncodingException;

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
public class ClickController extends CommonController {
	@Autowired
	ApiService apiService;
	private static final Logger log = Logger.getLogger("API");
	
	
	/** 
	  *点击
	  * @author xulin
	  * @date 2014年12月5日
	  *  @param request
	  *  @return
	  *  ModelAndView
	 * @throws UnsupportedEncodingException 
	  */ 
	@RequestMapping("/rankClick")
	@ResponseBody
    public ResultJson  rankingsClick(HttpServletRequest request) throws UnsupportedEncodingException{
		boolean flag=false;
		String source=request.getParameter("source");
		String devicePlate=request.getParameter("dp");
		String appId=request.getParameter("appId");
		String idfa=request.getParameter("idfa");
		if(StringUtil.isBlank(idfa)||StringUtil.isBlank(source)||StringUtil.isBlank(devicePlate)||StringUtil.isBlank(appId)){
			return getResultJson(flag);
		}
		GetParametersUtil.getParameters(request, log);
		TbProductDetail rankingsProduct=apiService.getProductWithAppId(source, devicePlate, Contans.RUNNING_STATUS_RUNNING,appId,"channelName");
		if(rankingsProduct!=null){
			flag=apiService.rankingClick(rankingsProduct,request);
		}
			return getResultJson(flag);
	}

	
	
}
