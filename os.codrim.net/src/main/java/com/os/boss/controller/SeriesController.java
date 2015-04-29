/**
 * 
 */
package com.os.boss.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.codrim.common.SelectJsonResult;
import common.codrim.service.SeriesService;

/**
 * @author Administrator
 *
 */
@Controller
public class SeriesController {
@Autowired
private SeriesService seriesService;

@RequestMapping("/getSeriesName.do")
@ResponseBody
public List<SelectJsonResult> getAllSeriesName(HttpServletRequest request){
	return seriesService.selectAll();
}
	
	
}
