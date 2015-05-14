<%@ page language="java" import="common.codrim.pojo.TbAdmin"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp"%>
<html>
<head>
<jsp:include page="../common/meta.jsp" flush="true" />
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/uploadify/jquery.uploadify.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/uploadify/uploadify.css" />
<style type="text/css">
	body {
		margin: 0;
	}
</style>
<script type="text/javascript">
$(function(){
	new uploadPreview({ UpBtn: "add_news_screenlock", DivShow: "addNewsScreenDiv", ImgShow: "addNewsScreenImg" });
	new uploadPreview({ UpBtn: "edit_news_screenlock", DivShow: "editNewsScreenDiv", ImgShow: "editNewsScreenImg" });
	$('#view').datagrid({
		url: '${ctx}/screenlock/getNewsList.do'
	});
	$('#dlg-add').dialog({
		onClose: dialogClose
	});
	$('#dlg-edit').dialog({
		onClose: dialogClose
	});
});

function addNewsSetting(){
	$('#dlg-add').dialog('open');
}

function editNewsSetting(index){
	var row = $('#view').datagrid('getData').rows[index];
	if (row){
		var dlg = $('#dlg-edit');
		dlg.find('img').attr('src', '${imgRoot}'+row.newsScreenLock);
		dlg.find('[name=id]').val(row.id);
		dlg.find('[name=title]').val(row.title);
		dlg.find('[name=newsUrl]').val(row.newsUrl);
		dlg.dialog('open');
	}
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

function deleteNews(index) {
	$.messager.confirm("删除新闻", "确认删除该新闻吗?", function(r){
		if (r) {
			var row = $('#view').datagrid('getData').rows[index];
			$.ajax({
				type: "post",
				url: "${ctx}/screenlock/deleteNews.do",
				data: {
					id: row.id
				},
			    dataType: "json",
				success: function (data) {
					if(data.success) {
						$('#view').datagrid('reload');
					}else {
						$.messager.alert('删除失败', result.msg, 'error');
					}
				},
				error: function (msg) {
					$.messager.alert('删除失败', msg, 'error');
				}
			});
		}
	});
}

function newsScreenlockFormatter(value, row, index) {
	return "<img width=\"100\" height=\"120\" src=\"${imgRoot}"+row.newsScreenLock+"\" />";
}

function dialogClose() {
	$(this).find('img').attr('src', '');
	$(this).find('[name=id]').val('');
	$(this).find('[name=newsScreenlockFile]').val('');
	$(this).find('[name=title]').val('');
	$(this).find('[name=newsUrl]').val('');
}

function actionColumnFormatter(value, row, index) {
	var actionStr = '<a href="javascript:void(0)" onclick="editNewsSetting('+index+')">编辑</a>'+
		'|<a href="javascript:void(0)" onclick="deleteNews('+index+')">删除</a>';
	return actionStr;
}


</script>

</head>
<body style="visibility: visible;">
	<div id="tb">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addNewsSetting()" id="add">新增</a>
	</div>
	<table id="view" idField="id" rownumbers="true" pagination="true" singleSelect="true" pageSize=10 toolbar="#tb" >
		<thead>
			<tr>
				<th field="id" width="80" align="center">编号</th>
				<th field="newsScreenLock" align="center" width="200" formatter="newsScreenlockFormatter">新闻图片</th>
				<th field="title" align="center" width="400">新闻标题</th>
				<th field=newsUrl align="center" width="600">新闻链接</th>
				<th field=addDate align="center" width="200" formatter="actionColumnFormatter">操作</th>
			</tr>
		</thead>
	</table>
	
	<div id="dlg-add" class="easyui-dialog" title="添加"
		style="width: 400px; height: 550px; padding: 10px 20px" closed="true" modal="true">
		<form:form commandName="news" id="addNewsForm" method="post" enctype="multipart/form-data">
			<div class="fitem">
				<label>新闻图片:</label>
				<div id="addNewsScreenDiv"><img id="addNewsScreenImg" width="300" height="350" /></div>
				<input name="newsScreenlockFile" type="file" id="add_news_screenlock" class="easyui-validatebox" required="required"/>
			</div>	
			
			<div class="fitem">
				<label>新闻标题：</label>
				<input name="title" type="text" class="easyui-validatebox" id="news_title" required="required" maxlength="10"/>
			</div>
			
			<div class="fitem">
				<label>新闻链接：</label>
				<input name="newsUrl" type="text" class="easyui-validatebox" required="required" validType="url" />
			</div>
			
			<div style="text-align:center;padding:5px" id>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="save('#addNewsForm', '${ctx}/screenlock/addNews.do', $('#dlg-add'))">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg-add').dialog('close')">取消</a>
			</div>
		</form:form>
	</div>
	
	<div id="dlg-edit" class="easyui-dialog" title="编辑"
		style="width: 400px; height: 550px; padding: 10px 20px" closed="true" modal="true">
		<form:form commandName="news" id="editNewsForm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" />
			<div class="fitem">
				<label>新闻图片:</label>
				<div id="editNewsScreenDiv"><img id="editNewsScreenImg" width="300" height="350" /></div>
				<input name="newsScreenlockFile" type="file" id="edit_news_screenlock" class="easyui-validatebox"/>
			</div>	
			
			<div class="fitem">
				<label>新闻标题：</label>
				<input name="title" type="text" class="easyui-validatebox" required />
			</div>
			
			<div class="fitem">
				<label>新闻链接：</label>
				<input name="newsUrl" type="text" class="easyui-validatebox" required validType="url" />
			</div>
			
			<div style="text-align:center;padding:5px" id>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="save('#editNewsForm', '${ctx}/screenlock/editNews.do', $('#dlg-edit'))">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg-edit').dialog('close')">取消</a>
			</div>
		</form:form>
	</div>
</body>
</html>