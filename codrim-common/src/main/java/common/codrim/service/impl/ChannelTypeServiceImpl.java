package common.codrim.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.common.SelectJsonResult;
import common.codrim.dao.TbChannelTypeMapper;
import common.codrim.pojo.TbChannelType;
import common.codrim.pojo.TbOffice;
import common.codrim.service.ChannelTypeService;

@Service
@Transactional
public class ChannelTypeServiceImpl implements ChannelTypeService {
	@Autowired
	private TbChannelTypeMapper channelTypeDao;

	@Override
	public List<SelectJsonResult> getAllChannelTypeName() throws DataAccessException{
		List<SelectJsonResult> nameList=null;
		List<TbChannelType> list=channelTypeDao.selectAllTbChannelType();
		if(list==null||list.size()<=0){
    	return nameList;
		}else{
			nameList=new ArrayList();
		}
		for(TbChannelType o:list){
			SelectJsonResult jsonResult=new SelectJsonResult();
			jsonResult.setId(o.getId());
			jsonResult.setText(o.getChannelType());
			nameList.add(jsonResult);
		}
		return nameList;
	}
	
	
	@Override
	public List<TbChannelType> getChannelTypeByPages(int startPage, int size)
			throws DataAccessException {
		int start=(startPage-1)*size;
		return channelTypeDao.selectTbChannelTypeByPages(start, size);
	}


	@Override
	public int getTotalChannelType() throws DataAccessException {
		
		return channelTypeDao.getTotalNum();
	}


	@Override
	public int addChannelType(TbChannelType channelType) throws DataAccessException {
		
		return channelTypeDao.insertSelective(channelType);
	}


	@Override
	public int modifyChannelType(TbChannelType channelType)
			throws DataAccessException {
		
		return channelTypeDao.updateByPrimaryKeySelective(channelType);
	}


	@Override
	public int deleteChannelType(Integer id) throws DataAccessException {
		
		return channelTypeDao.deleteByPrimaryKey(id);
	}

	
}
