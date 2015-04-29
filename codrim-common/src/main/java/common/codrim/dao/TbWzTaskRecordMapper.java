package common.codrim.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import common.codrim.pojo.TbWzTaskRecord;
import common.codrim.wz.sql.result.StepReportInfo;
import common.codrim.wz.sql.result.TaskRecordInfo;
import common.codrim.wz.sql.result.TaskReportInfo;
import common.codrim.wz.sql.result.TaskReviewInfo;

public interface TbWzTaskRecordMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2014-12-25 20:21:06
     */
    int deleteByPrimaryKey(Long recordId);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-25 20:21:06
     */
    int insert(TbWzTaskRecord record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-25 20:21:06
     */
    int insertSelective(TbWzTaskRecord record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-12-25 20:21:06
     */
    TbWzTaskRecord selectByPrimaryKey(Long recordId);
    
    TbWzTaskRecord getRecordByStepAndUser(@Param("stepId") long stepId, @Param("userId") long userId);
    TbWzTaskRecord getLastFinishedRecord(@Param("taskId") long taskId, @Param("userId") long userId);
    
    void batchTransferRecord(@Param("fromUser") long fromUser, @Param("toUser") long toUser);
   
    List<TaskRecordInfo> page(@Param("startPage") int startPage, @Param("size") int size, @Param("param") TaskRecordInfo param);
    int count(@Param("param") TaskRecordInfo param);
    
    List<TaskReviewInfo> page4Review(@Param("startPage") int startPage, @Param("size") int size, @Param("param") TaskReviewInfo param);
    int count4Review(@Param("param") TaskReviewInfo param);
    TaskReviewInfo getRecord4Review(@Param("recordId") long recordId);
    
    //---------------------- For Report
    List<TaskReportInfo> pageTaskReportInfo(@Param("startPage") int startPage, @Param("size") int size, @Param("param") TaskReportInfo param);
    int countTaskReportInfo(@Param("param") TaskReportInfo param);
    List<StepReportInfo> getStepReportInfoListByTask(@Param("param") TaskReportInfo param);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-25 20:21:06
     */
    int updateByPrimaryKeySelective(TbWzTaskRecord record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-25 20:21:06
     */
    int updateByPrimaryKey(TbWzTaskRecord record);

	long getTodayPoints(@Param("userId")long userId, @Param("date")Date date);

	int getNowDayTaskNums(@Param("userId")long userId, @Param("date")Date date);
}