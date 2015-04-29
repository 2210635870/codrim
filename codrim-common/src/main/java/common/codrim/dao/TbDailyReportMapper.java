package common.codrim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import common.codrim.pojo.TbDailyReport;
import common.codrim.query.resultmap.DailyReport;

public interface TbDailyReportMapper {
	
	DailyReport getById(@Param("id") long id);
	
	List<DailyReport> page(@Param("startPage") int startPage, @Param("size") int size, @Param("param") DailyReport param);
    int count(@Param("param") DailyReport param);
    
    List<DailyReport> getDailyReportSummary(@Param("param") DailyReport param);
	
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-01-23 12:19:32
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-01-23 12:19:32
     */
    int insert(TbDailyReport record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-01-23 12:19:32
     */
    int insertSelective(TbDailyReport record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-01-23 12:19:32
     */
    TbDailyReport selectByPrimaryKey(Long id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-01-23 12:19:32
     */
    int updateByPrimaryKeySelective(TbDailyReport record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-01-23 12:19:32
     */
    int updateByPrimaryKey(TbDailyReport record);
}