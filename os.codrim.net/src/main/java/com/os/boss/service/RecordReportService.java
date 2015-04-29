/**
 * 
 */
package com.os.boss.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.os.boss.bean.RecordDataBean;

import common.codrim.common.SelectResultByCodition;
import common.codrim.pojo.TbProductDetail;
import common.codrim.pojo.TbRankingsReport;
import common.codrim.service.ProductDetailService;
import common.codrim.service.RankingsReportService;
import common.codrim.util.DateUtil;
import common.codrim.util.NumberFormatUtil;
import common.codrim.util.StringUtil;

/**
 * @author Administrator
 *
 */
@Service
public class RecordReportService {
	@Autowired
	ProductDetailService productDetailService;
	@Autowired
	RankingsReportService rankingsReportService;
	
	public TbRankingsReport getTbRankingsReport(HttpServletRequest request){
		long id = 0;
		try {
			id = Long.parseLong(request.getParameter("id"));
		} catch (NumberFormatException e) {
			// TODO: handle exception
			id=0;
		}
		if(id==0){
			return null;
		}
		TbRankingsReport rankingsReport=new TbRankingsReport();
		rankingsReport.setId(id);
		String accessPrice=request.getParameter("accessPrice");
		String putPrice=request.getParameter("putPrice");
		String effectNum=request.getParameter("effectNum");
		String clickNum=request.getParameter("clickNum");
		rankingsReport.setEffectNum(Long.parseLong(effectNum));
		rankingsReport.setClickNum(Long.parseLong(clickNum));
		String issubtract=request.getParameter("issubtract");
		if(!StringUtil.isBlank(issubtract)){
			rankingsReport.setIssubtract(Short.parseShort(issubtract));
			}
		double income = 0;
		double costs = 0;
		String conversinRate = "0.0%";
		double grossProfit = 0;
		String grossMargin = "0.0%";
		try {
			income = Double.parseDouble(accessPrice)
					* Double.parseDouble(effectNum);
		} catch (Exception e) {
			// TODO: handle exception
			income = 0;

		}
		try {
			costs = Double.parseDouble(putPrice)
					* Double.parseDouble(effectNum);
		} catch (Exception e) {
			// TODO: handle exception
			costs = 0;

		}
		try {
			conversinRate = NumberFormatUtil.getDeci(Double.parseDouble(effectNum),Double.parseDouble(clickNum));
		} catch (Exception e) {
			// TODO: handle exception
			conversinRate = "0.0%";

		}
		try {
			grossProfit = (Double.parseDouble(accessPrice) - Double.parseDouble(putPrice))
					* Double.parseDouble(effectNum);
		} catch (Exception e) {
			// TODO: handle exception
			grossProfit = 0;

		}
		try {
			grossMargin = NumberFormatUtil.getDeci(grossProfit, income);
		} catch (Exception e) {
			// TODO: handle exception
			grossMargin = "0.0%";

		}
		// 收入
		rankingsReport.setIncome(NumberFormatUtil.doubleFormat(income, "#.00"));
		// 成本
		rankingsReport.setCosts(NumberFormatUtil.doubleFormat(costs, "#.00"));
		// 转化率
		rankingsReport.setConversinRate(conversinRate);
		// 毛利
		rankingsReport.setGrossProfit(NumberFormatUtil.doubleFormat(grossProfit, "#.00"));
		// 毛利率
		rankingsReport.setGrossMargin(grossMargin);
		return rankingsReport;
		
		
		
	}
	
	
public TbProductDetail getTbProductDetail(HttpServletRequest request){
	RecordDataBean recordDataBean=null;
	String productName=request.getParameter("productName");
	String channelName=request.getParameter("channelName");
	String deviceplate=request.getParameter("deviceplate");
	SelectResultByCodition codition=new SelectResultByCodition();
	codition.setProductName(productName);
	codition.setChannelName(channelName);
	codition.setDeviceplate(deviceplate);
	TbProductDetail productDetail=productDetailService.getProductDetailByCodition(codition);
//    TbRankingsReport rankingsReport=rankingsReportService.checkRankingsReportByHour(codition);
//    if(productDetail!=null&&rankingsReport!=null){
//    	recordDataBean=new RecordDataBean();
//    recordDataBean.setId(rankingsReport.getId());
//   recordDataBean.setAccessPrice(productDetail.getAccessPrice());
//   recordDataBean.setPutPrice(productDetail.getPutPrice());
//   recordDataBean.setProductIsBack(productDetail.getProductIsback());
//	recordDataBean.setChannelIsBack(productDetail.getChannelIsback());
//	recordDataBean.setEffectNum(rankingsReport.getEffectNum());
//	recordDataBean.setClickNum(rankingsReport.getClickNum());
//	recordDataBean.setEffectNumWithDeduplication(rankingsReport.getEffectNumWithDeduplication());
//	recordDataBean.setDeduplicationNum(rankingsReport.getDeduplicationNum());
//	recordDataBean.setDeduplicationRate(rankingsReport.getDeduplicationRate());
//	recordDataBean.setSubtractNum(rankingsReport.getSubtractNum());
//	recordDataBean.setChannelExternalNum(rankingsReport.getChannelExternalNum());
//	recordDataBean.setIssubtract(rankingsReport.getIssubtract());
//	}
	return productDetail;
} 




}
