/**
 * 
 */
package com.os.boss.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.os.boss.manager.AdvertManager;

import common.codrim.common.ChannelEnquiryBean;
import common.codrim.common.ResultJsonBean;
import common.codrim.common.SelectJsonResult;
import common.codrim.pojo.TbAdvert;
import common.codrim.service.AdvertChannelEnquiryService;
import common.codrim.service.AdvertService;

/**
 * @author Administrator
 *
 */
@Controller
public class AdvertController {
@Autowired
AdvertManager advertManaager;
@Autowired
AdvertChannelEnquiryService channelEnquiryService;
@Autowired
AdvertService advertService;

@RequestMapping("/getAdvertsAndPutChannelNumByProductId.do")
@ResponseBody
public Map getAdvertsAndPutChannelNumByProductId(HttpServletRequest request){
	return advertManaager.getAdvertsAndPutChannelNumByProductId(request);
}
@RequestMapping("/addPutChannel.do")
@ResponseBody
public ResultJsonBean addPutChannel(HttpServletRequest request){
	ResultJsonBean bean=new ResultJsonBean();
	return advertManaager.addPutChannel(request,bean);
}

@RequestMapping("/putChannelList.do")
@ResponseBody
public Map putChannelList(HttpServletRequest request){
	return advertManaager.putChannelList(request);
}
/**
 * 下拉框
 * @param request
 * @return
 */
@RequestMapping("/getAdvertNamesByProductId.do")
@ResponseBody
public List<SelectJsonResult> getAdvertNameByProductId(HttpServletRequest request){
	List<SelectJsonResult> bean=new ArrayList<SelectJsonResult>();
	long productId=Long.parseLong(request.getParameter("productId"));
	List<TbAdvert> list=advertService.getAdvertsByproductId(productId);
if(list!=null&&list.size()>0){
	for(TbAdvert advert:list){
		SelectJsonResult result=new SelectJsonResult();
		result.setId(advert.getId());
		result.setText(advertManaager.convertAdvertName(advert.getAdvertName()));
		bean.add(result);
	}
}
	return bean;
}


/**列表
 * @param request
 * @return
 */
@RequestMapping("/getAdvertsByProductId.do")
@ResponseBody
public Map getAdvertsByProductId(HttpServletRequest request){
	return advertManaager.getAdvertsByProductId(request);
}
@RequestMapping("/getAdvertById.do")
@ResponseBody
public ResultJsonBean getAdvertById(HttpServletRequest request){
	ResultJsonBean bean=new ResultJsonBean();
	return advertManaager.getAdvertById(request,bean);
}
@RequestMapping("/addAdvert.do")
@ResponseBody
public ResultJsonBean addAdvert(HttpServletRequest request){
	ResultJsonBean bean=new ResultJsonBean();
	return advertManaager.addOrEditAdvert(request,bean);
}
@RequestMapping("/editAdvert.do")
@ResponseBody
public ResultJsonBean editAdvert(HttpServletRequest request){
	ResultJsonBean bean=new ResultJsonBean();
	return advertManaager.addOrEditAdvert(request,bean);
}

@RequestMapping("/addChannelEnquiry.do")
@ResponseBody
public ResultJsonBean addChannelEnquiry(HttpServletRequest request){
	ResultJsonBean bean=new ResultJsonBean();
	return advertManaager.addChannelEnquiry(request,bean);
}

@RequestMapping("/confimChannelEnquiry.do")
@ResponseBody
public ResultJsonBean confimChannelEnquiry(HttpServletRequest request){
	ResultJsonBean bean=new ResultJsonBean();
	return advertManaager.confimChannelEnquiry(request,bean);
}


@RequestMapping("/addOperateEvaluate.do")
@ResponseBody
public ResultJsonBean addOperateEvaluate(HttpServletRequest request){
	ResultJsonBean bean=new ResultJsonBean();
	return advertManaager.addOperateEvaluate(request,bean);
}

@RequestMapping("/addFinalEvaluate.do")
@ResponseBody
public ResultJsonBean addFinalEvaluate(HttpServletRequest request){
	ResultJsonBean bean=new ResultJsonBean();
	return advertManaager.addFinalEvaluate(request,bean);
}
@RequestMapping("/getChannelEnquirysByAdvertId.do")
@ResponseBody
public Map getChannelEnquirysByAdvertId(HttpServletRequest request){
Map map=new HashedMap();
long advertId=0;
try {
	advertId=Long.parseLong(request.getParameter("advertId"));
} catch (Exception e) {
	// TODO: handle exception
	return null;
}
List<ChannelEnquiryBean> lists=new ArrayList<ChannelEnquiryBean>();
lists=channelEnquiryService.getChannelEnquirysByAdvertId(advertId);
if(lists!=null&&lists.size()>0){
	map.put("total", lists.size());
	map.put("rows", lists);
}else{
	map.put("total", 0);
	map.put("rows", lists);
	return map;
}
return map;
}


	
}
