package common.codrim.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import common.codrim.pojo.TbWzCoinCashSetting;

public interface WzCoinCashService {

	public List<TbWzCoinCashSetting> getCashSettings()throws DataAccessException;
	
	public int saveCoinCashSetting(TbWzCoinCashSetting coinCashSetting)throws DataAccessException;
	
	public int updateCoinCashSetting(TbWzCoinCashSetting coinCashSetting)throws DataAccessException;
	
	
	public TbWzCoinCashSetting getCashSettingByCashType(short cashType)throws DataAccessException;
}
