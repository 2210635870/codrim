package common.codrim.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.dao.TbWzImageUrlSettingMapper;
import common.codrim.pojo.TbWzImageUrlSetting;
import common.codrim.pojo.TbWzWallpaper;
import common.codrim.service.WzImageUrlSettingService;

@Service
@Transactional
public class WzImageUrlSettingServiceImpl implements WzImageUrlSettingService {
	
	@Value("#{configProperties['upload.root']}")
	private String uploadRoot;
	
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
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("showSer", urlSetting.getShowSer());
		TbWzImageUrlSetting oldSerPO = urlSettingDao.selectList(params).get(0);
		TbWzImageUrlSetting oldUpdatePO = urlSettingDao
				.selectByPrimaryKey(urlSetting.getId());
		oldSerPO.setShowSer(oldUpdatePO.getShowSer());

		urlSettingDao.updateByPrimaryKeySelective(oldSerPO);
		return urlSettingDao.updateByPrimaryKeySelective(urlSetting);
	}

	@Override
	public int deleteImageUrlSetting(Integer id) throws DataAccessException {
		TbWzImageUrlSetting urlSetting = urlSettingDao.selectByPrimaryKey(id);
		File originalFile = new File(uploadRoot + urlSetting.getUrl());

		int num = urlSettingDao.deleteByPrimaryKey(id);
		
		urlSettingDao.updateShowSerAfterDelete(urlSetting.getShowSer());
		if (originalFile.exists()) {
			FileUtils.deleteQuietly(originalFile);
		}
		return num;
	}

}
