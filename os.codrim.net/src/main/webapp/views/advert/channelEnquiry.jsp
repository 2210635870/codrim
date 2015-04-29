<%@ page language="java" import="common.codrim.pojo.TbAdmin"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script type="text/javascript">
var rowId=0;
var advertId=0;
function toChannelEnquiry(){
	deleteAll();
	var row= $('#view').datagrid('getSelected');
	if(row){
		if(row.evaluateStatus=='商务评估'||row.evaluateStatus=='运营评估'){
			alert("已进行评估环节，不能再次询价！");
			return ;
		}
		initProductWIthAdvert("dlg_channel");
		advertId=row.id;
	$('#dlg_channel').dialog('open').dialog('setTitle', '渠道询价');	
	$('#dlg_channel').window('center');

	initViewAdvert(row);
	}else{
		alert("请先选择广告！");
	}
	}

	
	
	
	
function more(){
	$('#channelEnquiry').datagrid('insertRow',{
		index: rowId,
		row:{
			channel: '<input id="channelName'+rowId+'" name="channelName">',
			isAccept: '<input type="radio" name="isAccept'+rowId+'" value="1" checked/>接入 <input type="radio" name="isAccept'+rowId+'" value="0"/>不接入 ',
			price: '<input type="text" name="price" id="price'+rowId+'" style="width:70px" />',
			remark:'<textarea name="remark"  id="remark'+rowId+'" cols="20" rows="3"></textarea>',
			personInCharge:'<span id="personInCharge'+rowId+'"></span>'
		}

	});
	$('#price'+rowId).numberbox({
	    min:0.1,
	    precision:1,
	    required:true
	});
	
	initChannel('channelName'+rowId,'personInCharge'+rowId);
	rowId++;
}
function deleteAll(){
	rowId=0;
	  var item = $('#channelEnquiry').datagrid('getRows');
      if (item) {
          for (var i = item.length - 1; i >= 0; i--) {
              var index = $('#channelEnquiry').datagrid('getRowIndex', item[i]);
              $('#channelEnquiry').datagrid('deleteRow', index);
          }
      }
      $('#channelEnquiry').datagrid('loadData', { total: 0, rows: [] });
}
function deleteRow(){
	var row= $('#channelEnquiry').datagrid('getSelected');
	if(row){
	     var index = $('#channelEnquiry').datagrid('getRowIndex', row);
         $('#channelEnquiry').datagrid('deleteRow', index);
	}else{
		alert("请先选择广告！");
	}
	
}



function initChannel(channelName,personInCharge) {
	$("#"+channelName+"").combobox({
		editable : true,
		url : projectPath + '/getChannelName.do',
		valueField : 'id',
		textField : 'text',
		width:'60px',
		filter:function(q,row){ 
			var opts=$(this).combobox("options"); 
			//return row[opts.textField].indexOf(q)==0;// 
			return row[opts.textField].indexOf(q)>-1;//将从头位置匹配改为任意匹配 
			},
		formatter:function(row){ 
			var opts=$(this).combobox("options"); 
			return row[opts.textField]; 
			},
		onLoadSuccess : function() { // 加载完成后,设置选中第一项
			var val = $(this).combobox("getData");
			if (val != null && val.length > 0) {
					$(this).combobox("setValue", val[0].id);	
					if(personInCharge!=""){
						getPersonInCharge(val[0].text,personInCharge);
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
function addAdvertChannelEnquiry(){
	var jsonString='[';
	var json;
	 var item = $('#channelEnquiry').datagrid('getRows');
     if (item) {
         for (var i = item.length - 1; i >= 0; i--) {
             var index = $('#channelEnquiry').datagrid('getRowIndex', item[i]);
             jsonString=jsonString+'{"advertid":'+advertId+',"channelid":'+$("#channelName"+index).combobox('getValue')
            	 +',"personInCharge":"'+$("#personInCharge"+index).html()+'","isaccept":'+$('input[name="isAccept'+index+'"]:checked').val()
            	 +',"price":'+$("#price"+index).numberbox("getValue")+',"remark":"'+$("#remark"+index).val()+'"},'
         }
	jsonString=jsonString.substring(0,jsonString.length-1);
    jsonString=jsonString+']';
	var obj= JSON.parse(jsonString); 
     }else{
    	 alert("没有选择渠道询价，不需要保存！");
     }
		$.ajax({
			            type: "post",
			            url:  projectPath + '/addChannelEnquiry.do',
			            dataType: "json",
			          data:{
			        	  json:jsonString
			          },
			            success: function (data) {
				         if(data.success){
				        	 $('#dlg_channel').dialog('close');
				        	 deleteAll();
				        	 alert("添加成功");
				        		$('#view').datagrid('reload');	
				         }else{
				        	 alert("添加失败");
				         }
				            },
			            error: function (msg) {
			alert("出错了！");
			            }
			});
}

</script>

<div  id="dlg_channel"  class="easyui-dialog"
		style="width: 700px; height: 500px; padding: 10px 20px" closed="true"
		buttons="#dlg_channel-buttons" modal="true" pagination="true">
		<jsp:include page="./viewProductWithAdvert.jsp"></jsp:include>
			<div  class="easyui-accordion" style="overflow-y:auto;overflow-x:hidden;fit:true"  data-options="border:false,animate:true,multiple:true" >
			<jsp:include page="./viewAdvert.jsp"></jsp:include>
		</div>
			<br>
			<br>
			<br>
					<table id="channelEnquiry"  class="easyui-datagrid"  rownumbers="true"    singleSelect="true" toolbar="#channelEnquiry_tb" fitColumns="true" >
		<thead>
		<tr>
  <th field="channel"  align="center" width="70px">渠道</th>
  <th field="isAccept" align="center" width="130px">是否接入</th>
   <th field="price" align="center" width="150px">渠道反馈单价</th>
    <th field="remark" align="center" width="150px">备注</th>
    <th field="personInCharge" align="center" width="90px">负责人</th>
</tr>
</thead>
		 </table>
		 
		<div id="channelEnquiry_tb">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="more()">添加</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="deleteRow()">删除</a>
	</div>
	
	
</div>
<div id="dlg_channel-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="submit" onclick="addAdvertChannelEnquiry()">保存</a> 
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_channel').dialog('close')">Cancel</a>
	</div>
</div>
