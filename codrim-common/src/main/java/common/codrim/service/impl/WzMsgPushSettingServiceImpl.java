package common.codrim.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.dao.TbWzMsgPushSettingMapper;
import common.codrim.dao.TbWzPointsLogMapper;
import common.codrim.pojo.TbWzMsgPushSetting;
import common.codrim.pojo.TbWzPointsLog;
import common.codrim.service.WzMsgPushSettingService;
import common.codrim.wz.constant.DataConstant;

@Service
@Transactional
public class WzMsgPushSettingServiceImpl implements WzMsgPushSettingService {

	@Autowired
	private TbWzMsgPushSettingMapper msgPushSettingDao;
	
	@Autowired
	private TbWzPointsLogMapper pointsLogDao;
	
	@Override
	public TbWzMsgPushSetting selectByPrimaryKey(Long id) throws DataAccessException {
		return msgPushSettingDao.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(Long id) throws DataAccessException {
		return msgPushSettingDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TbWzMsgPushSetting setting) throws DataAccessException {
		return msgPushSettingDao.insert(setting);
	}

	@Override
	public int updateByPrimaryKey(TbWzMsgPushSetting setting) throws DataAccessException {
		return msgPushSettingDao.updateByPrimaryKey(setting);
	}

	@Override
	public List<TbWzMsgPushSetting> selectList(Map<String, Object> params) throws DataAccessException {
		return msgPushSettingDao.selectList(params);
	}

	@Override
	public int getTotalNum(Map<String, Object> params) throws DataAccessException {
		return msgPushSettingDao.getTotalNum(params);
	}

	public void updateValueByName(List<TbWzMsgPushSetting> list) throws DataAccessException {
		msgPushSettingDao.updateValueByName(list);
	}
}
