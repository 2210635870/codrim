package common.codrim.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import common.codrim.pojo.TbWzImageUrlSetting;

@Component
public interface TbWzImageUrlSettingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbWzImageUrlSetting record);

    int insertSelective(TbWzImageUrlSetting record);

    TbWzImageUrlSetting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbWzImageUrlSetting record);

    int updateByPrimaryKey(TbWzImageUrlSetting record);

	List<TbWzImageUrlSetting> selectUrlSettings();

}