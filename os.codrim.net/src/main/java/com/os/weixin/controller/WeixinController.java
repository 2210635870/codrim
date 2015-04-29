package com.os.weixin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.os.weixin.bean.SignatureResultBean;
import com.os.weixin.service.WeixinManager;

import common.codrim.common.Contans;
import common.codrim.util.StringUtil;

@Controller
public class WeixinController {
@Autowired
WeixinManager weixinManager;
	
	@RequestMapping("/getSignature.do")
	@ResponseBody
	public SignatureResultBean getSignature(HttpServletRequest request,HttpServletResponse response){
		SignatureResultBean bean=new SignatureResultBean();
		String url=request.getParameter("url");
		if(StringUtil.isBlank(url)){
			bean.setCode(Contans.FALSE+"");
			return bean;
		}
		response.setCharacterEncoding("utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST,GET" );
		response.addHeader("Access-Control-Allow-Credentials", "true" );
		return weixinManager.getSignature(url,bean);
	}
	
	
	
	
	
	
	
	
}
