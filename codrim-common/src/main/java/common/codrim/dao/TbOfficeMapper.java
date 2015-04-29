package common.codrim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import common.codrim.pojo.TbChannelType;
import common.codrim.pojo.TbOffice;


public interface TbOfficeMapper {
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
    int insert(TbOffice record) throws DataAccessException;

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-24 12:23:47
     */
    int insertSelective(TbOffice record) throws DataAccessException;

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-12-24 12:23:47
     */
    TbOffice selectByPrimaryKey(Integer id)throws DataAccessException;

    /**
	 * 查询TbOffice对象 带分页
	 * @author searh
	 * @date 2012/12/25
	 * @parame int startPage 
	 * @parame 
	 * @return  int
	 * */
	 List<TbOffice> selectTbOfficeByPages(@Param("startPage")int startPage,@Param("size")int size) throws DataAccessException;

		/**所有职位类型总数
		 * @return
		 * @throws DataAccessException
		 */
	int getTotalNum()throws DataAccessException;
	/**
     * 查询全部的TbOffice数据
     * @author searh
     * @date 2012/12/24
     * @parame 
     * @return
     * */
    List<TbOffice> selectAllTbOffice() throws DataAccessException;
    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-24 12:23:47
     */
    int updateByPrimaryKeySelective(TbOffice record) throws DataAccessException;

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-24 12:23:47
     */
    int updateByPrimaryKey(TbOffice record) throws DataAccessException;
    
    
   
}