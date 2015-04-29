<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp"%>

<html>
<head>
<jsp:include page="../common/meta.jsp" flush="true" />

<script type="text/javascript">

	$(function() {
		$('#taskList').datagrid({
			url: '${ctx}/wz/task/list.do'
		});
		
		ajaxFillSelect("taskIdFilter", "/loadTaskNames.do", "0");
	});
	
	function getQueryParams(queryParams) {  
	    var taskStartDate = $("#taskStartDate").datebox("getValue");  
	    var taskEndDate = $("#taskEndDate").datebox("getValue");               
	    var taskId = $("#taskIdFilter").combobox("getValue");  
	    var taskStatus = $("#taskStatus").combobox("getValue");
	 
	    queryParams.taskStartDate = taskStartDate;  
	    queryParams.taskEndDate = taskEndDate;  
	    queryParams.taskId = taskId;  
	    queryParams.taskStatus = taskStatus;  
	 
	    return queryParams;  
	}
	
    function reloadGrid() {  
        var queryParams = $('#taskList').datagrid('options').queryParams;  
        getQueryParams(queryParams);  
        $('#taskList').datagrid('options').queryParams = queryParams;  
        $("#taskList").datagrid('reload');  
    }
    
    function taskActionformater(value, row, index) {
    	var actionStr = "";
    	
    	actionStr += "<a href='${ctx}/wz/task/view.do?taskId="+row.taskId+"'>查看</a>";
    	if (row.taskStatus == "进行中") {
    		actionStr += " | <a href='javascript:void(0)' onclick=\"suspend('"+row.taskId+"')\">暂停</a>";
    	}
    	if (row.taskStatus == "暂停") {
    		actionStr += " | <a href='${ctx}/wz/task/initEdit.do?taskId="+row.taskId+"'>编辑</a>";
    		actionStr += " | <a href='javascript:void(0)' onclick=\"online('"+row.taskId+"')\">运行</a>";
    		actionStr += " | <a href='javascript:void(0)' onclick=\"offline('"+row.taskId+"')\">下线</a>";
    		actionStr += " | <a href='javascript:void(0)' onclick=\"deleteTask('"+row.taskId+"')\">删除</a>";
    	}
    	if (row.taskStatus == "下线") {
    		actionStr += " | <a href='javascript:void(0)' onclick=\"deleteTask('"+row.taskId+"')\">删除</a>";
    	}
    		
    	return actionStr;
    }
    
    function suspend(taskId) {
    	changeStatus("暂停任务", "确认暂停该任务吗?", taskId, 2);
    }
    
    function online(taskId) {
    	changeStatus("运行任务", "确认运行该任务吗?", taskId, 1);
    }
    
    function offline(taskId) {
    	changeStatus("下线任务", "确认下线该任务吗?", taskId, 3);
    }
    
    function changeStatus(title, msg, taskId, status) {
    	$.messager.confirm(title, msg, function(r){
    		if (r) {
    			$.ajax({
					url: "${ctx}/wz/task/changeStatus.do?status="+status+"&taskId=" + taskId, 
	        		success: function(){
	        			$("#taskList").datagrid('reload');
	    	        }
	        	});
    		}
    	});
    }
    
    function deleteTask(taskId) {
    	$.messager.confirm("删除任务", "确认删除该任务吗?", function(r){
    		if (r) {
    			$.ajax({
					url: "${ctx}/wz/task/delete.do?taskId=" + taskId, 
	        		success: function(){
	        			$("#taskList").datagrid('reload');
	    	        }
	        	});
    		}
    	});
    }
    
    function iconFormatter(value, row, index) {
    	return "<img width=\"50\" height=\"50\" src=\"${imgRoot}"+row.appIcon+"\" />";
    }
    function taskBelongFormatter(value, row, index){
    	if(value==1){
    		return "盈+";
    	}else if(value==2){
    		return "社交墙"
    	}
    }
    
    function taskPriceFormatter(value, row, index) {
    	var origPrice = parseFloat(row.taskOrigPrice);
    	var exchangeRate = parseInt($("#exchangeRate").val());
		var groupCodrimPercent = parseInt($("#groupCodrimPercent").val());
		var userCodrimPercent = parseInt($("#userCodrimPercent").val());
		
		var userPrice = ((origPrice*10)*(100-userCodrimPercent))/1000;
		var groupPrice = ((origPrice*10)*(100-groupCodrimPercent))/1000;
		
		return "普通: " + userPrice + "元 = " + userPrice*exchangeRate + "金币<br>" +
			"团队: " + groupPrice + "元 = " + groupPrice*exchangeRate + "金币";
    }
</script>

</head>

<body style="visibility: visible;">
	<input type="hidden" id="exchangeRate" value="${settingCurrency.exchangeRate}" />
	<input type="hidden" id="groupCodrimPercent" value="${settingCurrency.groupCodrimPercent}" />
	<input type="hidden" id="userCodrimPercent" value="${settingCurrency.userCodrimPercent}" />
	<table id="searchTb" cellpadding="5" >
		<tr>
			<td>
				开始时间：<input type="text" id="taskStartDate" class="easyui-datebox"/>
			</td>
			<td>
				结束时间：<input type="text" id="taskEndDate" class="easyui-datebox"/>
			</td>
			<td>
				产品名：<input type="text" id="taskIdFilter" class="easyui-combobox"/>
			</td>
			<td>
				状态：
				<select id="taskStatus" class="easyui-combobox">
					<option value="0">全部</option>
				    <option value="1">进行中</option>
				    <option value="2">暂停</option>
				    <option value="3">下线</option>
				</select>
			</td>
			<td>
				<div>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="reloadGrid()">搜索</a>
				</div>
			</td>
		</tr>
	</table>
	
	<table id="taskList" idField="taskId" rownumbers="true" pagination="true" singleSelect="true" pageSize=10  fitColumns="true">
		<thead>
			<tr>
				<th field="appIcon" align="center" width="auto" formatter="iconFormatter">产品ICON</th>
				<th field="taskName" align="center" width="auto">产品名</th>
				<th field="taskBelong" align="center" width="auto"   formatter="taskBelongFormatter">任务所属</th>
				<th field="weight" align="center" width="auto">显示权重</th>
				<th field="productWeight" align="center" width="auto">产品权重</th>
				<th field="taskOrigPrice" align="center" width="auto" formatter="rmbUnitFormatter">接入单价</th>
				<th field="taskPrice" align="center" width="180" formatter="taskPriceFormatter">投放单价</th>
				<th field="taskStartDate" align="center" width="auto">开始时间</th>
				<th field="taskEndDate" align="center" width="auto">结束时间</th>
				<th field="taskEffect" align="center" width="auto">效果</th>
				<th field="taskStatus" align="center" width="auto">状态</th>
				<th field="taskId" align="center" width="220" formatter="taskActionformater">操作</th>
			</tr>
		</thead>
	</table>

</body>
</html>