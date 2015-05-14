package com.os.wz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import common.codrim.common.Contans;
import common.codrim.common.ResultJsonBean;
import common.codrim.pojo.TbWzBinding;
import common.codrim.pojo.TbWzPointsLog;
import common.codrim.pojo.TbWzUser;
import common.codrim.service.WzExchangeService;
import common.codrim.service.WzPointsLogService;
import common.codrim.service.WzUserService;
import common.codrim.util.StringUtil;
import common.codrim.wz.constant.DataConstant;
import common.codrim.wz.sql.in.Order;
import common.codrim.wz.sql.result.ExchangeRecordInfo;

@Controller
@SessionAttributes()
public class UserController extends BaseController {
	
	private static final Logger log = Logger.getLogger("yj");
	
	@Autowired
	private WzUserService userService;
	
	@Autowired
	private WzExchangeService exchangeService;
	
	@Autowired
	private WzPointsLogService pointsLogService;
	/**修改用户状态
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/wz/user/changeDisable.do")
	@ResponseBody
	public ResultJsonBean  changeDisable(HttpServletRequest request) throws DataAccessException{
               short isDisable=Short.parseShort(request.getParameter("isDisable"));
		long userId = Long.parseLong(request.getParameter("userId"));
		ResultJsonBean bean=new ResultJsonBean();
		TbWzUser user=new TbWzUser();
		user.setUserId(userId);
		if(isDisable==1){
			user.setIsDisable(Contans.FALSE);
		}
		if(isDisable==0){
			user.setIsDisable(Contans.TRUE);
		}
	  int i= userService.modifyUser(user);
		if(i>0){
			bean.setSuccess(true);
		}
		return bean;
	}
	
	/**积分获取记录
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/wz/user/pointsLogList.do")
	@ResponseBody
	public Map<String, Object> pointsLogList(HttpServletRequest request) throws DataAccessException{
		int startPage = 0;
		try {
			startPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			startPage = 1;
			log.error("----------------------------------传入分页参数错误startPage"
					+ startPage + ":::" + e);
		}
		int size = 0;
		try {
			size = Integer.parseInt(request.getParameter("rows"));
		} catch (NumberFormatException e) {
			size = 10;
			log.error("----------------------------------传入分页参数错误size" + size
					+ ":::" + e);
		}
		long userId = Long.parseLong(request.getParameter("userId"));
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<TbWzPointsLog> list = pointsLogService.getPointsLogList((startPage-1)*size,size,userId);
			
			
			// 邀请 和 被邀请 需要对应显示被邀请人和邀请人
			for( TbWzPointsLog item : list ) {
				if( item.getPointsType() == DataConstant.INCOME_POINTS_TYPE_INVITE_BY ) {
					long uId = getUserIdByInviteCode(item.getName(), DataConstant.INVITE_CODE_TYPE_USEAPP);
					if(uId == 0) {
						uId = getUserIdByInviteCode(item.getName(), DataConstant.INVITE_CODE_TYPE_JOINGROUP);
					}
					
					if(uId != 0) {
						TbWzUser user = userService.getUserById(uId);
						item.setName(user.getUsername());
					}
				} else if( item.getPointsType() == DataConstant.INCOME_POINTS_TYPE_INVITE ) {
					TbWzPointsLog inviteeLog = pointsLogService.selectInvaiteesByInviterLog(item);
					item.setName(inviteeLog.getName());
				}
			}
			
			int total = pointsLogService.getPointsLogTotalAmount(userId);
			map.put("total", total);
			map.put("rows", list);
		} catch (Exception e) {
			log.error(">>>>> pointsLog List Error: ", e);
			e.printStackTrace();
		}
		
		return map;
	}
	@RequestMapping("/wz/user/userList.do")
	@ResponseBody
	public Map<String, Object> userList(HttpServletRequest request) throws DataAccessException{
		int startPage = Integer.parseInt(request.getParameter("page"));
		int size = Integer.parseInt(request.getParameter("rows"));
		
		String groupId = request.getParameter("groupId");
		String userId = request.getParameter("userId");
		String username = request.getParameter("username");
		String phoneNumber = request.getParameter("phoneNumber");
		
		log.info(">>>>> User List [startPage=" + startPage + ", size=" + size + "]");

		TbWzUser param = new TbWzUser();
		
		if (!StringUtil.isEmpty(groupId)) {
			param.setGroupId(Long.valueOf(groupId));
		}
		if (!StringUtil.isEmpty(userId)) {
			param.setUserId(Long.valueOf(userId));
		}
		if (!StringUtil.isEmpty(username)) {
			param.setUsername(username);
		}
		if (!StringUtil.isEmpty(phoneNumber)) {
			param.setPhoneNumber(phoneNumber);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<TbWzUser> list = userService.searchUser(startPage, size, param, new Order("user_id", Order.DESC));
			int total = userService.getUserTotalAmount(param);
			
			map.put("total", total);
			map.put("rows", list);
			
		} catch (Exception e) {
			log.error(">>>>> User List Error: ", e);
			e.printStackTrace();
		}
		
		return map;
	}
	
	@RequestMapping("/wz/user/view.do")
	public ModelAndView view(@RequestParam long userId) {
		ModelAndView mv = new ModelAndView("/wz/user/viewUser");
			
		mv.addObject("user", userService.getUserById(Long.valueOf(userId)));
		
		return mv;
	}
	
	@RequestMapping("/wz/user/bindingList.do")
	@ResponseBody
	public Map<String, Object> bindingList(HttpServletRequest request) throws DataAccessException{
		int startPage = Integer.parseInt(request.getParameter("page"));
		int size = Integer.parseInt(request.getParameter("rows"));
		
		String userId = request.getParameter("userId");
		
		log.info(">>>>> User Binding List [startPage=" + startPage + ", size=" + size + ", userId=" + userId + "]");

		TbWzBinding param = new TbWzBinding();
		param.setUserId(Long.valueOf(userId));
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<TbWzBinding> list = userService.searchBinding(startPage, size, param);
			int total = userService.getBindingTotalAmount(param);
			
			map.put("total", total);
			map.put("rows", list);
			
		} catch (Exception e) {
			log.error(">>>>> User Binding List Error: ", e);
			e.printStackTrace();
		}
		
		return map;
	}
	
	@RequestMapping("/wz/user/exchangeList.do")
	@ResponseBody
	public Map<String, Object> exchangeList(HttpServletRequest request) throws DataAccessException{
		int startPage = Integer.parseInt(request.getParameter("page"));
		int size = Integer.parseInt(request.getParameter("rows"));
		
		String userId = request.getParameter("userId");
		
		log.info(">>>>> User Exchange List [startPage=" + startPage + ", size=" + size + ", userId=" + userId +"]");
		
		String reviewStatus = request.getParameter("reviewStatus");
		String exchangeType = request.getParameter("exchangeType");
		
		ExchangeRecordInfo param = new ExchangeRecordInfo();
		param.setUserId(Long.valueOf(userId));
		
		if (!StringUtil.isEmpty(reviewStatus) && !"0".equals(reviewStatus)) {
			param.setReviewStatus(Integer.valueOf(reviewStatus));
		}
		if (!StringUtil.isEmpty(exchangeType) && !"0".equals(exchangeType)) {
			param.setExchangeType(Integer.valueOf(exchangeType));
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<ExchangeRecordInfo> list = exchangeService.searchExchangeRecord(startPage, size, param);
			int total = exchangeService.getExchangeRecordTotalAmount(param);
			
			map.put("total", total);
			map.put("rows", list);
			
		} catch (Exception e) {
			log.error(">>>>> User Exchange List Error: ", e);
			e.printStackTrace();
		}
		
		return map;
	}

}
