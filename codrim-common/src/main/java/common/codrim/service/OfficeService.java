package common.codrim.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import common.codrim.common.ResultJsonBean;
import common.codrim.common.SelectJsonResult;
import common.codrim.pojo.TbChannelType;
import common.codrim.pojo.TbOffice;

public interface OfficeService {

	/**
	 * 获得所有的职位类型
	 * @author searh
	 * @date 2012/12/24
	 * @parame
	 * @return  List<SelectJsonResult>
	 * */
	public List<SelectJsonResult> getAllOffecName() throws DataAccessException;
	
	/**
	 * 查询TbOffice对象 带分页
	 * @author searh
	 * @date 2012/12/25
	 * @parame int startPage 
	 * @parame int size
	 * @return  List<TbOffice>
	 * */
	public  List<TbOffice> getOfficeByPages(int startPage,int size) throws DataAccessException;

	/**所有渠道类型总数
	 * @return
	 * @throws DataAccessException
	 */
	public int getTotalTbOffice() throws DataAccessException;;
	
	
	/**
	 * 添加TbOffice对象
	 * @author searh
	 * @date 2012/12/25
	 * @parame
	 * @return  int
	 * */
	public int addTbOffice(TbOffice office) throws DataAccessException;
	/**
	 * 修改TbOffice对象
	 * @author searh
	 * @date 2012/12/25
	 * @parame
	 * @return  int
	 * */
	public int modifyTbOffice(TbOffice office) throws DataAccessException;
	
	/**
	 * 删除TbOffice对象
	 * @author searh
	 * @date 2012/12/25
	 * @parame
	 * @return  int
	 * */
	public int deleteTbOffice(Integer id) throws DataAccessException;
}
