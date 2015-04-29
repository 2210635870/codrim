package common.codrim.dao;

import java.util.List;

import common.codrim.common.ChannelEnquiryBean;
import common.codrim.pojo.TbAdvertChannelEnquiry;

public interface TbAdvertChannelEnquiryMapper {
	
	
	List<ChannelEnquiryBean> getChannelEnquirysByAdvertId(Long advertId);
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-02-27 13:59:46
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-02-27 13:59:46
     */
    int insert(TbAdvertChannelEnquiry record);

    /**
     * 批量插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-02-27 13:59:46
     */
    int insertByBatch(List<TbAdvertChannelEnquiry> list);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-02-27 13:59:46
     */
    int insertSelective(TbAdvertChannelEnquiry record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-02-27 13:59:46
     */
    TbAdvertChannelEnquiry selectByPrimaryKey(Long id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-02-27 13:59:46
     */
    int updateByPrimaryKeySelective(TbAdvertChannelEnquiry record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-02-27 13:59:46
     */
    int updateByPrimaryKey(TbAdvertChannelEnquiry record);
}