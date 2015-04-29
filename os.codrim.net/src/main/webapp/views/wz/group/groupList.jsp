<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp"%>

<html>
<head>
<jsp:include page="../common/meta.jsp" flush="true" />

<script type="text/javascript">

	$(function() {
		$('#groupList').datagrid({
			url: '${ctx}/wz/group/groupList.do'
		});
	});
	
	function getQueryParams(queryParams) {  
	    var groupId = $("#groupIdFilter").val();
	    var groupName = $("#groupNameFilter").val();
	 
	    queryParams.groupId = groupId;
	    queryParams.groupName = groupName;  
	 
	    return queryParams;  
	}
	
    function reloadGrid() {  
        var queryParams = $('#groupList').datagrid('options').queryParams;  
        getQueryParams(queryParams);  
        $('#groupList').datagrid('options').queryParams = queryParams;  
        $("#groupList").datagrid('reload');  
    }
    
    function actionformater(value, row, index) {
    	return  "<a href='${ctx}/wz/group/view.do?groupId="+row.groupId+"'>查看</a>";;
    }
    
</script>

</head>

<body style="visibility: visible;">
	<table id="searchTb" cellpadding="5" >
		<tr>
			<td>
				团队ID:
				<input id="groupIdFilter" class="easyui-validatebox" />
			</td>
			<td>
				团队名:
				<input id="groupNameFilter" class="easyui-validatebox" />
			</td>
			<td>
				<div>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="reloadGrid()">搜索</a>
				</div>
			</td>
		</tr>
	</table>
	
	<table id="groupList" idField="groupId" rownumbers="true" pagination="true" singleSelect="true" pageSize=10 >
		<thead>
			<tr>
				<th field="groupId" align="center" width="auto">团队ID</th>
				<th field="groupName" align="center" width="auto">团队名</th>
				<th field="leaderName" align="center" width="auto">团队LEADER</th>
				<th field="groupUserNo" align="center" width="auto">团队人数</th>
				<th field="groupEffect" align="center" width="auto">完成效果总数</th>
				<th field="groupAction" align="center" width="auto" formatter="actionformater">操作</th>
			</tr>
		</thead>
	</table>

</body>
</html>