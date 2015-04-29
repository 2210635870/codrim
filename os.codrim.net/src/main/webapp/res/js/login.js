							$(function(){
						               $('.log input').each(function () {
					            if ($(this).attr('required') || $(this).attr('validType'))
					                $(this).validatebox();
					        })
								 });
							//键盘监听 回车提交
							window.document.onkeydown=function(){
								if(event.keyCode==13){
						    	submit();
						    };
							}
							function submit(){
								var flag = true;
								$('.log input').each(function () {
								    if ($(this).attr('required') || $(this).attr('validType')) {
								    if (!$(this).validatebox('isValid')) {
								        flag = false;
								        return;
								    }
								    }
								})
								if (flag){
									var email=$("#email").val();
									var pass=$("#pass").val();
									login(email,pass);
						            }else{
								    alert('验证失败！');
								}
							}
							function cancel(){
								$("#email").val("");
								$("#pass").val("");
							}
							function login(email,password){
								//location.href=projectPath+"/views/main.jsp";
								$.ajax({
								            type: "post",
								            url: projectPath+"/login.do",
								            data: {
									         email:email,
										     password:password
							              	},
								            dataType: "json",
								            success: function (data) {
									         if(data.res){
									        	location.href=projectPath+data.url;
									         }else{
									        	 alert("帐号或密码错误！");
									        	 }
									            },
								            error: function (msg) {
								                cancel();
								alert("帐号或密码错误！");
								            }
								});
							}
