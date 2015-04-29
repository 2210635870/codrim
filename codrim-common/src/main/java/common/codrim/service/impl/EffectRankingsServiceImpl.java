package common.codrim.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.common.ConsumeNum;
import common.codrim.common.RankingsReportWithDeduplicationBean;
import common.codrim.common.SelectEffectRankings;
import common.codrim.common.SelectResultByCodition;
import common.codrim.dao.TbEffectRankingsMapper;
import common.codrim.pojo.TbEffectRankings;
import common.codrim.pojo.TbRankingsReport;
import common.codrim.service.EffectRankingsService;
import common.codrim.util.StringUtil;

@Service
@Transactional
public class EffectRankingsServiceImpl implements EffectRankingsService {
	
	@Autowired
	private TbEffectRankingsMapper effectRankingsDao;
	/**
	 * 按条件得到effectRankings对象   SelectEffectRankings为临时对象,接收数据相对应的值
	 * @date 2014/12/18 
	 * @param  SelectResultByCodition codition
	 * @return List<SelectEffectRankings> 
	 * */
	@Override
	public List<SelectEffectRankings> getTbEffectRankingsByPages(
			SelectResultByCodition codition) throws DataAccessException{
		codition.setStartPage((codition.getStartPage()-1)*codition.getSize());
		List<SelectEffectRankings> list=effectRankingsDao.selectTbEffectRankingsByPages(codition);
		if(list!=null&&list.size()>0){
			for(SelectEffectRankings effectRankings:list){
				String consumeMore=effectRankings.getConsumeMore();
				if(!StringUtil.isBlank(consumeMore)){
					int num=Integer.parseInt(consumeMore);
					if(num==1){
						effectRankings.setConsumeOne("1");
						effectRankings.setConsumeMore("0");
					}
					if(num>1){
						effectRankings.setConsumeOne("1");
						effectRankings.setConsumeMore(num+"");
					}
					if(num==0){
						effectRankings.setConsumeOne("0");
						effectRankings.setConsumeMore("0");
					}
				}
				if(StringUtil.isBlank(effectRankings.getClickNum())){
					effectRankings.setClickNum("0");
				}
				if(StringUtil.isBlank(effectRankings.getActivate())){
					effectRankings.setActivate("0");
				}
				if(StringUtil.isBlank(effectRankings.getEffect())){
					effectRankings.setEffect("0");
				}
				if(StringUtil.isBlank(effectRankings.getRegister())){
					effectRankings.setRegister("0");
				}
				if(StringUtil.isBlank(effectRankings.getRecharge())){
					effectRankings.setRecharge("0");
				}
				if(StringUtil.isBlank(effectRankings.getCredit())){
					effectRankings.setCredit("0");
				}
				if(StringUtil.isBlank(effectRankings.getBaddept())){
					effectRankings.setBaddept("0");
				}
				
			}
		}
		return list;
	}
	/**
	 * 按条件得到effectRankings对象   SelectEffectRankings为临时对象,接收数据相对应的值     
	 * 得到记录数 
	 * @date 2014/12/18 
	 * @param  SelectResultByCodition codition
	 * @return int
	 * */
	@Override
	public int getTotalTbEffectRankings(SelectResultByCodition codition) throws DataAccessException{
		
		Integer res=effectRankingsDao.getTotalNum(codition);
		return res==null?0:res.intValue();
	}
	/**
	 * 按条件得到符合条件的产品名和渠道名
	 * @date 2014/12/18 
	 * @param  Date leftDate
	 * @param  Date rightDate
	 * @return List<GroupByProductChannel>
	 * */
	@Override
	public List<Date> getEffectRankingsDate() throws DataAccessException{
		
		return effectRankingsDao.selectEffectRankingsWithDate();
	}
	/**
	 * 添加TbEffectRankings信息 
	 * @date 2014/12/18 
	 * @param  TbEffectRankings effectRankings
	 * @return int
	 * */
	@Override
	public int addEffectRankings(TbEffectRankings effectRankings) throws DataAccessException{
		// TODO Auto-generated method stub
		return effectRankingsDao.insertSelective(effectRankings);
	}

	/**
	 * 得到统计effectRankings里的统计数据
	 * @date  2014-12-18
	 * @param  SelectResultByCodition codition
	 * @return  TbRankingsReport
	 * */
	@Override
	public TbRankingsReport getRankingsReportData(
			SelectResultByCodition codition) throws DataAccessException {
		codition.setIsDeduplication("no");
List<ConsumeNum> nums=effectRankingsDao.getConsumeNum(codition);		
int consumeMore=0;
int consumeOne=0;
		if(nums!=null&&nums.size()>0){
			for(ConsumeNum num:nums){
				if(num.getNum()==1){
					consumeOne =consumeOne+1;
				}
				if(num.getNum()>1){
					consumeOne =consumeOne+1;
					consumeMore =consumeMore+1;
				}
			}
		}
		TbRankingsReport rankingsReport=effectRankingsDao.selectRankingsReportData(codition);
		if(rankingsReport!=null){
			rankingsReport.setConsumeMore(consumeMore);
			rankingsReport.setConsumeOne(consumeOne);
		}
		return rankingsReport;
	}
	/**
	 * 得到统计effectRankings里的统计数据 去重
	 * @date  2014-12-18
	 * @param  SelectResultByCodition codition
	 * @return  TbRankingsReport
	 * */
	@Override
	public RankingsReportWithDeduplicationBean  getRankingsReportDataWithDeduplication(
			SelectResultByCodition codition) throws DataAccessException{
		codition.setIsDeduplication("yes");
		List<ConsumeNum> nums=effectRankingsDao.getConsumeNum(codition);		
		int consumeMore=0;
		int consumeOne=0;
				if(nums!=null&&nums.size()>0){
					for(ConsumeNum num:nums){
						if(num.getNum()==1){
							consumeOne =consumeOne+1;
						}
						if(num.getNum()>1){
							consumeOne =consumeOne+1;
							consumeMore =consumeMore+1;
						}
					}
				}
		RankingsReportWithDeduplicationBean rankingsReport=effectRankingsDao.selectRankingsReportDataWithDeduplication(codition);
		if(rankingsReport!=null){
			rankingsReport.setConsumeMore(consumeMore);
			rankingsReport.setConsumeOne(consumeOne);
		}
		return rankingsReport;
	}
	
	/** 
	  *检查是否录入过 通过检查时间是否相等
	  * @author xulin
	  * @date 2014年12月18日
	  *  @param effectRankings
	  *  @return
	  *  int
	  */ 
	public TbEffectRankings getEffectRankings(TbEffectRankings effectRankings) {
		// TODO Auto-generated method stub
		return effectRankingsDao.getEffectRankings(effectRankings);
	}
	/* (non-Javadoc)
	 * @see common.codrim.service.EffectRankingsService#updateEffectByIdfa(common.codrim.pojo.TbEffectRankings)
	 */
	@Override
	public int updateEffectByIdfa(TbEffectRankings effectRankings)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Integer integer=effectRankingsDao.updateEffectByIdfa(effectRankings);
		return integer==null?0:integer.intValue();
	}
}
