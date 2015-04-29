/**
 * 
 */
package common.codrim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.dao.TbCodrimLinkParamMapper;
import common.codrim.pojo.TbCodrimLinkParam;
import common.codrim.service.CodrimLinkParamService;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class CodrimLinkParamServiceImpl implements CodrimLinkParamService {
@Autowired
TbCodrimLinkParamMapper codrimLinkParamDao;

	/* (non-Javadoc)
	 * @see common.codrim.service.CodrimLinkParamService#selectAll()
	 */
	@Override
	public List<TbCodrimLinkParam> selectAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return codrimLinkParamDao.selectAll();
	}

	/* (non-Javadoc)
	 * @see common.codrim.service.CodrimLinkParamService#updateCodrimLinkParam(common.codrim.pojo.TbCodrimLinkParam)
	 */
	@Override
	public int updateCodrimLinkParam(TbCodrimLinkParam codrimLinkParam)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return codrimLinkParamDao.updateByPrimaryKeySelective(codrimLinkParam);
	}

	/* (non-Javadoc)
	 * @see common.codrim.service.CodrimLinkParamService#saveCodrimLinkParam(common.codrim.pojo.TbCodrimLinkParam)
	 */
	@Override
	public int saveCodrimLinkParam(TbCodrimLinkParam codrimLinkParam)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return codrimLinkParamDao.insertSelective(codrimLinkParam);
	}

	/* (non-Javadoc)
	 * @see common.codrim.service.CodrimLinkParamService#validCodrimLinkParamName(java.lang.String, java.lang.String)
	 */
	@Override
	public TbCodrimLinkParam validCodrimLinkParamName(String type, String name)
			throws DataAccessException {
		// TODO Auto-generated method stub
		TbCodrimLinkParam codrimLinkParam=null;
		if(type.equals("zh"))
			codrimLinkParam =codrimLinkParamDao.validCodrimLinkParamZhName(name);
		else 		
			codrimLinkParam =codrimLinkParamDao.validCodrimLinkParamEnName(name);

		return codrimLinkParam;
	}

}
