<%@ page language="java" import="common.codrim.pojo.TbAdmin"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/common-taglib.jsp"%>
<html>
<head>
<jsp:include page="../common/common.jsp" />
<script type="text/javascript" src="${ctx}/res/js/rankingsReportDetails.js"></script>
<script type="text/javascript">
	var flag = <%=request.getParameter("flag") %>;
	if(flag){
		var deviceplate='<%=request.getParameter("deviceplate") %>';
		var time = '<%=request.getParameter("startTime") %>';
		var productName = '<%=request.getParameter("productName") %>';
		var channelName = '<%=request.getParameter("channelName") %>';
	}
	
</script>

<style type="text/css">
body {
	margin: 0;
}
</style>

</head>
<body style="visibility: visible;">
   <div id="navDiv">
     <!-- 导航内容 -->
   </div>
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
							class="easyui-validatebox" validType=length[2,20] /></td>
						<td>渠道名</td>
						<td><input name="channelName" id="channelName"
							class="easyui-validatebox" validType=length[2,20] /></td>
						<td>平台</td>
						<td><select id="deviceplate" name="deviceplate">
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
		fitColumns="true" singleSelect="true" pageSize=50 toolbar="#tb">
		<thead>
			<tr>
				<th field="actionTime" align="center" width="150" editor="{type:'validatebox',options:{required:true}}">日期</th>
				<th field="productName" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">产品名</th>
				<th field="channelName" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">渠道名</th>
				<th field="telephone" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">手机号</th>
				<th field="deviceplate" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">设备系统</th>
				<th field="imei" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">imei</th>
				<th field="mac" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">mac</th>
				<th field="idfa" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">idfa</th>
				<th field="address" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">位置</th>
				<th field="clickNum" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">点击</th>
				<th field="effect" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">效果</th>
				<th field="register" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">注册</th>
				<th field="consumeOne" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">首次消费</th>
				<th field="consumeMore" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">2次及以上消费</th>
				<th field="recharge" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">充值</th>
				<th field="credit" align="center" width="150" editor="{type:'validatebox',options:{required:true}}">绑卡</th>
				<th field="remark" align="center" width="150" editor="{type:'validatebox',options:{required:true}}">备注</th>
				
			</tr>
		</thead>
	</table>


</body>
</html>