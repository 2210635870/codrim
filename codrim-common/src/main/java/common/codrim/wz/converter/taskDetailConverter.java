package common.codrim.wz.converter;

import common.codrim.pojo.TbWzTask;
import common.codrim.util.DateUtil;
import common.codrim.util.StringUtil;
import common.codrim.wz.dto.TaskDetail;

public class taskDetailConverter extends Converter<TbWzTask, TaskDetail> {

	@Override
	public TbWzTask toPO(TaskDetail dto) {
		TbWzTask task = new TbWzTask();
		if (!StringUtil.isEmpty(dto.getTaskId()))
			task.setTaskId(Long.valueOf(dto.getTaskId()));
		task.setAppIcon(dto.getAppIcon());
		task.setAppPname(dto.getAppPname());
		task.setAppScreen1(dto.getAppScreen1());
		task.setAppScreen2(dto.getAppScreen2());
		task.setAppSize(Integer.valueOf(dto.getAppSize()));
		task.setAppUrl(dto.getAppUrl());
		task.setCustomerId(Long.valueOf(dto.getCustomerId()));
		task.setTaskDesc(dto.getTaskDesc());
		task.setTaskEffect(Integer.valueOf(dto.getTaskEffect()));
		task.setTaskEndDate(DateUtil.parseDate(dto.getTaskEndDate()));
		task.setTaskName(dto.getTaskName());
		task.setTaskOrigPrice(Double.valueOf(dto.getTaskOrigPrice()));
		task.setTaskRemark(dto.getTaskRemark());
		task.setTaskStartDate(DateUtil.parseDate(dto.getTaskStartDate()));
		task.setTaskStatus(Integer.valueOf(dto.getTaskStatus()));
		task.setTaskBelong(dto.getTaskBelong());
		task.setWeight(dto.getWeight());
		task.setProductWeight(dto.getProductWeight());
		task.setAppScreenlockFile((dto.getAppScreenLock()));
		
		return task;
	}

	@Override
	public TaskDetail toDTO(TbWzTask po) {
		TaskDetail task = new TaskDetail();
		
		task.setTaskId(StringUtil.toString(po.getTaskId()));
		task.setAppIcon(po.getAppIcon());
		task.setAppPname(po.getAppPname());
		task.setAppScreen1(po.getAppScreen1());
		task.setAppScreen2(po.getAppScreen2());
		task.setAppSize(StringUtil.toString(po.getAppSize()));
		task.setAppUrl(po.getAppUrl());
		task.setCustomerId(StringUtil.toString(po.getCustomerId()));
		task.setTaskDesc(po.getTaskDesc());
		task.setTaskEffect(StringUtil.toString(po.getTaskEffect()));
		task.setTaskEndDate(DateUtil.formatDate(po.getTaskEndDate()));
		task.setTaskName(po.getTaskName());
		task.setTaskOrigPrice(StringUtil.toString(po.getTaskOrigPrice()));
		task.setTaskRemark(po.getTaskRemark());
		task.setTaskStartDate(DateUtil.formatDate(po.getTaskStartDate()));
		task.setTaskStatus(StringUtil.toString(po.getTaskStatus()));
		task.setTaskBelong(po.getTaskBelong());
		task.setWeight(po.getWeight());
		task.setProductWeight(po.getProductWeight());
		task.setAppScreenLock(po.getAppScreenlockFile());
		return task;
	}

}
