<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp"%>

<html>
<head>
<jsp:include page="../common/meta.jsp" flush="true" />

<script type="text/javascript">
	
</script>

</head>

<body style="visibility: visible;">
<div class="easyui-panel" title="${taskDetail.taskName} - <fmt:formatDate value="${taskDetail.finishDate}" pattern="yyyy-MM-dd"/>" style="width:635px">

	<table id="reportDetailTb" class="bordered" style="border-spacing: 0; width: 100%;" >
		<thead>
			<tr>
				<th align="center" width="8%">#</th>
				<th align="center" width="30%">任务描述</th>
				<th align="center" width="20%">任务单价</th>
				<th align="center" width="22%">任务完成数量</th>
				<th align="center" width="20%">任务成本</th>
			</tr>
		</thead>
		<c:forEach items="${taskDetail.stepReports}" var="detail" varStatus="vs">
			<tr>
				<td>${vs.index+1}</td>
				<td>${detail.stepDesc}</td>
				<td>${detail.stepPrice}元</td>
				<td>
					<c:choose>
						<c:when test="${vs.last}">
							<font color="red"><b>${detail.stepEffect}</b></font>
						</c:when>
						<c:otherwise>${detail.stepEffect}</c:otherwise>
					</c:choose>
				</td>
				<td>${detail.stepCost}元</td>
			</tr>
		</c:forEach>
		
		<tr>
			<td><b>合计</b></td>
			<td></td>
			<td>${taskDetail.taskOrigPrice}元</td>
			<td>${taskDetail.stepEffectTotal}</td>
			<td>${taskDetail.cost}元</td>
		</tr>
	</table>
	
	<%-- <br>
	<div style="text-align:center;padding:5px">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="location.href='${ctx}/views/wz/report/taskReport.jsp'">返回</a>
	</div> --%>
</div>

</body>
</html>