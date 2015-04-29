package com.codrim.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codrim.bean.JsonBasicResult;
import com.codrim.bean.JsonErrorResult;
import com.codrim.constant.ErrorCode;

import common.codrim.pojo.TbWzImageUrlSetting;
import common.codrim.service.WzImageUrlSettingService;
import common.codrim.wz.constant.DataConstant;

@Controller
public class BannerController extends BaseController{
	@Autowired
	WzImageUrlSettingService urlSettingService;
	
	/**
	 * 获取banner图片
	 * @param request
	 * @return
	 */
	@RequestMapping("/getUrl.do")
	@ResponseBody
	public JsonBasicResult getUrl(HttpServletRequest request){
		List<TbWzImageUrlSetting> list=urlSettingService.getTbWzImageUrlSettings();
		JsonBasicResult<List<TbWzImageUrlSetting>> result = new JsonBasicResult<List<TbWzImageUrlSetting>>();
		 if(list!=null){
			 for(TbWzImageUrlSetting urlSetting:list){
				 urlSetting.setUrl(imgProjectPath+urlSetting.getUrl());
			 }
			 result.setRtCode(DataConstant.SUCCESS);
			 result.setResult(list);
		 }else{
			 result.setRtCode(DataConstant.FAIL);
		 }
		return result;
	}
	
	
}
