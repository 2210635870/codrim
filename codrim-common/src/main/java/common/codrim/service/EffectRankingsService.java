package common.codrim.service;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import common.codrim.common.RankingsReportWithDeduplicationBean;
import common.codrim.common.SelectEffectRankings;
import common.codrim.common.SelectResultByCodition;
import common.codrim.pojo.TbEffectRankings;
import common.codrim.pojo.TbRankingsReport;

public interface EffectRankingsService {
	/**
	 * 按条件得到effectRankings对象   SelectEffectRankings为临时对象,接收数据相对应的值
	 * @date 2014/12/18 
	 * @param  SelectResultByCodition codition
	 * @return List<SelectEffectRankings> 
	 * */
	public List<SelectEffectRankings> getTbEffectRankingsByPages(SelectResultByCodition codition) throws DataAccessException;
	public RankingsReportWithDeduplicationBean getRankingsReportDataWithDeduplication(
			SelectResultByCodition codition)throws DataAccessException;;
	/**
	 * 按条件得到effectRankings对象   SelectEffectRankings为临时对象,接收数据相对应的值     
	 * 得到记录数 
	 * @date 2014/12/18 
	 * @param  SelectResultByCodition codition
	 * @return int
	 * */
	public int getTotalTbEffectRankings(SelectResultByCodition codition) throws DataAccessException; 
    /** 
     *根据idfa 更新手机号
     * @author xulin
     * @date 2015年1月4日
     *  @param effectRankings
     *  @return
     *  @throws DataAccessException
     *  int
     */ 
	public int updateEffectByIdfa(TbEffectRankings effectRankings) throws DataAccessException; 
	/**
	 * 
	 * @date 2014/12/18 
	 * @param  Date leftDate
	 * @param  Date rightDate
	 * @return List<GroupByProductChannel>
	 * */
	public List<Date> getEffectRankingsDate() throws DataAccessException;
    
	/**
	 * 添加TbEffectRankings信息 
	 * @date 2014/12/18 
	 * @param  TbEffectRankings effectRankings
	 * @return int
	 * */
	int addEffectRankings(TbEffectRankings effectRankings) throws DataAccessException;
	
	/**
	 * 得到统计effectRankings里的统计数据
	 * @date  2014-12-18
	 * @param  SelectResultByCodition codition
	 * @return  TbRankingsReport
	 * */
	public TbRankingsReport getRankingsReportData(SelectResultByCodition codition) throws DataAccessException;
	
	/** 
	  *检查是否录入过 通过检查时间是否相等
	  * @author xulin
	  * @date 2014年12月18日
	  *  @param effectRankings
	  *  @return
	  *  int
	  */ 
	public TbEffectRankings getEffectRankings(TbEffectRankings effectRankings) throws DataAccessException;
}
