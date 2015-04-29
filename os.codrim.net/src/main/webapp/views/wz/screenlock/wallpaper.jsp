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
	new uploadPreview({ UpBtn: "add_wallpaper", DivShow: "addWallpaperDiv", ImgShow: "addWallpaperImg" });
	new uploadPreview({ UpBtn: "edit_wallpaper", DivShow: "editWallpaperDiv", ImgShow: "editWallpaperImg" });
	
	$('#view').datagrid({
		url: '${ctx}/screenlock/getWallpaperList.do'
	});
	
	$('#dlg-add').dialog({
		onClose: dialogClose
	});
	$('#dlg-edit').dialog({
		onClose: dialogClose
	});
});

function addWallpaperSetting(){
	$('#dlg-add').dialog('open');
}

function editWallpaper(index){
	var row = $('#view').datagrid('getData').rows[index];
	var dlg = $('#dlg-edit');
	dlg.find('img').attr('src', '${imgRoot}'+row.wallpaper);
	dlg.find('[name=id]').val(row.id);
	dlg.dialog('open');
}

function save(formId, url, $dialog) {
	var $form = $(formId);
	$form.form("submit", {    
		url: url,
	    onSubmit: function(){
			return $(this).form('validate');  
	    },    
	    success:function(data){    
	    	var result = eval('(' + data + ')');
			if (result.success) {
				$dialog.dialog('close');
				$('#view').datagrid('reload');
			} else {
				$.messager.alert('保存失败', result.msg, 'error');
			}
	    }    
	}); 
}

function deleteWallpape(index){
	var row = $('#view').datagrid('getData').rows[index];
	$.ajax({
		type: "post",
		url: "${ctx}/screenlock/deleteWallpaper.do",
		data: {
			id: row.id
		},
	    dataType: "json",
		success: function (data) {
			if(data.success) {
				$('#view').datagrid('reload');
			}else {
				$.messager.alert('保存失败', result.msg, 'error');
			}
		},
		error: function (msg) {
			$.messager.alert('保存失败', msg, 'error');
		}
	});
}

function newsWallpaperFormatter(value, row, index) {
	return "<img width=\"120\" height=\"150\" src=\"${imgRoot}"+row.wallpaper+"\" />";
}

function actionColumnFormatter(value, row, index) {
	var actionStr = '<a href="javascript:void(0)" onclick="editWallpaper('+index+')">编辑</a>'+
		'|<a href="javascript:void(0)" onclick="deleteWallpape('+index+')">删除</a>';
	return actionStr;
}

function dialogClose() {
	$(this).find('img').attr('src', '');
	$(this).find('[name=wallpaperFile]').val('');
	$(this).find('[name=id]').val('');
}

</script>

</head>
<body style="visibility: visible;">
	<div id="tb">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addWallpaperSetting()" id="add">新增</a>
	</div>
	<table id="view" idField="id" rownumbers="true" pagination="true" singleSelect="true" pageSize=10 toolbar="#tb" >
		<thead>
			<tr>
				<th field="id" width="80" align="center">编号</th>
				<th field="wallpaper" align="center" width="200" formatter="newsWallpaperFormatter">壁纸图片</th>
				<th field="addDate" align="center" width="300" formatter="actionColumnFormatter">操作</th>
			</tr>
		</thead>
	</table>
	
	<div id="dlg-add" class="easyui-dialog" title="添加"
		style="width: 400px; height: 550px; padding: 10px 20px" closed="true" modal="true">
		<form:form id="addWallpaperForm" method="post" enctype="multipart/form-data">
			<div class="fitem">
				<label>壁纸图片:</label>
				<div id="addWallpaperDiv"><img id="addWallpaperImg" width="300" height="350" /></div>
				<input name="wallpaperFile" type="file" id="add_wallpaper" class="easyui-validatebox" required="required"/>
			</div>	
			
			<div style="text-align:center;padding:5px" id>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="save('#addWallpaperForm', '${ctx}/screenlock/addWallpaper.do', $('#dlg-add'))">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg-add').dialog('close')">取消</a>
			</div>
		</form:form>
	</div>
	
	<div id="dlg-edit" class="easyui-dialog" title="编辑"
		style="width: 400px; height: 550px; padding: 10px 20px" closed="true" modal="true">
		<form:form id="editWallpaperForm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" />
			<div class="fitem">
				<label>壁纸图片:</label>
				<div id="editWallpaperDiv"><img id="editWallpaperImg" width="300" height="350" /></div>
				<input name="wallpaperFile" type="file" id="edit_wallpaper" class="easyui-validatebox"/>
			</div>	
			
			<div style="text-align:center;padding:5px" id>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="save('#editWallpaperForm', '${ctx}/screenlock/editWallpaper.do', $('#dlg-edit'))">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg-edit').dialog('close')">取消</a>
			</div>
		</form:form>
	</div>
</body>
</html>