/**
 * 
 */
package com.os.boss.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import common.codrim.common.Contans;
import common.codrim.common.ResultJsonBean;
import common.codrim.pojo.TbCodrimLinkParam;
import common.codrim.pojo.TbLinkParam;
import common.codrim.pojo.TbProduct;
import common.codrim.service.LinkParamService;
import common.codrim.service.ProductService;
import common.codrim.util.PropertiesUtil;

/**
 * @author Administrator
 *
 */
@Repository
public class ParamManager {
	@Autowired
	ProductService productService;
	@Autowired
	LinkParamService linkParamService;
	public ResultJsonBean saveCPParam(HttpServletRequest request){
		ResultJsonBean bean=new ResultJsonBean();
		long productId=0;
		short isBack=0;
		try {
			productId=Long.parseLong(request.getParameter("productId"));
			isBack=Short.parseShort(request.getParameter("isBack"));
		} catch (Exception e) {
			// TODO: handle exception
			return bean;
		}
		String paramValue=request.getParameter("value");
		TbProduct product=new TbProduct();
		product.setId(productId);
		product.setIsBack(isBack);
		int i=productService.update(product);
		if(i>0){
			if(isBack==1){
			List<TbLinkParam> linkParams=new ArrayList<TbLinkParam>();
				String[]  values=paramValue.split(",");
				for(String str:values){
					TbLinkParam linkParam=new TbLinkParam();
					linkParam.setProductId(productId);
					linkParam.setAddTime(new Date());
					linkParam.setCodrimParamId(Long.parseLong(str));
					linkParam.setLinkType(Contans.LINK_TYPE_CP);
				//	linkParam.setDefaultValueId(defaultValueId);
					linkParam.setIsChangeDefultValue(Contans.FALSE);
					linkParams.add(linkParam);
				}
				int res=linkParamService.insertByBatch(linkParams);
				if(res>0)
				bean.setSuccess(true);
			}
		}
		return bean;
	}
	/** 
	  *
	  * @author xulin
	  * @date 2015年3月3日
	  *  @param request
	  *  @return
	  *  ResultJsonBean
	  */ 
	public ResultJsonBean getCPParam(HttpServletRequest request) {
		// TODO Auto-generated method stub
		ResultJsonBean bean=new ResultJsonBean();
		long productId=0;
		try {
			productId=Long.parseLong(request.getParameter("productId"));
		} catch (Exception e) {
			// TODO: handle exception
			return bean;
		}
		List<TbCodrimLinkParam> lists=linkParamService.getLinkParamsByProductId(productId);
		StringBuffer buffer=new StringBuffer();
		StringBuffer bufferUrl=new StringBuffer();
		bufferUrl.append(PropertiesUtil.getValue("/os.properties", "CP_action_url"));
		if(lists!=null&&lists.size()>0){
			for(TbCodrimLinkParam linkParam:lists){
				buffer.append(linkParam.getParamZhName()+",");
				bufferUrl.append(linkParam.getParamEnName()+"={}&");
			}
			String str=buffer.toString().substring(0, buffer.toString().length()-1)+"_"+bufferUrl.toString();
			bean.setObject(str);
		}
		bean.setSuccess(true);
		return bean;
	}
	
	
	
	
	
	
	
	
}
