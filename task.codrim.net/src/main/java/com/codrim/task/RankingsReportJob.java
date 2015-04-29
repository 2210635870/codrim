/**
 * 
 */
package com.codrim.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.codrim.task.service.RankingsReportJobService;


/**
 * @author Administrator
 *
 */
public class RankingsReportJob {
	
	@Autowired
	private RankingsReportJobService rankingsService;
	
	private static final Logger log=Logger.getLogger("TASK");
	
	public void work() {
		 log.info("-------------------------定时添加冲榜报表数据start");
		 long startTime=System.currentTimeMillis();
		 rankingsService.addRankingsReport();
		 long endTime=System.currentTimeMillis();
	     log.info("-------------------------定时添加冲榜报表数据end  用时："+(startTime-endTime)/1000 +"秒");
	}
}
