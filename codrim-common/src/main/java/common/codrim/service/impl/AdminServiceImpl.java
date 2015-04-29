package common.codrim.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.common.SelectJsonResult;
import common.codrim.dao.TbAdminMapper;
import common.codrim.pojo.TbAdmin;
import common.codrim.service.AdminService;
import common.codrim.util.DynamicDataSource;
@Service
@Transactional
public class AdminServiceImpl  implements AdminService{
@Autowired
private TbAdminMapper userDao;
	/**
	 * 查找所有用户的信息
	 * @date 2012/12/18
	 * @param 
	 * @return List<TbUser>
	 * */
	@Override
	public List<TbAdmin> getAllTbUsers() throws DataAccessException{
		// TODO Auto-generated method stub
		return userDao.selectAll();
	}
	/**
	 * 查找所有用户的信息的记录数
	 * @date 2012/12/18
	 * @param 
	 * @return int
	 * */
	@Override
	public int getTotalUsers() throws DataAccessException{
		// TODO Auto-generated method stub
		System.out.println(DynamicDataSource.getCurrentLookupKey()+"-----------");
		return userDao.getTotalNum();
	}
	/**
	 * 查找所有用户的信息   带分页
	 * @date 2012/12/18
	 * @param int startPage
	 * @param int size
	 * @return int
	 * */
	@Override
	public List<TbAdmin> getUsersByPageAndAdmin(int startPage ,int size) throws DataAccessException{
		// TODO Auto-generated method stub
		int start=(startPage-1)*size;
		return userDao.selectUsersByPage(start, size);
	}
	/**
	 * 添加用户信息
	 * @date 2012/12/18
	 * @param TbUser对象
	 * @return int
	 * */
	@Override
	public int addTbUser(TbAdmin user) throws DataAccessException{
		// TODO Auto-generated method stub
		return userDao.insertSelective(user);
	}
	/**
	 * 修改用户信息
	 * @date 2012/12/18
	 * @param TbUser对象
	 * @return int
	 * */
	@Override
	public int modifyTbUser(TbAdmin user) throws DataAccessException{
		// TODO Auto-generated method stub
		return userDao.updateByPrimaryKeySelective(user);
	}
	/**
	 * 通过有键删除用户信息
	 * @date 2012/12/18
	 * @param long id
	 * @return int
	 * */
	@Override
	public int deleteById(long id) throws DataAccessException{
		// TODO Auto-generated method stub
		return userDao.deleteByPrimaryKey(id);
	}
	/**
	 * 查找全部用户名
	 * @date 2012/12/18
	 * @param 
	 * @return List<SelectJsonResult>
	 * */
	@Override
	public List<SelectJsonResult> getAllTbUserName() throws DataAccessException{
		List<TbAdmin> list= userDao.selectAll();
		List<SelectJsonResult> nameList=null;
		if(list!=null&&list.size()>0){
			nameList=new ArrayList<SelectJsonResult>();
			for(TbAdmin user:list){
				if(user.getType()!=1){
				SelectJsonResult json=new SelectJsonResult();
				json.setId(user.getId());
				json.setText(user.getAccount());
				nameList.add(json);
			}}
	}
		return nameList;
	}
	/**
	 * 根据用户名查找是否存在
	 * @date 2012/12/18
	 * @param String name
	 * @return boolean
	 * */
	@Override
	public boolean checkAccount(String name) throws DataAccessException{
		// TODO Auto-generated method stub
		boolean flag=false;
		TbAdmin user=userDao.selectIdByName(name);
		if(user==null)
				flag=true;
	
		return flag;
	}
	/**
	 * 根据用户名和密码登陆
	 * @date 2012/12/18
	 * @param String account
	 * @param String password
	 * @return TbUser对象
	 * */
	@Override
	public TbAdmin login(String email, String password) throws DataAccessException{
		// TODO Auto-generated method stub
		return userDao.login(email,password);
	}

}
