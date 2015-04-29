package com.codrim.controller;

import java.util.ArrayList;
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
import com.codrim.util.ParametersDebugUtils;
import common.codrim.pojo.TbWzCommonSetting;
import common.codrim.pojo.TbWzGroup;
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
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("size", DataConstant.TASK_LIST_SIZE);
		TbWzGroup group = null;
		String userId = request.getParameter("userId");
		if( !StringUtil.isEmpty(userId) ) {
			Long userIdVal = Long.parseLong(userId);
			params.put("userId", userIdVal);
			group = groupService.getGroupByUser(userIdVal);
		}
		
		ParametersDebugUtils.debugParameters(request, "Task List", "userId");
		List<ScreenlockTask> list = new ArrayList<ScreenlockTask>();
		
		TbWzCommonSetting setting = settingService.getCommonSetting();
		
		List<Map<String, Object>> taskList = taskService.selectScreenlockTasks(params);
		try {
			for( Map<String, Object> task : taskList ) {
				ScreenlockTask st = new ScreenlockTask();
				st.setPicUrl(task.get("pic_url") == null ? "": task.get("pic_url").toString());
				st.setTaskId((long)task.get("task_id"));
				st.setTaskName(task.get("task_name").toString());
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
			
			if( taskList.size() < DataConstant.TASK_LIST_SIZE ) {
				params.put("size", DataConstant.TASK_LIST_SIZE-taskList.size());
				List<Map<String, Object>> newsList = newsService.selectForScreenlock(params);
				for( Map<String, Object> news : newsList ) {
					ScreenlockTask st = new ScreenlockTask();
					st.setPicUrl(news.get("pic_url") == null ? "": news.get("pic_url").toString());
					st.setTaskId((long)news.get("task_id"));
					st.setTaskName(news.get("task_name").toString());
					st.setType(DataConstant.TASK_INFO_TYPE);
					st.setWeight( news.get("weight")==null ? 1:(long)news.get("weight"));
					st.setPrice(news.get("price").toString());
					list.add(st);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("get screent task list error", e);
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("taskList", list);
		result.put("signin", msgPushSettingService.selectByPrimaryKey(1L));
		return result;
	}
}
