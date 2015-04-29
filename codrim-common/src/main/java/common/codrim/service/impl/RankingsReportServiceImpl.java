package common.codrim.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.common.SelectResultByCodition;
import common.codrim.common.TotalReportBean;
import common.codrim.dao.TbRankingsReportMapper;
import common.codrim.pojo.TbRankingsReport;
import common.codrim.service.RankingsReportService;
import common.codrim.util.DateUtil;

@Service
@Transactional
public class RankingsReportServiceImpl implements RankingsReportService {
	@Autowired
	private TbRankingsReportMapper rankingsReportDao;
@Autowired
private GetCurrentDateRankingsReportJobService rankingsReportJobService;

@Override
public int modifyRankingsReport(TbRankingsReport report){
	return rankingsReportDao.updateByPrimaryKeySelective(report);
}


@Override
public List<TbRankingsReport> getRankingsReportsWithExternal(SelectResultByCodition codition)
throws DataAccessException{
	List<TbRankingsReport>  rankingsReports=rankingsReportDao.selectTbRankingsReportWithExternal(codition);
	return rankingsReports;
}

/** 
*总报表查询
* @author xulin
* @date 2015年1月16日
*  @param codition
*  @return
*  @throws DataAccessException
*  TotalReportBean
*/ 
@Override
public TotalReportBean selectSumData(SelectResultByCodition codition)throws DataAccessException{
	return rankingsReportDao.selectSumData(codition);
}



	/**
	 * 按条件得到TbRankingsReport对象数据  带分页
	 * @date 2014/12/18
	 * @param SelectResultByCodition codition
	 * @return List<TbRankingsReport>
	 * 
	 * */
	@Override
	public List<TbRankingsReport> getRankingsReportByPage(Date nowTime,
			SelectResultByCodition codition) throws DataAccessException{
		Date endTime=codition.getEndTime();
		List<TbRankingsReport> reports=new ArrayList<TbRankingsReport>();
		if(endTime.getTime()==nowTime.getTime()){
			reports.addAll(rankingsReportJobService.getRankingsReport(codition,""));
		}
		reports.addAll( rankingsReportDao.selectTbRankingsReportByPages(codition));
		return reports;
	}
	
	/**
	 * 按条件得到TbRankingsReport对象数据的总数  带分页
	 * @date 2014/12/18
	 * @param SelectResultByCodition codition
	 * @return int
	 * 
	 * */
	@Override
	public int getTotalRandingsReport(SelectResultByCodition codition) throws DataAccessException{
		
		Integer res = rankingsReportDao.getTotalNum(codition);
		
		return res == null ? 0 : res.intValue();
	}
	/**
	 * 批量添加TbRankingsReport对象数据 
	 * @date 2014/12/18
	 * @param List<TbRankingsReport> list
	 * @return 
	 * 
	 * */
	@Override
	public void addBatch(List<TbRankingsReport> list) throws DataAccessException{
		rankingsReportDao.insertByBatch(list);
		
	}
	/**
	 * 按条件查找TbRankingsReport对象是否存在
	 * @date 2014/12/18
	 * @param  SelectResultByCodition resultByCodition
	 * @return  TbRankingsReport
	 * */
	@Override
	public TbRankingsReport checkRankingsReportByHour(SelectResultByCodition resultByCodition) throws DataAccessException{
		
		return rankingsReportDao.selectTbRankingsReportByCodition(resultByCodition);
	}







	/* (non-Javadoc)
	 * @see common.codrim.service.RankingsReportService#getTbRankingsReportTotalNumWithExternal(common.codrim.common.SelectResultByCodition)
	 */
	@Override
	public int getTbRankingsReportTotalNumWithExternal(
			SelectResultByCodition codition) throws DataAccessException {
		// TODO Auto-generated method stub
		Integer integer=rankingsReportDao.getTbRankingsReportTotalNumWithExternal(codition);
		return integer==null?0:integer.intValue();
	}

}
