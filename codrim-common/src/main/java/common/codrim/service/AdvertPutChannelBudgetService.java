/**
 * 
 */
package common.codrim.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import common.codrim.common.SelectResultByCodition;
import common.codrim.pojo.TbAdvertPutChannel;
import common.codrim.pojo.TbAdvertPutChannelBudget;

/**
 * @author Administrator
 *
 */
public interface AdvertPutChannelBudgetService {
	
int saveAdvertPutAdvertBudgetByBatch(List<TbAdvertPutChannelBudget> list)throws DataAccessException;

}
