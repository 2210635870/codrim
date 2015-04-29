package com.codrim.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.math3.distribution.EnumeratedDistribution;
import org.apache.commons.math3.util.Pair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codrim.bean.JsonBasicResult;
import com.codrim.bean.JsonErrorResult;
import com.codrim.bean.cj.JsonChouJiangInfo;
import com.codrim.constant.ErrorCode;
import com.codrim.util.ParametersDebugUtils;

import common.codrim.pojo.TbWzCommonSetting;
import common.codrim.pojo.TbWzPointsLog;
import common.codrim.pojo.TbWzUser;
import common.codrim.service.WzUserService;
import common.codrim.util.DateUtil;
import common.codrim.util.StringUtil;
import common.codrim.wz.constant.DataConstant;

@Controller
public class ChouJiangController extends BaseController {
	
	private static final Logger logger = Logger.getLogger("yj");
	
	@Autowired
	private WzUserService userService;

	/**
	 * 抽奖主界面
	 * 5,6,3,1,2,6,4,1,3,2,6,1 (6为恭喜发财)
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/cjPage.do")
	@ResponseBody
	public JsonBasicResult cjPage(HttpServletRequest request) throws DataAccessException {

		String userId = request.getParameter("userId");
		
		ParametersDebugUtils.debugParameters(request, "ChouJiang Page", "userId");
		if (StringUtil.isEmpty(userId)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+userId);
		if(!sign_flag){
			return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
		}
		JsonBasicResult<JsonChouJiangInfo> result = new JsonBasicResult<JsonChouJiangInfo>();
		try {
			JsonChouJiangInfo cj = new JsonChouJiangInfo();
			
			TbWzUser user = userService.getUserById(Long.valueOf(userId));
			if(user==null){
				return new JsonErrorResult(ErrorCode.UN401, "userid is error.");
			}
			TbWzCommonSetting setting = settingService.getCommonSetting();
			BigDecimal er = toBigDecimal(setting.getExchangeRate());
			BigDecimal cost = toBigDecimal(setting.getCjCost());
			
			long apartSeconds = DateUtil.getDateSecondDiff(user.getLastCjDate(), new Date());
			long intervalSeconds = setting.getCjInterval() * 60;
			if (user.getLastCjDate() != null && apartSeconds < intervalSeconds) {
				cj.setRemainSeconds(String.valueOf(intervalSeconds - apartSeconds));
			} else {
				cj.setRemainSeconds("0");
			}
			cj.setCost(multiply(er, cost));
			cj.setInterval(StringUtil.toString(intervalSeconds));
			
			cj.setP1(multiply(er, toBigDecimal(setting.getCjPrize5())));
			cj.setP2("0");
			cj.setP3(multiply(er, toBigDecimal(setting.getCjPrize3())));
			cj.setP4(multiply(er, toBigDecimal(setting.getCjPrize1())));
			cj.setP5(multiply(er, toBigDecimal(setting.getCjPrize2())));
			cj.setP6("0");
			cj.setP7(multiply(er, toBigDecimal(setting.getCjPrize4())));
			cj.setP8(multiply(er, toBigDecimal(setting.getCjPrize1())));
			cj.setP9(multiply(er, toBigDecimal(setting.getCjPrize3())));
			cj.setP10(multiply(er, toBigDecimal(setting.getCjPrize2())));
			cj.setP11("0");
			cj.setP12(multiply(er, toBigDecimal(setting.getCjPrize1())));
			result.setResult(cj);
			result.setRtCode(DataConstant.SUCCESS);
			
		} catch(Exception e) {
			logger.error(">>>>> ChouJiang Page ERROR!", e);
			return new JsonErrorResult(ErrorCode.UN000, "cjPage ERROR");
		}
		
		return result;
	}
	
	/**
	 * 抽奖
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/choujiang.do")
	@ResponseBody
	public JsonBasicResult choujiang(HttpServletRequest request) throws DataAccessException {

		String userId = request.getParameter("userId");
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+userId);
if(!sign_flag){
	return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
}
		ParametersDebugUtils.debugParameters(request, "Choujiang", "userId");
		if (StringUtil.isEmpty(userId)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		
		JsonBasicResult<String> result = new JsonBasicResult<String>();
		try {
			TbWzUser user = userService.getUserById(Long.valueOf(userId));
			if(user==null){
				return new JsonErrorResult(ErrorCode.UN401, "userid is error.");
			}
			TbWzCommonSetting setting = settingService.getCommonSetting();
			BigDecimal er = toBigDecimal(setting.getExchangeRate());
			long cost = Long.valueOf(multiply(er, toBigDecimal(setting.getCjCost())));
			
			long finalBalance = user.getBalance() - cost;
			if (finalBalance < 0) {
				return new JsonErrorResult(ErrorCode.EX301, "");
			}
			
			String prizeLevel = luckyDraw(setting);
			long prizeGold=0;
			if (!"6".equals(prizeLevel)) {
				prizeGold = getPrizeGoldByLevel(setting, prizeLevel);
				finalBalance += prizeGold;
			}
			
			user.setBalance(finalBalance);
			user.setLastCjDate(new Date());
			userService.modifyUser(user);
			
			
			TbWzPointsLog pointsLog=new TbWzPointsLog(Long.valueOf(userId), DataConstant.INCOME_POINTS_TYPE_CHOUJIANG, null, prizeGold, new Date());
			pointsLogService.savePintsLog(pointsLog);
			
			
			result.setResult(getPrizeNumberByLevel(prizeLevel));
			result.setRtCode(DataConstant.SUCCESS);
			
		} catch(Exception e) {
			logger.error(">>>>> Choujiang ERROR!", e);
			return new JsonErrorResult(ErrorCode.UN000, "choujiang ERROR");
		}
		
		return result;
	}
	
	//------------------------------------------------------------------------------ Lucky Draw
	private static char[] PRIZE1_SEED = {'4', '8', 'C'};
	private static char[] PRIZE2_SEED = {'5', 'A'};
	private static char[] PRIZE3_SEED = {'3', '9'};
	private static char[] PRIZE4_SEED = {'7'};
	private static char[] PRIZE5_SEED = {'1'};
	private static char[] PRIZE6_SEED = {'2', '6', 'B'};
	
	/**
	 * 总计12个格子，从左上角顺时针序号依次为1-12，其中：<br>
	 * 奖品1： 3个 (4, 8, 12)<br>
	 * 奖品2： 2个 (5, 10)<br>
	 * 奖品3： 2个 (3, 9)<br>
	 * 奖品4： 1个 (7)<br>
	 * 奖品5： 1个 (1)<br>
	 * 奖品6： 3个 (2, 6, 11)   --恭喜发财<br>
	 * 根据奖励等级的权重来得出最终奖励等级，再随机在奖励等级对应的序号里随机抽取一个序号作为最终的获奖序号
	 * @param setting
	 * @return
	 */
	private static String luckyDraw(TbWzCommonSetting setting) {
		List<Pair<String, Double>> pairs = new ArrayList<Pair<String, Double>>();
		pairs.add(new Pair<String, Double>("1", setting.getCjPrize1Prob()));
		pairs.add(new Pair<String, Double>("2", setting.getCjPrize2Prob()));
		pairs.add(new Pair<String, Double>("3", setting.getCjPrize3Prob()));
		pairs.add(new Pair<String, Double>("4", setting.getCjPrize4Prob()));
		pairs.add(new Pair<String, Double>("5", setting.getCjPrize5Prob()));
		pairs.add(new Pair<String, Double>("6", setting.getCjNoneProb()));
		
		EnumeratedDistribution<String> ed = new EnumeratedDistribution<String>(pairs);
		
		return ed.sample();
	}
	
	private static String getPrizeNumberByLevel(String prizeLevel) {
		String prizeIndex = "";
		switch (prizeLevel) {
		case "1":
			prizeIndex = RandomStringUtils.random(1, PRIZE1_SEED);
			break;
			
		case "2":
			prizeIndex = RandomStringUtils.random(1, PRIZE2_SEED);
			break;
			
		case "3":
			prizeIndex = RandomStringUtils.random(1, PRIZE3_SEED);
			break;
			
		case "4":
			prizeIndex = RandomStringUtils.random(1, PRIZE4_SEED);
			break;
			
		case "5":
			prizeIndex = RandomStringUtils.random(1, PRIZE5_SEED);
			break;

		default:
			prizeIndex = RandomStringUtils.random(1, PRIZE6_SEED);
			break;
		}
		
		return StringUtil.toString(Integer.parseInt(prizeIndex, 16));
	}
	
	private static long getPrizeGoldByLevel(TbWzCommonSetting setting, String prizeLevel) {
		BigDecimal er = toBigDecimal(setting.getExchangeRate());
		BigDecimal prize1 = toBigDecimal(setting.getCjPrize1());
		BigDecimal prize2 = toBigDecimal(setting.getCjPrize2());
		BigDecimal prize3 = toBigDecimal(setting.getCjPrize3());
		BigDecimal prize4 = toBigDecimal(setting.getCjPrize4());
		BigDecimal prize5 = toBigDecimal(setting.getCjPrize5());
		
		String prizeGold = "";
		switch (prizeLevel) {
		case "1":
			prizeGold = multiply(er, prize1);
			break;
			
		case "2":
			prizeGold = multiply(er, prize2);
			break;
			
		case "3":
			prizeGold = multiply(er, prize3);
			break;
			
		case "4":
			prizeGold = multiply(er, prize4);
			break;
			
		case "5":
			prizeGold = multiply(er, prize5);
			break;

		default:
			prizeGold = "0";
			break;
		}
		
		return Long.valueOf(prizeGold);
	}
	
	
	public static void main(String[] args) {
		
		TbWzCommonSetting setting = new TbWzCommonSetting();
		setting.setCjPrize1Prob(30D);
		setting.setCjPrize2Prob(25D);
		setting.setCjPrize3Prob(10D);
		setting.setCjPrize4Prob(4D);
		setting.setCjPrize5Prob(1D);
		setting.setCjNoneProb(30D);
		
		int p1=0,p2=0,p3=0,p4=0,p5=0,p6=0;
		
		int n = 1000;
		for (int i=0; i<n; i++) {
			String j = luckyDraw(setting);
			if ("1".equals(j)) {
				p1++;
			} else if ("2".equals(j)) {
				p2++;
			} else if ("3".equals(j)) {
				p3++;
			} else if ("4".equals(j)) {
				p4++;
			} else if ("5".equals(j)) {
				p5++;
			} else {
				p6++;
			}
		}
		
		DecimalFormat df = new DecimalFormat("0.00");
		System.out.println("总计抽奖次数：" + n);
		System.out.println("恭喜发财中奖人数：" + p6 + ", 占比：" + df.format(Integer.valueOf(p6).doubleValue()/n * 100) + "%");
		System.out.println("奖品1中奖人数：" + p1 + ", 占比：" + df.format(Integer.valueOf(p1).doubleValue()/n * 100) + "%");
		System.out.println("奖品2中奖人数：" + p2 + ", 占比：" + df.format(Integer.valueOf(p2).doubleValue()/n * 100) + "%");
		System.out.println("奖品3中奖人数：" + p3 + ", 占比：" + df.format(Integer.valueOf(p3).doubleValue()/n * 100) + "%");
		System.out.println("奖品4中奖人数：" + p4 + ", 占比：" + df.format( Integer.valueOf(p4).doubleValue()/n * 100) + "%");
		System.out.println("奖品5中奖人数：" + p5 + ", 占比：" + df.format(Integer.valueOf(p5).doubleValue()/n * 100) + "%");
	}

}
