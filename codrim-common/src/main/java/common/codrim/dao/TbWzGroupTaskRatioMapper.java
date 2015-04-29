package common.codrim.dao;

import org.apache.ibatis.annotations.Param;

import common.codrim.pojo.TbWzGroupTaskRatio;

public interface TbWzGroupTaskRatioMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2014-12-30 10:38:10
     */
    int deleteByPrimaryKey(Long ratioId);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-30 10:38:10
     */
    int insert(TbWzGroupTaskRatio record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-30 10:38:10
     */
    int insertSelective(TbWzGroupTaskRatio record);
    
    void batchInsertByTask(@Param("groupId") long groupId, @Param("leaderPercent") long leaderPercent);
    void batchInsertByGroup(@Param("taskId") long taskId);
    void deleteByGroupId(@Param("groupId") long groupId);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-12-30 10:38:10
     */
    TbWzGroupTaskRatio selectByPrimaryKey(Long ratioId);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-30 10:38:10
     */
    int updateByPrimaryKeySelective(TbWzGroupTaskRatio record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-30 10:38:10
     */
    int updateByPrimaryKey(TbWzGroupTaskRatio record);
}