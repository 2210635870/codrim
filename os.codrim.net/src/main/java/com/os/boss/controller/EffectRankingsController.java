package com.os.boss.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.codrim.common.SelectEffectRankings;
import common.codrim.common.SelectResultByCodition;
import common.codrim.service.EffectRankingsService;
import common.codrim.util.DateUtil;

@Controller
public class EffectRankingsController {
	
	@Autowired
	private EffectRankingsService effectRankingsService;
	private static final Logger log=Logger.getLogger("BOSS");
	
	/**
	 * 按条件获取IOS冲榜效果表信息
	 * @param  request
	 * @return    Map<String, Object>
	 * */
	@RequestMapping("/getEffectRankings.do")
	@ResponseBody
	public Map<String, Object> searchRankingsReport(HttpServletRequest request) throws DataAccessException{
		List<SelectEffectRankings> list=new ArrayList<SelectEffectRankings>();
		int startPage;
		try {
 			startPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			startPage = 1;
			log.error("----------------------------------传入分页参数              错误startPage"
					+ startPage + ":::" + e);
		}
		int size;
		try {
			size = Integer.parseInt(request.getParameter("rows"));
		} catch (NumberFormatException e) {
			size = 10;
			log.error("----------------------------------传入分页参数错误size" + size
					+ ":::" + e);
		}
		//查询条件
		String deviceplate=request.getParameter("deviceplate");
		String productName=request.getParameter("productName");
		String channelName=request.getParameter("channelName");
		
		String type="yyyy-MM-dd HH:mm:ss";
		Date startTime=DateUtil.convertStringToDate(request.getParameter("startTime")+" 00:00:00", type);
		Date endTime=DateUtil.convertStringToDate(request.getParameter("endTime")+" 23:59:59", type);
		
		
		
        //short effect=Short.parseShort(request.getParameter("effect"));
		SelectResultByCodition codition = new SelectResultByCodition();
		codition.setDeviceplate(deviceplate);
		codition.setProductName(productName);
		codition.setChannelName(channelName);
		//codition.setEffect(effect);
		codition.setStartTime(startTime);
		codition.setEndTime(endTime);
		codition.setStartPage(startPage);
		codition.setSize(size);
		list = effectRankingsService.getTbEffectRankingsByPages(codition);
		int total = effectRankingsService.getTotalTbEffectRankings(codition);
		Map<String, Object> map = new HashMap<String, Object>();
 		map.put("total",total);
		map.put("rows", list);
		map.put("success", true);
		log.info("----------------------------------获取所有IOS冲榜效果表信息");
		return map;
	}

}
