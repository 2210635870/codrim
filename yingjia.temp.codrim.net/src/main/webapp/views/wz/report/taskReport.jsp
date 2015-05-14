<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp"%>

<html>
<head>
<jsp:include page="../common/meta.jsp" flush="true" />

<script type="text/javascript" src="${ctx}/res/js/wz/datagrid-groupview.js"></script>
<script type="text/javascript" src="${ctx}/res/js/wz/highcharts4/highcharts.js"></script>
<%-- <script type="text/javascript" src="${ctx}/res/js/wz/highcharts4/modules/exporting.js"></script> --%>

<script type="text/javascript">

	$(function () {
		ajaxFillSelect("taskIdFilter", "/loadTaskNames.do", "0");
		
		var curr_time = new Date();   
	    $("#dateFromFilter").datebox("setValue",curr_time.format("yyyy-MM-dd"));
	    $("#dateToFilter").datebox("setValue",curr_time.format("yyyy-MM-dd"));
		
		$('#taskReportList').datagrid({
			url: '${ctx}/wz/task/taskReportList.do',
			queryParams: {
				dateFrom: curr_time.format("yyyy-MM-dd"),
				dateTo: curr_time.format("yyyy-MM-dd")
			},
			groupField: 'finishDate',
			view: groupview,
			groupFormatter: dateFormatter,
			loadFilter: function(data){
				$.each(data.rows,function(n,value){
						var profitRate=(value.profitRate*100).toFixed(2)+"%";
						value.profitRate=profitRate;
				});
			return data;
			}
		});
		
	});
	
	function getQueryParams(queryParams) {  
	    var dateFrom = $("#dateFromFilter").datebox("getValue");
	    var dateTo = $("#dateToFilter").datebox("getValue");
	    var taskId = $("#taskIdFilter").combobox("getValue");
	 
	    queryParams.dateFrom = dateFrom;
	    queryParams.dateTo = dateTo;
	    queryParams.taskId = taskId;
	 
	    return queryParams;  
	}
	
    function reloadGrid() {  
        var queryParams = $('#taskReportList').datagrid('options').queryParams;  
        getQueryParams(queryParams);  
        $('#taskReportList').datagrid('options').queryParams = queryParams;  
        $("#taskReportList").datagrid('reload');
    }
    
    function reportActionFormatter(value, row, index) {
    	if (row.taskId == "0" || row.taskId == null)
    		return "";
    	
    	var date = new Date(parseInt(row.finishDate));
    	//return  "<a href='${ctx}/wz/task/viewReportDetail.do?taskId="+row.taskId+"&finishDate="+date.format("yyyy-MM-dd")+"'>任务明细</a>";
    	return  "<a href='javascript:void(0)' onclick='openDetailDialog(\""+row.taskId+"\",\""+date.format("yyyy-MM-dd")+"\")'>任务明细</a>";
    }
    
    function openDetailDialog(taskId, finishDate) {
    	$('#detailWindow').window({
    	    title: '任务明细',
    	    left: "150px", 
    	    top: "150px",
    	    width: 650,   
    	    height: 450,
    	    cache: false,
    	    collapsible: false,
    	    minimizable: false,
    		maximizable: false,
    		draggable: false,
    		resizable: false,
    	    modal: true
    	});
    	
    	$('#detailWindow').window('refresh', '${ctx}/wz/task/viewReportDetail.do?taskId='+taskId+'&finishDate='+finishDate); 
    }

</script>

<style>
/* table {
    *border-collapse: collapse; /* IE7 and lower */
    border-spacing: 0;
    width: 100%;    
} */

.bordered {
    border: solid #ccc 1px;
    -moz-border-radius: 6px;
    -webkit-border-radius: 6px;
    border-radius: 6px;
    -webkit-box-shadow: 0 1px 1px #ccc; 
    -moz-box-shadow: 0 1px 1px #ccc; 
    box-shadow: 0 1px 1px #ccc;         
}

.bordered tr:hover {
    background: #fbf8e9;
    -o-transition: all 0.1s ease-in-out;
    -webkit-transition: all 0.1s ease-in-out;
    -moz-transition: all 0.1s ease-in-out;
    -ms-transition: all 0.1s ease-in-out;
    transition: all 0.1s ease-in-out;     
}    
    
.bordered td, .bordered th {
    border-left: 1px solid #ccc;
    border-top: 1px solid #ccc;
    padding: 10px;
    text-align: left;    
}

.bordered th {
    background-color: #dce9f9;
    background-image: -webkit-gradient(linear, left top, left bottom, from(#ebf3fc), to(#dce9f9));
    background-image: -webkit-linear-gradient(top, #ebf3fc, #dce9f9);
    background-image:    -moz-linear-gradient(top, #ebf3fc, #dce9f9);
    background-image:     -ms-linear-gradient(top, #ebf3fc, #dce9f9);
    background-image:      -o-linear-gradient(top, #ebf3fc, #dce9f9);
    background-image:         linear-gradient(top, #ebf3fc, #dce9f9);
    -webkit-box-shadow: 0 1px 0 rgba(255,255,255,.8) inset; 
    -moz-box-shadow:0 1px 0 rgba(255,255,255,.8) inset;  
    box-shadow: 0 1px 0 rgba(255,255,255,.8) inset;        
    border-top: none;
    text-shadow: 0 1px 0 rgba(255,255,255,.5); 
}

.bordered td:first-child, .bordered th:first-child {
    border-left: none;
}

.bordered th:first-child {
    -moz-border-radius: 6px 0 0 0;
    -webkit-border-radius: 6px 0 0 0;
    border-radius: 6px 0 0 0;
}

.bordered th:last-child {
    -moz-border-radius: 0 6px 0 0;
    -webkit-border-radius: 0 6px 0 0;
    border-radius: 0 6px 0 0;
}

.bordered th:only-child{
    -moz-border-radius: 6px 6px 0 0;
    -webkit-border-radius: 6px 6px 0 0;
    border-radius: 6px 6px 0 0;
}

.bordered tr:last-child td:first-child {
    -moz-border-radius: 0 0 0 6px;
    -webkit-border-radius: 0 0 0 6px;
    border-radius: 0 0 0 6px;
}

.bordered tr:last-child td:last-child {
    -moz-border-radius: 0 0 6px 0;
    -webkit-border-radius: 0 0 6px 0;
    border-radius: 0 0 6px 0;
}	
</style>

</head>

<body style="visibility: visible;">

<div class="easyui-panel" title="报表" style="width:1200px">
	<div style="padding:10px 10px 10px 10px">
	
	<table id="searchTb" cellpadding="5" >
		<tr>
			<td>
				开始时间：<input type="text" id="dateFromFilter" class="easyui-datebox"/>
			</td>
			<td>
				结束时间：<input type="text" id="dateToFilter" class="easyui-datebox"/>
			</td>
			<td>
				产品名：<input type="text" id="taskIdFilter" class="easyui-combobox"/>
			</td>
			<td>
				<div>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="reloadGrid()">查询</a>
				</div>
			</td>
		</tr>
	</table>
	
	<!-- rownumbers="true" pagination="true" singleSelect="true" pageSize=20 -->
	<table id="taskReportList" idField="taskId" singleSelect="true" rownumbers="true" showFooter="true" style="height:500px" >
		<thead>
			<tr>
				<!-- <th field="finishDate" align="center" width="auto" formatter="dateFormatter">完成日期</th> -->
				<th field="taskName" align="center" width="auto">产品名</th>
				<th field="taskOrigPrice" align="center" width="auto">接入单价</th>
				<th field="effect" align="center" width="auto">效果</th>
				<th field="cost" align="center" width="auto">成本</th>
				<th field="income" align="center" width="auto">收入</th>
				<th field="profit" align="center" width="auto">毛利</th>
				<th field="profitRate" align="center" width="auto">毛利率</th>
				<th field="reportAction" align="center" width="auto" formatter="reportActionFormatter">操作</th>
			</tr>
		</thead>
	</table>
	</div>
	
	<!-- <br>
	<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
	<br> -->
	
	<div id="detailWindow"></div>
</div>
	
</body>
</html>