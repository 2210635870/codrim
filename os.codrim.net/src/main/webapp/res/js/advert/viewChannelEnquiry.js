var rows;
var nameArray;
	function getChannelEnquirys(dlgId,advertId){
		$.ajax({
			            type: "post",
			            url:  projectPath + '/getChannelEnquirysByAdvertId.do?advertId='+advertId,
			            dataType: "json",
			            success: function (data) {
				         if(data!=null&&data.total>0){
				             rows= data.rows;
				             nameArray= new Array();
				     		$.each(rows,function(n,value){
				    			var name=value.addName;
				    			var flag=true;
				    			for(var i=0;i<nameArray.length;i++){
				    				  if(name==nameArray[i]){
				    					  flag=false;
				    					  break;
				    				  }
				    				}
				    			if(flag){
				    			nameArray.push(name);
				    			}
				    		});
				     		var html='';
				     		for(var i=0;i<nameArray.length;i++){
				     			if(i==0){
				     				viewChannelEnquirysByAddName(nameArray[0],dlgId);
				     			}
			    				  html=html+'<input type="button" onclick="viewChannelEnquirysByAddName(\''+nameArray[i]+'\',\''+dlgId+'\')" value='+nameArray[i]+' id="'+dlgId+nameArray[i]+'">';
			    				}
				     		$("#"+dlgId+" #nameArray").html(html);
				     		channeNameCss(nameArray[0],dlgId);
				         }
				            },
			            error: function (msg) {
			alert("出错了！");
			            }
			});
	}
	function  viewChannelEnquirysByAddName(addName,dlgId){
		var jsonString='[';
		var i=0;
		$.each(rows,function(n,value){
			var name=value.addName;
			if(name==addName){
				jsonString=jsonString+'{"id":'+value.id+',"channelName":"'+value.channelName+'","isaccept":'+value.isaccept+',"price":'+value.price+',"remark":"'+value.remark+'","personInCharge":"'+value.personInCharge+'"},';
		i++;
			}
		
		});
		jsonString=jsonString.substring(0,jsonString.length-1);
	    jsonString=jsonString+']';
	    jsonString='{"total":'+i+',"rows":'+jsonString+'}';
		var dataJson= JSON.parse(jsonString); 
		$("#"+dlgId+" #channelEnquiryView").datagrid({
			data:dataJson,
			loadFilter: function(data){
			$.each(data.rows,function(n,value){
				if(value.isaccept==1){
					value.isaccept='接入';
				}
				if(value.isaccept==0){
					value.isaccept='不接入';
				}
			});
			return data;
			}
		});
		channeNameCss(addName,dlgId);
	}
	
	function channeNameCss(name,dlgId){
		
		for(var i=0;i<nameArray.length;i++){
	 		if(nameArray[i]==name){
	 			$("#"+dlgId+name).addClass("buttonCSS");
	 		}else{
	 			$("#"+dlgId+nameArray[i]).removeClass("buttonCSS");

	 		}
				}
	}
	