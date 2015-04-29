<%@ page language="java"  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
	<title>社交墙-跳转中</title>
<jsp:include page="./common/common.jsp" />
		<style type="text/css">
		body {
			background-color: #fff;
		}
		#redirecting {
			display: block;
			width: 100%;
		}
		section {
			display: none;
			position: relative;
		}
		section a {
			position: absolute;
			width: 60%;
			height: 13%;
			margin-left: -30%;
			left: 50%;
			bottom: 0;
		}
		#tips {
			display: none;
			background-color: rgba(0, 0, 0, 0.5);
			position: fixed;
			width: 100%;
			height: 100%;
			left: 0;
			top: 0;
			right: 0;
			bottom: 0;
		}
		#tips img {
			width: 97%;
			display: block;
			margin: 0 auto;
		}
			#tips a {
			display: block;
			width: 190px;
			height: 46px;
			border-radius: 6px;
			line-height: 46px;
			text-align: center;
			color: #f18800;
			font-size: 18px;
			background-color: #fffaea;
			margin: 100px auto 0;
		}
	</style>
</head>
<body >
	<section>
		<img src="${pageContext.request.contextPath}/res/images/redirect.png" id="redirecting">
		<a href="http://img.codrim.net/yingjia/KrOffer.apk"  id="download"></a>
	</section>
	<div id="tips">
		<img src="${pageContext.request.contextPath}/res/images/tips_3.png">
		<a href="javascript:window.history.go(-1)">返回任务列表</a>
	</div>
	<script type="text/javascript">
	var taskId;
		function getQueryString(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]);
            return null;
        }

   
		if(isWeiXin()) {
			document.querySelector('#tips').style.display = 'block';
		} else {
			document.querySelector('section').style.display = 'block';
			taskId=<%=request.getParameter("taskId")%>;
		         userId=<%=request.getParameter("userId")%>;
		         if(userId!=null){addUser();}
			if(taskId!=null&&taskId!=""){
				getTaskDetail(taskId);
			}else{
				// alert("出错了！请联系管理员！");
				openApp('open',null);
			}
			
		}
		function openAppToDownload(){
			
			
			
		}
		
		function getTaskDetail(taskId){
			checkUserId();
			var timeStamp=new Date().getTime();
			var sign=getSign(timeStamp+userId+taskId);
			$.ajax({
				            type: "post",
				            url: interPath+"/getTaskDetail.do",
				async:false,
				            data: {
					         userId:userId,
					        	 taskId:taskId,
					        	 sign:sign,
					        	 timeStamp:timeStamp
			              	},
				            dataType: "json",
				            success: function (data) {
					         if(data.rtCode=='success'){
							//window.location.href=imagePath+"/yingjia/"+data.result.appURL;
					        	 openApp('download',data.result);
					         }else{
					        	 alert("亲，维护中，请稍后再来！");
					        	 }
					            },
				            error: function (msg) {
				alert("亲，维护中，请稍后再来！");
				            }
				});
		}
		function openApp(type,data){
//		    document.getElementById('direct').onclick= function(e){
//		        // 通过iframe的方式试图打开APP，如果能正常打开，会直接切换到APP，并自动阻止a标签的默认行为
//		        //否则打开a标签的href链接
		        var ifr = document.createElement('iframe');
		        if(type=='open'){
		        	   ifr.src = 'codrim://KrOffer.com/?type=1'+'<%=request.getQueryString()%>';
		        }else if(type=='download'){
		        	 ifr.src = 'codrim://KrOffer.com/?'+
		        			 'type=2&appURL='+data.appURL+'&appname='+data.taskName+'&appsize='+data.appSize+'&packagename='+data.appPname+'&appicon='+data.appIcon+'&stepId='+data.taskSteps[0].stepId;  
		        }
		        ifr.style.display= 'none';
		        document.body.appendChild(ifr);
		        window.setTimeout(function(){
		            document.body.removeChild(ifr);
		        },3000)
//		    };
		}
	</script>
	
</body>
</html>