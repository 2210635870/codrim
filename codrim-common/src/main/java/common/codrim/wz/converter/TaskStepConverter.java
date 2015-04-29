package common.codrim.wz.converter;

import common.codrim.pojo.TbWzTaskStep;
import common.codrim.util.DateUtil;
import common.codrim.util.StringUtil;
import common.codrim.wz.dto.TaskStep;

public class TaskStepConverter extends Converter<TbWzTaskStep, TaskStep> {

	@Override
	public TbWzTaskStep toPO(TaskStep dto) {
		TbWzTaskStep step = new TbWzTaskStep();
		
		if (!StringUtil.isEmpty(dto.getStepId()))
			step.setStepId(Long.valueOf(dto.getStepId()));
		
		if (!StringUtil.isEmpty(dto.getCountDuration()))
			step.setCountDuration(Integer.valueOf(dto.getCountDuration()));
		else
			step.setCountDuration(0);
		
		if (!StringUtil.isEmpty(dto.getCountInterval()))
			step.setCountInterval(Integer.valueOf(dto.getCountInterval()));
		else
			step.setCountInterval(0);
		
		if (!StringUtil.isEmpty(dto.getScreenNo()))
			step.setScreenNo(Integer.valueOf(dto.getScreenNo()));
		else
			step.setScreenNo(0);
		if (!StringUtil.isEmpty(dto.getStepOrder()))
			step.setStepOrder(Short.valueOf(dto.getStepOrder()));
		step.setScreenDesc(dto.getScreenDesc());
		step.setRewardPercent(Integer.valueOf(dto.getRewardPercent()));
		step.setStepDesc(dto.getStepDesc());
		step.setStepType(Integer.valueOf(dto.getStepType()));
		
		return step;
	}

	@Override
	public TaskStep toDTO(TbWzTaskStep po) {
		TaskStep step = new TaskStep();
		
		step.setStepId(StringUtil.toString(po.getStepId()));
		step.setCountDuration(StringUtil.toString(po.getCountDuration()));
		step.setCountInterval(StringUtil.toString(po.getCountInterval()));
		step.setScreenNo(StringUtil.toString(po.getScreenNo()));
		step.setScreenDesc(po.getScreenDesc());
		step.setRewardPercent(StringUtil.toString(po.getRewardPercent()));
		step.setStepDesc(po.getStepDesc());
		step.setStepType(StringUtil.toString(po.getStepType()));
		
		return step;
	}

}
