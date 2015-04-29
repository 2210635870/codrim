package com.os.wz.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.os.wz.bean.AppUploadResultJson;
import com.os.wz.util.apk.ApkInfo;
import com.os.wz.util.apk.ApkUtil;
import com.os.wz.util.apk.IconUtil;

import common.codrim.common.ResultJsonBean;
import common.codrim.common.SelectJsonResult;
import common.codrim.pojo.TbWzTask;
import common.codrim.pojo.TbWzTaskStep;
import common.codrim.service.WzTaskService;
import common.codrim.util.DateUtil;
import common.codrim.util.StringUtil;
import common.codrim.wz.constant.DataConstant;
import common.codrim.wz.converter.TaskInfoConverter;
import common.codrim.wz.converter.TaskStepConverter;
import common.codrim.wz.converter.taskDetailConverter;
import common.codrim.wz.dto.TaskDetail;
import common.codrim.wz.dto.TaskStep;
import common.codrim.wz.sql.result.TaskRecordInfo;

@Controller
@SessionAttributes("task")
public class TaskController extends BaseController {
	
	private static final Logger log = Logger.getLogger("yj");
	
	private static TaskInfoConverter taskInfoConverter = new TaskInfoConverter();
	private static taskDetailConverter taskDetailConverter = new taskDetailConverter();
	private static TaskStepConverter taskStepConverter = new TaskStepConverter();
	
	@Autowired
	private WzTaskService taskService;
	
	
	@RequestMapping("/getTaskNamesAndIds.do")
	@ResponseBody
	public List<SelectJsonResult> getTaskNamesAndIds(HttpServletRequest request){
		TbWzTask param = new TbWzTask();
			param.setTaskId(null);
			param.setTaskStatus(1);;
		Map<String, Object> map = new HashMap<String, Object>();
			List<TbWzTask> list = taskService.searchTask(null, null, param);
			 List<SelectJsonResult> results=null;
			 if(list!=null&&list.size()>0){
				 results=new ArrayList<SelectJsonResult>();
					for(TbWzTask wzTask:list){
						SelectJsonResult result=new SelectJsonResult();
						result.setId(wzTask.getTaskId());
						result.setText(wzTask.getTaskName());
						results.add(result);
					}
			 }
			 return results;

		
	}
	
	@RequestMapping("/wz/task/list.do")
	@ResponseBody
	public Map<String, Object> taskList(HttpServletRequest request) {
		int startPage = Integer.parseInt(request.getParameter("page"));
		int size = Integer.parseInt(request.getParameter("rows"));
		
		String taskStartDate = request.getParameter("taskStartDate");
		String taskEndDate = request.getParameter("taskEndDate");
		String taskId = request.getParameter("taskId");
		String taskStatus = request.getParameter("taskStatus");
		
		log.info(">>>>> TaskList [startPage=" + startPage + ", size=" + size + "]");
		log.info(">>>>> TaskList [taskStartDate=" + taskStartDate + ", taskEndDate=" + taskEndDate 
				+ ", taskId=" +  taskId + ", taskStatus=" + taskStatus + "]");
		TbWzTask param = new TbWzTask();
		if (!StringUtil.isEmpty(taskId) && !"0".equals(taskId)) {
			param.setTaskId(Long.valueOf(taskId));
		}
		if (!StringUtil.isEmpty(taskStatus) && !"0".equals(taskStatus)) {
			param.setTaskStatus(Integer.valueOf(taskStatus));
		}
		if (!StringUtil.isEmpty(taskStartDate)) {
			param.setTaskStartDate(DateUtil.parseDate(taskStartDate));
		}
		if (!StringUtil.isEmpty(taskEndDate)) {
			param.setTaskEndDate(DateUtil.parseDate(taskEndDate));
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<TbWzTask> list = taskService.searchTask(startPage, size, param);
			int total = taskService.getTaskTotalAmount(param);
			
			log.info(">>>>> TaskList [total=" + total + "]");
			
			map.put("total", total);
			map.put("rows", taskInfoConverter.toDTOs(list));
			
		} catch (Exception e) {
			log.error(">>>>> TaskList Error: ", e);
			e.printStackTrace();
		}
		
		if (request.getSession().getAttribute("task") != null)
			request.getSession().removeAttribute("task");
		
		return map;
	}
	
	@RequestMapping("/wz/task/initAdd.do")
	public ModelAndView initAdd() {
		ModelAndView mv = new ModelAndView("/wz/task/add");
		
		TaskDetail task = new TaskDetail();
		task.setTaskStatus(String.valueOf(DataConstant.TASK_STATUS_INPROGRESS));
		task.setMaxStepIndex("0");
    	task.setTaskEffect("0");
		
		for (int i = 0; i < 10; i++) {
			TaskStep step = new TaskStep();
			step.setStepType(String.valueOf(DataConstant.STEP_TYPE_COUNT));
			
			if (i == 0) {
				step.setStepDesc("下载安装");
				step.setStepType(String.valueOf(DataConstant.STEP_TYPE_DOWNLOAD));
			}
			
			task.addStep(step);
		}
		
		mv.addObject("task", task);

		return mv;
	}
	
	@RequestMapping("/wz/task/add.do")
	@ResponseBody
	public ResultJsonBean add(@ModelAttribute("task") TaskDetail task,
			SessionStatus sessionStatus) {
		ResultJsonBean rjb = new ResultJsonBean();
		rjb.setSuccess(true);
		
		log.info(">>>>> Add Task Start...");

		MultipartFile appScreen1File = task.getAppScreen1File();
		MultipartFile appScreen2File = task.getAppScreen2File();
		MultipartFile appIconFile = task.getAppIconFile();
		MultipartFile appScreenLockFile = task.getAppScreenLockFile();

        try {
        	String appScreen1FileName = StringUtil.getUniqueFilename(appScreen1File.getOriginalFilename());
        	String appScreen2FileName = StringUtil.getUniqueFilename(appScreen2File.getOriginalFilename());
        	String appScreenLockFileName = StringUtil.getUniqueFilename(appScreenLockFile.getOriginalFilename());
			String appIconFileName = StringUtil.getUniqueFilename("foo.png");

        	FileUtils.copyInputStreamToFile(appScreen1File.getInputStream(), new File(
        			imgUploadRoot + appScreen1FileName));
			FileUtils.copyInputStreamToFile(appScreen2File.getInputStream(), new File(
					imgUploadRoot + appScreen2FileName));
			FileUtils.copyInputStreamToFile(appScreenLockFile.getInputStream(), new File(
					imgUploadRoot + appScreenLockFileName));
			FileUtils.copyInputStreamToFile(appIconFile.getInputStream(), new File(
					imgUploadRoot + appIconFileName));
        	task.setAppScreen1(imgRoot + appScreen1FileName);
        	task.setAppScreen2(imgRoot + appScreen2FileName);
        	task.setAppScreenLock(imgRoot + appScreenLockFileName);
			task.setAppIcon(imgRoot +appIconFileName);
			
			TbWzTask newTask = taskDetailConverter.toPO(task);
	        List<TbWzTaskStep> steps = new ArrayList<TbWzTaskStep>();
	        for (int i = 0; i <= Integer.valueOf(task.getMaxStepIndex()); i++) {
	        	steps.add(taskStepConverter.toPO(task.getTaskSteps().get(i)));
	        }
	        
	        taskService.addTask(newTask, steps);
	        
	        log.info(">>>>> Add Task Success!!");
	        
	        sessionStatus.setComplete();
		} catch (Exception e) {
			log.error(">>>>> Add Task Error: ", e);
			rjb.setSuccess(false);
			e.printStackTrace();
		}
        
		return rjb;
	}
	
	@RequestMapping("/wz/task/changeStatus.do")
	@ResponseBody
	public ResultJsonBean changeStatus(@RequestParam long taskId, @RequestParam int status) {
		ResultJsonBean rjb = new ResultJsonBean();
		rjb.setSuccess(true);
		
		log.info(">>>>> Change Task Status Start...[taskId=" + taskId + "]");
		try{
			taskService.modifyTaskStatus(taskId, status);
			
			log.info(">>>>> Change Task Status Success!!");
		} catch (Exception e) {
			log.error(">>>>> Change Task Status Error: ", e);
			e.printStackTrace();
		}
		
		return rjb;
	}
	
	@RequestMapping("/wz/task/delete.do")
	@ResponseBody
	public ResultJsonBean delete(@RequestParam long taskId) {
		ResultJsonBean rjb = new ResultJsonBean();
		rjb.setSuccess(true);
		
		log.info(">>>>> Delete Task Start...[taskId=" + taskId + "]");
		try{
			taskService.deleteTask(taskId);
			
			log.info(">>>>> Delete Task Success!!");
		} catch (Exception e) {
			log.error(">>>>> Delete Task Error: ", e);
			e.printStackTrace();
		}

		return rjb;		
	}
	
	@RequestMapping("/wz/task/initEdit.do")
	public ModelAndView initEdit(@RequestParam long taskId) {
		ModelAndView mv = new ModelAndView("/wz/task/edit");
		
		log.info(">>>>> Init Edit Task...[taskId=" + taskId + "]");
		
		TaskDetail task = taskService.getTaskById(taskId);
		for (int i = 0; i < 9-Integer.valueOf(task.getMaxStepIndex()); i++) {
			TaskStep step = new TaskStep();
			step.setStepType(String.valueOf(DataConstant.STEP_TYPE_COUNT));
			if (i == 0)
				step.setCountInterval("0");
			
			task.addStep(step);
		}
		mv.addObject("task", task);

		return mv;
	}
	
	@RequestMapping("/wz/task/edit.do")
	@ResponseBody
	public ResultJsonBean edit(@ModelAttribute("task") TaskDetail task,
			SessionStatus sessionStatus) {
		ResultJsonBean rjb = new ResultJsonBean();
		rjb.setSuccess(true);
		
		log.info(">>>>> Edit Task Start...[taskId=" + task.getTaskId() + "]");
		
		MultipartFile appScreen1File = task.getAppScreen1File();
		MultipartFile appScreen2File = task.getAppScreen2File();
		MultipartFile appIconFile = task.getAppIconFile();
		MultipartFile appScreenLockFile = task.getAppScreenLockFile();
		
		log.info(">>>>> Edit Task appScreenLockFile: " + appScreenLockFile);

		try {
			if (!appScreen1File.isEmpty()) {
				String appScreen1FileName = StringUtil.getUniqueFilename(appScreen1File.getOriginalFilename());
				FileUtils.copyInputStreamToFile(appScreen1File.getInputStream(), new File(
	        			imgUploadRoot + appScreen1FileName));
				
				task.setAppScreen1(imgRoot + appScreen1FileName);
			}
			
        	if (!appScreen2File.isEmpty()) {
        		String appScreen2FileName = StringUtil.getUniqueFilename(appScreen2File.getOriginalFilename());
    			FileUtils.copyInputStreamToFile(appScreen2File.getInputStream(), new File(
    					imgUploadRoot + appScreen2FileName));
    			
            	task.setAppScreen2(imgRoot + appScreen2FileName);
        	}	
        	if (!appIconFile.isEmpty()) {
            	String appIconFileName = StringUtil.getUniqueFilename("foo.png");
            	FileUtils.copyInputStreamToFile(appIconFile.getInputStream(), new File(
    					imgUploadRoot + appIconFileName));
    			
    			task.setAppIcon(imgRoot +appIconFileName);
        	}	
        	if(!appScreenLockFile.isEmpty()) {
        		String appScreenLockFileName = StringUtil.getUniqueFilename(appScreenLockFile.getOriginalFilename());
        		FileUtils.copyInputStreamToFile(appScreenLockFile.getInputStream(), new File(
        				imgUploadRoot + appScreenLockFileName));
        		
        		task.setAppScreenLock(imgRoot +appScreenLockFileName);
        	}

			
        	TbWzTask task4Update = taskDetailConverter.toPO(task);
            List<TbWzTaskStep> steps = new ArrayList<TbWzTaskStep>();
            for (int i = 0; i <= Integer.valueOf(task.getMaxStepIndex()); i++) {
            	steps.add(taskStepConverter.toPO(task.getTaskSteps().get(i)));
            }
            
            taskService.modifyTask(task4Update, steps);
	        
            log.info(">>>>> Edit Task Success!!");
	        sessionStatus.setComplete();
		} catch (Exception e) {
			log.error(">>>>> Edit Task Error: ", e);
			rjb.setSuccess(false);
			e.printStackTrace();
		}

        sessionStatus.setComplete();
		return rjb;
	}
	
	@RequestMapping("/wz/task/view.do")
	public ModelAndView view(@RequestParam long taskId) {
		ModelAndView mv = new ModelAndView("/wz/task/view");
			
		TaskDetail task = taskService.getTaskById(taskId);
		for (int i = 0; i < 9-Integer.valueOf(task.getMaxStepIndex()); i++) {
			TaskStep step = new TaskStep();
			step.setStepType(String.valueOf(DataConstant.STEP_TYPE_COUNT));
			if (i == 0)
				step.setCountInterval("0");
			
			task.addStep(step);
		}
		
		mv.addObject("task4View", task);
		
		return mv;
	}
	
	@RequestMapping("/wz/task/uploadApp.do")
	@ResponseBody
	public AppUploadResultJson uploadApp(@RequestParam("appFileInput") MultipartFile appFile,
			@ModelAttribute("task") TaskDetail task) {
		AppUploadResultJson rjb = new AppUploadResultJson();
		rjb.setSuccess(true);
		
		log.info(">>>>> Upload App Start...");
		try {
			String appIconFileName = StringUtil.getUniqueFilename("foo.png");
			String appFileName = StringUtil.getUniqueFilename(appFile.getOriginalFilename());
			String appPath = appUploadRoot + appFileName;
			FileUtils.copyInputStreamToFile(appFile.getInputStream(), new File(appPath));
			
			log.info(">>>>> Start to analysis APK: " + appPath);
			ApkInfo apkInfo = new ApkUtil().getApkInfo(appPath);
			log.info(">>>>> Analysis APK Success!");
			
			log.info("#################### APK INFO Start ##########################");
			log.info(apkInfo);
			log.info("#################### APK INFO End ##########################");

			log.info(">>>>> Start Extract Icons to [" + imgUploadRoot + appIconFileName + "]");
			String icon = "";
			if (apkInfo.getApplicationIcons().containsKey(ApkInfo.APPLICATION_ICON_320)) {
				icon = apkInfo.getApplicationIcons().get(ApkInfo.APPLICATION_ICON_320);
			} else if (apkInfo.getApplicationIcons().containsKey(ApkInfo.APPLICATION_ICON_240)) {
				icon = apkInfo.getApplicationIcons().get(ApkInfo.APPLICATION_ICON_240);
			} else if (apkInfo.getApplicationIcons().containsKey(ApkInfo.APPLICATION_ICON_160)) {
				icon = apkInfo.getApplicationIcons().get(ApkInfo.APPLICATION_ICON_160);
			} else {
				icon = apkInfo.getApplicationIcons().get(ApkInfo.APPLICATION_ICON_120);
			}
			IconUtil.extractFileFromApk(appPath, icon, imgUploadRoot + appIconFileName);
			log.info(">>>>> Extract Icon Success [ICON=" + icon + "]");
			
			task.setAppIcon(imgRoot + appIconFileName);
			task.setAppUrl(appRoot + appFileName);
			task.setAppSize(String.valueOf(appFile.getSize()));
			task.setAppPname(apkInfo.getPackageName());
			
			rjb.setAppIcon(task.getAppIcon());
			rjb.setAppPname(task.getAppPname());
			
			log.info(">>>>> Upload App Success!!");
			
		} catch (Exception e) {
			log.info(e);
			rjb.setSuccess(false);
			e.printStackTrace();
		}
		
		return rjb;
	}
	
	@RequestMapping("/wz/task/taskRecordList.do")
	@ResponseBody
	public Map<String, Object> taskRecordList(HttpServletRequest request) throws DataAccessException{
		int startPage = Integer.parseInt(request.getParameter("page"));
		int size = Integer.parseInt(request.getParameter("rows"));
		
		String userId = request.getParameter("userId");
		
		log.info("Task Record List [startPage=" + startPage + ", size=" + size + "]");

		TaskRecordInfo param = new TaskRecordInfo();
		param.setUserId(Long.valueOf(userId));
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<TaskRecordInfo> list = taskService.searchTaskRecord(startPage, size, param);
			int total = taskService.getTaskRecordTotalAmount(param);
			
			map.put("total", total);
			map.put("rows", list);
			
		} catch (Exception e) {
			log.error(">>>>> Task Record List Error: ", e);
			e.printStackTrace();
		}
		
		return map;
	}
}
