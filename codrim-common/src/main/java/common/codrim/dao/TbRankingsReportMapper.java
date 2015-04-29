package common.codrim.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import common.codrim.common.SelectResultByCodition;
import common.codrim.common.TotalReportBean;
import common.codrim.pojo.TbRankingsReport;

public interface TbRankingsReportMapper {
 
	 /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2014-12-11 11:45:15
     */
    int deleteByPrimaryKey(Long id) throws DataAccessException;
    
    
   /** 
  *总报表查询
  * @author xulin
  * @date 2015年1月16日
  *  @param codition
  *  @return
  *  @throws DataAccessException
  *  TotalReportBean
  */ 
TotalReportBean  selectSumData(SelectResultByCodition codition) throws DataAccessException;
    
    Integer getTbRankingsReportTotalNumWithExternal(SelectResultByCodition codition) throws DataAccessException;
   /** 
  *渠道开放数据显示
  * @author xulin
  * @date 2015年1月4日
  *  @param codition
  *  @return
  *  @throws DataAccessException
  *  List<TbRankingsReport>
  */ 
List<TbRankingsReport> selectTbRankingsReportWithExternal(SelectResultByCodition codition) throws DataAccessException;
    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-11 11:45:15
     */
    int insert(TbRankingsReport record) throws DataAccessException;
    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-11 11:45:15
     */
    int insertSelective(TbRankingsReport record) throws DataAccessException;
    
    /**
     * 批量插入
     * 参数:list
     * 返回:int 
     * @ibatorgenerated 2014-12-15
     */
    int insertByBatch(List<TbRankingsReport> list) throws DataAccessException;

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-11-25 14:21:17
     */
    TbRankingsReport selectByPrimaryKey(Long id) throws DataAccessException;
    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-11-25 14:21:17
     */
    int updateByPrimaryKeySelective(TbRankingsReport record) throws DataAccessException;
    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-11-25 14:21:17
     */
    int updateByPrimaryKey(TbRankingsReport record) throws DataAccessException;
    /**
     * 按条件分页 
     * @param  SelectResultByCodition
     * @return  <TbRankingsReport>
     * */
    List<TbRankingsReport> selectTbRankingsReportByPages(SelectResultByCodition codition) throws DataAccessException;
    /**
     * 按条件分页 
     * @param  SelectResultByCodition
     * @return  int
     * */
    int getTotalNum(SelectResultByCodition codition) throws DataAccessException;
    
    /**
	  * 按条件查询
	  * @param SelectResultByCodition resultByCodition
	  * @return TbRankingsReport
	  * */
     TbRankingsReport selectTbRankingsReportByCodition(SelectResultByCodition resultByCodition) throws DataAccessException;
    

}