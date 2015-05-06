package com.codrim.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codrim.constant.ErrorCode;
import com.codrim.util.ParametersDebugUtils;

import common.codrim.pojo.TbWzCommonSetting;
import common.codrim.pojo.TbWzGroup;
import common.codrim.pojo.TbWzMsgPushSetting;
import common.codrim.pojo.TbWzPointsLog;
import common.codrim.pojo.TbWzUser;
import common.codrim.service.WzGroupService;
import common.codrim.service.WzMsgPushSettingService;
import common.codrim.service.WzNewsService;
import common.codrim.service.WzTaskService;
import common.codrim.service.WzUserService;
import common.codrim.util.StringUtil;
import common.codrim.wz.constant.DataConstant;
import common.codrim.wz.sql.result.ScreenlockTask;

@Controller
public class ScreenlockController extends BaseController {
	private static final Logger logger = Logger.getLogger("yj");
	
	@Autowired
	private WzUserService userService;
	@Autowired
	private WzNewsService newsService;
	@Autowired
	private WzTaskService taskService;
	@Autowired
	private WzGroupService groupService;
	@Autowired
	private WzMsgPushSettingService msgPushSettingService;
	
	@RequestMapping("/getScreenlockTaskList.do")
	@ResponseBody
	public Map<String, Object> getScreenlockTaskList(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("size", DataConstant.TASK_LIST_SIZE);
		
		String userId = request.getParameter("userId");
		if( StringUtil.isEmpty(userId) ) {
			result.put("errCode", ErrorCode.UN100);
			result.put("errMsg", "equired fields is empty.");
			return result;
		}
		response.setCharacterEncoding("utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST,GET" );
		response.addHeader("Access-Control-Allow-Credentials", "true" );
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+userId);
		if(!sign_flag){
			result.put("errCode", ErrorCode.UN400);
			result.put("errMsg", "sign check failed!");
			return result;
		}
		Long userIdVal = Long.parseLong(userId);
		params.put("userId", userIdVal);
		TbWzGroup group = groupService.getGroupByUser(userIdVal);
		
		Map<String, Object> msgParams = new HashMap<String, Object>();
		msgParams.put("order", "id asc" );
		List<TbWzMsgPushSetting> msgPushList = msgPushSettingService.selectList(msgParams);
		
		ParametersDebugUtils.debugParameters(request, "Task List", "userId");
		List<ScreenlockTask> list = new ArrayList<ScreenlockTask>();
		
		TbWzCommonSetting setting = settingService.getCommonSetting();
		
		List<Map<String, Object>> taskList = taskService.selectScreenlockTasks(params);
		try {
			for( Map<String, Object> task : taskList ) {
				ScreenlockTask st = new ScreenlockTask();
				st.setPicUrl(task.get("pic_url") == null ? "": task.get("pic_url").toString());
				st.setTaskId((long)task.get("task_id"));
				st.setTaskName(task.get("app_icon") == null ? "": task.get("app_icon").toString());
				st.setType((int)task.get("nextStepType"));
				st.setWeight(task.get("weight") == null ? 1 : (int)task.get("weight"));
				
				long totalStepCount = (long)task.get("totalStepCount");
				long remainStepCount = (long)task.get("remainStepCount");
				
				if (totalStepCount == remainStepCount) { //全新任务
					if (group== null) { // 普通用户
						st.setPrice(StringUtil.toString(getUserTaskPrice(setting, (double)task.get("price"))));
						
					} else { // 团队用户
						st.setPrice(StringUtil.toString(getGroupTaskPrice(setting, (double)task.get("price"), group.getLeaderPercent())));
					}
					
				} else { // 部分完成
					if (group == null) { // 普通用户
						st.setPrice(getUserTaskStepPrice(setting, (double)task.get("price"), (int)task.get("nextStepRewardPercent"), DataConstant.GOLD));
					} else { // 团队用户
						st.setPrice(getGroupTaskStepPrice(setting, (double)task.get("price"), (int)task.get("nextStepRewardPercent"),group.getLeaderPercent(), DataConstant.GOLD));
					}
				}
				
				list.add(st);
			}
			
			TbWzMsgPushSetting msgPushSetting = msgPushList.get(2);
			result.put("msgPush", msgPushSetting);
			
			if( taskList.size() < DataConstant.TASK_LIST_SIZE ) {
				params.put("size", Integer.parseInt(msgPushSetting.getValue()));
				List<Map<String, Object>> newsList = newsService.selectForScreenlock(params);
				for( Map<String, Object> news : newsList ) {
					ScreenlockTask st = new ScreenlockTask();
					st.setPicUrl(news.get("pic_url") == null ? "": news.get("pic_url").toString());
					st.setTaskId((long)news.get("task_id"));
					st.setTaskName(news.get("task_name").toString());
					st.setType(DataConstant.TASK_INFO_TYPE);
					st.setWeight( news.get("weight")==null ? 1:(long)news.get("weight"));
					st.setPrice(news.get("price").toString());
					st.setTitle(news.get("title")==null ? "":news.get("title").toString());
					list.add(st);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("get screent task list error", e);
		}
		
		result.put("taskList", list);
		
		Map<String, Object> logParams = new HashMap<String, Object>();
		params.put("userId", userIdVal);
		params.put("pointsType", DataConstant.INCOME_POINTS_TYPE_SCREENLOCK_SIGNIN);
		List<TbWzPointsLog> logPOList = pointsLogService.selectList(logParams); 
		TbWzPointsLog logPO = logPOList.size() > 0 ? logPOList.get(0) : null;
		
		TbWzMsgPushSetting signSetting = msgPushList.get(0);
		TbWzMsgPushSetting newsSetting = msgPushList.get(1);
		//锁屏签到间隔满半小时才有金币
		if(logPO != null) {
			long diff = (new Date().getTime() - logPO.getIncomeDate().getTime()) / 1000;
			if(diff < 1800) {
				signSetting.setValue("0");
			}
		}
		
		signSetting.setValue(toBigDecimal(signSetting.getValue())
				.multiply(toBigDecimal(setting.getExchangeRate()))
				.stripTrailingZeros().toPlainString());
		
		newsSetting.setValue(toBigDecimal(newsSetting.getValue())
		.multiply(toBigDecimal(setting.getExchangeRate()))
		.stripTrailingZeros().toPlainString());
		
		result.put("signin", signSetting);
		result.put("news", newsSetting);
		return result;
	}
	
	@RequestMapping("/screenlockSignin.do")
	@ResponseBody
	public Map<String, Object> screenlockSignin(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		String userId = request.getParameter("userId");
		logger.info(">>>>>screenlockSignin  user id: " + userId);
		response.setCharacterEncoding("utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST,GET" );
		response.addHeader("Access-Control-Allow-Credentials", "true" );
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+userId);
		if(!sign_flag){
			result.put("code", ErrorCode.UN400);
			result.put("errMsg", "sign check failed!");
			return result;
		}
		
		Long userKey = Long.parseLong(userId);
		
		TbWzUser user = userService.getUserById(userKey);
		
		if( user == null ) {
			result.put("code", ErrorCode.UN401);
			result.put("errMsg", "userid is error.");
			return result;
		}else if( user.getIsDisable() == 1) {
			result.put("code", ErrorCode.UN402);
			result.put("errMsg", "user is disabled.");
			return result;
		}
		
		TbWzCommonSetting setting = settingService.getCommonSetting();
		TbWzMsgPushSetting msgSetting = msgPushSettingService.selectByPrimaryKey(1L);
		
		long signinAdd = Long.parseLong(toBigDecimal(msgSetting.getValue())
				.multiply(toBigDecimal(setting.getExchangeRate()))
				.stripTrailingZeros().toPlainString());
		
		Date now = new Date();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userKey);
		params.put("pointsType", DataConstant.INCOME_POINTS_TYPE_SCREENLOCK_SIGNIN);
		List<TbWzPointsLog> logPOList = pointsLogService.selectList(params); 
		TbWzPointsLog logPO = logPOList.size() > 0 ? logPOList.get(0) : null;
		
		if(logPO != null) {
			long diff = (now.getTime() - logPO.getIncomeDate().getTime()) / 1000;
			if(diff < 1800) {
				result.put("code", "2");
				return result;
			}
		}
		
		long balance = user.getBalance()+signinAdd;
		user.setBalance(balance);
		userService.modifyUser(user);
		
		TbWzPointsLog log = new TbWzPointsLog(userKey, DataConstant.INCOME_POINTS_TYPE_SCREENLOCK_SIGNIN, null, signinAdd, now);
		pointsLogService.savePintsLog(log);
		result.put("code", "1");
		
		return result;
	}
}
