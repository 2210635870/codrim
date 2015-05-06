package com.os.wz.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import common.codrim.common.Contans;
import common.codrim.common.ResultJsonBean;
import common.codrim.pojo.TbAdmin;
import common.codrim.pojo.TbWzAppUpgrade;
import common.codrim.pojo.TbWzCoinCashSetting;
import common.codrim.pojo.TbWzCommonSetting;
import common.codrim.pojo.TbWzImageUrlSetting;
import common.codrim.pojo.TbWzTaskNumLimit;
import common.codrim.service.WzCoinCashService;
import common.codrim.service.WzImageUrlSettingService;
import common.codrim.service.WzTaskNumLimitService;
import common.codrim.util.JsonUtil;
import common.codrim.util.MemcacheUtil;
import common.codrim.wz.constant.DataConstant;

@Controller
@SessionAttributes({ "setting", "auSetting" })
public class SettingController extends BaseController {
	@Autowired
	WzImageUrlSettingService urlSettingService;
	@Autowired
	WzCoinCashService coinCashService;

	@Autowired
	WzTaskNumLimitService numLimitService;

	private static final Logger log = Logger.getLogger("yj");

	@RequestMapping("/getNumLimits.do")
	@ResponseBody
	public Map getNumLimits() {
		Map map = new HashMap();
		List<TbWzTaskNumLimit> list = new ArrayList<TbWzTaskNumLimit>();
		list = numLimitService.getAll();
		map.put("rows", list);
		map.put("total", list.size());
		return map;
	}

	@RequestMapping("/updateNumLimit.do")
	@ResponseBody
	public ResultJsonBean updateNumLimit(HttpServletRequest request) {
		ResultJsonBean bean = new ResultJsonBean();
		TbWzTaskNumLimit limit = new TbWzTaskNumLimit();
		TbAdmin admin = (TbAdmin) request.getSession().getAttribute("user");
		if (admin != null) {
			limit.setAddName(admin.getAccount());
			limit.setId(Integer.parseInt(request.getParameter("id")));
			limit.setLimitNum(Integer.parseInt(request.getParameter("limitNum")));
			limit.setRemark(request.getParameter("remark"));
			int i = numLimitService.updateTaskNumLimitWithCache(limit);
			if (i > 0) {
				bean.setSuccess(true);
			}
		}
		return bean;
	}

	@RequestMapping("/getCashSettings.do")
	@ResponseBody
	public Map getGoldCashSettings() {
		Map map = new HashMap();
		List<TbWzCoinCashSetting> list = new ArrayList<TbWzCoinCashSetting>();
		list = coinCashService.getCashSettings();
		map.put("rows", list);
		map.put("total", list.size());
		return map;
	}

	@RequestMapping("/addCoinCashSetting.do")
	@ResponseBody
	public ResultJsonBean addCoinCashSetting(TbWzCoinCashSetting cashSetting) {
		ResultJsonBean bean = new ResultJsonBean();
		int i = coinCashService.saveCoinCashSetting(cashSetting);
		if (i > 0) {
			bean.setSuccess(true);
		}
		return bean;
	}

	@RequestMapping("/updateCoinCashSetting.do")
	@ResponseBody
	public ResultJsonBean updateCoinCashSetting(TbWzCoinCashSetting cashSetting) {
		ResultJsonBean bean = new ResultJsonBean();
		int i = coinCashService.updateCoinCashSetting(cashSetting);
		if (i > 0) {
			bean.setSuccess(true);
		}
		return bean;
	}

	@RequestMapping("/getImageUrlSettingList.do")
	@ResponseBody
	public Map getImageUrlSettingList() {
		Map map = new HashMap();
		List<TbWzImageUrlSetting> list = new ArrayList<TbWzImageUrlSetting>();
		list = urlSettingService.getTbWzImageUrlSettings();
		map.put("rows", list);
		map.put("total", list.size());
		return map;
	}

	@RequestMapping("/addImageUrlSetting.do")
	@ResponseBody
	public ResultJsonBean addImageUrlSetting(HttpServletRequest request) {
		ResultJsonBean bean = new ResultJsonBean();
		TbWzImageUrlSetting urlSetting = new TbWzImageUrlSetting();
		urlSetting.setUrl(request.getParameter("url"));
		urlSetting.setSkipUrl(request.getParameter("skipUrl"));
		short type = Short.parseShort(request.getParameter("type"));
		urlSetting.setType(type);
		if (type == 2) {
			urlSetting
					.setTaskId(Long.parseLong(request.getParameter("taskId")));
			urlSetting.setTaskName(request.getParameter("taskName"));
		}
		int i = urlSettingService.saveImageUrlSetting(urlSetting);
		if (i > 0) {
			bean.setSuccess(true);
		}
		return bean;
	}

	@RequestMapping("/editImageUrlSetting.do")
	@ResponseBody
	public ResultJsonBean editImageUrlSetting(HttpServletRequest request) {
		ResultJsonBean bean = new ResultJsonBean();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			return bean;
		}
		TbWzImageUrlSetting urlSetting = new TbWzImageUrlSetting();
		urlSetting.setId(id);
		urlSetting.setUrl(request.getParameter("url"));
		urlSetting.setSkipUrl(request.getParameter("skipUrl"));
		urlSetting.setShowSer(Integer.parseInt(request.getParameter("showSer")));
		short type = Short.parseShort(request.getParameter("type"));
		urlSetting.setType(type);
		if (type == 2) {
			urlSetting.setTaskId(Long.parseLong(request.getParameter("taskId")));
			urlSetting.setTaskName(request.getParameter("taskName"));
		}
		int i = urlSettingService.updateImageUrlSetting(urlSetting);
		if (i > 0) {
			bean.setSuccess(true);
		}
		return bean;
	}
	
	@RequestMapping("/deleteImageUrlSetting.do")
	@ResponseBody
	public ResultJsonBean deleteImageUrlSetting(@RequestParam("id")int id, SessionStatus sessionStatusn) {
		ResultJsonBean rjb = new ResultJsonBean();
		
		try {
			urlSettingService.deleteImageUrlSetting(id);
			rjb.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(">>>>>  error:", e);
			rjb.setSuccess(false);
		}
		
		return rjb;
	}

	@RequestMapping("/wz/setting/initCurrencySetting.do")
	public ModelAndView initCurrencySetting() {
		ModelAndView mv = new ModelAndView("/wz/setting/currencySetting");

		mv.addObject("setting", settingService.getCommonSetting());

		return mv;
	}

	@RequestMapping("/wz/setting/updateCurrencySetting.do")
	@ResponseBody
	public ResultJsonBean updateCurrencySetting(HttpServletRequest request,
			@ModelAttribute("setting") TbWzCommonSetting setting,
			SessionStatus sessionStatus) {

		ResultJsonBean rjb = new ResultJsonBean();
		rjb.setSuccess(true);

		try {
			settingService.modifyCommonSetting(setting);

			request.getSession().setAttribute(
					DataConstant.SESSION_KEY_SETTING_CURRENCY, setting);

			log.error(">>>>> Update Currency Setting Success!!");
			sessionStatus.setComplete();
		} catch (Exception e) {
			log.error(">>>>> Update Currency Setting Error: ", e);
			rjb.setSuccess(false);
			e.printStackTrace();
		}

		return rjb;
	}

	@RequestMapping("/wz/setting/initAppUpgradeSetting.do")
	public ModelAndView initAppUpgradeSetting() {
		ModelAndView mv = new ModelAndView("/wz/setting/appUpgradeSetting");

		mv.addObject("auSetting", settingService.getAppUpgradeInfo(1l));

		return mv;
	}

	@RequestMapping("/wz/setting/updateAppUpgradeSetting.do")
	@ResponseBody
	public ResultJsonBean updateAppUpgradeSetting(HttpServletRequest request,
			@RequestParam MultipartFile appFile,
			@ModelAttribute("auSetting") TbWzAppUpgrade setting,
			SessionStatus sessionStatus) {

		ResultJsonBean rjb = new ResultJsonBean();
		rjb.setSuccess(true);

		try {
			if (!appFile.isEmpty()) {
				String appFileName = "yingjia.apk";
				String appPath = uploadRoot + appFileName;
				FileUtils.copyInputStreamToFile(appFile.getInputStream(),
						new File(appPath));
				setting.setApkUrl(appFileName);
			}

			setting.setPublishDate(new Date());
			settingService.modifyAppUpgradeInfo(setting);

			log.error(">>>>> Update App Upgrade Setting Success!!");
			sessionStatus.setComplete();
		} catch (Exception e) {
			log.error(">>>>> Update App Upgrade Setting Error: ", e);
			rjb.setSuccess(false);
			e.printStackTrace();
		}

		return rjb;
	}

}
