package common.codrim.service;

import org.springframework.dao.DataAccessException;

import common.codrim.pojo.TbWzSequence;

public interface WzSequenceService {

	public int  getId(TbWzSequence record)throws DataAccessException;

}
