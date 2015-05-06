package com.codrim.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codrim.manager.IntegralWallManager;
import com.codrim.manager.IntegralWallResultBean;

@Controller
public class IntegralWallController {
	@Autowired
	IntegralWallManager wallManager;
	private static final Logger logger = Logger.getLogger("yj");

	@RequestMapping("/codrim.do")
	@ResponseBody
	public IntegralWallResultBean getYouMiData(HttpServletRequest request)
			throws UnsupportedEncodingException {
		String ptn = request.getParameter("ptn");
		IntegralWallResultBean bean = new IntegralWallResultBean();
		if (ptn.equals("youmi")) {
			logger.info("有米积分墙回调开始");
			bean = wallManager.resolveYouMi(request, bean);
		} else if (ptn.equals("wanpu")) {
			logger.info("万普积分墙回调开始");
			bean = wallManager.resolveWanPu(request, bean);
		} else if (ptn.equals("domob")) {
			logger.info("多盟积分墙回调开始");
			bean = wallManager.resolveDomob(request, bean);
		} else if (ptn.equals("dianjoy")) {
			logger.info("点入积分墙回调开始");
			bean = wallManager.resolveDianjoy(request, bean);
		} else if (ptn.equals("chukong")) {
			logger.info("触控积分墙回调开始");
			bean = wallManager.resolveChukong(request, bean);
		} else if (ptn.equals("anwo")) {
			logger.info("安沃积分墙回调开始");
			bean = wallManager.resolveAnwo(request, bean);
		} else if (ptn.equals("miidi")) {
			logger.info("米迪积分墙回调开始");
			bean = wallManager.resolveMiDi(request, bean);
		} else if (ptn.equals("qidian")) {
			logger.info("麒点积分墙回调开始");
			bean = wallManager.resolveQiDian(request, bean);
		} else if (ptn.equals("yijifen")) {
			logger.info("易积分墙回调开始");
			bean = wallManager.resolveYiJiFen(request, bean);
		}
		
		return bean;
	}

}
