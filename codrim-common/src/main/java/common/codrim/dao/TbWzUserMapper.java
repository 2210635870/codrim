package common.codrim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import common.codrim.pojo.TbWzUser;
import common.codrim.wz.sql.in.Order;
import common.codrim.wz.sql.result.RankingInfo;

public interface TbWzUserMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2014-12-25 20:21:06
     */
    int deleteByPrimaryKey(Long userId);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-25 20:21:06
     */
    int insert(TbWzUser record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-25 20:21:06
     */
    int insertSelective(TbWzUser record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-12-25 20:21:06
     */
    TbWzUser selectByPrimaryKey(Long userId);
    
    long getBalance(Long userId);
    
    TbWzUser selectByPhoneNumber(String phoneNumber);
    
    List<TbWzUser> page(@Param("startPage") int startPage, @Param("size") int size, @Param("param") TbWzUser param, @Param("order") Order order);
    
    int count(@Param("param") TbWzUser param);
    
    List<RankingInfo> userRanking(@Param("size") int size);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-25 20:21:06
     */
    int updateByPrimaryKeySelective(TbWzUser record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-25 20:21:06
     */
    int updateByPrimaryKey(TbWzUser record);
}