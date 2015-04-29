package common.codrim.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.common.SelectJsonResult;
import common.codrim.dao.TbProductTypeMapper;
import common.codrim.pojo.TbCustomerType;
import common.codrim.pojo.TbProductType;
import common.codrim.service.ProductTypeService;

@Service
@Transactional
public class ProductTypeServiceImpl implements ProductTypeService {

	@Autowired
    private TbProductTypeMapper productTypeDao;

	@Override
	public List<TbProductType> getProductTypeByPages(int startPage, int size)
			throws DataAccessException {
		int start=(startPage-1)*size;
		return productTypeDao.selectTbProductTypeByPages(start, size);
	}

	@Override
	public int getTotalTbProductType() throws DataAccessException {
		
		return productTypeDao.getTotalNum();
	}

	@Override
	public int addTbProductType(TbProductType productType)
			throws DataAccessException {
		
		return productTypeDao.insertSelective(productType);
	}

	@Override
	public int modifyTbProductType(TbProductType productType)
			throws DataAccessException {
		
		return productTypeDao.updateByPrimaryKeySelective(productType);
	}

	@Override
	public int deleteTbProductType(Integer id) throws DataAccessException {
		
		return productTypeDao.deleteByPrimaryKey(id);
	}
	
	@Override
	public List<SelectJsonResult> getAllProductTypes() {
	List<TbProductType>	list=productTypeDao.selectAllProductTypes();
		List<SelectJsonResult> clist=null;
		if(list!=null&&list.size()>0){
			clist=new ArrayList<SelectJsonResult>();
			for(TbProductType ct:list){
				SelectJsonResult json=new SelectJsonResult();
				json.setId(ct.getId());
				json.setText(ct.getProductType());
				clist.add(json);
			}
		}
		return clist;
	}

	@Override
	public TbProductType getProductTypeById(int id) {
		// TODO Auto-generated method stub
		return productTypeDao.selectByPrimaryKey(id);
	}
}
