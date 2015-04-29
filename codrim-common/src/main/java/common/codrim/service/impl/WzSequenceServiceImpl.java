package common.codrim.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.dao.TbWzSequenceMapper;
import common.codrim.pojo.TbWzSequence;
import common.codrim.service.WzSequenceService;

@Service
@Transactional
public class WzSequenceServiceImpl implements WzSequenceService{
@Autowired
TbWzSequenceMapper sequenceDao;

@Override
public int getId(TbWzSequence record) throws DataAccessException {
	// TODO Auto-generated method stub
	return sequenceDao.insertSelective(record);
}



}
