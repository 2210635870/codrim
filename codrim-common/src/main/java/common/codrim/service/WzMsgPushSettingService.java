package common.codrim.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import common.codrim.pojo.TbWzMsgPushSetting;

public interface WzMsgPushSettingService {
	
	TbWzMsgPushSetting selectByPrimaryKey(Long id) throws DataAccessException;
	
	int deleteByPrimaryKey(Long id) throws DataAccessException;
	 
	int insert(TbWzMsgPushSetting setting) throws DataAccessException;
	
	int updateByPrimaryKey(TbWzMsgPushSetting setting) throws DataAccessException;
	
	List<TbWzMsgPushSetting> selectList(Map<String, Object> params) throws DataAccessException;
	
	int getTotalNum(Map<String, Object> params) throws DataAccessException;
	
	void updateValueByName(List<TbWzMsgPushSetting> list) throws DataAccessException;
}
