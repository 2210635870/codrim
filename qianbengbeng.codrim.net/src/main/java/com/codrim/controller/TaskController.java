package com.codrim.controller;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codrim.bean.JsonBasicResult;
import com.codrim.bean.JsonErrorResult;
import com.codrim.bean.task.JsonTaskDetail;
import com.codrim.bean.task.JsonTaskInfo;
import com.codrim.bean.task.JsonTaskRecord;
import com.codrim.bean.task.JsonTaskStep;
import com.codrim.constant.ErrorCode;
import com.codrim.util.ParametersDebugUtils;

import common.codrim.common.Contans;
import common.codrim.pojo.TbWzCommonSetting;
import common.codrim.pojo.TbWzGroup;
import common.codrim.pojo.TbWzTaskRecord;
import common.codrim.pojo.TbWzTaskStep;
import common.codrim.service.WzGroupService;
import common.codrim.service.WzTaskService;
import common.codrim.util.DateUtil;
import common.codrim.util.NumberFormatUtil;
import common.codrim.util.StringUtil;
import common.codrim.wz.constant.DataConstant;
import common.codrim.wz.sql.result.StepWithUserRecord;
import common.codrim.wz.sql.result.TaskRecordInfo;
import common.codrim.wz.sql.result.TaskWithGroupRatio;

@Controller
public class TaskController extends BaseController {
	
	private static final Logger logger = Logger.getLogger("yj");
	
	@Autowired
	private WzTaskService taskService;
	@Autowired
	private WzGroupService groupService;
	/**
	 * 获取任务列表
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/getTaskList.do")
	@ResponseBody
	public JsonBasicResult getTaskList(HttpServletRequest request,HttpServletResponse response) throws DataAccessException {

		String userId = request.getParameter("userId");
		String page = request.getParameter("page");
		String type=request.getParameter("type");
		response.setCharacterEncoding("utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST,GET" );
		response.addHeader("Access-Control-Allow-Credentials", "true" );
		ParametersDebugUtils.debugParameters(request, "Task List", "userId", "page","type");
		if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(page)|| StringUtil.isEmpty(type)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+userId+page+type);
		if(!sign_flag){
			return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
		}
		JsonBasicResult taskInfoResult = new JsonBasicResult();
		try {
			TbWzCommonSetting setting = settingService.getCommonSetting();
			int limitNum=getLimitNum(Contans.TASK_NUM_LIMIT_TASK+"");
			int nowDayTaskNum=taskService.getNowDayTaskNums(Long.parseLong(userId), DateUtil.getNowDateToDate(System.currentTimeMillis(), "yyyy-MM-dd"));
			if(nowDayTaskNum>=limitNum){
				taskInfoResult.setIsLimit(1+"");
			}else{
				taskInfoResult.setIsLimit(0+"");
			}
			List<TaskWithGroupRatio> tasks = taskService.searchTaskOnApp(Short.parseShort(type),Integer.valueOf(page), 
					DataConstant.PAGESIZE_20, Long.valueOf(userId));
			TbWzGroup group=groupService.getGroupByUser(Long.parseLong(userId));
			
			List<JsonTaskInfo> result = new ArrayList<JsonTaskInfo>();
			for (TaskWithGroupRatio task : tasks) {
				JsonTaskInfo ti = new JsonTaskInfo();
				ti.setTaskId(task.getTaskId());
				ti.setTaskName(task.getTaskName());
				ti.setTaskRemark(task.getTaskRemark());
				ti.setAppIcon(task.getAppIcon());
				
				// 根据用户任务完成度来计算需要显示的金额
				// 1. 全新任务(即未完成任何一步)，显示总金额
				// 2. 部分完成，显示下一步的任务金额
				// 3. 全部完成
				int totalStepCount = task.getTotalStepCount();
				int remainStepCount = task.getRemainStepCount();
				
				if (totalStepCount != 0) {
					if (totalStepCount == remainStepCount) { //全新任务
						ti.setFinishStatus(DataConstant.TASK_FINISH_STATUS_NEW);
						if (group== null) { // 普通用户
							ti.setTaskPrice(StringUtil.toString(getUserTaskPrice(setting, task.getTaskOrigPrice())));
							
						} else { // 团队用户
							ti.setTaskPrice(StringUtil.toString(getGroupTaskPrice(setting, task.getTaskOrigPrice(), group.getLeaderPercent())));
						}
						
					} else { // 部分完成
						ti.setFinishStatus(DataConstant.TASK_FINISH_STATUS_PART);
						ti.setNextStepType(String.valueOf(task.getNextStepType()));
						if (group == null) { // 普通用户
							ti.setTaskPrice(getUserTaskStepPrice(setting, task.getTaskOrigPrice(), task.getNextStepRewardPercent(), DataConstant.GOLD));
							
						} else { // 团队用户
							ti.setTaskPrice(getGroupTaskStepPrice(setting, task.getTaskOrigPrice(), task.getNextStepRewardPercent(), task.getLeaderPercent(), DataConstant.GOLD));
						}
					}
					
				} else { // 全部完成
					ti.setFinishStatus(DataConstant.TASK_FINISH_STATUS_ALL);
					ti.setTaskPrice("0");
				}
				
				if (group == null) { // 普通用户
					ti.setTaskTarget(String.valueOf(DataConstant.TASK_TARGET_USER));
				} else { // 团队用户
					ti.setTaskTarget(String.valueOf(DataConstant.TASK_TARGET_GROUP));
				}
				if("2".equals(type)){
					if(totalStepCount!=0&&totalStepCount==remainStepCount){
						taskInfoResult.setResult(result);
						result.add(ti);
						}
				}else{
					result.add(ti);
					taskInfoResult.setResult(result);
				}
				
			}
			
			taskInfoResult.setRtCode(DataConstant.SUCCESS);
			taskInfoResult.setResult(result);
			
		} catch(Exception e) {
			logger.error(">>>>> Task List ERROR!! userId=" + userId, e);
			return new JsonErrorResult(ErrorCode.UN000, "getTaskList ERROR");
		}
		
		return taskInfoResult;
	}
	
	/**
	 * 获取任务详情
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/getTaskDetail.do")
	@ResponseBody
	public JsonBasicResult getTaskDetail(HttpServletRequest request,HttpServletResponse response) throws DataAccessException {

		String userId = request.getParameter("userId");
		String taskId = request.getParameter("taskId");
		// 用于判断是否需要添加apk全路径
		String getFull = request.getParameter("getFull");
		
		response.setCharacterEncoding("utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST,GET" );
		response.addHeader("Access-Control-Allow-Credentials", "true" );
		ParametersDebugUtils.debugParameters(request, "Get Task Detail", "userId", "taskId");
		if (StringUtil.isEmpty(taskId) || StringUtil.isEmpty(userId)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+userId+taskId);
		if(!sign_flag){
			return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
		}
		JsonBasicResult taskDetailResult = new JsonBasicResult();
		try {
		
			TbWzCommonSetting setting = settingService.getCommonSetting();
			
			JsonTaskDetail taskDetail = new JsonTaskDetail();
			TaskWithGroupRatio td = taskService.getTaskDetailOnApp(Long.valueOf(taskId), Long.valueOf(userId));
			
			taskDetail.setTaskId(Long.valueOf(td.getTaskId()));
			taskDetail.setTaskName(td.getTaskName());
			
			if( !StringUtil.isEmpty(getFull) && "y".equalsIgnoreCase(getFull) ) {
				Pattern pattern = Pattern.compile("^((http|https|ftp)://)([a-zA-Z0-9_-]+.)*");
				if( pattern.matcher(td.getAppUrl()).matches() ) {
					taskDetail.setAppURL(td.getAppUrl());
				} else {
					taskDetail.setAppURL(urlRoot+td.getAppUrl());
				}
			} else {
				taskDetail.setAppURL(td.getAppUrl());
			}
			taskDetail.setAppIcon(td.getAppIcon());
			taskDetail.setAppScreen1(td.getAppScreen1());
			taskDetail.setAppScreen2(td.getAppScreen2());
			taskDetail.setAppSize(StringUtil.readableFileSize(td.getAppSize()));
			taskDetail.setAppPname(td.getAppPname());
			taskDetail.setTaskDesc(td.getTaskDesc());
			taskDetail.setTaskRemark(td.getTaskRemark());
			String taskPrice = "0";
			if (td.getGroupId() == 0) { // 普通用户
				taskDetail.setTaskTarget(String.valueOf(DataConstant.TASK_TARGET_USER));
				taskPrice = getUserTaskPrice(setting, td.getTaskOrigPrice());
			} else { // 团队用户
				taskDetail.setTaskTarget(String.valueOf(DataConstant.TASK_TARGET_GROUP));
				taskPrice = getGroupTaskPrice(setting, td.getTaskOrigPrice(), td.getLeaderPercent());
			}
			taskDetail.setTaskPrice(StringUtil.toString(taskPrice));
			
			long currentStepId = 0; //目前到了哪一步
			boolean isFound = false;
			for (StepWithUserRecord step : td.getSteps()) {
				JsonTaskStep taskStep = new JsonTaskStep();

				taskStep.setStepId(step.getStepId());
				taskStep.setStepType(StringUtil.toString(step.getStepType()));
				taskStep.setStepDesc(step.getStepDesc());
				taskStep.setReviewStatus(StringUtil.toString(step.getReviewStatus()));
				taskStep.setFinishDate(DateUtil.formatDate(step.getFinishDate()));
				taskStep.setIsFinal(StringUtil.toString(step.getIsFinal()));
				taskStep.setStepOrder(StringUtil.toString(step.getStepOrder()));
				double rewardPercent = NumberFormatUtil.numberToPercent(step.getRewardPercent());
				taskStep.setReward(multiply(toBigDecimal(taskPrice), toBigDecimal(rewardPercent)));
				
				if (!isFound &&  step.getStepType() == DataConstant.STEP_TYPE_SCREEN 
						&& (step.getReviewStatus() == DataConstant.REVIEW_STATUS_PENDING || step.getReviewStatus() == DataConstant.REVIEW_STATUS_DENY)) {
					currentStepId = step.getStepId();
					isFound = true;
				}
				
				if (!isFound && step.getFinishDate() == null) {
					currentStepId = step.getStepId();
					isFound = true;
				}
				
				taskDetail.addStep(taskStep);
			}
			
			taskDetail.setCurrentStep(currentStepId);
			
			taskDetailResult.setRtCode(DataConstant.SUCCESS);
			taskDetailResult.setResult(taskDetail);
			
		} catch(Exception e) {
			logger.error(">>>>> Get Task Detail ERROR!! userId=" + userId + ", taskId=" + taskId, e);
			return new JsonErrorResult(ErrorCode.UN000, "getTaskDetail ERROR");
		}
		
		return taskDetailResult;
	}
	
	/**
	 * 查看更多单价所需要花费的金币
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/seeMorePriceCost.do")
	@ResponseBody
	public JsonBasicResult seeMorePriceCost(HttpServletRequest request) throws DataAccessException {
		
		JsonBasicResult<String> result = new JsonBasicResult<String>();
		try {
			TbWzCommonSetting setting = settingService.getCommonSetting();
			BigDecimal er = new BigDecimal(String.valueOf(setting.getExchangeRate()));
			BigDecimal seePriceCost = new BigDecimal(String.valueOf(setting.getSeePriceCost()));

			result.setRtCode(DataConstant.SUCCESS);
			result.setResult(seePriceCost.multiply(er).stripTrailingZeros().toPlainString());
		} catch(Exception e) {
			logger.error(">>>>> See MorePrice Cost ERROR!", e);
			return new JsonErrorResult(ErrorCode.UN000, "seeMorePriceCost ERROR");
		}
		
		return result;
	}
	
	/**
	 * 查看更多单价
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/seeMorePrice.do")
	@ResponseBody
	public JsonBasicResult seeMorePrice(HttpServletRequest request) throws DataAccessException {
		
		String userId = request.getParameter("userId");
		String taskId = request.getParameter("taskId");
		
		ParametersDebugUtils.debugParameters(request, "See MorePrice", "userId", "taskId");
		if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(taskId)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+userId+taskId);
		if(!sign_flag){
			return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
		}
		JsonBasicResult<String> result = new JsonBasicResult<String>();
		try {
			String price = taskService.modifyToseeMorePrice(Long.valueOf(taskId), Long.valueOf(userId), settingService.getCommonSetting());
			
			if (StringUtil.isEmpty(price)) {
				return new JsonErrorResult(ErrorCode.EX301, "");
			}
			
			result.setResult(price);
			result.setRtCode(DataConstant.SUCCESS);
		} catch(Exception e) {
			logger.error(">>>>> See MorePrice ERROR!! userId=" + userId + ", taskId=" + taskId, e);
			return new JsonErrorResult(ErrorCode.UN000, "seeMorePrice ERROR");
		}
		
		return result;
	}
	
	/**
	 * 获取任务步骤详情，并检查任务条件是否满足(如计时任务的时间间隔)
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/getStepDetail.do")
	@ResponseBody
	public JsonBasicResult getStepDetail(HttpServletRequest request) throws DataAccessException {

		String userId = request.getParameter("userId");
		String stepId = request.getParameter("stepId");
		
		ParametersDebugUtils.debugParameters(request, "Get Step Detail", "userId", "stepId");
		if (StringUtil.isEmpty(stepId) || StringUtil.isEmpty(userId)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+userId+stepId);
		if(!sign_flag){
			return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
		}
		JsonBasicResult<JsonTaskStep> stepDetailResult = new JsonBasicResult<JsonTaskStep>();
		try {
			TbWzTaskStep step = taskService.getStepById(Long.valueOf(stepId));
			
			JsonTaskStep jts = new JsonTaskStep();
			jts.setStepId(step.getStepId());
			jts.setStepType(StringUtil.toString(step.getStepType()));
			jts.setScreenNo(StringUtil.toString(step.getScreenNo()));
			jts.setCountDuration(StringUtil.toString(step.getCountDuration() * 60)); //s			
			jts.setIsConfirmed(String.valueOf(DataConstant.TRUE));
			
			// 检查是否满足计时任务的时间间隔条件
			if (step.getStepType() == DataConstant.STEP_TYPE_COUNT
					&& step.getCountInterval() != null && step.getCountInterval() > 0) {
				TbWzTaskRecord lastFinishedStep = taskService.getLastFinishedRecord(step.getTaskId(), Long.valueOf(userId));
				
				if (lastFinishedStep == null 
						|| DateUtil.getDateDayDiff(lastFinishedStep.getFinishDate(), new Date()) < step.getCountInterval()) {
					jts.setIsConfirmed(String.valueOf(DataConstant.FALSE));
				}
			}
			
			stepDetailResult.setRtCode(DataConstant.SUCCESS);
			stepDetailResult.setResult(jts);
			
		} catch(Exception e) {
			logger.error(">>>>> Get Step Detail ERROR!! userId=" + userId + ", stepId=" + stepId, e);
			return new JsonErrorResult(ErrorCode.UN000, "getStepDetail ERROR");
		}
		
		return stepDetailResult;
	}
	
	
	/**
	 * 完成任务
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/finishTask.do")
	@ResponseBody
	public JsonBasicResult finishTask(HttpServletRequest request) throws DataAccessException {

		String userId = request.getParameter("userId");
		String stepId = request.getParameter("stepId");
		
		
		ParametersDebugUtils.debugParameters(request, "Finish Task", "userId", "stepId", "screenTypes","appScreens");
		if (StringUtil.isEmpty(stepId) || StringUtil.isEmpty(userId)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+userId+stepId+request.getParameter("screenTypes")+request.getParameter("appScreens"));
		if(!sign_flag){
			return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
		}
		JsonBasicResult stepDetailResult = new JsonBasicResult();
		try {
			TbWzTaskStep step = taskService.getStepById(Long.valueOf(stepId));
			TbWzTaskRecord record = new TbWzTaskRecord();
			record.setTaskId(step.getTaskId());
			record.setStepId(Long.valueOf(stepId));
			record.setUserId(Long.valueOf(userId));
			record.setStepType(step.getStepType());
			
			boolean isNeedReview = (step.getStepType() == DataConstant.STEP_TYPE_SCREEN);
			if (isNeedReview) { // 截图任务需要审核
				record.setReviewStatus(DataConstant.REVIEW_STATUS_PENDING);
				
				String[] screenTypes = request.getParameter("screenTypes").split("\\|");
				String[] appScreensBase64 = request.getParameter("appScreens").split("\\|");
				if (appScreensBase64.length != step.getScreenNo()) {
					return new JsonErrorResult(ErrorCode.UN000, "finishTask ERROR: no enough imgs!");
				}
				
				logger.debug(">>>>> Finish Task: needReview=" + isNeedReview + ", screen No.=" + step.getScreenNo());
				
				if (step.getScreenNo() == 1) {
					String appScreen1Name = StringUtil.getUniqueFilenameByImgType(screenTypes[0]);
					FileUtils.writeByteArrayToFile(new File(imgUploadRoot + appScreen1Name), Base64.decodeBase64(appScreensBase64[0]));
					record.setAppScreen1(imgRoot + appScreen1Name);
					
				} else if (step.getScreenNo() == 2) {
					String appScreen1Name = StringUtil.getUniqueFilenameByImgType(screenTypes[0]);
					FileUtils.writeByteArrayToFile(new File(imgUploadRoot + appScreen1Name), Base64.decodeBase64(appScreensBase64[0]));
					record.setAppScreen1(imgRoot + appScreen1Name);
					
					String appScreen2Name = StringUtil.getUniqueFilenameByImgType(screenTypes[1]);
					FileUtils.writeByteArrayToFile(new File(imgUploadRoot + appScreen2Name), Base64.decodeBase64(appScreensBase64[1]));
					record.setAppScreen2(imgRoot + appScreen2Name);
					
				} else if (step.getScreenNo() == 3) {
					String appScreen1Name = StringUtil.getUniqueFilenameByImgType(screenTypes[0]);
					FileUtils.writeByteArrayToFile(new File(imgUploadRoot + appScreen1Name), Base64.decodeBase64(appScreensBase64[0]));
					record.setAppScreen1(imgRoot + appScreen1Name);
					
					String appScreen2Name = StringUtil.getUniqueFilenameByImgType(screenTypes[1]);
					FileUtils.writeByteArrayToFile(new File(imgUploadRoot + appScreen2Name), Base64.decodeBase64(appScreensBase64[1]));
					record.setAppScreen2(imgRoot + appScreen2Name);
					
					String appScreen3Name = StringUtil.getUniqueFilenameByImgType(screenTypes[2]);
					FileUtils.writeByteArrayToFile(new File(imgUploadRoot + appScreen3Name), Base64.decodeBase64(appScreensBase64[2]));
					record.setAppScreen3(imgRoot + appScreen3Name);
					
				} else if (step.getScreenNo() == 4) {
					String appScreen1Name = StringUtil.getUniqueFilenameByImgType(screenTypes[0]);
					FileUtils.writeByteArrayToFile(new File(imgUploadRoot + appScreen1Name), Base64.decodeBase64(appScreensBase64[0]));
					record.setAppScreen1(imgRoot + appScreen1Name);
					
					String appScreen2Name = StringUtil.getUniqueFilenameByImgType(screenTypes[1]);
					FileUtils.writeByteArrayToFile(new File(imgUploadRoot + appScreen2Name), Base64.decodeBase64(appScreensBase64[1]));
					record.setAppScreen2(imgRoot + appScreen2Name);
					
					String appScreen3Name = StringUtil.getUniqueFilenameByImgType(screenTypes[2]);
					FileUtils.writeByteArrayToFile(new File(imgUploadRoot + appScreen3Name), Base64.decodeBase64(appScreensBase64[2]));
					record.setAppScreen3(imgRoot + appScreen3Name);
					
					String appScreen4Name = StringUtil.getUniqueFilenameByImgType(screenTypes[3]);
					FileUtils.writeByteArrayToFile(new File(imgUploadRoot + appScreen4Name), Base64.decodeBase64(appScreensBase64[3]));
					record.setAppScreen4(imgRoot + appScreen4Name);
					
				} else if (step.getScreenNo() == 5) {
					String appScreen1Name = StringUtil.getUniqueFilenameByImgType(screenTypes[0]);
					FileUtils.writeByteArrayToFile(new File(imgUploadRoot + appScreen1Name), Base64.decodeBase64(appScreensBase64[0]));
					record.setAppScreen1(imgRoot + appScreen1Name);
					
					String appScreen2Name = StringUtil.getUniqueFilenameByImgType(screenTypes[1]);
					FileUtils.writeByteArrayToFile(new File(imgUploadRoot + appScreen2Name), Base64.decodeBase64(appScreensBase64[1]));
					record.setAppScreen2(imgRoot + appScreen2Name);
					
					String appScreen3Name = StringUtil.getUniqueFilenameByImgType(screenTypes[2]);
					FileUtils.writeByteArrayToFile(new File(imgUploadRoot + appScreen3Name), Base64.decodeBase64(appScreensBase64[2]));
					record.setAppScreen3(imgRoot + appScreen3Name);
					
					String appScreen4Name = StringUtil.getUniqueFilenameByImgType(screenTypes[3]);
					FileUtils.writeByteArrayToFile(new File(imgUploadRoot + appScreen4Name), Base64.decodeBase64(appScreensBase64[3]));
					record.setAppScreen4(imgRoot + appScreen4Name);
					
					String appScreen5Name = StringUtil.getUniqueFilenameByImgType(screenTypes[4]);
					FileUtils.writeByteArrayToFile(new File(imgUploadRoot + appScreen5Name), Base64.decodeBase64(appScreensBase64[4]));
					record.setAppScreen5(imgRoot + appScreen5Name);
				}
			} else {
				record.setReviewStatus(DataConstant.REVIEW_STATUS_APPROVED);
			}
			
			// 根据用户类型计算收益
			TbWzCommonSetting setting = settingService.getCommonSetting();
			TaskWithGroupRatio td = taskService.getTaskDetailOnApp(step.getTaskId(), Long.valueOf(userId));
			
			record.setFinishDate(new Date());
			if (td.getGroupId() == 0) { // 普通用户
				record.setIncomeGold(Long.valueOf(getUserTaskStepPrice(setting, td.getTaskOrigPrice(), step.getRewardPercent(), DataConstant.GOLD)));
				record.setIncomeMoney(Double.valueOf(getUserTaskStepPrice(setting, td.getTaskOrigPrice(), step.getRewardPercent(), DataConstant.YUAN)));
				record.setLeaderIncome(0L);
				taskService.modifyTofinishTask(record, (step.getIsFinal() == DataConstant.TRUE), isNeedReview);
				
			} else { // 团队用户
				record.setIncomeGold(Long.valueOf(getGroupTaskStepPrice(setting, td.getTaskOrigPrice(), step.getRewardPercent(), td.getLeaderPercent(), DataConstant.GOLD)));
				
				String userIncome = getGroupTaskStepPrice(setting, td.getTaskOrigPrice(), step.getRewardPercent(), td.getLeaderPercent(), DataConstant.YUAN);
				String leaderIncome = getGroupTaskStepPrice4Leader(setting, td.getTaskOrigPrice(), step.getRewardPercent(), td.getLeaderPercent(), DataConstant.YUAN);
				record.setIncomeMoney(Double.valueOf(toBigDecimal(userIncome).add(toBigDecimal(leaderIncome)).stripTrailingZeros().toPlainString()));
				record.setLeaderIncome(Long.valueOf(getGroupTaskStepPrice4Leader(setting, td.getTaskOrigPrice(), step.getRewardPercent(), td.getLeaderPercent(), DataConstant.GOLD)));
				
				taskService.modifyTofinishTask(record, (step.getIsFinal() == DataConstant.TRUE), isNeedReview);
			}
			
			logger.info(">>>>> Finish Task Success!! userId=" + userId + ", stepId=" + stepId);
			stepDetailResult.setRtCode(DataConstant.SUCCESS);
			
			stepDetailResult.setResult(record.getIncomeGold()+"");

			
		} catch(Exception e) {
			logger.error(">>>>> Finish Task ERROR!! userId=" + userId + ", stepId=" + stepId, e);
			return new JsonErrorResult(ErrorCode.UN000, "finishTask ERROR");
		}
		
		return stepDetailResult;
	}
	
	/**
	 * 任务记录列表
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/taskRecords.do")
	@ResponseBody
	public JsonBasicResult taskRecords(HttpServletRequest request) throws DataAccessException {
		
		String userId = request.getParameter("userId");
		String page = request.getParameter("page");
		
		ParametersDebugUtils.debugParameters(request, "Get Task Records", "userId", "page");
		if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(page)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+userId+page);
		if(!sign_flag){
			return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
		}
		JsonBasicResult<List<JsonTaskRecord>> result = new JsonBasicResult<List<JsonTaskRecord>>();
		try {
			TaskRecordInfo param = new TaskRecordInfo();
			param.setUserId(Long.valueOf(userId));
			param.setRealFinished(true);
			List<TaskRecordInfo> records = taskService.searchTaskRecord(Integer.valueOf(page), DataConstant.PAGESIZE_20, param);
			
			List<JsonTaskRecord> trs = new ArrayList<JsonTaskRecord>();
			if (records != null && records.size() > 0) {
				for (TaskRecordInfo record : records) {
					JsonTaskRecord tr = new JsonTaskRecord();
					tr.setRecordId(record.getRecordId());
					tr.setFinshDate(DateUtil.formatDateTime("MM-dd", record.getFinishDate()));
					tr.setIncomeGold(StringUtil.toString(record.getIncomeGold()));
					tr.setStepDesc(record.getStepDesc());
					tr.setTaskName(record.getTaskName());
					
					trs.add(tr);
				}
			}
			
			result.setResult(trs);
			result.setRtCode(DataConstant.SUCCESS);
		} catch(Exception e) {
			logger.error(">>>>> Get Task Records ERROR!! userId=" + userId, e);
			return new JsonErrorResult(ErrorCode.UN000, "taskRecords ERROR");
		}
		
		return result;
	}
}
