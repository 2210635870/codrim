package common.codrim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import common.codrim.pojo.TbChannel;
@Repository
public interface TbChannelMapper {
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
    int insert(TbChannel record)throws DataAccessException;
    
    
    TbChannel channelLogin(@Param("email") String email,@Param("password")String password)throws DataAccessException;
    
    /** 
      *根据渠道号
      * @author xulin
      * @date 2014年12月30日
      *  @param channelNumber
      *  @return
      *  @throws DataAccessException
      *  TbChannel
      */ 
    TbChannel selectByChannelNum(String channelNumber)throws DataAccessException;
    /**
     * 根据name查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-11-25 14:21:17
     */
    TbChannel selectByName(String name)throws DataAccessException;
    TbChannel selectByCompanyName(String companyName)throws DataAccessException;
    
    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-11-25 14:21:17
     */
    int insertSelective(TbChannel record)throws DataAccessException;

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-11-25 14:21:17
     */
    TbChannel selectByPrimaryKey(Long id)throws DataAccessException;
    /**
     * 查询所有
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-11-25 14:21:17
     */
    List<TbChannel> selectAll()throws DataAccessException;
    /**
     * 查询所有
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-11-25 14:21:17
     */
    List<TbChannel> selectChannelsByPage(@Param("startPage")int startPage,@Param("size")int size)throws DataAccessException;
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
    int updateByPrimaryKeySelective(TbChannel record)throws DataAccessException;

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-11-25 14:21:17
     */
    int updateByPrimaryKey(TbChannel record)throws DataAccessException;
}