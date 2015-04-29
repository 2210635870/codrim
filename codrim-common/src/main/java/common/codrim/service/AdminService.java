package common.codrim.service;


import java.util.List;

import org.springframework.dao.DataAccessException;

import common.codrim.common.SelectJsonResult;
import common.codrim.pojo.TbAdmin;

public interface  AdminService {
	/**
	 * 查找所有用户的信息
	 * @date 2012/12/18
	 * @param 
	 * @return List<TbUser>
	 * */
	public List<TbAdmin> getAllTbUsers() throws DataAccessException;
	/**
	 * 查找所有用户的信息的记录数
	 * @date 2012/12/18
	 * @param 
	 * @return int
	 * */
	public int getTotalUsers() throws DataAccessException;
	/**
	 * 查找所有用户的信息   带分页
	 * @date 2012/12/18
	 * @param int startPage
	 * @param int size
	 * @return int
	 * */
	public List<TbAdmin> getUsersByPageAndAdmin(int startPage ,int size) throws DataAccessException;
	/**
	 * 添加用户信息
	 * @date 2012/12/18
	 * @param TbUser对象
	 * @return int
	 * */
	public int addTbUser(TbAdmin user) throws DataAccessException;
	/**
	 * 修改用户信息
	 * @date 2012/12/18
	 * @param TbUser对象
	 * @return int
	 * */
	public int modifyTbUser(TbAdmin user) throws DataAccessException;
	/**
	 * 通过有键删除用户信息
	 * @date 2012/12/18
	 * @param long id
	 * @return int
	 * */
	public int deleteById(long id) throws DataAccessException;
	/**
	 * 查找全部用户名
	 * @date 2012/12/18
	 * @param 
	 * @return List<SelectJsonResult>
	 * */
	public List<SelectJsonResult> getAllTbUserName() throws DataAccessException;
	/**
	 * 根据用户名查找是否存在
	 * @date 2012/12/18
	 * @param String name
	 * @return boolean
	 * */
	public boolean checkAccount(String name) throws DataAccessException;
	/**
	 * 根据用户名和密码登陆
	 * @date 2012/12/18
	 * @param String account
	 * @param String password
	 * @return TbUser对象
	 * */
	public TbAdmin login(String email,String password) throws DataAccessException;
}
