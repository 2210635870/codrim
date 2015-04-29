package common.codrim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import common.codrim.pojo.TbProductType;
@Repository
public interface TbProductTypeMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2014-12-24 13:32:16
     */
    int deleteByPrimaryKey(Integer id) throws DataAccessException;

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-24 13:32:16
     */
    int insert(TbProductType record) throws DataAccessException;

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-24 13:32:16
     */
    int insertSelective(TbProductType record) throws DataAccessException;

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-12-24 13:32:16
     */
    TbProductType selectByPrimaryKey(Integer id) throws DataAccessException;
    
    /**
   	 * 查询全部TbProductType对象
   	 * @author searh
   	 * @date 2012/12/25
   	 * @return  List<TbProductType> 
   	 * */
    List<TbProductType> selectAllProductTypes() throws DataAccessException;
    /**
   	 * 查询TbProductType对象 带分页
   	 * @author searh
   	 * @date 2012/12/25
   	 * @parame int startPage 
   	 * @parame int size
   	 * @return  int
   	 * */
   	 List<TbProductType> selectTbProductTypeByPages(@Param("startPage")int startPage,@Param("size")int size) throws DataAccessException;

   		/**所有广告类型总数
   		 * @return
   		 * @throws DataAccessException
   		 */
   	int getTotalNum()throws DataAccessException;
    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-24 13:32:16
     */
    int updateByPrimaryKeySelective(TbProductType record) throws DataAccessException;

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-24 13:32:16
     */
    int updateByPrimaryKey(TbProductType record) throws DataAccessException;
}