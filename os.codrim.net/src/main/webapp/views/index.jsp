<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<title>登录</title>
<link rel="stylesheet" type="text/css"
	href="<%=path %>/res/jQuery.easyui.1.2.2/js/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=path %>/res/jQuery.easyui.1.2.2/js/themes/icon.css">
	<script type="text/javascript"
	src="<%=path %>/res/jQuery.easyui.1.2.2/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript"
	src="<%=path %>/res/jQuery.easyui.1.2.2/js/jquery.easyui.min.js"></script>
	<script type="text/javascript"
	src="<%=path %>/res/jQuery.easyui.1.2.2/js/locale/easyui-lang-zh_CN.js"></script>
<link href="<%=path %>/index/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=path %>/res/js/login.js"></script>
	<script type="text/javascript">
	var projectPath="${pageContext.request.contextPath}";
	</script>
</head>
<body>


<div class="login">
    <div class="box png">
		<div class="logo png"></div>
		<div class="input">
			<div class="log">
				<div class="name">
					<label>邮箱</label><input type="text" class="text easyui-validatebox" id="email" placeholder="邮箱" name="email"  tabindex="1" 
						required="true" validType="email" missingMessage="请输入正确的邮箱"/>
				</div>
				<div class="pwd">
					<label>密码 </label><input type="password" class="text easyui-validatebox" id="pass" placeholder="密码" name="password"  tabindex="2"
					  required="true" missingMessage="不能为空"/>
					  <br/>
					<input type="button" class="submit" onclick="submit();"  tabindex="3" value="登录"/>
					<div class="check"></div>
				</div>
				<div class="tip"></div>
			</div>
		</div>
	</div>
    <div class="air-balloon ab-1 png"></div>
	<div class="air-balloon ab-2 png"></div>
    <div class="footer"></div>
</div>

<script type="text/javascript" src="<%=path %>/index/js/fun.base.js"></script>
<script type="text/javascript" src="<%=path %>/index/js/script.js"></script>


<!--[if IE 6]>
<script src="js/DD_belatedPNG.js" type="text/javascript"></script>
<script>DD_belatedPNG.fix('.png')</script>
<![endif]-->

</body>
</html>