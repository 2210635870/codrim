<%@ page language="java" import="common.codrim.pojo.TbAdmin"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<jsp:include page="../common/meta.jsp" flush="true" />
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/res/uploadify/jquery.uploadify.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/res/uploadify/uploadify.css" />
<style type="text/css">
body {
	margin: 0;
}
</style>
<script type="text/javascript">
$(function(){
	datagrid();
	initUpload();
	getAllTask(null);
});
function datagrid(){
	$('#view').datagrid({
		url : '${ctx}/getImageUrlSettingList.do',
		loadFilter: function(data){
			$.each(data.rows,function(n,value){
			if (value.type==1){
				value.type='网页';
			} else if(value.type==2){
				value.type='任务';
			}
			if (value.url!=null&&value.url!=""){
				value.url='<img src="http://img.codrim.net/'+value.url+'"  style="width: 200px;height: 20px;"  id="rowUrl"/>';
			} 
			});
			return data;
		}
		});
}

function addImageUrlSetting(){
	var data =$('#view').datagrid('getData');
	if(data.rows.length>=4){
		alert("只能添加四个，可以前往更新！");
		return;
	}
	$('#dlg').dialog('open').dialog('setTitle','添加');
	clearForm();
	url = '${ctx}/addImageUrlSetting.do';
}
function clearForm(){
	$("#queue").html("");
	imageUrl=null;
	$("input[name='type'][value=1]").attr("checked",	true);
	$("#task_list").css("display","none");
	$("#skip_url").css("display","block");
}
function changeDiv(type){
	if(type==1){
		$("#task_list").css("display","none");
		$("#skip_url").css("display","block");
	}else{
		$("#task_list").css("display","block");
		$("#skip_url").css("display","none");
	}
	
	
}
var imageUrl=null;
var url;
function editImageUrlSetting(){
	var row = $('#view').datagrid('getSelected');
	if (row){
		clearForm();
		$('#dlg').dialog('open').dialog('setTitle','编辑');
		$("#queue").html(row.url);
		if(row.type=='任务'){
			getAllTask(row.taskId);
			$("input[name='type'][value=2]").attr("checked",	true);
			$("#task_list").css("display","block");
			$("#skip_url").css("display","none");
		}else{
			$("input[name='type'][value=1]").attr("checked",	true);
			$("#skipUrl").val(row.skipUrl);
			$("#skip_url").css("display","block");
			$("#task_list").css("display","none");
		}
		url = '${ctx}/editImageUrlSetting.do?id='+row.id;
	}else{
		alert("请选择进行设置！");
	}
}

function getAllTask(taskId){
	$('#taskId').combobox({
		editable : false,
		url :  '${ctx}/getTaskNamesAndIds.do',
		valueField : 'id',
		textField : 'text',
		onLoadSuccess : function() { // 加载完成后,设置选中第一项
			var val = $(this).combobox("getData");
			if (val.length > 0) {
				if(taskId!=null){
					$(this).combobox("setValue", taskId);
				}else{
					$(this).combobox("setValue", val[0].id);
				}
			}
		}
	});
	
	
}


function save(){
	$.ajax({
		            type: "post",
		            url: url,
		            dataType: "json",
		            data:{
		            	url:imageUrl,
		            	skipUrl:$("#skipUrl").val(),
		            	type:$('input[name="type"]:checked').val(),
		            	taskId:$("#taskId").combobox('getValue'),
		            	taskName:$("#taskId").combobox('getText')
		            },
		            success: function (data) {
			         if(data.success){
			        	 $('#dlg').dialog('close'); 
							$('#view').datagrid('reload');
			         }
			            },
		            error: function (msg) {
		alert("出错了！");
		            }
		});
	
	
	
}

function initUpload(){
	$("#url").uploadify(
			{
				'swf' :  '${ctx}/res/uploadify/uploadify.swf',
				'uploader' : "${ctx}/uploadImage.do",
				'method' : 'get',
				'auto' : true, // 选定文件后是否自动上传，默认false
				'fileSizeLimit' : '2MB', // 设置单个文件大小限制，单位为byte
				'fileTypeDesc' : '支持格式:jpg或jpeg或png', // 如果配置了以下的'fileExt'属性，那么这个属性是必须的
				'fileTypeExts' : '*.jpg;*.jpeg;*.png',// 允许的格式
				'buttonImage' :  '${ctx}/res/img/test.png',
				'width' : 70,
				'height' : 29,
			    'multi':false,
			    'removeCompleted'	: false,
			    'queueSizeLimit'  : 1,
			    'progressData' 	: 'all',// 'percentage''speed''all'//队列中显示文件上传进度的方式：all-上传速度+百分比，percentage-百分比，speed-上传速度
			    'queueID':'queue',
				'formData' : {
					'type':'yingjia_banner'
				},
				'overrideEvents' : [ 'onDialogClose', 'onUploadSuccess', 'onUploadError', 'onSelectError' ],
			    'onSelectError' : uploadify_onSelectError,
			    'onSelect' : uploadify_onSelect,
				'onUploadSuccess' : function(file, data, response) {
					var res=$.parseJSON(data);
					if(res.success){
						urlCallback(res,file);
					}else{
						alert("上传失败");
					}
					
				},
				'onUploadError' :uploadify_onUploadError
			});
	
}
function uploadify_onSelect(){
	$("#dlg #rowUrl").remove();
}
function urlCallback(res,file){
	imageUrl=res.object;
	var img='<img src="http://img.codrim.net/'+res.object.re+'"  style="width: 307px;height: 66px;">';
	var Id = file.id;
	$("#"+Id+" .fileName").css("display","none");
	$("#"+Id+" .data").css("display","none");
	$("#"+Id+" .uploadify-progress").css("display","none");
	$("#"+Id+" .cancel a").attr("href","javascript:delete('"+Id+"')");
	$("#"+Id+" .cancel").css("display","block");
	$("#"+Id).append(img);
}
function deleteIcon(id){
	$('#url').uploadify('cancel', id);
	imageUrl=null;
}

</script>




</head>
<body style="visibility: visible;">
	<table id="view" idField="id"   style="height:300px"class="easyui-datagrid" fitColumns="true" singleSelect="true"  toolbar="#tb">
		<thead>
			<tr>
				<th field="id" width="80" align="center">编号</th>
				<th field="url" align="center" width="120" editor="{type:'validatebox',options:{required:true}}">图片地址</th>
				<th field="type" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">跳转类型</th>
				<th field="skipUrl" align="center" width="100" editor="{type:'validatebox',options:{required:true}}">跳转地址</th>
				<th field="taskId" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">任务id</th>
				<th field="taskName" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">任务名</th>
			</tr>
		</thead>
	</table>

	<div id="tb">
		 <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addImageUrlSetting()" id="add">新增</a>
	    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editImageUrlSetting()" id="edit">编辑</a>
	</div>
	
	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons" modal="true">
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>上传图片:</label> <input id="url"  type="file">
				<div id="queue" class="uploadify-queue" >
</div>
			</div>
			<div class="fitem">
				<label>类型：:</label>
				<input name="type"  type="radio"  id="type_1" checked value="1"  style="width:auto" onclick="changeDiv(1)">网页
				<input name="type"  type="radio"  id="type_2"  value="2"   style="width:auto" onclick="changeDiv(2)">任务
			</div>
		 <div class="fitem"  id="skip_url" style="display:none">
				<label>跳转地址:</label> <input name="skipUrl" id="skipUrl" class="easyui-validatebox" required="true" validType='url'>
			</div>
			<div class="fitem"  id="task_list" style="display:none">
				<label>任务列表:</label> <input name="taskId" id="taskId"  type="text">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="save()">Save</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>
	
</body>
</html>