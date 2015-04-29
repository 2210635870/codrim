/**
 * 
 */
package com.os.boss.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.os.boss.service.ChannelReportExternalManager;
import common.codrim.common.SelectJsonResult;
import common.codrim.dao.TbEffectRankingsMapper;
import common.codrim.service.EffectRankingsService;

/**
 * @author Administrator
 *
 */
@Controller
public class ChannelReportExternalController {
	@Autowired
	private ChannelReportExternalManager externalManager;
	@Autowired
	private EffectRankingsService effectRankingsService;
	@Autowired
	TbEffectRankingsMapper dao;
	
	@RequestMapping("/getProductNameByCN.do")
	@ResponseBody
	public List<SelectJsonResult>  getProductNameByChannelName(HttpServletRequest request){
		String channelName=request.getParameter("channelName");
		return externalManager.getProductsByChannelName(channelName);
	}
	@RequestMapping("/getChannelReportWithExternal.do")
	@ResponseBody
	public Map<String, Object> getReport(HttpServletRequest request){
		return externalManager.getReportExternalBeans(request);
	}
	
	
	
	
	
	
}
