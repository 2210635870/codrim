package common.codrim.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import common.codrim.common.SelectResultByCodition;
import common.codrim.common.ViewProductToCountBean;
import common.codrim.pojo.TbProduct;
@Repository
public interface TbProductMapper {
	
	List<TbProduct> getProductNameByCustomerName(String customerName);
	/** 
	  *查询一个产品下面的所有数据
	  * @author xulin
	  * @date 2015年2月6日
	  *  @param codition
	  *  @return
	  *  List<ViewProductToCountBean>
	  */ 
	List<ViewProductToCountBean> selectViewProductToCount(SelectResultByCodition codition);
	
	/**
	 * 根据主键删除 参数:主键 返回:删除个数
	 * @ibatorgenerated  2015-02-04 12:04:11
	 */
	int deleteByPrimaryKey(Long id);


	/**
	 * 插入，空属性也会插入 参数:pojo对象 返回:删除个数
	 * @ibatorgenerated  2015-02-04 12:04:11
	 */
	int insert(TbProduct record);


	/**
	 * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
	 * @ibatorgenerated  2015-02-04 12:04:11
	 */
	int insertSelective(TbProduct record);


	/**
	 * 根据主键查询 参数:查询条件,主键值 返回:对象
	 * @ibatorgenerated  2015-02-04 12:04:11
	 */
	TbProduct selectByPrimaryKey(Long id);


	/**
	 * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
	 * @ibatorgenerated  2015-02-04 12:04:11
	 */
	int updateByPrimaryKeySelective(TbProduct record);


	/**
	 * 根据主键修改，空值条件会修改成null 参数:1.要修改成的值 返回:成功修改个数
	 * @ibatorgenerated  2015-02-04 12:04:11
	 */
	int updateByPrimaryKey(TbProduct record);


}