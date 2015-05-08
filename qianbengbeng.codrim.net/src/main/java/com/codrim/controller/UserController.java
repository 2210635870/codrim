package com.codrim.controller;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codrim.bean.AddressInfo;
import com.codrim.bean.JsonBasicResult;
import com.codrim.bean.JsonErrorResult;
import com.codrim.bean.user.JsonSignInInfo;
import com.codrim.bean.user.JsonUserDetail;
import com.codrim.bean.user.JsonUserInfo;
import com.codrim.constant.ErrorCode;
import com.codrim.util.AddressUtils;
import com.codrim.util.ParametersDebugUtils;
import com.codrim.util.SmsUtils;

import common.codrim.common.Contans;
import common.codrim.pojo.TbWzBinding;
import common.codrim.pojo.TbWzPointsLog;
import common.codrim.pojo.TbWzThirdDeviceUid;
import common.codrim.pojo.TbWzUser;
import common.codrim.service.WzThirdDeviceUidService;
import common.codrim.service.WzUserService;
import common.codrim.util.DateUtil;
import common.codrim.util.StringUtil;
import common.codrim.wz.constant.DataConstant;

@Controller
public class UserController extends BaseController {
	
	private static final Logger logger = Logger.getLogger("yj");

	@Autowired
	private WzUserService userService;
	@Autowired
	WzThirdDeviceUidService thirdDeviceUidService;
	
	/**
	 * 登录(欢迎页面)
	 * 如果设备号存在，找到对应的用户，直接进入主页并更新最后登录时间;
	 * 如果设备号不存在，创建新用户，绑定设备号，然后进入主页并更新最后登录时间
	 * @return JsonBasicResult
	 * @throws DataAccessException
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	public JsonBasicResult login(HttpServletRequest request) throws DataAccessException {

		String deviceId = request.getParameter("deviceId");
		String devicePlat = request.getParameter("devicePlat");
		String ip = request.getParameter("ip");
		String mac = request.getParameter("mac");
		String channel=request.getParameter("channel");
		String type=request.getParameter("type");
		//社交墙使用
		String unionid=request.getParameter("unionid");
		String difId=request.getParameter("difId");
		ParametersDebugUtils.debugParameters(request, "User Login","id","unionid","difId", "deviceId", "devicePlat", "ip", "mac","channel","type","timeStamp","sign");
		if ( StringUtil.isEmpty(deviceId)|| StringUtil.isEmpty(devicePlat)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+deviceId+devicePlat+ip+mac+channel+type);
		if(!sign_flag){
			return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
		}
		JsonBasicResult<JsonUserInfo> result = new JsonBasicResult<JsonUserInfo>();
		try {
			JsonUserInfo ui = new JsonUserInfo();
			
			TbWzBinding existedBinding = userService.getBindingByDevice(deviceId);
			if (existedBinding == null) { // new user
				logger.info(">>>>> User Login: Create a new user...");
				TbWzUser user = new TbWzUser();
				String account = "U" + RandomStringUtils.randomNumeric(6);
				user.setPhoneNumber(account); //暂时先随机一个账号和用户名
				user.setUsername(account);
				user.setCreateDate(new Date());
				user.setStatus(DataConstant.USER_STATUS_ENABLE);
				user.setBindStatus(DataConstant.FALSE);
				user.setIsLeader(DataConstant.FALSE);
				user.setBalance(0L);
				user.setUserEffect(0);
				user.setStepEffect(0);
				user.setGroupId(0L);
				user.setLastLoginDate(new Date());
				user.setIsDisable(Contans.FALSE);
				user.setUserSource(Short.parseShort(type));
				TbWzBinding binding = new TbWzBinding();
				binding.setDeviceId(deviceId);
				binding.setDevicePlat(Integer.valueOf(devicePlat));
				binding.setIp(ip);
				binding.setMac(mac);
				binding.setBindingDate(new Date());
				binding.setChannelName(channel);
				// 根据IP获取地理位置等相关信息
				logger.info(">>>>> User Login: Start to get Address by ip: " + ip);
				AddressInfo address = AddressUtils.getAddress(ip);
				if (address != null) {
					binding.setCountry(address.getCountry());
					binding.setCity(address.getCity());
					binding.setRegion(address.getRegion());
					binding.setIsp(address.getIsp());
					logger.info(">>>>> User Login: Get Address by ip success: " 
							+ address.getCountry() + address.getCity() + address.getRegion() + address.getIsp());
				}
				
				// 邀请码链接的二维码
				String qrCodeUser = StringUtil.getUniqueFilename("foo.png");
				String qrCodeGroup = StringUtil.getUniqueFilename("foo.png");
				user.setQrCodeUser(imgRoot + qrCodeUser);
				user.setQrCodeGroup(imgRoot + qrCodeGroup);
				
				long userId = userService.addUserAndBinding(user, binding);
				
				logger.info(">>>>> User Login: Create QR Code Img for new user");
				writeQRCodeByURL(getInviteURLByInviteCode(getInviteCodeByUserId(userId, DataConstant.INVITE_CODE_TYPE_USEAPP), DataConstant.INVITE_CODE_TYPE_USEAPP), 
						FileUtils.openOutputStream((new File(imgUploadRoot + qrCodeUser))));
				writeQRCodeByURL(getInviteURLByInviteCode(getInviteCodeByUserId(userId, DataConstant.INVITE_CODE_TYPE_JOINGROUP), DataConstant.INVITE_CODE_TYPE_JOINGROUP), 
						FileUtils.openOutputStream((new File(imgUploadRoot + qrCodeGroup))));
				
				ui.setUserId(userId);
				ui.setIsBind(StringUtil.toString(DataConstant.FALSE));
				ui.setIsFirstLogin(String.valueOf(DataConstant.TRUE));
				ui.setIsPointsLimit(0+"");
				logger.info(">>>>> User Login: Create a new user success!!");
			} else {
				logger.info(">>>>> User Login: Existed user!");
				TbWzUser user=userService.getUserById(existedBinding.getUserId());
				if(user==null){
					return new JsonErrorResult(ErrorCode.UN401, "userid is error.");
				}
				if(user.getIsDisable()==1){
					return new JsonErrorResult(ErrorCode.UN402, user_disable_image_path);
				}
				int bindStatus = userService.addLogin(existedBinding.getUserId());
				if(bindStatus==0){
					int limitNum=getLimitNum(Contans.TASK_NUM_LIMIT_POINTS+"");
					if(user.getBalance()>=limitNum){
						ui.setIsPointsLimit(1+"");
					}else{
						ui.setIsPointsLimit(0+"");
					}
				}
				ui.setUserId(user.getUserId());
				ui.setIsBind(StringUtil.toString(bindStatus));
				ui.setIsFirstLogin(String.valueOf(DataConstant.FALSE));
			}
			if("2".equals(type)){
				long id=0;
				try {
					id=Long.parseLong(request.getParameter("id"));
				} catch (Exception e) {
					// TODO: handle exception
					id=0;
				}
				
				if(id!=0){
					TbWzThirdDeviceUid thirdDeviceUid=thirdDeviceUidService.getThirdDeviceUidById(id);
                      if(thirdDeviceUid!=null){
                    	  if(thirdDeviceUid.getDeviceId()==null){
          					thirdDeviceUid.setUnionid(unionid);
          					thirdDeviceUid.setDifId(difId);
          					thirdDeviceUid.setUserId(ui.getUserId());
          					thirdDeviceUid.setDeviceId(deviceId);
          					thirdDeviceUidService.updateThirdDeviceUidById(thirdDeviceUid);
                    	  }else{
                    		  if(!deviceId.equals(thirdDeviceUid.getDeviceId())){
                    			  thirdDeviceUid=new TbWzThirdDeviceUid();
                					thirdDeviceUid.setDeviceId(deviceId);
                					thirdDeviceUid.setId(null);
                  					thirdDeviceUidService.saveThirdDeviceUid(thirdDeviceUid);
                    		  }
                    	  }
                      }
					
					
				}
				}
			
			result.setResult(ui);
			result.setRtCode(DataConstant.SUCCESS);
			logger.info(">>>>> User Login Success!! userId=" + ui.getUserId());
		} catch(Exception e) {
			logger.error(">>>>> User Login Error!!", e);
			return new JsonErrorResult(ErrorCode.UN000, "login ERROR");
		}
		
		return result;
	}
	
	/**
	 * 获取验证码(6位随机数字，失效时间为2分钟)
	 * @return JsonBasicResult
	 * @throws DataAccessException
	 */
	@RequestMapping("/getVerifyCode.do")
	@ResponseBody
	public JsonBasicResult getVerifyCode(HttpServletRequest request) throws DataAccessException {

		String userId = request.getParameter("userId");
		String phoneNumber = request.getParameter("phoneNumber");
		
		ParametersDebugUtils.debugParameters(request, "Get Verify Code", "userId", "phoneNumber");
		if (StringUtil.isEmpty(phoneNumber) || StringUtil.isEmpty(userId)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+userId+phoneNumber);
		if(!sign_flag){
			return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
		}
		if (StringUtil.isEmpty(userId)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		
		JsonBasicResult result = new JsonBasicResult();
		try {
			TbWzUser user =userService.getUserById(Long.valueOf(userId));
			if(user==null){
				return new JsonErrorResult(ErrorCode.UN401, "userid is error.");
			}
			int bindingAmount = userService.getBindingAmount(phoneNumber);
			if (bindingAmount >= 2) {
				return new JsonErrorResult(ErrorCode.RL103, "");
			}
			
			String verifyCode = RandomStringUtils.randomNumeric(6);
			
			boolean sendOK = SmsUtils.sendVerifyCodeSMS(phoneNumber, verifyCode, "2");
			if (!sendOK) {
				return new JsonErrorResult(ErrorCode.RL109, "");
			}
			user.setVerifyCode(verifyCode);
			user.setVerifyCodeDate(new Date());
			userService.modifyUser(user);
	
		} catch(Exception e) {
			logger.error(">>>>> Get Verify Code ERROR!", e);
			return new JsonErrorResult(ErrorCode.UN000, "getVerifyCode ERROR");
		}
		
		result.setRtCode(DataConstant.SUCCESS);
		return result;
	}

	/**
	 * 绑定手机号
	 * @param request
	 * @return JsonBasicResult
	 * @throws DataAccessException
	 */
	@RequestMapping("/bindPhoneNumber.do")
	@ResponseBody
	public JsonBasicResult bindPhoneNumber(HttpServletRequest request) throws DataAccessException {
		
		String phoneNumber = request.getParameter("phoneNumber");
		String code = request.getParameter("code");
		String userId = request.getParameter("userId");
		
		ParametersDebugUtils.debugParameters(request, "Bind PhoneNumber", "phoneNumber", "code", "userId");
		if (StringUtil.isEmpty(phoneNumber) || StringUtil.isEmpty(userId) || StringUtil.isEmpty(code)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+userId+phoneNumber+code);
		if(!sign_flag){
			return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
		}
		JsonBasicResult<JsonUserInfo> result = new JsonBasicResult<JsonUserInfo>();
		JsonUserInfo ui = new JsonUserInfo();
		try {
			TbWzUser currentUser = userService.getUserById(Long.valueOf(userId));
			
			// 确认验证码的合法性(是否匹配、是否超时)
			if (code.equals(currentUser.getVerifyCode())) {
				long diff = Calendar.getInstance().getTimeInMillis() - currentUser.getVerifyCodeDate().getTime();  
		        if (diff/1000 > 120) {
		        	return new JsonErrorResult(ErrorCode.RL106, "");
		        }
			} else {
				return new JsonErrorResult(ErrorCode.RL107, "");
			}
			
			TbWzUser existedUser = userService.getUserByPhone(phoneNumber);
			if (existedUser != null) { // 第二次绑定手机号 需要把余额和任务记录等转移到第一次绑定的用户里
				logger.info(">>>>> Bind PhoneNumber: Second times binding need to merge users: phone=" + phoneNumber);
				userService.modifyUsersForMerge(currentUser, existedUser);
				ui.setUserId(existedUser.getUserId());
				
			} else { // 第一次绑定手机号
				logger.info(">>>>> Bind PhoneNumber: First times binding: phone=" + phoneNumber);
				currentUser.setPhoneNumber(phoneNumber);
				currentUser.setVerifyCode(null);
				currentUser.setVerifyCodeDate(null);
				currentUser.setBindStatus(DataConstant.TRUE);
				userService.modifyUser(currentUser);
				ui.setUserId(currentUser.getUserId());
			}
	
		} catch(Exception e) {
			logger.error(">>>>> Bind PhoneNumber ERROR: phone=" + phoneNumber + ", userId=" + userId, e);
			return new JsonErrorResult(ErrorCode.UN000, "getVerifyCode ERROR");
		}
		
		logger.info(">>>>> Bind PhoneNumber Success: phone=" + phoneNumber + ", userId=" + userId);
		result.setResult(ui);
		result.setRtCode(DataConstant.SUCCESS);
		return result;
	}

	
	/**
	 * 我的金币余额
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/getMyGold.do")
	@ResponseBody
	public JsonBasicResult getMyGold(HttpServletRequest request) throws DataAccessException {
		String userId = request.getParameter("userId");
		ParametersDebugUtils.debugParameters(request, "Get MyGold", "userId");
		if (StringUtil.isEmpty(userId)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+userId);
		if(!sign_flag){
			return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
		}
		JsonBasicResult<String> result = new JsonBasicResult<String>();
		try {
			TbWzUser user=userService.getUserById(Long.valueOf(userId));
			if(user==null){
				return new JsonErrorResult(ErrorCode.UN401, "userid is error.");
			}
			result.setResult(user.getBalance()+"");
			result.setRtCode(DataConstant.SUCCESS);
		} catch(Exception e) {
			logger.error(">>>>> Get MyGold ERROR! userId=" + userId, e);
			return new JsonErrorResult(ErrorCode.UN000, "userDetail ERROR");
		}
		return result;
	}
	
	/**
	 * 我的个人资料
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/getMyProfile.do")
	@ResponseBody
	public JsonBasicResult getMyProfile(HttpServletRequest request) throws DataAccessException {

		String userId = request.getParameter("userId");
		
		ParametersDebugUtils.debugParameters(request, "Get MyProfile", "userId");
		if (StringUtil.isEmpty(userId)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+userId);
		if(!sign_flag){
			return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
		}
		JsonBasicResult<JsonUserDetail> result = new JsonBasicResult<JsonUserDetail>();
		try {
			JsonUserDetail ud = new JsonUserDetail();
			TbWzUser user = userService.getUserById(Long.valueOf(userId));
			ud.setUserId(user.getUserId());
			ud.setUsername(user.getUsername());
			ud.setIcon(user.getIcon());
			ud.setPhoneNumber(user.getPhoneNumber());
			ud.setEmail(StringUtil.toString(user.getEmail()));
			
			result.setResult(ud);
			result.setRtCode(DataConstant.SUCCESS);
			
		} catch(Exception e) {
			logger.error(">>>>> Get MyProfile ERROR! userId=" + userId, e);
			return new JsonErrorResult(ErrorCode.UN000, "getMyProfile ERROR");
		}
		
		return result;
	}
	
	/**
	 * 修改用户 名字，或邮箱 或头像
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/updateUser.do")
	@ResponseBody
	public JsonBasicResult updateName(HttpServletRequest request) throws DataAccessException {
		String userId = request.getParameter("userId");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String icon = request.getParameter("icon");
		ParametersDebugUtils.debugParameters(request, "Update User Name", "userId", "name","email","icon");
		if (StringUtil.isEmpty(userId) ) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+userId+name+email+icon);
		if(!sign_flag){
			return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
		}
		JsonBasicResult result = new JsonBasicResult();
		try {
			TbWzUser user = userService.getUserById(Long.valueOf(userId));
			if(user==null){
				return new JsonErrorResult(ErrorCode.UN401, "userid is error.");
			}
			if(!StringUtil.isBlank(icon)){
			String iconName = StringUtil.getUniqueFilename("foo.png");
			FileUtils.writeByteArrayToFile(new File(imgUploadRoot + iconName), Base64.decodeBase64(icon));
			user.setIcon(imgRoot + iconName);
			}else if(!StringUtil.isBlank(name)){
			user.setUsername(name);
			}else if(!StringUtil.isBlank(email)){
				user.setEmail(email);
				}
			userService.modifyUser(user);
			logger.info(">>>>> Update User  Success!! userId=" + userId);
			result.setRtCode(DataConstant.SUCCESS);
		} catch(Exception e) {
			logger.error("Update User Name  userId=" + userId, e);
			return new JsonErrorResult(ErrorCode.UN000, "updateName ERROR");
		}
		return result;
	}
	
	
	
	/**
	 * 签到信息(是否今日已签，签到奖励)
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/signInInfo.do")
	@ResponseBody
	public JsonBasicResult signInInfo(HttpServletRequest request) throws DataAccessException {

		String userId = request.getParameter("userId");
		
		ParametersDebugUtils.debugParameters(request, "Get Sign Info", "userId");
		if (StringUtil.isEmpty(userId)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+userId);
		if(!sign_flag){
			return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
		}
		JsonBasicResult<JsonSignInInfo> result = new JsonBasicResult<JsonSignInInfo>();
		try {
			JsonSignInInfo si = new JsonSignInInfo();
			si.setReward(getSignInReward(settingService.getCommonSetting()));
			si.setIsSigned(String.valueOf(DataConstant.FALSE));
			
			TbWzUser user = userService.getUserById(Long.valueOf(userId));
			if(userId==null){
				return new JsonErrorResult(ErrorCode.UN401, "userid is error.");
			}
			Date lastSignDate = user.getLastSignDate();
			if (lastSignDate != null && DateUtil.getDateDayDiff(lastSignDate, new Date()) == 0) {
				si.setIsSigned(String.valueOf(DataConstant.TRUE));
			}
			
			result.setResult(si);
			result.setRtCode(DataConstant.SUCCESS);
			
		} catch(Exception e) {
			logger.error(">>>>> Get Sign Info ERROR!! userId=" + userId, e);
			return new JsonErrorResult(ErrorCode.UN000, "signInInfo ERROR");
		}
		
		return result;
	}
	
	/**
	 * 签到
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/signIn.do")
	@ResponseBody
	public JsonBasicResult signIn(HttpServletRequest request) throws DataAccessException {

		String userId = request.getParameter("userId");
		
		ParametersDebugUtils.debugParameters(request, "Sign In", "userId");
		if (StringUtil.isEmpty(userId)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+userId);
		if(!sign_flag){
			return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
		}
		JsonBasicResult result = new JsonBasicResult();
		try {
			TbWzUser user = userService.getUserById(Long.valueOf(userId));
			if(userId==null){
				return new JsonErrorResult(ErrorCode.UN401, "userid is error.");
			}
			long signInAdd=Long.valueOf(getSignInReward(settingService.getCommonSetting()));
			user.setLastSignDate(new Date());
			long balance=user.getBalance() + signInAdd;
			user.setBalance(balance);
			userService.modifyUser(user);
			
			TbWzPointsLog pointsLog=new TbWzPointsLog(user.getUserId(), DataConstant.INCOME_POINTS_TYPE_SIGNIN,null , signInAdd, new Date());
			pointsLogService.savePintsLog(pointsLog);
			result.setResult(balance+"");
			result.setRtCode(DataConstant.SUCCESS);
		} catch(Exception e) {
			logger.error(">>>>> Sign In ERROR!! userId=" + userId, e);
			return new JsonErrorResult(ErrorCode.UN000, "signIn ERROR");
		}
		
		return result;
	}

}
