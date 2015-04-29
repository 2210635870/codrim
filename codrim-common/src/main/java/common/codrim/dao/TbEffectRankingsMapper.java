package common.codrim.dao;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import common.codrim.common.ConsumeNum;
import common.codrim.common.RankingsReportWithDeduplicationBean;
import common.codrim.common.SelectEffectRankings;
import common.codrim.common.SelectResultByCodition;
import common.codrim.pojo.TbEffectRankings;
import common.codrim.pojo.TbRankingsReport;
@Repository
public interface TbEffectRankingsMapper {

	  /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2014-12-05 14:34:53
     */
    int deleteByPrimaryKey(Long id) throws DataAccessException;
    
    /** 
      *根据idfa 更新手机号
      * @author xulin
      * @date 2015年1月4日
      *  @param effectRankings
      *  @return
      *  @throws DataAccessException
      *  int
      */ 
    Integer updateEffectByIdfa(TbEffectRankings effectRankings) throws DataAccessException;
  /** 
  *获取每个imei消费次数
  * @author xulin
  * @date 2014年12月30日
  *  @return
  *  @throws DataAccessException
  *  List<ConsumeNum>
  */ 
List<ConsumeNum>  getConsumeNum(SelectResultByCodition coditon)throws DataAccessException;
    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-05 14:34:53
     */
    int insert(TbEffectRankings record) throws DataAccessException;
    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-05 14:34:53
     */
    int insertSelective(TbEffectRankings record) throws DataAccessException;
    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-12-05 14:34:53
     */
    TbEffectRankings selectByPrimaryKey(Long id) throws DataAccessException;

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-05 14:34:53
     */
    int updateByPrimaryKeySelective(TbEffectRankings record) throws DataAccessException;

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-05 14:34:53
     */
    int updateByPrimaryKey(TbEffectRankings record) throws DataAccessException;
    /**
     * 按条件分页 
     * @param  SelectResultByCodition
     * @return  List<SelectEffectRankings>
     * */
    List<SelectEffectRankings> selectTbEffectRankingsByPages(SelectResultByCodition coditon) throws DataAccessException;
    /**
     * 按条件分页 
     * @param  SelectResultByCodition
     * @return  int 
     * */
    public int getTotalNum(SelectResultByCodition codition) throws DataAccessException;
    /**
     * 
     * @param  Date leftDate
     * @param  Date rightDate
     * @return   List<GroupByProductChannel>
     * */
    public List<Date> selectEffectRankingsWithDate() throws DataAccessException;
   
   
    /**
     * 从TbEffectRankings 表里统计数据到TbRankingsReport对象中
     * @date 2014-12-18
     * @param SelectResultByCodition codition
     * @return TbRankingsReport
     * */
	TbRankingsReport selectRankingsReportData(SelectResultByCodition codition) throws DataAccessException;
	
	
	/** 
	  *从TbEffectRankings 表里统计数据到TbRankingsReport对象中 去重
	  * @author xulin
	  * @date 2015年1月12日
	  *  @param codition
	  *  @return
	  *  @throws DataAccessException
	  *  TbChannelExternalReport
	  */ 
	RankingsReportWithDeduplicationBean   selectRankingsReportDataWithDeduplication(SelectResultByCodition codition) throws DataAccessException;
	
	Integer test(SelectResultByCodition codition);
    
    /** 
      *根据时间查找用户行为是否已录入
      * @author xulin
      * @date 2014年12月18日
      *  @param effectRankings
      *  @return
      *  TbEffectRankings
      */ 
    TbEffectRankings getEffectRankings(TbEffectRankings effectRankings)throws DataAccessException;
}