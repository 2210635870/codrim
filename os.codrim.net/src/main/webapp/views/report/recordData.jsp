<%@ page language="java" import="common.codrim.pojo.TbAdmin"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/common-taglib.jsp"%>
<html>
<head>
<jsp:include page="../common/common.jsp" />
<script type="text/javascript" src="${ctx}/res/js/recordData.js"></script>
<style type="text/css">
body {
	margin: 0;
}
</style>

</head>

<body style="visibility: visible;">
<div>
		<form method="post" id="searchForm">
			<div style="padding-left: 150px">
				<table>
					<tr>
						<td>客户</td>
						<td><input name="customerName" id="customerName"
							class="easyui-validatebox" validType=length[2,20] /></td>
						<td>产品</td>
						<td><input name="productName" id="productName"
							class="easyui-validatebox" validType=length[2,20]  /></td>
						<td>渠道</td>
						<td><input name="channelName" id="channelName"
							class="easyui-validatebox" validType=length[2,20] /></td>
						<td>平台</td>
						<td><select id="deviceplate" name="deviceplate"  class="easyui-combobox">
								<option value="ios">ios</option>
								<option value="android">android</option>
						</select></td>
						<td>数据产生时间</td>
						<td><input id="startTime" name="startTime" /></td>
						<td>&nbsp;</td>
						<td><a class="easyui-linkbutton" onclick="submit();">获取信息</a></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	
	<div id="center_middle" style="text-align: center;">
	<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>接入单价：</label><input id="accessPrice"  name="accessPrice" readonly="readonly">
			</div>
			<div class="fitem">
				<label>投放单价：</label><input id="putPrice"  name="putPrice"  readonly="readonly">
			</div>
			<div class="fitem">
				<label>广告反馈效果数：</label><input   type="text" name="effectNum"  
				id="effectNum" class="easyui-numberbox"  required="true" min="0" precision="0" missingMessage="请输入整数数字" />
			</div>
			<div class="fitem">
				<label>渠道反馈点击数：</label><input   type="text" name="clickNum" 
				 id="clickNum" class="easyui-numberbox"  required="true" min="0" precision="0" missingMessage="请输入整数数字" />
			</div>
				<div class="fitem">
				<label>去重后:</label> <input name="effectNumWithDeduplication" id="effectNumWithDeduplication"  readonly="readonly">
			</div>
				<div class="fitem">
				<label>重复数据:</label> <input name="deduplicationNum" id="deduplicationNum"  class="easyui-numberbox"  required="true" min="0" precision="0" missingMessage="请输入整数数字" >
			</div>
				<div class="fitem">
				<label>重复率:</label> <input name="deduplicationRate" id="deduplicationRate" readonly="readonly" >
			</div>
				<div class="fitem">
				<label>核减数:</label> <input name="subtractNum"
					id="subtractNum" class="easyui-numberbox"  required="true" min="0" precision="0" missingMessage="请输入整数数字" validType="subtract['effectNumWithDeduplication']">
			</div>
				<div class="fitem">
				<label>渠道实际展示数:</label> <input name="channelExternalNum" id="channelExternalNum"  readonly="readonly">
			</div>
             <div class="fitem">
				<label>核减与否:</label>
				 <input name="issubtract" id="issubtract" type="radio" value="1" checked>确认核减
				 <input name="issubtract" id="issubtract" type="radio" value="0">取消核减
			</div>
		</form>
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="save()">Save</a> 
	</div>
	
	
	
	
<table id="view" idField="id" class="easyui-datagrid" rownumbers="true" pagination="true"
	 singleSelect="true" pageSize=30 toolbar="#tb">
		<thead>
			<tr>
				<th field="rankingsTime" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">日期</th>
				<th field="productName" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">产品名</th>
				<th field="channelName" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">渠道名</th>
				<th field="deviceplate" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">设备系统</th>
				<th field="accessPrice" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">接入单价</th>
				<th field="putPrice" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">投放单价</th>
				<th field="clickNum" align="center" width="150" editor="{type:'validatebox',options:{required:true}}">点击</th>
				<th field="effectNum" align="center" width="150" editor="{type:'validatebox',options:{required:true}}">效果</th>
				<th field="effectNumWithDeduplication" align="center" width="150" editor="{type:'validatebox',options:{required:true}}">去重后</th>
				<th field="deduplicationNum" align="center" width="150" editor="{type:'validatebox',options:{required:true}}">重复数据</th>
				<th field="deduplicationRate" align="center" width="150" editor="{type:'validatebox',options:{required:true}}">重复率</th>
				<th field="subtractNum" align="center" width="150" editor="{type:'validatebox',options:{required:true}}">核减数</th>
				<th field="channelExternalNum" align="center" width="150" editor="{type:'validatebox',options:{required:true}}">渠道实际展示数</th>
				<th field="issubtract" align="center" width="150" editor="{type:'validatebox',options:{required:true}}">是否已核减</th>
				<th field="conversinRate" align="center" width="150" editor="{type:'validatebox',options:{required:true}}">转化率</th>
				<th field="income" align="center" width="150" editor="{type:'validatebox',options:{required:true}}">收入</th>
				<th field="costs" align="center" width="150" editor="{type:'validatebox',options:{required:true}}">成本</th>
				<th field="grossProfit" align="center" width="150" editor="{type:'validatebox',options:{required:true}}">毛利</th>
				<th field="grossMargin" align="center" width="150" editor="{type:'validatebox',options:{required:true}}">毛利率</th>
			</tr>
		</thead>
	</table>
 <div id="tb">
	<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="modifyData()">修改数据</a>
	</div>



	

</body>
</html>