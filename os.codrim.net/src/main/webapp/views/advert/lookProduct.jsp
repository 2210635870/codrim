<%@ page language="java" import="common.codrim.pojo.TbAdmin"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/common-taglib.jsp"%>
<html>
<head>
<jsp:include page="../common/common.jsp" />
<script type="text/javascript" src="${ctx}/res/js/advert/upload.js"></script>
<script type="text/javascript" src="${ctx}/res/js/advert/lookProduct.js"></script>
<script type="text/javascript">
var productId=<%=(String)request.getParameter("id")%>;
var productType='<%=(String)request.getParameter("productType")%>';
</script>
<style type="text/css">
.divcss{
overflow:auto;padding:10px;width:auto;height:auto
}
.accordion-header{
height: 30px;
}
.accordion-header .panel-title{
font-size: 24px;
line-height: 28px;
}
.divcss div{
padding-bottom: 20px;
}
.divcss div span{
font-size: 20px;
}


.uploadify{
height: 9px;
width: 70px;
position: absolute;
padding-left: 100px;
margin-top: -24px;
}
 #banner-queue .uploadify-queue-item{
position: relative;
float: left;
max-width:700px;
margin-right: 10px;
display: inline;
height: 150px;
}

 #banner-queue .uploadify-queue-item img{
width: 640px;
height: 100px;
/* overflow: hidden; */
display: block;
margin-top: 28px;
margin-left: 2px;
}

 #tablescreen-queue .uploadify-queue-item{
position: relative;
float: left;
max-width: 500px;
margin-right: 10px;
display: inline;
height: 360px;
}
 #tablescreen-queue .uploadify-queue-item img{
width: 480px;
height: 320px;
/* overflow: hidden; */
display: block;
margin-top: 28px;
margin-left: 1px;
}
 #fullscreen-queue .uploadify-queue-item{
position: relative;
float: left;
max-width: 700px;
margin-right: 10px;
display: inline;
height:995px;
}
 #fullscreen-queue .uploadify-queue-item img{
width: 640px;
height: 960px;
/* overflow: hidden; */
display: block;
margin-top: 28px;
margin-left: 1px;
}
</style>
</head>
<body style="visibility: visible;" >
<div style="padding: 15px 16px 10px;"><Strong><a href="javascript:history.go(-1);" style="font-size:25px">返回产品列表页</a></Strong></div>



<div id="lookProduct" class="easyui-accordion" style="overflow-y:auto;overflow-x:hidden;width:auto;height:auto"  data-options="border:false,animate:true,multiple:true" >
    <div title="产品信息" class="divcss" data-options="collapsed:false" >
		<div ><span>客户：</span><span id="customerName"></span></div>
		<div><span>广告系列名：</span><span id="productName"></span></div>
		<div><span>类别：</span><span id="productType"></span></div>
    </div>
    <div title="产品参数" class="divcss" data-options="collapsed:false" >
		<div><span> icon:</span><div id="icon"></div>    </div>
    <div><span> 截图:</span><div id="screenshot"></div>    </div>
     <div><span>描述:</span><span id="createDescribe"></span>  </div>
        <div><span>Android:</span>  </div>
      <div><span>包大小:</span><span id="androidPacketSize"></span>  </div>
        <div><span>包链接:</span><span id="androidPacketUrl"></span>  </div>
            <div><span>ios:</span>  </div>
         <div><span>包大小:</span><span id="iosPacketSize"></span>  </div>
        <div><span>包链接:</span><span id="iosPacketUrl"></span>  </div>
        
    </div>
    <div title="广告素材"  data-options="collapsed:false" >
    <div style="padding-left:242px"><strong><input type="button" value="保存" onclick="save()" style="cursor:pointer;width: 92px;height: 41px;"></strong></div>
	<div><span style="font-size: 20px;">banner:</span> <input id="banner" type="file" /> </div>
		<div><div id="banner-queue" class="uploadify-queue" style="width: 100%;  height: 200px; padding-top: 10px;">
		
</div>
</div>
		<div><span style="font-size: 20px;">插屏:</span>  <input id="tablescreen" type="file" /> </div>
		<div id="tablescreen-queue" class="uploadify-queue" style="width: 100%;  height: 400px;padding-top: 10px;">
		
</div>
		<div><span style="font-size: 20px;">全屏:</span>  <input id="fullscreen" type="file" /> </div>
	<div id="fullscreen-queue" class="uploadify-queue" style="width: 100%;  height: 700px;padding-top: 10px;">
	
</div>
	
	
    </div>
    <script type="text/javascript">
    function toConfig(type){
    	if(type==1){
    		getCodrimParams("viewCodrimParam");
    		$("#configureParam").css("display","block");
    	}else{
    		$("#configureParam").css("display","none");
    	}
    }
	  var chk_value ='';  
	  var url='Http://test.codrim.net/action?';
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
    function saveCPParam(){
    	getCheckBoxValue();
    	if(chk_value==''||url==''){
    		alert("请选择回调参数！");
    		return;
    	}
    	$.ajax({
    		            type: "post",
    		            url: projectPath+"/saveCPParam.do",
    		            dataType: "json",
    		            data:{
    		            	productId:productId,
    		            	isBack:$('input[name="isBack"]:checked').val(),
    		            	value:chk_value
    		            },
    		            success: function (data) {
    			         if(data.success){
    			        	 $("#CPcallBackUrl").html(url);
    			        	 $("#viewCodrimParam").html(checkedName);
    			         }
    			            },
    		            error: function (msg) {
    		alert("出错了！");
    		            }
    		});
    	
    }
    function getCPParam(){
    	$.ajax({
    		            type: "post",
    		            url: projectPath+"/getCPParam.do",
    		            dataType: "json",
    		            data:{
    		            	productId:productId
    		            },
    		            success: function (data) {
    			         if(data.success){
    			        	 $("#CPButton").css("display","none");
    			        	 var html=data.object;
    			        	 if(html!=null){
    			        	 $("#viewCodrimParam").html(html.substring(0,html.indexOf("_")));
    			        	 $("#configureParam").css("display","block");
    			        	 alert(html.substring(html.indexOf("_")+1,html.length-1));
    			        	 $("#CPcallBackUrl").html(html.substring(html.indexOf("_")+1,html.length-1));
    			        	 }
    			         }
    			            },
    		            error: function (msg) {
    		alert("出错了！");
    		            }
    		});
    	
    }
    </script>
    <div title="广告主参数回调" class="divcss" data-options="collapsed:false" >
    <div style="padding-left:242px" id="CPButton"><strong><input type="button" value="保存" onclick="saveCPParam()" style="cursor:pointer;width: 92px;height: 41px;"></strong></div>
		  <div>   
    <span>是否有后台</span>
    <div id="isBack"><input type="radio" name="isBack" value="1" onclick="toConfig(1)">是<input type="radio" name="isBack" value="0" onclick="toConfig(1)" checked>否</div>
  </div>
  <div id="configureParam" style="display:none">
  <span>参数配置</span>
  <div id="viewCodrimParam">

     </div>
     </div>
       <div >
  <span>广告主回调地址:</span><span id="CPcallBackUrl"></span>
     </div>
     
    </div>
       <div title="广告"   data-options="collapsed:false" >
       	<table id="view" idField="id" class="easyui-datagrid" rownumbers="true"  singleSelect="true" fitColumns="true" >
		<thead>
			<tr>
				<th field="id" width="80" align="center">编号</th>
				<th field="advertName" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">广告</th>
				<th field="evaluateStatus" align="center"width="80"
					editor="{type:'validatebox',options:{required:true}}">评估状态</th>
						<th field="runningStatus" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">运行状态</th>
				<th field="accessPrice" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">接入单价</th>
				<th field="deviceplate" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">平台</th>	
				<th field="settlementWay" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">结算方式</th>
					<th field="waysOfCooperation" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">合作方式</th>
				<th field="effectType" align="center" width="80""
					editor="{type:'validatebox',options:{required:true}}">效果</th>
				<th field="effectTypeBack" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">效果2(参考)</th>
				<th field="putChannelNums" align="center" width="80"
					editor="{type:'validatebox',options:{required:true}}">已投渠道数</th>
			</tr>
		</thead>
	</table>
    </div>
</div>
 
 
 
 
 
 
 
 
</body>
</html>