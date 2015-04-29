<%@ page language="java" import="common.codrim.pojo.TbAdmin"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/common-taglib.jsp"%>
<html>
<head>
<jsp:include page="../common/common.jsp" />
<script type="text/javascript" src="${ctx}/res/js/channelType.js"></script>
<style type="text/css">
body {
	margin: 0;
}
</style>
</head>
<body style="visibility: visible;">
	<table id="view" idField="id" rownumbers="true" pagination="true"
		fitColumns="true" singleSelect="true" pageSize=10 toolbar="#tb">
		<thead>
			<tr>
				<th field="id" width="auto" align="center">编号</th>
				<th field="channelType" align="center" width="auto"
					editor="{type:'validatebox',options:{required:true}}">类型名</th>
				<th field="date" align="center" width="auto"
					editor="{type:'validatebox',options:{required:true}}">添加时间</th>
				<th field="remark" align="center" width="auto" editor="">备注</th>
			</tr>
		</thead>
	</table>

	<div id="tb">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="newChannelType()">新增</a> 
			<a href="#" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editChannelType()">编辑</a> 
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
			onclick="removeChannelType()">删除</a> 
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons" modal="true">
		<div class="ftitle">渠道类型信息</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>类型名字:</label> <input name="channelType" id="channelType"
					class="easyui-validatebox" required="true"
					validType=length[2,20]>
			</div>
			<div class="fitem">
				<label>备注:</label> <input name="remark" id="remark"
					class="easyui-validatebox" validType=length[0,200]>
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="saveChannelType()">Save</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>
	
</body>
</html>