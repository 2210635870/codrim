var userId=null;
function checkUserId(){
   userId=localStorage.getItem("user");
	if(userId==null||userId==''){
		window.location.href="http://kroffer.codrim.net";
	}
}
function addUser(){
	localStorage.setItem("user",userId);//设置b为"isaac"
}
function isWeiXin() { 
	var ua = window.navigator.userAgent.toLowerCase(); 
	if(ua.match(/MicroMessenger/i) == 'micromessenger') { 
		return true; 
	} else { 
		return false; 
	} 
}


function getSign(str){
	return $.md5("C43A4DC12F8774733572EA813CEC3D3A"+str).toUpperCase();
}



