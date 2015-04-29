$(function() {
	initCurrentTime("startTime","endTime");
	initChannel('channelName');
	initProduct('/getChannelNameByProductName.do?productName=',"productName","channelName");
	getCustomerName(true,'/getProductNameByCustomerName.do?customerName=',"customerName","productName");
	$.extend($.fn.validatebox.defaults.rules, {
		subtract : {
			validator : function(value, param) {
				if(value!=null&&value!=""){
					if(param=='effectNumWithDeduplication'){
					$("#channelExternalNum").val($("#"+param).val()-value);
					}else{
						$("#fm_record #channelExternalNum").val($("#"+param).val()-value);
					}
					return true;
				}else{
					return false;
				}
			},
			message : '请输入数字！'
		}
	});
	
});
function submit() {
	var url = projectPath + '/getRankingsReport.do?startTime='
			+ $("#startTime").datebox("getValue") + '&endTime='
			+ $("#endTime").datebox("getValue") + '&customerName='
			+ $("#customerName").combobox("getText") + '&productName='
			+ $("#productName").combobox("getText") + '&channelName='
			+ $("#channelName").combobox("getText") +'&deviceplate='
			+ $("#deviceplate").combobox("getText");
	$('#view').datagrid({
		url :url,
		 onLoadSuccess: function(){
	        	compute();
	        },
	 		loadFilter: function(data){
				$.each(data.rows,function(n,value){
					if (value.issubtract==1){
						value.issubtract='已核减';
					} else{
						value.issubtract='未核减';
					}
				});
			return data;
			}
	});
	
	self.setInterval("refresh",1000*60); 

}



function refresh(){
	$('#view').datagrid("reload");
}

function compute() {
    var rows = $('#view').datagrid('getRows')
  
    var effectTotal = 0;
    var incomeTotal = 0;
    var clickNumTotal=0;
    var activateTotal=0;
    var registerTotal=0;
    var consumeOneTotal=0;
    var consumeMoreTotal=0;
    var rechargeTotal=0;
    var creditTotal=0;
    var baddeptTotal=0;
    var costsTotal=0;
    var grossProfitTotal=0;
    var conversinRateTotal;
    var grossMarginTotal;
    var effectNumWithDeduplicationTotal=0;
    var deduplicationNumTotal=0;
    var deduplicationRate;
    var subtractNumTotal=0;
   var  channelExternalNumTotal=0;
   
    for (var i = 0; i < rows.length; i++) {
    	
    	effectTotal +=parseFloat( rows[i]['effectNum']);
    	incomeTotal += parseFloat(rows[i]['income']);
    	clickNumTotal+= parseFloat(rows[i]['clickNum']);
    	activateTotal+=parseFloat(rows[i]['activate']);
    	registerTotal+=parseFloat(rows[i]['register']);
    	consumeOneTotal+=parseFloat(rows[i]['consumeOne']);
    	consumeMoreTotal+=parseFloat(rows[i]['consumeMore']);
    	rechargeTotal+=parseFloat(rows[i]['recharge']);
    	creditTotal+=parseFloat(rows[i]['credit']);
    	baddeptTotal+=parseFloat(rows[i]['baddept']);
    	costsTotal+=parseFloat(rows[i]['costs']);
    	grossProfitTotal+=parseFloat(rows[i]['grossProfit']);
    	effectNumWithDeduplicationTotal+=parseFloat( rows[i]['effectNumWithDeduplication']);
    	deduplicationNumTotal+=parseFloat( rows[i]['deduplicationNum']);
    	if(rows[i]['subtractNum']!=null){
    	subtractNumTotal+=parseFloat( rows[i]['subtractNum']);
    	}
    	if(rows[i]['channelExternalNum']!=null){
    		channelExternalNumTotal+=parseFloat( rows[i]['channelExternalNum']);
    	}
    	
    }
   
    if(clickNumTotal==0){
    	conversinRateTotal="0.00%";
    }else{
    	conversinRateTotal=(effectTotal/clickNumTotal*100).toFixed(2)+"%";
    }
    if(effectTotal==0){
    	deduplicationRate="0.00%";
    }else{
    	deduplicationRate=(deduplicationNumTotal/effectTotal*100).toFixed(2)+"%";
    }
     if(incomeTotal==0){
    	 grossMarginTotal="0.00%";
     }else{
    	 grossMarginTotal=((incomeTotal-costsTotal)/incomeTotal*100).toFixed(2)+"%";
     }
   
    //新增一行显示统计信息
    $('#view').datagrid('appendRow', { rankingsTime: '<b>统计：</b>',
    
    	effectNum: effectTotal,
    	effectNumWithDeduplication:effectNumWithDeduplicationTotal,
    	deduplicationNum: deduplicationNumTotal,
    	deduplicationRate: deduplicationRate,
    	subtractNum: subtractNumTotal,
    	channelExternalNum:  channelExternalNumTotal,
    	clickNum:clickNumTotal,
    	activate:activateTotal,
    	register:registerTotal,
        consumeOne:consumeOneTotal,
        consumeMore:consumeMoreTotal,
        recharge:rechargeTotal,
        credit:creditTotal,
        baddept:baddeptTotal,
        grossProfit:grossProfitTotal.toFixed(2),
        costs:costsTotal.toFixed(2),
    	income: incomeTotal,
    	conversinRate:conversinRateTotal,
    	grossMargin:grossMarginTotal});
}
function getDetailLink(row,index) {
	var row = $('#view').datagrid('getSelected');
	if(row){
		var url = 'startTime='+ row.rankingsTime
		+ '&productName='+ row.productName
		+ '&channelName='+ row.channelName
		+ '&deviceplate='+$("#deviceplate").val()
		+ '&flag=true';
		
		window.location.href=projectPath+"/views/rankingsReport/rankingsReportDetails.jsp?"+url;
		
	}
	}
	var url;
	function toSubtract(){
		var row = $('#view').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', '核减信息');
			$('#fm').form('load', row);
		     if(row.issubtract='已核减'){
		    	 $("input[name='issubtract'][value=1]").attr("checked",true);
		     }else{
		    	 $("input[name='issubtract'][value=0]").attr("checked",true);
		     }
		     url=projectPath+"/modifySubtract.do?id="+row.id;
		}
		
	}
	function saveSubtract(){
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
	
	var recordUrl;
	function toRecord(){
		var row = $('#view').datagrid('getSelected');
		if (row) {
			getProductDetail(row);
		}
	}
	function  saveRecord(){
		$.ajax({
			            type: "post",
			            url: recordUrl,
			        async:false,
			            data: {
				accessPrice:$('#fm_record #accessPrice').val(),
				putPrice:$('#fm_record #putPrice').val(),
				effectNum:$('#fm_record #effectNum').val(),
				clickNum:$('#fm_record #clickNum').val()
		              	},
			            dataType: "json",
			            success: function (data) {
			                  if(data.success){
			                		$('#dlg_record').dialog('close'); // close the dialog
			    					$('#view').datagrid('reload'); // reload the user data
			                  }else{
			                	alert("出错了！");
			                  }
				            },
			            error: function (msg) {
				alert("出错了！");
			            }
			});
	
	
}
	
	function chechDetail(productIsBack,channelIsBack,accessPrice,putPrice,row){
			if(channelIsBack==1&&channelIsBack==1){
				alert("该产品和对应渠道都有后台，不可手动录入数据");
				return;
			}
			$('#dlg_record').dialog('open').dialog('setTitle', '核减信息');
			$('#fm_record').form('load', row);
			$('#fm_record #accessPrice').val(accessPrice);
			$('#fm_record #putPrice').val(putPrice);
			   if(productIsBack==1){
				   alert("该产品对应渠道没有后台，只需要录入渠道点击数");
				   $('#fm_record #effectNum').attr("readonly","readonly");
			   }else if(channelIsBack==1){
				   $('#fm_record #clickNum').attr("readonly","readonly");
			   }else{
				   alert("该产品没有后台，只需要录入效果数和渠道实际展现数");
				   $('#fm_record #channelExternalNum').removeAttr("readonly");  
				   $("#recordWithIssubtract").css("display","block");
				   if(row.issubtract='已核减'){
				    	 $("input[name='record_issubtract'][value=1]").attr("checked",true);
				     }else{
				    	 $("input[name='record_issubtract'][value=0]").attr("checked",true);
				     }
			   }
			     recordUrl=projectPath+"/addRecord.do?id="+row.id;
		
	}
	
	function  getProductDetail(row){
			$.ajax({
				            type: "post",
				            url: projectPath+"/getReportDataToRecord.do",
				        async:false,
				            data: {
					      productName:row.productName,
					      channelName:row.channelName,
					    	  deviceplate:row.deviceplate
			              	},
				            dataType: "json",
				            success: function (data) {
				                  if(data.success){
				                	  chechDetail(data.object.productIsback,data.object.channelIsback,data.object.accessPrice,data.object.putPrice,row)
				                  }else{
				                	  alert("出错了！找不到改产品信息！");
				                  }
					            },
				            error: function (msg) {
					  alert("出错了！系统错误！");
				            }
				});
		
		
	}
	
	

