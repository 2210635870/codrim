<%@ page language="java" import="common.codrim.pojo.TbAdmin"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/common-taglib.jsp"%>
<html>
<head>
<jsp:include page="../common/common.jsp" />
<script type="text/javascript" src="${ctx}/res/js/customer.js">
</script>

</head>
<body style="visibility: visible;">
	<table id="view" idField="id" rownumbers="true" pagination="true" fitColumns="true" singleSelect="true" pageSize=10 toolbar="#tb">
		<thead>
			<tr>
				<!-- <th field="id" width="80" align="center">编号</th> -->
				<th field="customerName" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">客户简称</th>
				<th field="companyName" align="center" width="150"
					editor="{type:'validatebox',options:{required:true}}">公司全称</th>
				<th field="customerType" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">客户类型</th>
				<th field="personInCharge" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">客户负责人</th>
				<th field="contactName" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">联系人名字</th>
				<th field="contactPhone" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">联系人电话</th>
				<th field="contactPosition" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">联系人职务</th>
				<th field="contactEmail" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">联系人邮箱</th>
				<th field="contactWx" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">联系人微信/QQ</th>
				<th field="companyPhone" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">公司电话</th>
				<th field="website" align="center" width="150"
					editor="{type:'validatebox',options:{required:true}}">公司官网</th>
				<th field="companyCountry" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">公司所在国家</th>
				<th field="companyCity" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">公司所在城市</th>
				<th field="companyAddress" align="center" width="150"
					editor="{type:'validatebox',options:{required:true}}">公司详细地址</th>
				<th field="remark" align="center" width="150" editor="">备注</th>
			</tr>
		</thead>
	</table>

	<div id="tb">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新增</a> 
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="editUser()">编辑</a> 
		<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="userDetail()">详细</a> -->
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="removeUser()">删除</a>
	</div>
	<div id="dlg" class="easyui-dialog" style="top: 100px; left: 200px; width: 620px; height: 520px; padding: 10px 20px" closed="true" buttons="#dlg-buttons" modal="true">
		<div class="ftitle">客户信息</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>客户简称:</label> <input name="customerName" id="customerName"
					class="easyui-validatebox" required="true"
					validType=length[1,20]>
			</div>
			<div class="fitem">
				<label>客户类型:</label> <input name="customerType" id="customerType" class="easyui-combobox" required="required" style="width: 220px">
			</div>
			<div class="fitem">
				<label>公司全称:</label> <input name="companyName" id="companyName"
					class="easyui-validatebox" required="true" validType=length[1,30]><span id="checkResultSpan"></span>
			</div>
			<div class="fitem">
				<label>公司电话:</label> <input name="companyPhone" id="companyPhone"
					class="easyui-validatebox" required="true" onchange="requiredPair('companyPhone','contactPhone');">
			</div>
			<div class="fitem">
				<label>公司官网:</label> <input name="website" id="website"
					class="easyui-validatebox" validType=length[1,200]>
			</div>
			<div class="fitem">
				<label>公司所在国家:</label> <input name="companyCountry" id="companyCountry"
					class="easyui-validatebox" validType=length[1,20]>
			</div>
			<div class="fitem">
				<label>公司所在城市:</label> <input name="companyCity" id="companyCity"
					class="easyui-validatebox" validType=length[1,20]>
			</div>
			<div class="fitem">
				<label>公司详细地址:</label> <input name="companyAddress" id="companyAddress"
					class="easyui-validatebox" validType=length[1,200]>
			</div>
			<div class="fitem">
				<label>联系人:</label> <input name="contactName" id="contactName"
					class="easyui-validatebox" required="true" validType=length[1,20]>
			</div>
			<div class="fitem">
				<label>联系人电话:</label> <input name="contactPhone" id="contactPhone"
					class="easyui-validatebox" required="true" onchange="requiredPair('contactPhone','companyPhone');">
			</div>
			<div class="fitem">
				<label>联系人职务:</label> <input name="contactPosition" id="contactPosition"
					class="easyui-validatebox" validType=length[1,30]>
			</div>
			<div class="fitem">
				<label>联系人邮箱:</label> <input name="contactEmail" id="contactEmail"
					class="easyui-validatebox" validType=length[1,30] validType="email" >
			</div>
			<div class="fitem">
				<label>联系人微信/QQ:</label> <input name="contactWx" id="contactWx"
					class="easyui-validatebox" validType=length[1,30]>
			</div>
			<div class="fitem">
				<label>备注:</label> <input name="remark" id="remark"
					class="easyui-validatebox" maxlength="200">
			</div>
			<div class="fitem">
				<label>客户负责人:</label> <%= account %>
			</div>
		</form>
	</div>
	<div id="dlg-buttons" style="text-align: center;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="saveUser()">保存</a> 
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
	<!--dlgDetail start  -->
	<div id="dlgDetail" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		modal="true">
		<table id="detail_msg">
			<tr>
				<td>客户姓名:</td>
				<td><span id="customerName"></span></td>
			</tr>
			<tr>
				<td>联系人名字:</td>
				<td><span id="contactName"></td>
			</tr>
			<tr>
				<td>联系人电话:</td>
				<td><span id="contactPhone"></td>
			</tr>
			<tr>
				<td>公司电话:</td>
				<td><span id="companyPhone"></td>
			</tr>
			<tr>
				<td>公司官网:</td>
				<td><span id="website"></td>
			</tr>
		</table>

		<!-- 搜索框 -->
		<table>
			<tr>
				<td>产品名</td>
				<td>
					<select name="productName"></select>
				</td>
				<td>状态</td>
				<td><select id="runningStatus" name="runningStatus">
						<option value="-1">全部</option>
						<option value="1">运行中</option>
						<option value="2">暂停</option>
						<option value="3">下线</option>
				</select></td>
			</tr>
		</table>
		<!-- 搜索框  end-->
		<!--view_detail start  -->
		<table id="view_detail" rownumbers="true" pagination="true"
			fitColumns="true" singleSelect="true" pageSize=10 toolbar="#tb">
			<thead>
				<tr>
					<th field="id" width="80" align="center">编号</th>
					<th field="productName" align="center" width="80">产品名称</th>
					<th field="runningStatus" align="center" width="80">状态</th>
					<th field="options" align="center" width="80">操作</th>
				</tr>
			</thead>
		</table>
	</div>
	<!-- dlgDetail end -->
</body>
</html>