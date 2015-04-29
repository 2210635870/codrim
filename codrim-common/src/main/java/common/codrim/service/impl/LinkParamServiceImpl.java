/**
 * 
 */
package common.codrim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.dao.TbLinkParamMapper;
import common.codrim.pojo.TbCodrimLinkParam;
import common.codrim.pojo.TbLinkParam;
import common.codrim.service.LinkParamService;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class LinkParamServiceImpl implements LinkParamService {
@Autowired
TbLinkParamMapper linkParamDao;
	/* (non-Javadoc)
	 * @see common.codrim.service.LinkParamService#getLinkParamsByPutChannelId(long)
	 */
	@Override
	public List<TbLinkParam> getLinkParamsByPutChannelId(long putChannelId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see common.codrim.service.LinkParamService#getLinkParamsByProductId(long)
	 */
	@Override
	public List<TbCodrimLinkParam> getLinkParamsByProductId(long productId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return linkParamDao.getLinkParamsByProductId(productId);
	}

	/* (non-Javadoc)
	 * @see common.codrim.service.LinkParamService#saveLinkParam(common.codrim.pojo.TbLinkParam)
	 */
	@Override
	public int saveLinkParam(TbLinkParam linkParam) throws DataAccessException {
		// TODO Auto-generated method stub
		return linkParamDao.insertSelective(linkParam);
	}

	/* (non-Javadoc)
	 * @see common.codrim.service.LinkParamService#updateLinkParam(common.codrim.pojo.TbLinkParam)
	 */
	@Override
	public int updateLinkParam(TbLinkParam linkParam)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return linkParamDao.updateByPrimaryKeySelective(linkParam);
	}

	/* (non-Javadoc)
	 * @see common.codrim.service.LinkParamService#deleteLinkParam(common.codrim.pojo.TbLinkParam)
	 */
	@Override
	public int deleteLinkParam(long id)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return linkParamDao.deleteByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see common.codrim.service.LinkParamService#insertByBatch(java.util.List)
	 */
	@Override
	public int insertByBatch(List<TbLinkParam> list) throws DataAccessException {
		// TODO Auto-generated method stub
		return linkParamDao.insertByBatch(list);
	}

}
