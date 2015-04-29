package common.codrim.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import common.codrim.pojo.TbWzThirdDeviceUid;

public interface WzThirdDeviceUidService {
	
	public TbWzThirdDeviceUid getThirdDeviceUidByUidAndDifId(String unionid,String difId) throws DataAccessException;
	public int updateThirdDeviceUidByUnionidAndDifId(TbWzThirdDeviceUid thirdDeviceUid) throws DataAccessException;
	public int saveThirdDeviceUid(TbWzThirdDeviceUid thirdDeviceUid) throws DataAccessException;
	public TbWzThirdDeviceUid getThirdDeviceUidByUidAndDifIdAndDeviceId(
			String unionid, String difId, String deviceId)throws DataAccessException;
	public int updateThirdDeviceUidById(TbWzThirdDeviceUid thirdDeviceUid) throws DataAccessException;
	public TbWzThirdDeviceUid getThirdDeviceUidById(long id) throws DataAccessException;

	

}
