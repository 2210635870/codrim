<%@ page language="java" import="common.codrim.pojo.TbAdmin"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script type="text/javascript">
var advertIdWithFinalEvaluate=0;
function toFinalEvaluate(){
	initFinalEvaluate();
	var row= $('#view').datagrid('getSelected');
	if(row){
		if(row.evaluateStatus=='运营评估'){
			alert("正在进行运营评估，不可进行商务评估！");
			return ;
		}
		if(row.evaluateStatus=='渠道询价'){
			alert("还没进行询价完毕，不可评估！");
			return ;
		}
		$("#finalEvaluateName").html(userName);
		advertIdWithFinalEvaluate=row.id;
	$('#dlg_finalEvaluate').dialog('open').dialog('setTitle', '运营评估');
	$('#dlg_finalEvaluate').window('center');

	getChannelEnquirys('dlg_finalEvaluate',advertIdWithFinalEvaluate);
	getOperateEvaluateData();
	initProductWIthAdvert("dlg_finalEvaluate");
	initViewAdvert(row);
	}else{
		alert("请先选择广告！");
	}
	}
	function getOperateEvaluateData(){
		$.ajax({
			            type: "post",
			            url:  projectPath + '/getAdvertById.do',
			            dataType: "json",
			          data:{
			        	 id:advertIdWithFinalEvaluate
			          },
			            success: function (data) {
				         if(data.success){
				        	 $("#dlg_finalEvaluate #operateEvaluateName").html(data.object.operateEvaluateName);
				        	 if(data.object.operateEvaluateResult==1){
				        		 $('#dlg_finalEvaluate #operateEvaluateResult').html("接入");
				        	 }else{
				        		 $('#dlg_finalEvaluate #operateEvaluateResult').html("不接入");
				        	 }
				        	 $("#dlg_finalEvaluate #operateEvaluatePrice").html(data.object.operateEvaluatePrice);
				        	 $("#dlg_finalEvaluate #operateEvaluateRemarkChannel").val(data.object.operateEvaluateRemarkChannel);
				        	 $("#dlg_finalEvaluate #operateEvaluateRemarkBusiness").val(data.object.operateEvaluateRemarkBusiness);
				         }else{
				        	 alert("出错了！");
				         }
				            },
			            error: function (msg) {
			alert("出错了！");
			            }
			});
	}

	
	
function addFinalEvaluate(){
	$.ajax({
		            type: "post",
		            url:  projectPath + '/addFinalEvaluate.do',
		            dataType: "json",
		          data:{
		        	  	advertId:advertIdWithFinalEvaluate,
		        	  	finalEvaluateName:$("#finalEvaluateName").html(),
		        	  	finalEvaluateResult:$('input[name="finalEvaluateResult"]:checked').val(),
		        	  	finalEvaluateRemark:$("#finalEvaluateRemark").val(),
		          },
		            success: function (data) {
			         if(data.success){
			        	 $('#dlg_finalEvaluate').dialog('close');
			        	 initFinalEvaluate();
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
function initFinalEvaluate(){
	
	
}
</script>

<div  id="dlg_finalEvaluate"  class="easyui-dialog"
		style="width: 700px; height: 500px; padding: 10px 20px" closed="true"
		buttons="#dlg_finalEvaluate-buttons" modal="true" pagination="true">
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
			<div title="运营评估" class="divcss" data-options="collapsed:false" >
		 <div id="">
		 <strong>运营评估</strong>
		 <div><span>评估人:</span><span id="operateEvaluateName"></span></div>
		 <div><span>评估结果:</span><span id="operateEvaluateResult" /></span></div>
		 <div><span>评估单价:</span><span id="operateEvaluatePrice"> </span></div>
		 <div><span>评估备注（渠道）:</span><textarea name="require"  id="operateEvaluateRemarkChannel" cols="20" rows="5" readonly></textarea></div>
		 <div><span>评估备注（商务）:</span><textarea name="require"  id="operateEvaluateRemarkBusiness" cols="20" rows="5" readonly></textarea></div>
		 </div>
		  </div>
		 </div>
		 <br>
		 <br>
		 		 <div >
		 <strong>商务评估</strong>
		 <div><span>评估人:</span><span id="finalEvaluateName"></span></div>
		 <div><span>评估结果:</span><input type="radio" name="finalEvaluateResult" value="1" checked/>投放<input type="radio" name="finalEvaluateResult" value="0"/>不投放</div>
		 <div><span>评估备注（商务）:</span><textarea name="require"  id="finalEvaluateRemark" cols="20" rows="5"></textarea></div>
		 </div>
		
		 
		 
</div>
<div id="dlg_finalEvaluate-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="submit" onclick="addFinalEvaluate()">保存</a> 
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_finalEvaluate').dialog('close')">Cancel</a>
	</div>
</div>
