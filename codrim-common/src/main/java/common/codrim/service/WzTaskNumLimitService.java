package common.codrim.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import common.codrim.pojo.TbWzTaskNumLimit;

public interface WzTaskNumLimitService {

	public int updateTaskNumLimitWithCache(TbWzTaskNumLimit taskNumLimit)  throws DataAccessException;
	
	public List<TbWzTaskNumLimit> getAll();
	
	public TbWzTaskNumLimit getTaskNumLimitWithCache(String limitType)throws DataAccessException;
	
}
