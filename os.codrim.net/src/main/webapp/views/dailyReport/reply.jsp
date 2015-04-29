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
	<div class="ftitle">日报信息</div>
	<table id="dailyReportTb" class="tb-form" >
		<tr>
			<td class="field-name">所属范畴:</td>
			<td>
				<c:choose>
					<c:when test="${reportReply.jobCategory eq 1}">渠道</c:when>
					<c:when test="${reportReply.jobCategory eq 2}">商务</c:when>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td class="field-name">跟进性质:</td>
			<td>
				<c:choose>
					<c:when test="${reportReply.followType eq 1}">新拓展</c:when>
					<c:when test="${reportReply.followType eq 2}">投放</c:when>
					<c:when test="${reportReply.followType eq 3}">维护</c:when>
				</c:choose>
			</td>
		</tr>
		<tr id="custTr">
			<td class="field-name">客户名称:</td>
			<td>
				<c:choose>
					<c:when test="${reportReply.jobCategory eq 1}">${reportReply.channelName}</c:when>
					<c:otherwise>${reportReply.customerName}</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td class="field-name">联系人:</td>
			<td>
				[姓名] <span id="contact_name">${reportReply.contactName}</span><br>
				[职务] <span id="contact_position">${reportReply.contactPosition}</span><br>
				[电话] <span id="contact_phone">${reportReply.contactPhone}</span><br>
				[邮箱] <span id="contact_email">${reportReply.contactEmail}</span><br>
				[微信/QQ] <span id="contact_wx">${reportReply.contactWx}</span>
			</td>
		</tr>
		<tr>
			<td class="field-name">跟进情况:</td>
			<td>
				${reportReply.followDetail}
			</td>
		</tr>
		<tr>
			<td class="field-name">遇到问题:</td>
			<td>
				${reportReply.followProblem}
			</td>
		</tr>
		<tr>
			<td class="field-name">需要的支持:</td>
			<td>
				${reportReply.support}
			</td>
		</tr>
		<tr>
			<td class="field-name">建议和想法:</td>
			<td>
				${reportReply.suggestion}
			</td>
		</tr>
		<tr>
			<td class="field-name">跟进人:</td>
			<td>
				${reportReply.followUsername}
			</td>
		</tr>
	</table>
	
	<br>
	<div class="ftitle">日报回复</div>
	<div class="comments">
		<ul>
			<c:forEach items="${replys}" var="reply">
				<li id="reply_${reply.id}">
					<div class="comment">
						<div class="commentInfo">
							<span class="username">${reply.username}</span><span class="time">${reply.replyTime}</span>
						</div>
						<div class="commentMsg">${fn:replace(reply.replyMsg,vEnter,'<br>') }</div>
						<c:if test="${reply.myReply}">
							<a href="javascript:void(0)" class="operation" onclick="deleteReply('${reply.id}')">删除</a>
						</c:if>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>

	<br>
	<div class="ftitle">添加回复</div>
	<form id="replyForm" action="" method="post">
		<textarea name="replyMsg" id="replyMsgInput" maxlength="200" cols="60" rows="6"  
				class="easyui-validatebox" required="required" /> (200字)
	</form>
	
	<div style="text-align:left; padding-top: 5px;">
		<a id="saveBtn" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="addReply();">回复</a>
	</div>
</div>

</body>
</html>