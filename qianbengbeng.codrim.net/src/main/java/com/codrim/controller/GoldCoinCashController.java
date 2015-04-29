package com.codrim.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codrim.bean.JsonBasicResult;
import com.codrim.bean.JsonErrorResult;
import com.codrim.bean.exchange.JsonExchangeRecord;
import com.codrim.constant.ErrorCode;
import com.codrim.util.ParametersDebugUtils;

import common.codrim.common.ResultJsonBean;
import common.codrim.pojo.TbWzCoinCashSetting;
import common.codrim.pojo.TbWzUser;
import common.codrim.service.WzCoinCashService;
import common.codrim.service.WzExchangeService;
import common.codrim.service.WzUserService;
import common.codrim.util.DateUtil;
import common.codrim.util.StringUtil;
import common.codrim.wz.constant.DataConstant;
import common.codrim.wz.sql.result.ExchangeRecordInfo;

@Controller
public class GoldCoinCashController extends BaseController{
	@Autowired
	WzCoinCashService coinCashService;
	@Autowired
	private WzUserService userService;
	@Autowired
	private WzExchangeService exchangeService;
	private static final Logger logger = Logger.getLogger("yj");
	
	/**
	 * 兑换列表值
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	
	@RequestMapping("/getCashSettings.do")
	@ResponseBody
	public List<TbWzCoinCashSetting>  getGoldCashSettings(HttpServletRequest request){
		List<TbWzCoinCashSetting> list=new ArrayList<TbWzCoinCashSetting>();
		ParametersDebugUtils.debugParameters(request, "getCashSettings", "");
		list=coinCashService.getCashSettings();
		return list;
	}
	
	/**社交墙获取兑换列表值
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("/getCashSettingByType.do")
	@ResponseBody
	public  boolean  getGoldCashSettings(HttpServletResponse response,HttpServletRequest request){
		response.setCharacterEncoding("utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST,GET" );
		response.addHeader("Access-Control-Allow-Credentials", "true" );
		short type=0;
		long userId=0;
		boolean flag = false;
		try {
			type=Short.parseShort(request.getParameter("type"));
			userId=Long.parseLong(request.getParameter("userId"));
		} catch (Exception e) {
			// TODO: handle exception
			return flag;
		}
		
		ParametersDebugUtils.debugParameters(request, "getCashSettingByType","type","userId");

		TbWzUser user = userService.getUserById(userId);
		if (user != null) {
			long balance = user.getBalance();
			TbWzCoinCashSetting coinCashSetting=coinCashService.getCashSettingByCashType(type);
			if (coinCashSetting!=null) {
			  if(balance>=coinCashSetting.getGoldCoin()){
				  flag=true;
			  }
			}
			}
		return flag;
	}
	/**
	 * 兑换
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/exchange.do")
	@ResponseBody
	public JsonBasicResult exchange(HttpServletRequest request,HttpServletResponse response) throws DataAccessException {
		
		String userId = request.getParameter("userId");
		String type = request.getParameter("type"); // Z10, H20
		String phone = request.getParameter("phone");
		String zfb = request.getParameter("zfb");
		response.setCharacterEncoding("utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST,GET" );
		response.addHeader("Access-Control-Allow-Credentials", "true" );
		ParametersDebugUtils.debugParameters(request, "Exchange", "userId", "type", "phone", "zfb");
		if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(type)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		logger.info(">>>>> Exchange: userId=" + userId + ", type=" + type + ", phone=" + phone + ", zfb=" + zfb);
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+userId+type+phone+zfb);
if(!sign_flag){
	return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
}

		JsonBasicResult result = new JsonBasicResult();
		try {
//		//	TbWzCommonSetting setting = settingService.getCommonSetting();
//			BigDecimal er = toBigDecimal(setting.getExchangeRate());
//			
	String exchangeMoney = type.substring(1);
//			long gold = er.multiply(toBigDecimal(exchangeMoney)).longValue();\
			short cashType=0;
			if(type.equals("Z10")){
				cashType=1;
			}
			if(type.equals("Z20")){
				cashType=2;
			}	if(type.equals("Z50")){
				cashType=3;
			}	if(type.equals("H10")){
				cashType=4;
			}	if(type.equals("H20")){
				cashType=5;
			}	if(type.equals("H50")){
				cashType=6;
			}
			long gold = coinCashService.getCashSettingByCashType(cashType).getGoldCoin();
			boolean exchangeResult = false;
			if (type.startsWith(DataConstant.EXCHANGE_TYPE_ZFB_PREFIX)) {
				exchangeResult = exchangeService.exchange(DataConstant.EXCHANGE_TYPE_ZFB, Double.valueOf(exchangeMoney), 
						gold, Long.valueOf(userId), zfb);
				
			} else if (type.startsWith(DataConstant.EXCHANGE_TYPE_HF_PREFIX)) {
				exchangeResult = exchangeService.exchange(DataConstant.EXCHANGE_TYPE_HF, Double.valueOf(exchangeMoney), 
						gold, Long.valueOf(userId), phone);
			}
			
			if (exchangeResult) {
				result.setRtCode(DataConstant.SUCCESS);
			} else {
				return new JsonErrorResult(ErrorCode.EX301, "");
			}
			
		} catch(Exception e) {
			logger.error(">>>>> Exchange ERROR!! userId=" + userId + ", type=" + type + ", phone=" + phone + ", zfb=" + zfb, e);
			return new JsonErrorResult(ErrorCode.UN000, "exchange ERROR");
		}
		
		return result;
	}
	
	/**
	 * 兑换记录列表
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/exchangeRecords.do")
	@ResponseBody
	public JsonBasicResult exchangeRecords(HttpServletRequest request) throws DataAccessException {
		
		String userId = request.getParameter("userId");
		String page = request.getParameter("page");
		
		ParametersDebugUtils.debugParameters(request, "Get Exchange Records", "userId", "page");
		if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(page)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+userId+page);
if(!sign_flag){
	return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
}
		JsonBasicResult<List<JsonExchangeRecord>> result = new JsonBasicResult<List<JsonExchangeRecord>>();
		try {
			ExchangeRecordInfo param = new ExchangeRecordInfo();
			param.setUserId(Long.valueOf(userId));
			List<ExchangeRecordInfo> records = exchangeService.searchExchangeRecord(Integer.valueOf(page), DataConstant.PAGESIZE_20, param);
			
			List<JsonExchangeRecord> ers = new ArrayList<JsonExchangeRecord>();
			if (records != null && records.size() > 0) {
				for (ExchangeRecordInfo record : records) {
					JsonExchangeRecord er = new JsonExchangeRecord();
					er.setRecordId(record.getRecordId());
					er.setExchangeType(StringUtil.toString(record.getExchangeType()));
					er.setExchangeMoney(StringUtil.toString(record.getExchangeMoney().intValue()));
					er.setExchangeGold(StringUtil.toString(record.getExchangeGold()));
					er.setExchangeDate(DateUtil.formatDate(record.getExchangeDate()));
					er.setReviewStatus(StringUtil.toString(record.getReviewStatus()));
					
					ers.add(er);
				}
			}
			
			result.setResult(ers);
			result.setRtCode(DataConstant.SUCCESS);
		} catch(Exception e) {
			logger.error(">>>>> Get Exchange Records ERROR!", e);
			return new JsonErrorResult(ErrorCode.UN000, "exchangeRecords ERROR");
		}
		
		return result;
	}
}
