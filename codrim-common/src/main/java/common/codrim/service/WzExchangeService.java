package common.codrim.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import common.codrim.pojo.TbWzExchangeRecord;
import common.codrim.wz.sql.result.ExchangeRecordInfo;


public interface WzExchangeService {
	
	public boolean exchange(int exchangeType, double money, long gold, long userId, String zfbOrPhone) throws DataAccessException;
	
	public List<ExchangeRecordInfo> searchExchangeRecord(int startPage, int size, ExchangeRecordInfo param) throws DataAccessException;
	public int getExchangeRecordTotalAmount(ExchangeRecordInfo param) throws DataAccessException;
	
	public ExchangeRecordInfo getExchangeRecord4Review(long recordId) throws DataAccessException;
	public void modifyExchange(TbWzExchangeRecord exchangeRecord) throws DataAccessException;

}
