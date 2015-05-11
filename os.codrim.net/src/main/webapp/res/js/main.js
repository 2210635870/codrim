window.onload = function(){
	$('#loading-mask').fadeOut();
}
var onlyOpenTitle="欢迎使用";// 不允许关闭的标签的标题

if(type==0){
	 _menus = {
			 "menus": [{
					"menuid": "1",
					"icon": "icon-sys",
					"menuname": "广告系列管理",
					"menus": [{
						"menuid": "11",
						"menuname": "广告类型",
						"icon": "icon-set",
						"url": "./advert/productType.jsp"
					},
					{
						"menuid": "12",
						"menuname": "参数配置",
						"icon": "icon-set",
						"url": "./advert/codrimLinkParam.jsp"
					},
					{
						"menuid": "13",
						"menuname": "广告系列",
						"icon": "icon-set",
						"url": "./advert/productList.jsp"
					},
					{
						"menuid": "14",
						"menuname": "广告",
						"icon": "icon-set",
						"url": "./advert/advertList.jsp"
					},
					{
						"menuid": "15",
						"menuname": "投放渠道",
						"icon": "icon-set",
						"url": "./advert/putChannelList.jsp"
					}
					]
	},
					{
						"menuid": "2",
						"menuname": "渠道管理",
						"icon": "icon-set",
						"url": "demo2.html",
						"menus": [{
							"menuid": "141",
							"menuname": "渠道类型",
							"icon": "icon-nav",
							"url": "./channel/channelType.jsp"
						},{
							"menuid": "141",
							"menuname": "渠道信息",
							"icon": "icon-nav",
							"url": "./channel/channel.jsp"
						}]
					},
					{
						"menuid": "3",
						"menuname": "客户管理",
						"icon": "icon-users",
						"url": "demo.html",
						"menus": [{
							"menuid": "153",
							"menuname": "客户信息",
							"icon": "icon-role",
							"url": "./customer/customerList.jsp"
						},{
							"menuid": "154",
							"menuname": "客户类型",
							"icon": "icon-role",
							"url": "./customer/customerTypeList.jsp"
						}
						]
					},
				{
					"menuid": "4",
					"icon": "icon-sys",
					"menuname": "报表统计",
					"menus": [
{
	"menuid": "41",
	"menuname": "总报表",
	"icon": "icon-nav",
	"url":"./report/report.jsp"
},	
{
						"menuid": "42",
						"menuname": "广告报表",
						"icon": "icon-nav",
						"child": [{
						"menuid": "422",
						"menuname": "报表",
						"icon": "icon-nav",
						"url": "./rankingsReport/rankingsReport.jsp",
					},	{
						"menuid": "423",
						"menuname": "明细",
						"icon": "icon-nav",
						"url": "./rankingsReport/rankingsReportDetails.jsp",
						
					}]	
					}]
				},
				{
					"menuid": "5",
					"menuname": "合同管理",
					"icon": "icon-set",
					"url": "demo2.html",
					"menus": [{
						"menuid": "141",
						"menuname": "合同信息",
						"icon": "icon-nav",
						"url": ""
					},{
						"menuid": "141",
						"menuname": "合同审核",
						"icon": "icon-nav",
						"url": "channelList.jsp"
					}]
				},
				{
					"menuid": "6",
					"menuname": "日报管理",
					"icon": "icon-set",
					"menus": [{
						"menuid": "61",
						"menuname": "日报列表",
						"icon": "icon-nav",
						"url": "./dailyReport/list.jsp"
					},{
						"menuid": "62",
						"menuname": "日报汇总",
						"icon": "icon-nav",
						"url": "./dailyReport/summary.jsp"
					}]
				},
				{
					"menuid": "5",
					"menuname": "设置",
					"icon": "icon-set",
					"url": "demo2.html",
					"menus": [{
						"menuid": "141",
						"menuname": "管理员设置",
						"icon": "icon-nav",
						"url": "./admin/admin.jsp"
					},{
						"menuid": "141",
						"menuname": "职位添加",
						"icon": "icon-nav",
						"url": "./admin/office.jsp"
					},{
						"menuid": "141",
						"menuname": "权限设置",
						"icon": "icon-nav",
						"url": "channelList.jsp"
					}]
				},
			{
					"menuid": "9",
					"icon": "icon-sys",
					"menuname": "盈+ 管理后台",
					"menus": [
					      	{
								"menuid": "95",
								"menuname": "游戏管理",
								"icon": "icon-set",
								"child": [
									{
										"menuid": "921",
										"menuname": "H5小游戏",
										"icon": "icon-set",
										"url":  "./wz/game/gameH5.jsp"
									}
								]
							},
						{
							"menuid": "91",
							"menuname": "审核模块",
							"icon": "icon-set",
							"child": [
								{
									"menuid": "911",
									"menuname": "任务审核",
									"icon": "icon-set",
									"url": "./wz/review/taskReviewList.jsp"
								},
								{
									"menuid": "912",
									"menuname": "兑换审核",
									"icon": "icon-set",
									"url": "./wz/review/exchangeRecordList.jsp"
								}
							]
						},
						{
							"menuid": "92",
							"menuname": "任务管理",
							"icon": "icon-set",
							"child": [
								{
									"menuid": "921",
									"menuname": "发布任务",
									"icon": "icon-set",
									"url": projectPath + "/wz/task/initAdd.do"
								},
								{
									"menuid": "922",
									"menuname": "任务管理",
									"icon": "icon-set",
									"url": "./wz/task/list.jsp"
								},
								{
									"menuid": "923",
									"menuname": "报表",
									"icon": "icon-set",
									"url": "./wz/report/taskReport.jsp"
								}
							]
						},
						{
							"menuid": "93",
							"menuname": "用户/团队管理",
							"icon": "icon-set",
							"child": [
								{
									"menuid": "931",
									"menuname": "用户管理",
									"icon": "icon-set",
									"url": "./wz/user/userList.jsp"
								},
								{
									"menuid": "932",
									"menuname": "团队管理",
									"icon": "icon-set",
									"url": "./wz/group/groupList.jsp"
								},
								{
									"menuid": "933",
									"menuname": "团队邀请码管理",
									"icon": "icon-set",
									"url": "./wz/group/groupInviteCodeList.jsp"
								}
							]
						},
						{
							"menuid": "94",
							"menuname": "设置",
							"icon": "icon-set",
							"child": [
							      	{
										"menuid": "942",
										"menuname": "图片设置",
										"icon": "icon-set",
										"url": "./wz/setting/bannerEdit.jsp"
									},
									{
										"menuid": "947",
										"menuname": "防刷设置",
										"icon": "icon-set",
										"url": "./wz/setting/taskNum.jsp"
									},
								{
									"menuid": "942",
									"menuname": "货币设置",
									"icon": "icon-set",
									"url": projectPath + "/wz/setting/initCurrencySetting.do"
								},
								{
									"menuid": "944",
									"menuname": "提现设置",
									"icon": "icon-set",
									"url":  "./wz/setting/coinCashSetting.jsp"
								},
								{
									"menuid": "943",
									"menuname": "版本管理",
									"icon": "icon-set",
									"url": projectPath + "/wz/setting/initAppUpgradeSetting.do"
								},
								{
									"menuid": "945",
									"menuname": "启动页图片管理",
									"icon": "icon-set",
									"url": projectPath + "/wz/setting/initBootBackgroundImgSetting.do"
								}
							]
						},
						{
							"menuid" : "95",
							"menuname": "锁屏设置",
							"icon": "icon-set",
							"child": [
					          {
					        	  "menuid": "951",
					        	  "menuname": "新闻管理",
					        	  "icon": "icon-set",
					        	  "url": "./wz/screenlock/news.jsp"
					          },
					          {
					        	  "menuid": "952",
					        	  "menuname": "壁纸管理",
					        	  "icon": "icon-set",
					        	  "url": "./wz/screenlock/wallpaper.jsp"
					          },
					          {
					        	  "menuid": "953",
					        	  "menuname": "推送设置",
					        	  "icon": "icon-set",
					        	  "url": projectPath + "/wz/task/initMsgPush.do"
					        	  
					          }]
						}
					]
				},
				//---------- 盈+ 管理后台 End ------------//
				]
			};	
}
// 登出
		function loginOut(){
			$.ajax({
				            type: "post",
				            url: projectPath+"/loginOut.do",
				            dataType: "json",
				            success: function (data) {
					location.href=projectPath+"/views/index.jsp";
					            },
				            error: function (msg) {
					$.messager.alert("出错了");
				            }
				});
		}
		
$(function(){
	InitLeftMenu();
	tabClose();
	tabCloseEven();

    $('#loginOut').click(function() {
        $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {
            if (r) {
            	loginOut();
            }
        });
    })
/*
 * 选择TAB时刷新内容 $('#tabs').tabs({ onSelect: function (title) { var currTab =
 * $('#tabs').tabs('getTab', title); var iframe =
 * $(currTab.panel('options').content);
 * 
 * var src = iframe.attr('src'); if(src) $('#tabs').tabs('update', { tab:
 * currTab, options: { content: createFrame(src)} });
 *  } });
 */
})

// 初始化左侧
function InitLeftMenu() {
	$("#nav").accordion({animate:false,fit:true,border:false});
	var selectedPanelname = '';
    $.each(_menus.menus, function(i, n) {
		var menulist ='';
		menulist +='<ul class="navlist">';
        $.each(n.menus, function(j, o) {
			menulist += '<li><div ><a ref="'+o.menuid+'" href="#" rel="' + o.url + '" ><span class="icon '+o.icon+'" >&nbsp;</span><span class="nav">' + o.menuname + '</span></a></div> ';
			if(o.child && o.child.length>0)
			{
				menulist += '<ul class="third_ul">';
				$.each(o.child,function(k,p){
					menulist += '<li><div><a ref="'+p.menuid+'" href="#" rel="' + p.url + '" ><span class="icon '+p.icon+'" >&nbsp;</span><span class="nav">' + p.menuname + '</span></a></div> </li>'
				});
				menulist += '</ul>';
			}
			menulist+='</li>';
        })
		menulist += '</ul>';
		$('#nav').accordion('add', {
            title: n.menuname,
            content: menulist,
				border:false,
            iconCls: 'icon ' + n.icon
        });
		if(i==0)
			selectedPanelname =n.menuname;
    });
	$('#nav').accordion('select',selectedPanelname);
// 添加点击事件
	$('.navlist li a').click(function(){
		var tabTitle = $(this).children('.nav').text();

		var url = $(this).attr("rel");
		var menuid = $(this).attr("ref");
		var icon = $(this).find('.icon').attr('class');

		var third = find(menuid);
		if(third && third.child && third.child.length>0)
		{
			$('.third_ul').slideUp();
			var ul =$(this).parent().next();
			if(ul.is(":hidden"))
				ul.slideDown();
			else
				ul.slideUp();
		}
		else{
			addTab(tabTitle,url,icon);
			$('.navlist li div').removeClass("selected");
			$(this).parent().addClass("selected");
		}
	}).hover(function(){
		$(this).parent().addClass("hover");
	},function(){
		$(this).parent().removeClass("hover");
	});
}
// 获取左侧导航的图标
function getIcon(menuid){
	var icon = 'icon ';
	$.each(_menus.menus, function(i, n) {
		 $.each(n.menus, function(j, o) {
		 	if(o.menuid==menuid){
				icon += o.icon;
			}
		 })
	})

	return icon;
}

function find(menuid){
	var obj=null;
	$.each(_menus.menus, function(i, n) {
		 $.each(n.menus, function(j, o) {
		 	if(o.menuid==menuid){
				obj = o;
			}
		 });
	});
	return obj;
}

function addTab(subtitle,url,icon){
	if(!$('#tabs').tabs('exists',subtitle)){
		$('#tabs').tabs('add',{
			title:subtitle,
			content:createFrame(url),
			closable:true,
			icon:icon
		});
	}else{
		$('#tabs').tabs('select',subtitle);
		$('#mm-tabupdate').click();
	}
	tabClose();
}

function createFrame(url)
{
	var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return s;
}

function tabClose()
{
	/* 双击关闭TAB选项卡 */
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	})
	/* 为选项卡绑定右键 */
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab",subtitle);
		$('#tabs').tabs('select',subtitle);
		return false;
	});
}


// 绑定右键菜单事件
function tabCloseEven() {

    $('#mm').menu({
        onClick: function (item) {
            closeTab(item.id);
        }
    });

    return false;
}

function closeTab(action)
{
    var alltabs = $('#tabs').tabs('tabs');
    var currentTab =$('#tabs').tabs('getSelected');
	var allTabtitle = [];
	$.each(alltabs,function(i,n){
		allTabtitle.push($(n).panel('options').title);
	})


    switch (action) {
        case "refresh":
            var iframe = $(currentTab.panel('options').content);
            var src = iframe.attr('src');
            $('#tabs').tabs('update', {
                tab: currentTab,
                options: {
                    content: createFrame(src)
                }
            })
            break;
        case "close":
            var currtab_title = currentTab.panel('options').title;
            $('#tabs').tabs('close', currtab_title);
            break;
        case "closeall":
            $.each(allTabtitle, function (i, n) {
                if (n != onlyOpenTitle){
                    $('#tabs').tabs('close', n);
				}
            });
            break;
        case "closeother":
            var currtab_title = currentTab.panel('options').title;
            $.each(allTabtitle, function (i, n) {
                if (n != currtab_title && n != onlyOpenTitle)
				{
                    $('#tabs').tabs('close', n);
				}
            });
            break;
        case "closeright":
            var tabIndex = $('#tabs').tabs('getTabIndex', currentTab);

            if (tabIndex == alltabs.length - 1){
                alert('亲，后边没有啦 ^@^!!');
                return false;
            }
            $.each(allTabtitle, function (i, n) {
                if (i > tabIndex) {
                    if (n != onlyOpenTitle){
                        $('#tabs').tabs('close', n);
					}
                }
            });

            break;
        case "closeleft":
            var tabIndex = $('#tabs').tabs('getTabIndex', currentTab);
            if (tabIndex == 1) {
                alert('亲，前边那个上头有人，咱惹不起哦。 ^@^!!');
                return false;
            }
            $.each(allTabtitle, function (i, n) {
                if (i < tabIndex) {
                    if (n != onlyOpenTitle){
                        $('#tabs').tabs('close', n);
					}
                }
            });

            break;
        case "exit":
            $('#closeMenu').menu('hide');
            break;
    }
}


// 弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}
