package com.codrim.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class TempDownloadController {
private static 	String wandoujia="http://apps.wandoujia.com/apps/com.nhzw.bingdu/download";
private static 	String 	baidu="http://gdown.baidu.com/data/wisegame/23ff086f1bf608a5/bingduxinwen_48.apk";
private static  String 	xiaomi="http://app.mi.com/download/88003";
	 
	@RequestMapping("/download_{type}")
	public  ModelAndView download(@PathVariable String type){
	
		if("wandoujia".equals(type)){
			return new ModelAndView(new RedirectView(wandoujia));
		}
		if("baidu".equals(type)){
			return new ModelAndView(new RedirectView(baidu));
		}
		if("xiaomi".equals(type)){
			return new ModelAndView(new RedirectView(xiaomi));
		}
		return new ModelAndView(new RedirectView("http://img.codrim.net/yingjia/yingjia.apk")); 
	}
	
	
	
}
