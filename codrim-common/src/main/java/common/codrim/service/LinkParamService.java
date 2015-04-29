/**
 * 
 */
package common.codrim.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import common.codrim.pojo.TbCodrimLinkParam;
import common.codrim.pojo.TbLinkParam;

/**
 * @author Administrator
 *
 */
public interface LinkParamService {
	
	public int insertByBatch(List<TbLinkParam> list)throws DataAccessException;
	
	public List<TbLinkParam> getLinkParamsByPutChannelId(long putChannelId)throws DataAccessException;
public List<TbCodrimLinkParam> getLinkParamsByProductId(long productId)throws DataAccessException;
public int saveLinkParam(TbLinkParam linkParam)throws DataAccessException;
public int updateLinkParam(TbLinkParam linkParam)throws DataAccessException;
public int deleteLinkParam(long id)throws DataAccessException;
	
	
	
}
