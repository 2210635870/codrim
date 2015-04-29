package common.codrim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import common.codrim.pojo.TbWzExchangeRecord;
import common.codrim.wz.sql.result.ExchangeRecordInfo;

public interface TbWzExchangeRecordMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2014-12-25 20:21:07
     */
    int deleteByPrimaryKey(Long recordId);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-25 20:21:07
     */
    int insert(TbWzExchangeRecord record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-12-25 20:21:07
     */
    int insertSelective(TbWzExchangeRecord record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-12-25 20:21:07
     */
    TbWzExchangeRecord selectByPrimaryKey(Long recordId);
    
    List<ExchangeRecordInfo> page(@Param("startPage") int startPage, @Param("size") int size, @Param("param") ExchangeRecordInfo param);
    
    int count(@Param("param") ExchangeRecordInfo param);
    
    ExchangeRecordInfo getExchangeRecordInfo(@Param("recordId") long recordId);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-25 20:21:07
     */
    int updateByPrimaryKeySelective(TbWzExchangeRecord record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-12-25 20:21:07
     */
    int updateByPrimaryKey(TbWzExchangeRecord record);
}