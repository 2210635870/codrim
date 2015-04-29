<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" import="common.codrim.pojo.TbAdmin"%>
<%@ include file="../common/common-taglib.jsp"%>

<html>
<head>
<jsp:include page="../common/common.jsp" flush="true" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/common.css">

<script type="text/javascript">
</script>

</head>

<body style="visibility: visible;">
<div class="fbody">

	<form:form commandName="dailyReport" id="dailyReportForm" method="post" action="/dailyReport/add.do">

		<table id="dailyReportTb" class="tb-form" >
			<tr>
				<td class="field-name">所属范畴:</td>
				<td>
					<form:select path="jobCategory" class="easyui-combobox" id="jobCategorySelect" required="required" cssStyle="width: 200px;">
						<form:option value="1">渠道</form:option>
						<form:option value="2">商务</form:option>
						<%-- <form:option value="3">运营</form:option>
						<form:option value="9">其他</form:option> --%>
					</form:select>
				</td>
			</tr>
			<tr>
				<td class="field-name">跟进性质:</td>
				<td>
					<form:select path="followType" class="easyui-combobox" id="followTypeSelect" required="required" cssStyle="width: 200px;"></form:select>
				</td>
			</tr>
			<tr id="custTr">
				<td class="field-name">客户名称:</td>
				<td>
					<form:select path="customerId" class="easyui-combobox" id="custSelect" required="required" cssStyle="width: 200px;"></form:select>
				</td>
			</tr>
			<tr>
				<td class="field-name">联系人:</td>
				<td>
					[姓名] <span id="contact_name">${dailyReport.contactName}</span><br>
					[职务] <span id="contact_position">${dailyReport.contactPosition}</span><br>
					[电话] <span id="contact_phone">${dailyReport.contactPhone}</span><br>
					[邮箱] <span id="contact_email">${dailyReport.contactEmail}</span><br>
					[微信/QQ] <span id="contact_wx">${dailyReport.contactWx}</span>
				</td>
			</tr>
			<tr>
				<td class="field-name">跟进情况:</td>
				<td>
					<form:textarea path="followDetail" id="followDetailInput" maxlength="200" cols="60" rows="6"  
								class="easyui-validatebox" required="required" /> (200字)
				</td>
			</tr>
			<tr>
				<td class="field-name">遇到问题:</td>
				<td>
					<form:textarea path="followProblem" id="followProblemInput" maxlength="200" cols="60" rows="6"  
								class="easyui-validatebox" required="required" /> (200字)
				</td>
			</tr>
			<tr>
				<td class="field-name">需要的支持:</td>
				<td>
					<form:textarea path="support" id="supportInput" maxlength="200" cols="60" rows="6"  
								class="easyui-validatebox" required="required" /> (200字)
				</td>
			</tr>
			<tr>
				<td class="field-name">建议和想法:</td>
				<td>
					<form:textarea path="suggestion" id="suggestionInput" maxlength="200" cols="60" rows="6"  
								class="easyui-validatebox" required="required" /> (200字)
				</td>
			</tr>
			<tr>
				<td class="field-name">跟进人:</td>
				<td>
					${dailyReport.followUsername}
				</td>
			</tr>
		</table>
	</form:form>
	
	<br>
	<div style="text-align:center;padding:5px" class="dialog-button">
		<a id="saveBtn" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="saveReport();">保存</a>
	</div>
</div>

</body>
</html>