<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp"%>

<html>
<head>
<jsp:include page="../common/meta.jsp" flush="true" />

<script type="text/javascript">
	
	function saveSetting() {
		$('#settingForm').form("submit", {
		    url: '${ctx}/wz/setting/updateCurrencySetting.do',    
		    onSubmit: function(){
				return $(this).form('validate');  
		    },    
		    success:function(data){    
		    	var result = eval('(' + data + ')');
				if (result.success) {
					$.messager.alert('保存设置成功', '保存设置成功！', 'info', function(){
						location.href = "${ctx}/wz/setting/initCurrencySetting.do";
					});
				} else {
					$.messager.alert('保存设置失败', result.msg, 'error');
				}
		    }    
		});
	}
	
	function reversePercent(percentInput, reverseId) {
		if (isNaN($(percentInput).val())) {
			return false;
		}
		
		var percent = parseInt($(percentInput).val());
		$("#"+reverseId).text(100-percent);
	}

</script>

</head>

<body style="visibility: visible;">

<div class="easyui-panel" title="货币设置" style="width:800px">
	<div style="padding:10px 10px 10px 10px">
		
		<form:form commandName="setting" id="settingForm" method="post" >
			
			<fieldset>
				<legend>汇率设置</legend>
				<form:input path="exchangeRate" id="exchangeRateInput" class="easyui-validatebox" required="required" /> 金币 = 1元
			</fieldset>
			
			<br>
			
			<fieldset>
				<legend>Codrim与团队分成设置</legend>
				<table cellpadding="2">
					<tr>
						<td class="field-name">Codrim收益:</td>
						<td>
							<form:input path="groupCodrimPercent" id="groupCodrimPercentInput" onchange="reversePercent(this, 'groupSelfPercent')"
								class="easyui-validatebox" required="required" validType="range[1,100]" /> %
						</td>
					</tr>
					<tr>
						<td class="field-name">团队收益:</td>
						<td><span id="groupSelfPercent">${100 - setting.groupCodrimPercent}</span> %</td>
					</tr>
				</table>
			</fieldset>
			
			<br>

			<fieldset>
				<legend>Codrim与普通用户分成设置</legend>
				<table cellpadding="2">
					<tr>
						<td class="field-name">Codrim收益:</td>
						<td>
							<form:input path="userCodrimPercent" id="userCodrimPercentInput" onchange="reversePercent(this, 'userSelfPercent')"
								class="easyui-validatebox" required="required" validType="range[1,100]" /> %
						</td>
					</tr>
					<tr>
						<td class="field-name">用户收益:</td>
						<td><span id="userSelfPercent">${100 - setting.userCodrimPercent}</span> %</td>
					</tr>
				</table>
			</fieldset>

			<br>

			<fieldset>
				<legend>用户每日签到奖励设置</legend>
				<form:input path="signInReward" id="signInRewardInput" class="easyui-validatebox" required="required" /> 元
			</fieldset>			
						
			<br>
			
			<fieldset>
				<legend>查看更多单价设置</legend>
				<form:input path="seePriceCost" id="seePriceCostInput" class="easyui-validatebox" required="required" /> 元
			</fieldset>		
			
			<br>
			
			<fieldset>
				<legend>邀请好友安装客户端获奖励设置</legend>
				<table cellpadding="2">
					<tr>
						<td class="field-name">邀请人:</td>
						<td>
							<form:input path="userInviterReward" id="userInviterRewardInput" 
								class="easyui-validatebox" required="required" /> 元
						</td>
					</tr>
					<tr>
						<td class="field-name">被邀请人:</td>
						<td>
							<form:input path="userInviteesReward" id="userInviteesRewardInput" 
								class="easyui-validatebox" required="required" /> 元
						</td>
					</tr>
				</table>
			</fieldset>
			
			<br>
			
			<fieldset>
				<legend>邀请好友加入团队获奖励设置</legend>
				<table cellpadding="2">
					<tr>
						<td class="field-name">邀请人:</td>
						<td>
							<form:input path="groupInviterReward" id="groupInviterRewardInput" 
								class="easyui-validatebox" required="required" /> 元
						</td>
					</tr>
					<tr>
						<td class="field-name">被邀请人:</td>
						<td>
							<form:input path="groupInviteesReward" id="groupInviteesRewardInput" 
								class="easyui-validatebox" required="required" /> 元
						</td>
					</tr>
				</table>
			</fieldset>
			
			<br>
			
			<fieldset>
				<legend>抽奖设置</legend>
				<table cellpadding="2">
					<tr>
						<td class="field-name">每次抽奖花费:</td>
						<td>
							<form:input path="cjCost" id="cjCostInput" 
								class="easyui-validatebox" required="required" /> 元
						</td>
					</tr>
					<tr>
						<td class="field-name">抽奖时间间隔:</td>
						<td>
							<form:input path="cjInterval" id="cjIntervalInput" 
								class="easyui-validatebox" required="required" /> 分钟
						</td>
					</tr>
				</table>
				
				<fieldset>
					<legend>奖项权重设置</legend>
					<table cellpadding="2">
						<tr>
							<td class="field-name">奖品一:</td>
							<td>
								<form:input path="cjPrize1" id="cjPrize1Input" 
									class="easyui-validatebox" required="required" /> 元
								&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td class="field-name">权重:</td>
							<td>
								<form:input path="cjPrize1Prob" id="cjPrize1ProbInput" 
									class="easyui-validatebox" required="required" /> %
							</td>
						</tr>
						<tr>
							<td class="field-name">奖品二:</td>
							<td>
								<form:input path="cjPrize2" id="cjPrize2Input" 
									class="easyui-validatebox" required="required" /> 元
								&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td class="field-name">权重:</td>
							<td>
								<form:input path="cjPrize2Prob" id="cjPrize2ProbInput" 
									class="easyui-validatebox" required="required" /> %
							</td>
						</tr>
						<tr>
							<td class="field-name">奖品三:</td>
							<td>
								<form:input path="cjPrize3" id="cjPrize3Input" 
									class="easyui-validatebox" required="required" /> 元
								&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td class="field-name">权重:</td>
							<td>
								<form:input path="cjPrize3Prob" id="cjPrize3ProbInput" 
									class="easyui-validatebox" required="required" /> %
							</td>
						</tr>
						<tr>
							<td class="field-name">奖品四:</td>
							<td>
								<form:input path="cjPrize4" id="cjPrize4Input" 
									class="easyui-validatebox" required="required" /> 元
								&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td class="field-name">权重:</td>
							<td>
								<form:input path="cjPrize4Prob" id="cjPrize4ProbInput" 
									class="easyui-validatebox" required="required" /> %
							</td>
						</tr>
						<tr>
							<td class="field-name">奖品五:</td>
							<td>
								<form:input path="cjPrize5" id="cjPrize5Input" 
									class="easyui-validatebox" required="required" /> 元
								&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td class="field-name">权重:</td>
							<td>
								<form:input path="cjPrize5Prob" id="cjPrize5ProbInput" 
									class="easyui-validatebox" required="required" /> %
							</td>
						</tr>
						<tr>
							<td class="field-name">恭喜发财:</td>
							<td></td>
							<td class="field-name">权重:</td>
							<td>
								<form:input path="cjNoneProb" id="cjNoneProbInput" 
									class="easyui-validatebox" required="required" /> %
							</td>
						</tr>
					</table>
				</fieldset>
			</fieldset>				
			
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