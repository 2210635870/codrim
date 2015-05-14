<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp"%>

<html>
<head>
<jsp:include page="../common/meta.jsp" flush="true" />

<script type="text/javascript">

	$(function() {
		$('#inviteCodeList').datagrid({
			url: '${ctx}/wz/group/inviteCodeList.do',
			toolbar: [
				{ 
		            text: '制作团队邀请码', 
		            iconCls: 'icon-add', 
		            handler: function() { 
		                openGenCodeDialog(); 
		            } 
		        }, '-',
				{ 
		            text: '编辑', 
		            iconCls: 'icon-edit', 
		            handler: function() { 
		                editCode(); 
		            } 
		        }, '-'
			]
		});
	});
	
	function getQueryParams(queryParams) {  
	    var status = $("#statusFilter").combobox("getValue");
	    var code = $("#codeFilter").val();
	 
	    queryParams.status = status;
	    queryParams.code = code;  
	 
	    return queryParams;  
	}
	
    function reloadGrid() {  
        var queryParams = $('#inviteCodeList').datagrid('options').queryParams;  
        getQueryParams(queryParams);  
        $('#inviteCodeList').datagrid('options').queryParams = queryParams;  
        $("#inviteCodeList").datagrid('reload');  
    }

    function openGenCodeDialog() {
        $("#dlg").dialog("open").dialog('setTitle', '制作团队邀请码'); ;
        $("#genCodeForm").form("clear");
        $("#codeDiv").hide();
    }
    
    function editCode() {
        var row = $("#inviteCodeList").datagrid("getSelected");
        if (row) {
            $("#dlg").dialog("open").dialog('setTitle', '编辑');
            $("#genCodeForm").form("load", row);
            $("#codeDiv").show();
            $("#codeSpan").text(row.code);
        }
    }
    
    function save() {
        $("#genCodeForm").form("submit", {
            url: '${ctx}/wz/group/saveCode.do',
            onsubmit: function () {
                return $(this).form("validate");
            },
            success: function (data) {
            	var result = eval('(' + data + ')');
                if (result.success) {
                	$.messager.alert('操作成功', '制作/编辑团队邀请码成功！', 'info', function(){
                		$("#dlg").dialog("close");
                        $("#inviteCodeList").datagrid('reload');
					});
                }
                else {
                    $.messager.alert("提示信息", "操作失败");
                }
            }
        });
    }
    
    function statusFormatter(value, row, index) {
    	if (value == "1") {
    		return "未使用";
    	} else {
    		return "已使用";
    	}
    }
    
</script>

</head>

<body style="visibility: visible;">
	<table id="searchTb" cellpadding="5" >
		<tr>
			<td>
				邀请码:
				<input id="codeFilter" class="easyui-validatebox" />
			</td>
			<td>
				状态:
				<select id="statusFilter" class="easyui-combobox">
					<option value="0">全部</option>
					<option value="1">未使用</option>
				    <option value="2">已使用</option>
				</select>
			</td>
			<td>
				<div>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="reloadGrid()">搜索</a>
				</div>
			</td>
		</tr>
	</table>
	
	<table id="inviteCodeList" idField="codeId" rownumbers="true" pagination="true" singleSelect="true" pageSize=10 >
		<thead>
			<tr>
				<th field="code" align="center" width="auto">邀请码</th>
				<th field="status" align="center" width="auto" formatter="statusFormatter">状态</th>
				<th field="contact" align="center" width="auto">联系人</th>
				<th field="qq" align="center" width="auto">QQ</th>
				<th field="phone" align="center" width="auto">手机</th>
				<th field="email" align="center" width="120">邮箱</th>
			</tr>
		</thead>
	</table>
	
	
	<div id="dlg" class="easyui-dialog" style="width: 480px; height: 280px; padding: 10px 20px;" closed="true" buttons="#dlg-buttons"> 
	   <div class="ftitle">联系人信息</div>
	   <div>
		   <form id="genCodeForm" method="post">
			   <div class="fitem"> 
				   <label>联系人</label> 
				   <input name="contact" class="easyui-validatebox" required="true" /> 
			   </div> 
			   <div class="fitem"> 
				   <label>QQ</label> 
				   <input name="qq" class="easyui-validatebox" required="true" /> 
			   </div> 
			   <div class="fitem"> 
				   <label>手机</label> 
				   <input name="phone" class="easyui-validatebox" required="true" /> 
			   </div> 
			   <div class="fitem"> 
				   <label>邮箱</label> 
				   <input name="email" class="easyui-validatebox" required="true" /> 
			   </div>
			   <div class="fitem" id="codeDiv"> 
				   <label>邀请码</label> 
				   <span id="codeSpan"></span>
			   </div>
			   <input type="hidden" name="code" />
			   <input type="hidden" id="action" />
		   </form>
	   </div>
	</div>
	<div id="dlg-buttons"> 
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="save()" iconcls="icon-save">保存</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a> 
	</div>

</body>
</html>