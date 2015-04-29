package common.codrim.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.common.SelectJsonResult;
import common.codrim.dao.TbChannelMapper;
import common.codrim.pojo.TbAdmin;
import common.codrim.pojo.TbChannel;
import common.codrim.service.ChannelService;
@Service
@Transactional
public class ChannelServiceImpl implements ChannelService{
@Autowired
private TbChannelMapper channelDao;
	/**
	 * 获各全部
	 * @date 2014/12/17
	 * @param 
	 * @return List<TbChannel> 
	 * */
	@Override
	public List<TbChannel> getAllTbChannels() throws DataAccessException{
		// TODO Auto-generated method stub
		return channelDao.selectAll();
	}
	/**
	 * 得到全部的渠道名
	 * @date 2014/12/17
	 * @param 
	 * @return int 
	 * */
	@Override
	public List<SelectJsonResult> getAllChannelName() throws DataAccessException{
		List<TbChannel> list= channelDao.selectAll();
		List<SelectJsonResult> nameList=null;
		if(list!=null&&list.size()>0){
			nameList=new ArrayList<SelectJsonResult>();
			for(TbChannel channel:list){
				SelectJsonResult json=new SelectJsonResult();
				json.setId(channel.getId());
				json.setText(channel.getChannelName());
				nameList.add(json);
			}
	}
		return nameList;
	}
	/**
	 * 得到总数
	 * @date 2014/12/17
	 * @param 
	 * @return int 
	 * */
	@Override
	public int getTotalChannels() throws DataAccessException{
		// TODO Auto-generated method stub
		return channelDao.getTotalNum();
	}
	/**
	 * 分页得到 数据
	 * @date 2014/12/17
	 * @param int startPage
	 * @param int size
	 * @return List<TbChannel> 
	 * */
	@Override
	public List<TbChannel> getChannelsByPage(int startPage, int size) throws DataAccessException{
		// TODO Auto-generated method stub
		int start=(startPage-1)*size;
		
		return channelDao.selectChannelsByPage(start, size);
	}
	/**
	 * 添加TbChannel对象
	 * @date 2014/12/17
	 * @param TbChannel channel 
	 * @return int 
	 * */
	@Override
	public int addChannel(TbChannel channel) throws DataAccessException{
		// TODO Auto-generated method stub
		return channelDao.insertSelective(channel);
	}
	/**
	 * 修改TbChannel对象
	 * @date 2014/12/17
	 * @param  TbChannel channel 
	 * @return int 
	 * */
	@Override
	public int modifyChannel(TbChannel channel) throws DataAccessException{
		// TODO Auto-generated method stub
		return channelDao.updateByPrimaryKeySelective(channel);
	}
	/**
	 * 通过id 删除TbChannel对象
	 * @date 2014/12/17
	 * @param  long id
	 * @return int 
	 * */
	@Override
	public int deleteById(long id) throws DataAccessException{
		// TODO Auto-generated method stub
		return channelDao.deleteByPrimaryKey(id);
	}
	/**
	 * 查找渠道名是否存在
	 * @date 2014/12/17
	 * @param 
	 * @return int 
	 * */
	@Override
	public TbChannel checkName(String name) throws DataAccessException{
		// TODO Auto-generated method stub
		return channelDao.selectByName(name);
	}

	/** 
	  *根据渠道联系人名字获取渠道名字
	  * @author xulin
	  * @date 2014年12月25日
	  *  @param contactName
	  *  @return
	  *  @throws DataAccessException
	  *  List<SelectJsonResult>
	  */ 
	@Override
	public List<SelectJsonResult> getChannelNameByContactName(String contactName) throws DataAccessException {
	List<TbChannel> list=	channelDao.selectAll();
	List<SelectJsonResult> channelNamesList=null;
	if(list!=null&&list.size()>0){
		channelNamesList=new ArrayList<SelectJsonResult>();
		Set<String> set=new HashSet<String>();
		for(TbChannel channel:list){
			System.out.println(channel.getChannelContactName()+"------------"+contactName);
			if(channel.getChannelContactName().equals(contactName)){
				SelectJsonResult json=new SelectJsonResult();
				json.setId(1);
				json.setText(channel.getChannelName());
				channelNamesList.add(json);
			}
		}
	}
		return channelNamesList;
	}
	/**
	 * 得到全部的渠道信息
	 * @date 2014/12/17
	 * @param 
	 * @return int 
	 * */
	@Override
	public List<SelectJsonResult> getAllChannel() throws DataAccessException {
	List<TbChannel> list=	channelDao.selectAll();
	List<SelectJsonResult> contactNamesList=null;
	if(list!=null&&list.size()>0){
		contactNamesList=new ArrayList<SelectJsonResult>();
		Set<String> set=new HashSet<String>();
		for(TbChannel channel:list){
			set.add(channel.getChannelContactName());
		}
		Iterator<String> it = set.iterator();  
		int i=0;
		while (it.hasNext()) {  
		  String str = it.next();  
		  SelectJsonResult json=new SelectJsonResult();
			json.setId(i);
			json.setText(str);
			contactNamesList.add(json);
			i++;
		} 
	}
		return contactNamesList;
	}
	/* (non-Javadoc)
	 * @see common.codrim.service.ChannelService#getChannelByChannelNumber(java.lang.String)
	 */
	@Override
	public TbChannel getChannelByChannelNumber(String channelNum)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return channelDao.selectByChannelNum(channelNum);
	}
	/* (non-Javadoc)
	 * @see common.codrim.service.ChannelService#getTbChannelByChannelName()
	 */
	@Override
	public TbChannel getTbChannelByChannelName(String channelName) throws DataAccessException {
		// TODO Auto-generated method stub
		return channelDao.selectByName(channelName);
	}
	/* (non-Javadoc)
	 * @see common.codrim.service.ChannelService#channelLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public TbAdmin channelLogin(String email, String password)
			throws DataAccessException {
		// TODO Auto-generated method stub
		TbChannel channel = channelDao.channelLogin(email, password);
		TbAdmin admin=null;
		if(channel!=null){
			admin=new TbAdmin();
			admin.setAccount(channel.getChannelName());
			admin.setEmail(channel.getChannelEmail());
			admin.setPassword(channel.getChannelPassword());
			admin.setType(1);
		}
		return admin;
	}
	@Override
	public TbChannel getById(long id) throws DataAccessException {
		return channelDao.selectByPrimaryKey(id);
	}
	@Override
	public boolean isValidCompanyName(String companyName) throws DataAccessException {
		TbChannel c = channelDao.selectByCompanyName(companyName);
		if (c == null)
			return true;
		
		return false;
	}
}
