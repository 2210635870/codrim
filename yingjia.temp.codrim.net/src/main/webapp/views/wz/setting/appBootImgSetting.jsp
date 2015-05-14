<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp"%>

<html>
<head>
<jsp:include page="../common/meta.jsp" flush="true" />

<script type="text/javascript">
	
	$(function(){
		new uploadPreview({ UpBtn: "imgBtn", DivShow: "bootImgDiv", ImgShow: "bootImg" });
	});
	
	function saveSetting() {
		var $form = $(settingForm);
		$form.form("submit", {    
			url: '${ctx}/wz/setting/updateBootBackgroundImg.do',
		    onSubmit: function(){
		    	if(!$('#bootImg').attr('src') && !$('#imgBtn').val()) {
					$.messager.alert('保存失败', '启动页图片不能为空', 'warning');
					return false;
		    	}
		    	
		    	if(!$('#imgBtn').val()) {
					$.messager.alert('警告', '未做更改', 'warning');
					return false;
		    	}
		    	
				return true;
		    },    
		    success:function(data){    
		    	var result = eval('(' + data + ')');
				if (result.success) {
					$.messager.alert('保存成功', 'OK', 'info');
					window.location.href = ctx + "/wz/setting/initBootBackgroundImgSetting.do";
				} else {
					$.messager.alert('保存失败', result.msg, 'error');
				}
		    }    
		}); 
	}

</script>

</head>

<body style="visibility: visible;">

<div class="easyui-panel" title="启动页图片设置" style="width:500px">
	<div style="padding:10px 10px 10px 10px">
		<form:form commandName="bootImgSetting" id="settingForm" method="post" enctype="multipart/form-data" action="#">
			<table cellpadding="2">
				<tr>
					<td>
						<div id="bootImgDiv">
							<img id="bootImg" width="300" height="400" src="${bootImgSetting.bootImg}"  />
						</div>
						<input type="file" id="imgBtn" name="bootBackgroundImg" class="easyui-validatebox" />
					</td>
				</tr>
			</table>
						
			<br>
			<br>
				
			<div style="text-align:center;padding:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveSetting()">保存</a>
			</div>
		</form:form>
	</div>
</div>
	
</body>
</html>