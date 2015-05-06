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
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import common.codrim.common.ResultJsonBean;
import common.codrim.pojo.TbWzMsgPushSetting;
import common.codrim.pojo.TbWzNews;
import common.codrim.pojo.TbWzWallpaper;
import common.codrim.service.WzMsgPushSettingService;
import common.codrim.service.WzNewsService;
import common.codrim.service.WzWallpaperService;
import common.codrim.util.StringUtil;
import common.codrim.wz.converter.NewsScreenlockConverter;
import common.codrim.wz.dto.NewsScreenlockForm;

@Controller
public class ScreenLockController extends BaseController {
	private static final Logger log = Logger.getLogger("yj");
	
	private static NewsScreenlockConverter newsConverter = new NewsScreenlockConverter();
	
	@Autowired
	private WzNewsService newsService;
	@Autowired
	private WzWallpaperService wallpaperService;
	@Autowired
	private WzMsgPushSettingService msgPushSettingService;
	
	@RequestMapping("/screenlock/getNewsList.do")
	@ResponseBody
	public Map<String, Object> getNewsList(HttpServletRequest request) {
		log.info(">>>>> load WzNews list");
		int start = Integer.parseInt(request.getParameter("page"));
		int size = Integer.parseInt(request.getParameter("rows"));
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", start);
		params.put("size", size);
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<TbWzNews> list = newsService.selectList(params);
			int total = newsService.getTotalNum(params);
			map.put("total", total);
			map.put("rows", list);
		} catch (Exception e) {
			log.error(">>>>>>  error: ", e);
		}
		
		return map;
	}
	
	@RequestMapping("/screenlock/addNews.do")
	@ResponseBody
	public ResultJsonBean addNews(@ModelAttribute("news") NewsScreenlockForm newsForm, SessionStatus sessionStatusn) {
		log.info(">>>>> add News");
		ResultJsonBean rjb = new ResultJsonBean();
		
		MultipartFile newsScreenLockFile = newsForm.getNewsScreenlockFile();
		try {
			String newsScreenlockFileName = StringUtil.getUniqueFilename(newsScreenLockFile.getOriginalFilename());
			
			FileUtils.copyInputStreamToFile(newsScreenLockFile.getInputStream(), new File(imgUploadRoot + newsScreenlockFileName));
			
			newsForm.setNewsScreenlock(imgRoot + newsScreenlockFileName);
			
			TbWzNews news = newsConverter.toPO(newsForm);
			news.setAddDate(new Date());
			newsService.insert(news);
			
			sessionStatusn.setComplete();
			rjb.setSuccess(true);
		} catch (Exception e) {
			log.error(">>>>> Add News Error: ", e);
			rjb.setSuccess(false);
			e.printStackTrace();
		}
		
		return rjb;
	}
	
	@RequestMapping("/screenlock/editNews.do")
	@ResponseBody
	public ResultJsonBean editNews(@ModelAttribute("news") NewsScreenlockForm newsForm, SessionStatus sessionStatusn) {
		ResultJsonBean rjb = new ResultJsonBean();
		
		MultipartFile newsScreenLockFile = newsForm.getNewsScreenlockFile();
		try {
			
			if( !newsScreenLockFile.isEmpty() ) {
				
				String newsScreenlockFileName = StringUtil.getUniqueFilename(newsScreenLockFile.getOriginalFilename());
				FileUtils.copyInputStreamToFile(newsScreenLockFile.getInputStream(), new File(imgUploadRoot + newsScreenlockFileName));
				newsForm.setNewsScreenlock(imgRoot + newsScreenlockFileName);
			}
			
			TbWzNews news = newsConverter.toPO(newsForm);
			newsService.updateByPrimaryKey(news);
			
			sessionStatusn.setComplete();
			rjb.setSuccess(true);
		} catch (Exception e) {
			log.error(">>>>> Add News Error: ", e);
			rjb.setSuccess(false);
			e.printStackTrace();
		}
		
		return rjb;
	}
	
	@RequestMapping("/screenlock/deleteNews.do")
	@ResponseBody
	public ResultJsonBean deleteNews(@RequestParam("id")long id, SessionStatus sessionStatusn) {
		ResultJsonBean rjb = new ResultJsonBean();
		
		try {
			newsService.deleteByPrimaryKey(id);
			rjb.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(">>>>>  error:", e);
			rjb.setSuccess(false);
		}
		
		return rjb;
	}
	
	@RequestMapping("/screenlock/getWallpaperList.do")
	@ResponseBody
	public Map<String, Object> getWallpaperList(HttpServletRequest request) {
		log.info(">>>>> load Wallpaper list");
		int start = Integer.parseInt(request.getParameter("page"));
		int size = Integer.parseInt(request.getParameter("rows"));
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", start);
		params.put("size", size);
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<TbWzWallpaper> list = wallpaperService.selectList(params);
			int total = wallpaperService.getTotalNum(params);
			map.put("total", total);
			map.put("rows", list);
		} catch (Exception e) {
			log.error(">>>>>>  error: ", e);
		}
		
		return map;
	}
	
	@RequestMapping("/screenlock/addWallpaper.do")
	@ResponseBody
	public ResultJsonBean addWallpaper(@RequestParam("wallpaperFile")MultipartFile wallpaperFile, SessionStatus sessionStatusn) {
		log.info(">>>>> add Wallpaper");
		ResultJsonBean rjb = new ResultJsonBean();
		
		try {
			String wallpaperFileName = StringUtil.getUniqueFilename(wallpaperFile.getOriginalFilename());
			
			FileUtils.copyInputStreamToFile(wallpaperFile.getInputStream(), new File(imgUploadRoot + wallpaperFileName));
			
			TbWzWallpaper toAdd = new TbWzWallpaper();
			toAdd.setWallpaper(imgRoot + wallpaperFileName);
			toAdd.setAddDate(new Date());
			wallpaperService.insert(toAdd);
			
			sessionStatusn.setComplete();
			rjb.setSuccess(true);
		} catch (Exception e) {
			log.error(">>>>> Add News Error: ", e);
			rjb.setSuccess(false);
			e.printStackTrace();
		}
		
		return rjb;
	}
	
	@RequestMapping("/screenlock/editWallpaper.do")
	@ResponseBody
	public ResultJsonBean editWallpaper(@RequestParam("id")long id, @RequestParam("wallpaperFile")MultipartFile wallpaperFile, SessionStatus sessionStatusn) {
		log.info(">>>>> edit Wallpaper");
		ResultJsonBean rjb = new ResultJsonBean();
		
		TbWzWallpaper wallpaper = new TbWzWallpaper();
		wallpaper.setId(id);
		try {
			if( !wallpaperFile.isEmpty() ) {
				String wallpaperFileName = StringUtil.getUniqueFilename(wallpaperFile.getOriginalFilename());
				FileUtils.copyInputStreamToFile(wallpaperFile.getInputStream(), new File(imgUploadRoot + wallpaperFileName));
				wallpaper.setWallpaper(imgRoot + wallpaperFileName);
				
				wallpaperService.updateByPrimaryKey(wallpaper);
			}
			sessionStatusn.setComplete();
			rjb.setSuccess(true);
		} catch (Exception e) {
			log.error(">>>>> Update Wallpaper Error: ", e);
			rjb.setSuccess(false);
			e.printStackTrace();
		}
		
		return rjb;
	}
	
	@RequestMapping("/screenlock/deleteWallpaper.do")
	@ResponseBody
	public ResultJsonBean deleteWallpaper(@RequestParam("id")long id, SessionStatus sessionStatusn) {
		ResultJsonBean rjb = new ResultJsonBean();
		
		try {
			wallpaperService.deleteByPrimaryKey(id);
			rjb.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(">>>>>  error:", e);
			rjb.setSuccess(false);
		}
		
		return rjb;
	}
	
	@RequestMapping("/wz/task/initMsgPush.do")
	public ModelAndView initAdd() {
		ModelAndView mv = new ModelAndView("/wz/screenlock/msg_push_setting");

		List<TbWzMsgPushSetting> settingList = msgPushSettingService.selectList(new HashMap<String, Object>());
		
		Map<String, Object> msgPushSetting = newsConverter.msgPushSettingListToMap(settingList);
		System.out.println(msgPushSetting);
		mv.addObject("msgPushSetting", msgPushSetting);

		return mv;
	}
	
	@RequestMapping("/screenlock/editMsgPushSetting.do")
	@ResponseBody
	public ResultJsonBean editMsgPushSetting(@RequestParam("settingJson")String settingJson, SessionStatus sessionStatusn) {
		log.info(">>>>> edit Msg push setting");
		ResultJsonBean rjb = new ResultJsonBean();
		try {
			List<TbWzMsgPushSetting> list = new ArrayList<TbWzMsgPushSetting>();
			ObjectMapper mapper = new ObjectMapper();
			
			list = mapper.readValue(settingJson, 
				    new TypeReference<ArrayList<TbWzMsgPushSetting>>() {});
			
			msgPushSettingService.updateValueByName(list);
			
			sessionStatusn.setComplete();
			rjb.setSuccess(true);
		} catch (Exception e) {
			log.error(">>>>> edit Msg push setting Error: ", e);
			rjb.setSuccess(false);
			e.printStackTrace();
		}
		
		return rjb;
	}
}
