<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" import="common.codrim.pojo.TbAdmin"%>
<%@ include file="../common/common-taglib.jsp"%>

<html>
<head>
<jsp:include page="../common/common.jsp" flush="true" />

<script type="text/javascript">

	$(function() {
		$('#customerTypeList').datagrid({
			url: '${ctx}/customerTypeList.do',
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
			]
		});
	});
	
    function add() {
    	$('#dlg').dialog('open').dialog('setTitle', '添加客户类型');
    	$('#fm').form('clear');
    }
    
    function edit() {
    	var row = $("#customerTypeList").datagrid("getSelected");
    	if (row) {
    		$('#dlg').dialog('open').dialog('setTitle', '编辑客户类型');
    		$("#fm").form("load", row);
    		$('#typeId').val(row.id);
    	}
    }

    function save() {
    	$('#fm').form("submit", {    
		    url: '${ctx}/saveCustomerType.do',    
		    onSubmit: function(){
				return $(this).form('validate');  
		    },
		    success:function(data){    
		    	var result = eval('(' + data + ')');
				if (result.success) {
					$('#dlg').dialog('close');
					$("#customerTypeList").datagrid('reload');  
					
				} else {
					$.messager.alert('保存失败', result.msg, 'error');
				}
		    }    
		});
    }
    
    function deleteReport() {
    	var row = $("#customerTypeList").datagrid("getSelected");
    	if (row) {
    		$.messager.confirm("删除客户类型", "你确定要删除该客户类型吗?", function(r){
        		if (r) {
        			$.ajax({
    					url: "${ctx}/deleteCustomerType.do",
    					data: {"id": row.id},
    					type: "POST",
    	        		success: function(result){
    	        			if (result.success) {
    	        				$("#customerTypeList").datagrid('reload');  
    	        			} else {
    	        				$.messager.alert("提示", result.msg);
    	        			}
    	    	        }
    	        	});
        		}
        	});
    	}
    }
    
</script>

</head>

<body style="visibility: visible;">
	
	<table id="customerTypeList" idField="id" rownumbers="true" pagination="true" singleSelect="true" pageSize=10 >
		<thead>
			<tr>
				<!-- <th field="id" align="center" width="auto" >编号</th> -->
				<th field="customerType" align="center" width="auto" >类型名</th>
				<th field="createDate" align="center" width="auto" formatter="dateFormatter" >添加时间</th>
				<th field="remark" align="center" width="260" >备注</th>
			</tr>
		</thead>
	</table>
	
	<div id="dlg" class="easyui-dialog" style="width: 400px; height: 280px; padding: 10px 20px" closed="true" buttons="#dlg-buttons" modal="true">
		<div class="ftitle">客户类型信息</div>
		<form id="fm" method="post" novalidate>
			<input type="hidden" name="id" id="typeId" />
			<div class="fitem">
				<label>客户类型名称:</label> <input name="customerType" id="customerType" class="easyui-validatebox" required="true" validType=length[1,20]>
			</div>
			<div class="fitem">
				<label>备注:</label> <input name="remark" id="remark" class="easyui-validatebox" validType=length[0,200]>
			</div>
		</form>
	</div>
	<div id="dlg-buttons" style="text-align: center;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="save()">保存</a> 
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
	
</body>
</html>