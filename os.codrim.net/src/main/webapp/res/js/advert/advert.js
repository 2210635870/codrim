$(function (){
	initCurrentTime("startTime","endTime");
	initProduct();
	getCustomerName(true,"/getProductNameByCustomerName.do?customerName=","customerName","productName");
})
// 产品初始化
function initProduct() {
	$("#productName").combobox({
		editable : false,
		valueField : 'id',
		textField : 'text',
		onLoadSuccess : function() { // 加载完成后,设置选中第一项
			var val = $(this).combobox("getData");
			if (val != null && val.length > 0) {
				$(this).combobox("setValue", val[0].id);
			}
			if (val == null) {
				$(this).combobox("clear");
			}
		}
	});
}




function getAdverts(){
	$('#view').datagrid({
		url : projectPath+'/getAdvertsByProductId.do?id='+$("#productName").combobox("getValue"),
		loadFilter: function(data){
			$.each(data.rows,function(n,value){
				if (value.evaluateStatus==0){
					value.evaluateStatus='渠道询价';
				} else if(value.evaluateStatus==1){
					value.evaluateStatus='运营评估';
				}else if(value.evaluateStatus==2){
					value.evaluateStatus='商务评估';
				}else {
					value.evaluateStatus='渠道询价';
				}
				if(value.operateEvaluateResult==1){
					value.operateEvaluateResult="接入"
				}else if(value.operateEvaluateResult==0){
					value.operateEvaluateResult="不接入"
				}else{
					value.operateEvaluateResult="";
				}
				if(value.finalEvaluateResult==1){
					value.finalEvaluateResult="投放"
				}else if(value.finalEvaluateResult==0){
					value.finalEvaluateResult="不投放"
				}else{
					value.finalEvaluateResult="";
				}
				
				
			
				if (value.runningStatus==3){
					value.runningStatus='下线';
				} else if(value.runningStatus==1){
					value.runningStatus='运行';
				}else if(value.runningStatus==2){
					value.runningStatus='暂停';
				}else{
					value.runningStatus='新加入';
				}
				if (value.waysOfCooperation==1){
					value.waysOfCooperation='普通';
				} else if(value.waysOfCooperation==2){
					value.waysOfCooperation='联运';
				}
				
				if (value.settlementWay==1){
					value.settlementWay='CPA';
				} else if(value.settlementWay==2){
					value.settlementWay='CPS';
				}
				if (value.effectType==1){
					value.effectType='注册';
					value.advertName='注册';
				} else if(value.effectType==2){
					value.effectType='激活';
					value.advertName='激活';
				}else if(value.effectType==3){
					value.effectType='消费一次';
					value.advertName='消费一次';
				}else if(value.effectType==4){
					value.effectType='消费两次以上';
					value.advertName='消费两次以上';
				}else if(value.effectType==5){
					value.effectType='消费金额';
					value.advertName='消费金额';
				}
				if (value.effectTypeBack==1){
					value.effectTypeBack='注册';
				} else if(value.effectTypeBack==2){
					value.effectTypeBack='激活';
				}else if(value.effectTypeBack==3){
					value.effectTypeBack='消费一次';
				}else if(value.effectTypeBack==4){
					value.effectTypeBack='消费两次以上';
				}else if(value.effectTypeBack==5){
					value.effectTypeBack='消费金额';
				}
			});
		return data;
		}
	});
	
	
}

