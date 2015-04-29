package common.codrim.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import common.codrim.common.SelectJsonResult;
import common.codrim.pojo.TbAdmin;
import common.codrim.pojo.TbChannel;

public interface ChannelService {
	/**
	 * 获各全部
	 * @date 2014/12/17
	 * @param 
	 * @return List<TbChannel> 
	 * */
	public List<TbChannel> getAllTbChannels() throws DataAccessException;
	
	
	/**
	 * 根据渠道名获取渠道信息
	 * @date 2014/12/17
	 * @param 
	 * @return List<TbChannel> 
	 * */
	public TbChannel getTbChannelByChannelName(String channelName) throws DataAccessException;
	/**
	 * 得到总数
	 * @date 2014/12/17
	 * @param 
	 * @return int 
	 * */
	public int getTotalChannels() throws DataAccessException;
	
	/** 
	  *渠道登录
	  * @author xulin
	  * @date 2015年1月5日
	  *  @param email
	  *  @param password
	  *  @return
	  *  @throws DataAccessException
	  *  int
	  */ 
	public TbAdmin channelLogin(String email,String password)throws DataAccessException;
	/** 
	  *根据渠道号获取渠道
	  * @author xulin
	  * @date 2014年12月30日
	  *  @param channelNum
	  *  @return
	  *  @throws DataAccessException
	  *  TbChannel
	  */ 
	public TbChannel getChannelByChannelNumber(String channelNum)throws DataAccessException;
	/**
	 * 分页得到 数据
	 * @date 2014/12/17
	 * @param int startPage
	 * @param int size
	 * @return List<TbChannel> 
	 * */
	public List<TbChannel> getChannelsByPage(int startPage ,int size) throws DataAccessException;
	/**
	 * 添加TbChannel对象
	 * @date 2014/12/17
	 * @param TbChannel channel 
	 * @return int 
	 * */
	public int addChannel(TbChannel channel) throws DataAccessException;
	
	/** 
	  *根据渠道联系人名字获取渠道名字
	  * @author xulin
	  * @date 2014年12月25日
	  *  @param contactName
	  *  @return
	  *  @throws DataAccessException
	  *  List<SelectJsonResult>
	  */ 
	public List<SelectJsonResult> getChannelNameByContactName(String contactName) throws DataAccessException;
	/**
	 * 修改TbChannel对象
	 * @date 2014/12/17
	 * @param  TbChannel channel 
	 * @return int 
	 * */
	public int modifyChannel(TbChannel channel) throws DataAccessException;
	/**
	 * 通过id 删除TbChannel对象
	 * @date 2014/12/17
	 * @param  long id
	 * @return int 
	 * */
	public int deleteById(long id) throws DataAccessException;
	/**
	 * 得到全部的渠道名
	 * @date 2014/12/17
	 * @param 
	 * @return int 
	 * */
	public List<SelectJsonResult> getAllChannelName() throws DataAccessException;
	/**
	 * 得到全部的渠道信息
	 * @date 2014/12/17
	 * @param 
	 * @return int 
	 * */
	public List<SelectJsonResult> getAllChannel() throws DataAccessException;
	/**
	 * 查找渠道名是否存在
	 * @date 2014/12/17
	 * @param 
	 * @return int 
	 * */
	public TbChannel checkName(String name) throws DataAccessException;
	
	public TbChannel getById(long id) throws DataAccessException;
	
	public boolean isValidCompanyName(String companyName) throws DataAccessException;
}
