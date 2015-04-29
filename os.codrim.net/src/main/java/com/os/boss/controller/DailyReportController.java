package com.os.boss.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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

import com.os.boss.bean.ContactInfoJson;
import com.os.boss.bean.DailyReportSummaryBean;
import com.os.wz.bean.ResultJson;
import common.codrim.pojo.TbAdmin;
import common.codrim.pojo.TbChannel;
import common.codrim.pojo.TbCustomer;
import common.codrim.pojo.TbDailyReport;
import common.codrim.pojo.TbDailyReportReply;
import common.codrim.query.resultmap.DailyReport;
import common.codrim.query.resultmap.DailyReportReply;
import common.codrim.service.ChannelService;
import common.codrim.service.CustomerService;
import common.codrim.service.DailyReportService;
import common.codrim.util.DateUtil;
import common.codrim.util.StringUtil;

@Controller
@SessionAttributes("dailyReport")
public class DailyReportController {
	
	@Autowired
	private DailyReportService dailyReportService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ChannelService channelService;

	private static final Logger log = Logger.getLogger("BOSS");

	@RequestMapping("/dailyReport/list.do")
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request) throws DataAccessException{
		int startPage = Integer.parseInt(request.getParameter("page"));
		int size = Integer.parseInt(request.getParameter("rows"));
		
		String jobCategory = request.getParameter("jobCategory");
		String followUser = request.getParameter("followUser");
		String followType = request.getParameter("followType");
		String createDateFrom = request.getParameter("createDateFrom");
		String createDateTo = request.getParameter("createDateTo");
		
		log.info(">>>>> Daily Report List [startPage=" + startPage + ", size=" + size + "]");

		DailyReport param = new DailyReport();
		
		if (!StringUtil.isEmpty(jobCategory) && !"0".equals(jobCategory)) {
			param.setJobCategory(Integer.valueOf(jobCategory));
		}
		if (!StringUtil.isEmpty(followUser) && !"0".equals(followUser)) {
			param.setFollowUser(Long.valueOf(followUser));
		}
		if (!StringUtil.isEmpty(followType) && !"0".equals(followType)) {
			param.setFollowType(Integer.valueOf(followType));
		}
		if (!StringUtil.isEmpty(createDateFrom)) {
			param.setCreateDateFrom(createDateFrom);
		}
		if (!StringUtil.isEmpty(createDateTo)) {
			param.setCreateDateTo(createDateTo);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<DailyReport> list = dailyReportService.page(startPage, size, param);
			int total = dailyReportService.count(param);
			
			for (DailyReport r : list) {
				if (StringUtil.isEmpty(r.getLatestReplyBy()))
					continue;
				r.setLatestReplyStatus(r.getLatestReplyBy() + " [" + DateUtil.formatDate(r.getLatestReplyDate()) + "]");
			}
			
			map.put("total", total);
			map.put("rows", list);
			
		} catch (Exception e) {
			log.error(">>>>> Daily Report List Error: ", e);
			e.printStackTrace();
		}
		
		if (request.getSession().getAttribute("dailyReport") != null)
			request.getSession().removeAttribute("dailyReport");
		
		return map;
	}
	
	@RequestMapping("/dailyReport/initAdd.do")
	public ModelAndView initAdd(HttpServletRequest request) throws DataAccessException{
		ModelAndView mv = new ModelAndView("/dailyReport/form");
		
		DailyReport dr = new DailyReport();
		
		TbAdmin user = (TbAdmin) request.getSession().getAttribute("user");
		dr.setFollowUser(user.getId());
		dr.setFollowUsername(user.getAccount());
		
		mv.addObject("dailyReport", dr);

		return mv;
	}
	
	@RequestMapping("/dailyReport/initEdit.do")
	public ModelAndView initEdit(@RequestParam long id) throws DataAccessException{
		ModelAndView mv = new ModelAndView("/dailyReport/form");
		
		mv.addObject("dailyReport", dailyReportService.getDailyReportById(id));

		return mv;
	}
	
	@RequestMapping("/dailyReport/loadContactInfo.do")
	@ResponseBody
	public ResultJson loadContactInfo(@ModelAttribute("dailyReport") DailyReport dr,
			@RequestParam long customerId, @RequestParam int jobCategory) {
		ResultJson rjb = new ResultJson();
		rjb.setSuccess(true);
		
        try {
        	if (jobCategory == 1) { // 渠道
        		TbChannel channel = channelService.getById(customerId);
        		dr.setContactName(channel.getChannelContactName());
        		dr.setContactEmail(channel.getChannelEmail());
        		dr.setContactPhone(channel.getChannelContactPhone());
        		dr.setContactPosition(channel.getChannelContactPosition());
        		dr.setContactWx(channel.getChannelContactWx());
        		
        	} else if (jobCategory == 2) { // 商务
        		TbCustomer customer = customerService.getById(customerId);
        		dr.setContactName(customer.getContactName());
        		dr.setContactEmail(customer.getContactEmail());
        		dr.setContactPhone(customer.getContactPhone());
        		dr.setContactPosition(customer.getContactPosition());
        		dr.setContactWx(customer.getContactWx());
        	}
        	
        	rjb.setData(new ContactInfoJson(StringUtil.toString(dr.getContactName()), 
        			StringUtil.toString(dr.getContactPosition()), 
        			StringUtil.toString(dr.getContactPhone()), 
        			StringUtil.toString(dr.getContactEmail()), 
        			StringUtil.toString(dr.getContactWx())));
	        
		} catch (Exception e) {
			log.error(">>>>> DailyReport: Load Contact Info Error: ", e);
			rjb.setSuccess(false);
			e.printStackTrace();
		}
        
		return rjb;
	}
	
	@RequestMapping("/dailyReport/save.do")
	@ResponseBody
	public ResultJson save(@ModelAttribute("dailyReport") DailyReport dr,
			SessionStatus sessionStatus) {
		ResultJson rjb = new ResultJson();
		rjb.setSuccess(true);
		
		log.info(">>>>> Save Daily Report Start...");
        try {
        	TbDailyReport report = new TbDailyReport();
        	report.setFollowDetail(dr.getFollowDetail());
        	report.setFollowProblem(dr.getFollowProblem());
        	report.setFollowType(dr.getFollowType());
        	report.setFollowUser(dr.getFollowUser());
        	report.setJobCategory(dr.getJobCategory());
        	report.setSuggestion(dr.getSuggestion());
        	report.setSupport(dr.getSupport());
        	report.setContactName(dr.getContactName());
        	report.setContactEmail(dr.getContactEmail());
        	report.setContactPhone(dr.getContactPhone());
        	report.setContactPosition(dr.getContactPosition());
        	report.setContactWx(dr.getContactWx());
        	
        	if (dr.getJobCategory() != null 
        			&& (dr.getJobCategory() == 1 || dr.getJobCategory() == 2)) {
        		report.setCustomerId(dr.getCustomerId());
        	} else {
        		report.setCustomerId(0L);
        	}
        	
        	if (dr.getId() != null && dr.getId() != 0L) {
        		report.setId(dr.getId());
        		dailyReportService.modifyDailyReport(report);
        	} else {
        		report.setCreateDate(new Date());
        		dailyReportService.addDailyReport(report);
        	}
        		
	        log.info(">>>>> Save Daily Report Success!!");
	        
	        sessionStatus.setComplete();
		} catch (Exception e) {
			log.error(">>>>> Save Daily Report Error: ", e);
			rjb.setSuccess(false);
			e.printStackTrace();
		}
        
		return rjb;
	}
	
	@RequestMapping("/dailyReport/delete.do")
	@ResponseBody
	public ResultJson delete(HttpServletRequest request, 
			@RequestParam long id, @RequestParam long followUser) {
		ResultJson rjb = new ResultJson();
		rjb.setSuccess(true);
		
		log.info(">>>>> Delete Daily Report Start...");
        try {
        	TbAdmin user = (TbAdmin) request.getSession().getAttribute("user");
        	if (followUser != user.getId()) {
        		rjb.setSuccess(false);
        		rjb.setMsg("对不起, 无法删除他人的日报！");
        		return rjb;
        	}
        	
        	dailyReportService.deleteDailyReport(id);
        		
	        log.info(">>>>> Delete Daily Report Success!!");
	        
		} catch (Exception e) {
			log.error(">>>>> Delete Daily Report Error: ", e);
			rjb.setSuccess(false);
			e.printStackTrace();
		}
        
		return rjb;
	}
	
	@RequestMapping("/dailyReport/initReply.do")
	public ModelAndView initReply(HttpServletRequest request, @RequestParam long id) throws DataAccessException{
		ModelAndView mv = new ModelAndView("/dailyReport/reply");
		
		mv.addObject("reportReply", dailyReportService.getDailyReportById(id));
		
		TbAdmin user = (TbAdmin) request.getSession().getAttribute("user");
		List<DailyReportReply> replys = dailyReportService.getReplysByReport(id);
		for (DailyReportReply reply : replys) {
			reply.setReplyTime(DateUtil.formatDateTime("yyyy-MM-dd HH:mm", reply.getReplyDate()));
			reply.setMyReply(user.getId().equals(reply.getReplyBy()));
		}
		mv.addObject("replys", replys);
		
		return mv;
	}
	
	@RequestMapping("/dailyReport/addReply.do")
	@ResponseBody
	public ResultJson addReply(HttpServletRequest request, 
			@RequestParam long reportId, @RequestParam String replyMsg) {
		ResultJson rjb = new ResultJson();
		rjb.setSuccess(true);
		
        try {
        	TbDailyReportReply reply = new TbDailyReportReply();
        	reply.setReportId(reportId);
        	reply.setReplyMsg(replyMsg);
        	
        	TbAdmin user = (TbAdmin) request.getSession().getAttribute("user");
        	reply.setReplyBy(user.getId());
        	reply.setReplyDate(new Date());
        	
        	dailyReportService.addReply(reply);
        	
        	DailyReportReply r = new DailyReportReply();
        	r.setId(reply.getId());
        	r.setUsername(user.getAccount());
        	r.setReplyTime(DateUtil.formatDateTime("yyyy-MM-dd HH:mm", reply.getReplyDate()));
        	r.setReplyMsg(replyMsg);
        	rjb.setData(r);
	        
		} catch (Exception e) {
			log.error(">>>>> Add Daily Report Reply Error: ", e);
			rjb.setSuccess(false);
			e.printStackTrace();
		}
        
		return rjb;
	}
	
	@RequestMapping("/dailyReport/deleteReply.do")
	@ResponseBody
	public ResultJson deleteReply(@RequestParam long id) {
		ResultJson rjb = new ResultJson();
		rjb.setSuccess(true);
		
        try {
        	dailyReportService.deleteReply(id);
	        
		} catch (Exception e) {
			log.error(">>>>> Delete Daily Report Reply Error: ", e);
			rjb.setSuccess(false);
			e.printStackTrace();
		}
        
		return rjb;
	}
	
	@RequestMapping("/dailyReport/summary.do")
	@ResponseBody
	public Map<String, Object> summary(HttpServletRequest request) throws DataAccessException{
		
		String followUser = request.getParameter("followUser");
		String createDateFrom = request.getParameter("createDateFrom");
		String createDateTo = request.getParameter("createDateTo");
		
		DailyReport param = new DailyReport();
		
		if (!StringUtil.isEmpty(followUser) && !"0".equals(followUser)) {
			param.setFollowUser(Long.valueOf(followUser));
		}
		if (!StringUtil.isEmpty(createDateFrom)) {
			param.setCreateDateFrom(createDateFrom);
		}
		if (!StringUtil.isEmpty(createDateTo)) {
			param.setCreateDateTo(createDateTo);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Map<String, DailyReportSummaryBean> summaryMap = new HashMap<String, DailyReportSummaryBean>();
			
			// 商务-新拓展
			param.setJobCategory(2);
			param.setFollowType(1);
			for (DailyReport s : dailyReportService.getDailyReportSummary(param)) {
				String key = s.getFollowUser() + "-" + DateUtil.formatDate(s.getCreateDate());
				if (summaryMap.containsKey(key)) {
					summaryMap.get(key).setCount01(s.getReportCount());
				} else {
					DailyReportSummaryBean sb = new DailyReportSummaryBean(s.getFollowUsername(), s.getCreateDate());
					sb.setCount01(s.getReportCount());
					summaryMap.put(key, sb);
				}
			}
			
			// 商务-维护
			param.setJobCategory(2);
			param.setFollowType(3);
			for (DailyReport s : dailyReportService.getDailyReportSummary(param)) {
				String key = s.getFollowUser() + "-" + DateUtil.formatDate(s.getCreateDate());
				if (summaryMap.containsKey(key)) {
					summaryMap.get(key).setCount02(s.getReportCount());
				} else {
					DailyReportSummaryBean sb = new DailyReportSummaryBean(s.getFollowUsername(), s.getCreateDate());
					sb.setCount02(s.getReportCount());
					summaryMap.put(key, sb);
				}
			}
			
			// 渠道-新拓展
			param.setJobCategory(1);
			param.setFollowType(1);
			for (DailyReport s : dailyReportService.getDailyReportSummary(param)) {
				String key = s.getFollowUser() + "-" + DateUtil.formatDate(s.getCreateDate());
				if (summaryMap.containsKey(key)) {
					summaryMap.get(key).setCount03(s.getReportCount());
				} else {
					DailyReportSummaryBean sb = new DailyReportSummaryBean(s.getFollowUsername(), s.getCreateDate());
					sb.setCount03(s.getReportCount());
					summaryMap.put(key, sb);
				}
			}
			
			// 渠道-维护
			param.setJobCategory(1);
			param.setFollowType(3);
			for (DailyReport s : dailyReportService.getDailyReportSummary(param)) {
				String key = s.getFollowUser() + "-" + DateUtil.formatDate(s.getCreateDate());
				if (summaryMap.containsKey(key)) {
					summaryMap.get(key).setCount04(s.getReportCount());
				} else {
					DailyReportSummaryBean sb = new DailyReportSummaryBean(s.getFollowUsername(), s.getCreateDate());
					sb.setCount04(s.getReportCount());
					summaryMap.put(key, sb);
				}
			}
			
			// 渠道-投放
			param.setJobCategory(1);
			param.setFollowType(2);
			for (DailyReport s : dailyReportService.getDailyReportSummary(param)) {
				String key = s.getFollowUser() + "-" + DateUtil.formatDate(s.getCreateDate());
				if (summaryMap.containsKey(key)) {
					summaryMap.get(key).setCount05(s.getReportCount());
				} else {
					DailyReportSummaryBean sb = new DailyReportSummaryBean(s.getFollowUsername(), s.getCreateDate());
					sb.setCount05(s.getReportCount());
					summaryMap.put(key, sb);
				}
			}
			
			/*// 运营-统计
			param.setJobCategory(3);
			param.setFollowType(4);
			for (DailyReport s : dailyReportService.getDailyReportSummary(param)) {
				String key = s.getFollowUser() + "-" + DateUtil.formatDate(s.getCreateDate());
				if (summaryMap.containsKey(key)) {
					summaryMap.get(key).setCount06(s.getReportCount());
				} else {
					DailyReportSummaryBean sb = new DailyReportSummaryBean(s.getFollowUsername(), s.getCreateDate());
					sb.setCount06(s.getReportCount());
					summaryMap.put(key, sb);
				}
			}
			
			// 运营-分析
			param.setJobCategory(3);
			param.setFollowType(5);
			for (DailyReport s : dailyReportService.getDailyReportSummary(param)) {
				String key = s.getFollowUser() + "-" + DateUtil.formatDate(s.getCreateDate());
				if (summaryMap.containsKey(key)) {
					summaryMap.get(key).setCount07(s.getReportCount());
				} else {
					DailyReportSummaryBean sb = new DailyReportSummaryBean(s.getFollowUsername(), s.getCreateDate());
					sb.setCount07(s.getReportCount());
					summaryMap.put(key, sb);
				}
			}*/
			
			// 小计
			param.setJobCategory(null);
			param.setFollowType(null);
			for (DailyReport s : dailyReportService.getDailyReportSummary(param)) {
				String key = s.getFollowUser() + "-" + DateUtil.formatDate(s.getCreateDate());
				if (summaryMap.containsKey(key)) {
					summaryMap.get(key).setCountTotal(s.getReportCount());
				} else {
					DailyReportSummaryBean sb = new DailyReportSummaryBean(s.getFollowUsername(), s.getCreateDate());
					sb.setCountTotal(s.getReportCount());
					summaryMap.put(key, sb);
				}
			}
			
			// 计算其他以及汇总
			int count01Total = 0, count02Total = 0, count03Total = 0, count04Total = 0, count05Total = 0, countElseTotal = 0, countTotal = 0;
			for (String key : summaryMap.keySet()) {
				DailyReportSummaryBean sb = summaryMap.get(key);
				sb.setCountElse(sb.getCountTotal() - (sb.getCount01() + sb.getCount02() + sb.getCount03() + sb.getCount04() + sb.getCount05()));
				
				count01Total += sb.getCount01();
				count02Total += sb.getCount02();
				count03Total += sb.getCount03();
				count04Total += sb.getCount04();
				count05Total += sb.getCount05();
				countElseTotal += sb.getCountElse();
				countTotal += sb.getCountTotal();
			}
			
			List<DailyReportSummaryBean> rows = new ArrayList<DailyReportSummaryBean>(summaryMap.values());
			Collections.sort(rows, new Comparator<DailyReportSummaryBean>() {
				public int compare(DailyReportSummaryBean o1, DailyReportSummaryBean o2) {
					if (o1.getCreateDate().getTime() > o2.getCreateDate().getTime())
						return 1;
					return -1;
				}
			});
			map.put("rows", rows);
			
			DailyReportSummaryBean footer = new DailyReportSummaryBean();
			footer.setFollowUsername("<b>合计</b>");
			footer.setCount01(count01Total);
			footer.setCount02(count02Total);
			footer.setCount03(count03Total);
			footer.setCount04(count04Total);
			footer.setCount05(count05Total);
			footer.setCountElse(countElseTotal);
			footer.setCountTotal(countTotal);
			
			map.put("footer", Arrays.asList(footer));
			
		} catch (Exception e) {
			log.error(">>>>> Daily Report Summary Error: ", e);
			e.printStackTrace();
		}
		
		return map;
	}

}
