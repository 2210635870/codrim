/**
 * 
 */
package com.os.boss.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import common.codrim.common.Contans;
import common.codrim.common.SelectResultByCodition;
import common.codrim.common.ViewProductToCountBean;
import common.codrim.pojo.TbAdmin;
import common.codrim.pojo.TbProduct;
import common.codrim.pojo.TbProductResource;
import common.codrim.service.AdvertPutChannelService;
import common.codrim.service.AdvertService;
import common.codrim.service.ChannelService;
import common.codrim.service.ProductResourceService;
import common.codrim.service.ProductService;
import common.codrim.util.DateUtil;
import common.codrim.util.JsonUtil;
import common.codrim.util.MemcacheUtil;
import common.codrim.util.StringUtil;
@Repository
public class ProductManager {
	@Autowired
	MemcacheUtil memcacheUtil;
	@Autowired
	JsonUtil jsonUtil;
	@Autowired
	ProductService productService;
	@Autowired
	AdvertService advertService;
	@Autowired
	AdvertPutChannelService putChannelService;
	@Autowired
	ChannelService channelService;
	@Autowired
	 ProductResourceService productRes;
	public Map getTbProduct(HttpServletRequest request){
		long id=0;
		try {
			id= Long.parseLong(request.getParameter("id"));
		} catch (Exception e) {
			// TODO: handle exception
			id=0;
		}
		 if(id<=0){
			 return null;
		 }
		 Map map=new HashedMap();
		TbProduct product=productService.geTbProduct(id);
		if(product==null){
			return null;
		}
		map.put("product", product);
		List<TbProductResource> productResources=productRes.geTbProductResources(id);
		map.put("res", productResources);
		return map;
	}
	
	
	public List<ViewProductToCountBean> getProductWithCustomerNameToCount(HttpServletRequest request) {
		String customerName=request.getParameter("customerName");
		String productName=request.getParameter("productName");
		SelectResultByCodition codition=new SelectResultByCodition();
		codition.setCustomerName(customerName);
		codition.setProductName(productName);
		List<ViewProductToCountBean> countBeans=productService.selectViewProductToCount(codition);
		if(countBeans!=null&&countBeans.size()>0){
			for(ViewProductToCountBean bean:countBeans){
				codition.setType(Contans.EVALUATE_STATUS_CHANNEL_ENQUIRY);
				codition.setProductId(bean.getId());
				int waitEvaluateNum=advertService.selectCountAdvertsByProductIdAndStatus(codition);
				codition.setType(null);
				int countPutChannels =putChannelService.selectCountProductAdvertByProductIdAndStatus(codition);
				codition.setType(Contans.RUNNING_STATUS_RUNNING);
				int countRunningPutChannels=putChannelService.selectCountProductAdvertByProductIdAndStatus(codition);
				bean.setWaitEvaluateNum(waitEvaluateNum+"");
				bean.setCountPutChannels(countPutChannels+"");
				bean.setCountRunningPutChannels(countRunningPutChannels+"");
			}
		}else{
			return countBeans;
		}
		return countBeans;
	}
	

	
	
	
	
	public List<TbProductResource> convertRequestToProductResource(HttpServletRequest request,long id) {
		 List<TbProductResource> resList=null;
		 if(id<=0){
			 return null;
		 }else{
			 resList=new ArrayList<TbProductResource>();
		 }
		String path=request.getParameter("resPath");//前台拼接  path_type:path_type
		if(StringUtil.isBlank(path)){
			return null;
		}
		String[] paths=path.split(":");
		for(String str:paths){
			TbProductResource resource=new TbProductResource();
			resource.setProductId(id);
			String[] singlePath=str.split("_");
			resource.setSourceUrl(singlePath[0]);
			resource.setSourceType(getSourceType(singlePath[1]));
			resList.add(resource);
		}
		return resList;
	}
	public short getSourceType(String type){
		short resourceType=0;
		if(type.equals("icon")){
			resourceType=Contans.PRODUCT_RESOURCE_ICON;
		}else if(type.equals("screenshot")){
			resourceType=Contans.PRODUCT_RESOURCE_SCREENSHOT;
		}else if(type.equals("banner")){
			resourceType=Contans.PRODUCT_RESOURCE_BANNER;
		}else if(type.equals("tablescreen")){
			resourceType=Contans.PRODUCT_RESOURCE_TABLE_SCREEN;
		}else if(type.equals("fullscreen")){
			resourceType=Contans.PRODUCT_RESOURCE_FULL_SCREEN;
		}else{
			resourceType=0;
		}
		return resourceType;
		
	} 
	public TbProduct convertRequestToProduct(HttpServletRequest request) {
		long id=0;
		try {
			id=Long.parseLong(request.getParameter("id"));
		} catch (Exception e) {
			// TODO: handle exception
			id=0;
		}
		TbProduct product=null;
		String productName = request.getParameter("productName");
		String customerName = request.getParameter("customerName");
		String productIden = StringUtil.toMd5(customerName+productName);
		Short productType = Short.parseShort(request.getParameter("productType"));
		String createDescribe =request.getParameter("createDescribe").trim();
		TbAdmin admin=(TbAdmin) request.getSession().getAttribute("user");
				if(admin==null){
					return null;
				}
		String createName =admin.getAccount();
		Date createTime = DateUtil.getNowDateToDate(System.currentTimeMillis(), "yyyy-MM-dd hh:mm:ss");
		String androidPacketSize = request.getParameter("androidPacketSize");
		String androidPacketUrl = request.getParameter("androidPacketUrl");
		String iosPacketSize = request.getParameter("iosPacketSize");
		String iosPacketUrl = request.getParameter("iosPacketUrl");
		if(id==0){
			product=new TbProduct(productName, productIden, customerName, productType, createDescribe, createName, createTime, androidPacketSize, androidPacketUrl, iosPacketSize, iosPacketUrl);
		}else{
			product= new TbProduct(id, productName, productIden, customerName, productType, createDescribe, createName, createTime, androidPacketSize, androidPacketUrl, iosPacketSize, iosPacketUrl);
		}
		return product;
		
	}

	

	
	
}
