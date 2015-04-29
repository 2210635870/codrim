/**
 * 
 */
package common.codrim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.dao.TbProductResourceMapper;
import common.codrim.pojo.TbProductResource;
import common.codrim.service.ProductResourceService;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class ProductResourceServiceImpl implements ProductResourceService{
@Autowired
TbProductResourceMapper  productResDao;
	/* (non-Javadoc)
	 * @see common.codrim.service.ProductResourceService#insertByBatch(java.util.List)
	 */
	@Override
	public int insertByBatch(List<TbProductResource> productResources)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return productResDao.insertByBatch(productResources);
	}
	/* (non-Javadoc)
	 * @see common.codrim.service.ProductResourceService#geTbProductResources()
	 */
	@Override
	public List<TbProductResource> geTbProductResources(long productId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return productResDao.selectProductResourcesByProductId(productId);
	}

}
