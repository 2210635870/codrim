package common.codrim.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.common.Contans;
import common.codrim.dao.TbWzTaskNumLimitMapper;
import common.codrim.pojo.TbWzTaskNumLimit;
import common.codrim.service.WzTaskNumLimitService;
import common.codrim.util.JsonUtil;
import common.codrim.util.MemcacheUtil;

@Service
@Transactional
public class WzTaskNumLimitServiceImpl implements WzTaskNumLimitService{
@Autowired
TbWzTaskNumLimitMapper limitDao;
@Autowired
MemcacheUtil memcacheUtil;
@Autowired
JsonUtil jsonUtil;

@Override
public int updateTaskNumLimitWithCache(TbWzTaskNumLimit taskNumLimit)
		throws DataAccessException {
	// TODO Auto-generated method stub
	int i=limitDao.updateByPrimaryKeySelective(taskNumLimit);
	if(i>0){
		memcacheUtil.updateCache(Contans.TASK_NUM_LIMIT_KEY+taskNumLimit.getLimitType(), jsonUtil.convertObjectToString(taskNumLimit),new Date( Contans.CACHE_SEVEN_DAY));
	}
	return i;
}

@Override
public TbWzTaskNumLimit getTaskNumLimitWithCache(String limitType)
		throws DataAccessException {
	// TODO Auto-generated method stub
	TbWzTaskNumLimit numLimit=null;
	String key=Contans.TASK_NUM_LIMIT_KEY+limitType;
	String json=memcacheUtil.getCacheByKey(key);
	if(json==null||json==""){
		numLimit=limitDao.selectByLimitType(Short.parseShort(limitType));
	}else{
		numLimit=jsonUtil.convertStringToObject(json, TbWzTaskNumLimit.class);
	}
	
	return numLimit;
}

@Override
public List<TbWzTaskNumLimit> getAll() {
	// TODO Auto-generated method stub
	return limitDao.selectAll();
}

}
