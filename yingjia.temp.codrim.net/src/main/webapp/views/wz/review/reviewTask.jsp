<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp"%>

<html>
<head>
<jsp:include page="../common/meta.jsp" flush="true" />

<script type="text/javascript">
	
	function reviewTask(result) {
		$('#taskReviewForm').form("submit", {    
		    url: '${ctx}/wz/review/reviewTask.do?result='+result,    
		    onSubmit: function(){
				return $(this).form('validate');  
		    },
		    success:function(data){    
		    	var result = eval('(' + data + ')');
				if (result.success) {
					$.messager.alert('审核成功', '审核任务成功！', 'info', function(){
						location.href = "${ctx}/views/wz/review/taskReviewList.jsp";
					});
				} else {
					$.messager.alert('审核失败', result.msg, 'error');
				}
		    }    
		});
	}
	

</script>

</head>

<body style="visibility: visible;">

<div class="easyui-panel" title="审核任务" style="width:800px">
	<div style="padding:10px 10px 10px 10px">
		
		<form:form commandName="taskReview" id="taskReviewForm" method="post" action="/wz/review/reviewTask.do">
			<input type="hidden" id="exchangeRate" value="${settingCurrency.exchangeRate}" />
			<input type="hidden" id="groupCodrimPercent" value="${settingCurrency.groupCodrimPercent}" />
			<input type="hidden" id="userCodrimPercent" value="${settingCurrency.userCodrimPercent}" />
			
			<table cellpadding="5">
				<tr>
					<td class="field-name">产品名称:</td>
					<td>
						${taskReview.taskName}
					</td>
				</tr>
				<tr>
					<td class="field-name">产品ICON:</td>
					<td>
						<img id="appIconImg" width="100" height="100" src="${imgRoot}${taskReview.appIcon}" />
					</td>
				</tr>
				<tr>
					<td class="field-name">用户名:</td>
					<td>
						${taskReview.username}
					</td>
				</tr>
				<tr>
					<td class="field-name">用户手机号:</td>
					<td>
						${taskReview.phoneNumber}
					</td>
				</tr>
				<tr>
					<td class="field-name">任务名称:</td>
					<td>
						${taskReview.stepDesc}
					</td>
				</tr>
				<tr>
					<td class="field-name">任务要求:</td>
					<td>
						${taskReview.screenDesc}
					</td>
				</tr>
				<tr>
					<td class="field-name">待审核截图:</td>
					<td>
						<div style="width: 650; overflow-x:scroll; white-space: nowrap;">
							<c:if test="${not empty taskReview.appScreen1}">
								<img id="appScreen1" width="300" height="400" src="${imgRoot}${taskReview.appScreen1}" />
							</c:if>
							<c:if test="${not empty taskReview.appScreen2}">
								<img id="appScreen2" width="300" height="400" src="${imgRoot}${taskReview.appScreen2}" />
							</c:if>
							<c:if test="${not empty taskReview.appScreen3}">
								<img id="appScreen3" width="300" height="400" src="${imgRoot}${taskReview.appScreen3}" />
							</c:if>
							<c:if test="${not empty taskReview.appScreen4}">
								<img id="appScreen4" width="300" height="400" src="${imgRoot}${taskReview.appScreen4}" />
							</c:if>
							<c:if test="${not empty taskReview.appScreen5}">
								<img id="appScreen5" width="300" height="400" src="${imgRoot}${taskReview.appScreen5}" />
							</c:if>
						</div>
					</td>
				</tr>
				<tr>
					<td class="field-name">备注:</td>
					<td>
						<form:textarea path="reviewRemark" id="reviewRemarkInput" maxlength="20" cols="60" rows="6"  
								class="easyui-validatebox" />
					</td>
				</tr>
			</table>
			
			<br>
			<div style="text-align:center;padding:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="reviewTask('1')">通过</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="reviewTask('0')">不通过</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="location.href='${ctx}/views/wz/review/taskReviewList.jsp'">返回</a>
			</div>
		</form:form>
	</div>
</div>
	
</body>
</html>