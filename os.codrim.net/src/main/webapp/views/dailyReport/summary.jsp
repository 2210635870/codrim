<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" import="common.codrim.pojo.TbAdmin"%>
<%@ include file="../common/common-taglib.jsp"%>

<html>
<head>
<jsp:include page="../common/common.jsp" flush="true" />

<script type="text/javascript" src="${ctx}/res/js/wz/datagrid-groupview.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/common.css">

<script type="text/javascript">

	$(function() {
		var curr_time = new Date();   
	    $("#createDateFromFilter").datebox("setValue",curr_time.format("yyyy-MM-dd"));
	    $("#createDateToFilter").datebox("setValue",curr_time.format("yyyy-MM-dd"));
		
		$('#dailyReportSummary').datagrid({
			url: '${ctx}/dailyReport/summary.do',
			queryParams: {
				createDateFrom: curr_time.format("yyyy-MM-dd"),
				createDateTo: curr_time.format("yyyy-MM-dd")
			},
			groupField: 'createDate',
			view: groupview,
			groupFormatter: dateFormatter
		});
		
		ajaxFillSelect("followUserFilter", "/loadFollowers.do", "0");
	});
	
	function getQueryParams(queryParams) {  
	    var followUser = $("#followUserFilter").combobox("getValue");
	    var createDateFrom = $("#createDateFromFilter").datebox("getValue");
	    var createDateTo = $("#createDateToFilter").datebox("getValue");
	 
	    queryParams.followUser = followUser;
	    queryParams.createDateFrom = createDateFrom;
	    queryParams.createDateTo = createDateTo;
	 
	    return queryParams;
	}
	
    function reloadGrid() {  
        var queryParams = $('#dailyReportSummary').datagrid('options').queryParams;  
        getQueryParams(queryParams);  
        $('#dailyReportSummary').datagrid('options').queryParams = queryParams;  
        $("#dailyReportSummary").datagrid('reload');  
    }

</script>

</head>

<body style="visibility: visible;">
	<table id="searchTb" cellpadding="5" >
		<tr>
			<td>
				开始时间：<input type="text" id="createDateFromFilter" class="easyui-datebox"/>
			</td>
			<td>
				结束时间：<input type="text" id="createDateToFilter" class="easyui-datebox"/>
			</td>
			<td>
				跟进人:
				<select id="followUserFilter" class="easyui-combobox"></select>
			</td>
			<td>
				<div>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="reloadGrid()">搜索</a>
				</div>
			</td>
		</tr>
	</table>
	
	<table id="dailyReportSummary" idField="id" singleSelect="true" rownumbers="true" showFooter="true" style="height:500px" >
		<thead>
			<tr>
				<!-- <th field="createDate" align="center" width="auto" formatter="dateFormatter" rowspan="2">日期</th> -->
				<th field="followUsername" align="center" width="auto" rowspan="2">跟进人</th>
				<th align="center" width="auto" rowspan="1" colspan="2">商务</th>
				<th align="center" width="auto" rowspan="1" colspan="3">渠道</th>
				<!-- <th align="center" width="auto" rowspan="1" colspan="2">运营</th> -->
				<!-- <th field="countElse" align="center" width="auto" rowspan="2">其他</th> -->
				<th field="countTotal" align="center" width="auto" rowspan="2">小计</th>
			</tr>
			<tr>
				<th field="count01" align="center" width="auto" rowspan="1" >新拓展</th>
				<th field="count02" align="center" width="auto" rowspan="1" >维护</th>
				<th field="count03" align="center" width="auto" rowspan="1" >新拓展</th>
				<th field="count04" align="center" width="auto" rowspan="1" >维护</th>
				<th field="count05" align="center" width="auto" rowspan="1" >投放</th>
				<!-- <th field="count06" align="center" width="auto" rowspan="1" >统计</th> -->
				<!-- <th field="count07" align="center" width="auto" rowspan="1" >分析</th> -->
			</tr>
		</thead>
	</table>
	
</body>
</html>