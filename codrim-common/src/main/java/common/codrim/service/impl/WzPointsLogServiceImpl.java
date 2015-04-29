package common.codrim.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.dao.TbWzPointsLogMapper;
import common.codrim.pojo.TbWzPointsLog;
import common.codrim.service.WzPointsLogService;
@Service
@Transactional
public class WzPointsLogServiceImpl implements WzPointsLogService {
@Autowired
TbWzPointsLogMapper pointsLogDao;
	@Override
	public int savePintsLog(TbWzPointsLog pointsLog) throws DataAccessException {
		// TODO Auto-generated method stub
		return pointsLogDao.insertSelective(pointsLog);
	}
	@Override
	public List<TbWzPointsLog> getPointsLogList(int startPage, int size,
			long userId) throws DataAccessException {
		// TODO Auto-generated method stub
		return pointsLogDao.getPointsLogList( startPage,  size, userId);
	}
	@Override
	public int getPointsLogTotalAmount(long userId) throws DataAccessException {
		// TODO Auto-generated method stub
		return pointsLogDao.getPointsLogTotalAmount( userId);
	}
	@Override
	public int getNowDayInvites(long userId, Date date)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return pointsLogDao.getNowDayInvites(userId, date);
	}
	
	@Override
	public TbWzPointsLog selectInvaiteesByInviterLog(TbWzPointsLog log) {
		return pointsLogDao.selectInvaiteesByInviterLog(log);
	}

}
