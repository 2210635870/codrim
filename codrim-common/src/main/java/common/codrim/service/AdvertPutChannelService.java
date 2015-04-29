/**
 * 
 */
package common.codrim.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import common.codrim.common.SelectResultByCodition;
import common.codrim.pojo.TbAdvertPutChannel;

/**
 * @author Administrator
 *
 */
public interface AdvertPutChannelService {
	int selectCountProductAdvertByProductIdAndStatus(SelectResultByCodition codition) throws DataAccessException;
int saveAdvertPutAdvert(TbAdvertPutChannel advertPutAdvert)throws DataAccessException;
int selectPutChannelNumByAdvertId(long advertId)throws DataAccessException;
List<TbAdvertPutChannel> geTbAdvertPutChannelList(long advertId)throws DataAccessException;
}
