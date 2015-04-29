package common.codrim.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.common.Contans;
import common.codrim.common.RankingsReportWithDeduplicationBean;
import common.codrim.common.SelectResultByCodition;
import common.codrim.pojo.TbProductDetail;
import common.codrim.pojo.TbRankingsReport;
import common.codrim.service.EffectRankingsService;
import common.codrim.service.ProductDetailService;
import common.codrim.service.RankingsReportService;
import common.codrim.util.DateUtil;
import common.codrim.util.NumberFormatUtil;

@Service
@Transactional
public class GetCurrentDateRankingsReportJobService {
	@Autowired
	private RankingsReportService rankingsReportSerivce;
	@Autowired
	private ProductDetailService productService;
	@Autowired
	private EffectRankingsService effectRankingsService;
	

	


	public List<TbRankingsReport> getRankingsReport(SelectResultByCodition codition,String type){
		List<TbRankingsReport> reports=new ArrayList<TbRankingsReport>();
		TbProductDetail product=null;
		if(type.equals("external")){
			product=productService.getProductDetail(codition.getProductName(), codition.getChannelName());
		}else{
			product	=productService.getProductDetailByCodition(codition);
		}
		if(product==null){
			return reports;
		}else{
			reports.add(judgeTbRankingsReport(product, codition.getDeviceplate(), codition.getEndTime()));
		}
		return reports;
	}
	
	public TbRankingsReport judgeTbRankingsReport(TbProductDetail product,String devicePlate,Date date){
			SelectResultByCodition resultByCodition=new SelectResultByCodition();
			resultByCodition.setProductName(product.getProductName());
			resultByCodition.setChannelName(product.getChannelName());
			resultByCodition.setDeviceplate(devicePlate);
			resultByCodition.setStartTime(date);
			    	return this.converTbRankingsReport(resultByCodition, DateUtil.getNowDateToString(date.getTime(), "yyyy-MM-dd"), product);
	}
	

	

	/**
	 * 获得TbRankingsReport对象数据
	 * @author searh
	 * @param SelectResultByCodition
	 * @param TbRankingsProduct
	 * @param short
	 * @return TbRankingsReport
	 * @date 2014年12月15日
	 * */
	public TbRankingsReport converTbRankingsReport(SelectResultByCodition codition,String date,TbProductDetail product) {
		    Date startTime=DateUtil.convertStringToDate(date,"yyyy-MM-dd");
		 //准备查询数据
		 codition.setClickNum(Contans.RANKINGS_ACTION_CLICK);   //点击数
		 codition.setEffect(Contans.RANKINGS_ACTION_EFFECT_SUCCESS);//效果数
		 codition.setActivate(Contans.RANKINGS_ACTION_ACTIVATE);//激活
		 codition.setRegister(Contans.RANKINGS_ACTION_REGISTER);//注册数
		 codition.setConsumeMore(Contans.RANKINGS_ACTION_CONSUME);//消费两次及以上
		 codition.setBecharge(Contans.RANKINGS_ACTION_RECHARGE);// 充值
		 codition.setCredit(Contans.RANKINGS_ACTION_CREDIT); //绑卡数
		 codition.setBaddept(Contans.RANKINGS_ACTION_BADDEPT);//坏账数
		 codition.setStartTime(startTime);
		 //通过条件得到的TbRankingsReport对象，已有 效果数、激活、注册数、消费两次及以上、充值、绑卡数、坏账数的统计数据
		 TbRankingsReport rankingsReport=effectRankingsService.getRankingsReportData(codition);
		//产品名
		 rankingsReport.setProductName(codition.getProductName());
		 //客户名称
		 rankingsReport.setCustomerName(codition.getCustomerName());
		 //渠道名
		 rankingsReport.setChannelName(codition.getChannelName());
		 //设备类型  ios/android
		 rankingsReport.setDeviceplate(codition.getDeviceplate());
		//产生效果的时间
		rankingsReport.setRankingsTime(DateUtil.convertStringToDate(date, "yyyy-MM-dd"));
	//小时
//		rankingsReport.setRankingsHour(hour);
		//接入单价
		rankingsReport.setAccessPrice(product.getAccessPrice());
		//投放单价
		rankingsReport.setPutPrice(product.getPutPrice());
		RankingsReportWithDeduplicationBean  rankingsReportWithDed = effectRankingsService.getRankingsReportDataWithDeduplication(codition);
		rankingsReport.setEffectNumWithDeduplication(rankingsReportWithDed.getEffectNum()+0l);
		if(!"ios".equals(codition.getDeviceplate())){
			short effectType = product.getEffectType();
			if (effectType == Contans.EFFECT_TYPE_ACTIVATE) {// 效果结算方式 激活
				rankingsReport.setEffectNum(rankingsReport.getActivate() + 0l);
				rankingsReport.setEffectNumWithDeduplication(rankingsReportWithDed.getActivate()+0l);
			}
			if (effectType == Contans.EFFECT_TYPE_REGISTER) {// 注册
				rankingsReport.setEffectNum(rankingsReport.getRegister() + 0l);
				rankingsReport.setEffectNumWithDeduplication(rankingsReportWithDed.getRegister()+0l);
			}
			if (effectType == Contans.EFFECT_TYPE_CONSUME_MORE) {// 消费两次以上
				rankingsReport
						.setEffectNum(rankingsReport.getConsumeMore() + 0l);
				rankingsReport.setEffectNumWithDeduplication(rankingsReportWithDed.getConsumeMore()+0l);

			}
			if (effectType == Contans.EFFECT_TYPE_CONSUME_ONE) {// 消费一次
				rankingsReport
						.setEffectNum(rankingsReport.getConsumeOne() + 0l);
				rankingsReport.setEffectNumWithDeduplication(rankingsReportWithDed.getConsumeOne()+0l);
			}
		}
		rankingsReport.setDeduplicationNum(rankingsReport.getEffectNum()-rankingsReport.getEffectNumWithDeduplication());
		rankingsReport.setDeduplicationRate( NumberFormatUtil.getDeci(rankingsReport
						.getDeduplicationNum().intValue(), rankingsReport
						.getEffectNum().intValue()));
		
		
		
		
		// if(effectType==Contans.EFFECT_TYPE_CONSUME_NUMBER){//消费总金额
		// rankingsReport.setEffectNum(rankingsReport.getRegister()+"");//暂未做
		// }
		double income = 0;
		double costs = 0;
		String conversinRate = "0.0%";
		double grossProfit = 0;
		String grossMargin = "0.0%";
		try {
			income = Double.parseDouble(rankingsReport.getAccessPrice())
					* rankingsReport.getEffectNum();
		} catch (Exception e) {
			// TODO: handle exception
			income = 0;

		}
		try {
			costs = Double.parseDouble(product.getPutPrice())
					* rankingsReport.getEffectNum();
		} catch (Exception e) {
			// TODO: handle exception
			costs = 0;

		}
		try {
			conversinRate = NumberFormatUtil.getDeci(rankingsReport
					.getEffectNum().intValue(), rankingsReport
					.getClickNum().intValue());
		} catch (Exception e) {
			// TODO: handle exception
			conversinRate = "0.0%";

		}
		try {
			grossProfit = (Double.parseDouble(product
					.getAccessPrice()) - Double.parseDouble(product
					.getPutPrice()))
					* rankingsReport.getEffectNum();
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
		rankingsReport.setIncome(income);
		// 成本
		rankingsReport.setCosts(costs);
		// 转化率
		rankingsReport.setConversinRate(conversinRate);
		// 毛利
		rankingsReport.setGrossProfit(grossProfit);
		// 毛利率
		rankingsReport.setGrossMargin(grossMargin);
		rankingsReport.setId(0l);		
		return rankingsReport;
	}


	
}
