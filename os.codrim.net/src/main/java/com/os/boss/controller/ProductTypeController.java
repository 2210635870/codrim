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
import common.codrim.pojo.TbProductType;
import common.codrim.service.ProductTypeService;
import common.codrim.util.DateUtil;


@Controller
public class ProductTypeController {

	@Autowired
	private ProductTypeService productTypeService;
	private static final Logger log = Logger.getLogger("BOSS");
	
	
	/**
	 * 获取所有渠道类型信息
	 * @date 2014年12月25日
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping("/getAllProductType.do")
	@ResponseBody
	public Map<String, Object> getAllProductType(HttpServletRequest request) throws DataAccessException{
		List<TbProductType> list = new ArrayList<TbProductType>();
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
		list = productTypeService.getProductTypeByPages(startPage, size);
		int total = productTypeService.getTotalTbProductType();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		log.info("----------------------------------获取所有广告类型信息");
		return map;
	}

	/**
	 * 添加渠道类型
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveProductType.do")
	@ResponseBody
	public ResultJsonBean addProductType(HttpServletRequest request) throws DataAccessException{
		ResultJsonBean jsonBean = new ResultJsonBean();
		String productType = request.getParameter("productType");
		String remark =request.getParameter("remark");
		TbProductType product = new TbProductType();
		product.setProductType(productType);
		product.setRemark(remark);
		product.setDate(DateUtil.getNowDateToDate(System.currentTimeMillis(), "yyyy-MM-dd"));
		int i = productTypeService.addTbProductType(product);
		if (i > 0) {
			jsonBean.setSuccess(true);
		}
		log.info("----------------------------------添加广告类型信息");
		return jsonBean;
	}

	/**
	 * 修改渠道类型
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/editProductType.do")
	@ResponseBody
	public ResultJsonBean modifyTbProductType(HttpServletRequest request) throws DataAccessException{
		ResultJsonBean jsonBean = new ResultJsonBean();
		int id;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			// TODO: handle exception
			return jsonBean;
		}
		String productType = request.getParameter("productType");
		String remark =request.getParameter("remark");
		TbProductType product = new TbProductType();
		product.setId(id);
		product.setProductType(productType);
		product.setRemark(remark);
		int i = productTypeService.modifyTbProductType(product);
		if (i > 0) {
			jsonBean.setSuccess(true);
		}
		log.info("----------------------------------修改广告类型信息");
		return jsonBean;
	}

	/**
	 * 删除渠道类型
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteProductType.do")
	@ResponseBody
	public ResultJsonBean deleteProductType(HttpServletRequest request) throws DataAccessException{
		ResultJsonBean jsonBean = new ResultJsonBean();
		int id;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			// TODO: handle exception
			return jsonBean;
		}
		int i = productTypeService.deleteTbProductType(id);
		if (i > 0) {
			jsonBean.setSuccess(true);
		}
		log.info("----------------------------------删除广告类型信息");
		return jsonBean;
	}
	/**
	 * 获得所有的广告类型
	 * @author searh
	 * @param 
	 * @return List<TbProductType>
	 */
	@RequestMapping("/getAllProductTypes.do")
	@ResponseBody
	public List<SelectJsonResult> getAllProductTypes(HttpServletRequest request){
		
		return productTypeService.getAllProductTypes();
	}
	/**
	 * 
	 * @author searh
	 * @param 
	 * 
	 */
	@RequestMapping("/getProductTypeById.do")
	@ResponseBody
	public ResultJsonBean getProductTypeById(HttpServletRequest request){
		ResultJsonBean bean=new ResultJsonBean();
		int id=0;
		try {
			id=Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		TbProductType productType=productTypeService.getProductTypeById(id);
		
		if(productType!=null){
			bean.setSuccess(true);
			bean.setObject(productType);
		}
		return bean;
	}
}
