<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp"%>

<html>
<head>
<jsp:include page="../common/meta.jsp" flush="true" />

<script type="text/javascript">

	$(function(){
		ajaxFillSelect("custSelect", "/loadCustomers.do", "${task.customerId}");
		
		new uploadPreview({ UpBtn: "appScreen1Btn", DivShow: "appScreen1div", ImgShow: "appScreen1Img" });
		new uploadPreview({ UpBtn: "appScreen2Btn", DivShow: "appScreen2div", ImgShow: "appScreen2Img" });
		new uploadPreview({ UpBtn: "appIconBtn", DivShow: "appIconDiv", ImgShow: "appIconImg" });
		new uploadPreview({ UpBtn: "appScreenLockBtn", DivShow: "appScreenLockDiv", ImgShow: "appScreenLockImg" });
		
		$("#addTaskBtn").click(function(){
			var maxStepIndex = parseInt($("#maxStepIndex").val());
			var nextStepIndex = maxStepIndex + 1;
			
			$("#maxStepIndex").val(nextStepIndex);
			$("#taskStepPanel" + nextStepIndex).fadeIn();
			
			if (nextStepIndex == 9) {
				$(this).hide();
				return;
			}
			
			if (nextStepIndex > 0) {
				$("#removeTaskBtn").fadeIn();
			}
		});
		
		$("#removeTaskBtn").click(function(){
			var maxStepIndex = parseInt($("#maxStepIndex").val());
			var preStepIndex = maxStepIndex - 1;
			
			$("#maxStepIndex").val(preStepIndex);
			$("#taskStepPanel" + maxStepIndex).fadeOut();
			
			if (preStepIndex == 0) {
				$(this).fadeOut();
				return;
			}
			$("#addTaskBtn").fadeIn();
		});
		
		if ("${task.maxStepIndex}" == 0) {
			$("#removeTaskBtn").hide();
		}
		if ("${task.maxStepIndex}" == 9) {
			$("#addTaskBtn").hide();
		}
		
		$("#origPriceInput").change(function(){
			if (isNaN($(this).val())) {
				return false;
			}
			
			var origPrice = parseFloat($(this).val());
			var exchangeRate = parseInt($("#exchangeRate").val());
			var groupCodrimPercent = parseInt($("#groupCodrimPercent").val());
			var userCodrimPercent = parseInt($("#userCodrimPercent").val());
			
			$("#normalTaskPriceYuan").text(((origPrice*10) * (100-userCodrimPercent))/1000);
			$("#normalTaskPriceGold").text(((origPrice*10) * (100-userCodrimPercent))/1000 * exchangeRate);
			$("#groupTaskPriceYuan").text(((origPrice*10) * (100-groupCodrimPercent))/1000);
			$("#groupTaskPriceGold").text(((origPrice*10) * (100-groupCodrimPercent))/1000 * exchangeRate);
		});
		
		var origPrice = parseFloat("${task.taskOrigPrice}");
		var exchangeRate = parseInt($("#exchangeRate").val());
		var groupCodrimPercent = parseInt($("#groupCodrimPercent").val());
		var userCodrimPercent = parseInt($("#userCodrimPercent").val());
		
		$("#normalTaskPriceYuan").text(((origPrice*10) * (100-userCodrimPercent))/1000);
		$("#normalTaskPriceGold").text(((origPrice*10) * (100-userCodrimPercent))/1000 * exchangeRate);
		$("#groupTaskPriceYuan").text(((origPrice*10) * (100-groupCodrimPercent))/1000);
		$("#groupTaskPriceGold").text(((origPrice*10) * (100-groupCodrimPercent))/1000 * exchangeRate);
		
		for (var i=parseInt($("#maxStepIndex").val())+1; i<10; i++) {
			$("#taskStepPanel" + i).hide();
		}
		
		$('#appUrlType1').change(function(){
			if($(this).attr('checked')) {
				$('#appUrlTips').html('例子：apps/ceshi.apk');
			}
		});
		$('#appUrlType2').change(function(){
			if($(this).attr('checked')) {
				$('#appUrlTips').html('为有效的资源路径名，例子：http://www.xxx.com/xxx.apk');
			}
		});
		
		setAppUrl();
	});
	
	function setAppUrl() {
		var appUrl = $('#appUrl').val();
		if(appUrl) {
			var reg = /^((http|https|ftp):\/\/)([a-zA-Z0-9_-]+\.)*/;
			reg.test(appUrl) ? $('#appUrlType2').attr('checked',true).change() : $('#appUrlType1').attr('checked',true).change();
		}
	}
	
	function checkAppUrl() {
		var appUrl = $('#appUrl').val();
		if(appUrl) {
			var reg;
			if( $('#appUrlType1').attr('checked') ) {
				reg = /^(apps)\/([.a-zA-Z0-9_-]+)\.apk/;
			} else {
				reg = /^((http|https|ftp):\/\/)([a-zA-Z0-9_-]+\.)*/;
			}
			
			return reg.test(appUrl); 
		}
	}
	
	function checkStepOrder(){
		var maxStepIndex = parseInt($("#maxStepIndex").val());
		var arrayObj = new Array();
		for(var i=0; i<=maxStepIndex; i++){
			var stepIndex=$("#stepOrder"+i).val();
			arrayObj.push(stepIndex);
		}
		     var hash = {};
		     for(var i in arrayObj) {
		         if(hash[arrayObj[i]])
		              return true;
		         hash[arrayObj[i]] = true;
		     }
		     return false;
	}

	function editTask() {
		if(checkStepOrder()){
			alert("任务步骤有相同的，请更改！");
			return;
		}
		$('#taskForm').form("submit", {    
		    url: '${ctx}/wz/task/edit.do',    
		    onSubmit: function(){
		    	if(!checkAppUrl()) {
					$.messager.alert('发布任务', '应用包下载路径格式无效！', 'warning');
					return false;
		    	}
				return $(this).form('validate');  
		    },    
		    success:function(data){    
		    	var result = eval('(' + data + ')');
				if (result.success) {
					$.messager.alert('编辑成功', '编辑任务成功！', 'info', function(){
						location.href = "${ctx}/views/wz/task/list.jsp";
					});
				} else {
					$.messager.alert('编辑失败', result.msg, 'error');
				}
		    }    
		});
	}
	
	/*
	function ajaxFileUpload() {
		if ($("#appFileInput").val().length == 0) {
			alert("请先选择应用包!");
			return false;
		}
		
		$("#loading").ajaxStart(function(){
			$(this).show();
		}).ajaxComplete(function(){
			$(this).hide();
		});

		$.ajaxFileUpload(
			{
				url: '${ctx}/wz/task/uploadApp.do',
				secureuri: false,
				fileElementId: 'appFileInput',
				dataType: 'json',
				success: function (data, status){
					if (data.success) {
						$("#appIconImg").attr("src","${imgRoot}" + data.appIcon);
						$("#appFileSpan").text(data.appPname);
					}
				},
				error: function (data, status, e){
					alert(e);
				}
			}
		)
		
		return false;
	}
		*/
		
	
</script>

</head>

<body style="visibility: visible;">

<div class="easyui-panel" title="发布任务" style="width:800px">
	<div style="padding:10px 10px 10px 10px">
		
		<form:form commandName="task" id="taskForm" method="post" enctype="multipart/form-data" action="/wz/task/edit.do">
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
							<form:select path="customerId" class="easyui-combobox" id="custSelect"></form:select>
						</td>
					</tr>
					<tr>
						<td class="field-name">任务名称:</td>
						<td>
							<form:input path="taskName" maxlength="10" class="easyui-validatebox" id="taskNameInput" required="required"/>
						</td>
					</tr>
					 <tr>
						<td class="field-name">任务所属:</td>
						<td>
							<form:radiobutton path="taskBelong" value="1" />盈+
						 <form:radiobutton path="taskBelong" value="2"/>社交墙
						</td>
					</tr>
						 <tr>
						<td class="field-name">显示权重:</td>
						<td>
					 <form:radiobutton path="weight" value="0" />0
						 <form:radiobutton path="weight" value="1"/>1
			             <form:radiobutton path="weight" value="2" />2
						 <form:radiobutton path="weight" value="3"/>3
						 <span style="color:red">代表在客户端显示顺序(权重相同默认按添加时间排序)，默认权重为0，依次递增</span>
						</td>
					</tr>
					<tr>
						<td class="field-name">应用包地址类型:</td>
						<td>
							<input type="radio" name="appUrlType" id="appUrlType1" />内网
							<input type="radio" name="appUrlType" id="appUrlType2" />外网
						</td>
					</tr>
					<tr>
						<td class="field-name">应用包下载地址:</td>
						<td>
								<form:input path="appUrl" maxlength="500" class="easyui-validatebox" id="appUrl" required="required" value="${task.appUrl}"/>
								<span style="color:red" id="appUrlTips"></span>
						</td>
					</tr>
						<tr>
						<td class="field-name">应用包大小:</td>
						<td>
						<form:input path="appSize" maxlength="50"  id="appSize" class="easyui-numberbox"
						required="true" min="0.1" precision="0" value="${task.appSize}"/>
						<span>例子：2.5m  填入的值应该是2.5x1024x1024=<span style="color:red">2621440</span></span>
						</td>
					</tr>
					<tr>
						<td class="field-name">应用包名:</td>
						<td>
								<form:input path="appPname" maxlength="50" class="easyui-validatebox" id="appPname" required="required" value="${task.appPname}"/>
						</td>
					</tr>
					<tr>
						<td class="field-name">产品ICON:</td>
						<td>
								<div id="appIconDiv"><img id="appIconImg" width="100" height="100" src="${imgRoot}${task.appIcon}"  /></div>
										<form:input path="appIconFile" type="file" id="appIconBtn" class="easyui-validatebox" />
						</td>
					</tr>
					<tr>
						<td class="field-name">产品截图:</td>
						<td>
							<table>
								<tr>
									<td>
										<div id="appScreen1div"><img id="appScreen1Img" width="300" height="400" src="${imgRoot}${task.appScreen1}" /></div>
										<form:input path="appScreen1File" type="file" id="appScreen1Btn" class="easyui-validatebox" />
									</td>
									<td>
										<div id="appScreen2div"><img id="appScreen2Img" width="300" height="400" src="${imgRoot}${task.appScreen2}" /></div>
										<form:input path="appScreen2File" type="file" id="appScreen2Btn" class="easyui-validatebox" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td class="field-name">产品备注:</td>
						<td>
							<form:textarea path="taskRemark" id="taskRemarkInput" maxlength="20" cols="60" rows="6"  
								class="easyui-validatebox" required="required" />
						</td>
					</tr>
					<tr>
						<td class="field-name">产品描述:</td>
						<td>
							<form:textarea path="taskDesc" id="taskDescInput" maxlength="200" cols="60" rows="6"  
								class="easyui-validatebox" required="required" />
						</td>
					</tr>
					<tr>
						<td class="field-name">锁屏推送图片</td>
						<td>
							<div id="appScreenLockDiv"><img id="appScreenLockImg" width="300" height="400" src="${imgRoot}${task.appScreenLock}" /></div>
							<form:input path="appScreenLockFile" type="file" id="appScreenLockBtn" class="easyui-validatebox" />
						</td>
					</tr>
					<tr>
						<td class="field-name">产品权重:</td>
						<td>
						 	<form:radiobutton path="productWeight" value="1"/>1
			             	<form:radiobutton path="productWeight" value="2" />2
						 	<form:radiobutton path="productWeight" value="3"/>3
							 <span style="color:red">代表在客户端锁屏推送显示概率，默认权重为1，依次递增</span>
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
							<form:input path="taskOrigPrice" id="origPriceInput"
								class="easyui-validatebox" validType="xiaoshu" required="required" /> 元
						</td>
					</tr>
					<tr>
						<td class="field-name">投放对象:</td>
						<td>
							普通用户: <span id="normalTaskPriceYuan">0</span>元 = <span id="normalTaskPriceGold">0</span>金币
							<br>
							团队: &nbsp;&nbsp;&nbsp;&nbsp;<span id="groupTaskPriceYuan">0</span>元 = <span id="groupTaskPriceGold">0</span>金币
						</td>
					</tr>
					<tr>
						<td class="field-name">投放时间:</td>
						<td>
							<form:input path="taskStartDate" id="taskStartDateInput" class="easyui-datebox" required="required" /> &nbsp;至&nbsp;
							<form:input path="taskEndDate" id="taskEndDateInput" class="easyui-datebox" required="required" />
						</td>
					</tr>
				</table>
			</fieldset>
			<br>
			
			<fieldset>
				<legend>任务设置</legend>
				
				<c:forEach items="${task.taskSteps}" var="taskStep" varStatus="vs" >
					<c:set var="index" value="${vs.index }" />
					<fieldset id="taskStepPanel${index}">
						<legend>任务${index+1}</legend>
						
						<table cellpadding="5">
							<tr>
								<td class="field-name">任务描述:</td>
								<td>
									<form:textarea path="taskSteps[${index}].stepDesc" id="stepDescInput${index}" maxlength="20" cols="60" rows="6" />
								</td>
							</tr>
							<tr>
								<td class="field-name">任务奖励:</td>
								<td>
									<form:input path="taskSteps[${index}].rewardPercent" id="rewardInput${index}" class="easyui-validatebox" validType="range[0,100]" /> %
										= <span id="taskStepPriceYuan${index}">0</span>元 = <span id="taskStepPriceGold${index}">0</span>金币
								</td>
							</tr>
								<tr>
								<td class="field-name">任务步骤:</td>
								<td>
									<form:input path="taskSteps[${index}].stepOrder" id="stepOrder${index}"  value="${index+1}"/> 
								</td>
							</tr>
							<tr id="stepTypeTR">
								<td class="field-name">任务类型:</td>
								<td>
									<form:radiobutton path="taskSteps[${index}].stepType" value="1" cssClass="downloadOptionRB" />
									<form:radiobutton path="taskSteps[${index}].stepType" value="2" />计时任务
									<form:radiobutton path="taskSteps[${index}].stepType" value="3" />上传截图
								</td>
							</tr>
							
							<tbody id="countTaskPanel${index}" >
								<tr>
									<td class="field-name">统计日期:</td>
									<td>
										<form:input path="taskSteps[${index}].countInterval" id="countIntervalInput${index}" /> 天 (从上一步任务开始生效的日期算起)
									</td>
								</tr>
								<tr>
									<td class="field-name">统计时长:</td>
									<td>
										<form:input path="taskSteps[${index}].countDuration" id="countDurationInput${index}" /> 分钟
									</td>
								</tr>
							</tbody>
							<tbody id="screenTaskPanel${index}">
								<tr>
									<td class="field-name">截图数量:</td>
									<td>
										<form:input path="taskSteps[${index}].screenNo" id="screenNoInput${index}" /> 张 (最多5张)
									</td>
								</tr>
								<tr>
									<td class="field-name">截图说明:</td>
									<td>
										<form:textarea path="taskSteps[${index}].screenDesc" id="screenDescInput${index}" maxlength="200" cols="60" rows="6" />
									</td>
								</tr>
							</tbody>
							
						</table>
					</fieldset>

					<script type="text/javascript">
						$(".downloadOptionRB").hide();
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
							if ($(this).val() == "2") {
								$("#countTaskPanel${index}").show();
								$("#screenTaskPanel${index}").hide();
							} else {
								$("#countTaskPanel${index}").hide();
								$("#screenTaskPanel${index}").show();
							}
						}); 
						
						$("#rewardInput${index}").change(function(){
							if (isNaN($(this).val())) {
								return false;
							}
							if (isNaN($("#origPriceInput").val())) {
								return false;
							}
							
							var stepPercent = parseInt($(this).val());
							var origPrice = parseFloat($("#origPriceInput").val());
							var exchangeRate = parseInt($("#exchangeRate").val());
							
							$("#taskStepPriceYuan${index}").text(((origPrice*10) * stepPercent)/1000);
							$("#taskStepPriceGold${index}").text(((origPrice*10) * stepPercent)/1000 * exchangeRate);
						});
						
						var stepPercent = parseInt("${taskStep.rewardPercent}");
						var origPrice = parseFloat($("#origPriceInput").val());
						var exchangeRate = parseInt($("#exchangeRate").val());
						$("#taskStepPriceYuan${index}").text(((origPrice*10) * stepPercent)/1000);
						$("#taskStepPriceGold${index}").text(((origPrice*10) * stepPercent)/1000 * exchangeRate);
					</script>
				</c:forEach>
				
				<br>
				<div style="text-align:center;padding:5px">
					<a id="addTaskBtn" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add">添加任务</a>
					<a id="removeTaskBtn" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove">删除任务</a>
				</div>
				<br>
			</fieldset>
			
			<div style="text-align:center;padding:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="editTask()">编辑</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="location.href='${pageContext.request.contextPath}/views/wz/task/list.jsp'">取消</a>
			</div>
		</form:form>
	</div>
</div>
	
</body>
</html>