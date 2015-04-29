package common.codrim.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.common.SelectJsonResult;
import common.codrim.dao.TbOfficeMapper;
import common.codrim.pojo.TbOffice;
import common.codrim.service.OfficeService;


@Service
@Transactional
public class OfficeServiceImpl implements OfficeService {
	@Autowired
	private TbOfficeMapper officeDao;
	
	/**
	 * 获得所有的职位类型名
	 * @author searh
	 * @date 2012/12/24
	 * @parame
	 * @return  List<SelectJsonResult>
	 * */
	@Override
	public List<SelectJsonResult> getAllOffecName() throws DataAccessException{
		List<SelectJsonResult> nameList=null;
		List<TbOffice> list=officeDao.selectAllTbOffice();
		if(list==null||list.size()<=0){
    	return nameList;
		}else{
			nameList=new ArrayList();
		}
		for(TbOffice o:list){
			SelectJsonResult jsonResult=new SelectJsonResult();
			jsonResult.setId(o.getId());
			jsonResult.setText(o.getOfficeName());
			nameList.add(jsonResult);
		}
		return nameList;
	}

	@Override
	public List<TbOffice> getOfficeByPages(int startPage, int size)
			throws DataAccessException {
		
		return officeDao.selectTbOfficeByPages((startPage-1)*size, size);
	}

	@Override
	public int getTotalTbOffice() throws DataAccessException {
		
		return officeDao.getTotalNum();
	}


	@Override
	public int addTbOffice(TbOffice office) throws DataAccessException {
		
		return officeDao.insertSelective(office);
	}

	@Override
	public int modifyTbOffice(TbOffice office) throws DataAccessException {
		
		return officeDao.updateByPrimaryKeySelective(office);
	}

	@Override
	public int deleteTbOffice(Integer id) throws DataAccessException {
		
		return officeDao.deleteByPrimaryKey(id);
	}

}
