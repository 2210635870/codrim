/**
 * 
 */
package common.codrim.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import common.codrim.common.SelectResultByCodition;
import common.codrim.pojo.TbAdvert;

/**
 * @author Administrator
 *
 */
public interface AdvertService {
	int selectCountAdvertsByProductIdAndStatus(SelectResultByCodition codition) throws DataAccessException;
	int saveAdvert(TbAdvert advert)throws DataAccessException;
	List<TbAdvert> getAdvertsByproductId(long productId) throws DataAccessException;;
   int updateAdvert(TbAdvert advert)throws DataAccessException;
   TbAdvert geTbAdvertById(long id)throws DataAccessException;
}
