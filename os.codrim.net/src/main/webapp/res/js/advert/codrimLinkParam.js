$(function(){
		$('#view').datagrid({
			url:projectPath+'/getAllCodrimLinkParam.do',
			loadFilter: function(data){
				$.each(data.rows,function(n,value){
					if(value.paramType==1){
						value.paramType='默认';
					}
					if(value.paramType==0){
						value.paramType='解析';
					}
				});
				return data;
				}
		});
	    $.extend($.fn.validatebox.defaults.rules, {     
	        validName : {     
	            validator: function(value,param){  
	         var length=strlen(value);
	         if(length>10||length<2){
	        	 $.fn.validatebox.defaults.rules.validName.message="输入内容长度必须介于2和10之间";
	        	 return false;
	         }else{
	        	 if(param=='zh'){
	        	 if(!checkZhStr(value)){
	        		 $.fn.validatebox.defaults.rules.validName.message="请输入中文";
		        	 return false;
	        	 }
	        	 }else{
	        		 if(!checkEnStr(value)){
		        		 $.fn.validatebox.defaults.rules.validName.message="请输入英文";
			        	 return false;
		        	 }
	        	 }
	        	 
	        	 $.fn.validatebox.defaults.rules.validName.message="已被使用";
	        	 var flag=false;
	 			$.ajax({
					            type: "post",
					            url: projectPath+"/validCodrimLinkParamName.do?paramType="+param,
					async:false,
					            data: {
						      name:value
				              	},
					            dataType: "json",
					            success: function (data) {
					     flag=data;
						            },
					            error: function (msg) {
					            }
					});
	        	 return flag;
	         }
	            },     
	            message: ''    
	        }     
	    });
});
function checkZhStr(str){ 
	var re1 = new RegExp("^[\\u4e00-\\u9fa5]*$"); 
	if (!re1.test(str)){ 
	return false; 
	} 
	return true; 
	}
function checkEnStr(str){ 
	// [\u4E00-\uFA29]|[\uE7C7-\uE7F3]汉字编码范围 
	var re1 = new RegExp("^[A-Za-z]+$"); 
	if (!re1.test(str)){ 
	return false; 
	} 
	return true; 
	}



var url;
function add(){
	$('#dlg').dialog('open').dialog('setTitle','添加参数');
	$('#fm').form('clear');
	
	$("input[name='paramType'][value=1]").attr("checked",true);
	url =projectPath+ '/saveCodrimLinkParam.do';
}
function edit(){
	var row = $('#view').datagrid('getSelected');
	if (row){
		$('#dlg').dialog('open').dialog('setTitle','编辑参数');
		$('#fm').form('load',row);
		url =projectPath+ '/editCodrimLinkParam.do?id='+row.id;
	}
}
function submit(){
	$('#fm').form('submit', {
		url : url,
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(result) {
			var result = eval('(' + result + ')');
			if (result.success) {
				$('#dlg').dialog('close'); // close the dialog
				$('#view').datagrid('reload'); // reload the user data
			} else {
				$.messager.show({
					title : 'Error',
					msg : result.msg
				});
			}
		}
	});
	
	
}

