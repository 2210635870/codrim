<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp"%>

<html>
<head>
<jsp:include page="../common/meta.jsp" flush="true" />

<script type="text/javascript">

	$(function(){
		ajaxFillSelect("custSelect", "/loadCustomers.do", "${task4View.customerId}");
		$("#custSelect").combobox({ disabled: true });
		
		countText("taskRemarkInput");
		countText("taskDescInput");
		countText("stepDescInput");
		countText("screenDescInput");
		
		for (var i=parseInt($("#maxStepIndex").val())+1; i<10; i++) {
			$("#taskStepPanel" + i).hide();
		}
		
		var origPrice = parseFloat("${task4View.taskOrigPrice}");
		var exchangeRate = parseInt($("#exchangeRate").val());
		var groupCodrimPercent = parseInt($("#groupCodrimPercent").val());
		var userCodrimPercent = parseInt($("#userCodrimPercent").val());
		
		$("#normalTaskPriceYuan").text(((origPrice*10) * (100-userCodrimPercent))/1000);
		$("#normalTaskPriceGold").text(((origPrice*10) * (100-userCodrimPercent))/1000 * exchangeRate);
		$("#groupTaskPriceYuan").text(((origPrice*10) * (100-groupCodrimPercent))/1000);
		$("#groupTaskPriceGold").text(((origPrice*10) * (100-groupCodrimPercent))/1000 * exchangeRate);
	});
	
</script>

</head>

<body style="visibility: visible;">


<div class="easyui-panel" title="查看任务" style="width:800px">
	<div style="padding:10px 10px 10px 10px">
		
		<form:form commandName="task4View" id="taskForm" method="post" enctype="multipart/form-data" action="/wz/task/view.do">
			<input type="hidden" id="exchangeRate" value="${settingCurrency.exchangeRate}" />
			<input type="hidden" id="groupCodrimPercent" value="${settingCurrency.groupCodrimPercent}" />
			<input type="hidden" id="userCodrimPercent" value="${settingCurrency.userCodrimPercent}" />
			<form:hidden path="maxStepIndex" id="maxStepIndex"/>
			<fieldset>
				<legend>基本信息</legend>
				<table cellpadding="5">
					<tr>
						<td class="field-name">客户:</td>
						<td>
							<form:select path="customerId" class="easyui-combobox" id="custSelect" disabled="disabled"></form:select>
						</td>
					</tr>
					<tr>
						<td class="field-name">任务名称:</td>
						<td>
							${task4View.taskName}
						</td>
					</tr>
					<tr>
						<td class="field-name">产品ICON:</td>
						<td>
							<img id="appIconImg" width="100" height="100" src="${imgRoot}${task4View.appIcon}" />
						</td>
					</tr>
					<tr>
						<td class="field-name">产品截图:</td>
						<td>
							<table>
								<tr>
									<td>
										<img id="appScreen1Img" width="300" height="400" src="${imgRoot}${task4View.appScreen1}" />
									</td>
									<td>
										<img id="appScreen2Img" width="300" height="400" src="${imgRoot}${task4View.appScreen2}" /
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td class="field-name">产品备注:</td>
						<td>
							${task4View.taskRemark}
						</td>
					</tr>
					<tr>
						<td class="field-name">产品描述:</td>
						<td>
							${fn:replace(task4View.taskDesc,vEnter,'<BR />') }
						</td>
					</tr>
					<tr>
						<td class="field-name">应用包:</td>
						<td>
							<a href="${imgRoot}${task4View.appUrl}">[下载链接]</a>
						</td>
					</tr>
					<tr>
						<td class="field-name">应用包名:</td>
						<td>
							${task4View.appPname}
						</td>
					</tr>
					<tr>
						<td class="field-name">锁屏推送图片</td>
						<td>
							<img id="appScreenLockImg" width="300" height="400" src="${imgRoot}${task4View.appScreenLock}" />
						</td>
					</tr>
				</table>
			</fieldset>
			<br>
			
			<fieldset>
				<legend>投放策略</legend>
				<table cellpadding="5">
					<tr>
						<td class="field-name">接入单价:</td>
						<td>
							${task4View.taskOrigPrice} 元
						</td>
					</tr>
					<tr>
						<td class="field-name">投放对象:</td>
						<td>
							普通用户: <span id="normalTaskPriceYuan"></span>元 = <span id="normalTaskPriceGold"></span>金币
							<br>
							团队: &nbsp;&nbsp;&nbsp;&nbsp;<span id="groupTaskPriceYuan"></span>元 = <span id="groupTaskPriceGold"></span>金币
						</td>
					</tr>
					<tr>
						<td class="field-name">投放时间:</td>
						<td>
							${task4View.taskStartDate} 至 ${task4View.taskEndDate}
						</td>
					</tr>
				</table>
			</fieldset>
			<br>
			
			<fieldset>
				<legend>任务设置</legend>
				
				<c:forEach items="${task4View.taskSteps}" var="taskStep" varStatus="vs" >
					<c:set var="index" value="${vs.index }" />
					<fieldset id="taskStepPanel${index}">
						<legend>任务${index+1}</legend>
						
						<table cellpadding="5">
							<tr>
								<td class="field-name">任务描述:</td>
								<td>
									${taskStep.stepDesc}
								</td>
							</tr>
							<tr>
								<td class="field-name">任务奖励:</td>
								<td>
									${taskStep.rewardPercent}% = <span id="stepRewardYuan${index}"></span>元 = <span id="stepRewardGold${index}"></span>金币
								</td>
							</tr>
							<tr id="stepTypeTR">
								<td class="field-name">任务类型:</td>
								<td>
									<c:choose>
										<c:when test="${taskStep.stepType eq 2}">计时任务</c:when>
										<c:when test="${taskStep.stepType eq 3}">截图任务</c:when>
									</c:choose>
								</td>
							</tr>
							
							<tbody id="countTaskPanel${index}" >
								<c:if test="${index ne 0}">
									<tr>
										<td class="field-name">统计日期:</td>
										<td>
											${taskStep.countInterval}天 (从上一步任务开始生效的日期算起)
										</td>
									</tr>
								</c:if>
								<tr>
									<td class="field-name">统计时长:</td>
									<td>
										${taskStep.countDuration}分钟
									</td>
								</tr>
							</tbody>
							<tbody id="screenTaskPanel${index}">
								<tr>
									<td class="field-name">截图数量:</td>
									<td>
										${taskStep.screenNo}张
									</td>
								</tr>
								<tr>
									<td class="field-name">截图说明:</td>
									<td>
										${taskStep.screenDesc}
									</td>
								</tr>
							</tbody>
							
						</table>
					</fieldset>
					
					<script type="text/javascript">
						if ("${taskStep.stepType}" == "1") {
							$("#stepTypeTR").hide();
							$("#countTaskPanel${index}").hide();
							$("#screenTaskPanel${index}").hide();
						} else if ("${taskStep.stepType}" == "2") {
							$("#screenTaskPanel${index}").hide();
						} else {
							$("#countTaskPanel${index}").hide();
						}
					
						$("input[name='taskSteps[${index}].stepType']").change(function(){
							if ($(this).val() == "1") {
								$("#countTaskPanel${index}").show();
								$("#screenTaskPanel${index}").hide();
							} else {
								$("#countTaskPanel${index}").hide();
								$("#screenTaskPanel${index}").show();
							}
						});
						
						var origPrice = parseFloat("${task4View.taskOrigPrice}");
						var exchangeRate = parseInt($("#exchangeRate").val());
						var rewardPercent = parseInt("${taskStep.rewardPercent}");
						$("#stepRewardYuan${index}").text(((origPrice*10) * rewardPercent)/1000);
						$("#stepRewardGold${index}").text(((origPrice*10) * rewardPercent)/1000 * exchangeRate);
					</script>
				</c:forEach>
				
				<br>
			</fieldset>
			
			<div style="text-align:center;padding:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="location.href='${pageContext.request.contextPath}/views/wz/task/list.jsp'">返回</a>
			</div>
		</form:form>
	</div>
</div>
	
</body>
</html>