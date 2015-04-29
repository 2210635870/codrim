<%@ page language="java" import="common.codrim.pojo.TbAdmin"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/common-taglib.jsp"%>
<html>
<head>
<jsp:include page="../common/common.jsp" />
<script type="text/javascript" src="${ctx}/res/js/advert/upload.js"></script>
<script type="text/javascript" src="${ctx}/res/js/advert/productList.js"></script>

<style type="text/css">
body {
	margin: 0;
}
.fitem{
margin: 10px 0 0 0;
}
.easyui-dialog form input{
/* width:95%; */
}
</style>
</head>
<body style="visibility: visible;">
 
    <div>
		<form method="post" id="searchForm">
			<div style="padding-left: 150px">
				<table>
					<tr>
						<!-- <td>开始时间</td>
						<td><input id="startTime" name="startTime"
							class="easyui-datebox" editable="false"
							data-options="formatter:myformatter,parser:myparser" /></td>
						<td>结束时间</td>
						<td><input id="endTime" name="endTime" class="easyui-datebox"
							editable="false"
							data-options="formatter:myformatter,parser:myparser" /></td>
							 -->
						<td>客户名</td>
						<td><input id="cusName" name="cusName"></td>
							<td>产品名</td>
						<td><input id="productName" name="productName"></td>
						<td>&nbsp;</td>
						<td><a class="easyui-linkbutton" href="#" onclick="searchProduct()">搜索</a></td>
					</tr>
				</table>
			</div>
		</form>
	</div>

		<table id="view" idField="id" class="easyui-datagrid" rownumbers="true"  singleSelect="true" toolbar="#tb" fitColumns="true" >
		<thead>
			<tr>
				<th field="id" width="80" align="center">编号</th>
				<th field="productName" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">广告系列</th>
				<th field="customerName" align="center"width="80"
					editor="{type:'validatebox',options:{required:true}}">客户</th>
						<th field="createName" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">创建人</th>
				<th field="productIden" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">产品标识</th>
				<th field="productType" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">产品类型</th>	
				<th field="advertNum" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">广告数</th>
				<th field="waitEvaluateNum" align="center" width="80""
					editor="{type:'validatebox',options:{required:true}}">待评估广告数</th>
				<th field="countPutChannels" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">渠道总数</th>
				<th field="countRunningPutChannels" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">在跑渠道</th>
			</tr>
		</thead>
	</table>

	<div id="tb">
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="makeProduct()">创建广告系列</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toLookProduct()">查看广告系列</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toEditProduct()">编辑广告系列</a>
	</div>
	<div>
	<jsp:include page="./createProduct.jsp"></jsp:include>
	
	
	
	
	
	</div>
</body>
</html>