package com.codrim.manager;

import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.stereotype.Repository;

import common.codrim.service.WzPointsLogService;
import common.codrim.service.WzTaskService;
import common.codrim.service.WzUserService;
import common.codrim.util.DateUtil;
@Repository
public class LimitManager {

	@Autowired
	private WzUserService userService;
	@Autowired
	private WzTaskService taskService;
	@Autowired
	protected WzPointsLogService pointsLogService;
	/**
	 * 判断任务数是否超过限制
	 * @param userId
	 * @return
	 */
	public int checkLimitWithTask(long userId,int limitNum){
		int res=0;
		int nowDayTaskNum=taskService.getNowDayTaskNums(userId, DateUtil.getNowDateToDate(System.currentTimeMillis(), "yyyy-MM-dd"));
		if(nowDayTaskNum>=limitNum){
			res=1;
		}
		return res;
	}
	/**
	 * 判断任务数是否超过限制
	 * @param userId
	 * @return
	 */
	public int checkLimitWithInvite(long userId,int limitNum){
		int res=0;
		int nowDayTaskNum=pointsLogService.getNowDayInvites(userId, DateUtil.getNowDateToDate(System.currentTimeMillis(), "yyyy-MM-dd"));
		if(nowDayTaskNum>=limitNum){
			res=1;
		}
		return res;
	}
}
