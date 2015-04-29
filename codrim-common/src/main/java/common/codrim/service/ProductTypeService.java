package common.codrim.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import common.codrim.common.ResultJsonBean;
import common.codrim.common.SelectJsonResult;
import common.codrim.pojo.TbProductType;

public interface ProductTypeService {

	
	/**
	 * 查询TbProductType对象 带分页
	 * @author searh
	 * @date 2012/12/25
	 * @parame int startPage 
	 * @parame int size
	 * @return  List<TbProductType>
	 * */
	public  List<TbProductType> getProductTypeByPages(int startPage,int size) throws DataAccessException;

	/**所有渠道类型总数
	 * @return
	 * @throws DataAccessException
	 */
	public int getTotalTbProductType()throws DataAccessException;;
	
	
	/**
	 * 添加TbProductType对象
	 * @author searh
	 * @date 2012/12/25
	 * @parame
	 * @return  int
	 * */
	public int addTbProductType(TbProductType productType)throws DataAccessException;
	/**
	 * 修改TbProductType对象
	 * @author searh
	 * @date 2012/12/25
	 * @parame
	 * @return  int
	 * */
	public int modifyTbProductType(TbProductType productType)throws DataAccessException;
	
	/**
	 * 删除TbProductType对象
	 * @author searh
	 * @date 2012/12/25
	 * @parame
	 * @return  int
	 * */
	public int deleteTbProductType(Integer id) throws DataAccessException;
	
	/**
	 * 获得所有的广告类型
	 * @author searh
	 * @param 
	 * @return List<TbProductType>
	 */
	public List<SelectJsonResult> getAllProductTypes() throws DataAccessException;

	public TbProductType getProductTypeById(int id);
}
