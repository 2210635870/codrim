$(function(){
	initCurrentTime("startTime","endTime");
	getProductNameByChannelName();
	var url = "?productName=" +  $('#productName').combobox('getText') + "&startTime=&endTime=&deviceplate="+ $('#deviceplate').combobox('getText');

$('#view').datagrid({
         url : projectPath + '/getChannelReportWithExternal.do' +url
     });



	
	})
	
	function searchReport(){
	var url = "?productName=" +  $('#productName').combobox('getText') + 
	"&startTime="+$("#startTime").datebox("getValue")+"&endTime="+$("#endTime").datebox("getValue")+
	"&channelName="+channelName+"&deviceplate="+ $('#deviceplate').combobox('getText');
	
	$('#view').datagrid({
        url : projectPath + '/getChannelReportWithExternal.do' +url,
        onLoadSuccess: function(){
        	compute();
        }
		});
}
function compute() {
    var rows = $('#view').datagrid('getRows')
    var effectTotal = 0;
    var incomeTotal = 0;
    for (var i = 0; i < rows.length; i++) {
    	effectTotal +=parseFloat( rows[i]['effectNum']);
    	incomeTotal += parseFloat(rows[i]['income']);
    }
    //新增一行显示统计信息
    $('#view').datagrid('appendRow', { date: '<b>统计：</b>', effectNum: effectTotal, income: incomeTotal.toFixed(2) });
}
function getProductNameByChannelName(){
	$('#productName').combobox({
		editable : false,
		url : projectPath + '/getProductNameByCN.do?channelName='+channelName,
		valueField : 'text',
		textField : 'text',
		onLoadSuccess : function() { // 加载完成后,设置选中第一项
			var val = $(this).combobox("getData");
			if (val.length > 0) {
				$(this).combobox("setValue", val[0].text);
			}
		}
	});
}
