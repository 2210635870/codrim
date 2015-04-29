package common.codrim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import common.codrim.pojo.TbWzGroup;
import common.codrim.pojo.TbWzGroupApplication;
import common.codrim.wz.sql.result.GroupApplicationInfo;

public interface TbWzGroupApplicationMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-01-03 13:42:05
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-01-03 13:42:05
     */
    int insert(TbWzGroupApplication record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-01-03 13:42:05
     */
    int insertSelective(TbWzGroupApplication record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-01-03 13:42:05
     */
    TbWzGroupApplication selectByPrimaryKey(Long id);
    
    List<GroupApplicationInfo> page(@Param("startPage") int startPage, @Param("size") int size, @Param("param") GroupApplicationInfo param);
    
    int count(@Param("param") GroupApplicationInfo param);
    
    GroupApplicationInfo getApplicationDetail(@Param("id") long id);
    
    void deletePendingAppByUser(@Param("userId") long userId);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-01-03 13:42:05
     */
    int updateByPrimaryKeySelective(TbWzGroupApplication record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-01-03 13:42:05
     */
    int updateByPrimaryKey(TbWzGroupApplication record);
}