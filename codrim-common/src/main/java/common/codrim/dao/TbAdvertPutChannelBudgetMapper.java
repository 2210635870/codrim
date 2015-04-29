package common.codrim.dao;

import java.util.List;

import common.codrim.pojo.TbAdvertPutChannelBudget;

public interface TbAdvertPutChannelBudgetMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-02-09 15:10:08
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-02-09 15:10:08
     */
    int insert(TbAdvertPutChannelBudget record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-02-09 15:10:08
     */
    int insertSelective(TbAdvertPutChannelBudget record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-02-09 15:10:08
     */
    TbAdvertPutChannelBudget selectByPrimaryKey(Long id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-02-09 15:10:08
     */
    int updateByPrimaryKeySelective(TbAdvertPutChannelBudget record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-02-09 15:10:08
     */
    int updateByPrimaryKey(TbAdvertPutChannelBudget record);

	int insertByBatch(List<TbAdvertPutChannelBudget> list);
}