package common.codrim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import common.codrim.pojo.TbChannelType;



public interface TbChannelTypeMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2014-12-24 12:23:47
     */
    int deleteByPrimaryKey(Integer id) throws DataAccessException;

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-24 12:23:47
     */
    int insert(TbChannelType record) throws DataAccessException;

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-24 12:23:47
     */
    int insertSelective(TbChannelType record) throws DataAccessException;

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-12-24 12:23:47
     */
    TbChannelType selectByPrimaryKey(Integer id) throws DataAccessException;
    
    
    
	/**
	 * 查询TbChannelType对象 带分页
	 * @author searh
	 * @date 2012/12/25
	 * @parame int startPage 
	 * @parame 
	 * @return  int
	 * */
	 List<TbChannelType> selectTbChannelTypeByPages(@Param("startPage")int startPage,@Param("size")int size) throws DataAccessException;

	 
		/**所有渠道类型总数
		 * @return
		 * @throws DataAccessException
		 */
	int getTotalNum()throws DataAccessException;
	 
	 
    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-24 12:23:47
     */
    int updateByPrimaryKeySelective(TbChannelType record) throws DataAccessException;

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-24 12:23:47
     */
    int updateByPrimaryKey(TbChannelType record) throws DataAccessException;
    
    /**
     * 获得所有TbChannelType数据
     * @author searh
     * @date 2012/12/24
     * @parame 
     * @return List<TbChannelType>
     * */
    List<TbChannelType> selectAllTbChannelType() throws DataAccessException;
}