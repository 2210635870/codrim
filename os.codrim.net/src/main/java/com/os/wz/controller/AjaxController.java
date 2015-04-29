package com.os.wz.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.codrim.common.SelectJsonResult;
import common.codrim.service.AdminService;
import common.codrim.service.ChannelService;
import common.codrim.service.CustomerService;
import common.codrim.service.WzTaskService;
import common.codrim.util.StringUtil;
import common.codrim.wz.constant.DataConstant;
import common.codrim.wz.sql.result.KeyValResult;

/**
 * 处理Ajax请求
 * @author MaLiang
 */
@Controller
public class AjaxController {
	
	private static final Logger log = Logger.getLogger("yj");

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private WzTaskService taskService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ChannelService channelService;
	

	/**
	 * 获取所有客户
	 * @param request
	 * @return List<SelectJsonResult>
	 */
	@RequestMapping("/loadCustomers.do")
	@ResponseBody
	public List<KeyValResult> loadCustomers(HttpServletRequest request) throws DataAccessException{
		List<SelectJsonResult> list = customerService.getAllCustomerName();
		List<KeyValResult> results = new ArrayList<KeyValResult>();
		for (SelectJsonResult l : list) {
			results.add(new KeyValResult(StringUtil.toString(l.getId()), l.getText()));
		}
		return results;
	}
	
	/**
	 * 获取所有产品名
	 * @param request
	 * @return List<SelectJsonResult>
	 */
	@RequestMapping("/loadTaskNames.do")
	@ResponseBody
	public List<KeyValResult> loadTaskNames(HttpServletRequest request) throws DataAccessException{
		List<KeyValResult> taskNames = taskService.getAllTaskSelectOptions();
		taskNames.add(0, new KeyValResult("0", "全部"));
		return taskNames;
	}
	
	/**
	 * 获取所有跟进人
	 * @param request
	 * @return List<SelectJsonResult>
	 */
	@RequestMapping("/loadFollowers.do")
	@ResponseBody
	public List<KeyValResult> loadFollowers(HttpServletRequest request) throws DataAccessException{
		
		List<SelectJsonResult> uns = adminService.getAllTbUserName();
		List<KeyValResult> kvs = new ArrayList<KeyValResult>();
		for (SelectJsonResult un : uns) {
			KeyValResult kv = new KeyValResult(String.valueOf(un.getId()), un.getText());
			kvs.add(kv);
		}
		kvs.add(0, new KeyValResult("0", "全部"));
		
		return kvs;
	}
	
	/**
	 * 获取所有渠道
	 * @param request
	 * @return List<SelectJsonResult>
	 */
	@RequestMapping("/loadChannels.do")
	@ResponseBody
	public List<KeyValResult> loadChannels(HttpServletRequest request) throws DataAccessException{
		
		List<SelectJsonResult> uns = channelService.getAllChannelName();
		List<KeyValResult> kvs = new ArrayList<KeyValResult>();
		for (SelectJsonResult un : uns) {
			KeyValResult kv = new KeyValResult(String.valueOf(un.getId()), un.getText());
			kvs.add(kv);
		}
		
		if ("1".equals(request.getParameter("all")))
			kvs.add(0, new KeyValResult("0", "全部"));
		
		return kvs;
	}

	/**
	 * 检查产品名字是否重复
	 * @param request
	 * @return List<SelectJsonResult>
	 */
	@RequestMapping("/checkTaskName.do")
	@ResponseBody
	public String checkTaskName(HttpServletRequest request) throws DataAccessException{
		String taskName = request.getParameter("taskName");
		if (taskService.isTaskNameExist(taskName)) {
			return String.valueOf(DataConstant.TRUE);
		} else {
			return String.valueOf(DataConstant.FALSE);
		}
	}
}
