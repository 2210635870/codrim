package common.codrim.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import common.codrim.pojo.TbRandomSendNum;
@Repository
public interface TbRandomSendNumMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2014-12-17 11:41:19
     */
    int deleteByPrimaryKey(Long id) throws DataAccessException;

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-17 11:41:19
     */
    int insert(TbRandomSendNum record) throws DataAccessException;

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-17 11:41:19
     */
    int insertSelective(TbRandomSendNum record) throws DataAccessException;

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-12-17 11:41:19
     */
    TbRandomSendNum selectByPrimaryKey(Long id) throws DataAccessException;
    /**
     * 根据产品，渠道 手机标识
     * 参数:查询条件
     * 返回:对象
     * @ibatorgenerated 2014-12-17 11:41:19
     */
    TbRandomSendNum  selectByPCM(TbRandomSendNum record) throws DataAccessException;
   
    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-17 11:41:19
     */
    int updateByPrimaryKeySelective(TbRandomSendNum record) throws DataAccessException;

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-17 11:41:19
     */
    int updateByPrimaryKey(TbRandomSendNum record) throws DataAccessException;
}