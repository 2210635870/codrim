$(function(){
//    $.extend($.fn.validatebox.defaults.rules, {     
//        web : {     
//            validator: function(value,param){     
//            	if(param=='channelRankingNumber'){
//            	if(value!=null&&value.length>0){
//            		$("#fm #codrimPostBackLink").val("https://api.codrim.com/statis?source="+$('#channelName').val()+
//            				"&pn="+$('#productName').val()+"&mac=xxx&imei=xxxx&dp=android" +
//            						"&isActivate={1or0}&isRegister={1or0}&consume={1,2,3}&isRecharge={1or0}&isCredit={1or0}&isBaddept={1or0}");
//            	return true;
//            	}else{
//            		return false;
//            	}
//            	}else{
//               var i= /^(http[s]{0,1}|ftp):\/\//i.test($.trim(value));    
//               if(i){
//            		if(param=='productTrackingLink'){
//            			$("#codrimTrackingLink").val("http://api.codrim.net/click?&pn="+$("#productName").val()+"&dp={android Or ios}");
//            		}else{
//            			$("#fm  #codrimPostBackLink").val("http://os.codrim.net/effect?"+"&pn="+$("#productName").val()+
//            	    			"&udid=xxx&mac=xxxx&idfa=xxxx&res={1or0}&dp=ios&isActivate={1or0}&isRegister={1or0}&timeStamp=13333332132");
//            	    			//"https://api.codrim.com/action?pn="+$("#productName").val()+"&udid=xxx&consume={1,2,3}&isRecharge={1or0}&isCredit={1or0}&isBaddept={1or0}");
//            		}
//            		return true;
//               }else{
//            	   return false;
//               }
//            	}
//            },     
//            message: '地址格式错误.'    
//        }     
//    });
    initUpload();
	initCurrentTime("startTime","endTime");
	initProduct();
	getCustomerNames("searchForm #cusName",null);
	getCustomerNames("createProduct #customerName",null);
	getAllProductTypes(null);
});
//产品初始化
//productName为选择的id    channelName为变化的id
function initProduct() {
	$("#searchForm #productName").combobox({
		editable : false,
		valueField : 'text',
		textField : 'text',
		onLoadSuccess : function() { // 加载完成后,设置选中第一项
			var val = $(this).combobox("getData");
			if (val != null && val.length > 0) {
				$(this).combobox("setValue", val[0].text);
			}
			if (val == null) {
				$(this).combobox("clear");
			}
		}
	});
}


function getCustomerNames(id,customerName) {
		$("#"+id+"").combobox({
			editable : true,
			url : projectPath + '/getCustomerName.do',
			valueField : 'id',
			textField : 'text',
			filter:function(q,row){ 
				var opts=$(this).combobox("options"); 
				//return row[opts.textField].indexOf(q)==0;// 
				return row[opts.textField].indexOf(q)>-1;//将从头位置匹配改为任意匹配 
				},
			formatter:function(row){ 
				var opts=$(this).combobox("options"); 
				return row[opts.textField]; 
				},
			onLoadSuccess : function() { // 加载完成后,设置选中第一项
				var val = $(this).combobox("getData");
				if (val != null && val.length > 0) {
					if(customerName!=null){
						$(this).combobox("setValue",customerName);
					}else{
						$(this).combobox("setValue", val[0].text);
						reloadProduct(val[0].text);
					}
				} else {
					$(this).combobox("clear");
				}
				
			},
			onSelect : function(rec) {
				reloadProduct(rec.text);
			}
				
		});

}

function reloadProduct(customerName) {
	var url = projectPath + "/getProductNameByCustomerName.do?customerName=" + customerName;
	$("#searchForm #productName").combobox("clear");
	$("#searchForm #productName").combobox('reload', url);
}



function getAllProductTypes(productType) {
	$("#createProduct #productType").combobox({
		editable : false,
		url : projectPath + '/getAllProductTypes.do',
		valueField : 'id',
		textField : 'text',
		onLoadSuccess : function() { // 加载完成后,设置选中第一项
		var val = $(this).combobox("getData");
		if (val != null && val.length > 0) {
			if(productType!=null){
				$(this).combobox("setValue", productType);
			}else{
				$(this).combobox("setValue", val[0].id);
			}
			}
		}
	});
}
function searchProduct(){
	var url = "?" +"&customerName="+$("#searchForm #cusName").combobox("getText")+"&productName="+$("#searchForm #productName").combobox("getText");
	$('#view').datagrid({
	         url : projectPath + '/countProduct.do' +url,
	     });
}

function makeProduct(){
	$("#submit").attr("onclick","add()");
	 clearForm();
	$("#createProduct").css("display","block");
}
function cancel(){
	 clearForm();
		$("#createProduct").css("display","none");
}


function toLookProduct(){
	var row = $('#view').datagrid('getSelected');
	if (row){
		location.href='./lookProduct.jsp?id='+row.id+"&productType="+row.productType;
	}else{
		alert("亲，请先选择产品，再进行查看！");
	}
}
var id;
function toEditProduct(){
	var row = $('#view').datagrid('getSelected');
	if (row){
		id=row.id;
		$.ajax({
			            type: "post",
			            url: projectPath+"/getProductData.do",
			            data: {
				id:id
		              	},
			            dataType: "json",
			            success: function (data) {
				         if(data !=null){
				        	 $("#createProduct #productName").val(data.product.productName);
				        	getCustomerNames("createProduct #customerName",data.product.customerName);
				        	getAllProductTypes(data.product.productType);
				        	$("#createDescribe").val(data.product.createDescribe);
				        	$('#androidPacketSize').numberbox('setValue', data.product.androidPacketSize);
				        	$("#androidPacketUrl").val(data.product.androidPacketUrl);
				        	$('#iosPacketSize').numberbox('setValue', data.product.iosPacketSize);
				        	$("#iosPacketUrl").val(data.product.iosPacketUrl);
				        	//图片处理
				        	if(data.res.length>0){
				        		convertImage(data.res);
				        	}
				    		$("#createProduct").css("display","block");
				    		$("#submit").attr("onclick","editProduct()");
				         }
				            },
			            error: function (msg) {
			   alert("获取失败，联系管理员！");
			            }
			});
	}else{
		alert("亲，请先选择产品，再进行编辑！");
	}
}
function convertImage(res){
	var screenshot="";
	$.each(res,function(n,value){
		if(value.sourceType==1){
			var img='<div class="uploadify-queue-item"><img src="'+imagePath+value.sourceUrl+'" id="iconImage" ></div>';
			$("#icon-queue").html(img);
		}else if(value.sourceType==2){
			screenshot +='<div class="uploadify-queue-item"><img src="'+imagePath+value.sourceUrl+'" ></div>';
		}
	});
	$("#screenshot-queue").html(screenshot);
}
function editProduct(){
	$.ajax({
		            type: "post",
		            url: projectPath+"/editProduct.do",
		            data: {
			id:id,
			productName:$("#createProduct #productName").val(),
			customerName:$("#createProduct #customerName").combobox("getText"),
			productType:$(" #productType").combobox("getValue"),
			createDescribe:$("#createDescribe").val(),
			androidPacketSize:$("#androidPacketSize").val(),
	           androidPacketUrl:$("#androidPacketUrl").val(),
	        	   iosPacketSize:$("#iosPacketSize").val(),
	        	   iosPacketUrl:$("#iosPacketUrl").val(),
	        	   resPath:getAllPath()
	              	},
		            dataType: "json",
		            success: function (data) {
			         if(data.success){
			        	 $("#createProduct").css("display","none");
			        	 clearForm();
			        	 $('#view').datagrid('reload');
			         }else{
			        	   alert("更新失败，联系管理员！");
			        	 }
			            },
		            error: function (msg) {
		   alert("更新失败，联系管理员！");
		            }
		});
	
	
}
function clearForm(){
	 $("#createProduct #productName").val("");
 	getCustomerNames("createProduct #customerName",null);
 	getAllProductTypes(null);
 	$("#createDescribe").val("");
 	$('#androidPacketSize').numberbox('setValue', "");
 	$("#androidPacketUrl").val("");
 	$('#iosPacketSize').numberbox('setValue', "");
 	$("#iosPacketUrl").val("");
 	$("#icon-queue").html("");
 	$("#screenshot-queue").html("");
}

function add(){
	var flag = true;
	$('#createProduct input').each(function () {
	    if ($(this).attr('required') || $(this).attr('validType')) {
	    if (!$(this).validatebox('isValid')) {
	        flag = false;
	        return;
	    }
	    }
	})
	if(flag){
		save();
	}
}

function getAllPath(){
	picpath = picpath.concat(iconPath);
	return picpath.join(":");
}


function save(){
	$.ajax({
		            type: "post",
		            url: projectPath+"/saveProduct.do",
		            data: {
			productName:$("#createProduct #productName").val(),
			customerName:$("#customerName").combobox("getText"),
			productType:$(" #productType").combobox("getValue"),
			createDescribe:$("#createDescribe").val(),
			androidPacketSize:$("#androidPacketSize").val(),
	           androidPacketUrl:$("#androidPacketUrl").val(),
	        	   iosPacketSize:$("#iosPacketSize").val(),
	        	   iosPacketUrl:$("#iosPacketUrl").val(),
	        	   resPath:getAllPath()
	              	},
		            dataType: "json",
		            success: function (data) {
			         if(data.success){
			        	 $("#createProduct").css("display","none");
			        	 clearForm();
			        	 $('#view').datagrid('reload');
//			        	if(data.object!='resourceError'){
//			        		
//			        	}else{
//			        		alert("图标和截图添加失败！请去往更新页面重新上传！");
//			        	}
			         }else{
			        	   alert("创建产品失败，联系管理员！");
			        	 }
			            },
		            error: function (msg) {
		   alert("创建产品失败，联系管理员！");
		            }
		});
}










