package common.codrim.service;


import java.util.List;

import org.springframework.dao.DataAccessException;

import common.codrim.common.SelectJsonResult;
import common.codrim.common.SelectResultByCodition;
import common.codrim.pojo.TbProductDetail;

public interface  ProductDetailService {

    /**
     * 获得TbProduct对象的所有数据
     * @date 2014/12/18
     * @param 
     * @return  List<TbProduct>
     * */
	public List<TbProductDetail> getAllTbProducts() throws DataAccessException;
    /**
     * 根据渠道名获得TbProduct
     * @date 2014/12/18
     * @param 
     * @return  List<TbProduct>
     * */
	public List<TbProductDetail> getTbProductsByChannelName(String channelName) throws DataAccessException;
	/**
     * 获得TbProduct对象的所有数据的记录数
     * @date 2014/12/18
     * @param 
     * @return  int
     * */
	public int getTotalProducts() throws DataAccessException;
	/**
     * 批量更新 渠道
     * @date 2014/12/18
     * @param 
     * @return  int
     * */
	public int insertByBatch(List list) throws DataAccessException;
	/**
	 * 通过产品名查询相对应的
	 * @date 2014/12/18
	 * @param  String customerName
	 * @return List<SelectJsonResult>
	 * */
	public TbProductDetail getProductDetail(String productName,String channelName) throws DataAccessException;

	/**
	 * 通过 产品名查询对应的渠道
	 * @date 2014/12/18
	 * @param  String customerName
	 * @return List<SelectJsonResult>
	 * */
	public List<SelectJsonResult> getChannelNamestByProductName(String productName) throws DataAccessException;
	/**
	 * 添加数据
	 * @date 2014/12/18
	 * @param  TbProductDetail product
	 * @return int 
	 * */
	public int addProduct(TbProductDetail product) throws DataAccessException;
	/**
	 * 修改数据
	 * @date 2014/12/18
	 * @param  TbProductDetail product
	 * @return int 
	 * */
	public int modifyProduct(TbProductDetail product) throws DataAccessException;
	/**
	 * 通过主键删除
	 * @date 2014/12/18
	 * @param  TbProductDetail product
	 * @return int 
	 * */
	public int deleteById(long id) throws DataAccessException;
	/**
	 * 通过客户名查询相对应的产品名
	 * @date 2014/12/18
	 * @param  String customerName
	 * @return List<SelectJsonResult>
	 * */
	public List<SelectJsonResult> getProductName(String customerName) throws DataAccessException;
	/**
	 * 获取全部的产品名
	 * @date 2014/12/18
	 * @return List<SelectJsonResult>
	 * */
	public List<SelectJsonResult> getProductName() throws DataAccessException;
	/**
	 * 按条件得到TbProduct对象
	 * @date 2014/12/18
	 * @param SelectResultByCodition codition
	 * @return TbProduct
	 * */
	public TbProductDetail getProductDetailByCodition(SelectResultByCodition codition) throws DataAccessException;
	
	/**
	 * 通过特定条件查询
	 * @author searh
	 * @date 2014/12/25
	 * @param SelectResultByCodition codition
	 * @return TbProduct
	 * */
	public List<TbProductDetail> getProductDetailsByCodition (SelectResultByCodition codition) throws DataAccessException;
	
	/**
	 * 通过特定条件查询 得到总数
	 * @author searh
	 * @date 2014/12/25
	 * @param SelectResultByCodition codition
	 * @return TbProduct
	 * */
	public int getTotalProductDetailsByCodition(SelectResultByCodition codition) throws DataAccessException;
	
	 /**
 	 * 根据产品类型查询所有
 	 * @author searh
 	 * @date 2014/12/30
 	 * @param productType
 	 * @return List<TbProduct>
 	 * */
	public List<TbProductDetail> selectAllTbProductDetailByProductProperty(SelectResultByCodition codition) throws DataAccessException;
	
	public TbProductDetail geTbProductWithId(long id) throws DataAccessException;
	
	/**
	 * 通过渠道联系人查询对应的渠道
	 * @author searh
	 * @date 2014/12/30
	 * @param request
	 * @return  selectProduct
	 * */
	public List<SelectJsonResult> getChannelNameByChannelContactName(String channelContactName) throws DataAccessException;
}
