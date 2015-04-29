package common.codrim.service.impl;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.activemq.filter.function.inListFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.common.SelectJsonResult;
import common.codrim.common.SelectResultByCodition;
import common.codrim.dao.TbProductDetailMapper;
import common.codrim.pojo.TbProductDetail;
import common.codrim.service.ProductDetailService;
import common.codrim.util.StringUtil;
@Service
@Transactional
public class ProductDetailServiceImpl  implements ProductDetailService{
	@Autowired
	private TbProductDetailMapper productDao;
	
	
	

	 /**
     * 获得TbProduct对象的所有数据
     * @date 2014/12/18
     * @param 
     * @return  List<TbProduct>
     * */
	@Override
	public List<TbProductDetail> getAllTbProducts() throws DataAccessException{
		// TODO Auto-generated method stub
		return productDao.selectAll();
	}
	/**
     * 获得TbProduct对象的所有数据的记录数
     * @date 2014/12/18
     * @param 
     * @return  int
     * */
	@Override
	public int getTotalProducts() throws DataAccessException{
		// TODO Auto-generated method stub
		return productDao.getTotalNum();
	}

	/**
	 * 添加数据
	 * @date 2014/12/18
	 * @param  TbProductDetail product
	 * @return int 
	 * */
	@Override
	public int addProduct(TbProductDetail product) throws DataAccessException{
		// TODO Auto-generated method stub
		return productDao.insertSelective(product);
	}
	/**
	 * 修改数据
	 * @date 2014/12/18
	 * @param  TbProductDetail product
	 * @return int 
	 * */
	@Override
	public int modifyProduct(TbProductDetail product) throws DataAccessException{
		// TODO Auto-generated method stub
		return productDao.updateByPrimaryKeySelective(product);
	}
	/**
	 * 通过主键删除
	 * @date 2014/12/18
	 * @param  TbProductDetail product
	 * @return int 
	 * */
	@Override
	public int deleteById(long id) throws DataAccessException{
		// TODO Auto-generated method stub
		return productDao.deleteByPrimaryKey(id);
	}
	/**
	 * 通过 产品渠道名查询对应的产品
	 * @date 2014/12/18
	 * @param  String customerName
	 * @return List<SelectJsonResult>
	 * */
	@Override
	public TbProductDetail getProductDetail(String productName,String channelName) throws DataAccessException{
		return productDao.getProduct(productName,channelName);
	}
	/**
	 * 通过 产品名查询对应的渠道
	 * @date 2014/12/18
	 * @param  String customerName
	 * @return List<SelectJsonResult>
	 * */
	@Override
	public List<SelectJsonResult> getChannelNamestByProductName(String productName) throws DataAccessException{
		List<SelectJsonResult> nameList=null;
		List<TbProductDetail> list=productDao.getChannelNamestByProductName(productName);
		if(list==null||list.size()<=0){
			return nameList;
		}else{
			nameList=new ArrayList();
		}
		for(TbProductDetail pro:list){
			SelectJsonResult jsonResult=new SelectJsonResult();
			jsonResult.setText(pro.getChannelName());
			jsonResult.setId(pro.getId());
			nameList.add(jsonResult);
		}
		SelectJsonResult jsonResult=new SelectJsonResult();
		jsonResult.setText("全部");
		jsonResult.setId(0);
		nameList.add(jsonResult);
		return nameList;
		
	}
	/**
	 * 通过客户名查询相对应的产品名
	 * @date 2014/12/18
	 * @param  String customerName
	 * @return List<SelectJsonResult>
	 * */
	@Override
	public List<SelectJsonResult> getProductName(String customerName) throws DataAccessException{
		List<SelectJsonResult> nameList=null;
		List<TbProductDetail> list=productDao.selectByCustomerName(customerName);
		if(list==null||list.size()<=0){
			return nameList;
		}else{
			nameList=new ArrayList();
		}
		Set<String> set=new HashSet<String>();
		for(TbProductDetail pro:list){
		set.add(pro.getProductName());
		}
		Iterator iterator=set.iterator();
		int i=0;
		while(iterator.hasNext()){
			SelectJsonResult jsonResult=new SelectJsonResult();
			jsonResult.setText((String)iterator.next());
			jsonResult.setId(i);
			i++;
			nameList.add(jsonResult);
		}
		SelectJsonResult jsonResult=new SelectJsonResult();
		jsonResult.setText("全部");
		jsonResult.setId(0);
		nameList.add(jsonResult);
		return nameList;
	}
	/**
	 * 获取全部的产品名
	 * @date 2014/12/18
	 * @return List<SelectJsonResult>
	 * */
	@Override
	public List<SelectJsonResult> getProductName() throws DataAccessException{
		List<SelectJsonResult> nameList=null;
		List<TbProductDetail> list=productDao.selectAll();
		if(list==null||list.size()<=0){
			return nameList;
		}else{
			nameList=new ArrayList();
		}
		Set<String> set=new HashSet<String>();
		for(TbProductDetail pro:list){
		set.add(pro.getProductName());
		}
		Iterator iterator=set.iterator();
		int i=0;
		while(iterator.hasNext()){
			SelectJsonResult jsonResult=new SelectJsonResult();
			jsonResult.setText((String)iterator.next());
			jsonResult.setId(i);
			i++;
			nameList.add(jsonResult);
		}
		SelectJsonResult jsonResult=new SelectJsonResult();
		jsonResult.setText("全部");
		jsonResult.setId(0);
		nameList.add(jsonResult);
		return nameList;
	}
	/**
	 * 按条件得到TbProduct对象
	 * @date 2014/12/18
	 * @param SelectResultByCodition codition
	 * @return TbProduct
	 * */
	@Override
	public TbProductDetail getProductDetailByCodition(SelectResultByCodition codition) throws DataAccessException{
		return productDao.selectTbProductDetailByCodition(codition);
	}
	
	/**
	 * 通过运行状态查询
	 * @author searh
	 * @date 2014/12/25
	 * @param SelectResultByCodition codition
	 * @return TbProduct
	 * */
	@Override
	public List<TbProductDetail> getProductDetailsByCodition(
			SelectResultByCodition codition) throws DataAccessException {
		codition.setStartPage((codition.getStartPage()-1)*codition.getSize());
		return productDao.selectTbProductDetailsByCodition(codition);
	}
	
	/**
	 * 通过运行状态查询 得到总数
	 * @author searh
	 * @date 2014/12/25
	 * @param SelectResultByCodition codition
	 * @return TbProduct
	 * */
	@Override
	public int getTotalProductDetailsByCodition(SelectResultByCodition codition)
			throws DataAccessException {
		return productDao.selectTotalProductDetailsByCodition(codition);
	}
	
	@Override
	public List<TbProductDetail> selectAllTbProductDetailByProductProperty(SelectResultByCodition codition)
			throws DataAccessException {
		return productDao.selectAllTbProductDetailByProductProperty(codition);
	}
	
	@Override
	public List<SelectJsonResult> getChannelNameByChannelContactName(
			String channelContactName) throws DataAccessException {
		List<SelectJsonResult> nameList=null;
		List<TbProductDetail> list=productDao.selectChannelNameByChannelContactName(channelContactName);
		if(list==null||list.size()<=0){
			return nameList;
		}else{
			nameList=new ArrayList();
		}
		for(TbProductDetail pro:list){
			SelectJsonResult jsonResult=new SelectJsonResult();
			jsonResult.setId(pro.getId());
			jsonResult.setText(pro.getChannelName());
			nameList.add(jsonResult);
		}
		SelectJsonResult jsonResult=new SelectJsonResult();
		jsonResult.setText("全部");
		jsonResult.setId(0);
		nameList.add(jsonResult);
		return nameList;

	}
	/* (non-Javadoc)
	 * @see common.codrim.service.ProductService#updateByBatch(java.util.List)
	 */
	@Override
	public int insertByBatch(List list) throws DataAccessException {
		// TODO Auto-generated method stub
		return productDao.insertByBatch(list);
	}
	/* (non-Javadoc)
	 * @see common.codrim.service.ProductService#geTbProductWithId(long)
	 */
	@Override
	public TbProductDetail geTbProductWithId(long id) throws DataAccessException {
		// TODO Auto-generated method stub
		return productDao.selectByPrimaryKey(id);
	}
	/* (non-Javadoc)
	 * @see common.codrim.service.ProductService#getTbProductsByChannelName(java.lang.String)
	 */
	@Override
	public List<TbProductDetail> getTbProductsByChannelName(String channelName)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return productDao.getProductByChannelName(channelName);
	}
	
}
