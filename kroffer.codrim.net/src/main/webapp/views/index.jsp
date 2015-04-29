<%@ page language="java"  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
	<title>社交墙-首页</title>
	
	

	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/index.css">
<jsp:include page="./common/common.jsp" />
	<script src="${pageContext.request.contextPath}/res/js/index.js"></script>
	<script type="text/javascript">
	var code='<%=request.getParameter("code")%>';
	var state='<%=request.getParameter("state")%>';
	</script>
	
	<!-- 	<style type="text/css">
		#direct {
			display: block;
			position: absolute;
			top: 100px;
			z-index: 1001;
			width: 100px;
			height: 100px;
			background-color: blue;
			left: 50%;
			margin-left: -50px;
			color: #fff;
		}
	</style>
		 -->
</head>
<body >
<div id="fixed" >
		<section>
			<header>第一次使用？需要安装Kroffer哦</header>
			<article>
				<a href="javascript:void(0)"  id="download" onclick="toRedirect();"></a>
			</article>
		</section>
		<img src="${pageContext.request.contextPath}/res/images/tips_2.png">
		<section>
			<header>已经使用过社交墙？输入你的用户ID，马上使用！</header>
			<article>
				<label>用户ID:</label>
				<input type="text" name="id" id="userId">
				<a href="javascript:void(0)" id="login">马上使用</a>
			</article>
		</section>
	</div>


	<div class="top">
		<header>
			<img src="${pageContext.request.contextPath}/res/images/coin.png">
			我的余额 :
		</header>
		<section>
			<p id="pts"></p>
		</section>
		<footer>今日收入 :  <span id="tdp"></span></footer>
	</div>
	<div class="list">
		<ul>
			<li>
				<a href="${pageContext.request.contextPath}/views/tasklist.jsp">
					<img src="${pageContext.request.contextPath}/res/images/task.png">
					<p>任务中心</p>
					<img src="${pageContext.request.contextPath}/res/images/r_arrow.png">
				</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/views/exchange.jsp">
					<img src="${pageContext.request.contextPath}/res/images/exchange.png">
					<p>兑换中心</p>
					<img src="${pageContext.request.contextPath}/res/images/r_arrow.png">
				</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/views/personal.jsp">
					<img src="${pageContext.request.contextPath}/res/images/personal.png">
					<p>个人中心</p>
					<img src="${pageContext.request.contextPath}/res/images/r_arrow.png">
				</a>
			</li>
		</ul>
	</div>
	
</body>
</html>
