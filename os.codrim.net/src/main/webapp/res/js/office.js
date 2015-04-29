$(function() {
	$('#view').datagrid({
		url : projectPath+'/getAllOffice.do',
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
												url : projectPath+"/validOfficeName.do",
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
function newOffice() {
	$('#dlg').dialog('open').dialog('setTitle', '新建职位信息');
	$('#fm').form('clear');
	url = projectPath+'/saveOffice.do';
}
function editOffice() {
	var row = $('#view').datagrid('getSelected');
	if (row) {
		$('#dlg').dialog('open').dialog('setTitle', '编辑职位信息');
		$('#fm').form('load', row);
		url =projectPath+ '/editOffice.do?id=' + row.id;
	}
}

function saveOffice() {
	
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
function removeOffice() {
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
												projectPath+'/deleteOffice.do',
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

