$(function(){
	$('#view').datagrid({
		url : projectPath+'/getChannels.do',
	});
	
	$("#companyName").keyup(function(){			
		$.getJSON(projectPath+"/validChannelName.do?companyName=" + $(this).val(), function(result){
		  	if (result) {
		  		$("#checkResultSpan").html("");
		  	} else {
		  		$("#checkResultSpan").html("<font color='red'> 公司名重复,请重新输入！</font>");
		  	}
		});
	});
	
	$.extend($.fn.validatebox.defaults.rules, {
		equals : {
			validator : function(value, param) {
				return value == $(param[0]).val();
			},
			message : '两次输入密码不一致'
		}
	});
});


var url;
function newChannel() {
	$('#dlg').dialog('open').dialog('setTitle', 'New User');
	$('#fm').form('clear');
	url = projectPath+'/saveChan.do';
	$('#channelName').validatebox({    
	    required: true,    
	    validType: "validName['channelName']" 
	});
	
	$.getJSON(projectPath+'/getAllChannelTypeName.do', function(json) {  
        $('#channelType').combobox({
        	data: json,
            valueField: 'text',  
            textField: 'text',  
            editable: 'false',
        });  
    })
    $("#companyCountry").val("中国");
}
function editChannel() {
	var row = $('#view').datagrid('getSelected');
	if (row) {
		$("#channelName").removeClass("easyui-validatebox validatebox-text validatebox-invalid").unbind('focus').unbind('blur');  
		$('#dlg').dialog('open').dialog('setTitle', '编辑客户信息');
		url =projectPath+ '/editChan.do?id=' + row.id;
		$("#channelPassword_2").val(row.channelPassword);
		$('#fm').form('load', row);
		
		
		if ($("#companyPhone").val().length > 0) {
			$("#channelContactPhone").validatebox({    
			    required: false  
			});
		}
		if ($("#channelContactPhone").val().length > 0) {
			$("#companyPhone").validatebox({    
			    required: false  
			});
		}
		$("#fm").form('validate');
		
		$.getJSON(projectPath+'/getAllChannelTypeName.do', function(json) {  
	        $('#channelType').combobox({
	        	data: json,
	            valueField: 'text',  
	            textField: 'text',  
	            editable: 'false',
	            onLoadSuccess : function() {
	            	$(this).combobox("setValue", row.channelType);
	    		}
	        });  
	    });
	}
}



function saveChannel(type) {
	
	 if(!$("#fm").form('validate')){
		 return;
	 };
	$.ajax({
		  type: "post",
		  url: url,
		  data:{
			channelName:$("#channelName").val(),
			channelType:$("#channelType").combobox("getText"),
			channelEmail:$("#channelEmail").val(),
			channelPassword:$("#channelPassword").val(),
			channelContactName:$("#channelContactName").val(),
			channelContactPhone:$("#channelContactPhone").val(),
			remark:$("#remark").val(),
			companyName:$("#companyName").val(),
			companyPhone:$("#companyPhone").val(),
			companyCountry:$("#companyCountry").val(),
			companyCity:$("#companyCity").val(),
			companyAddress:$("#companyAddress").val(),
			channelContactPosition:$("#channelContactPosition").val(),
			//channelContactEmail:$("#channelContactEmail").val(),
			channelContactWx:$("#channelContactWx").val(),
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
function removeChannel() {
	var row = $('#view').datagrid('getSelected');
	if (row) {
		$.messager.confirm('删除渠道','你确认删除该渠道吗?',
			function(r) {
				if (r) {
					$.post(projectPath+'/deleteChan.do',
			{
				id : row.id
			}, function(result) {
				if (result.success) {
					$('#view').datagrid('reload');
			} else {
				$.messager.show({
			title : 'Error',
						msg : result.msg
					});
				}
			}, 'json');
				}
			});
	}
}
	
	 
	 function requiredPair(id, oid) {
		if ($("#"+id).val().length == 0) {
			$("#"+oid).validatebox({    
			    required: true  
			});
		} else {
			$("#"+oid).validatebox({    
			    required: false  
			});
		}
		$("#fm").form('validate');
	}
		
