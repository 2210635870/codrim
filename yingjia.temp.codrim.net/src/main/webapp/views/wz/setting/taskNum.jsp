<%@ page language="java" import="common.codrim.pojo.TbAdmin"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<jsp:include page="../common/meta.jsp" flush="true" />
<style type="text/css">
body {
	margin: 0;
}
</style>
<script type="text/javascript">
$(function(){
	datagrid();
});
function datagrid(){
	$('#view').datagrid({
		url : '${ctx}/getNumLimits.do',
		loadFilter: function(data){
			$.each(data.rows,function(n,value){
			if (value.limitType==1){
				value.limitType='任务限制';
			} else if(value.limitType==2){
				value.limitType='积分限制';
			}else if(value.limitType==3){
				value.limitType='邀请限制';
			}else if(value.limitType==4){
				value.limitType='团队限制';
			}
			
			});
			return data;
		}
		});
}
var url;
function edit(){
	var row = $('#view').datagrid('getSelected');
	if (row){
		$('#dlg').dialog('open').dialog('setTitle','编辑');
		$('#fm').form('load', row);
		url='${ctx}/updateNumLimit.do?id='+row.id;
	}
}




function save(){
	$.ajax({
		            type: "post",
		            url: url,
		            dataType: "json",
		            data:{
		            	limitNum:$("#limitNum").numberbox('getValue'),
		            	remark:$("#remark").val()
		            	
		            },
		            success: function (data) {
			         if(data.success){
			        	 $('#dlg').dialog('close'); 
							$('#view').datagrid('reload');
			         }
			            },
		            error: function (msg) {
		alert("出错了！");
		            }
		});
	
	
	
}


</script>




</head>
<body style="visibility: visible;">
	<table id="view" idField="id"  class="easyui-datagrid" style="height:300px" fitColumns="true" singleSelect="true"  toolbar="#tb">
		<thead>
			<tr>
				<th field="id" width="80" align="center">编号</th>
			<th field="limitType" align="center" width="120" editor="{type:'validatebox',options:{required:true}}">限制类型</th>
				<th field="limitNum" align="center" width="120" editor="{type:'validatebox',options:{required:true}}">限制数</th>
					<th field="addName" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">修改人</th>
					<th field="remark" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">备注</th>
			</tr>
		</thead>
	</table>

	<div id="tb">
	    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()" id="edit">编辑</a>
	</div>
	
	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons" modal="true">
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>任务数:</label> <input name="limitNum" id="limitNum"
							class="easyui-numberbox easyui-validatebox" min="0" precision="0" missingMessage="请输入数字" >
			</div>
			<div class="fitem">
					<label>备注:</label>
					<textarea id="remark" name="remark" 
							rows="10" cols="40" style="overflow-x:hidden;margin-bottom: -129px;" 
							resize="none"  autofocus="autofocus" wrap="physical" align="left"  class="easyui-validatebox" validtype="length[0,200]" >
							</textarea>
							</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="save()">Save</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>
	
</body>
</html>