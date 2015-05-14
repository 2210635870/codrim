package com.os.wz.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.codrim.common.Contans;
import common.codrim.common.ResultJsonBean;
import common.codrim.common.SelectResultByCodition;
import common.codrim.pojo.TbWzGameH5;
import common.codrim.service.WzGameH5Service;

@Controller
public class GameController {
	private static final Logger log = Logger.getLogger("BOSS");
@Autowired
WzGameH5Service h5Service;
	
	
	
	@RequestMapping("/getGameH5List.do")
	@ResponseBody
	public Map getGameH5List(HttpServletRequest request){
		List<TbWzGameH5> list = new ArrayList<TbWzGameH5>();
		int startPage = 0;
		try {
			startPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			startPage = 1;
			log.error("----------------------------------传入分页参数错误startPage"
					+ startPage + ":::" + e);
		}
		int size = 0;
		try {
			size = Integer.parseInt(request.getParameter("rows"));
		} catch (NumberFormatException e) {
			size = 10;
			log.error("----------------------------------传入分页参数错误size" + size
					+ ":::" + e);
		}
		SelectResultByCodition codition=new SelectResultByCodition();
		codition.setSize(size);
		codition.setStartPage((startPage-1)*size);
		 list = h5Service.getGameH5List(codition);
     int total=h5Service.getTotalGameH5(codition);
		Map map=new HashMap();
		map.put("total", total);
		map.put("rows", list);
		return map;
		
	}
	
	@RequestMapping("/addH5.do")
	@ResponseBody
	public ResultJsonBean addH5(TbWzGameH5 h5){
		ResultJsonBean bean=new ResultJsonBean();
		h5.setAddDate(new Date());
		h5.setStatus(Contans.RUNNING_STATUS_RUNNING);
		int i=h5Service.saveH5(h5);
		if(i>0){
		bean.setSuccess(true);
		}
		return bean;
	}
	@RequestMapping("/editH5.do")
	@ResponseBody
	public ResultJsonBean editH5(TbWzGameH5 h5){
		ResultJsonBean bean=new ResultJsonBean();
		if(h5.getId()==null||h5.getId()==0){
			return bean;
		}
		int i=h5Service.update(h5);
		if(i>0){
		bean.setSuccess(true);
		}
		return bean;
	}
	
	/** 
	 * 注册时间类型的属性编辑器，将String转化为Date 
	 */  
	@InitBinder  
	public void initBinder(ServletRequestDataBinder binder) {  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(  
	            new SimpleDateFormat("yyyy-MM-dd"), true));  
}
}