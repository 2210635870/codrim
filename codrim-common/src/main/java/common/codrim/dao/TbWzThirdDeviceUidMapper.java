package common.codrim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import common.codrim.pojo.TbWzThirdDeviceUid;

public interface TbWzThirdDeviceUidMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-16 10:58:56
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-16 10:58:56
     */
    int insert(TbWzThirdDeviceUid record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-16 10:58:56
     */
    int insertSelective(TbWzThirdDeviceUid record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-16 10:58:56
     */
    TbWzThirdDeviceUid selectByPrimaryKey(Long id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-16 10:58:56
     */
    int updateByPrimaryKeySelective(TbWzThirdDeviceUid record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-16 10:58:56
     */
    int updateByPrimaryKey(TbWzThirdDeviceUid record);

	int updateThirdDeviceUidByUnionidAndDifId(TbWzThirdDeviceUid thirdDeviceUid);

	 TbWzThirdDeviceUid getThirdDeviceUidByUidAndDifId(@Param("unionid")String unionid,@Param("difId")String difId);

	TbWzThirdDeviceUid getThirdDeviceUidByUidAndDifIdAndDeviceId(
			@Param("unionid")String unionid, @Param("difId")String difId, @Param("deviceId")String deviceId);
}