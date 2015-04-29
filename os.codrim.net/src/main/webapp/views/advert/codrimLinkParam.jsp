<%@ page language="java" import="common.codrim.pojo.TbAdmin"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/common-taglib.jsp"%>
<html>
<head>
<jsp:include page="../common/common.jsp" />
<script type="text/javascript" src="${ctx}/res/js/advert/codrimLinkParam.js"></script>
<style type="text/css">
body {
	margin: 0;
}
</style>

</head>
<body style="visibility: visible;">
	<table id="view" idField="id" rownumbers="true" 
		fitColumns="true" singleSelect="true" pageSize=10 toolbar="#tb">
		<thead>
			<tr>
				<th field="id" width="80" align="center">编号</th>
				<th field="paramZhName" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">中文名</th>
				<th field="paramEnName" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">英文名</th>
				<th field="paramType" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">值类型</th>
			</tr>
		</thead>
	</table>

	<div id="tb">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">新增</a>
	<!--  <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a> -->  
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons" modal="true">
		<div class="ftitle">添加参数</div>
		
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>参数中文名:</label> <input name="paramZhName" id="paramZhName"
					class="easyui-validatebox" validType="validName['zh']" required="true">
			</div>
			 <div class="fitem">
				<label>参数英文名:</label> <input name="paramEnName" id="paramEnName"
					class="easyui-validatebox" required="true"
					validType="validName['en']" >
			</div>
			<div class="fitem">
				<label>值类型:</label> <input type="radio" name="paramType" value="1" checked/>默认 <input type="radio" name="paramType" value="0"/>解析（例如：ip）
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="submit()">Save</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>
	
</body>
</html>