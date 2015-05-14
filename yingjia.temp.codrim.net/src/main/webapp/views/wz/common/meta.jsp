<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
	request.setAttribute("vEnter", "\n");
%>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/jQuery.easyui.1.2.2/js/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/jQuery.easyui.1.2.2/js/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/jQuery.easyui.1.2.2/css/main.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/common.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/res/jQuery.easyui.1.2.2/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/jQuery.easyui.1.2.2/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/jQuery.easyui.1.2.2/js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/jQuery.easyui.1.2.2/js/plugins/jquery.edatagrid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/jQuery.easyui.1.2.2/js/plugins/jquery.edatagrid.lang.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/uploadPreview.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/wz/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/wz/validateBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/wz/ajaxfileupload.js"></script>

<script type="text/javascript">
	var ctx = "${pageContext.request.contextPath}";
</script>