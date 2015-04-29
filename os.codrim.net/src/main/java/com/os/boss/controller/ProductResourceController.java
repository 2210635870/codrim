/**
 * 
 */
package com.os.boss.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.codrim.common.Contans;
import common.codrim.common.ResultJsonBean;
import common.codrim.pojo.TbProductResource;
import common.codrim.service.ProductResourceService;
import common.codrim.util.StringUtil;

/**
 * @author Administrator
 *
 */
@Controller
public class ProductResourceController {
@Autowired
ProductResourceService resService;
	
	@RequestMapping("/savePath.do")
	@ResponseBody
	public ResultJsonBean saveRes(HttpServletRequest request){
		ResultJsonBean json=new ResultJsonBean();
	long id=0;
	try {
		id=Long.parseLong(request.getParameter("id"));
	} catch (Exception e) {
		// TODO: handle exception
		return json;
	}
	String banner=request.getParameter("banner");
	String tablescreen=request.getParameter("tablescreen");
	String fullscreen=request.getParameter("fullscreen");
	List<TbProductResource> list=new ArrayList<TbProductResource>();
	if(!StringUtil.isBlank(banner)){
	    TbProductResource resource=new TbProductResource(id, banner, Contans.PRODUCT_RESOURCE_BANNER);
	    list.add(resource);
	}
	if(!StringUtil.isBlank(tablescreen)){
	    TbProductResource resource=new TbProductResource(id, tablescreen, Contans.PRODUCT_RESOURCE_TABLE_SCREEN);
	    list.add(resource);
	}
	if(!StringUtil.isBlank(fullscreen)){
	    TbProductResource resource=new TbProductResource(id, fullscreen, Contans.PRODUCT_RESOURCE_FULL_SCREEN);
	    list.add(resource);
	}
	int i=resService.insertByBatch(list);
	if(i>0){
		json.setSuccess(true);
	}
	return json;
	}
	
	
	
	
}
