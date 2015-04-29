package common.codrim.service;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import common.codrim.common.SelectResultByCodition;
import common.codrim.common.TotalReportBean;
import common.codrim.pojo.TbRankingsReport;

public interface RankingsReportService {
	
	
	
	
	   /** 
	  *总报表查询
	  * @author xulin
	  * @date 2015年1月16日
	  *  @param codition
	  *  @return
	  *  @throws DataAccessException
	  *  TotalReportBean
	  */ 
	public TotalReportBean selectSumData(SelectResultByCodition codition)throws DataAccessException;
	/**
	 * 按条件得到TbRankingsReport对象数据  带分页
	 * @date 2014/12/18
	 * @param SelectResultByCodition codition
	 * @return List<TbRankingsReport>
	 * 
	 * */
	public List<TbRankingsReport> getRankingsReportByPage(Date nowTime,SelectResultByCodition codition) throws DataAccessException;
	
	
	public int modifyRankingsReport(TbRankingsReport report)throws DataAccessException;
	/**
	 * 按条件得到TbRankingsReport对象数据  带分页
	 * @date 2014/12/18
	 * @param SelectResultByCodition codition
	 * @return List<TbRankingsReport>
	 * 
	 * */
	public List<TbRankingsReport> getRankingsReportsWithExternal(SelectResultByCodition codition)
			throws DataAccessException;
	
	public int getTbRankingsReportTotalNumWithExternal(SelectResultByCodition codition)throws DataAccessException;
	/**
	 * 按条件得到TbRankingsReport对象数据的总数  带分页
	 * @date 2014/12/18
	 * @param SelectResultByCodition codition
	 * @return int
	 * 
	 * */
	public int getTotalRandingsReport(SelectResultByCodition codition) throws DataAccessException; 
	/**
	 * 批量添加TbRankingsReport对象数据 
	 * @date 2014/12/18
	 * @param List<TbRankingsReport> list
	 * @return 
	 * 
	 * */
	public void addBatch(List<TbRankingsReport> list) throws DataAccessException;
	/**
	 * 按条件查找TbRankingsReport对象是否存在
	 * @date 2014/12/18
	 * @param  SelectResultByCodition resultByCodition
	 * @return  TbRankingsReport
	 * */
	public TbRankingsReport checkRankingsReportByHour(SelectResultByCodition resultByCodition) throws DataAccessException;
	

}
