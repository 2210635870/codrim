package common.codrim.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.dao.TbWzExchangeRecordMapper;
import common.codrim.dao.TbWzUserMapper;
import common.codrim.pojo.TbWzExchangeRecord;
import common.codrim.pojo.TbWzUser;
import common.codrim.service.WzExchangeService;
import common.codrim.wz.constant.DataConstant;
import common.codrim.wz.sql.result.ExchangeRecordInfo;

@Service
@Transactional
public class WzExchangeServiceImpl implements WzExchangeService {
	
	@Autowired
	private TbWzExchangeRecordMapper exchangeDao;
	
	@Autowired
	private TbWzUserMapper userDao;

	@Override
	public boolean exchange(int exchangeType, double money, long gold, long userId, String zfbOrPhone) throws DataAccessException {
		boolean exchangeResult = true;
		
		TbWzUser user = userDao.selectByPrimaryKey(userId);
		if (user != null) {
			long balance = user.getBalance();
			if (balance >= gold) {
				TbWzExchangeRecord record = new TbWzExchangeRecord();
				record.setUserId(userId);
				record.setExchangeGold(gold);
				record.setExchangeMoney(money);
				record.setExchangeType(exchangeType);
				record.setExchangeDate(new Date());
				record.setReviewStatus(DataConstant.REVIEW_STATUS_PENDING);
				
				if (DataConstant.EXCHANGE_TYPE_ZFB == exchangeType) {
					record.setZfbAccount(zfbOrPhone);
					
				} else if (DataConstant.EXCHANGE_TYPE_HF == exchangeType) {
					record.setPhoneNumber(zfbOrPhone);
				}
				
				exchangeDao.insert(record);
				
				user.setBalance(balance - gold);
				userDao.updateByPrimaryKeySelective(user);
			} else {
				exchangeResult = false;
			}
		}
		
		return exchangeResult;
	}

	@Override
	public List<ExchangeRecordInfo> searchExchangeRecord(int startPage, int size, ExchangeRecordInfo param) throws DataAccessException {
		int start = (startPage-1) * size;
		return exchangeDao.page(start, size, param);
	}

	@Override
	public int getExchangeRecordTotalAmount(ExchangeRecordInfo param) throws DataAccessException {
		return exchangeDao.count(param);
	}

	@Override
	public ExchangeRecordInfo getExchangeRecord4Review(long recordId) throws DataAccessException {
		return exchangeDao.getExchangeRecordInfo(recordId);
	}

	@Override
	public void modifyExchange(TbWzExchangeRecord exchangeRecord) throws DataAccessException {
		
		if (DataConstant.REVIEW_STATUS_DENY == exchangeRecord.getReviewStatus()) {
			TbWzUser user = userDao.selectByPrimaryKey(exchangeRecord.getUserId());
			user.setBalance(user.getBalance() + exchangeRecord.getExchangeGold());
			userDao.updateByPrimaryKeySelective(user);
		}
		
		exchangeDao.updateByPrimaryKeySelective(exchangeRecord);
	}


}
