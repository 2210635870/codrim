package common.codrim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.common.SelectResultByCodition;
import common.codrim.dao.TbWzGameH5Mapper;
import common.codrim.pojo.TbWzGameH5;
import common.codrim.service.WzGameH5Service;
@Service
@Transactional
public class WzGameH5ServiceImpl implements WzGameH5Service {
@Autowired
TbWzGameH5Mapper h5Dao;
	@Override
	public int saveH5(TbWzGameH5 h5) throws DataAccessException {
		// TODO Auto-generated method stub
		return h5Dao.insertSelective(h5);
	}

	@Override
	public int update(TbWzGameH5 h5) throws DataAccessException {
		// TODO Auto-generated method stub
		return h5Dao.updateByPrimaryKeySelective(h5);
	}

	@Override
	public List<TbWzGameH5> getGameH5List(SelectResultByCodition codition)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return h5Dao.getGameH5List(codition);
	}

	@Override
	public int getTotalGameH5(SelectResultByCodition codition)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return h5Dao.getTotalGameH5(codition);
	}

}
