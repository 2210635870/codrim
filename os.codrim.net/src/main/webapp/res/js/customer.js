$(function(){
	$('#view').datagrid({
		url:projectPath+'/getCustomers.do',
	});
    /*$.extend($.fn.validatebox.defaults.rules, {     
        validName : {     
            validator: function(value,param){  
            	$.getJSON(projectPath+"/validCompanyName.do?companyName=" + value, function(result){
            		console.log(result);
    			  	if (result == "true") {
    			  		return true;
    			  	} else {
    			  		return false;
    			  	}
    			});
           },     
           message: "公司名称重复"
       }
    });*/
	$("#companyName").keyup(function(){			
		$.getJSON(projectPath+"/validCompanyName.do?companyName=" + $(this).val(), function(result){
		  	if (result) {
		  		$("#checkResultSpan").html("");
		  	} else {
		  		$("#checkResultSpan").html("<font color='red'> 公司名重复,请重新输入！</font>");
		  	}
		});
	});
});
	var url;
	function newUser(){
		$('#dlg').dialog('open').dialog('setTitle','添加客户');
		$('#fm').form('clear');
		url = projectPath+'/saveCus.do';
		$.getJSON(projectPath+'/getAllCustomerType.do', function(json) {  
	        $('#customerType').combobox({
	        	data: json,
	            valueField: 'text',  
	            textField: 'text',  
	            editable: 'false',
	        });  
	    })
	    $("#companyCountry").val("中国");
	}
	function editUser(){
		var row = $('#view').datagrid('getSelected');
		if (row){
			$('#dlg').dialog('open').dialog('setTitle','编辑客户信息');
			$('#fm').form('load',row);
			url = projectPath+'/editCus.do?id='+row.id;
			
			if ($("#companyPhone").val().length > 0) {
				$("#contactPhone").validatebox({    
				    required: false  
				});
			}
			if ($("#contactPhone").val().length > 0) {
				$("#companyPhone").validatebox({    
				    required: false  
				});
			}
			
			$.getJSON(projectPath+'/getAllCustomerType.do', function(json) {  
		        $('#customerType').combobox({
		        	data: json,
		            valueField: 'text',  
		            textField: 'text',  
		            editable: 'false',
		            onLoadSuccess : function() {
		            	$(this).combobox("setValue", row.customerType);
		    		}
		        });  
		    })
		    
		}
	}
	function saveUser(){
		if(!$("#fm").form('validate')){
			 return;
		 };
		$('#fm').form('submit',{
			url: url,
			onSubmit: function(){
				return $(this).form('validate');
			},
			success: function(result){
				var result = eval('('+result+')');
				if (result.success){
					$('#dlg').dialog('close');		// close the dialog
					$('#view').datagrid('reload');	// reload the user data
				} else {
					$.messager.show({
						title: 'Error',
						msg: result.msg
					});
				}
			}
		});
	}
	function removeUser(){
		var row = $('#view').datagrid('getSelected');
		if (row){
			$.messager.confirm('删除客户','你确认删除该客户吗?',function(r){
				if (r){
					$.post(projectPath+'/deleteCus.do',{id:row.id},function(result){
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
	function userDetail(){
		var row=$('#view').datagrid('getSelected');
		if(row){
			$('#dlgDetail').dialog('open').dialog('setTitle','客户详细');
			$("#detail_msg #customerName").text(row.customerName);
			$("#detail_msg #contactName").text(row.contactName);
			$("#detail_msg #contactPhone").text(row.contactPhone);
			$("#detail_msg #companyPhone").text(row.companyPhone);
			$("#detail_msg #website").text(row.website);
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
	