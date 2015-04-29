package common.codrim.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import common.codrim.pojo.TbWzBinding;
import common.codrim.pojo.TbWzUser;
import common.codrim.wz.sql.in.Order;
import common.codrim.wz.sql.result.RankingInfo;

public interface WzUserService {

	public boolean isPhoneExist(String phoneNumber) throws DataAccessException;
	
	public TbWzUser getUserById(long userId) throws DataAccessException;
	
	public TbWzUser getUserByPhone(String phoneNumber) throws DataAccessException;
	
	public int getBindingAmount(String phoneNumber) throws DataAccessException;
	public TbWzBinding getBindingByDevice(String deviceId) throws DataAccessException;
	
	public int addLogin(long userId) throws DataAccessException;
	public long addUserAndBinding(TbWzUser user, TbWzBinding binding) throws DataAccessException;
	
	public List<TbWzUser> searchUser(int startPage, int size, TbWzUser param, Order order) throws DataAccessException;
	public int getUserTotalAmount(TbWzUser param) throws DataAccessException;
	
	public List<TbWzBinding> searchBinding(int startPage, int size, TbWzBinding param) throws DataAccessException;
	public int getBindingTotalAmount(TbWzBinding param) throws DataAccessException;
	
	public int modifyUser(TbWzUser user) throws DataAccessException;
	public void modifyInviterAndInvitees(TbWzUser inviter, TbWzUser invitees) throws DataAccessException;
	
	public long getBalance(long userId) throws DataAccessException;
	
	public List<RankingInfo> userRanking(int size) throws DataAccessException;
	
	public void modifyUsersForMerge(TbWzUser from, TbWzUser to) throws DataAccessException;
}
