<%@ page language="java"  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
	<title>社交墙-任务中心</title>
<jsp:include page="./common/common.jsp" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/tasklist.css">
	
</head>
<body >
		<header class="bar">
		<a href="${pageContext.request.contextPath}/views/index.jsp"><img src="${pageContext.request.contextPath}/res/images/l_arrow.png"></a>
		<p>任务中心</p>
	</header>
		<div class="list">
		<ul id="list">
		
		</ul>
	</div>
	<div class="fixed">
		<div class="content">
			
		</div>
	</div>

	<script src="${pageContext.request.contextPath}/res/js/zepto/zepto.min.js"></script>
	<script src="${pageContext.request.contextPath}/res/js/tasklist.js"></script>
	
</body>
</html>