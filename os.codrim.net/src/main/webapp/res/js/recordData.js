$(function(){
	//initCurrentTime("startTime","endTime");
	initChannel('channelName');
	initProduct('/getChannelNameByProductName.do?productName=',"productName","channelName");
	getCustomerName(true,'/getProductNameByCustomerName.do?customerName=',"customerName","productName");
	$.extend($.fn.validatebox.defaults.rules, {
		subtract : {
			validator : function(value, param) {
				if(value!=null&&value!=""){
					$("#channelExternalNum").val($("#"+param).val()-value);
					return true;
				}else{
					return false;
				}
			},
			message : '请输入数字！'
		}
	});
$("#startTime").datebox({
	required:true,
	formatter:myformatter,
		parser:myparser,
		editable:false,
	onSelect:function(date){
		checkDate(date);
	}
});
initCurrentTime("startTime","endTime");
})

function checkDate(date){
	//alert(date);
}

var customerSettledNum;//客户结算数
var channelFeedBackNum;//渠道反馈数
var channelSettledNum;//渠道结算数
var accessPrice;//接入单价
var putPrice;//放出单价
var subtractChannelProportion;//核减渠道比例
var productConversionRate;//产品转化率
var settledIncome;//结算收入
var settledCosts;//结算成本
var grossProfit;//毛利
var grossMargin;//毛利率
 
 
  var saveFlag=false;
  function calculate(){
	  saveFlag=true;
	  var flag = false;
	  $('#center_middle input').each(function () {
	    if ($(this).attr('required') || $(this).attr('validType')) {
	    if (!$(this).validatebox('isValid')) {
	        flag = false;
	        return;
	    }else{
	    	flag=true;
	    	}
	    }
	   })
	  if(flag){
		  customerSettledNum=$("#customerSettledNum").val();//客户结算数
		  channelFeedBackNum=$("#channelFeedBackNum").val();//渠道反馈数
		  channelSettledNum=$("#channelSettledNum").val();//渠道结算数
		  accessPrice=$("#accessPrice").html();//接入单价
		  putPrice=$("#putPrice").html(); //放出单价
		  
		  //计算
		  subtractChannelProportion=(channelFeedBackNum-channelSettledNum)/channelFeedBackNum;
		  subtractChannelProportion=subtractChannelProportion.toFixed(2)+"%";
		  $("#subtractChannelProportion").html(subtractChannelProportion);//核减渠道比例
		  productConversionRate=customerSettledNum/channelFeedBackNum;
		  productConversionRate=productConversionRate.toFixed(2)+"%";
		  $("#productConversionRate").html(productConversionRate);//产品转化率
		  settledIncome=customerSettledNum*accessPrice
		  settledIncome=settledIncome.toFixed(2);
		  $("#settledIncome").html(settledIncome);//结算收入
		  settledCosts=accessPrice*channelSettledNum;  //渠道结算单价   暂为渠道结算数
		  settledCosts=settledCosts.toFixed(2);
		  $("#settledCosts").html(settledCosts);//结算成本
		  grossProfit=settledIncome-settledCosts;
		  grossProfit=grossProfit.toFixed(2);
		  $("#grossProfit").html(grossProfit);//毛利
		  grossMargin=grossProfit/settledIncome;
		  grossMargin=grossMargin.toFixed(2)+"%";
		  $("#grossMargin").html(grossMargin);//毛利率
	  }
	  
		 
		  
  }
  
  function saveRecordData(){
		if(!saveFlag){
			alert("请先计算");
			return;
		}
	
	  $.ajax({
            type: "POST",
            url: projectPath+"/saveRecordData.do",
            data: {
				customerName:$("#customerName").combobox("getText"),
				productName:$("#productName").combobox("getText"),
				channelContactName:$("#channelContactName").combobox("getText"),
				channelName:$("#channelName").combobox("getText"),
				productType:$("#productType").html(),
				accessPrice:accessPrice,
				putPrice:putPrice,
				customerSettledNum:customerSettledNum,
				channelFeedBackNum:channelFeedBackNum,
				channelSettledNum:channelSettledNum,
				subtractChannelProportion:subtractChannelProportion,
				productConversionRate:productConversionRate,
				settledIncome:settledIncome,
				settledCosts:settledCosts,
				grossProfit:grossProfit,
				grossMargin:grossMargin
			},
		    dataType: "json",
		    success: function (data) {
			         if(data){
			        	alert("保存成功");
			         }
			            },
		            error: function (msg) {
		                cancel();
		   $.messager.alert("保存失败！");
		            }
		});
		
  }