<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<style type="text/css">
.formdiv {
padding-bottom: 20px;
padding-top: 20px;
}
.formdiv  label{
padding-right: 15px;
font-size:20px
}
.uploadify{
padding-left: 856px;
margin-top: -33px;
}
#apkUpload{
padding-left: 940px;
}
.uploadify-button{
margin-left: 34px;
}
#screenshot-queue	.uploadify-queue-item{
    height: 189px;  
    border-radius: 0;
    margin-top: 0;
    padding: 0;
    position: relative;
    float: left;
width: 192px;
margin-right: 10px;
display: inline;
	}
	#icon-queue .uploadify-queue-item{
    height: 110px;  
    border-radius: 0;
    margin-top: 0;
    padding: 0;
    position: relative;
    float: left;
width: 110px;
margin-right: 10px;
display: inline;
	}
		#apk-queue .uploadify-queue-item{
    height: 110px;  
    border-radius: 0;
    margin-top: 0;
    padding: 0;
    position: relative;
    float: left;
width: 110px;
margin-right: 10px;
display: inline;
	}
	#icon-queue .uploadify-queue-item img{
	width: 90px;  height:90px;  overflow: hidden;  display: block;
    margin-top: 10px;
    margin-left: 10px;
	}
#screenshot-queue .uploadify-queue-item img{
	width: 158px;  height:180px;  overflow: hidden;  display: block;
    margin-top: 6px;
    margin-left: 17px;
	}

</style>


<div id="createProduct" style="text-align: center;display:none">

		<div class="formdiv">
							<label >客户: </label>
							<span >
							<input name="customerName" id="customerName"
							class="easyui-validatebox" validType=length[2,20] />
							</span>
						</div>

		<div class="formdiv">
							<label >产品: </label>
							<span >
							<input name="productName" id="productName"  class="easyui-validatebox" validType=length[2,20] />
							</span>
						</div>

		<div class="formdiv">
							<label >应用类别: </label>
							<span >
							<input name="productType" id="productType" />
							</span>
						</div>

	<div class="formdiv">
							<label style="padding-right: 157px;margin-top: 35px;">ICON: </label>
							<span >
							<input id="icon" type="file" />
							<div id="icon-queue" class="uploadify-queue" style="width: 100%;  height: 100px;padding-left: 842px; padding-top: 10px;">
</div>
							</span>
						</div>
<div class="formdiv">
							<label style="padding-right: 157px;margin-top: 35px;">截图: </label>
							<span >
							<input id="screenshot" type="file" />
<div id="screenshot-queue" class="uploadify-queue" style="width: 100%;  height: 100px;padding-left:  746px;; padding-top: 10px;">
</div>
							</span>
						</div>

<div class="formdiv" style="padding-top: 61px;">
							<label >描述: </label>
							<span >
							<textarea id="createDescribe" name="createDescribe" 
							rows="10" cols="40" style="overflow-x:hidden;margin-bottom: -129px;" 
							resize="none"  autofocus="autofocus" wrap="physical" align="left" validtype="length[0,300]" >
							</textarea>
							</span>
						</div>

<div class="formdiv" style="padding-top: 131px;">
							<label >Android包大小: </label>
							<span >
							<input name="androidPacketSize" id="androidPacketSize"
							class="easyui-numberbox easyui-validatebox" min="0.1" precision="1" missingMessage="请输入数字" /> MB
							</span>
						</div>
<div class="formdiv">
							<label >Android包下载地址:</label>
							<div>
								<input type="file" id="apkUpload">
								<span >
							<input name="androidPacketUrl" id="androidPacketUrl"
							class="easyui-validatebox" validType="url" />
							</span>
							<div id="apk-queue" class="uploadify-queue" style="width: 100%;  height: 100px;padding-left: 842px; padding-top: 10px;">
</div>
						</div>	
							
							
						</div>
<div class="formdiv">
							<label >IOS包大小: </label>
							<span >
							<input name="iosPacketSize" id="iosPacketSize"
						class="easyui-numberbox easyui-validatebox" min="0.1" precision="1" missingMessage="请输入数字" />MB
							</span>
						</div>						
						
<div class="formdiv">
							<label >IOS下载地址: </label>
							<span >
							<input name="iosPacketUrl" id="iosPacketUrl"
							class="easyui-validatebox" validType="url" />
							</span>
						</div>

	<a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="submit"
			onclick="add()">Save</a> 
				<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" id="submit"
			onclick="cancel()">Cancel</a> 
			


</div>