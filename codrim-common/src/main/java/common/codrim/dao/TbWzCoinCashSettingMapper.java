package common.codrim.dao;

import java.util.List;

import common.codrim.pojo.TbWzCoinCashSetting;

public interface TbWzCoinCashSettingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbWzCoinCashSetting record);

    int insertSelective(TbWzCoinCashSetting record);

    TbWzCoinCashSetting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbWzCoinCashSetting record);

    int updateByPrimaryKey(TbWzCoinCashSetting record);

    TbWzCoinCashSetting getCashSettingByCashType(short cashType);
    
	List<TbWzCoinCashSetting> getCashSettings();
}