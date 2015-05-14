package common.codrim.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.dao.TbWzCommonSettingMapper;
import common.codrim.dao.TbWzGroupMapper;
import common.codrim.dao.TbWzGroupTaskRatioMapper;
import common.codrim.dao.TbWzNewsMapper;
import common.codrim.dao.TbWzPointsLogMapper;
import common.codrim.dao.TbWzTaskMapper;
import common.codrim.dao.TbWzTaskRecordMapper;
import common.codrim.dao.TbWzTaskStepMapper;
import common.codrim.dao.TbWzUserMapper;
import common.codrim.pojo.TbWzCommonSetting;
import common.codrim.pojo.TbWzGroup;
import common.codrim.pojo.TbWzNews;
import common.codrim.pojo.TbWzPointsLog;
import common.codrim.pojo.TbWzTask;
import common.codrim.pojo.TbWzTaskRecord;
import common.codrim.pojo.TbWzTaskStep;
import common.codrim.pojo.TbWzUser;
import common.codrim.service.WzTaskService;
import common.codrim.util.NumberFormatUtil;
import common.codrim.util.StringUtil;
import common.codrim.wz.constant.DataConstant;
import common.codrim.wz.converter.TaskStepConverter;
import common.codrim.wz.converter.taskDetailConverter;
import common.codrim.wz.dto.TaskDetail;
import common.codrim.wz.sql.result.KeyValResult;
import common.codrim.wz.sql.result.ScreenlockTask;
import common.codrim.wz.sql.result.StepReportInfo;
import common.codrim.wz.sql.result.StepWithUserRecord;
import common.codrim.wz.sql.result.TaskRecordInfo;
import common.codrim.wz.sql.result.TaskReportInfo;
import common.codrim.wz.sql.result.TaskReviewInfo;
import common.codrim.wz.sql.result.TaskWithGroupRatio;

@Service
@Transactional
public class WzTaskServiceImpl implements WzTaskService {
	
	@Autowired
	private TbWzTaskMapper taskDao;
	
	@Autowired
	private TbWzTaskStepMapper stepDao;
	
	@Autowired
	private TbWzTaskRecordMapper taskRecordDao;
	
	@Autowired
	private TbWzUserMapper userDao;
	
	@Autowired
	private TbWzGroupMapper groupDao;
	
	@Autowired
	private TbWzGroupTaskRatioMapper ratioDao;

	@Autowired
	protected TbWzPointsLogMapper pointsLogDao;
	
	@Autowired
	private TbWzNewsMapper newsDao;
	
	@Autowired
	private TbWzCommonSettingMapper commonSettingDao;
	
	private static taskDetailConverter taskDetailConverter = new taskDetailConverter();
	private static TaskStepConverter taskStepConverter = new TaskStepConverter();

	
	@Override
	public TaskDetail getTaskById(long taskId) throws DataAccessException {
		TbWzTask task = taskDao.selectByPrimaryKey(taskId);
		List<TbWzTaskStep> steps = stepDao.getStepsByTask(taskId);
		
		TaskDetail taskDetail = taskDetailConverter.toDTO(task);
		
		int maxStepIndex = steps.size() - 1;
		taskDetail.setMaxStepIndex(String.valueOf(maxStepIndex));
		for (TbWzTaskStep step : steps) {
			taskDetail.addStep(taskStepConverter.toDTO(step));
		}
		
		return taskDetail;
	}
	
	@Override
	public List<TbWzTask> searchTask(Integer startPage, Integer size, TbWzTask param) throws DataAccessException {
		if(startPage==null||size==null){
			return taskDao.page(null, null, param);
		}else{
			int start = (startPage-1) * size;	
			return taskDao.page(start, size, param);
		}
	}

	@Override
	public int getTaskTotalAmount(TbWzTask param) throws DataAccessException {
		return taskDao.count(param);
	}
	
	@Override
	public List<TaskWithGroupRatio> searchTaskOnApp(short taskBelong,int startPage, int size, long userId) throws DataAccessException {
		int start = (startPage-1) * size;	
		return taskDao.pageWithGroupRatio( taskBelong,start, size, userId);
	}
	
	

	@Override
	public List<Map<String, Object>> selectScreenlockTasks(Map<String, Object> params) {
		return taskDao.selectScreenlockTasks(params);
	}

	@Override
	public TaskWithGroupRatio getTaskDetailOnApp(long taskId, long userId) throws DataAccessException {
		TaskWithGroupRatio task = taskDao.getTask(taskId, userId);
		
		List<StepWithUserRecord> steps = stepDao.getStepsByTaskAndUser(taskId, userId);
		task.setSteps(steps);
		
		return task;
	}

	@Override
	public void addTask(TbWzTask task, List<TbWzTaskStep> steps) throws DataAccessException {
		taskDao.insert(task);
		long taskId = task.getTaskId();
		
		int i = 0;
		for (TbWzTaskStep step : steps) {
			step.setTaskId(taskId);
			
			if (++i == steps.size()) {
				step.setIsFinal(DataConstant.TRUE);
			} else {
				step.setIsFinal(DataConstant.FALSE);
			}
			
			stepDao.insert(step);
		}
		
		ratioDao.batchInsertByGroup(taskId);
	}
	
	@Override
	public void modifyTask(TbWzTask task, List<TbWzTaskStep> steps) throws DataAccessException {
		taskDao.updateByPrimaryKeySelective(task);
		
		int i = 0;
		for (TbWzTaskStep step : steps) {
			if (++i == steps.size()) {
				step.setIsFinal(DataConstant.TRUE);
			} else {
				step.setIsFinal(DataConstant.FALSE);
			}
			
			if (step.getStepId() != null && step.getStepId() != 0L) {
				stepDao.updateByPrimaryKeySelective(step);
			} else {
				step.setTaskId(task.getTaskId());
				stepDao.insert(step);
			}
		}
	}

	@Override
	public List<KeyValResult> getAllTaskSelectOptions() throws DataAccessException {
		return taskDao.getAllTaskSelectOptions();
	}

	@Override
	public void modifyTaskStatus(long taskId, int status) throws DataAccessException {
		taskDao.changeStatus(taskId, status);
	}

	@Override
	public void deleteTask(long taskId) throws DataAccessException {
		taskDao.deleteByPrimaryKey(taskId);
		stepDao.deleteByTaskId(taskId);
	}

	@Override
	public TbWzTaskStep getStepById(long stepId) throws DataAccessException {
		return stepDao.selectByPrimaryKey(stepId);
	}

	@Override
	public TbWzTaskRecord getLastFinishedRecord(long taskId, long userId) throws DataAccessException {
		return taskRecordDao.getLastFinishedRecord(taskId, userId);
	}

	@Override
	public void modifyTofinishTask(TbWzTaskRecord record, boolean isFinalStep, boolean isNeedReview)
			throws DataAccessException {
		
		TbWzTaskRecord existedRecord = taskRecordDao.getRecordByStepAndUser(record.getStepId(), record.getUserId());
		if (existedRecord == null) {
			
			// 对于不需要审查的任务直接结算收益并增加效果
			if (!isNeedReview) {
				TbWzUser user = userDao.selectByPrimaryKey(record.getUserId());
				
				// 个人收益
				user.setBalance(user.getBalance() + record.getIncomeGold());
				
				// 团队收益及效果
				if (user.getGroupId()!= null && user.getGroupId() != 0L) {
					TbWzGroup group = groupDao.selectByPrimaryKey(user.getGroupId());
					group.setGroupIncome(group.getGroupIncome() + record.getIncomeGold());
					
					if (isFinalStep) {
						group.setGroupEffect(group.getGroupEffect() + 1);
					}
					
					groupDao.updateByPrimaryKeySelective(group);
					
					// 结算收益给团队Leader
					if (record.getLeaderIncome() != 0L) {
						TbWzUser leader = userDao.selectByPrimaryKey(group.getGroupLeader());
						leader.setBalance(leader.getBalance() + record.getLeaderIncome());
						userDao.updateByPrimaryKeySelective(leader);
						TbWzPointsLog pointsLog=new TbWzPointsLog(leader.getUserId(), DataConstant.INCOME_POINTS_TYPE_GROUP, record.getUserId()+"",record.getLeaderIncome(), new Date());
		    			pointsLogDao.insertSelective(pointsLog);
					}
				}

				// 效果
				user.setStepEffect(user.getStepEffect() + 1);
				if (isFinalStep) {
					user.setUserEffect(user.getUserEffect() + 1);
					TbWzTask task = taskDao.selectByPrimaryKey(record.getTaskId());
					task.setTaskEffect(task.getTaskEffect() + 1);
					taskDao.updateByPrimaryKeySelective(task);
				}
				
				userDao.updateByPrimaryKeySelective(user);

        		TbWzPointsLog pointsLog=new TbWzPointsLog(user.getUserId(), DataConstant.INCOME_POINTS_TYPE_TASK, record.getTaskId()+"",record.getIncomeGold(), new Date());
    			pointsLogDao.insertSelective(pointsLog);
				
			}
			
			taskRecordDao.insert(record);
			
		} else { //截图任务审核未通过重新上传并重新提交审核
			if(isNeedReview) {
				record.setReviewBy(0L);
				record.setReviewDate(null);
				record.setReviewRemark("");
				record.setReviewStatus(DataConstant.REVIEW_STATUS_PENDING);
				record.setRecordId(existedRecord.getRecordId());
			}
			taskRecordDao.updateByPrimaryKeySelective(record);
		}
	}

	@Override
	public List<TaskRecordInfo> searchTaskRecord(int startPage, int size, TaskRecordInfo param) throws DataAccessException {
		int start = (startPage-1) * size;
		return taskRecordDao.page(start, size, param);
	}

	@Override
	public int getTaskRecordTotalAmount(TaskRecordInfo param) throws DataAccessException {
		return taskRecordDao.count(param);
	}

	@Override
	public String modifyToseeMorePrice(long taskId, long userId, TbWzCommonSetting setting) throws DataAccessException {
		BigDecimal ec = new BigDecimal(String.valueOf(setting.getExchangeRate()));
		long seePriceCost = new BigDecimal(String.valueOf(setting.getSeePriceCost())).multiply(ec).longValue();
		
		TbWzUser user = userDao.selectByPrimaryKey(userId);
		if (user.getBalance() < seePriceCost)
			return "";
		
		TbWzTask task = taskDao.selectByPrimaryKey(taskId);
		double cp = NumberFormatUtil.numberToPercent(100 - setting.getUserCodrimPercent());
		
		BigDecimal taskPrice = new BigDecimal(String.valueOf(task.getTaskOrigPrice()))
				.multiply(new BigDecimal(String.valueOf(cp)))
				.multiply(new BigDecimal(String.valueOf(setting.getExchangeRate())));
		
		user.setBalance(user.getBalance() - seePriceCost);
		userDao.updateByPrimaryKeySelective(user);
		
		return taskPrice.stripTrailingZeros().toPlainString();
	}

	@Override
	public List<TaskReviewInfo> searchTaskRecord4Review(int startPage, int size, TaskReviewInfo param) throws DataAccessException {
		int start = (startPage-1) * size;
		return taskRecordDao.page4Review(start, size, param);
	}

	@Override
	public int getTaskRecord4ReviewTotal(TaskReviewInfo param) throws DataAccessException {
		return taskRecordDao.count4Review(param);
	}

	@Override
	public TaskReviewInfo getTaskRecord4Review(long recordId) throws DataAccessException {
		return taskRecordDao.getRecord4Review(recordId);
	}

	@Override
	public void modifyTaskRecord(TbWzTaskRecord record) throws DataAccessException {
		
		if (DataConstant.REVIEW_STATUS_APPROVED == record.getReviewStatus()) {
			TbWzUser user = userDao.selectByPrimaryKey(record.getUserId());
			user.setBalance(user.getBalance() + record.getIncomeGold());
			
			TbWzTaskStep step = stepDao.selectByPrimaryKey(record.getStepId());
			if (step.getIsFinal() == DataConstant.TRUE) {
				user.setUserEffect(user.getUserEffect() + 1);
				user.setStepEffect(user.getStepEffect() + 1);
				
				if (user.getGroupId() != 0) {
					TbWzGroup group = groupDao.selectByPrimaryKey(user.getGroupId());
					group.setGroupEffect(group.getGroupEffect() + 1);
					groupDao.updateByPrimaryKeySelective(group);
				}
				
				TbWzTask task = taskDao.selectByPrimaryKey(record.getTaskId());
				task.setTaskEffect(task.getTaskEffect() + 1);
				taskDao.updateByPrimaryKeySelective(task);
			}
			
			if (user.getGroupId() != 0 && record.getLeaderIncome() != 0L) {
				TbWzGroup group = groupDao.selectByPrimaryKey(user.getGroupId());
				TbWzUser leader = userDao.selectByPrimaryKey(group.getGroupLeader());
				leader.setBalance(leader.getBalance() + record.getLeaderIncome());
				userDao.updateByPrimaryKeySelective(leader);
				
				group.setGroupIncome(group.getGroupIncome() + record.getIncomeGold());
				groupDao.updateByPrimaryKeySelective(group);
			}
			
			userDao.updateByPrimaryKeySelective(user);
		}
		
		taskRecordDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<TaskReportInfo> pageTaskReportInfo(int startPage, int size,TaskReportInfo param) {
		int start = (startPage-1) * size;
		return taskRecordDao.pageTaskReportInfo(start, size, param);
	}

	@Override
	public int countTaskReportInfo(TaskReportInfo param) {
		return taskRecordDao.countTaskReportInfo(param);
	}

	@Override
	public List<StepReportInfo> getStepReportInfoListByTask(TaskReportInfo param) {
		return taskRecordDao.getStepReportInfoListByTask(param);
	}

	@Override
	public boolean isTaskNameExist(String taskName) throws DataAccessException {
		TbWzTask param = new TbWzTask();
		param.setTaskName(taskName);
		return taskDao.count(param) != 0;
	}

	@Override
	public long getTodayPoints(long userId, Date date)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return taskRecordDao.getTodayPoints( userId, date);
	}

	@Override
	public int getNowDayTaskNums(long userId, Date date)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return taskRecordDao.getNowDayTaskNums(userId,date);
	}

	@Override
	public int updateSuspendTasks() throws DataAccessException {
		return taskDao.updateSuspendTasks();
	}

}
