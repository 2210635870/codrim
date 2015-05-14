package common.codrim.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import common.codrim.common.SelectResultByCodition;
import common.codrim.pojo.TbWzCommonSetting;
import common.codrim.pojo.TbWzTask;
import common.codrim.pojo.TbWzTaskRecord;
import common.codrim.pojo.TbWzTaskStep;
import common.codrim.wz.dto.TaskDetail;
import common.codrim.wz.sql.result.KeyValResult;
import common.codrim.wz.sql.result.ScreenlockTask;
import common.codrim.wz.sql.result.StepReportInfo;
import common.codrim.wz.sql.result.TaskRecordInfo;
import common.codrim.wz.sql.result.TaskReportInfo;
import common.codrim.wz.sql.result.TaskReviewInfo;
import common.codrim.wz.sql.result.TaskWithGroupRatio;

public interface WzTaskService {
	
	
	
	
	
	public TaskDetail getTaskById(long taskId) throws DataAccessException;
	public void addTask(TbWzTask task, List<TbWzTaskStep> steps) throws DataAccessException;
	public void modifyTask(TbWzTask task, List<TbWzTaskStep> steps) throws DataAccessException;
	public List<TbWzTask> searchTask(Integer startPage, Integer size, TbWzTask param) throws DataAccessException;
	public int getTaskTotalAmount(TbWzTask param) throws DataAccessException;
	public List<KeyValResult> getAllTaskSelectOptions() throws DataAccessException;
	public void modifyTaskStatus(long taskId, int status) throws DataAccessException;
	public void deleteTask(long taskId) throws DataAccessException;
	public List<TaskRecordInfo> searchTaskRecord(int startPage, int size, TaskRecordInfo param) throws DataAccessException;
	public int getTaskRecordTotalAmount(TaskRecordInfo param) throws DataAccessException;
	public boolean isTaskNameExist(String taskName) throws DataAccessException;
	// 更新过期任务状态为暂停
	public int updateSuspendTasks() throws DataAccessException;
	

	public long getTodayPoints(long userId,Date date)throws DataAccessException;
	// 任务审核
	public List<TaskReviewInfo> searchTaskRecord4Review(int startPage, int size, TaskReviewInfo param) throws DataAccessException;
	public int getTaskRecord4ReviewTotal(TaskReviewInfo param) throws DataAccessException;
	public TaskReviewInfo getTaskRecord4Review(long recordId) throws DataAccessException;
	public void modifyTaskRecord(TbWzTaskRecord record) throws DataAccessException;
	
	// APP接口
	public List<TaskWithGroupRatio> searchTaskOnApp(short taskBelong,int startPage, int size, long userId) throws DataAccessException;
	public TaskWithGroupRatio getTaskDetailOnApp(long taskId, long userId) throws DataAccessException;
	public TbWzTaskStep getStepById(long stepId) throws DataAccessException;
	public TbWzTaskRecord getLastFinishedRecord(long taskId, long userId) throws DataAccessException;
	public void modifyTofinishTask(TbWzTaskRecord record, boolean isFinalStep, boolean isNeedReview) throws DataAccessException;
	public String modifyToseeMorePrice(long taskId, long userId, TbWzCommonSetting setting) throws DataAccessException;
	public List<Map<String, Object>> selectScreenlockTasks(Map<String, Object> params);
	
	
	public int getNowDayTaskNums(long userId,Date date)throws DataAccessException;
	
	
	// 报表接口
	public List<TaskReportInfo> pageTaskReportInfo(int startPage, int size, TaskReportInfo param);
	public int countTaskReportInfo(TaskReportInfo param);
	public List<StepReportInfo> getStepReportInfoListByTask(TaskReportInfo param);
	
}
