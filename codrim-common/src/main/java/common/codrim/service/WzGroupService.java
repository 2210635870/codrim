package common.codrim.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import common.codrim.pojo.TbWzGroup;
import common.codrim.pojo.TbWzGroupApplication;
import common.codrim.pojo.TbWzGroupInviteCode;
import common.codrim.wz.sql.result.GroupApplicationInfo;
import common.codrim.wz.sql.result.GroupInfo;
import common.codrim.wz.sql.result.RankingInfo;

public interface WzGroupService {
	
	public List<GroupInfo> searchGroup(int startPage, int size, TbWzGroup param) throws DataAccessException;
	public int getGroupTotalAmount(TbWzGroup param) throws DataAccessException;
	public GroupInfo getGroupDetail(long groupId) throws DataAccessException;
	
	public List<TbWzGroupInviteCode> searchInviteCode(int startPage, int size, TbWzGroupInviteCode param) throws DataAccessException;
	public int getInviteCodeTotalAmount(TbWzGroupInviteCode param) throws DataAccessException;
	public long addCode(TbWzGroupInviteCode code) throws DataAccessException;
	public boolean checkCode(long code) throws DataAccessException;
	public TbWzGroup getGroupByUser (long userId) throws DataAccessException;
	public List<GroupApplicationInfo> searchGroupApplication(int startPage, int size, GroupApplicationInfo param) throws DataAccessException;
	public int getGroupApplicationTotalAmount(GroupApplicationInfo param) throws DataAccessException;
	public void addGroupApplication(TbWzGroupApplication application) throws DataAccessException;
	public boolean modifyGroupApplicationStatus(long id, int status) throws DataAccessException;
	public GroupApplicationInfo getApplicationById(long id) throws DataAccessException;
	public void deletePendingAppByUser(long userId) throws DataAccessException;
	
	public void addGroup(TbWzGroup group, long userId, long inviteCode) throws DataAccessException;
	public TbWzGroup searchGroup(String param, int paramType) throws DataAccessException;
	public void modifyGroup(TbWzGroup group) throws DataAccessException;
	public void deleteGroupUser(long userId) throws DataAccessException;
	public void deleteTodismissGroup(long groupId) throws DataAccessException;
	
	public List<RankingInfo> groupRanking(int size) throws DataAccessException;
}
