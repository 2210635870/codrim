/**
 * 
 */
package common.codrim.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import common.codrim.pojo.TbCodrimLinkParam;

/**
 * @author Administrator
 *
 */
public interface CodrimLinkParamService {

	public List<TbCodrimLinkParam> selectAll() throws DataAccessException;
	public int updateCodrimLinkParam(TbCodrimLinkParam codrimLinkParam)throws DataAccessException;
	public int saveCodrimLinkParam(TbCodrimLinkParam codrimLinkParam)throws DataAccessException;
	/** 
	  *
	  * @author xulin
	  * @date 2015年3月3日
	  *  @return
	  *  TbCodrimLinkParam
	  */ 
	public TbCodrimLinkParam validCodrimLinkParamName(String type,String name)throws DataAccessException;;
	
	
	
}
