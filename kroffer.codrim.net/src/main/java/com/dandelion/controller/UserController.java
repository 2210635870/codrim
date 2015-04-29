package com.dandelion.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dandelion.bean.AddressInfo;
import com.dandelion.bean.JsonUserInfo;
import com.dandelion.util.AddressUtils;
import com.dandelion.util.WeixinUtil;

import common.codrim.common.Contans;
import common.codrim.common.ResultJsonBean;
import common.codrim.pojo.TbWzBinding;
import common.codrim.pojo.TbWzSequence;
import common.codrim.pojo.TbWzThirdDeviceUid;
import common.codrim.pojo.TbWzUser;
import common.codrim.service.WzSequenceService;
import common.codrim.service.WzTaskService;
import common.codrim.service.WzThirdDeviceUidService;
import common.codrim.service.WzUserService;
import common.codrim.util.DateUtil;
import common.codrim.util.GetParametersUtil;
import common.codrim.util.StringUtil;
import common.codrim.wz.constant.DataConstant;

@Controller
public class UserController {
	private static final Logger logger = Logger.getLogger("kroffer");

	@Autowired
	private WzUserService userService;
	@Autowired
	private WzTaskService taskService;
	@Autowired
	WzThirdDeviceUidService thirdDeviceUidService;
	@Autowired
	private WeixinUtil weixinUtil;
	

	
	
	@RequestMapping("/user.do")
	@ResponseBody
	public ResultJsonBean login(HttpServletRequest request) throws DataAccessException {
		ResultJsonBean bean = new ResultJsonBean();
		GetParametersUtil.getParameters(request, logger);
		try {
			long userId=0;
			String id=request.getParameter("userId");
			if(!StringUtil.isBlank(id)){
				userId=Long.parseLong(id);
			}
			String weixinCode=request.getParameter("weixinCode");
			String difId=request.getParameter("difId");
			String unionid=request.getParameter("unionid");
			JsonUserInfo ui =new JsonUserInfo();
			if(!StringUtil.isBlank(weixinCode)&&!StringUtil.isBlank(difId)){
				TbWzThirdDeviceUid	thirdDeviceUid=null;
				if(!weixinCode.equals("null")){
                	logger.info(">>>>> weixin  使用微信api登陆"+"unionid:  "+unionid+"  difId:   "+difId);

					thirdDeviceUid=weixinUtil.getWeixinOpenUId(weixinCode,difId);
					if(thirdDeviceUid!=null&&!StringUtil.isBlank(thirdDeviceUid.getUnionid())){
						TbWzThirdDeviceUid	thirdDeviceUid_exists=thirdDeviceUidService.getThirdDeviceUidByUidAndDifId(thirdDeviceUid.getUnionid(),difId);
						if(thirdDeviceUid_exists==null){
							int i=thirdDeviceUidService.saveThirdDeviceUid(thirdDeviceUid);
							if(i>0){
							    ui.setIsBindingDeviceId(Contans.FALSE);
		                        ui.setUnionid(thirdDeviceUid.getUnionid());
		                        ui.setDifId(difId);
		                        ui.setId(thirdDeviceUid.getId());
		                    	bean.setObject(ui);
		                		bean.setSuccess(true);
		                    	logger.info(">>>>> weixin登陆：没有绑定");
		        				return bean;
							}
						}else{
							 ui.setIsBindingDeviceId(thirdDeviceUid_exists.getDeviceId()==null?Contans.FALSE:Contans.TRUE);      
		                     ui.setUnionid(thirdDeviceUid_exists.getUnionid());
		                        ui.setDifId(difId);
		                        ui.setId(thirdDeviceUid_exists.getId());
		                    	logger.info(">>>>> weixin登陆：已存在unionid");
			                     userId=thirdDeviceUid_exists.getUserId()==null?0:thirdDeviceUid_exists.getUserId();
		                     bean.setObject(ui);
		                		bean.setSuccess(true);
						}
					}else{
						logger.info(">>>>> 微信获取用户id 出错 code："+weixinCode);
						return bean;
					}
					
					
					
				}else if(!StringUtil.isBlank(unionid)){
                	logger.info(">>>>> weixin  使用页面缓存登陆"+"unionid:  "+unionid+"  difId:   "+difId);

					thirdDeviceUid=thirdDeviceUidService.getThirdDeviceUidByUidAndDifId(unionid,difId);
					if(thirdDeviceUid!=null&&!StringUtil.isBlank(thirdDeviceUid.getUnionid())){
                    	logger.info(">>>>> weixin登陆：已存在unionid");
						ui.setIsBindingDeviceId(thirdDeviceUid.getDeviceId()==null?Contans.FALSE:Contans.TRUE);      
	                     ui.setUnionid(thirdDeviceUid.getUnionid());
	                        ui.setDifId(difId);
	                        ui.setId(thirdDeviceUid.getId());
	                     userId=thirdDeviceUid.getUserId()==null?0:thirdDeviceUid.getUserId();
	                     bean.setObject(ui);
	                		bean.setSuccess(true);
					}else{
						return bean;
					}
				}
				
			
			}
			
			if(userId==0){
				return bean;
			}
			TbWzUser user = userService.getUserById(Long.valueOf(userId));
			if (user == null) { // new user
				logger.info(">>>>> 页面登陆：用户不存在");
				return bean;
			} else {
				logger.info(">>>>> 页面登陆：用户存在");
				ui.setUserId(user.getUserId());
				ui.setPoints(user.getBalance());
				long todayPoints=taskService.getTodayPoints(userId, DateUtil.getNowDateToDate(System.currentTimeMillis(), "yyyy-MM-dd"));
				ui.setTodayPoints(todayPoints);
			}
		bean.setObject(ui);
		bean.setSuccess(true);
			logger.info(">>>>>  页面登陆 Success!! userId=" + ui.getUserId());
		} catch(Exception e) {
			logger.error(">>>>>  页面登陆 Error!!", e);
						return bean;
		}
		return bean;
	}
	
	
	
	
	
	/**
	 *统计工具登陆
	 * 如果设备号存在，找到对应的用户，直接进入主页并更新最后登录时间;
	 * 如果设备号不存在，创建新用户，绑定设备号，然后进入主页并更新最后登录时间
	 * @return JsonBasicResult
	 * @throws DataAccessException
	 */
	@RequestMapping("/bind.do")
	@ResponseBody
	public ResultJsonBean bind(HttpServletRequest request) throws DataAccessException {
		ResultJsonBean bean=new ResultJsonBean();
		String deviceId = request.getParameter("deviceId");
		String devicePlat = request.getParameter("devicePlat");
		String ip = request.getParameter("ip");
		String mac = request.getParameter("mac");
		GetParametersUtil.getParameters(request, logger);
		if ( StringUtil.isEmpty(deviceId) || StringUtil.isEmpty(devicePlat)
				|| StringUtil.isEmpty(ip) || StringUtil.isEmpty(mac)) {
			return bean;
		}
		try {
			JsonUserInfo ui = new JsonUserInfo();
			TbWzBinding existedBinding = userService.getBindingByDevice(deviceId);
			if (existedBinding == null) { // new user
				logger.info(">>>>> 统计工具登陆：新用户-生成");
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
				
				TbWzBinding binding = new TbWzBinding();
				binding.setDeviceId(deviceId);
				binding.setDevicePlat(Integer.valueOf(devicePlat));
				binding.setIp(ip);
				binding.setMac(mac);
				binding.setBindingDate(new Date());
				
				// 根据IP获取地理位置等相关信息
				logger.info(">>>>> 获取地理位置信息: " + ip);
				AddressInfo address = AddressUtils.getAddress(ip);
				if (address != null) {
					binding.setCountry(address.getCountry());
					binding.setCity(address.getCity());
					binding.setRegion(address.getRegion());
					binding.setIsp(address.getIsp());
					logger.info(">>>>> 获取地理位置信息success: " 
							+ address.getCountry() + address.getCity() + address.getRegion() + address.getIsp());
				}
				long userId = userService.addUserAndBinding(user, binding);
				ui.setUserId(userId);
				logger.info(">>>>> 统计工具登陆：新用户-生成 success!!");
			} else {
				logger.info(">>>>> 统计工具登陆：用户存在");
				ui.setUserId(existedBinding.getUserId());
			}
			bean.setObject(ui);
			bean.setSuccess(true);
			logger.info(">>>>>  统计工具登陆 Success!! userId=" + ui.getUserId());
		} catch(Exception e) {
			logger.error(">>>>>  统计工具登陆 Error!!", e);
			return bean;
		}
		return bean;
	}
	
	
	
	
}
