<%@ page language="java" import="common.codrim.pojo.TbAdmin"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script type="text/javascript">
var advertIdWithConfimEnquiry=0;
function confirmChannelEnquiry(){
	
	var row= $('#view').datagrid('getSelected');
	if(row){
		$('#dlg_confimChannelEnquiry').dialog('open').dialog('setTitle', '询价确认');
		$('#dlg_confimChannelEnquiry').window('center');
		advertIdWithConfimEnquiry=row.id;
		getChannelEnquirys('dlg_confimChannelEnquiry',advertIdWithConfimEnquiry);
		initProductWIthAdvert("dlg_confimChannelEnquiry");
		initViewAdvert(row);
	}else{
		alert("请先选择广告！");
	}
	}
	
	
	
function toConfirmChannelEnquiry(){
	$.ajax({
		            type: "post",
		            url:  projectPath + '/confimChannelEnquiry.do',
		            dataType: "json",
		          data:{
		        	  	advertId:advertIdWithConfimEnquiry
		          },
		            success: function (data) {
			         if(data.success){
			        	 $('#dlg_confimChannelEnquiry').dialog('close');
			        	 alert("询价确认完成！");
			        		$('#view').datagrid('reload');	
			         }else{
			        	 alert("询价确认失败！");
			         }
			            },
		            error: function (msg) {
		alert("出错了！");
		            }
		});
	
	
}

</script>

<div  id="dlg_confimChannelEnquiry"  class="easyui-dialog"
		style="width: 700px; height: 500px; padding: 10px 20px" closed="true"
		buttons="#dlg_confimChannelEnquiry-buttons" modal="true" pagination="true">
		<jsp:include page="./viewProductWithAdvert.jsp"></jsp:include>
			<div  class="easyui-accordion" style="overflow-y:auto;overflow-x:hidden;fit:true"  data-options="border:false,animate:true,multiple:true" >
			<jsp:include page="./viewAdvert.jsp"></jsp:include>
			<div title="渠道询价" class="divcss" data-options="collapsed:false" >
			<div id="nameArray"></div>
				<table id="channelEnquiryView"  class="easyui-datagrid"  idField="id" rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<!-- <th field="id" width="80" align="center">编号</th> -->
		<th field="id"  align="center" width="80">序号</th>
  <th field="channelName"  align="center" width="80">渠道</th>
  <th field="isaccept" align="center" width="130">是否接入</th>
   <th field="price" align="center" width="100">渠道反馈单价</th>
    <th field="remark" align="center" width="200">备注</th>
    <th field="personInCharge" align="center" width="80">负责人</th>
			</tr>
		</thead>
	</table>
			</div>
</div>
</div>
<div id="dlg_confimChannelEnquiry-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="submit" onclick="toConfirmChannelEnquiry()">询价完毕</a> 
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_confimChannelEnquiry').dialog('close')">Cancel</a>
	</div>
</div>
