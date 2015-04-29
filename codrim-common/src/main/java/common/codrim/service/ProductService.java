/**
 * 
 */
package common.codrim.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import common.codrim.common.SelectJsonResult;
import common.codrim.common.SelectResultByCodition;
import common.codrim.common.ViewProductToCountBean;
import common.codrim.pojo.TbProduct;

/**
 * @author Administrator
 *
 */
public interface  ProductService {
	public int save(TbProduct product)  throws DataAccessException;
	List<ViewProductToCountBean> selectViewProductToCount(SelectResultByCodition codition) throws DataAccessException;
	TbProduct geTbProduct(long id) throws DataAccessException;
	int update(TbProduct product) throws DataAccessException;
	List<SelectJsonResult> getProductNameByCustomerName(String customerName)throws DataAccessException;
}
