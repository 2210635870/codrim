$(function(){
	initCurrentTime("startTime","endTime");
	initChannel('channelName');
	initProduct('/getChannelNameByProductName.do?productName=',"productName","channelName");
	getCustomerName(true,'/getProductNameByCustomerName.do?customerName=',"customerName","productName");
	
});


function submit(){
	$.ajax({
		            type: "post",
		            url: projectPath+"/getAllReport.do",
		            data: {
			         startTime:$("#startTime").datebox("getValue") ,
			         endTime:$("#endTime").datebox("getValue"),
			         customerName:$("#customerName").combobox("getText"),
			         productName: $("#productName").combobox("getText"),
			         channelName:$("#channelName").combobox("getText")
	              	},
		            dataType: "json",
		            success: function (data) {
			         if(data.success){
			        	 var res=data.object;
			        	$("#allIncome").html(res.allIncome);
			        	$("#allCosts").html(res.allCosts);
			        	$("#allGrossProfit").html(res.allGrossProfit);
			        	$("#generalAllRunning").html(res.generalAllRunning);
			        	$("#generalAllPause").html(res.generalAllPause);
			        	$("#generalAllOffline").html(res.generalAllOffline);
			        	$("#generalAllIncome").html(res.generalAllIncome);
			        	$("#generalAllCosts").html(res.generalAllCosts);
			        	$("#generalAllGrossProfit").html(res.generalAllGrossProfit);
			        	$("#generalAllGrossMargin").html(res.generalAllGrossMargin);
			        	$("#interAllRunning").html(res.interAllRunning);
			        	$("#interAllPause").html(res.interAllPause);
			        	$("#interAllOffline").html(res.interAllOffline);
			        	$("#interAllIncome").html(res.interAllIncome);
			        	$("#interAllCosts").html(res.interAllCosts);
			        	$("#interAllGrossProfit").html(res.interAllGrossProfit);
			        	$("#interAllGrossMargin").html(res.interAllGrossMargin);
			         }else{
			        	 alert("出错了！");
			        	 }
			            },
		            error: function (msg) {
		     	 alert("出错了！");
		            }
		});
	
	
}
