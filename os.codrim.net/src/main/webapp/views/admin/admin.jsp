<%@ page language="java" import="common.codrim.pojo.TbAdmin"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/common-taglib.jsp"%>
<html>
<head>
<jsp:include page="../common/common.jsp" />
<script type="text/javascript" src="${ctx}/res/js/admin.js"></script>
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
				<th field="id" width="80" align="center">编号</th>
				<th field="account" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">姓名</th>
				<th field="officeName" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">职位</th>
				<th field="email" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">邮箱</th>
				
			</tr>
		</thead>
	</table>

	<div id="tb">
		<!--   <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#bookview').edatagrid('addRow')">新增</a>  
	    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:$('#bookview').edatagrid('destroyRow')">删除</a>
	    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:$('#bookview').edatagrid('saveRow')">保存</a>  
	    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="javascript:$('#bookview').edatagrid('cancelRow')">取消</a> 
	    -->
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newAdmin()">新增</a>
	    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editAdmin()">编辑</a>
	    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeAdmin()">删除</a>
	    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="modifyPassword()"> 修改密码</a>
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons" modal="true">
		<div class="ftitle">添加信息</div>
		
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>姓名:</label> <input name="account" id="account"
					class="easyui-validatebox" validType="validName['account']" validType=length[2,20]>
			</div>
			 <div class="fitem">
				<label>登陆邮箱:</label> <input name="email" id="email"
					class="easyui-validatebox" required="true"
					validType="email" validType=length[2,20]>
			</div>
			<div class="fitem">
				<label>职位:</label><input name="officeName" id="officeName" validType=length[2,20]>
			</div>
			<div class="fitem">
				<label>登陆密码:</label> <input name="password" type="password"
					id="password" class="easyui-validatebox" required="true"
					validType="length[2,20]">
			</div>
			<div class="fitem">
				<label>确认密码:</label> <input name="password" id="password_2"
					type="password" class="easyui-validatebox" required="true"
					validType="equals['#password']" validType="length[2,20]">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="saveAdmin()">Save</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>
	
</body>
</html>