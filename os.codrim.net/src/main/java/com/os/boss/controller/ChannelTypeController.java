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
import common.codrim.pojo.TbAdmin;
import common.codrim.pojo.TbChannelType;
import common.codrim.service.ChannelTypeService;
import common.codrim.util.DateUtil;

@Controller
public class ChannelTypeController {
	
	@Autowired
	private ChannelTypeService channelTypeService;
	private static final Logger log = Logger.getLogger("BOSS");
	/**
	 * 获得所有的渠道类型名
	 * @author searh
	 * @date 2012/12/24
	 * @parame
	 * @return  List<SelectJsonResult>
	 * */
	@RequestMapping("/getAllChannelTypeName.do")
	@ResponseBody
	public List<SelectJsonResult> getAllChannelTypeName(HttpServletRequest request){
		System.out.println("...");
		log.info("----------------------------------获取渠道类型名称");
		return channelTypeService.getAllChannelTypeName();
	}
	
	
	
	/**
	 * 获取所有渠道类型信息
	 * 
	 * @author xulin
	 * @date 2014年12月1日
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping("/getAllChannelType.do")
	@ResponseBody
	public Map<String, Object> getAllChannelType(HttpServletRequest request) throws DataAccessException{
		List<TbChannelType> list = new ArrayList<TbChannelType>();
		int startPage = 0;
		try {
			startPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			startPage = 1;
			log.error("----------------------------------传入分页参数错误startPage"
					+ startPage + ":::" + e);
		}
		int size = 0;
		try {
			size = Integer.parseInt(request.getParameter("rows"));
		} catch (NumberFormatException e) {
			size = 10;
			log.error("----------------------------------传入分页参数错误size" + size
					+ ":::" + e);
		}
		list = channelTypeService.getChannelTypeByPages(startPage, size);
		int total = channelTypeService.getTotalChannelType();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		log.info("----------------------------------获取所有渠道类型信息");
		return map;
	}

	/**
	 * 添加渠道类型
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveChannelType.do")
	@ResponseBody
	public ResultJsonBean addChannelType(HttpServletRequest request) throws DataAccessException{
		ResultJsonBean jsonBean = new ResultJsonBean();
		String channelType = request.getParameter("channelType");
		String remark =request.getParameter("remark");
		TbChannelType channel = new TbChannelType();
		channel.setChannelType(channelType);
		channel.setRemark(remark);
		channel.setDate(DateUtil.getNowDateToDate(System.currentTimeMillis(), "yyyy-MM-dd"));
		int i = channelTypeService.addChannelType(channel);
		if (i > 0) {
			jsonBean.setSuccess(true);
		}
		log.info("----------------------------------添加渠道类型信息");
		return jsonBean;
	}

	/**
	 * 修改渠道类型
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/editChannelType.do")
	@ResponseBody
	public ResultJsonBean modifyTbChannelType(HttpServletRequest request) throws DataAccessException{
		ResultJsonBean jsonBean = new ResultJsonBean();
		int id;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			// TODO: handle exception
			return jsonBean;
		}
		String channelType = request.getParameter("channelType");
		String remark =request.getParameter("remark");
		TbChannelType channel = new TbChannelType(id, channelType, DateUtil.getNowDateToDate(System.currentTimeMillis(), "yyyy-MM-dd"), remark);
		int i = channelTypeService.modifyChannelType(channel);
		if (i > 0) {
			jsonBean.setSuccess(true);
		}
		log.info("----------------------------------修改渠道类型信息");
		return jsonBean;
	}

	/**
	 * 删除渠道类型
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteChannelType.do")
	@ResponseBody
	public ResultJsonBean deleteChannelType(HttpServletRequest request) throws DataAccessException{
		ResultJsonBean jsonBean = new ResultJsonBean();
		int id;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			// TODO: handle exception
			return jsonBean;
		}
		int i = channelTypeService.deleteChannelType(id);
		if (i > 0) {
			jsonBean.setSuccess(true);
		}
		log.info("----------------------------------删除渠道类型信息");
		return jsonBean;
	}
}
