package common.codrim.service;


import java.util.List;

import org.springframework.dao.DataAccessException;

import common.codrim.common.SelectJsonResult;
import common.codrim.pojo.TbCustomer;
import common.codrim.pojo.TbCustomerType;

public interface  CustomerService {
	/**
	 * 获得TbCustomer的所有数据
	 * @date 2014-12-18
	 * @return  List<TbCustomer>
	 * */
	public List<TbCustomer> getAllTbCustomers() throws DataAccessException;
	/**
	 * 获得TbCustomer的数据记录的条数
	 * @date 2014-12-18
	 * @return  int
	 * */
	public int getTotalCustomers() throws DataAccessException;
	/**
	 * 根据页码各显示的条数查找数据
	 * @date 2014-12-18
	 * @param int startPage
	 * @param int size
	 * @return  List<TbCustomer>
	 * */
	public List<TbCustomer> getCustomersByPage(int startPage ,int size) throws DataAccessException;
	/**
	 * 添加TbCustomer对象
	 * @date 2014-12-18
	 * @param TbCustomer对象
	 * @return  int
	 * */
	public int addCustomer(TbCustomer customer) throws DataAccessException;
	/**
	 * 通过主键修改TbCustomer的数据
	 * @date 2014-12-18
	 * @param TbCustomer
	 * @return  int
	 * */
	public int modifyCustomer(TbCustomer customer) throws DataAccessException;
	/**
	 * 根据有键删除对应的信息
	 * @date 2014-12-18
	 * @param long id
	 * @return  int
	 * */
	public int deleteById(long id) throws DataAccessException;
	/**
	 * 查找所有TbCustomer对象里的客户名
	 * @date 2014-12-18
	 * @param 
	 * @return  List<SelectJsonResult>
	 * */
	public List<SelectJsonResult> getAllCustomerName() throws DataAccessException;
	/**
	 * 查找用户用户名是否存在
	 * @date 2014-12-18
	 * @param String name
	 * @return  boolean
	 * */
	public boolean checkName(String name) throws DataAccessException;
	
	public TbCustomer getById(long id) throws DataAccessException;
	
	public void addCustomerType(TbCustomerType ct) throws DataAccessException;
	public void modifyCustomerType(TbCustomerType ct) throws DataAccessException;
	public void deleteCustomerType(long id) throws DataAccessException;
	public int count() throws DataAccessException;
	public List<TbCustomerType> page(int startPage, int size) throws DataAccessException;
	public List<SelectJsonResult> getAllCustomerType() throws DataAccessException;
}
