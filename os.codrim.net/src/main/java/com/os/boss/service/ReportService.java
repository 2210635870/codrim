/**
 * 
 */
package com.os.boss.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.codrim.common.Contans;
import common.codrim.common.SelectResultByCodition;
import common.codrim.common.TotalReportBean;
import common.codrim.pojo.TbProductDetail;
import common.codrim.service.ProductDetailService;
import common.codrim.service.RankingsReportService;
import common.codrim.util.DateUtil;
import common.codrim.util.NumberFormatUtil;

/**
 * @author Administrator
 *
 */
@Service
public class ReportService {
	@Autowired
	RankingsReportService rankingsReportService;
	@Autowired
	ProductDetailService productDetailService;
	
	private static final Logger log = Logger.getLogger("BOSS");
	public TotalReportBean getData(HttpServletRequest request){
		Date startTime=DateUtil.convertStringToDate(request.getParameter("startTime"), "yyyy-MM-dd");
		Date endTime=DateUtil.convertStringToDate(request.getParameter("endTime"), "yyyy-MM-dd");
		String customerName=request.getParameter("customerName");
		String productName=request.getParameter("productName");
		String channelName=request.getParameter("channelName");
		TotalReportBean bean=new TotalReportBean();
        SelectResultByCodition reCodition=new SelectResultByCodition();                       
        reCodition.setStartTime(startTime);
        reCodition.setEndTime(endTime);
        boolean flag=false;
		if("全部".equals(customerName)){//客户名选择全部则统计所有数据
			log.info("----------------------客户名选择全部则统计所有数据");
		}else{//客户名选择不是全部
			reCodition.setCustomerName(customerName);
			if("全部".equals(productName)){//产品为全部，具体统计某一客户所有产品数据
				log.info("---------------------产品为全部，具体统计某一客户所有产品数据");
			}else{//产品选择不是全部
				reCodition.setProductName(productName);
				if("全部".equals(channelName)){//渠道为全部，具体统计产品所有渠道数据
					log.info("---------------------渠道为全部，具体统计产品所有渠道数据");
				}else{//渠道选择不是全部，根据产品，渠道统计数据
					reCodition.setChannelName(channelName);
					log.info("---------------------渠道选择不是全部，根据产品，渠道统计数据");
					flag=true;
				}
			}
		}
		bean= rankingsReportService.selectSumData(reCodition);	
        bean=this.convertProductDetailsTotalReportBean(bean, reCodition,flag);
		return bean;
	}
	
	
	public TotalReportBean convertProductDetailsTotalReportBean(TotalReportBean bean,SelectResultByCodition reCodition,boolean flag){
		reCodition.setProductProperty(Contans.PRODUCT_PROPERTY_GENERAL);
		List<TbProductDetail> listWithProductPropertyGeneral=productDetailService.selectAllTbProductDetailByProductProperty(reCodition);
		reCodition.setProductProperty(Contans.PRODUCT_PROPERTY_INTERMODAL);
		List<TbProductDetail> listWithProductPropertyIntermodal=productDetailService.selectAllTbProductDetailByProductProperty(reCodition);
		//在投广告总数
		int generalAllRunning = 0;
		//待上线广告数量
		int generalAllPause = 0;
		//评估广告数量
		int generalAllAdvert;
		//下线广告数量
		int generalAllOffline=0;
		//广告收入
		int generalAllIncome = 0;
		//广告成本
		int generalAllCosts = 0;
		//广告毛利
		int generalAllGrossProfit = 0;
		//广告毛利率
		String generalAllGrossMargin="";
		 
			//在投广告总数
		int interAllRunning = 0;
			//待上线广告数量
		int interAllPause = 0;
			//评估广告数量
		int interAllAdvert;
			//下线广告数量
		int interAllOffline = 0;
			//广告收入
		int interAllIncome = 0;
			//广告成本
		int interAllCosts = 0;
			//广告毛利
		int interAllGrossProfit = 0;
			//广告毛利率
		String interAllGrossMargin="";
		 //普通
		if(listWithProductPropertyGeneral!=null&&listWithProductPropertyGeneral.size()>0){
		for(TbProductDetail tbProductDetail:listWithProductPropertyGeneral){
			if(tbProductDetail.getRunningStatus()==Contans.RUNNING_STATUS_RUNNING){
				generalAllRunning +=1;
			}else if(tbProductDetail.getRunningStatus()==Contans.RUNNING_STATUS_OFFLINE){
				generalAllOffline +=1;
			}else{
				generalAllPause +=1;
			}
			reCodition.setCustomerName(tbProductDetail.getCustomerName());
			reCodition.setChannelName(tbProductDetail.getChannelName());
			reCodition.setProductName(tbProductDetail.getProductName());
			if(flag){
				reCodition.setDeviceplate(tbProductDetail.getDeviceplate());
			}
			TotalReportBean singleReportBean=rankingsReportService.selectSumData(reCodition);
			if(singleReportBean !=null){
			generalAllIncome +=singleReportBean.getAllIncome();
			generalAllCosts  +=singleReportBean.getAllCosts();
			generalAllGrossProfit  +=singleReportBean.getAllGrossProfit();
			}
		}
		}
		bean.setGeneralAllCosts(generalAllCosts);
		bean.setGeneralAllGrossProfit(generalAllGrossProfit);
		bean.setGeneralAllIncome(generalAllIncome);
		bean.setGeneralAllOffline(generalAllOffline);
		bean.setGeneralAllPause(generalAllPause);
		bean.setGeneralAllRunning(generalAllRunning);
		bean.setGeneralAllGrossMargin(NumberFormatUtil.getDeci(generalAllGrossProfit, generalAllIncome));
		//联运
		if(listWithProductPropertyIntermodal!=null&&listWithProductPropertyIntermodal.size()>0){
		for(TbProductDetail tbProductDetail:listWithProductPropertyIntermodal){
			if(tbProductDetail.getRunningStatus()==Contans.RUNNING_STATUS_RUNNING){
				interAllRunning +=1;
			}else if(tbProductDetail.getRunningStatus()==Contans.RUNNING_STATUS_OFFLINE){
				interAllOffline +=1;
			}else{
				interAllPause +=1;
			}
			reCodition.setCustomerName(tbProductDetail.getCustomerName());
			reCodition.setChannelName(tbProductDetail.getChannelName());
			reCodition.setProductName(tbProductDetail.getProductName());
			if(flag){
				reCodition.setDeviceplate(tbProductDetail.getDeviceplate());
			}
			TotalReportBean singleReportBean=rankingsReportService.selectSumData(reCodition);
			if(singleReportBean !=null){
			interAllIncome +=singleReportBean.getAllIncome();
			interAllCosts  +=singleReportBean.getAllCosts();
			interAllGrossProfit  +=singleReportBean.getAllGrossProfit();
			}
		}
		}
		bean.setInterAllCosts(interAllCosts);
		bean.setInterAllGrossProfit(interAllGrossProfit);
		bean.setInterAllIncome(interAllIncome);
		bean.setInterAllOffline(interAllOffline);
		bean.setInterAllPause(interAllPause);
		bean.setInterAllRunning(interAllRunning);
		bean.setInterAllGrossMargin(NumberFormatUtil.getDeci(interAllGrossProfit, interAllIncome));
		return bean;
	}
	
}
