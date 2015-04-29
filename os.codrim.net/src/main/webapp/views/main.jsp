<%@ page language="java" import="common.codrim.pojo.TbAdmin"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="./common/common-taglib.jsp"%>
<html>
<head>
<title>网站后台管理系统</title>
<jsp:include page="./common/common.jsp" />
<script type="text/javascript">
	var type =<%=type%>;
	var _menus;
</script>
<script type="text/javascript" src='${pageContext.request.contextPath}/res/js/main.js'>
	</script>
</head>
<body class="easyui-layout" style="overflow-y: hidden" fit="true"
	scroll="no">
	<!-- 页面提示 -->
	<noscript>
		<div
			style="position: absolute; z-index: 100000; height: 2046px; top: 0px; left: 0px; width: 100%; background: white; text-align: center;">
			<img
				src="${pageContext.request.contextPath}/res/jQuery.easyui.1.2.2/images/noscript.gif"
				alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>

	<div id="loading-mask"
		style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; background: #D2E0F2; z-index: 20000">
		<div id="pageloading"
			style="position: absolute; top: 50%; left: 50%; margin: -120px 0px 0px -120px; text-align: center; border: 2px solid #8DB2E3; width: 200px; height: 40px; font-size: 14px; padding: 10px; font-weight: bold; background: #fff; color: #15428B;">
			<img
				src="${pageContext.request.contextPath}/res/jQuery.easyui.1.2.2/images/loading.gif"
				align="absmiddle" /> 正在加载中,请稍候...
		</div>
	</div>
	<!-- 页面头 -->
	<div region="north" split="true" border="false"
		style="overflow: hidden; height: 56px;
        background: url(${pageContext.request.contextPath}/res/jQuery.easyui.1.2.2/images/layout-browser-hd-bg.jpg) #7f99be repeat-x center 50%;
        line-height: 49px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
		<span style="float: right; padding-right: 50px;font-size:22px" class="head">欢迎
			<%=account%> <a href="#" id="loginOut">安全退出</a>
		</span> <span style="padding-left: 10px; font-size: 37px;"><img
			src="${pageContext.request.contextPath}/res/jQuery.easyui.1.2.2/images/blocks.png"
			width="35" height="40" align="absmiddle" /> codrim</span>
	</div>

	<!-- 页面底部信息 -->
	<div region="south" style="height: 35px;">
		<center>
			<span>联系我们 | 法律声明 | 合作伙伴 | 客户服务 | 隐私声明</span><br> <span>Ristone
				版权所有© 氪金信息科技有限公司 Copyright 2009 XI’AN RISTONE All Rights Reserved
				陕ICP备 09016836号 </span>
		</center>
	</div>
	<div region="west" split="true" title="导航菜单" style="width: 180px;"
		id="west">
		<div id="nav">
			<!--  导航内容 -->
		</div>
	</div>
	<!-- 中间层 -->
	<div id="mainPanle" region="center"
		style="background: #eee; overflow-y: hidden">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div title="欢迎使用"
				style="padding: 20px; overflow: hidden;">
				<div style="padding: 20px;">
<iframe name="weather_inc" src="http://i.tianqi.com/index.php?c=code&id=55" style="border:solid 1px #7ec8ea" width="255" height="294" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
</div>
		         	<h1 style="font-size: 24px; color: red;margin-top: -242px;padding-left: 503px;">* *  欢迎光临氪金后台管理系统！* *</h1>
			</div>
		</div>
	</div>
	<!-- 右键信息 -->
	<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="close">关闭</div>
		<div id="closeall">全部关闭</div>
		<div id="closeother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="closeright">当前页右侧全部关闭</div>
		<div id="closeleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="exit">退出</div>
	</div>
</body>
</html>
