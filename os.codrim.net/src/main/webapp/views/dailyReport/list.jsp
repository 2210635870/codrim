<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" import="common.codrim.pojo.TbAdmin"%>
<%@ include file="../common/common-taglib.jsp"%>

<html>
<head>
<jsp:include page="../common/common.jsp" flush="true" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/common.css">

<script type="text/javascript">

	$(function() {
		var curr_time = new Date();   
	    $("#createDateFromFilter").datebox("setValue",curr_time.format("yyyy-MM-dd"));
	    $("#createDateToFilter").datebox("setValue",curr_time.format("yyyy-MM-dd"));
		
		$('#dailyReportList').datagrid({
			url: '${ctx}/dailyReport/list.do',
			queryParams: {
				createDateFrom: curr_time.format("yyyy-MM-dd"),
				createDateTo: curr_time.format("yyyy-MM-dd")
			},
			toolbar: [
				{ 
		            text: '添加', 
		            iconCls: 'icon-add', 
		            handler: function() { 
		                add(); 
		            } 
		        }, '-',
				{ 
		            text: '编辑', 
		            iconCls: 'icon-edit', 
		            handler: function() { 
		                edit(); 
		            } 
		        }, '-',
				{ 
		            text: '删除', 
		            iconCls: 'icon-remove', 
		            handler: function() { 
		                deleteReport(); 
		            } 
		        }, '-',
				{ 
		            text: '回复', 
		            iconCls: 'icon-back', 
		            handler: function() { 
		                reply(); 
		            } 
		        }, '-'
			]
		});
		
		ajaxFillSelect("followUserFilter", "/loadFollowers.do", "0");
	});
	
	function getQueryParams(queryParams) {  
	    var followUser = $("#followUserFilter").combobox("getValue");
	    var jobCategory = $("#jobCategoryFilter").combobox("getValue");
	    var followType = $("#followTypeFilter").combobox("getValue");
	    var createDateFrom = $("#createDateFromFilter").datebox("getValue");
	    var createDateTo = $("#createDateToFilter").datebox("getValue");
	 
	    queryParams.followUser = followUser;
	    queryParams.jobCategory = jobCategory;
	    queryParams.followType = followType;
	    queryParams.createDateFrom = createDateFrom;
	    queryParams.createDateTo = createDateTo;
	 
	    return queryParams;
	}
	
    function reloadGrid() {  
        var queryParams = $('#dailyReportList').datagrid('options').queryParams;  
        getQueryParams(queryParams);  
        $('#dailyReportList').datagrid('options').queryParams = getQueryParams(queryParams);  
        $("#dailyReportList").datagrid('reload');  
    }
    
    function windowOptions(title, custId) {
    	var options = {
       	    title: title,
       	    left: "150px",
       	    top: "50px",
       	    width: 680,
       	    height: 735,
       	    cache: false,
       	    collapsible: false,
       	    minimizable: false,
       		maximizable: false,
       		draggable: false,
       		resizable: false,
       	    modal: true,
       	    onLoad: function(){
       	    	if ($("#jobCategorySelect").length <= 0) {
       	    		return;
       	    	}
       	    	
       	    	var ftOption1 = [{"value":"1", "text":"新拓展"},{"value":"2", "text":"投放"},{"value":"3", "text":"维护"}];
       	    	var ftOption2 = [{"value":"1", "text":"新拓展"},{"value":"3", "text":"维护"}];
       	    	
       	    	var category = $("#jobCategorySelect").combobox("getValue");
       	    	if (category == "1") { //渠道
   	    			$('#custSelect').combobox({required:true});
   	    			$('#custTr').show();
   	    			ajaxFillSelect("custSelect", "/loadChannels.do", custId);
   	    			
   	    			$("#followTypeSelect").combobox({
   	       	    		valueField: "value",
   	       	    		textField: "text",
   	       	    		data: ftOption1,
	   	       	    	onLoadSuccess : function() {
	   	       	    		var row = $("#dailyReportList").datagrid("getSelected");
		   	     			if (row) {
		   	     				$(this).combobox("setValue", row.followType);
		   	     			}
		   	     		}
   	       	    	});
   	    			
   	    		} else if (category == "2") { //商务
   	    			$('#custSelect').combobox({required:true});
   	    			$('#custTr').show();
   	    			ajaxFillSelect("custSelect", "/loadCustomers.do", custId);
   	    			
   	    			$("#followTypeSelect").combobox({
   	       	    		valueField: "value",
   	       	    		textField: "text",
   	       	    		data: ftOption2,
	   	       	    	onLoadSuccess : function() {
	   	       	    		var row = $("#dailyReportList").datagrid("getSelected");
		   	     			if (row) {
		   	     				$(this).combobox("setValue", row.followType);
		   	     			}
		   	     		}
   	       	    	});
   	    			
   	    		} else {
   	    			$('#custSelect').combobox({required:false});
   	    			$('#custTr').hide();
   	    		}
       	    	
       	    	$("#jobCategorySelect").combobox({
       	    		onSelect: function(cs){
           	    		var c = cs.value;
           	    		if (c == 1) { //渠道
           	    			$('#custSelect').combobox('reload', "${ctx}/loadChannels.do"); 
           	    			$('#custSelect').combobox({required:true});
           	    			$('#custTr').show();
           	    			
           	    			$("#followTypeSelect").combobox({
           	       	    		valueField: "value",
           	       	    		textField: "text",
           	       	    		data: ftOption1
           	       	    	});
           	    			
           	    		} else if (c == 2) { //商务
           	    			$('#custSelect').combobox('reload', "${ctx}/loadCustomers.do"); 
           	    			$('#custSelect').combobox({required:true});
           	    			$('#custTr').show();
           	    			
           	    			$("#followTypeSelect").combobox({
           	       	    		valueField: "value",
           	       	    		textField: "text",
           	       	    		data: ftOption2
           	       	    	});
           	    			
           	    		} else {
           	    			$('#custSelect').combobox('clear');
           	    			$('#custSelect').combobox({required:false});
           	    			$('#custTr').hide();
           	    		}
           	    	}
       	    	});
       	    	
       	    	$("#custSelect").combobox({
       	    		onSelect: function(cs){
       	    			var c = cs.key;
       	    			if (c) {
       	    				$.ajax({
       	    					url: "${ctx}/dailyReport/loadContactInfo.do",
       	    					data: {"customerId": c, "jobCategory": $("#jobCategorySelect").combobox("getValue")},
       	    					cache: false,
       	    	        		success: function(result){
       	    	        			if (result.success) {
       	    	        				$("#contact_name").text(result.data.contactName);
       	    	        				$("#contact_position").text(result.data.contactPosition);
       	    	        				$("#contact_phone").text(result.data.contactPhone);
       	    	        				$("#contact_email").text(result.data.contactEmail);
       	    	        				$("#contact_wx").text(result.data.contactWx);
       	    	        			} else {
       	    	        				$.messager.alert("提示", result.msg);
       	    	        			}
       	    	    	        }
       	    	        	});
       	    			}
       	    		}
       	    	});
       	    }
       	}
    	
    	return options
    }
    
    function add() {
    	$('#drFormWindow').window(windowOptions('添加日报'));
    	$('#drFormWindow').window('refresh', '${ctx}/dailyReport/initAdd.do'); 
    }
    
    function edit() {
    	var row = $("#dailyReportList").datagrid("getSelected");
    	if (row) {
    		$('#drFormWindow').window(windowOptions('编辑日报', row.customerId));
        	$('#drFormWindow').window('refresh', '${ctx}/dailyReport/initEdit.do?id=' + row.id); 
    	}
    }
    
    function reply() {
    	var row = $("#dailyReportList").datagrid("getSelected");
    	if (row) {
    		$('#drFormWindow').window(windowOptions('回复日报', row.customerId));
        	$('#drFormWindow').window('refresh', '${ctx}/dailyReport/initReply.do?id=' + row.id); 
    	}
    }
    
    function saveReport() {
    	$('#dailyReportForm').form("submit", {    
		    url: '${ctx}/dailyReport/save.do',    
		    onSubmit: function(){
				return $(this).form('validate');  
		    },
		    success:function(data){    
		    	var result = eval('(' + data + ')');
				if (result.success) {
					$('#drFormWindow').window('close');
					reloadGrid();
					
				} else {
					$.messager.alert('保存失败', result.msg, 'error');
				}
		    }    
		});
    }
    
    function deleteReport() {
    	var row = $("#dailyReportList").datagrid("getSelected");
    	if (row) {
    		$.messager.confirm("删除日报", "你确定要删除该日报吗?", function(r){
        		if (r) {
        			$.ajax({
    					url: "${ctx}/dailyReport/delete.do",
    					data: {"id": row.id, "followUser": row.followUser},
    					type: "POST",
    	        		success: function(result){
    	        			if (result.success) {
    	        				reloadGrid();
    	        			} else {
    	        				$.messager.alert("提示", result.msg);
    	        			}
    	    	        }
    	        	});
        		}
        	});
    	}
    }
    
    function addReply() {
    	var row = $("#dailyReportList").datagrid("getSelected");
    	if (row) {
    		$('#replyForm').form("submit", {    
    		    url: '${ctx}/dailyReport/addReply.do?reportId=' + row.id,    
    		    onSubmit: function(){
    				return $(this).form('validate');  
    		    },
    		    success:function(data){
    		    	var r = eval('(' + data + ')');
    				if (r.success) {
    					var msg = r.data.replyMsg.replace(/\n/g, "<br />");
    					
    					$('<li id="reply_'+r.data.id+'"><div class="comment"><div class="commentInfo"><span class="username">'
    							+r.data.username+'</span><span class="time">'
    							+r.data.replyTime+'</span></div><div class="commentMsg">'
    							+msg+'</div><a href="javascript:void(0)" class="operation" onclick="deleteReply(\''+r.data.id+'\')">删除</a></div></li>').hide().appendTo('.comments ul').slideDown();
    					
    					$("#replyMsgInput").val("");
    					
    				} else {
    					$.messager.alert('添加回复失败', r.msg, 'error');
    				}
    		    }    
    		});
    	}
    }
    
    function deleteReply(id) {
    	$.messager.confirm("删除回复", "你确定要删除该回复吗?", function(r){
    		if (r) {
    			$.ajax({
					url: "${ctx}/dailyReport/deleteReply.do",
					data: {"id": id},
					type: "POST",
	        		success: function(result){
	        			if (result.success) {
	        				$("#reply_" + id).fadeOut();
	        			} else {
	        				$.messager.alert("提示", result.msg);
	        			}
	    	        }
	        	});
    		}
    	});
    }
    
    function jobCategoryFormatter(value, row, index) {
    	if (value == "1") {
    		return "渠道";
    	} else if (value == "2") {
    		return "商务";
    	} else if (value == "3") {
    		return "运营";
    	} else {
    		return "其他";
    	}
    }
    
    function followTypeFormatter(value, row, index) {
    	if (value == "1") {
    		return "新拓展";
    	} else if (value == "2") {
    		return "投放";
    	} else if (value == "3") {
    		return "维护";
    	} else if (value == "4") {
    		return "统计";
    	} else if (value == "5") {
    		return "分析";
    	} else {
    		return "其他";
    	}
    }
    
    function customerNameFormatter(value, row, index) {
    	if (row.jobCategory == "1") {
    		return row.channelName;
    	} else {
    		return value;
    	}
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
				所属范畴:
				<select id="jobCategoryFilter" class="easyui-combobox">
					<option value="0">全部</option>
					<option value="1">渠道</option>
					<option value="2">商务</option>
					<!-- 
					<option value="3">运营</option>
					<option value="9">其他</option> 
					-->
				</select>
			</td>
			<td>
				跟进性质:
				<select id="followTypeFilter" class="easyui-combobox">
					<option value="0">全部</option>
					<option value="1">新拓展</option>
					<option value="2">投放</option>
					<option value="3">维护</option>
					<!-- 
					<option value="4">统计</option>
					<option value="5">分析</option>
					<option value="9">其他</option> 
					-->
				</select>
			</td>
			<td>
				<div>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="reloadGrid()">搜索</a>
				</div>
			</td>
		</tr>
	</table>
	
	<table id="dailyReportList" idField="id" rownumbers="true" pagination="true" singleSelect="true" pageSize=10 >
		<thead>
			<tr>
				<th field="createDate" align="center" width="auto" formatter="dateFormatter" >日期</th>
				<th field="followUsername" align="center" width="auto">跟进人</th>
				<th field="jobCategory" align="center" width="80" formatter="jobCategoryFormatter">所属范畴</th>
				<th field="followType" align="center" width="auto" formatter="followTypeFormatter">跟进性质</th>
				<th field="customerName" align="center" width="auto" formatter="customerNameFormatter">客户名称</th>
				<th field="followDetail" align="center" width="150">跟进情况</th>
				<th field="followProblem" align="center" width="150">遇到的问题</th>
				<th field="support" align="center" width="150">需要的支持</th>
				<th field="suggestion" align="center" width="150">建议和想法</th>
				<th field="latestReplyStatus" align="center" width="150">最新回复状态</th>
			</tr>
		</thead>
	</table>
	
	<div id="drFormWindow"></div>

</body>
</html>