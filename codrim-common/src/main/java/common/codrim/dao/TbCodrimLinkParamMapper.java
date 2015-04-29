package common.codrim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import common.codrim.pojo.TbCodrimLinkParam;
@Repository
public interface TbCodrimLinkParamMapper {
	
	List<TbCodrimLinkParam> selectAll();

	/**
	 * 根据主键删除 参数:主键 返回:删除个数
	 * @ibatorgenerated  2015-03-03 11:54:27
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * 插入，空属性也会插入 参数:pojo对象 返回:删除个数
	 * @ibatorgenerated  2015-03-03 11:54:27
	 */
	int insert(TbCodrimLinkParam record);

	/**
	 * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
	 * @ibatorgenerated  2015-03-03 11:54:27
	 */
	int insertSelective(TbCodrimLinkParam record);

	/**
	 * 根据主键查询 参数:查询条件,主键值 返回:对象
	 * @ibatorgenerated  2015-03-03 11:54:27
	 */
	TbCodrimLinkParam selectByPrimaryKey(Long id);

	/**
	 * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
	 * @ibatorgenerated  2015-03-03 11:54:27
	 */
	int updateByPrimaryKeySelective(TbCodrimLinkParam record);

	/**
	 * 根据主键修改，空值条件会修改成null 参数:1.要修改成的值 返回:成功修改个数
	 * @ibatorgenerated  2015-03-03 11:54:27
	 */
	int updateByPrimaryKey(TbCodrimLinkParam record);

	/** 
	  *
	  * @author xulin
	  * @date 2015年3月3日
	  *  @param type
	  *  @param name
	  *  @return
	  *  TbCodrimLinkParam
	  */ 
	TbCodrimLinkParam validCodrimLinkParamZhName(String name);
	/** 
	  *
	  * @author xulin
	  * @date 2015年3月3日
	  *  @param type
	  *  @param name
	  *  @return
	  *  TbCodrimLinkParam
	  */ 
	TbCodrimLinkParam validCodrimLinkParamEnName( String name);
}