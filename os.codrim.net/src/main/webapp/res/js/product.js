$(function() {
		$('#view').datagrid({
			url :projectPath+ '/getProducts.do',
		});
		
	    $.extend($.fn.validatebox.defaults.rules, {     
	        web : {     
	            validator: function(value,param){     
	               var i= /^(http[s]{0,1}|ftp):\/\//i.test($.trim(value));    
	               if(i){
	            		if(param=='productTrackingLink'){
	            			$("#codrimTrackingLink").val("http://api.codrim.net/click?&pn="+$("#productName").val()+"&dp={android Or ios}");
	            		}else{
	            			return true;
	            			//$("#codrimPostBackLink").val("http://api.codrim.net/effect?"+"&pn="+$("#productName").val()+
	            	    	//		"&isRanking=0"+"&udid=xxxx&mac=xxx&imei=xxxx&idfa=xxxx&res={1or0}&dp={android Or ios}&"+$("#returnParameter").val());
	            		}
	            		return true;
	               }else{
	            	   return false;
	               }
	            },     
	            message: '地址格式错误.'    
	        }     
	    });
	    
	    
	  
	});

	function convertUrl(){
		$("#fm_url #codrimTrackingLink").val("http://api.codrim.net/click?&pn="+$("#fm_url #productName").val()+"&dp={android Or ios}&"+$("#returnParameter").val());
			$("#fm_url #codrimPostBackLink").val("http://os.codrim.net/effect?"+"&pn="+$("#fm_url #productName").val()+
	    			"&isRanking="+isRanking+"&udid="+$("#udid").val()+"&res={1or0}");
    	
    			
		}
	
function toUrl(){
	var row = $('#view').datagrid('getSelected');
	if (row) {
		$('#fm_url').form('clear');
		$('#dlg_url').dialog('open').dialog('setTitle', 'url信息');
		$('#fm_url').form('load', row);
		//$("input[type=radio][name=isRanking][value=0]").attr("checked",'checked')
		url =projectPath+ '/editPro.do?id=' + row.id;
	}
}

function generateUrl(){
	$("#codrimPostBackLink").val("http://api.codrim.net/effect?"+"&pn="+$("#productName").val()+
			"&isRanking=0"+"&udid=xxxx&mac=xxx&imei=xxxx&idfa=xxxx&res={1or0}&dp={android Or ios}&"+$("#returnParameter").val());
}
function saveUrl(){
	$('#fm_url').form('submit', {
		url : url,
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(result) {
			var result = eval('(' + result + ')');
			if (result.success) {
				$('#dlg_url').dialog('close'); // close the dialog
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
	var url;
	function newProduct() {
		$('#dlg').dialog('open').dialog('setTitle', 'New User');
		$('#fm').form('clear');
		getChannelName();
		getCustomerName(false,"","customerName",'');
		url = projectPath+'/savePro.do';
	}

	function editProduct() {
		var row = $('#view').datagrid('getSelected');
		if (row) {
			$('#fm').form('clear');
			$('#dlg').dialog('open').dialog('setTitle', '编辑客户信息');
			$('#fm').form('load', row);
			url =projectPath+ '/editPro.do?id=' + row.id;
		}
	}
	function saveProduct() {
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
	function removeProduct() {
		var row = $('#view').datagrid('getSelected');
		if (row) {
			$.messager
					.confirm(
							'Confirm',
							'Are you sure you want to remove this user?',
							function(r) {
								if (r) {
									$
											.post(
													projectPath+'/deletePro.do',
													{
														id : row.id
													},
													function(result) {
														if (result.success) {
															$('#view')
																	.datagrid(
																			'reload'); // reload the user data
														} else {
															$.messager
																	.show({ // show error message
																		title : 'Error',
																		msg : result.msg
																	});
														}
													}, 'json');
								}
							});
		}
	}
