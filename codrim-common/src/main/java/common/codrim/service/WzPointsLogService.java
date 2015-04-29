package common.codrim.service;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import common.codrim.pojo.TbWzPointsLog;

public interface WzPointsLogService {

	
     int savePintsLog(TbWzPointsLog pointsLog)throws DataAccessException;
	
	List<TbWzPointsLog>  getPointsLogList(int startPage,int size,long userId)throws DataAccessException;
	
	int getPointsLogTotalAmount(long userId)throws DataAccessException;
	int getNowDayInvites(long userId,Date date)throws DataAccessException;
	
	TbWzPointsLog selectInvaiteesByInviterLog(TbWzPointsLog log);
}
