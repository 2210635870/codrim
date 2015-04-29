<%@ page language="java" import="common.codrim.pojo.TbAdmin"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/common-taglib.jsp"%>
<html>
<head>
<jsp:include page="../common/common.jsp" />
<script type="text/javascript" src="${ctx}/res/js/rankingsReport.js"></script>
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
						<td>开始时间</td>
						<td><input id="startTime" name="startTime" class="easyui-datebox" editable="false" data-options="formatter:myformatter,parser:myparser"/></td>
						<td>结束时间</td>
						<td><input id="endTime" name="endTime" class="easyui-datebox" editable="false" data-options="formatter:myformatter,parser:myparser"/></td>
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
						<td>&nbsp;</td>
						<td><a class="easyui-linkbutton" onclick="submit();">搜索</a></td>
					</tr>
				</table>
			</div>
		</form>
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
				
				<th field="activate" align="center" width="150" editor="{type:'validatebox',options:{required:true}}">激活</th>
				<th field="register" align="center" width="150" editor="{type:'validatebox',options:{required:true}}">注册</th>
				<th field="consumeOne" align="center" width="150" editor="{type:'validatebox',options:{required:true}}">首次消费</th>
				<th field="consumeMore" align="center" width="150" editor="{type:'validatebox',options:{required:true}}">2次及以上消费</th>		
				<th field="recharge" align="center" width="150" editor="{type:'validatebox',options:{required:true}}">充值</th>
				<th field="credit" align="center" width="150" editor="{type:'validatebox',options:{required:true}}">绑卡</th>
				<th field="baddept" align="center" width="150" editor="{type:'validatebox',options:{required:true}}">坏账</th>
				<th field="conversinRate" align="center" width="150" editor="{type:'validatebox',options:{required:true}}">转化率</th>
				<th field="income" align="center" width="150" editor="{type:'validatebox',options:{required:true}}">收入</th>
				<th field="costs" align="center" width="150" editor="{type:'validatebox',options:{required:true}}">成本</th>
				<th field="grossProfit" align="center" width="150" editor="{type:'validatebox',options:{required:true}}">毛利</th>
				<th field="grossMargin" align="center" width="150" editor="{type:'validatebox',options:{required:true}}">毛利率</th>
			</tr>
		</thead>
	</table>
  <div id="tb">
	<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="getDetailLink()">详细</a> 
	<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toSubtract()">确认核减信息</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toRecord()">录入数据</a>
	</div>
<div id="dlg" class="easyui-dialog"
		style="width: 500px; height: 500px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons" modal="true">
		<div class="ftitle">核减信息</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem" >
				<label>时间:</label> <input name="rankingsTime" id="rankingsTime"  readonly="readonly">
			</div>
			<div class="fitem">
				<label>产品名:</label> <input name="productName" id="productName" readonly="readonly" >
			</div>
				<div class="fitem" id="appId_ios">
				<label>渠道名:</label> <input name="channelName" id="channelName"  readonly="readonly">
			</div>
			<div class="fitem">
				<label>效果数:</label> <input name="effectNum"  readonly="readonly" id="effectNum" >
			</div>
				<div class="fitem">
				<label>去重后:</label> <input name="effectNumWithDeduplication" id="effectNumWithDeduplication"  readonly="readonly">
			</div>
				<div class="fitem">
				<label>重复数据:</label> <input name="deduplicationNum" id="deduplicationNum"  readonly="readonly">
			</div>
				<div class="fitem">
				<label>重复率:</label> <input name="deduplicationRate" id="deduplicationRate"  readonly="readonly">
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
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="saveSubtract()">Save</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>
<div id="dlg_record" class="easyui-dialog"
		style="width: 500px; height: 500px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons_record" modal="true">
		<div class="ftitle">录入信息</div>
<form id="fm_record" method="post" novalidate>
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
		<!--	<div class="fitem">
				<label>去重后:</label> <input name="effectNumWithDeduplication" id="effectNumWithDeduplication"  class="easyui-numberbox"  required="true" min="0" precision="0" missingMessage="请输入整数数字">
			</div>
				<div class="fitem">
				<label>重复数据:</label> <input name="deduplicationNum" id="deduplicationNum"  class="easyui-numberbox"  required="true" min="0" precision="0" missingMessage="请输入整数数字" >
			</div>
				<div class="fitem">
				<label>重复率:</label> <input name="deduplicationRate" id="deduplicationRate" readonly="readonly" >
			</div>
				<div class="fitem">
				<label>核减数:</label> <input name="subtractNum"
					id="subtractNum" class="easyui-numberbox"  required="true" min="0" precision="0" missingMessage="请输入整数数字" validType="subtract['fm_record #effectNumWithDeduplication']">
			</div>  -->	
				<div class="fitem">
				<label>渠道实际展示数:</label> <input name="channelExternalNum" id="channelExternalNum"  readonly="readonly">
			</div>
             <div class="fitem"  id="recordWithIssubtract" style="display:none">
				<label>核减与否:</label>
				 <input name="record_issubtract" id="record_issubtract" type="radio" value="1" checked>确认核减
				 <input name="record_issubtract" id="record_issubtract" type="radio" value="0">取消核减
			</div>
		</form>
	</div>

	<div id="dlg-buttons_record">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="saveRecord()">Save</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg_record').dialog('close')">Cancel</a>
	</div>


</body>
</html>