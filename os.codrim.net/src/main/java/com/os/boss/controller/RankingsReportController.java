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


import common.codrim.common.ResultJsonBean;
import common.codrim.common.SelectResultByCodition;
import common.codrim.pojo.TbRankingsReport;
import common.codrim.service.RankingsReportService;
import common.codrim.util.DateUtil;

@Controller
public class RankingsReportController {
	@Autowired
	private RankingsReportService rankingsReportService;
	private static final Logger log=Logger.getLogger("BOSS");
	
	
	

@RequestMapping("/modifySubtract.do")
@ResponseBody
public 	ResultJsonBean modifySubtract(HttpServletRequest request){
	 ResultJsonBean jsonBean = new ResultJsonBean(); 
		long id = 0;
		try {
			id = Long.parseLong(request.getParameter("id"));
		} catch (NumberFormatException e) {
			// TODO: handle exception
			id=0;
		}
		if(id==0){
			jsonBean.setSuccess(false);
			return jsonBean;
		}
	 TbRankingsReport rankingsReport=new TbRankingsReport();
	 rankingsReport.setId(id);
	 rankingsReport.setSubtractNum(Integer.parseInt(request.getParameter("subtractNum")));
	 rankingsReport.setChannelExternalNum(Long.parseLong(request.getParameter("channelExternalNum")));
	 rankingsReport.setIssubtract(Short.parseShort(request.getParameter("issubtract")));
	 int i=rankingsReportService.modifyRankingsReport(rankingsReport);
	 if(i>0){
		 jsonBean.setSuccess(true);
	 }
	 return jsonBean;
}

	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 按条件获取iOS冲榜报表信息
	 * @param  request
	 * @return    Map<String, Object>
	 * */
	@RequestMapping("/getRankingsReport.do")
	@ResponseBody
	public Map<String, Object> searchRankingsReport(HttpServletRequest request) throws DataAccessException{
		List<TbRankingsReport> list=new ArrayList<TbRankingsReport>();
		int startPage;
		try {
			startPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			startPage = 1;
			log.error("----------------------------------传入分页参数错误startPage"
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
		String deviceplate=request.getParameter("deviceplate");
		String customerName=request.getParameter("customerName");
		String productName=request.getParameter("productName");
		String channelName=request.getParameter("channelName");
		String type="yyyy-MM-dd";
		Date startTime=DateUtil.convertStringToDate(request.getParameter("startTime"), type);
		Date endTime=DateUtil.convertStringToDate(request.getParameter("endTime"), type);
		Date nowTime=DateUtil.getNowDateToDate(System.currentTimeMillis(), "yyyy-MM-dd");

		SelectResultByCodition codition = new SelectResultByCodition();
		codition.setDeviceplate(deviceplate);
		codition.setCustomerName(customerName);
		codition.setProductName(productName);
		codition.setChannelName(channelName);
		codition.setStartTime(startTime);
		codition.setEndTime(endTime);
		if(endTime.getTime()==nowTime.getTime()&&startPage==1){
			size=size-1;
			startPage=(startPage-1)*size;
		}else if(endTime.getTime()==nowTime.getTime()&&startPage!=1){
			startPage=(startPage-1)*(size-1);
		}else{
			startPage=(startPage-1)*size;
		}
		codition.setStartPage(startPage);
		codition.setSize(size);
		list = rankingsReportService.getRankingsReportByPage(nowTime,codition);
		int total = rankingsReportService.getTotalRandingsReport(codition);
		if(startTime.getTime()==nowTime.getTime()&&startPage==1){
			total=total+1;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total",total);
		map.put("rows", list);
		
		map.put("success", true);
		log.info("----------------------------------获取所有iOS冲榜报表信息");
		return map;
	}
}
