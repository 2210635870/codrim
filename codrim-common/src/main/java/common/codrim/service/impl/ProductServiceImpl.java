/**
 * 
 */
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
import common.codrim.common.SelectResultByCodition;
import common.codrim.common.ViewProductToCountBean;
import common.codrim.dao.TbProductMapper;
import common.codrim.pojo.TbProduct;
import common.codrim.pojo.TbProductDetail;
import common.codrim.service.ProductService;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService{
@Autowired
TbProductMapper productDao;

/* (non-Javadoc)
 * @see common.codrim.service.ProductService#save(common.codrim.pojo.TbProduct)
 */
@Override
public int save(TbProduct product) throws DataAccessException {
	// TODO Auto-generated method stub
	return productDao.insertSelective(product);
}

/* (non-Javadoc)
 * @see common.codrim.service.ProductService#selectViewProductToCount(common.codrim.common.SelectResultByCodition)
 */
@Override
public List<ViewProductToCountBean> selectViewProductToCount(
		SelectResultByCodition codition) throws DataAccessException {
	// TODO Auto-generated method stub
	return productDao.selectViewProductToCount(codition);
}

/* (non-Javadoc)
 * @see common.codrim.service.ProductService#geTbProduct(long)
 */
@Override
public TbProduct geTbProduct(long id) throws DataAccessException {
	// TODO Auto-generated method stub
	return productDao.selectByPrimaryKey(id);
}

/* (non-Javadoc)
 * @see common.codrim.service.ProductService#update(common.codrim.pojo.TbProduct)
 */
@Override
public int update(TbProduct product) throws DataAccessException {
	// TODO Auto-generated method stub
	return productDao.updateByPrimaryKeySelective(product);
}

/* (non-Javadoc)
 * @see common.codrim.service.ProductService#getProductNameByCustomerName(java.lang.String)
 */
@Override
public List<SelectJsonResult> getProductNameByCustomerName(String customerName)
		throws DataAccessException {
	// TODO Auto-generated method stub
	List<TbProduct> products=productDao.getProductNameByCustomerName(customerName);
	List<SelectJsonResult> results=null;
	if(products!=null&&products.size()>0){
		results=new ArrayList<SelectJsonResult>();
		for(TbProduct product:products){
			SelectJsonResult result=new SelectJsonResult();
			result.setId(product.getId());
			result.setText(product.getProductName());
			results.add(result);
		}
		SelectJsonResult result=new SelectJsonResult();
result.setId(0);
result.setText("全部");
		results.add(result);
	}
	return results;
}


}
