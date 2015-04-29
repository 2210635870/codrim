package common.codrim.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import common.codrim.pojo.TbWzNews;

public interface WzNewsService {
	
	TbWzNews selectByPrimaryKey(Long id) throws DataAccessException;
	
	int deleteByPrimaryKey(Long id) throws DataAccessException;
	 
	int insert(TbWzNews news) throws DataAccessException;
	
	int updateByPrimaryKey(TbWzNews news) throws DataAccessException;
	
	List<TbWzNews> selectList(Map<String, Object> params) throws DataAccessException;
	
	int getTotalNum(Map<String, Object> params) throws DataAccessException;
	
	List<Map<String, Object>> selectForScreenlock(Map<String, Object> params);
}
