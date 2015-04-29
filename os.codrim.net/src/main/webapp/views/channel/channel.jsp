<%@ page language="java" import="common.codrim.pojo.TbAdmin"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/common-taglib.jsp"%>
<html>
<head>
<jsp:include page="../common/common.jsp" />
<script type="text/javascript" src="${ctx}/res/js/channel.js"></script>
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
				<!-- <th field="id" width="80" align="center">编号</th> -->
				<th field="channelName" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">渠道简称</th>
				<th field="companyName" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">公司全称</th>
				<th field="companyPhone" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">公司电话</th>
				<th field="companyCountry" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">公司所在国家</th>
				<th field="companyCity" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">公司所在城市</th>
				<th field="companyAddress" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">公司详细地址</th>
				<th field="personInCharge" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">渠道负责人</th>
				<th field="channelNumber" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">渠道号</th>
				<th field="channelType" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">渠道类型</th>
				<th field="channelContactName" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">联系人</th>
				<th field="channelContactPhone" align="center" width="150"
					editor="{type:'validatebox',options:{required:true}}">联系人电话</th>
				<th field="channelContactPosition" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">联系人职务</th>
				<th field="channelEmail" align="center" width="150"
					editor="{type:'validatebox',options:{required:true}}">联系人邮箱</th>
				<!-- <th field="channelContactEmail" align="center" width="150"
					editor="{type:'validatebox',options:{required:true}}">联系人邮箱</th> -->
				<th field="channelContactWx" align="center" width="150"
					editor="{type:'validatebox',options:{required:true}}">联系人微信/QQ</th>
				<th field="remark" align="center" width="150" editor="">备注</th>
			</tr>
		</thead>
	</table>

	<div id="tb">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="newChannel()">新增</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editChannel()">编辑</a> <a
			href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
			onclick="removeChannel()">删除</a> 
	</div>
	<div id="dlg" class="easyui-dialog"
		style="top: 100px; left: 200px; width: 620px; height: 560px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons" modal="true">
		<div class="ftitle">客户信息</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>渠道简称:</label> <input name="channelName" id="channelName"  
					class="easyui-validatebox" required="true" validType=length[1,20] >
			</div>
			<div class="fitem">
				<label>渠道类型:</label> <input name="channelType" id="channelType" class="easyui-combobox" required="required" style="width: 220px;">
			</div>
			<div class="fitem">
				<label>公司全称:</label> <input name="companyName" id="companyName"  
					class="easyui-validatebox" required="true" validType=length[1,30]><span id="checkResultSpan"></span>
			</div>
			<div class="fitem">
				<label>公司电话:</label> <input name="companyPhone" id="companyPhone"
					class="easyui-validatebox" required="true" onchange="requiredPair('companyPhone','channelContactPhone');">
			</div>
			<div class="fitem">
				<label>公司所在国家:</label> <input name="companyCountry" id="companyCountry" validType=length[1,20] value="中国">
			</div>
			<div class="fitem">
				<label>公司所在城市:</label> <input name="companyCity" id="companyCity" validType=length[1,20]>
			</div>
			<div class="fitem">
				<label>公司详细地址:</label> <input name="companyAddress" id="companyAddress" validType=length[1,200]>
			</div>
			<div class="fitem">
				<label>联系人:</label> <input name="channelContactName" id="channelContactName"
					class="easyui-validatebox" required="true" validType=length[1,20]>
			</div>
			<div class="fitem">
				<label>联系人电话:</label> <input name="channelContactPhone" id="channelContactPhone"
					class="easyui-validatebox" required="required" onchange="requiredPair('channelContactPhone','companyPhone');">
			</div>
			<div class="fitem">
				<label>联系人职务:</label> <input name="channelContactPosition" id="channelContactPosition"
					class="easyui-validatebox" validType=length[1,30]>
			</div>
			<!-- <div class="fitem">
				<label>联系人邮箱:</label> <input name="channelContactEmail" id="channelContactEmail"
					class="easyui-validatebox" validType="email" validType=length[5,30]>
			</div> -->
			<div class="fitem">
				<label>联系人微信/QQ:</label> <input name="channelContactWx" id="channelContactWx"
					class="easyui-validatebox" validType=length[1,30]>
			</div>
			<div class="fitem">
				<label>联系人邮箱:</label> <input name="channelEmail" id="channelEmail"
					class="easyui-validatebox" required="true" validType="email"
					validType=length[1,30]>
			</div>
			<div class="fitem" id="password">
				<label>密码:</label> <input name="channelPassword" 
					id="channelPassword" class="easyui-validatebox" required="true"
					validType=length[2,50]>
			</div>
			<div class="fitem" id="password_2">
				<label>确认密码:</label> <input name="channelPassword"
					id="channelPassword_2"  class="easyui-validatebox"
					required="true" validType="equals['#channelPassword']"
					validType=length[2,50]>
			</div>
			<div class="fitem">
				<label>备注:</label> <input name="remark" id="remark"
					class="easyui-validatebox" validType=length[0,300]>
			</div>
			<div class="fitem">
				<label>渠道负责人:</label> <%= account %>
			</div>
		</form>
	</div>
	<div id="dlg-buttons" style="text-align: center;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="saveChannel('')">保存</a> 
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
	
</body>
</html>