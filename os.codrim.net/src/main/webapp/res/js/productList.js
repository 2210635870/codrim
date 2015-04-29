$(function(){
    $.extend($.fn.validatebox.defaults.rules, {     
        web : {     
            validator: function(value,param){     
            	if(param=='channelRankingNumber'){
            	if(value!=null&&value.length>0){
            		$("#fm #codrimPostBackLink").val("https://api.codrim.com/statis?source="+$('#channelName').val()+
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
            			$("#fm  #codrimPostBackLink").val("http://os.codrim.net/effect?"+"&pn="+$("#productName").val()+
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
    initUpload();
	//initProduct("", "searchForm #productName", "")
	initCurrentTime("startTime","endTime");
	getCustomerName(false,"","searchForm #customerName","");
	getCustomerName(false,"","createProduct #customerName","");
	getAllProductTypes();
	var url = "?evaluateResult=1" + "&startTime="
	+ $("#searchForm #startTime").datebox("getValue") + "&endTime="
	+ $("#searchForm #endTime").datebox("getValue")+
	"&productName="+$("#searchForm #productName").combobox("getText")+
	"&customerName="+$("#searchForm #customerName").combobox("getText");
	$('#view').datagrid({
	         url : projectPath + '/getProductDetails.do' +url,
	 		loadFilter: function(data){
				$.each(data.rows,function(n,value){
					if (value.runningStatus==1){
						value.runningStatus='运行';
					} else if(value.runningStatus==2){
						value.runningStatus='暂停';
					}else if(value.runningStatus==4){
						value.runningStatus='待定';
					}else{
						value.runningStatus='下线';
					}
					if (value.evaluateResult==1){
						value.evaluateResult='接入';
					} else if(value.evaluateResult==2){
						value.evaluateResult='不接入';
					}else{
						value.evaluateResult='待评估';
					}
					if (value.productIsback==1){
						value.productIsback='是';
					}else{
						value.productIsback='否';
					}
					if (value.channelIsback==1){
						value.channelIsback='是';
					}else{
						value.channelIsback='否';
					}
					
					if (value.settlementWay==1){
						value.settlementWay='CPA';
					} else if(value.settlementWay==2){
						value.settlementWay='CPS';
					}
					if (value.effectType==1){
						value.effectType='注册';
					} else if(value.effectType==2){
						value.effectType='激活';
					}else if(value.effectType==3){
						value.effectType='消费一次';
					}else if(value.effectType==4){
						value.effectType='消费两次以上';
					}else if(value.effectType==5){
						value.effectType='消费金额';
					}
					
				});
			return data;
			}
	     });
});
function getAllProductTypes() {
	$("#createProduct #productType").combobox({
		editable : false,
		url : projectPath + '/getAllProductTypes.do',
		valueField : 'text',
		textField : 'text',
		onLoadSuccess : function() { // 加载完成后,设置选中第一项
		var val = $(this).combobox("getData");
		if (val != null && val.length > 0) {
			$(this).combobox("setValue", val[0].text);
			}
		}
	});
	
	
	
	
}





var devicePlate;
var id;
var channelNameByedit=null;
/**
 * 获取渠道联系人下拉框 参数为是否有下级（渠道）级联
 * 
 * @param type
 */
function getAllChannelContactName(name,method,channelContactName,channelName) {
		$("#"+channelContactName+"").combobox({
			editable : false,
			url : projectPath + '/getAllChannelContactName.do',
			valueField : 'text',
			textField : 'text',
			onLoadSuccess : function() { // 加载完成后,设置选中第一项
				var val = $(this).combobox("getData");
				if (val != null && val.length > 0) {
					if(name!=""&&name!=null){
						$(this).combobox("setValue", name);
						reloadChannel(name,method,channelName);
					}else{
						$(this).combobox("setValue", val[0].text);
						reloadChannel(val[0].text,method,channelName);
					}
				}
				if (val == null) {
					$(this).combobox("clear");
				}
			},
			onSelect : function(rec) {
				reloadChannel(rec.text,method,channelName);
			}
		});

}
function changeEffectType(type){
	if(type==1){
	$("#effect_type_1").css("display","block");
	$("#effect_type_2").css("display","none");
	$("input[name='effectType'][value=1]").attr("checked",
			true);
	}else{
		$("#effect_type_1").css("display","none");
		$("#effect_type_2").css("display","block");
		$("input[name='effectType'][value=3]").attr("checked",
				true);
	}
}
/**
 * 初始化渠道
 */
function initChannel(channelName,personInCharge) {
	$("#"+channelName+"").combobox({
		editable : false,
		url : projectPath + '/getChannelName.do',
		valueField : 'text',
		textField : 'text',
		onLoadSuccess : function() { // 加载完成后,设置选中第一项
			var val = $(this).combobox("getData");
			if (val != null && val.length > 0) {
				if(channelNameByedit!=""&&channelNameByedit!=null){
					$(this).combobox("setValue", channelNameByedit);
					if(personInCharge!=""){
						getPersonInCharge(channelNameByedit,personInCharge);
					}
				}else{
					$(this).combobox("setValue", val[0].text);	
					if(personInCharge!=""){
						getPersonInCharge(val[0].text,personInCharge);
					}
				}
			}
			if (val == null) {
				$(this).combobox("clear");
			}
		},
		onSelect : function(rec) {
			if(personInCharge!=""){
				getPersonInCharge(rec.text,personInCharge);
			}
		}

	});
}

function editProductCreate(){
	var row = $('#view').datagrid('getSelected');
	if (row) {
		$('#dlg').dialog('open').dialog('setTitle', '添加产品渠道信息');
		$('#fm').form('load', row);
		id=row.id;
		channelNameByedit=row.channelName;
		 initChannel("fm #channelName","fm #personInCharge");
			//getAllChannelContactName(row.channelContactName,"/getChannelNameByContactName.do?contactName=","fm #channelContactName","fm #channelName");
		if(row.productProperty=='普通'){
			$("input[name='productProperty'][value=普通]").attr("checked",true);
		}else if(row.productProperty=='联运'){
			$("input[name='productProperty'][value=联运]").attr("checked",true);
		}else{
			$("input[name='productProperty'][value=普通]").attr("checked",true);
		}
		if(row.productIsback=='是'){
			$("input[name='productIsback'][value=1]").attr("checked",true);
		}else{
			$("input[name='productIsback'][value=0]").attr("checked",true);
		}
		if(row.channelIsback=='是'){
			$("input[name='channelIsback'][value=1]").attr("checked",true);
		}else{
			$("input[name='channelIsback'][value=0]").attr("checked",true);
		}
		
		if(row.deviceplate=='ios'){
			$("#appId_ios").css("display","block");
			$("#link_ios").css("display","block");
			$("#channelRankingNumber_android").css("display","none");
			devicePlate='ios';
		}
		if(row.deviceplate=='android'){
			$("#appId_ios").css("display","none");
			$("#link_ios").css("display","none");
			$("#channelRankingNumber_android").css("display","block");
			devicePlate='android';
		}
		if(row.runningStatus=='下线'){
			$("input[name='runningStatus'][value=3]").attr("checked", true);
		}else if(row.runningStatus=='暂停'){
			$("input[name='runningStatus'][value=2]").attr("checked", true);
			}else if(row.runningStatus=='4'){
			$("input[name='runningStatus'][value=4]").attr("checked", true);
			}else{
				$("input[name='runningStatus'][value=1]").attr("checked", true);
			}
		if(row.settlementWay=='CPA'){
			$("#effect_type_1").css("display","block");
			$("#effect_type_2").css("display","none");
			$("input[name='settlementWay'][value=1]").attr("checked",
				true);
		}else if(row.settlementWay=='CPS'){
			$("#effect_type_1").css("display","none");
			$("#effect_type_2").css("display","block");
			$("input[name='settlementWay'][value=2]").attr("checked",
					true);
		}else{
			$("#effect_type_1").css("display","block");
		$("#effect_type_2").css("display","none");
			$("input[name='settlementWay'][value=1]").attr("checked",
					true);
		}
		if (row.effectType=='注册'){
			$("input[name='effectType'][value=1]").attr("checked",
					true);
		} else if(row.effectType=='激活'){
			$("input[name='effectType'][value=2]").attr("checked",
					true);
		}else if(row.effectType=='消费一次'){
			$("input[name='effectType'][value=3]").attr("checked",
					true);
		}else if(row.effectType=='消费两次以上'){
			$("input[name='effectType'][value=4]").attr("checked",
					true);
		}else if(row.effectType=='消费金额'){
			$("input[name='effectType'][value=5]").attr("checked",
					true);
		}else{
			$("input[name='effectType'][value=1]").attr("checked",
					true);
		}	
		
	}else{
		alert("请选择！");
	}
}
function submit(){
	var url = "?evaluateResult=1" + "&startTime="
	+ $("#searchForm #startTime").datebox("getValue") + "&endTime="
	+ $("#searchForm #endTime").datebox("getValue")+
	"&productName="+$("#searchForm #productName").combobox("getText")+
	"&customerName="+$("#searchForm #customerName").combobox("getText");
	$('#view').datagrid({
	         url : projectPath + '/getProductDetails.do' +url
	     });
}

function saveProductCreate(){
	$.ajax({
		            type: "post",
		            url: projectPath+"/modifyProductDetail.do",
		            data: {
			id:id,
			deviceplate:devicePlate,
			productName:$("#fm #productName").val(),
			customerName:$("#fm #customerName").val(),
			productProperty:$('input[name="productProperty"]:checked').val(),
			channelName:$('#fm #channelName').combobox("getText"),
			accessPrice:$("#fm #accessPrice").val(),
			putPrice:$("#fm #putPrice").val(),
			personInCharge:$("#fm #personInCharge").val(),
			runningStatus:$('input[name="runningStatus"]:checked').val(),
			appId:$("#fm #appid").val(),
			productIsback:$('input[name="productIsback"]:checked').val(),
			channelIsback:$('input[name="channelIsback"]:checked').val(),
			channelRankingNumber:$("#fm #channelRankingNumber").val(),
			productTrackingLink:$("#fm #productTrackingLink").val(),
				codrimTrackingLink:$("#fm #codrimTrackingLink").val(),
					channelPostBackLink:$("#fm #channelPostBackLink").val(),		
					codrimPostBackLink:$("#fm #codrimPostBackLink").val(),
					settlementWay:$('input[name="settlementWay"]:checked').val(),
					effectType:$('input[name="effectType"]:checked').val(),
			startTime:$("#fm #startTime").datebox("getValue"),
			endTime:$("#fm #endTime").datebox("getValue")
	              	},
		            dataType: "json",
		            success: function (data) {
			         if(data.success){
			        		$('#dlg').dialog('close');		// close the dialog
							$('#view').datagrid('reload');	
			         }
			            },
		            error: function (msg) {
		alert("出错了！");
		            }
		});

}