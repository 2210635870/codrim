var iconPath;
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
	iconPath=res.object;
	var img='<img src="'+imagePath+iconPath+'" id="iconImage" >';
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
	var img='<img src="'+imagePath+iconPath+'" id="screenshotImage" >';
	var Id = file.id;
	$("#"+Id+" .fileName").css("display","none");
	$("#"+Id+" .data").css("display","none");
	$("#"+Id+" .uploadify-progress").css("display","none");
	$("#"+Id+" .cancel a").attr("href","javascript:deleteScreentshot('"+Id+"','"+screenshotPath+"')");
	$("#"+Id+" .cancel").css("display","block");
	$("#"+Id).append(img);
	picpath = picpath.concat(screenshotPath);
}
function deleteScreentshot(id,imagePath){
	$('#screenshot').uploadify('cancel', id);
	picpath.splice($.inArray(imagePath,picpath),1);
}

var uploadify_onSelectError = function(file, errorCode, errorMsg) {
    var msgText = "上传失败\n";
    switch (errorCode) {
        case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
            //this.queueData.errorMsg = "每次最多上传 " + this.settings.queueSizeLimit + "个文件";
            msgText += "最多还能上传 " + this.settings.queueSizeLimit + "个文件";
            break;
        case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
            msgText += "文件大小超过限制( " + this.settings.fileSizeLimit + " )";
            break;
        case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
            msgText += "文件大小为0";
            break;
        case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
        	
            msgText += "文件格式不正确，仅限 " + this.settings.fileTypeExts;
            break;
        default:
            msgText += "错误代码：" + errorCode + "\n" + errorMsg;
    }
    alert(msgText);
};

var uploadify_onUploadError = function(file, errorCode, errorMsg, errorString) {
    // 手工取消不弹出提示
    if (errorCode == SWFUpload.UPLOAD_ERROR.FILE_CANCELLED
            || errorCode == SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED) {
        return;
    }
    var msgText = "上传失败\n";
    switch (errorCode) {
        case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
            msgText += "HTTP 错误\n" + errorMsg;
            break;
        case SWFUpload.UPLOAD_ERROR.MISSING_UPLOAD_URL:
            msgText += "上传文件丢失，请重新上传";
            break;
        case SWFUpload.UPLOAD_ERROR.IO_ERROR:
            msgText += "IO错误";
            break;
        case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
            msgText += "安全性错误\n" + errorMsg;
            break;
        case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
            msgText += "最多上传 " + this.settings.uploadLimit + "个";
            break;
        case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
            msgText += errorMsg;
            break;
        case SWFUpload.UPLOAD_ERROR.SPECIFIED_FILE_ID_NOT_FOUND:
            msgText += "找不到指定文件，请重新操作";
            break;
        case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
            msgText += "参数错误";
            break;
        default:
            msgText += "文件:" + file.name + "\n错误码:" + errorCode + "\n"
                    + errorMsg + "\n" + errorString;
    }
    alert(msgText);
}

var uploadify_onSelect = function(file){

};
