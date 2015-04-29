package com.codrim.controller;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codrim.bean.JsonBasicResult;
import com.codrim.bean.JsonErrorResult;
import com.codrim.bean.invite.InviteDetail;
import com.codrim.constant.ErrorCode;
import com.codrim.util.ParametersDebugUtils;

import common.codrim.common.Contans;
import common.codrim.pojo.TbWzAppUpgrade;
import common.codrim.pojo.TbWzCommonSetting;
import common.codrim.pojo.TbWzPointsLog;
import common.codrim.pojo.TbWzUser;
import common.codrim.service.WzUserService;
import common.codrim.util.DateUtil;
import common.codrim.util.StringUtil;
import common.codrim.wz.constant.DataConstant;

@Controller
public class InviteController extends BaseController {
	
	private static final Logger logger = Logger.getLogger("yj");
	
	@Autowired
	private WzUserService userService;
	
	/**
	 * 邀请界面(使用APP，加入团队)
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/invitePage.do")
	@ResponseBody
	public JsonBasicResult invitePage(HttpServletRequest request) throws DataAccessException {

		String userId = request.getParameter("userId");
		String type = request.getParameter("type");
		
		ParametersDebugUtils.debugParameters(request, "Invite Page", "userId", "type");
		if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(type)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+userId+type);
		if(!sign_flag){
			return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
		}
		JsonBasicResult<InviteDetail> result = new JsonBasicResult<InviteDetail>();
		try {
			InviteDetail id = new InviteDetail();
			String inviteCode = getInviteCodeByUserId(Long.valueOf(userId), Integer.valueOf(type));
			id.setCode(inviteCode);
			id.setInviteURL(getInviteURLByInviteCode(inviteCode, Integer.valueOf(type)));
			TbWzUser user = userService.getUserById(Long.valueOf(userId));
			if(user==null){
				return new JsonErrorResult(ErrorCode.UN401, "userid is error.");
			}
			TbWzCommonSetting setting = settingService.getCommonSetting();
			BigDecimal er = new BigDecimal(String.valueOf(setting.getExchangeRate()));
			
			if (DataConstant.INVITE_CODE_TYPE_USEAPP == Integer.valueOf(type)) {
				id.setQrCode(user.getQrCodeUser());
				BigDecimal inviterReward = new BigDecimal(String.valueOf(setting.getUserInviterReward())).multiply(er);
				BigDecimal inviteesReward = new BigDecimal(String.valueOf(setting.getUserInviteesReward())).multiply(er);
				id.setInviterReward(inviterReward.stripTrailingZeros().toPlainString());
				id.setInviteeReward(inviteesReward.stripTrailingZeros().toPlainString());
				
			} else if(DataConstant.INVITE_CODE_TYPE_JOINGROUP ==Integer.valueOf(type)) {
				id.setQrCode(user.getQrCodeGroup());
				BigDecimal inviterReward = new BigDecimal(String.valueOf(setting.getGroupInviterReward())).multiply(er);
				BigDecimal inviteesReward = new BigDecimal(String.valueOf(setting.getGroupInviteesReward())).multiply(er);
				id.setInviterReward(inviterReward.stripTrailingZeros().toPlainString());
				id.setInviteeReward(inviteesReward.stripTrailingZeros().toPlainString());
			}
			
			result.setResult(id);
			result.setRtCode(DataConstant.SUCCESS);
			
		} catch(Exception e) {
			logger.error(">>>>> Invite Page ERROR!! userId=" + userId + ", type=" + type, e);
			return new JsonErrorResult(ErrorCode.UN000, "invitePage ERROR");
		}
		
		return result;
	}
	
	/**
	 * 填写邀请码，在用户第一次打开APP的时候出现该界面
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/inputInviteCode.do")
	@ResponseBody
	public JsonBasicResult inputInviteCode(HttpServletRequest request) throws DataAccessException {

		String userId = request.getParameter("userId");
		String code = request.getParameter("code");
		
		ParametersDebugUtils.debugParameters(request, "Input Invite Code", "userId", "code");
		if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(code)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+userId+code);
		if(!sign_flag){
			return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
		}
		JsonBasicResult<String> result = new JsonBasicResult<String>();
		try {
		
			
			long id = getUserIdByInviteCode(code, DataConstant.INVITE_CODE_TYPE_USEAPP);
			if (id == 0) {
				id = getUserIdByInviteCode(code, DataConstant.INVITE_CODE_TYPE_JOINGROUP);
				if(id==0){
					return new JsonErrorResult(ErrorCode.RL108, "");
					}
				result.setResult(DataConstant.INVITE_CODE_TYPE_JOINGROUP+"");
			}else{
				int limitNum=getLimitNum(Contans.TASK_NUM_LIMIT_INVITE+"");

				int nowDayTaskNum=pointsLogService.getNowDayInvites(id, DateUtil.getNowDateToDate(System.currentTimeMillis(), "yyyy-MM-dd"));
				if(nowDayTaskNum>=limitNum){
					result.setRtCode(DataConstant.SUCCESS);
					result.setResult("true");
					return result;
				}
				
				
				TbWzUser inviter = userService.getUserById(id);
				TbWzUser invitees = userService.getUserById(Long.valueOf(userId));
				if(inviter==null||invitees==null){
					return new JsonErrorResult(ErrorCode.UN401, "userid is error.");
				}
				
				TbWzCommonSetting setting = settingService.getCommonSetting();
				
				BigDecimal er = new BigDecimal(String.valueOf(setting.getExchangeRate()));
				BigDecimal inviterReward = new BigDecimal(String.valueOf(setting.getUserInviterReward()));
				BigDecimal inviteesReward = new BigDecimal(String.valueOf(setting.getUserInviteesReward()));
				long inviterAdd=inviterReward.multiply(er).longValue();
				long inviteesAdd=inviteesReward.multiply(er).longValue();
				inviter.setBalance(inviter.getBalance() + inviterAdd);
				invitees.setBalance(invitees.getBalance() +inviteesAdd );
				
				userService.modifyInviterAndInvitees(inviter, invitees);
				result.setResult(String.valueOf(DataConstant.INVITE_CODE_TYPE_USEAPP));
        		TbWzPointsLog pointsLog_inviter=new TbWzPointsLog(inviter.getUserId(), DataConstant.INCOME_POINTS_TYPE_INVITE, code, inviterAdd, new Date());
        		TbWzPointsLog pointsLog_invitees=new TbWzPointsLog(invitees.getUserId(), DataConstant.INCOME_POINTS_TYPE_INVITE_BY, code, inviterAdd, new Date());
    			pointsLogService.savePintsLog(pointsLog_inviter);
    			pointsLogService.savePintsLog(pointsLog_invitees);
			} 
			
			result.setRtCode(DataConstant.SUCCESS);
			
		} catch(Exception e) {
			logger.error(">>>>> Input Invite Code ERROR!! userId=" + userId + ", code=" + code, e);
			return new JsonErrorResult(ErrorCode.UN000, "invitePage ERROR");
		}
		
		return result;
	}
	
	/**
	 * 邀请码能得到的奖励
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/inviteReward.do")
	@ResponseBody
	public JsonBasicResult inviteReward(HttpServletRequest request) throws DataAccessException {

		String type = request.getParameter("type");
		
		ParametersDebugUtils.debugParameters(request, "Get Invite Reward", "type");
		if (StringUtil.isEmpty(type)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+type);
		if(!sign_flag){
			return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
		}
		JsonBasicResult<InviteDetail> result = new JsonBasicResult<InviteDetail>();
		try {
			InviteDetail id = new InviteDetail();
			
			TbWzCommonSetting setting = settingService.getCommonSetting();
			BigDecimal er = new BigDecimal(String.valueOf(setting.getExchangeRate()));
			
			if (DataConstant.INVITE_CODE_TYPE_USEAPP == Integer.valueOf(type)) {
				BigDecimal inviterReward = new BigDecimal(String.valueOf(setting.getUserInviterReward())).multiply(er);
				BigDecimal inviteesReward = new BigDecimal(String.valueOf(setting.getUserInviteesReward())).multiply(er);
				id.setInviterReward(inviterReward.stripTrailingZeros().toPlainString());
				id.setInviteeReward(inviteesReward.stripTrailingZeros().toPlainString());
				
			} else {
				BigDecimal inviterReward = new BigDecimal(String.valueOf(setting.getGroupInviterReward())).multiply(er);
				BigDecimal inviteesReward = new BigDecimal(String.valueOf(setting.getGroupInviteesReward())).multiply(er);
				id.setInviterReward(inviterReward.stripTrailingZeros().toPlainString());
				id.setInviteeReward(inviteesReward.stripTrailingZeros().toPlainString());
			}
			
			result.setResult(id);
			result.setRtCode(DataConstant.SUCCESS);
			
		} catch(Exception e) {
			logger.error(">>>>> Get Invite Reward ERROR!! type=" + type, e);
			return new JsonErrorResult(ErrorCode.UN000, "inviteReward ERROR");
		}
		
		return result;
	}
	
	/**
	 * 获取应用最新版本号信息
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/getLastestVersion.do")
	@ResponseBody
	public JsonBasicResult getLastestVersion(HttpServletRequest request) throws DataAccessException {
		
		JsonBasicResult<TbWzAppUpgrade> result = new JsonBasicResult<TbWzAppUpgrade>();
		String type=request.getParameter("type");
		try {
			TbWzAppUpgrade auInfo =null;
			if("2".equals(type)){
				auInfo=settingService.getAppUpgradeInfo(2l);
			}else{
				auInfo=settingService.getAppUpgradeInfo(1l);
			}
			auInfo.setApkUrl(urlRoot + auInfo.getApkUrl());
			
			result.setResult(auInfo);
			result.setRtCode(DataConstant.SUCCESS);
			
		} catch(Exception e) {
			logger.error(">>>>> Get App Latest Version ERROR!!", e);
			return new JsonErrorResult(ErrorCode.UN000, "getLastestVersion ERROR");
		}
		
		return result;
	}
	
}
