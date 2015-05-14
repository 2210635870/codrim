function initUpload(){
	$("#appFileInput").uploadify(
			{
				'swf' : projectPath
						+ '/res/uploadify/uploadify.swf',
				'uploader' : url,
				'method' : 'get',
				'auto' : true, // 选定文件后是否自动上传，默认false
				'fileSizeLimit' : '1000MB', // 设置单个文件大小限制，单位为byte
				'fileTypeDesc' : '支持格式:apk', // 如果配置了以下的'fileExt'属性，那么这个属性是必须的
				'fileTypeExts' : '*.apk',// 允许的格式
				'buttonImage' : projectPath
						+ '/res/img/test.png',
				'width' : 70,
				'height' : 29,
			    'multi':false,
			    'fileObjName':'appFileInput',
			    'removeCompleted'	: false,
			    'queueSizeLimit'  : 1,
			    'progressData' 	: 'all',// 'percentage''speed''all'//队列中显示文件上传进度的方式：all-上传速度+百分比，percentage-百分比，speed-上传速度
			    'queueID':'queue',
				'formData' : {
					'type':'icon'
				},
				'overrideEvents' : [ 'onDialogClose', 'onUploadSuccess', 'onUploadError', 'onSelectError' ],
			    'onSelectError' : uploadify_onSelectError,
			    'onSelect' : uploadify_onSelect,
				'onUploadSuccess' : function(file, data, response) {
					var res=$.parseJSON(data);
					if(res.success){
							$("#appIconImg").attr("src","http://img.codrim.net/yingjia/" + data.appIcon);
							$("#appFileSpan").text(data.appPname);
					}else{
						alert("上传失败");
					}
					
				},
				'onUploadError' :uploadify_onUploadError
			});
	
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
