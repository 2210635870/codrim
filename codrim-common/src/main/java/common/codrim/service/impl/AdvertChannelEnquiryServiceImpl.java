/**
 * 
 */
package common.codrim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.common.ChannelEnquiryBean;
import common.codrim.dao.TbAdvertChannelEnquiryMapper;
import common.codrim.pojo.TbAdvertChannelEnquiry;
import common.codrim.service.AdvertChannelEnquiryService;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class AdvertChannelEnquiryServiceImpl implements AdvertChannelEnquiryService{
@Autowired
TbAdvertChannelEnquiryMapper channelEnquiryDao;
	/* (non-Javadoc)
	 * @see common.codrim.service.AdvertChannelEnquiryService#saveChannelEnquiry(common.codrim.pojo.TbAdvertChannelEnquiry)
	 */
	@Override
	public int saveChannelEnquiry(TbAdvertChannelEnquiry advertChannelEnquiry)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return channelEnquiryDao.insertSelective(advertChannelEnquiry);
	}
	/* (non-Javadoc)
	 * @see common.codrim.service.AdvertChannelEnquiryService#insertByBatch(java.util.List)
	 */
	@Override
	public int insertByBatch(List<TbAdvertChannelEnquiry> list)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return channelEnquiryDao.insertByBatch(list);
	}
	/* (non-Javadoc)
	 * @see common.codrim.service.AdvertChannelEnquiryService#getChannelEnquirysByAdvertId(long)
	 */
	@Override
	public List<ChannelEnquiryBean> getChannelEnquirysByAdvertId(
			long advertId) throws DataAccessException {
		// TODO Auto-generated method stub
		return channelEnquiryDao.getChannelEnquirysByAdvertId(advertId);
	}

}
