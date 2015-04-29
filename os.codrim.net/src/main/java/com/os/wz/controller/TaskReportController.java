package com.os.wz.controller;

import java.math.BigDecimal;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import common.codrim.service.WzTaskService;
import common.codrim.util.NumberFormatUtil;
import common.codrim.util.StringUtil;
import common.codrim.wz.sql.result.StepReportInfo;
import common.codrim.wz.sql.result.TaskReportInfo;

@Controller
@SessionAttributes()
public class TaskReportController extends BaseController {
	
	private static final Logger log = Logger.getLogger("yj");
	
	@Autowired
	private WzTaskService taskService;
	
	@RequestMapping("/wz/task/taskReportList.do")
	@ResponseBody
	public Map<String, Object> taskReportList(HttpServletRequest request) throws DataAccessException{
		
		String taskId = request.getParameter("taskId");
		String dateFrom = request.getParameter("dateFrom");
		String dateTo = request.getParameter("dateTo");

		TaskReportInfo param = new TaskReportInfo();
		
		if (!StringUtil.isEmpty(taskId) && !"0".equals(taskId)) {
			param.setTaskId(Long.valueOf(taskId));
		}
		if (!StringUtil.isEmpty(dateFrom)) {
			param.setFinishDateFrom(dateFrom);
		}
		if (!StringUtil.isEmpty(dateTo)) {
			param.setFinishDateTo(dateTo);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<TaskReportInfo> list = taskService.pageTaskReportInfo(1, 20, param);
			
			//Map<String, List<TaskReportInfo>> reportGroups = new HashMap<String, List<TaskReportInfo>>();
			
			int totalEffect = 0;
			BigDecimal totalCost = toBigDecimal(0);
			BigDecimal totalIncome = toBigDecimal(0);
			BigDecimal totalProfit = toBigDecimal(0);
			for (TaskReportInfo report : list) {
				double income = Double.valueOf(multiply(toBigDecimal(report.getTaskOrigPrice()), toBigDecimal(report.getEffect())));
				double profit = Double.valueOf(subtract(toBigDecimal(income), toBigDecimal(report.getCost())));
				double profitRate = (income == 0L? 0 : Double.valueOf(divide(toBigDecimal(profit), toBigDecimal(income))));
				
				report.setIncome(income);
				report.setProfit(profit);
				report.setProfitRate(profitRate);
				
				totalCost = totalCost.add(toBigDecimal(report.getCost()));
				totalIncome = totalIncome.add(toBigDecimal(report.getIncome()));
				totalProfit = totalProfit.add(toBigDecimal(report.getProfit()));
				totalEffect += report.getEffect();
				
				/*String taskName = report.getTaskName();
				if (reportGroups.containsKey(taskName)) {
					reportGroups.get(taskName).add(report);
				} else {
					List<TaskReportInfo> gr = new ArrayList<TaskReportInfo>();
					gr.add(report);
					reportGroups.put(taskName, gr);
				}*/
			}
			
			map.put("rows", list);
			
			TaskReportInfo footer = new TaskReportInfo();
			footer.setTaskName("<b>合计</b>");
			footer.setEffect(totalEffect);
			footer.setCost(Double.valueOf(totalCost.stripTrailingZeros().toPlainString()));
			footer.setIncome(Double.valueOf(totalIncome.stripTrailingZeros().toPlainString()));
			footer.setProfit(Double.valueOf(totalProfit.stripTrailingZeros().toPlainString()));
			map.put("footer", Arrays.asList(footer));
			
			// construct HighCharts data
			/*for (String taskName : reportGroups.keySet()) {
				Series series = new Series();
				series.setName(taskName);
				for (TaskReportInfo gr : reportGroups.get(taskName)) {
					Element e = new Element(year, month, day, gr.getEffect());
					series.addElement(e);
				}
			}*/
			
		} catch (Exception e) {
			log.error(">>>>> Task Report Error: ", e);
			e.printStackTrace();
		}
		
		return map;
	}
	
	@RequestMapping("/wz/task/viewReportDetail.do")
	public ModelAndView viewReportDetail(HttpServletRequest request) throws DataAccessException{
		ModelAndView mv = new ModelAndView("/wz/report/viewReportDetail");
		
		String taskId = request.getParameter("taskId");
		String finishDate = request.getParameter("finishDate");
		
		log.info(">>>>> Task Report List [taskId=" + taskId + ", finishDate=" + finishDate + "]");

		TaskReportInfo param = new TaskReportInfo();
		param.setTaskId(Long.valueOf(taskId));
		param.setFinishDateFrom(finishDate);
		param.setFinishDateTo(finishDate);
		
		try {
			TaskReportInfo reportDetail = taskService.pageTaskReportInfo(1, 1, param).get(0);
			List<StepReportInfo> stepReports = taskService.getStepReportInfoListByTask(param);
			
			int stepEffectTotal = 0;
			for (StepReportInfo stepDetail : stepReports) {
				double rp = NumberFormatUtil.numberToPercent(stepDetail.getRewardPercent());
				stepDetail.setStepPrice(Double.valueOf(multiply(toBigDecimal(stepDetail.getTaskOrigPrice()), toBigDecimal(rp))));
				
				if (stepDetail.getStepCost() == null)
					stepDetail.setStepCost(0D);
				
				stepEffectTotal += stepDetail.getStepEffect();
			}
			
			reportDetail.setStepEffectTotal(stepEffectTotal);
			reportDetail.setStepReports(stepReports);
			
			mv.addObject("taskDetail", reportDetail);
			
		} catch (Exception e) {
			log.error(">>>>> View Report Detail Error: ", e);
			e.printStackTrace();
		}
		
		
		return mv;
	}
}
