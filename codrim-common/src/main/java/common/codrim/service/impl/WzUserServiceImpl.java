package common.codrim.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.dao.TbWzBindingMapper;
import common.codrim.dao.TbWzTaskRecordMapper;
import common.codrim.dao.TbWzUserMapper;
import common.codrim.pojo.TbWzBinding;
import common.codrim.pojo.TbWzUser;
import common.codrim.service.WzUserService;
import common.codrim.util.StringUtil;
import common.codrim.wz.sql.in.Order;
import common.codrim.wz.sql.result.RankingInfo;

@Service
@Transactional
public class WzUserServiceImpl implements WzUserService {
	
	@Autowired
	private TbWzUserMapper userDao;
	
	@Autowired
	private TbWzBindingMapper bindingDao;
	
	@Autowired
	private TbWzTaskRecordMapper taskRecordDao;

	@Override
	public boolean isPhoneExist(String phoneNumber) throws DataAccessException {
		TbWzUser user = userDao.selectByPhoneNumber(phoneNumber);
		return user != null;
	}
	
	@Override
	public TbWzBinding getBindingByDevice(String deviceId) throws DataAccessException {
		return bindingDao.selectByDeviceId(deviceId);
	}

	@Override
	public int addLogin(long userId) throws DataAccessException {
		TbWzUser user = userDao.selectByPrimaryKey(userId);
		user.setLastLoginDate(new Date());
		userDao.updateByPrimaryKeySelective(user);
		
		return user.getBindStatus();
	}

	@Override
	public TbWzUser getUserByPhone(String phoneNumber) throws DataAccessException {
		return userDao.selectByPhoneNumber(phoneNumber);
	}

	@Override
	public long addUserAndBinding(TbWzUser user, TbWzBinding binding) throws DataAccessException {
		userDao.insert(user);
		
		long userId = user.getUserId();
		
		binding.setUserId(userId);
		bindingDao.insert(binding);
		
		return userId;
	}

	@Override
	public List<TbWzUser> searchUser(int startPage, int size, TbWzUser param, Order order) throws DataAccessException {
		int start = (startPage-1) * size;
		return userDao.page(start, size, param, order);
	}

	@Override
	public int getUserTotalAmount(TbWzUser param) throws DataAccessException {
		return userDao.count(param);
	}

	@Override
	public TbWzUser getUserById(long userId) throws DataAccessException {
		return userDao.selectByPrimaryKey(userId);
	}

	@Override
	public List<TbWzBinding> searchBinding(int startPage, int size, TbWzBinding param) throws DataAccessException {
		int start = (startPage-1) * size;
		return bindingDao.page(start, size, param);
	}

	@Override
	public int getBindingTotalAmount(TbWzBinding param) throws DataAccessException {
		return bindingDao.count(param);
	}

	@Override
	public int modifyUser(TbWzUser user) throws DataAccessException {
	return 	userDao.updateByPrimaryKeySelective(user);
	}

	@Override
	public long getBalance(long userId) throws DataAccessException {
		return userDao.getBalance(userId);
	}

	@Override
	public List<RankingInfo> userRanking(int size) throws DataAccessException {
		return userDao.userRanking(size);
	}

	@Override
	public int getBindingAmount(String phoneNumber) throws DataAccessException {
		TbWzUser user = userDao.selectByPhoneNumber(phoneNumber);
		if (user == null) {
			return 0;
		} else {
			TbWzBinding param = new TbWzBinding();
			param.setUserId(user.getUserId());
			return bindingDao.count(param);
		}
	}

	@Override
	public void modifyUsersForMerge(TbWzUser from, TbWzUser to) throws DataAccessException {
		long fromUserId = from.getUserId();
		long toUserId = to.getUserId();
		
		taskRecordDao.batchTransferRecord(fromUserId, toUserId);
		bindingDao.batchTransferBinding(fromUserId, toUserId);
		
		to.setUserEffect(to.getUserEffect() + from.getUserEffect());
		to.setStepEffect(to.getStepEffect() + from.getStepEffect());
		to.setBalance(to.getBalance() + from.getBalance());
		userDao.updateByPrimaryKeySelective(to);
		
		userDao.deleteByPrimaryKey(fromUserId);
	}

	@Override
	public void modifyInviterAndInvitees(TbWzUser inviter, TbWzUser invitees)throws DataAccessException {
		userDao.updateByPrimaryKeySelective(inviter);
		userDao.updateByPrimaryKeySelective(invitees);
	}


}
