package common.codrim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.dao.TbWzAppUpgradeMapper;
import common.codrim.dao.TbWzCommonSettingMapper;
import common.codrim.pojo.TbWzAppUpgrade;
import common.codrim.pojo.TbWzCommonSetting;
import common.codrim.service.WzSettingService;

@Service
@Transactional
public class WzSettingServiceImpl implements WzSettingService {
	
	@Autowired
	private TbWzCommonSettingMapper commonSettingDao;
	
	@Autowired
	private TbWzAppUpgradeMapper appUpgradeDao;

	@Override
	public TbWzCommonSetting getCommonSetting() {
		return commonSettingDao.selectByPrimaryKey(1L);
	}

	@Override
	public void modifyCommonSetting(TbWzCommonSetting setting) {
		commonSettingDao.updateByPrimaryKeySelective(setting);
	}

	@Override
	public TbWzAppUpgrade getAppUpgradeInfo(Long id) {
		return appUpgradeDao.selectByPrimaryKey(id);
	}


	@Override
	public void modifyAppUpgradeInfo(TbWzAppUpgrade appUpgrade) {
		appUpgradeDao.updateByPrimaryKeySelective(appUpgrade);
	}	
	@Override
	public List<TbWzAppUpgrade> getAllAppUpgrade() {
		// TODO Auto-generated method stub
		return appUpgradeDao.selectAllAppUpgrade();
	}	

}
