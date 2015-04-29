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
import common.codrim.pojo.TbChannelType;
import common.codrim.pojo.TbOffice;
import common.codrim.service.OfficeService;
import common.codrim.util.DateUtil;

@Controller
public class OfficeController {
	@Autowired
	private OfficeService officeService;
	private static final Logger log = Logger.getLogger("BOSS");
	/**
	 * 获得所有的职位类型名
	 * @author searh
	 * @date 2012/12/24
	 * @parame
	 * @return  List<SelectJsonResult>
	 * */
	@RequestMapping("/getAllOfficeName.do")
	@ResponseBody
	public List<SelectJsonResult> getAllOfficeName(HttpServletRequest request){
		System.out.println("...");
		log.info("----------------------------------获取职位名称");
		return officeService.getAllOffecName();
	}
	
	
	
	/**
	 * 获取所有渠道类型信息
	 * @date 2014年12月25日
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping("/getAllOffice.do")
	@ResponseBody
	public Map<String, Object> geAllOffice(HttpServletRequest request) throws DataAccessException{
		List<TbOffice> list = new ArrayList<TbOffice>();
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
		list = officeService.getOfficeByPages(startPage, size);
		int total = officeService.getTotalTbOffice();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		log.info("----------------------------------获取所有职位信息");
		return map;
	}

	/**
	 * 添加渠道类型
	 * @date 2012/12/24
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveOffice.do")
	@ResponseBody
	public ResultJsonBean addOffice(HttpServletRequest request) throws DataAccessException{
		ResultJsonBean jsonBean = new ResultJsonBean();
		String officeName = request.getParameter("officeName");
		String remark =request.getParameter("remark");
		TbOffice office=new TbOffice();
		office.setOfficeName(officeName);
		office.setRemark(remark);
		office.setDate(DateUtil.getNowDateToDate(System.currentTimeMillis(), "yyyy-MM-dd"));
		int i = officeService.addTbOffice(office);
		if (i > 0) {
			jsonBean.setSuccess(true);
		}
		log.info("----------------------------------添加职位信息");
		return jsonBean;
	}

	/**
	 * 修改渠道类型
	 * @date 2012/12/24
	 * @param request
	 * @return
	 */
	@RequestMapping("/editOffice.do")
	@ResponseBody
	public ResultJsonBean modifyOffice(HttpServletRequest request) throws DataAccessException{
		ResultJsonBean jsonBean = new ResultJsonBean();
		int id;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			
			return jsonBean;
		}
		String officeName = request.getParameter("officeName");
		String remark =request.getParameter("remark");
		TbOffice office=new TbOffice();
		office.setId(id);
		office.setOfficeName(officeName);
		office.setRemark(remark);
		office.setDate(DateUtil.getNowDateToDate(System.currentTimeMillis(), "yyyy-MM-dd"));
		int i = officeService.modifyTbOffice(office);
		if (i > 0) {
			jsonBean.setSuccess(true);
		}
		log.info("----------------------------------修改职位信息");
		return jsonBean;
	}

	/**
	 * 删除渠道类型
	 * @date 2012/12/24
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteOffice.do")
	@ResponseBody
	public ResultJsonBean deleteOffice(HttpServletRequest request) throws DataAccessException{
		ResultJsonBean jsonBean = new ResultJsonBean();
		int id;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			// TODO: handle exception
			return jsonBean;
		}
		int i = officeService.deleteTbOffice(id);
		if (i > 0) {
			jsonBean.setSuccess(true);
		}
		log.info("----------------------------------删除职位信息");
		return jsonBean;
	}
}
