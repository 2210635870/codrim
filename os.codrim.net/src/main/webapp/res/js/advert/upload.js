var iconPath=null;
function initUpload(){
	$("#icon").uploadify(
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
			    'queueID':'icon-queue',
				'formData' : {
					'type':'icon'
				},
				'overrideEvents' : [ 'onDialogClose', 'onUploadSuccess', 'onUploadError', 'onSelectError' ],
			    'onSelectError' : uploadify_onSelectError,
			    'onSelect' : uploadify_onSelect,
				'onUploadSuccess' : function(file, data, response) {
					var res=$.parseJSON(data);
					if(res.success){
						iconCallback(res,file);
					}else{
						alert("上传失败");
					}
					
				},
				'onUploadError' :uploadify_onUploadError
			});
	
	$("#screenshot").uploadify(
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
			    'multi':true,
			    'removeCompleted'	: false,
			    'queueSizeLimit'  : 3,
			    'progressData' 	: 'all',// 'percentage''speed''all'//队列中显示文件上传进度的方式：all-上传速度+百分比，percentage-百分比，speed-上传速度
			    'queueID':'screenshot-queue',
				'formData' : {
					'type':'screenshot'
				},
				'overrideEvents' : [ 'onDialogClose', 'onUploadSuccess', 'onUploadError', 'onSelectError', ],
			    'onSelect' : uploadify_onSelect,
			    'onSelectError' : uploadify_onSelectError,
				'onUploadSuccess' : function(file, data, response) {
					var res=$.parseJSON(data);
					if(res.success){
						screenshotCallBack(res,file);
					}else{
						alert("上传失败");
					}
					
				},
				'onUploadError' :uploadify_onUploadError
			});
	
	$("#apkUpload").uploadify(
			{
				'swf' : projectPath
						+ '/res/uploadify/uploadify.swf',
				'uploader' : projectPath+"/uploadApk.do",
				'method' : 'get',
				'auto' : true, // 选定文件后是否自动上传，默认false
				'fileSizeLimit' : '100MB', // 设置单个文件大小限制，单位为byte
				'fileTypeDesc' : '支持格式:apk', // 如果配置了以下的'fileExt'属性，那么这个属性是必须的
				'fileTypeExts' : '*.apk',// 允许的格式
				'buttonImage' : projectPath
						+ '/res/img/test.png',
				'width' : 70,
				'height' : 29,
			    'multi':false,
			    'removeCompleted'	: true,
			    'queueSizeLimit'  : 1,
			    'progressData' 	: 'all',// 'percentage''speed''all'//队列中显示文件上传进度的方式：all-上传速度+百分比，percentage-百分比，speed-上传速度
			    'queueID':'apk-queue',
				'formData' : {
					'type':'apk'
				},
				'overrideEvents' : [ 'onDialogClose', 'onUploadSuccess', 'onUploadError', 'onSelectError' ],
			    'onSelectError' : uploadify_onSelectError,
				'onUploadSuccess' : function(file, data, response) {
					var res=$.parseJSON(data);
					if(res.success){
						$("#androidPacketUrl").val(imagePath+res.object);
						$("#androidPacketUrl").attr("readonly","readonly");
					}else{
						alert("上传失败");
					}
					
				},
				'onUploadError' :uploadify_onUploadError
			});
	
	
	
}

function iconCallback(res,file){
	iconPath=res.object+"_icon";
	var img='<img src="'+imagePath+res.object+'" id="iconImage" >';
	var Id = file.id;
	$("#"+Id+" .fileName").css("display","none");
	$("#"+Id+" .data").css("display","none");
	$("#"+Id+" .uploadify-progress").css("display","none");
	$("#"+Id+" .cancel a").attr("href","javascript:deleteIcon('"+Id+"')");
	$("#"+Id+" .cancel").css("display","block");
	$("#"+Id).append(img);
}

function deleteIcon(id){
	$('#icon').uploadify('cancel', id);
	 iconPath=null;
}


var picpath=new Array();
function screenshotCallBack(res,file){
	var screenshotPath=res.object;
	var img='<img src="'+imagePath+screenshotPath+'" id="screenshotImage" >';
	var Id = file.id;
	$("#"+Id+" .fileName").css("display","none");
	$("#"+Id+" .data").css("display","none");
	$("#"+Id+" .uploadify-progress").css("display","none");
	$("#"+Id+" .cancel a").attr("href","javascript:deleteScreentshot('"+Id+"','"+screenshotPath+"')");
	$("#"+Id+" .cancel").css("display","block");
	$("#"+Id).append(img);
	picpath = picpath.concat(screenshotPath+"_screenshot");
}
function deleteScreentshot(id,imagePath){
	$('#screenshot').uploadify('cancel', id);
	picpath.splice($.inArray(imagePath,picpath),1);
}









