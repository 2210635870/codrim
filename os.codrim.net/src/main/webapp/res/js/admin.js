$(function(){
		$('#view').datagrid({
			url:projectPath+'/getAllAdmins.do'
		});
		$.extend($.fn.validatebox.defaults.rules, {
		    equals: {
				validator: function(value,param){
					return value == $(param[0]).val();
				},
				message: '两次输入密码不一致'
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
	        	 $.fn.validatebox.defaults.rules.validName.message="已被使用";
	        	 var flag=false;
	 			$.ajax({
					            type: "post",
					            url: projectPath+"/validAdminName.do",
					async:false,
					            data: {
						      account:value
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
//初始化职位信息
var officeNameData;
var url;
function newAdmin(){
	getAllOfficeName();//初始化职位名
	$('#account').validatebox({    
	    required: true,    
	    validType: "validName['account']" 
	});  
	$('#dlg').dialog('open').dialog('setTitle','New User');
	$('#fm').form('clear');
	url =projectPath+ '/saveAdmin.do';
	
}
function editAdmin(){
	var row = $('#view').datagrid('getSelected');
	if (row){
		getAllOfficeName();//初始化职位名
		$("#account").removeClass("easyui-validatebox validatebox-text validatebox-invalid").unbind('focus').unbind('blur');  
		$('#dlg').dialog('open').dialog('setTitle','编辑客户信息');
		$('#fm').form('load',row);
		//$('#account').validatebox('destroy');
		url =projectPath+ '/editAdmin.do?id='+row.id;
	}
}
function saveAdmin(){
	
	 if(!$("#fm").form('validate')){
		 return;
	 };
	$.ajax({
		  type: "post",
		  url: url,
		  data:{
			account:$("#account").val(),
			password:$("#password").val(),
		    officeName:$("#officeName").combobox("getText"),
		    email:$("#email").val(),
		  },
		  dataType:'json',
		  success:function(result){
		  //var result = eval('('+result+')');
			if (result.success){
				$('#dlg').dialog('close');		// close the dialog
				$('#view').datagrid('reload');	// reload the user data
			} else {
				$.messager.show({
					title: 'Error',
					msg: result.msg
				});
			}
		  },
		  error:function(){
			  
		  }
		});
	
}

function removeAdmin(){
	var row = $('#view').datagrid('getSelected');
	if (row){
		$.messager.confirm('Confirm','Are you sure you want to remove this user?',function(r){
			if (r){
				$.post(projectPath+'/deleteAdmin.do',{id:row.id},function(result){
					if (result.success){
						$('#view').datagrid('reload');	// reload the user data
					} else {
						$.messager.show({	// show error message
							title: 'Error',
							msg: result.msg
						});
					}
				},'json');
			}
		});
	}
}


//获得所有的职位类型名
 function getAllOfficeName(){
	if(officeNameData==null){
	 $('#officeName').combobox({
			editable:false,
		    url:projectPath+'/getAllOfficeName.do',
		    valueField:'text',
		    textField:'text',
		    onLoadSuccess: function () { //加载完成后,设置选中第一项
             var val = $(this).combobox("getData");
             officeNameData=val;//赋值
		    if(val.length>0){
               $(this).combobox("setValue",val[0].text);
             }
           }
		});
	}else{
		 $('#officeName').combobox({
				editable:false,
			   // url:projectPath+'/getAllOfficeName.do',
				data:getOffectNameStr(officeNameData),
			    valueField:'text',
			    textField:'text',
			    onLoadSuccess: function () { //加载完成后,设置选中第一项
	             var val = $(this).combobox("getData");
	             officeNameData=val;//赋值
			    if(val.length>0){
	               $(this).combobox("setValue",val[0].text);
	             }
	           }
			});
	}
	
	function getOffectNameStr(data){
		var str="";
		$.each(data,function(i,o){
			str+="{id:"+o.id+",text:"+o.text+"},";
		});
		str=str.substring(0,str.length-1);
		return str;
	}
 }
