package com.os.boss.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.os.boss.manager.ProductManager;

import common.codrim.common.ResultJsonBean;
import common.codrim.common.SelectJsonResult;
import common.codrim.common.SelectResultByCodition;
import common.codrim.common.ViewProductToCountBean;
import common.codrim.pojo.TbProduct;
import common.codrim.pojo.TbProductResource;
import common.codrim.service.ProductResourceService;
import common.codrim.service.ProductService;
import common.codrim.util.DateUtil;
import common.codrim.util.StringUtil;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductManager productManager;
	@Autowired
	private ProductResourceService productRes;
	private static final Logger log = Logger.getLogger("BOSS");




	/**
	 *查看广告系列所有信息
	 * 
	 * @author xulin
	 * @date 2014年12月1日
	 * @param request
	 * @return ResultJsonBean
	 */
	@RequestMapping("/countProduct.do")
	@ResponseBody
	public Map<String, Object> countProduct(HttpServletRequest request) {
		List<ViewProductToCountBean> beans=new ArrayList<ViewProductToCountBean>();
		beans=productManager.getProductWithCustomerNameToCount(request);
		Map<String, Object> map = new HashMap<String, Object>();
		if(beans!=null&&beans.size()>0){
		map.put("total", beans.size());
		map.put("rows", beans);
		map.put("success",true);
		}else{
			map.put("total", 0);
			map.put("rows", beans);
			map.put("success",false);
		}
		return map;
	}
	
	
	
	
	/**
	 * 根据相对应的客户名称查找相对应的产品名称
	 * @author prh
	 * @date 2014/12/5
	 * @param request
	 * @return  selectProduct
	 * */
	@RequestMapping("/getProductNameByCustomerName.do")
	@ResponseBody
	public List<SelectJsonResult> getProductNameByCustomerName(HttpServletRequest request) throws DataAccessException{
		String customerName=request.getParameter("customerName");
		return productService.getProductNameByCustomerName(customerName);
	}

	 
	   /**
			 * 添加评估
			 * @author searh
			 * @date 2014/12/28
			 * @param request
			 * @return  ResultJsonBean
			 */
		 @RequestMapping("/saveProduct.do")
		 @ResponseBody
		 public ResultJsonBean saveProduct(HttpServletRequest request){
			 ResultJsonBean jsonBean = new ResultJsonBean();
			TbProduct product=productManager.convertRequestToProduct(request);
			if(product==null){
				return jsonBean;
			}
			int i =productService.save(product);
				if (i > 0) {
					List<TbProductResource> resources=productManager.convertRequestToProductResource(request,product.getId());
					if(resources!=null){
					productRes.insertByBatch(resources);
					}
					jsonBean.setSuccess(true);
				}
				log.info("----------------------------------添加产品");
				return jsonBean;
		 }
		 
		   /**
				 * 获取广告系列信息
				 * @author searh
				 * @date 2014/12/28
				 * @param request
				 * @return  ResultJsonBean
				 */
			 @RequestMapping("/getProductData.do")
			 @ResponseBody
			 public Map getProductData(HttpServletRequest request){
					log.info("----------------------------------检索广告系列信息");
					return productManager.getTbProduct(request);
			 }
			 
			 
		 
			   /**
						 * 更新广告系列信息
						 * @author searh
						 * @date 2014/12/28
						 * @param request
						 * @return  ResultJsonBean
						 */
					 @RequestMapping("/editProduct.do")
					 @ResponseBody
					 public ResultJsonBean editProduct(HttpServletRequest request){
						 ResultJsonBean jsonBean = new ResultJsonBean();
							TbProduct product=productManager.convertRequestToProduct(request);
							if(product==null){
								return jsonBean;
							}
							if(product.getId()>0){
								int i =productService.update(product);
								if (i > 0) {
									List<TbProductResource> resources=productManager.convertRequestToProductResource(request,product.getId());
									if(resources!=null){
									productRes.insertByBatch(resources);
									}
									jsonBean.setSuccess(true);
								}
							}
								log.info("----------------------------------更新产品");
								return jsonBean;
					 }
		 
		
}
