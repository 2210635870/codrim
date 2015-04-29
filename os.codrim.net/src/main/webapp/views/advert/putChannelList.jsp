<%@ page language="java" import="common.codrim.pojo.TbAdmin"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/common-taglib.jsp"%>
<html>
<head>
<jsp:include page="../common/common.jsp" />

<script>
var userName='<%=account%>';
$(function (){
	initProduct();
	initAdvert();
	getCustomerName(true,"/getProductNameByCustomerName.do?customerName=","customerName","productName");
})
function getPutChannels(){
	$('#putChannelList').datagrid({
		url : projectPath+'/putChannelList.do?advertId='+$("#advertName").combobox("getValue"),
		loadFilter: function(data){
			$.each(data.rows,function(n,value){
			if (value.runningStatus==3){
				value.runningStatus='下线';
			} else if(value.runningStatus==1){
				value.runningStatus='运行';
			}else if(value.runningStatus==2){
				value.runningStatus='暂停';
			}else{
				value.runningStatus='新加入';
			}
			if (value.putChannelType==1){
				value.putChannelType='网盟';
			} else if(value.putChannelType==2){
				value.putChannelType='CPA';
			}else if(value.putChannelType==3){
				value.putChannelType='CPS';
			}
			
			if (value.settlementWay==1){
				value.settlementWay='CPA';
			} else if(value.settlementWay==2){
				value.settlementWay='CPS';
			}
			if (value.effectType==1){
				value.effectType='注册';
			} else if(value.effectType==2){
				value.effectType='激活';
			}else if(value.effectType==3){
				value.effectType='消费一次';
			}else if(value.effectType==4){
				value.effectType='消费两次以上';
			}else if(value.effectType==5){
				value.effectType='消费金额';
			}
			if (value.isback==1){
				value.isback='是';
			} else if(value.isback==0){
				value.isback='否';
			}
			
			});
			return data;
		}
		});
}
function initProduct() {
	$("#productName").combobox({
		editable : false,
		valueField : 'id',
		textField : 'text',
		onLoadSuccess : function() { // 加载完成后,设置选中第一项
			var val = $(this).combobox("getData");
			if (val != null && val.length > 0) {
				$(this).combobox("setValue", val[0].id);
				reloadAdvert(val[0].id);
			}
			if (val == null) {
				$(this).combobox("clear");
			}
		},
		onSelect : function(rec) {
			reloadAdvert(rec.id);
		}
	});
}

function reloadAdvert(productId) {
	var url = projectPath + "/getAdvertNamesByProductId.do?productId=" + productId;
	$("#advertName").combobox("clear");
	$("#advertName").combobox('reload', url);
}
function initAdvert(){
	$("#advertName").combobox({
		editable : false,
		valueField : 'id',
		textField : 'text',
		onLoadSuccess : function() { // 加载完成后,设置选中第一项
			var val = $(this).combobox("getData");
			if (val != null && val.length > 0) {
				$(this).combobox("setValue", val[0].id);
			}
			if (val == null) {
				$(this).combobox("clear");
			}
		}
	});
}

</script>
</head>
<body style="visibility: visible;">
 
    <div>
		<form method="post" id="searchForm">
			<div style="padding-left: 150px">
				<table>
					<tr>
						<td>客户名</td>
						<td><input id="customerName" name="customerName"></td>
							<td>广告系列</td>
						<td><input id="productName" name="productName"></td>
							<td>广告</td>
						<td><input id="advertName" name="advertName"></td>
						<td>&nbsp;</td>
						<td><a class="easyui-linkbutton" href="#" onclick="getPutChannels()">搜索</a></td>
					</tr>
				</table>
			</div>
		</form>
	</div>

		<table id="putChannelList"  class="easyui-datagrid"  idField="id" rownumbers="true"  fitColumns="true"  singleSelect="true"  toolbar="#tb">
		<thead>
		<tr>
		<th field="id"  align="center" width="100">编号</th>
  <th field="channelName"  align="center" width="100">渠道</th>
  <th field="personInCharge" align="center" width="120">渠道负责人</th>
   <th field="putChannelType" align="center" width="120">渠道类型</th>
    <th field="runningStatus" align="center" width="120">运行状态</th>
    <th field="settlementWay" align="center" width="120">结算方式</th>
        <th field="effectType" align="center" width="120">效果定义</th>
        <th field="putPrice" align="center" width="120">接入单价</th>
     <th field="startTime" align="center" width="120">开始时间</th>
        <th field="endTime" align="center" width="120">结束时间</th>
        <th field="isback" align="center" width="120">是否有后台</th>
</tr>
</thead>
		 </table>

	<div id="tb">
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editPutChannel()">编辑</a>
	</div>
</body>
</html>