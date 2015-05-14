<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp"%>

<html>
<head>
<jsp:include page="../common/meta.jsp" flush="true" />

<script type="text/javascript">
	$(function() {
		$('#bindingList').datagrid({
			url: '${ctx}/wz/user/bindingList.do?userId=${user.userId}'
		});
		
		$('#taskRecordList').datagrid({
			url: '${ctx}/wz/task/taskRecordList.do?userId=${user.userId}'
		});
		
		$('#exchangeRecordList').datagrid({
			url: '${ctx}/wz/user/exchangeList.do?userId=${user.userId}'
		});
		$('#pointsLogList').datagrid({
			url: '${ctx}/wz/user/pointsLogList.do?userId=${user.userId}'
		});
		
	});
	
	function locationDetailFormatter(value, row, index) {
    	return row.country + row.region + row.city + " (" + row.isp + ")";
    }
	function pointsTypeFormatter(value, row, index){
		if(value==1){
			return "任务";
		}
		if(value==2){
			return "邀请";
		}
		if(value==7){
			return "被邀请";
		}
		if(value==3){
			return " 签到";
		}
		if(value==4){
			return "抽奖";
		}
		if(value==5){
			return "积分墙";
		}
		if(value==6){
			return "团队成员";
		}
		if(value==8){
			return "锁屏滑动签到";
		}
	}
</script>

</head>

<body style="visibility: visible;">


<div class="easyui-panel" title="查看用户" style="width:auto">
	<div style="padding:10px 10px 10px 10px">
		
		<form:form commandName="user" id="userForm" method="post" action="/wz/user/view.do">
			<table cellpadding="5">
				<tr>
					<td class="field-name">用户头像:</td>
					<td>
						<c:choose>
							<c:when test="${not empty user.icon}">
								<img src="${imgRoot}${user.icon}" width="100" height="100" />
							</c:when>
							<c:otherwise>用户暂未上传头像</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td class="field-name">用户ID:</td>
					<td>
						${user.userId}
					</td>
				</tr>
					<tr>
					<td class="field-name">注册来源:</td>
					<td>
						<c:if test="${user.userSource ==1}">
							盈+
							</c:if>
							<c:if test="${user.userSource ==2}">
							社交墙
							</c:if>
					</td>
				</tr>
						<tr>
					<td class="field-name">是否被禁用:</td>
					<td>
						<c:if test="${user.isDisable ==1}">
							是
							</c:if>
							<c:if test="${user.isDisable ==0}">
							否
							</c:if>
					</td>
				</tr>
				<tr>
					<td class="field-name">用户名:</td>
					<td>
						${user.username}
					</td>
				</tr>
				<tr>
					<td class="field-name">手机号:</td>
					<td>
						${user.phoneNumber}
					</td>
				</tr>
				<tr>
					<td class="field-name">邮箱:</td>
					<td>
						${user.email}
					</td>
				</tr>
				<tr>
					<td class="field-name">注册时间:</td>
					<td>
						<fmt:formatDate value="${user.createDate}" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td class="field-name">余额:</td>
					<td>
						${user.balance} 金币
					</td>
				</tr>
			</table>
		</form:form>
	</div>
	
	<br>
	<fieldset>
		<legend>绑定记录</legend>
		<table id="bindingList" idField="bindingId" rownumbers="true" pagination="true" singleSelect="true" pageSize=10 fitColumns="true" >
			<thead>
				<tr>
				<th field="channelName" align="center" width="150" >来源渠道</th>
					<th field="bindingDate" align="center" width="150" formatter="datetimeFormatter">绑定日期</th>
					<th field="deviceId" align="center" width="150">IMEI</th>
					<th field="mac" align="center" width="150">MAC</th>
					<th field="ip" align="center" width="150">IP</th>
					<th field="location" align="center" width="150" formatter="locationDetailFormatter">详细位置</th>
				</tr>
			</thead>
		</table>
	</fieldset>
	
	<br>
	<fieldset>
		<legend>积分获取记录</legend>
		<table id="pointsLogList" idField="id" rownumbers="true" pagination="true" singleSelect="true" pageSize=10 fitColumns="true">
			<thead>
				<tr>
					<th field="pointsType" align="center" width="320"  formatter="pointsTypeFormatter">类型</th>
					<th field="incomeDate" align="center" width="auto" formatter="datetimeFormatter">日期</th>
					<th field="name" align="center" width="150">名称</th>
					<th field="incomeGold" align="center" width="auto" >金币</th>
				</tr>
			</thead>
		</table>
	</fieldset>
	
	<br>
	<fieldset>
		<legend>任务记录</legend>
		<table id="taskRecordList" idField="recordId" rownumbers="true" pagination="true" singleSelect="true" pageSize=10 fitColumns="true">
			<thead>
				<tr>
					<th field="finishDate" align="center" width="auto" formatter="dateFormatter">完成日期</th>
					<th field="taskName" align="center" width="150">产品</th>
					<th field="stepDesc" align="center" width="320">任务名</th>
					<th field="reviewStatus" align="center" width="auto" formatter="reviewStatusFormatter">审核状态</th>
					<th field="incomeGold" align="center" width="auto">任务金额</th>
				</tr>
			</thead>
		</table>
	</fieldset>
	
	<br>
	<fieldset>
		<legend>兑换记录</legend>
		<table id="exchangeRecordList" idField="recordId" rownumbers="true" pagination="true" singleSelect="true" pageSize=10 fitColumns="true">
			<thead>
				<tr>
					<th field="exchangeDate" align="center" width="auto" formatter="dateFormatter">兑换日期</th>
					<th field="exchangeType" align="center" width="auto" formatter="exchangeTypeFormatter">兑换选项</th>
					<th field="exchangeMoney" align="center" width="auto">金额</th>
					<th field="reviewStatus" align="center" width="auto" formatter="reviewStatusFormatter">审核状态</th>
				</tr>
			</thead>
		</table>
	</fieldset>
	
	<br>
	<div style="text-align:center;padding:5px">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="location.href='${ctx}/views/wz/user/userList.jsp'">返回</a>
	</div>
</div>
	
</body>
</html>