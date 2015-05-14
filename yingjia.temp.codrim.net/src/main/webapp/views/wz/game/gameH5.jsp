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
.datagrid-row {
height: 90px;
}
</style>
<script type="text/javascript">
$(function(){
	datagrid();
	initUpload();
});

function datagrid(){
	$('#view').datagrid({
		url : '${ctx}/getGameH5List.do',
		loadFilter: function(data){
			$.each(data.rows,function(n,value){
			if (value.status==1){
				value.status='上线';
			} else if(value.status==3){
				value.status='下线';
			}
			if (value.gameIcon!=null&&value.gameIcon!=""){
				value.gameIcon='<img src="http://img.codrim.net/'+value.gameIcon+'"  style="width: 100px;height: 80px;"  id="game_url"/>';
			} 
			
			value.operate='<span><a href="javascript:void(0);" onclick="changeStatus(1,'+value.id+')">上线</a> </span><span> <a href="javascript:void(0);" onclick="changeStatus(3,'+value.id+')">下线</a></span>';
			});
			return data;
		}
		});
}

function addH5(){
	$('#dlg').dialog('open').dialog('setTitle','添加');
	clearForm();
	url = '${ctx}/addH5.do';
}
function clearForm(){
	$('#fm').form('clear');
	$("#queue").html("");
	imageUrl=null;
	$("input[name='weight'][value=0]").attr("checked",	true);
}
function changeStatus(status,id){
	$.ajax({
		            type: "post",
		            url: "${ctx}/editH5.do?id="+id,
		            dataType: "json",
		            data:{
		            	status:status
		            },
		            success: function (data) {
			         if(data.success){
							$('#view').datagrid('reload');
			         }
			            },
		            error: function (msg) {
		alert("出错了！");
		            }
		});
	
	
}
var imageUrl=null;
var url;
function editH5(){
	var row = $('#view').datagrid('getSelected');
	if (row){
		clearForm();
		$('#dlg').dialog('open').dialog('setTitle','编辑');
		$('#fm').form('load', row);
		$("#queue").html(row.gameIcon);
		$("input[name='weight'][value="+row.weight+"]").attr("checked",	true);
		url = '${ctx}/editH5.do?id='+row.id;
	}else{
		alert("请选择进行设置！");
	}
}



function save(){
	$.ajax({
		            type: "post",
		            url: url,
		            dataType: "json",
		            data:{
		            	gameName:$("#gameName").val(),
		            	gameIcon:imageUrl,
		            	gameUrl:$("#gameUrl").val(),
		            	gameDesc:$("#gameDesc").val(),
		            	playingNum:$("#playingNum").numberbox('getValue'),
		            	company:$("#company").val(),
		            	startTime:$("#startTime").datebox('getValue'),
		            	endTime:$("#endTime").datebox('getValue'),
		            	weight:$('input[name="weight"]:checked').val()
		            },
		            success: function (data) {
			         if(data.success){
			        	 $('#dlg').dialog('close'); 
							$('#view').datagrid('reload');
							clearForm();
			         }
			            },
		            error: function (msg) {
		alert("出错了！");
		            }
		});
	
	
	
}

function initUpload(){
	$("#gameIcon").uploadify(
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
					'type':'h5_icon'
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
	$("#dlg #game_url").remove();
}
function urlCallback(res,file){
	imageUrl=res.object;
	var img='<img src="http://img.codrim.net/'+res.object+'"  style="width: 307px;height: 66px;">';
	var Id = file.id;
	$("#"+Id+" .fileName").css("display","none");
	$("#"+Id+" .data").css("display","none");
	$("#"+Id+" .uploadify-progress").css("display","none");
	$("#"+Id+" .cancel a").attr("href","javascript:delete('"+Id+"')");
	$("#"+Id+" .cancel").css("display","block");
	$("#"+Id).append(img);
}
function deleteIcon(id){
	$('#gameIcon').uploadify('cancel', id);
	imageUrl=null;
}

</script>




</head>
<body style="visibility: visible;">
	<table id="view" idField="id"  class="easyui-datagrid" fitColumns="true" singleSelect="true"  toolbar="#tb" pagination="true"pageSize=10 >
		<thead>
			<tr>
				<th field="id" width="80" align="center">编号</th>
				<th field="gameIcon" align="center" width="120" editor="{type:'validatebox',options:{required:true}}">游戏图标</th>
				<th field="gameName" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">游戏名字</th>
				<th field="gameUrl" align="center" width="100" editor="{type:'validatebox',options:{required:true}}">游戏地址</th>
				<th field="gameDesc" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">游戏描述</th>
				<th field="company" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">所属公司</th>
				<th field="status" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">状态</th>
				<th field="startTime" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">开始时间</th>
				<th field="endTime" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">结束时间</th>
				<th field="weight" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">显示权重</th>
				<th field="playingNum" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">玩家人数</th>
				<th field="operate" align="center" width="80" editor="{type:'validatebox',options:{required:true}}">操作</th>
			</tr>
		</thead>
	</table>

	<div id="tb">
		 <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addH5()" id="add">新增</a>
	    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editH5()" id="edit">编辑</a>
	</div>
	
	<div id="dlg" class="easyui-dialog"
		style="width: 500px; height: 400px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons" modal="true">
		<form id="fm" method="post" novalidate>
					<div class="fitem">
				<label>游戏名字：:</label> <input id="gameName"  name="gameName"type="text"  class="easyui-validatebox"  validType='length[1,20]'>
</div>
			<div class="fitem"  style="width:350px;height:160px">
				<label>上传图标:</label> <input id="gameIcon"  type="file">
				<div id="queue" class="uploadify-queue"   ></div>
</div>
<div class="fitem"  >
				<label>游戏地址:</label> 
				<input name="gameUrl" id="gameUrl" class="easyui-validatebox"  validType='url'/>
			</div>
		<div class="fitem">
				<label>所属公司：:</label> <input id="company"  name="company"  type="text"  class="easyui-validatebox" validType='length[1,20]'/>
</div>
<div class="fitem"  >
				<label>游戏介绍:</label>
				 	<textarea id="gameDesc" name="gameDesc" 
							rows="10" cols="40" style="overflow-x:hidden;" 
							resize="none"  autofocus="autofocus" wrap="physical" class="easyui-validatebox" align="left" validtype="length[0,300]" >
							</textarea>
			</div>
			
			<div class="fitem"  >
				<label>玩家人数:</label> <input name="playingNum" id="playingNum"
							class="easyui-numberbox easyui-validatebox" min="0" precision="0" missingMessage="请输入数字" /> 
			</div>
			
			
							
			<div class="fitem"  >
				<label>开始时间:</label><input id="startTime" name="startTime"
							class="easyui-datebox" editable="false"
							data-options="formatter:myformatter,parser:myparser" />
			</div>
			
						
			<div class="fitem"  >
				<label>结束时间:</label> 	<input id="endTime" name="endTime" class="easyui-datebox"
							editable="false"
							data-options="formatter:myformatter,parser:myparser" />
			</div>			
										
							
			<div class="fitem">
				<label>显示权重:</label>
				<input name="weight"  type="radio"   checked value="0"  style="width:auto" >0
				<input name="weight"  type="radio" value="1"   style="width:auto" >1
				<input name="weight"  type="radio" value="2"   style="width:auto" >2
				<input name="weight"  type="radio" value="3"   style="width:auto" >3
				<br>
				<span style="color:red">代表在客户端显示顺序(权重相同默认按添加时间排序)，默认权重为0，依次递增</span>
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