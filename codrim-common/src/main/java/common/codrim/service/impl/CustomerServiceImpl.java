package common.codrim.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.common.SelectJsonResult;
import common.codrim.dao.TbCustomerMapper;
import common.codrim.dao.TbCustomerTypeMapper;
import common.codrim.pojo.TbCustomer;
import common.codrim.pojo.TbCustomerType;
import common.codrim.service.CustomerService;
@Service
@Transactional
public class CustomerServiceImpl  implements CustomerService{
	
	@Autowired
	private TbCustomerMapper customerDao;
	
	@Autowired
	private TbCustomerTypeMapper customerTypeDao;
	
	/**
	 * 获得TbCustomer的所有数据
	 * @date 2014-12-18
	 * @return  List<TbCustomer>
	 * */
	@Override
	public List<TbCustomer> getAllTbCustomers() throws DataAccessException{
		// TODO Auto-generated method stub
		return customerDao.selectAll();
	}
	/**
	 * 获得TbCustomer的数据记录的条数
	 * @date 2014-12-18
	 * @return  int
	 * */
	@Override
	public int getTotalCustomers() throws DataAccessException{
		// TODO Auto-generated method stub
		return customerDao.getTotalNum();
	}
	/**
	 * 根据页码各显示的条数查找数据
	 * @date 2014-12-18
	 * @param int startPage
	 * @param int size
	 * @return  List<TbCustomer>
	 * */
	@Override
	public List<TbCustomer> getCustomersByPage(int startPage ,int size) throws DataAccessException{
		// TODO Auto-generated method stub
		int start=(startPage-1)*size;
		return customerDao.selectCustomersByPage(start, size);
	}
	/**
	 * 添加TbCustomer对象
	 * @date 2014-12-18
	 * @param TbCustomer对象
	 * @return  int
	 * */
	@Override
	public int addCustomer(TbCustomer customer) throws DataAccessException{
		// TODO Auto-generated method stub
		return customerDao.insertSelective(customer);
	}
	/**
	 * 通过主键修改TbCustomer的数据
	 * @date 2014-12-18
	 * @param TbCustomer
	 * @return  int
	 * */
	@Override
	public int modifyCustomer(TbCustomer customer) throws DataAccessException{
		// TODO Auto-generated method stub
		return customerDao.updateByPrimaryKeySelective(customer);
	}
	/**
	 * 根据有键删除对应的信息
	 * @date 2014-12-18
	 * @param long id
	 * @return  int
	 * */
	@Override
	public int deleteById(long id) throws DataAccessException{
		// TODO Auto-generated method stub
		return customerDao.deleteByPrimaryKey(id);
	}
	/**
	 * 查找所有TbCustomer对象里的客户名
	 * @date 2014-12-18
	 * @param 
	 * @return  List<SelectJsonResult>
	 * */
	@Override
	public List<SelectJsonResult> getAllCustomerName() throws DataAccessException{
		List<TbCustomer> list= customerDao.selectAll();
		List<SelectJsonResult> nameList=null;
		if(list!=null&&list.size()>0){
			nameList=new ArrayList<SelectJsonResult>();
			for(TbCustomer customer:list){
				SelectJsonResult json=new SelectJsonResult();
				json.setId(customer.getId());
				json.setText(customer.getCustomerName());
				nameList.add(json);
			}
			
		}
		return nameList;
	}

	@Override
	public boolean checkName(String name) throws DataAccessException{
		boolean flag=false;
		TbCustomer customer=customerDao.selectByCompanyName(name);
		if(customer == null)
			flag=true;
		
		return flag;
	}
	@Override
	public TbCustomer getById(long id) throws DataAccessException {
		return customerDao.selectByPrimaryKey(id);
	}
	
	@Override
	public void addCustomerType(TbCustomerType ct) throws DataAccessException {
		customerTypeDao.insert(ct);
	}
	@Override
	public void modifyCustomerType(TbCustomerType ct) throws DataAccessException {
		customerTypeDao.updateByPrimaryKeySelective(ct);
	}
	@Override
	public void deleteCustomerType(long id) throws DataAccessException {
		customerTypeDao.deleteByPrimaryKey(id);
	}
	@Override
	public int count() throws DataAccessException {
		return customerTypeDao.count();
	}
	@Override
	public List<TbCustomerType> page(int startPage, int size) throws DataAccessException {
		int start=(startPage-1)*size;
		return customerTypeDao.page(start, size);
	}
	@Override
	public List<SelectJsonResult> getAllCustomerType() throws DataAccessException {
		List<TbCustomerType> list= customerTypeDao.getAllCustomerType();
		List<SelectJsonResult> clist=null;
		if(list!=null&&list.size()>0){
			clist=new ArrayList<SelectJsonResult>();
			for(TbCustomerType ct:list){
				SelectJsonResult json=new SelectJsonResult();
				json.setId(ct.getId());
				json.setText(ct.getCustomerType());
				clist.add(json);
			}
			
		}
		return clist;
	}

}
