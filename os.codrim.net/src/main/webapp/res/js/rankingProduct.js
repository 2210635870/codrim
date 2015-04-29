$(function() {
		$('#view').datagrid({
			url :projectPath+ '/getRankingsProducts.do',
			loadFilter: function(data){
				$.each(data.rows,function(n,value){
					if (value.runningStatus==1){
						value.runningStatus='运行';
					} else {
						value.runningStatus='下线';
					}
				});
			return data;
			}
		});
	    $.extend($.fn.validatebox.defaults.rules, {     
	        web : {     
	            validator: function(value,param){     
	            	if(param=='channelNumber'){
	            	if(value!=null&&value.length>0){
	            		$("#fm_android #codrimPostBackLink").val("https://api.codrim.com/statis?source="+$('#channelName').val()+
	            				"&pn="+$('#productName').val()+"&mac=xxx&imei=xxxx&dp=android" +
	            						"&isActivate={1or0}&isRegister={1or0}&consume={1,2,3}&isRecharge={1or0}&isCredit={1or0}&isBaddept={1or0}");
	            	return true;
	            	}else{
	            		return false;
	            	}
	            	}else{
	               var i= /^(http[s]{0,1}|ftp):\/\//i.test($.trim(value));    
	               if(i){
	            		if(param=='productTrackingLink'){
	            			$("#codrimTrackingLink").val("http://api.codrim.net/click?&pn="+$("#productName").val()+"&dp={android Or ios}");
	            		}else{
	            			$("#fm_ios #codrimPostBackLink").val("http://os.codrim.net/effect?"+"&pn="+$("#productName").val()+
	            	    			"&udid=xxx&mac=xxxx&idfa=xxxx&res={1or0}&dp=ios&isActivate={1or0}&isRegister={1or0}&timeStamp=13333332132");
	            	    			//"https://api.codrim.com/action?pn="+$("#productName").val()+"&udid=xxx&consume={1,2,3}&isRecharge={1or0}&isCredit={1or0}&isBaddept={1or0}");
	            		}
	            		return true;
	               }else{
	            	   return false;
	               }
	            	}
	            },     
	            message: '地址格式错误.'    
	        }     
	    });
	});
function checkDevice(device){
	if(device==1){
		$("#fm_ios").css("display","block");
		$("#fm_android").css("display","none");
		$("#fm_ios #deviceplate").val("ios");
	}else{
		$("#fm_ios").css("display","none");
		$("#fm_android").css("display","block");
		$("#fm_android #deviceplate").val("android");
	}
}
	var url;
	function editProduct() {
		var row = $('#view').datagrid('getSelected');
		if (row) {
			$('#fm_'+row.deviceplate).form('clear');
				$('#dlg_'+row.deviceplate).dialog('open').dialog('setTitle', '编辑产品信息');
			$('#fm_'+row.deviceplate).form('load', row);
			if(row.runningStatus=='下线'){
			$("input[name='runningStatus'][value=3]").attr("checked", true);
		}else{
			$("input[name='runningStatus'][value=1]").attr("checked", true);
			}
			convertInputRead();
			url= projectPath+'/editRankingsPro.do?id='+row.id;
		}
	}
	function convertInputRead(){
		$('#productName').attr("readonly","readonly");
		$('#customerName').attr("readonly","readonly");
		$('#channelName').attr("readonly","readonly");
		$('#channelNumber').attr("readonly","readonly");
	}
	
	function modifyProduct(type) {
		$('#fm_'+type).form('submit', {
			url :url,
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					$('#dlg_'+type).dialog('close');	
					$('#view').datagrid('reload');					
				} else {
					$.messager.show({
						title : 'Error',
						msg : result.msg
					});
				}
			}
		});
	}
	
	function saveProduct(type) {
		$('#fm_'+type).form('submit', {
			url : projectPath+'/saveRankingsPro.do?runningStatus=1',
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					location.href=projectPath+"/views/advert/rankingProductList.jsp?flag=true";
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
													projectPath+'/deleteRankingsPro.do',
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