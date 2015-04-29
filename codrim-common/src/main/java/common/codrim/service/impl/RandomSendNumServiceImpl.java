/**
 * 
 */
package common.codrim.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.dao.TbRandomSendNumMapper;
import common.codrim.pojo.TbRandomSendNum;
import common.codrim.service.RandomSendNumService;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class RandomSendNumServiceImpl implements RandomSendNumService{
@Autowired
TbRandomSendNumMapper randomSendNumDao;
	/**
	 * 添加数据
	 * @date 2014/12/18
	 * @param  TbRandomSendNum randomSendNum
	 * @return int
	 * */
	@Override
	public int addRandomSendNum(TbRandomSendNum randomSendNum) throws DataAccessException{
		// TODO Auto-generated method stub
		return randomSendNumDao.insertSelective(randomSendNum);
	}


	/** 
	  *搜索对应发送次数 通过 产品 渠道  手机标识
	  * @author xulin
	  * @date 2014年12月17日
	  *  @param randomSendNum 
	  *  @return
	  *  TbRandomSendNum
	  */ 
	@Override
	public TbRandomSendNum selectRandomSendNumByPCM(TbRandomSendNum randomSendNum) throws DataAccessException{
		// TODO Auto-generated method stub
		return randomSendNumDao.selectByPCM(randomSendNum);
	}
	
	/** 
	  *更新对应发送次数 
	  * @author xulin
	  * @date 2014年12月17日
	  *  @param randomSendNum 
	  *  @return
	  *  TbRandomSendNum
	  */ 
	@Override
	public int updateRandomSendNum(TbRandomSendNum randomSendNum) throws DataAccessException{
		// TODO Auto-generated method stub
		return randomSendNumDao.updateByPrimaryKeySelective(randomSendNum);
	}
	

}
