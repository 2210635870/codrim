// 日期格式化，如 new Date(val).format("yyyy-MM-dd")
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


// 获取SELECT控件数据
function ajaxFillSelect(selectId, url, selectedKey) {
	$.getJSON(ctx + url, function(json) {  
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

// 文字计数
function countText(inputId) {
	$count = $('#' + inputId).siblings(".textCounts").children("strong");
	$('#' + inputId).keyup(function(){
		var num = $('#' + inputId).val().length;
		$count.html(num.toString());
	});
}



//---------------------------- EasyUI Data Grid Formatters
function datetimeFormatter(value, row, index) {
	var date = new Date(value);
	return date.format("yyyy-MM-dd hh:mm:ss");
}

function dateFormatter(value, row, index) {
	var date = new Date(value);
	return date.format("yyyy-MM-dd");
}

function rmbUnitFormatter(value, row, index) {
	return value + "元";
}

function reviewStatusFormatter(value, row, index) {
	if (value == "1") {
		return "待审核";
	} else if (value == "2") {
		return "通过";
	} else if (value == "3") {
		return "不通过";
	} else {
		return "";
	}
}

function exchangeTypeFormatter(value, row, index) {
	if (value == "1") {
		return "支付宝";
	} else {
		return "手机话费";
	}
}