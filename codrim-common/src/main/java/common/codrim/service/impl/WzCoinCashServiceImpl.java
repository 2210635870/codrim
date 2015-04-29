package common.codrim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.dao.TbWzCoinCashSettingMapper;
import common.codrim.pojo.TbWzCoinCashSetting;
import common.codrim.service.WzCoinCashService;
@Service
@Transactional
public class WzCoinCashServiceImpl implements WzCoinCashService {
@Autowired
TbWzCoinCashSettingMapper cashSettingDao;


	@Override
	public List<TbWzCoinCashSetting> getCashSettings()
			throws DataAccessException {
		// TODO Auto-generated method stub
		return cashSettingDao.getCashSettings();
	}

	@Override
	public int saveCoinCashSetting(TbWzCoinCashSetting coinCashSetting)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return cashSettingDao.insertSelective(coinCashSetting);
	}

	@Override
	public int updateCoinCashSetting(TbWzCoinCashSetting coinCashSetting)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return cashSettingDao.updateByPrimaryKeySelective(coinCashSetting);
	}

	@Override
	public TbWzCoinCashSetting getCashSettingByCashType(short cashType)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return cashSettingDao.getCashSettingByCashType(cashType);
	}

}
