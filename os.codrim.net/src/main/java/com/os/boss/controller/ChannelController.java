package com.os.boss.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.codrim.common.ResultJsonBean;
import common.codrim.common.SelectJsonResult;
import common.codrim.pojo.TbChannel;
import common.codrim.pojo.TbAdmin;
import common.codrim.service.ChannelService;
import common.codrim.service.AdminService;
import common.codrim.util.StringUtil;

@Controller
public class ChannelController {
	@Autowired
	private ChannelService channelService;
	@Autowired
	private AdminService userService;

	private static final Logger log = Logger.getLogger("BOSS");

	/**
	 * 获取所有渠道的名字
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getChannelName.do")
	@ResponseBody
	public List<SelectJsonResult> getChannelName(HttpServletRequest request)
			throws DataAccessException {
		log.info("----------------------------------获取渠道所有名字");
		return channelService.getAllChannelName();
	}

	/**
	 * 获取所有渠道联系人的名字
	 * 
	 * @author xulin
	 * @date 2014年12月25日
	 * @param request
	 * @return
	 * @throws DataAccessException
	 *             List<SelectJsonResult>
	 */
	@RequestMapping("/getAllChannelContactName.do")
	@ResponseBody
	public List<SelectJsonResult> getAllChannel(HttpServletRequest request)
			throws DataAccessException {
		log.info("----------------------------------获取渠道所有联系人");
		return channelService.getAllChannel();
	}

	/**
	 * 根据渠道联系人获取渠道名字
	 * 
	 * @author xulin
	 * @date 2014年12月25日
	 * @param request
	 * @return
	 * @throws DataAccessException
	 *             List<SelectJsonResult>
	 */
	@RequestMapping("getChannelNameByContactName.do")
	@ResponseBody
	public List<SelectJsonResult> getChannelNameByContactName(
			HttpServletRequest request) throws DataAccessException {
		log.info("----------------------------------获取渠道所有联系人");
		return channelService.getChannelNameByContactName(request
				.getParameter("contactName"));
	}

	/**
	 * 校验渠道名字是否输入过
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/validChannelName.do")
	@ResponseBody
	public boolean checkChannelName(HttpServletRequest request)
			throws DataAccessException {
		String name = request.getParameter("companyName");
		log.info("----------------------------------校验渠道名字：：" + name);
		/*TbChannel channel = channelService.checkName(name);
		if (channel == null) {
			return false;
		} else {
			return true;
		}*/
		return channelService.isValidCompanyName(name);
	}

	/**
	 * 渠道名字获取联系人
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getPersonInCharge.do")
	@ResponseBody
	public Map getPersonInChargeByChannelName(HttpServletRequest request)
			throws DataAccessException {
		String name = request.getParameter("channelName");
		log.info("----------------------------------渠道名字获取联系人：：" + name);
		TbChannel channel = channelService.checkName(name);
		Map map = new HashMap();
		if (channel == null) {
			map.put("success", false);
		} else {
			map.put("success", true);
			map.put("personInCharge", channel.getPersonInCharge());
		}
		return map;
	}

	/**
	 * 分页获取渠道所有信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getChannels.do")
	@ResponseBody
	public Map<String, Object> geTbChannels(HttpServletRequest request)
			throws DataAccessException {
		List<TbChannel> list = new ArrayList<TbChannel>();
		int startPage;
		try {
			startPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			startPage = 1;
			log.error("----------------------------------传入分页参数错误startPage"
					+ startPage + ":::" + e);
		}
		int size;
		try {
			size = Integer.parseInt(request.getParameter("rows"));
		} catch (NumberFormatException e) {
			size = 10;
			log.error("----------------------------------传入分页参数错误size" + size
					+ ":::" + e);

		}
		list = channelService.getChannelsByPage(startPage, size);
		int total = channelService.getTotalChannels();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		log.info("----------------------------------分页获取所有渠道信息");
		return map;
	}

	/**
	 * 添加渠道
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveChan.do")
	@ResponseBody
	public ResultJsonBean addChannel(HttpServletRequest request,
			TbChannel channel) throws DataAccessException {
		ResultJsonBean jsonBean = new ResultJsonBean();
		String channelNum = StringUtil.toMd5(channel.getChannelName()
				+ "codrim" + System.currentTimeMillis());
		channel.setChannelNumber(channelNum);
		TbAdmin admin = (TbAdmin) request.getSession().getAttribute("user");
		channel.setPersonInCharge(admin.getAccount());
		int i = channelService.addChannel(channel);
		if (i > 0) {
			TbAdmin user = new TbAdmin(channel.getChannelName(),
					channel.getChannelPassword(), channel.getChannelEmail(),
					"渠道", 1);
			userService.addTbUser(user);
			jsonBean.setSuccess(true);
		}
		log.info("----------------------------------添加渠道信息");
		return jsonBean;
	}

	/**
	 * 修改渠道信息
	 * 
	 * @author xxxxxx
	 * @date 2014年12月1日
	 * @param request
	 * @return ResultJsonBean
	 */
	@RequestMapping("/editChan.do")
	@ResponseBody
	public ResultJsonBean modifyChannel(HttpServletRequest request,TbChannel channel)
			throws DataAccessException {
		ResultJsonBean jsonBean = new ResultJsonBean();
		int i = channelService.modifyChannel(channel);
		if (i > 0) {
		TbAdmin user = userService.login(channel.getChannelEmail(), channel.getChannelPassword());
		if (user == null) {
				user = new TbAdmin(channel.getChannelName(), channel.getChannelPassword(), channel.getChannelEmail(),"渠道", 1);
		userService.addTbUser(user);
		}
			jsonBean.setSuccess(true);
		}
		log.info("----------------------------------修改渠道信息");
		return jsonBean;
	}

	/**
	 * 删除渠道信息
	 * 
	 * @author xxxxxx
	 * @date 2014年12月1日
	 * @param request
	 * @return ResultJsonBean
	 */
	@RequestMapping("/deleteChan.do")
	@ResponseBody
	public ResultJsonBean deleteChannel(HttpServletRequest request)
			throws DataAccessException {
		ResultJsonBean jsonBean = new ResultJsonBean();
		long id;
		try {
			id = Long.parseLong(request.getParameter("id"));
		} catch (NumberFormatException e) {
			// TODO: handle exception
			return jsonBean;
		}
		int i = channelService.deleteById(id);
		if (i > 0) {
			jsonBean.setSuccess(true);
		}
		log.info("----------------------------------删除渠道信息");

		return jsonBean;
	}

}
