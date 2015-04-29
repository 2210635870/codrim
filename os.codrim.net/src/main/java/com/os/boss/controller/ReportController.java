/**
 * 
 */
package com.os.boss.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.os.boss.service.ReportService;
import common.codrim.common.ResultJsonBean;
import common.codrim.common.TotalReportBean;



/**
 * @author Administrator
 *
 */
@Controller
public class ReportController {
@Autowired
ReportService reportService;
	
	
	@RequestMapping("/getAllReport.do")
	@ResponseBody
	public ResultJsonBean  getAllReport(HttpServletRequest request){
		ResultJsonBean jsonBean = new ResultJsonBean();
		TotalReportBean bean=reportService.getData(request);
		if(bean!=null){
			jsonBean.setSuccess(true);
			jsonBean.setObject(bean);
		}else{
			jsonBean.setSuccess(false);
		}
		return jsonBean;
	}
	
	
	
	
	
	
	
}
