package common.codrim.service;

import java.util.List;

import common.codrim.pojo.TbWzAppUpgrade;
import common.codrim.pojo.TbWzCommonSetting;

public interface WzSettingService {

	public TbWzCommonSetting getCommonSetting();
	public void modifyCommonSetting(TbWzCommonSetting setting);
	
	public TbWzAppUpgrade getAppUpgradeInfo(Long id);
	public void modifyAppUpgradeInfo(TbWzAppUpgrade appUpgrade);
	public List<TbWzAppUpgrade> getAllAppUpgrade();
}
