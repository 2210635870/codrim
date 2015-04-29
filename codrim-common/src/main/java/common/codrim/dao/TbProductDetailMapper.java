package common.codrim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import common.codrim.common.SelectResultByCodition;
import common.codrim.pojo.TbProductDetail;
@Repository
public interface TbProductDetailMapper {
	

	
	
	
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2014-11-25 14:21:17
     */
    int deleteByPrimaryKey(Long id) throws DataAccessException;
   
    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-11-25 14:21:17
     */
    int insert(TbProductDetail record) throws DataAccessException;

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-11-25 14:21:17
     */
    int insertSelective(TbProductDetail record) throws DataAccessException;

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-11-25 14:21:17
     */
    TbProductDetail selectByPrimaryKey(Long id) throws DataAccessException;
    /**
     * 查询所有
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-11-25 14:21:17
     */
    List<TbProductDetail> selectAll()throws DataAccessException;
    
    TbProductDetail getProduct(@Param("productName")String productName,@Param("channelName")String channeName)throws DataAccessException;
    
    
    
    
    /** 
      *通过渠道名获取所有产品
      * @author xulin
      * @date 2015年1月4日
      *  @param channelName
      *  @return
      *  @throws DataAccessException
      *  List<TbProduct>
      */ 
    List<TbProductDetail> getProductByChannelName(String channelName)throws DataAccessException;
    /** 
      *通过产品获取渠道名
      * @author xulin
      * @date 2015年1月4日
      *  @param productName
      *  @return
      *  @throws DataAccessException
      *  List<TbProduct>
      */ 
    List<TbProductDetail> getChannelNamestByProductName(String productName)throws DataAccessException;
    /**
     * 查询所有
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-11-25 14:21:17
     */
    List<TbProductDetail> selectProductsByPage(@Param("startPage")int startPage,
    		@Param("size")int size,@Param("evaluateResult")short evaluateResult)throws DataAccessException;
    /**
     * 查询总数  分页使用
     * 参数:查询条件,主键值
     * 返回:总数
     * @ibatorgenerated 2014-11-25 14:21:17
     */
    int getTotalNum()throws DataAccessException;
    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-11-25 14:21:17
     */
    int updateByPrimaryKeySelective(TbProductDetail record) throws DataAccessException;

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-11-25 14:21:17
     */
    int updateByPrimaryKey(TbProductDetail record) throws DataAccessException;
    
    /**
   	 * 根据相对应的客户名称查找相对应的产口名称
   	 * @author searh
   	 * @date 2014/12/5
   	 * @param request
   	 * @return  list集合
   	 * */
   List<TbProductDetail> selectByCustomerName(String customerName) throws DataAccessException;
   
   /**
  	 * 根据相对应的条件  客户名  产口名称  查找TbProduct对象
  	 * @author searh
  	 * @date 2014/12/12
  	 * @param SelectResultByCodition
  	 * @return  TbProduct对象
  	 * */
    TbProductDetail selectTbProductDetailByCodition(SelectResultByCodition codition) throws DataAccessException;
    
    /**
	 * 通过运行状态查询
	 * @author searh
	 * @date 2014/12/25
	 * @param SelectResultByCodition codition
	 * @return TbProduct
	 * */
    List<TbProductDetail> selectTbProductDetailsByCodition(SelectResultByCodition codition) throws DataAccessException;
    
    /**
	 * 通过运行状态查询 得到总数
	 * @author searh
	 * @date 2014/12/25
	 * @param SelectResultByCodition codition
	 * @return TbProduct
	 * */
  Integer selectTotalProductDetailsByCodition(SelectResultByCodition codition) throws DataAccessException;
   /**
 	 * 根据产品性质查询所有
 	 * @author searh
 	 * @date 2014/12/30
 	 * @param productType
 	 * @return List<TbProduct>
 	 * */
  List<TbProductDetail> selectAllTbProductDetailByProductProperty(SelectResultByCodition codition) throws DataAccessException;
  /**
   * 批量更新
   * 参数:pojo对象
   * 
   * @ibatorgenerated 2014-12-05 14:34:53
   */
  int insertByBatch(List list) throws DataAccessException;
  

	/**
	 * 通过渠道联系人查询对应的渠道
	 * @author searh
	 * @date 2014/12/30
	 * @param request
	 * @return  selectProduct
	 * */
  List<TbProductDetail> selectChannelNameByChannelContactName(@Param("channelContactName") String channelContactName) throws DataAccessException;
}