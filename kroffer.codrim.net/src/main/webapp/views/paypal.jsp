<%@ page language="java"  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
	<title>社交墙-兑换中心</title>
<jsp:include page="./common/common.jsp" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/paypal.css">
		<script src="${pageContext.request.contextPath}/res/js/zepto/zepto.min.js"></script>
		<script type="text/javascript">
		var type='<%=request.getParameter("type")%>';
		</script>
	<script src="${pageContext.request.contextPath}/res/js/paypal.js"></script>
</head>
<body >
	<header class="bar">
		<a href="${pageContext.request.contextPath}/views/exchange.jsp"><img src="${pageContext.request.contextPath}/res/images/l_arrow.png"></a>
		<p id="headname">paypal充值</p>
	</header>
	<div class="top">
		<header>
			我的余额 :
		</header>
		<section>			
			<p>165,462</p>
		</section>
	</div>
	<div class="tips"  style="display:none">
		<p>余额不足</p>	
		<img src="${pageContext.request.contextPath}/res/images/close2.png" class="close">
		<a href="${pageContext.request.contextPath}/views/tasklist.jsp"><img src="${pageContext.request.contextPath}/res/images/earn.jpg" class="earn"></a>
	</div>
	<ul class="input">
		<li>
			<label  id="account">paypal账号：</label>
			<input type="text" name="username"  id="username"/>
		</li>
		<li class="price">
			<label>充值金额：</label>
			<p></p>
		</li>
	</ul>
	<a class="btn submit">提交</a>
	<div class="fixed"></div>
	<div class="selected">
		<ul>
			<li><span>10</span> </li>
			<li><span>20</span> </li>
			<li><span>50</span> </li>
		</ul>
		<a class="btn cancel">取消</a>
	</div>


	
</body>
</html>