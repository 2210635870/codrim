$(function() {
	checkUserId();
	
	var winWidth = $(window).width();
	var winHeight = $(window).height();
	$(document).on('click', '.list li', function() {
		var $this = $(this);
		getTaskDetail($this.attr("id"));
		
		var $fixed = $('.fixed');
		var $content = $('.fixed .content');
//		var imgSrc =  $this.siblings('img').attr('src');
//		var title = $this.siblings('dl').find('dt').text();
//		var desc = $this.siblings('dl').find('dd').eq(0).text();
//		var num = $this.siblings('dl').find('.number').text();
		$fixed.show();
		$content.css({
			top: (winHeight-$content.height())/2,
			left: (winWidth-$content.width())/2
		});
//		$fixed.find('.icon').attr('src', imgSrc);
//		$fixed.find('header dt').text(title);
//		$fixed.find('header dd').text(desc);
//		$fixed.find('p span').text(num);
	});

	$(document).on('click', '.fixed .close', function() {
		$('.fixed').hide();
	});
	imagePath=imagePath+"/yingjia/";

	var page = 1;
	var scrollTop0 = 0;
	var windowHeight = $(window).height()- $('.bar').innerHeight();
	var scrollLoad = function(callback) {
		$(window).scroll(function() {
			var bodyHeight = $('body').height();
			var scrollTop = $('body').scrollTop();
			if((bodyHeight - windowHeight) == scrollTop && scrollTop > scrollTop0) {
				scrollTop0 = scrollTop;
				page++;
				callback(page,false);
			}
		})
	}
	getTaskList(page,true);
scrollLoad(getTaskList);
});

var list=null;
function getTaskList(page,flag){
	checkUserId();
	var timeStamp=new Date().getTime();
	var sign=getSign(timeStamp+userId+page+"2");
	$.ajax({
		            type: "post",
		            url: interPath+"/getTaskList.do",
					async:false,
		            data: {
			        page:page,
			         userId:userId,
			        	 type:"2",
			        	 sign:sign,
			        	 timeStamp:timeStamp
	              	},
		            dataType: "json",
		            success: function (data) {
			         if(data.rtCode=='success'){
			        	list=data.result;
			        	writeTaskList(flag);
			         }else{
			        	 alert("亲，维护中，请稍后再来！");
			        	 }
			            },
		            error: function (msg) {
		alert("亲，维护中，请稍后再来！");
		            }
		});
	
	
	
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
			        writeTaskDetail(data.result)
			         }else{
			        	 alert("亲，维护中，请稍后再来！");
			        	 }
			            },
		            error: function (msg) {
		alert("亲，维护中，请稍后再来！");
		            }
		});
}
function writeTaskDetail(detail){
	if(detail!=null){
		var html='<header>'+
		'<img src="'+imagePath+detail.appIcon+'" class="icon">'+
		'<dl>'+
			'<dt>'+detail.taskName+'</dt>'+
			'<dd>'+detail.taskRemark+'</dd>'+
		'</dl>'+
		'<img src="'+projectPath+'/res/images/close.png" class="close">'+
	'</header>'+
	'<section>'+
		'<img src="'+projectPath+'/res/images/wallet.jpg">'+
		'<p>完成下列条件获得<span>'+detail.taskPrice+'</span></p>'+
	'</section>'+
	'<div class="step">'+
		'<div class="num">1</div>'+
		'<p>'+detail.taskSteps[0].stepDesc+'</p>'+
	'</div>'+
	'<a href="javascript:void(0)" class="start" onclick="toDownload('+detail.taskId+')">开始任务</a>';
		$('.fixed .content').html(html);
	}
	
	
}
function toDownload(taskId){
	window.location.href=projectPath+'/views/redirect.jsp?taskId='+taskId+"&userId="+userId;
}

function writeTaskList(flag){
	var html='';
	if(list!=null){
	$.each(list,function(n,value){
		html=html+'<li data-url="" id="'+value.taskId+'">'+
		'<img src="'+imagePath+value.appIcon+'">'+
		'<dl>'+
			'<dt>'+value.taskName+'</dt>'+
			'<dd>'+value.taskRemark+'</dd>'+
		'</dl>'+
		'<div class="reward" >+ <span></span><span class="number">'+value.taskPrice+'</span></div>'+
	'</li>';
	});
	if(flag){
		$("#list").html(html);
	}else{
		$("#list").append(html);

	}
	}
}

