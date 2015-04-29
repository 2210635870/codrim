package common.codrim.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import common.codrim.common.SelectJsonResult;
import common.codrim.pojo.TbChannelType;

public interface ChannelTypeService{
	
	/**
	 * 获得所有的渠道类型名
	 * @author searh
	 * @date 2012/12/24
	 * @parame
	 * @return  List<SelectJsonResult>
	 * */
	public List<SelectJsonResult> getAllChannelTypeName() throws DataAccessException;
	/**
	 * 查询TbChannelType对象 带分页
	 * @author searh
	 * @date 2012/12/25
	 * @parame int startPage 
	 * @parame int size
	 * @return  List<ChannelType>
	 * */
	public  List<TbChannelType> getChannelTypeByPages(int startPage,int size) throws DataAccessException;

	/**所有广告类型总数
	 * @return
	 * @throws DataAccessException
	 */
	public int getTotalChannelType() throws DataAccessException;;
	
	
	/**
	 * 添加TbChannelType对象
	 * @author searh
	 * @date 2012/12/25
	 * @parame
	 * @return  int
	 * */
	public int addChannelType(TbChannelType office) throws DataAccessException;
	/**
	 * 修改TbChannelType对象
	 * @author searh
	 * @date 2012/12/25
	 * @parame
	 * @return  int
	 * */
	public int modifyChannelType(TbChannelType office) throws DataAccessException;
	
	/**
	 * 删除TbChannelType对象
	 * @author searh
	 * @date 2012/12/25
	 * @parame
	 * @return  int
	 * */
	public int deleteChannelType(Integer id) throws DataAccessException;
	
}
