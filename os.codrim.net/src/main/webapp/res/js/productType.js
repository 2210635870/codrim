$(function() {
	$('#view').datagrid({
		url : projectPath+'/getAllProductType.do',
	});

	$.extend(
					$.fn.validatebox.defaults.rules,
					{
						validName : {
							validator : function(value, param) {
								var length = strlen(value);
								if (length > 10 || length < 2) {
									$.fn.validatebox.defaults.rules.validName.message = "输入内容长度必须介于2和10之间";
									return false;
								} else {
									$.fn.validatebox.defaults.rules.validName.message = "已被使用";
									var flag = false;
									$.ajax({
												type : "post",
												url : projectPath+"/validProductTypeName.do",
												async : false,
												data : {
													customerName : value
												},
												dataType : "json",
												success : function(data) {
													flag = data;
												},
												error : function(msg) {
												}
											});
									return flag;
								}
							},
							message : ''
						}
					});
});
var url;
function newProductType() {
	$('#dlg').dialog('open').dialog('setTitle', '新建广告类型');
	$('#fm').form('clear');
	url = projectPath+'/saveProductType.do';
}
function editProductType() {
	var row = $('#view').datagrid('getSelected');
	if (row) {
		$('#dlg').dialog('open').dialog('setTitle', '编辑广告类型信息');
		$('#fm').form('load', row);
		url =projectPath+ '/editProductType.do?id=' + row.id;
	}
}

function saveProductType() {
	$('#fm').form('submit', {
		url : url,
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(result) {
			var result = eval('(' + result + ')');
			if (result.success) {
				$('#dlg').dialog('close'); // close the dialog
				$('#view').datagrid('reload'); // reload the user data
			} else {
				$.messager.show({
					title : 'Error',
					msg : result.msg
				});
			}
		}
	});
}
function removeProductType() {
	var row = $('#view').datagrid('getSelected');
	if (row) {
		$.messager
				.confirm(
						'Confirm',
						'Are you sure you want to remove this user?',
						function(r) {
							if (r) {
								$
										.post(
												projectPath+'/deleteProductType.do',
												{
													id : row.id
												}, function(result) {
													if (result.success) {
														$('#view').datagrid(
																'reload'); // reload
																			// the
																			// user
																			// data
													} else {
														$.messager.show({ // show
																			// error
																			// message
															title : 'Error',
															msg : result.msg
														});
													}
												}, 'json');
							}
						});
	}
}

