<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp"%>

<html>
<head>
<jsp:include page="../common/meta.jsp" flush="true" />

<script type="text/javascript">

	$(function() {
		ajaxFillSelect("taskIdFilter", "/loadTaskNames.do", "0");
		
		$('#taskReviewList').datagrid({
			url: '${ctx}/wz/review/taskReviewList.do'
		});
	});
	
	function getQueryParams(queryParams) {  
	    var taskId = $("#taskIdFilter").combobox("getValue");
	    var username = $("#usernameFilter").val();
	    var phoneNumber = $("#phoneNumberFilter").val();
	    var reviewStatus = $("#reviewStatusFilter").combobox("getValue");
	 
	    queryParams.taskId = taskId;
	    queryParams.username = username;
	    queryParams.phoneNumber = phoneNumber;
	    queryParams.reviewStatus = reviewStatus;
	 
	    return queryParams;  
	}
	
    function reloadGrid() {  
        var queryParams = $('#taskReviewList').datagrid('options').queryParams;  
        getQueryParams(queryParams);  
        $('#taskReviewList').datagrid('options').queryParams = queryParams;  
        $("#taskReviewList").datagrid('reload');  
    }
    
    function actionformater(value, row, index) {
    	if (row.reviewStatus == "1") {
    		return "<a href='${ctx}/wz/review/initReviewTask.do?recordId="+row.recordId+"'>审核</a>";
    	} else {
    		return "<a href='${ctx}/wz/review/viewReviewTask.do?recordId="+row.recordId+"'>查看</a>";
    	}
    }
    
    function iconFormatter(value, row, index) {
    	return "<img width=\"50\" height=\"50\" src=\"${imgRoot}"+row.appIcon+"\" />";
    }
    
    function reviewStatusFormatter(value, row, index) {
    	if (value == "1") {
    		return "待审核";
    	} else if (value == "2") {
    		return "通过";
    	} else {
    		return "不通过";
    	}
    }
    
</script>

</head>

<body style="visibility: visible;">
	<table id="searchTb" cellpadding="5" >
		<tr>
			<td>
				产品名:
				<input id="taskIdFilter" class="easyui-validatebox" />
			</td>
			<td>
				用户名:
				<input id="usernameFilter" class="easyui-validatebox" />
			</td>
			<td>
				手机号:
				<input id="phoneNumberFilter" class="easyui-validatebox" />
			</td>
			<td>
				状态:
				<select id="reviewStatusFilter" class="easyui-combobox">
					<option value="0">全部</option>
					<option value="1">待审核</option>
				    <option value="2">通过</option>
				    <option value="3">不通过</option>
				</select>
			</td>
			<td>
				<div>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="reloadGrid()">搜索</a>
				</div>
			</td>
		</tr>
	</table>
	
	<table id="taskReviewList" idField="recordId" rownumbers="true" pagination="true" singleSelect="true" pageSize=10 >
		<thead>
			<tr>
				<th field="appIcon" align="center" width="auto" formatter="iconFormatter">产品ICON</th>
				<th field="taskName" align="center" width="150">产品名</th>
				<th field="stepDesc" align="center" width="200">任务描述</th>
				<th field="username" align="center" width="auto">用户名</th>
				<th field="phoneNumber" align="center" width="auto">手机号</th>
				<th field="reviewStatus" align="center" width="auto" formatter="reviewStatusFormatter">状态</th>
				<th field="reviewAction" align="center" width="100" formatter="actionformater">操作</th>
			</tr>
		</thead>
	</table>

</body>
</html>