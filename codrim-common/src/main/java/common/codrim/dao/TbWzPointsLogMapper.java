package common.codrim.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import common.codrim.pojo.TbWzPointsLog;

public interface TbWzPointsLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbWzPointsLog record);

    int insertSelective(TbWzPointsLog record);

    TbWzPointsLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbWzPointsLog record);

    int updateByPrimaryKey(TbWzPointsLog record);

	List<TbWzPointsLog> getPointsLogList(@Param("startPage")int startPage, @Param("size")int size, @Param("userId")long userId);

	int getPointsLogTotalAmount(long userId);

	int getNowDayInvites(@Param("userId")long userId, @Param("date")Date date);
	
	TbWzPointsLog selectInvaiteesByInviterLog(TbWzPointsLog log);
}