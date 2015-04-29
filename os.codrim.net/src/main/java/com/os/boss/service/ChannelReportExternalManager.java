package com.os.boss.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.os.boss.bean.ReportExternalBean;
import common.codrim.common.SelectJsonResult;
import common.codrim.common.SelectResultByCodition;
import common.codrim.pojo.TbProductDetail;
import common.codrim.pojo.TbRankingsReport;
import common.codrim.service.ChannelService;
import common.codrim.service.ProductDetailService;
import common.codrim.service.RankingsReportService;
import common.codrim.util.DateUtil;


@Service
@Transactional
public class ChannelReportExternalManager {
	@Autowired
	private ProductDetailService productService;
	@Autowired
	private ChannelService channelService;
	@Autowired
	private RankingsReportService rankingsReportService;
	private static final Logger log=Logger.getLogger("BOSS");
	public List<SelectJsonResult> getProductsByChannelName(String channelName)
			throws DataAccessException {
		// TODO Auto-generated method stub
		List<TbProductDetail> products=productService.getTbProductsByChannelName(channelName);
		List<SelectJsonResult> nameList=new ArrayList();
		int i=0;
		if(products!=null||products.size()>0){
			for(TbProductDetail pro:products){
				SelectJsonResult jsonResult=new SelectJsonResult();
				jsonResult.setText(pro.getProductName());
				jsonResult.setId(i);
				nameList.add(jsonResult);
				i++;
			}
		}
		return nameList;
	}

	public Map<String, Object>  getReportExternalBeans(HttpServletRequest request){
		int startPage;
		try {
			startPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			startPage = 1;
			log.error("----------------------------------传入分页参数错误startPage"
					+ startPage + ":::" + e);
		}
		int size;
		try {
			size = Integer.parseInt(request.getParameter("rows"));
		} catch (NumberFormatException e) {
			size = 10;
			log.error("----------------------------------传入分页参数错误size" + size
					+ ":::" + e);
		}
		String productName=request.getParameter("productName");
		String channelName=request.getParameter("channelName");
		String deviceplate=request.getParameter("deviceplate");
		TbProductDetail product=productService.getProductDetail(productName, channelName);
		String type="yyyy-MM-dd";
		Date startTime=DateUtil.convertStringToDate(request.getParameter("startTime"), type);
		Date endTime=DateUtil.convertStringToDate(request.getParameter("endTime"), type);
		SelectResultByCodition codition = new SelectResultByCodition();
		codition.setProductName(productName);
		codition.setChannelName(channelName);
		if("全部".equals(deviceplate)){
			codition.setDeviceplate(null);
			codition.setSize(size*2);
		}else{
			codition.setDeviceplate(deviceplate);
			codition.setSize(size);
		}
		codition.setStartPage((startPage-1)*size);
		codition.setStartTime(startTime);
		codition.setEndTime(endTime);
		 List<ReportExternalBean> externalBeans=new ArrayList<ReportExternalBean>();
			List<TbRankingsReport> reports=rankingsReportService.getRankingsReportsWithExternal(codition);
			if(reports!=null&&reports.size()>0){
				for(TbRankingsReport rankingsReport:reports){
					ReportExternalBean bean=new ReportExternalBean();
					bean.setChannelName(channelName);
					bean.setProductName(productName);
					bean.setDate(rankingsReport.getRankingsTime());
					bean.setPutPrice(product.getPutPrice());
					bean=this.convert(product.getEffectType(), rankingsReport, bean);
					externalBeans.add(bean);
				}
			}
			int total=0;
			if(externalBeans!=null&&externalBeans.size()>0){
				if("全部".equals(deviceplate)){
					int i=rankingsReportService.getTbRankingsReportTotalNumWithExternal(codition)%2;
					if(i!=0){
						total=i+1;
					}else{
						total=i;
					}
				}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", total);
			map.put("rows", externalBeans);
			return map;
	}
	
	public ReportExternalBean convert(short effectType,TbRankingsReport report,	ReportExternalBean bean){
			bean.setEffectNum(report.getChannelExternalNum()+"");
double income=0;
try {
	income=Double.parseDouble(bean.getPutPrice())*Integer.parseInt(bean.getEffectNum());

} catch (Exception e) {
	// TODO: handle exception
	income=0;
}
DecimalFormat df = new DecimalFormat(".00");
bean.setIncome(df.format(income));
return bean;
	}

}
