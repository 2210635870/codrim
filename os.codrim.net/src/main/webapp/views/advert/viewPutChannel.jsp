<%@ page language="java" import="common.codrim.pojo.TbAdmin"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script type="text/javascript">
function viewPutChannel(){
	var row= $('#view').datagrid('getSelected');
	if(row){
		$('#dlg_viewPutChannel').dialog('open').dialog('setTitle', '配置广告');
		$('#dlg_viewPutChannel').window('center');

		getPutChannels(row.id);
		$("#dlg_viewPutChannel #productName").html($("#searchForm #productName").combobox('getText'));
		$("#dlg_viewPutChannel #accessPrice").html(row.accessPrice);
		$("#dlg_viewPutChannel #settlementWay").html(row.settlementWay);
		$("#dlg_viewPutChannel #effectType").html(row.effectType);
		$(".accordion-header").addClass("accordion-header-selected");

	}else{
		alert("请先选择广告！");
	}
	}
function getPutChannels(advertId){
	$('#putChannelList').datagrid({
		url : projectPath+'/putChannelList.do?advertId='+advertId,
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
</script>

<div  id="dlg_viewPutChannel"  class="easyui-dialog"
		style="width: 700px; height: 500px; padding: 10px 20px" closed="true"
		buttons="#dlg_viewPutChannel-buttons" modal="true" pagination="true">
			<div  class="easyui-accordion" style="overflow-y:auto;overflow-x:hidden;fit:true"  data-options="border:false,animate:true,multiple:true" >
			<div title="配置广告" class="divcss" data-options="collapsed:false" >
			<div>
			<span>广告系列：</span><span id="productName"></span>
			</div>
			<div>
			<span>接入单价：</span><span id="accessPrice"></span>
			</div>
			<div>
			<span>结算方式：</span><span id="settlementWay"></span>
			</div>
			<div>
			<span>效果定义：</span><span id="effectType"></span>
			</div>
			</div>
			<div title="定向条件" class="divcss" data-options="collapsed:false" >
			<div>
			<span>地域</span>
			<div id=""></div>
			</div>
				<div>
			<span>手机品牌</span>
			<div id=""></div>
			</div>
			
				<div>
			<span>类型偏好</span>
			<div id=""></div>
			</div>
			
			</div>
			<div title="渠道信息" class="divcss" data-options="collapsed:false" >
					<table id="putChannelList"  class="easyui-datagrid"  idField="id" rownumbers="true"  fitColumns="true"  singleSelect="true" >
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
			</div>
			
			
		</div>
	
</div>
<div id="dlg_viewPutChannel-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="submit" onclick="addDirectional()">保存</a> 
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_viewPutChannel').dialog('close')">Cancel</a>
	</div>
</div>
