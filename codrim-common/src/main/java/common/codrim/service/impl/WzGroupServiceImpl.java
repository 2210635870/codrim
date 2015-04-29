package common.codrim.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.dao.TbWzCommonSettingMapper;
import common.codrim.dao.TbWzGroupApplicationMapper;
import common.codrim.dao.TbWzGroupInviteCodeMapper;
import common.codrim.dao.TbWzGroupMapper;
import common.codrim.dao.TbWzGroupTaskRatioMapper;
import common.codrim.dao.TbWzUserMapper;
import common.codrim.pojo.TbWzCommonSetting;
import common.codrim.pojo.TbWzGroup;
import common.codrim.pojo.TbWzGroupApplication;
import common.codrim.pojo.TbWzGroupInviteCode;
import common.codrim.pojo.TbWzUser;
import common.codrim.service.WzGroupService;
import common.codrim.wz.constant.DataConstant;
import common.codrim.wz.sql.result.GroupApplicationInfo;
import common.codrim.wz.sql.result.GroupInfo;
import common.codrim.wz.sql.result.RankingInfo;

@Service
@Transactional
public class WzGroupServiceImpl implements WzGroupService {
	
	@Autowired
	private TbWzGroupInviteCodeMapper inviteCodeDao;
	
	@Autowired
	private TbWzGroupMapper groupDao;
	
	@Autowired
	private TbWzGroupApplicationMapper applicationDao;
	
	@Autowired
	private TbWzUserMapper userDao;
	
	@Autowired
	private TbWzGroupInviteCodeMapper codeDao;
	
	@Autowired
	private TbWzGroupTaskRatioMapper ratioDao;
	
	@Autowired
	private TbWzCommonSettingMapper settingDao;
	@Override
	public TbWzGroup getGroupByUser(long userId) throws DataAccessException {
		return groupDao.getByUser(userId);
	}
	@Override
	public List<TbWzGroupInviteCode> searchInviteCode(int startPage, int size, TbWzGroupInviteCode param) 
			throws DataAccessException {
		int start = (startPage-1) * size;
		return inviteCodeDao.page(start, size, param);
	}

	@Override
	public int getInviteCodeTotalAmount(TbWzGroupInviteCode param) throws DataAccessException {
		return inviteCodeDao.count(param);
	}

	@Override
	public long addCode(TbWzGroupInviteCode code) throws DataAccessException {
		if (code.getCode() != null && code.getCode() != 0L)
			inviteCodeDao.updateByPrimaryKeySelective(code);
		else
			inviteCodeDao.insert(code);
		
		return code.getCode();
	}

	@Override
	public List<GroupInfo> searchGroup(int startPage, int size, TbWzGroup param) throws DataAccessException {
		int start = (startPage-1) * size;
		return groupDao.page(start, size, param);
	}

	@Override
	public int getGroupTotalAmount(TbWzGroup param) throws DataAccessException {
		return groupDao.count(param);
	}

	@Override
	public GroupInfo getGroupDetail(long groupId) throws DataAccessException {
		return groupDao.getGroupDetail(groupId);
	}

	@Override
	public List<GroupApplicationInfo> searchGroupApplication(int startPage, int size, GroupApplicationInfo param) throws DataAccessException {
		int start = (startPage-1) * size;
		return applicationDao.page(start, size, param);
	}

	@Override
	public int getGroupApplicationTotalAmount(GroupApplicationInfo param) throws DataAccessException {
		return applicationDao.count(param);
	}

	@Override
	public boolean checkCode(long code) throws DataAccessException {
		TbWzGroupInviteCode inviteCode = inviteCodeDao.selectByPrimaryKey(code);
		return (inviteCode != null) && (inviteCode.getStatus() == DataConstant.GROUP_INVITE_CODE_STATUS_UNUSE);
	}

	@Override
	public void addGroup(TbWzGroup group, long userId, long inviteCode)throws DataAccessException {
		
		groupDao.insert(group);
		
		TbWzUser user = userDao.selectByPrimaryKey(userId);
		user.setGroupId(group.getGroupId());
		user.setIsLeader(DataConstant.TRUE);
		userDao.updateByPrimaryKeySelective(user);
		
		TbWzGroupInviteCode code = codeDao.selectByPrimaryKey(inviteCode);
		code.setGroupId(group.getGroupId());
		code.setStatus(DataConstant.GROUP_INVITE_CODE_STATUS_USED);
		codeDao.updateByPrimaryKeySelective(code);
		
		ratioDao.batchInsertByTask(group.getGroupId(), group.getLeaderPercent());
	}

	@Override
	public TbWzGroup searchGroup(String param, int paramType) throws DataAccessException {
		TbWzGroup group = null;
		TbWzGroup pg = new TbWzGroup();
		if (paramType == DataConstant.GROUP_SEARCH_GID) {
			try {
				pg.setGroupId(Long.valueOf(param));
				group = groupDao.searchGroup(pg);
			} catch (Exception e) {
				return null;
			}
			
		} else if (paramType == DataConstant.GROUP_SEARCH_GNAME) {
			pg.setGroupName(param);
			group = groupDao.searchGroup(pg);
			
		} else if (paramType == DataConstant.GROUP_SEARCH_INVID) {
			TbWzUser user = userDao.selectByPrimaryKey(Long.valueOf(param));
			if (user != null) {
				pg.setGroupId(user.getGroupId());
				group = groupDao.searchGroup(pg);
			}
		}

		return group;
	}

	@Override
	public void addGroupApplication(TbWzGroupApplication application) throws DataAccessException {
		applicationDao.insert(application);
	}

	@Override
	public void modifyGroup(TbWzGroup group) throws DataAccessException {
		groupDao.updateByPrimaryKeySelective(group);
	}

	@Override
	public GroupApplicationInfo getApplicationById(long id) throws DataAccessException {
		return applicationDao.getApplicationDetail(id);
	}

	@Override
	public boolean modifyGroupApplicationStatus(long id, int status) throws DataAccessException {
		TbWzGroupApplication app = applicationDao.selectByPrimaryKey(id);
		if (app == null)
			return false;
		
		if (status == DataConstant.REVIEW_STATUS_APPROVED) {
			TbWzUser invitees = userDao.selectByPrimaryKey(app.getUserId());
			invitees.setGroupId(app.getGroupId());
			
			// 如果是是通过邀请码加入团队的 邀请者和被邀请者都能获奖励
			if (app.getInviteBy() != null && app.getInviteBy() != 0L) {
				
				GroupApplicationInfo gaParam = new GroupApplicationInfo();
				gaParam.setUserId(Long.valueOf(app.getUserId()));
				gaParam.setStatus(DataConstant.REVIEW_STATUS_APPROVED);
				gaParam.setIsInvited(DataConstant.TRUE);
				int invitedCount = applicationDao.count(gaParam);
				
				if (invitedCount == 0) { // 团队邀请码只能奖励一次
					TbWzUser inviter = userDao.selectByPrimaryKey(app.getInviteBy());
					
					TbWzCommonSetting setting = settingDao.selectByPrimaryKey(1L);
					BigDecimal er = new BigDecimal(String.valueOf(setting.getExchangeRate()));
					BigDecimal inviterReward = new BigDecimal(String.valueOf(setting.getGroupInviterReward()));
					BigDecimal inviteesReward = new BigDecimal(String.valueOf(setting.getGroupInviteesReward()));
					
					inviter.setBalance(inviter.getBalance() + inviterReward.multiply(er).longValue());
					invitees.setBalance(invitees.getBalance() + inviteesReward.multiply(er).longValue());
					
					userDao.updateByPrimaryKeySelective(inviter);
				}
			}
			
			userDao.updateByPrimaryKeySelective(invitees);
		}
		
		app.setStatus(status);
		applicationDao.updateByPrimaryKeySelective(app);
		
		return true;
	}

	@Override
	public void deleteGroupUser(long userId) throws DataAccessException {
		TbWzUser user = userDao.selectByPrimaryKey(userId);
		user.setGroupId(0L);
		userDao.updateByPrimaryKeySelective(user);
	}

	@Override
	public List<RankingInfo> groupRanking(int size) throws DataAccessException {
		return groupDao.groupRanking(size);
	}

	@Override
	public void deleteTodismissGroup(long groupId) throws DataAccessException {
		groupDao.dismissGroupUsers(groupId);
		groupDao.deleteByPrimaryKey(groupId);
		ratioDao.deleteByGroupId(groupId);
	}

	@Override
	public void deletePendingAppByUser(long userId) throws DataAccessException {
		applicationDao.deletePendingAppByUser(userId);
	}

}
