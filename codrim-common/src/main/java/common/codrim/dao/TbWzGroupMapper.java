package common.codrim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import common.codrim.pojo.TbWzGroup;
import common.codrim.wz.sql.result.GroupInfo;
import common.codrim.wz.sql.result.RankingInfo;

public interface TbWzGroupMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2014-12-25 20:21:07
     */
    int deleteByPrimaryKey(Long groupId);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-25 20:21:07
     */
    int insert(TbWzGroup record);
    TbWzGroup getByUser(@Param("userId")long userId);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-25 20:21:07
     */
    int insertSelective(TbWzGroup record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-12-25 20:21:07
     */
    TbWzGroup selectByPrimaryKey(Long groupId);
    
    List<GroupInfo> page(@Param("startPage") int startPage, @Param("size") int size, @Param("param") TbWzGroup param);
    
    int count(@Param("param") TbWzGroup param);
    
    TbWzGroup searchGroup(@Param("param") TbWzGroup param);
    
    GroupInfo getGroupDetail(@Param("groupId") long groupId);
    
    List<RankingInfo> groupRanking(@Param("size") int size);
    
    void dismissGroupUsers(@Param("groupId") long groupId);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-25 20:21:07
     */
    int updateByPrimaryKeySelective(TbWzGroup record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-25 20:21:07
     */
    int updateByPrimaryKey(TbWzGroup record);
}