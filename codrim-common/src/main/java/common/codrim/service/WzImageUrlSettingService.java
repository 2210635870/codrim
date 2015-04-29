package common.codrim.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import common.codrim.pojo.TbWzImageUrlSetting;

public interface WzImageUrlSettingService {
public List<TbWzImageUrlSetting> getTbWzImageUrlSettings() throws DataAccessException;

public int saveImageUrlSetting(TbWzImageUrlSetting urlSetting)throws DataAccessException;
	
	
public int updateImageUrlSetting(TbWzImageUrlSetting urlSetting)throws DataAccessException;
}
