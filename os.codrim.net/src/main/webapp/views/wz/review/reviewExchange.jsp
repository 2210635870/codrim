<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp"%>

<html>
<head>
<jsp:include page="../common/meta.jsp" flush="true" />

<script type="text/javascript">
	
	function reviewExchange(result) {
		$('#exchangeReviewForm').form("submit", {    
		    url: '${ctx}/wz/review/reviewExchange.do?result='+result,    
		    onSubmit: function(){
				return $(this).form('validate');  
		    },
		    success:function(data){    
		    	var result = eval('(' + data + ')');
				if (result.success) {
					$.messager.alert('审核成功', '审核兑换成功！', 'info', function(){
						location.href = "${ctx}/views/wz/review/exchangeRecordList.jsp";
					});
				} else {
					$.messager.alert('审核失败', result.msg, 'error');
				}
		    }    
		});
	}
	

</script>

</head>

<body style="visibility: visible;">

<div class="easyui-panel" title="审核兑换" style="width:800px">
	<div style="padding:10px 10px 10px 10px">
		
		<form:form commandName="exchangeRecord" id="exchangeReviewForm" method="post" action="/wz/review/reviewExchange.do">
			<input type="hidden" id="exchangeRate" value="${settingCurrency.exchangeRate}" />
			<input type="hidden" id="groupCodrimPercent" value="${settingCurrency.groupCodrimPercent}" />
			<input type="hidden" id="userCodrimPercent" value="${settingCurrency.userCodrimPercent}" />
			
			<fieldset>
				<legend>用户信息</legend>
				<table cellpadding="5">
					<tr>
						<td class="field-name">用户名:</td>
						<td>
							${exchangeRecord.username}
						</td>
					</tr>
					<tr>
						<td class="field-name">用户手机号:</td>
						<td>
							${exchangeRecord.userPhone}
						</td>
					</tr>
					<tr>
						<td class="field-name">用户邮箱:</td>
						<td>
							${exchangeRecord.userEmail}
						</td>
					</tr>
				</table>
			</fieldset>
			
			<fieldset>
				<legend>兑换信息</legend>
				<table cellpadding="5">
					<tr>
						<td class="field-name">兑换日期:</td>
						<td>
							<fmt:formatDate value="${exchangeRecord.exchangeDate}" pattern="yyyy-MM-dd"/>
						</td>
					</tr>
					<tr>
						<td class="field-name">兑换选项:</td>
						<td>
							<c:choose>
								<c:when test="${exchangeRecord.exchangeType eq 1}">
									支付宝
								</c:when>
								<c:otherwise>
									手机话费
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td class="field-name">
							<c:choose>
								<c:when test="${exchangeRecord.exchangeType eq 1}">
									支付宝账号:
								</c:when>
								<c:otherwise>
									充值手机号:
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${exchangeRecord.exchangeType eq 1}">
									${exchangeRecord.zfbAccount}
								</c:when>
								<c:otherwise>
									${exchangeRecord.phoneNumber}
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td class="field-name">兑换金额:</td>
						<td>
							${exchangeRecord.exchangeMoney}元
						</td>
					</tr>
				</table>
			</fieldset>
			
			<br>
			<table cellpadding="5">
				<tr>
					<td class="field-name">备注:</td>
					<td>
						<form:textarea path="reviewRemark" id="reviewRemarkInput" maxlength="20" cols="60" rows="6"  
								class="easyui-validatebox" />
					</td>
				</tr>
			</table>
			
			<br>
			<div style="text-align:center;padding:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="reviewExchange('1')">通过</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="reviewExchange('0')">不通过</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="location.href='${ctx}/views/wz/review/exchangeRecordList.jsp'">返回</a>
			</div>
		</form:form>
	</div>
</div>
	
</body>
</html>