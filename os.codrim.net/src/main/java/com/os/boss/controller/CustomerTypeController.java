package com.os.boss.controller;

import java.util.Date;
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

import common.codrim.common.ResultJsonBean;
import common.codrim.common.SelectJsonResult;
import common.codrim.pojo.TbCustomerType;
import common.codrim.service.CustomerService;
import common.codrim.util.StringUtil;

@Controller
public class CustomerTypeController {
	
	@Autowired
	private CustomerService customerService;
	
	private static final Logger log = Logger.getLogger("BOSS");
	
	@RequestMapping("/getAllCustomerType.do")
	@ResponseBody
	public List<SelectJsonResult> getAllChannelTypeName(HttpServletRequest request){
		return customerService.getAllCustomerType();
	}
	
	@RequestMapping("/customerTypeList.do")
	@ResponseBody
	public Map<String, Object> customerTypeList(HttpServletRequest request) throws DataAccessException{
		int startPage = Integer.parseInt(request.getParameter("page"));
		int size = Integer.parseInt(request.getParameter("rows"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<TbCustomerType> list = customerService.page(startPage, size);
			int total = customerService.count();
			
			map.put("total", total);
			map.put("rows", list);
			
		} catch (Exception e) {
			log.error(">>>>> Customer Type List Error: ", e);
			e.printStackTrace();
		}
		
		return map;
	}
	
	@RequestMapping("/saveCustomerType.do")
	@ResponseBody
	public ResultJsonBean saveCustomerType(HttpServletRequest request, 
			@RequestParam String id) throws DataAccessException{
		ResultJsonBean rjb = new ResultJsonBean();
		rjb.setSuccess(true);
		
		String customerType = request.getParameter("customerType");
		String remark = request.getParameter("remark");
		
		try {
			TbCustomerType ct = new TbCustomerType();
			ct.setCustomerType(customerType);
			ct.setRemark(remark);
			
			if (StringUtil.isEmpty(id)) {
				ct.setCreateDate(new Date());
				customerService.addCustomerType(ct);
			} else {
				ct.setId(Long.valueOf(id));
				customerService.modifyCustomerType(ct);
			}
			
		} catch(Exception e) {
			rjb.setSuccess(false);
			log.error("Save Customer Type Failed!", e);
		}
		
		return rjb;
	}

	@RequestMapping("/deleteCustomerType.do")
	@ResponseBody
	public ResultJsonBean deleteCustomerType(HttpServletRequest request, 
			@RequestParam long id) throws DataAccessException{
		ResultJsonBean rjb = new ResultJsonBean();
		rjb.setSuccess(true);
		
		try {
			customerService.deleteCustomerType(id);
			
		} catch(Exception e) {
			rjb.setSuccess(false);
			log.error("Delete Customer Type Failed!", e);
		}
		
		return rjb;
	}
}
