package common.codrim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.dao.TbAdvertPutChannelBudgetMapper;
import common.codrim.pojo.TbAdvertPutChannelBudget;
import common.codrim.service.AdvertPutChannelBudgetService;
@Service
@Transactional
public class AdvertPutChannelBudgetServiceImpl implements
		AdvertPutChannelBudgetService {
@Autowired
TbAdvertPutChannelBudgetMapper putChannelBudgetDao;
	@Override
	public int saveAdvertPutAdvertBudgetByBatch(
			List<TbAdvertPutChannelBudget> list) throws DataAccessException {
		// TODO Auto-generated method stub
		return putChannelBudgetDao.insertByBatch(list);
	}

}
