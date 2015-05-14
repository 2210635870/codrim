package com.os.wz.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import common.codrim.common.ResultJsonBean;
import common.codrim.common.SelectJsonResult;
import common.codrim.pojo.TbAdmin;
import common.codrim.service.AdminService;
import common.codrim.service.ChannelService;
import common.codrim.service.WzSettingService;
import common.codrim.wz.constant.DataConstant;

@Controller
public class LoginController {
	@Autowired
	private AdminService userService;
	@Autowired
	private ChannelService channelService;
	
	@Autowired
	protected WzSettingService settingService;
	
	private static final Logger log = Logger.getLogger("BOSS");

	/**
	 * 后台登录
	 * 
	 * @author xulin
	 * @date 2014年12月1日
	 * @param request
	 * @return boolean
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	public Map login(HttpServletRequest request) throws DataAccessException{
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		TbAdmin	user= userService.login(email, password);
		log.info("----------------------------------后台登录：：：" + email);
		Map map=new HashMap();
		if (user != null) {
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute(DataConstant.SESSION_KEY_SETTING_CURRENCY, 
					settingService.getCommonSetting()); //网赚产品货币设置
			String url="";
			if(user.getType()==1){
				url="/views/channel.html";
			}else{
				url="/views/main.jsp";
			}
			map.put("res", true);
			map.put("url", url);
		
		} else {
			map.put("res", false);
		}
		return map;
	}
	@RequestMapping("/views/channel.html")
	public ModelAndView channelLogin(HttpServletRequest request) throws DataAccessException{
		return new ModelAndView("channel_");
	}
	/**
	 * 登出
	 * 
	 * @author xulin
	 * @date 2014年12月1日
	 * @param request
	 * @return boolean
	 */
	@RequestMapping("/loginOut.do")
	@ResponseBody
	public boolean loginOut(HttpServletRequest request) throws DataAccessException{
		request.getSession().removeAttribute("user");
		log.info("----------------------------------后台登出");
		return true;
	}

	/**
	 * 校验用户名字是否存在
	 * 
	 * @author xulin
	 * @date 2014年12月1日
	 * @param request
	 * @return boolean
	 */
	@RequestMapping("/validAdminName.do")
	@ResponseBody
	public boolean checkAdminName(HttpServletRequest request) throws DataAccessException{
		String name = request.getParameter("account");
		log.info("----------------------------------校验管理员名字" + name);
		return userService.checkAccount(name);
	}

	/**
	 * 获取所有管理员帐号信息
	 * 
	 * @author xulin
	 * @date 2014年12月1日
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping("/getAllAdmins.do")
	@ResponseBody
	public Map<String, Object> geTbCustomers(HttpServletRequest request) throws DataAccessException{
		List<TbAdmin> list = new ArrayList<TbAdmin>();
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
		list = userService.getUsersByPageAndAdmin(startPage, size);
		int total = userService.getTotalUsers();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		log.info("----------------------------------获取所有管理员信息");
		return map;
	}

	
	
	
	@RequestMapping("/getAdminNames.do")
	@ResponseBody
	public List<SelectJsonResult> getAdminNames(HttpServletRequest request){
		return userService.getAllTbUserName();
	}
	/**
	 * 添加管理员
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveAdmin.do")
	@ResponseBody
	public ResultJsonBean addAdmin(HttpServletRequest request) throws DataAccessException{
		ResultJsonBean jsonBean = new ResultJsonBean();
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String officeName =request.getParameter("officeName");
		String email =request.getParameter("email");
		TbAdmin user = new TbAdmin();
		user.setAccount(account);
		user.setPassword(password);
		user.setEmail(email);
		user.setOfficeName(officeName);
		user.setType(0);
		int i = userService.addTbUser(user);
		if (i > 0) {
			jsonBean.setSuccess(true);
		}
		log.info("----------------------------------添加管理员信息");
		return jsonBean;
	}

	/**
	 * 修改管理员
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/editAdmin.do")
	@ResponseBody
	public ResultJsonBean modifyAdmin(HttpServletRequest request) throws DataAccessException{
		ResultJsonBean jsonBean = new ResultJsonBean();
		long id;
		try {
			id = Long.parseLong(request.getParameter("id"));
		} catch (NumberFormatException e) {
			// TODO: handle exception
			return jsonBean;
		}
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String officeName = request.getParameter("officeName");
		TbAdmin user = new TbAdmin(id, account, password,email,officeName, 0);
		int i = userService.modifyTbUser(user);
		if (i > 0) {
			jsonBean.setSuccess(true);
		}
		log.info("----------------------------------修改客户信息");
		return jsonBean;
	}

	/**
	 * 删除管理员
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteAdmin.do")
	@ResponseBody
	public ResultJsonBean deleteAdmin(HttpServletRequest request) throws DataAccessException{
		ResultJsonBean jsonBean = new ResultJsonBean();
		long id;
		try {
			id = Long.parseLong(request.getParameter("id"));
		} catch (NumberFormatException e) {
			// TODO: handle exception
			return jsonBean;
		}
		int i = userService.deleteById(id);
		if (i > 0) {
			jsonBean.setSuccess(true);
		}
		log.info("----------------------------------删除管理员信息");
		return jsonBean;
	}

}
