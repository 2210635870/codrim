<%@ page language="java" import="common.codrim.pojo.TbAdmin"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/common-taglib.jsp"%>
<html>
<head>
<jsp:include page="../common/common.jsp" />
<script type="text/javascript" src="${ctx}/res/js/advert/advert.js"></script>
<script type="text/javascript" src="${ctx}/res/js/advert/createAdvert.js"></script>
<script type="text/javascript" src="${ctx}/res/js/advert/viewChannelEnquiry.js"></script>

<script>
var userName='<%=account%>';
</script>
<style type="text/css">
body {
	margin: 0;
}
.fitem{
margin: 10px 0 0 0;
}

</style>
</head>
<body style="visibility: visible;overflow-y:hidden;overflow-x:hidden;">
 
    <div>
		<form method="post" id="searchForm">
			<div style="padding-left: 150px">
				<table>
					<tr>
				<!-- 	<td>开始时间</td>
						<td><input id="startTime" name="startTime"
							class="easyui-datebox" editable="false"
							data-options="formatter:myformatter,parser:myparser" /></td>
						<td>结束时间</td>
						<td><input id="endTime" name="endTime" class="easyui-datebox"
							editable="false"
							data-options="formatter:myformatter,parser:myparser" /></td>
							 -->
						<td>客户名</td>
						<td><input id="customerName" name="customerName"></td>
							<td>广告系列</td>
						<td><input id="productName" name="productName"></td>
						<td>&nbsp;</td>
						<td><a class="easyui-linkbutton" href="#" onclick="getAdverts()">搜索</a></td>
					</tr>
				</table>
			</div>
		</form>
	</div>

		<table id="view" idField="id" class="easyui-datagrid" rownumbers="true"  singleSelect="true" toolbar="#tb" fitColumns="true" >
		<thead>
			<tr>
				<th field="id" width="80" align="center">编号</th>
				<th field="advertName" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">广告</th>
				<th field="evaluateStatus" align="center"width="80"
					editor="{type:'validatebox',options:{required:true}}">评估状态</th>
					<th field="operateEvaluateResult" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">运营评估结果</th>
						<th field="finalEvaluateResult" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">商务评估结果</th>
					
						<th field="runningStatus" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">运行状态</th>
				<th field="accessPrice" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">接入单价</th>
				<th field="deviceplate" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">平台</th>	
				<th field="settlementWay" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">结算方式</th>
					<th field="effectType" align="center" width="80""
					editor="{type:'validatebox',options:{required:true}}">效果</th>
						<th field="effectTypeBack" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">效果2(参考)</th>
					<th field="waysOfCooperation" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">合作方式</th>
			<th field="require" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">要求</th>
				<th field="createName" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">添加人</th>
					<th field="addTime" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">添加时间</th>
			</tr>
		</thead>
	</table>

	<div id="tb">
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="addAdvert()">添加</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editAdvert()">编辑</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toChannelEnquiry()">渠道询价</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="confirmChannelEnquiry()">确认询价</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toOperateEvaluate()">运营评估</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toFinalEvaluate()">商务评估</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="addPutChannel()">添加渠道</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="viewPutChannel()">查看渠道</a>
	</div>
	<jsp:include page="./createAdvert.jsp"></jsp:include>
	<jsp:include page="./channelEnquiry.jsp"></jsp:include>
	<jsp:include page="./operateEvaluate.jsp"></jsp:include>
	<jsp:include page="./finalEvaluate.jsp"></jsp:include>
	<jsp:include page="./confirmChannelEnquiry.jsp"></jsp:include>
	<jsp:include page="./addPutChannel.jsp"></jsp:include>
	<jsp:include page="./viewPutChannel.jsp"></jsp:include>
</body>
</html>