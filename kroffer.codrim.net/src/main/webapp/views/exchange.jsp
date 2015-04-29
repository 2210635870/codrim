<%@ page language="java"  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
	<title>社交墙-兑换中心</title>
<jsp:include page="./common/common.jsp" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/exchange.css">
	
</head>
<body >
		<header class="bar">
		<a href="${pageContext.request.contextPath}/views/index.jsp"><img src="${pageContext.request.contextPath}/res/images/l_arrow.png"></a>
		<p>兑换中心</p>
	</header>
	<div class="list">
		<ul>
			<li>
				<a href="${pageContext.request.contextPath}/views/paypal.jsp?type=Z">
					<div class="icon zhifubao"></div>
					<dl>
						<dt>支付宝</dt>
						<dd>马上兑现</dd>
					</dl>
					<div class="wallet">
					</div>
				</a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/views/paypal.jsp?type=H">
					<div class="icon huafei"></div>
					<dl>
						<dt>话费</dt>
						<dd>马上兑现</dd>
					</dl>
					<div class="wallet">
					</div>
				</a>
			</li>
			
			
			
		</ul>
	</div>
	
</body>
</html>