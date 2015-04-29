package common.codrim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.dao.TbWzThirdDeviceUidMapper;
import common.codrim.pojo.TbWzThirdDeviceUid;
import common.codrim.service.WzThirdDeviceUidService;

@Service
@Transactional
public class WzThirdDeviceUidServiceImpl implements WzThirdDeviceUidService {
@Autowired
TbWzThirdDeviceUidMapper thirdDeviceUidDao;

@Override
public  TbWzThirdDeviceUid getThirdDeviceUidByUidAndDifId(String unionid,String difId)
		throws DataAccessException {
	// TODO Auto-generated method stub
	return thirdDeviceUidDao.getThirdDeviceUidByUidAndDifId(unionid,difId);
}

@Override
public int updateThirdDeviceUidByUnionidAndDifId(TbWzThirdDeviceUid thirdDeviceUid)
		throws DataAccessException {
	// TODO Auto-generated method stub
	return thirdDeviceUidDao.updateThirdDeviceUidByUnionidAndDifId(thirdDeviceUid);
}

@Override
public int saveThirdDeviceUid(TbWzThirdDeviceUid thirdDeviceUid)
		throws DataAccessException {
	// TODO Auto-generated method stub
	return thirdDeviceUidDao.insertSelective(thirdDeviceUid);
}

@Override
public TbWzThirdDeviceUid getThirdDeviceUidByUidAndDifIdAndDeviceId(
		String unionid, String difId, String deviceId)
		throws DataAccessException {
	// TODO Auto-generated method stub
	return thirdDeviceUidDao.getThirdDeviceUidByUidAndDifIdAndDeviceId(unionid,difId,deviceId);
}

@Override
public int updateThirdDeviceUidById(TbWzThirdDeviceUid thirdDeviceUid)
		throws DataAccessException {
	// TODO Auto-generated method stub
	return thirdDeviceUidDao.updateByPrimaryKeySelective(thirdDeviceUid);
}

@Override
public TbWzThirdDeviceUid getThirdDeviceUidById(long id)
		throws DataAccessException {
	// TODO Auto-generated method stub
	return thirdDeviceUidDao.selectByPrimaryKey(id);
}
}
