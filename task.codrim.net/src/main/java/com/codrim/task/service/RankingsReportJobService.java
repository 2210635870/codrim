package com.codrim.task.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.common.Contans;
import common.codrim.common.RankingsReportWithDeduplicationBean;
import common.codrim.common.SelectResultByCodition;
import common.codrim.pojo.TbProductDetail;
import common.codrim.pojo.TbRankingsReport;
import common.codrim.service.ChannelService;
import common.codrim.service.EffectRankingsService;
import common.codrim.service.OperateService;
import common.codrim.service.ProductDetailService;
import common.codrim.service.RankingsReportService;
import common.codrim.util.DateUtil;
import common.codrim.util.NumberFormatUtil;

@Service
@Transactional
public class RankingsReportJobService {
	@Autowired
	private RankingsReportService rankingsReportSerivce;
	@Autowired
	private ProductDetailService productService;
	@Autowired
	private OperateService operateService;
	@Autowired
	private EffectRankingsService effectRankingsService;
	@Autowired
	private ChannelService channelService;

	private static final Logger log = Logger.getLogger("TASK");

	public void addRankingsReport() {
		List<TbRankingsReport> reports = this.getRankingsReport();
		if (reports != null && reports.size() > 0) {
			log.info("--------------------------------冲榜报表添加开始");
			rankingsReportSerivce.addBatch(reports);
		}
	}

	public List<TbRankingsReport> getRankingsReport() {
		List<TbProductDetail> products = productService.getAllTbProducts();
		List<TbRankingsReport> reports = new ArrayList<TbRankingsReport>();
		String[] deviceplates = { "ios", "android" };
		log.info("--------------------------------获取所有冲榜产品 是否为空："
				+ (products == null));
		SelectResultByCodition reCodition=new SelectResultByCodition();
		if (products != null && products.size() > 0) {
			for (TbProductDetail product : products) {
				for (String deviceplate : deviceplates) {
					log.info("-------------------------------遍历冲榜产品和系统平台："
							+ product.getProductName() + "----"
							+ product.getChannelName() + "-----" + deviceplate);
					reCodition.setProductName(product.getProductName());
					reCodition.setChannelName(product.getChannelName());
					reCodition.setDeviceplate(deviceplate);
					TbProductDetail		newProduct=productService.getProductDetailByCodition(reCodition);
					if(newProduct!=null){
					reports.addAll(this.getRankingsReport(newProduct, deviceplate));
					}
				}
			}
		}
		return reports;
	}

	public List<TbRankingsReport> getRankingsReport(TbProductDetail product,
			String devicePlate) {
		List<Date> dates = effectRankingsService.getEffectRankingsDate();
		List<TbRankingsReport> reports = new ArrayList<TbRankingsReport>();
		log.info("--------------------------------获取冲榜明细表所有数据时间："
				+ (dates == null));
		if (dates != null && dates.size() > 0) {
			for (Date date : dates) {
				log.info("--------------------------------遍历时间循环添加："
						+ DateUtil.getNowDateToString(date.getTime(),
								"yyyy-MM-dd"));
				TbRankingsReport rankingsReport = judgeAndGetReports(product,
						devicePlate, date);
				if (rankingsReport != null)
					reports.add(rankingsReport);
			}
		}
		return reports;
	}

	public TbRankingsReport judgeAndGetReports(TbProductDetail rankingsProduct,
			String devicePlate, Date date) {
		TbRankingsReport rankingsReport = null;
		Date nowDate = DateUtil.getNowDateToDate(System.currentTimeMillis(),
				"yyyy-MM-dd");
		log.info("-*******************************************************------------"
				+ nowDate + "-------------" + date);
		if (nowDate.getTime() != date.getTime()) {
			rankingsReport = judgeTbRankingsReport(rankingsProduct,
					devicePlate, date);
		} else {
			// String
			// timeString=DateUtil.getBeforeNowDate(DateUtil.getNowDateToString(date.getTime(),
			// "yyyy-MM-dd"), "yyyy-MM-dd");
			// rankingsReport=judgeTbRankingsReport(rankingsProduct,
			// devicePlate, DateUtil.convertStringToDate(timeString,
			// "yyyy-MM-dd"));
			log.info("--------------------当天数据不添加：" + nowDate);
		}
		return rankingsReport;
	}

	/**
	 * 获取RankingsReport 数据
	 * 
	 * @author searh
	 * @date 2014年12月17日
	 * @param reports
	 * @param lists
	 * @param date
	 * @param nowHour
	 * @param rightDateWithHour
	 * @return List<TbChannelReport>
	 */

	public TbRankingsReport judgeTbRankingsReport(TbProductDetail rankingsProduct,
			String devicePlate, Date date) {
		SelectResultByCodition resultByCodition = new SelectResultByCodition();
		resultByCodition.setProductName(rankingsProduct.getProductName());
		resultByCodition.setChannelName(rankingsProduct.getChannelName());
		resultByCodition.setDeviceplate(devicePlate);
		resultByCodition.setStartTime(date);
		resultByCodition.setCustomerName(rankingsProduct.getCustomerName());
		TbRankingsReport rankingsReport = rankingsReportSerivce
				.checkRankingsReportByHour(resultByCodition);
		log.info("--------------------------------判断冲榜统计表是否已有数据："
				+ (rankingsReport == null) + "-----"
				+ rankingsProduct.getProductName() + "-----"
				+ rankingsProduct.getChannelName() + "-----" + date);
		if (rankingsReport == null) {
			// 不存在，需要添加
			log.info("--------------------------------冲榜统计表" + date + "数据没有添加");
			return this.converTbRankingsReport(resultByCodition, date,
					rankingsProduct);
		}
		return null;
	}

	/**
	 * 获得TbRankingsReport对象数据
	 * 
	 * @author searh
	 * @param SelectResultByCodition
	 * @param TbRankingsProduct
	 * @param short
	 * @return TbRankingsReport
	 * @date 2014年12月15日
	 * */
	public TbRankingsReport converTbRankingsReport(
			SelectResultByCodition codition, Date date,
			TbProductDetail rankingsProduct) {
		log.info("--------------------------------获取rankingreport对象：" + date);
		// 准备查询数据
		codition.setClickNum(Contans.RANKINGS_ACTION_CLICK); // 点击数
		codition.setEffect(Contans.RANKINGS_ACTION_EFFECT_SUCCESS);// 效果数
		codition.setActivate(Contans.RANKINGS_ACTION_ACTIVATE);// 激活
		codition.setRegister(Contans.RANKINGS_ACTION_REGISTER);// 注册数
		codition.setConsumeMore(Contans.RANKINGS_ACTION_CONSUME);// 消费两次及以上
		codition.setBecharge(Contans.RANKINGS_ACTION_RECHARGE);// 充值
		codition.setCredit(Contans.RANKINGS_ACTION_CREDIT); // 绑卡数
		codition.setBaddept(Contans.RANKINGS_ACTION_BADDEPT);// 坏账数
		codition.setStartTime(date);
		// 通过条件得到的TbRankingsReport对象，已有 效果数、激活、注册数、消费两次及以上、充值、绑卡数、坏账数的统计数据
		TbRankingsReport rankingsReport = effectRankingsService
				.getRankingsReportData(codition);
		if("android".equals(codition.getDeviceplate())){
			codition.setMarkType("imei");
		}
		if("ios".equals(codition.getDeviceplate())){
			codition.setMarkType("idfa");
		}
		rankingsReport.setIssubtract(Contans.FALSE);
		// 产品名
		rankingsReport.setProductName(codition.getProductName());
		// 客户名称
		rankingsReport.setCustomerName(codition.getCustomerName());
		// 渠道名
		rankingsReport.setChannelName(codition.getChannelName());
		// 设备类型 ios/android
		rankingsReport.setDeviceplate(codition.getDeviceplate());
		// 产生效果的时间
		rankingsReport.setRankingsTime(date);
		// 小时
		// rankingsReport.setRankingsHour(hour);
		// 接入单价
		rankingsReport.setAccessPrice(rankingsProduct.getAccessPrice());
		// 投放单价
		rankingsReport.setPutPrice(rankingsProduct.getPutPrice());
		RankingsReportWithDeduplicationBean  rankingsReportWithDed = effectRankingsService.getRankingsReportDataWithDeduplication(codition);
		rankingsReport.setEffectNumWithDeduplication(rankingsReportWithDed.getEffectNum()+0l);
		if(!"ios".equals(codition.getDeviceplate())){
			short effectType = rankingsProduct.getEffectType();
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
				costs = Double.parseDouble(rankingsProduct.getPutPrice())
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
				grossProfit = (Double.parseDouble(rankingsProduct
						.getAccessPrice()) - Double.parseDouble(rankingsProduct
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

}
