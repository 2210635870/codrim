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
import common.codrim.pojo.TbCustomer;
import common.codrim.service.CustomerService;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	private static final Logger log = Logger.getLogger("BOSS");

	/**
	 * 获取所有客户名字
	 * 
	 * @author xulin
	 * @date 2014年12月1日
	 * @param request
	 * @return List<SelectJsonResult>
	 */
	@RequestMapping("/getCustomerName.do")
	@ResponseBody
	public List<SelectJsonResult> getCustomerName(HttpServletRequest request) throws DataAccessException{
		log.info("----------------------------------获取管理员所有名字");
		
		List<SelectJsonResult> nameList = customerService.getAllCustomerName();
		
		if (nameList != null) {
			SelectJsonResult json=new SelectJsonResult();
			json.setId(0);
 			json.setText("全部");
			nameList.add(json);
		}
		
		return nameList;
	}

	@RequestMapping("/validCompanyName.do")
	@ResponseBody
	public boolean checkCustomerName(HttpServletRequest request) throws DataAccessException{
		String name = request.getParameter("companyName");
		return customerService.checkName(name);
	}

	/**
	 * 获取所有客户信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getCustomers.do")
	@ResponseBody
	public Map<String, Object> geTbCustomers(HttpServletRequest request) throws DataAccessException{
		List<TbCustomer> list = new ArrayList<TbCustomer>();
		int startPage = 0;
		try {
			startPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			log.error("----------------------------------传入分页参数错误startPage"
					+ startPage + ":::" + e);
			startPage = 1;
		}
		int size = 0;
		try {
			size = Integer.parseInt(request.getParameter("rows"));
		} catch (NumberFormatException e) {
			log.error("----------------------------------传入分页参数错误size" + size
					+ ":::" + e);
			size = 10;
		}
		list = customerService.getCustomersByPage(startPage, size);
		int total = customerService.getTotalCustomers();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		log.info("----------------------------------获取所有客户信息");
		return map;
	}

	/**
	 * 添加客户
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveCus.do")
	@ResponseBody
	public ResultJsonBean addCustomer(HttpServletRequest request) throws DataAccessException{
		ResultJsonBean jsonBean = new ResultJsonBean();
		String customerName = request.getParameter("customerName");
		String contactName = request.getParameter("contactName");
		String contactPhone = request.getParameter("contactPhone");
		String companyPhone = request.getParameter("companyPhone");
		String website = request.getParameter("website");
		String remark = request.getParameter("remark");
		String contactPosition = request.getParameter("contactPosition");
		String contactEmail = request.getParameter("contactEmail");
		String contactWx = request.getParameter("contactWx");
		String companyCity = request.getParameter("companyCity");
		String companyAddress = request.getParameter("companyAddress");
		String companyName = request.getParameter("companyName");
		String companyCountry = request.getParameter("companyCountry");
		String customerType = request.getParameter("customerType");
		
		TbCustomer customer = new TbCustomer();
		customer.setCustomerName(customerName);
		customer.setContactName(contactName);
		customer.setContactPhone(contactPhone);
		customer.setCompanyPhone(companyPhone);
		customer.setWebsite(website);
		customer.setRemark(remark);
		customer.setContactPosition(contactPosition);
		customer.setContactEmail(contactEmail);
		customer.setContactWx(contactWx);
		customer.setCompanyCity(companyCity);
		customer.setCompanyAddress(companyAddress);
		customer.setCompanyName(companyName);
		customer.setCompanyCountry(companyCountry);
		TbAdmin user = (TbAdmin) request.getSession().getAttribute("user");
		customer.setPersonInCharge(user.getAccount());
		customer.setCustomerType(customerType);
		
		int i = customerService.addCustomer(customer);
		if (i > 0) {
			jsonBean.setSuccess(true);
		}
		log.info("----------------------------------添加客户信息");
		return jsonBean;
	}

	/**
	 * 修改客户
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/editCus.do")
	@ResponseBody
	public ResultJsonBean modifyCustomer(HttpServletRequest request) throws DataAccessException{
		ResultJsonBean jsonBean = new ResultJsonBean();
		long id;
		try {
			id = Long.parseLong(request.getParameter("id"));
		} catch (NumberFormatException e) {
			// TODO: handle exception
			return jsonBean;
		}
		String customerName = request.getParameter("customerName");
		String contactName = request.getParameter("contactName");
		String contactPhone = request.getParameter("contactPhone");
		String companyPhone = request.getParameter("companyPhone");
		String website = request.getParameter("website");
		String remark = request.getParameter("remark");
		String contactPosition = request.getParameter("contactPosition");
		String contactEmail = request.getParameter("contactEmail");
		String contactWx = request.getParameter("contactWx");
		String companyCity = request.getParameter("companyCity");
		String companyAddress = request.getParameter("companyAddress");
		String companyName = request.getParameter("companyName");
		String companyCountry = request.getParameter("companyCountry");
		String customerType = request.getParameter("customerType");
		
		TbCustomer customer = new TbCustomer();
		customer.setId(id);
		customer.setCustomerName(customerName);
		customer.setContactName(contactName);
		customer.setContactPhone(contactPhone);
		customer.setCompanyPhone(companyPhone);
		customer.setWebsite(website);
		customer.setRemark(remark);
		customer.setContactPosition(contactPosition);
		customer.setContactEmail(contactEmail);
		customer.setContactWx(contactWx);
		customer.setCompanyCity(companyCity);
		customer.setCompanyAddress(companyAddress);
		customer.setCompanyName(companyName);
		customer.setCompanyCountry(companyCountry);
		TbAdmin user = (TbAdmin) request.getSession().getAttribute("user");
		customer.setPersonInCharge(user.getAccount());
		customer.setCustomerType(customerType);
		
		int i = customerService.modifyCustomer(customer);
		if (i > 0) {
			jsonBean.setSuccess(true);
		}
		log.info("----------------------------------修改客户信息");
		return jsonBean;
	}

	/**
	 * 删除客户
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteCus.do")
	@ResponseBody
	public ResultJsonBean deleteCustomer(HttpServletRequest request) throws DataAccessException{
		ResultJsonBean jsonBean = new ResultJsonBean();
		long id;
		try {
			id = Long.parseLong(request.getParameter("id"));
		} catch (NumberFormatException e) {
			// TODO: handle exception
			return jsonBean;
		}
		int i = customerService.deleteById(id);
		if (i > 0) {
			jsonBean.setSuccess(true);
		}
		log.info("----------------------------------删除客户信息");
		return jsonBean;
	}

}
