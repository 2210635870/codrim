package common.codrim.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import common.codrim.common.SelectResultByCodition;
import common.codrim.pojo.TbWzTask;
import common.codrim.wz.sql.result.KeyValResult;
import common.codrim.wz.sql.result.ScreenlockTask;
import common.codrim.wz.sql.result.TaskWithGroupRatio;

public interface TbWzTaskMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2014-12-25 20:21:06
     */
    int deleteByPrimaryKey(Long taskId);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-25 20:21:06
     */
    int insert(TbWzTask record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-25 20:21:06
     */
    int insertSelective(TbWzTask record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-12-25 20:21:06
     */
    TbWzTask selectByPrimaryKey(Long taskId);
    
    List<TbWzTask> page(@Param("startPage") Integer startPage, @Param("size") Integer size, @Param("param") TbWzTask param);
    
    List<TaskWithGroupRatio> pageWithGroupRatio(@Param("taskBelong") short taskBelong,@Param("startPage") int startPage, @Param("size") int size, @Param("userId") long userId);
    
    TaskWithGroupRatio getTask(@Param("taskId") long taskId, @Param("userId") long userId);
    
    int count(@Param("param") TbWzTask param);
    
    List<KeyValResult> getAllTaskSelectOptions();
    
    void changeStatus(@Param("taskId") long taskId, @Param("status") int status);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-25 20:21:06
     */
    int updateByPrimaryKeySelective(TbWzTask record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-25 20:21:06
     */
    int updateByPrimaryKey(TbWzTask record);

	List<TbWzTask> getTaskByPutType(SelectResultByCodition codition);

	int getTotalTaskByPutType(SelectResultByCodition codition);
	
	List<Map<String, Object>> selectScreenlockTasks(Map<String, Object> params);
}