package com.os.wz.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import common.codrim.common.ResultJsonBean;
import common.codrim.pojo.TbWzExchangeRecord;
import common.codrim.pojo.TbWzTaskRecord;
import common.codrim.service.WzExchangeService;
import common.codrim.service.WzTaskService;
import common.codrim.util.DateUtil;
import common.codrim.util.StringUtil;
import common.codrim.wz.constant.DataConstant;
import common.codrim.wz.sql.result.ExchangeRecordInfo;
import common.codrim.wz.sql.result.TaskReviewInfo;

@Controller
@SessionAttributes({"taskReview","exchangeRecord"})
public class ReviewController extends BaseController {
	
	private static final Logger log = Logger.getLogger("yj");
	
	@Autowired
	private WzTaskService taskService;
	
	@Autowired
	private WzExchangeService exchangeService;
	
	//------------------------------------------------------ 任务审核
	@RequestMapping("/wz/review/taskReviewList.do")
	@ResponseBody
	public Map<String, Object> taskRecordList(HttpServletRequest request) throws DataAccessException{
		int startPage = Integer.parseInt(request.getParameter("page"));
		int size = Integer.parseInt(request.getParameter("rows"));
		
		log.info(">>>>> Task Review List [startPage=" + startPage + ", size=" + size + "]");
		
		String taskId = request.getParameter("taskId");
		String username = request.getParameter("username");
		String phoneNumber = request.getParameter("phoneNumber");
		String reviewStatus = request.getParameter("reviewStatus");

		TaskReviewInfo param = new TaskReviewInfo();
		param.setStepType(DataConstant.STEP_TYPE_SCREEN);
		
		if (!StringUtil.isEmpty(taskId) && !"0".equals(taskId)) {
			param.setTaskId(Long.valueOf(taskId));
		}
		if (!StringUtil.isEmpty(username)) {
			param.setUsername(username);
		}
		if (!StringUtil.isEmpty(phoneNumber)) {
			param.setPhoneNumber(phoneNumber);
		}
		if (!StringUtil.isEmpty(reviewStatus) && !"0".equals(reviewStatus)) {
			param.setReviewStatus(Integer.valueOf(reviewStatus));
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<TaskReviewInfo> list = taskService.searchTaskRecord4Review(startPage, size, param);
			int total = taskService.getTaskRecord4ReviewTotal(param);
			
			map.put("total", total);
			map.put("rows", list);
			
		} catch (Exception e) {
			log.error(">>>>> Task Review List Error: ", e);
			e.printStackTrace();
		}
		
		if (request.getSession().getAttribute("taskReview") != null) {
			request.getSession().removeAttribute("taskReview");
		}
		
		return map;
	}
	
	@RequestMapping("/wz/review/viewReviewTask.do")
	public ModelAndView viewReviewTask(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/wz/review/viewReviewTask");
		
		String recordId = request.getParameter("recordId");
		
		TaskReviewInfo taskReview = taskService.getTaskRecord4Review(Long.valueOf(recordId));
		
		mv.addObject("taskReview", taskReview);

		return mv;
	}
	
	@RequestMapping("/wz/review/initReviewTask.do")
	public ModelAndView initReviewTask(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/wz/review/reviewTask");
		
		String recordId = request.getParameter("recordId");
		
		TaskReviewInfo taskReview = taskService.getTaskRecord4Review(Long.valueOf(recordId));
		
		mv.addObject("taskReview", taskReview);

		return mv;
	}
	
	@RequestMapping("/wz/review/reviewTask.do")
	@ResponseBody
	public ResultJsonBean reviewTask(@ModelAttribute("taskReview") TaskReviewInfo taskReview,
			@RequestParam int result, SessionStatus sessionStatus) {
		ResultJsonBean rjb = new ResultJsonBean();
		rjb.setSuccess(true);
		
		try {
			TbWzTaskRecord record = new TbWzTaskRecord();
			record.setRecordId(taskReview.getRecordId());
			if (DataConstant.TRUE == result) {
				record.setReviewStatus(DataConstant.REVIEW_STATUS_APPROVED);
				record.setIncomeGold(taskReview.getIncomeGold());
				record.setLeaderIncome(taskReview.getLeaderIncome());
			} else {
				record.setReviewStatus(DataConstant.REVIEW_STATUS_DENY);
			}
			record.setReviewRemark(taskReview.getReviewRemark());
			record.setReviewDate(new Date());
			record.setReviewBy(0L);
			record.setUserId(taskReview.getUserId());
			record.setTaskId(taskReview.getTaskId());
			record.setStepId(taskReview.getStepId());

			taskService.modifyTaskRecord(record);
			sessionStatus.setComplete();

		} catch (Exception e) {
			log.error(">>>>> Review Task Error: ", e);
			rjb.setSuccess(false);
			e.printStackTrace();
		}

        sessionStatus.setComplete();
		return rjb;
	}
	
	//------------------------------------------------------ 兑换审核
	@RequestMapping("/wz/review/exchangeReviewList.do")
	@ResponseBody
	public Map<String, Object> exchangeReviewList(HttpServletRequest request) throws DataAccessException{
		int startPage = Integer.parseInt(request.getParameter("page"));
		int size = Integer.parseInt(request.getParameter("rows"));
		
		log.info(">>>>> Exchange Review List [startPage=" + startPage + ", size=" + size + "]");
		
		String username = request.getParameter("username");
		String userPhone = request.getParameter("userPhone");
		String reviewStatus = request.getParameter("reviewStatus");
		String exchangeType = request.getParameter("exchangeType");
		
		 Date exchangeStartDate =DateUtil.convertStringToDate(request.getParameter("exchangeStartDate")+" 00:00:00", DateUtil.datetimeFormat);
		 Date exchangeEndDate=DateUtil.convertStringToDate(request.getParameter("exchangeEndDate")+" 23:59:59", DateUtil.datetimeFormat);
		 Date reviewStartDate=DateUtil.convertStringToDate(request.getParameter("reviewStartDate"), DateUtil.dateFormat);
		 Date reviewEndDate=DateUtil.convertStringToDate(request.getParameter("reviewEndDate"), DateUtil.dateFormat);
		
		ExchangeRecordInfo param = new ExchangeRecordInfo();
		param.setExchangeStartDate(exchangeStartDate);
		param.setExchangeEndDate(exchangeEndDate);
		param.setReviewEndDate(reviewEndDate);
		param.setReviewStartDate(reviewStartDate);
		if (!StringUtil.isEmpty(username)) {
			param.setUsername(username);
		}
		if (!StringUtil.isEmpty(userPhone)) {
			param.setUserPhone(userPhone);
		}
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
			log.error(">>>>> Exchange Review List Error: ", e);
			e.printStackTrace();
		}
		
		if (request.getSession().getAttribute("exchangeRecord") != null) {
			request.getSession().removeAttribute("exchangeRecord");
		}
		
		return map;
	}
	
	@RequestMapping("/wz/review/viewReviewExchange.do")
	public ModelAndView viewReviewExchange(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/wz/review/viewReviewExchange");
		
		String recordId = request.getParameter("recordId");
		
		ExchangeRecordInfo exchangeRecord = exchangeService.getExchangeRecord4Review(Long.valueOf(recordId));
		
		mv.addObject("exchangeRecord", exchangeRecord);

		return mv;
	}
	
	@RequestMapping("/wz/review/initReviewExchange.do")
	public ModelAndView initReviewExchange(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/wz/review/reviewExchange");
		
		String recordId = request.getParameter("recordId");
		
		ExchangeRecordInfo exchangeRecord = exchangeService.getExchangeRecord4Review(Long.valueOf(recordId));
		
		mv.addObject("exchangeRecord", exchangeRecord);

		return mv;
	}
	
	@RequestMapping("/wz/review/reviewExchange.do")
	@ResponseBody
	public ResultJsonBean reviewExchange(@ModelAttribute("exchangeRecord") ExchangeRecordInfo exchangeRecord,
			@RequestParam int result, SessionStatus sessionStatus) {
		ResultJsonBean rjb = new ResultJsonBean();
		rjb.setSuccess(true);
		
		try {
			TbWzExchangeRecord record = new TbWzExchangeRecord();
			record.setRecordId(exchangeRecord.getRecordId());
			if (DataConstant.TRUE == result) {
				record.setReviewStatus(DataConstant.REVIEW_STATUS_APPROVED);
			} else {
				record.setReviewStatus(DataConstant.REVIEW_STATUS_DENY);
				record.setExchangeGold(exchangeRecord.getExchangeGold());
			}
			record.setReviewRemark(exchangeRecord.getReviewRemark());
			record.setReviewDate(new Date());
			record.setReviewBy(0L);
			record.setUserId(exchangeRecord.getUserId());

			exchangeService.modifyExchange(record);
			sessionStatus.setComplete();

		} catch (Exception e) {
			log.error(">>>>> Review Exchange Error: ", e);
			rjb.setSuccess(false);
			e.printStackTrace();
		}

        sessionStatus.setComplete();
		return rjb;
	}
}
