/**
 * 
 */
package common.codrim.service;

import org.springframework.dao.DataAccessException;

import common.codrim.pojo.TbRandomSendNum;

/**
 * @author Administrator
 *
 */
public interface RandomSendNumService {
    
	/**
	 * 添加数据
	 * @date 2014/12/18
	 * @param  TbRandomSendNum randomSendNum
	 * @return int
	 * */
	public int addRandomSendNum(TbRandomSendNum randomSendNum) throws DataAccessException;

	/** 
	  *更新对应发送次数 
	  * @author xulin
	  * @date 2014年12月17日
	  *  @param randomSendNum 
	  *  @return
	  *  TbRandomSendNum
	  */ 
	public int updateRandomSendNum(TbRandomSendNum randomSendNum) throws DataAccessException;
	/** 
	  *搜索对应发送次数 通过 产品 渠道  手机标识
	  * @author xulin
	  * @date 2014年12月17日
	  *  @param randomSendNum 
	  *  @return
	  *  TbRandomSendNum
	  */ 
	public TbRandomSendNum selectRandomSendNumByPCM(TbRandomSendNum randomSendNum) throws DataAccessException;
	
}
