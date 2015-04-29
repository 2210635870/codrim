$(function() {
	initEffectRankingsData();
});

function initEffectRankingsData() {
	if (flag) {
		var Url = window.location.href;
		var s = Url.indexOf("?");
		//window.location.search
		var url = Url.substring(s);//得到地址栏参数
	
		//添加导航内容
		$("#navDiv").append("<a id='navHistory' href='javascript:history.go(-1);'>冲榜报表</a> > 明细").css({'width':'160px','height':'45px'});
		
		//初始化搜索内容
		$('#startTime').datebox('setValue',time);
		$('#endTime').datebox('setValue',time);
		getChannelName();
		$("#channelName").combobox("setValue",channelName);
        $("#effect").combobox("setValue",effect);
		//$("#productName").attr("readonly","readonly");
		$("#productName").combobox({ 
	        valueField:'id',  
	        textField:'text',
	        editable:false
	    });
		$("#productName").combobox("setValue",productName);
		$("#productName").combobox('disable');
		//获取数据
		$("#view").datagrid({
			url : projectPath + '/getEffectRankings.do' + url,
		});
	}else{
		initCurrentTime("startTime","endTime");
		initChannel('channelName');
		getProductName("/getProductName.do","/getChannelNameByProductName.do?productName=","productName","channelName");
	}
}
function submit() {
  var url = projectPath + '/getEffectRankings.do?'
	+ 'startTime='+$("#startTime").datebox("getValue")
	+ '&endTime='+ $("#endTime").datebox("getValue")
	+ '&productName='+ $("#productName").combobox("getText")
	+ '&channelName='+ $("#channelName").combobox("getText")
	+ '&deviceplate='+ $("#deviceplate").val();
  //获取数据
	$("#view").datagrid({
		url : url,
		 onLoadSuccess: function(){
	        	compute();
	        }
	});
	setInterval("refresh",1000*60); 
}
function refresh(){
	$('#view').datagrid("reload");
}
function compute() {
    var rows = $('#view').datagrid('getRows');
   
    var clickNumTotal=0;
    var effectTotal = 0;
    var registerTotal=0;
    var consumeOneTotal=0;
    var consumeMoreTotal=0;
    var rechargeTotal=0;
    var creditTotal=0;
    var baddeptTotal=0;
    
    for (var i = 0; i < rows.length; i++) {
    	clickNumTotal+= parseFloat(rows[i]['clickNum']);
    	effectTotal +=parseFloat( rows[i]['effect']);
    	registerTotal+=parseFloat(rows[i]['register']);
    	consumeOneTotal+=parseFloat(rows[i]['consumeOne']);
    	consumeMoreTotal+=parseFloat(rows[i]['consumeMore']);
    	rechargeTotal +=parseFloat(rows[i]['recharge']);
    	creditTotal+=parseFloat(rows[i]['credit']);
    	baddeptTotal+=parseFloat(rows[i]['baddept']);
    	
    }
    
    //新增一行显示统计信息
    $('#view').datagrid('appendRow', { actionTime: '<b>统计：</b>',
    	clickNum:clickNumTotal,
    	effect: effectTotal,
    	register:registerTotal,
        consumeOne:consumeOneTotal,
        consumeMore:consumeMoreTotal,
        recharge:rechargeTotal,
        credit:creditTotal,
        baddept:baddeptTotal});
}

