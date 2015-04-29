/**
 * 
 */
package common.codrim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.common.SelectResultByCodition;
import common.codrim.dao.TbAdvertPutChannelMapper;
import common.codrim.pojo.TbAdvertPutChannel;
import common.codrim.service.AdvertPutChannelService;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class AdvertPutChannelServiceImpl implements AdvertPutChannelService {
@Autowired
TbAdvertPutChannelMapper putChannelDao;
	/* (non-Javadoc)
	 * @see common.codrim.service.ProductPutAdvertService#selectCountProductAdvertByProductIdAndStatus(common.codrim.common.SelectResultByCodition)
	 */
	@Override
	public int selectCountProductAdvertByProductIdAndStatus(
			SelectResultByCodition codition) throws DataAccessException {
		// TODO Auto-generated method stub
		return putChannelDao.selectCountProductAdvertByProductIdAndStatus(codition);
	}

	/* (non-Javadoc)
	 * @see common.codrim.service.ProductPutAdvertService#saveProductPutAdvert(common.codrim.pojo.TbProductPutAdvert)
	 */
	@Override
	public int saveAdvertPutAdvert(TbAdvertPutChannel productPutAdvert)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return putChannelDao.insertSelective(productPutAdvert);
	}

	/* (non-Javadoc)
	 * @see common.codrim.service.AdvertPutChannelService#selectPutChannelNumByAdvertId(long)
	 */
	@Override
	public int selectPutChannelNumByAdvertId(long advertId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return putChannelDao.selectPutChannelNumByAdvertId(advertId);
	}

	@Override
	public List<TbAdvertPutChannel> geTbAdvertPutChannelList(long advertId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return putChannelDao.selectPutChannelByAdvertId(advertId);
	}

}
