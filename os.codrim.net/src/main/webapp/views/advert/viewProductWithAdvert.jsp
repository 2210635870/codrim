<%@ page language="java" import="common.codrim.pojo.TbAdmin"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script  type="text/javascript">
function initProductWIthAdvert(imageId){
	getProduct(imageId);
	$(".accordion-header").addClass("accordion-header-selected");
}
function getProduct(imageId){
	$.ajax({
		            type: "post",
		            url: projectPath+"/getProductData.do",
		            data: {
			id:$("#searchForm #productName").combobox("getValue")
	              	},
		            dataType: "json",
		            success: function (data) {
	         if(data !=null){
	        	 $("#lookProduct  #productName").html(data.product.productName);
	        	 $("#lookProduct #customerName").html(data.product.customerName);
	        	 getProductTypeNameById(data.product.productType);
	        	$("#lookProduct #createDescribe").html(data.product.createDescribe);
	        	$('#lookProduct #androidPacketSize').html( data.product.androidPacketSize+" MB");
	        	$("#lookProduct #androidPacketUrl").html(data.product.androidPacketUrl);
	        	$('#lookProduct #iosPacketSize').html( data.product.iosPacketSize+" MB");
	        	$("#lookProduct #iosPacketUrl").html(data.product.iosPacketUrl);
	        	$("#lookProduct #isBack").html(data.product.isBack==1?'是':'否');
	        	//图片处理
	        	if(data.res.length>0){
	        		convertImage(data.res,imageId);
	        	}
	         }else{
	        	 alert("获取失败，联系管理员！");
	         }
			
			
			            },
		            error: function (msg) {
		  alert("获取失败，联系管理员！");
		            }
		});
}
function getProductTypeNameById(productTypeId){
	$.ajax({
		            type: "post",
		            url: projectPath+"/getProductTypeById.do",
		            data: {
			id:productTypeId
	              	},
		            dataType: "json",
		            success: function (data) {
	         if(data.success){
	        	 $("#lookProduct #productType").html(data.object.productType);
	         }else{
	        	 alert("获取失败，联系管理员！");
	         }
			            },
		            error: function (msg) {
		  alert("获取失败，联系管理员！");
		            }
		});
}



	function convertImage(res,imageId){
		var screenshot="";
		$.each(res,function(n,value){
			if(value.sourceType==1){
				var img='<img src="'+imagePath+value.sourceUrl+'" id="iconImage" width="90px" height="90px">';
				$("#"+imageId+" #icon").html(img);
			}else if(value.sourceType==2){
				screenshot +='<img src="'+imagePath+value.sourceUrl+'"  style="width: 158px;  height:180px;">';
			}else if(value.sourceType==3){
				var img='<img src="'+imagePath+value.sourceUrl+'"  style="width: 640px;height: 100px;">';
				$("#"+imageId+" #banner-queue").html(img);
			}else if(value.sourceType==4){
				var img='<img src="'+imagePath+value.sourceUrl+'"  style="width: 480px;height: 320px;">';
				$("#"+imageId+" #tablescreen-queue").html(img);
			}
			else if(value.sourceType==5){
				var img='<img src="'+imagePath+value.sourceUrl+'"  style="width: 640px;height: 960px;">';
				$("#"+imageId+" #fullscreen-queue").html(img);
			}
		});
		$("#"+imageId+" #screenshot").html(screenshot);
	}
	
</script>


<div id="lookProduct" class="easyui-accordion" style="overflow-y:auto;overflow-x:hidden;fit:true"  data-options="border:false,animate:true,multiple:true" >
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
    </div>
