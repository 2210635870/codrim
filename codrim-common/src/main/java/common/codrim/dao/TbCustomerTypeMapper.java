package common.codrim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import common.codrim.pojo.TbCustomerType;

public interface TbCustomerTypeMapper {
	
	int count() throws DataAccessException;
	List<TbCustomerType> page(@Param("startPage") int startPage, @Param("size") int size) throws DataAccessException;
	List<TbCustomerType> getAllCustomerType() throws DataAccessException;
	
	
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-01-29 17:14:01
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-01-29 17:14:01
     */
    int insert(TbCustomerType record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-01-29 17:14:01
     */
    int insertSelective(TbCustomerType record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-01-29 17:14:01
     */
    TbCustomerType selectByPrimaryKey(Long id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-01-29 17:14:01
     */
    int updateByPrimaryKeySelective(TbCustomerType record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-01-29 17:14:01
     */
    int updateByPrimaryKey(TbCustomerType record);
}