$(function() {
	initCurrentTime("startTime","endTime");
	initChannel('channelName');
	initProduct('/getChannelNameByProductName.do?productName=',"productName","channelName");
	getCustomerName(true,'/getProductNameByCustomerName.do?customerName=',"customerName","productName");
	});

	function submit() {
		var url= projectPath + '/getChannelReport.do?startTime='+$("#startTime").datebox("getValue")+
				'&endTime='+$("#endTime").datebox("getValue")+
				'&customerName='+$("#customerName").combobox("getText")+
				'&productName='+$("#productName").combobox("getText")+
				'&channelName='+$("#channelName").combobox("getText");
		$('#view').datagrid({
			url :url,
		});
	}
	function getDetailLink() {
		var row = $('#view').datagrid('getSelected');
		if(row){
			var url = 'startTime='+ row.statisticsTime
			+ '&productName='+ row.productName
			+ '&channelName='+ row.channelName
			+ '&effect=-1'
			+ '&flag=true';
		window.location.href=projectPath+"/views/channelReport/channelReport_details.jsp?"+url;
		}
	}
