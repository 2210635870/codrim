package common.codrim.dao;

import common.codrim.pojo.TbWzSequence;

public interface TbWzSequenceMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-16 11:59:22
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-16 11:59:22
     */
    int insert(TbWzSequence record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-16 11:59:22
     */
    int insertSelective(TbWzSequence record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-16 11:59:22
     */
    TbWzSequence selectByPrimaryKey(Long id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-16 11:59:22
     */
    int updateByPrimaryKeySelective(TbWzSequence record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-16 11:59:22
     */
    int updateByPrimaryKey(TbWzSequence record);
}