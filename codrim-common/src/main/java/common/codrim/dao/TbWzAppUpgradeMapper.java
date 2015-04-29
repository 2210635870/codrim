package common.codrim.dao;

import java.util.List;

import common.codrim.pojo.TbWzAppUpgrade;

public interface TbWzAppUpgradeMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-01-19 16:10:34
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-01-19 16:10:34
     */
    int insert(TbWzAppUpgrade record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-01-19 16:10:34
     */
    int insertSelective(TbWzAppUpgrade record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-01-19 16:10:34
     */
    TbWzAppUpgrade selectByPrimaryKey(Long id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-01-19 16:10:34
     */
    int updateByPrimaryKeySelective(TbWzAppUpgrade record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-01-19 16:10:34
     */
    int updateByPrimaryKey(TbWzAppUpgrade record);
    
	List<TbWzAppUpgrade> selectAllAppUpgrade();
}