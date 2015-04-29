<%@ page language="java" import="common.codrim.pojo.TbAdmin"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>



<div  id="dlg"  class="easyui-dialog"
		style="width: 500px; height: 500px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons" modal="true" pagination="true">
			<jsp:include page="./viewProductWithAdvert.jsp"></jsp:include>
			<div  class="easyui-accordion" style="overflow-y:auto;overflow-x:hidden;fit:true"  data-options="border:false,animate:true,multiple:true" >
			<div title="广告信息" class="divcss" data-options="collapsed:false" >
						<div><span>广告名：</span><span id="advertName"></span></div>	
					<div><span>平台：</span>
					<input type="radio" name="deviceplate" value="ios" checked onclick="checkSizeAndUrl('ios')">ios
					<input type="radio" name="deviceplate" value="android" onclick="checkSizeAndUrl('android')">android</div>		
							<div class="formdiv">
		<strong>结算相关</strong>
					<div class="fitem">
				<label>结算方式:</label>
				 <input name="settlementWay"  type="radio" value ="1" checked>CPA 
				<input name="settlementWay"   type="radio"  value="2" > CPS
			</div>
			<div >
				<label>效果定义:</label>
				  <input name="effectType"  type="radio" checked value="1" onclick="toAdvertName('注册')">注册
  <input name="effectType"  type="radio" value="2" onclick="toAdvertName('激活')"> 激活
   <input name="effectType" type="radio"  value="3" onclick="toAdvertName('首次消费成功')">首次消费成功
    <input name="effectType" type="radio" value="4" onclick="toAdvertName('两次以上消费成功')">两次以上消费成功
  <input name="effectType"  type="radio" value="5" onclick="toAdvertName('消费金额')"> 消费金额
			</div>
		<div class="fitem">
				<span>效果定义2(参考使用):</span>
				  <input name="effectTypeBack" type="radio" checked value="1">注册
  <input name="effectTypeBack"  type="radio" value="2" > 激活
   <input name="effectTypeBack"  type="radio"  value="3">首次消费成功
    <input name="effectTypeBack"  type="radio" value="4">两次以上消费成功
  <input name="effectTypeBack"  type="radio" value="5" > 消费金额
			</div>
			
			<div><span>接入单价：</span><input type="text" id="accessPrice" class="easyui-numberbox"
						required="true" min="0.1" precision="1"></div>
					
				<div >
				<span>合作方式:</span>
				 <input name="waysOfCooperation" id="waysOfCooperation" type="radio" value="1" checked>普通
				 <input name="waysOfCooperation" id="waysOfCooperation" type="radio" value="2">联运
			</div>
					<div><span>备注：</span> <textarea name="require"  id="require" cols="20" rows="5"></textarea></div>
	
</div>
					
					
					
						</div>
						
						
						</div>
						
	

<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="submit" onclick="submit()">保存</a> 
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>
</div>
