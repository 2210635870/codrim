$(function(){
	getProduct();
	$(".accordion-header").addClass("accordion-header-selected");
	getAdvertDataAndPutChannelNum();
	getCPParam();
	
	
});

window.onload=function(){
	initUpload();
}

function getAdvertDataAndPutChannelNum(){
	$('#view').datagrid({
		url : projectPath+'/getAdvertsAndPutChannelNumByProductId.do?id='+productId,
		loadFilter: function(data){
			$.each(data.rows,function(n,value){
			
			});
		return data;
		}
	});
	
}


function initUpload(){
	$("#banner").uploadify(
			{
				'swf' : projectPath
						+ '/res/uploadify/uploadify.swf',
				'uploader' : projectPath+"/uploadImage.do",
				'method' : 'get',
				'auto' : true, // 选定文件后是否自动上传，默认false
				'fileSizeLimit' : '2MB', // 设置单个文件大小限制，单位为byte
				'fileTypeDesc' : '支持格式:jpg或jpeg或png', // 如果配置了以下的'fileExt'属性，那么这个属性是必须的
				'fileTypeExts' : '*.jpg;*.jpeg;*.png',// 允许的格式
				'buttonImage' : projectPath
						+ '/res/img/test.png',
				'width' : 70,
				'height' : 29,
			    'multi':false,
			    'removeCompleted'	: false,
			    'queueSizeLimit'  : 1,
			    'progressData' 	: 'all',// 'percentage''speed''all'//队列中显示文件上传进度的方式：all-上传速度+百分比，percentage-百分比，speed-上传速度
			    'queueID':'banner-queue',
				'formData' : {
					'type':'banner'
				},
				'overrideEvents' : [ 'onDialogClose', 'onUploadSuccess', 'onUploadError', 'onSelectError' ],
			    'onSelectError' : uploadify_onSelectError,
				'onUploadSuccess' : function(file, data, response) {
					var res=$.parseJSON(data);
					if(res.success){
						imageCallback(res,file,'banner');
					}else{
						alert("上传失败");
					}
					
				},
				'onUploadError' :uploadify_onUploadError
			});
	
	$("#tablescreen").uploadify(
			{
				'swf' : projectPath
						+ '/res/uploadify/uploadify.swf',
				'uploader' : projectPath+"/uploadImage.do",
				'method' : 'get',
				'auto' : true, // 选定文件后是否自动上传，默认false
				'fileSizeLimit' : '20MB', // 设置单个文件大小限制，单位为byte
				'fileTypeDesc' : '支持格式:jpg或jpeg或png', // 如果配置了以下的'fileExt'属性，那么这个属性是必须的
				'fileTypeExts' : '*.jpg;*.jpeg;*.png',// 允许的格式
				'buttonImage' : projectPath
						+ '/res/img/test.png',
						'checkExisting' : false,
				'width' : 70,
				'height' : 29,
			    'multi':false,
			    'removeCompleted'	: false,
			    'queueSizeLimit'  : 1,
			    'progressData' 	: 'all',// 'percentage''speed''all'//队列中显示文件上传进度的方式：all-上传速度+百分比，percentage-百分比，speed-上传速度
			    'queueID':'tablescreen-queue',
				'formData' : {
					'type':'tablescreen'
				},
				'overrideEvents' : [ 'onDialogClose', 'onUploadSuccess', 'onUploadError', 'onSelectError', ],
			    'onSelect' : uploadify_onSelect,
			    'onSelectError' : uploadify_onSelectError,
				'onUploadSuccess' : function(file, data, response) {
					var res=$.parseJSON(data);
					if(res.success){
						imageCallback(res,file,'tablescreen');
					}else{
						alert("上传失败");
					}
					
				},
				'onUploadError' :uploadify_onUploadError
			});
	$("#fullscreen").uploadify(
			{
				'swf' : projectPath
						+ '/res/uploadify/uploadify.swf',
				'uploader' : projectPath+"/uploadImage.do",
				'method' : 'get',
				'auto' : true, // 选定文件后是否自动上传，默认false
				'fileSizeLimit' : '20MB', // 设置单个文件大小限制，单位为byte
				'fileTypeDesc' : '支持格式:jpg或jpeg或png', // 如果配置了以下的'fileExt'属性，那么这个属性是必须的
				'fileTypeExts' : '*.jpg;*.jpeg;*.png',// 允许的格式
				'buttonImage' : projectPath
						+ '/res/img/test.png',
						'checkExisting' : false,
				'width' : 70,
				'height' : 29,
			    'multi':false,
			    'removeCompleted'	: false,
			    'queueSizeLimit'  : 1,
			    'progressData' 	: 'all',// 'percentage''speed''all'//队列中显示文件上传进度的方式：all-上传速度+百分比，percentage-百分比，speed-上传速度
			    'queueID':'fullscreen-queue',
				'formData' : {
					'type':'fullscreen'
				},
				'overrideEvents' : [ 'onDialogClose', 'onUploadSuccess', 'onUploadError', 'onSelectError', ],
			    'onSelect' : uploadify_onSelect,
			    'onSelectError' : uploadify_onSelectError,
				'onUploadSuccess' : function(file, data, response) {
					var res=$.parseJSON(data);
					if(res.success){
						imageCallback(res,file,'fullscreen');
					}else{
						alert("上传失败");
					}
					
				},
				'onUploadError' :uploadify_onUploadError
			});
}
function save(){
	if(bannerPath==null||tablescreenPath==null||fullscreenPath==null){
		alert("请上传完整素材！");
		return ;
	}else{
			$.ajax({
				            type: "post",
				            url: projectPath+"/savePath.do",
				            data: {
					id:productId,
					banner:bannerPath,
					tablescreen:tablescreenPath,
					fullscreen:fullscreenPath
			              	},
				            dataType: "json",
				            success: function (data) {
					         if(data.success){
					    alert("保存成功！");
					         }
					            },
				            error: function (msg) {
				   alert("保存失败，联系管理员！");
				            }
				});
	}
}
var bannerPath,tablescreenPath,fullscreenPath;
function imageCallback(res,file,type){
	if(type=='banner'){
	bannerPath=res.object;
	}
	if(type=='tablescreen'){
		tablescreenPath=res.object;
		}
	if(type=='fullscreen'){
		fullscreenPath=res.object;
		}
	var img='<img src="'+imagePath+res.object+'"  >';
	var Id = file.id;
	$("#"+type+"-queue #"+Id+" .fileName").css("display","none");
	$("#"+type+"-queue #"+Id+" .data").css("display","none");
	$("#"+type+"-queue #"+Id+" .uploadify-progress").css("display","none");
	$("#"+type+"-queue #"+Id+" .cancel a").attr("href","javascript:deleteImage('"+Id+"','"+type+"')");
	$("#"+type+"-queue #"+Id+" .cancel").css("display","block");
	$("#"+type+"-queue #"+Id).append(img);
	
}
function deleteImage(id,type){
	if(type=='banner'){
		bannerPath=null;
		$('#banner').uploadify('cancel', id);
		}
		if(type=='tablescreen'){
			tablescreenPath=null;
			$('#tablescreen').uploadify('cancel', id);
			}
		if(type=='fullscreen'){
			fullscreenPath=null;
			$('#fullscreen').uploadify('cancel', id);
			}
}
function getProduct(){
	$.ajax({
		            type: "post",
		            url: projectPath+"/getProductData.do",
		            data: {
			id:productId
	              	},
		            dataType: "json",
		            success: function (data) {
	         if(data !=null){
	        	 $("#productName").html(data.product.productName);
	        	 $("#customerName").html(data.product.customerName);
	        	 $("#productType").html(productType);
	        	$("#createDescribe").html(data.product.createDescribe);
	        	$('#androidPacketSize').html( data.product.androidPacketSize+" MB");
	        	$("#androidPacketUrl").html(data.product.androidPacketUrl);
	        	$('#iosPacketSize').html( data.product.iosPacketSize+" MB");
	        	$("#iosPacketUrl").html(data.product.iosPacketUrl);
	        	if(data.product.isBack!=null){
	        	$("#isBack").html(data.product.isBack==1?'是':'否');
	        	}
	        	//图片处理
	        	if(data.res.length>0){
	        		convertImage(data.res);
	        	}
	    		$("#createProduct").css("display","block");
	    		$("#submit").attr("onclick","editProduct()");
	         }else{
	        	 alert("获取失败，联系管理员！");
	         }
			
			
			            },
		            error: function (msg) {
		  alert("获取失败，联系管理员！");
		            }
		});
}
	function convertImage(res){
		var screenshot="";
		$.each(res,function(n,value){
			if(value.sourceType==1){
				var img='<img src="'+imagePath+value.sourceUrl+'" id="iconImage" width="90px" height="90px">';
				$("#icon").html(img);
			}else if(value.sourceType==2){
				screenshot +='<img src="'+imagePath+value.sourceUrl+'"  style="width: 158px;  height:180px;">';
			}else if(value.sourceType==3){
				var img='<img src="'+imagePath+value.sourceUrl+'"  style="width: 640px;height: 100px;">';
				$("#banner-queue").html(img);
			}else if(value.sourceType==4){
				var img='<img src="'+imagePath+value.sourceUrl+'"  style="width: 480px;height: 320px;">';
				$("#tablescreen-queue").html(img);
			}
			else if(value.sourceType==5){
				var img='<img src="'+imagePath+value.sourceUrl+'"  style="width: 640px;height: 960px;">';
				$("#fullscreen-queue").html(img);
			}
		});
		$("#screenshot").html(screenshot);
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
