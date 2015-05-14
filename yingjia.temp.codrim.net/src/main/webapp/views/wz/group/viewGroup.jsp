<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp"%>

<html>
<head>
<jsp:include page="../common/meta.jsp" flush="true" />

<script type="text/javascript">

	$(function() {
		$('#groupApplicationList').datagrid({
			url: '${ctx}/wz/group/groupApplicationList.do?groupId=${group.groupId}'
		});
		
		$('#groupUserList').datagrid({
			url: '${ctx}/wz/user/userList.do?groupId=${group.groupId}'
		});
	});
	
	
</script>

</head>

<body style="visibility: visible;">


<div class="easyui-panel" title="查看团队" style="width:800px">
	<div style="padding:10px 10px 10px 10px">
		
		<form:form commandName="group" id="groupForm" method="post" action="/wz/group/view.do">
			<table cellpadding="5">
				<tr>
					<td class="field-name">团队头像:</td>
					<td>
						<img src="${imgRoot}${group.groupIcon}" width="100" height="100" />
					</td>
				</tr>
				<tr>
					<td class="field-name">团队ID:</td>
					<td>
						${group.groupId}
					</td>
				</tr>
				<tr>
					<td class="field-name">团队名:</td>
					<td>
						${group.groupName}
					</td>
				</tr>
				<tr>
					<td class="field-name">团队说明:</td>
					<td>
						${group.groupDesc}
					</td>
				</tr>
				<tr>
					<td class="field-name">团队Leader:</td>
					<td>
						${group.leaderName}
					</td>
				</tr>
				<tr>
					<td class="field-name">团队Leader分成:</td>
					<td>
						${group.leaderPercent}%
					</td>
				</tr>
				<tr>
					<td class="field-name">团队人数:</td>
					<td>
						${group.groupUserNo}
					</td>
				</tr>
				<tr>
					<td class="field-name">完成任务数量:</td>
					<td>
						${group.groupEffect}
					</td>
				</tr>
				<tr>
					<td class="field-name">创建时间:</td>
					<td>
						<fmt:formatDate value="${group.createDate}" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
			</table>
		</form:form>
	</div>
	
	<br>
	<fieldset>
		<legend>申请加入团队的用户</legend>
		<table id="groupApplicationList" idField="id" rownumbers="true" pagination="true" singleSelect="true" pageSize=10 >
			<thead>
				<tr>
					<th field="applicantName" align="center" width="auto">申请人</th>
					<th field="joinReason" align="center" width="500">申请理由</th>
					<th field="status" align="center" width="auto" formatter="reviewStatusFormatter">状态</th>
				</tr>
			</thead>
		</table>
	</fieldset>
	
	<br>
	<fieldset>
		<legend>团队成员</legend>
		<table id="groupUserList" idField="userId" rownumbers="true" pagination="true" singleSelect="true" pageSize=10 >
			<thead>
				<tr>
					<th field="username" align="center" width="auto">用户名</th>
					<th field="phoneNumber" align="center" width="auto">手机号</th>
					<th field="email" align="center" width="120">邮箱</th>
					<th field="createDate" align="center" width="auto">注册时间</th>
					<th field="userEffect" align="center" width="auto">完成效果数量</th>
					<th field="balance" align="center" width="auto">余额</th>
				</tr>
			</thead>
		</table>
	</fieldset>
	
	<br>
	<div style="text-align:center;padding:5px">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="location.href='${ctx}/views/wz/group/groupList.jsp'">返回</a>
	</div>
</div>
	
</body>
</html>