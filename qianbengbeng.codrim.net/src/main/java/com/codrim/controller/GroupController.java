package com.codrim.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codrim.bean.JsonBasicResult;
import com.codrim.bean.JsonErrorResult;
import com.codrim.bean.group.JsonGroupDetail;
import com.codrim.bean.task.JsonTaskRecord;
import com.codrim.bean.user.JsonUserDetail;
import com.codrim.constant.ErrorCode;
import com.codrim.util.ParametersDebugUtils;

import common.codrim.common.Contans;
import common.codrim.pojo.TbWzGroup;
import common.codrim.pojo.TbWzGroupApplication;
import common.codrim.pojo.TbWzUser;
import common.codrim.service.WzGroupService;
import common.codrim.service.WzTaskService;
import common.codrim.service.WzUserService;
import common.codrim.util.DateUtil;
import common.codrim.util.StringUtil;
import common.codrim.wz.constant.DataConstant;
import common.codrim.wz.sql.in.Order;
import common.codrim.wz.sql.result.GroupApplicationInfo;
import common.codrim.wz.sql.result.GroupInfo;
import common.codrim.wz.sql.result.TaskRecordInfo;

@Controller
public class GroupController extends BaseController {
	
	private static final Logger logger = Logger.getLogger("yj");
	
	@Autowired
	private WzGroupService groupService;
	
	@Autowired
	private WzUserService userService;
	
	@Autowired
	private WzTaskService taskService;
	
	
	/**
	 * 获取团队详情
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/getGroupDetail.do")
	@ResponseBody
	public JsonBasicResult getGroupDetail(HttpServletRequest request) throws DataAccessException {

		String userId = request.getParameter("userId");
		String page = request.getParameter("page");
		
		ParametersDebugUtils.debugParameters(request, "Get Group Detail", "userId", "page");
		if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(page)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+userId+page);
if(!sign_flag){
	return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
}
		JsonBasicResult<JsonGroupDetail> taskInfoResult = new JsonBasicResult<JsonGroupDetail>();
		try {
			JsonGroupDetail jgd = new JsonGroupDetail();
	
			TbWzUser user = userService.getUserById(Long.valueOf(userId));
			if (user.getGroupId() != null && user.getGroupId() != 0L) {
				GroupInfo groupInfo = groupService.getGroupDetail(user.getGroupId());
				jgd.setGroupId(groupInfo.getGroupId());
				jgd.setGroupIcon(groupInfo.getGroupIcon());
				jgd.setGroupName(groupInfo.getGroupName());
				jgd.setGroupDesc(groupInfo.getGroupDesc());
				jgd.setGroupIncome(StringUtil.toString(groupInfo.getGroupIncome()));
				jgd.setGroupUserNo(StringUtil.toString(groupInfo.getGroupUserNo()));
				jgd.setLeaderRatio(StringUtil.toString(groupInfo.getLeaderPercent()));
				jgd.setLeaderName(groupInfo.getLeaderName());
				jgd.setLeaderIcon(groupInfo.getLeaderIcon());
				if (userId.equals(String.valueOf(groupInfo.getGroupLeader()))) {
					jgd.setUserRole(String.valueOf(DataConstant.GROUP_ROLE_LEADER));
				} else {
					jgd.setUserRole(String.valueOf(DataConstant.GROUP_ROLE_NORMAL));
				}
				
				// 检查是否有申请待处理
				GroupApplicationInfo gaParam = new GroupApplicationInfo();
				gaParam.setGroupId(groupInfo.getGroupId());
				gaParam.setStatus(DataConstant.REVIEW_STATUS_PENDING);
				int pendingApplication = groupService.getGroupApplicationTotalAmount(gaParam);
				jgd.setHasApplication(pendingApplication == 0? String.valueOf(DataConstant.FALSE) : String.valueOf(DataConstant.TRUE));
				
				TbWzUser param = new TbWzUser();
				param.setGroupId(user.getGroupId());
				List<TbWzUser> groupUsers = userService.searchUser(Integer.valueOf(page), DataConstant.PAGESIZE_20, param, new Order("balance", Order.DESC));
				
				if (groupUsers != null && groupUsers.size() > 0) {
					for (TbWzUser groupUser : groupUsers) {
						JsonUserDetail ud = new JsonUserDetail();
						ud.setUserId(groupUser.getUserId());
						ud.setUsername(groupUser.getUsername());
						ud.setPhoneNumber(groupUser.getPhoneNumber());
						ud.setIcon(groupUser.getIcon());
						ud.setEmail(groupUser.getEmail());
						ud.setBalance(StringUtil.toString(groupUser.getBalance()));
						jgd.addGroupUser(ud);
					}
				}
			} else {
				// 检查该用户是否有提交过申请
				GroupApplicationInfo gaParam = new GroupApplicationInfo();
				gaParam.setUserId(Long.valueOf(userId));
				gaParam.setStatus(DataConstant.REVIEW_STATUS_PENDING);
				int pendingApplication = groupService.getGroupApplicationTotalAmount(gaParam);
				jgd.setHasApplication(pendingApplication == 0? String.valueOf(DataConstant.FALSE) : String.valueOf(DataConstant.TRUE));
				jgd.setGroupId(0L);
			}
			
			taskInfoResult.setRtCode(DataConstant.SUCCESS);
			taskInfoResult.setResult(jgd);
			
		} catch(Exception e) {
			logger.error(">>>>> Get Group Detail ERROR!! userId=" + userId, e);
			return new JsonErrorResult(ErrorCode.UN000, "getGroupDetail ERROR");
		}
		
		return taskInfoResult;
	}
	
	/**
	 * 验证团队邀请码是否有效
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/checkGroupInviteCode.do")
	@ResponseBody
	public JsonBasicResult checkGroupInviteCode(HttpServletRequest request) throws DataAccessException {

		String code = request.getParameter("code");
		
		ParametersDebugUtils.debugParameters(request, "Check Group Invite Code", "code");
		if (StringUtil.isEmpty(code)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+code);
if(!sign_flag){
	return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
}
		JsonBasicResult taskInfoResult = new JsonBasicResult();
		
		try {
			Pattern pattern = Pattern.compile("^[\\d]{4}$");
			if (!pattern.matcher(code).matches()) {
				return new JsonErrorResult(ErrorCode.GP201, "");
				
			} else {
				if (groupService.checkCode(Long.valueOf(code))) {
					taskInfoResult.setRtCode(DataConstant.SUCCESS);
				} else {
					return new JsonErrorResult(ErrorCode.GP201, "");
				}
			}
			
		} catch(Exception e) {
			logger.error(">>>>> Check Group Invite Code ERROR!! code=" + code, e);
			return new JsonErrorResult(ErrorCode.GP201, "");
		}
		
		return taskInfoResult;
	}
	
	/**
	 * 创建团队
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/createGroup.do")
	@ResponseBody
	public JsonBasicResult createGroup(HttpServletRequest request) throws DataAccessException {

		String userId = request.getParameter("userId");
		String groupName = request.getParameter("groupName");
		String groupDesc = request.getParameter("groupDesc");
		String groupIcon = request.getParameter("groupIcon");
		String code = request.getParameter("code"); 
		
		ParametersDebugUtils.debugParameters(request, "Create Group", "userId", "groupName", "groupDesc", "groupIcon", "code");
		if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(groupName) || StringUtil.isEmpty(groupDesc)
				|| StringUtil.isEmpty(groupIcon) || StringUtil.isEmpty(code)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		logger.info(">>>>> Create Group: userId=" + userId + ", code=" + code + ", groupName=" + groupName);
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+userId+groupName+groupDesc+groupIcon+code);
if(!sign_flag){
	return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
}
		JsonBasicResult result = new JsonBasicResult();
		try {
			TbWzGroup group = new TbWzGroup();
			group.setGroupName(groupName);
			group.setGroupDesc(groupDesc);
			group.setGroupIncome(0L);
			group.setGroupLeader(Long.valueOf(userId));
			group.setLeaderPercent(DataConstant.GROUP_LEADER_PERCENT_DEFAULT);
			group.setCreateDate(new Date());
			group.setGroupEffect(0);
			
			String groupIconName = StringUtil.getUniqueFilename("foo.png");
			FileUtils.writeByteArrayToFile(new File(imgUploadRoot + groupIconName), Base64.decodeBase64(groupIcon));
			group.setGroupIcon(imgRoot + groupIconName);
			
			groupService.addGroup(group, Long.valueOf(userId), Long.valueOf(code));
			
			result.setRtCode(DataConstant.SUCCESS);
			
		} catch(Exception e) {
			logger.error(">>>>> Create Group ERROR!! userId=" + userId, e);
			return new JsonErrorResult(ErrorCode.UN000, "createGroup ERROR");
		}
		
		return result;
	}
	
	/**
	 * 加入团队申请
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/groupApplication.do")
	@ResponseBody
	public JsonBasicResult groupApplication(HttpServletRequest request) throws DataAccessException {

		String userId = request.getParameter("userId");
		String paramTypeStr = request.getParameter("paramType");
		String param = request.getParameter("param");
		String joinReason = request.getParameter("joinReason");
		
		ParametersDebugUtils.debugParameters(request, "Create Group Application", "userId", "paramType", "param", "joinReason");
		if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(paramTypeStr) || StringUtil.isEmpty(param)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+userId+paramTypeStr+param+joinReason);
		if(!sign_flag){
			return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
		}
		JsonBasicResult<Integer> result = new JsonBasicResult<Integer> ();
		try {
			int paramType = Integer.valueOf(paramTypeStr);
			if (paramType == DataConstant.GROUP_SEARCH_INVID) {
				long inviter = getUserIdByInviteCode(param, DataConstant.INVITE_CODE_TYPE_JOINGROUP);
				if (inviter == 0) {
					return new JsonErrorResult(ErrorCode.GP201, "");
				}
				param = String.valueOf(inviter);
			}
					
			TbWzGroup group = groupService.searchGroup(param, paramType);
			if (group == null) {
				if (paramType == DataConstant.GROUP_SEARCH_GID) {
					return new JsonErrorResult(ErrorCode.GP202, "");
				} else if (paramType == DataConstant.GROUP_SEARCH_GNAME) {
					return new JsonErrorResult(ErrorCode.GP203, "");
				} else if (paramType == DataConstant.GROUP_SEARCH_INVID) {
					return new JsonErrorResult(ErrorCode.GP201, "");
				}
			}
			
			GroupInfo groupInfo = groupService.getGroupDetail(group.getGroupId());
			if(groupInfo!=null){
		int gourpUser=groupInfo.getGroupUserNo();
		int limitNum=getLimitNum(Contans.TASK_NUM_LIMIT_GROUP+"");
		result.setRtCode(DataConstant.SUCCESS);
		
		if(gourpUser>=limitNum){
			result.setResult(1);
			return result;
		}
			}else{
				result.setResult(0);
			}
			TbWzGroupApplication application = new TbWzGroupApplication();
			application.setGroupId(group.getGroupId());
			application.setUserId(Long.valueOf(userId));
			application.setJoinReason(joinReason);
			application.setStatus(DataConstant.REVIEW_STATUS_PENDING);
			application.setCreateDate(new Date());
			if (paramType == DataConstant.GROUP_SEARCH_INVID)
				application.setInviteBy(Long.valueOf(param));
			
			groupService.addGroupApplication(application);
			
			
		} catch(Exception e) {
			logger.error(">>>>> Create Group Application ERROR!! userId=" + userId + ", paramTypeStr=" + paramTypeStr + ", param=" + param, e);
			return new JsonErrorResult(ErrorCode.UN000, "groupApplication ERROR");
		}
		
		return result;
	}
	
	/**
	 * 取消加入团队申请
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/cancelApplication.do")
	@ResponseBody
	public JsonBasicResult cancelApplication(HttpServletRequest request) throws DataAccessException {

		String userId = request.getParameter("userId");
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+userId);
		if(!sign_flag){
			return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
		}
		ParametersDebugUtils.debugParameters(request, "Cancel Group Application", "userId");
		if (StringUtil.isEmpty(userId)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		
		JsonBasicResult result = new JsonBasicResult();
		try {
			groupService.deletePendingAppByUser(Long.valueOf(userId));
			
			result.setRtCode(DataConstant.SUCCESS);
			
		} catch(Exception e) {
			logger.error(">>>>> Cancel Group Application ERROR!! userId=" + userId, e);
			return new JsonErrorResult(ErrorCode.UN000, "cancelApplication ERROR");
		}
		
		return result;
	}
	
	/**
	 * 加入团队申请列表
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/groupApplicationList.do")
	@ResponseBody
	public JsonBasicResult groupApplicationList(HttpServletRequest request) throws DataAccessException {

		String groupId = request.getParameter("groupId");
		String page = request.getParameter("page");
		
		ParametersDebugUtils.debugParameters(request, "Get Group Application List", "groupId", "page");
		if (StringUtil.isEmpty(groupId) || StringUtil.isEmpty(page)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+groupId+page);
		if(!sign_flag){
			return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
		}
		JsonBasicResult<List<GroupApplicationInfo>> result = new JsonBasicResult<List<GroupApplicationInfo>>();
		try {
			GroupApplicationInfo param = new GroupApplicationInfo();
			param.setGroupId(Long.valueOf(groupId));
			param.setStatus(DataConstant.REVIEW_STATUS_PENDING);
			
			List<GroupApplicationInfo> apps = groupService.searchGroupApplication(Integer.valueOf(page), DataConstant.PAGESIZE_20, param);
			
			result.setResult(apps);
			result.setRtCode(DataConstant.SUCCESS);
			
		} catch(Exception e) {
			logger.error(">>>>> Get Group Application List ERROR!! groupId=" + groupId, e);
			return new JsonErrorResult(ErrorCode.UN000, "groupApplicationList ERROR");
		}
		
		return result;
	}
	
	/**
	 * 加入团队申请详情
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/groupApplicationDetail.do")
	@ResponseBody
	public JsonBasicResult groupApplicationDetail(HttpServletRequest request) throws DataAccessException {

		String id = request.getParameter("id");
		
		ParametersDebugUtils.debugParameters(request, "Get Group Application Detail", "id");
		if (StringUtil.isEmpty(id)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+id);
		if(!sign_flag){
			return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
		}
		JsonBasicResult<GroupApplicationInfo> result = new JsonBasicResult<GroupApplicationInfo>();
		try {
			
			GroupApplicationInfo app = groupService.getApplicationById(Long.valueOf(id));
			
			result.setResult(app);
			result.setRtCode(DataConstant.SUCCESS);
			
		} catch(Exception e) {
			logger.error(">>>>> Get Group Application Detail ERROR!! id=" + id, e);
			return new JsonErrorResult(ErrorCode.UN000, "groupApplicationDetail ERROR");
		}
		
		return result;
	}
	
	/**
	 * 批准/拒绝加入团队申请
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/groupApplicationResult.do")
	@ResponseBody
	public JsonBasicResult groupApplicationResult(HttpServletRequest request) throws DataAccessException {

		String id = request.getParameter("id");
		String appResult = request.getParameter("result");
		String groupId = request.getParameter("groupId");
		ParametersDebugUtils.debugParameters(request, "Change Group Application Result", "id", "result","groupId");
		if (StringUtil.isEmpty(id) || StringUtil.isEmpty(appResult)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+id+appResult);
		if(!sign_flag){
			return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
		}
		JsonBasicResult result = new JsonBasicResult();
		try {
			int status = 0;
			GroupInfo groupInfo = groupService.getGroupDetail(Long.parseLong(groupId));
			if(groupInfo!=null){
			int gourpUser=groupInfo.getGroupUserNo();
			int limitNum=getLimitNum(Contans.TASK_NUM_LIMIT_GROUP+"");
			result.setRtCode(DataConstant.SUCCESS);
			if(gourpUser>=limitNum){
				result.setResult(1);
				return result;
			}
			}else{
				result.setResult(0);
			}
			
			
			if (DataConstant.TRUE == Integer.valueOf(appResult)) {
				status = DataConstant.REVIEW_STATUS_APPROVED;
			} else {
				status = DataConstant.REVIEW_STATUS_DENY;
			}
			boolean r = groupService.modifyGroupApplicationStatus(Long.valueOf(id), status);
			
			if (!r)
				return new JsonErrorResult(ErrorCode.GP204, "");
			
			result.setRtCode(DataConstant.SUCCESS);
			
		} catch(Exception e) {
			logger.error(">>>>> Change Group Application Result ERROR!! id=" + id + ", result=" + result, e);
			return new JsonErrorResult(ErrorCode.UN000, "groupApplicationResult ERROR");
		}
		
		return result;
	}
	

	
	/**
	 * 修改团队名字,介绍，头像
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/updateGroup.do")
	@ResponseBody
	public JsonBasicResult updateGroupName(HttpServletRequest request) throws DataAccessException {

		String groupId = request.getParameter("groupId");
		String leaderPercent = request.getParameter("leaderPercent");
		String groupName = request.getParameter("groupName");
		String groupDesc = request.getParameter("groupDesc");
		String groupIcon = request.getParameter("groupIcon");
		
		ParametersDebugUtils.debugParameters(request, "Update Group ", "groupId", "groupName","groupDesc","groupIcon");
		if (StringUtil.isEmpty(groupId) ) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+groupId+leaderPercent+groupName+groupDesc+groupIcon);
		if(!sign_flag){
			return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
		}
		JsonBasicResult result = new JsonBasicResult();
		try {
			TbWzGroup group = new TbWzGroup();
			group.setGroupId(Long.valueOf(groupId));
			if(!StringUtil.isBlank(groupIcon)){
				String groupIconName = StringUtil.getUniqueFilename("foo.png");
				FileUtils.writeByteArrayToFile(new File(imgUploadRoot + groupIconName), Base64.decodeBase64(groupIcon));
				group.setGroupIcon(imgRoot + groupIconName);
			}else if(!StringUtil.isBlank(groupName)){
				group.setGroupName(groupName);
			}else if(!StringUtil.isBlank(groupDesc)){
				group.setGroupDesc(groupDesc);
				}else if(!StringUtil.isBlank(leaderPercent)){
					group.setLeaderPercent(Integer.valueOf(leaderPercent));
					}
			
			groupService.modifyGroup(group);
			
			result.setRtCode(DataConstant.SUCCESS);
			
		} catch(Exception e) {
			logger.error(">>>>> Update Group Name ERROR!! groupId=" + groupId + ", groupName=" + groupName, e);
			return new JsonErrorResult(ErrorCode.UN000, "updateGroupName ERROR");
		}
		
		return result;
	}
	
	
	
	
	/**
	 * 团队成员列表
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/groupUserList.do")
	@ResponseBody
	public JsonBasicResult groupUserList(HttpServletRequest request) throws DataAccessException {

		String groupId = request.getParameter("groupId");
		String page = request.getParameter("page");
		
		ParametersDebugUtils.debugParameters(request, "Get Group User List", "groupId", "page");
		if (StringUtil.isEmpty(groupId) || StringUtil.isEmpty(page)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+groupId+page);
		if(!sign_flag){
			return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
		}
		JsonBasicResult<JsonGroupDetail> result = new JsonBasicResult<JsonGroupDetail>();
		try {
			JsonGroupDetail gd = new JsonGroupDetail();
			
			TbWzUser countParam = new TbWzUser();
			countParam.setGroupId(Long.valueOf(groupId));
			gd.setGroupUserNo(StringUtil.toString(userService.getUserTotalAmount(countParam) - 1)); //排除Leader
			
			TbWzUser param = new TbWzUser();
			param.setGroupId(Long.valueOf(groupId));
			param.setIsLeader(DataConstant.FALSE);
			List<TbWzUser> groupUsers = userService.searchUser(Integer.valueOf(page), 
					DataConstant.PAGESIZE_20, param, new Order("balance", Order.DESC));
			
			if (groupUsers != null && groupUsers.size() > 0) {
				List<JsonUserDetail> uds = new ArrayList<JsonUserDetail>();
				for (TbWzUser gu : groupUsers) {
					JsonUserDetail ud = new JsonUserDetail();
					ud.setUserId(gu.getUserId());
					ud.setUsername(gu.getUsername());
					ud.setIcon(gu.getIcon());
					ud.setBalance(StringUtil.toString(gu.getBalance()));
					
					uds.add(ud);
				}
				
				gd.setGroupUsers(uds);
			}
			
			result.setResult(gd);
			result.setRtCode(DataConstant.SUCCESS);
			
		} catch(Exception e) {
			logger.error(">>>>> Get Group User List ERROR!! groupId=" + groupId, e);
			return new JsonErrorResult(ErrorCode.UN000, "groupUserList ERROR");
		}
		
		return result;
	}
	
	/**
	 * 团队成员详情
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/groupUserDetail.do")
	@ResponseBody
	public JsonBasicResult groupUserDetail(HttpServletRequest request) throws DataAccessException {

		String userId = request.getParameter("userId");
		String page = request.getParameter("page");
		
		ParametersDebugUtils.debugParameters(request, "Get Group User Detail", "userId", "page");
		if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(page)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+userId+page);
		if(!sign_flag){
			return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
		}
		JsonBasicResult<JsonUserDetail> result = new JsonBasicResult<JsonUserDetail>();
		try {
			JsonUserDetail ud = new JsonUserDetail();
			
			TbWzUser user = userService.getUserById(Long.valueOf(userId));
			ud.setUserId(user.getUserId());
			ud.setUsername(user.getUsername());
			ud.setIcon(user.getIcon());
			ud.setPhoneNumber(user.getPhoneNumber());
			ud.setEmail(user.getEmail());
			ud.setLastLoginDate(DateUtil.formatDate(user.getLastLoginDate()));
			ud.setBalance(StringUtil.toString(user.getBalance()));
			
			TaskRecordInfo param =  new TaskRecordInfo();
			param.setUserId(Long.valueOf(userId));
			param.setRealFinished(true);
			List<TaskRecordInfo> records = taskService.searchTaskRecord(Integer.valueOf(page), DataConstant.PAGESIZE_20, param);
			
			if (records != null && records.size() > 0) {
				for (TaskRecordInfo record : records) {
					JsonTaskRecord tr = new JsonTaskRecord();
					tr.setFinshDate(DateUtil.formatDateTime("MM-dd", record.getFinishDate()));
					tr.setIncomeGold(StringUtil.toString(record.getIncomeGold()));
					tr.setStepDesc(record.getStepDesc());
					tr.setRecordId(record.getRecordId());
					tr.setTaskName(record.getTaskName());
					
					ud.addTaskRecord(tr);
				}
			}
			
			result.setResult(ud);
			result.setRtCode(DataConstant.SUCCESS);
			
		} catch(Exception e) {
			logger.error(">>>>> Get Group User Detail ERROR!! userId=" + userId, e);
			return new JsonErrorResult(ErrorCode.UN000, "groupUserDetail ERROR");
		}
		
		return result;
	}
	
	/**
	 * 踢出团队成员/退出团队
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/deleteGroupUser.do")
	@ResponseBody
	public JsonBasicResult deleteGroupUser(HttpServletRequest request) throws DataAccessException {

		String userId = request.getParameter("userId");
		
		ParametersDebugUtils.debugParameters(request, "Delete Group User", "userId");
		if (StringUtil.isEmpty(userId)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		logger.info(">>>>> Delete Group User: userId=" + userId);
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+userId);
		if(!sign_flag){
			return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
		}
		JsonBasicResult result = new JsonBasicResult();
		try {
			groupService.deleteGroupUser(Long.valueOf(userId));
			
			result.setRtCode(DataConstant.SUCCESS);
			
		} catch(Exception e) {
			logger.error(">>>>> Delete Group User ERROR!! userId=" + userId, e);
			return new JsonErrorResult(ErrorCode.UN000, "deleteGroupUser ERROR");
		}
		
		return result;
	}
	
	/**
	 * 解散团队
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/dismissGroup.do")
	@ResponseBody
	public JsonBasicResult dismissGroup(HttpServletRequest request) throws DataAccessException {

		String groupId = request.getParameter("groupId");
		
		ParametersDebugUtils.debugParameters(request, "Dismiss Group", "groupId");
		if (StringUtil.isEmpty(groupId)) {
			return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
		}
		logger.info(">>>>> Dismiss Group: groupId=" + groupId);
		boolean sign_flag=checkSign(request.getParameter("sign"),request.getParameter("timeStamp")+groupId);
		if(!sign_flag){
			return new JsonErrorResult(ErrorCode.UN400, "sign check failed!");
		}
		JsonBasicResult result = new JsonBasicResult();
		try {
			groupService.deleteTodismissGroup(Long.valueOf(groupId));
			
			result.setRtCode(DataConstant.SUCCESS);
			
		} catch(Exception e) {
			logger.error(">>>>> Dismiss Group ERROR!! groupId=" + groupId, e);
			return new JsonErrorResult(ErrorCode.UN000, "deleteGroupUser ERROR");
		}
		
		return result;
	}

}
