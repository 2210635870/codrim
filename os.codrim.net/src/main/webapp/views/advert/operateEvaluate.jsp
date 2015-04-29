<%@ page language="java" import="common.codrim.pojo.TbAdmin"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style type="text/css">
.buttonCSS{
background-color: #174BD3;
color: #fff;
}
</style>
<script type="text/javascript">
var advertIdWithOperateEvaluate=0;



function toOperateEvaluate(){
	initOperateEvaluate();
	var row= $('#view').datagrid('getSelected');
	if(row){
		if(row.evaluateStatus=='商务评估'){
			alert("已进行最终评估，不能再次评估！");
			return ;
		}
		if(row.evaluateStatus=='渠道询价'){
			alert("还没进行渠道询价，不可评估！");
			return ;
		}
	
		$("#operateEvaluateName").html(userName);
		$('#dlg_operateEvaluate').dialog('open').dialog('setTitle', '运营评估');
		$('#dlg_operateEvaluate').window('center');

		advertIdWithOperateEvaluate=row.id;
		getChannelEnquirys('dlg_operateEvaluate',advertIdWithOperateEvaluate);
		initProductWIthAdvert("dlg_operateEvaluate");
		initViewAdvert(row);
	
	}else{
		alert("请先选择广告！");
	}
	}
	
	
	
function addOperateEvaluate(){
	$.ajax({
		            type: "post",
		            url:  projectPath + '/addOperateEvaluate.do',
		            dataType: "json",
		          data:{
		        	  	advertId:advertIdWithOperateEvaluate,
		        	  	operateEvaluateName:$("#dlg_operateEvaluate #operateEvaluateName").html(),
		        	  	operateEvaluateResult:$('input[name="operateEvaluateResult"]:checked').val(),
		        	  	operateEvaluatePrice:$("#dlg_operateEvaluate #operateEvaluatePrice").numberbox("getValue"),
		        	  	operateEvaluateRemarkChannel:$("#dlg_operateEvaluate #operateEvaluateRemarkChannel").val(),
		        	  	operateEvaluateRemarkBusiness:$("#dlg_operateEvaluate #operateEvaluateRemarkBusiness").val(),
		          },
		            success: function (data) {
			         if(data.success){
			        	 $('#dlg_operateEvaluate').dialog('close');
			        	 initOperateEvaluate();
			        	 alert("评估成功");
			        		$('#view').datagrid('reload');	
			         }else{
			        	 alert("评估失败");
			         }
			            },
		            error: function (msg) {
		alert("出错了！");
		            }
		});
	
	
}
function initOperateEvaluate(){
	
	
}
</script>

<div  id="dlg_operateEvaluate"  class="easyui-dialog"
		style="width: 700px; height: 500px; padding: 10px 20px" closed="true"
		buttons="#dlg_operateEvaluate-buttons" modal="true" pagination="true">
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
<br>
<br>
		 <div >
		 <strong>运营评估</strong>
		 <div><span>评估人:</span><span id="operateEvaluateName"></span></div>
		 <div><span>评估结果</span><input type="radio" name="operateEvaluateResult" value="1" checked/>接入<input type="radio" name="operateEvaluateResult" value="0"/>不接入</div>
		 <div><span>评估单价</span><input type="text" name="operateEvaluatePrice" class="easyui-numberbox" id="operateEvaluatePrice"
						required="true" min="0.1" precision="1"/></div>
		 <div><span>评估备注（渠道）</span><textarea name="require"  id="operateEvaluateRemarkChannel" cols="20" rows="5"></textarea></div>
		 <div><span>评估备注（商务）</span><textarea name="require"  id="operateEvaluateRemarkBusiness" cols="20" rows="5"></textarea></div>
		 </div>
		 
</div>
<div id="dlg_operateEvaluate-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="submit" onclick="addOperateEvaluate()">保存</a> 
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_operateEvaluate').dialog('close')">Cancel</a>
	</div>
</div>
