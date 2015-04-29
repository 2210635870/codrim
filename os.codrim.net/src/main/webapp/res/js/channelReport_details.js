$(function() {
	initEffectData();
});

function initEffectData() {
	
	if (flag) {
		var Url = window.location.href;
		var s = Url.indexOf("?");
		var url = Url.substring(s);
		
		//添加导航内容
		$("#navDiv").append("<a id='navHistory' href='javascript:history.go(-1);'>渠道报表</a> > 明细");
		
		//初始化搜索内容
		$('#startTime').datebox('setValue',time);
		$('#endTime').datebox('setValue',time);
		getChannelName();
		$("#channelName").combobox("setValue",channelName);
        $("#effect").combobox("setValue",effect);
		$("#productName").attr("readonly","readonly");
		$("#productName").combobox({ 
	        valueField:'id',  
	        textField:'text',
	        editable:false
	    });
		$("#productName").combobox("setValue",productName);
		$("#productName").combobox('disable');
		//获取数据
		$("#view").datagrid({
			url : projectPath + '/getEffect.do' + url,
		});
	}else{
		initCurrentTime("startTime","endTime");
		initChannel('channelName');
		getProductName("/getProductName.do","/getChannelNameByProductName.do?productName=","productName","channelName");
	}
}
function submit() {
	 var url = projectPath + '/getEffect.do?'
		+ 'startTime='+$("#startTime").datebox("getValue")
		+ '&endTime='+ $("#endTime").datebox("getValue")
		+ '&productName='+ $("#productName").combobox("getText")
		+ '&channelName='+ $("#channelName").combobox("getText")
		+ '&effect='+ $("#effect").combobox("getValue");
	    
	 $.getJSON(url, function(result) {
			var columns = new Array();
			$.each(result.rows, function(i, o) {
				var effect;
				if(o.effect==1) effect="成功";
				else effect="失败";
				
				var column = {};
				column["id"] = o.id;
				column["effectTime"] = o.effectTime;
				column["channelName"] = o.channelName;
				column["deviceplate"] = o.deviceplate;
				column["mac"] = o.mac;
				column["idfa"] = o.idfa;
				column["udid"] = o.udid;
				column["imei"] = o.imei;
				column["effect"] = effect;
				column["remark"] = o.remark;
				
				columns.push(column);// 当需要formatter的时候自己添加就可以了,原理就是拼接字符串.
			});
			$('#view').datagrid({
				// title:' XXX公司价差补差分配表',
				// height:500,
				singleSelect : true,
				// url:url,
				frozenColumns : [ [ {
					field : 'id',
					title : '编号',
					width : 80,
					sortable : true,
					align : 'center'
				}, {
					field : 'effectTime',
					title : '日期',
					width : 150,
					sortable : true
				},{
					field : 'channelName',
					title : '渠道名',
					width : 150,
					sortable : true
				},{
					field : 'deviceplate',
					title : '设备系统',
					width : 150,
					sortable : true
				}, {
					field : 'mac',
					title : 'mac',
					width : 150,
					sortable : true
				}, {
					field : 'udid',
					title : 'udid',
					width : 150,
					sortable : true
				}, {
					field : 'imei',
					title : 'imei',
					width : 150,
					sortable : true
				}, {
					field : 'effect',
					title : '效果',
					width : 80,
					sortable : true
				}, {
					field : 'remark',
					title : '备注',
					width : 80,
					sortable : true
				} ] ],
				columns : [ columns ],
				
			    onClickCell:function(rowIndex,field,value){
			    	
				   } ,
				 
				rownumbers : true
			}).datagrid('loadData', columns);
			// .datagrid('loadData',result.bodys)
		});
	 
}
