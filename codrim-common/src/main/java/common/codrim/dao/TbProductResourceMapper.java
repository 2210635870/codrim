package common.codrim.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import common.codrim.pojo.TbProductResource;
@Repository
public interface TbProductResourceMapper {
	
	
	
	  /**
     * 批量插入
     * 参数:list
     * 返回:int 
     * @ibatorgenerated 2014-12-15
     */
    int insertByBatch(List<TbProductResource> list) throws DataAccessException;

    /** 
      *根据productID查询所有图片素材
      * @author xulin
      * @date 2015年2月9日
      *  @param productId
      *  @return
      *  @throws DataAccessException
      *  List<TbProductResource>
      */ 
    List<TbProductResource> selectProductResourcesByProductId(long productId)throws DataAccessException;
	
	
	
	
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    int insert(TbProductResource record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    int insertSelective(TbProductResource record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    TbProductResource selectByPrimaryKey(Long id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    int updateByPrimaryKeySelective(TbProductResource record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-02-04 12:04:11
     */
    int updateByPrimaryKey(TbProductResource record);
}