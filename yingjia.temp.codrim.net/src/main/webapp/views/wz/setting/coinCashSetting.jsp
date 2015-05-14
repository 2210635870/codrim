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
		url : '${ctx}/getCashSettings.do',
		loadFilter: function(data){
			$.each(data.rows,function(n,value){
			if (value.cashType==1){
				value.cashType='支付宝-10';
			} else if(value.cashType==2){
				value.cashType='支付宝-20';
			} else if(value.cashType==3){
				value.cashType='支付宝-50';
			} else if(value.cashType==4){
				value.cashType='话费-10';
			} else if(value.cashType==5){
				value.cashType='话费-20';
			} else if(value.cashType==6){
				value.cashType='话费-50';
			}
	
			});
			return data;
		}
		});
}

function addCoinCashSetting(){
	var data =$('#view').datagrid('getData');
	clearForm();
	if(data.rows.length>0){
		if(data.rows.length>=6){
			alert("所有类型已经添加完毕，不可再次添加，可以前往编辑！");
			return;
		}
		$.each(data.rows,function(n,value){
			if(value.cashType=='支付宝-10'){
				$("#cashType_1").attr("disabled","disabled");
			}
			if(value.cashType=='支付宝-20'){
				$("#cashType_2").attr("disabled","disabled");
			}
			if(value.cashType=='支付宝-50'){
				$("#cashType_3").attr("disabled","disabled");
			}
			if(value.cashType=='话费-10'){
				$("#cashType_4").attr("disabled","disabled");
			}
			if(value.cashType=='话费-20'){
				$("#cashType_5").attr("disabled","disabled");
			}		
			if(value.cashType=='话费-50'){
				$("#cashType_6").attr("disabled","disabled");
			}
		});
	}
	$('#dlg').dialog('open').dialog('setTitle','添加');
	url = '${ctx}/addCoinCashSetting.do';
}


function clearForm(){
	$('#fm').form('clear');
	$("input:radio:not([checked])").removeAttr("disabled");
}

function editCoinCashSetting(){
	var row = $('#view').datagrid('getSelected');
	if (row){
		clearForm();
		$('#dlg').dialog('open').dialog('setTitle','编辑');
		$('#fm').form('load', row);
		if(row.cashType=='支付宝-10'){
			$("input[name='cashType'][value=1]").attr("checked",	true);
		}else if(row.cashType=='支付宝-20'){
			$("input[name='cashType'][value=2]").attr("checked",	true);
		}else if(row.cashType=='支付宝-50'){
			$("input[name='cashType'][value=3]").attr("checked",	true);
		}else if(row.cashType=='话费-10'){
			$("input[name='cashType'][value=4]").attr("checked",	true);
		}else if(row.cashType=='话费-20'){
			$("input[name='cashType'][value=5]").attr("checked",	true);
		}else if(row.cashType=='话费-50'){
			$("input[name='cashType'][value=6]").attr("checked",	true);
		}
		$("input:radio:not([checked])").attr("disabled","disabled");
		url = '${ctx}/updateCoinCashSetting.do?id='+row.id;
	}else{
		alert("请选择！");
	}
}



function save(){
	$.ajax({
		            type: "post",
		            url: url,
		            dataType: "json",
		            data:{
		            	cashType:$('input[name="cashType"]:checked').val(),
		            	goldCoin:$("#goldCoin").numberbox('getValue')
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
	<table id="view" idField="id"  class="easyui-datagrid" fitColumns="true" singleSelect="true"  toolbar="#tb">
		<thead>
			<tr>
				<th field="id" width="80" align="center">编号</th>
				<th field="cashType" align="center" width="120" editor="{type:'validatebox',options:{required:true}}">方式</th>
				<th field="goldCoin" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">金币</th>
			</tr>
		</thead>
	</table>

	<div id="tb">
		 <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addCoinCashSetting()" id="add">新增</a>
	    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editCoinCashSetting()" id="edit">编辑</a>
	</div>
	
	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons" modal="true">
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>选择方式:</label>
				<input name="cashType"  type="radio"  id="cashType_1"  value="1"  style="width:auto" >支付宝-10
				<input name="cashType"  type="radio"  id="cashType_2"  value="2"   style="width:auto" >支付宝-20
					<input name="cashType"  type="radio"  id="cashType_3" value="3"  style="width:auto" >支付宝-50
				<input name="cashType"  type="radio"  id="cashType_4"  value="4"   style="width:auto" >话费-10
					<input name="cashType"  type="radio"  id="cashType_5"  value="5"  style="width:auto" >话费-20
				<input name="cashType"  type="radio"  id="cashType_6"  value="6"   style="width:auto" >话费-50
			</div>
		 <div class="fitem"  >
				<label>兑换金币数:</label><input name="goldCoin" id="goldCoin"
							class="easyui-numberbox easyui-validatebox" min="0" precision="0" missingMessage="请输入数字" /> 
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