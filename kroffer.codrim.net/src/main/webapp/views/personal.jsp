<%@ page language="java"  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
	<title>社交墙-兑换中心</title>
<jsp:include page="./common/common.jsp" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/personal.css">
			<script src="${pageContext.request.contextPath}/res/js/personal.js"></script>
</head>
<body >
			<header class="bar">
		<a href="${pageContext.request.contextPath}/views/index.jsp"><img src="${pageContext.request.contextPath}/res/images/l_arrow.png"></a>
		<p>个人中心</p>
	</header>
	<div class="list">
		<ul>
			<li>用户ID: <span id="uid">456789</span></li>
		</ul>
	</div>
	
</body>
</html>