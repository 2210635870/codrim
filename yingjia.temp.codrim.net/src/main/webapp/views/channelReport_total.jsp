<%@ page language="java" import="common.codrim.pojo.TbAdmin"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
	TbAdmin user = (TbAdmin) session.getAttribute("user");
	String account = "";
	int type = 0;
	 if (user == null) {
		 response.getWriter().write("<script>window.parent.location.href='index.jsp';</script>");
	} else {
		account = user.getAccount();
		type = user.getType();
	}  
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/res/jQuery.easyui.1.2.2/js/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/res/jQuery.easyui.1.2.2/js/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/res/jQuery.easyui.1.2.2/css/main.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/res/jQuery.easyui.1.2.2/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/res/jQuery.easyui.1.2.2/js/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/res/jQuery.easyui.1.2.2/js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/res/jQuery.easyui.1.2.2/js/plugins/jquery.edatagrid.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/res/jQuery.easyui.1.2.2/js/plugins/jquery.edatagrid.lang.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/res/js/common.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/res/js/channelReport_total.js"></script>
		<script type="text/javascript">
	var projectPath="${pageContext.request.contextPath}";
	var channelName='<%=account%>';
	
</script>
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
						<td>产品名</td>
						<td><input name="productName" id="productName"
							class="easyui-validatebox" validType=length[2,10] /></td>
							<td>投放平台</td>
						<td><select id="deviceplate" class="easyui-combobox" name="deviceplate" style="width:200px;">
			<option value="-1">全部</option>			
    <option value="android">android</option>
    <option value="ios">ios</option>
</select></td>
						<td>&nbsp;</td>
						<td><a class="easyui-linkbutton" onclick="searchReport();">搜索</a></td>
					</tr>
				</table>
			</div>
		</form>
	</div>

	<table id="view" idField="id" class="easyui-datagrid" rownumbers="true" pagination="true"
		fitColumns="true"  singleSelect="true" pageSize=10 toolbar="#tb">
		<thead>
			<tr>
				<th field="date" align="center" width="auto"
					editor="{type:'validatebox',options:{required:true}}">日期</th>
				<th field="productName" align="center" width="auto"
					editor="{type:'validatebox',options:{required:true}}">产品</th>
				<th field="channelName" align="center" width="auto"
					editor="{type:'validatebox',options:{required:true}}">渠道名</th>
				<th field="putPrice" align="center" width="auto"
					editor="{type:'validatebox',options:{required:true}}">单价</th>
				<th field="effectNum" align="center" width="auto"
					editor="{type:'validatebox',options:{required:true}}">效果数</th>
				<th field="income" align="center" width="auto"
					editor="{type:'validatebox',options:{required:true}}">收入</th>
			</tr>
		</thead>
	</table>

</body>
</html>