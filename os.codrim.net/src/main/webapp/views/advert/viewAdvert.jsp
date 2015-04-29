<%@ page language="java" import="common.codrim.pojo.TbAdmin"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script type="text/javascript">
function initViewAdvert(data){
	id=data.id;
	$("#viewAdvert #advertName").html(data.advertName);
	$("#viewAdvert #deviceplate").html(data.deviceplate);
	$("#viewAdvert #settlementWay").html(data.settlementWay);
	$("#viewAdvert #effectType").html(data.effectType);
	$("#viewAdvert #effectTypeBack").html(data.effectTypeBack);
	$("#viewAdvert #accessPrice").html(data.accessPrice);
	$("#viewAdvert #waysOfCooperation").html(data.waysOfCooperation);
	$("#viewAdvert #require").html(data.require);
	} 
</script>



			<div title="广告信息" class="divcss" data-options="collapsed:false"  id="viewAdvert">
						<div><span>广告名：</span><span id="advertName"></span></div>	
					<div><span>平台：</span><span id="deviceplate"></span></div>		
					<div >
		<strong>结算相关</strong>
					<div ><label>结算方式:</label><span id="settlementWay"></span></div>
			<div ><label>效果定义:</label><span id="effectType"></span></div>
		<div ><span>效果定义2(参考使用):</span><span id="effectTypeBack"></span></div>
			
			<div><span>接入单价：</span><span id="accessPrice"></span></div>
					
				<div >
				<span>合作方式:</span><span id="waysOfCooperation"></span></span>
			</div>
					<div><span>备注：</span><span id="require"></span></div>
	
</div>
						</div>
						
						
						

