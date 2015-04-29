package common.codrim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import common.codrim.pojo.TbWzBinding;
import common.codrim.pojo.TbWzUser;

public interface TbWzBindingMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2014-12-25 20:21:06
     */
    int deleteByPrimaryKey(Long bindingId);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-25 20:21:06
     */
    int insert(TbWzBinding record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-25 20:21:06
     */
    int insertSelective(TbWzBinding record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-12-25 20:21:06
     */
    TbWzBinding selectByPrimaryKey(Long bindingId);
    
    TbWzBinding selectByDeviceId(String deviceId);
    
    List<TbWzBinding> page(@Param("startPage") int startPage, @Param("size") int size, @Param("param") TbWzBinding param);
    
    int count(@Param("param") TbWzBinding param);
    
    void batchTransferBinding(@Param("fromUser") long fromUser, @Param("toUser") long toUser);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-25 20:21:06
     */
    int updateByPrimaryKeySelective(TbWzBinding record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-25 20:21:06
     */
    int updateByPrimaryKey(TbWzBinding record);
}