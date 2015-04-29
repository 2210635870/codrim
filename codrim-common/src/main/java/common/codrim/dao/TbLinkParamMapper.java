package common.codrim.dao;

import java.util.List;

import common.codrim.pojo.TbCodrimLinkParam;
import common.codrim.pojo.TbLinkParam;

public interface TbLinkParamMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-03-03 12:05:19
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-03-03 12:05:19
     */
    int insert(TbLinkParam record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-03-03 12:05:19
     */
    int insertSelective(TbLinkParam record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-03-03 12:05:19
     */
    TbLinkParam selectByPrimaryKey(Long id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-03-03 12:05:19
     */
    int updateByPrimaryKeySelective(TbLinkParam record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-03-03 12:05:19
     */
    int updateByPrimaryKey(TbLinkParam record);

	/** 
	  *
	  * @author xulin
	  * @date 2015年3月3日
	  *  @param list
	  *  @return
	  *  int
	  */ 
	int insertByBatch(List<TbLinkParam> list);

	/** 
	  *
	  * @author xulin
	  * @date 2015年3月3日
	  *  @param productId
	  *  @return
	  *  List<TbLinkParam>
	  */ 
	List<TbCodrimLinkParam> getLinkParamsByProductId(long productId);
}