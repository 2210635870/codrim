<%@ page language="java" import="common.codrim.pojo.TbAdmin"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script type="text/javascript">
var  putChannelId;;
var channelName;
function editPutChannel(){
	var row= $('#putChannelList').datagrid('getSelected');
	if(row){
		putChannelId=row.id;
		channelName=row.channelName;
	$('#dlg_put_channel').dialog('open').dialog('setTitle', '渠道');
	initChannel("dlg_put_channel #channelName","dlg_put_channel #personInCharge");
	initDateBox();
	$("#dlg_put_channel #channelName").combobox("resize","auto");
	}else{
		alert("请先选择广告！");
	}
	}
	
	function initDateBox(){
		$('#startTime').datebox({
		    required:true,
		    editable:false,
		    formatter:myformatter,
		    parser:myparser
		});
		$('#endTime').datebox({
		    required:true,
		    editable:false,
		    formatter:myformatter,
		    parser:myparser,
		    onSelect:function(date){
		    	writeBudgeWithDay();
		    }
		});
		initCurrentTime("startTime","endTime");

	}
	function getBudgeNumWithDay(){
		var budgeJson='';
		for(time in timeArray){
			budgeJson=budgeJson+timeArray[time]+'_'+$("#time_"+timeArray[time]).numberbox('getValue')+',';
			}
		budgeJson=budgeJson.substring(0,budgeJson.length-1);
		return budgeJson;
	}
	function savePutChannel(){
		var isBack=$('input[name="channelIsBack"]:checked').val();
		var effectCallBackValue=$("#effectCallBackValue").val();
		if(isBack==1){
		getCheckBoxValue();
    	if(chk_value==''||url==''){
    		alert("请选择回调参数！");
    		return;
    	}
    	if(effectCallBackValue==''||effectCallBackValue==null){
    		alert("请填写效果接受方式！");
    		return;
    	}
		}
    	$.ajax({
    		            type: "post",
    		            url: projectPath+"/addPutChannel.do",
    		            dataType: "json",
    		            data:{
    		            	productId:$("#searchform #productName").combobox('getValue'),
    		            		advertId:advertIdWithPutChannel,
    		            		channelId:$("#dlg_put_channel #channelName").combobox('getValue'),
    		            		personInCharge:$("#personInCharge").html(),
    		            		startTime:$("#startTime").datebox('getValue'),
    		            		endTime:$("#endTime").datebox('getValue'),
    		            		effectType:$('input[name="effectType"]:checked').val(),
    		            		effectTypeBack:$('input[name="effectTypeBack"]:checked').val(),
    		            		putPrice:$("#putPrice").numberbox('getValue'),
    		            		budgeType:$('input[name="budgeType"]:checked').val(),
    		            		budgeNumWithAll:$("#budgeNumWithAll").numberbox('getValue'),
    		            			budgeNumWithDay	:getBudgeNumWithDay(),
    		            			settlementWay:$('input[name="settlementWay"]:checked').val(),
    		            	isBack: isBack,
    		            	putChannelType:1,
    		            	effectCallBackType:$('input[name="effect"]:checked').val(),
    		            	effectCallBackValue:effectCallBackValue,
    		            	paramValue:chk_value
    		            },
    		            success: function (data) {
    			         if(data.success){
    			        	 $('#dlg_put_channel').dialog('close');
    			        	 alert("保存成功！");
    			         }
    			            },
    		            error: function (msg) {
    		alert("出错了！");
    		            }
    		});
		
	}
function clearPutChannel(){
	$("input[name='effectType'][value=1]").attr("checked",	true);
	$("input[name='effectTypeBack'][value=1]").attr("checked",	true);
            $("#putPrice").numberbox('setValue','');
            $("input[name='budgeType'][value=1]").attr("checked",	true);
$("#budgeNumWithAll").numberbox('setValue','');
$("input[name='effect'][value=1]").attr("checked",	true);
$("input[name='channelIsBack'][value=0]").attr("checked",	true);
$("input[name='settlementWay'][value=1]").attr("checked",	true);

toBudgeConfig(1);
	$("#effectCallBackValue").val('');
	toChannelParam(0);
}

</script>

<div  id="dlg_put_channel"  class="easyui-dialog"
		style="width: 700px; height: 500px; padding: 10px 20px" closed="true"
		buttons="#dlg_put_channel-buttons" modal="true" pagination="true">
		<div>
		<strong>投放方式</strong><div><a href="#" class="easyui-linkbutton">网盟</a><a href="#" class="easyui-linkbutton">CPA</a><a href="#" class="easyui-linkbutton">CPC</a></div>
		</div>
		
		<div>
		<strong>渠道信息</strong>
		<div><span>渠道：</span><input id="channelName" name="channelName"></div>
		<div><span>渠道负责人：</span><span id="personInCharge" ></span></div>
		</div>
		
				<div>
		<strong>投放策略</strong>
		
		<div><span>开始时间：</span><input id="startTime" name="startTime"
							class="easyui-datebox" editable="false"
							data-options="formatter:myformatter,parser:myparser" /></div>
		<div><span>结束时间：</span><input id="endTime" name="endTime" class="easyui-datebox"
							editable="false"
							data-options="formatter:myformatter,parser:myparser" /></div>
								<div><span>结算方式：<input name="settlementWay"  type="radio" checked value="1" onclick="toAdvertName('注册')">CPA
  <input name="settlementWay"  type="radio" value="2" onclick="toAdvertName('激活')"> CPS</span>
  </div>	
		<div><span>效果定义：</span><input name="effectType"  type="radio" checked value="1" onclick="toAdvertName('注册')">注册
  <input name="effectType"  type="radio" value="2" onclick="toAdvertName('激活')"> 激活
   <input name="effectType" type="radio"  value="3" onclick="toAdvertName('首次消费成功')">首次消费成功
    <input name="effectType" type="radio" value="4" onclick="toAdvertName('两次以上消费成功')">两次以上消费成功
  <input name="effectType"  type="radio" value="5" onclick="toAdvertName('消费金额')"> 消费金额</div>
  
  	<div >
				<span>效果定义2(参考使用):</span>
				  <input name="effectTypeBack" type="radio" checked value="1">注册
  <input name="effectTypeBack"  type="radio" value="2" > 激活
   <input name="effectTypeBack"  type="radio"  value="3">首次消费成功
    <input name="effectTypeBack"  type="radio" value="4">两次以上消费成功
  <input name="effectTypeBack"  type="radio" value="5" > 消费金额
			</div>
  	<div><span>投放单价：</span><input id="putPrice" name="putPrice"  type="text"  class="easyui-numberbox"
						required="true" min="0.1" precision="1"/></div>
						
		</div>
	<div>
	<script type="text/javascript">
	function  toBudgeConfig(type){
		if(type==1){
			$("#budgeTpe_2").css("display","none");
			$("#budgeTpe_3").css("display","none");
		}
		if(type==2){
			$("#budgeTpe_2").css("display","block");
			$("#budgeTpe_3").css("display","none");
		}
		if(type==3){
			$("#budgeTpe_2").css("display","none");
			$("#budgeTpe_3").css("display","block");
			writeBudgeWithDay();
		}
	}
	var timeArray;
	function initNumBox(){
		for(time in timeArray){
			$('#time_'+timeArray[time]).numberbox({
			    min:0,
			    precision:0,
			    required:true
			});
			}
	}
	function writeBudgeWithDay(){
		var startTime=$("#startTime") .datebox('getValue');
		var endTime=$("#endTime") .datebox('getValue');
		var days=dateDiff(startTime, endTime);
		timeArray=new Array();;
		var html='<tr><th>日期</th><th>预算</th></tr><tr><td >'+startTime+'</td><td><input  id="time_'+startTime+'" type="text"  style="width: 60px;"></td></tr>';
		timeArray.push(startTime);
		for(var i=0;i<days;i++){
			startTime=startTime.replace(/-/g,"/");
			startTime=new Date(startTime);
			startTime=startTime.setDate(startTime.getDate()+1);
			startTime=myformatter(new Date(startTime));
			html=html+'<tr><td >'+startTime+'</td><td><input  id="time_'+startTime+'" type="text" style="width: 60px;"></td></tr>';
			timeArray.push(startTime);
		}
		$("#budgeNumWithDay").html(html);
		initNumBox();
	}
	</script>
	
	<strong>预算</strong>
	<div><input name="budgeType" type="radio" checked value="1" onclick="toBudgeConfig(1)">无预算
  <input name="budgeType"  type="radio" value="2"  onclick="toBudgeConfig(2)"> 总预算
   <input name="budgeType"  type="radio"  value="3" onclick="toBudgeConfig(3)">日预算</div>
   <div id="budgeTpe_2"  style="display:none"><span>预算值：</span><input type="text"  id="budgeNumWithAll" class="easyui-numberbox"
						required="true" min="0" precision="0"/></div>
   <div id="budgeTpe_3" style="display:none">
<table  >
    <tbody id="budgeNumWithDay">
  
  </tbody></table>

   
   </div>
   
	</div>
		<div>
		<script type="text/javascript">
	    function toChannelParam(type){
	    	if(type==1){
	    		getCodrimParams("channelWithCodrimParam");
	    		$("#configureChannelParam").css("display","block");
	    		$("#effectCallBack").css("display","block");
	    	}else{
	    		$("#configureChannelParam").css("display","none");
	    		$("#effectCallBack").css("display","none");
	    	}
	    }
		function checkEffectCallBack(type){
			if(type==1){
				$("#effectCallBackId").html("链接地址:");
			}else{
				$("#effectCallBackId").html("参数名称:");

			}
		}
		 var chk_value ='';  
		  var url='Http://test.codrim.net/rankClick?';
		  var checkedName='';
	    function getCheckBoxValue(){  //jquery获取复选框值    
	    	  $('input[name="codrimParam"]:checked').each(function(){    
	    	   chk_value =chk_value+$(this).val()+",";   
	    	   var text=$(this).next().html();
	    	   checkedName=checkedName+text.substring(0,text.indexOf("_"))+",";
	    	   url=url+text.substring(text.indexOf("_")+1,text.length)+"={}&";
	    	  });    
	    	  chk_value=chk_value.substring(0,chk_value.length-1);
	    	  url=url.substring(0,url.length-1);
	    	  checkedName=checkedName.substring(0,checkedName.length-1);
	    	}
		
		
		
		</script>
	<strong>回调</strong>
	<div><span>是否有后台</span><input name="channelIsBack" type="radio"  value="1" onclick="toChannelParam(1)">有
  <input name="channelIsBack"  type="radio" value="0"  onclick="toChannelParam(0)" checked> 无
  </div>
  <div id="effectCallBack" style="display:none">
   <span>回传渠道效果方式：</span><input name="effect" type="radio" checked value="1" onclick="checkEffectCallBack(1)">固定链接
  <input name="effect"  type="radio" value="2"  onclick="checkEffectCallBack(0)"> 使用链接参数
  <span id="effectCallBackId">链接地址:</span><input type="text"  id="effectCallBackValue"/>
   </div>
   <div id="configureChannelParam"  style="display:none">
   <span>接收点击数据参数配置：</span>
   <div id="channelWithCodrimParam">
   </div>
   </div>
		
   
   
	</div>
		
		
		
	
	
</div>
<div id="dlg_put_channel-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="submit" onclick="savePutChannel()">保存</a> 
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_put_channel').dialog('close')">Cancel</a>
	</div>
</div>
