package com.codrim.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codrim.bean.JsonBasicResult;
import com.codrim.manager.IntegralWallManager;
import com.codrim.manager.IntegralWallResultBean;
import common.codrim.pojo.TbWzBinding;
import common.codrim.pojo.TbWzUser;
import common.codrim.service.WzUserService;
import common.codrim.util.PropertiesUtil;
import common.codrim.util.StringUtil;
import common.codrim.wz.constant.DataConstant;

@Controller
public class IntegralWallController {
@Autowired
IntegralWallManager wallManager;
	private static final Logger logger = Logger.getLogger("yj");

	
	
	@RequestMapping("/codrim.do")
	@ResponseBody
	public IntegralWallResultBean getYouMiData(HttpServletRequest request) throws UnsupportedEncodingException{
		String ptn=request.getParameter("ptn");
		IntegralWallResultBean bean = new IntegralWallResultBean();
if(ptn.equals("youmi")){
	logger.info("有米积分墙回调开始");
	bean=wallManager.resolveYouMi(request,bean);
}
if(ptn.equals("wanpu")){
	logger.info("万普积分墙回调开始");
	bean=wallManager.resolveWanPu(request,bean);
}
if(ptn.equals("domob")){
	logger.info("多盟积分墙回调开始");
	bean=wallManager.resolveDomob(request,bean);
}

if(ptn.equals("dianjoy")){
	logger.info("点入积分墙回调开始");
	bean=wallManager.resolveDianjoy(request,bean);
}
if(ptn.equals("chukong")){
	logger.info("触控积分墙回调开始");
	bean=wallManager.resolveChukong(request,bean);
}
if(ptn.equals("anwo")){
	logger.info("安沃积分墙回调开始");
	bean=wallManager.resolveAnwo(request,bean);
}
         return bean;
	}
	

	
	
	
	
}
