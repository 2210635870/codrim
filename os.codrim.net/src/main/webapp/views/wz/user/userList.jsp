<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp"%>

<html>
<head>
<jsp:include page="../common/meta.jsp" flush="true" />

<script type="text/javascript">

	$(function() {
		$('#userList').datagrid({
			url: '${ctx}/wz/user/userList.do'
		});
	});
	
	function getQueryParams(queryParams) {  
	    var userId = $("#userIdFilter").val();
	    var username = $("#usernameFilter").val();
	    var phoneNumber = $("#phoneNumberFilter").val();
	 
	    queryParams.userId = userId;
	    queryParams.username = username;
	    queryParams.phoneNumber = phoneNumber;
	 
	    return queryParams;  
	}
	
    function reloadGrid() {  
        var queryParams = $('#userList').datagrid('options').queryParams;  
        getQueryParams(queryParams);  
        $('#userList').datagrid('options').queryParams = queryParams;  
        $("#userList").datagrid('reload');  
    }
    
    function actionformater(value, row, index) {
    	return '<a href="${ctx}/wz/user/view.do?userId='+row.userId+'"   style="margin-right:10px;">查看</a>'+
    			'<a href="javascript:changeDisable(\''+row.userId+'\',\''+row.isDisable+'\')">禁用</a>';
    }
    
    function changeDisable(userId,isDisable){
		$.ajax({
			            type: "post",
			            url:  '${ctx}/wz/user/changeDisable.do',
			            dataType: "json",
			          data:{
			        	  userId:userId,
			        	  isDisable:isDisable
			          },
			            success: function (data) {
				         if(data.success){
				        		$('#userList').datagrid('reload');	
				         }else{
				        	 alert("修改失败");
				         }
				            },
			            error: function (msg) {
			alert("出错了！");
			            }
			});
    }
    function isDisableFormatter(value, row, index) {
    	if (value == "1") {
    		return "禁用";
    	} else {
    		return "活跃";
    	}
    }
    
    function userSourceFormatter(value, row, index) {
    	if (value == "1") {
    		return "盈+";
    	} else if (value == "2") {
    		return "社交墙";
    	}
    }
</script>

</head>

<body style="visibility: visible;">
	<table id="searchTb" cellpadding="5" >
		<tr>
			<td>
				用户ID:
				<input id="userIdFilter" class="easyui-validatebox" />
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
				<div>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="reloadGrid()">搜索</a>
				</div>
			</td>
		</tr>
	</table>
	
	<table id="userList" idField="userId" rownumbers="true" pagination="true" singleSelect="true" pageSize=10 fitColumns="true" >
		<thead>
			<tr>
				<th field="userId" align="center" width="auto">用户ID</th>
				<th field="username" align="center" width="auto">用户名</th>
				<th field="phoneNumber" align="center" width="auto">手机号</th>
				<th field="email" align="center" width="150">邮箱</th>
				<th field="userEffect" align="center" width="auto">完成效果数量</th>
				<th field="stepEffect" align="center" width="auto">完成任务数量</th>
				<th field="balance" align="center" width="auto">余额</th>
				<th field="isDisable" align="center" width="auto"  formatter="isDisableFormatter">是否禁用</th>
				<th field="userSource" align="center" width="auto" formatter="userSourceFormatter">来源</th>
				<th field="userAction" align="center" width="100" formatter="actionformater">操作</th>
			</tr>
		</thead>
	</table>

</body>
</html>