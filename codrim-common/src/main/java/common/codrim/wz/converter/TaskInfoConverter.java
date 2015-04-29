package common.codrim.wz.converter;


import common.codrim.pojo.TbWzTask;
import common.codrim.util.DateUtil;
import common.codrim.util.StringUtil;
import common.codrim.wz.constant.DataConstant;
import common.codrim.wz.dto.TaskInfo;

public class TaskInfoConverter extends Converter<TbWzTask, TaskInfo> {

	@Override
	public TbWzTask toPO(TaskInfo dto) {
		
		return null;
	}

	@Override
	public TaskInfo toDTO(TbWzTask po) {
		TaskInfo dto = new TaskInfo();
		
		dto.setTaskEffect(StringUtil.toString(po.getTaskEffect()));
		dto.setTaskId(StringUtil.toString(po.getTaskId()));
		dto.setTaskName(StringUtil.toString(po.getTaskName()));
		dto.setTaskOrigPrice(StringUtil.toString(po.getTaskOrigPrice()));
		dto.setTaskStartDate(DateUtil.formatDate(po.getTaskStartDate()));
		dto.setTaskEndDate(DateUtil.formatDate(po.getTaskEndDate()));
		dto.setAppIcon(po.getAppIcon());
		dto.setTaskBelong(po.getTaskBelong());
		dto.setWeight(po.getWeight());
		dto.setProductWeight(po.getProductWeight());
		if (DataConstant.TASK_STATUS_INPROGRESS == po.getTaskStatus())
			dto.setTaskStatus("进行中");
		else if (DataConstant.TASK_STATUS_SUSPEND == po.getTaskStatus())
			dto.setTaskStatus("暂停");
		else if (DataConstant.TASK_STATUS_OFFLINE == po.getTaskStatus())
			dto.setTaskStatus("下线");
		
		return dto;
	}


}
