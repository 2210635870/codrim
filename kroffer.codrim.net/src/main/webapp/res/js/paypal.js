var payType=null;
$(function() {
	$('body').css({
		'max-width': $(window).width(),
		overflow: 'hidden'
	});
if(type=='Z'){
	$("#headname").html("支付宝充值");
	$("#account").html("支付宝账号:");
}
if(type=='H'){
	$("#headname").html("话费充值");
	$("#account").html("手机号:");
}


	$(document).on('touchend', '.submit', function() {
		submit();
	});

	$(document).on('touchend', '.price', function() {
		$('.fixed').show();
		$('.selected').show();
		setTimeout(function() {
			$('.selected').css({
				bottom: 0
			});
		}, 100);
	});

	$(document).on('touchstart', '.cancel', function() {
		$(this).addClass('active');
	});

	$(document).on('touchend', '.cancel', function() {
		$(this).removeClass('active');
		$('.selected').css({
			bottom: '-70%'
		});
		$('.fixed').hide();
	});

	$(document).on('touchend', '.selected li', function() {
		var val = $(this).find('span').text();
		checkPrice(val)
		$('.selected').css({
			bottom: '-80%'
		});
		$('.fixed').hide();
	});

	$(document).on('touchend', '.close', function() {
		$('.tips').hide();
	});
	checkUserId();
	$(".top p").html(userPoints);
});

function submit(){
	if(payType==null){
		return ;
	}
	var username=$("#username").val();
	if(username==null||username==''){
		alert("请填写账号");
		return ;
	}
	checkUserId();
	var timeStamp=new Date().getTime();
	var sign=getSign(timeStamp+userId+payType+$("#username").val()+$("#username").val());
	
	$.ajax({
		            type: "post",
		            url: interPath+"/exchange.do",
					async:false,
		            data: {
			userId:userId,
			        	 type:payType,
			        	 zfb:$("#username").val(),
			        	 phone:$("#username").val(),
			        	 sign:sign,
			        	 timeStamp:timeStamp
	              	},
		            dataType: "json",
		            success: function (data) {
			         if(data.rtCode){
			        		$('.price p').text('');
			        		 payType=null;
			        		 alert("提交成功，请等待管理员审核！");
			         }else{
			        	 alert("亲，提现失败，请联系管理员！");
			        	 payType=null;
			        	 }
			            },
		            error: function (msg) {
		alert("亲，维护中，请稍后再来！");
		payType=null;
		            }
		});
	
	
}



function checkPrice(val){
	var coinType;
	if(type=="Z"){
	if(val==10){
		coinType=1;
	}
	if(val==20){
		coinType=2;
	}
	if(val==50){
		coinType=3;
	}
	}else if(type=="H"){
		if(val==10){
			coinType=4;
		}
		if(val==20){
			coinType=5;
		}
		if(val==50){
			coinType=6;
		}
	}
	$.ajax({
		            type: "post",
		            url: interPath+"/getCashSettingByType.do",
					async:false,
		            data: {
			userId:userId,
			        	 type:coinType
	              	},
		            dataType: "json",
		            success: function (data) {
			         if(data){
			        		$('.price p').text(val);
			        		 $(".tips").css("display","none");
			        		 payType=type+val;
			         }else{
			        	 $('.price p').text('');
			        	 $(".tips").css("display","block");
			        	 payType=null;
			        	 }
			            },
		            error: function (msg) {
		alert("亲，维护中，请稍后再来！");
		payType=null;
		            }
		});
	
	
}

