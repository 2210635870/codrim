package common.codrim.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import common.codrim.pojo.TbSeries;
@Repository
public interface TbSeriesMapper {
	
	
	List<TbSeries> selectAll();
	
	
	
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-02-06 13:23:28
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-02-06 13:23:28
     */
    int insert(TbSeries record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-02-06 13:23:28
     */
    int insertSelective(TbSeries record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-02-06 13:23:28
     */
    TbSeries selectByPrimaryKey(Long id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-02-06 13:23:28
     */
    int updateByPrimaryKeySelective(TbSeries record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-02-06 13:23:28
     */
    int updateByPrimaryKey(TbSeries record);
}