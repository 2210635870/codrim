$(function(){
	checkCookie();
	
	
	$(document).on('touchend', '#login', function() {
		userId=$("#userId").val();
		if(userId!=null&&userId!=""){
		addUser();
		getUserData();
		}else{
			alert("亲，请先填写用户ID!");
		}
	});
	
})

function checkCookie(){
	
	if(isWeiXin()){//微信浏览器打开
		difId=localStorage.getItem("difId");
		unionid=localStorage.getItem("unionid");
		$("#fixed").css("display","none");
		if(code=="null"||code==''){
			if(difId!=null&&difId!=""&&unionid!=null&&unionid!=""){
				getUserData();
			}else{
//				window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3cc220d7b8b62251&redirect_uri=Http%3A%2F%2Fkroffer.codrim.net%2F"
//					+"&response_type=code&scope=snsapi_userinfo&state=weixin#wechat_redirect";
				window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3cc220d7b8b62251&redirect_uri=Http%3A%2F%2Ftestkroffer.codrim.net%2F"
					+"&response_type=code&scope=snsapi_userinfo&state=weixin#wechat_redirect";
				
				//Http%3A%2F%2Ftestkroffer.codrim.net%2F
			}
		}else{
			getUserData();
		}
	}else{//不是微信浏览器
		userId= localStorage.getItem("user");
		if(userId==null){
			$("#fixed").css("display","block");
		}else{
			getUserData('y');
		}
	}
	}
var difId;
var unionid;
var id;
function toRedirect(){
	window.location.href=projectPath+"/views/redirect.jsp?&difId="+(difId == undefined ?  "" : difId)+"&unionid="+(unionid == undefined ? "" : unionid)+"&id="+(id == undefined ? "" : id);
}
function addUidAndDifId(){
	localStorage.setItem("difId",difId);//设置b为"isaac"
	localStorage.setItem("unionid",unionid);//设置b为"isaac"
}



function getUserData(skipRedirect){
	if(state==""||state=="null"||status==null){
		state=localStorage.getItem("difId");
	}
	
	var _userId = localStorage.getItem("user");
	_userId = (!_userId || _userId == 'null' || _userId=='undefined') ? '': _userId;
	var _unionid = localStorage.getItem("unionid");
	_unionid = (!_unionid || _unionid == 'null' || _unionid=='undefined') ? '': _unionid;
	
	$.ajax({
		 type: "post",
		            url:  projectPath + '/user.do',
		            dataType: "json",
		           async:false,
		          data:{
		        	  userId:_userId,
		        	  weixinCode:code,
		        	  unionid:_unionid,
		        	  difId:state
		          },
		            success: function (data) {
			         if(data.success){
		        		 difId=data.object.difId;
		        		 unionid=data.object.unionid;
		        		 id=data.object.id;
			        	 if(data.object.isBindingDeviceId==1){
			        		 userId=data.object.userId;
			        			$("#fixed").css("display","none");
			        			$("#pts").html(data.object.points);
			        			$("#tdp").html(data.object.todayPoints);
			        			addUser();
			        			addUidAndDifId();
			        	 }else{
			        		 
			        	//	 $("#fixed").css("display","block");
			        		 addUidAndDifId();
			        		 if(!skipRedirect){
			        			 toRedirect();
			        		 }
			        	 }
			         }else{
			        	 alert("维护中，请稍后再来！");
			    		 $("#fixed").css("display","block");
			         }
			            },
		            error: function (msg) {
			 alert("出错了，请稍后再来！");
		            }
	});
}

