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
import common.codrim.dao.TbAdvertMapper;
import common.codrim.pojo.TbAdvert;
import common.codrim.service.AdvertService;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class AdvertServiceImpl implements AdvertService{
@Autowired
TbAdvertMapper advertDao;

	/* (non-Javadoc)
	 * @see common.codrim.service.ProductSeriesService#selectCountProductSeriesByProductIdAndStatus(common.codrim.common.SelectResultByCodition)
	 */
	@Override
	public int selectCountAdvertsByProductIdAndStatus(
			SelectResultByCodition codition) throws DataAccessException {
		// TODO Auto-generated method stub
		return advertDao.selectCountAdvertsByProductIdAndStatus(codition);
	}

	/* (non-Javadoc)
	 * @see common.codrim.service.ProductSeriesService#saveProductSeries(common.codrim.pojo.TbProductSeries)
	 */
	@Override
	public int saveAdvert(TbAdvert advert)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return advertDao.insertSelective(advert);
	}

	/* (non-Javadoc)
	 * @see common.codrim.service.AdvertService#getAdvertsByproductId()
	 */
	@Override
	public List<TbAdvert> getAdvertsByproductId(long productId) throws DataAccessException {
		// TODO Auto-generated method stub
		return advertDao.getAdvertsByproductId(productId);
	}

	/* (non-Javadoc)
	 * @see common.codrim.service.AdvertService#updateAdvert(common.codrim.pojo.TbAdvert)
	 */
	@Override
	public int updateAdvert(TbAdvert advert) throws DataAccessException {
		// TODO Auto-generated method stub
		return advertDao.updateByPrimaryKeySelective(advert);
	}

	/* (non-Javadoc)
	 * @see common.codrim.service.AdvertService#geTbAdvertById(long)
	 */
	@Override
	public TbAdvert geTbAdvertById(long id) throws DataAccessException {
		// TODO Auto-generated method stub
		return advertDao.selectByPrimaryKey(id);
	}

}
