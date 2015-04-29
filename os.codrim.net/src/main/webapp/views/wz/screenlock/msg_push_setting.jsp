<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp"%>

<html>
<head>
<jsp:include page="../common/meta.jsp" flush="true" />

<script type="text/javascript">

function saveSettingInfo() {
	
	var signinVal = $('#signinInput').val();
	var readInfoVal = $('#readInfoInput').val();
	var msgDailyVal = $('#msgDailyNumInput').val();
	
	if( signinVal <= 0 ) {
		$.messager.alert('', '签到金币必须大于0', 'warning');
		return;
	}
	if( readInfoVal <= 0 ) {
		$.messager.alert('', '阅读资讯金币必须大于0', 'warning');
		return;
	}
	if( msgDailyVal <= 0 ) {
		$.messager.alert('', '资讯每日推送数量必须大于0', 'warning');
		return;
	}
	
	var settingJson = [{
		name: '${msgPushSetting.signin}',
		value: $('#signinInput').val()
	},{
		name: '${msgPushSetting.readInfo}',
		value: $('#readInfoInput').val()
	},{
		name: '${msgPushSetting.msgDailyNum}',
		value: $('#msgDailyNumInput').val()
	}];
	
	$.ajax({
		type: "post",
		url: "${ctx}/screenlock/editMsgPushSetting.do",
		data: {
			settingJson: JSON.stringify(settingJson)
		},
	    dataType: "json",
		success: function (data) {
			if(data.success) {
				$.messager.alert('保存成功', '', 'info');
			}else {
				$.messager.alert('保存失败', '', 'error');
			}
		},
		error: function (msg) {
			$.messager.alert('保存失败', msg, 'error');
		}
	});
}	
</script>

</head>

<body style="visibility: visible;">

<div class="easyui-panel" title="推送设置" style="width:800px">
	<div style="padding:10px 10px 10px 10px">
		
		<form:form commandName="msgPushSetting" id="settingForm" method="post" enctype="multipart/form-data" action="#">
			<fieldset>
				<legend>设置信息</legend>
				<table cellpadding="5">
					<tr>
						<td class="field-name">${msgPushSetting.signin}:</td>
						<td>
							<input name="signinVal" maxlength="10" class="easyui-numberbox" id="signinInput" required="required" precision="2"
								value="${msgPushSetting.signinVal}" />
						</td>
					</tr>
					 <tr>
						<td class="field-name">${msgPushSetting['readInfo']}:</td>
						<td>
							<input name="readInfoVal" maxlength="10" class="easyui-numberbox" id="readInfoInput" required="required" precision="2"
								value="${msgPushSetting.readInfoVal}" />
						</td>
					</tr>
					
					<tr>
						<td class="field-name">${msgPushSetting.msgDailyNum}:</td>
						<td>
							<input name="msgDailyNumVal" maxlength="10" class="easyui-numberbox" id="msgDailyNumInput" required="required"
								value="${msgPushSetting.msgDailyNumVal}" />
						</td>
				</table>
			</fieldset>
			
			<div style="text-align:center;padding:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveSettingInfo()">编辑</a>
			</div>
		</form:form>
	</div>
</div>
	
</body>
</html>