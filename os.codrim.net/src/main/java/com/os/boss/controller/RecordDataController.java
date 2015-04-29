package com.os.boss.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.os.boss.service.RecordReportService;

import common.codrim.common.ResultJsonBean;
import common.codrim.pojo.TbProductDetail;
import common.codrim.pojo.TbRankingsReport;
import common.codrim.service.RankingsReportService;


@Controller
public class RecordDataController {
	@Autowired
	private RecordReportService recordReportService;
	@Autowired
	private RankingsReportService rankingsReportService;
	
	private static final Logger log=Logger.getLogger("BOSS");
	
	
	
	@RequestMapping("/getReportDataToRecord.do")
	@ResponseBody
public ResultJsonBean 	getReportDataToRecord(HttpServletRequest request){
		ResultJsonBean jsonBean = new ResultJsonBean();
		TbProductDetail productDetail=recordReportService.getTbProductDetail(request);
		if(productDetail!=null){
		jsonBean.setObject(productDetail);
		jsonBean.setSuccess(true);
		}else{
			jsonBean.setSuccess(false);
		}
		return jsonBean;
	}
	
	@RequestMapping("/addRecord.do")
	@ResponseBody
	public ResultJsonBean addRecord(HttpServletRequest request)	{
		ResultJsonBean jsonBean = new ResultJsonBean();
	TbRankingsReport report=recordReportService.getTbRankingsReport(request);
			int i=0;
			if(report!=null){
			     i=rankingsReportService.modifyRankingsReport(report);;
			}
			if(i>0){
				jsonBean.setSuccess(true);
			}
			return jsonBean;
	}
	
}
