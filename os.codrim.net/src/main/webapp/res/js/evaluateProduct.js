$(function() {
	initCurrentTime("startTime","endTime");
	var url = "?evaluateResult=" + $("#evaluateResult").val() + "&startTime="
	+ $("#startTime").datebox("getValue") + "&endTime="
	+ $("#endTime").datebox("getValue");

$('#view').datagrid({
         url : projectPath + '/getProductsByStatus.do' +url,
 		loadFilter: function(data){
			$.each(data.rows,function(n,value){
				if (value.evaluateResult==1){
					value.evaluateResult='接入';
				} else if(value.evaluateResult==2){
					value.evaluateResult='不接入';
				}else{
					value.evaluateResult='待评估';
				}
				if (value.isback==1){
					value.isback='是';
				}else{
					value.isback='否';
				}
			});
		return data;
		}
     });
});

function getAllProductTypes(type) {
	$.getJSON(projectPath + "/getAllProductTypes.do", function(result) {
		var str = "";
		$.each(result, function(i, o) {
			str += "<input name='productType' type='radio' value='"
					+ o.productType + "' validType='radio'> " + o.productType;
		});
		$("#productType").html(str);
		if (type != null) {
			$("input[name='productType'][value=" + type + "]").attr("checked",
					true);
		} else {
			$("input:radio[name='productType']").eq(0).attr("checked", true);
		}
	});
}
var flag = false;
var url;
function newEvaluateProduct() {
	$('#dlg').dialog('open').dialog('setTitle', '添加评估');
	$('#fm').form('clear');
	getAllProductTypes(null);
	getCustomerName(false,"","customerName",'');
	$("input[name='deviceplate'][value=android]").attr("checked", true);
	$("input[name='isback'][value=1]").attr("checked", true);
	$("#applyName").val(username);
	url = projectPath + '/saveEvaluateProduct.do';
}
// 编辑评估
function editEvaluateProduct() {
	var row = $('#view').datagrid('getSelected');
	if (row) {
		$('#fm').form('clear');
		$('#dlg').dialog('open').dialog('setTitle', '编辑评估信息');
		$('#fm').form('load', row);
		getAllProductTypes(row.productType);
		if(row.isback=='是'){
		$("input[name='isback'][value=1]").attr("checked", true);
		}
		else{
			$("input[name='isback'][value=0]").attr("checked", true);
			}
		url = projectPath + '/editEvaluateProduct.do?id=' + row.id+"&type=evaluate";
	}
}
// 评估
function evaluateProduct() {
	var row = $('#view').datagrid('getSelected');
	if (row) {
		$('#dlg_edit').dialog('open').dialog('setTitle', '评估信息');
		$("#evaluate_div").css("display", "block");
		$("#evaluate_div_res").css("display", "none");
		$("#dlg-buttons_edit").css("display","block");
		$('#fm_edit').form('load', row);
		url = projectPath + '/evaluateProduct.do?id=' + row.id;
		$("#evaluateName").val(username);// 评估人
		$("input[name='evaluateResult'][value=1]").attr("checked", true);
	}
}
function addProductDetail(){
	var row = $('#view').datagrid('getSelected');
	if (row) {
		if(row.evaluateResult=='接入'){
			location.href='./productDetailCreate.jsp?row='+JSON.stringify(row);
		}else{
			alert("请选择已通过审核产品进行渠道添加！");
		}
	}else{
		alert("请选择产品进行渠道添加！");
	}
}
function lookEvaluateProduct() {
	var row = $('#view').datagrid('getSelected');
	if (row) {
		$('#dlg_edit').dialog('open').dialog('setTitle', '查看评估信息');
		$("#evaluate_div").css("display", "none");
		$("#evaluate_div_res").css("display", "block");
		$('#fm_edit').form('load', row);
		$("#evaluateResult_res").val(row.evaluateResult);
		$("#evaluateRemark_res").val(row.evaluateRemark);
		$("#dlg-buttons_edit").css("display","none");
	}
}

function saveEvaluateProduct() {
	$('#fm').form('submit', {
		url : url,
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(result) {
			var result = eval('(' + result + ')');
			if (result.success) {
				$('#dlg').dialog('close'); // close the dialog
					$('#view').datagrid('reload');
			} else {
				$.messager.show({
					title : 'Error',
					msg : "添加错误"
				});
			}
		}
	});
}
function saveEvaluateProduct_edit() {

	$('#fm_edit').form('submit', {
		url : url,
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(result) {
			var result = eval('(' + result + ')');
			if (result.success) {
				$('#dlg_edit').dialog('close'); // close the dialog
				// // reload the user data
					$('#view').datagrid('reload');
			} else {
				$.messager.show({
					title : 'Error',
					msg : "添加错误"
				});
			}
		}
	});

}
function removeEvaluateProduct() {
	var row = $('#view').datagrid('getSelected');
	if (row) {
		$.messager.confirm('Confirm',
				'Are you sure you want to remove this user?', function(r) {
					if (r) {
						$.post(projectPath + '/deleteProduct.do', {
							id : row.id
						}, function(result) {
							if (result.success) {
								$('#view').datagrid('reload'); // reload the
																// user data
							} else {
								$.messager.show({ // show error message
									title : 'Error',
									msg : result.msg
								});
							}
						}, 'json');
					}
				});
	}
}

function submit() {
	var url = "?evaluateResult=" + $("#evaluateResult").val() + "&startTime="
	+ $("#startTime").datebox("getValue") + "&endTime="
	+ $("#endTime").datebox("getValue");
	$('#view').datagrid({
        url : projectPath + '/getProductsByStatus.do' +url,
	});
}
