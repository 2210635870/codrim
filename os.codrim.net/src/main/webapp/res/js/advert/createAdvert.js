var url=projectPath;
var id;
function addAdvert(){
	initProductWIthAdvert("dlg");
	clearAdvert();
	$('#dlg').dialog('open').dialog('setTitle', '添加广告');
	$('#dlg').window('center');
$("#advertName").html("注册");
url=url+"/addAdvert.do";
}
var operateEvaluateResult;
function editAdvert(){
	var row= $('#view').datagrid('getSelected');
	if(row){
		operateEvaluateResult=row.operateEvaluateResult;
		initProductWIthAdvert("dlg");
		clearAdvert();
		$('#dlg').dialog('open').dialog('setTitle', '修改广告');
		$('#dlg').window('center');
		initData(row);
	}else{
		alert("请先选择广告！");
	}
	}

function initData(data){
	id=data.id;
	$("#advertName").html(data.advertName);
	if(data.deviceplate=='ios'){
		$("input[name='deviceplate'][value=ios]").attr("checked",	true);
	}else{
		$("input[name='deviceplate'][value=android]").attr("checked",	true);
	}
	if(data.settlementWay=='CPA'){
		$("input[name='settlementWay'][value=1]").attr("checked",	true);
	}else{
		$("input[name='settlementWay'][value=2]").attr("checked",	true);
	}
	var effectType;
	var effectTypeBack;
	if (data.effectType=='注册'){
		effectType=1;
	} else if(data.effectType=='激活'){
		effectType=2;
	}else if(data.effectType=='消费一次'){
		effectType=3;
	}else if(data.effectType=='消费两次以上'){
		effectType=4;
	}else if(data.effectType=='消费金额'){
	effectType=5;
	}
	if (data.effectTypeBack=='注册'){
		effectTypeBack=1;
	} else if(data.effectTypeBack=='激活'){
		effectTypeBack=2;
	}else if(data.effectTypeBack=='消费一次'){
		effectTypeBack=3;
	}else if(data.effectTypeBack=='消费两次以上'){
		effectTypeBack=4;
	}else if(data.effectTypeBack=='消费金额'){
		effectTypeBack=5;
	}
	
	$("input[name='effectType'][value="+effectType+"]").attr("checked",true);
	$("input[name='effectTypeBack'][value="+effectTypeBack+"]").attr("checked",true);
	$('#accessPrice').numberbox('setValue', data.accessPrice);
	if(data.waysOfCooperation=='普通'){
		$("input[name='waysOfCooperation'][value=1]").attr("checked",	true);
	}else{
		$("input[name='waysOfCooperation'][value=2]").attr("checked",	true);
	}
	$("#require").val(data.require);
	url=url+"/editAdvert.do";
	}
function clearAdvert(){
	$("input[name='deviceplate'][value=ios]").attr("checked",	true);
	$("#advertName").html("");
	$("input[name='effectType'][value=1]").attr("checked",	true);
	$("input[name='effectTypeBack'][value=1]").attr("checked",	true);
	$("input[name='settlementWay'][value=1]").attr("checked",	true);
	$("input[name='waysOfCooperation'][value=1]").attr("checked",	true);
	$(" #accessPrice").val("");
$("#require").val("");
}

function submit(){
	$.ajax({
		            type: "post",
		            url: url,
		            data: {
			id:id,
			productId:$("#productName").combobox("getValue"),
			deviceplate:$('input[name="deviceplate"]:checked').val(),
			effectType:$('input[name="effectType"]:checked').val(),
			effectTypeBack:$('input[name="effectTypeBack"]:checked').val(),
			accessPrice:$(" #accessPrice").numberbox("getValue"),
			settlementWay:$('input[name="settlementWay"]:checked').val(),
			waysOfCooperation:$('input[name="waysOfCooperation"]:checked').val(),
			require:$("#require").val(),
			operateEvaluateResult:operateEvaluateResult,
			createName:userName
	              	},
		            dataType: "json",
		            success: function (data) {
			         if(data.success){
			        	 $('#dlg').dialog('close'); // close the dialog
			        	 getAdverts();
			         }
			            },
		            error: function (msg) {
		alert("出错了！");
		            }
		});
	
	
	
}
function checkSizeAndUrl(deviceplate){
		$.ajax({
			            type: "post",
			            url: projectPath+"/getProductData.do",
			            data: {
				id:$("#searchForm #productName").combobox("getValue")
		              	},
			            dataType: "json",
			            success: function (data) {
		         if(data !=null){
		        	 if(deviceplate=='ios'){
		        		 if(data.product.androidPacketSize==""||data.product.androidPacketSize==null){
		        			 alert("该广告系列的android包大小或下载链接没有填写，请前往填写再添加广告！");
		        			 $('#dlg').dialog('close')
		        		 }
		        		 if(data.product.androidPacketUrl==""||data.product.androidPacketUrl==null){
		        			 alert("该广告系列的android包大小或下载链接没有填写，请前往填写再添加广告！");
		        			 $('#dlg').dialog('close')
		        		 }
		        	 }else{
		        		 if(data.product.iosPacketSize==""||data.product.iosPacketSize==null){
		        			 alert("该广告系列的ios包大小和下载链接没有填写，请前往填写再添加广告！");
		        			 $('#dlg').dialog('close')
		        		 }
		        		 if(data.product.iosPacketUrl==""||data.product.iosPacketUrl==null){
		        			 alert("该广告系列的ios包大小和下载链接没有填写，请前往填写再添加广告！");
		        			 $('#dlg').dialog('close')
		        		 }
		        	 }
		         }else{
		        	 alert("判断失败，联系管理员！");
		         }
				            },
			            error: function (msg) {
			  alert("判断失败，联系管理员！");
			            }
			});
	
	
}


function toAdvertName(name){
	$("#advertName").html(name);
}

