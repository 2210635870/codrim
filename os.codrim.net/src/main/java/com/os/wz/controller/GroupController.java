package com.os.wz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import common.codrim.common.ResultJsonBean;
import common.codrim.pojo.TbWzGroup;
import common.codrim.pojo.TbWzGroupInviteCode;
import common.codrim.pojo.TbWzUser;
import common.codrim.service.WzGroupService;
import common.codrim.util.StringUtil;
import common.codrim.wz.constant.DataConstant;
import common.codrim.wz.sql.result.GroupApplicationInfo;
import common.codrim.wz.sql.result.GroupInfo;
import common.codrim.wz.sql.result.TaskReviewInfo;

@Controller
@SessionAttributes()
public class GroupController extends BaseController {
	
	private static final Logger log = Logger.getLogger("yj");
	
	@Autowired
	private WzGroupService groupService;
	
	
	@RequestMapping("/wz/group/groupList.do")
	@ResponseBody
	public Map<String, Object> groupList(HttpServletRequest request) throws DataAccessException{
		int startPage = Integer.parseInt(request.getParameter("page"));
		int size = Integer.parseInt(request.getParameter("rows"));
		
		String groupId = request.getParameter("groupId");
		String groupName = request.getParameter("groupName");
		
		log.info(">>>>> Group List [startPage=" + startPage + ", size=" + size + "]");
		log.info(">>>>> Group List [groupId=" + groupId + ", groupName=" + groupName + "]");

		TbWzGroup param = new TbWzGroup();
		
		if (!StringUtil.isEmpty(groupId)) {
			param.setGroupId(Long.valueOf(groupId));
		}
		if (!StringUtil.isEmpty(groupName)) {
			param.setGroupName(groupName);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<GroupInfo> list = groupService.searchGroup(startPage, size, param);
			int total = groupService.getGroupTotalAmount(param);
			
			map.put("total", total);
			map.put("rows", list);
			
		} catch (Exception e) {
			log.error(">>>>> Group List Error: ", e);
			e.printStackTrace();
		}
		
		return map;
	}
	
	@RequestMapping("/wz/group/view.do")
	public ModelAndView view(@RequestParam long groupId) {
		ModelAndView mv = new ModelAndView("/wz/group/viewGroup");
			
		mv.addObject("group", groupService.getGroupDetail(groupId));
		
		return mv;
	}
	
	@RequestMapping("/wz/group/inviteCodeList.do")
	@ResponseBody
	public Map<String, Object> codeList(HttpServletRequest request) throws DataAccessException{
		int startPage = Integer.parseInt(request.getParameter("page"));
		int size = Integer.parseInt(request.getParameter("rows"));
		
		String status = request.getParameter("status");
		String code = request.getParameter("code");
		
		log.info(">>>>> Group Invite Code List [startPage=" + startPage + ", size=" + size + "]");
		log.info(">>>>> Group Invite Code List [status=" + status + ", code=" + code + "]");

		TbWzGroupInviteCode param = new TbWzGroupInviteCode();
		if (!StringUtil.isEmpty(status) && !"0".equals(status)) {
			param.setStatus(Integer.valueOf(status));
		}
		if (!StringUtil.isEmpty(code)) {
			param.setCode(Long.valueOf(code));
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<TbWzGroupInviteCode> list = groupService.searchInviteCode(startPage, size, param);
			int total = groupService.getInviteCodeTotalAmount(param);
			
			map.put("total", total);
			map.put("rows", list);
			
		} catch (Exception e) {
			log.error(">>>>> Group Invite Code List Error: ", e);
			e.printStackTrace();
		}
		
		return map;
	}
	
	@RequestMapping("/wz/group/saveCode.do")
	@ResponseBody
	public ResultJsonBean saveCode(HttpServletRequest request) throws DataAccessException{
		ResultJsonBean rjb = new ResultJsonBean();
		rjb.setSuccess(true);
		
		String code = request.getParameter("code");
		String contact = request.getParameter("contact");
		String qq = request.getParameter("qq");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		try {
			TbWzGroupInviteCode inviteCode = new TbWzGroupInviteCode();
			if (!StringUtil.isEmpty(code))
				inviteCode.setCode(Long.valueOf(code));
			inviteCode.setContact(contact);
			inviteCode.setQq(qq);
			inviteCode.setPhone(phone);
			inviteCode.setEmail(email);
			inviteCode.setStatus(DataConstant.GROUP_INVITE_CODE_STATUS_UNUSE);
			groupService.addCode(inviteCode);
			
		} catch(Exception e) {
			rjb.setSuccess(false);
			log.error("Generate Group Invite Code Failed!", e);
		}
		
		return rjb;
	}
	
	
	@RequestMapping("/wz/group/groupApplicationList.do")
	@ResponseBody
	public Map<String, Object> groupApplicationList(HttpServletRequest request) throws DataAccessException{
		int startPage = Integer.parseInt(request.getParameter("page"));
		int size = Integer.parseInt(request.getParameter("rows"));
		
		String groupId = request.getParameter("groupId");
		
		log.info(">>>>> Group Application List [startPage=" + startPage + ", size=" + size + "]");

		GroupApplicationInfo param = new GroupApplicationInfo();
		param.setGroupId(Long.valueOf(groupId));
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<GroupApplicationInfo> list = groupService.searchGroupApplication(startPage, size, param);
			int total = groupService.getGroupApplicationTotalAmount(param);
			
			map.put("total", total);
			map.put("rows", list);
			
		} catch (Exception e) {
			log.error(">>>>> Group Application List Error: ", e);
			e.printStackTrace();
		}
		
		return map;
	}

}
