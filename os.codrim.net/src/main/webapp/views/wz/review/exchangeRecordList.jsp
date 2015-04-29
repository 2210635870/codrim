<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp"%>

<html>
<head>
<jsp:include page="../common/meta.jsp" flush="true" />

<script type="text/javascript">

	$(function() {
		$('#exchangeReviewList').datagrid({
			url: '${ctx}/wz/review/exchangeReviewList.do'
		});
	});
	
	function getQueryParams(queryParams) {  
	    var username = $("#usernameFilter").val();
	    var userPhone = $("#userPhoneFilter").val();
	    var exchangeType = $("#exchangeTypeFilter").combobox("getValue");
	    var reviewStatus = $("#reviewStatusFilter").combobox("getValue");
	 
	    queryParams.username = username;
	    queryParams.userPhone = userPhone;
	    queryParams.reviewStatus = reviewStatus;
	    queryParams.exchangeType = exchangeType;
	    queryParams.exchangeStartDate=$("#exchangeStartDate").datebox('getValue');
	    queryParams.exchangeEndDate=$("#exchangeEndDate").datebox('getValue');
	    queryParams.reviewStartDate=$("#reviewStartDate").datebox('getValue');
	    queryParams.reviewEndDate=$("#reviewEndDate").datebox('getValue');
	    
	    return queryParams;  
	}
	
    function reloadGrid() {  
        var queryParams = $('#exchangeReviewList').datagrid('options').queryParams;  
        getQueryParams(queryParams);  
        $('#exchangeReviewList').datagrid('options').queryParams = queryParams;  
        $("#exchangeReviewList").datagrid('reload');  
    }
    
    function actionformater(value, row, index) {
    	if (row.reviewStatus == "1") {
    		return "<a href='${ctx}/wz/review/initReviewExchange.do?recordId="+row.recordId+"'>审核</a>";
    	} else {
    		return "<a href='${ctx}/wz/review/viewReviewExchange.do?recordId="+row.recordId+"'>查看</a>";
    	}
    } 
    function myformatter(date) {
    	var y = date.getFullYear();
    	var m = date.getMonth() + 1;
    	var d = date.getDate();
    	return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
    }
    function myparser(s) {
    	if (!s)
    		return new Date();
    	var ss = (s.split('-'));
    	var y = parseInt(ss[0], 10);
    	var m = parseInt(ss[1], 10);
    	var d = parseInt(ss[2], 10);
    	if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
    		return new Date(y, m - 1, d);
    	} else {
    		return new Date();
    	}
    }
</script>

</head>

<body style="visibility: visible;">
	<table id="searchTb" cellpadding="5" >
		<tr>
			<td>
				用户名:
				<input id="usernameFilter" class="easyui-validatebox" />
			</td>
			<td>
				手机号:
				<input id="userPhoneFilter" class="easyui-validatebox" />
			</td>
			<td>
				兑换选项:
				<select id="exchangeTypeFilter" class="easyui-combobox">
					<option value="0">全部</option>
					<option value="1">支付宝</option>
				    <option value="2">话费</option>
				</select>
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
		<td>提现开始</td>
						<td><input id="exchangeStartDate" name="exchangeStartDate" class="easyui-datebox" editable="false" data-options="formatter:myformatter,parser:myparser"/></td>
		<td>提现结束</td>
						<td><input id="exchangeEndDate" name="exchangeEndDate" class="easyui-datebox" editable="false" data-options="formatter:myformatter,parser:myparser"/></td>
		<td>审核开始</td>
						<td><input id="reviewStartDate" name="reviewStartDate" class="easyui-datebox" editable="false" data-options="formatter:myformatter,parser:myparser"/></td>
		<td>审核结束</td>
			<td><input id="reviewEndDate" name="reviewEndDate" class="easyui-datebox" editable="false" data-options="formatter:myformatter,parser:myparser"/></td>
									
						
						
			<td>
				<div>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="reloadGrid()">搜索</a>
				</div>
			</td>
		</tr>
	</table>
	
	<table id="exchangeReviewList" idField="recordId" rownumbers="true" pagination="true" singleSelect="true" pageSize=10  fitColumns="true" >
		<thead>
			<tr>
			<th field="exchangeDate" align="center" width="auto">提现时间</th>
				<th field="username" align="center" width="auto">用户名</th>
				<th field="userPhone" align="center" width="auto">手机号</th>
				<th field="userEmail" align="center" width="150">邮箱</th>
				<th field="exchangeType" align="center" width="auto" formatter="exchangeTypeFormatter">兑换选项</th>
				<th field="exchangeMoney" align="center" width="auto">兑换金额</th>
				<th field="reviewStatus" align="center" width="auto" formatter="reviewStatusFormatter">状态</th>
					<th field="reviewDate" align="reviewDate" width="auto">审核时间</th>
				<th field="reviewAction" align="center" width="100" formatter="actionformater">操作</th>
			</tr>
		</thead>
	</table>

</body>
</html>