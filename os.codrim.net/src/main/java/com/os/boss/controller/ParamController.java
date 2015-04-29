/**
 * 
 */
package com.os.boss.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.os.boss.manager.ParamManager;
import common.codrim.common.ResultJsonBean;
import common.codrim.pojo.TbCodrimLinkParam;
import common.codrim.service.CodrimLinkParamService;

/**
 * @author Administrator
 *
 */
@Controller
public class ParamController {
@Autowired
CodrimLinkParamService codrimLinkParamService;
	@Autowired
	ParamManager paramManager;
@RequestMapping("/saveCPParam.do")
@ResponseBody
public ResultJsonBean saveCPParam(HttpServletRequest request){
	return paramManager.saveCPParam(request);
}

@RequestMapping("/getCPParam.do")
@ResponseBody
public ResultJsonBean getCPParam(HttpServletRequest request){
	return paramManager.getCPParam(request);
}


	@RequestMapping("/getAllCodrimLinkParam.do")
	@ResponseBody
	public Map getAllCodrimLinkParams(HttpServletRequest request){
		Map map=new HashedMap();
		List<TbCodrimLinkParam> list=new ArrayList<TbCodrimLinkParam>();
		list=codrimLinkParamService.selectAll();
		map.put("total", list.size());
		map.put("rows", list);
		return map;
	}
	@RequestMapping("/validCodrimLinkParamName.do")
	@ResponseBody
	public boolean validCodrimLinkParamZhName(HttpServletRequest request){
				String type=request.getParameter("paramType");
				String name=request.getParameter("name");
		TbCodrimLinkParam codrimLinkParam=codrimLinkParamService.validCodrimLinkParamName(type,name);
		if(codrimLinkParam==null){
			return true;
		}else{
			return false;
		}
	}
	
	
	@RequestMapping("/saveCodrimLinkParam.do")
	@ResponseBody
	public ResultJsonBean saveCodrimLinkParam(HttpServletRequest request){
		ResultJsonBean bean=new ResultJsonBean();
		String paramZhName=request.getParameter("paramZhName");
		String paramEnName=request.getParameter("paramEnName");
        Short paramType=Short.parseShort(request.getParameter("paramType"));
		TbCodrimLinkParam codrimLinkParam=new TbCodrimLinkParam();
		codrimLinkParam.setAddTime(new Date());
        codrimLinkParam.setParamEnName(paramEnName);
        codrimLinkParam.setParamZhName(paramZhName);
        codrimLinkParam.setParamType(paramType);
       int i= codrimLinkParamService.saveCodrimLinkParam(codrimLinkParam);
       if(i>0){
    	   bean.setSuccess(true);
       }
		return bean;
	}
	
	@RequestMapping("/editCodrimLinkParam.do")
	@ResponseBody
	public ResultJsonBean editCodrimLinkParam(HttpServletRequest request){
		ResultJsonBean bean=new ResultJsonBean();
		long id=0;
		try {
			id=Long.parseLong(request.getParameter("id"));
		} catch (Exception e) {
			// TODO: handle exception
			return bean;
		}
		String paramZhName=request.getParameter("paramZhName");
		String paramEnName=request.getParameter("paramEnName");
        Short paramType=Short.parseShort(request.getParameter("paramType"));
		TbCodrimLinkParam codrimLinkParam=new TbCodrimLinkParam();
		codrimLinkParam.setId(id);
		codrimLinkParam.setAddTime(new Date());
        codrimLinkParam.setParamEnName(paramEnName);
        codrimLinkParam.setParamZhName(paramZhName);
        codrimLinkParam.setParamType(paramType);
       int i= codrimLinkParamService.updateCodrimLinkParam(codrimLinkParam);
       if(i>0){
    	   bean.setSuccess(true);
       }
		return bean;
	}
	

	
	
	
	
}
