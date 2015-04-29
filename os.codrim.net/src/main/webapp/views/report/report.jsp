<%@ page language="java" import="common.codrim.pojo.TbAdmin"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/common-taglib.jsp"%>
<html>
<head>
<jsp:include page="../common/common.jsp" />
<script type="text/javascript" src="${ctx}/res/js/report.js"></script>
<style type="text/css">
body {
	margin: 0;
}
.tableCss {
border:1px solid black;
border-collapse:collapse;
width:100%;
}
.tableCss td{
border:1px solid black;
font-size:21px;
text-align:center;
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
							<td>客户名</td>
						<td><input name="customerName" id="customerName"
							class="easyui-validatebox" validType=length[2,20] /></td>
						<td>产品名</td>
						<td><input name="productName" id="productName"
							class="easyui-validatebox" validType=length[2,20] /></td>
							<!-- 	<td>渠道BD</td>
						<td><input name="channelContactName" id="channelContactName"
							class="easyui-validatebox" validType=length[2,10] /></td>
							 -->
						<td>渠道名</td>
						<td><input name="channelName" id="channelName"
							class="easyui-validatebox" validType=length[2,20] /></td>
						<td>&nbsp;</td>
						<td><a class="easyui-linkbutton" onclick="submit();">搜索</a></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<br>
	<br>
	<div>
	<div><strong style="font-size:30px">汇总</strong></div>
	<br>
	<br>
	<table class="tableCss">
	<tr>
	<td>收入</td><td id="allIncome" style="color:red">&nbsp;&nbsp;</td>
	<td>成本</td><td id="allCosts" style="color:red">&nbsp;&nbsp;</td>
	<td>毛利</td><td id="allGrossProfit" style="color:red">&nbsp;&nbsp;</td>
	</tr>
	</table>
	</div>
	<br>
	<br>
		<div>
		<div><strong style="font-size:30px">普通广告</strong></div>
		<br>
		<br>
	<table class="tableCss">
	<tr>
	<td>在投广告总数</td><td id="generalAllRunning" style="color:red">&nbsp;&nbsp;</td>
	<td>待上线广告数量</td><td id="generalAllPause" style="color:red">&nbsp;&nbsp;</td>
	<!--  <td>评估广告数量</td><td>&nbsp;&nbsp;</td>-->
	<td  >下线广告数量</td><td id="generalAllOffline" style="color:red">&nbsp;&nbsp;</td>
	</tr>
		<tr>
	<td>广告收入</td><td id="generalAllIncome" style="color:red">&nbsp;&nbsp;</td>
	<td>广告成本</td><td id="generalAllCosts" style="color:red">&nbsp;&nbsp;</td>
	<td>广告毛利</td><td id="generalAllGrossProfit" style="color:red">&nbsp;&nbsp;</td>
	<td>广告毛利率</td><td id="generalAllGrossMargin" style="color:red">&nbsp;&nbsp;</td>
	</tr>
	</table>
	</div>
	<br>
	<br>
			<div>
		<div><strong style="font-size:30px">联运广告</strong></div>
		<br>
		<br>
	<table class="tableCss">
	<tr>
	<td>在投广告总数</td><td id="interAllRunning" style="color:red">&nbsp;&nbsp;</td>
	<td>待上线广告数量</td><td id="interAllPause" style="color:red">&nbsp;&nbsp;</td>
	<!-- <td>评估广告数量</td><td>&nbsp;&nbsp;</td> -->
	<td  >下线广告数量</td><td id="interAllOffline" style="color:red">&nbsp;&nbsp;</td>
	</tr>
		<tr>
	<td>广告收入</td><td id="interAllIncome" style="color:red">&nbsp;&nbsp;</td>
	<td>广告成本</td><td id="interAllCosts" style="color:red">&nbsp;&nbsp;</td>
	<td>广告毛利</td><td id="interAllGrossProfit" style="color:red">&nbsp;&nbsp;</td>
	<td>广告毛利率</td><td id="interAllGrossMargin" style="color:red">&nbsp;&nbsp;</td>
	</tr>
	</table>
	</div>
	<br>
		<br>
	
	<!--  <div>
		<div><strong style="font-size:30px">录入数据</strong></div>
		<br>
		<br>
			<table id="view" idField="id" class="easyui-datagrid" rownumbers="true" pagination="true" fitColumns="true"
	singleSelect="true" pageSize=10 toolbar="#tb">
		<thead>
			<tr>
				<th field="id" width="auto" align="center" editor="{type:'validatebox',options:{required:true}}">编号</th>
				<th field="rankingsTime" align="center" width="auto" editor="{type:'validatebox',options:{required:true}}">录入时间</th>
				<th field="rankingsHour" align="center" width="auto" editor="{type:'validatebox',options:{required:true}}">客户名</th>
				<th field="productName" align="center" width="auto" editor="{type:'validatebox',options:{required:true}}">产品名</th>
				<th field="channelName" align="center" width="auto" editor="{type:'validatebox',options:{required:true}}">渠道BD</th>
				<th field="accessPrice" align="center" width="auto" editor="{type:'validatebox',options:{required:true}}">渠道名</th>
				<th field="putPrice" align="center" width="auto" editor="{type:'validatebox',options:{required:true}}">产品性质</th>
				<th field="activate" align="center" width="auto" editor="{type:'validatebox',options:{required:true}}">接入单价</th>
				<th field="register" align="center" width="auto" editor="{type:'validatebox',options:{required:true}}">放出单价</th>
				<th field="consumeOne" align="center" width="auto" editor="{type:'validatebox',options:{required:true}}">客户结算数</th>
				<th field="consumeMore" align="center" width="auto" editor="{type:'validatebox',options:{required:true}}">渠道反馈数</th>		
				<th field="recharge" align="center" width="auto" editor="{type:'validatebox',options:{required:true}}">渠道结算数</th>
				<th field="credit" align="center" width="auto" editor="{type:'validatebox',options:{required:true}}">核减渠道比例</th>
				<th field="baddept" align="center" width="auto" editor="{type:'validatebox',options:{required:true}}">产品转化率</th>
				<th field="detail" align="center" width="auto" editor="{type:'validatebox',options:{required:true}}">结算收入</th>
				<th field="detail" align="center" width="auto" editor="{type:'validatebox',options:{required:true}}">结算成本</th>
				<th field="detail" align="center" width="auto" editor="{type:'validatebox',options:{required:true}}">毛利</th>
					<th field="detail" align="center" width="auto" editor="{type:'validatebox',options:{required:true}}">毛利率</th>
					<th field="detail" align="center" width="auto" editor="{type:'validatebox',options:{required:true}}">备注</th>
			</tr>
		</thead>
	</table>
	</div>
	-->		
	
	
</body>
</html>