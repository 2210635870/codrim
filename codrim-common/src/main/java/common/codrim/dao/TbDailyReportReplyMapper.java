package common.codrim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import common.codrim.pojo.TbDailyReportReply;
import common.codrim.query.resultmap.DailyReportReply;

public interface TbDailyReportReplyMapper {
	
	
	List<DailyReportReply> getReplysByReport(@Param("reportId") long reportId);
	void deleteByReport(@Param("reportId") long reportId);
	
	
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-01-28 10:37:26
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-01-28 10:37:26
     */
    int insert(TbDailyReportReply record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-01-28 10:37:26
     */
    int insertSelective(TbDailyReportReply record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-01-28 10:37:26
     */
    TbDailyReportReply selectByPrimaryKey(Long id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-01-28 10:37:26
     */
    int updateByPrimaryKeySelective(TbDailyReportReply record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-01-28 10:37:26
     */
    int updateByPrimaryKey(TbDailyReportReply record);
}