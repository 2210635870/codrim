//获取字符串长度
function strlen(str) {
	var len = 0;
	for (var i = 0; i < str.length; i++) {
		var c = str.charCodeAt(i);
		// 单字节加1
		if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
			len++;
		} else {
			len += 2;
		}
	}
	return len;
}

function myformatter(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
}
function myparser(s) {
	if (!s)
		return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0], 10);
	var m = parseInt(ss[1], 10);
	var d = parseInt(ss[2], 10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
		return new Date(y, m - 1, d);
	} else {
		return new Date();
	}
}
function getCodrimParams(viewCodrimId){
	
	$.ajax({
		            type: "post",
		            url: projectPath+"/getAllCodrimLinkParam.do",
		            dataType: "json",
		            success: function (data) {
			         if(data.total>0){
			        	 var html='';
			        	$.each(data.rows,function(n,value){
			        		html=html+'  <input type="checkbox" name="codrimParam" id="" value="'+value.id+'"><span>'+value.paramZhName+'_'+value.paramEnName+'</span>';
			        	});
			        	 $("#"+viewCodrimId).html(html);
			         }
			            },
		            error: function (msg) {
		alert("出错了！");
		            }
		});
	
	
	
}





// 获取渠道名下拉框
function getChannelName() {
	$('#channelName').combobox({
		editable : false,
		url : projectPath + '/getChannelName.do',
		valueField : 'text',
		textField : 'text',
		onLoadSuccess : function() { // 加载完成后,设置选中第一项
			var val = $(this).combobox("getData");
			if (val.length > 0) {
				$(this).combobox("setValue", val[0].text);
			}
		}
	});
}
var json = [];
var data1 = {
	"text" : "全部",
	"id" : "全部"
};
json.push(data1);
// 获取客户名下拉框 参数select为是否有下级（产品）下拉框联动 ,method 为访问接口
//customerName为选择的id   productName为变化的id
function getCustomerName(select, method,customerName,productName) {
	if (select == false) {
		$("#"+customerName+"").combobox({
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
					$(this).combobox("setValue", val[0].text);
				} else {
					$(this).combobox("clear");
				}
			}
		});
	}
	if (select == true) {
		$("#"+customerName+"").combobox({
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
					$(this).combobox("setValue", val[0].text);
					reloadProduct(val[0].text, method,productName);
				}
				if (val == null) {
					$(this).combobox("clear");
				}
			},
			onSelect : function(rec) {
				if (rec.text == "全部") {
					$("#"+productName+"").combobox("clear");
					$("#"+productName+"").combobox("loadData", json);
					$("#channelName").combobox("clear");
					$("#channelName").combobox("loadData", json);
					return;
				}
				reloadProduct(rec.text, method,productName);
			}
		});
	}
}





/**
 * 冲榜产品
 * 
 * @param customerName
 */
function reloadProduct(customerName, method,productName) {
	var url = projectPath + method + customerName;
	$("#"+productName+"").combobox("clear");
	$("#"+productName+"").combobox('reload', url);
}


function getPersonInCharge(channelName,personInCharge){
	$.ajax({
		            type: "post",
		            url: projectPath+"/getPersonInCharge.do",
		            data: {
		                 channelName:channelName
	              	},
		            dataType: "json",
		            success: function (data) {
			         if(data.success){
			    $("#"+personInCharge).html(data.personInCharge);
			         }else{
			     	    $("#"+personInCharge).html("");
			         }
			            },
		            error: function (msg) {
		    $("#"+personInCharge).html("");
		alert("出错了！");
		            }
		});
	
	
	
	
}

/**
 * 获取产品下拉框
 * productName选择的id    channelName变化的id
 */
function getProductName(method, channelMethod,productName,channelName) {
	$("#"+productName+"").combobox({
		editable : false,
		url : projectPath + method,
		valueField : 'text',
		textField : 'text',
		onLoadSuccess : function() { // 加载完成后,设置选中第一项
			var val = $(this).combobox("getData");
			if (val.length > 0) {
				$(this).combobox("setValue", val[0].text);
				reloadChannel(val[0].text, channelMethod,channelName);
			}
		},
		onSelect : function(rec) {
			if (rec.text == "全部") {
				$("#"+channelName+"").combobox("clear");
				$("#"+channelName+"").combobox("loadData", json);
				return;
			}
			reloadChannel(rec.text, channelMethod,channelName);
		}

	});
}

/**
 * 获取渠道联系人下拉框 参数为是否有下级（渠道）级联
 * 
 * @param type
 */
function getAllChannelContactName(type,method,channelContactName,channelName) {
	if (type == false) {
		$("#"+channelContactName+"").combobox({
			editable : false,
			url : projectPath + '/getAllChannelContactName.do',
			valueField : 'text',
			textField : 'text',
			onLoadSuccess : function() { // 加载完成后,设置选中第一项
				var val = $(this).combobox("getData");
				if (val.length > 0) {
					$(this).combobox("setValue", val[0].text);
				}
			}
		});
	}
	if (type == true) {
		$("#"+channelContactName+"").combobox({
			editable : false,
			url : projectPath + '/getAllChannelContactName.do',
			valueField : 'text',
			textField : 'text',
			onLoadSuccess : function() { // 加载完成后,设置选中第一项
				var val = $(this).combobox("getData");
				if (val != null && val.length > 0) {
					$(this).combobox("setValue", val[0].text);
					reloadChannel(val[0].text,method,channelName);
				}
				if (val == null) {
					$(this).combobox("clear");
				}
			},
			onSelect : function(rec) {
				reloadChannel(rec.text,method,channelName);
			}
		});
	}

}

// 初始化当前时间
function initCurrentTime(startTime,endTime) {
	var curr_time = new Date();
	$("#"+startTime+"").datebox("setValue", myformatter(curr_time));
	$("#"+endTime+"").datebox("setValue", myformatter(curr_time));
}



//------------------------------------------------------------------- Added by MaLiang Start
//日期格式化，如 new Date(val).format("yyyy-MM-dd")
Date.prototype.format = function(format) {
    var o = {
        "M+": this.getMonth() + 1, // month
        "d+": this.getDate(), // day
        "h+": this.getHours(), // hour
        "m+": this.getMinutes(), // minute
        "s+": this.getSeconds(), // second
        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
        "S": this.getMilliseconds() // millisecond
    }
    if (/(y+)/.test(format))
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(format))
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
    
    return format;
}

// AJAX获取SELECT控件数据
function ajaxFillSelect(selectId, url, selectedKey) {
	$.getJSON(projectPath + url, function(json) {  
        $('#' + selectId).combobox({
        	data: json,
            valueField: 'key',  
            textField: 'value',  
            editable: 'false',
            onLoadSuccess : function() {
            	$(this).combobox("setValue", selectedKey);
    		}
        });  
    })
}
// Formatters
function datetimeFormatter(value, row, index) {
	var date = new Date(value);
	return date.format("yyyy-MM-dd hh:mm");
}

function dateFormatter(value, row, index) {
	var date = new Date(value);
	return date.format("yyyy-MM-dd");
}

//计算两个日期间相差天数
//判断是否为闰年
function isLeapYear(year){
if(year % 4 == 0 && ((year % 100 != 0) || (year % 400 == 0)))
{
     return true;
}
return false;
}
//判断前后两个日期
function validatePeriod(fyear,fmonth,fday,byear,bmonth,bday){
if(fyear < byear){
return true;
}else if(fyear == byear){
if(fmonth < bmonth){
   return true;
} else if (fmonth == bmonth){
   if(fday <= bday){
    return true;
   }else {
    return false;
   }
} else {
   return false;
}
}else {
return false;
}
}
//计算两个日期的差值
function dateDiff(d1,d2){
    var disNum=compareDate(d1,d2);
    return disNum;
}
function compareDate(date1,date2)
{
    var regexp=/^(\d{1,4})[-|\.]{1}(\d{1,2})[-|\.]{1}(\d{1,2})$/;
    var monthDays=[0,3,0,1,0,1,0,0,1,0,0,1];
    regexp.test(date1);
    var date1Year=RegExp.$1;
    var date1Month=RegExp.$2;
    var date1Day=RegExp.$3;

    regexp.test(date2);
    var date2Year=RegExp.$1;
    var date2Month=RegExp.$2;
    var date2Day=RegExp.$3;

if(validatePeriod(date1Year,date1Month,date1Day,date2Year,date2Month,date2Day)){
firstDate=new Date(date1Year,date1Month,date1Day);
     secondDate=new Date(date2Year,date2Month,date2Day);

     result=Math.floor((secondDate.getTime()-firstDate.getTime())/(1000*3600*24));
     for(j=date1Year;j<=date2Year;j++){
  if(isLeapYear(j)){
      monthDays[1]=2;
  }else{
      monthDays[1]=3;
  }
  for(i=date1Month-1;i<date2Month;i++){
      result=result-monthDays[i];
  }
     }
     return result;
}else{
    alert('对不起第一个时间必须小于第二个时间，谢谢！');
    exit;
}
}


//------------------------------------------------------------------- Added by MaLiang End



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

