<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp"%>

<html>
<head>
<jsp:include page="../common/meta.jsp" flush="true" />

<script type="text/javascript">
	
	function saveSetting() {
		$.messager.confirm("更新版本提醒", "确认发布该版本吗?<br>注意: 只有当版本号发生变化时才表示新版本的发布！", function(r){
    		if (r) {
    			$('#settingForm').form("submit", {
    			    url: '${ctx}/wz/setting/updateAppUpgradeSetting.do',    
    			    onSubmit: function(){
    					return $(this).form('validate');  
    			    },    
    			    success:function(data){    
    			    	var result = eval('(' + data + ')');
    					if (result.success) {
    						$.messager.alert('保存设置成功', '保存设置成功！', 'info', function(){
    							location.href = "${ctx}/wz/setting/initAppUpgradeSetting.do";
    						});
    					} else {
    						$.messager.alert('保存设置失败', result.msg, 'error');
    					}
    			    }    
    			});
    		}
    	});
	}

</script>

</head>

<body style="visibility: visible;">

<div class="easyui-panel" title="版本设置" style="width:800px">
	<div style="padding:10px 10px 10px 10px">
		
		<form:form commandName="auSetting" id="settingForm" method="post" enctype="multipart/form-data" >
			
			<table cellpadding="2">
				<tr>
					<td class="field-name">版本号:</td>
					<td>
						<form:input path="apkVersion" id="apkVersionInput" class="easyui-validatebox" required="required"  />&nbsp;
						<a href="${imgRoot}${auSetting.apkUrl}">[下载链接]</a>
					</td>
				</tr>
				<tr>
					<td class="field-name">发布时间:</td>
					<td>
						<fmt:formatDate value="${auSetting.publishDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr>
					<td class="field-name">应用包:</td>
					<td>
						<input name="appFile" type="file" id="appFileInput" />
					</td>
				</tr>
				<tr>
					<td class="field-name">更新说明:</td>
					<td>
						<form:textarea path="upgradeInfo" id="upgradeInfoInput" maxlength="200" cols="60" rows="6"  /> (200字)
					</td>
				</tr>
				<tr>
					<td class="field-name">强制更新:</td>
					<td>
						是<form:radiobutton path="isMandatory" value="1"/> &nbsp;
						否<form:radiobutton path="isMandatory" value="0"/>
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