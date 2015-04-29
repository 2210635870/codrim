package common.codrim.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.common.Contans;
import common.codrim.dao.TbWzImageUrlSettingMapper;
import common.codrim.pojo.TbWzImageUrlSetting;
import common.codrim.service.WzImageUrlSettingService;
import common.codrim.util.JsonUtil;
import common.codrim.util.MemcacheUtil;

@Service
@Transactional
public class WzImageUrlSettingServiceImpl implements WzImageUrlSettingService{
@Autowired
TbWzImageUrlSettingMapper urlSettingDao;


	@Override
	public List<TbWzImageUrlSetting> getTbWzImageUrlSettings()
			throws DataAccessException {
		// TODO Auto-generated method stub
		return urlSettingDao.selectUrlSettings();
	}
	@Override
	public int saveImageUrlSetting(TbWzImageUrlSetting urlSetting)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return urlSettingDao.insertSelective(urlSetting);
	}
	@Override
	public int updateImageUrlSetting(TbWzImageUrlSetting urlSetting)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return urlSettingDao.updateByPrimaryKeySelective(urlSetting);
	}

	
}
