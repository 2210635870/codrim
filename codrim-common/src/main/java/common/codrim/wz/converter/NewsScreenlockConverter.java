package common.codrim.wz.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.codrim.pojo.TbWzMsgPushSetting;
import common.codrim.pojo.TbWzNews;
import common.codrim.util.StringUtil;
import common.codrim.wz.dto.NewsScreenlockForm;
import common.codrim.wz.enums.MsgPushSettingEnum;

public class NewsScreenlockConverter extends Converter<TbWzNews, NewsScreenlockForm>{

	@Override
	public TbWzNews toPO(NewsScreenlockForm dto) {
		TbWzNews po = new TbWzNews();
		
		if(!StringUtil.isEmpty(dto.getId())) {
			po.setId(Long.parseLong(dto.getId()));
		}
		
		po.setNewsScreenLock(dto.getNewsScreenlock());
		po.setNewsUrl(dto.getNewsUrl());
		po.setTitle(dto.getTitle());
		return po;
	}

	@Override
	public NewsScreenlockForm toDTO(TbWzNews po) {
		NewsScreenlockForm dto = new NewsScreenlockForm();
		if(po.getId() != null) {
			dto.setId(po.getId().toString());
		}
		
		dto.setNewsScreenlock(po.getNewsScreenLock());
		dto.setNewsUrl(po.getNewsUrl());
		dto.setTitle(po.getTitle());
		
		return dto;
	}
	
	public Map<String, Object> msgPushSettingListToMap(List<TbWzMsgPushSetting> list) {
		
		Map<String, Object> msgPushSetting = new HashMap<String, Object>();
		
		for( TbWzMsgPushSetting item : list ) {
			System.out.println(item.getName() + "----" + item.getValue());
			String valueKey = "";
			if( item.getId() == MsgPushSettingEnum.SIGNIN.getCode() ) {
				msgPushSetting.put("signin", item.getName());
				valueKey = "signinVal";
			} else if( item.getId() == MsgPushSettingEnum.READ_INFO.getCode() ) {
				msgPushSetting.put("readInfo", item.getName());
				valueKey = "readInfoVal";
			} else if( item.getId() == MsgPushSettingEnum.INFO_DAILY_NUM.getCode() ) {
				msgPushSetting.put("msgDailyNum", item.getName());
				valueKey = "msgDailyNumVal";
			}
			msgPushSetting.put(valueKey, item.getValue() == null ? "0" : item.getValue());
		}
		
		return msgPushSetting;
	}
	
}
